package dao;

import classes.Assento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;

public class AssentoDAO {
    private DataSource dataSource;

    public AssentoDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // Método para ler todos os assentos
    public ArrayList<Assento> readAll() {
        ArrayList<Assento> lista = new ArrayList<>();
        try {
            String SQL = "SELECT * FROM assento";  // SQL para selecionar todos os assentos
            Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Assento assento = new Assento(
                    rs.getInt("numero"), // Atribuindo número do assento
                    rs.getBoolean("ocupado") // Atribuindo se o assento está ocupado ou não
                );
                lista.add(assento);
            }
            ps.close();
            conn.close(); // Fechar a conexão
        } catch (SQLException ex) {
            System.err.println("Erro ao recuperar: " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println("Erro geral: " + ex.getMessage());
        }
        return lista;
    }

    // Método para inserir um novo assento
    public boolean addAssento(Assento assento) {
        String SQL = "INSERT INTO assento (numero, ocupado) VALUES (?, ?)"; 
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL)) {

            ps.setInt(1, assento.getNumero());
            ps.setBoolean(2, assento.isOcupado());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            System.err.println("Erro ao adicionar assento: " + ex.getMessage());
        }
        return false;
    }

    // Método para remover um assento
    public boolean removeAssento(int numero) {
        String SQL = "DELETE FROM assento WHERE numero = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL)) {

            ps.setInt(1, numero);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            System.err.println("Erro ao remover assento: " + ex.getMessage());
        }
        return false;
    }
}