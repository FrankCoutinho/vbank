package br.com.vbank.repository.interceptors;

import java.math.BigDecimal;

import javax.inject.Inject;

import org.junit.Test;

import br.com.vbank.AbstractTest;
import br.com.vbank.domain.NotaFiscal;
import br.com.vbank.interceptors.ServicoTeste;

public class LoggerInterceptorTest extends AbstractTest {

	@Inject
	private ServicoTeste servico;

	@Test
	public void deveExecutarLog() {
		servico.executar(new NotaFiscal("100", BigDecimal.ZERO));
	}

}
