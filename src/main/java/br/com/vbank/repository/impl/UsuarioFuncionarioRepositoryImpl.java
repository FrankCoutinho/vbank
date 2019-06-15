package br.com.vbank.repository.impl;

import javax.ejb.Stateless;

import br.com.vbank.domain.UsuarioFuncionario;
import br.com.vbank.repository.UsuarioFuncionarioRepository;


@Stateless
public class UsuarioFuncionarioRepositoryImpl extends AbstractRepositoryImpl<UsuarioFuncionario>
		implements UsuarioFuncionarioRepository {

	public UsuarioFuncionarioRepositoryImpl() {
		super(UsuarioFuncionario.class);
	}

}
