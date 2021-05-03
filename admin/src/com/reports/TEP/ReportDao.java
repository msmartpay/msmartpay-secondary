package com.reports.TEP;

/*
 * Update By      : Manoj
 * Updated Date   :22 may 2012
 * Updated matter : convert all report into CSV and provide RAR file.
 */

import java.io.FileWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.common.HibernateSession;
import com.flights.domestic.services.pojo.DomesticAirAccountingRecord;
import com.flights.domestic.services.pojo.DomesticAirCancellatioAccountingRecord;
import com.flights.domestic.services.pojo.PessangerForReport;

public class ReportDao {

	@SuppressWarnings("deprecation")
	public  String getAirTransactionReport(String filePath,String fromDate, String toDate) 
	{
		Session session = null;
		Transaction txn = null;
		String Status = "Norecord";
		Connection con =null;
		try 
		{
			session = HibernateSession.getSessionFactory().openSession();
			txn = session.beginTransaction();
			con = session.connection();
			CallableStatement cstmt = null;

			cstmt = con.prepareCall("{call udpAir_Download_Report(?,?)}");
			cstmt.setString(1, toDate);
			cstmt.setString(2, fromDate);
			ResultSet rs = cstmt.executeQuery();
			txn.commit();

			Status = "Success";
			FileWriter writer = new FileWriter(filePath);
			writer.append("Transaction Date");
			writer.append('\t');
			writer.append("Transaction Time");
			writer.append('\t');
			writer.append("Refrence No.");
			writer.append('\t');
			writer.append("Agent ID");
			writer.append('\t');
			writer.append("Distributor ID");
			writer.append('\t');
			writer.append("Partner Id");
			writer.append('\t');
			writer.append("Journey Type");
			writer.append('\t');
			writer.append("Journey");
			writer.append('\t');
			writer.append("Flight");
			writer.append('\t');
			writer.append("Sector");
			writer.append('\t');
			writer.append("Class");
			writer.append('\t');
			writer.append("Travel Date");
			writer.append('\t');
			writer.append("PNR No.");
			writer.append('\t');
			writer.append("Booking Status");
			writer.append('\t');
			writer.append("Adult Pax");
			writer.append('\t');
			writer.append("Child Pax");
			writer.append('\t');
			writer.append("Infant Pax");
			writer.append('\t');
			writer.append("Adult Fare");
			writer.append('\t');
			writer.append("Child Fare");
			writer.append('\t');
			writer.append("Infant Fare");
			writer.append('\t');
			writer.append("Basic Fare");
			writer.append('\t');
			writer.append("Adult Tax Breakup");
			writer.append('\t');
			writer.append("Child Tax Breakup");
			writer.append('\t');
			writer.append("Infant Tax Breakup");
			writer.append('\t');
			writer.append("Taxes");
			writer.append('\t');
			writer.append("Service Taxes");
			writer.append('\t');
			writer.append("Transaction Fees");
			writer.append('\t');
			writer.append("Gross Total");
			writer.append('\t');
			writer.append("Transaction Fee Reversed");
			writer.append('\t');
			writer.append("Partner Commission");
			writer.append('\t');
			writer.append("Comm In Basic %");
			writer.append('\t');
			writer.append("Comm In Basic % Value");
			writer.append('\t');
			writer.append("Comm In YQ%");
			writer.append('\t');
			writer.append("Comm In YQ % Value");
			writer.append('\t');
			writer.append("Comm In Gross%");
			writer.append('\t');
			writer.append("Comm In Gross% value");
			writer.append('\t');
			writer.append("Fix Agent Comm");
			writer.append('\t');
			writer.append("Agent Commission");
			writer.append('\t');
			writer.append("Service Charge");
			writer.append('\t');
			writer.append("Net Amount");
			writer.append('\t');
			writer.append("TDS IN%");
			writer.append('\t');
			writer.append("TDS IN% Value");
			writer.append('\t');
			writer.append("Payment Gateway Charges");
			writer.append('\t');
			writer.append("Total");
			writer.append('\t');
			writer.append("Agent Markup");
			writer.append('\t');
			writer.append("DS Commissionin Rs/%");
			writer.append('\t');
			writer.append("DS Commission");
			writer.append('\t');
			writer.append("Opening balance");
			writer.append('\t');
			writer.append("Closing Balance");
			writer.append('\n');

			while (rs.next()) 
			{
				writer.append(rs.getString(2));
				writer.append('\t');
				writer.append(rs.getString(3));
				writer.append('\t');
				writer.append(rs.getString(9));
				writer.append('\t');
				writer.append(rs.getString(4));
				writer.append('\t');
				writer.append(rs.getString(5));
				writer.append('\t');
				writer.append(rs.getString(8));
				writer.append('\t');
				writer.append(rs.getString(10));
				writer.append('\t');
				writer.append(rs.getString(13));
				writer.append('\t');
				writer.append(rs.getString(16));
				writer.append('\t');
				writer.append(rs.getString(12));
				writer.append('\t');
				writer.append(rs.getString(18));
				writer.append('\t');
				writer.append(rs.getString(11));
				writer.append('\t');
				writer.append(rs.getString(15));
				writer.append('\t');
				writer.append(rs.getString(19));
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
				writer.append(rs.getString(35));
				writer.append('\t');
				writer.append(rs.getString(36));
				writer.append('\t');
				writer.append(rs.getString(37));
				writer.append('\t');
				writer.append(rs.getString(28));
				writer.append('\t');
				writer.append(rs.getString(29));
				writer.append('\t');
				writer.append(rs.getString(30));
				writer.append('\t');
				writer.append(rs.getString(34));
				writer.append('\t');
				writer.append(rs.getString(30));
				writer.append('\t');
				writer.append(rs.getString(44));
				writer.append('\t');
				writer.append(rs.getString(38));
				writer.append('\t');
				writer.append(rs.getString(39));
				writer.append('\t');
				writer.append(rs.getString(40));
				writer.append('\t');
				writer.append(rs.getString(41));
				writer.append('\t');
				writer.append(rs.getString(42));
				writer.append('\t');
				writer.append(rs.getString(43));
				writer.append('\t');
				writer.append(rs.getString(44));
				writer.append('\t');
				writer.append(rs.getString(46));
				writer.append('\t');
				writer.append(rs.getString(32));
				writer.append('\t');
				writer.append(rs.getString(49));
				writer.append('\t');
				writer.append(rs.getString(47));
				writer.append('\t');
				writer.append(rs.getString(48));
				writer.append('\t');
				writer.append("0");
				writer.append('\t');
				writer.append(rs.getString(49));
				writer.append('\t');
				writer.append(rs.getString(33));
				writer.append('\t');
				writer.append(rs.getString(50));
				writer.append('\t');
				writer.append(rs.getString(51));
				writer.append('\t');
				writer.append(rs.getString(53));
				writer.append('\t');
				writer.append(rs.getString(54));
				writer.append('\n');
			}
			writer.flush();
			writer.close();
		} catch (Exception e) {
			Status = "Norecord";
			if (txn != null) {
				try {
					txn.rollback();

				} catch (Exception ex) {
					System.out.println("Exception in ReportDao,getAirTransactionReport");
					System.out.println(e.toString());
				}
			}
		} finally {
			try {
				if(con!=null)con.close();
				if(session!=null)session.close();

			} catch (Exception e) {
				System.out.println("Exception in ReportDao,getAirTransactionReport");
				System.out.println(e.toString());
			}
		}
		return Status;
	}

