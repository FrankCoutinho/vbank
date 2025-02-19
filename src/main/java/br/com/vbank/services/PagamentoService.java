package br.com.vbank.services;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.vbank.domain.Movimento;
import br.com.vbank.domain.Pagamento;
import br.com.vbank.enums.SituacaoPagamento;
import br.com.vbank.repository.PagamentoRepository;

@Stateless
public class PagamentoService {

	@EJB
	private PagamentoRepository pagamentoRepository;

	@EJB
	private MovimentoService movimentoService;

	@EJB
	private HorarioDeTransacoesService parametroService;

	public List<Pagamento> getAll() {
		return pagamentoRepository.getAll();
	}

	public Pagamento save(Pagamento pagamento) {
		if (parametroService.recuperar().isAbertoParaTransacoes()) {
			Pagamento pagament = pagamentoRepository.save(pagamento);
			if (pagamento.getSituacaoPagamento() == SituacaoPagamento.FINALIZADO) {
				movimentoService.save(new Movimento().addMovimento(pagament));
			}
			return pagament;
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
			throw new RuntimeException();
//					"Só são permitidas movimentações entre " + sdf.format(parametro.getHorarioDeInicioTransacoes()) + " e "
//							+ sdf.format(parametro.getHoraFimTransacoes()) + ".");
		}
	}

	public Pagamento findById(Long id) {
		return pagamentoRepository.findById(id);
	}

	public void remove(Long id) {
		pagamentoRepository.remove(id);
	}

}
