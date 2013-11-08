package br.com.cast.painel.usuario;

import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.spring.injection.annot.SpringBean;

import br.com.cast.entidades.Usuario;
import br.com.cast.service.CidadeService;
import br.com.cast.service.DistanciaService;

public class ConsultaUsuarioPanel extends Panel {

	private static final long serialVersionUID = 2054654186391637277L;
	
	@SpringBean
	private CidadeService serviceCidade;
	@SpringBean
	private DistanciaService distanciaService;
	
	public ConsultaUsuarioPanel(String id) {
		super(id);
		
		setOutputMarkupId(true);
	}

	public List<Usuario> gridFiltro(List<Usuario> lista){
	
	DataView<Usuario> repeticao = new DataView<Usuario>("gridFiltro", 
			new ListDataProvider<Usuario>(lista),3) {


		private static final long serialVersionUID = -5472273693630199672L;

		@Override
		protected void populateItem(Item<Usuario> item) {
			Usuario atual = item.getModelObject();
			item.add(new Label("nome", atual.getNome()));
			item.add(new Label("cidade", serviceCidade.recuperarCidadeUsuario(atual).getNome()));
			item.add(new Label("idade", atual.getIdade()));
			item.add(new Label("distancia", distanciaService.calcularDistanciaWebService(atual, (Usuario) getSession().getAttribute("usuarioSessao")).getDistancia()));
		}
	};
	
	addOrReplace(new PagingNavigator("repeticao", repeticao));
	addOrReplace(repeticao);
	
	return lista;
}	
}
