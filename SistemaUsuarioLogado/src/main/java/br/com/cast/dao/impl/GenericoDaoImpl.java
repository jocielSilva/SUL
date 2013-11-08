package br.com.cast.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import br.com.cast.dao.GenericoDao;
import br.com.cast.to.Entidade;

@SuppressWarnings("unchecked")
public class GenericoDaoImpl<E extends Entidade> extends JpaDaoImpl<E> implements GenericoDao<E>
{

   private static final long serialVersionUID = 3207593623716337968L;

   @PersistenceContext
   private EntityManager entityManager;

   private HibernateTemplate hibernateTemplate = new HibernateTemplate();

   @Autowired
   private void instanciar()
   {
      try
      {
         final ParameterizedType tipo = (ParameterizedType) getClass().getGenericSuperclass();
         final Type[] parametro = tipo.getActualTypeArguments();
         Class<?> tipoGenerico = null;
         if (parametro != null && (0 < parametro.length))
         {
            tipoGenerico = (Class<?>) parametro[0];
            setPersistentClass((Class<E>) tipoGenerico);
            setHibernateTemplate(new HibernateTemplate(
               ((Session) entityManager.getDelegate()).getSessionFactory()));
         }
      }
      catch (final Exception e)
      {
         e.printStackTrace();
      }
   }

   public EntityManager getEntityManager()
   {
      return entityManager;
   }

   public void setHibernateTemplate(final HibernateTemplate hibernateTemplate)
   {
      this.hibernateTemplate = hibernateTemplate;
   }

   public HibernateTemplate getHibernateTemplate()
   {
      return hibernateTemplate;
   }

   public void incluir(final E entidade)
   {
      save(entidade);
   }

   public void excluir(final E entidade)
   {
      delete(entidade.getId());
   }

   public void alterar(final E entidade)
   {
      update(entidade);
   }

   public E obter(final Serializable id)
   {
      return findById(id);
   }

   public List<E> listar()
   {
      return findAll();
   }

}
