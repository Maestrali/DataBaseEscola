package br.hepta.dbescola.testePrimitivos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import br.hepta.dbescola.dao.ProfessorDAO;
import br.hepta.dbescola.entity.Professor;

public class TesteAddProf {

    public static void main(String[] args) {

        Professor prof = new Professor();
        prof.setNome("ertret");
        prof.setEmail("ertret@email.com");
        prof.setTelefone(777777777);
        prof.setMateria("Geografia");
        prof.setEstagiario(false);

//---------------------------------------------------------------------------------
//      Maneira complicada de definir a data de matricula que funciona começa aqui
//---------------------------------------------------------------------------------

        String dataI = "2010-10-20";
        Date dataI1 = null;
        try {
            dataI1 = new SimpleDateFormat("yyyy-MM-dd").parse(dataI);
        } catch (ParseException e) {
            System.out.println("erro na data da Inscricao");
            e.printStackTrace();
        }
        java.sql.Date dataI2 = new java.sql.Date(dataI1.getTime());
        
        LocalDate dataIF = dataI2.toLocalDate();

        prof.setDataId(dataIF);

//---------------------------------------------------------------------------------
//      Maneira complicada de definir a data de matricula que funciona acaba aqui
//---------------------------------------------------------------------------------  

//---------------------------------------------------------------------------------        
//      Maneira complicada de definir a data de nascimento que funciona começa aqui
//---------------------------------------------------------------------------------     

        String dataN = "1990-05-15";
        Date dataN1 = null;
        try {
            dataN1 = new SimpleDateFormat("yyyy-MM-dd").parse(dataN);
        } catch (ParseException e) {
            System.out.println("erro na data de Nascimento");
            e.printStackTrace();
        }
        java.sql.Date dataN2 = new java.sql.Date(dataN1.getTime());
        
        LocalDate dataNF = dataN2.toLocalDate();

        prof.setDataNascimento(dataNF);

//--------------------------------------------------------------------------------       
//      Maneira complicada de definir a data de nascimento que funciona acaba aqui
//--------------------------------------------------------------------------------

        ProfessorDAO dao = new ProfessorDAO();
        dao.cadastrarProf(prof);
        System.out.println("Prof cadastrado com sucesso!");

    }
}
