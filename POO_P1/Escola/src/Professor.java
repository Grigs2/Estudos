public class Professor extends Cadastro{
    String disciplina;
    String nivelDeFormacao;
    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }
    public void setNivelDeFormacao(String nivelDeFormacao) {
        this.nivelDeFormacao = nivelDeFormacao;
    }
    public String getDisciplina() {
        return disciplina;
    }
    public String getNivelDeFormacao() {
        return nivelDeFormacao;
    }
    

    public void atribuirTarefas(){


    }

    public void atribuirNota(Turma turma, double nota, int aluno){
        turma.getAlunos().get(aluno).nota.setNota(nota); 
    }
    
    public void fornecerFeedback(){

    }

    public void acompanharProgresso(Aluno aluno){
        System.out.println("semestre: "+aluno.matricula.getSemestreAtual());
        System.out.println("nota 1: "+aluno.nota.getNota1());
        System.out.println("nota 2: "+aluno.nota.getNota2());
        System.out.println("nota 3: "+aluno.nota.getNota3());
        System.out.println("nota 4: "+aluno.nota.getNota4());
    }

    public Professor(String nome, String cpf, String disciplina, String nivelDeFormacao){
        setCpf(cpf);
        setDisciplina(disciplina);
        setNome(nome);
        setNivelDeFormacao(nivelDeFormacao);
    }

    @Override
    public void consultar() {
        System.out.println("=============PROFESSOR============");
        super.consultar();
        System.out.println("disciplina:"+getDisciplina());
        System.out.println("Nivel de Formação:"+getNivelDeFormacao());
      
    }
}
