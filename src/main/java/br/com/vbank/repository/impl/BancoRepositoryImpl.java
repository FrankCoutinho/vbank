package br.com.vbank.repository.impl;

import javax.ejb.Stateless;

import br.com.vbank.domain.Banco;
import br.com.vbank.repository.BancoRepository;

@Stateless
public class BancoRepositoryImpl extends AbstractRepositoryImpl<Banco> implements BancoRepository {

	public BancoRepositoryImpl() {
		super(Banco.class);
	}

}
