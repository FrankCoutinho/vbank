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

import br.com.vbank.domain.Cliente;
import br.com.vbank.services.ClienteService;

@Path("/clientes")
@Produces("application/json")
public class ClienteRest {

	@EJB
	private ClienteService clienteService;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Cliente save(Cliente cliente) {
		if (clienteService.existeClienteComCpf(cliente.getCpf())) {
			throw new RuntimeException("Já existe um cliente cadastrado com este CPF.");
		} else if (!clienteService.isCpfValido(cliente.getCpf())) {
			throw new RuntimeException("O CPF informado não é válido.");
		} else {
			return clienteService.save(cliente);
		}
	}

	@PUT
	@Path("/{id}")
	public Cliente update(@PathParam("id") Long id, Cliente cliente) {

		Cliente clienteResult = clienteService.findById(id);
		clienteResult.setCpf(cliente.getCpf());
		clienteResult.setNome(cliente.getNome());
		clienteResult.setVlRenda(cliente.getVlRenda());
		clienteResult.setConta(cliente.getConta());
		clienteResult.setEmail(cliente.getEmail());

		return clienteService.save(clienteResult);
	}

	@DELETE
	@Path("/{id}")
	public void remove(@PathParam("id") Long id) {
		clienteService.remove(id);
	}

	@GET
	@Path("/{id}")
	public Cliente findById(@PathParam("id") Long id) {
		return clienteService.findById(id);
	}

	@GET
	public List<Cliente> getAll() {
		return clienteService.getAll();
	}
}
