package com.distributor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import com.db.*;
//import com.mobilesms.SmsToMobileDao;



public class DistributorDetailsDao {

	@SuppressWarnings("null")
	public static String deactivateDistributorStatus(String distributorId,String mdId){
		Statement stmt=null;
		ResultSet rs=null;
		Connection con=null;
		String status="";
		try
		{
			con=DBConnection.getConnection();
			stmt = con.createStatement();

			String sql="update distributor_login_details set login_status='Deactive' where user_id in (select distributor_id from distributor_details where (distributor_initial+convert(varchar,distributor_id))='"+distributorId+"')";
			System.out.println(sql);
			int a=stmt.executeUpdate(sql);
			if(a>0){
				status="update";            	
				String testMessage="Dear Area Facility Center, Your AFC ID "+distributorId+" has been deactivated.";
				//String smsStatus=(String)SmsToMobileDao.sendMobileSmsZone(AuthorizedMobileNo,testMessage);
			}
			else{
				status="notupdate";
			}	     

		}catch(Exception E)
		{
			status="notupdate";
			System.out.println("Exception in deactivateDistributorStatus======"+E.toString());
		}
		finally
		{
			try
			{
				if(rs!=null)
					rs.close();
				if(stmt!=null)
					stmt.close();
				if(con!=null)
					con.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in deactivateDistributorStatus while closing connection"+e.toString());
			}			
		}
		return status;	
	}

	@SuppressWarnings("null")
	public static String activateDistributorStatus(String distributorId,String mdId){
		Statement stmt=null;
		ResultSet rs=null;
		Connection con=null;
		String status="";
		try
		{		
			con=DBConnection.getConnection();
			stmt=con.createStatement();
			String sql="update distributor_login_details set login_status='Activate' where user_id in (select distributor_id from distributor_details where (distributor_initial+convert(varchar,distributor_id))='"+distributorId+"')";
			//System.out.println(sql);
			int a=stmt.executeUpdate(sql);
			if(a>0){
				status="update";			
				//	String testMessage="Dear Distributor, Your Distributor ID "+distributorId+" has been activated.";
				//	String smsStatus=(String)SmsToMobileDao.sendMobileSmsZone(AuthorizedMobileNo,testMessage);
			}
			else{
				status="notupdate";
			}

		}catch(Exception E)
		{
			status="notupdate";
			System.out.println("Exception in activateDistributorStatus======"+E.toString());
		}
		finally
		{
			try
			{
				if(rs!=null)
					rs.close();
				if(stmt!=null)
					stmt.close();
				if(con!=null)
					con.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in activateDistributorStatus while closing connection"+e.toString());
			}		
		}
		return status;	
	}

	public static String DoAllActivateDistributor(String mdId){
		Statement stmt=null;
		ResultSet rs=null;
		Connection con=null;
		String status="";
		try
		{		
			con=DBConnection.getConnection();
			stmt = con.createStatement();			
			String sql="update distributor_login_details set login_status='Activate' where user_id in (select distributor_id from distributor_details where md_id='"+mdId+"')";
			System.out.println(sql);
			int a=stmt.executeUpdate(sql);
			if(a>0){
				status="update";			 
				//	String testMessage="Dear Distributor, Your Distributor ID "+distributorId+" has been activated.";
				//	String smsStatus=(String)SmsToMobileDao.sendMobileSmsZone(AuthorizedMobileNo,testMessage);
			}
			else{
				status="notupdate";
			}			
		}catch(Exception E)
		{
			status="notupdate";
			System.out.println("Exception in activateDistributorStatus======"+E.toString());
		}
		finally
		{
			try
			{
				if(rs!=null)
					rs.close();
				if(stmt!=null)
					stmt.close();
				if(con!=null)
					con.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in activateDistributorStatus while closing connection"+e.toString());
			}		
		}
		return status;	
	}

	public static String DoAllDeactivateDistributor(String mdId){
		Statement stmt=null;	
		ResultSet rs=null;
		Connection con=null;
		String status="";	
		try
		{
			con=DBConnection.getConnection();		
			stmt = con.createStatement();     
			String sql="update distributor_login_details set login_status='Deactive' where user_id in (select distributor_id from distributor_details where md_id='"+mdId+"')";
			System.out.println(sql);
			int a=stmt.executeUpdate(sql);
			if(a>0){
				status="update";
				//	String testMessage="Dear Distributor, Your Distributor ID "+distributorId+" has been deactivated.";
				//String smsStatus=(String)SmsToMobileDao.sendMobileSmsZone(AuthorizedMobileNo,testMessage);
			}
			else{
				status="notupdate";
			}	       
		}catch(Exception E)
		{
			status="notupdate";
			System.out.println("Exception in deactivateDistributorStatus======"+E.toString());
		}
		finally
		{
			try
			{
				if(rs!=null)
					rs.close();
				if(stmt!=null)
					stmt.close();
				if(con!=null)
					con.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in deactivateDistributorStatus while closing connection"+e.toString());
			}			
		}
		return status;	
	}

