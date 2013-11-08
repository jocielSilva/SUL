package br.com.cast.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.cast.dao.JpaDao;

@Repository("jpaDao")
@SuppressWarnings("unchecked")
public class JpaDaoImpl<T> implements JpaDao<T>
{

   private static final long serialVersionUID = 6907111403428623938L;

   private Class<T> persistentClass;

   @PersistenceContext
   private EntityManager entityManager;

   public List<?> find(final String queryStr, final Object... params)
   {
      return find(queryStr, null, params);
   }

   public T findById(final Serializable id)
   {
      return this.entityManager.find(getPersistentClass(), id);
   }

   public List<T> findByNamedParams(final String queryname, final Map<String, Object> params)
   {
      final Query query = this.entityManager.createNamedQuery(queryname);
      setQueryParams(query, params);
      return query.getResultList();
   }

   public List<T> findAll()
   {
      final Query query =
         this.entityManager.createQuery(" FROM " + getPersistentClass().getSimpleName());
      return query.getResultList();
   }

   public List<T> findByNamedQuery(final String namedQuery, final Object... params)
   {
      return findByNamedQuery(namedQuery, null, params);
   }

   public List<T> findByNativeQuery(final String sql, final Object... params)
   {
      return findByNativeQuery(sql, null, params);
   }

   public Class<T> getPersistentClass()
   {
      if (persistentClass == null)
      {
         throw new RuntimeException(
            "É necessário invocar o método setPersistentClass(Class<T> clazz)");
      }
      return persistentClass;
   }

   public void refresh(final T entity)
   {
      this.entityManager.refresh(entity);
   }

   public void save(final T obj)
   {
      this.entityManager.persist(obj);
   }

   public void setPersistentClass(final Class<T> persistentClass)
   {
      this.persistentClass = persistentClass;
   }

   private void setQueryParams(final Query query, final Map<String, Object> params)
   {
      for (final Entry<String, Object> entry : params.entrySet())
      {
         query.setParameter(entry.getKey(), entry.getValue());
      }
   }

   public void delete(final Serializable id)
   {
      this.entityManager.remove(findById(id));
   }

   public void update(final T obj)
   {
      this.entityManager.merge(obj);
   }

}
