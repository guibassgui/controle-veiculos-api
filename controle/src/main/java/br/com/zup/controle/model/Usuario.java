package br.com.zup.controle.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Usuario {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) 
	private Long id;  	
	@Column( nullable = false)
    private String nome;
	@Column( nullable = false)
    private LocalDate nascimento;
	@Column(unique = true,  nullable = false)
    private String email;
	@Column(unique = true,  nullable = false)
    private String cpf;
	@Column
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	private List<Veiculo> veiculos = new ArrayList<>();
	
	public Usuario() {
		
	}	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public LocalDate getNascimento() {
		return nascimento;
	}
	public void setNascimento(LocalDate nascimento) {
		this.nascimento = nascimento;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public List<Veiculo> getVeiculos() {
		return veiculos;
	}

	public void setVeiculos(List<Veiculo> veiculos) {
		this.veiculos = veiculos;
	}
}
