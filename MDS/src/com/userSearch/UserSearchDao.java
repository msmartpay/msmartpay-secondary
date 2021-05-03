package com.userSearch;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

import javax.servlet.http.HttpSession;

import com.db.*;
import com.mobilesms.SmsToMobileDao;
import com.utility.UtilityP;



public class UserSearchDao {


	@SuppressWarnings("unused")
	public ArrayList<HashMap<String, String>> getDSDetails(String mdID,String mobileNo,String emailID, String UserID) 
	{
		ArrayList<HashMap<String, String>> dsDetails=new ArrayList<HashMap<String, String>>();
		HashMap<String, String> information=new HashMap<String, String>();
		Statement stmt=null;
		ResultSet rs=null;
		Connection con=null;
		String sql="";
		try
		{
			con=DBConnection.getConnection();
			stmt=con.createStatement();
			if(mobileNo !=null && !mobileNo.equalsIgnoreCase("")){
				sql="select (convert(varchar(10),a.distributor_initial)+convert(varchar(10),a.distributor_id)) as compdid,(c.totcash-c.usedcash+c.cutoff_amount) as balance,a.company_name as cname, a.mobile_no as mobno, a.email_id as eid ,b.login_status as status,a.distributor_name as contact_person from distributor_details a ,distributor_login_details b,distributor_amount c where  c.distributor_id=a.distributor_id and a.distributor_id =b.user_id and a.md_id="+mdID+" and a.mobile_no='"+mobileNo+"'";
			}
			else if(emailID !=null && !emailID.equalsIgnoreCase("")){
				sql="select (convert(varchar(10),a.distributor_initial)+convert(varchar(10),a.distributor_id)) as compdid,(c.totcash-c.usedcash+c.cutoff_amount) as balance,a.company_name as cname, a.mobile_no as mobno, a.email_id as eid ,b.login_status as status,a.distributor_name as contact_person from distributor_details a ,distributor_login_details b,distributor_amount c where  c.distributor_id=a.distributor_id and a.distributor_id =b.user_id and a.md_id="+mdID+" and a.email_id='"+emailID+"'";
			}
			else if(UserID !=null && !UserID.equalsIgnoreCase("")){
				sql="select (convert(varchar(10),a.distributor_initial)+convert(varchar(10),a.distributor_id)) as compdid,(c.totcash-c.usedcash+c.cutoff_amount) as balance,a.company_name as cname, a.mobile_no as mobno, a.email_id as eid ,b.login_status as status,a.distributor_name as contact_person from distributor_details a ,distributor_login_details b,distributor_amount c where  c.distributor_id=a.distributor_id and a.distributor_id =b.user_id and a.md_id="+mdID+" and a.distributor_initial+convert(varchar(10),a.distributor_id)='"+UserID+"'";
			}
			else if(mobileNo !=null && emailID!=null && !mobileNo.equalsIgnoreCase("") && !emailID.equalsIgnoreCase("")){
				sql="select (convert(varchar(10),a.distributor_initial)+convert(varchar(10),a.distributor_id)) as compdid,(c.totcash-c.usedcash+c.cutoff_amount) as balance,a.company_name as cname, a.mobile_no as mobno, a.email_id as eid ,b.login_status as status,a.distributor_name as contact_person from distributor_details a ,distributor_login_details b,distributor_amount c where  c.distributor_id=a.distributor_id and a.distributor_id =b.user_id and a.md_id="+mdID+" and a.email_id='"+emailID+"' and a.mobile_no='"+mobileNo+"'";
			}else if (UserID!=null && mobileNo!=null && !mobileNo.equalsIgnoreCase("") && !UserID.equalsIgnoreCase("")){
				sql="select (convert(varchar(10),a.distributor_initial)+convert(varchar(10),a.distributor_id)) as compdid,(c.totcash-c.usedcash+c.cutoff_amount) as balance,a.company_name as cname, a.mobile_no as mobno, a.email_id as eid ,b.login_status as status,a.distributor_name as contact_person from distributor_details a ,distributor_login_details b,distributor_amount c where  c.distributor_id=a.distributor_id and a.distributor_id =b.user_id and a.md_id="+mdID+" and a.distributor_initial+convert(varchar(10),a.distributor_id)='"+UserID+"' and a.mobile_no='"+mobileNo+"'";
			}else if(emailID!=null && UserID!=null && !emailID.equalsIgnoreCase("") && !UserID.equalsIgnoreCase("")){
				sql="select (convert(varchar(10),a.distributor_initial)+convert(varchar(10),a.distributor_id)) as compdid,(c.totcash-c.usedcash+c.cutoff_amount) as balance,a.company_name as cname, a.mobile_no as mobno, a.email_id as eid ,b.login_status as status,a.distributor_name as contact_person from distributor_details a ,distributor_login_details b,distributor_amount c where  c.distributor_id=a.distributor_id and a.distributor_id =b.user_id and a.md_id="+mdID+" and a.distributor_initial+convert(varchar(10),a.distributor_id)='"+UserID+"' and a.email_id='"+emailID+"'";
			}
			else if(emailID!=null && UserID!=null && mobileNo!=null && !mobileNo.equalsIgnoreCase("") && !emailID.equalsIgnoreCase("") && !UserID.equalsIgnoreCase("")){
				sql="select (convert(varchar(10),a.distributor_initial)+convert(varchar(10),a.distributor_id)) as compdid,(c.totcash-c.usedcash+c.cutoff_amount) as balance,a.company_name as cname, a.mobile_no as mobno, a.email_id as eid ,b.login_status as status,a.distributor_name as contact_person from distributor_details a ,distributor_login_details b,distributor_amount c where  c.distributor_id=a.distributor_id and a.distributor_id =b.user_id and a.md_id="+mdID+" and a.distributor_initial+convert(varchar(10),a.distributor_id)='"+UserID+"' and a.mobile_no='"+mobileNo+"' and a.email_id='"+emailID+"'";
			}	
			System.out.println(sql);
			rs=stmt.executeQuery(sql);
			while(rs.next())
			{
				information.put("distributor_id",rs.getString("compdid"));
				information.put("balance",rs.getString("balance"));	
				information.put("cname",rs.getString("cname"));	
				information.put("mobile",rs.getString("mobno"));
				information.put("email_id",rs.getString("eid"));
				information.put("login_status",rs.getString("status"));
				information.put("contact_person",rs.getString("contact_person"));

				dsDetails.add(information);

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
				System.out.println("Exception in getDSDetails OF ADMIN"+e.toString());
			}

		}
		return dsDetails;
	}


