package br.com.cast.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cast.dao.EstadoDao;
import br.com.cast.dao.GenericoDao;
import br.com.cast.entidades.Estado;

@Service("estadoService")
public class EstadoService extends GenericoService<Estado> {

	private static final long serialVersionUID = 735003934454536178L;

	@Autowired
	private EstadoDao daoEstado;
	
	@Override
	protected GenericoDao<Estado> getDao() {
	
		return daoEstado;
	}

	public List<Estado> listarEstados() {
	
		return daoEstado.listarEstados();
	}

}
