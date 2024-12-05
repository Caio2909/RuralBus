package classes;

public class Veiculo {
	int id;
    String placa;
    int capacidade;
    Assento[] assentos;

    public Veiculo(){

    }


    public String getPlaca() {
        return placa;
    }
    
    public int getId() {
    	  return id;
    }

	public void setId(int id) {
		this.id = id;
	}
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public Assento[] getAssentos() {
        return assentos;
    }

    public void setAssentos(Assento[] assentos) {
        this.assentos = assentos;
    }
}