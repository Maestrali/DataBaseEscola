package br.hepta.dbescola.testePrimitivos;

import br.hepta.dbescola.dao.TurmaDAO;
import br.hepta.dbescola.entity.Turma;

public class TesteUpTurma {

    public static void main(String[] args) {
            
            Turma turma = new Turma();
            turma.setId(4);
            turma.setSerie(3);
            turma.setNome("D");
            turma.setSala(104);
            
            TurmaDAO dao = new TurmaDAO();
            dao.atualizarTurma(turma);
            System.out.println(turma);
        }

}

