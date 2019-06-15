package br.com.vbank.repository;

import java.util.List;

import javax.ejb.EJB;

import org.junit.Assert;
import org.junit.Test;

import br.com.vbank.AbstractTest;
import br.com.vbank.domain.Log;
import br.com.vbank.domain.TestEntity;

public class TestEntityRepositoryTest extends AbstractTest {

	@EJB
	private TestEntityRepository repository;

	@EJB
	private LogRepository logRepository;

	@Test
	public void deveGravarLogdeAuditoriaDeGravacaoDaEntidade() {
		TestEntity t = new TestEntity();
		t.setTest("xpto");
		repository.save(t);

		List<Log> log = logRepository.getAll();
		Assert.assertEquals(1, log.size());
	}

	@Test
	public void deveGravarLogdeAuditoriaDeGravacaoDaEntidadeMesmoDadoExcecao() {
		try {
			TestEntity t = new TestEntity();
			repository.save(t);
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<Log> log = logRepository.getAll();
		Assert.assertTrue(log.size() > 0);
	}

}
