package jsfjpa.entity;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Cacheable
@Table(name="Automovel", schema="public")
public class Automovel {
	
	@Id @GeneratedValue
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="modelo_id")
	private Modelo modelo;
	
	private String marca;
	
	private Integer anoFabricacao;
	private Integer anoModelo;
	
	private Double preco;
	private Double kilometragem;
	
	private String observacoes;
	
	public Automovel() {
		// TODO Auto-generated constructor stub
	}
	
	

	public Double getPreco() {
		return preco;
	}



	public void setPreco(Double preco) {
		this.preco = preco;
	}



	public Double getKilometragem() {
		return kilometragem;
	}



	public void setKilometragem(Double kilometragem) {
		this.kilometragem = kilometragem;
	}



	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	
	public Modelo getModelo() {
		return modelo;
	}



	public Integer getAnoFabricacao() {
		return anoFabricacao;
	}

	public void setAnoFabricacao(Integer anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}

	public Integer getAnoModelo() {
		return anoModelo;
	}

	public void setAnoModelo(Integer anoModelo) {
		this.anoModelo = anoModelo;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	
	
	

}
