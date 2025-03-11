public class Aluno extends Cadastro{

    Matricula matricula;
    Turma turma;
    Nota nota;

public void matricular(int n, int ano, int semestre, Curso curso){
    this.matricula = new Matricula(n,ano,semestre,this, curso);
}
public void setNota(Nota nota) {
    this.nota = nota;
}
public Nota getNota() {
    return nota;
}

public void selecionarCurso(Curso curso){
    this.matricula.setCurso(curso);
}

public void efetuarPagamento(){
    //TODO
}
public Aluno(String nome, String cpf){

    setNome(nome);
    setCpf(cpf);
}
public void setTurma(Turma turma) {
    this.turma = turma;
}
public Turma getTurma() {
    return turma;
}

@Override
public void consultar() {
    System.out.println("============ALUNO==============");
    super.consultar();
    System.out.println("Numero Matricula: "+this.matricula.getNumeroMatricula());
    System.out.println("Turma matriculada:"+this.turma.curso.getDisciplina());
    System.out.println("Professor da Turma: "+this.turma.professor.getNome());
    System.out.println("Média atual: "+this.nota.media());
}

}
