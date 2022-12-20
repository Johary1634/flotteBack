package com.avion.app.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.avion.app.model.Avion;

public class AvionDAO {
	public static Avion[] selectAll() throws SQLException, ClassNotFoundException{
        Connection co=null;
        PreparedStatement prst=null;
        ResultSet result=null;
        Avion[] liste= null;
        ArrayList<Avion> list= new ArrayList<Avion>();
        try{
            co=Connexion.getConnect();
            String sql="Select * from Avion";
            
            prst=co.prepareStatement(sql);
            result=prst.executeQuery();
            while(result.next()){
            	Avion temp= new Avion();
                temp.setId(result.getInt("id"));
                temp.setMarque(result.getString("marque"));
                temp.setMatricule(result.getString("matricule"));
                temp.setKilometre(result.getInt("kilometre"));
                list.add(temp);
            }
            liste= new Avion[list.size()];
        }
        catch(SQLException | ClassNotFoundException e){
            throw e;
        }finally {
        	if(result!=null)co.close();
        	if(prst!=null)prst.close();
        	if(co!=null)co.close();
        }
        return list.toArray(liste);
    }
	
	public static Avion getById(int id) throws SQLException, ClassNotFoundException {
		Connection co=null;
		Avion liste = null;
        
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
	
	public static Avion getById(Connection co, int id) throws SQLException {
		PreparedStatement prst=null;
        ResultSet result=null;
        Avion liste= null;
        
        try{
            String sql="Select * from Avion WHERE id = ?";
            
            prst=co.prepareStatement(sql);
            prst.setInt(1, id);
            result=prst.executeQuery();
            while(result.next()){
            	liste= new Avion();
            	liste.setId(result.getInt("id"));
            	liste.setMarque(result.getString("marque"));
            	liste.setMatricule(result.getString("matricule"));
            	liste.setKilometre(result.getInt("kilometre"));
            }
          
        }
        catch(SQLException e){
            throw e;
        }finally {
        	if(result!=null)co.close();
        	if(prst!=null)prst.close();
        }
        return liste;
	}
}
