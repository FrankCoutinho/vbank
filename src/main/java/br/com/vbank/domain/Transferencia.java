package br.com.vbank.domain;

import br.com.vbank.enums.SituacaoTransferencia;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "transferencia")
public class Transferencia extends BaseEntity implements Serializable {
	
	@Column(name = "nr_conta_remetente", length = 20, nullable = false)
	private String nrContaRemetente;
	
	@Column(name = "nr_conta_favorecido", length = 20, nullable = false)
	private String nrContaFavorecido;
	
	@Column(name = "vl_tranferencia", precision = 14, scale = 2, nullable = false)
	private BigDecimal vlTransferencia;
	
	@Column(name = "dt_transferencia", nullable = true)
	private LocalDate dtTransferencia;
			
	@Enumerated(EnumType.STRING)
	@Column(name = "tp_situacao", length = 15, nullable = false)
	private SituacaoTransferencia situacaoTransferencia;
	
	@JoinColumn(name = "dt_agendamento", referencedColumnName = "id", nullable = true)
	private LocalDateTime dtAgendamento;
	
	public Transferencia() {

	}
	
	public Transferencia(String nrContaRemetente, String nrContaFavorecido, BigDecimal vlTransferencia, LocalDate dtTransferencia) {
		this.nrContaRemetente = nrContaRemetente;
		this.nrContaFavorecido = nrContaFavorecido;
		this.vlTransferencia = vlTransferencia;
		this.dtTransferencia = dtTransferencia;
		this.situacaoTransferencia = SituacaoTransferencia.PENDENTE;
		this.dtAgendamento = LocalDateTime.now();
	}

	public String getNrContaRemetente() {
		return nrContaRemetente;
	}

	public void setNrContaRemetente(String nrContaRemetente) {
		this.nrContaRemetente = nrContaRemetente;
	}

	public String getNrContaFavorecido() {
		return nrContaFavorecido;
	}

	public void setNrContaFavorecido(String nrContaFavorecido) {
		this.nrContaFavorecido = nrContaFavorecido;
	}

	public BigDecimal getVlTransferencia() {
		return vlTransferencia;
	}

	public void setVlTransferencia(BigDecimal vlTransferencia) {
		this.vlTransferencia = vlTransferencia;
	}

	public LocalDate getDtTransferencia() {
		return dtTransferencia;
	}

	public void setDtTransferencia(LocalDate dtTransferencia) {
		this.dtTransferencia = dtTransferencia;
	}

	public SituacaoTransferencia getSituacaoTransferencia() {
		return situacaoTransferencia;
	}

	public void setSituacaoTransferencia(SituacaoTransferencia situacaoTransferencia) {
		this.situacaoTransferencia = situacaoTransferencia;
	}

	public LocalDateTime getDtAgendamento() {
		return dtAgendamento;
	}

	public void setDtAgendamento(LocalDateTime dtAgendamento) {
		this.dtAgendamento = dtAgendamento;
	}

}
