package br.com.vbank.repository.impl;

import br.com.vbank.domain.HorarioDeTransacoes;
import br.com.vbank.repository.HorarioDeTransacoesRepository;

import javax.ejb.Stateless;

@Stateless
public class HorarioDeTransacoesRepositoryImpl
	extends AbstractRepositoryImpl<HorarioDeTransacoes>
	implements HorarioDeTransacoesRepository {

	public HorarioDeTransacoesRepositoryImpl() {
		super(HorarioDeTransacoes.class);
	}
}
