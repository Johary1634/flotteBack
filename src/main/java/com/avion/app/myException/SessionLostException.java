package com.avion.app.myException;

public class SessionLostException extends Exception{
	public SessionLostException() {
		super("Session lost");
	}
}
