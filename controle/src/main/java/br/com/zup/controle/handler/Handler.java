package br.com.zup.controle.handler;


import br.com.zup.controle.handler.exceptions.CpfInvalidoException;
import br.com.zup.controle.handler.exceptions.EmailInvalidoException;
import br.com.zup.controle.handler.exceptions.UsuarioNaoEncontradoException;
import feign.FeignException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class Handler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> MethodArgumentNotValid(MethodArgumentNotValidException e){
        List<Error> erros = e.getFieldErrors().stream().map(Error::new).collect(Collectors.toList());
        return ResponseEntity.badRequest().body(erros);
    }

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity<?> MethodArgumentNotValid(UsuarioNaoEncontradoException e){
        Error error = new Error(e.getMessage(), "id");
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(EmailInvalidoException.class)
    public ResponseEntity<?> MethodArgumentNotValid(EmailInvalidoException e){
        Error error = new Error(e.getMessage(), "email");
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(CpfInvalidoException.class)
    public ResponseEntity<?> MethodArgumentNotValid(CpfInvalidoException e){
        Error error = new Error(e.getMessage(), "cpf");
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(FeignException.NotFound.class)
    public ResponseEntity<?> MethodArgumentNotValid(FeignException.NotFound e){
        Error error = new Error("Algum parâmetro foi informado incorretamente", "veiculo, codigoMarca, codigoModelo, codigoAno");
        return ResponseEntity.badRequest().body(error);
    }

}