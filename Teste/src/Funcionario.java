public abstract class Funcionario {

    private String nome;
    private int telefone;
    private String senha;

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public int getTelefone() {
        return telefone;
    }
    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public void acessar(){
        System.out.println("Nome: "+getNome());
        System.out.println("telefone: "+getTelefone());
        System.out.println("Senha: "+getSenha());
    }


}
