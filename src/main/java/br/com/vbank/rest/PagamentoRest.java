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

import br.com.vbank.domain.Pagamento;
import br.com.vbank.services.PagamentoService;

@Path("/pagamentos")
@Produces("application/json")
public class PagamentoRest {

	@EJB
	private PagamentoService pagamentoService;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Pagamento save(Pagamento pagamento) {
		return pagamentoService.save(pagamento);
	}

	@PUT
	@Path("/{id}")
	public Pagamento update(@PathParam("id") Long id, Pagamento pagamento) {
		Pagamento pagamentoResult = pagamentoService.findById(id);
		pagamentoResult.setClienteRemetente(pagamento.getClienteRemetente());
		pagamentoResult.setCodigoBarra(pagamento.getCodigoBarra());
		pagamentoResult.setSituacaoPagamento(pagamento.getSituacaoPagamento());
		pagamentoResult.setTipoContaDebito(pagamento.getTipoContaDebito());
		pagamentoResult.setVlPagamento(pagamento.getVlPagamento());
		pagamentoResult.setDtPagamento(pagamento.getDtPagamento());
		pagamentoResult.setAgendamento(pagamento.getAgendamento());

		return pagamentoService.save(pagamentoResult);
	}

	@DELETE
	@Path("/{id}")
	public void remove(@PathParam("id") Long id) {
		pagamentoService.remove(id);
	}

	@GET
	@Path("/{id}")
	public Pagamento findById(@PathParam("id") Long id) {
		return pagamentoService.findById(id);
	}

	@GET
	public List<Pagamento> getAll() {
		return pagamentoService.getAll();
	}

}
