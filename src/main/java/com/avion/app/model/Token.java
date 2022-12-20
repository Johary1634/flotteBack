package com.avion.app.model;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;

public class Token {
	private Admin admin;
	private String value;
	private Timestamp dateExpiration;

	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Timestamp getDateExpiration() {
		return dateExpiration;
	}
	public void setDateExpiration(Timestamp dateExpiration) {
		this.dateExpiration = dateExpiration;
	}
	
	public static String encrypt(String valeur) throws NoSuchAlgorithmException{
		String hash = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			byte[] messageD  = md.digest(valeur.getBytes());
			BigInteger num   = new BigInteger(1, messageD);
			hash      = num.toString(16);
			while(hash.length()<32) {
				hash = "0" + hash;
			}
		} catch (NoSuchAlgorithmException e) {
			throw e;
			// TODO: handle exception
		}
		return hash;
	}
	
}








