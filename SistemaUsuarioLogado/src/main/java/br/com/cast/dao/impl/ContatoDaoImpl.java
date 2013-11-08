package br.com.cast.dao.impl;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;

import br.com.cast.dao.ContatoDao;
import br.com.cast.entidades.Contato;
import br.com.cast.entidades.Usuario;

@Repository("contatoDao")
@Scope(proxyMode = ScopedProxyMode.NO, value = "prototype")
public class ContatoDaoImpl extends GenericoDaoImpl<Contato> implements ContatoDao {

	private static final long serialVersionUID = -4947411298590321295L;


	@SuppressWarnings("unchecked")
	@Override
	public List<Contato> recuperarContatoUsuario(Usuario usuario) {
		
	return getEntityManager().createQuery("From Contato c where c.usuario = " + usuario.getCodigo()).getResultList();
	
	}



}
