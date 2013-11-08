package br.com.cast.dao.impl;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;

import br.com.cast.dao.EstadoDao;
import br.com.cast.entidades.Estado;

@Repository("estadoDao")
@Scope(proxyMode = ScopedProxyMode.NO, value = "prototype")
public class EstadoDaoImpl extends GenericoDaoImpl<Estado> implements EstadoDao {

	private static final long serialVersionUID = 4463704848926454822L;

	@SuppressWarnings("unchecked")
	@Override
	public List<Estado> listarEstados() {
		
		return getEntityManager().createQuery("FROM Estado").getResultList();
	}


}
