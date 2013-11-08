package br.com.cast.dao;

import java.util.List;

import br.com.cast.entidades.Estado;

public interface EstadoDao extends GenericoDao<Estado> {

	List<Estado> listarEstados();


}