	public ArrayList<HashMap<String, String>> getAllDistributor(String mdId) {
		ArrayList<HashMap<String, String>> getDistributor=new ArrayList<HashMap<String, String>>();
		Statement stmt=null;
		ResultSet rs=null;
		Connection con=null;
		try
		{
			System.out.println("test 1");
			con=DBConnection.getConnection();

			stmt=con.createStatement();
			String sql="";			
			sql="select (convert(varchar(10),a.distributor_initial)+convert(varchar(10),a.distributor_id)) as compdid,(c.totcash-c.usedcash+c.cutoff_amount) as balance,a.distributor_id as did,a.distributor_initial as ini,a.company_name as cname, a.mobile_no as mobno, a.email_id as eid ,b.login_status as status,a.distributor_name as contact_person from distributor_details a ,distributor_login_details b,distributor_amount c where  c.distributor_id=a.distributor_id and a.distributor_id =b.user_id and a.md_id="+mdId+" order by a.date_of_joining desc";						

			System.out.println(sql);			
			rs=stmt.executeQuery(sql); 
			while(rs.next())
			{               
				String initial=rs.getString("ini");
				String id=rs.getString("did");
				String distributor_id=initial+id;				
				//System.out.println("hello we are inside while loop");
				HashMap<String, String> information=new HashMap<String, String>();
				information.put("balance",rs.getString("balance"));
				information.put("distributor_id",distributor_id);
				information.put("company_name",rs.getString("cname"));
				information.put("mobile",rs.getString("mobno"));
				information.put("email_id",rs.getString("eid"));
				information.put("login_status",rs.getString("status"));
				information.put("contact_person",rs.getString("contact_person"));				
				getDistributor.add(information);
			}

		}catch(Exception e)
		{
			System.out.println("Hello Add Member"+e.toString());
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(rs!=null)
					rs.close();
				if(stmt!=null)
					stmt.close();
				if(con!=null)
					con.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in Login OF ADMIN"+e.toString());
			}

		}
		return getDistributor;
	}

	public ArrayList<HashMap<String, String>> getAllDistributorActive(String mdId) {
		ArrayList<HashMap<String, String>> getDistributor=new ArrayList<HashMap<String, String>>();
		Statement stmt=null;
		ResultSet rs=null;
		Connection con=null;	
		try
		{			
			System.out.println("test 1");
			con=DBConnection.getConnection();

			stmt=con.createStatement();
			String sql="";			
			sql="select (convert(varchar(10),a.distributor_initial)+convert(varchar(10),a.distributor_id)) as compdid,(c.totcash-c.usedcash+c.cutoff_amount) as balance,a.distributor_id as did,a.distributor_initial as ini,a.company_name as cname, a.mobile_no as mobno, a.email_id as eid ,b.login_status as status,a.distributor_name as contact_person from distributor_details a ,distributor_login_details b,distributor_amount c where  c.distributor_id=a.distributor_id and a.distributor_id =b.user_id and a.md_id="+mdId+" and b.login_status='Activate' order by a.date_of_joining desc";

			//System.out.println(sql);			
			rs=stmt.executeQuery(sql); 
			while(rs.next())
			{               
				String initial=rs.getString("ini");
				String id=rs.getString("did");
				String distributor_id=initial+id;				
				HashMap<String, String> information=new HashMap<String, String>();
				information.put("balance",rs.getString("balance"));
				information.put("distributor_id",distributor_id);
				information.put("company_name",rs.getString("cname"));
				information.put("mobile",rs.getString("mobno"));
				information.put("email_id",rs.getString("eid"));
				information.put("login_status",rs.getString("status"));
				information.put("contact_person",rs.getString("contact_person"));				
				getDistributor.add(information);
			}

		}catch(Exception e)
		{
			System.out.println("Hello Add Member"+e.toString());
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(rs!=null)
					rs.close();
				if(stmt!=null)
					stmt.close();
				if(con!=null)
					con.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in Login OF ADMIN"+e.toString());
			}			
		}
		return getDistributor;
	}
	public ArrayList<HashMap<String, String>> getAllDistributorDeactive(String mdId) {
		ArrayList<HashMap<String, String>> getDistributor=new ArrayList<HashMap<String, String>>();
		Statement stmt=null;	
		ResultSet rs=null;	
		Connection con=null;
		try
		{
			System.out.println("test 1");
			con=DBConnection.getConnection();

			stmt=con.createStatement();
			String sql="";					
			sql="select (convert(varchar(10),a.distributor_initial)+convert(varchar(10),a.distributor_id)) as compdid,(c.totcash-c.usedcash+c.cutoff_amount) as balance,a.distributor_id as did,a.distributor_initial as ini,a.company_name as cname, a.mobile_no as mobno, a.email_id as eid ,b.login_status as status,a.distributor_name as contact_person from distributor_details a ,distributor_login_details b,distributor_amount c where  c.distributor_id=a.distributor_id and a.distributor_id =b.user_id and a.md_id="+mdId+" and b.login_status in ('Deactive','Deactivate') order by a.date_of_joining desc";

			//System.out.println(sql);			
			rs=stmt.executeQuery(sql); 
			while(rs.next())
			{		
				String initial=rs.getString("ini");
				String id=rs.getString("did");
				String distributor_id=initial+id;
				HashMap<String, String> information=new HashMap<String, String>();
				information.put("balance",rs.getString("balance"));
				information.put("distributor_id",distributor_id);
				information.put("company_name",rs.getString("cname"));
				information.put("mobile",rs.getString("mobno"));
				information.put("email_id",rs.getString("eid"));
				information.put("login_status",rs.getString("status"));
				information.put("contact_person",rs.getString("contact_person"));			
				getDistributor.add(information);
			}	
		}catch(Exception e)
		{
			System.out.println("Hello Add Member"+e.toString());
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(rs!=null)
					rs.close();
				if(stmt!=null)
					stmt.close();
				if(con!=null)
					con.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in Login OF ADMIN"+e.toString());
			}

		}
		return getDistributor;
	}
}
