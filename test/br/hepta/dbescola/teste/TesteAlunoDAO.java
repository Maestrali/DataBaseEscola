package br.hepta.dbescola.teste;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import br.hepta.dbescola.dao.AlunoDAO;
import br.hepta.dbescola.entity.Aluno;

@TestMethodOrder(OrderAnnotation.class)

public class TesteAlunoDAO {

    private static Integer pk = 0;

    @Test
    @Order(1)
    public void testAddAluno() throws SQLException {

        Aluno alunoTeste = new Aluno();

        alunoTeste.setNome("Aluno Teste");
        alunoTeste.setEmail("aluno.novo@email.com");
        alunoTeste.setTelefone(555555);
        alunoTeste.setIdTurma(1);
        alunoTeste.setPcd(false);
        alunoTeste.setDataMatricula(LocalDate.now());
        alunoTeste.setDataNascimento(LocalDate.parse("1999-05-16"));

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
    public void testUpAluno() throws SQLException {

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
    public void testSelecionarAlunoMatricula2() {
        
        AlunoDAO dao = new AlunoDAO();

        Aluno alunoBuscado = dao.selecionarAlunoMatricula(pk);

        assert (alunoBuscado != null);
    }

    @Test
    @Order(6)
    public void testDelAluno() {

        Boolean check = false;

        AlunoDAO dao = new AlunoDAO();

        check = dao.removerAluno(pk);

        assert (check);

    }

}
