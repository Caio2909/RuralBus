package dao;

import classes.Cliente;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteDAO {
	  private static final Logger LOGGER = Logger.getLogger(ClienteDAO.class.getName());

	    // Método para listar todos os clientes
	    public ArrayList<Cliente> readAll() {
	        ArrayList<Cliente> lista = new ArrayList<>();
	        String SQL = "SELECT * FROM cliente";
	        
	        try (Connection conn = DatabaseConnection.getConnection();
	             PreparedStatement ps = conn.prepareStatement(SQL);
	             ResultSet rs = ps.executeQuery()) {

	            while (rs.next()) {
	                Cliente cli = extractClienteFromResultSet(rs);
	                lista.add(cli);
	            }
	        } catch (SQLException ex) {
	            LOGGER.log(Level.SEVERE, "Erro ao recuperar clientes", ex);
	        }
	        return lista;
	    }

	    // Método para adicionar um novo cliente
	    public boolean addCliente(Cliente cliente) {
	        String SQL = "INSERT INTO cliente (nome, email, senha, cpf) VALUES (?, ?, ?, ?)";
	        
	        try (Connection conn = DatabaseConnection.getConnection();
	             PreparedStatement ps = conn.prepareStatement(SQL)) {

	            ps.setString(1, cliente.getNome());
	            ps.setString(2, cliente.getEmail());
	            ps.setString(3, cliente.getSenha());
	            ps.setString(4, cliente.getCPF());

	            int rowsAffected = ps.executeUpdate();
	            return rowsAffected > 0;
	        } catch (SQLException ex) {
	            LOGGER.log(Level.SEVERE, "Erro ao adicionar cliente", ex);
	            return false;
	        }
	    }

	    // Método para remover um cliente pelo ID
	    public boolean removeCliente(long id) {
	        String SQL = "DELETE FROM cliente WHERE id = ?";
	        
	        try (Connection conn = DatabaseConnection.getConnection();
	             PreparedStatement ps = conn.prepareStatement(SQL)) {

	            ps.setLong(1, id);

	            int rowsAffected = ps.executeUpdate();
	            return rowsAffected > 0;
	        } catch (SQLException ex) {
	            LOGGER.log(Level.SEVERE, "Erro ao remover cliente", ex);
	            return false;
	        }
	    }

	    // Método para buscar um cliente pelo ID
	    public Cliente getClienteById(long id) {
	        Cliente cliente = null;
	        String SQL = "SELECT * FROM cliente WHERE id = ?";
	        
	        try (Connection conn = DatabaseConnection.getConnection();
	             PreparedStatement ps = conn.prepareStatement(SQL)) {

	            ps.setLong(1, id);

	            try (ResultSet rs = ps.executeQuery()) {
	                if (rs.next()) {
	                    cliente = extractClienteFromResultSet(rs);
	                }
	            }
	        } catch (SQLException ex) {
	            LOGGER.log(Level.SEVERE, "Erro ao recuperar cliente por ID", ex);
	        }
	        return cliente;
	    }

	    // Método para buscar um cliente por email
	    public Cliente getClienteByEmail(String email) {
	        Cliente cliente = null;
	        String SQL = "SELECT * FROM cliente WHERE email = ?";
	        
	        try (Connection conn = DatabaseConnection.getConnection();
	             PreparedStatement ps = conn.prepareStatement(SQL)) {

	            ps.setString(1, email);

	            try (ResultSet rs = ps.executeQuery()) {
	                if (rs.next()) {
	                    cliente = extractClienteFromResultSet(rs);
	                }
	            }
	        } catch (SQLException ex) {
	            LOGGER.log(Level.SEVERE, "Erro ao recuperar cliente por email", ex);
	        }
	        return cliente;
	    }

	    // Método auxiliar para extrair Cliente de um ResultSet
	    private Cliente extractClienteFromResultSet(ResultSet rs) throws SQLException {
	        Cliente cli = new Cliente();
	        cli.setId(rs.getLong("id"));
	        cli.setNome(rs.getString("nome"));
	        cli.setEmail(rs.getString("email"));
	        cli.setSenha(rs.getString("senha"));
	        cli.setCPF(rs.getString("CPF"));
	        return cli;
	    }


    
}