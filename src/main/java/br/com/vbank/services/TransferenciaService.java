package br.com.vbank.services;


import br.com.vbank.domain.Transferencia;
import br.com.vbank.enums.SituacaoTransferencia;
import br.com.vbank.repository.TransferenciaRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class TransferenciaService {

	@EJB
	private TransferenciaRepository transferenciaRepository;

	public List<Transferencia> getAll() {
		return transferenciaRepository.getAll();
	}
	
	public Transferencia salvar(Transferencia transferencia) {
		return transferenciaRepository.save(transferencia);
	}

	public Transferencia getById(Long id) {
		return transferenciaRepository.getById(id);
	}

	public List<Transferencia> getTransferenciasPendentes() {		
		return transferenciaRepository
				.getAll()
				.stream()
				.filter(x -> x.getSituacaoTransferencia() == SituacaoTransferencia.PENDENTE)
				.collect(Collectors.toList());
	}
}
