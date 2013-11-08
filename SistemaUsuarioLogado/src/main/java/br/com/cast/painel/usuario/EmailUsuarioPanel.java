package br.com.cast.painel.usuario;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;

import org.apache.wicket.model.Model;

public class EmailUsuarioPanel extends Panel {
	
	private static final long serialVersionUID = -6976143100851405919L;

	public EmailUsuarioPanel(String id) {
		super(id);
		
		add(new TextField<String>("endereco", Model.of("")));
		add(new Button("addEndereco", Model.of("")));
		add(new Button("removeEndereco", Model.of("")));
		
	}

}
