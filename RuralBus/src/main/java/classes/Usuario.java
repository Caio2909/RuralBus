package classes;


public class Usuario {

    private Long id;


    private String nome;

 
    private String email;


    private String senha;

 
    private String CPF;

    public Usuario() {
    }

    public Usuario(String nome, String email, String senha, String CPF) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.CPF = CPF;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}