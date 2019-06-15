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

import br.com.vbank.domain.UsuarioFuncionario;
import br.com.vbank.services.UsuarioFuncionarioService;

@Path("/usuarios/funcionarios")
@Produces("application/json")
public class UsuarioFuncionarioRest {

	@EJB
	private UsuarioFuncionarioService usuarioFuncionarioService;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public UsuarioFuncionario save(UsuarioFuncionario usuarioFuncionario) {
		return usuarioFuncionarioService.save(usuarioFuncionario);
	}

	@PUT
	@Path("/{id}")
	public UsuarioFuncionario update(@PathParam("id") Long id, UsuarioFuncionario usuarioFuncionario) {
		UsuarioFuncionario usuarioFuncionarioResult = usuarioFuncionarioService.findById(id);
		usuarioFuncionarioResult.setLogin(usuarioFuncionario.getLogin());
		usuarioFuncionarioResult.alterarSenha(usuarioFuncionario.getSenha());
		usuarioFuncionarioResult.setFuncionario(usuarioFuncionario.getFuncionario());

		return usuarioFuncionarioService.save(usuarioFuncionarioResult);
	}

	@DELETE
	@Path("/{id}")
	public void remove(@PathParam("id") Long id) {
		usuarioFuncionarioService.remove(id);
	}

	@GET
	@Path("/{id}")
	public UsuarioFuncionario findById(@PathParam("id") Long id) {
		return usuarioFuncionarioService.findById(id);
	}

	@GET
	public List<UsuarioFuncionario> getAll() {
		return usuarioFuncionarioService.getAll();
	}
}
