package dao;

import classes.Veiculo;
import classes.Assento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;

public class VeiculoDAO {
    private DataSource dataSource;

    public VeiculoDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // Método para ler todos os veículos
    public ArrayList<Veiculo> readAll() {
        ArrayList<Veiculo> lista = new ArrayList<>();
        try {
            String SQL = "SELECT * FROM veiculo";  // SQL para selecionar todos os veículos
            Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String placa = rs.getString("placa");
                int capacidade = rs.getInt("capacidade");  // Agora, só pegamos a capacidade

                // Criar o array de assentos com base na capacidade
                Assento[] assentos = new Assento[capacidade]; // Gerar assentos dinamicamente
                for (int i = 0; i < capacidade; i++) {
                    assentos[i] = new Assento(i + 1, false);  // Inicializando assentos (não ocupados)
                }

                Veiculo veiculo = new Veiculo(placa, capacidade, assentos);
                lista.add(veiculo);
            }
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            System.err.println("Erro ao recuperar veículos: " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println("Erro geral: " + ex.getMessage());
        }
        return lista;
    }
}