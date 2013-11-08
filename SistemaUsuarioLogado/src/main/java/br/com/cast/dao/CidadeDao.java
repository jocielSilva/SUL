package br.com.cast.dao;

import java.util.List;

import br.com.cast.entidades.Cidade;
import br.com.cast.entidades.Estado;

public interface CidadeDao extends GenericoDao<Cidade> {

	List<Cidade> listarCidades(Estado estado);

}
