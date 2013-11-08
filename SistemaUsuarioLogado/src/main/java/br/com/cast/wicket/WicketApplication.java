package br.com.cast.wicket;

import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

import br.com.cast.paginas.login.HomePage;

/**
 * Application object for your web application. If you want to run this application without
 * deploying, run the Start class.
 */
public class WicketApplication extends WebApplication
{
   /**
    * @see org.apache.wicket.Application#getHomePage()
    */
   @Override
   public Class<HomePage> getHomePage()
   {
      return HomePage.class;
   }

   /**
    * @see org.apache.wicket.Application#init()
    */
   @Override
   public void init()
   {
      super.init();
      getComponentInstantiationListeners().add(new SpringComponentInjector(this));
   }
}
