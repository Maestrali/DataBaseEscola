package br.hepta.dbescola.testePrimitivos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import org.junit.jupiter.api.Test;

import br.hepta.dbescola.dao.AlunoDAO;
import br.hepta.dbescola.entity.Aluno;

public class TesteAddAluno {
    
    @Test
    public void testAddAluno() {

        Aluno aluno = new Aluno();
        
        aluno.setNome("aaa");
        aluno.setEmail("bbaab@email.com");
        aluno.setTelefone(222555555);
        aluno.setIdTurma(1);
        aluno.setPcd(false);

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

        aluno.setDataMatricula(dataM);

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

        aluno.setDataNascimento(dataN);

//--------------------------------------------------------------------------------       
//      Maneira complicada de definir a data de NASCIMENTO que funciona ACABA aqui
//--------------------------------------------------------------------------------

        AlunoDAO dao = new AlunoDAO();
        /* BOOLEAN resultado */ dao.cadastrarAluno(aluno);              
        //System.out.println(resultado);
        //assert(resultado);
        
        
    }
}
