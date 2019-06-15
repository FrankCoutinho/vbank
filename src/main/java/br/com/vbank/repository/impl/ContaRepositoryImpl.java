package br.com.vbank.repository.impl;

import javax.ejb.Stateless;

import br.com.vbank.domain.Conta;
import br.com.vbank.repository.ContaRepository;

@Stateless
public class ContaRepositoryImpl extends AbstractRepositoryImpl<Conta> implements ContaRepository{

	public ContaRepositoryImpl() {
		super(Conta.class);
	}

}
