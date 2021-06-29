package br.hepta.dbescola.rest;

import java.net.URI;
import java.net.URISyntaxException;
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

import br.hepta.dbescola.dao.TurmaDAO;
import br.hepta.dbescola.entity.Turma;

@Path("/turma")
public class TurmaResource {

    private static TurmaDAO dao = new TurmaDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Turma> listarTodasTurmas() {

        return dao.buscarTurmasSerie(null);

    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response encontrarTurma(@PathParam("id") int id) {

        Turma turmaBuscada = dao.selecionarTurmaId(id);
        if (turmaBuscada.getId() != null) {
            return Response.ok(turmaBuscada, MediaType.APPLICATION_JSON).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response adicionarTurma(Turma turma) throws URISyntaxException {

        turma.setSerie(turma.getSerie() != null ? turma.getSerie() : 0);

        turma.setNome(turma.getNome() != null ? turma.getNome() : "0");

        turma.setSala(turma.getSala() != null ? turma.getSala() : 0);

        TurmaDAO dao = new TurmaDAO();

        int id = dao.cadastrarTurma(turma);
        URI uri = new URI("/turma/" + id);

        return Response.created(uri).build();

    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response updateTurma(@PathParam("id") int id, Turma turma) {

        turma.setId(id);
        if (dao.atualizarTurma(turma)) {
            return Response.ok().build();
        } else {
            return Response.notModified().build();
        }

    }

    @DELETE
    @Path("{id}")
    public Response excluirTurma(@PathParam("id") int id) {

        if (dao.removerTurma(id)) {
            return Response.ok().build();
        } else {
            return Response.notModified().build();
        }

    }

}
