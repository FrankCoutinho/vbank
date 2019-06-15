package br.com.vbank.repository.impl;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import br.com.vbank.domain.Log;
import br.com.vbank.repository.LogRepository;

@Stateless
public class LogRepositoryImpl extends AbstractRepositoryImpl<Log> implements LogRepository {

	public LogRepositoryImpl() {
		super(Log.class);
	}
	
	/*
	 *REQUIRES_NEW é usado para identificar quando queremos uma nova transação ao iniciar
	 *este método
	 *
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Log save(Log object) {
		return super.save(object);
	}
}
