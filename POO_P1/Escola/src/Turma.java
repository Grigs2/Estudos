
import java.util.ArrayList;
import java.util.List;

public class Turma {

    Professor professor;
    private List<Aluno> alunos = new ArrayList<Aluno>();
    Curso curso;

public void addAluno(Aluno a){
    this.alunos.add(a);
    a.setTurma(this);
}

public void setAlunos(List<Aluno> alunos) {
    this.alunos = alunos;
}
public List<Aluno> getAlunos() {
    return alunos;
}

public void setCurso(Curso curso) {
    this.curso = curso;
}
public Curso getCurso() {
    return curso;
}
public void setProfessor(Professor professor) {
    this.professor = professor;
}
public Professor getProfessor() {
    return professor;
}

public void checarAlunos(){

    System.out.println("alunos:"+alunos.getClass());
}
public Turma(Professor professor, Curso curso){
    setCurso(curso);
    setProfessor(professor);
}
}
