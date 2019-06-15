package br.com.vbank.repository;

import java.util.List;

import br.com.vbank.domain.Pessoa;

public interface PessoaRepository extends Repository<Pessoa> {
	
	List<Pessoa> findPessoaPorNome(String nome);

}
