package br.com.cast.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericoDao<E extends Serializable> extends JpaDao<E> {

	public List<E> listar();

	public E obter(final Serializable id);

	public void incluir(final E entidade);

	public void excluir(final E entidade);

	public void alterar(final E entidade);

}
