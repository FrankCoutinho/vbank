package br.com.vbank.repository;

import java.util.List;
import java.util.Optional;

import br.com.vbank.domain.Proposta;
import br.com.vbank.enums.SituacaoProposta;

public interface PropostaRepository extends Repository<Proposta>{

	Proposta getById(Long id);
	
	Optional<Proposta> findUltimaProposta(String cpf);

	List<Proposta> findByRegiaoAndSituacaoProposta(String regiao, SituacaoProposta situacaoProposta);
}
