package com.pushbalance;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.Vector;

import java.sql.*;

import com.db.DBConnection;
import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;


public class TransferAmountToDistributorDao {

	public static HashMap<String, String> getDistributorDetails(String distId,String mdId){


		Statement stmt=null;	
		ResultSet rs=null;
		String status="invalid";
		HashMap<String, String> map=new HashMap<String, String>();
		Connection con=null;
		try
		{
			con=DBConnection.getConnection();
			String query="";
			stmt=con.createStatement();
			query="Select distributor_id,distributor_name from distributor_details where (distributor_initial+convert(varchar,distributor_id))='"+distId+"' and md_id="+mdId+"";
			System.out.println("query:===================="+query);
			rs=stmt.executeQuery(query);
			while(rs.next())
			{
				map.put("distId",rs.getString(1));
				map.put("name",rs.getString(2));			  
				status="valid";	
			}

		}
		catch(Exception e)
		{
			status="invalid";
			System.out.println("Exception in TransferAmountToDistributorDao,method checkDistIdStatus "+e.toString());
			e.printStackTrace();
		}
		finally
		{
			try{
				if(rs!=null)
					rs.close();
				if(stmt!=null)
					stmt.close();				
				if(con!=null)
					con.close();

			}
			catch(Exception e)
			{
				System.out.println("Exception in TransferAmountToDistributorDao,method checkDistIdStatus while closing connections"+e.toString());
			}
		}
		return map;

	}


	public static String  checkDistIdStatus(Connection con,String mdId,String dist_id,HttpSession session){

		Statement stmt=null;	

		ResultSet rs=null;

		String distributorId="";
		String status="invalid";
		try
		{
			con=DBConnection.getConnection();
			String query="";
			stmt=con.createStatement();

			query="Select distributor_id from distributor_details where (distributor_initial+convert(varchar,distributor_id))='"+dist_id+"' and md_id="+mdId+"";
			System.out.println("query:===================="+query);
			rs=stmt.executeQuery(query);
			while(rs.next())
			{
				distributorId= rs.getString(1);
				session.setAttribute("distributorId",distributorId);
				status="valid";	
			}

		}
		catch(Exception e)
		{
			status="invalid";
			System.out.println("Exception in TransferAmountToDistributorDao,method checkDistIdStatus "+e.toString());
			e.printStackTrace();
		}
		finally
		{
			try{
				if(rs!=null)
					rs.close();
				if(stmt!=null)
					stmt.close();				
				if(con!=null)
					con.close();

			}
			catch(Exception e)
			{
				System.out.println("Exception in TransferAmountToDistributorDao,method checkDistIdStatus while closing connections"+e.toString());
			}
		}
		return status;  
	}

	public static String transferAmount(Connection con,String mdId,String distributor_id,double amount,String remark,String mode,String ip_address,HttpSession session)
	{


		System.out.println("mdId  is===================="+mdId);
		System.out.println("distributor_id  is===================="+distributor_id);

		CallableStatement cstmt=null;
		Statement stmt=null;
		String status="";
		ResultSet rs=null;
		try{
			con=DBConnection.getConnection();
			String  TransactionId="";
			String prefix="";

			Date now = new Date();
			long logntranId = now.getTime();

			int length = distributor_id.length();


			if(length<=4)
			{
				prefix="100"+distributor_id;
			}
			if(length>=6)
			{
				prefix="1"+distributor_id;
			}
			String suffix = new String();

			suffix=String.valueOf(logntranId);

			TransactionId=prefix+suffix;
			System.out.println("transaction id is=============="+TransactionId);

			cstmt=con.prepareCall("{call MD_balance_transfer_from_master_distributor_to_distributor(?,?,?,?,?,?,?,?)}");
			int d_id=Integer.parseInt(distributor_id);
			int m_id=Integer.parseInt(mdId);
			cstmt.setInt(1,m_id);
			cstmt.setInt(2,d_id);
			cstmt.setDouble(3,amount);
			cstmt.setString(4,mode);
			cstmt.setString(5,remark);
			cstmt.setString(6,ip_address);
			cstmt.setString(7,TransactionId);
			cstmt.registerOutParameter(8, java.sql.Types.LONGVARCHAR);
			cstmt.execute();
			status= cstmt.getString(8);

			String balanceQuery="select mobile_no,(TotCash-usedcash+cutoff_amount)as balance from distributor_amount a,distributor_details d where a.distributor_id='"+distributor_id+"' and a.distributor_id=d.distributor_id";
			String discurrentBalAmount="0";
			String distributorMob="";
			stmt=con.createStatement();
			rs=stmt.executeQuery(balanceQuery);

			while(rs.next()){
				distributorMob=(String)rs.getString(1);
				discurrentBalAmount=(String)rs.getString(2);
			}
			session.setAttribute("distributorMob",distributorMob);
			session.setAttribute("discurrentBalAmount",discurrentBalAmount);


		}


		catch(Exception e){
			System.out.println("exception in transferAmount"+e.toString());
		}

		finally
		{
			try{
				if(rs!=null)
					rs.close();
				if(stmt!=null)
					stmt.close();	
				if(cstmt!=null)
					cstmt.close();				
				if(con!=null)
					con.close();

			}
			catch(Exception e)
			{
				System.out.println("Exception in TransferAmountToAgentDao,method getAmountStatus while closing connections"+e.toString());
			}
		}


		return status;
	}
	public static String getAmountStatus(Connection con, double amount,String mdId) 
	{
		Statement stmt=null;	

		ResultSet rs=null;

		Double bal=0.00;
		String status="valid";
		try
		{
			con=DBConnection.getConnection();
			String query="";


			stmt=con.createStatement();

			query="Select Available_Bal_Amount as balance from md_amount where md_id="+mdId+"";
			System.out.println("query:===================="+query);
			rs=stmt.executeQuery(query);
			while(rs.next())
			{
				bal=rs.getDouble("balance");	
				System.out.println(" MD bal ::"+bal);
			}
			if(amount>bal){
				status="invalid";
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception in TransferAmountToDistributorDao,method getAmountStatus "+e.toString());
			e.printStackTrace();
		}
		finally
		{
			try{
				if(rs!=null)
					rs.close();
				if(stmt!=null)
					stmt.close();				
				if(con!=null)
					con.close();

			}
			catch(Exception e)
			{
				System.out.println("Exception in TransferAmountToAgentDao,method getAmountStatus while closing connections"+e.toString());
			}
		}
		return status;
	}
}