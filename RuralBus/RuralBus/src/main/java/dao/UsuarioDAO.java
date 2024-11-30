package dao;

import classes.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;

public class UsuarioDAO {
    private DataSource dataSource;

    public UsuarioDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // Método para listar todos os usuarios
    public ArrayList<Usuario> readAll() {
        ArrayList<Usuario> lista = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM usuario");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Usuario usu = new Usuario();
                usu.setId(rs.getLong("id"));
                usu.setNome(rs.getString("nome"));
                usu.setEmail(rs.getString("email"));
                usu.setSenha(rs.getString("senha"));
                lista.add(usu);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao recuperar: " + ex.getMessage());
        }
        return lista;
    }

    // Método para adicionar um novo usuario
    public boolean addUsuario(Usuario usuario) {
        String SQL = "INSERT INTO usuario (nome, email, senha) VALUES (?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL)) {

            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getSenha());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // Retorna true se a inserção foi bem-sucedida
        } catch (SQLException ex) {
            System.err.println("Erro ao adicionar usuario: " + ex.getMessage());
            return false;
        }
    }

    // Método para remover um usuario pelo ID
    public boolean removeUsuario(long id) {
        String SQL = "DELETE FROM usuario WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL)) {

            ps.setLong(1, id);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // Retorna true se a remoção foi bem-sucedida
        } catch (SQLException ex) {
            System.err.println("Erro ao remover usuario: " + ex.getMessage());
            return false;
        }
    }
}