	@SuppressWarnings({ "unused", "null" })
	public ArrayList getAgentDetails(String mdID,String mobileNo,String emailID, String UserID) 
	{
		ArrayList AGDetails=new ArrayList();
		HashMap information=new HashMap();
		Statement stmt=null;
		ResultSet rs=null;
		ResultSet rs1=null;
		Connection con=null;
		String sql="";
		String	sqlQry="";
		String flag="0";
		try
		{
			con=DBConnection.getConnection();
			stmt=con.createStatement();

			if(mobileNo !=null && !mobileNo.equalsIgnoreCase("")){
				sql="select flag from white_label_details where client_id in (select client_id from md_details where md_id in (select md_id from distributor_details where distributor_id in (select distributor_id from agent_details where mobile_no='"+mobileNo+"')))";
			}else if(emailID!=null && !emailID.equalsIgnoreCase("")){
				sql="select flag from white_label_details where client_id in (select client_id from md_details where md_id in (select md_id from distributor_details where distributor_id in (select distributor_id from agent_details where email_id='"+emailID+"')))";
			}else if(mobileNo !=null && emailID!=null && !emailID.equalsIgnoreCase("") && !mobileNo.equalsIgnoreCase("") ){
				sql="select flag from white_label_details where client_id in (select client_id from md_details where md_id in (select md_id from distributor_details where distributor_id in (select distributor_id from agent_details where mobile_no='"+mobileNo+"' and email_id='"+emailID+"')))";
			}else if( UserID!=null && !UserID.equalsIgnoreCase("")){
				sql="select flag from white_label_details where client_id in (select client_id from md_details where md_id in (select md_id from distributor_details where distributor_id in (select distributor_id from agent_details where agent_initial+convert(varchar(10),agent_id)='"+UserID+"')))";
			}else if(mobileNo !=null && emailID!=null && UserID!=null && !emailID.equalsIgnoreCase("") && !mobileNo.equalsIgnoreCase("") && !UserID.equalsIgnoreCase("") ){
				sql="select flag from white_label_details where client_id in (select client_id from md_details where md_id in (select md_id from distributor_details where distributor_id in (select distributor_id from agent_details where agent_initial+convert(varchar(10),agent_id)='"+UserID+"' and  mobile_no='"+mobileNo+"' and email_id='"+emailID+"')))";
			}
			System.out.println(sql);
			rs=stmt.executeQuery(sql);
			while( rs.next()){
				flag=rs.getString(1);
			}

			System.out.println(flag);
			if(flag.equalsIgnoreCase("0") || flag.equalsIgnoreCase("2")){
				if(mobileNo !=null && !mobileNo.equalsIgnoreCase("") ){
					sqlQry="select ad.agent_initial+convert(varchar(10),ad.agent_id) as AGID,dd.distributor_initial+convert(varchar(10),ad.distributor_id) as DSID,ad.mobile_no,ad.email_id,ld.login_status,ad.MPIN from agent_details ad,login_details ld,distributor_details dd,md_details md where ad.mobile_no='"+mobileNo+"' and ad.agent_id=ld.user_id and ad.distributor_id=dd.distributor_id and md.md_id=dd.md_id and md.md_id='"+mdID+"'";
				}
				else if(emailID !=null && !emailID.equalsIgnoreCase("")){
					sqlQry="select ad.agent_initial+convert(varchar(10),ad.agent_id) as AGID,dd.distributor_initial+convert(varchar(10),ad.distributor_id) as DSID,ad.mobile_no,ad.email_id,ld.login_status,ad.MPIN from agent_details ad,login_details ld,distributor_details dd,md_details md where ad.email_id='"+emailID+"' and ad.agent_id=ld.user_id and ad.distributor_id=dd.distributor_id and md.md_id=dd.md_id and md.md_id='"+mdID+"'";
				}
				else if(UserID !=null && !UserID.equalsIgnoreCase("")){
					sqlQry="select ad.agent_initial+convert(varchar(10),ad.agent_id) as AGID,dd.distributor_initial+convert(varchar(10),ad.distributor_id) as DSID, ad.mobile_no,ad.email_id,ld.login_status,ad.MPIN  from agent_details ad,login_details ld,distributor_details dd,md_details md where ad.agent_id=ld.user_id and ad.distributor_id=dd.distributor_id and ad.agent_initial+convert(varchar(10),ad.agent_id)='"+UserID+"' and md.md_id='"+mdID+"'";
				}
				else if(mobileNo !=null && emailID!=null && !emailID.equalsIgnoreCase("") && !mobileNo.equalsIgnoreCase("")){
					sqlQry="select ad.agent_initial+convert(varchar(10),ad.agent_id) as AGID,dd.distributor_initial+convert(varchar(10),ad.distributor_id) as DSID,ad.mobile_no,ad.email_id,ld.login_status,ad.MPIN from agent_details ad,login_details ld,distributor_details dd,md_details md where ad.email_id='"+emailID+"' and ad.mobile_no='"+mobileNo+"' and ad.agent_id=ld.user_id and ad.distributor_id=dd.distributor_id and md.md_id=dd.md_id and md.md_id='"+mdID+"'";
				}else if (UserID!=null && mobileNo!=null && !UserID.equalsIgnoreCase("") || !mobileNo.equalsIgnoreCase("")){
					sqlQry="select ad.agent_initial+convert(varchar(10),ad.agent_id) as AGID,dd.distributor_initial+convert(varchar(10),ad.distributor_id) as DSID,ad.mobile_no,ad.email_id,ld.login_status,ad.MPIN from agent_details ad,login_details ld,distributor_details dd,md_details md where ad.agent_initial+convert(varchar(10),ad.agent_id)='"+UserID+"' and ad.mobile_no='"+mobileNo+"' and ad.agent_id=ld.user_id and ad.distributor_id=dd.distributor_id and md.md_id=dd.md_id and md.md_id='"+mdID+"'";
				}else if(emailID!=null && UserID!=null && !emailID.equalsIgnoreCase("") && !UserID.equalsIgnoreCase("")){
					sqlQry="select ad.agent_initial+convert(varchar(10),ad.agent_id) as AGID,dd.distributor_initial+convert(varchar(10),ad.distributor_id) as DSID,ad.mobile_no,ad.email_id,ld.login_status,ad.MPIN from agent_details ad,login_details ld,distributor_details dd,md_details md where ad.email_id='"+emailID+"' and ad.agent_initial+convert(varchar(10),ad.agent_id)='"+UserID+"' and ad.agent_id=ld.user_id and ad.distributor_id=dd.distributor_id and md.md_id=dd.md_id and md.md_id='"+mdID+"'";
				}
				else if(emailID!=null && UserID!=null && mobileNo!=null && !emailID.equalsIgnoreCase("") && !UserID.equalsIgnoreCase("") && !mobileNo.equalsIgnoreCase("")){
					sqlQry="select ad.agent_initial+convert(varchar(10),ad.agent_id) as AGID,dd.distributor_initial+convert(varchar(10),ad.distributor_id) as DSID,ad.mobile_no,ad.email_id,ld.login_status,ad.MPIN from agent_details ad,login_details ld,distributor_details dd,md_details md where ad.email_id='"+emailID+"' and ad.agent_initial+convert(varchar(10),ad.agent_id)='"+UserID+"' and ad.mobile_no='"+mobileNo+"' and ad.agent_id=ld.user_id and ad.distributor_id=dd.distributor_id and md.md_id=dd.md_id and md.md_id='"+mdID+"'";
				}
			}else {
				if(mobileNo !=null && !mobileNo.equalsIgnoreCase("") ){
					sqlQry="select ad.agent_initial+convert(varchar(10),ad.agent_id) as AGID,dd.distributor_initial+convert(varchar(10),ad.distributor_id) as DSID,ad.mobile_no,ad.email_id,ld.login_status,ad.MPIN from agent_details ad,recharge_e_point_login_info ld,distributor_details dd,md_details md where ad.mobile_no='"+mobileNo+"' and ad.agent_id=ld.user_id and ad.distributor_id=dd.distributor_id and md.md_id=dd.md_id and md.md_id='"+mdID+"'";
				}
				else if(emailID !=null && !emailID.equalsIgnoreCase("")){
					sqlQry="select ad.agent_initial+convert(varchar(10),ad.agent_id) as AGID,dd.distributor_initial+convert(varchar(10),ad.distributor_id) as DSID,ad.mobile_no,ad.email_id,ld.login_status,ad.MPIN from agent_details ad,recharge_e_point_login_info ld,distributor_details dd,md_details md where ad.email_id='"+emailID+"' and ad.agent_id=ld.user_id and ad.distributor_id=dd.distributor_id and md.md_id=dd.md_id and md.md_id='"+mdID+"'";
				}
				else if(UserID !=null && !UserID.equalsIgnoreCase("")){
					sqlQry="select ad.agent_initial+convert(varchar(10),ad.agent_id) as AGID,dd.distributor_initial+convert(varchar(10),ad.distributor_id) as DSID, ad.mobile_no,ad.email_id,ld.login_status,ad.MPIN  from agent_details ad,recharge_e_point_login_info ld,distributor_details dd,md_details md where ad.agent_id=ld.user_id and ad.distributor_id=dd.distributor_id and ad.agent_initial+convert(varchar(10),ad.agent_id)='"+UserID+"' and md.md_id='"+mdID+"'";
				}
				else if(mobileNo !=null && emailID!=null && !emailID.equalsIgnoreCase("") && !mobileNo.equalsIgnoreCase("")){
					sqlQry="select ad.agent_initial+convert(varchar(10),ad.agent_id) as AGID,dd.distributor_initial+convert(varchar(10),ad.distributor_id) as DSID,ad.mobile_no,ad.email_id,ld.login_status,ad.MPIN from agent_details ad,recharge_e_point_login_info ld,distributor_details dd,md_details md where ad.email_id='"+emailID+"' and ad.mobile_no='"+mobileNo+"' and ad.agent_id=ld.user_id and ad.distributor_id=dd.distributor_id and md.md_id=dd.md_id and md.md_id='"+mdID+"'";
				}else if (UserID!=null && mobileNo!=null && !UserID.equalsIgnoreCase("") && !mobileNo.equalsIgnoreCase("")){
					sqlQry="select ad.agent_initial+convert(varchar(10),ad.agent_id) as AGID,dd.distributor_initial+convert(varchar(10),ad.distributor_id) as DSID,ad.mobile_no,ad.email_id,ld.login_status,ad.MPIN from agent_details ad,recharge_e_point_login_info ld,distributor_details dd,md_details md where ad.agent_initial+convert(varchar(10),ad.agent_id)='"+UserID+"' and ad.mobile_no='"+mobileNo+"' and ad.agent_id=ld.user_id and ad.distributor_id=dd.distributor_id and md.md_id=dd.md_id and md.md_id='"+mdID+"'";
				}else if(emailID!=null && UserID!=null &&!emailID.equalsIgnoreCase("") && !UserID.equalsIgnoreCase("")){
					sqlQry="select ad.agent_initial+convert(varchar(10),ad.agent_id) as AGID,dd.distributor_initial+convert(varchar(10),ad.distributor_id) as DSID,ad.mobile_no,ad.email_id,ld.login_status,ad.MPIN from agent_details ad,recharge_e_point_login_info ld,distributor_details dd,md_details md where ad.email_id='"+emailID+"' and ad.agent_initial+convert(varchar(10),ad.agent_id)='"+UserID+"' and ad.agent_id=ld.user_id and ad.distributor_id=dd.distributor_id and md.md_id=dd.md_id and md.md_id='"+mdID+"'";
				}
				else if(emailID!=null && UserID!=null && mobileNo!=null && !emailID.equalsIgnoreCase("") && !UserID.equalsIgnoreCase("") && !mobileNo.equalsIgnoreCase("")){
					sqlQry="select ad.agent_initial+convert(varchar(10),ad.agent_id) as AGID,dd.distributor_initial+convert(varchar(10),ad.distributor_id) as DSID,ad.mobile_no,ad.email_id,ld.login_status,ad.MPIN from agent_details ad,recharge_e_point_login_info ld,distributor_details dd,md_details md where ad.email_id='"+emailID+"' and ad.agent_initial+convert(varchar(10),ad.agent_id)='"+UserID+"' and ad.mobile_no='"+mobileNo+"' and ad.agent_id=ld.user_id and ad.distributor_id=dd.distributor_id and md.md_id=dd.md_id and md.md_id='"+mdID+"'";
				}

			}	
			System.out.println(sqlQry);
			rs1=stmt.executeQuery(sqlQry);
			while(rs1.next())
			{

				information.put("AGID",rs1.getString("AGID"));
				information.put("distributor_id",rs1.getString("DSID"));				   
				information.put("mobile",rs1.getString("mobile_no"));
				information.put("email_id",rs1.getString("email_id"));
				information.put("login_status",rs1.getString("login_status"));				   
				information.put("mpin", rs1.getString("MPIN"));
				AGDetails.add(information);

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
				if(rs1!=null)
					rs1.close();
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
		return AGDetails;
	}







}
