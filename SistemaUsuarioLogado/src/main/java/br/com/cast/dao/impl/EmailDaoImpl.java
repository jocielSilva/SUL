package br.com.cast.dao.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;

import br.com.cast.dao.EmailDao;
import br.com.cast.entidades.Email;

@Repository("emailDao")
@Scope(proxyMode = ScopedProxyMode.NO, value = "prototype")
public class EmailDaoImpl extends GenericoDaoImpl<Email> implements EmailDao {


	private static final long serialVersionUID = 7613165103423109863L;


}
