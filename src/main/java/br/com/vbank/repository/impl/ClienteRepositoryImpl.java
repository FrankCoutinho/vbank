package br.com.vbank.repository.impl;

import javax.ejb.Stateless;

import br.com.vbank.domain.Cliente;
import br.com.vbank.repository.ClienteRepository;

@Stateless
public class ClienteRepositoryImpl extends AbstractRepositoryImpl<Cliente> implements ClienteRepository {

	public ClienteRepositoryImpl() {
		super(Cliente.class);
	}

}
