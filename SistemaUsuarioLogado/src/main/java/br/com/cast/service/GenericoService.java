package br.com.cast.service;

import java.io.Serializable;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import br.com.cast.dao.GenericoDao;
import br.com.cast.to.Entidade;

public abstract class GenericoService<E extends Entidade> implements Serializable
{

   private static final long serialVersionUID = 8992534276130856464L;

   protected abstract GenericoDao<E> getDao();

   @Transactional(readOnly = true)
   public E obter(final Serializable id)
   {
      return getDao().obter(id);
   }

   @Transactional(readOnly = true)
   public List<E> listar()
   {
      return getDao().listar();
   }

   @Transactional(readOnly = false)
   public void incluir(final E entidade)
   {
      getDao().incluir(entidade);
   }

   @Transactional(readOnly = false)
   public void alterar(final E entidade)
   {
      getDao().alterar(entidade);
   }

   @Transactional(readOnly = false)
   public void excluir(final Serializable id)
   {
      getDao().excluir(obter(id));
   }

   @Transactional(readOnly = false)
   public void excluirTodos(final List<E> entidades)
   {
      if (entidades != null && !entidades.isEmpty())
      {
         for (final E entidade : entidades)
         {
            excluir(entidade);
         }
      }
   }

}
