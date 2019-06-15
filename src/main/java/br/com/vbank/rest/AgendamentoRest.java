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

import br.com.vbank.domain.Agendamento;
import br.com.vbank.services.AgendamentoService;

@Path("/agendamentos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AgendamentoRest {

	@EJB
	private AgendamentoService agendamentoService;

	@POST
	public Agendamento criar(Agendamento agendamento) {
		return agendamentoService.criar(agendamento);
	}

	@PUT
	@Path("/{id}")
	public Agendamento update(@PathParam("id") Long id, Agendamento agendamento) {
		Agendamento agendamentoResult = agendamentoService.findById(id);
		agendamentoResult.setDataAgendamento(agendamento.getDataAgendamento());
		agendamentoResult.setCliente(agendamento.getCliente());

		return agendamentoService.criar(agendamentoResult);
	}

	@DELETE
	@Path("/{id}")
	public void remove(@PathParam("id") Long id) {
		agendamentoService.remove(id);
	}

	@GET
	@Path("/{id}")
	public Agendamento findById(@PathParam("id") Long id) {
		return agendamentoService.findById(id);
	}

	@GET
	public List<Agendamento> getAll() {
		return agendamentoService.getAll();
	}
}
