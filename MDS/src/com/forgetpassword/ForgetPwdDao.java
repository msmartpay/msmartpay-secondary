package com.forgetpassword;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.db.DBConnection;
//import com.smsgateway.SmsGateway;
//import com.utility.SendSmtpMail;
//import com.Transfer.SendSmtpMail;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class ForgetPwdDao {




	public static HashMap getDynamicDetails(String clientId){

		Statement stmt=null;
		ResultSet rs=null;
		Connection con=null;
		String status="";
		HashMap hm=new HashMap();


		try
		{
			con=DBConnection.getConnection();
			stmt = con.createStatement();
			String selectQuery="select md_login_url,Agent_cell_email_id,domain_name,company_name,Help_email_id1 from white_label_details where client_id="+clientId+"";

			//String selectQuery="select md_header_home_image,md_page_title,md_footer_copyright,Help_mobileNo1,user_type from white_label_details where client_id="+clientId+"";
			System.out.println(selectQuery);
			rs=stmt.executeQuery(selectQuery);

			while(rs.next()){

				hm.put("md_login_url",rs.getString(1));
				hm.put("Agent_cell_email_id",rs.getString(2));
				hm.put("domain_name",rs.getString(3));
				hm.put("company_name",rs.getString(4));
				hm.put("Help_email_id1",rs.getString(5));

			}


		}catch(Exception E)
		{
			status="notupdate";
			System.out.println("Exception in getDynamicDetails======"+E.toString());
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
				System.out.println("Exception in getDynamicDetails while closing connection"+e.toString());
			}

		}




		return hm;
	}


	public static HashMap chkEmail( String emailId,String clientID,HttpSession session) {


		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		ResultSet rs1=null;
		String userId="";
		String completeId="";
		String userName="";
		String pwd="invalid";
		String status="";
		HashMap hm=new HashMap();
		int count=0;


		try
		{   con=DBConnection.getConnection();
		stmt=con.createStatement();
		String selectQuery1="select b.user_name as name,a.md_id,(a.md_initial+convert(varchar,a.md_id)) as complete_id from md_details a,md_login_details b where a.md_id=b.user_id and b.user_name='"+emailId+"'";
		System.out.println("selectQuery1 is"+selectQuery1);
		rs=stmt.executeQuery(selectQuery1);
		while(rs.next())
		{
			status="valid";
			session.setAttribute("status",status);
			rs.getString("name");
			userId=rs.getString("md_id");
			hm.put("userId",userId);
			completeId=rs.getString("complete_id");
			hm.put("completeId",completeId);
		}

		if(status.equals("valid"))
		{
			String selectQuery2="select user_name,password from md_login_details where user_id='"+userId+"' group by user_name,password" ;
			System.out.println("selectQuery2 is"+selectQuery2);
			rs1=stmt.executeQuery(selectQuery2);
			while(rs1.next())
			{

				hm.put("userName",rs1.getString("user_name"));
				hm.put("pwd",rs1.getString("password"));

			}
		}
		else{ 
			status="invalid";
		}
		session.setAttribute("status",status);

		}catch(Exception e)
		{
			session.setAttribute("status","invalid");
			System.out.println("Exception in ForgetPwdDao, method chkEmail "+e.toString());
		} 
		finally
		{
			try
			{
				if(rs !=null)
					rs.close();
				if(rs1 !=null)
					rs1.close();
				if(stmt != null)
					stmt.close();
				if(con!= null)
					con.close();
			}
			catch ( Exception e)
			{
				System.out.println("Exception in ForgetPwdDao, method chkEmail "+e.toString());
			}
		}
		return hm;

	}




}
