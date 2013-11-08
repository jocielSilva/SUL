package br.com.cast.paginas.login;

import org.apache.wicket.markup.html.WebPage;

public class HomePage extends WebPage
{
   private static final long serialVersionUID = 1L;

   public HomePage()
   {
      add(new HomeForm("formularioHome"));
   }

}
