package br.com.vbank.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.vbank.domain.UsuarioCliente;
import br.com.vbank.repository.UsuarioClienteRepository;

@Stateless
public class UsuarioClienteService {

	@EJB 
	private UsuarioClienteRepository usuarioClienteRepository;
	
	public List<UsuarioCliente> getAll() {
		return usuarioClienteRepository.getAll();
	}
	
	public UsuarioCliente save(UsuarioCliente usuarioCliente){
		return usuarioClienteRepository.save(usuarioCliente);
	}
	
	public UsuarioCliente getById(Long id){
		return usuarioClienteRepository.getById(id);
	}
	
	public void remove(Long id){
		usuarioClienteRepository.remove(id);
	}	
		
}
