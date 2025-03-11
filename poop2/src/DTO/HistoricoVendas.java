package DTO;

import java.math.BigDecimal;

public class HistoricoVendas {
    private int codigo;
    private String nomeProduto;
    private int quantidade;
    private BigDecimal valorTotal;
    private int idProduto;
    private long idVenda;
    private long idPagamento;
    private long idCliente;
    private long idPedido;




//====================================Get e setters==========================================================
    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public String getNomeProduto() {
        return nomeProduto;
    }
    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    public BigDecimal getValorTotal() {
        return valorTotal;
    }
    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }
    public int getIdProduto() {
        return idProduto;
    }
    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }
    public long getIdVenda() {
        return idVenda;
    }
    public void setIdVenda(long idVenda) {
        this.idVenda = idVenda;
    }
    public long getIdPagamento() {
        return idPagamento;
    }
    public void setIdPagamento(long idPagamento) {
        this.idPagamento = idPagamento;
    }
    public long getIdCliente() {
        return idCliente;
    }
    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }
    public long getIdPedido() {
        return idPedido;
    }
    public void setIdPedido(long idPedido) {
        this.idPedido = idPedido;
    }
//====================================Get e setters==========================================================






}
