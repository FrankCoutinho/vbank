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

import br.com.vbank.domain.Cargo;
import br.com.vbank.services.CargoService;

@Path("/cargos")
@Produces("application/json")
public class CargoRest {

	@EJB
	private CargoService cargoService;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Cargo save(Cargo cargo) {
		return cargoService.save(cargo);
	}

	@PUT
	@Path("/{id}")
	public Cargo update(@PathParam("id") Long id, Cargo cargo) {
	
		Cargo cargoResult = cargoService.findById(id);
		cargoResult.setDescricaoCargo(cargo.getDescricaoCargo());
		return cargoService.save(cargoResult);
	}

	@DELETE
	@Path("/{id}")
	public void remove(@PathParam("id") Long id) {
		cargoService.remove(id);
	}

	@GET
	@Path("/{id}")
	public Cargo findById(@PathParam("id") Long id) {
		return cargoService.findById(id);
	}

	@GET
	public List<Cargo> getAll() {
		return cargoService.getAll();
	}
}
