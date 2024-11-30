package classes;

public class Assento {
    Integer numero;
    boolean ocupado;

    public Assento(Integer numero, Boolean ocupado) {
        this.numero = numero;
        this.ocupado = ocupado;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(Boolean ocupado) {
        this.ocupado = ocupado;
    }

    public void ocupar() {
        this.ocupado = true;
    }

    public void desocupar() {
        this.ocupado = false;
    }
    
}