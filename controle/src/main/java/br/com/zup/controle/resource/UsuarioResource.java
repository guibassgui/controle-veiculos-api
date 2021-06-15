package br.com.zup.controle.resource;

import br.com.zup.controle.model.Usuario;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UsuarioResource {

	@JsonProperty
	@NotBlank(message="Não pode ser em branco ou nulo")
    private String nome;
	@JsonProperty
	@NotBlank(message="Não pode ser em branco ou nulo")
    private String nascimento;
	@JsonProperty
	@NotBlank(message="Não pode ser em branco ou nulo")
    private String email;
	@JsonProperty
	@NotBlank(message="Não pode ser em branco ou nulo")
    private String cpf;
	
	public String getNome() {
		return nome;
	}
	public String getNascimento() {
		return nascimento;
	}
	public String getEmail() {
		return email;
	}
	public String getCpf() {
		return cpf;
	}

	public Usuario converter(UsuarioResource usuario) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		Usuario convertido = new Usuario();
		convertido.setNome(usuario.getNome());
		convertido.setCpf(usuario.getCpf());
		convertido.setNascimento(LocalDate.parse(usuario.getNascimento(),formatter));
		convertido.setEmail(usuario.getEmail());
		return convertido;
	}
}
