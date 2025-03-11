public class Material {
    int codigo;
    Aluno aluno;
    Curso curso;

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public void setCurso(Curso curso) {
        this.curso = curso;
    }
    public Aluno getAluno() {
        return aluno;
    }
    public int getCodigo() {
        return codigo;
    }
    public Curso getCurso() {
        return curso;
    }

public void consultar(){
    System.out.println("=========MATERIAL==========");
    System.out.println("codigo:"+getCodigo());
    System.out.println("Aluno: "+this.aluno.getNome());
    System.out.println("curso:"+this.curso.getDisciplina());
}
public Material(int codigo, Aluno aluno, Curso curso){
    setAluno(aluno);setCodigo(codigo);setCurso(curso);
}

}
