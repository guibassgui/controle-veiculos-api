package br.com.zup.controle.controller;

import br.com.zup.controle.handler.exceptions.CpfInvalidoException;
import br.com.zup.controle.handler.exceptions.EmailInvalidoException;
import br.com.zup.controle.handler.exceptions.UsuarioNaoEncontradoException;
import br.com.zup.controle.model.Usuario;
import br.com.zup.controle.model.Veiculo;
import br.com.zup.controle.repository.UsuarioRepository;
import br.com.zup.controle.resource.UsuarioResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping(value="/api/usuarios")
public class UsuarioController {

    @Autowired
	UsuarioRepository repository;

    @Autowired
    VeiculoController veiculoController;

    @GetMapping(value="/{id}")
    public ResponseEntity<?>buscarPorID(@Valid @PathVariable("id") Long id){
        Optional<Usuario> usuario = repository.findById(id);
        if(!usuario.isPresent()){
            throw  new UsuarioNaoEncontradoException("Usuário não encontrado pelo ID informado");
        }else {
            usuario.get().setVeiculos(veiculoController.atualizaDiaRodizio(usuario.get().getVeiculos()));
            return ResponseEntity.ok().body(usuario);
        }
    }
    
    @PostMapping(value="/cadastrar")
    public ResponseEntity<Usuario>cadastrar( @Valid @RequestBody UsuarioResource resource){
    Usuario usuario=resource.converter(resource);
    Optional<Usuario> aux = Optional.ofNullable(repository.findByEmail(usuario.getEmail()));
    if(aux.isPresent()){
        throw new EmailInvalidoException("email já em uso, escolha em outro");
    }
    aux = Optional.ofNullable(repository.findByCpf(usuario.getCpf()));
    if(aux.isPresent()){
        throw new CpfInvalidoException("CPF já em uso, escolha em outro");
    }
    return ResponseEntity.created(null).body(repository.saveAndFlush(usuario));
    }

}
