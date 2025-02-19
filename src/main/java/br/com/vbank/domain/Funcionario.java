package br.com.vbank.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "funcionario")
public class Funcionario extends BaseEntity implements Serializable {

	@Column(name = "nome", length = 60, nullable = false)
	private String nome;

	@Column(name = "cpf", length = 14, nullable = false)
	private String cpf;

	@Column(name = "email", length = 200, nullable = false)
	private String email;
	
	/*
	 * Cargo do funcionário.
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_cargo", referencedColumnName = "id", nullable = false)
	private Cargo cargo;

	public Funcionario() {

	}

	public Funcionario(String nome, String email, String cpf, Cargo cargo) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.cargo = cargo;
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

}
