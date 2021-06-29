package br.hepta.dbescola.teste;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import br.hepta.dbescola.dao.AlunoDAO;
import br.hepta.dbescola.entity.Aluno;

@TestMethodOrder(OrderAnnotation.class)

public class TesteAlunoDAO {

    private static Integer pk;

    @Test
    @Order(1)
    public void testAddAluno() {

        Aluno alunoTeste = new Aluno();

        alunoTeste.setNome("Aluno Teste");
        alunoTeste.setEmail("aluno.novo@email.com");
        alunoTeste.setTelefone(555555);
        alunoTeste.setIdTurma(1);
        alunoTeste.setPcd(false);

//---------------------------------------------------------------------------------
//      Maneira complicada de definir a data de MATRICULA que funciona COMEÇA aqui
//---------------------------------------------------------------------------------

        String dataM1 = "2010-10-21";
        Date dataM2 = null;
        try {
            dataM2 = new SimpleDateFormat("yyyy-MM-dd").parse(dataM1);
        } catch (ParseException e) {
            System.out.println("erro na data da Matricula");
            e.printStackTrace();
        }
        java.sql.Date dataM3 = new java.sql.Date(dataM2.getTime());

        LocalDate dataM = dataM3.toLocalDate();

        alunoTeste.setDataMatricula(dataM);

//---------------------------------------------------------------------------------
//      Maneira complicada de definir a data de MATRICULA que funciona ACABA aqui
//---------------------------------------------------------------------------------  
//
//---------------------------------------------------------------------------------        
//      Maneira complicada de definir a data de NASCIMENTO que funciona COMEÇA aqui
//---------------------------------------------------------------------------------     

        String dataN1 = "1999-05-16";
        Date dataN2 = null;
        try {
            dataN2 = new SimpleDateFormat("yyyy-MM-dd").parse(dataN1);
        } catch (ParseException e) {
            System.out.println("erro na data");
            e.printStackTrace();
        }
        java.sql.Date dataN3 = new java.sql.Date(dataN2.getTime());

        LocalDate dataN = dataN3.toLocalDate();

        alunoTeste.setDataNascimento(dataN);

//--------------------------------------------------------------------------------       
//      Maneira complicada de definir a data de NASCIMENTO que funciona ACABA aqui
//--------------------------------------------------------------------------------

        AlunoDAO dao = new AlunoDAO();
        pk = dao.cadastrarAluno(alunoTeste); // matricula do aluno cadastrado

        assert (pk > 0);

    }

    @Test
    @Order(2)
    public void testSelecionarAlunoMatricula() {
        
        AlunoDAO dao = new AlunoDAO();

        Aluno alunoBuscado = dao.selecionarAlunoMatricula(pk);

        assert (alunoBuscado != null);
        
    }
    
    @Test
    @Order(3)
    public void testBuscarAluno() {

        List<Aluno> check = null;

        AlunoDAO dao = new AlunoDAO();

        check = dao.buscarAlunosNome("Aluno T");

        assert (check.size()>0);
        
    }
    
    @Test
    @Order(4)
    public void testUpAluno() {

        Boolean check = false;

        Aluno alunoTeste = new Aluno();
        alunoTeste.setMatricula(pk);
        alunoTeste.setNome("Aluno Teste Atualizado");
        AlunoDAO dao = new AlunoDAO();

        check = dao.atualizarAluno(alunoTeste);

        assert (check);

    }

    @Test
    @Order(5)
    public void testDelAluno() {

        Boolean check = false;

        AlunoDAO dao = new AlunoDAO();

        check = dao.removerAluno(pk);

        assert (check);

    }

}
