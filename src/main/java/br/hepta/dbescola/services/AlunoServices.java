package br.hepta.dbescola.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import br.hepta.dbescola.dao.AlunoDAO;
import br.hepta.dbescola.dto.AlunoDTO;
import br.hepta.dbescola.entity.Aluno;

public class AlunoServices {

    private AlunoDAO dao = new AlunoDAO();

    public byte[] toFoto(File file) throws IOException {

        byte[] foto = Files.readAllBytes(file.toPath());
        return foto;
    }

    public Integer cadastrarAluno(AlunoDTO alunoDto, File file) throws SQLException, IOException {

        byte[] foto = toFoto(file);

        alunoDto.setFoto(foto);
        Aluno aluno = alunoDto.toAluno();

        Integer id = dao.cadastrarAluno(aluno);

        return id;
    }

    public AlunoDTO selecionarAlunoMatricula(int matricula) {

        Aluno aluno = dao.selecionarAlunoMatricula(matricula);

        AlunoDTO alunoDto = new AlunoDTO(aluno);

        return alunoDto;

    }

    public List<AlunoDTO> buscarAlunosNome(String nome) {

        List<Aluno> alunos = dao.buscarAlunosNome(nome);

//        List<AlunoDTO> alunosDto = new ArrayList(alunos);

        List<AlunoDTO> alunosDto = alunos.stream().map(AlunoDTO::new).collect(Collectors.toList());

//        alunos.forEach((aluno) -> {
//            AlunoDTO alunoDto = new AlunoDTO(aluno);
//            alunosDto.add(alunoDto);
//        });

        return alunosDto;

    }

    public Boolean removerAluno(int id) {

        return dao.removerAluno(id);
    }

    public Boolean atualizarAluno(AlunoDTO alunoDto, File file) throws SQLException, IOException {

        byte[] foto = toFoto(file);

        alunoDto.setFoto(foto);

        Aluno aluno = alunoDto.toAluno();

        return dao.atualizarAluno(aluno);
    }

}
