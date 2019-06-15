package br.com.vbank.repository.impl;

import javax.ejb.Stateless;

import br.com.vbank.domain.Papel;
import br.com.vbank.repository.PapelRepository;

@Stateless
public class PapelRepositoryImpl extends AbstractRepositoryImpl<Papel> implements PapelRepository {

	public PapelRepositoryImpl() {
		super(Papel.class);
	}

}
