package br.com.vbank.repository.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import br.com.vbank.domain.Pessoa;
import br.com.vbank.repository.PessoaRepository;

@Stateless
public class PessoaRepositoryImpl extends AbstractRepositoryImpl<Pessoa> implements PessoaRepository {

	public PessoaRepositoryImpl() {
		super(Pessoa.class);
	}

	@Override
	public List<Pessoa> findPessoaPorNome(String nome) {
		TypedQuery<Pessoa> tq = em.createQuery("select p from Pessoa p where nome like :nome", Pessoa.class);
		tq.setParameter("nome", nome + "%");
		return tq.getResultList();
	}

}
