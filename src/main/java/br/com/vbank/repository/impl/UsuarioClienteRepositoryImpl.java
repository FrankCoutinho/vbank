package br.com.vbank.repository.impl;

import javax.ejb.Stateless;

import br.com.vbank.domain.UsuarioCliente;
import br.com.vbank.repository.UsuarioClienteRepository;


@Stateless
public class UsuarioClienteRepositoryImpl extends AbstractRepositoryImpl<UsuarioCliente> implements UsuarioClienteRepository {

	public UsuarioClienteRepositoryImpl() {
		super(UsuarioCliente.class);
	}

}
