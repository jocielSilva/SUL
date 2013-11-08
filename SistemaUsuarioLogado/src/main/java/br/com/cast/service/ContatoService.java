package br.com.cast.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cast.dao.ContatoDao;
import br.com.cast.dao.GenericoDao;
import br.com.cast.entidades.Contato;
import br.com.cast.entidades.Usuario;

@Service("contatoService")
public class ContatoService extends GenericoService<Contato> {

	private static final long serialVersionUID = -5695949063931010667L;
	
	@Autowired
	private ContatoDao daoContato;

	@Override
	protected GenericoDao<Contato> getDao() {

		return daoContato;
	}
	
	public Contato recuperarContatoUsuario(Usuario usuario){
		List<Contato> listaContato = daoContato.recuperarContatoUsuario(usuario);
		return listaContato.get(0);
	}


	public List<Contato> buscarCidadeUsuario(Usuario atual) {
		return null;
	}


	
	
}
