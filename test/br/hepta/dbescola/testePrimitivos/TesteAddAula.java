package br.hepta.dbescola.testePrimitivos;

import br.hepta.dbescola.dao.AulaDAO;
import br.hepta.dbescola.entity.Aula;

public class TesteAddAula {

    public static void main(String[] args) {

        Aula pt = new Aula();
        pt.setIdProf(1);
        pt.setIdTurma(1);
        pt.setHorario("2º");
        pt.setDiaSemana("6ª");

        AulaDAO dao = new AulaDAO();
        dao.cadastrarAula(pt);
        System.out.println("Aula cadastrada com sucesso!");

    }
}
