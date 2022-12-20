package com.avion.app.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
	public static Connection getConnect() throws ClassNotFoundException, SQLException{
		
		
		String user = "ubqpjepx";
        String mdp = "jAL2a8_nLUyoPdSHwjK_9QLlxQNJmRl3";
        Connection connect;
        Class.forName("org.postgresql.Driver");
        connect = DriverManager.getConnection("jdbc:postgresql://fanny.db.elephantsql.com/ubqpjepx",user,mdp);
		
        /*String user = "cloud";
        String mdp = "secret";
        Connection connect;
        Class.forName("org.postgresql.Driver");
        connect = DriverManager.getConnection("jdbc:postgresql://localhost:5432/avion",user,mdp);*/
        return connect;
    } 
}
