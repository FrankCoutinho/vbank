package br.com.vbank.repository.service;

import java.math.BigDecimal;

import javax.inject.Inject;

import org.junit.Test;

import br.com.vbank.AbstractTest;
import br.com.vbank.domain.NotaFiscal;
import br.com.vbank.services.NotaFiscalService;

public class NotaFiscalConsumerTest extends AbstractTest{

	@Inject
	private NotaFiscalService nfs;

	@Test
	public void deveEnviareConsumirNotaFiscal() {
		NotaFiscal n1 = new NotaFiscal("AB", new BigDecimal(100));
		NotaFiscal n2 = new NotaFiscal("AC", new BigDecimal(200));
		NotaFiscal n3 = new NotaFiscal("AD", new BigDecimal(400));
		nfs.imprimirNotaFiscal(n1);
		nfs.imprimirNotaFiscal(n2);
		nfs.imprimirNotaFiscal(n3);
	}
}
