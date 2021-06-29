package br.hepta.dbescola.testePrimitivos;

import br.hepta.dbescola.dao.TurmaDAO;
import br.hepta.dbescola.entity.Turma;

public class TesteAddTurma {

    public static void main(String[] args) {

        Turma turma = new Turma();
        turma.setNome("B");
        turma.setSerie(2);
        turma.setSala(102);

        TurmaDAO dao = new TurmaDAO();
        dao.cadastrarTurma(turma);
        System.out.println("Turma cadastrada com sucesso!");

    }
}
