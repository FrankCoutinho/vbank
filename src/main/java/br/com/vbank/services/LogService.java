package br.com.vbank.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.vbank.domain.Log;
import br.com.vbank.repository.LogRepository;

@Stateless
public class LogService {

	@EJB
	private LogRepository repository;

	public List<Log> getAllLogs() {
		return repository.getAll();
	}
}
