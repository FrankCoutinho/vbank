package br.com.rp.rest;

import br.com.rp.domain.UsuarioCliente;
import br.com.rp.services.UsuarioClienteService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/usuarios/clientes")
@Produces("application/json")
public class UsuarioClienteRest {

	@EJB
	private UsuarioClienteService usuarioClienteService;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public UsuarioCliente save(UsuarioCliente usuarioCliente) {
		return usuarioClienteService.save(usuarioCliente);
	}

	@PUT
	@Path("/{id}")
	public UsuarioCliente update(@PathParam("id") Long id, String senha) {
		UsuarioCliente usuarioClienteResult = usuarioClienteService.getById(id);
		usuarioClienteResult.alterarSenha(senha);
		return usuarioClienteService.save(usuarioClienteResult);
	}

	@DELETE
	@Path("/{id}")
	public void remove(@PathParam("id") Long id) {
		usuarioClienteService.remove(id);
	}

	@GET
	@Path("/{id}")
	public UsuarioCliente findById(@PathParam("id") Long id) {
		return usuarioClienteService.getById(id);
	}

	@GET
	public List<UsuarioCliente> getAll() {
		return usuarioClienteService.getAll();
	}

}
