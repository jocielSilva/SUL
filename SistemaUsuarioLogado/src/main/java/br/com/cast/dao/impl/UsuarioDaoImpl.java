package br.com.cast.dao.impl;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;

import br.com.cast.dao.UsuarioDao;
import br.com.cast.entidades.Contato;
import br.com.cast.entidades.Usuario;

@Repository("usuarioDao")
@Scope(proxyMode = ScopedProxyMode.NO, value = "prototype")
public class UsuarioDaoImpl  extends GenericoDaoImpl<Usuario> implements UsuarioDao {


	private static final long serialVersionUID = -2688346166129914710L;

	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> verificarAcesso(String login, String senha, Usuario usuario) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("From Usuario u");
		sb.append(" WHERE 1=1");
		if(login != "" && senha != ""){
		sb.append(" and u.login = '"+ login+"'");
		sb.append(" and u.senha = '"+ senha+"'");
		}
		return getEntityManager().createQuery(sb.toString()).getResultList();
		
		 	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> listarUsuario(Usuario usuario) {
		
		
		return getEntityManager().createQuery("From Usuario u where u.nome != '" +usuario.getNome() +"'").getResultList();
	}

	@Override
	public List<Usuario> filtar(Usuario usuario, Contato contato) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("FROM Contato c, Usuario u");
		sb.append(" WHERE 1=1");
		if(usuario.getNome() != "")
		sb.append(" and u.nome like '%" + usuario.getNome()+"%'");
		return null;
	}

}
