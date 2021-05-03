
package com.db;
import java.sql.*;


public class DBConnection {


	private DBConnection(){

	}

	public synchronized  static Connection getConnection() throws Exception {     

		Connection con=null;	
		try{
			con=getConnectionWithoutPool();
		}catch(Exception ex){
			ex.printStackTrace();		 
			throw(ex);
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

			driver="com.microsoft.sqlserver.jdbc.SQLServerDriver"; 
			username = "arpit"; 
			password = "!@123lancer";
			url="jdbc:sqlserver://13.235.120.132:1433;databasename=b2b_msmart";
			Class.forName(driver);

			con= DriverManager.getConnection(url,username,password);

		}catch(Exception ex){
			ex.printStackTrace();	
			System.out.println("problem in DBConnection "+ex);	 
			throw(ex);
		}
		return con;
	}

	/**close Connection*/
	public synchronized static void closeCon(Connection con){

		try
		{	
			con.close();			
		}
		catch (Exception Ex)
		{
			System.out.println("Error is closing the connection");
			con=null;
		}


	}
	/**close Result Set */
	public synchronized static void closeRS(ResultSet rs){
		if(rs!=null){
			try{rs.close();}catch(Exception e){}		
		}
	}
	/**close Statement */
	public synchronized static void closeStmt(Statement stmt){
		if(stmt!=null){
			try{stmt.close();}catch(Exception e){}		
		}
	}
}
