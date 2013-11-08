package br.com.cast.entidades;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.cast.enums.EnumSexo;
import br.com.cast.to.Entidade;

@Entity
@Table(name = "tb_usuario")
public class Usuario implements Entidade {

	private static final long serialVersionUID = 6553727251111306136L;

	@Override
	public Serializable getId() {

		return getCodigo();
	}

	@Id
	@Column(name = "id")
	@GeneratedValue
	private Integer codigo;

	@Column(name = "nome")
	private String nome;

	@Column(name = "genero")
	private EnumSexo genero;

	@Column(name = "data_nascimento")
	private Date dataNascimento;

	@Column(name = "cpf")
	private Long cpf;

	@Column(name = "login")
	private String login;

	@Column(name = "senha")
	private String senha;

	@Column(name = "data_cadastro")
	private Date dataCadastro;

	@Transient
	private Integer idade;
	@Transient
	private List<Usuario> dadosUsuario;
	
	public List<Usuario> getDadosUsuario() {
		return dadosUsuario;
	}

	public void setDadosUsuario(List<Usuario> dadosUsuario) {
		this.dadosUsuario = dadosUsuario;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public EnumSexo getGenero() {
		return genero;
	}

	public void setGenero(EnumSexo genero) {
		this.genero = genero;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Integer getIdade() {
		Date dataHOje = new Date();
		Calendar cal = Calendar.getInstance();
		
		cal.setTime(dataHOje);
		int dia1 = cal.get(Calendar.DAY_OF_YEAR);
		int ano1 = cal.get(Calendar.YEAR);
		
		cal.setTime(getDataNascimento());
		int dia2 = cal.get(Calendar.DAY_OF_YEAR);
		int ano2 = cal.get(Calendar.YEAR);
		
		int nAno = ano1 - ano2;
		
		if (dia1 < dia2){
			nAno--;
		}
		
		return nAno;
	}

}
