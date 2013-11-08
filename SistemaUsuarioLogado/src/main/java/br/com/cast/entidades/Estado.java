package br.com.cast.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.cast.to.Entidade;


@Entity
@Table(name="tb_estado")
public class Estado implements Entidade {

	private static final long serialVersionUID = -9170092433062185222L;

	@Override
	public Serializable getId() {
	
		return getCodigo();
	}
	
	@Id
	@Column(name="id")
	private Integer codigo;
	
	@Column(name="sigla")
	private String sigla;
	
	@Column(name="nome")
	private String nome;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	

}
