package br.com.vbank.domain;

import br.com.vbank.enums.SituacaoTransferencia;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "transferencia")
public class Transferencia extends BaseEntity implements Serializable {
	
	@Column(name = "nr_conta_remetente", length = 20, nullable = false)
	private String nrContaRemetente;
	
	@Column(name = "nr_conta_favorecido", length = 20, nullable = false)
	private String nrContaFavorecido;
	
	@Column(name = "vl_tranferencia", precision = 14, scale = 2, nullable = false)
	private BigDecimal vlTransferencia;
	
	@Column(name = "dt_transferencia")
	private LocalDateTime dtTransferencia;

	@Column(name = "dt_agendamento", nullable = false)
	private LocalDateTime dtAgendamento;

	@Enumerated(EnumType.STRING)
	@Column(name = "tp_situacao", length = 15, nullable = false)
	private SituacaoTransferencia situacaoTransferencia;

	public Transferencia() {

	}
	
	public Transferencia(
		String nrContaRemetente,
		String nrContaFavorecido,
		BigDecimal vlTransferencia,
		LocalDateTime dtTransferencia
	) {
		this.nrContaRemetente = nrContaRemetente;
		this.nrContaFavorecido = nrContaFavorecido;
		this.vlTransferencia = vlTransferencia;
		this.dtTransferencia = dtTransferencia;
		this.situacaoTransferencia = SituacaoTransferencia.PENDENTE;
		this.dtAgendamento = LocalDateTime.now();
	}
}
