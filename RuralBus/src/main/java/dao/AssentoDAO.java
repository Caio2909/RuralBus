package dao;

import classes.Assento;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AssentoDAO {

    // Método para ler todos os assentos
    public ArrayList<Assento> readAll() {
        ArrayList<Assento> lista = new ArrayList<>();
        String SQL = "SELECT * FROM assento";  // SQL para selecionar todos os assentos
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Assento assento = new Assento(
                    rs.getInt("numero"),
                    rs.getBoolean("ocupado")
                );
                assento.setId(rs.getInt("id"));  // Supondo que existe uma coluna "id" no banco
                lista.add(assento);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao recuperar: " + ex.getMessage());
        }
        return lista;
    }

    // Método para inserir um novo assento
    public boolean addAssento(Assento assento) {
        String SQL = "INSERT INTO assento (numero, ocupado) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL)) {

            ps.setInt(1, assento.getNumero());
            ps.setBoolean(2, assento.isOcupado());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.err.println("Erro ao adicionar assento: " + ex.getMessage());
        }
        return false;
    }

    // Método para remover um assento pelo número
    public boolean removeAssento(int numero) {
        String SQL = "DELETE FROM assento WHERE numero = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL)) {

            ps.setInt(1, numero);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.err.println("Erro ao remover assento: " + ex.getMessage());
        }
        return false;
    }

    // Método para atualizar o status de ocupação do assento
    public boolean atualizarOcupacao(int numero, boolean ocupado) {
        String SQL = "UPDATE assento SET ocupado = ? WHERE numero = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL)) {

            ps.setBoolean(1, ocupado);
            ps.setInt(2, numero);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.err.println("Erro ao atualizar ocupação do assento: " + ex.getMessage());
        }
        return false;
    }
}
