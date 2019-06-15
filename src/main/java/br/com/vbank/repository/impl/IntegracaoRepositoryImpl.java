package br.com.vbank.repository.impl;

import javax.ejb.Stateless;

import br.com.vbank.integration.Integracao;
import br.com.vbank.repository.IntegracaoRepository;

@Stateless
public class IntegracaoRepositoryImpl extends AbstractRepositoryImpl<Integracao> implements IntegracaoRepository {

	public IntegracaoRepositoryImpl() {
		super(Integracao.class);
	}

}
