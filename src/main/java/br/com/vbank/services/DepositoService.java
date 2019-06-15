package br.com.vbank.services;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.vbank.domain.Deposito;
import br.com.vbank.domain.Movimento;
import br.com.vbank.domain.TimeIntegration;
import br.com.vbank.enums.SituacaoDeposito;
import br.com.vbank.repository.DepositoRepository;

@Stateless
public class DepositoService {

	@EJB
	private DepositoRepository depositoRepository;

	@EJB
	private MovimentoService movimentoService;

	@EJB
	private TimeIntegrationService parametroService;

	public List<Deposito> getAll() {
		return depositoRepository.getAll();
	}

	public Deposito save(Deposito deposito) {
		TimeIntegration parametro = parametroService.findParametro();
		if (parametroService.isHorarioTransacaoValido(parametro, deposito)) {
			Deposito deposit = depositoRepository.save(deposito);
			if (deposito.getSituacaoDeposito() == SituacaoDeposito.FINALIZADO) {
				movimentoService.save(new Movimento().addMovimento(deposit));
			}
			return deposit;
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
			throw new RuntimeException(
					"Só são permitidas movimentações entre " + sdf.format(parametro.getHoraInicioTransacoes()) + " e "
							+ sdf.format(parametro.getHoraFimTransacoes()) + ".");
		}
	}

	public Deposito findById(Long id) {
		return depositoRepository.findById(id);
	}

	public void remove(Long id) {
		depositoRepository.remove(id);
	}

}
