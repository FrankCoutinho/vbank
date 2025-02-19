package br.com.vbank.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.vbank.enums.TipoConta;
import br.com.vbank.enums.TipoMovimento;
import br.com.vbank.enums.TipoTransacao;

@Entity
@Table(name = "movimento")
public class Movimento extends BaseEntity implements Serializable {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cliente_id", referencedColumnName = "id", nullable = true)
	private Cliente clienteRemetente;

	@Column(name = "codigo_barra", length = 100, nullable = true)
	private String codigoBarra;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "agendamento_id", referencedColumnName = "id", nullable = true)
	private Agendamento agendamento;

	@Column(name = "vl_movimento", precision = 14, scale = 2, nullable = true)
	private BigDecimal vlMovimento;

	@Enumerated(EnumType.STRING)
	@Column(name = "tp_conta_debito", length = 15, nullable = true)
	private TipoConta tipoContaDebito;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_movimento", nullable = true)
	private Date dtMovimento;

	@Column(name = "nr_conta_favorecido", length = 20, nullable = true)
	private String nrContaFavorecido;

	@Column(name = "agencia_favorecido", length = 10, nullable = true)
	private String agenciaFavorecido;

	@Column(name = "email_favorecido", length = 50, nullable = true)
	private String emailFavorecido;

	@Enumerated(EnumType.STRING)
	@Column(name = "tp_conta_favorecido", length = 15, nullable = true)
	private TipoConta tipoContaFavorecido;

	@Lob
	@Column(name = "foto_cheque_frente", nullable = true)
	private byte[] fotoChequeFrente;

	@Lob
	@Column(name = "foto_cheque_verso", nullable = true)
	private byte[] fotoChequeVerso;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "banco_id", referencedColumnName = "id", nullable = true)
	private Banco bancoFavorecido;

	@Enumerated(EnumType.STRING)
	@Column(name = "tp_movimento", length = 20, nullable = true)
	private TipoMovimento tipoMovimento;

	@Enumerated(EnumType.STRING)
	@Column(name = "tp_transacao", length = 15, nullable = false)
	private TipoTransacao tipoTransacao;

	public Movimento() {

	}
	public Movimento addMovimento(Object object) {
		if (object instanceof Transferencia){
			return addTransferencia((Transferencia) object);
		}else if(object instanceof Pagamento) {
			return addPagamento((Pagamento) object);
		} else if (object instanceof Deposito){
			return addDeposito((Deposito) object);
		}else{
			throw new RuntimeException("O objeto informado não é do tipo esperado.");
		}
	}
	
	private Movimento addTransferencia(Transferencia transferencia) {
		Movimento movimento = new Movimento();
//		movimento.setClienteRemetente(transferencia.getClienteRemetente());
//		movimento.setDtMovimento(transferencia.getDtTransferencia());
//		movimento.setAgendamento(transferencia.getAgendamento());
//		movimento.setVlMovimento(transferencia.getVlTranferencia());
//		movimento.setNrContaFavorecido(transferencia.getNrContaFavorecido());
//		movimento.setAgenciaFavorecido(transferencia.getAgenciaFavorecido());
//		movimento.setEmailFavorecido(transferencia.getEmailFavorecido());
//		movimento.setTipoContaFavorecido(transferencia.getTipoContaFavorecido());
//		movimento.setTipoContaDebito(transferencia.getTipoContaDebito());
//		movimento.setBancoFavorecido(transferencia.getBancoFavorecido());
		movimento.setTipoMovimento(TipoMovimento.SAIDA);
		movimento.setTipoTransacao(TipoTransacao.TRANSFERENCIA);
		return movimento;
	}

	private Movimento addPagamento(Pagamento pagamento) {
		Movimento movimento = new Movimento();
		movimento.setClienteRemetente(pagamento.getClienteRemetente());
		movimento.setCodigoBarra(pagamento.getCodigoBarra());
		movimento.setDtMovimento(pagamento.getDtPagamento());
		movimento.setAgendamento(pagamento.getAgendamento());
		movimento.setVlMovimento(pagamento.getVlPagamento());
		movimento.setTipoContaDebito(pagamento.getTipoContaDebito());
		movimento.setTipoMovimento(TipoMovimento.SAIDA);
		movimento.setTipoTransacao(TipoTransacao.PAGAMENTO);
		return movimento;
	}

	private Movimento addDeposito(Deposito deposito) {
		Movimento movimento = new Movimento();
		movimento.setClienteRemetente(deposito.getClienteRemetente());
		movimento.setDtMovimento(deposito.getDtDeposito());
		movimento.setAgendamento(deposito.getAgendamento());
		movimento.setVlMovimento(deposito.getVlDeposito());
		movimento.setAgenciaFavorecido(deposito.getAgenciaFavorecido());
		movimento.setNrContaFavorecido(deposito.getNrContaFavorecido());
		movimento.setEmailFavorecido(deposito.getEmailFavorecido());
		movimento.setTipoContaFavorecido(deposito.getTipoContaFavorecido());
		movimento.setTipoContaDebito(deposito.getTipoContaDebito());
		movimento.setFotoChequeFrente(deposito.getFotoChequeFrente());
		movimento.setFotoChequeVerso(deposito.getFotoChequeVerso());
		movimento.setBancoFavorecido(deposito.getBancoFavorecido());
		movimento.setTipoMovimento(TipoMovimento.SAIDA);
		movimento.setTipoTransacao(TipoTransacao.DEPOSITO);
		return movimento;
	}

	public Cliente getClienteRemetente() {
		return clienteRemetente;
	}

	public void setClienteRemetente(Cliente clienteRemetente) {
		this.clienteRemetente = clienteRemetente;
	}

	public String getCodigoBarra() {
		return codigoBarra;
	}

	public void setCodigoBarra(String codigoBarra) {
		this.codigoBarra = codigoBarra;
	}

	public Agendamento getAgendamento() {
		return agendamento;
	}

	public void setAgendamento(Agendamento agendamento) {
		this.agendamento = agendamento;
	}

	public BigDecimal getVlMovimento() {
		return vlMovimento;
	}

	public void setVlMovimento(BigDecimal vlMovimento) {
		this.vlMovimento = vlMovimento;
	}

	public TipoConta getTipoContaDebito() {
		return tipoContaDebito;
	}

	public void setTipoContaDebito(TipoConta tipoContaDebito) {
		this.tipoContaDebito = tipoContaDebito;
	}

	public Date getDtMovimento() {
		return dtMovimento;
	}

	public void setDtMovimento(Date dtMovimento) {
		this.dtMovimento = dtMovimento;
	}

	public String getNrContaFavorecido() {
		return nrContaFavorecido;
	}

	public void setNrContaFavorecido(String nrContaFavorecido) {
		this.nrContaFavorecido = nrContaFavorecido;
	}

	public String getAgenciaFavorecido() {
		return agenciaFavorecido;
	}

	public void setAgenciaFavorecido(String agenciaFavorecido) {
		this.agenciaFavorecido = agenciaFavorecido;
	}

	public String getEmailFavorecido() {
		return emailFavorecido;
	}

	public void setEmailFavorecido(String emailFavorecido) {
		this.emailFavorecido = emailFavorecido;
	}

	public TipoConta getTipoContaFavorecido() {
		return tipoContaFavorecido;
	}

	public void setTipoContaFavorecido(TipoConta tipoContaFavorecido) {
		this.tipoContaFavorecido = tipoContaFavorecido;
	}

	public byte[] getFotoChequeFrente() {
		return fotoChequeFrente;
	}

	public void setFotoChequeFrente(byte[] fotoChequeFrente) {
		this.fotoChequeFrente = fotoChequeFrente;
	}

	public byte[] getFotoChequeVerso() {
		return fotoChequeVerso;
	}

	public void setFotoChequeVerso(byte[] fotoChequeVerso) {
		this.fotoChequeVerso = fotoChequeVerso;
	}

	public Banco getBancoFavorecido() {
		return bancoFavorecido;
	}

	public void setBancoFavorecido(Banco bancoFavorecido) {
		this.bancoFavorecido = bancoFavorecido;
	}

	public TipoMovimento getTipoMovimento() {
		return tipoMovimento;
	}

	public void setTipoMovimento(TipoMovimento tipoMovimento) {
		this.tipoMovimento = tipoMovimento;
	}

	public TipoTransacao getTipoTransacao() {
		return tipoTransacao;
	}

	public void setTipoTransacao(TipoTransacao tipoTransacao) {
		this.tipoTransacao = tipoTransacao;
	}
}
