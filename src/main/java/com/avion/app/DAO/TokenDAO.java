package com.avion.app.DAO;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import com.avion.app.model.*;

public class TokenDAO {
	
	public static Token insertToken(Admin x) throws SQLException, ClassNotFoundException,NoSuchAlgorithmException{
		Connection connexion = null;
        PreparedStatement stat = null;
        String hash = x.getEmail()+x.getPassword()+LocalDateTime.now();
        Token ans = null;
        
        try {
        	hash = Token.encrypt(hash);
        	LocalDateTime expiration = LocalDateTime.now().plusMinutes(30);
        	Timestamp exp = Timestamp.valueOf(expiration);
            connexion = Connexion.getConnect();
            String requete = "INSERT INTO Token (idAdmin, value,dateexpiration) VALUES (?, ?, ?)";
            stat = connexion.prepareStatement(requete);
            stat.setInt(1, x.getId());
            stat.setString(2, hash);
            stat.setTimestamp(3, exp);
            
            stat.execute();
            ans = new Token();
            ans.setAdmin(x);
            ans.setValue(hash);
            ans.setDateExpiration(exp);
        } catch (SQLException | NoSuchAlgorithmException | ClassNotFoundException e) {
            throw e;
        } finally {
            if (stat != null) stat.close();
            if (connexion != null) connexion.close();
        }
        return ans;
	}
	
	
	public static Token getToken(String token)throws SQLException, ClassNotFoundException,NoSuchAlgorithmException{
		Connection co=null;
        PreparedStatement prst=null;
        ResultSet result=null;
        Token ans = null;
        try {
            co=Connexion.getConnect();
            String sql="Select * from Token WHERE value = '"+token+"'";
            prst=co.prepareStatement(sql);
            result=prst.executeQuery();
            while(result.next()){
            	ans = new Token();
            	Admin x = AdminDAO.getById(result.getInt("idAdmin"));
            	ans.setAdmin(x);          	
            	ans.setValue(result.getString("value"));
            	ans.setDateExpiration(result.getTimestamp("dateexpiration"));
            }
        }
        catch (SQLException | ClassNotFoundException e) {
			// TODO: handle exception
        	throw e;
		}     
        return ans;
	}
	
}














