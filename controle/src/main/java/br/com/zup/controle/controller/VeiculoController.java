package br.com.zup.controle.controller;

import br.com.zup.controle.handler.exceptions.UsuarioNaoEncontradoException;
import br.com.zup.controle.model.Usuario;
import br.com.zup.controle.model.Veiculo;
import br.com.zup.controle.repository.UsuarioRepository;
import br.com.zup.controle.repository.VeiculoRepository;
import br.com.zup.controle.resource.FipeResource;
import br.com.zup.controle.resource.FipeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@EnableFeignClients
@RestController
@RequestMapping(value="/api/veiculos")
public class VeiculoController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    VeiculoRepository veiculoRepository;

    @Autowired
    FipeController fipe;

    @GetMapping
    public ResponseEntity<List<Veiculo>> buscar(){
        List<Veiculo>lista=veiculoRepository.findAll();
        atualizaDiaRodizio(lista);
        return ResponseEntity.ok().body(lista);
    }

    @PostMapping(value="/cadastrar/{id}")
    public ResponseEntity<?>cadastrar(@Valid  @PathVariable("id") Long id,
    		@Valid @RequestBody FipeResource resource){
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if(!usuario.isPresent()){
            throw  new UsuarioNaoEncontradoException("Usuário não encontrado pelo ID informado");
        }else {
            Veiculo veiculo = new Veiculo();
            FipeResponse fipeResponse = fipe.getVeiculo(resource.getVeiculo() ,
            		resource.getCodigoMarca(), resource.getCodigoModelo(), resource.getCodigoAno());
            veiculo.setUsuario(usuario.get());
            veiculo.setMarca(fipeResponse.getMarca());
            veiculo.setModelo(fipeResponse.getModelo());
            veiculo.setAno(fipeResponse.getAnoModelo());
            veiculo.setValor(fipeResponse.getValor());
            veiculo.setDiaRodizio(diaRodizioVeiculo(fipeResponse.getAnoModelo()));
            veiculo.setRodizioAtivo(verificaDiaRodizio(veiculo.getDiaRodizio()));
            return ResponseEntity.created(null).body(veiculoRepository.saveAndFlush(veiculo));
        }
    }

    @PostMapping("/marcas")
    public ResponseEntity<?> tiposVeiculos(@RequestBody FipeResource resource){
        return ResponseEntity.ok().body(fipe.getTipoVeiculo(resource.getVeiculo()));
    }

    @PostMapping("/modelos")
    public ResponseEntity<?> modelosVeiculos(@RequestBody FipeResource resource){
        return ResponseEntity.ok().body(fipe.getModeloVeiculo(resource.getVeiculo() ,
        		resource.getCodigoMarca()));
    }

    @PostMapping("/anos")
    public ResponseEntity<?> anosVeiculos(@RequestBody FipeResource resource){
        return ResponseEntity.ok().body(fipe.getAnosVeiculo(resource.getVeiculo() ,
        		resource.getCodigoMarca(), resource.getCodigoModelo()));
    }

    @PostMapping("/detalhes")
    public ResponseEntity<?> detalhesPedidos(@RequestBody FipeResource resource){
        return ResponseEntity.ok().body(fipe.getVeiculo(resource.getVeiculo() ,
        		resource.getCodigoMarca(), resource.getCodigoModelo(), resource.getCodigoAno()));
    }

    private String diaRodizioVeiculo(Integer ano) {
        String anoConvertido = ano.toString();
        char ultimoDigito = anoConvertido.charAt(anoConvertido.length() - 1);
        String diaSemana = null;
        switch (ultimoDigito) {
           case '0':
                diaSemana = "segunda-feira";
                break;
           case '1':
                diaSemana = "segunda-feira";
                break;
           case '2':
                diaSemana = "terça-feira";
                break;
           case '3':
               diaSemana = "terça-feira";
                break;
           case '4':
                diaSemana = "quarta-feira";
                break;
           case '5':
                diaSemana = "quarta-feira";
                break;
           case '6':
                diaSemana = "quinta-feira";
                break;
           case '7':
                diaSemana = "quinta-feira";
                break;
           case '8':
                diaSemana = "sexta-feira";
                break;
           case '9':
                diaSemana = "sexta-feira";
                break;
        }
        return diaSemana;
    }

    Boolean verificaDiaRodizio(String diaRodizio)  {

        //LocalDateTime agora = LocalDateTime.of(2021, 6, 8, 19, 18);
        LocalDateTime agora = LocalDateTime.now();
        Date data = Date.from(agora.atZone(ZoneId.systemDefault()).toInstant());
        DateFormat formatter = new SimpleDateFormat("EEEE");
        String diaAtual = formatter.format(data);
        return diaAtual.equals(diaRodizio);
    }

    List<Veiculo> atualizaDiaRodizio(List<Veiculo> lista){
        Veiculo veiculo;
        for(int i =0; i< lista.size();i++){
            veiculo = lista.get(i);
            veiculo.setRodizioAtivo(verificaDiaRodizio(veiculo.getDiaRodizio()));
            lista.set(i, veiculo);
        }
        return lista;
    }

}
