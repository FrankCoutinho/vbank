package br.com.vbank.repository.impl;

import javax.ejb.Stateless;

import br.com.vbank.domain.Funcionario;
import br.com.vbank.repository.FuncionarioRepository;


@Stateless
public class FuncionarioRepositoryImpl extends AbstractRepositoryImpl<Funcionario> implements FuncionarioRepository {

	public FuncionarioRepositoryImpl() {
		super(Funcionario.class);
	}

}
