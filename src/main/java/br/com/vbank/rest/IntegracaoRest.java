package br.com.vbank.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import br.com.vbank.integration.Integracao;
import br.com.vbank.services.IntegracaoService;

@Path("/integracoes")
@Produces("application/json")
public class IntegracaoRest {
	
	@EJB
	private IntegracaoService integracaoService;
	
	@DELETE
	@Path("/{id}")
	public void remove(@PathParam("id") Long id) {
		integracaoService.remove(id);
	}

	@GET
	public List<Integracao> getAll() {
		return integracaoService.getAll();
	}

}
