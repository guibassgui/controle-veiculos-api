package br.com.zup.controle.handler.exceptions;

public class CpfInvalidoException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public CpfInvalidoException(String message) {
        super(message);
    }
}
