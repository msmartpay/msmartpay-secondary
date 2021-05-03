package com.msmart.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;

import com.msmart.service.PropertyFile;

public class DBConnection {

	public synchronized   static Connection getConnection() {     

		Connection con=null;	
		try{
			con=getConnectionWithoutPool();
		}catch(Exception ex){
			ex.printStackTrace();		 
		}
		return con;
	}

	public static Connection getConnectionWithoutPool() {
		
		Connection con = null;
		String username = "";
		String password = "";
		String url = "";
		String driver = "";
		try{          

			driver="com.microsoft.sqlserver.jdbc.SQLServerDriver"; 
			url="jdbc:sqlserver://"+PropertyFile.DB_IP+";databasename="+PropertyFile.DB_NAME; 
			username = PropertyFile.DB_USERNAME;
			password = PropertyFile.DB_PASSWORD; 
			Class.forName(driver);

			con= DriverManager.getConnection(url,username,password);

		}catch(Exception ex){
			ex.printStackTrace();	
			System.out.println("problem in DBConnection "+ex);	 
		}
		return con;
	}

}
