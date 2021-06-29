package br.hepta.dbescola.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import br.hepta.dbescola.dao.AlunoDAO;
import br.hepta.dbescola.entity.Aluno;

@Path("/aluno")
public class AlunoResource {

    private static AlunoDAO dao = new AlunoDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Aluno> listarTodosAlunos() {
        return dao.buscarAlunosNome("");
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response encontrarAluno(@PathParam("id") int id) {

        Aluno alunoBuscado = dao.selecionarAlunoMatricula(id);
        if (alunoBuscado.getMatricula() != null) {
            return Response.ok(alunoBuscado, MediaType.APPLICATION_JSON).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response adicionarAluno(Aluno aluno) throws URISyntaxException {

        aluno.setNome(aluno.getNome() != null ? aluno.getNome() : "");

        Date date = new Date(System.currentTimeMillis());
        java.sql.Date data = new java.sql.Date(date.getTime());
        LocalDate dataM = data.toLocalDate();
        aluno.setDataMatricula(dataM);

        aluno.setDataNascimento(aluno.getDataNascimento() != null ? aluno.getDataNascimento() : dataM);

        aluno.setIdTurma(aluno.getIdTurma() != null ? aluno.getIdTurma() : 10);

        aluno.setPcd(aluno.isPcd() != null ? aluno.isPcd() : false);

        aluno.setEmail(aluno.getEmail() != null ? aluno.getEmail() : "");

        aluno.setTelefone(aluno.getTelefone() != null ? aluno.getTelefone() : 0);

        AlunoDAO dao = new AlunoDAO();

        int matricula = dao.cadastrarAluno(aluno);
        URI uri = new URI("/aluno/" + matricula);

        return Response.created(uri).build();

    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response updateAluno(@PathParam("id") int id, Aluno aluno) {
        aluno.setMatricula(id);
        if (dao.atualizarAluno(aluno)) {
            return Response.ok().build();
        } else {
            return Response.notModified().build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response excluirAluno(@PathParam("id") int id) {

        if (dao.removerAluno(id)) {
            return Response.ok().build();
        } else {
            return Response.notModified().build();
        }
    }

}