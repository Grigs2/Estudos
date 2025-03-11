public class Escola {
    public static void main(String[] args) throws Exception {



        Admin ad1 = new Admin("Gabriel", "2812930", "dodo");
        ad1.consultar();
        Curso c1 = new Curso("ADS", 1, 2);
        Professor p1= new Professor("Hebert", "123123123x", "POO", "doutorado");
        Turma t1 = new Turma(p1, c1);
        p1.consultar();
        Aluno a1 = new Aluno("Rogerio", "1293101203190");
        a1.matricular(1, 2024, 1, c1);
        a1.nota = new Nota(a1, "nenhuma");
        ad1.adicionarAluno(a1, t1);
        Aluno a2 = new Aluno("Mateus", "213123x");
        a2.matricular(2, 2024, 2, c1);
        a2.nota = new Nota(a2, "nenhuma");
        ad1.adicionarAluno(a2, t1);

        ad1.adicionarAluno(a2, t1);
        p1.atribuirNota(t1, 9, 0);
        p1.atribuirNota(t1, 9, 0);
        p1.atribuirNota(t1, 9, 0);
        p1.atribuirNota(t1, 9, 0);
        p1.acompanharProgresso(a1);
        a1.consultar();
        p1.atribuirNota(t1, 4.5, 1);
        p1.atribuirNota(t1, 9, 1);
        a2.consultar();
        c1.consultar();
        a1.matricula.consultar();
        Curso c2 = new Curso("vet", 2, 1);
        Material m1 = new Material(1, a1, c2);
        m1.consultar();

        Aluno a3 = new Aluno("otavio", "342345123");
        a3.matricular(3, 2022, 8, c2);
        Certificado ce1 = new Certificado(1, c2, 2024, a3);
        ce1.consultar();









    }
}
