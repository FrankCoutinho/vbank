package br.com.vbank.services;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.vbank.domain.Transferencia;
import br.com.vbank.enums.SituacaoTransferencia;
import br.com.vbank.repository.TransferenciaRepository;

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

	public List<Transferencia> getTransferenciasPendentes() {		
		
		List<Transferencia> transferencias = 
				transferenciaRepository
				.getAll()
				.stream()
				.filter(x -> x.getSituacaoTransferencia() == SituacaoTransferencia.PENDENTE )
				.collect(Collectors.toList());
		
		List<Transferencia> transferenciasPendentes = new ArrayList<Transferencia>();
		for (Transferencia transferencia : transferencias) {
			if (validarSeATransferenciaPassouDaHoraPermitida(transferencia)) {
				transferenciasPendentes.add(transferencia);
			}		
		}		
		return transferenciasPendentes;
	}
	
	public boolean validarSeATransferenciaPassouDaHoraPermitida(Transferencia transferencia) {		
		return 	transferencia
				.getDtAgendamento()				
				.isBefore(LocalDateTime.now());
 
	}
}
