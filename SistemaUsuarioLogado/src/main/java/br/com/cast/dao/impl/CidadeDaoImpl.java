package br.com.cast.dao.impl;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;

import br.com.cast.dao.CidadeDao;
import br.com.cast.entidades.Cidade;
import br.com.cast.entidades.Estado;

@Repository("cidadeDao")
@Scope(proxyMode = ScopedProxyMode.NO, value = "prototype")
public class CidadeDaoImpl extends GenericoDaoImpl<Cidade> implements CidadeDao {

	private static final long serialVersionUID = -6395879422505490972L;


	@SuppressWarnings("unchecked")
	@Override
	public List<Cidade> listarCidades(Estado estado) {
		
		return getEntityManager().createQuery("From Cidade c where c.estado = " + estado.getCodigo()).getResultList();

	}


}
