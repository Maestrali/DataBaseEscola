package br.hepta.dbescola.teste;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import br.hepta.dbescola.dao.ProfessorDAO;
import br.hepta.dbescola.entity.Professor;

@TestMethodOrder(OrderAnnotation.class)

public class TesteProfDAO {

    private static Integer pk;

    @Test
    @Order(1)
    public void testAddProfessor() {

        Professor profTeste = new Professor();

        profTeste.setNome("Professor Teste");
        profTeste.setEmail("prof.novo@email.com");
        profTeste.setTelefone(555555);
        profTeste.setMateria("Fisica");
        profTeste.setEstagiario(false);

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

        profTeste.setDataId(dataM);

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

        profTeste.setDataNascimento(dataN);

//--------------------------------------------------------------------------------       
//      Maneira complicada de definir a data de NASCIMENTO que funciona ACABA aqui
//--------------------------------------------------------------------------------

        ProfessorDAO dao = new ProfessorDAO();
        pk = dao.cadastrarProf(profTeste);

        assert (pk > 0);

    }

    @Test
    @Order(2)
    public void testBuscarProfessorID() {

        Professor check = null;

        Professor profTeste = new Professor();

        profTeste.setId(pk);

        ProfessorDAO dao = new ProfessorDAO();

        check = dao.selecionarProfId(pk);

        assert (check != null);
        
    }
    
    @Test
    @Order(3)
    public void testBuscarProfessorNome() {

        List<Professor> check = null;

        ProfessorDAO dao = new ProfessorDAO();

        check = dao.buscarProfsNome("Professor Tes");

        assert (check.size()>0);
        
    }

    @Test
    @Order(4)
    public void testUpProfessor() {

        Boolean check = false;

        Professor profTeste = new Professor();
        profTeste.setId(pk);
        profTeste.setNome("Professor Teste Atualizado");
        ProfessorDAO dao = new ProfessorDAO();

        check = dao.atualizarProf(profTeste);

        assert (check);

    }

    @Test
    @Order(5)
    public void testDelProfessor() {

        Boolean check = false;

        ProfessorDAO dao = new ProfessorDAO();

        check = dao.removerProf(pk);

        assert (check);

    }

}
