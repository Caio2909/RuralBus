package classes;

import java.util.Date;

public class Viagem {
    String partida;
    String destino;
    Date data_partida;
    Date data_chegada;
    Passagem passagem;
    Veiculo veiculo;

    public Viagem(String partida, String destino, Date data_partida, Date data_chegada, Passagem passagem, Veiculo veiculo) {
        this.partida = partida;
        this.destino = destino;
        this.data_partida = data_partida;
        this.data_chegada = data_chegada;
        this.passagem = passagem;
        this.veiculo = veiculo;
    }

    public String getPartida() {
        return partida;
    }

    public void setPartida(String partida) {
        this.partida = partida;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Date getData_partida() {
        return data_partida;
    }

    public void setData_partida(Date data_partida) {
        this.data_partida = data_partida;
    }

    public Date getData_chegada() {
        return data_chegada;
    }

    public void setData_chegada(Date data_chegada) {
        this.data_chegada = data_chegada;
    }

    public Passagem getPassagem() {
        return passagem;
    }

    public void setPassagem(Passagem passagem) {
        this.passagem = passagem;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }


}
