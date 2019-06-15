package br.com.rp.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Random;

import javax.persistence.*;


@Entity
@Table(name = "conta")
public class Conta extends BaseEntity implements Serializable {

	@Column(name = "nr_conta", length = 20, nullable = false)
	private String nrConta;

	@Column(name = "is_contacorrente", length = 1, nullable = false)
	private Boolean isContaCorrente;

	@Column(name = "is_contapoupanca", length = 1, nullable = false)
	private Boolean isContaPoupanca;

	@Column(name = "vl_saldo_conta_poupanca", precision = 14, scale = 2, nullable = true)
	private BigDecimal vlSaldoContaPoupanca;
	
	@Column(name = "vl_saldo_conta_corrente", precision = 14, scale = 2, nullable = true)
	private BigDecimal vlSaldoContaCorrente;

	@OneToOne
	@JoinColumn(name = "cliente_id", nullable = false, insertable = false, updatable = false)
	private Cliente cliente;

	public Conta() {
	}

	public Conta(String nrConta, Boolean isContaCorrente, Boolean isContaPoupanca) {
		this.nrConta = nrConta;
		this.isContaCorrente = isContaCorrente;
		this.isContaPoupanca = isContaPoupanca;
	}

	public Conta(Cliente cliente) {
		this.nrConta = Integer.toString(new Random().nextInt(9999999));
		this.cliente = cliente;
		this.isContaCorrente = true;
		this.isContaPoupanca = true;
		this.vlSaldoContaCorrente = BigDecimal.ZERO;
		this.vlSaldoContaPoupanca = BigDecimal.ZERO;
	}

	public String getNrConta() {
		return nrConta;
	}

	public void setNrConta(String nrConta) {
		this.nrConta = nrConta;
	}

	public Boolean getIsContaCorrente() {
		return isContaCorrente;
	}

	public void setIsContaCorrente(Boolean isContaCorrente) {
		this.isContaCorrente = isContaCorrente;
	}

	public Boolean getIsContaPoupanca() {
		return isContaPoupanca;
	}

	public void setIsContaPoupanca(Boolean isContaPoupanca) {
		this.isContaPoupanca = isContaPoupanca;
	}

	public BigDecimal getVlSaldoContaPoupanca() {
		return vlSaldoContaPoupanca;
	}

	public void setVlSaldoContaPoupanca(BigDecimal vlSaldoContaPoupanca) {
		this.vlSaldoContaPoupanca = vlSaldoContaPoupanca;
	}

	public BigDecimal getVlSaldoContaCorrente() {
		return vlSaldoContaCorrente;
	}

	public void setVlSaldoContaCorrente(BigDecimal vlSaldoContaCorrente) {
		this.vlSaldoContaCorrente = vlSaldoContaCorrente;
	}

}
