package dao;

import classes.Assento;
import classes.Veiculo;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;


public class AssentoDAO {
  
    public boolean atualizarOcupacao(Assento assento) {
        String SQL = "UPDATE assento SET ocupado = ? WHERE numero = ? and veiculo_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL)) {

            ps.setBoolean(1, assento.isOcupado());
            ps.setInt(2, assento.getNumero());
            ps.setInt(3, assento.getVeiculoId());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.err.println("Erro ao atualizar ocupação do assento: " + ex.getMessage());
        }
        return false;
    }
}
