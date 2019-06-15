package br.com.vbank.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "papel")
public class Papel extends BaseEntity implements Serializable{
	
	
	@Column(name = "ds_papel", length = 60, nullable = false)
	private String descricaoPapel;
	
	public Papel() {

	}
	
	public Papel(String descricaoPapel) {
		super();
		this.descricaoPapel = descricaoPapel;
	}

	public String getDescricaoAcao() {
		return descricaoPapel;
	}

	public void setDescricaoAcao(String descricaoPapel) {
		this.descricaoPapel = descricaoPapel;
	}

}
