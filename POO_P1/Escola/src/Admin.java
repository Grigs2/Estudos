
public class Admin extends Cadastro{

    String senha;

    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

    
    public void adicionarAluno(Aluno aluno, Turma turma){
        turma.addAluno(aluno);
    }

    public void removerTurma() {

    }
    public void checarReceita(){

    }

    public void gerarRelatorio() {


    }

    public Admin(String nome, String cpf, String senha){

        setNome(nome);
        setCpf(cpf);
        setSenha(senha);
    }

    @Override
    public void consultar() {
        System.out.println("=========ADMIN==========");
        super.consultar();
        System.out.println("Senha:"+getSenha());
    }
}
