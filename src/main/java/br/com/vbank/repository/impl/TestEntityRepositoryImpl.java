package br.com.vbank.repository.impl;

import javax.ejb.Stateless;

import br.com.vbank.domain.TestEntity;
import br.com.vbank.repository.TestEntityRepository;

@Stateless
public class TestEntityRepositoryImpl extends AbstractRepositoryImpl<TestEntity> implements TestEntityRepository {

	public TestEntityRepositoryImpl() {
		super(TestEntity.class);
	}
}
