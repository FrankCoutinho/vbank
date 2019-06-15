package br.com.vbank.repository.impl;

import javax.ejb.Stateless;

import br.com.vbank.domain.TimeIntegration;
import br.com.vbank.repository.TimeIntegrationRepository;

@Stateless
public class TimeIntegrationRepositoryImpl extends AbstractRepositoryImpl<TimeIntegration> implements TimeIntegrationRepository {

	public TimeIntegrationRepositoryImpl() {
		super(TimeIntegration.class);
	}

}
