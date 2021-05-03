package com.smsResponse;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import com.db.DBConnection;

public class SMSResponseDao {


	@SuppressWarnings("unused")
	public ArrayList<HashMap<String, String>> getSMSDetailsByMob(String mdID,String mobileNo,String DOJ) 
	{
		ArrayList<HashMap<String, String>> SMSDetails=new ArrayList<HashMap<String, String>>();
		
		Statement stmt=null;
		ResultSet rs=null;
		Connection con=null;
		String sql="";
		try
		{
			int count=0;
			con=DBConnection.getConnection();
			stmt=con.createStatement();
			String AgentMobileNo="91"+mobileNo;			
		
				sql="select top 20 rec.TEXT_MESSAGE,rec.KEYWORD,convert(varchar(20),rec.CREATED_DATE,113) as CREATED_DATE,sms_send.TEXT_MESSAGE,convert(varchar(20),sms_send.SMS_SEND_DATETIME,113) as SMS_SEND_DATETIME from SMS_RECEIVED as rec inner join sms_send on sms_send.REF_NO=rec.REF_NO inner join agent_details as ag on ag.agent_mobileno=rec.mobile inner join distributor_details as ds on ds.distributor_id=ag.distributor_id inner join md_details as md on md.md_id=ds.md_id where md.md_id='"+mdID+"' and ag.Agent_MobileNo='"+AgentMobileNo+"' and rec.CREATED_DATE='"+DOJ+"' order by rec.CREATED_DATE desc";
				//	sql="select top 20 rec.TEXT_MESSAGE,rec.KEYWORD,rec.CREATED_DATE,sms_send.TEXT_MESSAGE,sms_send.SMS_SEND_DATETIME from SMS_RECEIVED as rec inner join sms_send on sms_send.REF_NO=rec.REF_NO inner join agent_details as ag on ag.agent_mobileno=rec.mobile inner join distributor_details as ds on ds.distributor_id=ag.distributor_id inner join md_details as md on md.md_id=ds.md_id where ag.Agent_MobileNo='"+AgentMobileNo+"'";
			
			System.out.println(sql);
			rs=stmt.executeQuery(sql);
			while(rs.next())
			{
				HashMap<String, String> information=new HashMap<String, String>();
				information.put("RecMsg", rs.getString(1));
				information.put("Keyword", rs.getString(2));
				information.put("RecTime", rs.getString(3));
				information.put("SendMsg", rs.getString(4));
				information.put("SendTime",rs.getString(5));				
				SMSDetails.add(information);
							
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
		return SMSDetails;
	}
	public ArrayList<HashMap<String, String>> getSMSDetailsByAgentID(String mdID,String mobileNo,String DOJ) 
	{
		ArrayList<HashMap<String, String>> SMSDetails=new ArrayList<HashMap<String, String>>();
		
		Statement stmt=null;
		ResultSet rs=null;
		Connection con=null;
		String sql="";
		try
		{
			con=DBConnection.getConnection();
			stmt=con.createStatement();
		
				sql="select top 20 rec.TEXT_MESSAGE,rec.KEYWORD,convert(varchar(20),rec.CREATED_DATE,113) as CREATED_DATE,sms_send.TEXT_MESSAGE,convert(varchar(20),sms_send.SMS_SEND_DATETIME,113) as SMS_SEND_DATETIME from SMS_RECEIVED as rec inner join sms_send on sms_send.REF_NO=rec.REF_NO inner join agent_details as ag on ag.agent_mobileno=rec.mobile inner join distributor_details as ds on ds.distributor_id=ag.distributor_id inner join md_details as md on md.md_id=ds.md_id where md.md_id='"+mdID+"' and ag.Agent_MobileNo='"+mobileNo+"' and ag.CREATED_DATE='"+DOJ+"' order by rec.CREATED_DATE desc";
				//	sql="select top 20 rec.TEXT_MESSAGE,rec.KEYWORD,rec.CREATED_DATE,sms_send.TEXT_MESSAGE,sms_send.SMS_SEND_DATETIME from SMS_RECEIVED as rec inner join sms_send on sms_send.REF_NO=rec.REF_NO inner join agent_details as ag on ag.agent_mobileno=rec.mobile inner join distributor_details as ds on ds.distributor_id=ag.distributor_id inner join md_details as md on md.md_id=ds.md_id where ag.Agent_MobileNo='"+mobileNo+"'";
			
			System.out.println(sql);
			rs=stmt.executeQuery(sql);
			while(rs.next())
			{ 
				HashMap<String, String> information=new HashMap<String, String>();
				information.put("RecMsg", rs.getString(1));
				information.put("Keyword", rs.getString(2));
				information.put("RecTime", rs.getString(3));
				information.put("SendMsg", rs.getString(4));
				information.put("SendTime",rs.getString(5));				
				SMSDetails.add(information);
							
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
		return SMSDetails;
	}
	
	public String getDOJ(String Agent_MobileNo) 
	{
		String DOJ=null;
		Statement stmt=null;
		ResultSet rs=null;
		Connection con=null;
		String sql="";
		try
		{
			con=DBConnection.getConnection();
			stmt=con.createStatement();
			sql="select convert(varchar(26),date_of_joining,23) from agent_details where Agent_MobileNo='"+Agent_MobileNo+"'";
			System.out.println(sql);
			rs=stmt.executeQuery(sql);
			while(rs.next())
			{
				DOJ =rs.getString(1);			
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
		return DOJ;
	}
	
	public String getAgentMob(String AgentId) 
	{
		String AgentMobileNo=null;
		Statement stmt=null;
		ResultSet rs=null;
		Connection con=null;
		String sql="";
		//String Status="invalid";
		try
		{
			con=DBConnection.getConnection();
			stmt=con.createStatement();
		
			sql="select Agent_MobileNo from agent_details where agent_initial+CONVERT(varchar,agent_id)='"+AgentId+"'";
			System.out.println(sql);
			rs=stmt.executeQuery(sql);
			while(rs.next())
			{
				AgentMobileNo =rs.getString(1);			
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
		return AgentMobileNo;
	}

}
