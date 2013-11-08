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
@Table(name="tb_email")
public class Email implements Entidade {

	private static final long serialVersionUID = 6723027466131489254L;

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
	
	@Column(name="email")
	private String email;
	
	@Column(name="primario")
	private Boolean primario;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getPrimario() {
		return primario;
	}

	public void setPrimario(Boolean primario) {
		this.primario = primario;
	}
	
	
	

}
