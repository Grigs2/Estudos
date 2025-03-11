package DTO;

public class Produto {
    private Long idProduto;
    private String nome;
    private double precoVenda;
    private double precoCompra;
    private String categoria;
    private Fornecedor fornecedor;
    private int quantidade;

    // Construtor padrão
    public Produto() {}

    // Construtor com parâmetros
    public Produto(String nome, double precoVenda, double precoCompra, String categoria, Fornecedor fornecedor, int quantidade) {
        this.nome = nome;
        this.precoVenda = precoVenda;
        this.precoCompra = precoCompra;
        this.categoria = categoria;
        this.fornecedor = fornecedor;
        this.quantidade = quantidade;
    }

    // Getters e Setters
    public Long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(double precoVenda) {
        this.precoVenda = precoVenda;
    }

    public double getPrecoCompra() {
        return precoCompra;
    }

    public void setPrecoCompra(double precoCompra) {
        this.precoCompra = precoCompra;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Long getCodigo() {
        return idProduto;
    }

    @Override
    public String toString() {
        return nome;
    }
}
