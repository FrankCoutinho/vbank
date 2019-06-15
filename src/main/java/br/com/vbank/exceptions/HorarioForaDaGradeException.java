package br.com.vbank.exceptions;

public class HorarioForaDaGradeException extends RuntimeException {

    public HorarioForaDaGradeException() {
        super("Grade de pagamentos fechada.");
    }
}
