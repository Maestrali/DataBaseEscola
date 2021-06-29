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

import br.hepta.dbescola.dao.AulaDAO;
import br.hepta.dbescola.entity.Aula;

@Path("/aula")
public class AulaResource {

    private static AulaDAO dao = new AulaDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Aula> listarTodasAulas() {

        return dao.buscarAulasProfTurma(0,0);

    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response encontrarAula(@PathParam("id") int id) {

        Aula aulaBuscada = dao.selecionarAulaId(id);
        if (aulaBuscada.getId() != null) {
            return Response.ok(aulaBuscada, MediaType.APPLICATION_JSON).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response adicionarAula(Aula aula) throws URISyntaxException {

        aula.setIdProf(aula.getIdProf() != null ? aula.getIdProf() : 0);

        aula.setIdTurma(aula.getIdTurma() != null ? aula.getIdTurma() : 0);

        aula.setHorario(aula.getHorario() != null ? aula.getHorario() : "");
        
        aula.setDiaSemana(aula.getDiaSemana() != null ? aula.getDiaSemana() : "");
        
        AulaDAO dao = new AulaDAO();

        int id = dao.cadastrarAula(aula);
        URI uri = new URI("/aula/" + id);

        return Response.created(uri).build();

    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response updateAula(@PathParam("id") int id, Aula aula) {

        aula.setId(id);
        if (dao.atualizarAula(aula)) {
            return Response.ok().build();
        } else {
            return Response.notModified().build();
        }

    }

    @DELETE
    @Path("{id}")
    public Response excluirAula(@PathParam("id") int id) {

        if (dao.removerAula(id)) {
            return Response.ok().build();
        } else {
            return Response.notModified().build();
        }

    }

}
