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

import br.com.vbank.domain.Banco;
import br.com.vbank.services.BancoService;

@Path("/bancos")
@Produces("application/json")
public class BancoRest {

	@EJB
	private BancoService bancoService;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Banco save(Banco banco) {
		return bancoService.save(banco);
	}

	@PUT
	@Path("/{id}")
	public Banco update(@PathParam("id") Long id, Banco banco) {
		Banco bancoResult = bancoService.findById(id);
		bancoResult.setDescricao(banco.getDescricao());
		return bancoService.save(bancoResult);
	}

	@DELETE
	@Path("/{id}")
	public void remove(@PathParam("id") Long id) {
		bancoService.remove(id);
	}

	@GET
	@Path("/{id}")
	public Banco findById(@PathParam("id") Long id) {
		return bancoService.findById(id);
	}

	@GET
	public List<Banco> getAll() {
		return bancoService.getAll();
	}

}
