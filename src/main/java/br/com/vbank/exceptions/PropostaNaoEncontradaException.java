package br.com.vbank.exceptions;

public class PropostaNaoEncontradaException extends NotFoundException {

	public PropostaNaoEncontradaException() {
		super("Proposta não encontrada.");
	}
}
