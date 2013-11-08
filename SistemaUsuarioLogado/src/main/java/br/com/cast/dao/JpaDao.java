package br.com.cast.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface JpaDao<T> extends Serializable
{

   void delete(final Serializable id);

   List<?> find(final String queryStr, final Object... params);

   T findById(final Serializable id);

   List<T> findByNamedParams(final String queryname, final Map<String, Object> params);

   List<T> findByNamedQuery(final String namedQuery, final Object... params);

   List<T> findByNativeQuery(final String sql, final Object... params);

   void refresh(final T entity);

   void save(final T obj);

   void setPersistentClass(final Class<T> persistentClass);

   void update(final T obj);

}
