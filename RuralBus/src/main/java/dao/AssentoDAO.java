package dao;

import classes.Assento;
import classes.Viagem;
import util.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AssentoDAO {
	
    public Assento criarAssento(Assento assento) {
        String SQL = "INSERT INTO assento (numero, viagem_id) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL, PreparedStatement.RETURN_GENERATED_KEYS)) {
            
            ps.setInt(1, assento.getNumero());
            ps.setInt(2, assento.getViagem().getId());
            
            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        assento.setId(generatedKeys.getInt(1));
                    }
                }
            }
            return assento;
        } catch (SQLException ex) {
            System.err.println("Erro ao criar assento: " + ex.getMessage());
            return null;
        }
    }

    public Assento getAssentoPorNumeroEViagem(int numero, Viagem viagem) {
        String SQL = "SELECT id FROM assento WHERE numero = ? AND viagem_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL)) {
            
            ps.setInt(1, numero);
            ps.setInt(2, viagem.getId());
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Assento assento = new Assento(numero, viagem);
                    assento.setId(rs.getInt("id"));
                    return assento;
                }
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao buscar assento: " + ex.getMessage());
        }
        return null;
    }

    public boolean isOcupado(Assento assento) {
        String SQL = "SELECT COUNT(*) FROM passagem p JOIN assento a ON p.assento_id = a.id where a.numero = ? and a.viagem_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL)) {
            ps.setInt(1, assento.getNumero());
            ps.setInt(2, assento.getViagem().getId());
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao verificar ocupação do assento: " + ex.getMessage());
        }
        return false;
    }
    public List<Integer> getAssentosOcupadosPorViagem(int viagemId) {
        List<Integer> ocupados = new ArrayList<>();
        String SQL = "SELECT numero FROM assento WHERE viagem_id = ? AND id IN (SELECT assento_id FROM passagem)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL)) {
            
            ps.setInt(1, viagemId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ocupados.add(rs.getInt("numero"));
                }
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao buscar assentos ocupados: " + ex.getMessage());
        }
        return ocupados;
    }

}