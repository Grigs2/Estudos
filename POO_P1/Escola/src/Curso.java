public class Curso {


private String disciplina;
private int codigo;
private int diaSemana;

public int getCodigo() {
    return codigo;
}
public int getDiaSemana() {
    return diaSemana;
}
public String getDisciplina() {
    return disciplina;
}
public void setCodigo(int codigo) {
    this.codigo = codigo;
}
public void setDiaSemana(int diaSemana) {
    this.diaSemana = diaSemana;
}
public void setDisciplina(String disciplina) {
    this.disciplina = disciplina;
}

public void consultar (){
    System.out.println("=========CURSO==========");
    System.out.println("Disciplina:"+getDisciplina());
    System.out.println("numero do curso:"+getCodigo());
    System.out.println("dia da semana:"+getDiaSemana());
}

public Curso(String disciplina, int codigo, int diaSemana){
    setCodigo(codigo);setDiaSemana(diaSemana);setDisciplina(disciplina);
}





}