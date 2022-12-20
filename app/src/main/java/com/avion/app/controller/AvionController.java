package com.avion.app.controller;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.avion.app.model.Avion;
import com.avion.app.model.ObjectReturn;
import com.avion.app.myException.NotFoundException;
import com.avion.app.myException.SessionLostException;
import com.avion.app.service.AvionService;
import com.avion.app.service.TokenService;

@RestController
@CrossOrigin
public class AvionController {
	@GetMapping("/avions")
	public ObjectReturn getAll(
			@RequestParam(name = "hash", required = true)String hash
			) throws SessionLostException,ClassNotFoundException, SQLException, NoSuchAlgorithmException {
		
		TokenService.bearerToken(hash);
		ObjectReturn data = null;
		try {
			Avion[] vehicles = AvionService.selectAll();
			data = new ObjectReturn();
			data.setData(vehicles);
			data.setMessage("succes");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		}
		System.out.println("liste");
		System.out.println("isa");
		System.out.println("----");
		return data;
		
	}
	
	@GetMapping("/avions/{id}")
	public ObjectReturn getById(
			@PathVariable(name = "id")int id,
			@RequestParam(name = "hash", required = true)String hash) throws Exception {
		TokenService.bearerToken(hash);
		ObjectReturn objet = null;
		
		try {
			
			Avion vehicle = AvionService.getById(id);
			objet=new ObjectReturn();
			objet.setData(vehicle);
			objet.setMessage("succes");
		} catch (ClassNotFoundException | SQLException | NotFoundException e) {
			// TODO Auto-generated catch block
			throw e;
		}
		System.out.println("byId");
		System.out.println("isa");
		System.out.println("****");
		return objet;
	}
}
