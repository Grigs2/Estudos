package DTO;

public class ItensVenda {
    private int idItem;
    private int quantidade;
    private long idProduto;
    private long idVenda;
//=======================GET E SETTERS========================================
    public int getIdItem() {
        return idItem;
    }
    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    public long getIdProduto() {
        return idProduto;
    }
    public void setIdProduto(long idProduto) {
        this.idProduto = idProduto;
    }
    public long getIdVenda() {
        return idVenda;
    }
    public void setIdVenda(long idVenda) {
        this.idVenda = idVenda;
    }
    //=======================GET E SETTERS========================================
}
