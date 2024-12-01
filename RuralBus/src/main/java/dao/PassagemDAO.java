package dao;

import classes.Passagem;
import classes.Cliente;
import classes.Assento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet; 
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
public class PassagemDAO {
    private DataSource dataSource;
    public PassagemDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    // Adicionar passagem
    public boolean addPassagem(Passagem passagem) {
        String SQL = "INSERT INTO passagem (id, cliente_id, assento_numero) VALUES (?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL)) {

            ps.setString(1, passagem.getId());
            ps.setLong(2, passagem.getCliente().getId());
            ps.setInt(3, passagem.getAssento().getNumero());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // Retorna true se a inserção foi bem-sucedida
        } catch (SQLException ex) {
            System.err.println("Erro ao adicionar passagem: " + ex.getMessage());
            return false;
        }
    }

    // Buscar passagem por ID
    public Passagem getPassagemById(String passagemId) {
        String SQL = "SELECT p.id, p.cliente_id, p.assento_numero, c.nome, a.numero " +
                     "FROM passagem p " +
                     "JOIN cliente c ON p.cliente_id = c.id " +
                     "JOIN assento a ON p.assento_numero = a.numero " +
                     "WHERE p.id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL)) {

            ps.setString(1, passagemId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Recuperando os dados da passagem
                    Cliente cliente = new Cliente();
                    cliente.setId(rs.getLong("cliente_id"));
                    cliente.setNome(rs.getString("nome"));

                    Assento assento = new Assento();
                    assento.setNumero(rs.getInt("assento_numero"));

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
        return null; // Retorna null se nenhuma passagem for encontrada
    }

    // Remover passagem
    public void removePassagem(String passagemId) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM passagem WHERE id = ?")) {

            ps.setString(1, passagemId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Erro ao remover passagem: " + ex.getMessage());
        }
    }

    // Listar passagens
    public List<Passagem> getAllPassagens() {
        String SQL = "SELECT p.id, p.cliente_id, c.nome, a.numero as assento_numero, a.ocupado " +
                     "FROM passagem p " +
                     "JOIN cliente c ON p.cliente_id = c.id " +
                     "JOIN assento a ON p.assento_numero = a.numero";
        List<Passagem> passagens = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                // Criar cliente
                Cliente cliente = new Cliente();
                cliente.setId(rs.getLong("cliente_id"));
                cliente.setNome(rs.getString("nome"));

                // Criar assento
                Assento assento = new Assento();
                assento.setNumero(rs.getInt("assento_numero"));
                if (rs.getBoolean("ocupado")) {
                    assento.ocupar();
                } else {
                    assento.desocupar();
                }

                // Criar passagem
                Passagem passagem = new Passagem();
                passagem.setId(rs.getString("id"));
                passagem.setCliente(cliente);
                passagem.setAssento(assento);

                // Adicionar à lista
                passagens.add(passagem);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao listar passagens: " + ex.getMessage());
        }

        return passagens;
    }
}