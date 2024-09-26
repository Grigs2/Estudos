public class Recepcionista extends Funcionario{

        private int cpf;
    
        public int getCpf() {
            return cpf;
        }
        public void setCpf(int cpf) {
            this.cpf = cpf;
        }

        public void cadastrar(String nome, int cpf, int telefone, String senha){
        setNome(nome);
        this.cpf = cpf;
        setTelefone(telefone);
        setSenha(senha);
     }

     public void acessar(){
        System.out.println("RECEPCIONISTA>>>>>>>>>>>>>>>>");
        super.acessar();
        System.out.println("nome:"+ getNome());
        System.out.println("cpf:"+ getCpf());
        System.out.println("telefone:"+ getTelefone());
        System.out.println("senha:"+ getSenha());
    }


}
