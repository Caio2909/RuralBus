package dao;

import classes.Passagem;
import classes.Veiculo;
import classes.Viagem;
import util.DatabaseConnection;
import classes.Cliente;
import classes.Assento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PassagemDAO {
    public boolean addPassagem(Passagem passagem) {
        String SQL = "INSERT INTO passagem (cliente_id, viagem_id, assento_id) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL)) {

            ps.setLong(1, passagem.getCliente().getId());
            ps.setInt(2, passagem.getViagem().getId());
            ps.setInt(3, passagem.getAssento().getId()); 

            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro ao adicionar passagem: " + ex.getMessage());
            return false;
        }
    }

    public Passagem getPassagemById(String passagemId) {
        String SQL = """
            SELECT p.id, p.cliente_id, c.nome, 
                   p.viagem_id, v.partida, v.destino, v.preco, v.data_partida, v.data_chegada,
                   p.assento_id, a.numero,
                   ve.id AS veiculo_id, ve.placa, ve.capacidade
            FROM passagem p
            JOIN cliente c ON p.cliente_id = c.id
            JOIN viagem v ON p.viagem_id = v.id
            JOIN assento a ON p.assento_id = a.id
            JOIN veiculo ve ON v.veiculo_id = ve.id
            WHERE p.id = ?
        """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL)) {

            ps.setString(1, passagemId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Cliente cliente = new Cliente();
                    cliente.setId(rs.getInt("cliente_id"));
                    cliente.setNome(rs.getString("nome"));

                    Veiculo veiculo = new Veiculo();
                    veiculo.setId(rs.getInt("veiculo_id"));
                    veiculo.setPlaca(rs.getString("placa"));
                    veiculo.setCapacidade(rs.getInt("capacidade"));

                    Viagem viagem = new Viagem();
                    viagem.setId(rs.getInt("viagem_id"));
                    viagem.setPartida(rs.getString("partida"));
                    viagem.setDestino(rs.getString("destino"));
                    viagem.setPreco(rs.getBigDecimal("preco"));
                    viagem.setData_partida(rs.getTimestamp("data_partida"));
                    viagem.setData_chegada(rs.getTimestamp("data_chegada"));
                    viagem.setVeiculo(veiculo);

                    Assento assento = new Assento();
                    assento.setId(rs.getInt("assento_id"));
                    assento.setNumero(rs.getInt("numero"));

                    Passagem passagem = new Passagem();
                    passagem.setId(rs.getString("id"));
                    passagem.setCliente(cliente);
                    passagem.setViagem(viagem);
                    passagem.setAssento(assento);
                    passagem.setPreco(viagem.getPreco().floatValue());

                    return passagem;
                }
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao buscar passagem: " + ex.getMessage());
        }
        return null;
    }
    public void removePassagem(String passagemId) {
        String SQL = "DELETE FROM passagem WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL)) {

            ps.setString(1, passagemId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Erro ao remover passagem: " + ex.getMessage());
        }
    }
   
    public List<Passagem> getPassagensByClienteId(long clienteId) {
        List<Passagem> passagens = new ArrayList<>();
        String SQL = """
            SELECT p.id, 
                   c.id AS cliente_id, c.nome, c.email, c.CPF,
                   v.id AS viagem_id, v.partida, v.destino, v.data_partida, v.data_chegada, v.preco, 
                   a.id AS assento_id, a.numero, 
                   ve.id AS veiculo_id, ve.placa, ve.capacidade,
                   (SELECT COUNT(*) FROM passagem WHERE assento_id = a.id) AS assento_ocupado
            FROM passagem p
            JOIN cliente c ON p.cliente_id = c.id
            JOIN viagem v ON p.viagem_id = v.id
            JOIN assento a ON p.assento_id = a.id
            JOIN veiculo ve ON v.veiculo_id = ve.id
            WHERE p.cliente_id = ?
            ORDER BY v.data_partida
        """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setLong(1, clienteId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Veiculo veiculo = new Veiculo();
                    veiculo.setId(rs.getInt("veiculo_id"));
                    veiculo.setPlaca(rs.getString("placa"));
                    veiculo.setCapacidade(rs.getInt("capacidade"));

                    Viagem viagem = new Viagem();
                    viagem.setId(rs.getInt("viagem_id"));
                    viagem.setPartida(rs.getString("partida"));
                    viagem.setDestino(rs.getString("destino"));
                    viagem.setData_partida(rs.getTimestamp("data_partida"));
                    viagem.setData_chegada(rs.getTimestamp("data_chegada"));
                    viagem.setPreco(rs.getBigDecimal("preco"));
                    viagem.setVeiculo(veiculo);

                    Assento assento = new Assento();
                    assento.setId(rs.getInt("assento_id"));
                    assento.setNumero(rs.getInt("numero"));
                    assento.setViagem(viagem);
                    
                    
                    Cliente cliente = new Cliente();
                    cliente.setId(rs.getInt("cliente_id"));
                    cliente.setNome(rs.getString("nome"));
                    cliente.setEmail(rs.getString("email"));
                    cliente.setCPF(rs.getString("CPF"));

                    Passagem passagem = new Passagem();
                    passagem.setId(rs.getString("id"));
                    passagem.setCliente(cliente);
                    passagem.setViagem(viagem);
                    passagem.setAssento(assento);
                    passagem.setPreco(viagem.getPreco().floatValue());

                    passagens.add(passagem);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar passagens do cliente: " + e.getMessage());
        }
        return passagens;
    }
}