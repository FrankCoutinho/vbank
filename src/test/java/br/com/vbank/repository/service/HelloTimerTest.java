package br.com.vbank.repository.service;

import javax.inject.Inject;

import org.junit.Test;

import br.com.vbank.AbstractTest;
import br.com.vbank.services.HelloTimer;

public class HelloTimerTest extends AbstractTest {

	@Inject
	HelloTimer hello;

	@Test
	public void deveExibirHelloWorld() throws Throwable {
		Thread.sleep(20000);
	}
}
