package dao;

import classes.Admin;
import util.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminDAO {
	
    public ArrayList<Admin> readAll() {
        ArrayList<Admin> lista = new ArrayList<>();
        String SQL = "SELECT * FROM admin";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Admin adm = new Admin();
                adm.setId(rs.getInt("id"));
                adm.setNome(rs.getString("nome"));
                adm.setSenha(rs.getString("senha"));
                lista.add(adm);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao recuperar: " + ex.getMessage());
        }
        return lista;
    }
    
    public Admin getAdminByNome(String nome) {
        String SQL = "SELECT * FROM admin WHERE nome = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL)) {
        	
            ps.setString(1, nome);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Admin adm = new Admin();
                    adm.setId(rs.getInt("id"));
                    adm.setNome(rs.getString("nome"));
                    adm.setSenha(rs.getString("senha"));
                    return adm; 
                }
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao autenticar administrador: " + ex.getMessage());
        }
        return null; 
    }

    public boolean addAdmin(Admin adm) {
        String SQL = "INSERT INTO admin (nome, senha) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL)) {

            ps.setString(1, adm.getNome());
            ps.setString(2, adm.getSenha());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            System.err.println("Erro ao adicionar administrador: " + ex.getMessage());
            return false;
        }
    }

    public boolean removeAdmin(int id) {
        String SQL = "DELETE FROM admin WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL)) {

            ps.setLong(1, id);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; 
        } catch (SQLException ex) {
            System.err.println("Erro ao remover administrador: " + ex.getMessage());
            return false;
        }
    }
}
