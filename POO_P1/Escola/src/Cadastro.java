public class Cadastro {

String nome;
String cpf;


public String getCpf() {
    return cpf;
}
public String getNome() {
    return nome;
}
public void setCpf(String cpf) {
    this.cpf = cpf;
}
public void setNome(String nome) {
    this.nome = nome;
}

public void consultar(){
    System.out.println("Nome:"+getNome());
    System.out.println("Cpf:"+getCpf());    
}

}
