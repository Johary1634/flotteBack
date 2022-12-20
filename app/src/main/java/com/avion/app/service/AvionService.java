package com.avion.app.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.avion.app.DAO.AssuranceDAO;
import com.avion.app.DAO.AvionDAO;
import com.avion.app.DAO.Connexion;
import com.avion.app.model.Assurance;
import com.avion.app.model.Avion;
import com.avion.app.myException.NotFoundException;

public class AvionService {
	public static Avion[] selectAll() throws ClassNotFoundException, SQLException {
		return AvionDAO.selectAll();
	}
	public static Avion getById(int idVehicule) throws ClassNotFoundException, SQLException,NotFoundException {
		Connection co = Connexion.getConnect();
		Avion ans = null;

		try {
			
			ans = AvionDAO.getById(idVehicule);
			if(ans==null)throw new NotFoundException();
			//System.out.println("IIIIIIDDDDD========================="+ans.getId());
			Assurance assurance = AssuranceDAO.getAssurancByIdVehicule(ans.getId(),co);
			if(assurance!=null) {
				ans.setDebutAssurance(assurance.getDebut());
				ans.setFinAssurance(assurance.getFin());
			}
			
		} catch (ClassNotFoundException| SQLException| NotFoundException e) {
			// TODO: handle exception
			throw e;
		}
		
		return ans;
	}
}
