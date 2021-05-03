package com.reports.statement;

import java.io.FileWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.common.HibernateSession;
import com.common.HibernateSession1;

public class AccountStatementDao {

	public final String getAgentAccountstmt(String loginType, String portalId,String userType, String id, String fromDate, String toDate, String filePath) {

		Session session = null;
		String Status = "Norecord";

		Connection con =null;
		try {

			session = HibernateSession.getSessionFactory().openSession();

			con = session.connection();

			CallableStatement cstmt = null;

			cstmt = con.prepareCall("{call AccountStatementSuperDownload(?,?,?,?,?,?)}");

			cstmt.setString(1, fromDate);
			cstmt.setString(2, toDate);
			cstmt.setString(3, userType);
			cstmt.setString(4, id);
			cstmt.setString(5, loginType);
			cstmt.setString(6, portalId);

			ResultSet rs = cstmt.executeQuery();

			Status = "Success";
			FileWriter writer = new FileWriter(filePath);

			writer.append("Complete MD ID");
			writer.append('\t');
			writer.append("MD Company Name");
			writer.append('\t');
			writer.append("Complete DS ID");
			writer.append('\t');
			writer.append("DS Company Name");
			writer.append('\t');
			writer.append("Complete Agent ID");
			writer.append('\t');
			writer.append("Agency Name");
			writer.append('\t');
			writer.append("Date");
			writer.append('\t');
			writer.append("Time");
			writer.append('\t');
			writer.append("Transaction Id");
			writer.append('\t');
			writer.append("Perticulars");
			writer.append('\t');
			writer.append("Transaction Amount");
			writer.append('\t');
			writer.append("Margin");
			writer.append('\t');
			writer.append("Fee");
			writer.append('\t');
			writer.append("GST");
			writer.append('\t');
			writer.append("Net Amount");
			writer.append('\t');
			writer.append("Type");
			writer.append('\t');
			writer.append("Current Balance");
			writer.append('\t');
			writer.append("Transacction Status");
			writer.append('\t');
			writer.append("Remark");
			writer.append('\n'); 
			String perticulers="";
			String type="";
			String remark="";

			while (rs.next()) {

				writer.append(rs.getString("Complete_MD_ID"));
				writer.append('\t');
				writer.append(rs.getString("company_name"));
				writer.append('\t');
				writer.append(rs.getString("Complete_DS_ID"));
				writer.append('\t');
				writer.append(rs.getString("company_name"));
				writer.append('\t');
				writer.append(rs.getString("Complete_Agent_ID"));
				writer.append('\t');
				writer.append(rs.getString("agency_name"));
				writer.append('\t');
				String service=(String)rs.getString("service");

				if(service==null || service.equalsIgnoreCase("null")){
					service="  ";

				}
				else if(service.equalsIgnoreCase("DisttoAgent")){
					perticulers="Trade Balance - Taken";

				}
				else if(service.equalsIgnoreCase("accountadjustment")){
					perticulers="Account Adjustment";
				}
				else if(service.equalsIgnoreCase("OffLineMobRech")|| service.equalsIgnoreCase("liveMobRech")||service.equalsIgnoreCase("Cyber Plat")){

					perticulers="Recharge - MOB";
				}
				else if(service.equalsIgnoreCase("OffLineDTHRech")|| service.equalsIgnoreCase("liveDTHRech")||service.equalsIgnoreCase("Cyber Plat")){

					perticulers="Recharge - DTH";
				}
				else if(service.equalsIgnoreCase("ROffLineDTHRech")){

					perticulers="Refund - Recharge  DTH";
				}
				else if(service.equalsIgnoreCase("ROffLineMobRech")){

					perticulers="Refund - Recharge  Mobile";
				}
				else if(service.equalsIgnoreCase("RliveDTHRech")){

					perticulers="Refund - Recharge DTH";
				}
				else  if(service.equalsIgnoreCase("RliveMobRech")){

					perticulers="Refund - Recharge MOB";
				}
				else{
					perticulers=service;
				}

				String action=((String)rs.getString("action"));
				if (action.equalsIgnoreCase("credit")){ 
					type="Cr";
				} 
				else if (action.equalsIgnoreCase("debit")){ 
					type="Dr";
				} 
				else
				{
					type=action;   
				}

				writer.append(rs.getString("tran_date"));
				writer.append('\t');
				writer.append(rs.getString("tran_time"));
				writer.append('\t');
				writer.append(rs.getString("tranid"));
				writer.append('\t');
				writer.append(perticulers);
				writer.append('\t');
				writer.append(rs.getString("requestamount"));
				writer.append('\t');
				writer.append(rs.getString("commision"));
				writer.append('\t');
				writer.append(rs.getString("charge"));
				writer.append('\t');
				writer.append(rs.getString("gst"));
				writer.append('\t');
				writer.append(rs.getString("netamount"));
				writer.append('\t');
				writer.append(type);
				writer.append('\t');
				writer.append(rs.getString("lastbalance"));
				writer.append('\t');
				writer.append(rs.getString("Tran_status"));
				writer.append('\t');
				remark=rs.getString("Remark");
				if(remark==null){
					remark="";
				}
				writer.append(remark);
				writer.append('\n');

			}

			writer.flush();
			writer.close();
		} catch (Exception e) {

			Status = "ERROR";
		} finally {
			try {

				if(con!=null)con.close();
				if(session!=null)session.close();

			} catch (Exception e) {
				//System.out.println("Exception in AccountStatementDao,getAgentAccountstmt");
				e.printStackTrace();
			}

		}
		return Status;
	}

	public final String getAgentAccountstmtDetail(String loginType, String portalId,String userType, String id, String fromDate, String toDate, String filePath) {

		Session session = null;

		String Status = "Norecord";

		Connection con =null;
		try {

			session = HibernateSession.getSessionFactory().openSession();
			con = session.connection();

			CallableStatement cstmt = null;

			cstmt = con.prepareCall("{call AccountStatementSuperDownload(?,?,?,?,?,?)}");

			cstmt.setString(1, fromDate);
			cstmt.setString(2, toDate);
			cstmt.setString(3, userType);
			cstmt.setString(4, id);
			cstmt.setString(5, loginType);
			cstmt.setString(6, portalId);

			ResultSet rs = cstmt.executeQuery();
			Status = "Success";
			FileWriter writer = new FileWriter(filePath);

			writer.append("Complete AgentID");
			writer.append('\t');
			writer.append("Agent Name");
			writer.append('\t');
			writer.append("ID_no");
			writer.append('\t');
			writer.append("Agent_Tran_ID");
			writer.append('\t');
			writer.append("User_Type");
			writer.append('\t');
			writer.append("AgentID");
			writer.append('\t');
			writer.append("Complete_DS_ID");
			writer.append('\t');
			writer.append("DS Company Name");
			writer.append('\t');
			writer.append("Transaction_No");
			writer.append('\t');
			writer.append("Date_Of_Transaction");
			writer.append('\t');
			writer.append("Time_Of_Transaction");
			writer.append('\t');
			writer.append("Service");
			writer.append('\t');
			writer.append("Agent_balAmt_b_Ded");
			writer.append('\t');
			writer.append("ReqAmount");
			writer.append('\t');
			writer.append("Commession");
			writer.append('\t');
			writer.append("Fee");
			writer.append('\t');
			writer.append("GST");
			writer.append('\t');
			writer.append("Other_charge");
			writer.append('\t');
			writer.append("extra_commission");
			writer.append('\t');
			writer.append("DeductedAmt");
			writer.append('\t');
			writer.append("Action_on_bal_amt");
			writer.append('\t');
			writer.append("Agent_balAmt_A_Ded");
			writer.append('\t');
			writer.append("Tran_status");
			writer.append('\t');
			writer.append("Updated_date");
			writer.append('\t');
			writer.append("updated_time");
			writer.append('\t');
			writer.append("Agent_F_balAmt");
			writer.append('\t');
			writer.append("IpAddress");
			writer.append('\t');
			writer.append("UpdIpAddress");
			writer.append('\t');
			writer.append("Bal_amt_b_Refund");
			writer.append('\t');
			writer.append("refunded_amount");
			writer.append('\t');
			writer.append("Bal_Amount_A_upd");
			writer.append('\t');
			writer.append("Remark");
			writer.append('\t');
			writer.append("Reference_id");
			writer.append('\t');
			writer.append("Tds_Amount");
			writer.append('\t');
			writer.append("External_remark");
			writer.append('\n');

			while (rs.next()) {

				writer.append(rs.getString(1));
				writer.append('\t');
				writer.append(rs.getString(34));
				writer.append('\t');
				writer.append(rs.getString(2));
				writer.append('\t');
				writer.append(rs.getString(3));
				writer.append('\t');
				writer.append(rs.getString(4));
				writer.append('\t');
				writer.append(rs.getString(5));
				writer.append('\t');
				writer.append(rs.getString(6));
				writer.append('\t');
				writer.append(rs.getString(35));
				writer.append('\t');
				writer.append(rs.getString(7));
				writer.append('\t');
				writer.append(rs.getString(8));
				writer.append('\t');
				writer.append(rs.getString(9));
				writer.append('\t');
				writer.append(rs.getString(10));
				writer.append('\t');
				writer.append(rs.getString(11));
				writer.append('\t');
				writer.append(rs.getString(12));
				writer.append('\t');
				writer.append(rs.getString(13));
				writer.append('\t');
				writer.append(rs.getString(14));
				writer.append('\t');
				writer.append(rs.getString(15));
				writer.append('\t');
				writer.append(rs.getString(16));
				writer.append('\t');
				writer.append(rs.getString(17));
				writer.append('\t');
				writer.append(rs.getString(18));
				writer.append('\t');
				writer.append(rs.getString(19));
				writer.append('\t');
				writer.append(rs.getString(20));
				writer.append('\t');
				writer.append(rs.getString(21));
				writer.append('\t');
				writer.append(rs.getString(22));
				writer.append('\t');
				writer.append(rs.getString(23));
				writer.append('\t');
				writer.append(rs.getString(24));
				writer.append('\t');
				writer.append(rs.getString(25));
				writer.append('\t');
				writer.append(rs.getString(26));
				writer.append('\t');
				writer.append(rs.getString(27));
				writer.append('\t');
				writer.append(rs.getString(28));
				writer.append('\t');
				writer.append(rs.getString(29));
				writer.append('\t');
				writer.append(rs.getString(30));
				writer.append('\t');
				writer.append(rs.getString(31));
				writer.append('\t');
				writer.append(rs.getString(32));
				writer.append('\t');
				writer.append(rs.getString(33));
				writer.append('\n');

			}

			writer.flush();
			writer.close();
		} catch (Exception e) {
			Status = "ERROR";
			//System.out.println("Exception in AccountStatementDao,getAgentAccountstmtDetail");
			e.printStackTrace();
		} finally {
			try {
				if(con!=null)con.close();
				if(session!=null)session.close();
			} catch (Exception e) {
				//System.out.println("Exception in AccountStatementDao,getAgentAccountstmtDetail");
				e.printStackTrace();
			}

		}
		return Status;
	}

	public final String checkIdStatus(String userType, String id) 
	{
		String result="Norecord";
		Session session=null;

		try{
			session=HibernateSession.getSessionFactory().openSession();

			String sql="";

			if(userType.equalsIgnoreCase("agent"))
			{
				sql="select agent_id from agent_details where agent_initial+convert(varchar,agent_id)='"+id+"'";
			}
			else if(userType.equalsIgnoreCase("ds"))
			{
				sql="select distributor_id from distributor_details where distributor_initial+convert(varchar,distributor_id)='"+id+"'";
			}
			else if(userType.equalsIgnoreCase("mds"))
			{
				sql="select md_id from md_details where md_initial+convert(varchar,md_id)='"+id+"'";
			}
			else{
				sql="select user_id from Admin_User_details where user_id='"+id+"'";
			}

			//System.out.println("Sql is........"+sql);
			Query query=session.createSQLQuery(sql);
			result=query.uniqueResult().toString();
			if(result !=null)
			{
				result="Success";
			}

		}catch(Exception e){
			result="ERROR";
			//System.out.println("Exception in AccountStatementDao,checkIdStatus");
			e.printStackTrace();

		}
		finally {
			try {

				if(session!=null)session.close();

			} catch (Exception e) {
				//System.out.println("Exception in AccountStatementDao,checkIdStatus");
				e.printStackTrace();
			}
		}
		////System.out.println("result is "+result);
		return result;
	}

