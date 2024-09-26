import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Consulta extends Agenda {


    private String motivo;
    private String historico;
    private Recepcionista recepcionista;
    private List<Receita> receitas = new ArrayList<Receita>();
    private List<Exame> exames = new ArrayList<Exame>();
    private List<Paciente> pacientes = new ArrayList<Paciente>();
    private List<Medico> medicos = new ArrayList<Medico>();

    public void Marcar(LocalDate data, LocalTime hora, Medico medico, Paciente paciente, String motivo, String historico, Recepcionista recepcionista, Exame exame, Receita receita){

        setData(data);
        setHora(hora);
        addMedico(medico);
        addPaciente(paciente);
        addExame(exame);
        addReceita(receita);
        this.motivo = motivo;
        this.historico = historico;
        setRecpcionista(recepcionista);
     }

     public Recepcionista getRc() {
         return recepcionista;
     }

     public String getHistorico() {
         return historico;
     }
     
     public String getMotivo() {
         return motivo;
     }

    

     public void setRecpcionista(Recepcionista atendente){
        this.recepcionista = atendente;
     }

 
     public List<Receita> getReceitas() {
         return receitas;
     }public void addReceita(Receita r){
        this.receitas.add(r);
     }
     public void setReceitas(List<Receita> receitas) {
         this.receitas = receitas;
     }


     public List<Exame> getExames() {
         return exames;
     }
     public void addExame(Exame e){
        this.exames.add(e);
     }
     public void setExames(List<Exame> exames) {
         this.exames = exames;
     }



     public void setPacientes(List<Paciente> pacientes) {
         this.pacientes = pacientes;
     }
     public void addPaciente(Paciente p){
        this.pacientes.add(p);
     }
     public List<Paciente> getPacientes() {
         return pacientes;
     }


     public void setMedicos(List<Medico> medicos) {
         this.medicos = medicos;
     }
     public void addMedico(Medico m){
        this.medicos.add(m);
     }
     public List<Medico> getMedicos() {
         return medicos;
     }

    public void Consultar(){
        super.Consultar();
        System.out.println("Motivo:"+ getMotivo());
        System.out.println("historico:"+ getHistorico());
        System.out.println("Pacientes: "+this.pacientes);
     }



}