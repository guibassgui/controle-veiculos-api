package br.com.zup.controle.resource;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class FipeResource {
    @JsonProperty
    @NotBlank(message="Não pode ser em branco ou nulo")
    private String veiculo;
    @JsonProperty
    @NotBlank(message="Não pode ser em branco ou nulo")
    private String codigoMarca;
    @JsonProperty
    @NotBlank(message="Não pode ser em branco ou nulo")
    private String codigoModelo;
    @JsonProperty
    @NotBlank(message="Não pode ser em branco ou nulo")
    private String codigoAno;

    public String getVeiculo() {
        return veiculo;
    }

    public String getCodigoMarca() {
        return codigoMarca;
    }

    public String getCodigoModelo() {
        return codigoModelo;
    }

    public String getCodigoAno() {
        return codigoAno;
    }
}
