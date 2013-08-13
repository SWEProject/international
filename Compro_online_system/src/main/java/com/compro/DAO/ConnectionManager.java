package com.compro.DAO;

import java.sql.Connection;
import java.sql.DriverManager;

 

class ConnectionManager {

	/**
	 * @param args
         * 
	 */
    
    public static void main(String[] argv)
    {
        dcConnect();
    }

	
   
		static Connection dcConnect() {

	        Connection con = null;
	        try {
                   
	            Class.forName("com.mysql.jdbc.Driver");
	            String connectionUrl = "jdbc:mysql://localhost:3306/test";
	            con = DriverManager.getConnection(connectionUrl,"root","miromiro");

	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	            System.out.println("Not able to connect to database, look for connection status");
	            System.exit(0);
	        }

	        return con;
	    }
		

	

}
