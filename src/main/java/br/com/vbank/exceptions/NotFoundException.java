package br.com.vbank.exceptions;

public class NotFoundException extends RuntimeException {
	
	public NotFoundException(String mensagem) {
		super(mensagem);
	}
}
