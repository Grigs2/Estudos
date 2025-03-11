public class Matricula {


    int numeroMatricula;
    int anoDeInicio;
    int semestreAtual;
    Aluno aluno;
    Curso curso;


    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
    public void setAnoDeInicio(int anoDeInicio) {
        this.anoDeInicio = anoDeInicio;
    }
    public void setCurso(Curso curso) {
        this.curso = curso;
    }
    public void setNumeroMatricula(int numeroMatricula) {
        this.numeroMatricula = numeroMatricula;
    }
    public void setSemestreAtual(int semestreAtual) {
        this.semestreAtual = semestreAtual;
    }
    public Aluno getAluno() {
        return aluno;
    }
    public int getAnoDeInicio() {
        return anoDeInicio;
    }
    public Curso getCurso() {
        return curso;
    }
    public int getNumeroMatricula() {
        return numeroMatricula;
    }
    public int getSemestreAtual() {
        return semestreAtual;
    }

    public void consultar(){
        System.out.println("=========MATRICULA==========");
        System.out.println("Numero matricula:"+getNumeroMatricula());
        System.out.println("ano de inicio:"+getAnoDeInicio());
        System.out.println("semestre atual:"+getSemestreAtual());
        System.out.println("Aluno:"+this.aluno.getNome());
        System.out.println("Curso:"+this.curso.getDisciplina());

    }

    public Matricula(int numeroMatricula, int anoDeInicio, int semestreAtual, Aluno aluno, Curso curso){
        setAluno(aluno);setAnoDeInicio(anoDeInicio);setNumeroMatricula(numeroMatricula);setSemestreAtual(semestreAtual); setCurso(curso);
}




}