	public final String getDSAccountstmt(String loginType, String portalId,String userType, String id, String fromDate, String toDate,String filePath) 
	{
		Session session = null;
		String Status = "Norecord";

		Connection con =null;
		try 
		{
			session = HibernateSession.getSessionFactory().openSession();


			con = session.connection();

			CallableStatement cstmt = null;

			cstmt = con.prepareCall("{call AccountStatementSuperDownload(?,?,?,?,?,?)}");

			cstmt.setString(1, fromDate);
			cstmt.setString(2, toDate);
			cstmt.setString(3, userType);
			cstmt.setString(4, id);
			cstmt.setString(5, loginType);
			cstmt.setString(6, portalId);

			ResultSet rs = cstmt.executeQuery();

			Status = "Success";
			FileWriter writer = new FileWriter(filePath);

			writer.append("Complete MD ID");
			writer.append('\t');
			writer.append("MD Company Name");
			writer.append('\t');
			writer.append("Complete DS ID");
			writer.append('\t');
			writer.append("DS Company Name");
			writer.append('\t');
			writer.append("Date");
			writer.append('\t');
			writer.append("Time");
			writer.append('\t');
			writer.append("Transaction Id");
			writer.append('\t');
			writer.append("Perticulars");
			writer.append('\t');
			writer.append("Transaction Amount");
			writer.append('\t');
			writer.append("Margin");
			writer.append('\t');
			writer.append("Charge");
			writer.append('\t');
			writer.append("Net Amount");
			writer.append('\t');
			writer.append("Type");
			writer.append('\t');
			writer.append("Current Balance");
			writer.append('\t');
			writer.append("Transacction Status");
			writer.append('\t');
			writer.append("Remark");
			writer.append('\n'); 
			String perticulers="";
			String type="";
			String remark="";

			while (rs.next()) {

				writer.append(rs.getString("Complete_MD_ID"));
				writer.append('\t');
				writer.append(rs.getString("company_name"));
				writer.append('\t');
				writer.append(rs.getString("Complete_DS_ID"));
				writer.append('\t');
				writer.append(rs.getString("company_name"));
				writer.append('\t');

				String service=(String)rs.getString("service");

				if(service.equalsIgnoreCase("mdtodist")){
					perticulers="DS TB - Taken";
				}
				else if(service.equalsIgnoreCase("transfertoagent")){
					perticulers="AG TB - Transfer";
				}
				else if(service.equalsIgnoreCase("transfertodist")){
					perticulers="DS TB - Taken";
				}
				else if(service.equalsIgnoreCase("accountadjustment")){
					perticulers="Account Adjustment";
				}
				else if(service.equalsIgnoreCase("PaymentGateway")){
					perticulers="DS TB - PG";
				}
				else{
					perticulers=service;
				}

				String action=((String)rs.getString("action"));
				if (action.equalsIgnoreCase("credit")){ 
					type="Cr";
				} 
				else if (action.equalsIgnoreCase("debit")){ 
					type="Dr";
				} 
				else{
					type=action;   
				}
				writer.append(rs.getString("tran_date"));
				writer.append('\t');
				writer.append(rs.getString("tran_time"));
				writer.append('\t');
				writer.append(rs.getString("tranid"));
				writer.append('\t');
				writer.append(perticulers);
				writer.append('\t');
				writer.append(rs.getString("requestamount"));
				writer.append('\t');
				writer.append(rs.getString("commision"));
				writer.append('\t');
				writer.append(rs.getString("charge"));
				writer.append('\t');
				writer.append(rs.getString("netamount"));
				writer.append('\t');
				writer.append(type);
				writer.append('\t');
				writer.append(rs.getString("lastbalance"));
				writer.append('\t');
				writer.append(rs.getString("Tran_status"));
				writer.append('\t');
				remark=rs.getString("Remark");
				if(remark==null){
					remark="";
				}
				writer.append(remark);
				writer.append('\n');

			}

			writer.flush();
			writer.close();
		} catch (Exception e) {
			Status = "ERROR";
			//System.out.println("Exception in AccountStatementDao,getDSAccountstmt");
			e.printStackTrace();

		} finally {
			try {
				if(con!=null)con.close();
				if(session!=null)session.close();

			} catch (Exception e) {
				//System.out.println("Exception in AccountStatementDao,getDSAccountstmt");
				e.printStackTrace();
			}
		}
		return Status;
	}

	public final String getMDAccountstmt(String loginType, String portalId,String userType, String id, String fromDate, String toDate,String filePath) 
	{

		Session session = null;
		String Status = "Norecord";

		Connection con =null;
		try {

			session = HibernateSession.getSessionFactory().openSession();


			con = session.connection();

			CallableStatement cstmt = null;

			cstmt = con.prepareCall("{call AccountStatementSuperDownload(?,?,?,?,?,?)}");

			cstmt.setString(1, fromDate);
			cstmt.setString(2, toDate);
			cstmt.setString(3, userType);
			cstmt.setString(4, id);
			cstmt.setString(5, loginType);
			cstmt.setString(6, portalId);

			ResultSet rs = cstmt.executeQuery();

			Status = "Success";
			FileWriter writer = new FileWriter(filePath);

			writer.append("Complete MD ID");
			writer.append('\t');
			writer.append("MD Name");
			writer.append('\t');
			writer.append("Date");
			writer.append('\t');
			writer.append("Time");
			writer.append('\t');
			writer.append("Transaction Id");
			writer.append('\t');
			writer.append("Perticulars");
			writer.append('\t');
			writer.append("Transaction Amount");
			writer.append('\t');
			writer.append("Margin");
			writer.append('\t');
			writer.append("Charge");
			writer.append('\t');
			writer.append("Net Amount");
			writer.append('\t');
			writer.append("Type");
			writer.append('\t');
			writer.append("Current Balance");
			writer.append('\t');
			writer.append("Transacction Status");
			writer.append('\t');
			writer.append("Remark");
			writer.append('\n'); 
			String perticulers="";
			String type="";
			String remark="";
			while (rs.next()) {

				writer.append(rs.getString("Complete_MD_ID"));
				writer.append('\t');
				writer.append(rs.getString("company_name"));
				writer.append('\t');
				perticulers=(String)rs.getString("service");
				String action=((String)rs.getString("action"));
				if (action.equalsIgnoreCase("credit")){ 
					type="Cr";
				} 
				else if (action.equalsIgnoreCase("debit")){ 
					type="Dr";
				} 
				else{
					type=action;   
				}
				writer.append(rs.getString("tran_date"));
				writer.append('\t');
				writer.append(rs.getString("tran_time"));
				writer.append('\t');
				writer.append(rs.getString("tranid"));
				writer.append('\t');
				writer.append(perticulers);
				writer.append('\t');
				writer.append(rs.getString("requestamount"));
				writer.append('\t');
				writer.append(rs.getString("commision"));
				writer.append('\t');
				writer.append(rs.getString("charge"));
				writer.append('\t');
				writer.append(rs.getString("netamount"));
				writer.append('\t');
				writer.append(type);
				writer.append('\t');
				writer.append(rs.getString("lastbalance"));
				writer.append('\t');
				writer.append(rs.getString("Tran_status"));
				writer.append('\t');
				remark=rs.getString("Remark");
				if(remark==null)
				{
					remark="";
				}
				writer.append(remark);
				writer.append('\n');

			}

			writer.flush();
			writer.close();
		} catch (Exception e) {
			//System.out.println("Exception in AccountStatementDao,getMDAccountstmt");
			//System.out.println(e.toString());
			Status = "ERROR";

		} finally {
			try {
				if(con!=null)con.close();
				if(session!=null)session.close();

			} catch (Exception e) {
				//System.out.println("Exception in AccountStatementDao,getMDAccountstmt");
				e.printStackTrace();
			}
		}
		return Status;
	}

	public final String getPortalAccountstmt(String loginType, String portalId,String userType, String id, String fromDate, String toDate,String filePath) 
	{

		Session session = null;
		String Status = "Norecord";
		String ServiceCharge="";
		String BankCharge="";
		String OtherCharge="";
		String Comm="";

		Connection con =null;

		try {

			session = HibernateSession.getSessionFactory().openSession();


			con = session.connection();

			CallableStatement cstmt = null;

			cstmt = con.prepareCall("{call AccountStatementSuperDownload(?,?,?,?,?,?)}");

			cstmt.setString(1, fromDate);
			cstmt.setString(2, toDate);
			cstmt.setString(3, userType);
			cstmt.setString(4, id);
			cstmt.setString(5, loginType);
			cstmt.setString(6, portalId);
			////System.out.println("we have called procedure");
			ResultSet rs = cstmt.executeQuery();

			Status = "Success";
			FileWriter writer = new FileWriter(filePath);

			writer.append("Client ID");
			writer.append('\t');
			writer.append("Portal ID");
			writer.append('\t');
			writer.append("Company Name");
			writer.append('\t');
			writer.append("User Type");
			writer.append('\t');
			writer.append("Transaction ID");
			writer.append('\t');
			writer.append("Reference ID");
			writer.append('\t');
			writer.append("Request Date");
			writer.append('\t');
			writer.append("Request Time");
			writer.append('\t');
			writer.append("Service");
			writer.append('\t');
			writer.append("Requested Amount");
			writer.append('\t');
			writer.append("Commission");
			writer.append('\t');
			writer.append("Service Charge");
			writer.append('\t');
			writer.append("Bank Charge");
			writer.append('\t');
			writer.append("Other Charge");
			writer.append('\t');
			//recent added
			writer.append("Accepted Amount");
			writer.append('\t');
			writer.append("Action on Amount");
			writer.append('\t');
			writer.append("Previous Balance ");
			writer.append('\t');
			writer.append("Current Balance");
			writer.append('\t');
			writer.append("Status");
			writer.append('\t');
			writer.append("Updated Date");
			writer.append('\t');
			writer.append("Updated Time");
			writer.append('\t');
			writer.append("Updated User");
			writer.append('\t');
			writer.append("Remark");
			writer.append('\n');

			while (rs.next()) {

				writer.append(rs.getString(1));
				writer.append('\t');
				writer.append(rs.getString(2));
				writer.append('\t');
				writer.append(rs.getString(24));
				writer.append('\t');
				writer.append(rs.getString(3));
				writer.append('\t');
				writer.append(rs.getString(4));
				writer.append('\t');
				writer.append(rs.getString(5));
				writer.append('\t');
				writer.append(rs.getString(6));
				writer.append('\t');
				writer.append(rs.getString(7));
				writer.append('\t');
				writer.append(rs.getString(8));
				writer.append('\t');
				writer.append(rs.getString(9));
				writer.append('\t');
				Comm=rs.getString(10);
				if(Comm==null){
					Comm="0";
				}
				writer.append(Comm);
				writer.append('\t');
				ServiceCharge=rs.getString(11);
				if(ServiceCharge==null){
					ServiceCharge="0";
				}
				BankCharge=rs.getString(12);
				if(BankCharge==null){
					BankCharge="0";
				}
				OtherCharge=rs.getString(13);
				if(OtherCharge==null){
					OtherCharge="0"; 
				}
				writer.append(ServiceCharge);
				writer.append('\t');
				writer.append(BankCharge);
				writer.append('\t');
				writer.append(OtherCharge);
				writer.append('\t');
				//recent
				writer.append(rs.getString(23));
				writer.append('\t');

				writer.append(rs.getString(14));
				writer.append('\t');
				writer.append(rs.getString(15));
				writer.append('\t');
				writer.append(rs.getString(16));
				writer.append('\t');
				writer.append(rs.getString(17));
				writer.append('\t');
				writer.append(rs.getString(18));
				writer.append('\t');
				writer.append(rs.getString(19));
				writer.append('\t');
				writer.append(rs.getString(20));
				writer.append('\t');
				writer.append(rs.getString(21));
				writer.append('\n');

			}

			writer.flush();
			writer.close();
		} catch (Exception e) {
			Status = "ERROR";
			//System.out.println("Exception in AccountStatementDao,getPortalAccountstmt");
			e.printStackTrace();

		} finally {
			try {
				if(con!=null)con.close();
				if(session!=null)session.close();
			} catch (Exception e) {
				//System.out.println("Exception in AccountStatementDao,getPortalAccountstmt");
				e.printStackTrace();
			}
		}
		////System.out.println("Status is "+Status);
		return Status;
	}

