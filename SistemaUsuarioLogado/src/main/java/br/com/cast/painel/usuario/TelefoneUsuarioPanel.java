package br.com.cast.painel.usuario;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

public class TelefoneUsuarioPanel extends Panel {

	private static final long serialVersionUID = -727802220396666584L;

	public TelefoneUsuarioPanel(String id) {
		super(id);
		
		add(new TextField<String>("telefone", Model.of("")));
		add(new Button("addTelefone", Model.of("")));
		add(new Button("removeTelefone", Model.of("")));
		
		
	}

}
