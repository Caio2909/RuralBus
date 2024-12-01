package dao;

import classes.Veiculo;
import classes.Assento;
import util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;

public class VeiculoDAO {
	
    public ArrayList<Veiculo> readAll() {
        ArrayList<Veiculo> lista = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String SQL = "SELECT * FROM veiculo";
            conn = DatabaseConnection.getConnection();
            ps = conn.prepareStatement(SQL);
            rs = ps.executeQuery();

            while (rs.next()) {
                String placa = rs.getString("placa");
                int capacidade = rs.getInt("capacidade");

                Assento[] assentos = new Assento[capacidade];
                for (int i = 0; i < capacidade; i++) {
                    assentos[i] = new Assento(i + 1, false);
                }

                Veiculo veiculo = new Veiculo(placa, capacidade, assentos);
                lista.add(veiculo);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao recuperar veículos: " + ex.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
        return lista;
    }

    public Veiculo getVeiculoByPlaca(String placa) {
        Veiculo veiculo = null;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM veiculo WHERE placa = ?")) {
            ps.setString(1, placa);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int capacidade = rs.getInt("capacidade");

                Assento[] assentos = new Assento[capacidade];
                for (int i = 0; i < capacidade; i++) {
                    assentos[i] = new Assento(i + 1, false);
                }

                veiculo = new Veiculo(placa, capacidade, assentos);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao buscar veículo: " + ex.getMessage());
        }
        return veiculo;
    }

    public boolean addVeiculo(Veiculo veiculo) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO veiculo (placa, capacidade) VALUES (?, ?)")) {
            ps.setString(1, veiculo.getPlaca());
            ps.setInt(2, veiculo.getCapacidade());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
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
}