	public String getAPIAccountstmt(String id, String fromDate, String toDate,String filePath) 
	{
		String Status="Norecord";
		Session session = null;
		Query query=null;
		String sqlQuery1="";
		String sqlQuery2="";
		String sqlQuery3="";
		String sqlQuery4="";
		try{
			session = HibernateSession1.getSessionFactory().openSession();
			if(id.equalsIgnoreCase("All"))
			{
				sqlQuery1="select count(Corporate_Agent_Id) from Rech_API_Agent_Transaction_details where  Date_of_Transaction between '"+fromDate+"' and '"+toDate+"'";
			}else
			{
				sqlQuery1="select count(Corporate_Agent_Id)  from Rech_API_Agent_Transaction_details where  Corporate_Agent_Id='"+id+"' and  Date_of_Transaction between '"+fromDate+"' and '"+toDate+"'";
			}
			query=session.createSQLQuery(sqlQuery1);
			int record=Integer.parseInt(query.uniqueResult().toString());
			//System.out.println(record);
			if(record<=0)
			{
				if(id.equalsIgnoreCase("All"))
				{
					sqlQuery1="select count(Corporate_Agent_Id) from Flight_API_Agent_Txn_details where  convert(varchar(10),Time_of_Transaction,120) between '"+fromDate+"' and '"+toDate+"'";
				}else
				{
					sqlQuery1="select count(Corporate_Agent_Id)  from Flight_API_Agent_Txn_details where  Corporate_Agent_Id='"+id+"' and  convert(varchar(10),Time_of_Transaction,120) between '"+fromDate+"' and '"+toDate+"'";
				}
				query=session.createSQLQuery(sqlQuery1);
				record=Integer.parseInt(query.uniqueResult().toString());
				//System.out.println(record);
				if(record<=0)
				{
					if(id.equalsIgnoreCase("All"))
					{
						sqlQuery1="select count(Corporate_Agent_Id) from Hotel_API_Agent_Txn_details where  convert(varchar(10),Time_of_Transaction,120) between '"+fromDate+"' and '"+toDate+"'";
					}else
					{
						sqlQuery1="select count(Corporate_Agent_Id)  from Hotel_API_Agent_Txn_details where  Corporate_Agent_Id='"+id+"' and  convert(varchar(10),Time_of_Transaction,120) between '"+fromDate+"' and '"+toDate+"'";
					}
					query=session.createSQLQuery(sqlQuery1);
					record=Integer.parseInt(query.uniqueResult().toString());
					//System.out.println(record);
					if(record<=0)
					{
						if(id.equalsIgnoreCase("All"))
						{
							sqlQuery1="select count(Client_id) from BUSAPI_Client_Transaction_Details where  convert(varchar(10),Time_of_Transaction,120) between '"+fromDate+"' and '"+toDate+"'";
						}else
						{
							sqlQuery1="select count(Client_id)  from BUSAPI_Client_Transaction_Details where  Client_id='"+id+"' and  convert(varchar(10),Date_of_Transaction,120) between '"+fromDate+"' and '"+toDate+"'";
						}
						query=session.createSQLQuery(sqlQuery1);
						record=Integer.parseInt(query.uniqueResult().toString());
						//System.out.println(record);
						if(record<=0)
						{

							Status="Norecord";
						}
					}
				}
			}
			if(record>=25000)
			{
				Status="MoreRecord";
			}
			if (record>0 && record<25000)
			{
				//System.out.println("true : "+id);

				if(id.equalsIgnoreCase("All"))
				{
					//System.out.println("If block");
					sqlQuery1="select ','+Corporate_Transaction_Id as Corporate_Transaction_Id,Refrence_Id as Refrence_Id,'API'+convert(varchar(10),Corporate_Agent_Id)as APIagentId,Sub_Agent_Id," +
							"convert(varchar(10),Date_of_Transaction,120) as date,convert(varchar, Time_of_transaction, 108) as transactiontime,Service,convert(decimal(18,2),Tran_Amount) as Tran_Amount," +
							"convert(decimal(18,2),Commission) as Commission,convert(decimal(18,2),Service_Charges) as Service_Charges,convert(decimal(18,2),Bank_Charges) as Bank_Charges ," +
							"Other_Charges,convert(decimal(18,2),Net_Tran_Amt) as Net_Tran_Amt,Action_On_Bal_Amt,Previous_Bal_Amt,Updated_Bal_Amt,Tran_Status,Final_Bal_Amt,Tran_IP," +
							"convert(varchar(10),Updated_Date,120) as updatedate,convert(varchar, Updated_Time, 108) as UpdatedTime,Updated_User,Updated_IP,Remark,ID_No from Rech_API_Agent_Transaction_details" +
							" where  Date_of_Transaction between '"+fromDate+"' and '"+toDate+"' order by ID_No";

					sqlQuery2="select ','+Corporate_Transaction_Id as Corporate_Transaction_Id,Partner_Ref_id as Refrence_Id,'API'+convert(varchar(10),Corporate_Agent_Id)as APIagentId," +
							"convert(varchar(10),Time_of_Transaction,120) as date,convert(varchar(10), Time_of_Transaction, 108) as transactiontime,Service,Tran_Amount as Tran_Amount," +
							"Commission as Commission ,Service_Charges as Service_Charges,Net_Tran_Amt as Net_Tran_Amt,Action_On_Bal_Amt,Status as Tran_Status,Bank_Charges,Other_Charges," +
							"Previous_Bal_Amt,Updated_Bal_Amt,Final_Bal_Amt,Book_IP,convert(varchar(10),Updated_Date,120) as updateddate,convert(varchar(10),Updated_Date,108) as updatedtime,Updated_User,Updated_IP,Remark,ID_No from Flight_API_Agent_Txn_details" +
							" where  convert(varchar(10),Time_of_Transaction,120) between '"+fromDate+"' and '"+toDate+"' order by ID_No";

					sqlQuery3="select ','+Corporate_Transaction_Id as Corporate_Transaction_Id,BookingRef as Refrence_Id,'API'+convert(varchar(10),Corporate_Agent_Id)as APIagentId," +
							"convert(varchar(10),Time_of_Transaction,120) as date,convert(varchar(10), Time_of_Transaction, 108) as transactiontime,Service,Tran_Amount as Tran_Amount," +
							"Commission as Commission ,Service_Charges as Service_Charges,Net_Tran_Amt as Net_Tran_Amt,Action_On_Bal_Amt,Status as Tran_Status,Bank_Charges,Other_Charges," +
							"Previous_Bal_Amt,Updated_Bal_Amt,Final_Bal_Amt,Book_IP,convert(varchar(10),Updated_Date,120) as updateddate,convert(varchar(10),Updated_Date,108) as updatedtime,Updated_User,Updated_IP,Remark,ID_No from Hotel_API_Agent_Txn_details" +
							" where convert(varchar(10),Time_of_Transaction,120) between '"+fromDate+"' and '"+toDate+"' order by ID_No";

					sqlQuery4="select ','+Transaction_id as Corporate_Transaction_Id,Ticket_PNR as Refrence_Id,'API'+Client_id as APIagentId,convert(varchar(10),Date_of_Transaction,120) as date," +
							"convert(varchar(10), Date_of_Transaction, 108) as transactiontime,Req_Amount as Tran_Amount,Commission as Commission ,DeductedAmt as Net_Tran_Amt," +
							"Action_on_bal_amt as Action_On_Bal_Amt,Tran_Status,Client_balAmt_b_Ded as Previous_Bal_Amt,Client_balAmt_A_Ded as Updated_Bal_Amt,Client_Final_balAmt as Final_Bal_Amt," +
							"IpAddress as Updated_IP,convert(varchar(10),Updated_date,120) as updateddate,convert(varchar(10),Updated_date,108) as updatedtime,Remark,Id as ID_No" +
							" from BUSAPI_Client_Transaction_Details where  convert(varchar(10),Date_of_Transaction,120) between '"+fromDate+"' and '"+toDate+"' order by Id";

				}
				else
				{
					//System.out.println("Else block");
					sqlQuery1="select ','+Corporate_Transaction_Id as Corporate_Transaction_Id,+Refrence_Id as Refrence_Id,'API'+convert(varchar(10),Corporate_Agent_Id)as APIagentId," +
							"Sub_Agent_Id,convert(varchar(10),Date_of_Transaction,120) as date,convert(varchar, Time_of_transaction, 108) as transactiontime,Service,Tran_Amount," +
							"Commission,Service_Charges,Bank_Charges,Other_Charges,Net_Tran_Amt,Action_On_Bal_Amt,Previous_Bal_Amt,Updated_Bal_Amt,Tran_Status,Final_Bal_Amt,Tran_IP," +
							"convert(varchar(10),Updated_Date,120) as updatedate,convert(varchar, Updated_Time, 108) as UpdatedTime,Updated_User,Updated_IP,Remark,ID_No from Rech_API_Agent_Transaction_details " +
							"where Corporate_Agent_Id='"+id+"' and  Date_of_Transaction between '"+fromDate+"' and '"+toDate+"'  order by ID_No";

					sqlQuery2="select ','+Corporate_Transaction_Id as Corporate_Transaction_Id,Partner_Ref_id as Refrence_Id,'API'+convert(varchar(10),Corporate_Agent_Id)as APIagentId," +
							"convert(varchar(10),Time_of_Transaction,120) as date,convert(varchar(10), Time_of_Transaction, 108) as transactiontime,Service,Tran_Amount as Tran_Amount," +
							"Commission as Commission ,Service_Charges as Service_Charges,Net_Tran_Amt as Net_Tran_Amt,Action_On_Bal_Amt,Status as Tran_Status,Bank_Charges,Other_Charges," +
							"Previous_Bal_Amt,Updated_Bal_Amt,Final_Bal_Amt,Book_IP,convert(varchar(10),Updated_Date,120) as updateddate,convert(varchar(10),Updated_Date,108) as updatedtime,Updated_User,Updated_IP,Remark,ID_No from Flight_API_Agent_Txn_details" +
							" where Corporate_Agent_Id='"+id+"' and convert(varchar(10),Time_of_Transaction,120) between '"+fromDate+"' and '"+toDate+"' order by ID_No";

					sqlQuery3="select ','+Corporate_Transaction_Id as Corporate_Transaction_Id,BookingRef as Refrence_Id,'API'+convert(varchar(10),Corporate_Agent_Id)as APIagentId," +
							"convert(varchar(10),Time_of_Transaction,120) as date,convert(varchar(10), Time_of_Transaction, 108) as transactiontime,Service,Tran_Amount as Tran_Amount," +
							"Commission as Commission ,Service_Charges as Service_Charges,Net_Tran_Amt as Net_Tran_Amt,Action_On_Bal_Amt,Status as Tran_Status,Bank_Charges,Other_Charges," +
							"Previous_Bal_Amt,Updated_Bal_Amt,Final_Bal_Amt,Book_IP,convert(varchar(10),Updated_Date,120) as updateddate,convert(varchar(10),Updated_Date,108) as updatedtime,Updated_User,Updated_IP,Remark,ID_No from Hotel_API_Agent_Txn_details" +
							" where Corporate_Agent_Id='"+id+"' and convert(varchar(10),Time_of_Transaction,120) between '"+fromDate+"' and '"+toDate+"' order by ID_No";

					sqlQuery4="select ','+Transaction_id as Corporate_Transaction_Id,Ticket_PNR as Refrence_Id,'API'+Client_id as APIagentId,convert(varchar(10),Date_of_Transaction,120) as date," +
							"convert(varchar(10), Date_of_Transaction, 108) as transactiontime,Req_Amount as Tran_Amount,Commission as Commission ,DeductedAmt as Net_Tran_Amt," +
							"Action_on_bal_amt as Action_On_Bal_Amt,Tran_Status,Client_balAmt_b_Ded as Previous_Bal_Amt,Client_balAmt_A_Ded as Updated_Bal_Amt,Client_Final_balAmt as Final_Bal_Amt," +
							"IpAddress as Updated_IP,convert(varchar(10),Updated_date,120) as updateddate,convert(varchar(10),Updated_date,108) as updatedtime,Remark,Id as ID_No" +
							" from BUSAPI_Client_Transaction_Details where Client_id='"+id+"' and convert(varchar(10),Date_of_Transaction,120) between '"+fromDate+"' and '"+toDate+"' order by Id";

				}

				FileWriter writer = new FileWriter(filePath);

				writer.append("ID");
				writer.append('\t');
				writer.append("Transaction ID");
				writer.append('\t');
				writer.append("Reference ID");
				writer.append('\t');
				writer.append("Agent ID");
				writer.append('\t');
				writer.append("Sub Agent Id");
				writer.append('\t');
				writer.append("Date of transaction");
				writer.append('\t');
				writer.append("Time of Transaction");
				writer.append('\t');
				writer.append("Service");
				writer.append('\t');
				writer.append("Transaction Amount");
				writer.append('\t');
				writer.append("Commission");
				writer.append('\t');
				/*writer.append("Service charge");
				writer.append('\t');
				writer.append("Bank Charges");
				writer.append('\t');
				writer.append("Other Charges");
				writer.append('\t');*/
				writer.append("Net Transaction Amount");
				writer.append('\t');
				writer.append("Action on Balance Amount");
				writer.append('\t');
				writer.append("Previous Balance Amount");
				writer.append('\t');
				writer.append("Updated Balance Amount");
				writer.append('\t');
				writer.append("Transaction Status");
				writer.append('\t');
				writer.append("Final Balance");
				writer.append('\t');
				/*writer.append("Transaction IP");
				writer.append('\t');
				writer.append("Update Date");
				writer.append('\t');
				writer.append("Update Time");
				writer.append('\t');
				writer.append("Update User");
				writer.append('\t');
				writer.append("Update IP");
				writer.append('\t');*/
				writer.append("Remark");
				writer.append('\n');

				for(int i=1;i<=4;i++)
				{

					if(i==1)
						query=session.createSQLQuery(sqlQuery1);
					if(i==2)
						query=session.createSQLQuery(sqlQuery2);
					if(i==3)
						query=session.createSQLQuery(sqlQuery3);	
					if(i==4)
						query=session.createSQLQuery(sqlQuery4);

					List list=query.list();
					Iterator itr=list.iterator();

					while(itr.hasNext())
					{
						////System.out.println("Writing : "+i);
						Object[] row=(Object[])itr.next();
						if(i==1)
						{
							writer.append(row[24]==null ?"NA":row[24].toString());
							writer.append('\t');
							writer.append(row[0]==null ?"NA":row[0].toString());
							writer.append('\t');
							writer.append(row[1]==null ?"NA":row[1].toString());
							writer.append('\t');
							writer.append(row[2]==null ?"NA":row[2].toString());
							writer.append('\t');
							writer.append(row[3]==null ?"NA":row[3].toString());
							writer.append('\t');
							writer.append(row[4]==null ?"NA":row[4].toString());
							writer.append('\t');
							writer.append(row[5]==null ?"NA":row[5].toString());
							writer.append('\t');
							writer.append(row[6]==null ?"NA":row[6].toString());
							writer.append('\t');
							writer.append(row[7]==null ?"NA":row[7].toString());
							writer.append('\t');
							writer.append(row[8]==null ?"NA":row[8].toString());
							writer.append('\t');
							/*writer.append(row[9]==null ?"NA":row[9].toString());
							writer.append('\t');
							writer.append(row[10]==null ?"NA":row[10].toString());
							writer.append('\t');
							writer.append(row[11]==null ?"NA":row[11].toString());
							writer.append('\t');*/
							writer.append(row[12]==null ?"NA":row[12].toString());
							writer.append('\t');
							writer.append(row[13]==null ?"NA":row[13].toString());
							writer.append('\t');
							writer.append(row[14]==null ?"NA":row[14].toString());
							writer.append('\t');
							writer.append(row[15]==null ?"NA":row[15].toString());
							writer.append('\t');
							writer.append(row[16]==null ?"NA":row[16].toString());
							writer.append('\t');
							writer.append(row[17]==null ?"NA":row[17].toString());
							writer.append('\t');
							/*writer.append(row[18]==null ?"NA":row[18].toString());
							writer.append('\t');
							writer.append(row[19]==null ?"NA":row[19].toString());
							writer.append('\t');
							writer.append(row[20]==null ?"NA":row[20].toString());
							writer.append('\t');
							writer.append(row[21]==null ?"NA":row[21].toString());
							writer.append('\t');
							writer.append(row[22]==null ?"NA":row[22].toString());
							writer.append('\t');*/
							writer.append(row[23]==null ?"NA":row[23].toString());
							writer.append('\n');
						}
						if(i==2)
						{
							writer.append(row[23]==null ?"NA":row[23].toString());
							writer.append('\t');
							writer.append(row[0]==null ?"NA":row[0].toString());
							writer.append('\t');
							writer.append(row[1]==null ?"NA":row[1].toString());
							writer.append('\t');
							writer.append(row[2]==null ?"NA":row[2].toString());
							writer.append('\t');
							writer.append("NA");
							writer.append('\t');
							writer.append(row[3]==null ?"NA":row[3].toString());
							writer.append('\t');
							writer.append(row[4]==null ?"NA":row[4].toString());
							writer.append('\t');
							writer.append("Flight");
							writer.append('\t');
							writer.append(row[6]==null ?"NA":row[6].toString());
							writer.append('\t');
							writer.append(row[7]==null ?"NA":row[7].toString());
							writer.append('\t');
							/*writer.append(row[8]==null ?"NA":row[8].toString());
							writer.append('\t');
							writer.append(row[12]==null ?"NA":row[12].toString());
							writer.append('\t');
							writer.append(row[13]==null ?"NA":row[13].toString());
							writer.append('\t');*/
							writer.append(row[9]==null ?"NA":row[9].toString());
							writer.append('\t');
							writer.append(row[10]==null ?"NA":row[10].toString());
							writer.append('\t');
							writer.append(row[14]==null ?"NA":row[14].toString());
							writer.append('\t');
							writer.append(row[15]==null ?"NA":row[15].toString());
							writer.append('\t');
							writer.append(row[11]==null ?"NA":row[11].toString());
							writer.append('\t');
							writer.append(row[16]==null ?"NA":row[16].toString());
							writer.append('\t');
							/*writer.append(row[17]==null ?"NA":row[17].toString());
							writer.append('\t');
							writer.append(row[18]==null ?"NA":row[18].toString());
							writer.append('\t');
							writer.append(row[19]==null ?"NA":row[19].toString());
							writer.append('\t');
							writer.append(row[20]==null ?"NA":row[20].toString());
							writer.append('\t');
							writer.append(row[21]==null ?"NA":row[21].toString());
							writer.append('\t');*/
							writer.append(row[22]==null ?"NA":row[22].toString());
							writer.append('\n');
						}
						if(i==3)
						{
							writer.append(row[23]==null ?"NA":row[23].toString());
							writer.append('\t');
							writer.append(row[0]==null ?"NA":row[0].toString());
							writer.append('\t');
							writer.append(row[1]==null ?"NA":row[1].toString());
							writer.append('\t');
							writer.append(row[2]==null ?"NA":row[2].toString());
							writer.append('\t');
							writer.append("NA");
							writer.append('\t');
							writer.append(row[3]==null ?"NA":row[3].toString());
							writer.append('\t');
							writer.append(row[4]==null ?"NA":row[4].toString());
							writer.append('\t');
							writer.append("Hotel");
							writer.append('\t');
							writer.append(row[6]==null ?"NA":row[6].toString());
							writer.append('\t');
							writer.append(row[7]==null ?"NA":row[7].toString());
							writer.append('\t');
							/*writer.append(row[8]==null ?"NA":row[8].toString());
							writer.append('\t');
							writer.append(row[12]==null ?"NA":row[12].toString());
							writer.append('\t');
							writer.append(row[13]==null ?"NA":row[13].toString());
							writer.append('\t');*/
							writer.append(row[9]==null ?"NA":row[9].toString());
							writer.append('\t');
							writer.append(row[10]==null ?"NA":row[10].toString());
							writer.append('\t');
							writer.append(row[14]==null ?"NA":row[14].toString());
							writer.append('\t');
							writer.append(row[15]==null ?"NA":row[15].toString());
							writer.append('\t');
							writer.append(row[11]==null ?"NA":row[11].toString());
							writer.append('\t');
							writer.append(row[16]==null ?"NA":row[16].toString());
							writer.append('\t');
							/*writer.append(row[17]==null ?"NA":row[17].toString());
							writer.append('\t');
							writer.append(row[18]==null ?"NA":row[18].toString());
							writer.append('\t');
							writer.append(row[19]==null ?"NA":row[19].toString());
							writer.append('\t');
							writer.append(row[20]==null ?"NA":row[20].toString());
							writer.append('\t');
							writer.append(row[21]==null ?"NA":row[21].toString());
							writer.append('\t');*/
							writer.append(row[22]==null ?"NA":row[22].toString());
							writer.append('\n');
						}
						if(i==4)
						{
							writer.append(row[17]==null ?"NA":row[17].toString());
							writer.append('\t');
							writer.append(row[0]==null ?"NA":row[0].toString());
							writer.append('\t');
							writer.append(row[1]==null ?"NA":row[1].toString());
							writer.append('\t');
							writer.append(row[2]==null ?"NA":row[2].toString());
							writer.append('\t');
							writer.append("NA");
							writer.append('\t');
							writer.append(row[3]==null ?"NA":row[3].toString());
							writer.append('\t');
							writer.append(row[4]==null ?"NA":row[4].toString());
							writer.append('\t');
							writer.append("Bus");
							writer.append('\t');
							writer.append(row[6]==null ?"NA":row[5].toString());
							writer.append('\t');
							writer.append(row[7]==null ?"NA":row[6].toString());
							writer.append('\t');
							/*writer.append("NA");
							writer.append('\t');
							writer.append("NA");
							writer.append('\t');
							writer.append("NA");
							writer.append('\t');*/
							writer.append(row[7]==null ?"NA":row[7].toString());
							writer.append('\t');
							writer.append(row[8]==null ?"NA":row[8].toString());
							writer.append('\t');
							writer.append(row[10]==null ?"NA":row[10].toString());
							writer.append('\t');
							writer.append(row[11]==null ?"NA":row[11].toString());
							writer.append('\t');
							writer.append(row[9]==null ?"NA":row[9].toString());
							writer.append('\t');
							writer.append(row[12]==null ?"NA":row[12].toString());
							writer.append('\t');
							/*writer.append(row[13]==null ?"NA":row[13].toString());
							writer.append('\t');
							writer.append(row[14]==null ?"NA":row[14].toString());
							writer.append('\t');
							writer.append(row[15]==null ?"NA":row[15].toString());
							writer.append('\t');
							writer.append("NA");
							writer.append('\t');
							writer.append("NA");
							writer.append('\t');*/
							writer.append(row[16]==null ?"NA":row[16].toString());
							writer.append('\n');
						}

					}
				}
				writer.flush();
				writer.close();
				Status="Success";
			}
		}
		catch(Exception e)
		{
			Status="ERROR";
			//System.out.println("Exception in AccountStatementDao,getAPIAccountstmt");
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(session!=null)session.close();
			}
			catch (Exception e) 
			{
				//System.out.println("Exception in AccountStatementDao,getPortalAccountstmt");
				e.printStackTrace();
			}
		}
		return Status;
	}

	public String getAPIAccountstmtDetails(String id, String fromDate, String toDate,String filePath) 
	{
		String Status="";
		Session session = null;
		Query query=null;
		String sqlQuery1="";
		String sqlQuery2="";
		String sqlQuery3="";
		String sqlQuery4="";
		try{
			session = HibernateSession1.getSessionFactory().openSession();
			if(id.equalsIgnoreCase("All"))
			{
				sqlQuery1="select count(Corporate_Agent_Id) from Rech_API_Agent_Transaction_details where  Date_of_Transaction between '"+fromDate+"' and '"+toDate+"'";
			}else
			{
				sqlQuery1="select count(Corporate_Agent_Id)  from Rech_API_Agent_Transaction_details where  Corporate_Agent_Id='"+id+"' and  Date_of_Transaction between '"+fromDate+"' and '"+toDate+"'";
			}
			query=session.createSQLQuery(sqlQuery1);
			int record=Integer.parseInt(query.uniqueResult().toString());
			//System.out.println(record);
			if(record<=0)
			{
				if(id.equalsIgnoreCase("All"))
				{
					sqlQuery1="select count(Corporate_Agent_Id) from Flight_API_Agent_Txn_details where  convert(varchar(10),Time_of_Transaction,120) between '"+fromDate+"' and '"+toDate+"'";
				}
				else
				{
					sqlQuery1="select count(Corporate_Agent_Id)  from Flight_API_Agent_Txn_details where  Corporate_Agent_Id='"+id+"' and  convert(varchar(10),Time_of_Transaction,120) between '"+fromDate+"' and '"+toDate+"'";
				}
				query=session.createSQLQuery(sqlQuery1);
				record=Integer.parseInt(query.uniqueResult().toString());
				//System.out.println(record);
				if(record<=0)
				{
					if(id.equalsIgnoreCase("All"))
					{
						sqlQuery1="select count(Corporate_Agent_Id) from Hotel_API_Agent_Txn_details where  convert(varchar(10),Time_of_Transaction,120) between '"+fromDate+"' and '"+toDate+"'";
					}else
					{
						sqlQuery1="select count(Corporate_Agent_Id)  from Hotel_API_Agent_Txn_details where  Corporate_Agent_Id='"+id+"' and  convert(varchar(10),Time_of_Transaction,120) between '"+fromDate+"' and '"+toDate+"'";
					}
					query=session.createSQLQuery(sqlQuery1);
					record=Integer.parseInt(query.uniqueResult().toString());
					//System.out.println(record);
					if(record<=0)
					{
						if(id.equalsIgnoreCase("All"))
						{
							sqlQuery1="select count(Client_id) from BUSAPI_Client_Transaction_Details where  convert(varchar(10),Time_of_Transaction,120) between '"+fromDate+"' and '"+toDate+"'";
						}else
						{
							sqlQuery1="select count(Client_id)  from BUSAPI_Client_Transaction_Details where  Client_id='"+id+"' and  convert(varchar(10),Date_of_Transaction,120) between '"+fromDate+"' and '"+toDate+"'";
						}
						query=session.createSQLQuery(sqlQuery1);
						record=Integer.parseInt(query.uniqueResult().toString());
						//System.out.println(record);
						if(record<=0)
						{

							Status="Norecord";
						}
					}
				}
			}
			if(record>=25000)
			{
				Status="MoreRecord";
			}
			if (record>0 && record<25000)
			{
				//System.out.println("true : "+id);

				if(id.equalsIgnoreCase("All"))
				{
					//System.out.println("If block");
					sqlQuery1="select Corporate_Transaction_Id as Corporate_Transaction_Id,Refrence_Id as Refrence_Id,'API'+convert(varchar(10),Corporate_Agent_Id)as APIagentId,Sub_Agent_Id," +
							"convert(varchar(10),Date_of_Transaction,120) as date,convert(varchar, Time_of_transaction, 108) as transactiontime,Service,convert(decimal(18,2),Tran_Amount) as Tran_Amount," +
							"convert(decimal(18,2),Commission) as Commission,convert(decimal(18,2),Service_Charges) as Service_Charges,convert(decimal(18,2),Bank_Charges) as Bank_Charges ," +
							"Other_Charges,convert(decimal(18,2),Net_Tran_Amt) as Net_Tran_Amt,Action_On_Bal_Amt,Previous_Bal_Amt,Updated_Bal_Amt,Tran_Status,Final_Bal_Amt,Tran_IP," +
							"convert(varchar(10),Updated_Date,120) as updatedate,convert(varchar, Updated_Time, 108) as UpdatedTime,Updated_User,Updated_IP,Remark,ID_No from Rech_API_Agent_Transaction_details" +
							" where  Date_of_Transaction between '"+fromDate+"' and '"+toDate+"' order by ID_No";

					sqlQuery2="select Corporate_Transaction_Id as Corporate_Transaction_Id,Partner_Ref_id as Refrence_Id,'API'+convert(varchar(10),Corporate_Agent_Id)as APIagentId," +
							"convert(varchar(10),Time_of_Transaction,120) as date,convert(varchar(10), Time_of_Transaction, 108) as transactiontime,Service,Tran_Amount as Tran_Amount," +
							"Commission as Commission ,Service_Charges as Service_Charges,Net_Tran_Amt as Net_Tran_Amt,Action_On_Bal_Amt,Status as Tran_Status,Bank_Charges,Other_Charges," +
							"Previous_Bal_Amt,Updated_Bal_Amt,Final_Bal_Amt,Book_IP,convert(varchar(10),Updated_Date,120) as updateddate,convert(varchar(10),Updated_Date,108) as updatedtime,Updated_User,Updated_IP,Remark,ID_No from Flight_API_Agent_Txn_details" +
							" where  convert(varchar(10),Time_of_Transaction,120) between '"+fromDate+"' and '"+toDate+"' order by ID_No";

					sqlQuery3="select Corporate_Transaction_Id as Corporate_Transaction_Id,BookingRef as Refrence_Id,'API'+convert(varchar(10),Corporate_Agent_Id)as APIagentId," +
							"convert(varchar(10),Time_of_Transaction,120) as date,convert(varchar(10), Time_of_Transaction, 108) as transactiontime,Service,Tran_Amount as Tran_Amount," +
							"Commission as Commission ,Service_Charges as Service_Charges,Net_Tran_Amt as Net_Tran_Amt,Action_On_Bal_Amt,Status as Tran_Status,Bank_Charges,Other_Charges," +
							"Previous_Bal_Amt,Updated_Bal_Amt,Final_Bal_Amt,Book_IP,convert(varchar(10),Updated_Date,120) as updateddate,convert(varchar(10),Updated_Date,108) as updatedtime,Updated_User,Updated_IP,Remark,ID_No from Hotel_API_Agent_Txn_details" +
							" where convert(varchar(10),Time_of_Transaction,120) between '"+fromDate+"' and '"+toDate+"' order by ID_No";

					sqlQuery4="select Transaction_id as Corporate_Transaction_Id,Ticket_PNR as Refrence_Id,'API'+Client_id as APIagentId,convert(varchar(10),Date_of_Transaction,120) as date," +
							"convert(varchar(10), Date_of_Transaction, 108) as transactiontime,Req_Amount as Tran_Amount,Commission as Commission ,DeductedAmt as Net_Tran_Amt," +
							"Action_on_bal_amt as Action_On_Bal_Amt,Tran_Status,Client_balAmt_b_Ded as Previous_Bal_Amt,Client_balAmt_A_Ded as Updated_Bal_Amt,Client_Final_balAmt as Final_Bal_Amt," +
							"IpAddress as Updated_IP,convert(varchar(10),Updated_date,120) as updateddate,convert(varchar(10),Updated_date,108) as updatedtime,Remark,Id as ID_No" +
							" from BUSAPI_Client_Transaction_Details where  convert(varchar(10),Date_of_Transaction,120) between '"+fromDate+"' and '"+toDate+"' order by Id";

				}
				else
				{
					//System.out.println("Else block");
					sqlQuery1="select Corporate_Transaction_Id as Corporate_Transaction_Id,+Refrence_Id as Refrence_Id,'API'+convert(varchar(10),Corporate_Agent_Id)as APIagentId," +
							"Sub_Agent_Id,convert(varchar(10),Date_of_Transaction,120) as date,convert(varchar, Time_of_transaction, 108) as transactiontime,Service,Tran_Amount," +
							"Commission,Service_Charges,Bank_Charges,Other_Charges,Net_Tran_Amt,Action_On_Bal_Amt,Previous_Bal_Amt,Updated_Bal_Amt,Tran_Status,Final_Bal_Amt,Tran_IP," +
							"convert(varchar(10),Updated_Date,120) as updatedate,convert(varchar, Updated_Time, 108) as UpdatedTime,Updated_User,Updated_IP,Remark,ID_No from Rech_API_Agent_Transaction_details " +
							"where Corporate_Agent_Id='"+id+"' and  Date_of_Transaction between '"+fromDate+"' and '"+toDate+"'  order by ID_No";

					sqlQuery2="select Corporate_Transaction_Id as Corporate_Transaction_Id,Partner_Ref_id as Refrence_Id,'API'+convert(varchar(10),Corporate_Agent_Id)as APIagentId," +
							"convert(varchar(10),Time_of_Transaction,120) as date,convert(varchar(10), Time_of_Transaction, 108) as transactiontime,Service,Tran_Amount as Tran_Amount," +
							"Commission as Commission ,Service_Charges as Service_Charges,Net_Tran_Amt as Net_Tran_Amt,Action_On_Bal_Amt,Status as Tran_Status,Bank_Charges,Other_Charges," +
							"Previous_Bal_Amt,Updated_Bal_Amt,Final_Bal_Amt,Book_IP,convert(varchar(10),Updated_Date,120) as updateddate,convert(varchar(10),Updated_Date,108) as updatedtime,Updated_User,Updated_IP,Remark,ID_No from Flight_API_Agent_Txn_details" +
							" where Corporate_Agent_Id='"+id+"' and convert(varchar(10),Time_of_Transaction,120) between '"+fromDate+"' and '"+toDate+"' order by ID_No";

					sqlQuery3="select Corporate_Transaction_Id as Corporate_Transaction_Id,BookingRef as Refrence_Id,'API'+convert(varchar(10),Corporate_Agent_Id)as APIagentId," +
							"convert(varchar(10),Time_of_Transaction,120) as date,convert(varchar(10), Time_of_Transaction, 108) as transactiontime,Service,Tran_Amount as Tran_Amount," +
							"Commission as Commission ,Service_Charges as Service_Charges,Net_Tran_Amt as Net_Tran_Amt,Action_On_Bal_Amt,Status as Tran_Status,Bank_Charges,Other_Charges," +
							"Previous_Bal_Amt,Updated_Bal_Amt,Final_Bal_Amt,Book_IP,convert(varchar(10),Updated_Date,120) as updateddate,convert(varchar(10),Updated_Date,108) as updatedtime,Updated_User,Updated_IP,Remark,ID_No from Hotel_API_Agent_Txn_details" +
							" where Corporate_Agent_Id='"+id+"' and convert(varchar(10),Time_of_Transaction,120) between '"+fromDate+"' and '"+toDate+"' order by ID_No";

					sqlQuery4="select Transaction_id as Corporate_Transaction_Id,Ticket_PNR as Refrence_Id,'API'+Client_id as APIagentId,convert(varchar(10),Date_of_Transaction,120) as date," +
							"convert(varchar(10), Date_of_Transaction, 108) as transactiontime,Req_Amount as Tran_Amount,Commission as Commission ,DeductedAmt as Net_Tran_Amt," +
							"Action_on_bal_amt as Action_On_Bal_Amt,Tran_Status,Client_balAmt_b_Ded as Previous_Bal_Amt,Client_balAmt_A_Ded as Updated_Bal_Amt,Client_Final_balAmt as Final_Bal_Amt," +
							"IpAddress as Updated_IP,convert(varchar(10),Updated_date,120) as updateddate,convert(varchar(10),Updated_date,108) as updatedtime,Remark,Id as ID_No" +
							" from BUSAPI_Client_Transaction_Details where Client_id='"+id+"' and convert(varchar(10),Date_of_Transaction,120) between '"+fromDate+"' and '"+toDate+"' order by Id";

				}

				FileWriter writer = new FileWriter(filePath);

				writer.append("ID");
				writer.append('\t');
				writer.append("Transaction ID");
				writer.append('\t');
				writer.append("Reference ID");
				writer.append('\t');
				writer.append("Agent ID");
				writer.append('\t');
				writer.append("Sub Agent Id");
				writer.append('\t');
				writer.append("Date of transaction");
				writer.append('\t');
				writer.append("Time of Transaction");
				writer.append('\t');
				writer.append("Service");
				writer.append('\t');
				writer.append("Transaction Amount");
				writer.append('\t');
				writer.append("Commission");
				writer.append('\t');
				writer.append("Service charge");
				writer.append('\t');
				writer.append("Bank Charges");
				writer.append('\t');
				writer.append("Other Charges");
				writer.append('\t');
				writer.append("Net Transaction Amount");
				writer.append('\t');
				writer.append("Action on Balance Amount");
				writer.append('\t');
				writer.append("Previous Balance Amount");
				writer.append('\t');
				writer.append("Updated Balance Amount");
				writer.append('\t');
				writer.append("Transaction Status");
				writer.append('\t');
				writer.append("Final Balance");
				writer.append('\t');
				writer.append("Transaction IP");
				writer.append('\t');
				writer.append("Update Date");
				writer.append('\t');
				writer.append("Update Time");
				writer.append('\t');
				writer.append("Update User");
				writer.append('\t');
				writer.append("Update IP");
				writer.append('\t');
				writer.append("Remark");
				writer.append('\n');

				for(int i=1;i<=4;i++)
				{

					if(i==1)
						query=session.createSQLQuery(sqlQuery1);
					if(i==2)
						query=session.createSQLQuery(sqlQuery2);
					if(i==3)
						query=session.createSQLQuery(sqlQuery3);	
					if(i==4)
						query=session.createSQLQuery(sqlQuery4);

					List list=query.list();
					Iterator itr=list.iterator();

					while(itr.hasNext())
					{
						////System.out.println("Writing : "+i);
						Object[] row=(Object[])itr.next();
						if(i==1)
						{
							writer.append(row[24]==null ?"NA":row[24].toString());
							writer.append('\t');
							writer.append(row[0]==null ?"NA":row[0].toString());
							writer.append('\t');
							writer.append(row[1]==null ?"NA":row[1].toString());
							writer.append('\t');
							writer.append(row[2]==null ?"NA":row[2].toString());
							writer.append('\t');
							writer.append(row[3]==null ?"NA":row[3].toString());
							writer.append('\t');
							writer.append(row[4]==null ?"NA":row[4].toString());
							writer.append('\t');
							writer.append(row[5]==null ?"NA":row[5].toString());
							writer.append('\t');
							writer.append(row[6]==null ?"NA":row[6].toString());
							writer.append('\t');
							writer.append(row[7]==null ?"NA":row[7].toString());
							writer.append('\t');
							writer.append(row[8]==null ?"NA":row[8].toString());
							writer.append('\t');
							writer.append(row[9]==null ?"NA":row[9].toString());
							writer.append('\t');
							writer.append(row[10]==null ?"NA":row[10].toString());
							writer.append('\t');
							writer.append(row[11]==null ?"NA":row[11].toString());
							writer.append('\t');
							writer.append(row[12]==null ?"NA":row[12].toString());
							writer.append('\t');
							writer.append(row[13]==null ?"NA":row[13].toString());
							writer.append('\t');
							writer.append(row[14]==null ?"NA":row[14].toString());
							writer.append('\t');
							writer.append(row[15]==null ?"NA":row[15].toString());
							writer.append('\t');
							writer.append(row[16]==null ?"NA":row[16].toString());
							writer.append('\t');
							writer.append(row[17]==null ?"NA":row[17].toString());
							writer.append('\t');
							writer.append(row[18]==null ?"NA":row[18].toString());
							writer.append('\t');
							writer.append(row[19]==null ?"NA":row[19].toString());
							writer.append('\t');
							writer.append(row[20]==null ?"NA":row[20].toString());
							writer.append('\t');
							writer.append(row[21]==null ?"NA":row[21].toString());
							writer.append('\t');
							writer.append(row[22]==null ?"NA":row[22].toString());
							writer.append('\t');
							writer.append(row[23]==null ?"NA":row[23].toString());
							writer.append('\n');
						}
						if(i==2)
						{
							writer.append(row[23]==null ?"NA":row[23].toString());
							writer.append('\t');
							writer.append(row[0]==null ?"NA":row[0].toString());
							writer.append('\t');
							writer.append(row[1]==null ?"NA":row[1].toString());
							writer.append('\t');
							writer.append(row[2]==null ?"NA":row[2].toString());
							writer.append('\t');
							writer.append("NA");
							writer.append('\t');
							writer.append(row[3]==null ?"NA":row[3].toString());
							writer.append('\t');
							writer.append(row[4]==null ?"NA":row[4].toString());
							writer.append('\t');
							writer.append("Flight");
							writer.append('\t');
							writer.append(row[6]==null ?"NA":row[6].toString());
							writer.append('\t');
							writer.append(row[7]==null ?"NA":row[7].toString());
							writer.append('\t');
							writer.append(row[8]==null ?"NA":row[8].toString());
							writer.append('\t');
							writer.append(row[12]==null ?"NA":row[12].toString());
							writer.append('\t');
							writer.append(row[13]==null ?"NA":row[13].toString());
							writer.append('\t');
							writer.append(row[9]==null ?"NA":row[9].toString());
							writer.append('\t');
							writer.append(row[10]==null ?"NA":row[10].toString());
							writer.append('\t');
							writer.append(row[14]==null ?"NA":row[14].toString());
							writer.append('\t');
							writer.append(row[15]==null ?"NA":row[15].toString());
							writer.append('\t');
							writer.append(row[11]==null ?"NA":row[11].toString());
							writer.append('\t');
							writer.append(row[16]==null ?"NA":row[16].toString());
							writer.append('\t');
							writer.append(row[17]==null ?"NA":row[17].toString());
							writer.append('\t');
							writer.append(row[18]==null ?"NA":row[18].toString());
							writer.append('\t');
							writer.append(row[19]==null ?"NA":row[19].toString());
							writer.append('\t');
							writer.append(row[20]==null ?"NA":row[20].toString());
							writer.append('\t');
							writer.append(row[21]==null ?"NA":row[21].toString());
							writer.append('\t');
							writer.append(row[22]==null ?"NA":row[22].toString());
							writer.append('\n');
						}
						if(i==3)
						{
							writer.append(row[23]==null ?"NA":row[23].toString());
							writer.append('\t');
							writer.append(row[0]==null ?"NA":row[0].toString());
							writer.append('\t');
							writer.append(row[1]==null ?"NA":row[1].toString());
							writer.append('\t');
							writer.append(row[2]==null ?"NA":row[2].toString());
							writer.append('\t');
							writer.append("NA");
							writer.append('\t');
							writer.append(row[3]==null ?"NA":row[3].toString());
							writer.append('\t');
							writer.append(row[4]==null ?"NA":row[4].toString());
							writer.append('\t');
							writer.append("Hotel");
							writer.append('\t');
							writer.append(row[6]==null ?"NA":row[6].toString());
							writer.append('\t');
							writer.append(row[7]==null ?"NA":row[7].toString());
							writer.append('\t');
							writer.append(row[8]==null ?"NA":row[8].toString());
							writer.append('\t');
							writer.append(row[12]==null ?"NA":row[12].toString());
							writer.append('\t');
							writer.append(row[13]==null ?"NA":row[13].toString());
							writer.append('\t');
							writer.append(row[9]==null ?"NA":row[9].toString());
							writer.append('\t');
							writer.append(row[10]==null ?"NA":row[10].toString());
							writer.append('\t');
							writer.append(row[14]==null ?"NA":row[14].toString());
							writer.append('\t');
							writer.append(row[15]==null ?"NA":row[15].toString());
							writer.append('\t');
							writer.append(row[11]==null ?"NA":row[11].toString());
							writer.append('\t');
							writer.append(row[16]==null ?"NA":row[16].toString());
							writer.append('\t');
							writer.append(row[17]==null ?"NA":row[17].toString());
							writer.append('\t');
							writer.append(row[18]==null ?"NA":row[18].toString());
							writer.append('\t');
							writer.append(row[19]==null ?"NA":row[19].toString());
							writer.append('\t');
							writer.append(row[20]==null ?"NA":row[20].toString());
							writer.append('\t');
							writer.append(row[21]==null ?"NA":row[21].toString());
							writer.append('\t');
							writer.append(row[22]==null ?"NA":row[22].toString());
							writer.append('\n');
						}
						if(i==4)
						{
							writer.append(row[17]==null ?"NA":row[17].toString());
							writer.append('\t');
							writer.append(row[0]==null ?"NA":row[0].toString());
							writer.append('\t');
							writer.append(row[1]==null ?"NA":row[1].toString());
							writer.append('\t');
							writer.append(row[2]==null ?"NA":row[2].toString());
							writer.append('\t');
							writer.append("NA");
							writer.append('\t');
							writer.append(row[3]==null ?"NA":row[3].toString());
							writer.append('\t');
							writer.append(row[4]==null ?"NA":row[4].toString());
							writer.append('\t');
							writer.append("Bus");
							writer.append('\t');
							writer.append(row[6]==null ?"NA":row[5].toString());
							writer.append('\t');
							writer.append(row[7]==null ?"NA":row[6].toString());
							writer.append('\t');
							writer.append("NA");
							writer.append('\t');
							writer.append("NA");
							writer.append('\t');
							writer.append("NA");
							writer.append('\t');
							writer.append(row[7]==null ?"NA":row[7].toString());
							writer.append('\t');
							writer.append(row[8]==null ?"NA":row[8].toString());
							writer.append('\t');
							writer.append(row[10]==null ?"NA":row[10].toString());
							writer.append('\t');
							writer.append(row[11]==null ?"NA":row[11].toString());
							writer.append('\t');
							writer.append(row[9]==null ?"NA":row[9].toString());
							writer.append('\t');
							writer.append(row[12]==null ?"NA":row[12].toString());
							writer.append('\t');
							writer.append(row[13]==null ?"NA":row[13].toString());
							writer.append('\t');
							writer.append(row[14]==null ?"NA":row[14].toString());
							writer.append('\t');
							writer.append(row[15]==null ?"NA":row[15].toString());
							writer.append('\t');
							writer.append("NA");
							writer.append('\t');
							writer.append("NA");
							writer.append('\t');
							writer.append(row[16]==null ?"NA":row[16].toString());
							writer.append('\n');
						}

					}
				}
				writer.flush();
				writer.close();
				Status="Success";
			}
		}
		catch(Exception e)
		{
			Status="ERROR";
			//System.out.println("Exception in AccountStatementDao,getAPIAccountstmt");
			e.printStackTrace();
		}
		finally 
		{
			try 
			{

				if(session!=null)session.close();
			}
			catch (Exception e) 
			{
				//System.out.println("Exception in AccountStatementDao,getPortalAccountstmt");
				e.printStackTrace();
			}
		}
		return Status;
	}

	public String getPortalAccountstmtDetails(String loginType,String portalId, String userType, String id, String fromDate,String toDate, String filePath) {
		Session session = null;

		String Status = "Norecord";
		Connection con =null;
		try {

			session = HibernateSession.getSessionFactory().openSession();
			con = session.connection();

			CallableStatement cstmt = null;

			cstmt = con.prepareCall("{call AccountStatementSuperDownload(?,?,?,?,?,?)}");

			cstmt.setString(1, fromDate);
			cstmt.setString(2, toDate);
			cstmt.setString(3, userType);
			cstmt.setString(4, id);
			cstmt.setString(5, loginType);
			cstmt.setString(6, portalId);

			ResultSet rs = cstmt.executeQuery();
			Status = "Success";
			FileWriter writer = new FileWriter(filePath);

			writer.append("Client ID");
			writer.append('\t');
			writer.append("ID No");
			writer.append('\t');
			writer.append("Transaction ID");
			writer.append('\t');
			writer.append("Reference ID");
			writer.append('\t');
			writer.append("Portal ID");
			writer.append('\t');
			writer.append("Company Name");
			writer.append('\t');
			writer.append("User Type");
			writer.append('\t');
			writer.append("Date of Transaction");
			writer.append('\t');
			writer.append("Time of Transaction");
			writer.append('\t');
			writer.append("Service");
			writer.append('\t');
			writer.append("Tran Amount");
			writer.append('\t');
			writer.append("Commission");
			writer.append('\t');
			writer.append("Service Charge");
			writer.append('\t');
			writer.append("Bank Charge");
			writer.append('\t');
			writer.append("Other Charge");
			writer.append('\t');
			writer.append("Accepted Amount");
			writer.append('\t');
			writer.append("Action on Amount");
			writer.append('\t');
			writer.append("Previous Balance ");
			writer.append('\t');
			writer.append("Updated Balance");
			writer.append('\t');
			writer.append("Status");
			writer.append('\t');
			writer.append("Final Balance");
			writer.append('\t');
			writer.append("Txn IP Address");
			writer.append('\t');
			writer.append("Updated Date");
			writer.append('\t');
			writer.append("Updated Time");
			writer.append('\t');
			writer.append("Updated User");
			writer.append('\t');
			writer.append("Updated IP Address");
			writer.append('\t');
			writer.append("Remark");
			writer.append('\t');
			writer.append("Transfer To");
			writer.append('\n');

			while (rs.next()) {

				writer.append(rs.getString(1));
				writer.append('\t');
				writer.append(rs.getString(2));
				writer.append('\t');
				writer.append(rs.getString(3));
				writer.append('\t');
				writer.append(rs.getString(4));
				writer.append('\t');
				writer.append(rs.getString(5));
				writer.append('\t');
				writer.append(rs.getString(28));
				writer.append('\t');
				writer.append(rs.getString(6));
				writer.append('\t');
				writer.append(rs.getString(7));
				writer.append('\t');
				writer.append(rs.getString(8));
				writer.append('\t');
				writer.append(rs.getString(9));
				writer.append('\t');
				writer.append(rs.getString(10));
				writer.append('\t');
				writer.append(rs.getString(11));
				writer.append('\t');
				writer.append(rs.getString(12));
				writer.append('\t');
				writer.append(rs.getString(13));
				writer.append('\t');
				writer.append(rs.getString(14));
				writer.append('\t');
				writer.append(rs.getString(15));
				writer.append('\t');
				writer.append(rs.getString(16));
				writer.append('\t');
				writer.append(rs.getString(17));
				writer.append('\t');
				writer.append(rs.getString(18));
				writer.append('\t');
				writer.append(rs.getString(19));
				writer.append('\t');
				writer.append(rs.getString(20));
				writer.append('\t');
				writer.append(rs.getString(21));
				writer.append('\t');

				writer.append(rs.getString(22));
				writer.append('\t');
				writer.append(rs.getString(23));
				writer.append('\t');
				writer.append(rs.getString(24));
				writer.append('\t');
				writer.append(rs.getString(25));
				writer.append('\t');
				writer.append(rs.getString(26));
				writer.append('\t');
				writer.append(rs.getString(27));
				writer.append('\t');
				writer.append('\n');
			}

			writer.flush();
			writer.close();
		} catch (Exception e) {
			Status = "ERROR";
			//System.out.println("Exception in AccountStatementDao,getPortalAccountstmtDetails");
			e.printStackTrace();
		} finally {
			try {
				if(con!=null)con.close();
				if(session!=null)session.close();

			} catch (Exception e) {
				//System.out.println("Exception in AccountStatementDao,getPortalAccountstmtDetails");
				e.printStackTrace();
			}
		}

		return Status;
	}

	public String getMDAccountstmtDetails(String loginType, String portalId,String userType, String id, String fromDate, String toDate,String filePath) 
	{
		Session session = null;
		String Status = "Norecord";

		Connection con =null;
		try {

			session = HibernateSession.getSessionFactory().openSession();


			con = session.connection();

			CallableStatement cstmt = null;

			cstmt = con.prepareCall("{call AccountStatementSuperDownload(?,?,?,?,?,?)}");

			cstmt.setString(1, fromDate);
			cstmt.setString(2, toDate);
			cstmt.setString(3, userType);
			cstmt.setString(4, id);
			cstmt.setString(5, loginType);
			cstmt.setString(6, portalId);

			ResultSet rs = cstmt.executeQuery();

			Status = "Success";
			FileWriter writer = new FileWriter(filePath);

			writer.append("ID_No");
			writer.append('\t');
			writer.append("Transaction_No");
			writer.append('\t');
			writer.append("Reference_ID");
			writer.append('\t');
			writer.append("Complete_MD_ID");
			writer.append('\t');
			writer.append("MD Company Name");
			writer.append('\t');
			writer.append("User_Type");
			writer.append('\t');
			writer.append("Date_of_Transaction");
			writer.append('\t');
			writer.append("Time_of_Transaction");
			writer.append('\t');
			writer.append("Service");
			writer.append('\t');
			writer.append("Transaction_Amount");
			writer.append('\t');
			writer.append("Margin");
			writer.append('\t');
			writer.append("Service_Charge");
			writer.append('\t');
			writer.append("Bank_Charge");
			writer.append('\t');
			writer.append("Other_Charge");
			writer.append('\t');
			writer.append("Net_Amount");
			writer.append('\t');
			writer.append("Action_on_Balance");
			writer.append('\t');
			writer.append("Previous_Balance");
			writer.append('\t');

			writer.append("Updated_Balance");
			writer.append('\t');
			writer.append("Tran_Status");
			writer.append('\t');
			writer.append("Final_Balance");
			writer.append('\t');
			writer.append("Txn_IP");
			writer.append('\t');
			writer.append("Updated_Date");
			writer.append('\t');
			writer.append("Updated_Time");
			writer.append('\t');
			writer.append("Updated_User");
			writer.append('\t');
			writer.append("Updated_IP_Address");
			writer.append('\t');
			writer.append("Remark");
			writer.append('\n'); 

			while (rs.next()) 
			{
				writer.append(rs.getString(1));
				writer.append('\t');
				writer.append(rs.getString(2));
				writer.append('\t');
				writer.append(rs.getString(3));
				writer.append('\t');
				writer.append(rs.getString(4));
				writer.append('\t');
				writer.append(rs.getString(26));
				writer.append('\t');
				writer.append(rs.getString(5));
				writer.append('\t');
				writer.append(rs.getString(6));
				writer.append('\t');
				writer.append(rs.getString(7));
				writer.append('\t');
				writer.append(rs.getString(8));
				writer.append('\t');
				writer.append(rs.getString(9));
				writer.append('\t');
				writer.append(rs.getString(10));
				writer.append('\t');
				writer.append(rs.getString(11));
				writer.append('\t');
				writer.append(rs.getString(12));
				writer.append('\t');
				writer.append(rs.getString(13));
				writer.append('\t');
				writer.append(rs.getString(14));
				writer.append('\t');
				writer.append(rs.getString(15));
				writer.append('\t');
				writer.append(rs.getString(16));
				writer.append('\t');
				writer.append(rs.getString(17));
				writer.append('\t');
				writer.append(rs.getString(18));
				writer.append('\t');
				writer.append(rs.getString(19));
				writer.append('\t');
				writer.append(rs.getString(20));
				writer.append('\t');
				writer.append(rs.getString(21));
				writer.append('\t');

				writer.append(rs.getString(22));
				writer.append('\t');
				writer.append(rs.getString(23));
				writer.append('\t');
				writer.append(rs.getString(24));
				writer.append('\t');
				writer.append(rs.getString(25));
				writer.append('\n');
			}

			writer.flush();
			writer.close();
		} catch (Exception e) {
			Status = "ERROR";
			//System.out.println("Exception in AccountStatementDao,getMDAccountstmtDetails");
			e.printStackTrace();

		} finally {
			try {
				if(con!=null)con.close();
				if(session!=null)session.close();

			} catch (Exception e) {
				//System.out.println("Exception in AccountStatementDao,getMDAccountstmtDetails");
				e.printStackTrace();
			}
		}
		return Status;
	}

	public String getDSAccountstmtDetails(String loginType, String portalId,String userType, String id, String fromDate, String toDate,String filePath) 
	{
		Session session = null;
		String Status = "Norecord";

		Connection con =null;
		try {

			session = HibernateSession.getSessionFactory().openSession();


			con = session.connection();
			CallableStatement cstmt = null;

			cstmt = con.prepareCall("{call AccountStatementSuperDownload(?,?,?,?,?,?)}");

			cstmt.setString(1, fromDate);
			cstmt.setString(2, toDate);
			cstmt.setString(3, userType);
			cstmt.setString(4, id);
			cstmt.setString(5, loginType);
			cstmt.setString(6, portalId);

			ResultSet rs = cstmt.executeQuery();

			Status = "Success";
			FileWriter writer = new FileWriter(filePath);

			writer.append("ID No");
			writer.append('\t');
			writer.append("Transaction No");
			writer.append('\t');
			writer.append("Reference ID");
			writer.append('\t');
			writer.append("Complete DS ID");
			writer.append('\t');
			writer.append("DS Company Name");
			writer.append('\t');
			writer.append("Complete MD ID");
			writer.append('\t');
			writer.append("MD Company Name");
			writer.append('\t');
			writer.append("User Type");
			writer.append('\t');
			writer.append("Date of Transaction");
			writer.append('\t');
			writer.append("Time of Transaction");
			writer.append('\t');
			writer.append("Service");
			writer.append('\t');
			writer.append("Transaction Amount");
			writer.append('\t');
			writer.append("Margin");
			writer.append('\t');
			writer.append("Service Charge");
			writer.append('\t');
			writer.append("Bank Charge");
			writer.append('\t');
			writer.append("Other Charge");
			writer.append('\t');
			writer.append("Net Amount");
			writer.append('\t');
			writer.append("Action on Balance");
			writer.append('\t');
			writer.append("Previous Balance");
			writer.append('\t');

			writer.append("Updated Balance");
			writer.append('\t');
			writer.append("Tran Status");
			writer.append('\t');
			writer.append("Final Balance");
			writer.append('\t');
			writer.append("Txn IP");
			writer.append('\t');
			writer.append("Updated Date");
			writer.append('\t');
			writer.append("Updated Time");
			writer.append('\t');
			writer.append("Updated User");
			writer.append('\t');
			writer.append("Updated IP Address");
			writer.append('\t');
			writer.append("Remark");
			writer.append('\n'); 

			while (rs.next()) 
			{
				writer.append(rs.getString(1));
				writer.append('\t');
				writer.append(rs.getString(2));
				writer.append('\t');
				writer.append(rs.getString(3));
				writer.append('\t');
				writer.append(rs.getString(4));
				writer.append('\t');
				writer.append(rs.getString(27));
				writer.append('\t');
				writer.append(rs.getString(5));
				writer.append('\t');
				writer.append(rs.getString(28));
				writer.append('\t');
				writer.append(rs.getString(6));
				writer.append('\t');
				writer.append(rs.getString(7));
				writer.append('\t');
				writer.append(rs.getString(8));
				writer.append('\t');
				writer.append(rs.getString(9));
				writer.append('\t');
				writer.append(rs.getString(10));
				writer.append('\t');
				writer.append(rs.getString(11));
				writer.append('\t');
				writer.append(rs.getString(12));
				writer.append('\t');
				writer.append(rs.getString(13));
				writer.append('\t');
				writer.append(rs.getString(14));
				writer.append('\t');
				writer.append(rs.getString(15));
				writer.append('\t');
				writer.append(rs.getString(16));
				writer.append('\t');
				writer.append(rs.getString(17));
				writer.append('\t');
				writer.append(rs.getString(18));
				writer.append('\t');
				writer.append(rs.getString(19));
				writer.append('\t');
				writer.append(rs.getString(20));
				writer.append('\t');
				writer.append(rs.getString(21));
				writer.append('\t');
				writer.append(rs.getString(22));
				writer.append('\t');
				writer.append(rs.getString(23));
				writer.append('\t');
				writer.append(rs.getString(24));
				writer.append('\t');
				writer.append(rs.getString(25));
				writer.append('\t');
				writer.append(rs.getString(26));
				writer.append('\n');
			}

			writer.flush();
			writer.close();
		} catch (Exception e) {
			Status = "ERROR";
			//System.out.println("Exception in AccountStatementDao,getDSAccountstmtDetails");
			e.printStackTrace();

		} finally {
			try {
				if(con!=null)con.close();
				if(session!=null)session.close();
			} catch (Exception e) {
				//System.out.println("Exception in AccountStatementDao,getDSAccountstmtDetails");
				e.printStackTrace();
			}
		}
		return Status;
	}

	public ArrayList getPendingAdminAccountStatementView(String loginType, String portalId,String userType,String id, String fromDate,String toDate,HttpServletRequest request ) 
	{
		Session session = null;
		String Status = "Norecord";
		ArrayList listData=new ArrayList();

		Connection con =null;

		try {
			session = HibernateSession.getSessionFactory().openSession();


			con = session.connection();

			CallableStatement cstmt = null;

			cstmt = con.prepareCall("{call PendingAccountStatementSuperView(?,?,?,?,?,?)}");

			cstmt.setString(1, fromDate);
			cstmt.setString(2, toDate);
			cstmt.setString(3, userType);
			cstmt.setString(4, id);
			cstmt.setString(5, loginType);
			cstmt.setString(6,portalId );	

			ResultSet rs = cstmt.executeQuery();
			while (rs.next()) 
			{
				HashMap map=new HashMap();
				map.put("agentId", rs.getString("Complete_Agent_ID"));
				map.put("dor", rs.getString("tran_date"));
				map.put("time",rs.getString("tran_time"));
				map.put("service", rs.getString("service"));
				map.put("tranid", rs.getString("tranid"));
				map.put("amt", rs.getString("requestamount"));
				map.put("action", rs.getString("action"));
				map.put("transtatus", rs.getString("Tran_status"));
				map.put("mobile_number", rs.getString("mobile_number"));
				map.put("tran_no", rs.getString("tran_no"));
				map.put("ApiProvider", rs.getString("ApiProvider"));
				map.put("Operator", rs.getString("mobile_operator"));
				listData.add(map);

				////System.out.println("Pending List : "+map);
			}

		} catch (Exception e) {
			Status = "Norecord";
			//request.setAttribute("NOData",Status);
			//System.out.println("Exception in AccountStatementDao,getAdminAccountStatementView");
			e.printStackTrace();

		} finally {
			try {
				if(con!=null)con.close();
				if(session!=null)session.close();

			} catch (Exception e) {
				//System.out.println("Exception in AccountStatementDao,getAdminAccountStatementView");
				e.printStackTrace();
			}
		}
		return listData;
	}
	public ArrayList getAdminAccountStatementView(String loginType, String portalId,String userType,String id, String fromDate,String toDate,HttpServletRequest request ) 
	{
		Session session = null;
		String Status = "Norecord";
		ArrayList listData=new ArrayList();

		Connection con =null;

		try {
			session = HibernateSession.getSessionFactory().openSession();


			con = session.connection();

			CallableStatement cstmt = null;

			cstmt = con.prepareCall("{call AccountStatementSuperView(?,?,?,?,?,?)}");

			cstmt.setString(1, fromDate);
			cstmt.setString(2, toDate);
			cstmt.setString(3, userType);
			cstmt.setString(4, id);
			cstmt.setString(5, loginType);
			cstmt.setString(6,portalId );	

			ResultSet rs = cstmt.executeQuery();
			int count=0;

			while (rs.next()) 
			{
				count++;
				HashMap map=new HashMap();

				map.put("agentId", rs.getString("client_id"));
				map.put("dor", rs.getString("Date_of_Transaction"));
				map.put("time",rs.getString("Time_of_Transaction"));
				map.put("mobno", rs.getString("Service"));
				map.put("operator", rs.getString("Transaction_No"));
				map.put("TepTranId", rs.getString("tranAmount"));
				map.put("amt", rs.getString("commission"));
				map.put("OperatorTranId", rs.getString("charge"));
				map.put("comm", rs.getString("netAmount"));
				map.put("Service_charge1", rs.getString("User_Type"));
				map.put("transtatus", rs.getString("Tran_status"));
				map.put("Remarks", rs.getString("Remarks")==null ? "--" : rs.getString("Remarks"));

				listData.add(map);


				//System.out.println(rs.getString("client_id"));
				if(count==50)
				{
					return listData;
				}
			}
		} catch (Exception e) {
			Status = "Norecord";
			request.setAttribute("NOData",Status);
			//System.out.println("Exception in AccountStatementDao,getAdminAccountStatementView");
			e.printStackTrace();

		} finally {
			try {
				if(con!=null)con.close();
				if(session!=null)session.close();

			} catch (Exception e) {
				//System.out.println("Exception in AccountStatementDao,getAdminAccountStatementView");
				e.printStackTrace();
			}
		}
		return listData;
	}

	public ArrayList getMDAccountStatement(String loginType, String portalId,String userType,String id, String fromDate,String toDate,HttpServletRequest request ) 
	{
		Session session = null;
		String Status = "Norecord";
		ArrayList listData=new ArrayList();

		Connection con =null;

		try {
			session = HibernateSession.getSessionFactory().openSession();

			con = session.connection();

			CallableStatement cstmt = null;

			cstmt = con.prepareCall("{call AccountStatementSuperView(?,?,?,?,?,?)}");

			cstmt.setString(1, fromDate);
			cstmt.setString(2, toDate);
			cstmt.setString(3, userType);
			cstmt.setString(4, id);
			cstmt.setString(5, loginType);
			cstmt.setString(6,portalId );	
			ResultSet rs = cstmt.executeQuery();

			int count=0;

			while (rs.next()) 
			{
				count++;
				HashMap map=new HashMap();

				map.put("agentId", rs.getString("Complete_MD_ID"));
				map.put("dor", rs.getString("tran_date"));
				map.put("time",rs.getString("tran_time"));
				map.put("mobno", rs.getString("service"));
				map.put("operator", rs.getString("tranid"));
				map.put("TepTranId", rs.getString("requestamount"));
				map.put("amt", rs.getString("commision"));
				map.put("OperatorTranId", rs.getString("charge"));
				map.put("comm", rs.getString("netamount"));
				map.put("Service_charge1", rs.getString("action"));
				map.put("transtatus", rs.getString("Tran_status"));
				//map.put("ServiceProvider", rs.getString("Remark"));

				listData.add(map);
				if(count==50)
				{
					return listData;
				}

			}
		} catch (Exception e) {
			Status = "Norecord";
			request.setAttribute("NOData",Status);
			//System.out.println("Exception in AccountStatementDao,getMDAccountStatement");
			//System.out.println(e.toString());

		} finally 
		{
			try 
			{
				if(con!=null)con.close();
				if(session!=null)session.close();
			} catch (Exception e) {
				//System.out.println("Exception in AccountStatementDao,getMDAccountStatement");
				e.printStackTrace();
			}
		}
		return listData;
	}

	public ArrayList getDistributorAccountStatement(String loginType, String portalId,String userType,String id, String fromDate,String toDate,HttpServletRequest request ) 
	{
		Session session = null;
		String Status = "Norecord";
		ArrayList listData=new ArrayList();

		Connection con =null;

		try {
			session = HibernateSession.getSessionFactory().openSession();

			con = session.connection();

			CallableStatement cstmt = null;

			cstmt = con.prepareCall("{call AccountStatementSuperView(?,?,?,?,?,?)}");

			cstmt.setString(1, fromDate);
			cstmt.setString(2, toDate);
			cstmt.setString(3, userType);
			cstmt.setString(4, id);
			cstmt.setString(5, loginType);
			cstmt.setString(6,portalId );		

			ResultSet rs = cstmt.executeQuery();
			int count=0;

			while (rs.next()) 
			{
				count++;
				HashMap map=new HashMap();

				map.put("agentId", rs.getString("Complete_DS_ID"));
				map.put("dor", rs.getString("tran_date"));
				map.put("time",rs.getString("tran_time"));
				map.put("mobno", rs.getString("service"));
				map.put("operator", rs.getString("tranid"));
				map.put("TepTranId", rs.getString("requestamount"));
				map.put("amt", rs.getString("commision"));
				map.put("OperatorTranId", rs.getString("charge"));
				map.put("comm", rs.getString("netamount"));
				map.put("Service_charge1", rs.getString("action"));
				map.put("transtatus", rs.getString("Tran_status"));
				//map.put("ServiceProvider", rs.getString("Remark"));

				listData.add(map);
				if(count==50)
				{
					return listData;
				}
			}
		} catch (Exception e) {
			Status = "Norecord";
			request.setAttribute("NOData",Status);
			//System.out.println("Exception in AccountStatementDao,getDistributorAccountStatement");
			e.printStackTrace();

		} finally {
			try {	
				if(con!=null)con.close();
				if(session!=null)session.close();
			} catch (Exception e) {
				//System.out.println("Exception in AccountStatementDao,getDistributorAccountStatement");
				e.printStackTrace();
			}
		}
		return listData;
	}

	public ArrayList getAgentAccountStatement(String loginType, String portalId,String userType,String id, String fromDate,String toDate,HttpServletRequest request ) 
	{
		Session session = null;
		String Status = "Norecord";
		ArrayList listData=new ArrayList();

		Connection con=null;

		try {
			session = HibernateSession.getSessionFactory().openSession();
			con = session.connection();

			CallableStatement cstmt = null;

			cstmt = con.prepareCall("{call AccountStatementSuperView(?,?,?,?,?,?)}");

			cstmt.setString(1, fromDate);
			cstmt.setString(2, toDate);
			cstmt.setString(3, userType);
			cstmt.setString(4, id);
			cstmt.setString(5, loginType);
			cstmt.setString(6,portalId );	

			ResultSet rs = cstmt.executeQuery();
			int count=0;
			while (rs.next()) 
			{
				count++;
				HashMap map=new HashMap();

				map.put("agentId", rs.getString("Complete_Agent_ID"));
				map.put("dor", rs.getString("tran_date"));
				map.put("time",rs.getString("tran_time"));
				map.put("mobno", rs.getString("service"));
				map.put("operator", "NA");
				map.put("TepTranId", rs.getString("tranid"));
				map.put("amt", rs.getString("requestamount"));
				map.put("netamount", rs.getString("netamount"));
				map.put("action", rs.getString("action"));
				map.put("OperatorTranId", "");
				map.put("comm", rs.getString("commision"));
				map.put("Service_charge1", rs.getString("charge"));
				map.put("GST", rs.getString("gst"));
				map.put("transtatus", rs.getString("Tran_status"));
				map.put("Remarks", rs.getString("remark"));

				listData.add(map);
				if(count==50)
				{
					return listData;
				}

			}
		} catch (Exception e) {

			Status = "Norecord";
			request.setAttribute("NOData",Status);
			//System.out.println("Exception in AccountStatementDao,getAgentAccountStatement");
			//System.out.println(e.toString());

		} finally {
			try {
				if(con!=null)con.close();
				if(session!=null)session.close();
			} catch (Exception e) {
				//System.out.println("Exception in AccountStatementDao,getAgentAccountStatement");
				//System.out.println(e.toString());
			}
		}
		return listData;
	}

	/*public ArrayList getAccountStatementEGEN( String id,String portalId, String fromDate,String toDate ) 
	{
		Session session = null;

		String Status = "Norecord";
		ArrayList listData=new ArrayList();
		Query query=null;
		String sqlQuery1="";
		String sqlQuery2="";
		String sqlQuery3="";
		String sqlQuery4="";

		Connection con =null;

		try 
		{
			session = HibernateSession1.getSessionFactory().openSession();
			//System.out.println("id is :"+id);

			con = session.connection();

			if(id.equalsIgnoreCase("All"))
			{
				sqlQuery1="select Top 20 ','+Corporate_Transaction_Id as Corporate_Transaction_Id,Refrence_Id as Refrence_Id,'API'+convert(varchar(10),Corporate_Agent_Id) as APIagentId," +
						"convert(varchar(10),Date_of_Transaction,120) as date,convert(varchar(10), Time_of_transaction, 108) as transactiontime," +
						"Service,convert(decimal(18,2),Tran_Amount) as Tran_Amount,convert(decimal(18,2),Commission) as Commission ,convert(decimal(18,2),Service_Charges) as Service_Charges," +
						"convert(decimal(18,2),Net_Tran_Amt) as Net_Tran_Amt,Action_On_Bal_Amt,Tran_Status from Rech_API_Agent_Transaction_details where  Date_of_Transaction between '"+fromDate+"' and '"+toDate+"' order by ID_No";

				sqlQuery2="select Top 20 ','+Corporate_Transaction_Id as Corporate_Transaction_Id,Partner_Ref_id as Refrence_Id," +
						"'API'+convert(varchar(10),Corporate_Agent_Id)as APIagentId,convert(varchar(10),Time_of_Transaction,120) as date," +
						"convert(varchar(10), Time_of_Transaction, 108) as transactiontime,Service,Tran_Amount as Tran_Amount,Commission as Commission ,Service_Charges as Service_Charges," +
						"Net_Tran_Amt as Net_Tran_Amt,Action_On_Bal_Amt,Status as Tran_Status from Flight_API_Agent_Txn_details where  convert(varchar(10),Time_of_Transaction,120) between '"+fromDate+"' and '"+toDate+"' order by ID_No";

				sqlQuery3="select Top 20 ','+Corporate_Transaction_Id as Corporate_Transaction_Id,BookingRef as Refrence_Id," +
						"'API'+convert(varchar(10),Corporate_Agent_Id)as APIagentId,convert(varchar(10),Time_of_Transaction,120) as date," +
						"convert(varchar(10), Time_of_Transaction, 108) as transactiontime,Service,Tran_Amount as Tran_Amount,Commission as Commission ,Service_Charges as Service_Charges," +
						"Net_Tran_Amt as Net_Tran_Amt,Action_On_Bal_Amt,Status as Tran_Status from Hotel_API_Agent_Txn_details where convert(varchar(10),Time_of_Transaction,120) between '"+fromDate+"' and '"+toDate+"' order by ID_No";

				sqlQuery4="select Top 20 ','+Transaction_id as Corporate_Transaction_Id,Ticket_PNR as Refrence_Id," +
						"'API'+Client_id as APIagentId,convert(varchar(10),Date_of_Transaction,120) as date," +
						"convert(varchar(10), Date_of_Transaction, 108) as transactiontime,Req_Amount as Tran_Amount,Commission as Commission ," +
						"DeductedAmt as Net_Tran_Amt,Action_on_bal_amt as Action_On_Bal_Amt,Tran_Status from BUSAPI_Client_Transaction_Details where  convert(varchar(10),Date_of_Transaction,120) between '"+fromDate+"' and '"+toDate+"' order by Id";

			}else
			{
				sqlQuery1="select Top 20 ','+Corporate_Transaction_Id as Corporate_Transaction_Id,Refrence_Id as Refrence_Id,'API'+convert(varchar(10),Corporate_Agent_Id) as APIagentId," +
						"convert(varchar(10),Date_of_Transaction,120) as date,convert(varchar(10), Time_of_transaction, 108) as transactiontime," +
						"Service,convert(decimal(18,2),Tran_Amount) as Tran_Amount,convert(decimal(18,2),Commission) as Commission ,convert(decimal(18,2),Service_Charges) as Service_Charges," +
						"convert(decimal(18,2),Net_Tran_Amt) as Net_Tran_Amt,Action_On_Bal_Amt,Tran_Status from Rech_API_Agent_Transaction_details where Corporate_Agent_Id='"+id+"' and " +
						"Date_of_Transaction between '"+fromDate+"' and '"+toDate+"'  order by ID_No";

				sqlQuery2="select Top 20 ','+Corporate_Transaction_Id as Corporate_Transaction_Id,Partner_Ref_id as Refrence_Id," +
						"'API'+convert(varchar(10),Corporate_Agent_Id)as APIagentId,convert(varchar(10),Time_of_Transaction,120) as date," +
						"convert(varchar(10), Time_of_Transaction, 108) as transactiontime,Service,Tran_Amount as Tran_Amount,Commission as Commission ,Service_Charges as Service_Charges," +
						"Net_Tran_Amt as Net_Tran_Amt,Action_On_Bal_Amt,Status as Tran_Status from Flight_API_Agent_Txn_details where Corporate_Agent_Id='"+id+"' and convert(varchar(10),Time_of_Transaction,120) between '"+fromDate+"' and '"+toDate+"' order by ID_No";

				sqlQuery3="select Top 20 ','+Corporate_Transaction_Id as Corporate_Transaction_Id,BookingRef as Refrence_Id," +
						"'API'+convert(varchar(10),Corporate_Agent_Id)as APIagentId,convert(varchar(10),Time_of_Transaction,120) as date," +
						"convert(varchar(10), Time_of_Transaction, 108) as transactiontime,Service,Tran_Amount as Tran_Amount,Commission as Commission ,Service_Charges as Service_Charges," +
						"Net_Tran_Amt as Net_Tran_Amt,Action_On_Bal_Amt,Status as Tran_Status from Hotel_API_Agent_Txn_details where Corporate_Agent_Id='"+id+"' and convert(varchar(10),Time_of_Transaction,120) between '"+fromDate+"' and '"+toDate+"' order by ID_No";

				sqlQuery4="select Top 20 ','+Transaction_id as Corporate_Transaction_Id,Ticket_PNR as Refrence_Id," +
						"'API'+Client_id as APIagentId,convert(varchar(10),Date_of_Transaction,120) as date," +
						"convert(varchar(10), Date_of_Transaction, 108) as transactiontime,Req_Amount as Tran_Amount,Commission as Commission ," +
						"DeductedAmt as Net_Tran_Amt,Action_on_bal_amt as Action_On_Bal_Amt,Tran_Status from BUSAPI_Client_Transaction_Details where Client_id='"+id+"' and convert(varchar(10),Date_of_Transaction,120) between '"+fromDate+"' and '"+toDate+"' order by Id";

			}

			////System.out.println("query is ::"+sqlQuery1);
			for(int i=1;i<=4;i++)
			{
				if(i==1)
					query=session.createSQLQuery(sqlQuery1);
				if(i==2)
					query=session.createSQLQuery(sqlQuery2);
				if(i==3)
					query=session.createSQLQuery(sqlQuery3);	
				if(i==4)
					query=session.createSQLQuery(sqlQuery4);

				List list=query.list();
				Iterator itr=list.iterator();
				HashMap map=null;
				Object[] row;

				while(itr.hasNext())
				{
					row = (Object[])itr.next();
					map=new HashMap();

					map.put("Corporate_Transaction_Id", row[0]);
					map.put("Refrence_Id", row[1]);
					map.put("APIagentId", row[2]);
					map.put("date", row[3]);
					map.put("transactiontime", row[4]);

					if(i==4)
					{
						map.put("Service", "Bus");
						map.put("Service_Charges", "0.00");
						map.put("Tran_Amount", row[5]);
						map.put("Commission", row[6]);
						map.put("Net_Tran_Amt", row[7]);
						map.put("Action_On_Bal_Amt", row[8]);
						map.put("Tran_Status", row[9]);
					}
					else
					{
						if(i==2)
						{
							map.put("Service", "Flight");
							map.put("Service_Charges", "0.00");
						}
						else if(i==3)
						{
							map.put("Service", "Hotel");
							map.put("Service_Charges", row[8]);
						}
						else
						{
							map.put("Service", row[5]);
							map.put("Service_Charges", row[8]);
						}
						map.put("Tran_Amount", row[6]);
						map.put("Commission", row[7]);
						map.put("Net_Tran_Amt", row[9]);
						map.put("Action_On_Bal_Amt", row[10]);
						map.put("Tran_Status", row[11]);
					}
					listData.add(map);
				}
			}

		} catch (Exception e) 
		{
			Status = "Norecord";
			//System.out.println("Exception in AccountStatementDao,getAccountStatementEGEN");
			e.printStackTrace();

		} 
		finally 
		{
			try {
				if(con!=null)con.close();;

				session.close();

			} catch (Exception e) {
				//System.out.println("Exception in AccountStatementDao,getAccountStatementEGEN");
				//System.out.println(e.toString());
			}
		}
		//System.out.println("list size  "+listData.size());
		return listData;
	}*/

	public ArrayList getAccountStatementEGEN( String reportOf,String reportFor, String fromDate,String toDate ) 
	{
		Session session = null;

		String Status = "Norecord";
		ArrayList listData=new ArrayList();
		Query query=null;
		String sqlQuery1="";
		String sqlQuery2="";
		String sqlQuery3="";
		String sqlQuery4="";

		Connection con =null;

		try 
		{
			session = HibernateSession1.getSessionFactory().openSession();

			con = session.connection();

			CallableStatement cstmt = null;

			cstmt = con.prepareCall("{call Admin_Egen_reports(?,?,?,?)}");
			//System.out.println("toDate value is" +toDate);
			cstmt.setString(1, reportOf);
			cstmt.setString(2, reportFor);
			cstmt.setString(3, fromDate);
			cstmt.setString(4, toDate);
			//System.out.println("fromDate value is" +fromDate);

			ResultSet rs = cstmt.executeQuery();
			int count=0;
			while (rs.next()) 
			{ 
				count++;
				HashMap <String,String>map=new HashMap<String,String>();
				map.put("Corporate_Transaction_Id", rs.getString(1));
				map.put("Refrence_Id", rs.getString(2));
				map.put("APIagentId", rs.getString(3));
				map.put("date", rs.getString(4));
				map.put("transactiontime", rs.getString(5));
				map.put("Service_Charges", (rs.getString(6)==null)?"0.0":rs.getString(6));
				map.put("Tran_Amount", rs.getString(7));
				map.put("Commission", rs.getString(8));
				map.put("Net_Tran_Amt", rs.getString(9));
				map.put("Action_On_Bal_Amt", rs.getString(10));
				map.put("Tran_Status", rs.getString(11));
				map.put("Service", rs.getString(12));
				listData.add(map);

				////System.out.println("listData   :   "+listData);
			}



		} catch (Exception e) 
		{
			Status = "Norecord";
			//System.out.println("Exception in AccountStatementDao,getAccountStatementEGEN");
			e.printStackTrace();

		} 
		finally 
		{
			try {
				if(con!=null)con.close();
				if(session!=null)session.close();

			} catch (Exception e) {
				//System.out.println("Exception in AccountStatementDao,getAccountStatementEGEN");
				//System.out.println(e.toString());
			}
		}
		//System.out.println("list size  "+listData.size());
		return listData;
	}
}
