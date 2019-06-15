package br.com.vbank.repository.impl;

import javax.ejb.Stateless;

import br.com.vbank.domain.Movimento;
import br.com.vbank.repository.MovimentoRepository;

@Stateless
public class MovimentoRepositoryImpl extends AbstractRepositoryImpl<Movimento> implements MovimentoRepository {

	public MovimentoRepositoryImpl() {
		super(Movimento.class);
	}
	
}
