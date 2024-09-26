import java.time.LocalDate;
import java.time.LocalTime;

public abstract class Agenda {

    private LocalDate data;
    private LocalTime hora;

    public LocalTime getHora() {
        return hora;
    }
    public LocalDate getData() {
        return data;
    }
    public void setData(LocalDate data) {
        this.data = data;
    }
    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public void cadastrar(LocalDate data, LocalTime hora){
        this.data = data;
        this.hora = hora;
     }

     public void Consultar(){
        System.out.println("CONSULTA>>>>>>>>>>>>>>>>");
        System.out.println("data:"+ getData());
        System.out.println("Hora:"+ getHora());
     }





}