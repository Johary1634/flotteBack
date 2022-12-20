package com.avion.app.myException;

public class NotFoundException extends Exception {
	public NotFoundException() {
		super("Element introuvable");
	}
}
