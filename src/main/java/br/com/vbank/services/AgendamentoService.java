package br.com.vbank.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.vbank.domain.Agendamento;
import br.com.vbank.repository.AgendamentoRepository;

@Stateless
public class AgendamentoService {

	@EJB
	private AgendamentoRepository agendamentoRepository;

	public List<Agendamento> getAll() {
		return agendamentoRepository.getAll();
	}

	public Agendamento save(Agendamento agendamento) {
		return agendamentoRepository.save(agendamento);
	}

	public Agendamento findById(Long id) {
		return agendamentoRepository.findById(id);
	}

	public void remove(Long id) {
		agendamentoRepository.remove(id);
	}
}
