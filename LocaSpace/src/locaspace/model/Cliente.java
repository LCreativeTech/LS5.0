package locaspace.model;

public class Cliente extends Usuario {
    private String nome;
    private String contato;
    private String cpf;

    public Cliente(Integer id, String login, String senha, String nome, String cpf) {
        super(id, login, senha);
        this.nome = nome;
        this.contato = "";
        this.cpf = cpf;
    }

    // Getters exigidos por GerenciadorDados
    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
}