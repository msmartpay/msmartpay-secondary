package com.masterdistributor;

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
import com.db.DBConnection;


public class MasterDistributorDao {

	public static String updateMasterDistributorProfile(String mdId,String firmname,String Address1,String Address2,String state,String District,String city,String PinCode){
		Statement stmt=null;
		ResultSet rs=null;
		Connection con=null;
		String status="";
		try
		{
			con=DBConnection.getConnection();
			stmt = con.createStatement();
			String sql="update md_details set Company_name='"+firmname+"',Address1='"+Address1+"',Address2='"+Address2+"',Pin_code='"+PinCode+"',City='"+city+"',office_district='"+District+"',State='"+state+"' where (md_initial+convert(varchar,md_id))='"+mdId+"'";

			System.out.println(sql);
			int a=stmt.executeUpdate(sql);
			if(a>0){
				status="update";
			}
			else{
				status="notupdate";
			}
			System.out.println("value of a is =========="+a);

		}catch(Exception E)
		{
			status="notupdate";
			System.out.println("Exception in updateMasterDistributorProfile======"+E.toString());
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
				System.out.println("Exception in updateMasterDistributorProfile while closing connection"+e.toString());
			}

		}


		return status;

	}




	public static String getCity(String cityCode)
	{
		String cityName="";
		Statement stmt=null;
		ResultSet rs=null;
		Connection con=null;
		try
		{
			con=DBConnection.getConnection();
			stmt = con.createStatement();
			String sql="select city_name from city_details where CITY_CODE="+cityCode+"";
			System.out.println(sql);
			rs=stmt.executeQuery(sql);
			if(rs.next()){
				cityName=rs.getString("city_name");	

			}
			else{
				cityName="notfound";
			}

		}catch(Exception E)
		{
			cityName="notfound";
			System.out.println("Exception in getCity======"+E.toString());
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
				System.out.println("Exception in getCity while closing connection"+e.toString());
			}

		}


		return cityName;
	}


	public static HashMap<String, Comparable> getMasterDistributorInformation(String mdId) 
	{


		HashMap<String, Comparable> information=new HashMap<String, Comparable>();
		Statement stmt=null;
		ResultSet rs=null;
		Connection con=null;
		try
		{
			con=DBConnection.getConnection();
			stmt=con.createStatement();
			String sql="";

			sql="select md.company_name,md.address1,md.address2,md.pin_code,md.city,md.state,md.office_district,md.Mobile_no,md.Email_id,ml.user_name,md.MD_name from md_details md, md_login_details ml where (md_initial+convert(varchar,md_id))='"+mdId+"' and ml.user_id=md.md_id;";
			System.out.println(sql);

			rs=stmt.executeQuery(sql); 
			while(rs.next())
			{
				information.put("company_name",rs.getString("company_name"));
				information.put("address1",rs.getString("address1"));
				information.put("address2",rs.getString("address2"));
				information.put("pin_code",rs.getInt("pin_code"));
				information.put("city",rs.getString("city"));
				information.put("district",rs.getString("office_district"));
				information.put("state",rs.getString("state"));
				information.put("Mobile_no",rs.getString("Mobile_no"));
				information.put("Email_id",rs.getString("Email_id"));
				information.put("user_name",rs.getString("user_name"));
				information.put("MD_name",rs.getString("MD_name"));
			}
		}catch(Exception e)
		{
			System.out.println("Exception in getMasterDistributorInformation"+e.toString());
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
				System.out.println("Exception in getMasterDistributorInformation"+e.toString());
			}

		}
		return information;
	}


}	