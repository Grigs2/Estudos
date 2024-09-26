import java.time.LocalDate;

public abstract class Procedimento {

    private LocalDate data;
    private String descritivo;


    public LocalDate getData() {
        return data;
    }
    public String getDescritivo() {
        return descritivo;
    }
    
    public void setData(LocalDate data) {
        this.data = data;
    }
    public void setDescritivo(String descritivo) {
        this.descritivo = descritivo;
    }

    public void consultar(){
        System.out.println("Data:"+getData());
        System.out.println("Hora:"+getDescritivo());
    }

}
