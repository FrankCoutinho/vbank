package br.com.vbank.repository.service;

import java.util.List;

import javax.ejb.EJB;

import org.jboss.arquillian.persistence.UsingDataSet;
import org.junit.Assert;
import org.junit.Test;

import br.com.vbank.AbstractTest;
import br.com.vbank.domain.Log;
import br.com.vbank.services.LogService;

public class LogServiceTest extends AbstractTest {

	@EJB
	private LogService service;

	@Test
	@UsingDataSet("db/log.xml")
	public void deveRetornar2Logs() {
		List<Log> logs = service.getAllLogs();
		Assert.assertEquals(2, logs.size());
	}

}
