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
import br.hepta.dbescola.dao.ProfessorDAO;
import br.hepta.dbescola.entity.Professor;

@Path("/prof")
public class ProfessorResource {

    private static ProfessorDAO dao = new ProfessorDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Professor> listarTodosProfs() {
        
        return dao.buscarProfsNome("");

    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response encontrarProf(@PathParam("id") int id) {

        Professor profBuscado = dao.selecionarProfId(id);
        if (profBuscado.getId() != null) {
            return Response.ok(profBuscado, MediaType.APPLICATION_JSON).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response adicionarProf(Professor prof) throws URISyntaxException {

        prof.setNome(prof.getNome() != null ? prof.getNome() : "");

        Date date = new Date(System.currentTimeMillis());
        java.sql.Date data = new java.sql.Date(date.getTime());
        LocalDate dataI = data.toLocalDate();
        prof.setDataId(dataI);

        prof.setDataNascimento(prof.getDataNascimento() != null ? prof.getDataNascimento() : dataI);

        prof.setMateria(prof.getMateria() != null ? prof.getMateria() : "Não definida");

        prof.setEstagiario(prof.isEstagiario() != null ? prof.isEstagiario() : false);

        prof.setEmail(prof.getEmail() != null ? prof.getEmail() : "");

        prof.setTelefone(prof.getTelefone() != null ? prof.getTelefone() : 0);

        ProfessorDAO dao = new ProfessorDAO();

        int id = dao.cadastrarProf(prof);
        URI uri = new URI("/prof/" + id);

        return Response.created(uri).build();

    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response updateProf(@PathParam("id") int id, Professor prof) {

        prof.setId(id);
        if (dao.atualizarProf(prof)) {
            return Response.ok().build();
        } else {
            return Response.notModified().build();
        }

    }

    @DELETE
    @Path("{id}")
    public Response excluirProf(@PathParam("id") int id) {
        
        if (dao.removerProf(id)) {
            return Response.ok().build();
        } else {
            return Response.notModified().build();
        }
        
    }
    
}
