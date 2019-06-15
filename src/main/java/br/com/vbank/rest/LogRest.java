package br.com.vbank.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import br.com.vbank.domain.Log;
import br.com.vbank.repository.LogRepository;

@Path("/log")
@Produces("application/json")
public class LogRest {

	@EJB
	private LogRepository repository;

	@GET
	public List<Log> consultarLogs() {
		return repository.getAll();
	}

}
