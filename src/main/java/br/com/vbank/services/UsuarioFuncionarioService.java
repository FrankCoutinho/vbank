package br.com.vbank.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.vbank.domain.UsuarioFuncionario;
import br.com.vbank.repository.UsuarioFuncionarioRepository;

@Stateless
public class UsuarioFuncionarioService  {

	@EJB
	private UsuarioFuncionarioRepository usuarioFuncionarioRepository;
	
	public List<UsuarioFuncionario> getAll(){
		return usuarioFuncionarioRepository.getAll();
	}
	
	public UsuarioFuncionario save(UsuarioFuncionario cargo){
		return usuarioFuncionarioRepository.save(cargo);
	}
	
	public UsuarioFuncionario findById(Long id){
		return usuarioFuncionarioRepository.findById(id);
	}

	public UsuarioFuncionario getById(Long id){
		return usuarioFuncionarioRepository.getById(id);
	}

	public void remove(Long id){
		usuarioFuncionarioRepository.remove(id);
	}
}
