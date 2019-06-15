package br.com.vbank.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.vbank.domain.Papel;
import br.com.vbank.services.PapelService;

@Path("/acoes")
@Produces("application/json")
public class PapelRest {

	@EJB
	private PapelService papelService;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/criar")
	public Papel save(Papel acao) {
		return papelService.save(acao);
	}

	@PUT
	@Path("/{id}")
	public Papel update(@PathParam("id") Long id, Papel acao) {
		Papel acaoResult = papelService.findById(id);
		acaoResult.setDescricaoAcao(acao.getDescricaoAcao());
		return papelService.save(acaoResult);
	}

	@DELETE
	@Path("/{id}")
	public void remove(@PathParam("id") Long id) {
		papelService.remove(id);
	}

	@GET
	@Path("/{id}")
	public Papel findById(@PathParam("id") Long id) {
		return papelService.findById(id);
	}

	@GET
	public List<Papel> getAll() {
		return papelService.getAll();
	}
}
