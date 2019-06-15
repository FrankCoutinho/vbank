package br.com.vbank.rest;

import br.com.vbank.domain.Email;
import br.com.vbank.services.EmailService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("v1/emails")
@Produces("application/json")
public class EmailRest {
	
	@EJB
	private EmailService emailService;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Email save(Email email) {
		return emailService.save(email);
	}

	@DELETE
	@Path("/{id}")
	public void remove(@PathParam("id") Long id) {
		emailService.remove(id);
	}

	@GET
	@Path("/{id}")
	public Email findById(@PathParam("id") Long id) {
		return emailService.findById(id);
	}

	@GET
	public List<Email> getAll() {
		return emailService.getAll();
	}
}
