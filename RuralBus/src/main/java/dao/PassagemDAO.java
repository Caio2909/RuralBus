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
        String SQL = "INSERT INTO passagem (id, cliente_id, assento_id) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL)) {

            ps.setString(1, passagem.getId());
            ps.setLong(2, passagem.getCliente().getId());
            ps.setInt(3, passagem.getAssento().getId()); 

            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.err.println("Erro ao adicionar passagem: " + ex.getMessage());
            return false;
        }
    }

    public Passagem getPassagemById(String passagemId) {
        String SQL = """
            SELECT p.id, p.cliente_id, c.nome, a.id AS assento_id, a.numero, a.ocupado
            FROM passagem p
            JOIN cliente c ON p.cliente_id = c.id
            JOIN assento a ON p.assento_id = a.id
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

                    Assento assento = new Assento();
                    assento.setId(rs.getInt("assento_id"));
                    assento.setNumero(rs.getInt("numero"));
                    if (rs.getBoolean("ocupado")) assento.ocupar();

                    Passagem passagem = new Passagem();
                    passagem.setId(rs.getString("id"));
                    passagem.setCliente(cliente);
                    passagem.setAssento(assento);

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

    public List<Passagem> getAllPassagens() {
        String SQL = """
            SELECT p.id, c.id AS cliente_id, c.nome, a.id AS assento_id, a.numero, a.ocupado
            FROM passagem p
            JOIN cliente c ON p.cliente_id = c.id
            JOIN assento a ON p.assento_id = a.id
        """;
        List<Passagem> passagens = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("cliente_id"));
                cliente.setNome(rs.getString("nome"));

                Assento assento = new Assento();
                assento.setId(rs.getInt("assento_id"));
                assento.setNumero(rs.getInt("numero"));
                if (rs.getBoolean("ocupado")) assento.ocupar();

                Passagem passagem = new Passagem();
                passagem.setId(rs.getString("id"));
                passagem.setCliente(cliente);
                passagem.setAssento(assento);

                passagens.add(passagem);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao listar passagens: " + ex.getMessage());
        }

        return passagens;
    }
    
    public List<Passagem> getPassagensByClienteId(int clienteId) {
        List<Passagem> passagens = new ArrayList<>();
        String sql = """
            SELECT p.id, v.partida, v.destino, v.data_partida, v.data_chegada, v.preco, a.numero, ve.id AS veiculo_id, ve.placa, ve.capacidade
            FROM passagem p
            JOIN viagem v ON p.viagem_id = v.id
            JOIN assento a ON p.assento_id = a.id
            JOIN veiculo ve ON v.veiculo_id = ve.id
            WHERE p.cliente_id = ?
        """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, clienteId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Passagem passagem = new Passagem();
                Viagem viagem = new Viagem();
                Veiculo veiculo = new Veiculo();
                Assento assento = new Assento();

                viagem.setPartida(rs.getString("partida"));
                viagem.setDestino(rs.getString("destino"));
                viagem.setData_partida(rs.getTimestamp("data_partida"));
                viagem.setData_chegada(rs.getTimestamp("data_chegada"));
                viagem.setPreco(rs.getFloat("preco"));

                assento.setNumero(rs.getInt("numero"));

                veiculo.setId(rs.getInt("veiculo_id"));
                veiculo.setPlaca(rs.getString("placa"));
                veiculo.setCapacidade(rs.getInt("capacidade"));

                viagem.setVeiculo(veiculo);
                passagem.setViagem(viagem);
                passagem.setAssento(assento);
                passagem.setPreco(rs.getFloat("preco"));

                passagens.add(passagem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return passagens;
    }


}
