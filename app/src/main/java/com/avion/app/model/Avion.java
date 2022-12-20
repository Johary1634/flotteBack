package com.avion.app.model;

import java.sql.Date;

public class Avion {
	private int id;
	private String marque;
	private String matricule;
	private int kilometre;
	private Date debutAssurance;
	private Date finAssurance;
	
	public Avion(int id, String marque, String matricule) {
		setId(id);
		setMarque(marque);
		setMatricule(matricule);
	}
	
	public Avion() {
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMarque() {
		return marque;
	}
	public void setMarque(String marque) {
		this.marque = marque;
	}
	public String getMatricule() {
		return matricule;
	}
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public int getKilometre() {
		return kilometre;
	}

	public void setKilometre(int kilometre) {
		this.kilometre = kilometre;
	}

	public Date getDebutAssurance() {
		return debutAssurance;
	}

	public void setDebutAssurance(Date debutAssurance) {
		this.debutAssurance = debutAssurance;
	}

	public Date getFinAssurance() {
		return finAssurance;
	}

	public void setFinAssurance(Date finAssurance) {
		this.finAssurance = finAssurance;
	}
}
