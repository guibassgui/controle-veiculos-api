package br.com.zup.controle.resource;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FipeResponse {

    @JsonProperty
    private String Valor ;
    @JsonProperty
    private String Marca ;
    @JsonProperty
    private String Modelo ;
    @JsonProperty
    private Integer AnoModelo;
    @JsonProperty
    private String Combustivel;
    @JsonProperty
    private String CodigoFipe;
    @JsonProperty
    private String MesReferencia;
    @JsonProperty
    private Integer TipoVeiculo;
    @JsonProperty
    private String SiglaCombustivel;

    public String getValor() {
        return Valor;
    }

    public String getMarca() {
        return Marca;
    }

    public String getModelo() {
        return Modelo;
    }

    public Integer getAnoModelo() {
        return AnoModelo;
    }

    public String getCombustivel() {
        return Combustivel;
    }

    public String getCodigoFipe() {
        return CodigoFipe;
    }

    public String getMesReferencia() {
        return MesReferencia;
    }

    public Integer getTipoVeiculo() {
        return TipoVeiculo;
    }

    public String getSiglaCombustivel() {
        return SiglaCombustivel;
    }
}
