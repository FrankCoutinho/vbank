package br.com.vbank.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Random;


@Entity
@DiscriminatorValue(value = "cliente")
public class UsuarioCliente extends Usuario {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cliente_id", nullable = true)
	private Cliente cliente;

	public UsuarioCliente() {
		super();
	}

    public UsuarioCliente(Cliente cliente) {
    	super(cliente.getCpf(), Integer.toString(new Random().nextInt(9_999_999)));
    }

    public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}
