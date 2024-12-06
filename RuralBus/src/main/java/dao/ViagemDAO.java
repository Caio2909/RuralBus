package dao;

import classes.Viagem;
import classes.Veiculo;
import util.DatabaseConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViagemDAO {
    public ViagemDAO() {
        
    }
    public boolean addViagem(Viagem viagem) {
        String SQL = "INSERT INTO viagem (partida, destino, data_partida, data_chegada, veiculo_id, preco) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL)) {
    
            ps.setString(1, viagem.getPartida());
            ps.setString(2, viagem.getDestino());
            ps.setDate(3, new java.sql.Date(viagem.getData_partida().getTime()));
            ps.setDate(4, new java.sql.Date(viagem.getData_chegada().getTime()));
            ps.setInt(5, viagem.getVeiculo().getId());
            ps.setBigDecimal(6, viagem.getPreco());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.err.println("Erro ao adicionar viagem: " + ex.getMessage());
            return false;
        }
    }
    //remover viagem
    public void removeViagem(int viagemId) {
        String SQL = "DELETE FROM viagem WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL)) {
    
            ps.setLong(1, viagemId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Erro ao remover viagem: " + ex.getMessage());
        }
    }
    
    //buscar viagem por ID
    public Viagem getViagemById(int viagemId) {
        String SQL = "SELECT v.partida, v.destino, v.data_partida, v.data_chegada, " +
                     "ve.placa, v.preco " +
                     "FROM viagem v " +                   
                     "JOIN veiculo ve ON v.veiculo_id = ve.id " +
                     "WHERE v.id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL)) {

            ps.setInt(1, viagemId);  // Ajustado para usar 'setInt' no ID da viagem
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                                    
                    Veiculo veiculo = new Veiculo();
                    veiculo.setPlaca(rs.getString("placa"));
 
                    Viagem viagem = new Viagem();
                    viagem.setPartida(rs.getString("partida"));
                    viagem.setDestino(rs.getString("destino"));
                    viagem.setData_partida(rs.getTimestamp("data_partida"));
                    viagem.setData_chegada(rs.getTimestamp("data_chegada"));
                    viagem.setPreco(rs.getBigDecimal("preco")); // Preenchendo o preço da viagem

                    viagem.setId(viagemId);
                    viagem.setVeiculo(veiculo);
                    System.out.println("Viagem encontrada: " + viagem);
                    return viagem;
                }
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao buscar viagem: " + ex.getMessage());
        }
        System.out.println("Viagem não encontrada");
        return null;
    }


    //Atualizar viagem
    public boolean updateViagem(int viagemId, Viagem viagem) {
        String SQL = "UPDATE viagem SET partida = ?, destino = ?, data_partida = ?, data_chegada = ?, " +
                     "preco = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL)) {
    
            ps.setString(1, viagem.getPartida());
            ps.setString(2, viagem.getDestino());
            ps.setDate(3, new java.sql.Date(viagem.getData_partida().getTime()));
            ps.setDate(4, new java.sql.Date(viagem.getData_chegada().getTime()));
            ps.setBigDecimal(5, viagem.getPreco());
            ps.setLong(6, viagemId);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            System.err.println("Erro ao atualizar viagem: " + ex.getMessage());
            return false;
        }
    }
    
    public List<Viagem> buscarViagens(String origem, String destino, Date dataPartida) {
        List<Viagem> viagens = new ArrayList<>();
        String SQL = "SELECT * FROM viagem WHERE partida = ? AND destino = ? AND DATE(data_partida) = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL)) {
            
            ps.setString(1, origem);
            ps.setString(2, destino);
            ps.setDate(3, new java.sql.Date(dataPartida.getTime()));
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Viagem viagem = new Viagem();
                    viagem.setId(rs.getInt("id"));
                    viagem.setPartida(rs.getString("partida"));
                    viagem.setDestino(rs.getString("destino"));
                    viagem.setData_partida(rs.getTimestamp("data_partida"));
                    viagem.setData_chegada(rs.getTimestamp("data_chegada"));
                    viagem.setPreco(rs.getBigDecimal("preco"));
                    
                    Veiculo veiculo = new Veiculo();
                    VeiculoDAO veiculoDAO = new VeiculoDAO();
                    veiculo.setPlaca(veiculoDAO.getPlacaVeiculo(rs.getInt("veiculo_id")));
                    veiculo.setId(rs.getInt("veiculo_id"));
                    viagem.setVeiculo(veiculo);
                    
                    viagens.add(viagem);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar viagens: " + e.getMessage());
            e.printStackTrace();
        }
        return viagens;
    }

}