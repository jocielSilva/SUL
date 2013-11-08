package br.com.cast.dao.impl;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;

import br.com.cast.dao.TelefoneDao;
import br.com.cast.entidades.Telefone;

@Repository("telefoneDao")
@Scope(proxyMode = ScopedProxyMode.NO, value = "prototype")
public class TelefoneDaoImpl extends GenericoDaoImpl<Telefone> implements TelefoneDao {

	private static final long serialVersionUID = -1059849272445639153L;

	@SuppressWarnings("unchecked")
	@Override
	public List<Telefone> listarDDD() {
		
		return getEntityManager().createQuery("From Telefone").getResultList();
	}

	
}
