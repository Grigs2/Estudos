package DTO;

public class Fornecedor {
    private Long codigo;
    private String nome;
    private String cnpj;
    private String endereco;
    private String infoContato;

    // Construtor padrão
    public Fornecedor() {}

    // Construtor com parâmetros
    public Fornecedor(Long codigo, String nome, String cnpj, String endereco, String infoContato) {
        this.codigo = codigo;
        this.nome = nome;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.infoContato = infoContato;
    }

    // Getters e Setters
    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getInfoContato() {
        return infoContato;
    }

    public void setInfoContato(String infoContato) {
        this.infoContato = infoContato;
    }

    // Método toString() sobrescrito para retornar o nome do fornecedor
    @Override
    public String toString() {
        return nome;
    }
}
