package com.avion.app.controller;

import java.sql.SQLException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.avion.app.model.ObjectReturn;
import com.avion.app.service.AssuranceService;
import com.avion.app.service.TokenService;

@RestController
@CrossOrigin
public class StatistiqueController {
	
	@GetMapping("/statistiques")
	public ObjectReturn getStat(@RequestParam(name = "hash", required = true)String hash) throws Exception {
		ObjectReturn ans = new ObjectReturn();
		
		try {
			TokenService.bearerToken(hash);
			Object[] data = AssuranceService.getAssuranceExpire23();
			ans.setData(data);
			ans.setMessage("succes");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		}
		
		return ans;
	}
}
