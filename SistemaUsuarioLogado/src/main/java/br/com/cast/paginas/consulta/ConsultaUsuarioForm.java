package br.com.cast.paginas.consulta;

import javax.servlet.http.HttpServletResponse;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import br.com.cast.componentes.util.RelatorioFactory;
import br.com.cast.entidades.Cidade;
import br.com.cast.entidades.Estado;
import br.com.cast.entidades.Usuario;
import br.com.cast.paginas.login.HomePage;
import br.com.cast.painel.usuario.ConsultaUsuarioPanel;
import br.com.cast.service.CidadeService;
import br.com.cast.service.EstadoService;
import br.com.cast.service.UsuarioService;

public class ConsultaUsuarioForm extends Form<Void> {

	private static final long serialVersionUID = -362327857214442930L;
	private ConsultaUsuarioPanel panelUsuario;
	private Usuario usuario = new Usuario();
	
	private DropDownChoice<Estado> dropestado;
	private DropDownChoice<Cidade> dropCidade;
	private CheckBox opcao;
	private WebMarkupContainer campoCidade;
	private WebMarkupContainer campoDistancia;
	
	@SpringBean
	private EstadoService estadoService;
	@SpringBean
	private CidadeService cidadeService;
	@SpringBean
	private UsuarioService usuarioService;
	
	public ConsultaUsuarioForm(String id, final Usuario usuarioSessao) {
		super(id);
	
		add(new TextField<Usuario>("nome", new PropertyModel<Usuario>(usuario, "nome")));
		
		campoDistancia = new WebMarkupContainer("distanciaMaxima");
		campoDistancia.add(new TextField<String>("distancia", Model.of("")));
		campoDistancia.setOutputMarkupPlaceholderTag(true);
		campoDistancia.setVisible(false);
		add(campoDistancia);
		
		dropestado = new DropDownChoice<Estado>("estado", estadoService.listarEstados());
		dropestado.setModel(new Model<Estado>());
		dropestado.setChoiceRenderer(new ChoiceRenderer<Estado>("nome"));
		dropestado.add(new AjaxFormComponentUpdatingBehavior("onchange"){

			private static final long serialVersionUID = 73152959031500006L;

			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				if(dropestado.getModelObject() == null){
					campoCidade.setVisible(false);
					target.add(campoCidade,dropestado);
				}
				else{
				dropCidade.setChoices(cidadeService.listarCidades(dropestado.getModelObject()));
				campoCidade.setVisible(true);
				target.add(campoCidade,dropCidade);}
			}
			
		});
		add(dropestado);
		
		campoCidade = new WebMarkupContainer("comboCidade");
		campoCidade.setOutputMarkupPlaceholderTag(true);
		dropCidade = new DropDownChoice<Cidade>("cidade");
		dropCidade.setOutputMarkupPlaceholderTag(true);
		dropCidade.setChoiceRenderer(new ChoiceRenderer<Cidade>("nome"));
		dropCidade.setModel(new Model<Cidade>());
		campoCidade.add(dropCidade);
		campoCidade.setVisible(false);
		add(campoCidade);
		
		opcao = new CheckBox("opcao");
		opcao.setModel(new Model<Boolean>());
		opcao.add(new AjaxFormComponentUpdatingBehavior("onclick") {
			
			private static final long serialVersionUID = -5484301811530165730L;

			protected void onUpdate(AjaxRequestTarget target) {
				if(opcao.getModelObject()){
				campoDistancia.setVisible(true);
				} else {
					campoDistancia.setVisible(false);
				}
				target.add(campoDistancia);
			}
		});
		add(opcao);
		
		add(new AjaxButton("filtro") {

			private static final long serialVersionUID = -2599216278434763653L;
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
					
			//	usuarioService.filtar(usuario);

			}
			
		});
		
		
		add(new AjaxButton("sair") {

			private static final long serialVersionUID = -2599216278434763653L;
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				getSession().removeAttribute("usuarioSessao");
				setResponsePage(new HomePage());
			}
			
		});
		
		add(new Button("btRelatorio"){

			private static final long serialVersionUID = 5428718389845909496L;

			@Override
			public void onSubmit() {
				
				String relatorioURL = "C:/Cast Informática S.A/ProjetosExtra/Jociel/SistemaUsuarioLogado/src/main/java/br/com/cast/componentes/util/relatorios/Usuario.jasper";
				RelatorioFactory.gerarRelatorio((HttpServletResponse) getResponse().getContainerResponse(), usuarioService.listar(), relatorioURL, null);
			}
		});
		
		panelUsuario = new ConsultaUsuarioPanel("panelConsulta");
		panelUsuario.setOutputMarkupId(true);
		panelUsuario.gridFiltro(usuarioService.listarUsuario(usuarioSessao));
		//panelUsuario.setVisible(false);
		addOrReplace(panelUsuario);
	}

}
