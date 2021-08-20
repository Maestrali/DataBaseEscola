package br.hepta.dbescola.rest;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataParam;

import br.hepta.dbescola.dao.AlunoDAO;
import br.hepta.dbescola.dto.AlunoDTO;
import br.hepta.dbescola.entity.Aluno;
import br.hepta.dbescola.services.AlunoServices;

@Path("/aluno")
public class AlunoResource {

    private AlunoDAO dao = new AlunoDAO();
    private AlunoServices service = new AlunoServices();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodosAlunos() {
        return Response.status(Response.Status.OK).entity(service.buscarAlunosNome("")).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response encontrarAluno(@PathParam("id") int id) {

        AlunoDTO alunoBuscado = service.selecionarAlunoMatricula(id);
        if (alunoBuscado.getMatricula() != null) {
            return Response.ok(alunoBuscado, MediaType.APPLICATION_JSON).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response adicionarAluno(@FormDataParam("aluno") FormDataBodyPart aluno, File file) throws SQLException, URISyntaxException, IOException {
        
        AlunoDTO alunoDto = new AlunoDTO();
        aluno.setMediaType(MediaType.APPLICATION_JSON_TYPE);
        alunoDto = aluno.getValueAs(AlunoDTO.class);

        int matricula = service.cadastrarAluno(alunoDto, file);
        URI uri = new URI("/DataBaseEscola/rest/aluno/" + matricula);

        return Response.created(uri).build();

    }

    @OPTIONS
    @Path("{id}")
    public Response fazerNadaComAluno(@PathParam("id") int id) {
        Response res = Response.ok().build();
        System.out.println(res);
        return res;
    }

    @OPTIONS
    public Response fazerNadaComAlunoDenovo(Aluno aluno) {
        Response res = Response.ok().build();
        System.out.println(res);
        return res;
    }

    @PUT
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Path("{id}")
    public Response atualizarAluno(@PathParam("id") int id, @FormDataParam("aluno") FormDataBodyPart aluno, File file) throws SQLException, IOException {
        AlunoDTO alunoDto = new AlunoDTO();
        aluno.setMediaType(MediaType.APPLICATION_JSON_TYPE);
        alunoDto = aluno.getValueAs(AlunoDTO.class);
        Response response = service.atualizarAluno(alunoDto, file) ? Response.ok().build() : Response.notModified().build();
        return response;
    }

    @HEAD
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response denovoNaoFazerNadaComAluno(@PathParam("id") int id, Aluno aluno) {
        Response res = Response.ok().build();
        System.out.println(res);
        return res;
    }

    @DELETE
    @Path("{id}")
    public Response excluirAluno(@PathParam("id") int id) {

        Boolean removerAluno = dao.removerAluno(id);
        Response response = removerAluno ? Response.ok().build() : Response.notModified().build();
        return response;

    }

}
