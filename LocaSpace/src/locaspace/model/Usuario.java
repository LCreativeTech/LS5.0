package locaspace.model;

public abstract class Usuario {
    private Integer id;
    private String login;
    private String senha;

    public Usuario(Integer id, String login, String senha) {
        this.id = id;
        this.login = login;
        this.senha = senha;
    }

    // Getters exigidos por GerenciadorDados
    public Integer getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }
}