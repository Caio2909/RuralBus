package dao;

import classes.Viagem;
import classes.Passagem;
import classes.Veiculo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

public class ViagemDAO {
    private DataSource dataSource;

    public ViagemDAO(DataSource dataSource) {
        this.dataSource = dataSource;

    }

    //adicionar viagem
    public boolean addViagem(Viagem viagem) {
        String SQL = "INSERT INTO viagem (partida, destino, data_partida, data_chegada, veiculo_id, preco) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL)) {
    
            ps.setString(1, viagem.getPartida());
            ps.setString(2, viagem.getDestino());
            ps.setDate(3, new java.sql.Date(viagem.getData_partida().getTime()));
            ps.setDate(4, new java.sql.Date(viagem.getData_chegada().getTime()));
            ps.setInt(5, viagem.getVeiculo().getId());
            ps.setDouble(6, viagem.getPreco());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.err.println("Erro ao adicionar viagem: " + ex.getMessage());
            return false;
        }
    }

    //remover viagem
    public void removeViagem(long viagemId) {
        String SQL = "DELETE FROM viagem WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
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
                     "p.id as passagem_id, ve.modelo, ve.placa " +
                     "FROM viagem v " +
                     "JOIN passagem p ON v.passagem_id = p.id " +
                     "JOIN veiculo ve ON v.veiculo_id = ve.id " +
                     "WHERE v.id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL)) {
    
            ps.setLong(1, viagemId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Passagem passagem = new Passagem();
                    passagem.setId(rs.getString("passagem_id"));
                    // Preencher mais detalhes da passagem, se necessário.
    
                    Veiculo veiculo = new Veiculo();
                    veiculo.setPlaca(rs.getString("placa"));
    
                    Viagem viagem = new Viagem();
                    viagem.setPartida(rs.getString("partida"));
                    viagem.setDestino(rs.getString("destino"));
                    viagem.setData_partida(rs.getDate("data_partida"));
                    
                    viagem.setData_chegada(rs.getDate("data_chegada"));
                    
                    viagem.setId(viagemId);
                    viagem.setVeiculo(veiculo);    
                    return viagem;
                }
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao buscar viagem: " + ex.getMessage());
        }
        return null;
    }

    //Atualizar viagem
    public boolean updateViagem(int viagemId, Viagem viagem) {
        String SQL = "UPDATE viagem SET partida = ?, destino = ?, data_partida = ?, data_chegada = ?, " +
                     "preco = ? WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL)) {
    
            ps.setString(1, viagem.getPartida());
            ps.setString(2, viagem.getDestino());
            ps.setDate(3, new java.sql.Date(viagem.getData_partida().getTime()));
            ps.setDate(4, new java.sql.Date(viagem.getData_chegada().getTime()));
            ps.setFloat(5, viagem.getPreco());
            ps.setLong(6, viagemId);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            System.err.println("Erro ao atualizar viagem: " + ex.getMessage());
            return false;
        }
    }

}