package com.db;

import java.sql.*;

public final class DBConnection {
	
	private DBConnection(){}

	public synchronized   static Connection getConnection() throws Exception {     

		Connection con=null;	
		try{
			con=getConnectionWithoutPool();
		}catch(Exception ex){
			ex.printStackTrace();		 
		}
		return con;
	}

	public static Connection getConnectionWithoutPool() throws Exception {

		Connection con = null;
		String username = "",password = "",url = "",driver = "";

		try{          

			driver="com.microsoft.sqlserver.jdbc.SQLServerDriver"; 
			
			//url="jdbc:sqlserver://103.25.128.64:2499;databasename=b2b_msmart;sendStringParametersAsUnicode=false";
			//url="jdbc:sqlserver://10.10.33.238:1433;databasename=b2b_msmart;sendStringParametersAsUnicode=false"; 
			//username="msmart" ;
			//password="!@123lancer" ;
			
			url="jdbc:sqlserver://13.235.120.132:1433;databasename=b2b_msmart;sendStringParametersAsUnicode=false";
			username="arpit" ;
			password="!@123lancer" ;
			
			Class.forName(driver);

			con= DriverManager.getConnection(url,username,password);

		}catch(Exception ex){
			ex.printStackTrace();	
		}
		return con;
	}
}