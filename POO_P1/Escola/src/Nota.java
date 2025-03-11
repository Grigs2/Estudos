public class Nota {

    Aluno aluno;
    double nota1, nota2, nota3, nota4;
    String decricao;

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
    public void setNota(double nota) {
        if(nota1== 0){this.nota1 = nota;} else{
        if(nota2== 0){this.nota2 = nota;} else{
        if(nota3== 0){this.nota3 = nota;} else{
        if(nota4== 0){this.nota4 = nota;}}}};
    }
    
    public Aluno getAluno() {
        return aluno;
    }
    
   public void setDecricao(String decricao) {
       this.decricao = decricao;
   }
   public String getDecricao() {
       return decricao;
   }
   public void setNota1(double nota1) {
       this.nota1 = nota1;
   }
   public void setNota2(double nota2) {
       this.nota2 = nota2;
   }
   public void setNota3(double nota3) {
       this.nota3 = nota3;
   }
   public void setNota4(double nota4) {
       this.nota4 = nota4;
   }
   public double getNota1() {
       return nota1;
   }
   public double getNota2() {
       return nota2;
   }
   public double getNota3() {
       return nota3;
   }
   public double getNota4() {
       return nota4;
   }
    public double media(){
        double a = ((nota1+nota2+nota3+nota4)/4);
        return a;
    }
    public Nota(Aluno aluno, String descricao){
        setAluno(aluno);setDecricao(descricao);setNota1(0);setNota2(0);setNota3(0);setNota4(0);
        aluno.setNota(this);
    }
}
