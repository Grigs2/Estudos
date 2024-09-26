import java.time.LocalDate;

public class Exame extends Procedimento{

    private String consulta;
    private Medico medico;

    public void setMedico(Medico medico) {
        this.medico = medico;
    }
    public Medico getMedico() {
        return medico;
    }

    public void cadastrar(String consulta, LocalDate data, String descritivo, Medico medico){
        this.consulta = consulta;
        setData(data);
        setDescritivo(descritivo);
        setMedico(medico);
     }

     public String getConsulta() {
            return consulta;
     }
    
    public void consultar(){
        System.out.println("EXAME>>>>>>>>>>>>>>>>");
        super.consultar();
        System.out.println("Consulta:"+ getConsulta());
        System.out.println("Medico Responsavel: "+medico.getNome());
    }
    
}