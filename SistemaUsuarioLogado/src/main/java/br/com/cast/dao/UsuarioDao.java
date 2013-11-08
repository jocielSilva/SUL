package br.com.cast.dao;

import java.util.List;

import br.com.cast.entidades.Contato;
import br.com.cast.entidades.Usuario;


public interface UsuarioDao extends GenericoDao<Usuario>{

	List<Usuario> verificarAcesso(String login, String senha, Usuario usuario);

	List<Usuario> listarUsuario(Usuario usuario);

	List<Usuario> filtar(Usuario usuarioSessao, Contato contato);
	
	

}
