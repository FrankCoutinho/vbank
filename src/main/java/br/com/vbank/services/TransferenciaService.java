package br.com.vbank.services;

import br.com.vbank.domain.Transferencia;
import br.com.vbank.repository.TransferenciaRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class TransferenciaService {

	@EJB
	private MovimentoService movimentoService;

	@EJB
	private HorarioDeTransacoesService parametroService;

	@EJB
	private TransferenciaRepository transferenciaRepository;

	public List<Transferencia> getAll() {
		return transferenciaRepository.getAll();
	}

	public Transferencia agendar(Transferencia transferencia) {
//		HorarioDeTransacoes horarioDeTransacoes = parametroService.recuperar();
//
//		if (horarioDeTransacoes.isAbertoParaTransacoes()) {
//
//			Transferencia transf = transferenciaRepository.save(transferencia);
//			if (transf.getSituacaoTransferencia() == SituacaoTransferencia.FINALIZADA) {
//				movimentoService.save(new Movimento().addMovimento(transf));
//			}
//			return transf;
//		}
//		else {
//			SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
//			throw new RuntimeException(
//					"Só são permitidas movimentações entre " + sdf.format(horarioDeTransacoes.getHorarioDeInicioTransacoes()) + " e "
//							+ sdf.format(horarioDeTransacoes.getHoraFimTransacoes()) + ".");
//		}
		return null;
	}

	public Transferencia getById(Long id) {
		return transferenciaRepository.getById(id);
	}
}
