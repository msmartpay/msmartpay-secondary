package com.report.tranReport;

/**
 *  @author Arshad 
 *  Created Date 16/2/2013
 *  Created Matter to provide the report
 */
import java.io.FileWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.common.HibernateSession;
import com.flights.domestic.services.pojo.DomesticAirAccountingRecord;
import com.flights.domestic.services.pojo.DomesticAirCancellatioAccountingRecord;
import com.flights.domestic.services.pojo.PessangerForReport;

public class TransactionReportDao {
	private Session session = null;
	private Connection con =null;
	private CallableStatement cstmt = null;
	private ResultSet rs=null;
	private Query qry = null;
	private String sqlQuery=null;
	private String status = "ERROR";
	public final String getBillPaymentReport(String filePath, String reportOf, String userId) {
		
		
        try {
        	session = HibernateSession.getSessionFactory().openSession();
        	con = session.connection();
			sqlQuery="select count(a.user_id) from bill_mobile a where Date_of_Booking between convert(varchar(10),getdate()-7,120) and convert(varchar(10),getdate(),120) and a.user_id in (select agent_id from agent_details where distributor_id='"+userId+"')";
//			sqlQuery="select count(a.user_id) from bill_mobile a where Date_of_Booking between '2012-12-10' and '2012-12-30' and a.user_id in (select agent_id from agent_details where distributor_id='"+userId+"')";
			System.out.println(sqlQuery);
			qry = session.createSQLQuery(sqlQuery);
			int count=Integer.parseInt(qry.uniqueResult().toString());
			System.out.println(count);
			if(count<=0 || count==0){
				status="NoRecord";
			}else if(count>=20000){
				status="MoreRecord";
			}else if(count>=1 && count<=20000){
			
				cstmt = con.prepareCall("{call DS_reports(?,?)}");
				cstmt.setString(1, reportOf);
				cstmt.setString(2, userId);
				status="Success";
				ResultSet rs = cstmt.executeQuery();
				FileWriter writer = new FileWriter(filePath);
//				writer.append("Master Distributor ID");
//				writer.append(';');
//				writer.append("Distributor Id");
//				writer.append(';');
				writer.append("Agent Id");
				writer.append(';');
				writer.append("Service");
				writer.append(';');
				writer.append("Operator Name");
				writer.append(';');
				writer.append("Account number");
				writer.append(';');
				writer.append("Mobile No");
				writer.append(';');
				writer.append("Transactions Id");
				writer.append(';');
				writer.append("Gross Amount");
				writer.append(';');
				writer.append("Margin");
				writer.append(';');
				writer.append("Net Amount");
				writer.append(';');
				writer.append("Status");
				writer.append(';');
				writer.append("Request Date");
				writer.append(';');
				writer.append("Request Time");
				writer.append(';');
				writer.append("Response Date");
				writer.append(';');
				writer.append("Response Time");
				writer.append(';');
				writer.append("Decline Reason");
				writer.append(';');
				writer.append("Agent EmailId");
				writer.append('\n');
				while(rs.next()){
					writer.append(rs.getString(1));
					writer.append(';');
					writer.append(rs.getString(2));
					writer.append(';');
					writer.append(rs.getString(3));
					writer.append(';');
					writer.append("BILL PAYMENT");
					writer.append(';');
					writer.append(rs.getString(4));
					writer.append(';');
					writer.append(rs.getString(5));
					writer.append(';');
					writer.append(rs.getString(6));
					writer.append(';');
					writer.append(rs.getString(7));
					writer.append(';');
					writer.append(rs.getString(8));
					writer.append(';');
					writer.append(rs.getString(9));
					writer.append(';');
					writer.append(rs.getString(8));
					writer.append(';');
					writer.append(rs.getString(10));
					writer.append(';');
					writer.append(rs.getString(11));
					writer.append(';');
					writer.append(rs.getString(12));
					writer.append(';');
					writer.append(rs.getString(13));
					writer.append(';');
					writer.append(rs.getString(14));
//					writer.append(';');
//					writer.append(rs.getString(15));
//					writer.append(';');
//					writer.append(rs.getString(16));
					writer.append('\n');
				}
				writer.flush();
				writer.close();
				
			}else{
				status="ERROR";
			}
		} catch (Exception e) {
			status = "ERROR";
			System.out.println("Exception in  getBillPaymentReport ");
			e.printStackTrace();
		}
		finally {
			try {
				if(con!=null)
					con.close();
				if(session!=null){
					session.flush();
					session.close();
				}
				
			} catch (Exception e) {
				System.out.println("Exception in  getBillPaymentReport while closing connection");
				e.printStackTrace();
			}

		}
		System.out.println("status is "+status);
		return status;
	}
	public final  String getBusTranBookReport(String filePath, String reportOf, String userId) {

		
		
        try {
        	session = HibernateSession.getSessionFactory().openSession();
			con = session.connection();
			sqlQuery="select count(a.Agent_id) from redbus_book_cancel_details a where transaction_type='Purchase' and convert(varchar(10),D_O_transaction,120) between convert(varchar(10),getdate()-7,120) and convert(varchar(10),getdate(),120) and a.Agent_id in (select agent_initial+convert(varchar,agent_id) from agent_details where distributor_id='"+userId+"')";
//			sqlQuery="select count(a.Agent_id) from redbus_book_cancel_details a where transaction_type='Purchase' and convert(varchar(10),D_O_transaction,120) between '2012-12-10' and '2012-12-30' and a.Agent_id in (select agent_initial+convert(varchar,agent_id) from agent_details where distributor_id='"+userId+"')";
			System.out.println(sqlQuery);
			qry = session.createSQLQuery(sqlQuery);
			int count=Integer.parseInt(qry.uniqueResult().toString());
			System.out.println(count);
			if(count<=0 || count==0){
				status="NoRecord";
			}else if(count>=20000){
				status="MoreRecord";
			}else if(count>=1 && count<=20000){
				cstmt = con.prepareCall("{call DS_reports(?,?)}");

				cstmt.setString(1, reportOf);
				cstmt.setString(2, userId);
	
				ResultSet rs = cstmt.executeQuery();
	
				status = "Success";
				FileWriter writer = new FileWriter(filePath);
	
//				writer.append("Master Distributor ID");
//				writer.append(';');
//				writer.append("Distributor ID");
//				writer.append(';');
				writer.append("Agent Id");
				writer.append(';');
				writer.append("Transaction Date");
				writer.append(';');
				writer.append("Transaction Time");
				writer.append(';');
				writer.append("Service");
				writer.append(';');
				writer.append("TicketNo");
				writer.append(';');
				writer.append("Gross Amount");
				writer.append(';');
				writer.append("Comission");
				writer.append(';');
				writer.append("Net Amount");
				writer.append(';');
				writer.append("Txn Id");
				writer.append(';');
				writer.append("Transaction Type");
				writer.append(';');
				writer.append("Ticket Fare");
				writer.append(';');
				writer.append("Markup");
				writer.append(';');
				writer.append("Passenger Name");
				writer.append(';');
				writer.append("Source");
				writer.append(';');
				writer.append("Destination");
				writer.append(';');
				writer.append("Cancellation Charges");
				writer.append(';');
				writer.append("Refund Amount");
				writer.append('\n');
			
				while (rs.next()) {
					
					writer.append(rs.getString(1));
					writer.append(';');
					writer.append(rs.getString(2));
					writer.append(';');
					writer.append(rs.getString(3));
					writer.append(';');
					writer.append(rs.getString(4));
					writer.append(';');
					writer.append(rs.getString(5));
					writer.append(';');
					writer.append("Bus");
					writer.append(';');
					writer.append(rs.getString(6));
					writer.append(';');
					writer.append(rs.getString(7));
					writer.append(';');
					writer.append(rs.getString(8));
					writer.append(';');
					writer.append(rs.getString(9));
					writer.append(';');
					writer.append(rs.getString(10));
					writer.append(';');
					writer.append(rs.getString(11));
					writer.append(';');
					writer.append(rs.getString(12));
					writer.append(';');
					writer.append(rs.getString(13));
					writer.append(';');
					writer.append(rs.getString(14));
					writer.append(';');
					writer.append(rs.getString(15));
					writer.append(';');
					writer.append(rs.getString(16));
//					writer.append(';');
//					writer.append(rs.getString(17));
//					writer.append(';');
//					writer.append(rs.getString(18));
					writer.append('\n');
				}
				writer.flush();
				writer.close();
				status = "Success";
			}else{
				status="ERROR";
			}
        }
        catch (Exception e) {
			status = "ERROR";
			System.out.println("Exception in  getBusTransactionReport ");
			e.printStackTrace();
		} finally {
			try {

				if(con!=null)
					con.close();
				session.flush();
				session.close();
				
			} catch (Exception e) {
				System.out.println("Exception in  getBusTransactionReport while closing connection");
				e.printStackTrace();
			}

		}
		return status;
	}
	
