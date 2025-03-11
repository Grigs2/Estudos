public class Certificado {


int codigo;
Curso curso;
int ano;
Aluno aluno;

public Aluno getAluno() {
    return aluno;
}
public int getAno() {
    return ano;
}
public int getCodigo() {
    return codigo;
}
public Curso getCurso() {
    return curso;
}
public void setAluno(Aluno aluno) {
    this.aluno = aluno;
}
public void setAno(int ano) {
    this.ano = ano;
}
public void setCodigo(int codigo) {
    this.codigo = codigo;
}
public void setCurso(Curso curso) {
    this.curso = curso;
}


public void consultar () {
    System.out.println("=========CERTIFICADO==========");
    System.out.println("Numero do certificado"+getCodigo());
    System.out.println("Curso:"+this.curso.getDisciplina());
    System.out.println("Ano de conclusão:"+getAno());
    System.out.println("Aluno:"+this.aluno.getNome());

}
public Certificado(int codigo, Curso curso, int ano, Aluno aluno){
    setAluno(aluno);setAno(ano);setCodigo(codigo);setCurso(curso);
}

}
