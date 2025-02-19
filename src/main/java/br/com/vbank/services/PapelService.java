package br.com.vbank.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.vbank.domain.Papel;
import br.com.vbank.repository.PapelRepository;

@Stateless
public class PapelService {
	
	@EJB
	PapelRepository acaoRepository;
	
	public List<Papel> getAll(){
		return acaoRepository.getAll();
	}
	
	public Papel save(Papel acao){
		return acaoRepository.save(acao);
	}
	
	public Papel findById(Long id){
		return acaoRepository.findById(id);
	}
	
	public void remove(Long id){
		acaoRepository.remove(id);
	}
}
