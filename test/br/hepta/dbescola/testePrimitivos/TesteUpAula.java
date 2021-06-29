package br.hepta.dbescola.testePrimitivos;

import br.hepta.dbescola.dao.AulaDAO;
import br.hepta.dbescola.entity.Aula;

public class TesteUpAula {

    public static void main(String[] args) {

        Aula aula = new Aula();
        aula.setId(6);
        aula.setIdProf(1);
        aula.setDiaSemana("2ª");

        AulaDAO dao = new AulaDAO();
        dao.atualizarAula(aula);
        System.out.println(aula);
    }

}
