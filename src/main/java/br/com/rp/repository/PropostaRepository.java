package br.com.rp.repository;

import java.util.List;
import java.util.Optional;

import br.com.rp.domain.Proposta;
import br.com.rp.enums.SituacaoProposta;

public interface PropostaRepository extends Repository<Proposta>{

	Proposta getById(Long id);
	
	Optional<Proposta> findUltimaProposta(String cpf);

	List<Proposta> findByRegiaoAndSituacaoProposta(String regiao, SituacaoProposta situacaoProposta);
}
