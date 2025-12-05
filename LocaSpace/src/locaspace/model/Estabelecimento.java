package locaspace.model;

public class Estabelecimento {
    private Integer id;
    private String nome;
    private String endereco;
    private TipoEstabelecimento tipo;
    private Locatario locatario;

    public Estabelecimento(Integer id, String nome, String endereco, TipoEstabelecimento tipo, Locatario locatario) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.tipo = tipo;
        this.locatario = locatario;
    }

    // Getters exigidos por GerenciadorDados
    public Integer getId() { return id; }
    public String getNome() { return nome; }
    public String getEndereco() { return endereco; }
    public TipoEstabelecimento getTipo() { return tipo; }
    public Locatario getLocatario() { return locatario; }
}