package br.com.cast.paginas.consulta;

import org.apache.wicket.markup.html.WebPage;

import br.com.cast.entidades.Usuario;

public class ConsultaUsuarioPage extends WebPage {

	private static final long serialVersionUID = 3812308117582270674L;


	public ConsultaUsuarioPage(Usuario usuario) {
		add(new ConsultaUsuarioForm("formularioConsulta", usuario));
	}
}
