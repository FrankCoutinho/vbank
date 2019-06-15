package br.com.vbank.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.vbank.domain.Transferencia;
import br.com.vbank.services.TransferenciaService;


@Path("/transferencias")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TransferenciaRest {
	
	@EJB
	private TransferenciaService transferenciaService;
	
	@GET
	@Path("/{id}")
	public Transferencia getById(@PathParam("id") Long id){
		return transferenciaService.getById(id);
	}
	
	@POST
	public Transferencia agendar(Transferencia transferencia){
		return transferenciaService.agendar(transferencia);
	}

	@GET
	public List<Transferencia> getAll(){
		return transferenciaService.getAll();
	}
}
