package com.avion.app.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.avion.app.DAO.AssuranceDAO;
import com.avion.app.DAO.Connexion;
import com.avion.app.model.Assurance;

public class AssuranceService {
	
	public static Assurance[] getAssuranceExpire(int mois) throws ClassNotFoundException, SQLException {
		return AssuranceDAO.getAssuranceExpire(mois);
	}
	
	public static Object[] getAssuranceExpire23() throws ClassNotFoundException, SQLException {
		Object[] objet = new Object[2];
		try {
			Connection co = Connexion.getConnect();
			Assurance[] deuxMois  = AssuranceDAO.getAssuranceExpire(1, co);
			Assurance[] troisMois = AssuranceDAO.getAssuranceExpire(3, co);
			objet[0] = deuxMois;
			objet[1] = troisMois;
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		}
		
		return objet;
	}
	
}
