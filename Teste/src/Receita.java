import java.time.LocalDate;

public class Receita extends Procedimento{

    private String consulta;
    private Medico medico;
    
    public void Preescrever(String consulta, LocalDate data, String descritivo,Medico medico){
        this.consulta = consulta;
        setData(data);
        setDescritivo(descritivo);
        setMedico(medico);
     }

     public String getConsulta() {
            return consulta;
     }

    public void consultar(){
        System.out.println("RECEITA>>>>>>>>>>>>>>>>");
        super.consultar();
        System.out.println("Consulta: "+ getConsulta());
        System.out.println("Médico responsável: " +medico.getNome()); 
    }
    public Medico getMedico() {
        return medico;
    }
    public void setMedico(Medico medico) {
        this.medico = medico;
    }
}