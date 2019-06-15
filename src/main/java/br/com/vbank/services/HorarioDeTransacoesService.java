package br.com.vbank.services;

import br.com.vbank.domain.HorarioDeTransacoes;
import br.com.vbank.dtos.EditarHorarioDeTransacoesRequest;
import br.com.vbank.repository.HorarioDeTransacoesRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class HorarioDeTransacoesService {

	@EJB
	private HorarioDeTransacoesRepository repository;

	public HorarioDeTransacoes atualizar(EditarHorarioDeTransacoesRequest request) {
		HorarioDeTransacoes horarioDeTransacoes = recuperar();
		horarioDeTransacoes.atualizar(request);
		return repository.save(horarioDeTransacoes);
	}

	public HorarioDeTransacoes recuperar() {
		return repository
			.getAll()
			.stream()
			.findFirst()
			.orElseThrow(() -> new IllegalStateException("Horário de transações não definidos."));
	}
}
