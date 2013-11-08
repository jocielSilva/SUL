package br.com.cast.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cast.dao.EmailDao;
import br.com.cast.dao.GenericoDao;
import br.com.cast.entidades.Email;

@Service("emailService")
public class EmailService extends GenericoService<Email> {

	private static final long serialVersionUID = 5659971565352282857L;

	@Autowired
	private EmailDao daoEmail;
	
	@Override
	protected GenericoDao<Email> getDao() {
		
		return daoEmail;
	}

}
