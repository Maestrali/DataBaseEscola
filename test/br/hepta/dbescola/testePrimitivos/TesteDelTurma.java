package br.hepta.dbescola.testePrimitivos;

import br.hepta.dbescola.dao.TurmaDAO;

public class TesteDelTurma {

    public static void main(String[] args) {

        TurmaDAO dao = new TurmaDAO();
        dao.removerTurma(3);
        System.out.println("Turma excuilda!");

    }

}
