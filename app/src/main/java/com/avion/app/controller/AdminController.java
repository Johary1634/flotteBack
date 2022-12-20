package com.avion.app.controller;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.avion.app.model.Admin;
import com.avion.app.model.ObjectReturn;
import com.avion.app.myException.AuthentificationFailedException;
import com.avion.app.service.AdminService;

@RestController
@CrossOrigin
public class AdminController {
	
	@PostMapping("/login")
	public ObjectReturn login(@RequestBody Admin admin) throws Exception {
		
		ObjectReturn ans = null;
		Object[] data = null;
		
		try {
			data = AdminService.toLog(admin);
			ans = new ObjectReturn();
			ans.setData(data);
			ans.setMessage("Authentification success");
		}
		catch (ClassNotFoundException | NoSuchAlgorithmException | SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		}
		catch (AuthentificationFailedException e) {
			// TODO Auto-generated catch block
			throw e;
		}
		return ans;	
	}
}