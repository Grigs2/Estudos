package DTO;



public class Estoque {

    public int qtndProdutoEstoque;
    public String status;
    public Produto produto;


    public Estoque(Produto obj){
        qtndProdutoEstoque = 1;
        status = "Em estoque";
        produto = obj;
    }
//==================Getters e setters=======================
    public void setQtndProdutoEstoque(int qtndProdutoEstoque) {
        this.qtndProdutoEstoque = qtndProdutoEstoque;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public int getQtndProdutoEstoque() {
        return qtndProdutoEstoque;
    }
    public String getStatus() {
        return status;
    }
    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    public Produto getProduto() {
        return produto;
    }
//==================Getters e setters=======================

    public void consultar(){
        //TODO
    }

    public void saida(){
        //TODO
    }

    public void entrada(){
        //TODO
    }



}