	public final String getBusTranCanReport(String filePath, String reportOf, String userId) {

		
		
        try {
        	session = HibernateSession.getSessionFactory().openSession();
			con = session.connection();
			sqlQuery="select count(a.Agent_id) from redbus_book_cancel_details a where transaction_type='Cancellation' and convert(varchar(10),D_O_transaction,120) between convert(varchar(10),getdate()-7,120) and convert(varchar(10),getdate(),120) and a.Agent_id in (select agent_initial+convert(varchar,agent_id) from agent_details where distributor_id='"+userId+"')";
//			sqlQuery="select count(a.Agent_id) from redbus_book_cancel_details a where transaction_type='Cancellation' and convert(varchar(10),D_O_transaction,120) between '2012-12-10' and '2012-12-30' and a.Agent_id in (select agent_initial+convert(varchar,agent_id) from agent_details where distributor_id='"+userId+"')";
			System.out.println(sqlQuery);
			qry = session.createSQLQuery(sqlQuery);
			int count=Integer.parseInt(qry.uniqueResult().toString());
			System.out.println(count);
			if(count<=0 || count==0){
				status="NoRecord";
			}else if(count>=20000){
				status="MoreRecord";
			}else if(count>=1 && count<=20000){
				cstmt = con.prepareCall("{call DS_reports(?,?)}");

				cstmt.setString(1, reportOf);
				cstmt.setString(2, userId);
	
				ResultSet rs = cstmt.executeQuery();
	
				status = "Success";
				FileWriter writer = new FileWriter(filePath);
	
//				writer.append("Master Distributor ID");
//				writer.append(';');
//				writer.append("Distributor ID");
//				writer.append(';');
				writer.append("Agent Id");
				writer.append(';');
				writer.append("Transaction Date");
				writer.append(';');
				writer.append("Transaction Time");
				writer.append(';');
				writer.append("Service");
				writer.append(';');
				writer.append("TicketNo");
				writer.append(';');
				writer.append("Gross Amount");
				writer.append(';');
				writer.append("Comission");
				writer.append(';');
				writer.append("Net Amount");
				writer.append(';');
				writer.append("Txn Id");
				writer.append(';');
				writer.append("Transaction Type");
				writer.append(';');
				writer.append("Ticket Fare");
				writer.append(';');
				writer.append("Markup");
				writer.append(';');
				writer.append("Passenger Name");
				writer.append(';');
				writer.append("Source");
				writer.append(';');
				writer.append("Destination");
				writer.append(';');
				writer.append("Cancellation Charges");
				writer.append(';');
				writer.append("Refund Amount");
				writer.append('\n');
			
				while (rs.next()) {
					
					writer.append(rs.getString(1));
					writer.append(';');
					writer.append(rs.getString(2));
					writer.append(';');
					writer.append(rs.getString(3));
					writer.append(';');
					writer.append("Bus");
					writer.append(';');
					writer.append(rs.getString(4));
					writer.append(';');
					writer.append(rs.getString(5));
					writer.append(';');
					
					writer.append(rs.getString(6));
					writer.append(';');
					writer.append(rs.getString(7));
					writer.append(';');
					writer.append(rs.getString(8));
					writer.append(';');
					writer.append(rs.getString(9));
					writer.append(';');
					writer.append(rs.getString(10));
					writer.append(';');
					writer.append(rs.getString(11));
					writer.append(';');
					writer.append(rs.getString(12));
					writer.append(';');
					writer.append(rs.getString(13));
					writer.append(';');
					writer.append(rs.getString(14));
					writer.append(';');
					writer.append(rs.getString(15));
					writer.append(';');
					writer.append(rs.getString(16));
//					writer.append(';');
//					writer.append(rs.getString(17));
//					writer.append(';');
//					writer.append(rs.getString(18));
					writer.append('\n');
				}
				writer.flush();
				writer.close();
				status = "Success";
			}else{
				status="ERROR";
			}
        }
        catch (Exception e) {
			status = "ERROR";
			System.out.println("Exception in  getBusTransactionReport ");
			e.printStackTrace();
		} finally {
			try {

				session.flush();
				session.close();
				con.close();
			} catch (Exception e) {
				System.out.println("Exception in  getBusTransactionReport while closing connection");
				e.printStackTrace();
			}

		}
		return status;
	}
	// Insurance Premium Report
	public final String getLICPremiumReport(String filePath,  String reportOf,  String userId) {
		
		try {
			session = HibernateSession.getSessionFactory().openSession();
			con = session.connection();
			sqlQuery="select count(user_id) from lic_premium where convert(varchar(10),Request_DATE,120) between convert(varchar(10),getdate()-7,120) and convert(varchar(10),getdate(),120) and user_id in (select agent_id from agent_details where distributor_id='"+userId+"')";
//			sqlQuery="select count(user_id) from lic_premium where convert(varchar(10),Request_DATE,120) between '2012-12-10' and '2012-12-30' and user_id in (select agent_id from agent_details where distributor_id='"+userId+"')";
			System.out.println(sqlQuery);
			qry = session.createSQLQuery(sqlQuery);
			int count=Integer.parseInt(qry.uniqueResult().toString());
			System.out.println(count);
			if(count<=0 || count==0){
				status="NoRecord";
			}else if(count>=20000){
				status="MoreRecord";
			}else if(count>=1 && count<=20000){
				cstmt = con.prepareCall("{call DS_reports(?,?)}");

				cstmt.setString(1, reportOf);
				cstmt.setString(2, userId);
				status = "Success";
				ResultSet rs = cstmt.executeQuery();
				
				FileWriter writer = new FileWriter(filePath);
	
//				writer.append("Master Distributor ID");
//				writer.append(';');
//				writer.append("Distributor ID");
//				writer.append(';');
				writer.append("Agent Id");
				writer.append(';');
				writer.append("Service");
				writer.append(';');
				writer.append("Operator Name");
				writer.append(';');
				writer.append("Policy No.");
				writer.append(';');
				writer.append("Policy Holder No.");
				writer.append(';');
				writer.append("Gross Amount");
				writer.append(';');
				writer.append("Margin");
				writer.append(';');
				writer.append("Net Amount");
				writer.append(';');
				writer.append("Transactions Id");
				writer.append(';');
				writer.append("Request Date");
				writer.append(';');
				writer.append("Request Time");
				writer.append(';');
				writer.append("Response Date");
				writer.append(';');
				writer.append("Response Date");
				writer.append(';');
				writer.append("Response Time");
				writer.append(';');
				writer.append("Status");
				writer.append(';');
				writer.append("Decline Reason");
				writer.append(';');
				writer.append("Agent E-Mail ID");
				writer.append('\n');
			
			while (rs.next()) {
				writer.append(rs.getString(1));
				writer.append(';');
				writer.append(rs.getString(2));
				writer.append(';');
				writer.append(rs.getString(3));
				writer.append(';');
				writer.append("Insurance Payment");
				writer.append(';');
				writer.append(rs.getString(4));
				writer.append(';');
				writer.append(rs.getString(5));
				writer.append(';');
				writer.append(rs.getString(6));
				writer.append(';');
				writer.append(rs.getString(7));
				writer.append(';');
				writer.append(rs.getString(8));
				writer.append(';');
				writer.append(rs.getString(9));
				writer.append(';');
				writer.append(rs.getString(10));
				writer.append(';');
				writer.append(rs.getString(11));
				writer.append(';');
				writer.append(rs.getString(12));
				writer.append(';');
				writer.append(rs.getString(5));
				writer.append(';');
				writer.append(rs.getString(13));
				writer.append(';');
				writer.append(rs.getString(14));
				writer.append(';');
				writer.append(rs.getString(15));
//				writer.append(';');
//				writer.append(rs.getString(16));
//				writer.append(';');
//				writer.append(rs.getString(17));
				writer.append('\n');
			}
			writer.flush();
			writer.close();
			
			}
			else{
			status="ERROR";
		}
    }
    catch (Exception e) {
		status = "ERROR";
		System.out.println("Exception in  getLICPremiumReport ");
		e.printStackTrace();
	} finally {
		try {

			session.flush();
			session.close();
			con.close();
		} catch (Exception e) {
			System.out.println("Exception in  getLICPremiumReport while closing connection");
			e.printStackTrace();
		}

	}
	return status;
	}
	// Live Recharge Transaction Report
	public final  String getLiveRechargeReport(String filePath, String reportOf, String userId) {
		
		
		try {
			session = HibernateSession.getSessionFactory().openSession();
			con = session.connection();
			sqlQuery="select count(user_id)  from live_recharge where date_of_recharge=convert(varchar(10),getdate()-1,120) and user_id in (select agent_id from agent_details where distributor_id='"+userId+"')";
//			sqlQuery="select count(user_id)  from live_recharge where date_of_recharge='2012-12-10' and user_id in (select agent_id from agent_details where distributor_id='"+userId+"')";
			System.out.println(sqlQuery);
			qry = session.createSQLQuery(sqlQuery);
			int count=Integer.parseInt(qry.uniqueResult().toString());
			System.out.println(count);
			if(count<=0 || count==0){
				status="NoRecord";
			}else if(count>=20000){
				status="MoreRecord";
			}else if(count>=1 && count<=20000){
				cstmt = con.prepareCall("{call DS_reports(?,?)}");

				cstmt.setString(1, reportOf);
				cstmt.setString(2, userId);
				ResultSet rs = cstmt.executeQuery();
				status = "Success";
				FileWriter writer = new FileWriter(filePath);

//				writer.append("Master Distributor ID");
//				writer.append(';');
//				writer.append("Distributor ID");
//				writer.append(';');
				writer.append("Agent Id");
				writer.append(';');
				writer.append("Service");
				writer.append(';');
				writer.append("Operator Name");
				writer.append(';');
				writer.append("Connection No");
				writer.append(';');
				writer.append("Gross Amount");
				writer.append(';');
				writer.append("Margin");
				writer.append(';');
				writer.append("Net Tran Amt");
				writer.append(';');
				writer.append("Transaction Id");
				writer.append(';');
				writer.append("Date");
				writer.append(';');
				writer.append("Time");
				writer.append(';');
				writer.append("Status");
				writer.append('\n');
				
				while (rs.next()) {
					writer.append(rs.getString(1));
					writer.append(';');
					writer.append(rs.getString(2));
					writer.append(';');
					writer.append(rs.getString(3));
					writer.append(';');
					writer.append(rs.getString(4));
					writer.append(';');
					writer.append(rs.getString(5));
					writer.append(';');
					writer.append(rs.getString(6));
					writer.append(';');
					writer.append(rs.getString(7));
					writer.append(';');
					writer.append(rs.getString(8));
					writer.append(';');
					writer.append(rs.getString(9));
					writer.append(';');
					writer.append(rs.getString(10));
					writer.append(';');
					writer.append(rs.getString(11));
//					writer.append(';');
//					writer.append(rs.getString(12));
//					writer.append(';');
//					writer.append(rs.getString(13));
					writer.append('\n');

				}
				writer.flush();
				writer.close();
				
			}else{
				status="ERROR";
			}
        }
        catch (Exception e) {
			status = "ERROR";
			System.out.println("Exception in  getLiveRechargeReport ");
			e.printStackTrace();
		} finally {
			try {

				session.flush();
				session.close();
				con.close();
			} catch (Exception e) {
				System.out.println("Exception in  getLiveRechargeReport while closing connection");
				e.printStackTrace();
			}

		}
		return status;	
	} 
	public  final String getPanCardReport(String filePath, String reportOf, String userId) {

		
        try {
        	session = HibernateSession.getSessionFactory().openSession();
        	con = session.connection();
        	sqlQuery="select count(user_id)  from pan_request where date_of_booking between convert(varchar(10),getdate()-7,120) and convert(varchar(10),getdate(),120) and user_id in (select agent_id from agent_details where distributor_id='"+userId+"')";
//        	sqlQuery="select count(user_id)  from pan_request where date_of_booking between '2012-12-10' and '2012-12-30' and user_id in (select agent_id from agent_details where distributor_id='"+userId+"')";
			System.out.println(sqlQuery);
			qry = session.createSQLQuery(sqlQuery);
			int count=Integer.parseInt(qry.uniqueResult().toString());
			System.out.println(count);
			if(count<=0 || count==0){
				status="NoRecord";
			}else if(count>=20000){
				status="MoreRecord";
			}else if(count>=1 && count<=20000){
				cstmt = con.prepareCall("{call DS_reports(?,?)}");

				cstmt.setString(1, reportOf);
				cstmt.setString(2, userId);
				ResultSet rs = cstmt.executeQuery();
				status = "Success";
				FileWriter writer = new FileWriter(filePath);

//				writer.append("Master Distributor ID");
//				writer.append(';');
//				writer.append("Distributor ID");
//				writer.append(';');
				writer.append("Agent Id");
				writer.append(';');
				writer.append("Service");
				writer.append(';');
				writer.append("Customer Name");
				writer.append(';');
				writer.append("Gross Amount");
				writer.append(';');
				writer.append("Service Charge");
				writer.append(';');
				writer.append("Net Amount");
				writer.append(';');
				writer.append("Transaction Id");
				writer.append(';');
				writer.append("Request Date");
				writer.append(';');
				writer.append("Request Time");
				writer.append(';');
				writer.append("Response Date");
				writer.append(';');
				writer.append("Response Time");
				writer.append(';');
				writer.append("Status");
				writer.append(';');
				writer.append("Decline Reason");
				writer.append('\n');
					
				while (rs.next()) {
					writer.append(rs.getString(1));
					writer.append(';');
					writer.append(rs.getString(2));
					writer.append(';');
					writer.append(rs.getString(3));
					writer.append(';');
					writer.append("Pan Card");
					writer.append(';');
					writer.append(rs.getString(4));
					writer.append(';');
					writer.append(rs.getString(5));
					writer.append(';');
					writer.append(rs.getString(6));
					writer.append(';');
					writer.append(rs.getString(7));
					writer.append(';');
					writer.append(rs.getString(8));
					writer.append(';');
					writer.append(rs.getString(9));
					writer.append(';');
					writer.append(rs.getString(10));
					writer.append(';');
					writer.append(rs.getString(11));
					writer.append(';');
					writer.append(rs.getString(12));
//					writer.append(';');
//					writer.append(rs.getString(13));
//					writer.append(';');
//					writer.append(rs.getString(14));
					writer.append('\n');

				}
				writer.flush();
				writer.close();
				
			}else{
				status="ERROR";
			}
        }
        catch (Exception e) {
			status = "ERROR";
			System.out.println("Exception in  getPanCardReport ");
			e.printStackTrace();
		} finally {
			try {

				session.flush();
				session.close();
				con.close();
			} catch (Exception e) {
				System.out.println("Exception in  getPanCardReport while closing connection");
				e.printStackTrace();
			}

		}
		return status;
	}
	
