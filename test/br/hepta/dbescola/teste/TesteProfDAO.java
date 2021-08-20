package br.hepta.dbescola.teste;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import br.hepta.dbescola.dao.ProfessorDAO;
import br.hepta.dbescola.entity.Professor;

@TestMethodOrder(OrderAnnotation.class)

public class TesteProfDAO {

    private static Integer pk = 0;

    @Test
    @Order(1)
    public void testAddProfessor() {

        Professor profTeste = new Professor();

        profTeste.setNome("Professor Teste");
        profTeste.setEmail("prof.novo@email.com");
        profTeste.setTelefone(555555);
        profTeste.setMateria("Fisica");
        profTeste.setEstagiario(false);
        
        LocalDate dataM = LocalDate.parse("1965-10-13");
        profTeste.setDataId(dataM);
        
        LocalDate dataN = LocalDate.parse("1965-10-13");
        profTeste.setDataNascimento(dataN);
        
        ProfessorDAO dao = new ProfessorDAO();
        pk = dao.cadastrarProf(profTeste);

        assert (pk > 0);

    }

    @Test
    @Order(2)
    public void testBuscarProfessorPeloID() throws SQLException {

        Professor check = null;

        Professor profTeste = new Professor();

        profTeste.setId(pk);

        ProfessorDAO dao = new ProfessorDAO();

        check = dao.selecionarProfPeloId(pk);

        assert (check != null);
        
    }
    
    @Test
    @Order(3)
    public void testBuscarProfessores() {

        List<Professor> check = null;

        ProfessorDAO dao = new ProfessorDAO();

        check = dao.buscarTodosProfs();
        System.out.println(check);

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
