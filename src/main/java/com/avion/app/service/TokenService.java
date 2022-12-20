package com.avion.app.service;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.avion.app.DAO.TokenDAO;
import com.avion.app.model.Token;
import com.avion.app.myException.SessionLostException;

public class TokenService {
	
	public static Token getToken(String token) throws ClassNotFoundException, NoSuchAlgorithmException, SQLException {
		return TokenDAO.getToken(token);
	}
	
	public static void bearerToken(String hash) throws SessionLostException,ClassNotFoundException,SQLException, NoSuchAlgorithmException {
		Token token = null;
		
		boolean ans = false;
		try {
			token = getToken(hash);
			if(token!=null) {
				if(token.getDateExpiration().after(Timestamp.valueOf(LocalDateTime.now()))){
					ans = true;
				}
				else {
					throw new SessionLostException();
				}
			}
			else {
				//update token
				throw new SessionLostException();
			}
		} catch (SessionLostException e) {
			throw e;
			// TODO: handle exception
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		} 
		
	}
}
