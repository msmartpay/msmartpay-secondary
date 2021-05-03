package com.dbConnection;
import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.log4j.Logger;


public class DBConnection {

	static final Logger logger = Logger.getLogger(DBConnection.class);

	private DBConnection(){

	}

	public static Connection getConnection() {  

		//logger.info("Start getConnection()>>>>>>>>>>>>>>>>>>>");

		Connection con=null;	
		try{
			con=getConnectionWithoutPool();
			//logger.info("Success getConnection()<<<<<<<<<<<<<<<<<<<");
		}catch(Exception ex){
			logger.info("Exception getConnection()<<<<<<<<<<<<<<<<<<<");
			ex.printStackTrace();		 
		}

		return con;
	}
	/**
	 *return the database connection
	 */
	public static Connection getConnectionWithoutPool() throws Exception {
		Connection con = null;
		String username = "";
		String password = "";
		String url = "";
		String driver = "";
		try{          

			driver="com.microsoft.sqlserver.jdbc.SQLServerDriver"; //sql server using sqljdbc4.jar driver

			url="jdbc:sqlserver://13.235.120.132:1433;databasename=b2b_msmart";
			username = "arpit";
			password = "!@123lancer";



			Class.forName(driver);
			con= DriverManager.getConnection(url,username,password);

		}catch(Exception ex){
			ex.printStackTrace();	
			System.out.println("problemin DBConnection "+ex);	 
			throw(ex);
		}
		return con;
	}


}
