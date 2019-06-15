package br.com.vbank.services;

import br.com.vbank.domain.Deposito;
import br.com.vbank.domain.Movimento;
import br.com.vbank.enums.SituacaoDeposito;
import br.com.vbank.repository.DepositoRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.text.SimpleDateFormat;
import java.util.List;

@Stateless
public class DepositoService {

	@EJB
	private DepositoRepository depositoRepository;

	@EJB
	private MovimentoService movimentoService;

	@EJB
	private HorarioDeTransacoesService parametroService;

	public List<Deposito> getAll() {
		return depositoRepository.getAll();
	}

	public Deposito save(Deposito deposito) {
		if (parametroService.recuperar().isAbertoParaTransacoes()) {
			Deposito deposit = depositoRepository.save(deposito);
			if (deposito.getSituacaoDeposito() == SituacaoDeposito.FINALIZADO) {
				movimentoService.save(new Movimento().addMovimento(deposit));
			}
			return deposit;
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
			throw new RuntimeException();
//					"Só são permitidas movimentações entre " + sdf.format(parametro.getHorarioDeInicioTransacoes()) + " e "
//							+ sdf.format(parametro.getHoraFimTransacoes()) + ".");
		}
	}

	public Deposito findById(Long id) {
		return depositoRepository.findById(id);
	}

	public void remove(Long id) {
		depositoRepository.remove(id);
	}

}
