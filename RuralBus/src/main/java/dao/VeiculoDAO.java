package dao;

import classes.Veiculo;
import classes.Assento;
import util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VeiculoDAO {
	

	public List<Veiculo> getVeiculos() {
        List<Veiculo> veiculos = new ArrayList<>();
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT id, placa, capacidade FROM veiculo")) {
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Veiculo veiculo = new Veiculo();
                    veiculo.setId(rs.getInt("id"));
                    veiculo.setPlaca(rs.getString("placa"));
                    veiculo.setCapacidade(rs.getInt("capacidade"));
                    
                    veiculos.add(veiculo);
                }
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao recuperar veículos: " + ex.getMessage());
        }
        
        return veiculos;
	}
    public Assento[] getAssentosParaVeiculo(int veiculoId, int capacidade) {
        Assento[] assentos = new Assento[capacidade];
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                 "SELECT id, numero FROM assento WHERE veiculo_id = ?")) {
            
            ps.setInt(1, veiculoId);
            try (ResultSet rs = ps.executeQuery()) {
                // Populate existing seats
                while (rs.next()) {
                    int numero = rs.getInt("numero");
                    int assentoId = rs.getInt("id");
                    
                    Assento assento = new Assento();
                    assento.setId(assentoId);
                    assento.setNumero(numero);
                    
                    // Adjust index to be 0-based
                    assentos[numero - 1] = assento;
                }
            }
            
            // Create any missing seats
            for (int i = 0; i < capacidade; i++) {
                if (assentos[i] == null) {
                    Assento novoAssento = new Assento();
                    novoAssento.setNumero(i + 1);
                    
                    // Persist the new seat in the database
                    try (PreparedStatement insertPs = conn.prepareStatement(
                        "INSERT INTO assento (numero, viagem_id, veiculo_id) VALUES (?, NULL, ?)", 
                        PreparedStatement.RETURN_GENERATED_KEYS)) {
                        
                        insertPs.setInt(1, i + 1);
                        insertPs.setInt(2, veiculoId);
                        
                        insertPs.executeUpdate();
                        
                        try (ResultSet generatedKeys = insertPs.getGeneratedKeys()) {
                            if (generatedKeys.next()) {
                                novoAssento.setId(generatedKeys.getInt(1));
                            }
                        }
                    }
                    
                    assentos[i] = novoAssento;
                }
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao recuperar assentos do veículo: " + ex.getMessage());
        }
        
        return assentos;
    }
    
	public String getPlacaVeiculo(int veiculoId) {
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement ps = conn.prepareStatement("SELECT placa FROM veiculo WHERE id = ?")) {

			ps.setInt(1, veiculoId);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return rs.getString("placa");
				}
			}
		} catch (SQLException ex) {
			System.err.println("Erro ao recuperar placa do veículo: " + ex.getMessage());
		}
		return null;
	}
    
    public boolean addVeiculo(Veiculo veiculo) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                 "INSERT INTO veiculo (placa, capacidade) VALUES (?, ?)", 
                 PreparedStatement.RETURN_GENERATED_KEYS)) {
            
            ps.setString(1, veiculo.getPlaca());
            ps.setInt(2, veiculo.getCapacidade());
            
            int rowsAffected = ps.executeUpdate();
            
            // Create seats for the new vehicle
            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int veiculoId = generatedKeys.getInt(1);
                        
                        try (PreparedStatement seatPs = conn.prepareStatement(
                            "INSERT INTO assento (numero, veiculo_id) VALUES (?, ?)")) {
                            
                            for (int i = 1; i <= veiculo.getCapacidade(); i++) {
                                seatPs.setInt(1, i);
                                seatPs.setInt(2, veiculoId);
                                seatPs.addBatch();
                            }
                            
                            seatPs.executeBatch();
                        }
                    }
                }
                return true;
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao inserir veículo: " + ex.getMessage());
        }
        return false;
    }

    public boolean delVeiculo(String placa) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM veiculo WHERE placa = ?")) {
            ps.setString(1, placa);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            System.err.println("Erro ao deletar veículo: " + ex.getMessage());
        }
        return false;
    }
    public Veiculo getVeiculoById(int veiculoId) {
    	   	try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT id, placa, capacidade FROM veiculo WHERE id = ?")) {
            
            ps.setInt(1, veiculoId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Veiculo veiculo = new Veiculo();
                    veiculo.setId(rs.getInt("id"));
                    veiculo.setPlaca(rs.getString("placa"));
                    veiculo.setCapacidade(rs.getInt("capacidade"));
                    return veiculo;
                }
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao recuperar veículo: " + ex.getMessage());
        }
        return null;
    }
}