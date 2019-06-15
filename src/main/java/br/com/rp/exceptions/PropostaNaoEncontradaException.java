package br.com.rp.exceptions;

public class PropostaNaoEncontradaException extends NotFoundException {

	public PropostaNaoEncontradaException() {
		super("Proposta não encontrada.");
	}
}
