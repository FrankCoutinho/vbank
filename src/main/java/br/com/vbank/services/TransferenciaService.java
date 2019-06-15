package br.com.vbank.services;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.vbank.domain.Movimento;
import br.com.vbank.domain.TimeIntegration;
import br.com.vbank.domain.Transferencia;
import br.com.vbank.enums.SituacaoTransferencia;
import br.com.vbank.repository.TransferenciaRepository;

@Stateless
public class TransferenciaService {

	@EJB
	private MovimentoService movimentoService;

	@EJB
	private TimeIntegrationService parametroService;

	@EJB
	private TransferenciaRepository transferenciaRepository;

	public List<Transferencia> getAll() {
		return transferenciaRepository.getAll();
	}

	public Transferencia agendar(Transferencia transferencia) {
		TimeIntegration parametro = parametroService.findParametro();

		if (parametroService.isHorarioTransacaoValido(parametro, transferencia)) {
			Transferencia transf = transferenciaRepository.save(transferencia);
			if (transf.getSituacaoTransferencia() == SituacaoTransferencia.FINALIZADA) {
				movimentoService.save(new Movimento().addMovimento(transf));
			}
			return transf;
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
			throw new RuntimeException(
					"Só são permitidas movimentações entre " + sdf.format(parametro.getHoraInicioTransacoes()) + " e "
							+ sdf.format(parametro.getHoraFimTransacoes()) + ".");
		}
	}

	public Transferencia getById(Long id) {
		return transferenciaRepository.getById(id);
	}
}
