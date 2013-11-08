package br.com.cast.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.cast.to.Entidade;

@Entity
@Table(name="tb_telefone")
public class Telefone implements Entidade {

	private static final long serialVersionUID = 8667004624240675708L;

	@Override
	public Serializable getId() {
		return getCodigo();
	}
	
	@Id
	@Column(name="id")
	@GeneratedValue
	private Integer codigo;
	
	
	@ManyToOne
	@JoinColumn(name="id_contato")
	private Contato contato;
	
	@Column(name="ddi")
	private Integer ddi;
	
	@Column(name="ddd")
	private Integer ddd;
	
	@Column(name="numero")
	private Long numero;
	
	@Column(name="primario")
	private Boolean primario;

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public Integer getDdi() {
		return ddi;
	}

	public void setDdi(Integer ddi) {
		this.ddi = ddi;
	}

	public Integer getDdd() {
		return ddd;
	}

	public void setDdd(Integer ddd) {
		this.ddd = ddd;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public Boolean getPrimario() {
		return primario;
	}

	public void setPrimario(Boolean primario) {
		this.primario = primario;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	
}
