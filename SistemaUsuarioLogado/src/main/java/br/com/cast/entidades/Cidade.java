package br.com.cast.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.cast.to.Entidade;

@Entity
@Table(name="tb_cidade")
public class Cidade implements Entidade {

	private static final long serialVersionUID = 6457280250051617298L;

	@Override
	public Serializable getId() {		
		return getCodigo();
	}
	
	@Id
	@Column(name="id")
	private Integer codigo;

	@ManyToOne
	@JoinColumn(name="id_estado")
	private Estado estado;

	@Column(name="nome")
	private String nomeCidade;
	
	@Column(name="latitude")
	private Double latitude;
	
	@Column(name="longitude")
	private Double longitude;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Estado getCodigoEstado() {
		return estado;
	}
	

	public void setCodigoEstado(Estado codigoEstado) {
		this.estado = codigoEstado;
	}

	
	public String getNome() {
		return nomeCidade;
	}

	public void setNome(String nome) {
		this.nomeCidade = nome;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLogitude(Double logitude) {
		this.longitude = logitude;
	}
	
	
}


