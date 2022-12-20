package com.avion.app.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.avion.app.model.Admin;

public class AdminDAO {

	public static Admin toLog(Admin x) throws SQLException,ClassNotFoundException {
		Connection co=null;
        PreparedStatement prst=null;
        ResultSet result=null;
        Admin liste= null;
        
        try{
            co=Connexion.getConnect();
            String sql="Select * from Admin WHERE email = ? AND password = ?";
            prst=co.prepareStatement(sql);
            prst.setString(1, x.getEmail());
            prst.setString(2, x.getPassword());
            result=prst.executeQuery();
            while(result.next()){
            	liste = new Admin();
            	liste.setId(result.getInt("id"));
            	liste.setEmail(result.getString("email"));
            	liste.setPassword(result.getString("password"));
            }
        }
        catch(SQLException | ClassNotFoundException e){
            throw e;
        }finally {
        	if(result!=null)result.close();
        	if(prst!=null)prst.close();
        	if(co!=null)co.close();
        }
        return liste;
	}
	
	public static Admin getById(int id) throws SQLException,ClassNotFoundException {
		Connection co=null;
		Admin liste = null;
        
        try{
            co=Connexion.getConnect();
            liste = getById(co, id);
        }
        catch(SQLException | ClassNotFoundException e){
            throw e;
        }finally {
        	if(co!=null)co.close();
        }
        return liste;
	}
	
	public static Admin getById(Connection co, int id) throws SQLException {
		PreparedStatement prst=null;
        ResultSet result=null;
        Admin liste= null;
		
        try {
        	String sql="Select * from Admin WHERE id = ?";
            prst=co.prepareStatement(sql);
            prst.setInt(1, id);
          
            result=prst.executeQuery();
            while(result.next()){
            	liste = new Admin();
            	liste.setId(result.getInt("id"));
            	liste.setEmail(result.getString("email"));
            	liste.setPassword(result.getString("password"));
            }
		} catch (SQLException e) {
			// TODO: handle exception
		}finally {
        	if(result!=null)result.close();
        	if(prst!=null)prst.close();
        	if(co!=null)co.close();
        }
        return liste;
	}
}











