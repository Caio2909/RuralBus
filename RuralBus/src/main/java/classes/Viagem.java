package classes;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Viagem {
	int id;
    String partida;
    String destino;
    Date data_partida;
    Date data_chegada;
    Veiculo veiculo;
    BigDecimal preco;
    public Viagem(){
        
    }

    public Viagem(String partida, String destino, Date data_partida, Date data_chegada, Veiculo veiculo, BigDecimal preco) {
        this.partida = partida;
        this.destino = destino;
        this.data_partida = data_partida;
        this.data_chegada = data_chegada;
        this.veiculo = veiculo;
        this.preco = preco;
    }

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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

    public String getDataPartidaFormatada() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        String data = dateFormat.format(data_partida);
        String hora = timeFormat.format(data_partida);
        return data + " " + hora;
    }
    
    public String getDataChegadaFormatada() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        String data = dateFormat.format(data_chegada);
        String hora = timeFormat.format(data_chegada);
        return data + " " + hora;
    }

	public String getHoraPartida() {
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
		String hora = timeFormat.format(data_partida);
		return hora;
	}
	public String getHoraChegada() {
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
		String hora = timeFormat.format(data_chegada);
		return hora;
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
    
	public BigDecimal getPreco() {
		return preco;
	}
	
	public void setPreco(BigDecimal f) {
		this.preco = f;
	}

    public void setData_chegada(Date data_chegada) {
        this.data_chegada = data_chegada;
    }


    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
    
	public String toString() {
    	return "Viagem [id=" + id + ", partida=" + partida + ", destino=" + destino + ", data_partida=" + data_partida;
	}

}