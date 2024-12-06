package classes;

public class Passagem {
    String id;
    Cliente cliente;
    Viagem viagem;
    Assento assento;
    float preco;
    public Passagem() {
        
    }

    public Passagem(Cliente cliente, Viagem viagem, Assento assento, float preco) {
        this.cliente = cliente;
        this.viagem = viagem;
        this.assento = assento;
        this.preco = preco;
    }

	public float getPreco() {
		return preco;
	}
	
	public void setPreco(float preco) {
		this.preco = preco;
	}
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

	public int getClienteId() {
		return cliente.getId();
	}
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Viagem getViagem() {
        return viagem;
    }

    public void setViagem(Viagem viagem) {
        this.viagem = viagem;

    }

    public Assento getAssento() {
        return assento;
    }

    public void setAssento(Assento assento) {
        this.assento = assento;
    }

}