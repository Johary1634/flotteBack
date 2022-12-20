package com.avion.app.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import com.avion.app.model.Assurance;
import com.avion.app.model.Avion;

public class AssuranceDAO {
	
	public static Assurance getAssurancByIdVehicule(Integer idVehicule) throws SQLException,ClassNotFoundException
    {
        Assurance assurance = null;
        Connection c = null;
        

        try{
        	c = Connexion.getConnect();
            assurance = getAssurancByIdVehicule(idVehicule, c);
        }catch (SQLException e)
        {
            //TODO: handle exception
            throw e;
        }
        finally
        {
            if(c!=null)
            {
                c.close();
            }
        }
        return assurance;
    }
	public static Assurance getAssurancByIdVehicule(Integer idVehicule,Connection c) throws SQLException,ClassNotFoundException{
		Assurance assurance = null;
		Statement stmt = null;
        ResultSet res = null;
        
        try{
            String sql = "select idAvion,max(debut) as debut,max(fin) as fin from Assurance where idAvion="+idVehicule+" group by idAvion limit 1";
            stmt = c.createStatement();
            res = stmt.executeQuery(sql);

            while(res.next())
            {
            	Avion v = new Avion();
            	v.setId(res.getInt("idAvion"));
                assurance = new Assurance(res.getDate("debut"),res.getDate("fin"));
                assurance.setVehicule(v);
            }
        }catch (SQLException e)
        {
            //TODO: handle exception
            throw e;
        }
        finally
        {
            if(res!=null)
            {
                res.close();
            }
            if(stmt!=null)
            {
                stmt.close();
            }
        }
        return assurance;
	}
	
	public static Assurance[] getAssuranceExpire(Integer mois) throws SQLException,ClassNotFoundException{
        Assurance[] ans = null;
        Connection c = null;
        PreparedStatement stmt = null;
        ResultSet res = null;

        try
        {
           ans = getAssuranceExpire(mois, c);
        }
        catch (SQLException e)
        {
            //TODO: handle exception
            throw e;
        }
        finally {
            if (res != null) {
                res.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (c != null) {
                c.close();
            }
        }
        return ans;
    }

	public static Assurance[] getAssuranceExpire(Integer mois, Connection c) throws SQLException, ClassNotFoundException {
		Assurance[] ans = null;
		Vector<Assurance> liste = new Vector<>();
		PreparedStatement stmt = null;
		ResultSet res = null;

		 try
	        {
	            String sql = "select *,(SELECT getDateFinal(now()::date,?)) as dt from assurance where date_part('month',fin)=date_part('month',(select(getDateFinal(now()::date,?)))) AND date_part('year',fin)=date_part('year',(select(getDateFinal(now()::date,?)))) ";
	            c = Connexion.getConnect();
	            stmt = c.prepareStatement(sql);
	            stmt.setInt(1,mois);
	            stmt.setInt(2,mois);
	            stmt.setInt(3,mois);
	            res = stmt.executeQuery();
	            //System.out.println("tafiditra");
	            while(res.next()) {
	            	Assurance assurance = null;
	            	Avion v = AvionDAO.getById(c, res.getInt("idAvion"));
	                assurance = new Assurance(res.getDate("debut"), res.getDate("fin"));
	                assurance.setVehicule(v);
	                liste.add(assurance);
	            }
	            ans = new Assurance[liste.size()];
	        }
	        catch (SQLException e)
	        {
	            //TODO: handle exception
	            throw e;
	        }
	        finally {
	            if (res != null) {
	                res.close();
	            }
	            if (stmt != null) {
	                stmt.close();
	            }        
	        }
	        return liste.toArray(ans);
	}
}

























