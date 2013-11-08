
package br.com.cast.paginas.cadastro;

import org.apache.wicket.markup.html.WebPage;

import br.com.cast.entidades.Usuario;

public class CadastroContatoPage extends WebPage {

	private static final long serialVersionUID = -8910331232115484932L;
	
	public CadastroContatoPage() {

		add(new CadastroContatoForm("formularioCadastro", new Usuario()));
	}

	public CadastroContatoPage(Usuario usuario) {
		add(new CadastroContatoForm("formularioCadastro", usuario));
	}

}
