package DTO;
import java.time.LocalDate;



public class Pedido {

    public int codigo;
    public LocalDate data;
    public double valorTotal;
    public Pagamento pagamento;

//==================Getters e setters=======================
    public int getCodigo() {
        return codigo;
    }
    public LocalDate getData() {
        return data;
    }
    
    public double getValorTotal() {
        return valorTotal;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public void setData(LocalDate data) {
        this.data = data;
    }
    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }
    public Pagamento getPagamento() {
        return pagamento;
    }
//========================================================


    public void consultar(){
        //TODO
    }



}
