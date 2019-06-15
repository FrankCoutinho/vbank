package br.com.vbank.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import br.com.vbank.domain.Movimento;
import br.com.vbank.services.MovimentoService;

@Path("/movimentos")
@Produces("application/json")
public class MovimentoRest {
	
	@EJB
	private MovimentoService movimentoService;
	
	@DELETE
	@Path("{id}")
	public void remove(@PathParam("id") Long id) {
		movimentoService.remove(id);
	}

	@GET
	public List<Movimento> getAll() {
		return movimentoService.getAll();
	}
}
