package br.com.cast.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cast.dao.GenericoDao;
import br.com.cast.dao.UsuarioDao;
import br.com.cast.entidades.Contato;
import br.com.cast.entidades.Usuario;

@Service("usuarioService")
public class UsuarioService extends GenericoService<Usuario> {

	private static final long serialVersionUID = -848982406360476468L;

	@Autowired
	private UsuarioDao daoUsuario;
	@Override
	protected GenericoDao<Usuario> getDao() {
	
		return daoUsuario;
	}
	public boolean verificarAcesso(String login, String senha,Usuario usuario) {
		boolean verifica = false;
		
		 List<Usuario> listaUsuario = daoUsuario.verificarAcesso(login,senha, usuario);
		
		if(listaUsuario.size() >=0 ){
			usuario.setDadosUsuario(listaUsuario);
			verifica = true;
		}
		return verifica;
	}
	public List<Usuario> listarUsuario(Usuario usuario) {
		
		return daoUsuario.listarUsuario(usuario);
	}
	public List<Usuario> filtar(Usuario usuarioSessao, Contato contato) {

		return daoUsuario.filtar(usuarioSessao,contato);
	}
	
	}

