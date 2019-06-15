package br.com.vbank.rest;

import br.com.vbank.domain.HorarioDeTransacoes;
import br.com.vbank.dtos.EditarHorarioDeTransacoesRequest;
import br.com.vbank.services.HorarioDeTransacoesService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/horario-de-transacoes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HorarioDeTransacoesRest {

	@EJB
	private HorarioDeTransacoesService service;

	@PUT
	public HorarioDeTransacoes atualizar(
		EditarHorarioDeTransacoesRequest request
	) {
		return service.atualizar(request);
	}

	@GET
	public HorarioDeTransacoes recuperar() {
		return service.recuperar();
	}
}
