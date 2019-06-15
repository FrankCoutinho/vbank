package br.com.vbank.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.vbank.domain.Banco;
import br.com.vbank.repository.BancoRepository;

@Stateless
public class BancoService {

	@EJB
	private BancoRepository bancoRepository;

	public List<Banco> getAll() {
		return bancoRepository.getAll();
	}

	public Banco save(Banco banco) {
		return bancoRepository.save(banco);
	}

	public Banco findById(Long id) {
		return bancoRepository.findById(id);
	}

	public void remove(Long id) {
		bancoRepository.remove(id);
	}
}
