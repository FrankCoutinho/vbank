package br.com.vbank.services;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.vbank.domain.Conta;
import br.com.vbank.domain.Movimento;
import br.com.vbank.enums.TipoConta;
import br.com.vbank.integration.Integracao;
import br.com.vbank.repository.MovimentoRepository;

@Stateless
public class MovimentoService {

	@EJB
	private MovimentoRepository movimentoRepository;

	@EJB
	private IntegracaoService integracaoService;

	@EJB
	private ContaService contaService;
	
	public List<Movimento> getAll() {
		return movimentoRepository.getAll();
	}

	public void remove(Long id) {
		movimentoRepository.remove(id);
	}

	public Movimento save(Movimento movimento) {
		contaService.save(atualizaSaldo(movimento));
		Movimento mov = movimentoRepository.save(movimento);
		integracaoService.save(new Integracao().addMovimento(mov));
		return mov;
	}

	public Movimento findById(Long id) {
		return movimentoRepository.findById(id);
	}

	public Conta atualizaSaldo(Movimento movimento) {
		Conta conta = movimento.getClienteRemetente().getConta();
		if (movimento.getTipoContaDebito() == TipoConta.POUPANCA) {
			if ((conta.getVlSaldoContaPoupanca().subtract(movimento.getVlMovimento())).compareTo(BigDecimal.ZERO) > 0)
				conta.setVlSaldoContaPoupanca(conta.getVlSaldoContaPoupanca().subtract(movimento.getVlMovimento()));
			else
				throw new RuntimeException("O saldo é insuficiente para o movimento. Saldo:"+ conta.getVlSaldoContaPoupanca() + ", Valor Desconto: "+ movimento.getVlMovimento());
		} else if (movimento.getTipoContaDebito() == TipoConta.CORRENTE) {
			if ((conta.getVlSaldoContaCorrente().subtract(movimento.getVlMovimento())).compareTo(BigDecimal.ZERO) > 0)
				conta.setVlSaldoContaCorrente(conta.getVlSaldoContaCorrente().subtract(movimento.getVlMovimento()));
			else
				throw new RuntimeException("O saldo é insuficiente para o movimento. Saldo:"+ conta.getVlSaldoContaCorrente() + ", Valor Desconto: "+ movimento.getVlMovimento());
		}

		return conta;
	}
}
