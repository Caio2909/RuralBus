package classes;

public class Passagem {
    String id;
    Cliente cliente;
    Viagem viagem;
    Assento assento;

    public Passagem() {
        
    }

    public Passagem(Cliente cliente, Viagem viagem, Assento assento) {
        this.cliente = cliente;
        this.viagem = viagem;
        this.assento = assento;

        /*
         * Inicializa o Assento da passagem
         */

        this.assento.ocupar();
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