package br.com.vbank.repository.impl;

import javax.ejb.Stateless;

import br.com.vbank.domain.Deposito;
import br.com.vbank.repository.DepositoRepository;


@Stateless
public class DepositoRepositoryImpl extends AbstractRepositoryImpl<Deposito> implements DepositoRepository {

	public DepositoRepositoryImpl() {
		super(Deposito.class);
	}

}