	public final String getSuspectDataReport(String filePath, String reportOf, String userId) {
		
		
		try {
			session = HibernateSession.getSessionFactory().openSession();
			con = session.connection();
			sqlQuery="select count(user_id) from live_recharge where convert(varchar(10),date_of_suspect,120) between convert(varchar(10),getdate()-7,120) and convert(varchar(10),getdate(),120) and suspect_flag='1' and user_id in (select agent_id from agent_details where distributor_id='"+userId+"')";
//			sqlQuery="select count(user_id) from live_recharge where convert(varchar(10),date_of_suspect,120) between '2012-12-10' and '2012-12-30' and suspect_flag='1' and user_id in (select agent_id from agent_details where distributor_id='"+userId+"')";
			System.out.println(sqlQuery);
			qry = session.createSQLQuery(sqlQuery);
			int count=Integer.parseInt(qry.uniqueResult().toString());
			System.out.println(count);
			if(count<=0 || count==0){
				status="NoRecord";
			}else if(count>=20000){
				status="MoreRecord";
			}else if(count>=1 && count<=20000){
				
				cstmt = con.prepareCall("{call DS_reports(?,?)}");

				cstmt.setString(1, reportOf);
				cstmt.setString(2, userId);
				ResultSet rs = cstmt.executeQuery();
				status = "Success";
				FileWriter writer = new FileWriter(filePath);
            
	            writer.append("Agent ID");
	            writer.append(';');
	            writer.append("Date of recharge");
	            writer.append(';');
	            writer.append("Time of recharge");
	            writer.append(';');
	            writer.append("Operator");
	            writer.append(';');
	            writer.append("Mobile No");
	            writer.append(';');
	            writer.append("Gross Amount");
	            writer.append(';');
	            writer.append("Transaction ID");
	            writer.append(';');
	            writer.append("Vendor Transaction ID");
	            writer.append(';');
	            writer.append("Transaction Number");
	            writer.append(';');
	            writer.append("Status");
	            writer.append('\n');
	            while (rs.next()) {
	            	writer.append(rs.getString(1));
					writer.append(';');
					writer.append(rs.getString(2));
					writer.append(';');
					writer.append(rs.getString(3));
					writer.append(';');
					writer.append(rs.getString(4));
					writer.append(';');
					writer.append(rs.getString(5));
					writer.append(';');
					writer.append(rs.getString(6));
					writer.append(';');
					writer.append(rs.getString(7));
					writer.append(';');
					writer.append(rs.getString(8));
					writer.append('\n');
	            }
	            writer.flush();
	            writer.close();
	            
			}else{
				status="ERROR";
			}
		}
		catch (Exception e) {
			status = "ERROR";
			System.out.println("Exception in  getSuspectDataReport ");
			e.printStackTrace();
		} finally {
			try {

				session.flush();
				session.close();
				con.close();
			} catch (Exception e) {
				System.out.println("Exception in  getSuspectDataReport while closing connection");
				e.printStackTrace();
			}

		}
		return status;
	}
	
