package br.com.cast.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cast.dao.GenericoDao;
import br.com.cast.dao.TelefoneDao;
import br.com.cast.entidades.Telefone;

@Service("telefoneService")
public class TelefoneService extends GenericoService<Telefone> {


	private static final long serialVersionUID = -6803039457227027528L;

	@Autowired
	private TelefoneDao daoTelefone;
	
	@Override
	protected GenericoDao<Telefone> getDao() {
		
		return daoTelefone;
	}

	public List<Telefone> listarDDD() {
		// TODO Auto-generated method stub
		return daoTelefone.listarDDD();
	}

}
