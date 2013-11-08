package br.com.cast.dao;

import java.util.List;

import br.com.cast.entidades.Contato;
import br.com.cast.entidades.Usuario;

public interface ContatoDao extends GenericoDao<Contato>{


	List<Contato> recuperarContatoUsuario(Usuario usuario);

}
