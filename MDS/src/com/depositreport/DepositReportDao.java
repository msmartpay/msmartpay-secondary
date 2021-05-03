package com.depositreport;
import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import javax.servlet.http.HttpSession;
import com.db.DBConnection;


public class DepositReportDao {
	public static String getAgentIdStatus(Connection con,String agentid,String dist_id){
		Statement stmt=null;
		ResultSet rs=null;
		String query="";
		String status="invalid";
		try{

			con=DBConnection.getConnection();
			stmt=con.createStatement();
			query="select agent_id from agent_details where agent_id="+agentid+" and distributor_id="+dist_id+"";
			System.out.println(query);
			rs=stmt.executeQuery(query);

			while(rs.next())
			{
				status="valid";
			}


		}
		catch(Exception e)
		{
			System.out.println("Exception in DepositReportDao, method getAgentDepositReport "+e.toString());
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
				System.out.println("Exception in DepositReportDao, method getAgentDepositReport while closing connection"+e.toString());
			}

		}
		return status;



	}
	public static Vector getAgentDepositReport(Connection con,String dist_id,String agentid,String todt,String fromdt)
	{

		Statement stmt=null;
		ResultSet rs=null;
		String query="";
		Vector vecdata=new Vector();
		try{

			con=DBConnection.getConnection();
			stmt=con.createStatement();

			query="select a.ammount_to_credit,a.remarks,convert(varchar(10),a.request_date,111) as date,a.request_time,a.mode_of_payment,a.checque_no,a.status,"+
					"a.bank_name,b.agency_name,b.email_id,b.mobile_no,b.agent_id,b.agent_initial from agent_journal a, agent_details b where a.distributor_id='"+dist_id+"' and   a.agent_id=b.agent_id and (a.request_date between '"+todt+"' and '"+fromdt+"') and b.agent_id="+agentid+" order by date"  ;


			System.out.println("query is>>>>>><<<"+query);
			rs=stmt.executeQuery(query);

			while(rs.next())
			{
				HashMap hm=new HashMap();
				hm.put("amount",rs.getDouble("ammount_to_credit")); 
				hm.put("remark",rs.getString("remarks")); 
				hm.put("date",rs.getString("date")); 
				hm.put("time",rs.getString("request_time")); 
				hm.put("mode",rs.getString("mode_of_payment"));
				hm.put("checque_no",rs.getString("checque_no"));
				hm.put("status",rs.getString("status"));
				hm.put("bank_name",rs.getString("bank_name"));
				hm.put("agencyname",rs.getString("agency_name"));
				hm.put("emailid",rs.getString("email_id"));
				hm.put("mobileno",rs.getString("mobile_no"));
				String agent_id=(String)rs.getString("agent_id");
				String  agent_initial=(String)rs.getString("agent_initial");

				String tot=agent_initial+agent_id;
				hm.put("agent_id",tot); 
				hm.put("agentid",agent_id); 

				vecdata.add(hm);	
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception in DepositReportDao, method getAgentDepositReport "+e.toString());
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
				System.out.println("Exception in DepositReportDao, method getAgentDepositReport while closing connection"+e.toString());
			}

		}
		return vecdata;


	}

	public static Vector getAllAgentDepositReport(Connection con,String dist_id,String todt,String fromdt)
	{

		Statement stmt=null;
		ResultSet rs=null;
		String query="";
		Vector vecdata=new Vector();
		try{

			con=DBConnection.getConnection();
			stmt=con.createStatement();

			query="select a.ammount_to_credit,a.remarks,convert(varchar(10),a.request_date,111) as date,a.request_time,a.mode_of_payment,a.checque_no,a.status,"+
					"a.bank_name,b.agency_name,b.email_id,b.mobile_no,b.agent_id,b.agent_initial from agent_journal a, agent_details b where a.distributor_id='"+dist_id+"' and   a.agent_id=b.agent_id and (a.request_date between '"+todt+"' and '"+fromdt+"') order by date";


			System.out.println("query is>>>>>><<<"+query);
			rs=stmt.executeQuery(query);

			while(rs.next())
			{
				HashMap hm=new HashMap();
				hm.put("amount",rs.getDouble("ammount_to_credit")); 
				hm.put("remark",rs.getString("remarks")); 
				hm.put("date",rs.getString("date")); 
				hm.put("time",rs.getString("request_time")); 
				hm.put("mode",rs.getString("mode_of_payment"));
				hm.put("checque_no",rs.getString("checque_no"));
				hm.put("status",rs.getString("status"));
				hm.put("bank_name",rs.getString("bank_name"));
				hm.put("agencyname",rs.getString("agency_name"));
				hm.put("emailid",rs.getString("email_id"));
				hm.put("mobileno",rs.getString("mobile_no"));
				String agent_id=(String)rs.getString("agent_id");
				String  agent_initial=(String)rs.getString("agent_initial");

				String tot=agent_initial+agent_id;
				hm.put("agent_id",tot); 
				hm.put("agentid",agent_id); 

				vecdata.add(hm);	
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception in DepositReportDao, method getAgentDepositReport "+e.toString());
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
				System.out.println("Exception in DepositReportDao, method getAgentDepositReport while closing connection"+e.toString());
			}

		}
		return vecdata;


	}

	public static Vector getDistributorDepositReport(Connection con,String dist_id,String todt,String fromdt){
		Statement stmt=null;
		ResultSet rs=null;
		String query="";
		Vector vecdata=new Vector();
		try{

			con=DBConnection.getConnection();
			stmt=con.createStatement();


			/*	if(fromdt=="" && todt=="")  

			 {
			 query="select a.ammount_to_credit,a.remarks,convert(varchar(10),a.request_date,111)as date,a.request_time,a.mode_of_payment,a.checque_no,a.status,"+
				 "a.bank_name,b.agency_name,b.email_id,b.mobile_no,b.agent_id,b.agent_initial from distributor_journal a, distributor_details b where a.distributor_id='"+dist_id+"' and   a.agent_id=b.agent_id" ;

			 }
			 else
		 {*/


			query="select a.ammount_to_credit,a.remarks,convert(varchar(10),a.request_date,111) as date,a.request_time,a.mode_of_payment,a.checque_no,a.status,"+
					"a.bank_name,b.company_name,b.email_id,b.mobile_no,b.distributor_id,b.distributor_initial from distributor_journal a, distributor_details b where a.distributor_id='"+dist_id+"' and "+
					"(a.request_date between '"+todt+"' and '"+fromdt+"')  and   a.distributor_id=b.distributor_id order by date";

			System.out.println("query is>>>>>><<<"+query);
			rs=stmt.executeQuery(query);

			while(rs.next())
			{
				HashMap hm=new HashMap();
				hm.put("amount",rs.getDouble("ammount_to_credit")); 
				hm.put("remark",rs.getString("remarks")); 
				hm.put("date",rs.getString("date")); 
				hm.put("time",rs.getString("request_time")); 
				hm.put("mode",rs.getString("mode_of_payment"));
				hm.put("checque_no",rs.getString("checque_no"));
				hm.put("status",rs.getString("status"));
				hm.put("bank_name",rs.getString("bank_name"));
				hm.put("agencyname",rs.getString("company_name"));
				hm.put("emailid",rs.getString("email_id"));
				hm.put("mobileno",rs.getString("mobile_no"));
				String distributor_id=(String)rs.getString("distributor_id");
				String  distributor_initial=(String)rs.getString("distributor_initial");

				String tot=distributor_initial+distributor_id;
				hm.put("tot",tot); 
				//hm.put("agentid",agent_id); 

				vecdata.add(hm);	
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception in DepositReportDao, method getDistributorDepositReport "+e.toString());
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
				System.out.println("Exception in DepositReportDao, method getDistributorDepositReport while closing connection"+e.toString());
			}

		}
		return vecdata;

	}
}