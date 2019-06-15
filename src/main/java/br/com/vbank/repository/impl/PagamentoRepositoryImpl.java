package br.com.vbank.repository.impl;

import javax.ejb.Stateless;

import br.com.vbank.domain.Pagamento;
import br.com.vbank.repository.PagamentoRepository;


@Stateless
public class PagamentoRepositoryImpl extends AbstractRepositoryImpl<Pagamento> implements PagamentoRepository {

	public PagamentoRepositoryImpl() {
		super(Pagamento.class);
	}

}
