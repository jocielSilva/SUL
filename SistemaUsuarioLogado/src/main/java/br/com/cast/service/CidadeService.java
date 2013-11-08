package br.com.cast.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cast.dao.CidadeDao;
import br.com.cast.dao.GenericoDao;
import br.com.cast.entidades.Cidade;
import br.com.cast.entidades.Contato;
import br.com.cast.entidades.Estado;
import br.com.cast.entidades.Usuario;

@Service("cidadeService")
public class CidadeService extends GenericoService<Cidade> {

	private static final long serialVersionUID = 7391013816599889224L;

	@Autowired
	private CidadeDao daoCidade;
	
	@Autowired
	private ContatoService serviceContato;
	
	@Override
	protected GenericoDao<Cidade> getDao() {

		return daoCidade;
	}

	public List<Cidade> listarCidades(Estado estado) {
	
		return daoCidade.listarCidades(estado);
	}

	public Cidade recuperarCidadeUsuario(Usuario atual) {
		
		Contato contatoUsuario =  serviceContato.recuperarContatoUsuario(atual);
		Cidade cidadeUsuario = contatoUsuario.getCidade();
		return cidadeUsuario;
	}

}
