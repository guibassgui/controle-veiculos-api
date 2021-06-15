package br.com.zup.controle.controller;

import br.com.zup.controle.resource.FipeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "fipe", url = "https://parallelum.com.br/fipe/api/v1/")
public interface FipeController {

        @RequestMapping("/{veiculo}/marcas")
        String getTipoVeiculo(@PathVariable("veiculo") String veiculo);

        @RequestMapping("/{veiculo}/marcas/{codigoMarca}/modelos")
        String getModeloVeiculo(@PathVariable("veiculo") String veiculo,
        		@PathVariable("codigoMarca") String codigoMarca);

        @RequestMapping("/{veiculo}/marcas/{codigoMarca}/modelos/{codigoModelo}/anos")
        String getAnosVeiculo(@PathVariable("veiculo") String veiculo,
        		@PathVariable("codigoMarca") String codigoMarca,
        		@PathVariable("codigoModelo") String codigoModelo);

        @RequestMapping("/{veiculo}/marcas/{codigoMarca}/modelos/{codigoModelo}/anos/{codigoAno}")
        FipeResponse getVeiculo(@PathVariable("veiculo") String veiculo,
        		@PathVariable("codigoMarca") String codigoMarca,
        		@PathVariable("codigoModelo") String codigoModelo,
        		@PathVariable("codigoAno") String codigoAno);
}

