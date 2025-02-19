package br.com.vbank.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;
import br.com.vbank.domain.Cliente;
import br.com.vbank.repository.ClienteRepository;

@Stateless
public class ClienteService {

	@PersistenceContext(unitName = "vbank")
	private EntityManager em;

	@EJB
	ClienteRepository clienteRepository;

	public List<Cliente> getAll() {
		return clienteRepository.getAll();
	}

	public Cliente save(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	public Cliente findById(Long id) {
		return clienteRepository.findById(id);
	}

	public Cliente findByCpf(String cpf) {
		return em.createQuery("from " + Cliente.class.getSimpleName() + " where cpf = " + cpf, Cliente.class)
				.getSingleResult();
	}

	public void remove(Long id) {
		clienteRepository.remove(id);
	}

	public Boolean isCpfValido(String cpf) {
		try {
			new CPFValidator().assertValid(cpf);
		} catch (InvalidStateException e) {
			return false;
		}
		return true;
	}

	public Boolean existeClienteComCpf(String cpf) {
		try {
			if (!findByCpf(cpf).equals(null)) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}
}
