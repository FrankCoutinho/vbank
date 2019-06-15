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

import br.com.vbank.domain.Transferencia;
import br.com.vbank.services.TransferenciaService;


@Path("/transferencias")
@Produces("application/json")
public class TransferenciaRest {
	
	@EJB
	private TransferenciaService transferenciaService;
	
	@GET
	@Path("/{id}")
	public Transferencia findById(@PathParam("id") Long id){
		return transferenciaService.findById(id);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Transferencia save(Transferencia transferencia){
		return transferenciaService.save(transferencia);
	}
	
	@PUT
	@Path("/{id}")
	public Transferencia update(@PathParam("id") Long id, Transferencia transferencia){
		Transferencia transferenciaResult = transferenciaService.findById(id);
		transferenciaResult.setSituacaoTransferencia(transferencia.getSituacaoTransferencia());		
		return transferenciaService.save(transferencia);
	}
	
	@DELETE
	@Path("/{id}")
	public void remove (@PathParam("id") Long id){
		transferenciaService.remove(id);
	}
	
	@GET
	public List<Transferencia> getAll(){
		return transferenciaService.getAll();
	}

}
