package br.com.rp.domain;

import br.com.rp.enums.SituacaoEmail;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import static br.com.rp.enums.SituacaoEmail.ENVIANDO;

@Entity
@Table(name = "email")
public class Email extends BaseEntity implements Serializable{

	@Column(name = "remetente", length = 50, nullable = true)
	private String remetente;
	
	@Column(name = "destinatario", length = 50, nullable = false)
	private String destinatario;
	
	@Column(name = "assunto", length = 200, nullable = false)
	private String assunto;
	
	@Lob
	@Column(name = "descricao", nullable = false)
	private String descricao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dh_envio", nullable = false)
	private Date dhEnvio;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tp_situacao", length = 30, nullable = false)
	private SituacaoEmail situacao;

	public Email() {
	}

	public Email(String remetente, String destinatario, String assunto, String descricao) {
		this.assunto = assunto;
		this.descricao = descricao;
		this.remetente = remetente;
		this.destinatario = destinatario;
		this.situacao = ENVIANDO;
		this.dhEnvio = Calendar.getInstance().getTime();
	}

	public String getDestinatario() {
		return destinatario;
	}

	public String getAssunto() {
		return assunto;
	}

	public String getDescricao() {
		return descricao;
	}
}
