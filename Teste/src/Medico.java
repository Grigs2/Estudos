public class Medico extends Funcionario{
        private int crm;
        private String especialidade;

   /*Metodo construtor */

        /* public Medico (){
              this.nome ="";
              this.crm = 0;
              this.telefone = 0;
              this.especialidade ="";
              this.senha ="";
           }*/

        public Medico(String nome, int crm, int telefone, String especialidade, String senha){
              setNome(nome);
              setCrm(crm);
              setTelefone(telefone);
              setEspecialidade(especialidade);
              setSenha(senha);
           }

           
        
        public int getCrm(){
            return crm;
            }

        public String getEspecialidade(){
            return especialidade;
             }

        public void setCrm(int crm) {
            this.crm = crm;
        }
        public void setEspecialidade(String especialidade) {
            this.especialidade = especialidade;
        }
        
        public void acessar(){
            System.out.println("MEDICO>>>>>>>>>>>>>>>>");
            super.acessar();
            System.out.println("nome:"+ getNome());
            System.out.println("Crm:"+ getCrm());
            System.out.println("telefone:"+ getTelefone());
            System.out.println("Especialidade:"+ getEspecialidade());
            System.out.println("Senha:"+ getSenha());
        }

        
}
