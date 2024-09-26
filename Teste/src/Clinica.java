import java.time.LocalDate;
import java.time.LocalTime;

public class Clinica { 
    public static void main(String[] args) {

        LocalDate hoje = LocalDate.of(2024,8,22);
        LocalTime agora = LocalTime.of(10,36,50);

    Recepcionista rc1 = new Recepcionista();
        rc1.cadastrar("Pamela", 237187381, 1232414, "892714912");
        rc1.acessar();

        Paciente p1 = new Paciente();
        p1.cadastrar(1, "Maria", 44231821, 1189283, "F", 33, rc1);
        p1.consultar();
    /* Metodo construtor no cadastro do medico */
        Medico m1 = new
            Medico("Sergio", 213231414, 11923823, "Acumpultura", "1234");
        m1.acessar();
   /* Metodo construtor no cadastro do medico */
        Exame e1 = new Exame();
        e1.cadastrar("teste", hoje, "exame pra ver se ta tudo certo no programa", m1);
        e1.consultar();

        Receita r1 = new Receita();
        r1.Preescrever("teste", hoje, "preescricao teste para programa", m1);
        r1.consultar();

        Consulta c1 = new Consulta();
        c1.Marcar(hoje, agora, m1, p1, "checkup", "nenhum", rc1, e1, r1);
        c1.Consultar();


        Medico m2 = new Medico("jousé", 293723, 237128, "carpinteiro", "senha1");
        m2.acessar();

        Recepcionista rc2 = new Recepcionista();
        rc2.cadastrar("Pamelo", 237187381, 1232414, "892714912");
        rc2.acessar();

        Paciente p2 = new Paciente();
        p2.cadastrar(2, "joao", 44821, 118239283, "M", 44, rc1);
        p2.consultar();

       

    }
}