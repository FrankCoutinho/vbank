package br.com.vbank.repository.impl;

import javax.ejb.Stateless;

import br.com.vbank.domain.Email;
import br.com.vbank.repository.EmailRepository;

@Stateless
public class EmailRepositoryImpl extends AbstractRepositoryImpl<Email> implements EmailRepository {

	public EmailRepositoryImpl() {
		super(Email.class);
	}

}
