public class Paciente {

    private long codigo;
    private String nome;
    private int cpf;
    private int telefone;
    private String genero;
    private int idade;
    private Recepcionista recepcionista;

    public long getCodigo() {
        return codigo;
    }
 
    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }
 
    public String getNome() {
        return nome;
    }
 
    public void setNome(String nome) {
        this.nome = nome;
    }
 
    public int getCpf() {
        return cpf;
    }
 
    public void setCpf(int cpf) {
        this.cpf = cpf;
    }
 
    public int getTelefone() {
        return telefone;
    }
 
    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }
 
    public String getGenero() {
        return genero;
    }
 
    public void setGenero(String genero) {
        this.genero = genero;
    }
 
    public int getIdade() {
        return idade;
    }
 
    public void setIdade(int idade) {
        this.idade = idade;
    }


    public void cadastrar(long codigo, String nome, int cpf, int telefone, String genero, int idade, Recepcionista rc){
        this.codigo = codigo;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.genero = genero;
        this.idade = idade;
        setRecepcionista(rc);
     }

    public void consultar(){
        System.out.println("PACIENTE>>>>>>>>>>>>>>>>");
        System.out.println("codigo:"+ getCodigo());
        System.out.println("nome:"+ getNome());
        System.out.println("cpf:"+ getCpf());
        System.out.println("telefone:"+ getTelefone());
        System.out.println("genero:"+ getGenero());
        System.out.println("idade:"+ getIdade());
        System.out.println("Atendido por: " +this.recepcionista.getNome());
    }

    public Recepcionista getRecepcionista() {
        return recepcionista;
    }
    public void setRecepcionista(Recepcionista rc){
        this.recepcionista = rc;
    }

}
