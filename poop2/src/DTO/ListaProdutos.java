package DTO;
import java.util.ArrayList;

public class ListaProdutos {
    public long codigo;
    public ArrayList<Produto> produtos = new ArrayList<>();
    public ArrayList<Integer> qntdProduto = new ArrayList<>();
    public ArrayList<Double> valorTotalProduto = new ArrayList<>();

//==================Getters e setters=======================
    public long getCodigo() {
        return codigo;
    }
    public ArrayList<Produto> getProdutos() {
        return produtos;
    }
   public ArrayList<Integer> getQntdProduto() {
       return qntdProduto;
   }
    public ArrayList<Double> getValorTotalProduto() {
        return valorTotalProduto;
    }
    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }
    public void setProdutos(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }
    public void setQntdProduto(ArrayList<Integer> qntdProduto) {
        this.qntdProduto = qntdProduto;
    }
    public void setValorTotalProduto(ArrayList<Double> valorTotalProduto) {
        this.valorTotalProduto = valorTotalProduto;
    }
 //==================Getters e setters=======================
 
 
}
