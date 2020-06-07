package nl.hu.v1wac.firstapp.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import nl.hu.v1wac.firstapp.model.User;
import nl.hu.v1wac.firstapp.persistence.PostgresBaseDao;
import nl.hu.v1wac.firstapp.persistence.interfaces.UserDao;

public class UserPostgresDaoImpl extends PostgresBaseDao implements UserDao{
	

	   		public boolean wachtwoordCheck(String password, String username) {
	        User gebruiker = new User();
	        boolean bool = false;
	        try {
	            Connection myConn = getConnection();
	            Statement myStmt = myConn.createStatement();
	            
	            String strQuery = "SELECT * FROM useraccount WHERE password  = '" + password + "' AND username = '" + username + "'";
	            
	            ResultSet rs = myStmt.executeQuery(strQuery);
	            		
	            while (rs.next()) {
	            	gebruiker.setPassword(rs.getString("password"));
	            	System.out.println(gebruiker.getPassword());
	            }
	     
	            if (password.equals(gebruiker.getPassword())) {
	            	bool = true;
	            }
	    
	           System.out.println(gebruiker);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
			
			return bool;
		}
	   		
	   		
   		public String findRoleForUser(String username, String password) {
   			String result = null;
   			try (Connection con = super.getConnection()) {
   				PreparedStatement pstmt = con.prepareStatement("SELECT * FROM useraccount WHERE password  = '" + password + "' AND username = '" + username + "'");
   				ResultSet dbResultSet = pstmt.executeQuery();
   				
   				while (dbResultSet.next()) {
   					result = dbResultSet.getString("role");
   				}
   				System.out.println(result);
   			} catch (SQLException sqle) { sqle.printStackTrace(); }
   			
   			return result;
   		}
   

	   
	
	 

}
