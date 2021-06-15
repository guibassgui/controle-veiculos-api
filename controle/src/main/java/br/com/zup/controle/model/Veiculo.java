package br.com.zup.controle.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Veiculo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) 
	private Long id;
	@Column( nullable = false)
	private String marca;
	@Column( nullable = false)
	private String modelo;
	@Column( nullable = false)
	private Integer ano;

	@Column( nullable = false)
	private String valor;

	@Column( nullable = false)
	private String diaRodizio;

	@Column( nullable = false)
	private Boolean rodizioAtivo;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "usuario_id_fk")
	private Usuario usuario;
	
	public Veiculo() {
		
	}
	public Long getId() {
		return id;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public Integer getAno() {
		return ano;
	}
	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setDiaRodizio(String diaRodizio) {
		this.diaRodizio = diaRodizio;
	}

	public void setRodizioAtivo(Boolean rodizioAtivo) {
		this.rodizioAtivo = rodizioAtivo;
	}

	public String getDiaRodizio() {
		return diaRodizio;
	}

	public Boolean getRodizioAtivo() {
		return rodizioAtivo;
	}
}
