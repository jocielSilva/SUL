package br.com.cast.paginas.cadastro;


import java.util.Arrays;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import br.com.cast.entidades.Cidade;
import br.com.cast.entidades.Contato;
import br.com.cast.entidades.Email;
import br.com.cast.entidades.Telefone;
import br.com.cast.entidades.Usuario;
import br.com.cast.enums.EnumDDD;
import br.com.cast.paginas.consulta.ConsultaUsuarioPage;
import br.com.cast.service.CidadeService;
import br.com.cast.service.ContatoService;
import br.com.cast.service.DistanciaService;
import br.com.cast.service.EmailService;
import br.com.cast.service.TelefoneService;
import br.com.cast.service.UsuarioService;
import br.com.cast.to.EnderecoTO;

public class CadastroContatoForm extends Form<Contato>{

	private static final long serialVersionUID = 4899104811001525746L;

	private Contato contato = new Contato();
	private Telefone telefone = new Telefone();
	private Email email = new Email();
	private FeedbackPanel feedback;
	
	@SpringBean
	private ContatoService contatoService;
	@SpringBean
	private UsuarioService usuarioService;
	@SpringBean
	private TelefoneService telefoneService;
	@SpringBean
	private EmailService emailService;
	@SpringBean
	private CidadeService ciadeService;
	@SpringBean
	private DistanciaService distanciaService;
	
	private DropDownChoice<Cidade> dropCidade;

	private DropDownChoice<EnumDDD> dropDDD;

	private TextField<Integer> cep;

	private TextField<String> logradouro;

	private TextField<String> bairro;

	public CadastroContatoForm(String id, final Usuario usuario) {
		super(id);
		setOutputMarkupId(true);
		setModel(new CompoundPropertyModel<Contato>(contato));
		
		add(feedback = new FeedbackPanel("mensagem"));
		feedback.setOutputMarkupId(true);
		cep = new TextField<Integer>("cep");
		cep.setOutputMarkupId(true);
		cep.add(new AjaxFormComponentUpdatingBehavior("onblur") {
	
			private static final long serialVersionUID = -8461023411776815403L;

			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				
				EnderecoTO enderecoEncontrado = distanciaService.recuperarEndereco(cep.getModelObject());
				if(!enderecoEncontrado.getCidade().equals("")){
				logradouro.setModel(Model.of(enderecoEncontrado.getLogradouro()));
				bairro.setModel(Model.of(enderecoEncontrado.getBairro()));
				} else {
					error("Cep não encontrado.");
				}
				target.add(cep,logradouro,bairro, feedback);
			}
		});
		add(cep);
		add(logradouro = new TextField<String>("logradouro"));
		logradouro.setModel(new PropertyModel<String>(contato, "logradouro"));
		logradouro.setOutputMarkupId(true);
		
		add(bairro = new TextField<String>("bairro"));
		bairro.setModel(new PropertyModel<String>(contato, "bairro"));
		bairro.setOutputMarkupId(true);
		
		add(new TextField<String>("complemento"));
		add(new TextField<Email>("email", new PropertyModel<Email>(email, "email")));
		add(new TextField<Telefone>("numero", new PropertyModel<Telefone>(telefone, "numero")));
		
		dropDDD = new DropDownChoice<EnumDDD>("ddd", Arrays.asList(EnumDDD.values()));
		dropDDD.setChoiceRenderer(new ChoiceRenderer<EnumDDD>("codigo"));
		dropDDD.setModel(new Model<EnumDDD>());
		dropDDD.setOutputMarkupId(true);
		
		dropCidade = new DropDownChoice<Cidade>("cidade",ciadeService.listar());
		dropCidade.setChoiceRenderer(new ChoiceRenderer<Cidade>("nome"));
		dropCidade.setModel(new Model<Cidade>());
		//dropCidade.setModel(Model.of(distanciaService.recuperarEndereco(cep.getModelObject()).getCidade()));
		dropCidade.setOutputMarkupId(true);
		add(dropCidade);
		add(dropDDD);
		
		telefone.setDdi(55);
		
		telefone.setContato(contato);
		telefone.setPrimario(true);
		email.setContato(contato);
		email.setPrimario(true);
		contato.setUsuario(usuario);
		
		
		add(new AjaxButton("addNumero"){
			
			private static final long serialVersionUID = -8526720114330977751L;

		});
		
		add(new AjaxButton("addEmail"){
			
			private static final long serialVersionUID = -8526720114330977751L;

		});
		
		add(new AjaxButton("confirmar") {

			private static final long serialVersionUID = -8862852069835315519L;
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				telefone.setDdd(dropDDD.getModelObject().getCodigo());
				contato.setCidade(dropCidade.getModelObject());
				usuarioService.incluir(usuario);
				contato.setBairro(bairro.getModelObject());
				contato.setLogradouro(logradouro.getModelObject());
				contatoService.incluir(contato);
				telefoneService.incluir(telefone);
				emailService.incluir(email);
				
				success("Cadastro realizado com sucesso !");
				setResponsePage(new ConsultaUsuarioPage(usuario));
				target.add(feedback,dropDDD,dropCidade);
			}
			
			@Override
			protected void onError(AjaxRequestTarget target, Form<?> form) {

				error("Erro ao cadastrar ");
				target.add(feedback);
			}
			
		});
		
		
	}

}
