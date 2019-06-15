package br.com.vbank.interceptors;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import br.com.vbank.domain.NotaFiscal;

@Stateless
public class ServicoTeste {

	@Interceptors({ HorarioInterceptor.class, LoggerInterceptor.class })
	public void executar(NotaFiscal nota) {
		int i = 0;
		while (i < 30) {
			System.out.println("Executando servico demorado");
			i++;
		}
	}
}
