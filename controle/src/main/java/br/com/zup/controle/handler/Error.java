package br.com.zup.controle.handler;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.FieldError;

public class Error {
    @JsonProperty
    private final String mensagem;
    @JsonProperty
    private final String campo;

    public Error(FieldError error) {
        this.mensagem = error.getDefaultMessage();
        this.campo = error.getField();
    }

    public Error(String mensagem, String campo) {
        this.mensagem = mensagem;
        this.campo = campo;
    }
}