	public String getAirDomesticAccountReport(String filePath, String userId) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		ResultSet rs4 = null;
		ResultSet rs5 = null;
		ResultSet rs7 = null;
		ResultSet rs8 = null;
		String status = "";
		String sqlQuery = "";
		int fkOrgnDstnId = 0;
		int fkBookingId = 0;
		List<DomesticAirAccountingRecord> domesticAirAccountingRecords = new ArrayList<DomesticAirAccountingRecord>();
		Session session = null;
		Transaction txn = null;
		try {
			session = HibernateSession.getSessionFactory().openSession();

			txn = session.beginTransaction();

			con = session.connection();

			stmt = con.createStatement();
//			sqlQuery = "select CONVERT(VARCHAR(11),trans_creation_date,106) as booking_date, CONVERT(VARCHAR(11),trans_creation_date,108) as booking_time," +
//			 " fk_orgn_dstn_id, partner_ref_id, (operating_airline_code +'-'+operating_airline_flight_number) as flight," +
//			 " (departure_airport_code + '/' + arrival_airport_code) as sector, booking_class, SUBSTRING(departure_date_time,0,11) as travel_date," +
//			 " adult_fare, child_fare, infant_fare, adult_tax_breakup, child_tax_breakup, infant_tax_breakup from " +
//			 " dom_air_flight_segments where convert(varchar(10),trans_creation_date,120) between '" + fromDate + "' and '" + toDate + "' order by fk_orgn_dstn_id";
			 //System.out.println(" sql query for getting data from Flight segment is ::: " + sqlQuery);
			sqlQuery="select CONVERT(VARCHAR(11),daf.trans_creation_date,106) as booking_date, CONVERT(VARCHAR(11),daf.trans_creation_date,108) as booking_time," +
					"daf.fk_orgn_dstn_id, daf.partner_ref_id, (daf.operating_airline_code +'-'+daf.operating_airline_flight_number) as flight," +
					"(daf.departure_airport_code + '/' + daf.arrival_airport_code) as sector, daf.booking_class, SUBSTRING(daf.departure_date_time,0,11) as travel_date," +
					"daf.adult_fare, daf.child_fare, daf.infant_fare, daf.adult_tax_breakup, daf.child_tax_breakup, daf.infant_tax_breakup " +
					"from dom_air_flight_segments daf,dom_air_bookings dab where convert(varchar(10),daf.trans_creation_date,120) between " +
					" convert(varchar(10),getdate()-7,120) and convert(varchar(10),getdate(),120) and daf.partner_ref_id=dab.partner_ref_id and dab.agent_id in " +
					"(select agent_id from agent_details where distributor_id='"+userId+"') order by fk_orgn_dstn_id";
			//System.out.println(sqlQuery);
			stmt = con.createStatement();
			 rs = stmt.executeQuery(sqlQuery);
			 while(rs.next()){

			 DomesticAirAccountingRecord domesticAirAccountingRecord = new DomesticAirAccountingRecord();
			 domesticAirAccountingRecord.setPessangers(new ArrayList<PessangerForReport>());

			 // set here values required form Flight segment table

			 domesticAirAccountingRecord.setBookingDate(rs.getString("booking_date"));
			 domesticAirAccountingRecord.setBookingTime(rs.getString("booking_time"));
			 fkOrgnDstnId = rs.getInt("fk_orgn_dstn_id");
			 domesticAirAccountingRecord.setPartnerRefID(rs.getString("partner_ref_id"));
			 domesticAirAccountingRecord.setFlight(rs.getString("flight"));
			 domesticAirAccountingRecord.setSector(rs.getString("sector"));
			 domesticAirAccountingRecord.setBookingClass(rs.getString("booking_class"));
			 domesticAirAccountingRecord.setTravelDate(rs.getString("travel_date"));
			 domesticAirAccountingRecord.setAdultFare(rs.getString("adult_fare"));
			 domesticAirAccountingRecord.setChildFare(rs.getString("child_fare"));
			 domesticAirAccountingRecord.setInfantFare(rs.getString("infant_fare"));
			 domesticAirAccountingRecord.setAdultTaxBreakup(rs.getString("adult_tax_breakup"));
			 domesticAirAccountingRecord.setChildTaxBreakup(rs.getString("child_tax_breakup"));
			 domesticAirAccountingRecord.setInfantTaxBreakup(rs.getString("infant_tax_breakup"));

			 // set here values required form OriginDestination table
			 //System.out.println(" frngKyofOrgDstnID is :: " + fkOrgnDstnId);
			 stmt = con.createStatement();
			 sqlQuery = "select fk_booking_id, flight_mode, no_of_adults, no_of_children, no_of_infants, tax, s_tax, " +
			 "s_charge, t_discount, printable_fare, agent_earning, base_fare, gross_fare, " +
			 "t_charge, basic_commision_in_percentage, gross_commision_in_percentage, yq_commision_in_percentage," +
			 "fix_amount_in_ruppes, discount_on_basic, discount_on_gross, discount_on_yq, discount_on_fix_amount, " +
			 "agent_markup, tds_amount, deductable_amount from dom_air_origin_destinations where pk_orgn_dstn_id =" + fkOrgnDstnId;

			// System.out.println(" sql query for getting data from Origin destination table is ::: " + sqlQuery);

			 rs1 = stmt.executeQuery(sqlQuery);
			 while(rs1.next()){

			 fkBookingId = rs1.getInt("fk_booking_id");
			 domesticAirAccountingRecord.setJourney(rs1.getString("flight_mode"));
			 domesticAirAccountingRecord.setAdultPax(rs1.getInt("no_of_adults"));
			 domesticAirAccountingRecord.setChildPax(rs1.getInt("no_of_children"));
			 domesticAirAccountingRecord.setInfantPax(rs1.getInt("no_of_infants"));
			 domesticAirAccountingRecord.setTaxes(rs1.getString("tax"));
			 domesticAirAccountingRecord.setServiceTaxes(rs1.getInt("s_tax"));
			 domesticAirAccountingRecord.setTdsInValue(rs1.getFloat("tds_amount"));
			 domesticAirAccountingRecord.setServiceCharge(rs1.getString("s_charge"));
			 domesticAirAccountingRecord.setPartnerCommission(rs1.getString("t_discount"));
			 domesticAirAccountingRecord.setPrintableMRP(rs1.getInt("printable_fare"));
			 domesticAirAccountingRecord.setAgentCommission(rs1.getInt("agent_earning"));
			 domesticAirAccountingRecord.setBasicFare(rs1.getInt("base_fare"));
			 domesticAirAccountingRecord.setGrossFare(rs1.getInt("gross_fare"));
			 domesticAirAccountingRecord.setTransactionFees(rs1.getInt("t_charge"));
			 domesticAirAccountingRecord.setNetAmount(rs1.getFloat("deductable_amount"));
			 domesticAirAccountingRecord.setCommInBasicInPercentage(rs1.getFloat("basic_commision_in_percentage"));
			 domesticAirAccountingRecord.setCommInGrossInPercentage(rs1.getFloat("gross_commision_in_percentage"));
			 domesticAirAccountingRecord.setCommInYQInPercentage(rs1.getFloat("yq_commision_in_percentage"));
			 domesticAirAccountingRecord.setFixAgentComm(rs1.getFloat("discount_on_fix_amount"));
			 domesticAirAccountingRecord.setCommInBasicInValue(rs1.getFloat("discount_on_basic"));
			 domesticAirAccountingRecord.setCommInGrossInValue(rs1.getFloat("discount_on_gross"));
			 domesticAirAccountingRecord.setCommInYQInValue(rs1.getFloat("discount_on_yq"));
			 domesticAirAccountingRecord.setTotalAgentMarkup(rs1.getInt("agent_markup"));

			 }

			 // set here values required form Air Booking table
			 stmt = con.createStatement();
			 //System.out.println(" fkBookingId is :: " + fkBookingId);
			 sqlQuery = "select arzoo_trans_id, status, travel_mode, agent_id from dom_air_bookings where pk_booking_id = " + fkBookingId;
			// System.out.println(" sql query for getting data from Air Bookings table is ::: " + sqlQuery);

			 rs2 = stmt.executeQuery(sqlQuery);
			 while(rs2.next()){
			 domesticAirAccountingRecord.setArzooReferenceID(rs2.getString("arzoo_trans_id"));
			 domesticAirAccountingRecord.setBookingStatus(rs2.getString("status"));
			 domesticAirAccountingRecord.setJourneyType(rs2.getString("travel_mode"));
			 domesticAirAccountingRecord.setAgentID(rs2.getString("agent_id"));
			 }

			 // getting distribute id using agent id
			 stmt = con.createStatement();
			 //System.out.println(" agent Id is :: " + domesticAirAccountingRecord.getAgentID());
			 sqlQuery = "select distributor_id, agent_initial from dbo.agent_details where agent_id='" + domesticAirAccountingRecord.getAgentID() + "'";
//			 System.out.println(" sql query for getting distribute id is ::: " + sqlQuery);
			 rs3 = stmt.executeQuery(sqlQuery);
			 while(rs3.next()){
			 String agentSimpleId = domesticAirAccountingRecord.getAgentID();
			 domesticAirAccountingRecord.setAgentID(rs3.getString("agent_initial") + agentSimpleId);
			 domesticAirAccountingRecord.setDistributorID(rs3.getString("distributor_id"));
			 }

			 // getting master distribute id using distribute id
			 stmt = con.createStatement();
//			 System.out.println(" distributor id is :: " + domesticAirAccountingRecord.getDistributorID());
			 sqlQuery = "select distributor_initial, md_id from dbo.distributor_details where distributor_id='" + domesticAirAccountingRecord.getDistributorID() + "'";
//			 System.out.println(" sql query for getting master distribute id is ::: " + sqlQuery);
			 rs4 = stmt.executeQuery(sqlQuery);
			 while(rs4.next()){
			 String distributorSimpleId = domesticAirAccountingRecord.getDistributorID();
			 domesticAirAccountingRecord.setDistributorID(rs4.getString("distributor_initial") + distributorSimpleId);
			 domesticAirAccountingRecord.setMasterDistributorID(rs4.getString("md_id"));
			 }

			 // getting master distribute initial id
			 stmt = con.createStatement();
			 //System.out.println(" Master distributor id is :: " + domesticAirAccountingRecord.getMasterDistributorID());
			 sqlQuery = "select MD_initial from MD_Details where md_id='" + domesticAirAccountingRecord.getMasterDistributorID() + "'";
//			 System.out.println(" sql query for getting master distribute id is ::: " + sqlQuery);
			 rs8 = stmt.executeQuery(sqlQuery);
			 while(rs8.next()){
			 String masterDistributorSimpleId = domesticAirAccountingRecord.getMasterDistributorID();
			 domesticAirAccountingRecord.setMasterDistributorID(rs8.getString("MD_initial") + masterDistributorSimpleId);
			 }

			 // getting passenger info from passenger table
			 stmt = con.createStatement();
			 //System.out.println(" partner reference id is :: " + domesticAirAccountingRecord.getPartnerRefID());
			 sqlQuery = "select psngr_title, psngr_first_name, psngr_last_name, pk_psngr_id from dbo.dom_air_passengers where partner_ref_id ='" + domesticAirAccountingRecord.getPartnerRefID() + "'";
//			 System.out.println(" sql query for getting passenger info ::: " + sqlQuery);
			 rs7 = stmt.executeQuery(sqlQuery);
			 while(rs7.next()){
			 PessangerForReport pessangerForReport = new PessangerForReport();
			 pessangerForReport.setPsngrId(rs7.getInt("pk_psngr_id"));
			 pessangerForReport.setPsngrTitle(rs7.getString("psngr_title"));
			 pessangerForReport.setPsngrFirstName(rs7.getString("psngr_first_name"));
			 pessangerForReport.setPsngrLastName(rs7.getString("psngr_last_name"));
			 domesticAirAccountingRecord.getPessangers().add(pessangerForReport);
			 }


			 for(Object obj : domesticAirAccountingRecord.getPessangers()){
			 PessangerForReport pessangerForReport = (PessangerForReport) obj;
			 // getting ticket and pnr info of passenger info from air_details
			 stmt = con.createStatement();;
			 sqlQuery = "select top 1 confirmation_id, psngr_ticket_no from dbo.dom_air_ticket_details where psngr_id=" + pessangerForReport.getPsngrId();
//			 System.out.println(" sql query for getting passenger ticket and pnr number ::: " + sqlQuery);
			 rs5 = stmt.executeQuery(sqlQuery);
			 while(rs5.next()){
			 pessangerForReport.setPnrNo(rs5.getString("confirmation_id"));
			 pessangerForReport.seteTicketNo(rs5.getString("psngr_ticket_no"));
			 }
			 }
			 domesticAirAccountingRecords.add(domesticAirAccountingRecord);
			 }

			System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&& LIST SIZE :: "
					+ domesticAirAccountingRecords.size());
			if (domesticAirAccountingRecords != null
					&& domesticAirAccountingRecords.size() == 0) {
				status = "norecord";
			} else if (domesticAirAccountingRecords != null
					&& domesticAirAccountingRecords.size() > 100000) {
				status = "downloadnotallowed";
			}else{
				status="Success";
			FileWriter writer = new FileWriter(filePath);

			writer.append("Booking Date");
			writer.append(';');
			writer.append("Booking Time");
			writer.append(';');
			writer.append("Agent_ID");
			writer.append(';');
//			writer.append("Distributor_ID");
//			writer.append(';');
//			writer.append("Master Distributor ID");
//			writer.append(';');
//			writer.append("Partner Id");
//			writer.append(';');
			writer.append("Refrence No.");
			writer.append(';');
			writer.append("Journey Type");
			writer.append(';');
			writer.append("Journey");
			writer.append(';');
			writer.append("Flight");
			writer.append(';');
			writer.append("Sector");
			writer.append(';');
			writer.append("Booking Class");
			writer.append(';');
			writer.append("Travel Date");
			writer.append(';');
			writer.append("PNR No.");
			writer.append(';');
			writer.append("Passenger Name");
			writer.append(';');
			writer.append("E-Ticket No.");
			writer.append(';');
			writer.append("Booking Status");
			writer.append(';');
			writer.append("Adult Pax");
			writer.append(';');
			writer.append("Child Pax");
			writer.append(';');
			writer.append("Infant Pax");
			writer.append(';');
			writer.append("Adult Fare");
			writer.append(';');
			writer.append("Child Fare");
			writer.append(';');
			writer.append("Infant Fare");
			writer.append(';');
			writer.append("Basic Fare");
			writer.append(';');
			writer.append("Adult Tax Breakup");
			writer.append(';');
			writer.append("Child Tax Breakup");
			writer.append(';');
			writer.append("Infant Tax Breakup");
			writer.append(';');
			writer.append("Taxes");
			writer.append(';');
			writer.append("Service Taxes");
			writer.append(';');
			writer.append("Transaction Fees");
			writer.append(';');
			writer.append("Printable MRP");
			writer.append(';');
			writer.append("Gross WithOut Airmarkup");
			writer.append(';');
			writer.append("Transaction Fee Reversed");
			writer.append(';');
//			writer.append("Partner Commission");
//			writer.append(';');
//			writer.append("Comm In Basic%");
//			writer.append(';');
//			writer.append("Comm In Basic % Value");
//			writer.append(';');
//			writer.append("Comm In YQ%");
//			writer.append(';');
//			writer.append("Comm In YQ % Value");
//			writer.append(';');
			writer.append("Gross Fare");
			writer.append(';');
			writer.append("Comm In Gross%");
			writer.append(';');
			writer.append("Comm In Gross% value");
			writer.append(';');
			writer.append("Fix Agent Comm");
			writer.append(';');
			writer.append("Agent Commission");
			writer.append(';');
//			writer.append("Service Charge");
//			writer.append(';');
			writer.append("Net Amount");
			writer.append(';');
			writer.append("TDS IN%");
			writer.append(';');
			writer.append("TDS IN% Value");
			writer.append(';');
			writer.append("Payment Gateway Charges");
			writer.append(';');
			writer.append("Total");
			writer.append(';');
			writer.append("Agent Markup");
//			writer.append(';');
//			writer.append("DS Commissionin Rs/%");
//			writer.append(';');
//			writer.append("DS Commission");
			writer.append('\n');

			for (Object obj : domesticAirAccountingRecords) {
				
				DomesticAirAccountingRecord domesticAirAccountingRecord = (DomesticAirAccountingRecord) obj;
				
				writer.append(domesticAirAccountingRecord.getBookingDate());
				writer.append(';');
				writer.append(domesticAirAccountingRecord.getBookingTime());
				writer.append(';');
				writer.append(domesticAirAccountingRecord.getAgentID());
				writer.append(';');
//				writer.append(domesticAirAccountingRecord.getDistributorID());
//				writer.append(';');
//				writer.append(domesticAirAccountingRecord
//						.getMasterDistributorID());
//				writer.append(';');
//				writer.append(domesticAirAccountingRecord.getPartnerRefID());
//				writer.append(';');
				writer.append(domesticAirAccountingRecord.getArzooReferenceID() == null
						|| "null".equalsIgnoreCase(domesticAirAccountingRecord
								.getArzooReferenceID()) ? " "
						: domesticAirAccountingRecord.getArzooReferenceID());
				writer.append(';');
				writer.append(domesticAirAccountingRecord.getJourneyType());
				writer.append(';');
				writer.append(domesticAirAccountingRecord.getJourney());
				writer.append(';');
				writer.append(domesticAirAccountingRecord.getFlight());
				writer.append(';');
				writer.append(domesticAirAccountingRecord.getSector());
				writer.append(';');
				writer.append(domesticAirAccountingRecord.getBookingClass());
				writer.append(';');
				writer.append(domesticAirAccountingRecord.getTravelDate());
				writer.append(';');
				for (Object obj1 : domesticAirAccountingRecord.getPessangers()) {
					PessangerForReport pessangerForReport = (PessangerForReport) obj1;
					writer.append(pessangerForReport.getPnrNo());
					if (pessangerForReport != null
							&& !"null".equalsIgnoreCase(pessangerForReport
									.getPnrNo())) {
						writer.append(", ");
					}
				}
				writer.append(';');
				for (Object obj1 : domesticAirAccountingRecord.getPessangers()) {
					PessangerForReport pessangerForReport = (PessangerForReport) obj1;
					writer.append(pessangerForReport.getPsngrTitle() + " "
							+ pessangerForReport.getPsngrFirstName() + " "
							+ pessangerForReport.getPsngrLastName());
					writer.append(", ");
				}
				writer.append(';');
				for (Object obj1 : domesticAirAccountingRecord.getPessangers()) {
					PessangerForReport pessangerForReport = (PessangerForReport) obj1;
					writer.append(pessangerForReport.geteTicketNo());
					writer.append(", ");
				}
				writer.append(';');
				writer.append(domesticAirAccountingRecord.getBookingStatus());
				writer.append(';');
				writer.append(Integer.toString(domesticAirAccountingRecord
						.getAdultPax()));
				writer.append(';');
				writer.append(Integer.toString(domesticAirAccountingRecord
						.getChildPax()));
				writer.append(';');
				writer.append(Integer.toString(domesticAirAccountingRecord
						.getInfantPax()));
				writer.append(';');
				writer.append(domesticAirAccountingRecord.getAdultFare());
				writer.append(';');
				writer.append(domesticAirAccountingRecord.getChildFare() == null
						|| "null".equalsIgnoreCase(domesticAirAccountingRecord
								.getChildFare()) ? "0"
						: domesticAirAccountingRecord.getChildFare());
				writer.append(';');
				writer.append(domesticAirAccountingRecord.getInfantFare() == null
						|| "null".equalsIgnoreCase(domesticAirAccountingRecord
								.getInfantFare()) ? "0"
						: domesticAirAccountingRecord.getInfantFare());
				writer.append(';');
				writer.append(Integer.toString(domesticAirAccountingRecord
						.getBasicFare()));
				writer.append(';');
				writer.append(domesticAirAccountingRecord.getAdultTaxBreakup()
						.replaceAll(",", "/"));
				writer.append(';');
				writer.append(domesticAirAccountingRecord.getChildTaxBreakup()
						.replaceAll(",", "/"));
				writer.append(';');
				writer.append(domesticAirAccountingRecord.getInfantTaxBreakup()
						.replaceAll(",", "/"));
				writer.append(';');
				writer.append(domesticAirAccountingRecord.getTaxes());
				writer.append(';');
				writer.append(Integer.toString(domesticAirAccountingRecord
						.getServiceTaxes()));
				writer.append(';');
				writer.append(Integer.toString(domesticAirAccountingRecord
						.getTransactionFees()));
				writer.append(';');
				writer.append(Integer.toString(domesticAirAccountingRecord
						.getPrintableMRP()));
				writer.append(';');
				writer.append(Integer.toString(domesticAirAccountingRecord
						.getGrossFare()));//
				writer.append(';');
				writer.append(Integer.toString(domesticAirAccountingRecord
						.getTransactionFees()));
				writer.append(';');
//				writer.append(domesticAirAccountingRecord
//						.getPartnerCommission());
//				writer.append(';');
//				writer.append(Double.toString(domesticAirAccountingRecord
//						.getCommInBasicInPercentage()));
//				writer.append(';');
//				writer.append(Double.toString(domesticAirAccountingRecord
//						.getCommInBasicInValue()));//
//				writer.append(';');
//				writer.append(Double.toString(domesticAirAccountingRecord
//						.getCommInYQInPercentage()));
//				writer.append(';');
//				writer.append(Double.toString(domesticAirAccountingRecord
//						.getCommInYQInValue()));
//				writer.append(';');
				writer.append(Integer.toString(domesticAirAccountingRecord
						.getGrossFare()));
				writer.append(';');

				writer.append(Double.toString(domesticAirAccountingRecord
						.getCommInGrossInPercentage()));
				writer.append(';');
				writer.append(Double.toString(domesticAirAccountingRecord
						.getCommInGrossInValue()));
				writer.append(';');
				writer.append(Double.toString(domesticAirAccountingRecord
						.getFixAgentComm()));
				writer.append(';');
				writer.append(Integer.toString(domesticAirAccountingRecord
						.getAgentCommission()));
				writer.append(';');
//				writer.append(domesticAirAccountingRecord.getServiceCharge());
//				writer.append(';');

				writer.append(Double.toString(domesticAirAccountingRecord
						.getNetAmount()
						- domesticAirAccountingRecord.getTransactionFees()));
				writer.append(';');

				writer.append(domesticAirAccountingRecord.getTdsInValue() == 0 ? "0"
						: "10");
				writer.append(';');
				writer.append(Double.toString(domesticAirAccountingRecord
						.getTdsInValue()));
				writer.append(';');

				writer.append("");
				writer.append(';');

				writer.append(Double.toString(domesticAirAccountingRecord
						.getNetAmount()
						- domesticAirAccountingRecord.getTransactionFees()));
				writer.append(';');

				writer.append(Integer.toString(domesticAirAccountingRecord
						.getTotalAgentMarkup()));
//				writer.append(';');
//
//				writer.append("");
//				writer.append(';');
//				writer.append("");
				writer.append('\n');
				

			}
			writer.flush();
			writer.close();
			}

		} catch (Exception e) {
			status = "norecord";
			e.printStackTrace();
		}finally{
			try {
				con.close();
				session.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		System.out.println(status);
		return status;
	}
	public   String getAirDomesticCancellationAccountReport(
			String filePath, String userId) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		ResultSet rs4 = null;
		ResultSet rs5 = null;
		ResultSet rs6 = null;
		ResultSet rs7 = null;
		ResultSet rs8 = null;
		ResultSet rs9 = null;
		String status = "";
		List<Integer> basicFares = new ArrayList<Integer>();
		List<Integer> grossFares = new ArrayList<Integer>();
		List<Integer> taxes = new ArrayList<Integer>();
		List<Integer> serviceTaxs = new ArrayList<Integer>();
		List<Integer> tPartnerCommissions = new ArrayList<Integer>();
		float tds = 0;
		String sqlQuery = "";
		String cancellationId = "";
		List<DomesticAirCancellatioAccountingRecord> domesticAirCancellatioAccountingRecords = new ArrayList<DomesticAirCancellatioAccountingRecord>();
		Session session = null;
		Transaction txn = null;
		try {
			session = HibernateSession.getSessionFactory().openSession();

			txn = session.beginTransaction();

			con = session.connection();

			stmt = con.createStatement();
//			sqlQuery = "select distinct cancelation_id, partner_ref_id from dom_air_ticket_details where cancelation_id is not null " +
//            "and cancelation_status='Processed' or cancelation_status_return='Processed' or cancelation_status_depart='Processed' " +
//            "and convert(varchar(10),trans_creation_date,120) between '" + fromDate + "' and  '" + toDate + "' order by cancelation_id desc ";
//System.out.println("    sql query for getting data from Air ticket details is :::    " + sqlQuery);
			sqlQuery="select distinct dot.cancelation_id, dot.partner_ref_id from dom_air_ticket_details dot,dom_air_bookings dab where cancelation_id is not null and (dot.cancelation_status='Processed' or dot.cancelation_status_return='Processed' or dot.cancelation_status_depart='Processed')and convert(varchar(10),dot.trans_creation_date,120) between convert(varchar(10),getdate()-7,120) and convert(varchar(10),getdate(),120) and dot.partner_ref_id=dab.partner_ref_id and dab.agent_id in (select agent_id from agent_details where distributor_id='"+userId+"') order by dot.cancelation_id desc";
//			System.out.println(sqlQuery);
			stmt = con.createStatement();
rs = stmt.executeQuery(sqlQuery);
while(rs.next()){
      
      DomesticAirCancellatioAccountingRecord domesticAirCancellatioAccountingRecord =  new DomesticAirCancellatioAccountingRecord();
      domesticAirCancellatioAccountingRecord.setPessangers(new ArrayList<PessangerForReport>());
      domesticAirCancellatioAccountingRecord.setPartnerRefID(rs.getString("partner_ref_id"));
      cancellationId = rs.getString("cancelation_id");
      
      
      // set here values required form OriginDestination table
      stmt = con.createStatement();
      sqlQuery = "select top 1 (operating_airline_code +'-'+operating_airline_flight_number) as flight," +
                  "(departure_airport_code + '/' + arrival_airport_code) as sector," +
                  "SUBSTRING(departure_date_time,0,11) as travel_date from dom_air_flight_segments" +
                  " where partner_ref_id = '" + domesticAirCancellatioAccountingRecord.getPartnerRefID() + "'";
      //System.out.println("    sql query for getting data from Origin destination table is :::    " + sqlQuery);
      rs1 = stmt.executeQuery(sqlQuery);
      while(rs1.next()){
      
            domesticAirCancellatioAccountingRecord.setFlight(rs1.getString("flight"));
            domesticAirCancellatioAccountingRecord.setSector(rs1.getString("sector"));
            domesticAirCancellatioAccountingRecord.setTravelDate(rs1.getString("travel_date"));
      }
      
      // create here catalog in database with this name *agent_transaction_catalog*
      // getting account info from account transaction detail table
      stmt = con.createStatement();
      //System.out.println("    partner reference id  is :: " + domesticAirCancellatioAccountingRecord.getPartnerRefID());
      //System.out.println("    cancellation Id  is :: " + cancellationId);
      sqlQuery = "select Agent_balAmt_b_Ded, DeductedAmt, Agent_balAmt_A_Ded, Tds_Amount from dbo.agent_transaction_details " +
                  "where  Transaction_No = '" + domesticAirCancellatioAccountingRecord.getPartnerRefID() + "' and  " +
                              "SUBSTRING(Remark,47,6) = '" + cancellationId + "'";
      //System.out.println("    sql query for getting account statemnet from account transaction isis :::    " + sqlQuery);                           
      rs2 = stmt.executeQuery(sqlQuery);
      while(rs2.next()){
            domesticAirCancellatioAccountingRecord.setOpeningbalance(rs2.getFloat("Agent_balAmt_b_Ded"));
            domesticAirCancellatioAccountingRecord.setRefundedAmount(rs2.getFloat("DeductedAmt"));
            domesticAirCancellatioAccountingRecord.setClosingBalance(rs2.getFloat("Agent_balAmt_A_Ded"));
            domesticAirCancellatioAccountingRecord.setTdsInPercentage(rs2.getFloat("Tds_Amount"));
      }
      
      // set here values required form Air Booking table
      stmt = con.createStatement();
      sqlQuery = "select arzoo_trans_id, status, travel_mode, agent_id from dom_air_bookings " +
                  "where partner_ref_id = '" + domesticAirCancellatioAccountingRecord.getPartnerRefID() + "'";
      //System.out.println("    sql query for getting data from Air Bookings table is :::    " + sqlQuery);
      
      rs3 = stmt.executeQuery(sqlQuery);
      while(rs3.next()){
            domesticAirCancellatioAccountingRecord.setArzooReferenceID(rs3.getString("arzoo_trans_id"));
            domesticAirCancellatioAccountingRecord.setJourneyType(rs3.getString("travel_mode"));
            domesticAirCancellatioAccountingRecord.setAgentID(rs3.getString("agent_id"));
      }
      
      // set here values required form Air Booking table
      stmt = con.createStatement();
      //System.out.println(" agent Id is :: " + domesticAirCancellatioAccountingRecord.getAgentID());
      sqlQuery = "select agent_initial from dbo.agent_details where agent_id='" + domesticAirCancellatioAccountingRecord.getAgentID() + "'";
      //System.out.println(" sql query for getting distribute id is ::: " + sqlQuery);
      rs9 = stmt.executeQuery(sqlQuery);
      while(rs9.next()){
      String agentSimpleId = domesticAirCancellatioAccountingRecord.getAgentID();
      domesticAirCancellatioAccountingRecord.setAgentID(rs9.getString("agent_initial") + agentSimpleId);
      }

      
      // set here values required form OriginDestination table
      stmt = con.createStatement();
      sqlQuery = "select airport_tax, s_tax, gross_fare, base_fare, tds_amount, t_partner_commision from " +
                  "dbo.dom_air_origin_destinations where partner_ref_id = '" + domesticAirCancellatioAccountingRecord.getPartnerRefID() + "'";
      
      //System.out.println("    sql query for getting data from Origin destination table is :::    " + sqlQuery);
      
      rs4 = stmt.executeQuery(sqlQuery);
      while(rs4.next()){
            taxes.add(rs4.getInt("airport_tax"));
            serviceTaxs.add(rs4.getInt("s_tax"));
            grossFares.add(rs4.getInt("gross_fare"));
            tds = rs4.getFloat("tds_amount");
            basicFares.add(rs4.getInt("base_fare"));
            tPartnerCommissions.add(Integer.parseInt(rs4.getString("t_partner_commision")));
      }
      //System.out.println("Tarvel Mode is " + domesticAirCancellatioAccountingRecord.getJourneyType());
      if("Round".equalsIgnoreCase(domesticAirCancellatioAccountingRecord.getJourneyType())){
            domesticAirCancellatioAccountingRecord.setTaxes(taxes.get(0) + taxes.get(1));
            domesticAirCancellatioAccountingRecord.setServiceTaxes(serviceTaxs.get(0) + serviceTaxs.get(1));
            domesticAirCancellatioAccountingRecord.setGrossFare(grossFares.get(0) + grossFares.get(1));
            domesticAirCancellatioAccountingRecord.setBasicFare(basicFares.get(0) + basicFares.get(1));
            domesticAirCancellatioAccountingRecord.setPartnerCommission(tPartnerCommissions.get(0) + tPartnerCommissions.get(1));
            domesticAirCancellatioAccountingRecord.setTdsInPercentage(tds);
      } else {
            domesticAirCancellatioAccountingRecord.setTaxes(taxes.get(0));
            domesticAirCancellatioAccountingRecord.setServiceTaxes(serviceTaxs.get(0));
            domesticAirCancellatioAccountingRecord.setGrossFare(grossFares.get(0));
            domesticAirCancellatioAccountingRecord.setBasicFare(basicFares.get(0));
            domesticAirCancellatioAccountingRecord.setPartnerCommission(tPartnerCommissions.get(0));
      }
      
      
      // getting master distribute id using distribute id
      stmt = con.createStatement();
      sqlQuery = "select distinct psngr_id, confirmation_id, psngr_ticket_no from dbo.dom_air_ticket_details " +
                  "where partner_ref_id = '" + domesticAirCancellatioAccountingRecord.getPartnerRefID() + "'";
      //System.out.println("    sql query for getting master distribute id is :::    " + sqlQuery);
      
      rs5 = stmt.executeQuery(sqlQuery);
      while(rs5.next()){
            PessangerForReport pessangerForReport = new PessangerForReport();
            pessangerForReport.setPnrNo(rs5.getString("confirmation_id"));
            pessangerForReport.seteTicketNo(rs5.getString("psngr_ticket_no"));
            stmt = con.createStatement();
            sqlQuery = "select psngr_title, psngr_first_name, psngr_last_name, psngr_type from " +
                        "dbo.dom_air_passengers where pk_psngr_id=" + rs5.getInt("psngr_id");
            //System.out.println("    sql query for getting master distribute id is :::    " + sqlQuery);
            rs6 = stmt.executeQuery(sqlQuery);
            while(rs6.next()){
                  pessangerForReport.setPsngrTitle(rs6.getString("psngr_title"));
                  pessangerForReport.setPsngrFirstName(rs6.getString("psngr_first_name"));
                  pessangerForReport.setPsngrLastName(rs6.getString("psngr_last_name"));
                  pessangerForReport.setType(rs6.getString("psngr_type"));                                        
            }
            domesticAirCancellatioAccountingRecord.getPessangers().add(pessangerForReport);
      }
      
      // getting account info from account transaction detail table
      stmt = con.createStatement();
      sqlQuery = "select top 1 cancelation_status_return, cancelation_status_depart, cancelation_status from dbo.dom_air_ticket_details where cancelation_id='" + cancellationId + "'";
      //System.out.println("    sql query for getting account statemnet from account transaction isis :::    " + sqlQuery);                           
      rs7 = stmt.executeQuery(sqlQuery);
      while(rs7.next()){
            if("Processed".equalsIgnoreCase(rs7.getString("cancelation_status_return"))){
                  stmt = con.createStatement();
                  //sqlQuery = "select top 1 cancellation_charges_return, cancellation_process_datetime_return from dbo.dom_air_ticket_details where cancelation_id='" + cancellationId + "'";
                  sqlQuery   ="select top 1 cancellation_charges_return,convert(varchar(10),cancellation_process_datetime_return,120) as date,SubString(cancellation_process_datetime_return,12,20) AS time from dbo.dom_air_ticket_details where cancelation_id='" + cancellationId + "'";
                  //System.out.println("    sql query for getting account statemnet from account transaction isis :::    " + sqlQuery);                           
                  rs8 = stmt.executeQuery(sqlQuery);
                  while (rs8.next()) {
                        domesticAirCancellatioAccountingRecord.setCancellationCharges(rs8.getString("cancellation_charges_return"));
                        domesticAirCancellatioAccountingRecord.setCancellationDate(rs8.getString("date"));
                        domesticAirCancellatioAccountingRecord.setCancellationTime(rs8.getString("time"));
                  }
            } else if("Processed".equalsIgnoreCase(rs7.getString("cancelation_status_depart"))){
                  stmt = con.createStatement();
                  //sqlQuery = "select top 1 cancellation_charges_depart, cancellation_process_datetime_depart from dbo.dom_air_ticket_details where cancelation_id='" + cancellationId + "'";
                  sqlQuery="select top 1 cancellation_charges_depart, convert(varchar(10),cancellation_process_datetime_depart,120) as date,SubString(cancellation_process_datetime_depart,12,20) AS time from dbo.dom_air_ticket_details where cancelation_id='" + cancellationId + "'";
                  //System.out.println("    sql query for getting account statemnet from account transaction isis :::    " + sqlQuery);                           
                  rs8 = stmt.executeQuery(sqlQuery);
                  while (rs8.next()) {
                        domesticAirCancellatioAccountingRecord.setCancellationCharges(rs8.getString("cancellation_charges_depart"));
                        domesticAirCancellatioAccountingRecord.setCancellationDate(rs8.getString("date"));
                        domesticAirCancellatioAccountingRecord.setCancellationTime(rs8.getString("time"));
                  }
            } else if("Processed".equalsIgnoreCase(rs7.getString("cancelation_status"))){
                  stmt = con.createStatement();
                  //sqlQuery = "select top 1 cancellation_charges, cancellation_process_DateTime from dbo.dom_air_ticket_details where cancelation_id='" + cancellationId + "'";
                  sqlQuery="select top 1 cancellation_charges, convert(varchar(10),cancellation_process_DateTime,120) as date,SubString(cancellation_process_DateTime,12,20) AS time from dbo.dom_air_ticket_details where cancelation_id='" + cancellationId + "'";
                  //System.out.println("    sql query for getting account statemnet from account transaction isis :::    " + sqlQuery);                           
                  rs8 = stmt.executeQuery(sqlQuery);
                  while (rs8.next()) {
                        domesticAirCancellatioAccountingRecord.setCancellationCharges(rs8.getString("cancellation_charges"));
                        domesticAirCancellatioAccountingRecord.setCancellationDate(rs8.getString("date"));
                        domesticAirCancellatioAccountingRecord.setCancellationTime(rs8.getString("time"));
                  }
            }
      }

      domesticAirCancellatioAccountingRecords.add(domesticAirCancellatioAccountingRecord);
      
}
			 System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&   LIST SIZE ::  "
			 + domesticAirCancellatioAccountingRecords.size());
			if (domesticAirCancellatioAccountingRecords != null
					&& domesticAirCancellatioAccountingRecords.size() == 0) {
				status = "norecord";
			} else if (domesticAirCancellatioAccountingRecords != null
					&& domesticAirCancellatioAccountingRecords.size() > 100000) {
				status = "downloadnotallowed";
			}else{
				status="Success";
			FileWriter writer = new FileWriter(filePath);
//			System.out.println("we are about to create file");
			
			writer.append("Agent_ID");
			writer.append(';');
			writer.append("Cancellation Date");
			writer.append(';');
			writer.append("Cancellation Time");
			writer.append(';');
//			writer.append("Partner Id");
//			writer.append(';');
			writer.append("Refrence No.");
			writer.append(';');
			writer.append("Journey Type");
			writer.append(';');
			writer.append("Journey");
			writer.append(';');
			writer.append("Flight");
			writer.append(';');
			writer.append("Sector");
			writer.append(';');
			writer.append("Travel Date");
			writer.append(';');
			writer.append("PNR No.");
			writer.append(';');
			writer.append("Passenger Name");
			writer.append(';');
			writer.append("Passanger Ticket No");
			writer.append(';');
			writer.append("Basic Fare");
			writer.append(';');
			writer.append("Taxes");
			writer.append(';');
			writer.append("Service Taxes");
			writer.append(';');
			writer.append("TDS IN% Value");
			writer.append(';');
			writer.append("Gross Total");
			writer.append(';');
			writer.append("Cancellation Charges");
			writer.append(';');
			writer.append("Discount Recalled");
			writer.append(';');
			writer.append("Refunded Amount");
//			writer.append(';');
//			writer.append("Opening balance");
//			writer.append(';');
//			writer.append("Closing Balance");
			writer.append('\n');

			for (Object obj : domesticAirCancellatioAccountingRecords) {
				DomesticAirCancellatioAccountingRecord DomesticAirCanRecord = (DomesticAirCancellatioAccountingRecord) obj;
				
				writer.append(DomesticAirCanRecord.getAgentID());
				writer.append(';');
				writer.append(DomesticAirCanRecord.getCancellationDate());
				writer.append(';');
				writer.append(DomesticAirCanRecord.getCancellationTime());//
				writer.append(';');
//				writer.append(DomesticAirCanRecord.getPartnerRefID());//
//				writer.append(';');
				writer.append(DomesticAirCanRecord.getArzooReferenceID());//
				writer.append(';');
				writer.append(DomesticAirCanRecord.getJourneyType());
				writer.append(';');
				writer.append(DomesticAirCanRecord.getJourneyType());
				writer.append(';');
				writer.append(DomesticAirCanRecord.getFlight());
				writer.append(';');
				writer.append(DomesticAirCanRecord.getSector());
				writer.append(';');
				writer.append(DomesticAirCanRecord.getTravelDate());
				writer.append(';');
				for (Object obj1 : DomesticAirCanRecord.getPessangers()) {
					PessangerForReport pessangerForReport = (PessangerForReport) obj1;
					writer.append(pessangerForReport.getPnrNo());
					writer.append(", ");
				}
				writer.append(';');
				for (Object obj1 : DomesticAirCanRecord.getPessangers()) {
					PessangerForReport pessangerForReport = (PessangerForReport) obj1;
					writer.append(pessangerForReport.getPsngrTitle() + " "
							+ pessangerForReport.getPsngrFirstName() + " "
							+ pessangerForReport.getPsngrLastName());
					writer.append(", ");
				}
				writer.append(';');
				for (Object obj1 : DomesticAirCanRecord.getPessangers()) {
					PessangerForReport pessangerForReport = (PessangerForReport) obj1;
					writer.append(pessangerForReport.geteTicketNo());
					writer.append(", ");
				}
				writer.append(';');
				writer.append(Integer.toString(DomesticAirCanRecord
						.getBasicFare()));
				writer.append(';');
				writer.append(Integer.toString(DomesticAirCanRecord.getTaxes()));
				writer.append(';');
				writer.append(Integer.toString(DomesticAirCanRecord
						.getServiceTaxes()));
				writer.append(';');
				writer.append(Double.toString(DomesticAirCanRecord
						.getTdsInPercentage()));
				writer.append(';');
				writer.append(Integer.toString(DomesticAirCanRecord
						.getGrossFare()));
				writer.append(';');
				writer.append(DomesticAirCanRecord.getCancellationCharges());
				writer.append(';');
				writer.append(String.valueOf(DomesticAirCanRecord
						.getPartnerCommission()));
				writer.append(';');
				writer.append(Double.toString(DomesticAirCanRecord
						.getRefundedAmount()));
//				writer.append(';');
//				writer.append(Double.toString(DomesticAirCanRecord
//						.getOpeningbalance()));
//				writer.append(';');
//				writer.append(Double.toString(DomesticAirCanRecord
//						.getClosingBalance()));
				writer.append('\n');
			}
			writer.flush();
			writer.close();
			}
		} catch (Exception e) {
			status = "norecord";
			e.printStackTrace();
		}finally{
			
			try {
				session.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(status);
		return status;
	}
}

