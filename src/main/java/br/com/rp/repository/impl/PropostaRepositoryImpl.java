package br.com.rp.repository.impl;

import java.util.List;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import br.com.rp.domain.Proposta;
import br.com.rp.enums.SituacaoProposta;
import br.com.rp.exceptions.PropostaNaoEncontradaException;
import br.com.rp.repository.PropostaRepository;

@Stateless
public class PropostaRepositoryImpl extends AbstractRepositoryImpl<Proposta> implements PropostaRepository {

	public PropostaRepositoryImpl() {
		super(Proposta.class);
	}
	
	public Proposta getById(Long id) {
		return Optional
				.ofNullable(em.find(Proposta.class, id))
				.orElseThrow(() -> new PropostaNaoEncontradaException());
	}
	
	public Optional<Proposta> findUltimaProposta(String cpf) {
		TypedQuery<Proposta> createQuery = em.createQuery(
				"FROM Proposta WHERE cpf = :cpf ORDER BY dataProposta DESC ", 
				Proposta.class
		);
		return createQuery
				.setParameter("cpf", cpf)
				.setMaxResults(1)
				.getResultList()
				.stream()
				.findFirst();
	}

	public List<Proposta> findByRegiaoAndSituacaoProposta(String regiao, SituacaoProposta situacaoProposta) {
		TypedQuery<Proposta> typedQuery =  em.createQuery("FROM  Proposta " +
				"WHERE regiao LIKE '%:regiao% " +
				"AND situacaoProposta = :situacao", Proposta.class);

		return typedQuery
				.setParameter("regiao", regiao)
				.setParameter("situacao", situacaoProposta.name())
				.getResultList();
	}
}
