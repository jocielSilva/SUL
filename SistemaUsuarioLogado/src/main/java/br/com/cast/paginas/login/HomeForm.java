package br.com.cast.paginas.login;

import java.util.Arrays;
import java.util.Date;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.datetime.StyleDateConverter;
import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import br.com.cast.entidades.Usuario;
import br.com.cast.enums.EnumSexo;
import br.com.cast.paginas.cadastro.CadastroContatoPage;
import br.com.cast.paginas.consulta.ConsultaUsuarioPage;
import br.com.cast.service.UsuarioService;

public class HomeForm extends Form<Usuario>{

	private static final long serialVersionUID = 8898704570651207679L;
	private RadioChoice<EnumSexo> radioSexo;
	private Usuario usuario = new Usuario();
	private DateTextField nascimento;
	@SpringBean
	private UsuarioService usuarioService;
	private FeedbackPanel feedback;
	private TextField<String> login;
	private TextField<String> senha;
	public HomeForm(String id) {
		
		super(id);
		add(feedback = new FeedbackPanel("mensagem"));
		feedback.setOutputMarkupId(true);
		add(login  = new TextField<String>("loginEntra", Model.of("")));
		login.setOutputMarkupId(true);
		add(senha = new TextField<String>("senhaEntra", Model.of("")));
		senha.setOutputMarkupId(true);
		
		add(new AjaxButton("entrar") {
			private static final long serialVersionUID = 5293965147189847339L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				
				if(verificarLoginSenha()){
				
				if(usuarioService.verificarAcesso(login.getModelObject(), senha.getModelObject(),usuario)){
					if(usuario.getDadosUsuario().isEmpty()){
						
						error("login ou senha inválido");
					}else{
					getSession().setAttribute("usuarioSessao", usuario.getDadosUsuario().get(0));
					setResponsePage(new ConsultaUsuarioPage(usuario.getDadosUsuario().get(0)));
					}
				}
				
				
				}
				target.add(feedback,login,senha);
			}


			
		});
		
		add(new TextField<Usuario>("nome", new PropertyModel<Usuario>(usuario, "nome")));
		radioSexo = new RadioChoice<EnumSexo>("genero", Arrays.asList(EnumSexo.values()));
		radioSexo.setChoiceRenderer(new ChoiceRenderer<EnumSexo>("descricao"));
		radioSexo.setSuffix("");
		radioSexo.setModel(new PropertyModel<EnumSexo>(usuario, "genero"));
		add(radioSexo);
		
		nascimento =  new DateTextField("dataNascimento", new PropertyModel<Date>(usuario, "dataNascimento"), new StyleDateConverter("S-",true));
		DatePicker datepicker = new DatePicker();
		datepicker.setShowOnFieldClick(true);
		datepicker.setAutoHide(true);
		nascimento.add(datepicker);
		add(nascimento);
		
		add(new TextField<Usuario>("cpf", new PropertyModel<Usuario>(usuario, "cpf")));
		
		add(new TextField<Usuario>("login", new PropertyModel<Usuario>(usuario, "login")));
		add(new TextField<Usuario>("senha", new PropertyModel<Usuario>(usuario, "senha")));
		
		add(new AjaxButton("cadastrar"){
	
			private static final long serialVersionUID = 810938299239324145L;
			
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				getSession().setAttribute("usuarioSessao",usuario);
				setResponsePage(new CadastroContatoPage(usuario));
			}
			
			@Override
			protected void onError(AjaxRequestTarget target, Form<?> form) {
				error("Erro ao cadastrar");
				target.add(feedback);
			}
		});
		
		
	}

	private boolean verificarLoginSenha() {
		boolean verificou = true;
		if(login.getModelObject() == null && senha.getModelObject() == null){
			error("Informe login e senha");
			verificou = false;
		}
		
		else if(login.getModelObject() == null || senha.getModelObject() == null){
			error("Informe login e senha");
			verificou = false;
		}
		return verificou;
	}
}

