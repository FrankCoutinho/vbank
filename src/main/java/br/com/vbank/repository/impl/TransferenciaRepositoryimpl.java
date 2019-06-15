package br.com.vbank.repository.impl;

import javax.ejb.Stateless;

import br.com.vbank.domain.Transferencia;
import br.com.vbank.repository.TransferenciaRepository;

@Stateless
public class TransferenciaRepositoryimpl extends AbstractRepositoryImpl<Transferencia> implements TransferenciaRepository {

	public TransferenciaRepositoryimpl() {
		super(Transferencia.class);
	}
	
}
