package dao;

import classes.Passagem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

    //listar passagens
    
}