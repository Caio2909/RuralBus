package classes;

public class Assento {
    private int id;
    private int numero;
    private Viagem viagem;
    private Veiculo veiculo;

    // Constructors
    public Assento() {}

    public Assento(int numero, Viagem viagem) {
        this.numero = numero;
        this.viagem = viagem;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Viagem getViagem() {
        return viagem;
    }

    public void setViagem(Viagem viagem) {
        this.viagem = viagem;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
}