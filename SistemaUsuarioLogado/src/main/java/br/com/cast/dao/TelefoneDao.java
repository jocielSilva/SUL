package br.com.cast.dao;

import java.util.List;

import br.com.cast.entidades.Telefone;

public interface TelefoneDao extends GenericoDao<Telefone> {

	List<Telefone> listarDDD();

}