	@SuppressWarnings("deprecation")
	public  String getAccountAdjustmentReport(String filePath,String fromDate, String toDate, String reportOf, String loginType, String portalId) 
	{
		Session session = null;
		Transaction txn = null;
		String Status = "Norecord";
		Connection con =null;
		try {
			session = HibernateSession.getSessionFactory().openSession();

			txn = session.beginTransaction();

			con = session.connection();

			CallableStatement cstmt = null;

			cstmt = con.prepareCall("{call Admin_reports(?,?,?,?,?)}");

			cstmt.setString(1, reportOf);
			cstmt.setString(2, fromDate);
			cstmt.setString(3, toDate);
			cstmt.setString(4, loginType);
			cstmt.setString(5, portalId);

			ResultSet rs = cstmt.executeQuery();

			txn.commit();
			Status = "Success";
			FileWriter writer = new FileWriter(filePath);

			writer.append("Transaction Date");
			writer.append('\t');
			writer.append("Transaction Time");
			writer.append('\t');
			writer.append("User Id");
			writer.append('\t');
			writer.append("User Type");
			writer.append('\t');
			writer.append("Transaction No");
			writer.append('\t');
			writer.append("Total Cash");
			writer.append('\t');
			writer.append("Used Cash");
			writer.append('\t');
			writer.append("Balance");
			writer.append('\t');
			writer.append("Txn. Type");
			writer.append('\t');
			writer.append("Adjusted Account");
			writer.append('\t');
			writer.append("Txn Amount");
			writer.append('\t');
			writer.append("Final Balance");
			writer.append('\t');
			writer.append("Updated User");
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
				writer.append('\n');
			}
			writer.flush();
			writer.close();
		} catch (Exception e) {
			Status = "Norecord";

			System.out.println("Exception in ReportDao,getAccountAdjustmentReport");
			System.out.println(e.toString());
			if (txn != null) {

				try {
					txn.rollback();

				} catch (Exception ex) {
				}
			}
		} finally {
			try {
				con.close();
				session.flush();
				session.close();

			} catch (Exception e) {
				System.out.println("Exception in ReportDao,getAccountAdjustmentReport");
				System.out.println(e.toString());}
		}
		return Status;
	}

	public  String getAgentAmountReport(String filePath, String fromDate,String toDate, String reportOf, String loginType, String portalId) {

		Session session = null;
		String Status = "Norecord";
		Connection con =null;

		try {

			session = HibernateSession.getSessionFactory().openSession();
			con = session.connection();

			CallableStatement cstmt = null;

			cstmt = con.prepareCall("{call Admin_reports(?,?,?,?,?)}");

			cstmt.setString(1, reportOf);
			cstmt.setString(2, fromDate);
			cstmt.setString(3, toDate);
			cstmt.setString(4, loginType);
			cstmt.setString(5, portalId);

			ResultSet rs = cstmt.executeQuery();


			Status = "Success";
			FileWriter writer = new FileWriter(filePath);

			writer.append("AGENT_ID");
			writer.append('\t');
			writer.append("AgencyName");
			writer.append('\t');
			writer.append("TotCash");
			writer.append('\t');
			writer.append("Usedcash");
			writer.append('\t');
			writer.append("LastAmount");
			writer.append('\n');

			while (rs.next()) 
			{
				String agent_id = rs.getString(1);
				String agent_initial = rs.getString(2);
				String AgentID = agent_initial + agent_id;
				writer.append(AgentID);
				writer.append('\t');
				writer.append(rs.getString(3));
				writer.append('\t');
				writer.append(rs.getString(4));
				writer.append('\t');
				writer.append(rs.getString(5));
				writer.append('\t');
				writer.append(rs.getString(6));
				writer.append('\n');
			}
			writer.flush();
			writer.close();
		} catch (Exception e)
		{
			Status = "Norecord";
			System.out.println("Exception in ReportDao,getAgentAmountReport");
			System.out.println(e.toString());
		} finally {
			try {
				if(con!=null)con.close();
				if(session!=null)session.close();

			} catch (Exception e) {
				System.out.println("Exception in ReportDao,getAgentAmountReport");
				System.out.println(e.toString());
			}
		}
		return Status;
	}

	public  String getAgentDetailsReport(String filePath,String fromDate, String toDate, String reportOf, String loginType, String portalId)
	{

		Session session = null;
		String Status = "Norecord";
		ResultSet rs =null;
		session = HibernateSession.getSessionFactory().openSession();
		try(Connection con = session.connection();CallableStatement cstmt=con.prepareCall("{call Admin_reports(?,?,?,?,?)}");) 
		{
			session = HibernateSession.getSessionFactory().openSession();

			cstmt.setString(1, reportOf);
			cstmt.setString(2, fromDate);
			cstmt.setString(3, toDate);
			cstmt.setString(4, loginType);
			cstmt.setString(5, portalId);

			rs = cstmt.executeQuery();

			Status = "Success";
			FileWriter writer = new FileWriter(filePath);
			writer.append("AgentId");
			writer.append('\t');
			writer.append("Dist Id");
			writer.append('\t');
			writer.append("Agent Initial");
			writer.append('\t');
			writer.append("Agency Name");
			writer.append('\t');
			writer.append("Agent name");
			writer.append('\t');
			writer.append("Address1");
			writer.append('\t');
			writer.append("Address2");
			writer.append('\t');
			writer.append("Pin Code");
			writer.append('\t');
			writer.append("City");
			writer.append('\t');
			writer.append("District");
			writer.append('\t');
			writer.append("State");
			writer.append('\t');
			writer.append("Country");
			writer.append('\t');
			writer.append("Email Id");
			writer.append('\t');
			writer.append("Mobile No");
			writer.append('\t');
			writer.append("date_of_joining");
			writer.append('\t');
			writer.append("Flight");
			writer.append('\t');
			writer.append("Bus");
			writer.append('\t');
			writer.append("Hotel");
			writer.append('\t');
			writer.append("Recharge");
			writer.append('\t');
			writer.append("DTHX");
			writer.append('\t');
			writer.append("Utility");
			writer.append('\t');
			writer.append("Add Monry");
			writer.append('\t');
			writer.append("Test Merit");
			writer.append('\t');
			writer.append("Holiday");
			writer.append('\t');
			writer.append("DateOfBirth");
			writer.append('\t');
			writer.append("Agent_MobileNo");
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
			Status = "Norecord";
			e.printStackTrace();
			System.out.println("Exception in ReportDao,getAgentDetailsReport");
		} finally {
			try {
				if(rs!=null)
					rs.close();
				if(session!=null)
					session.close();

			} catch (Exception e) {
				System.out.println("Exception in ReportDao,getAgentDetailsReport");
				System.out.println(e.toString());
			}
		}
		return Status;
	}

	public  String getAgentJournalReport(String filePath,String fromDate, String toDate, String reportOf, String loginType, String portalId) {

		Session session = null;
		String Status = "Norecord";
		Connection con =null;
		try {
			session = HibernateSession.getSessionFactory().openSession();

			con = session.connection();

			CallableStatement cstmt = null;

			cstmt = con.prepareCall("{call Admin_reports(?,?,?,?,?)}");

			cstmt.setString(1, reportOf);
			cstmt.setString(2, fromDate);
			cstmt.setString(3, toDate);
			cstmt.setString(4, loginType);
			cstmt.setString(5, portalId);

			ResultSet rs = cstmt.executeQuery();


			Status = "Success";
			FileWriter writer = new FileWriter(filePath);

			writer.append("Distributor Id");
			writer.append('\t');
			writer.append("Agent Id");
			writer.append('\t');
			writer.append("Journal_Id");
			writer.append('\t');
			writer.append("Request date");
			writer.append('\t');
			writer.append("Request Time");
			writer.append('\t');
			writer.append("Mode_Of_Payment");
			writer.append('\t');
			writer.append("Amount");
			writer.append('\t');
			writer.append("Bank Charges");
			writer.append('\t');
			writer.append("Accepted_amount");
			writer.append('\t');
			writer.append("Bank_Name");
			writer.append('\t');
			writer.append("Branch");
			writer.append('\t');
			writer.append("Transaction Id");
			writer.append('\t');
			writer.append("Reciept No");
			writer.append('\t');
			writer.append("Deposit_Date");
			writer.append('\t');
			writer.append("Cheque_DD_Date");
			writer.append('\t');
			writer.append("Cheque_DD_No");
			writer.append('\t');
			writer.append("Approval_Date");
			writer.append('\t');
			writer.append("Approved By");
			writer.append('\t');
			writer.append("Status");
			writer.append('\t');
			writer.append("User Account No");
			writer.append('\t');
			writer.append("Company Account No");
			writer.append('\t');
			writer.append("Order Id");
			writer.append('\t');
			writer.append("Reference ID");
			writer.append('\t');
			writer.append("BC Remarks");
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
			Status = "Norecord";
			System.out.println("Exception in ReportDao,getAgentJournalReport");
			System.out.println(e.toString());
		} finally {
			try {
				if(con!=null)con.close();
				if(session!=null)session.close();

			} catch (Exception e) {
				System.out.println("Exception in ReportDao,getAgentJournalReport");
				System.out.println(e.toString());
			}
		}
		return Status;
	}

	public   String getAgentRegistrationReport(String filePath,String fromDate, String toDate, String reportOf, String loginType, String portalId) {

		Session session = null;
		String Status = "Norecord";
		Connection con =null;

		try {
			session = HibernateSession.getSessionFactory().openSession();


			con = session.connection();

			CallableStatement cstmt = null;

			cstmt = con.prepareCall("{call Admin_reports(?,?,?,?,?)}");

			cstmt.setString(1, reportOf);
			cstmt.setString(2, fromDate);
			cstmt.setString(3, toDate);
			cstmt.setString(4, loginType);
			cstmt.setString(5, portalId);

			ResultSet rs = cstmt.executeQuery();


			Status = "Success";
			FileWriter writer = new FileWriter(filePath);

			writer.append("DOJ");
			writer.append('\t');
			writer.append("Agent ID");
			writer.append('\t');
			//	writer.append("ID");
			//	writer.append('\t');
			writer.append("Agent Name");
			writer.append('\t');
			writer.append("Agency Name");
			writer.append('\t');
			writer.append("City");
			writer.append('\t');
			writer.append("State");
			writer.append('\t');
			writer.append("Mobile-1");
			writer.append('\t');
			writer.append("Address1+Address2+ -Pin Code");
			writer.append('\t');
			writer.append("Land Line Phone 1");
			writer.append('\t');
			writer.append("Distributor Name");
			writer.append('\t');
			writer.append("Distributor ID");
			writer.append('\t');

			writer.append("Pan No");
			writer.append('\n');

			while (rs.next()) {
				writer.append(rs.getString(1));
				writer.append('\t');
				writer.append(rs.getString(2));
				writer.append('\t');
				//	writer.append(rs.getString(3));
				//	writer.append('\t');
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
				writer.append(rs.getString(10));
				writer.append('\t');
				writer.append(rs.getString(12));
				writer.append('\t');
				writer.append(rs.getString(13));
				writer.append('\t');
				String panNo = rs.getString(11);

				if (panNo == null || panNo.equalsIgnoreCase("null")) {
					panNo = "-";
				}
				writer.append(panNo);
				writer.append('\n');
			}
			writer.flush();
			writer.close();
		} catch (Exception e) {
			Status = "Norecord";
			System.out.println("Exception in ReportDao,getAgentRegistrationReport");
			System.out.println(e.toString());
		} finally {
			try {
				if(con!=null)con.close();
				if(session!=null)session.close();

			} catch (Exception e) {
				System.out.println("Exception in ReportDao,getAgentRegistrationReport");
				System.out.println(e.toString());
			}
		}
		return Status;
	}

	public   String getBillPaymentReport(String filePath, String fromDate,String toDate, String reportOf, String loginType, String portalId) {

		Session session = null;
		String Status = "Norecord";
		Connection con =null;
		try {
			session = HibernateSession.getSessionFactory().openSession();


			con = session.connection();

			CallableStatement cstmt = null;

			cstmt = con.prepareCall("{call Admin_reports(?,?,?,?,?)}");

			cstmt.setString(1, reportOf);
			cstmt.setString(2, fromDate);
			cstmt.setString(3, toDate);
			cstmt.setString(4, loginType);
			cstmt.setString(5, portalId);

			ResultSet rs = cstmt.executeQuery();

			Status = "Success";
			FileWriter writer = new FileWriter(filePath);

			writer.append("MD ID");
			writer.append('\t');
			writer.append("Request Date");
			writer.append('\t');
			writer.append("Request Time");
			writer.append('\t');
			writer.append("Agent Id");
			writer.append('\t');
			writer.append("Operator Name");
			writer.append('\t');
			writer.append("Mobile No");
			writer.append('\t');
			writer.append("Gross Amount");
			writer.append('\t');
			writer.append("Margin");
			writer.append('\t');
			writer.append("Net Amount");
			writer.append('\t');
			writer.append("Dist. Margin");
			writer.append('\t');
			writer.append("Transactions Id");
			writer.append('\t');
			writer.append("Distributor Id");
			writer.append('\t');
			writer.append("Service");
			writer.append('\t');
			writer.append("Dist. Use");
			writer.append('\t');
			writer.append("Response Date");
			writer.append('\t');
			writer.append("Response Time");
			writer.append('\t');
			writer.append("Status");
			writer.append('\t');
			writer.append("Service Provider");
			writer.append('\t');
			writer.append("Decline Reason");
			writer.append('\t');
			writer.append("Agent EmailId");
			writer.append('\n');

			while (rs.next())
			{
				String tran = rs.getString("tranId");
				String tarn2 = "' ";
				String tran3 = tarn2.concat(tran);
				writer.append(rs.getString("mdfullid"));
				writer.append('\t');
				writer.append(rs.getString("request_date"));
				writer.append('\t');
				writer.append(rs.getString("request_time"));
				writer.append('\t');
				writer.append(rs.getString("agentId"));
				writer.append('\t');
				writer.append(rs.getString("operator"));
				writer.append('\t');
				writer.append(rs.getString("mobile"));
				writer.append('\t');
				writer.append(rs.getString("amt"));
				writer.append('\t');
				writer.append(rs.getString("comm"));
				writer.append('\t');
				writer.append(rs.getString("netamt"));
				writer.append('\t');
				writer.append(rs.getString("distcomm"));
				writer.append('\t');
				writer.append(tran3);
				writer.append('\t');
				writer.append(rs.getString("Distributor_Id"));
				writer.append('\t');
				writer.append("BILL PAYMENT");
				writer.append('\t');
				writer.append(rs.getString("distuse"));
				writer.append('\t');
				writer.append(rs.getString("response_date"));
				writer.append('\t');
				writer.append(rs.getString("response_time"));
				writer.append('\t');
				writer.append(rs.getString("status"));
				writer.append('\t');
				writer.append(rs.getString("thru"));
				writer.append('\t');
				writer.append(rs.getString("decline"));
				writer.append('\t');
				writer.append(rs.getString("email"));
				writer.append('\n');
			}
			writer.flush();
			writer.close();
		} catch (Exception e) {
			Status = "Norecord";
			System.out.println("Exception in ReportDao,getBillPaymentReport");
			System.out.println(e.toString());
		} finally {
			try {
				if(con!=null)con.close();
				if(session!=null)session.close();

			} catch (Exception e) {
				System.out.println("Exception in ReportDao,getBillPaymentReport");
				System.out.println(e.toString());			}

		}
		return Status;
	}

	public   String getBusTransactionReport(String filePath,String fromDate, String toDate, String reportOf, String loginType, String portalId) {

		Session session = null;
		String Status = "Norecord";
		Connection con =null;
		try {
			session = HibernateSession.getSessionFactory().openSession();


			con = session.connection();

			CallableStatement cstmt = null;

			cstmt = con.prepareCall("{call Admin_reports(?,?,?,?,?)}");

			cstmt.setString(1, reportOf);
			cstmt.setString(2, fromDate);
			cstmt.setString(3, toDate);
			cstmt.setString(4, loginType);
			cstmt.setString(5, portalId);

			ResultSet rs = cstmt.executeQuery();

			String record = "";

			Status = "Success";
			FileWriter writer = new FileWriter(filePath);

			writer.append("MD ID");
			writer.append('\t');
			writer.append("Transaction Date");
			writer.append('\t');
			writer.append("Time");
			writer.append('\t');
			writer.append("Agent Id");
			writer.append('\t');
			writer.append("Operator Name");
			writer.append('\t');
			writer.append("RB TicketNo");
			writer.append('\t');
			writer.append("Gross Amount");
			writer.append('\t');
			writer.append("Comission");
			writer.append('\t');
			writer.append("Net Amount");
			writer.append('\t');
			writer.append("Dist. margin");
			writer.append('\t');
			writer.append("Txn Id");
			writer.append('\t');
			writer.append("Dist.Id");
			writer.append('\t');
			writer.append("Service");
			writer.append('\t');
			writer.append("Dist. use");
			writer.append('\t');
			writer.append("Transaction Type");
			writer.append('\t');
			writer.append("Ticket Fare");
			writer.append('\t');
			writer.append("Markup");
			writer.append('\t');
			writer.append("Dist markup");
			writer.append('\t');
			writer.append("Passenger Name");
			writer.append('\t');
			writer.append("Source");
			writer.append('\t');
			writer.append("Destination");
			writer.append('\t');
			writer.append("Cancellation Charges");
			writer.append('\t');
			writer.append("Refund Amount");
			writer.append('\n');

			while (rs.next()) {

				writer.append(rs.getString(20));
				writer.append('\t');
				writer.append(rs.getString(1));
				writer.append('\t');
				writer.append(rs.getString(2));
				writer.append('\t');
				writer.append(rs.getString(3));
				writer.append('\t');
				writer.append("");
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
				writer.append(rs.getString(10));
				writer.append('\t');
				writer.append("Bus");
				writer.append('\t');
				writer.append(rs.getString(11));
				writer.append('\t');
				writer.append(rs.getString(12));
				writer.append('\t');
				writer.append(rs.getString(5));
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
				writer.append('\n');
			}
			writer.flush();
			writer.close();
		} catch (Exception e) {
			Status = "Norecord";
			System.out.println("Exception in ReportDao,getBusTransactionReport");
			System.out.println(e.toString());
		} finally {
			try {
				if(con!=null)con.close();
				if(session!=null)session.close();

			} catch (Exception e) {
				System.out.println("Exception in ReportDao,getBusTransactionReport");
				System.out.println(e.toString());
			}
		}
		return Status;
	}

	public   String getCyberplatBalanceReport(String filePath,String fromDate, String toDate, String reportOf, String loginType, String portalId) {

		Session session = null;
		String Status = "Norecord";
		int i = 0;
		Connection con =null;
		try {

			session = HibernateSession.getSessionFactory().openSession();


			con = session.connection();

			CallableStatement cstmt = null;

			cstmt = con.prepareCall("{call Admin_reports(?,?,?,?,?)}");

			cstmt.setString(1, reportOf);
			cstmt.setString(2, fromDate);
			cstmt.setString(3, toDate);
			cstmt.setString(4, loginType);
			cstmt.setString(5, portalId);

			ResultSet rs = cstmt.executeQuery();

			Status = "Success";
			FileWriter writer = new FileWriter(filePath);

			writer.append("SI ND");
			writer.append('\t');
			writer.append("Date");
			writer.append('\t');
			writer.append("Opening Balance");
			writer.append('\n');
			while (rs.next()) {
				i = i + 1;
				String index = "" + i;

				writer.append(index);
				writer.append('\t');
				writer.append(rs.getString("datefinal"));
				writer.append('\t');
				writer.append(rs.getString("balance"));
				writer.append('\n');
			}
			writer.flush();
			writer.close();
		} catch (Exception e) {
			Status = "Norecord";
			System.out.println("Exception in ReportDao,getCyberplatBalanceReport");
			System.out.println(e.toString());

		} finally {
			try {
				if(con!=null)con.close();
				if(session!=null)session.close();

			} catch (Exception e) {
				System.out.println("Exception in ReportDao,getCyberplatBalanceReport");
				System.out.println(e.toString());
			}
		}
		return Status;
	}

	public   String getDistributorDetailsReport(String filePath,String fromDate, String toDate, String reportOf, String loginType, String portalId) {

		Session session = null;
		String Status = "Norecord";
		String CompleteID="";
		Connection con =null;
		try {
			session = HibernateSession.getSessionFactory().openSession();


			con = session.connection();

			CallableStatement cstmt = null;

			cstmt = con.prepareCall("{call Admin_reports(?,?,?,?,?)}");

			cstmt.setString(1, reportOf);
			cstmt.setString(2, fromDate);
			cstmt.setString(3, toDate);
			cstmt.setString(4, loginType);
			cstmt.setString(5, portalId);

			ResultSet rs = cstmt.executeQuery();

			Status = "Success";
			FileWriter writer = new FileWriter(filePath);

			writer.append("Distributor Id");
			writer.append('\t');
			writer.append("Company Name");
			writer.append('\t');
			writer.append("Distributor Name");
			writer.append('\t');
			writer.append("Address1");
			writer.append('\t');
			writer.append("Address2");
			writer.append('\t');
			writer.append("Address3");
			writer.append('\t');
			writer.append("Landmark");
			writer.append('\t');
			writer.append("Nearest Airport");
			writer.append('\t');
			writer.append("Pin Code");
			writer.append('\t');
			writer.append("City");
			writer.append('\t');
			writer.append("State");
			writer.append('\t');
			writer.append("Country");
			writer.append('\t');
			writer.append("Email Id");
			writer.append('\t');
			writer.append("office Phone1");
			writer.append('\t');
			writer.append("Mobile No");
			writer.append('\t');
			writer.append("Fax No");
			writer.append('\t');
			writer.append("date_of_joining");
			writer.append('\t');
			writer.append("Region");
			writer.append('\t');
			writer.append("No of Agent");
			writer.append('\t');

			if(loginType.equalsIgnoreCase("superadmin")||loginType.equalsIgnoreCase("activityAdmin")||loginType.equalsIgnoreCase("activityUser")){
				writer.append("legal_status");
				writer.append('\t');
				writer.append("air");
				writer.append('\t');
				writer.append("rail");
				writer.append('\t');
				writer.append("re_mobile");
				writer.append('\t');
				writer.append("re_dth");
				writer.append('\t');
				writer.append("bl_mobile");
				writer.append('\t');
				writer.append("bl_lic");
				writer.append('\t');
				writer.append("bk_bus");
				writer.append('\t');
				writer.append("bk_hotel");
				writer.append('\t');
				writer.append("bk_tour");
				writer.append('\t');
				writer.append("reqest_courier");
				writer.append('\t');
				writer.append("request_pancard");
				writer.append('\t');
				writer.append("request_share");
				writer.append('\t');
				writer.append("e_com_product");
				writer.append('\t');
				writer.append("e_com_service");
				writer.append('\t');
				writer.append("e_com_leadbased");
				writer.append('\t');
				writer.append("ps_edu_e_point");
				writer.append('\t');
				writer.append("ps_citizen_e_point");
				writer.append('\t');
				writer.append("ps_profit_bazar");
				writer.append('\t');
				writer.append("live_recharge");
				writer.append('\t');
			}
			writer.append("PAN or TIN No");
			writer.append('\n');

			while (rs.next()) 
			{
				CompleteID=rs.getString(2)+""+rs.getString(1);
				writer.append(CompleteID);
				writer.append('\t');
				writer.append(rs.getString(3));
				writer.append('\t');
				writer.append(rs.getString(4));
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
				if(loginType.equalsIgnoreCase("superadmin")||loginType.equalsIgnoreCase("activityAdmin")||loginType.equalsIgnoreCase("activityUser"))
				{
					writer.append(rs.getString(5));
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
					writer.append('\t');
					writer.append(rs.getString(34));
					writer.append('\t');
					writer.append(rs.getString(35));
					writer.append('\t');
					writer.append(rs.getString(36));
					writer.append('\t');
					writer.append(rs.getString(37));
					writer.append('\t');
					writer.append(rs.getString(38));
					writer.append('\t');
					writer.append(rs.getString(39));
					writer.append('\t');
					writer.append(rs.getString(40));
					writer.append('\t');
					writer.append(rs.getString(41));
					writer.append('\t');
				}
				writer.append(rs.getString(22));
				writer.append('\n');
			}
			writer.flush();
			writer.close();
		} catch (Exception e) {
			Status = "Norecord";
			System.out.println("Exception in ReportDao,getDistributorDetailsReport");
			System.out.println(e.toString());

		} finally {
			try {
				if(con!=null)con.close();
				if(session!=null)session.close();

			} catch (Exception e) {
				System.out.println("Exception in ReportDao,getDistributorDetailsReport");
				System.out.println(e.toString());
			}
		}
		return Status;
	}

	public   String getDistributorRegistrationReport(String filePath,String fromDate, String toDate, String reportOf, String loginType, String portalId) {

		Session session = null;
		String Status = "Norecord";
		Connection con =null;
		try {

			session = HibernateSession.getSessionFactory().openSession();


			con = session.connection();

			CallableStatement cstmt = null;

			cstmt = con.prepareCall("{call Admin_reports(?,?,?,?,?)}");

			cstmt.setString(1, reportOf);
			cstmt.setString(2, fromDate);
			cstmt.setString(3, toDate);
			cstmt.setString(4, loginType);
			cstmt.setString(5, portalId);

			ResultSet rs = cstmt.executeQuery();


			Status = "Success";
			FileWriter writer = new FileWriter(filePath);

			writer.append("MD ID");
			writer.append('\t');
			writer.append("DOJ");
			writer.append('\t');
			writer.append("Distributor ID+Initial");
			writer.append('\t');
			//					writer.append("Id");
			//					writer.append('\t');
			writer.append("Distributor Name");
			writer.append('\t');
			writer.append("Agency Name");
			writer.append('\t');
			writer.append("City");
			writer.append('\t');
			writer.append("State");
			writer.append('\t');
			writer.append("Mobile-1");
			writer.append('\t');
			writer.append("Address1+Address2+ -Pin Code");
			writer.append('\t');
			writer.append("Land Line Phone 1");
			writer.append('\t');
			writer.append("Email Id");
			writer.append('\t');
			writer.append("No Of Agents");
			writer.append('\t');
			writer.append("Client Type");
			//	writer.append('\t');
			// writer.append("Company Name");
			writer.append('\n');

			while (rs.next()) 
			{
				writer.append(rs.getString(14) + "" + rs.getString(13));
				writer.append('\t');
				writer.append(rs.getString(1));
				writer.append('\t');
				writer.append(rs.getString(2));
				writer.append('\t');
				//	writer.append(rs.getString(3));
				//	writer.append('\t');
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
				writer.append(rs.getString(10));
				writer.append('\t');
				writer.append(rs.getString(11));
				writer.append('\t');
				writer.append(rs.getString(12));
				writer.append('\t');
				writer.append(rs.getString(15));
				//	writer.append('\t');
				//	writer.append(rs.getString(16));
				writer.append('\n');
			}
			writer.flush();
			writer.close();
		} catch (Exception e) {
			Status = "Norecord";
			System.out.println("Exception in ReportDao,getDistributorRegistrationReport");
			System.out.println(e.toString());

		} finally {
			try {
				if(con!=null)con.close();
				if(session!=null)session.close();

			} catch (Exception e) {
				System.out.println("Exception in ReportDao,getDistributorRegistrationReport");
				System.out.println(e.toString());
			}
		}
		return Status;
	}

	public   String getDistributorJournalReport(String filePath,String fromDate, String toDate, String reportOf, String loginType, String portalId) {

		Session session = null;
		String Status = "Norecord";
		Connection con =null;
		try {

			session = HibernateSession.getSessionFactory().openSession();


			con = session.connection();

			CallableStatement cstmt = null;

			cstmt = con.prepareCall("{call Admin_reports(?,?,?,?,?)}");

			cstmt.setString(1, reportOf);
			cstmt.setString(2, fromDate);
			cstmt.setString(3, toDate);
			cstmt.setString(4, loginType);
			cstmt.setString(5, portalId);

			ResultSet rs = cstmt.executeQuery();

			Status = "Success";
			FileWriter writer = new FileWriter(filePath);

			writer.append("MD_Id");
			writer.append('\t');
			writer.append("Distributor_Id");
			writer.append('\t');
			writer.append("Transaction Id");
			writer.append('\t');
			writer.append("Request Date");
			writer.append('\t');
			writer.append("Request Time");
			writer.append('\t');
			writer.append("Mode Of Payment");
			writer.append('\t');
			writer.append("Amount to credit");
			writer.append('\t');
			writer.append("Bank Charges");
			writer.append('\t');
			writer.append("Accepted Amt");
			writer.append('\t');
			writer.append("Bank Name");
			writer.append('\t');
			writer.append("Branch");
			writer.append('\t');
			writer.append("Bank Tran Id");
			writer.append('\t');
			writer.append("Reciept No");
			writer.append('\t');
			writer.append("Deposit_Date");
			writer.append('\t');
			writer.append("Cheque_DD_Date");
			writer.append('\t');
			writer.append("Cheque_DD_No");
			writer.append('\t');
			writer.append("Approval Date");
			writer.append('\t');
			writer.append("Approved By");
			writer.append('\t');
			writer.append("Status");
			writer.append('\t');
			writer.append("User Account No");
			writer.append('\t');
			writer.append("Company Account No");
			writer.append('\t');
			writer.append("Order Id");
			writer.append('\t');
			writer.append("Reference No");
			writer.append('\t');
			writer.append("BC_Remarks");
			writer.append('\t');
			writer.append("Remarks");
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
			Status = "Norecord";
			System.out.println("Exception in ReportDao,getDistributorJournalReport");
			System.out.println(e.toString());

		} finally {
			try {
				if(con!=null)con.close();
				if(session!=null)session.close();

			} catch (Exception e) {
				System.out.println("Exception in ReportDao,getDistributorJournalReport");
				System.out.println(e.toString());
			}
		}
		return Status;
	}

	public   String getIRCTCRegisteredAgentReport(String filePath,String fromDate, String toDate, String reportOf, String loginType, String portalId) {

		Session session = null;
		String Status = "Norecord";
		Connection con =null;
		try {

			session = HibernateSession.getSessionFactory().openSession();


			con = session.connection();

			CallableStatement cstmt = null;

			cstmt = con.prepareCall("{call Admin_reports(?,?,?,?,?)}");

			cstmt.setString(1, reportOf);
			cstmt.setString(2, fromDate);
			cstmt.setString(3, toDate);
			cstmt.setString(4, loginType);
			cstmt.setString(5, portalId);

			ResultSet rs = cstmt.executeQuery();


			Status = "Success";
			FileWriter writer = new FileWriter(filePath);

			writer.append("SNo");
			writer.append('\t');
			writer.append("agent_initial");
			writer.append('\t');
			writer.append("agent_rg_id");
			writer.append('\t');
			writer.append("agent_lg_mail_id");
			writer.append('\t');
			writer.append("USERID");
			writer.append('\t');
			writer.append("PASSWORD");
			writer.append('\t');
			writer.append("FIRSTNAME");
			writer.append('\t');
			writer.append("MIDDLENAME");
			writer.append('\t');
			writer.append("LASTNAME");
			writer.append('\t');
			writer.append("CORPORATENAME");
			writer.append('\t');
			writer.append("OFFICEADDRESSLINE1");
			writer.append('\t');
			writer.append("OFFICEADDRESSLINE2");
			writer.append('\t');
			writer.append("OFFICECITY");
			writer.append('\t');
			writer.append("OFFICESTATE");
			writer.append('\t');
			writer.append("OFFICECOUNTRY");
			writer.append('\t');
			writer.append("OFFICEPIN");
			writer.append('\t');
			writer.append("OFFICEPHONE");
			writer.append('\t');
			writer.append("OFFICEPHONEEXT");
			writer.append('\t');
			writer.append("OFFICEFAX");
			writer.append('\t');
			writer.append("EMAIL");
			writer.append('\t');
			writer.append("RESIDADDRESSLINE1");
			writer.append('\t');
			writer.append("RESIDADDRESSLINE2");
			writer.append('\t');
			writer.append("RESIDCITY");
			writer.append('\t');
			writer.append("RESIDSTATE");
			writer.append('\t');
			writer.append("RESIDCOUNTRY");
			writer.append('\t');
			writer.append("RESIDPIN");
			writer.append('\t');
			writer.append("RESIDPHONE");
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
				writer.append('\n');

			}
			writer.flush();
			writer.close();
		} catch (Exception e) {
			Status = "Norecord";
			System.out.println("Exception in ReportDao,getIRCTCRegisteredAgentReport");
			System.out.println(e.toString());

		} finally {
			try {
				if(con!=null)con.close();
				if(session!=null)session.close();

			} catch (Exception e) {
				System.out.println("Exception in ReportDao,getIRCTCRegisteredAgentReport");
				System.out.println(e.toString());
			}
		}
		return Status;
	}

	public   String getLICPremiumReport(String filePath, String fromDate,String toDate, String reportOf, String loginType, String portalId) {

		Session session = null;
		String Status = "Norecord";
		Connection con =null;

		try {
			session = HibernateSession.getSessionFactory().openSession();


			con = session.connection();

			CallableStatement cstmt = null;

			cstmt = con.prepareCall("{call Admin_reports(?,?,?,?,?)}");

			cstmt.setString(1, reportOf);
			cstmt.setString(2, fromDate);
			cstmt.setString(3, toDate);
			cstmt.setString(4, loginType);
			cstmt.setString(5, portalId);

			ResultSet rs = cstmt.executeQuery();


			Status = "Success";
			FileWriter writer = new FileWriter(filePath);

			writer.append("MD ID");
			writer.append('\t');
			writer.append("Request Date");
			writer.append('\t');
			writer.append("Request Time");
			writer.append('\t');
			writer.append("Agent Id");
			writer.append('\t');
			writer.append("Operator Name");
			writer.append('\t');
			writer.append("Mobile No");
			writer.append('\t');
			writer.append("Gross Amount");
			writer.append('\t');
			writer.append("Margin");
			writer.append('\t');
			writer.append("Net Amount");
			writer.append('\t');
			writer.append("Dist. Margin");
			writer.append('\t');
			writer.append("Transactions Id");
			writer.append('\t');
			writer.append("DistributorId");
			writer.append('\t');
			writer.append("Service");
			writer.append('\t');
			writer.append("Dist. Use");
			writer.append('\t');
			writer.append("Response Date");
			writer.append('\t');
			writer.append("Response Time");
			writer.append('\t');
			writer.append("Status");
			writer.append('\t');
			writer.append("Service Provider");
			writer.append('\t');
			writer.append("Decline Reason");
			writer.append('\t');
			writer.append("Agent E-Mail ID");
			writer.append('\n');

			while (rs.next()) {
				String tran = rs.getString("tranId");
				String tarn2 = "' ";
				String tran3 = tarn2.concat(tran);

				writer.append(rs.getString("mdfullid"));
				writer.append('\t');
				writer.append(rs.getString("request_date"));
				writer.append('\t');
				writer.append(rs.getString("request_time"));
				writer.append('\t');
				writer.append(rs.getString("agentId"));
				writer.append('\t');
				writer.append(rs.getString("operator"));
				writer.append('\t');
				writer.append(rs.getString("mobileno"));
				writer.append('\t');
				writer.append(rs.getString("amt"));
				writer.append('\t');
				writer.append(rs.getString("comm"));
				writer.append('\t');
				writer.append(rs.getString("netamt"));
				writer.append('\t');
				writer.append(rs.getString("distcomm"));
				writer.append('\t');
				writer.append(tran3);
				writer.append('\t');
				writer.append(rs.getString("Distributor_Id"));
				writer.append('\t');
				writer.append("LIC Payment");
				writer.append('\t');
				writer.append(rs.getString("distuse"));
				writer.append('\t');
				writer.append(rs.getString("response_date"));
				writer.append('\t');
				writer.append(rs.getString("response_time"));
				writer.append('\t');
				writer.append(rs.getString("status"));
				writer.append('\t');
				writer.append(rs.getString("thru"));
				writer.append('\t');
				writer.append(rs.getString("decline"));
				writer.append('\t');
				writer.append(rs.getString("email"));
				writer.append('\n');
			}
			writer.flush();
			writer.close();
		}	
		catch (Exception e) {
			Status = "Norecord";
			System.out.println("Exception in ReportDao,getLICPremiumReport");
			System.out.println(e.toString());

		} finally {
			try {
				if(con!=null)con.close();
				if(session!=null)session.close();
			} catch (Exception e) {
				System.out.println("Exception in ReportDao,getLICPremiumReport");
				System.out.println(e.toString());
			}
		}
		return Status;
	}

	public   String getLiveDMRReport(HttpServletResponse response,String fromDate, String toDate, String reportOf, String loginType, String portalId,String agentId) {

		Session session = null;
		String Status = "Norecord";
		Connection con =null;

		try {
			session = HibernateSession.getSessionFactory().openSession();

			con = session.connection();

			PreparedStatement pstmt = null;
			System.out.println(reportOf+" : "+fromDate+" : "+toDate+" : "+loginType+" : "+portalId);

			if("all".equalsIgnoreCase(agentId))
				pstmt=con.prepareStatement("select ad.MDS_ID,ad.DS_id,std.Agent_id,std.Sender_Id,std.Transaction_No,isnull(std.Reference_id,'') as UTR,std.Date_of_Transaction,std.Time_of_Transaction,atd.Service,std.Tran_status,atd.Agent_balAmt_b_Ded,atd.ReqAmount,atd.Commession,atd.Service_charge1,atd.DeductedAmt,atd.Agent_balAmt_A_Ded,isnull(std.Bene_Name,'') as beneName,isnull(std.Bene_Account,'') as beneAccount,isnull(std.Bene_Bank_Name,'') as beneBankName,isnull(std.Bene_Bank_IFSC,'') as beneBankIfsc from DMR_SENDER_TRANSACTION_DETAILS (nolock) std join agent_transaction_details (nolock) atd on std.Transaction_No=atd.Transaction_No join agent_details ad on ad.Agent_id=atd.Agent_id and std.Agent_id=atd.Agent_id and std.Date_of_Transaction=atd.Date_of_Transaction and std.Date_of_Transaction between '"+fromDate+"' and '"+toDate+"' order by Date_of_Transaction desc,Time_of_Transaction desc");
			else
				pstmt=con.prepareStatement("select ad.MDS_ID,ad.DS_id,std.Agent_id,std.Sender_Id,std.Transaction_No,isnull(std.Reference_id,'') as UTR,std.Date_of_Transaction,std.Time_of_Transaction,atd.Service,std.Tran_status,atd.Agent_balAmt_b_Ded,atd.ReqAmount,atd.Commession,atd.Service_charge1,atd.DeductedAmt,atd.Agent_balAmt_A_Ded,isnull(std.Bene_Name,'') as beneName,isnull(std.Bene_Account,'') as beneAccount,isnull(std.Bene_Bank_Name,'') as beneBankName,isnull(std.Bene_Bank_IFSC,'') as beneBankIfsc from DMR_SENDER_TRANSACTION_DETAILS (nolock) std join agent_transaction_details (nolock) atd on std.Transaction_No=atd.Transaction_No join agent_details ad on ad.Agent_id=atd.Agent_id  and std.Agent_id=atd.Agent_id and std.Date_of_Transaction=atd.Date_of_Transaction and std.Date_of_Transaction between '"+fromDate+"' and '"+toDate+"' and atd.Agent_id="+Long.parseLong(agentId)+" order by Date_of_Transaction desc,Time_of_Transaction desc");

			ResultSet rs = pstmt.executeQuery();


			Status = "Success";
			response.setContentType("application/vnd.ms-excel");
			String reportName = "DMR_Transaction_details.xls";
			response.setHeader("Content-disposition", "attachment; filename=" + reportName);
			ArrayList<String> rows = new ArrayList<String>();

			rows.add("MD ID");
			rows.add("\t");
			rows.add("DS");
			rows.add("\t");
			rows.add("Agent Id");
			rows.add("\t");
			rows.add("Sender Id");
			rows.add("\t");
			rows.add("Date");
			rows.add("\t");
			rows.add("Time");
			rows.add("\t");
			rows.add("Txn Id");
			rows.add("\t");
			rows.add("Bank Referance Id");
			rows.add("\t");
			rows.add("Service");
			rows.add("\t");
			rows.add("Tran Status");
			rows.add("\t");
			rows.add("Previous Balance");
			rows.add("\t");
			rows.add("Req Amount");
			rows.add("\t");
			rows.add("Comission");
			rows.add("\t");
			rows.add("Service Charge");
			rows.add("\t");
			rows.add("Net Tran Amt");
			rows.add("\t");
			rows.add("Final Balance");
			rows.add("\t");
			rows.add("Bene Name");
			rows.add("\t");
			rows.add("Bene Account");
			rows.add("\t");
			rows.add("Bene Bank Name");
			rows.add("\t");
			rows.add("Bene Bank IFSC");
			rows.add("\n");

			while (rs.next()) {

				rows.add(rs.getString(1));
				rows.add("\t");
				rows.add(rs.getString(2));
				rows.add("\t");
				rows.add("AG"+rs.getString(3));
				rows.add("\t");
				rows.add(rs.getString(4));
				rows.add("\t");
				rows.add(rs.getString(5));
				rows.add("\t");
				rows.add(rs.getString(6));
				rows.add("\t");
				rows.add(rs.getString(7));
				rows.add("\t");
				rows.add(rs.getString(8));
				rows.add("\t");
				rows.add(rs.getString(9));
				rows.add("\t");
				rows.add(rs.getString(10));
				rows.add("\t");
				rows.add(rs.getString(11));
				rows.add("\t");
				rows.add(rs.getString(12));
				rows.add("\t");
				rows.add(rs.getString(13));
				rows.add("\t");
				rows.add(rs.getString(14));
				rows.add("\t");
				rows.add(rs.getString(15));
				rows.add("\t");
				rows.add(rs.getString(16));
				rows.add("\t");
				rows.add(rs.getString(17));
				rows.add("\t");
				rows.add(rs.getString(18));
				rows.add("\t");
				rows.add(rs.getString(19));
				rows.add("\t");
				rows.add(rs.getString(20));
				rows.add("\n");
			}

			Iterator<String> iter = rows.iterator();
			while (iter.hasNext()) {
				String outputString = (String) iter.next();
				response.getOutputStream().print(outputString);
			}
			response.getOutputStream().flush();
			response.getOutputStream().close();


		} catch (Exception e) {
			Status = "Norecord";
			System.out.println("Exception in ReportDao,getLiveRechargeReport");
			System.out.println(e.toString());

		} finally {
			try {
				if(con!=null)con.close();
				if(session!=null)session.close();

			} catch (Exception e) {
				System.out.println("Exception in ReportDao,getLiveRechargeReport");
				System.out.println(e.toString());
			}
		}
		return Status;
	}

	public   String getLiveRechargeReport(HttpServletResponse response,String fromDate, String toDate, String reportOf, String loginType, String portalId) {

		Session session = null;
		String Status = "Norecord";
		Connection con =null;
		CallableStatement cstmt = null;
		ResultSet rs=null;
		try {

			session = HibernateSession.getSessionFactory().openSession();
			con = session.connection();

			

			cstmt = con.prepareCall("{call Admin_reports(?,?,?,?,?)}");

			cstmt.setString(1, reportOf);
			cstmt.setString(2, fromDate);
			cstmt.setString(3, toDate);
			cstmt.setString(4, loginType);
			cstmt.setLong(5, Long.parseLong(portalId));

			rs = cstmt.executeQuery();

			Status = "Success";

			response.setContentType("application/vnd.ms-excel");
			String reportName = "Transaction_Report.xls";
			response.setHeader("Content-disposition", "attachment; filename=" + reportName);
			ArrayList<String> rows = new ArrayList<String>();

			rows.add("MD ID");
			rows.add("\t");
			rows.add("Date");
			rows.add("\t");
			rows.add("Time");
			rows.add("\t");
			rows.add("Agent Id");
			rows.add("\t");
			rows.add("Agency Name");
			rows.add("\t");
			rows.add("Operator");
			rows.add("\t");
			rows.add("Mobile No");
			rows.add("\t");
			rows.add("Gross Amount");
			rows.add("\t");
			rows.add("Comission");
			rows.add("\t");
			rows.add("Charge");
			rows.add("\t");
			rows.add("Dist. Commision");
			rows.add("\t");
			rows.add("TEP Txn Id");
			rows.add("\t");
			rows.add("Operatpor Txn Id");
			rows.add("\t");
			rows.add("DistributorId");
			rows.add("\t");
			rows.add("Service");
			rows.add("\t");
			if("SuperAdmin".equalsIgnoreCase(loginType) || "ActivityAdmin".equalsIgnoreCase(loginType) || "ActivityUser".equalsIgnoreCase(loginType)){
				rows.add("Service Panel");
				rows.add("\t");
				rows.add("Service Provider");
				rows.add("\t");

			}
			rows.add("Dist. Use");
			rows.add("\t");
			rows.add("Status");
			rows.add("\t");
			rows.add("Response Code");
			rows.add("\t");
			rows.add("Bal Before Tran");
			rows.add("\t");
			rows.add("Bal After Tran");
			rows.add("\t");
			rows.add("Tran Status");
			rows.add("\n");	


			while (rs.next()){

				rows.add(rs.getString("mdfullid"));
				rows.add("\t");
				rows.add(rs.getString("dor")+"");
				rows.add("\t");
				rows.add(rs.getString("time")+"");
				rows.add("\t");
				rows.add(rs.getString("agentId"));
				rows.add("\t");
				rows.add(rs.getString("agency_name"));
				rows.add("\t");
				rows.add(rs.getString("operator"));
				rows.add("\t");
				rows.add(rs.getString("mobno"));
				rows.add("\t");
				rows.add(rs.getString("amt"));
				rows.add("\t");
				rows.add(rs.getString("comm"));
				rows.add("\t");
				rows.add(rs.getString("Service_charge1"));
				rows.add("\t");
				rows.add(rs.getString("distcommision"));
				rows.add("\t");
				rows.add(rs.getString("TepTranId"));
				rows.add("\t");
				rows.add(rs.getString("OperatorTranId"));
				rows.add("\t");
				rows.add(rs.getString("Distributor_Id"));
				rows.add("\t");
				rows.add(rs.getString("service"));
				rows.add("\t");
				if("SuperAdmin".equalsIgnoreCase(loginType) || "ActivityAdmin".equalsIgnoreCase(loginType) || "ActivityUser".equalsIgnoreCase(loginType)){

					rows.add(rs.getString("servicePanel"));
					rows.add("\t");
					rows.add(rs.getString("ServiceProvider"));
					rows.add("\t");

				}
				rows.add(rs.getString("distuse"));
				rows.add("\t");
				rows.add(rs.getString("status"));
				rows.add("\t");
				rows.add(rs.getString("rescode"));
				rows.add("\t");
				rows.add(rs.getString("balbefore"));
				rows.add("\t");
				rows.add(rs.getString("balafter"));
				rows.add("\t");
				rows.add(rs.getString("transtatus"));
				rows.add("\n");	
			}
			Iterator<String> iter = rows.iterator();
			while (iter.hasNext()) {
				String outputString = (String) iter.next();
				response.getOutputStream().print(outputString);
			}
			response.getOutputStream().flush();
			response.getOutputStream().close();



		} catch (Exception e) {
			Status = "Norecord";
			//System.out.println("Exception in ReportDao,getLiveRechargeReport");
			e.printStackTrace();


		} finally {
			try {

				if(rs!=null)rs.close();
				if(cstmt!=null)cstmt.close();
				if(con!=null)con.close();
				if(session!=null)session.close();

			} catch (Exception e) {
				//System.out.println("Exception in ReportDao,getAirTransactionReport");
				//System.out.println(e.toString());
			}
		}
		return Status;
	}


	public   String getMdJournalReport(String filePath, String fromDate,String toDate, String reportOf, String loginType, String portalId) {

		Session session = null;
		String Status = "Norecord";
		Connection con =null;

		try {
			session = HibernateSession.getSessionFactory().openSession();
			con = session.connection();
			CallableStatement cstmt = null;

			cstmt = con.prepareCall("{call Admin_reports(?,?,?,?,?)}");

			cstmt.setString(1, reportOf);
			cstmt.setString(2, fromDate);
			cstmt.setString(3, toDate);
			cstmt.setString(4, loginType);
			cstmt.setString(5, portalId);


			ResultSet rs = cstmt.executeQuery();

			Status = "Success";
			FileWriter writer = new FileWriter(filePath);

			writer.append("MD ID");
			writer.append('\t');
			writer.append("Transaction Id");
			writer.append('\t');
			writer.append("Request date");
			writer.append('\t');
			writer.append("Request Time");
			writer.append('\t');
			writer.append("Mode Of Payment");
			writer.append('\t');
			writer.append("Amount");
			writer.append('\t');
			writer.append("Bank Charges");
			writer.append('\t');
			writer.append("Accepted amount");
			writer.append('\t');
			writer.append("Receiving Bank Name");
			writer.append('\t');
			writer.append("Receiving Branch Name");
			writer.append('\t');
			writer.append("Bank Transaction Id");
			writer.append('\t');
			writer.append("Reference ID");
			writer.append('\t');
			writer.append("Receipt No");
			writer.append('\t');
			writer.append("Deposit_Date");
			writer.append('\t');
			writer.append("Cheque_DD_No");
			writer.append('\t');
			writer.append("Approval_Date");
			writer.append('\t');
			writer.append("Approved By");
			writer.append('\t');
			writer.append("Status");
			writer.append('\t');
			writer.append("User Account No");
			writer.append('\t');
			writer.append("Company Account No");
			writer.append('\t');
			writer.append("order_id");
			writer.append('\t');
			writer.append("Refrence_no");
			writer.append('\t');
			writer.append("Remarks");
			writer.append('\t');
			writer.append("Admin Remarks");
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
				writer.append(rs.getString("approve"));
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
				writer.append('\n');

			}
			writer.flush();
			writer.close();
		} catch (Exception e) {
			Status = "Norecord";
			System.out.println("Exception in ReportDao,getMdJournalReport");
			System.out.println(e.toString());


		} finally {
			try {
				if(con!=null)con.close();
				if(session!=null)session.close();

			} catch (Exception e) {
				System.out.println("Exception in ReportDao,getMdJournalReport");
				System.out.println(e.toString());
			}
		}
		return Status;
	}

	// /////////////////////////////////////////////////////////////////////////////

	public   String getSuccessLiveRechargeReport(String filePath,String fromDate, String toDate, String reportOf, String loginType, String portalId) {

		Session session = null;
		String Status = "Norecord";
		Connection con =null;
		try {

			session = HibernateSession.getSessionFactory().openSession();
			con = session.connection();

			CallableStatement cstmt = null;

			cstmt = con.prepareCall("{call Admin_reports(?,?,?,?,?)}");

			cstmt.setString(1, reportOf);
			cstmt.setString(2, fromDate);
			cstmt.setString(3, toDate);
			cstmt.setString(4, loginType);
			cstmt.setString(5, portalId);

			ResultSet rs = cstmt.executeQuery();

			Status = "Success";
			FileWriter writer = new FileWriter(filePath);

			writer.append("AGENT_ID");
			writer.append('\t');
			writer.append("Mobile_operator");
			writer.append('\t');
			writer.append("Mobile_number");
			writer.append('\t');
			writer.append("Recharge_Amount");
			writer.append('\t');
			writer.append("Date_of_recharge");
			writer.append('\t');
			writer.append("mob_commission");
			writer.append('\t');
			writer.append("Tran_id");
			writer.append('\t');
			writer.append("Service");
			writer.append('\t');
			writer.append("Status");
			writer.append('\n');

			while (rs.next()) 
			{
				String agent_initial = rs.getString(1);
				String agent_id = rs.getString(2);
				String AgentID = agent_initial + agent_id;

				writer.append(AgentID);
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
				writer.append(rs.getString(10));
				writer.append('\n');

			}
			writer.flush();
			writer.close();

		} catch (Exception e) {
			Status = "Norecord";
			System.out.println("Exception in ReportDao,getSuccessLiveRechargeReport");
			System.out.println(e.toString());


		} finally {
			try {
				if(con!=null)con.close();
				if(session!=null)session.close();
			} catch (Exception e) {
				System.out.println("Exception in ReportDao,getSuccessLiveRechargeReport");
				System.out.println(e.toString());
			}
		}
		return Status;
	}

	public   String getMdRegistrationReport(String filePath,String fromDate, String toDate, String reportOf, String loginType, String portalId) {

		Session session = null;
		String Status = "Norecord";
		Connection con =null;
		try {
			session = HibernateSession.getSessionFactory().openSession();
			con = session.connection();

			CallableStatement cstmt = null;

			cstmt = con.prepareCall("{call Admin_reports(?,?,?,?,?)}");

			cstmt.setString(1, reportOf);
			cstmt.setString(2, fromDate);
			cstmt.setString(3, toDate);
			cstmt.setString(4, loginType);
			cstmt.setString(5, portalId);

			ResultSet rs = cstmt.executeQuery();


			Status = "Success";
			FileWriter writer = new FileWriter(filePath);

			writer.append("Client ID and Type");
			writer.append('\t');
			writer.append("DOJ");
			writer.append('\t');
			writer.append("MD ID+Initial");
			writer.append('\t');
			//	writer.append("ID");
			//	writer.append('\t');
			writer.append("MD Name");
			writer.append('\t');
			writer.append("Company Name");
			writer.append('\t');
			writer.append("City");
			writer.append('\t');
			writer.append("State");
			writer.append('\t');
			writer.append("Mobile-1");
			writer.append('\t');
			writer.append("Address1+Address2+ -Pin Code");
			writer.append('\t');
			writer.append("Land Line Phone 1");
			writer.append('\t');
			writer.append("Email Id");
			writer.append('\t');
			writer.append("No Of Distributors");
			writer.append('\n');

			while (rs.next()) 
			{
				writer.append(rs.getString(13) + "-" + rs.getString(14));
				writer.append('\t');
				writer.append(rs.getString(1));
				writer.append('\t');
				writer.append(rs.getString(2));
				writer.append('\t');
				//	writer.append(rs.getString(3));
				//	writer.append('\t');
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
				writer.append(rs.getString(10));
				writer.append('\t');
				writer.append(rs.getString(11));
				writer.append('\t');
				writer.append(rs.getString(12));
				writer.append('\n');
			}
			writer.flush();
			writer.close();

		} catch (Exception e) {
			Status = "Norecord";
			System.out.println("Exception in ReportDao,getMdRegistrationReport");
			System.out.println(e.toString());
		} finally {
			try {
				if(con!=null)con.close();
				if(session!=null)session.close();

			} catch (Exception e) {
				System.out.println("Exception in ReportDao,getMdRegistrationReport");
				System.out.println(e.toString());
			}
		}
		return Status;
	}

	public   String getOfflineMobileRechargeReport(String filePath,String fromDate, String toDate, String reportOf, String loginType, String portalId) {

		Session session = null;
		String Status = "Norecord";
		Connection con =null;

		try 
		{
			session = HibernateSession.getSessionFactory().openSession();


			con = session.connection();

			CallableStatement cstmt = null;

			cstmt = con.prepareCall("{call Admin_reports(?,?,?,?,?)}");

			cstmt.setString(1, reportOf);
			cstmt.setString(2, fromDate);
			cstmt.setString(3, toDate);
			cstmt.setString(4, loginType);
			cstmt.setString(5, portalId);

			ResultSet rs = cstmt.executeQuery();

			Status = "Success";
			FileWriter writer = new FileWriter(filePath);

			writer.append("MD ID");
			writer.append('\t');
			writer.append("Request Date");
			writer.append('\t');
			writer.append("Request Time");
			writer.append('\t');
			writer.append("Agent Id");
			writer.append('\t');
			writer.append("Operator Name");
			writer.append('\t');
			writer.append("Mobile No");
			writer.append('\t');
			writer.append("Gross Amount");
			writer.append('\t');
			writer.append("Margin");
			writer.append('\t');
			writer.append("Net Amount");
			writer.append('\t');
			writer.append("Dist. Margin");
			writer.append('\t');
			writer.append("Txn Id");
			writer.append('\t');
			writer.append("DistributorId");
			writer.append('\t');
			writer.append("Service");
			writer.append('\t');
			writer.append("Dist. Use");
			writer.append('\t');
			writer.append("Response Date");
			writer.append('\t');
			writer.append("Response Time");
			writer.append('\t');
			writer.append("Status");
			writer.append('\t');
			writer.append("ServiceProvider");
			writer.append('\t');
			writer.append("Decline Reason");
			writer.append('\n');

			while (rs.next()) 
			{
				String tran = rs.getString("tranId");
				String tarn2 = "' ";
				String tran3 = tarn2.concat(tran);
				writer.append(rs.getString("mdfullid"));
				writer.append('\t');
				writer.append(rs.getString("request_date"));
				writer.append('\t');
				writer.append(rs.getString("request_time"));
				writer.append('\t');
				writer.append(rs.getString("agentId"));
				writer.append('\t');
				writer.append(rs.getString("operator"));
				writer.append('\t');
				writer.append(rs.getString("mobileNo"));
				writer.append('\t');
				writer.append(rs.getString("amt"));
				writer.append('\t');
				writer.append(rs.getString("comm"));
				writer.append('\t');
				writer.append(rs.getString("netamt"));
				writer.append('\t');
				writer.append(rs.getString("distcomm"));
				writer.append('\t');
				writer.append(tran3);
				writer.append('\t');
				writer.append(rs.getString("Distributor_Id"));
				writer.append('\t');
				writer.append("MOBILE");
				writer.append('\t');
				writer.append(rs.getString("distuse"));
				writer.append('\t');
				writer.append(rs.getString("response_date"));
				writer.append('\t');
				writer.append(rs.getString("response_time"));
				writer.append('\t');
				writer.append(rs.getString("status"));
				writer.append('\t');
				writer.append(rs.getString("thru"));
				writer.append('\t');
				writer.append(rs.getString("decline"));
				writer.append('\n');

			}
			writer.flush();
			writer.close();
		} catch (Exception e) {
			Status = "Norecord";
			System.out.println("Exception in ReportDao,getOfflineMobileRechargeReport");
			System.out.println(e.toString());

		} finally {
			try {
				if(con!=null)con.close();
				if(session!=null)session.close();

			} catch (Exception e) {
				System.out.println("Exception in ReportDao,getOfflineMobileRechargeReport");
				System.out.println(e.toString());
			}
		}
		return Status;
	}

	public String getOfflineDthRechargeReport(String filePath,String fromDate, String toDate, String reportOf, String loginType, String portalId) {

		Session session = null;
		String Status = "Norecord";
		Connection con =null;

		try 
		{
			session = HibernateSession.getSessionFactory().openSession();

			con = session.connection();

			CallableStatement cstmt = null;

			cstmt = con.prepareCall("{call Admin_reports(?,?,?,?,?)}");

			cstmt.setString(1, reportOf);
			cstmt.setString(2, fromDate);
			cstmt.setString(3, toDate);
			cstmt.setString(4, loginType);
			cstmt.setString(5, portalId);

			ResultSet rs = cstmt.executeQuery();

			Status = "Success";

			FileWriter writer = new FileWriter(filePath);

			writer.append("MD ID");
			writer.append('\t');
			writer.append("Request Date");
			writer.append('\t');
			writer.append("Request Time");
			writer.append('\t');
			writer.append("Agent Id");
			writer.append('\t');
			writer.append("Operator");
			writer.append('\t');
			writer.append("DTH Acc. No");
			writer.append('\t');
			writer.append("Gross Amount");
			writer.append('\t');
			writer.append("Margin");
			writer.append('\t');
			writer.append("Net Amount");
			writer.append('\t');
			writer.append("Dist. Margin");
			writer.append('\t');
			writer.append("Txn Id");
			writer.append('\t');
			writer.append("DistributorId");
			writer.append('\t');
			writer.append("Service");
			writer.append('\t');
			writer.append("Dist. Use");
			writer.append('\t');
			writer.append("Response Date");
			writer.append('\t');
			writer.append("Response Time");
			writer.append('\t');
			writer.append("Status");
			writer.append('\t');
			writer.append("Through");
			writer.append('\t');
			writer.append("Decline Reason");
			writer.append('\n');

			while (rs.next()) 
			{
				writer.append(rs.getString("mdid"));
				writer.append('\t');
				writer.append(rs.getString("request_date"));
				writer.append('\t');
				writer.append(rs.getString("request_time"));
				writer.append('\t');
				writer.append(rs.getString("agentId"));
				writer.append('\t');
				writer.append(rs.getString("operator"));
				writer.append('\t');
				writer.append(rs.getString("dthno"));
				writer.append('\t');
				writer.append(rs.getString("amt"));
				writer.append('\t');
				writer.append(rs.getString("comm"));
				writer.append('\t');
				writer.append(rs.getString("netamt"));
				writer.append('\t');
				writer.append(rs.getString("distcomm"));
				writer.append('\t');
				writer.append(rs.getString("tranId"));
				writer.append('\t');
				writer.append(rs.getString("Distributor_Id"));
				writer.append('\t');
				writer.append("DTH");
				writer.append('\t');
				writer.append(rs.getString("distuse"));
				writer.append('\t');
				writer.append(rs.getString("response_date"));
				writer.append('\t');
				writer.append(rs.getString("response_time"));
				writer.append('\t');
				writer.append(rs.getString("status"));
				writer.append('\t');
				writer.append("AIRTELDTH");
				writer.append('\t');
				writer.append(rs.getString("decline"));
				writer.append('\n');
			}
			writer.flush();
			writer.close();
		} catch (Exception e) 
		{
			Status = "Norecord";
			System.out.println("Exception in ReportDao,getOfflineDthRechargeReport");
			System.out.println(e.toString());

		} finally {
			try {
				if(con!=null)con.close();
				if(session!=null)session.close();

			} catch (Exception e) {
				System.out.println("Exception in ReportDao,getOfflineDthRechargeReport");
				System.out.println(e.toString());
			}
		}
		return Status;
	}

	public String getPanCardReport(String filePath, String fromDate,String toDate, String reportOf, String loginType, String portalId) 
	{
		Session session = null;
		String Status = "Norecord";
		Connection con =null;
		try 
		{
			session = HibernateSession.getSessionFactory().openSession();


			con = session.connection();

			CallableStatement cstmt = null;

			cstmt = con.prepareCall("{call Admin_reports(?,?,?,?,?)}");

			cstmt.setString(1, reportOf);
			cstmt.setString(2, fromDate);
			cstmt.setString(3, toDate);
			cstmt.setString(4, loginType);
			cstmt.setString(5, portalId);

			ResultSet rs = cstmt.executeQuery();


			Status = "Success";

			FileWriter writer = new FileWriter(filePath);

			writer.append("MD ID");
			writer.append('\t');
			writer.append("Request Date");
			writer.append('\t');
			writer.append("Request Time");
			writer.append('\t');
			writer.append("Agent Id");
			writer.append('\t');
			writer.append("Customer Name");
			writer.append('\t');
			writer.append("Mobile No");
			writer.append('\t');
			writer.append("Gross Amount");
			writer.append('\t');
			writer.append("Margin");
			writer.append('\t');
			writer.append("Net Amount");
			writer.append('\t');
			writer.append("Dist. Margin");
			writer.append('\t');
			writer.append("Transaction Id");
			writer.append('\t');
			writer.append("Distributor Id");
			writer.append('\t');
			writer.append("Service");
			writer.append('\t');
			writer.append("Dist Use");
			writer.append('\t');
			writer.append("Response Date");
			writer.append('\t');
			writer.append("Response Time");
			writer.append('\t');
			writer.append("Status");
			writer.append('\t');
			writer.append("Through");
			writer.append('\t');
			writer.append("Decline Reason");
			writer.append('\n');

			while (rs.next()) 
			{
				String tran = rs.getString("tranId");
				String tarn2 = "' ";
				String tran3 = tarn2.concat(tran);

				writer.append(rs.getString("mdfullid"));
				writer.append('\t');
				writer.append(rs.getString("reqdate"));
				writer.append('\t');
				writer.append(rs.getString("reqtime"));
				writer.append('\t');
				writer.append(rs.getString("agentId"));
				writer.append('\t');
				writer.append(rs.getString("name"));
				writer.append('\t');
				writer.append(rs.getString("mobno"));
				writer.append('\t');
				writer.append(rs.getString("amt"));
				writer.append('\t');
				writer.append(rs.getString("comm"));
				writer.append('\t');
				writer.append(rs.getString("netamt"));
				writer.append('\t');
				writer.append(rs.getString("distcomm"));
				writer.append('\t');
				writer.append(tran3);
				writer.append('\t');
				writer.append(rs.getString("distId"));
				writer.append('\t');
				writer.append("Pan Card");
				writer.append('\t');
				writer.append(rs.getString("distuse"));
				writer.append('\t');
				writer.append(rs.getString("resdate"));
				writer.append('\t');
				writer.append(rs.getString("restime"));
				writer.append('\t');
				writer.append(rs.getString("status"));
				writer.append('\t');
				writer.append(rs.getString("thru"));
				writer.append('\t');
				writer.append(rs.getString("decline"));
				writer.append('\n');

			}
			writer.flush();
			writer.close();
		} catch (Exception e) 
		{
			Status = "Norecord";
			System.out.println("Exception in ReportDao,getPanCardReport");
			System.out.println(e.toString());

		} finally {
			try 
			{
				if(con!=null)con.close();
				if(session!=null)session.close();

			} catch (Exception e) 
			{
				System.out.println("Exception in ReportDao,getPanCardReport");
				System.out.println(e.toString());
			}
		}
		return Status;
	}

	public   String getPaymentGatewayReport(String filePath,String fromDate, String toDate, String reportOf, String loginType, String portalId) {

		Session session = null;
		String Status = "Norecord";
		Connection con =null;

		try 
		{

			session = HibernateSession.getSessionFactory().openSession();


			con = session.connection();

			CallableStatement cstmt = null;

			cstmt = con.prepareCall("{call Admin_reports(?,?,?,?,?)}");

			cstmt.setString(1, reportOf);
			cstmt.setString(2, fromDate);
			cstmt.setString(3, toDate);
			cstmt.setString(4, loginType);
			cstmt.setString(5, portalId);

			ResultSet rs = cstmt.executeQuery();

			Status = "Success";

			FileWriter writer = new FileWriter(filePath);

			writer.append("MD ID");
			writer.append('\t');
			writer.append("Transaction Date");
			writer.append('\t');
			writer.append("Transaction Time");
			writer.append('\t');
			writer.append("Reference No");
			writer.append('\t');
			writer.append("User Type");
			writer.append('\t');
			writer.append("User Id");
			writer.append('\t');
			writer.append("Bank");
			writer.append('\t');
			writer.append("Transaction Amount");
			writer.append('\t');
			writer.append("PG_Charge");
			writer.append('\t');
			writer.append("Service_Tax");
			writer.append('\t');
			writer.append("Final_Amount");
			writer.append('\t');
			writer.append("Order Id");
			writer.append('\t');
			writer.append("Status");
			writer.append('\t');
			writer.append("Opening Balance");
			writer.append('\t');
			writer.append("Closing Balance");
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
				writer.append(rs.getString(5));
				writer.append('\t');
				writer.append("Distributor");
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
				writer.append('\n');
			}
			writer.flush();
			writer.close();

		} catch (Exception e) 
		{
			Status = "Norecord";
			System.out.println("Exception in ReportDao,getPaymentGatewayReport");
			System.out.println(e.toString());
		} finally {
			try 
			{
				if(con!=null)con.close();
				if(session!=null)session.close();

			} catch (Exception e) 
			{
				System.out.println("Exception in ReportDao,getPaymentGatewayReport");
				System.out.println(e.toString());
			}
		}
		return Status;
	}

	public String getRailTicketCancellationReport(String filePath,String fromDate, String toDate, String reportOf, String loginType, String portalId) {

		Session session = null;
		String Status = "Norecord";
		Connection con =null;

		try {
			session = HibernateSession.getSessionFactory().openSession();


			con = session.connection();

			CallableStatement cstmt = null;

			cstmt = con.prepareCall("{call Admin_reports(?,?,?,?,?)}");

			cstmt.setString(1, reportOf);
			cstmt.setString(2, fromDate);
			cstmt.setString(3, toDate);
			cstmt.setString(4, loginType);
			cstmt.setString(5, portalId);

			ResultSet rs = cstmt.executeQuery();


			Status = "Success";

			FileWriter writer = new FileWriter(filePath);

			writer.append("MD ID");
			writer.append('\t');
			writer.append("Date");
			writer.append('\t');
			writer.append("Time");
			writer.append('\t');
			writer.append("Agent Id");
			writer.append('\t');
			writer.append("Operator Name");
			writer.append('\t');
			writer.append("PNR No");
			writer.append('\t');
			writer.append("Ticket Amount");
			writer.append('\t');
			writer.append("Cancellation Charge");
			writer.append('\t');
			writer.append("Refunded Amount");
			writer.append('\t');
			writer.append("Distributor Margin");
			writer.append('\t');
			writer.append("Transaction Id");
			writer.append('\t');
			writer.append("Distributor Id");
			writer.append('\t');
			writer.append("Service");
			writer.append('\t');
			writer.append("Distributor Use");
			writer.append('\t');
			writer.append("Ticket No");
			writer.append('\t');
			writer.append("Source");
			writer.append('\t');
			writer.append("Destination");
			writer.append('\t');
			writer.append("Date of Booking");
			writer.append('\t');
			writer.append("Date Of Journey");
			writer.append('\t');
			writer.append("Total Passengers");
			writer.append('\t');
			writer.append("Canceled Passengers");
			writer.append('\n');

			while (rs.next()) 
			{
				String tran = rs.getString(1);
				String tarn2 = "' ";
				String tran3 = tarn2.concat(tran);

				writer.append(rs.getString(19));
				writer.append('\t');
				writer.append(rs.getString(7));
				writer.append('\t');
				writer.append(rs.getString(18));
				writer.append('\t');
				writer.append(rs.getString(15) + "" + rs.getString(14));
				writer.append('\t');
				writer.append("IRCTC");
				writer.append('\t');
				writer.append(rs.getString(3));
				writer.append('\t');
				writer.append(rs.getString(11));
				writer.append('\t');
				writer.append(rs.getString(12));
				writer.append('\t');
				writer.append(rs.getString(13));
				writer.append('\t');
				writer.append("0");
				writer.append('\t');
				writer.append(tran3);
				writer.append('\t');
				writer.append(rs.getString(17) + " " + rs.getString(16));
				writer.append('\t');
				writer.append("Rail");
				writer.append('\t');
				writer.append(rs.getString(13));
				writer.append('\t');
				writer.append(rs.getString(2));
				writer.append('\t');
				writer.append(rs.getString(4));
				writer.append('\t');
				writer.append(rs.getString(5));
				writer.append('\t');
				writer.append(rs.getString(6));
				writer.append('\t');
				writer.append(rs.getString(8));
				writer.append('\t');
				writer.append(rs.getString(9));
				writer.append('\t');
				writer.append(rs.getString(10));
				writer.append('\n');
			}
			writer.flush();
			writer.close();
		} catch (Exception e) {
			Status = "Norecord";
			System.out.println("Exception in ReportDao,getRailTicketCancellationReport");
			System.out.println(e.toString());


		} finally {
			try {
				if(con!=null)con.close();
				if(session!=null)session.close();

			} catch (Exception e) {
				System.out.println("Exception in ReportDao,getRailTicketCancellationReport");
				System.out.println(e.toString());
			}
		}
		return Status;
	}

	public   String getRailTicketBookingReport(String filePath,String fromDate, String toDate, String reportOf, String loginType, String portalId) {

		Session session = null;
		String Status = "Norecord";
		Connection con =null;

		try {
			session = HibernateSession.getSessionFactory().openSession();


			con = session.connection();

			CallableStatement cstmt = null;

			cstmt = con.prepareCall("{call Admin_reports(?,?,?,?,?)}");

			cstmt.setString(1, reportOf);
			cstmt.setString(2, fromDate);
			cstmt.setString(3, toDate);
			cstmt.setString(4, loginType);
			cstmt.setString(5, portalId);


			ResultSet rs = cstmt.executeQuery();


			Status = "Success";

			FileWriter writer = new FileWriter(filePath);

			writer.append("MD ID");
			writer.append('\t');
			writer.append("Booking Date");
			writer.append('\t');
			writer.append("Time");
			writer.append('\t');
			writer.append("Agent Id");
			writer.append('\t');
			writer.append("Operator Name");
			writer.append('\t');
			writer.append("PNR No.");
			writer.append('\t');
			writer.append("Gross Amount");
			writer.append('\t');
			writer.append("Service Charge");
			writer.append('\t');
			writer.append("Net Amount");
			writer.append('\t');
			writer.append("Distributor Margin");
			writer.append('\t');
			writer.append("Transaction Id");
			writer.append('\t');
			writer.append("DistributorId");
			writer.append('\t');
			writer.append("Service");
			writer.append('\t');
			writer.append("Distributor Use");
			writer.append('\t');
			writer.append("Rail Code");
			writer.append('\t');
			writer.append("Ticket No");
			writer.append('\t');
			writer.append("Train Name");
			writer.append('\t');
			writer.append("Source");
			writer.append('\t');
			writer.append("Destination");
			writer.append('\t');
			writer.append("Journey Date");
			writer.append('\t');
			writer.append("Passenger");
			writer.append('\t');
			writer.append("Pass1");
			writer.append('\t');
			writer.append("Pass2");
			writer.append('\t');
			writer.append("Pass3");
			writer.append('\t');
			writer.append("Pass4");
			writer.append('\t');
			writer.append("Customer no");
			writer.append('\t');
			writer.append("Status");
			writer.append('\t');
			writer.append("Pass5");
			writer.append('\t');
			writer.append("Pass6");
			writer.append('\t');
			writer.append("initial");
			writer.append('\t');
			writer.append("chd1");
			writer.append('\t');
			writer.append("chd2");
			writer.append('\t');
			writer.append("Commission");
			writer.append('\t');
			writer.append("Agent Mobile");
			writer.append('\t');
			writer.append("Agent Email ID");
			writer.append('\n');

			while (rs.next()) 
			{
				String tran = rs.getString("tranId");
				String tarn2 = "' ";
				String tran3 = tarn2.concat(tran);

				writer.append(rs.getString("mdfullid"));
				writer.append('\t');
				writer.append(rs.getString("dob"));
				writer.append('\t');
				writer.append(rs.getString("time"));
				writer.append('\t');
				writer.append(rs.getString("agentId"));
				writer.append('\t');
				writer.append("IRCTC");
				writer.append('\t');
				writer.append(rs.getString("pnr"));
				writer.append('\t');
				writer.append(rs.getString("grossamount"));
				writer.append('\t');
				writer.append(rs.getString("servicecharge"));
				writer.append('\t');
				writer.append(rs.getString("netamount"));
				writer.append('\t');
				writer.append("0");
				writer.append('\t');
				writer.append(tran3);
				writer.append('\t');
				writer.append(rs.getString("Distributor_Id"));
				writer.append('\t');
				writer.append("Rail");
				writer.append('\t');
				writer.append(rs.getString("netamount"));
				writer.append('\t');
				writer.append(rs.getString("code"));
				writer.append('\t');
				writer.append(rs.getString("tno"));
				writer.append('\t');
				writer.append(rs.getString("trainname"));
				writer.append('\t');
				writer.append(rs.getString("source"));
				writer.append('\t');
				writer.append(rs.getString("destination"));
				writer.append('\t');
				writer.append(rs.getString("journey"));
				writer.append('\t');
				writer.append(rs.getString("nopass"));
				writer.append('\t');
				writer.append(rs.getString("pass1"));
				writer.append('\t');
				writer.append(rs.getString("pass2"));
				writer.append('\t');
				writer.append(rs.getString("pass3"));
				writer.append('\t');
				writer.append(rs.getString("pass4"));
				writer.append('\t');
				writer.append(rs.getString("mobno"));
				writer.append('\t');
				writer.append(rs.getString("status"));
				writer.append('\t');
				writer.append(rs.getString("pass5"));
				writer.append('\t');
				writer.append(rs.getString("pass6"));
				writer.append('\t');
				writer.append(rs.getString("initial"));
				writer.append('\t');
				writer.append(rs.getString("chd1"));
				writer.append('\t');
				writer.append(rs.getString("chd2"));
				writer.append('\t');
				writer.append(rs.getString("comm"));
				writer.append('\t');
				writer.append(rs.getString("agentmob"));
				writer.append('\t');
				writer.append(rs.getString("email"));
				writer.append('\n');
			}
			writer.flush();
			writer.close();

		} catch (Exception e) {
			Status = "Norecord";
			System.out.println("Exception in ReportDao,getRailTicketBookingReport");
			System.out.println(e.toString());

		} finally {
			try {
				if(con!=null)con.close();
				if(session!=null)session.close();
			} catch (Exception e) {
				System.out.println("Exception in ReportDao,getRailTicketBookingReport");
				System.out.println(e.toString());
			}
		}
		return Status;
	}

	public   String getDistributorAmountReport(String filePath,String fromDate, String toDate, String reportOf, String loginType, String portalId) {

		Session session = null;
		String Status = "Norecord";
		Connection con =null;

		try 
		{
			session = HibernateSession.getSessionFactory().openSession();


			con = session.connection();

			CallableStatement cstmt = null;

			cstmt = con.prepareCall("{call Admin_reports(?,?,?,?,?)}");

			cstmt.setString(1, reportOf);
			cstmt.setString(2, fromDate);
			cstmt.setString(3, toDate);
			cstmt.setString(4, loginType);
			cstmt.setString(5, portalId);

			ResultSet rs = cstmt.executeQuery();


			Status = "Success";
			FileWriter writer = new FileWriter(filePath);

			writer.append("DistributorId");
			writer.append('\t');
			writer.append("DistributorName");
			writer.append('\t');
			writer.append("Totcash");
			writer.append('\t');
			writer.append("UsedCash");
			writer.append('\t');
			writer.append("last_amount");
			writer.append('\n');

			while (rs.next()) 
			{
				String disId = rs.getString(1);
				String disInitial = rs.getString(2);
				String DistributorId = disInitial + disId;

				writer.append(DistributorId);
				writer.append('\t');
				writer.append(rs.getString(3));
				writer.append('\t');
				writer.append(rs.getString(4));
				writer.append('\t');
				writer.append(rs.getString(5));
				writer.append('\t');
				writer.append(rs.getString(6));
				writer.append('\n');
			}
			writer.flush();
			writer.close();
		} catch (Exception e) 
		{
			Status = "Norecord";
			System.out.println("Exception in ReportDao,getDistributorAmountReport");
			System.out.println(e.toString());

		} finally 
		{
			try 
			{
				if(con!=null)con.close();
				if(session!=null)session.close();

			} catch (Exception e) 
			{
				System.out.println("Exception in ReportDao,getDistributorAmountReport");
				System.out.println(e.toString());
			}
		}
		return Status;
	}

	public   String getAirDomesticAccountReport(String filePath,String fromDate, String toDate, String loginType,String portalId) 
	{
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

		try 
		{
			session = HibernateSession.getSessionFactory().openSession();

			txn = session.beginTransaction();

			con = session.connection();

			stmt = con.createStatement();
			sqlQuery = "select CONVERT(VARCHAR(11),trans_creation_date,106) as booking_date, CONVERT(VARCHAR(11),trans_creation_date,108) as booking_time," +
					" fk_orgn_dstn_id, partner_ref_id, (operating_airline_code +'-'+operating_airline_flight_number) as flight," +
					" (departure_airport_code + '/' + arrival_airport_code) as sector, booking_class, SUBSTRING(departure_date_time,0,11) as travel_date," +
					" adult_fare, child_fare, infant_fare, adult_tax_breakup, child_tax_breakup, infant_tax_breakup from " +
					" dom_air_flight_segments where convert(varchar(10),trans_creation_date,120) between '" + fromDate + "' and '" + toDate + "' order by fk_orgn_dstn_id";
			//System.out.println(" sql query for getting data from Flight segment is ::: " + sqlQuery);
			stmt = con.createStatement();
			rs = stmt.executeQuery(sqlQuery);
			while(rs.next())
			{

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

				//System.out.println(" sql query for getting data from Origin destination table is ::: " + sqlQuery);

				rs1 = stmt.executeQuery(sqlQuery);
				while(rs1.next())
				{

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
				//System.out.println(" sql query for getting data from Air Bookings table is ::: " + sqlQuery);

				rs2 = stmt.executeQuery(sqlQuery);
				while(rs2.next())
				{
					domesticAirAccountingRecord.setArzooReferenceID(rs2.getString("arzoo_trans_id"));
					domesticAirAccountingRecord.setBookingStatus(rs2.getString("status"));
					domesticAirAccountingRecord.setJourneyType(rs2.getString("travel_mode"));
					domesticAirAccountingRecord.setAgentID(rs2.getString("agent_id"));
				}

				// getting distribute id using agent id
				stmt = con.createStatement();
				//System.out.println(" agent Id is :: " + domesticAirAccountingRecord.getAgentID());
				sqlQuery = "select distributor_id, agent_initial from dbo.agent_details where agent_id='" + domesticAirAccountingRecord.getAgentID() + "'";
				//System.out.println(" sql query for getting distribute id is ::: " + sqlQuery);
				rs3 = stmt.executeQuery(sqlQuery);
				while(rs3.next())
				{
					String agentSimpleId = domesticAirAccountingRecord.getAgentID();
					domesticAirAccountingRecord.setAgentID(rs3.getString("agent_initial") + agentSimpleId);
					domesticAirAccountingRecord.setDistributorID(rs3.getString("distributor_id"));
				}

				// getting master distribute id using distribute id
				stmt = con.createStatement();
				//System.out.println(" distributor id is :: " + domesticAirAccountingRecord.getDistributorID());
				sqlQuery = "select distributor_initial, md_id from dbo.distributor_details where distributor_id='" + domesticAirAccountingRecord.getDistributorID() + "'";
				//System.out.println(" sql query for getting master distribute id is ::: " + sqlQuery);
				rs4 = stmt.executeQuery(sqlQuery);
				while(rs4.next())
				{
					String distributorSimpleId = domesticAirAccountingRecord.getDistributorID();
					domesticAirAccountingRecord.setDistributorID(rs4.getString("distributor_initial") + distributorSimpleId);
					domesticAirAccountingRecord.setMasterDistributorID(rs4.getString("md_id"));
				}

				// getting master distribute initial id
				stmt = con.createStatement();
				//System.out.println(" Master distributor id is :: " + domesticAirAccountingRecord.getMasterDistributorID());
				sqlQuery = "select MD_initial from MD_Details where md_id='" + domesticAirAccountingRecord.getMasterDistributorID() + "'";
				//System.out.println(" sql query for getting master distribute id is ::: " + sqlQuery);
				rs8 = stmt.executeQuery(sqlQuery);
				while(rs8.next())
				{
					String masterDistributorSimpleId = domesticAirAccountingRecord.getMasterDistributorID();
					domesticAirAccountingRecord.setMasterDistributorID(rs8.getString("MD_initial") + masterDistributorSimpleId);
				}

				// getting passenger info from passenger table
				stmt = con.createStatement();
				//System.out.println(" partner reference id is :: " + domesticAirAccountingRecord.getPartnerRefID());
				sqlQuery = "select psngr_title, psngr_first_name, psngr_last_name, pk_psngr_id from dbo.dom_air_passengers where partner_ref_id ='" + domesticAirAccountingRecord.getPartnerRefID() + "'";
				//System.out.println(" sql query for getting passenger info ::: " + sqlQuery);
				rs7 = stmt.executeQuery(sqlQuery);
				while(rs7.next())
				{
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
					//				 System.out.println(" sql query for getting passenger ticket and pnr number ::: " + sqlQuery);
					rs5 = stmt.executeQuery(sqlQuery);
					while(rs5.next())
					{
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
			}

			FileWriter writer = new FileWriter(filePath);

			writer.append("Booking Date");
			writer.append('\t');
			writer.append("Booking Time");
			writer.append('\t');
			writer.append("Agent_ID");
			writer.append('\t');
			writer.append("Distributor_ID");
			writer.append('\t');
			writer.append("Master Distributor ID");
			writer.append('\t');
			writer.append("Partner Id");
			writer.append('\t');
			writer.append("Refrence No.");
			writer.append('\t');
			writer.append("Journey Type");
			writer.append('\t');
			writer.append("Journey");
			writer.append('\t');
			writer.append("Flight");
			writer.append('\t');
			writer.append("Sector");
			writer.append('\t');
			writer.append("Booking Class");
			writer.append('\t');
			writer.append("Travel Date");
			writer.append('\t');
			writer.append("PNR No.");
			writer.append('\t');
			writer.append("Passenger Name");
			writer.append('\t');
			writer.append("E-Ticket No.");
			writer.append('\t');
			writer.append("Booking Status");
			writer.append('\t');
			writer.append("Adult Pax");
			writer.append('\t');
			writer.append("Child Pax");
			writer.append('\t');
			writer.append("Infant Pax");
			writer.append('\t');
			writer.append("Adult Fare");
			writer.append('\t');
			writer.append("Child Fare");
			writer.append('\t');
			writer.append("Infant Fare");
			writer.append('\t');
			writer.append("Basic Fare");
			writer.append('\t');
			writer.append("Adult Tax Breakup");
			writer.append('\t');
			writer.append("Child Tax Breakup");
			writer.append('\t');
			writer.append("Infant Tax Breakup");
			writer.append('\t');
			writer.append("Taxes");
			writer.append('\t');
			writer.append("Service Taxes");
			writer.append('\t');
			writer.append("Transaction Fees");
			writer.append('\t');
			writer.append("Printable MRP");
			writer.append('\t');
			writer.append("Gross WithOut Airmarkup");
			writer.append('\t');
			writer.append("Transaction Fee Reversed");
			writer.append('\t');
			writer.append("Partner Commission");
			writer.append('\t');
			writer.append("Comm In Basic%");
			writer.append('\t');
			writer.append("Comm In Basic % Value");
			writer.append('\t');
			writer.append("Comm In YQ%");
			writer.append('\t');
			writer.append("Comm In YQ % Value");
			writer.append('\t');
			writer.append("Gross Fare");
			writer.append('\t');
			writer.append("Comm In Gross%");
			writer.append('\t');
			writer.append("Comm In Gross% value");
			writer.append('\t');
			writer.append("Fix Agent Comm");
			writer.append('\t');
			writer.append("Agent Commission");
			writer.append('\t');
			writer.append("Service Charge");
			writer.append('\t');
			writer.append("Net Amount");
			writer.append('\t');
			writer.append("TDS IN%");
			writer.append('\t');
			writer.append("TDS IN% Value");
			writer.append('\t');
			writer.append("Payment Gateway Charges");
			writer.append('\t');
			writer.append("Total");
			writer.append('\t');
			writer.append("Agent Markup");
			writer.append('\t');
			writer.append("DS Commissionin Rs/%");
			writer.append('\t');
			writer.append("DS Commission");
			writer.append('\n');

			for (Object obj : domesticAirAccountingRecords) 
			{
				DomesticAirAccountingRecord domesticAirAccountingRecord = (DomesticAirAccountingRecord) obj;

				writer.append(domesticAirAccountingRecord.getBookingDate());
				writer.append('\t');
				writer.append(domesticAirAccountingRecord.getBookingTime());
				writer.append('\t');
				writer.append(domesticAirAccountingRecord.getAgentID());
				writer.append('\t');
				writer.append(domesticAirAccountingRecord.getDistributorID());
				writer.append('\t');
				writer.append(domesticAirAccountingRecord.getMasterDistributorID());
				writer.append('\t');
				writer.append(domesticAirAccountingRecord.getPartnerRefID());
				writer.append('\t');
				writer.append(domesticAirAccountingRecord.getArzooReferenceID() == null
						|| "null".equalsIgnoreCase(domesticAirAccountingRecord
								.getArzooReferenceID()) ? " "
										: domesticAirAccountingRecord.getArzooReferenceID());
				writer.append('\t');
				writer.append(domesticAirAccountingRecord.getJourneyType());
				writer.append('\t');
				writer.append(domesticAirAccountingRecord.getJourney());
				writer.append('\t');
				writer.append(domesticAirAccountingRecord.getFlight());
				writer.append('\t');
				writer.append(domesticAirAccountingRecord.getSector());
				writer.append('\t');
				writer.append(domesticAirAccountingRecord.getBookingClass());
				writer.append('\t');
				writer.append(domesticAirAccountingRecord.getTravelDate());
				writer.append('\t');
				for (Object obj1 : domesticAirAccountingRecord.getPessangers()) 
				{
					PessangerForReport pessangerForReport = (PessangerForReport) obj1;
					writer.append(pessangerForReport.getPnrNo());
					if (pessangerForReport != null
							&& !"null".equalsIgnoreCase(pessangerForReport
									.getPnrNo())) 
					{
						writer.append(", ");
					}
				}
				writer.append('\t');
				for (Object obj1 : domesticAirAccountingRecord.getPessangers()) 
				{
					PessangerForReport pessangerForReport = (PessangerForReport) obj1;
					writer.append(pessangerForReport.getPsngrTitle() + " "
							+ pessangerForReport.getPsngrFirstName() + " "
							+ pessangerForReport.getPsngrLastName());
					writer.append(", ");
				}
				writer.append('\t');
				for (Object obj1 : domesticAirAccountingRecord.getPessangers()) 
				{
					PessangerForReport pessangerForReport = (PessangerForReport) obj1;
					writer.append(pessangerForReport.geteTicketNo());
					writer.append(", ");
				}
				writer.append('\t');
				writer.append(domesticAirAccountingRecord.getBookingStatus());
				writer.append('\t');
				writer.append(Integer.toString(domesticAirAccountingRecord
						.getAdultPax()));
				writer.append('\t');
				writer.append(Integer.toString(domesticAirAccountingRecord
						.getChildPax()));
				writer.append('\t');
				writer.append(Integer.toString(domesticAirAccountingRecord
						.getInfantPax()));
				writer.append('\t');
				writer.append(domesticAirAccountingRecord.getAdultFare());
				writer.append('\t');
				writer.append(domesticAirAccountingRecord.getChildFare() == null
						|| "null".equalsIgnoreCase(domesticAirAccountingRecord
								.getChildFare()) ? "0"
										: domesticAirAccountingRecord.getChildFare());
				writer.append('\t');
				writer.append(domesticAirAccountingRecord.getInfantFare() == null
						|| "null".equalsIgnoreCase(domesticAirAccountingRecord
								.getInfantFare()) ? "0"
										: domesticAirAccountingRecord.getInfantFare());
				writer.append('\t');
				writer.append(Integer.toString(domesticAirAccountingRecord
						.getBasicFare()));
				writer.append('\t');
				writer.append(domesticAirAccountingRecord.getAdultTaxBreakup()
						.replaceAll(",", "/"));
				writer.append('\t');
				writer.append(domesticAirAccountingRecord.getChildTaxBreakup()
						.replaceAll(",", "/"));
				writer.append('\t');
				writer.append(domesticAirAccountingRecord.getInfantTaxBreakup()
						.replaceAll(",", "/"));
				writer.append('\t');
				writer.append(domesticAirAccountingRecord.getTaxes());
				writer.append('\t');
				writer.append(Integer.toString(domesticAirAccountingRecord
						.getServiceTaxes()));
				writer.append('\t');
				writer.append(Integer.toString(domesticAirAccountingRecord
						.getTransactionFees()));
				writer.append('\t');
				writer.append(Integer.toString(domesticAirAccountingRecord
						.getPrintableMRP()));
				writer.append('\t');
				writer.append(Integer.toString(domesticAirAccountingRecord
						.getGrossFare()));//
				writer.append('\t');
				writer.append(Integer.toString(domesticAirAccountingRecord
						.getTransactionFees()));
				writer.append('\t');
				writer.append(domesticAirAccountingRecord
						.getPartnerCommission());
				writer.append('\t');
				writer.append(Double.toString(domesticAirAccountingRecord
						.getCommInBasicInPercentage()));
				writer.append('\t');
				writer.append(Double.toString(domesticAirAccountingRecord
						.getCommInBasicInValue()));//
				writer.append('\t');
				writer.append(Double.toString(domesticAirAccountingRecord
						.getCommInYQInPercentage()));
				writer.append('\t');
				writer.append(Double.toString(domesticAirAccountingRecord
						.getCommInYQInValue()));
				writer.append('\t');
				writer.append(Integer.toString(domesticAirAccountingRecord
						.getGrossFare()));
				writer.append('\t');

				writer.append(Double.toString(domesticAirAccountingRecord
						.getCommInGrossInPercentage()));
				writer.append('\t');
				writer.append(Double.toString(domesticAirAccountingRecord
						.getCommInGrossInValue()));
				writer.append('\t');
				writer.append(Double.toString(domesticAirAccountingRecord
						.getFixAgentComm()));
				writer.append('\t');
				writer.append(Integer.toString(domesticAirAccountingRecord
						.getAgentCommission()));
				writer.append('\t');
				writer.append(domesticAirAccountingRecord.getServiceCharge());
				writer.append('\t');

				writer.append(Double.toString(domesticAirAccountingRecord
						.getNetAmount()
						- domesticAirAccountingRecord.getTransactionFees()));
				writer.append('\t');

				writer.append(domesticAirAccountingRecord.getTdsInValue() == 0 ? "0"
						: "10");
				writer.append('\t');
				writer.append(Double.toString(domesticAirAccountingRecord
						.getTdsInValue()));
				writer.append('\t');

				writer.append("");
				writer.append('\t');

				writer.append(Double.toString(domesticAirAccountingRecord
						.getNetAmount()
						- domesticAirAccountingRecord.getTransactionFees()));
				writer.append('\t');

				writer.append(Integer.toString(domesticAirAccountingRecord
						.getTotalAgentMarkup()));
				writer.append('\t');

				writer.append("");
				writer.append('\t');
				writer.append("");
				writer.append('\n');

			}
			writer.flush();
			writer.close();

		} catch (Exception e) {
			status = "norecord";
			System.out.println("Exception in ReportDao,getAirDomesticAccountReport");
			System.out.println(e.toString());
		}
		finally {
			try {
				con.close();
				session.flush();
				session.close();
			} catch (Exception e) {
				System.out.println("Exception in ReportDao,getAirDomesticAccountReport");
				e.printStackTrace();
			}
		}
		return status;
	}

	/**
	 * 
	 * @param fromDate
	 * @param toDate
	 * @param portalId 
	 * @param loginType 
	 * @return
	 * @throws SQLException
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws AirAgentException
	 */

	public String getAirDomesticCancellationAccountReport(String filePath, String fromDate, String toDate, String loginType, String portalId) 
	{
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

		try 
		{
			session = HibernateSession.getSessionFactory().openSession();

			txn = session.beginTransaction();

			con = session.connection();

			stmt = con.createStatement();
			sqlQuery = "select distinct cancelation_id, partner_ref_id from dom_air_ticket_details where cancelation_id is not null " +
					"and (cancelation_status='Processed' or cancelation_status_return='Processed' or cancelation_status_depart='Processed') " +
					"and convert(varchar(10),trans_creation_date,120) between '" + fromDate + "' and  '" + toDate + "' order by cancelation_id desc ";
			//System.out.println("    sql query for getting data from Air ticket details is :::    " + sqlQuery);
			stmt = con.createStatement();
			rs = stmt.executeQuery(sqlQuery);
			while(rs.next())
			{

				DomesticAirCancellatioAccountingRecord domesticAirCancellatioAccountingRecord =  new DomesticAirCancellatioAccountingRecord();
				domesticAirCancellatioAccountingRecord.setPessangers(new ArrayList<PessangerForReport>());
				domesticAirCancellatioAccountingRecord.setPartnerRefID(rs.getString("partner_ref_id"));
				cancellationId = rs.getString("cancelation_id");


				// 	set here values required form OriginDestination table
				stmt = con.createStatement();
				sqlQuery = "select top 1 (operating_airline_code +'-'+operating_airline_flight_number) as flight," +
						"(departure_airport_code + '/' + arrival_airport_code) as sector," +
						"SUBSTRING(departure_date_time,0,11) as travel_date from dom_air_flight_segments" +
						" where partner_ref_id = '" + domesticAirCancellatioAccountingRecord.getPartnerRefID() + "'";
				//				System.out.println("    sql query for getting data from Origin destination table is :::    " + sqlQuery);
				rs1 = stmt.executeQuery(sqlQuery);
				while(rs1.next())
				{

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
				while(rs2.next())
				{
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
				while(rs3.next())
				{
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
				while(rs9.next())
				{
					String agentSimpleId = domesticAirCancellatioAccountingRecord.getAgentID();
					domesticAirCancellatioAccountingRecord.setAgentID(rs9.getString("agent_initial") + agentSimpleId);
				}


				// set here values required form OriginDestination table
				stmt = con.createStatement();
				sqlQuery = "select airport_tax, s_tax, gross_fare, base_fare, tds_amount, t_partner_commision from " +
						"dbo.dom_air_origin_destinations where partner_ref_id = '" + domesticAirCancellatioAccountingRecord.getPartnerRefID() + "'";

				//System.out.println("    sql query for getting data from Origin destination table is :::    " + sqlQuery);

				rs4 = stmt.executeQuery(sqlQuery);
				while(rs4.next())
				{
					taxes.add(rs4.getInt("airport_tax"));
					serviceTaxs.add(rs4.getInt("s_tax"));
					grossFares.add(rs4.getInt("gross_fare"));
					tds = rs4.getFloat("tds_amount");
					basicFares.add(rs4.getInt("base_fare"));
					tPartnerCommissions.add(Integer.parseInt(rs4.getString("t_partner_commision")));
				}
				//System.out.println("Tarvel Mode is " + domesticAirCancellatioAccountingRecord.getJourneyType());
				if("Round".equalsIgnoreCase(domesticAirCancellatioAccountingRecord.getJourneyType()))
				{
					domesticAirCancellatioAccountingRecord.setTaxes(taxes.get(0) + taxes.get(1));
					domesticAirCancellatioAccountingRecord.setServiceTaxes(serviceTaxs.get(0) + serviceTaxs.get(1));
					domesticAirCancellatioAccountingRecord.setGrossFare(grossFares.get(0) + grossFares.get(1));
					domesticAirCancellatioAccountingRecord.setBasicFare(basicFares.get(0) + basicFares.get(1));
					domesticAirCancellatioAccountingRecord.setPartnerCommission(tPartnerCommissions.get(0) + tPartnerCommissions.get(1));
					domesticAirCancellatioAccountingRecord.setTdsInPercentage(tds);
				} else 
				{
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
				while(rs5.next())
				{
					PessangerForReport pessangerForReport = new PessangerForReport();
					pessangerForReport.setPnrNo(rs5.getString("confirmation_id"));
					pessangerForReport.seteTicketNo(rs5.getString("psngr_ticket_no"));
					stmt = con.createStatement();
					sqlQuery = "select psngr_title, psngr_first_name, psngr_last_name, psngr_type from " +
							"dbo.dom_air_passengers where pk_psngr_id=" + rs5.getInt("psngr_id");
					//System.out.println("    sql query for getting master distribute id is :::    " + sqlQuery);
					rs6 = stmt.executeQuery(sqlQuery);
					while(rs6.next())
					{
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
				while(rs7.next())
				{
					if("Processed".equalsIgnoreCase(rs7.getString("cancelation_status_return"))){
						stmt = con.createStatement();
						//sqlQuery = "select top 1 cancellation_charges_return, cancellation_process_datetime_return from dbo.dom_air_ticket_details where cancelation_id='" + cancellationId + "'";
						sqlQuery   ="select top 1 cancellation_charges_return,convert(varchar(10),cancellation_process_datetime_return,120) as date,SubString(cancellation_process_datetime_return,12,20) AS time from dbo.dom_air_ticket_details where cancelation_id='" + cancellationId + "'";
						//System.out.println("    sql query for getting account statemnet from account transaction isis :::    " + sqlQuery);                           
						rs8 = stmt.executeQuery(sqlQuery);
						while (rs8.next()) 
						{
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
						while (rs8.next()) 
						{
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
						while (rs8.next()) 
						{
							domesticAirCancellatioAccountingRecord.setCancellationCharges(rs8.getString("cancellation_charges"));
							domesticAirCancellatioAccountingRecord.setCancellationDate(rs8.getString("date"));
							domesticAirCancellatioAccountingRecord.setCancellationTime(rs8.getString("time"));
						}
					}
				}

				domesticAirCancellatioAccountingRecords.add(domesticAirCancellatioAccountingRecord);

			}
			// System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&   LIST SIZE ::  "
			// + domesticAirCancellatioAccountingRecords.size());
			if (domesticAirCancellatioAccountingRecords != null
					&& domesticAirCancellatioAccountingRecords.size() == 0) {
				status = "norecord";
			} else if (domesticAirCancellatioAccountingRecords != null
					&& domesticAirCancellatioAccountingRecords.size() > 100000) {
				status = "downloadnotallowed";
			}
			FileWriter writer = new FileWriter(filePath);

			writer.append("Agent_ID");
			writer.append('\t');
			writer.append("Cancellation Date");
			writer.append('\t');
			writer.append("Cancellation Time");
			writer.append('\t');
			writer.append("Partner Id");
			writer.append('\t');
			writer.append("Refrence No.");
			writer.append('\t');
			writer.append("Journey Type");
			writer.append('\t');
			writer.append("Journey");
			writer.append('\t');
			writer.append("Flight");
			writer.append('\t');
			writer.append("Sector");
			writer.append('\t');
			writer.append("Travel Date");
			writer.append('\t');
			writer.append("PNR No.");
			writer.append('\t');
			writer.append("Passenger Name");
			writer.append('\t');
			writer.append("Passanger Ticket No");
			writer.append('\t');
			writer.append("Basic Fare");
			writer.append('\t');
			writer.append("Taxes");
			writer.append('\t');
			writer.append("Service Taxes");
			writer.append('\t');
			writer.append("TDS IN% Value");
			writer.append('\t');
			writer.append("Gross Total");
			writer.append('\t');
			writer.append("Cancellation Charges");
			writer.append('\t');
			writer.append("Discount Recalled");
			writer.append('\t');
			writer.append("Refunded Amount");
			writer.append('\t');
			writer.append("Opening balance");
			writer.append('\t');
			writer.append("Closing Balance");
			writer.append('\n');

			for (Object obj : domesticAirCancellatioAccountingRecords) 
			{
				DomesticAirCancellatioAccountingRecord DomesticAirCanRecord = (DomesticAirCancellatioAccountingRecord) obj;

				writer.append(DomesticAirCanRecord.getAgentID());
				writer.append('\t');
				writer.append(DomesticAirCanRecord.getCancellationDate());
				writer.append('\t');
				writer.append(DomesticAirCanRecord.getCancellationTime());//
				writer.append('\t');
				writer.append(DomesticAirCanRecord.getPartnerRefID());//
				writer.append('\t');
				writer.append(DomesticAirCanRecord.getArzooReferenceID());//
				writer.append('\t');
				writer.append(DomesticAirCanRecord.getJourneyType());
				writer.append('\t');
				writer.append(DomesticAirCanRecord.getJourneyType());
				writer.append('\t');
				writer.append(DomesticAirCanRecord.getFlight());
				writer.append('\t');
				writer.append(DomesticAirCanRecord.getSector());
				writer.append('\t');
				writer.append(DomesticAirCanRecord.getTravelDate());
				writer.append('\t');
				for (Object obj1 : DomesticAirCanRecord.getPessangers()) 
				{
					PessangerForReport pessangerForReport = (PessangerForReport) obj1;
					writer.append(pessangerForReport.getPnrNo());
					writer.append(", ");
				}
				writer.append('\t');
				for (Object obj1 : DomesticAirCanRecord.getPessangers()) {
					PessangerForReport pessangerForReport = (PessangerForReport) obj1;
					writer.append(pessangerForReport.getPsngrTitle() + " "
							+ pessangerForReport.getPsngrFirstName() + " "
							+ pessangerForReport.getPsngrLastName());
					writer.append(", ");
				}
				writer.append('\t');
				for (Object obj1 : DomesticAirCanRecord.getPessangers()) {
					PessangerForReport pessangerForReport = (PessangerForReport) obj1;
					writer.append(pessangerForReport.geteTicketNo());
					writer.append(", ");
				}
				writer.append('\t');
				writer.append(Integer.toString(DomesticAirCanRecord
						.getBasicFare()));
				writer.append('\t');
				writer.append(Integer.toString(DomesticAirCanRecord.getTaxes()));
				writer.append('\t');
				writer.append(Integer.toString(DomesticAirCanRecord
						.getServiceTaxes()));
				writer.append('\t');
				writer.append(Double.toString(DomesticAirCanRecord
						.getTdsInPercentage()));
				writer.append('\t');
				writer.append(Integer.toString(DomesticAirCanRecord
						.getGrossFare()));
				writer.append('\t');
				writer.append(DomesticAirCanRecord.getCancellationCharges());
				writer.append('\t');
				writer.append(String.valueOf(DomesticAirCanRecord
						.getPartnerCommission()));
				writer.append('\t');
				writer.append(Double.toString(DomesticAirCanRecord
						.getRefundedAmount()));
				writer.append('\t');
				writer.append(Double.toString(DomesticAirCanRecord
						.getOpeningbalance()));
				writer.append('\t');
				writer.append(Double.toString(DomesticAirCanRecord
						.getClosingBalance()));
				writer.append('\n');
			}
			writer.flush();
			writer.close();
		} catch (Exception e) {
			status = "norecord";
			System.out.println("Exception in ReportDao,getAirDomesticCancellationAccountReport");
			System.out.println(e.toString());
		}
		finally 
		{
			try 
			{
				con.close();
				session.flush();
				session.close();

			} catch (Exception e) 
			{
				System.out.println("Exception in ReportDao,getDistributorAmountReport");
				System.out.println(e.toString());
			}
		}

		return status;
	}

	public String getPortalJournalReport(String filePath, String fromDate,String toDate, String loginType, String portalId, String reportOf) 
	{
		Session session = null;
		String Status = "Norecord";
		Connection con =null;

		try 
		{
			session = HibernateSession.getSessionFactory().openSession();


			con = session.connection();

			CallableStatement cstmt = null;

			cstmt = con.prepareCall("{call Admin_reports(?,?,?,?,?)}");

			cstmt.setString(1, reportOf);
			cstmt.setString(2, fromDate);
			cstmt.setString(3, toDate);
			cstmt.setString(4, loginType);
			cstmt.setString(5, portalId);


			ResultSet rs = cstmt.executeQuery();


			Status = "Success";
			FileWriter writer = new FileWriter(filePath);

			writer.append("Client ID");
			writer.append('\t');
			writer.append("User ID");
			writer.append('\t');
			writer.append("Transaction ID");
			writer.append('\t');
			writer.append("Request date");
			writer.append('\t');
			writer.append("Request Time");
			writer.append('\t');
			writer.append("Mode Of Payment");
			writer.append('\t');
			writer.append("Amount");
			writer.append('\t');
			writer.append("Bank Charges");
			writer.append('\t');
			writer.append("Accepted amount");
			writer.append('\t');
			writer.append("Receiving Bank Name");
			writer.append('\t');
			writer.append("Receiving Branch Name");
			writer.append('\t');
			writer.append("Bank Transaction Id");
			writer.append('\t');
			writer.append("Reference ID");
			writer.append('\t');
			writer.append("Receipt No");
			writer.append('\t');
			writer.append("Deposit Date");
			writer.append('\t');
			writer.append("Cheque DD No");
			writer.append('\t');
			writer.append("Approval Date");
			writer.append('\t');
			writer.append("Approved By");
			writer.append('\t');
			writer.append("Status");
			writer.append('\t');
			writer.append("User Account No");
			writer.append('\t');
			writer.append("Company Account No");
			writer.append('\t');
			writer.append("Order ID");
			writer.append('\t');
			writer.append("Refrence No");
			writer.append('\t');
			writer.append("Remarks");
			writer.append('\t');
			writer.append("Admin Remarks");
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
			Status = "Norecord";
			System.out.println("Exception in ReportDao,getPortalJournalReport");
			System.out.println(e.toString());


		} finally {
			try 
			{
				if(con!=null)con.close();
				if(session!=null)session.close();
			} catch (Exception e) 
			{
				System.out.println("Exception in ReportDao,getPortalJournalReport");
				System.out.println(e.toString());
			}
		}
		return Status;

	}

	public String getBookingHotalReport(String filePath, String fromDate,String toDate, String loginType, String portalId, String reportOf) 
	{
		Session session = null;
		String Status = "Norecord";
		Connection con =null;

		try {
			session = HibernateSession.getSessionFactory().openSession();

			con = session.connection();

			CallableStatement cstmt = null;

			cstmt = con.prepareCall("{call Admin_reports(?,?,?,?,?)}");

			cstmt.setString(1, reportOf);
			cstmt.setString(2, fromDate);
			cstmt.setString(3, toDate);
			cstmt.setString(4, loginType);
			cstmt.setString(5, portalId);

			ResultSet rs = cstmt.executeQuery();
			FileWriter writer = new FileWriter(filePath);

			writer.append("AGID");
			writer.append('\t');
			writer.append("TranID");
			writer.append('\t');
			writer.append(" TranNo");
			writer.append('\t');
			writer.append("Date_of_Transaction");
			writer.append('\t');
			writer.append("time");
			writer.append('\t');
			writer.append("ReqAmount");
			writer.append('\t');
			writer.append("Commession");
			writer.append('\t');
			writer.append("Service_charge1");
			writer.append('\t');
			writer.append("DeductedAmt");
			writer.append('\t');
			writer.append("Tran_status");
			writer.append('\t');
			writer.append("Remark");
			writer.append('\t');
			writer.append("Transaction_Id");
			writer.append('\t');
			writer.append("Hotel_Name");
			writer.append('\t');

			writer.append('\n');
			System.out.println("we are in success");
			Status = "Success";
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

				writer.append('\n');

				Status="Success";
			}
			writer.flush();
			writer.close();

			//	txn.commit();
		} catch (Exception e) {
			Status = "Norecord";
			System.out.println("Exception in ReportDao,getBookingHotalReport");
			System.out.println(e.toString());


		} finally {
			try {
				if(con!=null)con.close();
				if(session!=null)session.close();

			} catch (Exception e) {
				System.out.println("Exception in ReportDao,getBookingHotalReport");
				System.out.println(e.toString());
			}
		}
		return Status;
	}

	public String getOpsTrancationReport(String filePath, String fromDate,String toDate, String loginType, String portalId, String reportOf) 
	{
		Session session = null;
		String Status = "Norecord";
		Connection con =null;

		try {
			session = HibernateSession.getSessionFactory().openSession();

			con = session.connection();

			CallableStatement cstmt = null;

			cstmt = con.prepareCall("{call Admin_reports(?,?,?,?,?)}");

			cstmt.setString(1, reportOf);
			cstmt.setString(2, fromDate);
			cstmt.setString(3, toDate);
			cstmt.setString(4, loginType);
			cstmt.setString(5, portalId);


			ResultSet rs = cstmt.executeQuery();
			FileWriter writer = new FileWriter(filePath);

			writer.append("AGID");
			writer.append('\t');
			writer.append("TranID");
			writer.append('\t');
			writer.append("UserType");
			writer.append('\t');
			writer.append("TranNo");
			writer.append('\t');
			writer.append("Date_of_Transaction");
			writer.append('\t');
			writer.append("time");
			writer.append('\t');
			writer.append("ReqAmount");
			writer.append('\t');
			writer.append("Commession");
			writer.append('\t');
			writer.append("Service_charge1");
			writer.append('\t');
			writer.append("DeductedAmt");
			writer.append('\t');
			writer.append(" Tran_status");
			writer.append('\t');
			writer.append("Remark");
			writer.append('\t');
			writer.append("tran_id");
			writer.append('\t');
			writer.append("service");
			writer.append('\t');

			writer.append('\n');
			System.out.println("we are in success");
			Status = "Success";
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

				writer.append('\n');

			}
			writer.flush();
			writer.close();

			//	txn.commit();
		} catch (Exception e) {
			Status = "Norecord";
			System.out.println("Exception in ReportDao,getOpsTrancationReport");
			System.out.println(e.toString());


		} finally {
			try {
				if(con!=null)con.close();
				if(session!=null)session.close();

			} catch (Exception e) {
				System.out.println("Exception in ReportDao,getOpsTrancationReport");
				System.out.println(e.toString());
			}

		}
		return Status;
	}

	public String getPortalAccountStatReport(String filePath, String fromDate,String toDate, String loginType, String portalId, String reportOf) {
		Session session = null;
		String Status = "Norecord";
		Connection con =null;

		try {
			session = HibernateSession.getSessionFactory().openSession();

			con = session.connection();

			CallableStatement cstmt = null;

			cstmt = con.prepareCall("{call Admin_reports(?,?,?,?,?)}");

			cstmt.setString(1, reportOf);
			cstmt.setString(2, fromDate);
			cstmt.setString(3, toDate);
			cstmt.setString(4, loginType);
			cstmt.setString(5, portalId);


			ResultSet rs = cstmt.executeQuery();
			FileWriter writer = new FileWriter(filePath);

			writer.append("Client ID");
			writer.append('\t');
			writer.append("Portal ID");
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
			writer.append("Transaction Amount");
			writer.append('\t');
			writer.append("Commission");
			writer.append('\t');
			writer.append("Service Charge");
			writer.append('\t');
			writer.append("Bank Charge");
			writer.append('\t');
			writer.append("Other Charge");
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
			writer.append('\t');
			writer.append("Transfer To");
			writer.append('\n');
			System.out.println("we are in success");
			Status = "Success";
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
				writer.append('\n');

			}
			writer.flush();
			writer.close();

			//	txn.commit();
		} catch (Exception e) {
			Status = "Norecord";
			System.out.println("Exception in ReportDao,getPortalAccountStatReport");
			System.out.println(e.toString());


		} finally {
			try {
				if(con!=null)con.close();
				if(session!=null)session.close();

			} catch (Exception e) {
				System.out.println("Exception in ReportDao,getPortalAccountStatReport");
				System.out.println(e.toString());
			}

		}
		return Status;
	}
	// method add for suspect report
	public String getSuspectDataReport(String filePath, String fromdate,String todate, String portalId, String loginType) {

		Session session = null;
		String Status = "Norecord";
		Query qry = null;
		String sqlQuery=null;
		String vendorId;
		Connection con =null;
		try {
			session = HibernateSession.getSessionFactory().openSession();

			//
			con = session.connection();
			//
			//			CallableStatement cstmt = null;
			//
			//			cstmt = con.prepareCall("{call Admin_Suspect_reports(?,?,?,?,?,?)}");
			//
			//			cstmt.setString(1, fromdate);
			//			cstmt.setString(2, todate);
			//			cstmt.setString(3, userType);
			//			cstmt.setString(4, userIdonly);
			//			cstmt.setString(5, loginType);
			//			cstmt.setString(6, portalId);
			//			ResultSet rs = cstmt.executeQuery();
			//
			//			txn.commit();
			if(loginType.equalsIgnoreCase("superadmin")||loginType.equalsIgnoreCase("activityAdmin")||loginType.equalsIgnoreCase("activityUser")){
				sqlQuery="select b.agent_initial+convert(varchar(12),a.agent_id),convert(varchar(10),l.date_of_recharge,101),convert(varchar(10),l.date_time,108),l.mobile_operator,l.mobile_number,l.amount,a.Transaction_Id,l.UsessionId,a.Tran_status from live_recharge l, agent_transaction_details a,agent_details b where convert(varchar(10),l.date_of_suspect,120) between'2012-10-26' and '2012-10-31' and l.suspect_flag='1' and a.Transaction_No=l.tran_id and a.agent_id=b.agent_id";
			}else{
				sqlQuery="select b.agent_initial+convert(varchar(12),a.agent_id),convert(varchar(10),l.date_of_recharge,101),convert(varchar(10),l.date_time,108),l.mobile_operator,l.mobile_number,l.amount,a.Transaction_Id,l.UsessionId,a.Tran_status from live_recharge l, agent_transaction_details a,agent_details b where convert(varchar(10),l.date_of_suspect,120) between'2012-10-26' and '2012-10-31' and l.suspect_flag='1' and a.Transaction_No=l.tran_id and a.agent_id=b.agent_id and agent_id in (select agent_id from agent_details where distributor_id in (select distributor_id from distributor_details where md_id in (select md_id from md_details where client_id='"+portalId+"')))" ;
			}
			System.out.println("sqlQuery :: "+sqlQuery);
			qry = session.createSQLQuery(sqlQuery);
			//log.print("Query in  class-SuspectTranDao and method getAllTran--"+qry, logger);
			List list =qry.list();
			Iterator itr = list.iterator();
			Object row[];
			Status = "Success";
			FileWriter writer = new FileWriter(filePath);

			writer.append("Agent ID");
			writer.append('\t');
			writer.append("Date of recharge");
			writer.append('\t');
			writer.append("Time of recharge");
			writer.append('\t');
			writer.append("Operator");
			writer.append('\t');
			writer.append("Mobile No");
			writer.append('\t');
			writer.append("Gross Amount");
			writer.append('\t');
			writer.append("Transaction ID");
			writer.append('\t');
			writer.append("Vendor Transaction ID");
			writer.append('\t');
			writer.append("Status");
			writer.append('\n');
			while (itr.hasNext()) {
				row = (Object[])itr.next();
				//System.out.println("we are here Manoj in loop "+row[1].toString());
				writer.append(row[0].toString());
				writer.append('\t');
				writer.append(row[1].toString());
				writer.append('\t');
				writer.append(row[2].toString());
				writer.append('\t');
				writer.append(row[3].toString());
				writer.append('\t');
				writer.append(row[4].toString());
				writer.append('\t');
				writer.append(row[5].toString());
				writer.append('\t');
				writer.append(row[6].toString());
				writer.append('\t');
				vendorId=(String) row[7];
				if(vendorId==null){
					vendorId="";
				}
				//System.out.println("we are here Manoj in loop "+vendorId);
				writer.append(vendorId);

				writer.append('\t');
				writer.append(row[8].toString());
				writer.append('\n');
			}
			writer.flush();
			writer.close();
		} catch (Exception e) {
			Status = "Norecord";
			System.out.println("Exception in ReportDao,getSuspectDataReport");
			System.out.println(e.toString());
		}finally {
			try {
				con.close();
				session.flush();
				session.close();

			} catch (Exception e) {
				System.out.println("Exception in ReportDao,getSuspectDataReport");
				System.out.println(e.toString());
			}

		}
		//System.out.println("status is ::"+Status);
		return Status;

	}

	public String getB2BDomesticFlightReport(String filePath, String fromDate,String toDate, String portalId, String loginType, String reportOf) {
		Session session = null;
		String Status = "Norecord";
		Connection con =null;

		try {
			session = HibernateSession.getSessionFactory().openSession();
			con = session.connection();

			CallableStatement cstmt = null;

			cstmt = con.prepareCall("{call Admin_reports(?,?,?,?,?)}");

			cstmt.setString(1, reportOf);
			cstmt.setString(2, fromDate);
			cstmt.setString(3, toDate);
			cstmt.setString(4, loginType);
			cstmt.setString(5, portalId);

			ResultSet rs = cstmt.executeQuery();
			FileWriter writer = new FileWriter(filePath);

			writer.append("Master Distributor ID");
			writer.append('\t');
			writer.append("Distributor ID");
			writer.append('\t');
			writer.append("Agent ID");
			writer.append('\t');
			writer.append("ID No");
			writer.append('\t');
			writer.append("Transaction ID");
			writer.append('\t');
			writer.append("User Type");
			writer.append('\t');
			writer.append("Transaction No");
			writer.append('\t');
			writer.append("Date of Transaction");
			writer.append('\t');
			writer.append("Time of Transaction");
			writer.append('\t');
			writer.append("Service");
			writer.append('\t');
			writer.append("Balance Before Txn");
			writer.append('\t');
			writer.append("Txn Amount");
			writer.append('\t');
			writer.append("Commission");
			writer.append('\t');
			writer.append("Service Charge");
			writer.append('\t');
			writer.append("Deducted Amount");
			writer.append('\t');
			writer.append("Action on Balance");
			writer.append('\t');
			writer.append("Balance After Txn");
			writer.append('\t');

			writer.append("Transaction Status");
			writer.append('\t');
			writer.append("Updated Date");
			writer.append('\t');
			writer.append("Updated Time");
			writer.append('\t');
			writer.append("Agent Final Balance");
			writer.append('\t');
			writer.append("IP Address");
			writer.append('\t');
			writer.append("Remark");
			writer.append('\t');
			writer.append("External Remark");
			writer.append('\t');
			writer.append("Ticket Reference ID");
			writer.append('\n');
			System.out.println("we are in success");
			Status = "Success";
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
			Status = "Norecord";
			System.out.println("Exception in ReportDao,getB2BDomesticFlightReport");
			System.out.println(e.toString());
		}	
		finally {
			try {
				con.close();
				session.flush();
				session.close();

			} catch (Exception e) {
				System.out.println("Exception in ReportDao,getB2BDomesticFlightReport");
				System.out.println(e.toString());
			}
		}
		return Status;
	}

	public String getB2BInternationalFlightReport(String filePath,String fromDate, String toDate, String portalId, String loginType, String reportOf) {
		Session session = null;
		String Status = "Norecord";
		Connection con =null;

		try {
			session = HibernateSession.getSessionFactory().openSession();
			con = session.connection();

			CallableStatement cstmt = null;

			cstmt = con.prepareCall("{call Admin_reports(?,?,?,?,?)}");

			cstmt.setString(1, reportOf);
			cstmt.setString(2, fromDate);
			cstmt.setString(3, toDate);
			cstmt.setString(4, loginType);
			cstmt.setString(5, portalId);

			ResultSet rs = cstmt.executeQuery();
			FileWriter writer = new FileWriter(filePath);

			writer.append("Master Distributor ID");
			writer.append('\t');
			writer.append("Distributor ID");
			writer.append('\t');
			writer.append("Agent ID");
			writer.append('\t');
			writer.append("ID No");
			writer.append('\t');
			writer.append("Transaction ID");
			writer.append('\t');
			writer.append("User Type");
			writer.append('\t');
			writer.append("Transaction No");
			writer.append('\t');
			writer.append("Date of Transaction");
			writer.append('\t');
			writer.append("Time of Transaction");
			writer.append('\t');
			writer.append("Service");
			writer.append('\t');
			writer.append("Balance Before Txn");
			writer.append('\t');
			writer.append("Txn Amount");
			writer.append('\t');
			writer.append("Commission");
			writer.append('\t');
			writer.append("Service Charge");
			writer.append('\t');
			writer.append("Deducted Amount");
			writer.append('\t');
			writer.append("Action on Balance");
			writer.append('\t');
			writer.append("Balance After Txn");
			writer.append('\t');

			writer.append("Transaction Status");
			writer.append('\t');
			writer.append("Updated Date");
			writer.append('\t');
			writer.append("Updated Time");
			writer.append('\t');
			writer.append("Agent Final Balance");
			writer.append('\t');
			writer.append("IP Address");
			writer.append('\t');
			writer.append("Remark");
			writer.append('\t');
			writer.append("External Remark");
			writer.append('\t');
			writer.append("Ticket Reference ID");
			writer.append('\n');
			System.out.println("we are in success");
			Status = "Success";
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
			Status = "Norecord";
			System.out.println("Exception in ReportDao,getB2BInternationalFlightReport");
			System.out.println(e.toString());
		} finally {
			try {
				if(con!=null)con.close();
				if(session!=null)session.close();
			} catch (Exception e) {
				System.out.println("Exception in ReportDao,getB2BInternationalFlightReport");
				System.out.println(e.toString());
			}
		}
		return Status;
	}

	public ArrayList getLiveRechargeReportView(String fromDate, String toDate,String reportOf, String loginType, String portalId,HttpServletRequest request) 
	{
		Session session = null;
		//Transaction txn = null;
		String Status = "Norecord";
		ArrayList listData=new ArrayList();
		Connection con =null;

		try 
		{
			session = HibernateSession.getSessionFactory().openSession();

			//	txn = session.beginTransaction();

			con = session.connection();

			CallableStatement cstmt = null;
			System.out.println(reportOf+" : "+fromDate+" : "+toDate+" : "+loginType+" : "+portalId);
			cstmt = con.prepareCall("{call Report_View(?,?,?,?,?)}");
			cstmt.setString(1, reportOf);
			cstmt.setString(2, fromDate);
			cstmt.setString(3, toDate);
			cstmt.setString(4, loginType);
			cstmt.setString(5, portalId);
			ResultSet rs = cstmt.executeQuery();


			while (rs.next()) {
				HashMap map=new HashMap();

				map.put("agentId", rs.getString("agentId"));
				map.put("dor", rs.getString("dor"));
				map.put("time",rs.getString("time") );
				map.put("mobno", rs.getString("mobno"));
				map.put("operator", rs.getString("operator"));
				map.put("TepTranId", rs.getString("TepTranId")==null ? "--" : rs.getString("TepTranId"));
				map.put("amt", rs.getString("amt"));
				map.put("OperatorTranId", rs.getString("OperatorTranId")==null ? "--" : rs.getString("OperatorTranId"));
				map.put("comm", rs.getString("comm")==null ? "--" : rs.getString("comm"));
				map.put("ServiceProvider",rs.getString("ServiceProvider") );
				map.put("transtatus", rs.getString("status"));
				map.put("Service_charge1", rs.getString("Service_charge1")==null ? "--" : rs.getString("Service_charge1"));

				listData.add(map);
			}
			System.out.println("List Data : "+listData);
		} catch (Exception e) {
			Status = "Norecord";
			request.setAttribute("NOData",Status);
			System.out.println("Exception in ReportDao,getLiveRechargeReport");
			System.out.println(e.toString());

		} finally {
			try {
				con.close();
				session.flush();
				session.close();
			} catch (Exception e) {
				System.out.println("Exception in ReportDao,getLiveRechargeReport");
				System.out.println(e.toString());
			}
		}
		return listData;
	}

	public ArrayList getLiveDMRReportView(String fromDate, String toDate,String reportOf, String loginType, String portalId,HttpServletRequest request,String agentId) 
	{
		Session session = null;
		String Status = "Norecord";
		ArrayList listData=new ArrayList();
		Connection con =null;
		double totBusiness=0,totComm=0,totCharge=0,totNetBusiness=0;
		try 
		{
			session = HibernateSession.getSessionFactory().openSession();

			con = session.connection();

			PreparedStatement pstmt = null;
			System.out.println(reportOf+" : "+fromDate+" : "+toDate+" : "+loginType+" : "+portalId);
			if("all".equalsIgnoreCase(agentId)){
				pstmt=con.prepareStatement("select std.Agent_id,std.Sender_Id,std.Date_of_Transaction,std.Time_of_Transaction,"
						+ "std.Transaction_No,isnull(std.Reference_id,'') as UTR,atd.Service,std.Tran_status,atd.Agent_balAmt_b_Ded,"
						+ "atd.ReqAmount,atd.Commession,atd.Service_charge1,atd.DeductedAmt,atd.Agent_balAmt_A_Ded,"
						+ "isnull(std.Bene_Name,'') as beneName,isnull(std.Bene_Account,'') as beneAccount,"
						+ "isnull(std.Bene_Bank_Name,'') as beneBankName,isnull(std.Bene_Bank_IFSC,'') as beneBankIfsc from DMR_SENDER_TRANSACTION_DETAILS std,agent_transaction_details atd where std.Transaction_No=atd.Transaction_No and std.Agent_id=atd.Agent_id and std.Date_of_Transaction=atd.Date_of_Transaction and std.Date_of_Transaction between '"+fromDate+"' and '"+toDate+"' order by Date_of_Transaction desc,Time_of_Transaction desc");

			}else{
				pstmt=con.prepareStatement("select std.Agent_id,std.Sender_Id,std.Date_of_Transaction,std.Time_of_Transaction,"
						+ "std.Transaction_No,isnull(std.Reference_id,'') as UTR,atd.Service,std.Tran_status,atd.Agent_balAmt_b_Ded,"
						+ "atd.ReqAmount,atd.Commession,atd.Service_charge1,atd.DeductedAmt,atd.Agent_balAmt_A_Ded,"
						+ "isnull(std.Bene_Name,'') as beneName,isnull(std.Bene_Account,'') as beneAccount,"
						+ "isnull(std.Bene_Bank_Name,'') as beneBankName,isnull(std.Bene_Bank_IFSC,'') as beneBankIfsc from DMR_SENDER_TRANSACTION_DETAILS std,agent_transaction_details atd where std.Transaction_No=atd.Transaction_No and std.Agent_id=atd.Agent_id and std.Date_of_Transaction=atd.Date_of_Transaction and atd.Agent_id="+Long.parseLong(agentId)+" and std.Date_of_Transaction between '"+fromDate+"' and '"+toDate+"' order by Date_of_Transaction desc,Time_of_Transaction desc");

			}

			ResultSet rs = pstmt.executeQuery();


			while (rs.next()) {
				HashMap<String,String> map=new HashMap<String,String>();

				map.put("agentId", "AG"+rs.getString(1));
				map.put("senderId", rs.getString(2));
				map.put("dor", rs.getString(3));
				map.put("time",rs.getString(4) );
				map.put("tranId", rs.getString(5));
				map.put("bankRefId", rs.getString(6));
				map.put("service", rs.getString(7));
				map.put("status", rs.getString(8));
				map.put("prevBal", rs.getString(9));
				map.put("reqAmount", rs.getString(10));
				map.put("comm",rs.getString(11) );
				map.put("ServiceCharge", rs.getString(12));
				map.put("netAmount", rs.getString(13));
				map.put("afterBal", rs.getString(14));
				map.put("beneName", rs.getString(15));
				map.put("beneAccount", rs.getString(16));
				map.put("beneBankName", rs.getString(17));
				map.put("beneBankIfsc", rs.getString(18));

				totBusiness=totBusiness+rs.getDouble(10);
				totCharge=totCharge+rs.getDouble(12);
				totComm=totComm+rs.getDouble(11);
				totNetBusiness=totNetBusiness+rs.getDouble(13);

				listData.add(map);
			}

			HashMap<String,String> map=new HashMap<String,String>();

			map.put("agentId", "");
			map.put("senderId", "");
			map.put("dor", "");
			map.put("time","");
			map.put("tranId", "");
			map.put("bankRefId", "");
			map.put("service", "");
			map.put("status", "");
			map.put("prevBal", "<span style='font-weight: bold;' >Total</span>");
			map.put("reqAmount", "<span style='background:RED;font-weight: bold;' >"+totBusiness+"</span>");
			map.put("comm","<span style='background:RED;font-weight: bold;' >"+totComm+"</span>");
			map.put("ServiceCharge", "<span style='background:RED;font-weight: bold;' >"+totCharge+"</span>");
			map.put("netAmount", "<span style='background:RED;font-weight: bold;' >"+totNetBusiness+"</span>");
			map.put("afterBal", "");
			map.put("beneName", "");
			map.put("beneAccount", "");
			map.put("beneBankName", "");
			map.put("beneBankIfsc", "");
			listData.add(map);

			System.out.println("List Data : "+listData);
		} catch (Exception e) {
			Status = "Norecord";
			request.setAttribute("NOData",Status);
			System.out.println("Exception in ReportDao,getLiveRechargeReport");
			e.printStackTrace();

		} finally {
			try {
				if(con!=null)con.close();
				if(session!=null)session.close();
			} catch (Exception e) {
				System.out.println("Exception in ReportDao,getLiveRechargeReport");
				System.out.println(e.toString());
			}
		}
		return listData;
	}


	public ArrayList getB2BDomFlightTransactionReportView(String fromDate,String toDate, String reportOf, String loginType, String portalId,HttpServletRequest request) 
	{
		Session session = null;
		String Status = "Norecord";
		ArrayList listData=new ArrayList();
		Connection con =null;

		try {
			session = HibernateSession.getSessionFactory().openSession();

			con = session.connection();

			CallableStatement cstmt = null;

			cstmt = con.prepareCall("{call Report_View(?,?,?,?,?)}");

			cstmt.setString(1, reportOf);
			cstmt.setString(2, fromDate);
			cstmt.setString(3, toDate);
			cstmt.setString(4, loginType);
			cstmt.setString(5, portalId);		

			ResultSet rs = cstmt.executeQuery();

			while (rs.next()) 
			{
				HashMap map=new HashMap();

				map.put("agentId", rs.getString("Agent_id"));
				map.put("dor", rs.getString("Date_of_Transaction"));
				map.put("time",rs.getString("Time_of_Transaction"));
				map.put("mobno", rs.getString("Reference_id")==null ? "--" : rs.getString("Reference_id"));
				map.put("operator", rs.getString("eticketno")==null ? "--" : rs.getString("eticketno"));
				map.put("TepTranId", rs.getString("Transaction_Id"));
				map.put("amt", rs.getString("ReqAmount"));
				map.put("OperatorTranId", rs.getString("Commession")==null ? "--" : rs.getString("Commession"));
				map.put("comm", rs.getString("Service_charge1")==null ? "--" : rs.getString("Service_charge1"));
				map.put("Service_charge1", rs.getString("DeductedAmt")==null ? "--" : rs.getString("DeductedAmt"));
				map.put("transtatus", rs.getString("Tran_status")==null ? "--" : rs.getString("Tran_status"));
				map.put("ServiceProvider", rs.getString("Remark")==null ? "--" : rs.getString("Remark"));
				listData.add(map);
			}
		} catch (Exception e) 
		{
			Status = "Norecord";
			request.setAttribute("NOData",Status);
			System.out.println("Exception in ReportDao,getB2BDomFlightTransactionReportView");
			System.out.println(e.toString());

		} finally {
			try {
				if(con!=null)con.close();
				if(session!=null)session.close();

			} catch (Exception e) 
			{
				System.out.println("Exception in ReportDao,getB2BDomFlightTransactionReportView");
				System.out.println(e.toString());
			}
		}
		return listData;
	}

	public ArrayList getBusTransactionReportView(String fromDate,String toDate, String reportOf, String loginType, String portalId,HttpServletRequest request) 
	{
		Session session = null;
		String Status = "Norecord";
		ArrayList listData=new ArrayList();
		Connection con =null;

		try {
			session = HibernateSession.getSessionFactory().openSession();

			con = session.connection();

			CallableStatement cstmt = null;

			cstmt = con.prepareCall("{call Report_View(?,?,?,?,?)}");

			cstmt.setString(1, reportOf);
			cstmt.setString(2, fromDate);
			cstmt.setString(3, toDate);
			cstmt.setString(4, loginType);
			cstmt.setString(5, portalId);		

			ResultSet rs = cstmt.executeQuery();

			while (rs.next())
			{
				HashMap map=new HashMap();

				map.put("agentId", rs.getString("agentId"));
				map.put("dor", rs.getString("tranDate"));
				map.put("time",rs.getString("time") );
				map.put("mobno", rs.getString("ticno"));
				map.put("operator", rs.getString("transaction_id"));
				map.put("TepTranId", rs.getString("ticfare"));
				map.put("amt", rs.getString("comm")==null ? "--" : rs.getString("comm"));
				map.put("OperatorTranId", rs.getString("cancelchg")==null ? "--" : rs.getString("cancelchg"));
				map.put("comm", rs.getString("debit"));
				map.put("Service_charge1", rs.getString("refamt")==null ? "--" : rs.getString("refamt"));
				map.put("transtatus", rs.getString("ticketstatus")==null ? "--" : rs.getString("ticketstatus"));
				map.put("ServiceProvider",rs.getString("trantype") );

				listData.add(map);
			}
		} catch (Exception e) {
			Status = "Norecord";
			request.setAttribute("NOData",Status);
			System.out.println("Exception in ReportDao,getBusTransactionReportView");
			System.out.println(e.toString());

		} finally {
			try {
				con.close();
				session.flush();
				session.close();if(con!=null)con.close();
				if(session!=null)session.close();
			} catch (Exception e) {
				System.out.println("Exception in ReportDao,getBusTransactionReport");
				System.out.println(e.toString());
			}
		}
		return listData;
	}

	public ArrayList getBookingHotalReportView(String fromDate,String toDate, String reportOf, String loginType, String portalId,HttpServletRequest request) 
	{
		Session session = null;
		//Transaction txn = null;
		String Status = "Norecord";
		ArrayList listData=new ArrayList();
		Connection con =null;

		try {
			session = HibernateSession.getSessionFactory().openSession();


			con = session.connection();

			CallableStatement cstmt = null;

			cstmt = con.prepareCall("{call Report_View(?,?,?,?,?)}");

			cstmt.setString(1, reportOf);
			cstmt.setString(2, fromDate);
			cstmt.setString(3, toDate);
			cstmt.setString(4, loginType);
			cstmt.setString(5, portalId);		

			ResultSet rs = cstmt.executeQuery();

			while (rs.next()) 
			{
				HashMap map=new HashMap();

				map.put("agentId", rs.getString("AGID"));
				map.put("dor", rs.getString("Date_of_Transaction"));
				map.put("time",rs.getString("time"));
				map.put("mobno", rs.getString("TranNo"));
				map.put("operator", rs.getString("Hotel_Name")==null ? "--" : rs.getString("Hotel_Name"));
				map.put("TepTranId", rs.getString("TranID"));
				map.put("amt", rs.getString("ReqAmount"));
				map.put("OperatorTranId", rs.getString("Commession")==null ? "--" : rs.getString("Commession"));
				map.put("comm", rs.getString("Service_charge1")==null ? "--" : rs.getString("Service_charge1"));
				map.put("Service_charge1", rs.getString("DeductedAmt")==null ? "--" : rs.getString("DeductedAmt"));
				map.put("transtatus", rs.getString("Tran_status"));
				map.put("ServiceProvider", rs.getString("Remark")==null ? "--" : rs.getString("Remark"));

				listData.add(map);
			}
		} catch (Exception e) {
			Status = "Norecord";
			request.setAttribute("NOData",Status);
			System.out.println("Exception in ReportDao,getBookingHotalReportView");
			System.out.println(e.toString());

		} finally {
			try {
				if(con!=null)con.close();
				if(session!=null)session.close();
			} catch (Exception e) {
				System.out.println("Exception in ReportDao,getBookingHotalReportView");
				System.out.println(e.toString());
			}
		}
		return listData;
	}

	public ArrayList getopstransactionReportView(String fromDate,String toDate, String reportOf, String loginType, String portalId,HttpServletRequest request) 
	{
		Session session = null;

		String Status = "Norecord";
		ArrayList listData=new ArrayList();
		Connection con =null;

		try {
			session = HibernateSession.getSessionFactory().openSession();

			con = session.connection();

			CallableStatement cstmt = null;
			//System.out.println("befor callin SP");
			cstmt = con.prepareCall("{call Report_View(?,?,?,?,?)}");

			cstmt.setString(1, reportOf);
			cstmt.setString(2, fromDate);
			cstmt.setString(3, toDate);
			cstmt.setString(4, loginType);
			cstmt.setString(5, portalId);		

			ResultSet rs = cstmt.executeQuery();

			while (rs.next()) 
			{
				HashMap map=new HashMap();

				map.put("agentId", rs.getString("AGID"));
				map.put("dor", rs.getString("Date_of_Transaction"));
				map.put("time",rs.getString("time"));
				map.put("mobno", rs.getString("TranNo"));
				map.put("operator", rs.getString("service"));
				map.put("TepTranId", rs.getString("TranID"));
				map.put("amt", rs.getString("ReqAmount"));
				map.put("OperatorTranId", rs.getString("Commession")==null ? "--" : rs.getString("Commession"));
				map.put("comm", rs.getString("Service_charge1")==null ? "--" : rs.getString("Service_charge1"));
				map.put("Service_charge1", rs.getString("DeductedAmt")==null ? "--" : rs.getString("DeductedAmt"));
				map.put("transtatus", rs.getString("Tran_status")==null ? "--" : rs.getString("Tran_status"));
				map.put("ServiceProvider", rs.getString("Remark"));

				listData.add(map);
			}
		} catch (Exception e) {
			Status = "Norecord";
			request.setAttribute("NOData",Status);
			System.out.println("Exception in ReportDao,getopstransactionReportView");
			System.out.println(e.toString());

		} finally {
			try {
				if(con!=null)con.close();
				if(session!=null)session.close();
			} catch (Exception e) {
				System.out.println("Exception in ReportDao,getopstransactionReportView");
				System.out.println(e.toString());
			}
		}
		return listData;
	}

	public ArrayList getB2BinterFlightTransactionReportView(String fromDate,String toDate, String reportOf, String loginType, String portalId,HttpServletRequest request) 
	{
		Session session = null;
		String Status = "Norecord";
		ArrayList listData=new ArrayList();
		Connection con =null;

		try {
			session = HibernateSession.getSessionFactory().openSession();

			con = session.connection();

			CallableStatement cstmt = null;

			cstmt = con.prepareCall("{call Report_View(?,?,?,?,?)}");

			cstmt.setString(1, reportOf);
			cstmt.setString(2, fromDate);
			cstmt.setString(3, toDate);
			cstmt.setString(4, loginType);
			cstmt.setString(5, portalId);		

			ResultSet rs = cstmt.executeQuery();

			while (rs.next()) 
			{

				HashMap map=new HashMap();

				map.put("agentId", rs.getString("Agent_id"));
				map.put("dor", rs.getString("Date_of_Transaction"));
				map.put("time",rs.getString("Time_of_Transaction"));
				map.put("mobno", rs.getString("Reference_id")==null ? "--" : rs.getString("Reference_id"));
				map.put("operator", rs.getString("eticketno"));
				map.put("TepTranId", rs.getString("Transaction_Id"));
				map.put("amt", rs.getString("ReqAmount"));
				map.put("OperatorTranId", rs.getString("Commession")==null ? "--" : rs.getString("Commession"));
				map.put("comm", rs.getString("Service_charge1")==null ? "--" : rs.getString("Service_charge1"));
				map.put("Service_charge1", rs.getString("DeductedAmt")==null ? "--" : rs.getString("DeductedAmt"));
				map.put("transtatus", rs.getString("Tran_status"));
				map.put("ServiceProvider", rs.getString("Remark")==null ? "--" : rs.getString("Remark"));

				listData.add(map);
			}

		} catch (Exception e) 
		{
			Status = "Norecord";
			request.setAttribute("NOData",Status);
			System.out.println("Exception in ReportDao,getB2BinterFlightTransactionReportView");
			System.out.println(e.toString());

		} finally 
		{
			try 
			{
				if(con!=null)con.close();
				if(session!=null)session.close();

			} catch (Exception e) {
				System.out.println("Exception in ReportDao,getB2BinterFlightTransactionReportView");
				System.out.println(e.toString());
			}
		}
		return listData;
	}
}