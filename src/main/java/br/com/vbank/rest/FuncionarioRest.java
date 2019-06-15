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

import br.com.vbank.domain.Funcionario;
import br.com.vbank.services.FuncionarioService;

@Path("/funcionarios")
@Produces("application/json")
public class FuncionarioRest {

	@EJB
	private FuncionarioService funcionarioService;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Funcionario save(Funcionario funcionario) {
		if (funcionarioService.isCpfExistente(funcionario.getCpf())) {
			throw new RuntimeException("Já existe um funcionário cadastrado com este CPF.");
		} else if (!funcionarioService.isCpfValido(funcionario.getCpf())){
			throw new RuntimeException("O CPF informado não é válido.");
		}else{
			return funcionarioService.save(funcionario);
		}
	}

	@PUT
	@Path("/update/{id}")
	public Funcionario update(@PathParam("id") Long id, Funcionario funcionario) {
	
		Funcionario funcionarioResult = funcionarioService.findById(id);
		funcionarioResult.setCpf(funcionario.getCpf());
		funcionarioResult.setNome(funcionario.getNome());
		funcionarioResult.setCargo(funcionario.getCargo());
		return funcionarioService.save(funcionarioResult);
	}

	@DELETE
	@Path("/{id}")
	public void remove(@PathParam("id") Long id) {
		funcionarioService.remove(id);
	}

	@GET
	@Path("/{id}")
	public Funcionario findById(@PathParam("id") Long id) {
		return funcionarioService.findById(id);
	}

	@GET
	public List<Funcionario> getAll() {
		return funcionarioService.getAll();
	}
}
