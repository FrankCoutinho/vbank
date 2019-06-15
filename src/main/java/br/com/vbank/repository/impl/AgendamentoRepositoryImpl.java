package br.com.vbank.repository.impl;

import javax.ejb.Stateless;

import br.com.vbank.domain.Agendamento;
import br.com.vbank.repository.AgendamentoRepository;


@Stateless
public class AgendamentoRepositoryImpl extends AbstractRepositoryImpl<Agendamento> implements AgendamentoRepository {

	public AgendamentoRepositoryImpl() {
		super(Agendamento.class);
	}
}
