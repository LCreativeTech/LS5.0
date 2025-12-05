package locaspace.model;

public class Unidade {
    private String nome;
    private Estabelecimento estabelecimento;
    private TipoEstabelecimento tipo;
    private double precoDiaria;
    private boolean disponivel;

    // Construtor compatível com testes e UI
    public Unidade(String nome, Estabelecimento estabelecimento, TipoEstabelecimento tipo, double precoDiaria, boolean disponivel) {
        this.nome = nome;
        this.estabelecimento = estabelecimento;
        this.tipo = tipo;
        this.precoDiaria = precoDiaria;
        this.disponivel = disponivel;
    }

    // Getters exigidos por GerenciadorDados (ID simulado)
    public Integer getId() {
        // ATENÇÃO: ID SIMULADO. Para persistência robusta, o ID deve ser um atributo real.
        return this.hashCode();
    }

    public String getNome() { return nome; }
    public Estabelecimento getEstabelecimento() { return estabelecimento; }
    public TipoEstabelecimento getTipo() { return tipo; }

    public double getPrecoDiaria() { // Método exigido pelo GerenciadorDados
        return precoDiaria;
    }

    // Getters e Setters para testes
    public boolean isDisponivel() { return disponivel; }
    public void setDisponivel(boolean disponivel) { this.disponivel = disponivel; }
}