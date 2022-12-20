package com.avion.app.service;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import com.avion.app.DAO.AdminDAO;
import com.avion.app.DAO.TokenDAO;
import com.avion.app.model.Admin;
import com.avion.app.myException.AuthentificationFailedException;
import com.avion.app.model.Token;

public class AdminService {

	public static Object[] toLog(Admin x) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException, AuthentificationFailedException {
		
		Admin ans = AdminDAO.toLog(x);
		if(ans==null) {
			throw new AuthentificationFailedException();
		}
		Token token = TokenDAO.insertToken(ans);
		
		Object[] objet = new Object[2];
		objet[0] = ans;
		objet[1] = token;
		
		return objet;
	}
	
}