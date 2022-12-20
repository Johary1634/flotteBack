package com.avion.app.model;

import java.sql.Date;

public class Assurance {
	private Avion vehicule;
	private Date debut;
	private Date fin;
	
	public Assurance() {
		
	}
	
	public Assurance(Date debut, Date fin) {
		this.debut = debut;
		this.fin = fin;
	}

	public Avion getVehicule() {
		return vehicule;
	}
	public void setVehicule(Avion vehicule) {
		this.vehicule = vehicule;
	}
	public Date getDebut() {
		return debut;
	}
	public void setDebut(Date debut) {
		this.debut = debut;
	}
	public Date getFin() {
		return fin;
	}
	public void setFin(Date fin) {
		this.fin = fin;
	}
	
	
}
