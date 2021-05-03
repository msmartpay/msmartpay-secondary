package com.reports.EGEN;
/*
 * Created By   : Manoj 
 * Created Date : 24 may 2012
 * Matter       : This class is used for Egen report.We are using Admin_Egen_reports stored Procedure in OnlineRechAPI_db
 */
import java.io.FileWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.common.HibernateSession1;

public class EgenReportDao {

	@SuppressWarnings("deprecation")
	public String getApiAccountStatement(String filePath, String fromDate,String toDate, String reportOfEgen, String reportFor) 
	{
		Session session=null;
		
		String Status="Norecord";
		
		Connection con=null;
		try
		{
			session = HibernateSession1.getSessionFactory().openSession(); 
 		    
			con=session.connection(); 
			 
			CallableStatement cstmt =null;
			 
			cstmt=con.prepareCall("{call Admin_Egen_reports(?,?,?,?)}");
			cstmt.setString(1,reportOfEgen);
			cstmt.setString(2,reportFor);
			cstmt.setString(3,fromDate);
			cstmt.setString(4,toDate);
              
			ResultSet  rs=cstmt.executeQuery();		
			Status="Success";
			FileWriter writer = new FileWriter(filePath);
			
			writer.append("ID");
			writer.append(';');
			writer.append("Transaction ID");
			writer.append(';');
			writer.append("Reference ID");
			writer.append(';');
			writer.append("Agent ID");
			writer.append(';');
			writer.append("Sub Agent Id");
			writer.append(';');
			writer.append("Date of transaction");
			writer.append(';');
			writer.append("Time of Transaction");
			writer.append(';');
			writer.append("Service");
			writer.append(';');
			writer.append("Transaction Amount");
			writer.append(';');
			writer.append("Commission");
			writer.append(';');
			writer.append("Service charge");
			writer.append(';');
			writer.append("Bank Charges");
			writer.append(';');
			writer.append("Other Charges");
			writer.append(';');
			writer.append("Net Transaction Amount");
			writer.append(';');
			writer.append("Action on Balance Amount");
			writer.append(';');
			writer.append("Previous Balance Amount");
			writer.append(';');
			writer.append("Updated Balance Amount");
			writer.append(';');
			writer.append("Transaction Status");
			writer.append(';');
			writer.append("Final Balance");
			writer.append(';');
			writer.append("Transaction IP");
			writer.append(';');
			writer.append("Update Date");
			writer.append(';');
			writer.append("Update Time");
			writer.append(';');
			writer.append("Update User");
			writer.append(';');
			writer.append("Update IP");
			writer.append(';');
			writer.append("Remark");
			writer.append('\n');
			
			while(rs.next())
			{
				writer.append(rs.getString("ID_No"));
				writer.append(';');
				writer.append(rs.getString("Corporate_Transaction_Id"));
				writer.append(';');
				writer.append(rs.getString("Refrence_Id"));
				writer.append(';');
				writer.append(rs.getString("APIagentId"));
				writer.append(';');
				writer.append(rs.getString("Sub_Agent_Id"));
				writer.append(';');
				writer.append(rs.getString("Date_of_Transaction"));
				writer.append(';');
				writer.append(rs.getString("transactiontime"));
				writer.append(';');
				writer.append(rs.getString("Service"));
				writer.append(';');
				writer.append(rs.getString("Tran_Amount"));
				writer.append(';');
				writer.append(rs.getString("Commission"));
				writer.append(';');
				writer.append(rs.getString("Service_Charges"));
				writer.append(';');
				writer.append(rs.getString("Bank_Charges"));
				writer.append(';');
				writer.append(rs.getString("Other_Charges"));
				writer.append(';');
				writer.append(rs.getString("Net_Tran_Amt"));
				writer.append(';');
				writer.append(rs.getString("Action_On_Bal_Amt"));
				writer.append(';');
				writer.append(rs.getString("Previous_Bal_Amt"));
				writer.append(';');
				writer.append(rs.getString("Updated_Bal_Amt"));
				writer.append(';');
				writer.append(rs.getString("Tran_Status"));
				writer.append(';');
				writer.append(rs.getString("Final_Bal_Amt"));
				writer.append(';');
				writer.append(rs.getString("Tran_IP"));
				writer.append(';');
				writer.append(rs.getString("Updated_Date"));
				writer.append(';');
				writer.append(rs.getString("UpdatedTime"));
				writer.append(';');
				writer.append(rs.getString("Updated_User"));
				writer.append(';');
				writer.append(rs.getString("Updated_IP"));
				writer.append(';');
				writer.append(rs.getString("Remark"));
				writer.append('\n');
			}
			writer.flush();
			writer.close();
		
		}catch(Exception e)
			{
			Status="Norecord";
			System.out.println("Exception in EgenReportDao,getApiAccountStatement");
			System.out.println(e.toString());
			}
		finally
		{
			try
			{
				con.close();
				session.flush();
				session.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in EgenReportDao,getApiAccountStatement");
				System.out.println(e.toString());
			}
			
		}
		return Status;
	}

	@SuppressWarnings("deprecation")
	public String getapiAccountAdjustment(String filePath, String fromDate,
			String toDate, String reportOfEgen, String reportFor) 
	{
		
		Session session=null;
		Connection con=null;
		
		String Status="Norecord";
		try
		{
			session = HibernateSession1.getSessionFactory().openSession(); 
 		    
			con=session.connection(); 
			 
			CallableStatement cstmt =null;
			 
			cstmt=con.prepareCall("{call Admin_Egen_reports(?,?,?,?)}");
			
			cstmt.setString(1,reportOfEgen);
			cstmt.setString(2,reportFor);
			cstmt.setString(3,fromDate);
			cstmt.setString(4,toDate);
			ResultSet  rs=cstmt.executeQuery();		
			Status="Success";
			FileWriter writer = new FileWriter(filePath);
			
			writer.append("Transaction Date");
			writer.append(';');
			writer.append("Transaction Time");
			writer.append(';');
			writer.append("User Id");
			writer.append(';');
			writer.append("Transaction No");
			writer.append(';');
			writer.append("Total Cash");
			writer.append(';');
			writer.append("Used Cash");
			writer.append(';');
			writer.append("Balance");
			writer.append(';');
			writer.append("Txn. Type");
			writer.append(';');
			writer.append("Adjusted Account");
			writer.append(';');
			writer.append("Txn Amount");
			writer.append(';');
			writer.append("Final Balance");
			writer.append(';');
			writer.append("Updated User");
			writer.append(';');
			writer.append("Remark");
			writer.append('\n');
			
			while(rs.next())
			{
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
				writer.append(';');
				writer.append(rs.getString(12));
				writer.append(';');
				writer.append(rs.getString(13));
				writer.append('\n');
			}
			writer.flush();
			writer.close();
		}catch(Exception e)
		{
			Status="Norecord";
			System.out.println("Exception in EgenReportDao,getapiAccountAdjustment");
			System.out.println(e.toString());
		}
		finally
		{
			try
			{
				con.close();
				session.flush();
				session.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in EgenReportDao,getapiAccountAdjustment");
				System.out.println(e.toString());
			}
			
		}
		return Status;
		
	}

	@SuppressWarnings("deprecation")
	public String getEgenBillPayReport(String filePath, String fromDate,String toDate, String reportOfEgen, String reportFor) 
	{
		Session session=null;
		
		String Status="Norecord";
		Connection con=null;
		try
		{
			session = HibernateSession1.getSessionFactory().openSession(); 
 		    
			 con=session.connection(); 
			 
			CallableStatement cstmt =null;
			 
			cstmt=con.prepareCall("{call Admin_Egen_reports(?,?,?,?)}");
			cstmt.setString(1,reportOfEgen);
			cstmt.setString(2,reportFor);
			cstmt.setString(3,fromDate);
			cstmt.setString(4,toDate);
			
			ResultSet  rs=cstmt.executeQuery();		
			Status="Success";
			FileWriter writer = new FileWriter(filePath);
			
			writer.append("ID");
			writer.append(';');
			writer.append("Date of Transaction");
			writer.append(';');
			writer.append("Transaction Time");
			writer.append(';');
			writer.append("Service");
			writer.append(';');
			writer.append("Corporate User ID");
			writer.append(';');
			writer.append("Corporate User TranId");
			writer.append(';');
			writer.append("Rep TranId");
			writer.append(';');
			writer.append("Operator Category");
			writer.append(';');
			writer.append("Circle");
			writer.append(';');
			writer.append("Account_No");
			writer.append(';');
			writer.append("Txn Amount");
			writer.append(';');
			writer.append("Txn Commission");
			writer.append(';');
			writer.append("Tran_Status");
			writer.append(';');
			writer.append("Amount Status");
			writer.append(';');
			writer.append("Bill Pay Status");
			writer.append(';');
			writer.append("Response Date");
			writer.append(';');
			writer.append("Updated IP");
			writer.append(';');
			writer.append("Through(Vendor)");
			writer.append(';');
			writer.append("Decline Reason");
			writer.append(';');
			writer.append("Other Remark");
			writer.append('\n');
			
			while(rs.next())
			{
				
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
				writer.append(';');
				writer.append(rs.getString(17));
				writer.append(';');
				writer.append(rs.getString(18));
				writer.append(';');
				writer.append(rs.getString(19));
				writer.append(';');
				writer.append(rs.getString(20));
				writer.append('\n');
			}
			writer.flush();
			writer.close();
		}catch(Exception e)
		{
			Status="Norecord";
			System.out.println("Exception in EgenReportDao,getEgenBillPayReport");
			System.out.println(e.toString());
		}
		finally
		{
			try
			{
				con.close();
				session.flush();
				session.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in EgenReportDao,getEgenBillPayReport");
				System.out.println(e.toString());
			}
			
		}
		return Status;
	}

	@SuppressWarnings("deprecation")
	public String getEgenLiveDeposit(String filePath, String fromDate,String toDate, String reportOfEgen, String reportFor) 
	{
		Session session=null;
		
		String Status="Norecord";
		Connection con=null;
		try
		{
			session = HibernateSession1.getSessionFactory().openSession(); 
			 con=session.connection(); 
			 
			CallableStatement cstmt =null;
			 
			cstmt=con.prepareCall("{call Admin_Egen_reports(?,?,?,?)}");
			cstmt.setString(1,reportOfEgen);
			cstmt.setString(2,reportFor);
			cstmt.setString(3,fromDate);
			cstmt.setString(4,toDate);
			
			ResultSet  rs=cstmt.executeQuery();		
			Status="Success";
			FileWriter writer = new FileWriter(filePath);
			
			writer.append("ID");
			writer.append(';');
			writer.append("Corporate_Agent_Id");
			writer.append(';');
			writer.append("Transaction_ID");
			writer.append(';');
			writer.append("Request_Date");
			writer.append(';');
			writer.append("Request_Time");
			writer.append(';');
			writer.append("Mode_of_payment");
			writer.append(';');
			writer.append("Amount_To_Credit");
			writer.append(';');
			writer.append("Bank_Charges");
			writer.append(';');
			writer.append("Accepted_Amount");
			writer.append(';');
			writer.append("Receiving_Bank_Name");
			writer.append(';');
			writer.append("Receiving_Branch_Name");
			writer.append(';');
			writer.append("Bank_Transaction_Id");
			writer.append(';');
			writer.append("Receipt_Number");
			writer.append(';');
			writer.append("Reference_Id");
			writer.append(';');
			writer.append("Deposit_Date");
			writer.append(';');
			writer.append("Cheque_DD_No");
			writer.append(';');
			writer.append("Cheque_Date");
			writer.append(';'); 
			writer.append("Cheque_bank_name");
			writer.append(';');
			writer.append("Approval_Date");
			writer.append(';');
			writer.append("Approved_By");
			writer.append(';');
			writer.append("Status");
			writer.append(';');
			writer.append("User Remark");
			writer.append(';');
			writer.append("Admin Remark");
			writer.append('\n');
			
			while(rs.next())
			{
				writer.append(rs.getString("ID_No"));
				writer.append(';');
				writer.append(rs.getString("id"));
				writer.append(';');
				writer.append(rs.getString("Transaction_Id"));
				writer.append(';');
				writer.append(rs.getString("Request_Date"));
				writer.append(';');
				writer.append(rs.getString("Requesttime"));
				writer.append(';');
				writer.append(rs.getString("Mode_of_payment"));
				writer.append(';');
				writer.append(rs.getString("Amount_To_Credit"));
				writer.append(';');
				writer.append(rs.getString("Bank_charges"));
				writer.append(';');
				writer.append(rs.getString("Accepted_Amount"));
				writer.append(';');
				writer.append(rs.getString("Recieving_Bank_Name"));
				writer.append(';');
				writer.append(rs.getString("Recieving_Branch_Name"));
				writer.append(';');
				writer.append(rs.getString("Bank_Tran_Id"));
				writer.append(';');
				writer.append(rs.getString("Reciept_No"));
				writer.append(';');
				writer.append(rs.getString("Refrence_Id"));
				writer.append(';');
				writer.append(rs.getString("Deposit_Date"));
				writer.append(';');
				writer.append(rs.getString("Cheque_DD_No"));
				writer.append(';');
				writer.append(rs.getString("Cheque_date"));
				writer.append(';');
				writer.append(rs.getString("Cheque_bank_name"));
				writer.append(';');
				writer.append(rs.getString("Approval_Date"));
				writer.append(';');
				writer.append(rs.getString("Approved_By"));
				writer.append(';');
				writer.append(rs.getString("Status"));
				writer.append(';');
				writer.append(rs.getString("remark"));
				writer.append(';');
				writer.append(rs.getString("remark_admin"));
				writer.append('\n');
			}
			writer.flush();
			writer.close();
		}catch(Exception e)
			{
			Status="Norecord";
			System.out.println("Exception in EgenReportDao,getEgenLiveDeposit");
			System.out.println(e.toString());

			}
		finally
		{
			try
			{	
				con.close();
				session.flush();
				session.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in EgenReportDao,getEgenLiveDeposit");
				System.out.println(e.toString());
			}
		}
		return Status;
	}

	@SuppressWarnings("deprecation")
	public String getEgenLiveRechargeReport(String filePath, String fromDate,String toDate, String reportOfEgen, String reportFor) 
	{
		Session session=null;
		
		String Status="Norecord";
		String vendorTranId="";
		String serviceProvider="";
		String clientID=null;
		String refId=null;
		Connection con=null;
		String APICorporateID="";
		try
		{
			session = HibernateSession1.getSessionFactory().openSession(); 
			 con=session.connection(); 
			 
			CallableStatement cstmt =null;
			 
			cstmt=con.prepareCall("{call Admin_Egen_reports(?,?,?,?)}");
			cstmt.setString(1,reportOfEgen);
			cstmt.setString(2,reportFor);
			cstmt.setString(3,fromDate);
			cstmt.setString(4,toDate);
			
			ResultSet  rs=cstmt.executeQuery();		
			Status="Success";
			FileWriter writer = new FileWriter(filePath);
			
			writer.append("Date"); 
			writer.append(';');
			writer.append("Time"); 
			writer.append(';');
			writer.append("User Code"); 
			writer.append(';');
			writer.append("Operator"); 
			writer.append(';');
			writer.append("Mobile No"); 
			writer.append(';');
			writer.append("Gross Amount"); 
			writer.append(';');
			writer.append("Commision"); 
			writer.append(';');
			writer.append("Corporate User Amount"); 
			writer.append(';');
			writer.append("TEP Txn Id");
			writer.append(';');
			writer.append("Vendor Txn Id"); 
			writer.append(';');
			writer.append("Corporate Txn Id");
			writer.append(';');
			writer.append("Status");
			writer.append(';');
			writer.append("Service");
			writer.append(';');
			writer.append("Service Provider");
			writer.append(';');
			writer.append("Response Result");
			writer.append(';');
			writer.append("Response Code");
			writer.append(';');
			writer.append("Balance Before Trans");
			writer.append(';');
			writer.append("Balance After Trans");
			writer.append(';');
			writer.append("Trans Status");
			writer.append(';');
			writer.append("API Corporate ID");
			writer.append('\n');
			
			while(rs.next())
			{
				serviceProvider=rs.getString(13);
				writer.append(rs.getString(1));
			    writer.append(';');
			    writer.append(rs.getString(2));
			    writer.append(';');
			    clientID=rs.getString(3);
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
			    
			    if(serviceProvider.equalsIgnoreCase("TATASKY")){
			    	refId=rs.getString(21);
			    	writer.append(refId);
			    }
			    else{
			    writer.append(rs.getString(9));
			    }
			    writer.append(';');
			 // add vendor tran id in sheet
			   
			    if( serviceProvider.equalsIgnoreCase("VIDEOCONDTH") || serviceProvider.equalsIgnoreCase("TATASKY")){
			    	vendorTranId=rs.getString(19);
			    	
			    }
			    else {
			    	vendorTranId=rs.getString(9);
			    }
			    writer.append(vendorTranId);
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
			    writer.append(';');
			    writer.append(rs.getString(17));
			    writer.append(';');
			    writer.append(rs.getString(18));
			    writer.append(';');
			    
			    if(clientID.equalsIgnoreCase("API100031")){
			    	APICorporateID=rs.getString(20);
			    	
			    	if(APICorporateID == null || APICorporateID.length()==10){
			    		APICorporateID="NA";
			    	}
			    }
			    else{
			    	APICorporateID="NA";
			    }
			    writer.append(APICorporateID);
			    writer.append('\n');	
			}
			writer.flush();
			writer.close();
			
		}catch(Exception e)
			{
			Status="Norecord";
			System.out.println("Exception in EgenReportDao,getEgenLiveRechargeReport");
			System.out.println(e.toString());
			}
		finally
		{
			try
			{	
				con.close();
				session.flush();
				session.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in EgenReportDao,getEgenLiveRechargeReport");
				System.out.println(e.toString());
			}
			
		}
		return Status;
	}

	public String getEgenLiveDOMFlight(String filePath, String fromDate,String toDate, String reportOfEgen, String reportFor) 
	{
		Session session=null;
		
		String Status="Norecord";
		String vendorTranId="";
		String serviceProvider="";
		String clientID=null;
		String refId=null;
		Connection con=null;
		String APICorporateID="";
		try
		{
			session = HibernateSession1.getSessionFactory().openSession(); 
			 con=session.connection(); 
			 
			CallableStatement cstmt =null;
			 
			cstmt=con.prepareCall("{call Admin_Egen_reports(?,?,?,?)}");
			cstmt.setString(1,reportOfEgen);
			cstmt.setString(2,reportFor);
			cstmt.setString(3,fromDate);
			cstmt.setString(4,toDate);
			
			ResultSet  rs=cstmt.executeQuery();		
			Status="Success";
			FileWriter writer = new FileWriter(filePath);
			
			writer.append("ID");
			writer.append(';');
			writer.append("Corporate Agent ID");
			writer.append(';');
			writer.append("Date of Transaction");
			writer.append(';');
			writer.append("Time of Transaction");
			writer.append(';');
			writer.append("Corporate Txn. ID");
			writer.append(';');
			writer.append("Partner Ref. ID");
			writer.append(';');
			writer.append("Booking Txn. ID");
			writer.append(';');
			writer.append("Service");
			writer.append(';');
			writer.append("Balance Before Txn");
			writer.append(';');
			writer.append("Txn Amount");
			writer.append(';');
			writer.append("Commission");
			writer.append(';');
			writer.append("Service Charge");
			writer.append(';');
			writer.append("Deducted Amount");
			writer.append(';');
			writer.append("Action on Balance");
			writer.append(';');
			writer.append("Balance After Txn");
			writer.append(';');
			writer.append("Transaction Status");
			writer.append(';');
			writer.append("Updated Date");
			writer.append(';');
			writer.append("Updated Time");
			writer.append(';');
			writer.append("Agent Final Balance");
			writer.append(';');
			writer.append("IP Address");
			writer.append(';');
			writer.append("Remark");
			writer.append('\n');
			System.out.println("we are in success");
			Status = "Success";
			while (rs.next()) {
				
				writer.append(rs.getString("ID_No"));
				writer.append(';');
				writer.append(rs.getString("id"));
				writer.append(';');
				writer.append(rs.getString("transactiondate"));
				writer.append(';');
				writer.append(rs.getString("Transactiontime"));
				writer.append(';');
				writer.append(rs.getString("corporate_transaction_id"));
				writer.append(';');
				writer.append(rs.getString("Partner_Ref_id"));
				writer.append(';');
				writer.append(rs.getString("Booking_Trn"));
				writer.append(';');
				writer.append(rs.getString("Service"));
				writer.append(';');
				writer.append(rs.getString("Previous_Bal_Amt"));
				writer.append(';');
				writer.append(rs.getString("Tran_Amount"));
				writer.append(';');
				writer.append(rs.getString("Commission"));
				writer.append(';');
				writer.append(rs.getString("Service_Charges"));
				writer.append(';');
				writer.append(rs.getString("Net_Tran_Amt"));
				writer.append(';');
				writer.append(rs.getString("Action_On_Bal_Amt"));
				writer.append(';');
				writer.append(rs.getString("Updated_Bal_Amt"));
				writer.append(';');
				writer.append(rs.getString("Status"));
				writer.append(';');
				writer.append(rs.getString("updated_date"));
				writer.append(';');
				writer.append(rs.getString("updated_time"));
				writer.append(';');
				writer.append(rs.getString("Final_Bal_Amt"));
				writer.append(';');
				writer.append(rs.getString("Book_IP"));
				writer.append(';');
				writer.append(rs.getString("Remark"));
				writer.append('\n');
			}
			writer.flush();
			writer.close();
			
		}catch(Exception e)
			{
			Status="Norecord";
			System.out.println("Exception in EgenReportDao,getEgenLiveRechargeReport");
			System.out.println(e.toString());
			}
		finally
		{
			try
			{	
				con.close();
				session.flush();
				session.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in EgenReportDao,getEgenLiveRechargeReport");
				System.out.println(e.toString());
			}
			
		}
		return Status;
	}
	
	public String getEgenLiveHotel(String filePath, String fromDate,String toDate, String reportOfEgen, String reportFor) 
	{
		Session session=null;
		
		String Status="Norecord";
		Connection con=null;
		try
		{
			session = HibernateSession1.getSessionFactory().openSession(); 
			 con=session.connection(); 
			 
			CallableStatement cstmt =null;
			 
			cstmt=con.prepareCall("{call Admin_Egen_reports(?,?,?,?)}");
			cstmt.setString(1,reportOfEgen);
			cstmt.setString(2,reportFor);
			cstmt.setString(3,fromDate);
			cstmt.setString(4,toDate);
			
			ResultSet  rs=cstmt.executeQuery();		
			Status="Success";
			FileWriter writer = new FileWriter(filePath);
			
			writer.append("ID");
			writer.append(';');
			writer.append("Corporate Agent ID");
			writer.append(';');
			writer.append("Date of Transaction");
			writer.append(';');
			writer.append("Time of Transaction");
			writer.append(';');
			writer.append("Corporate Txn. ID");
			writer.append(';');
			if(reportOfEgen.equalsIgnoreCase("B2BBus"))
			{
				writer.append("Tentative Booking");
				writer.append(';');
				writer.append("Ticket PNR");
				writer.append(';');
			}
			else
			{
				writer.append("Booking Ref. ID");
				writer.append(';');
				writer.append("Booking Txn. ID");
				writer.append(';');
			}
			if(!reportOfEgen.equalsIgnoreCase("B2BBus"))
			{
				writer.append("Service");
				writer.append(';');
			}
			writer.append("Balance Before Txn");
			writer.append(';');
			writer.append("Txn Amount");
			writer.append(';');
			writer.append("Commission");
			writer.append(';');
			if(!reportOfEgen.equalsIgnoreCase("B2BBus"))
			{
				writer.append("Service Charge");
				writer.append(';');
			}
			writer.append("Deducted Amount");
			writer.append(';');
			writer.append("Action on Balance");
			writer.append(';');
			writer.append("Balance After Txn");
			writer.append(';');
			writer.append("Transaction Status");
			writer.append(';');
			writer.append("Updated Date");
			writer.append(';');
			writer.append("Updated Time");
			writer.append(';');
			writer.append("Agent Final Balance");
			writer.append(';');
			if(!reportOfEgen.equalsIgnoreCase("B2BBus"))
			{
				writer.append("IP Address");
				writer.append(';');
			}
			writer.append("Remark");
			writer.append('\n');
			System.out.println("we are in success");
			Status = "Success";
			while (rs.next()) {
				
				writer.append(rs.getString("ID_No"));
				writer.append(';');
				writer.append(rs.getString("id"));
				writer.append(';');
				writer.append(rs.getString("transactiondate"));
				writer.append(';');
				writer.append(rs.getString("Transactiontime"));
				writer.append(';');
				writer.append(rs.getString("corporate_transaction_id"));
				writer.append(';');
				if(reportOfEgen.equalsIgnoreCase("B2BBus"))
				{
					writer.append(rs.getString("Tentative_Book"));
					writer.append(';');
					writer.append(rs.getString("Ticket_PNR"));
					writer.append(';');
				}
				else
				{
					writer.append(rs.getString("BookingRef"));
					writer.append(';');
					writer.append(rs.getString("BookingTrn"));
					writer.append(';');
				}
				if(!reportOfEgen.equalsIgnoreCase("B2BBus"))
				{
					writer.append(rs.getString("Service"));
					writer.append(';');
				}
				writer.append(rs.getString("Previous_Bal_Amt"));
				writer.append(';');
				writer.append(rs.getString("Tran_Amount"));
				writer.append(';');
				writer.append(rs.getString("Commission"));
				writer.append(';');
				if(!reportOfEgen.equalsIgnoreCase("B2BBus"))
				{
					writer.append(rs.getString("Service_Charges"));
					writer.append(';');
				}
				writer.append(rs.getString("Net_Tran_Amt"));
				writer.append(';');
				writer.append(rs.getString("Action_On_Bal_Amt"));
				writer.append(';');
				writer.append(rs.getString("Updated_Bal_Amt"));
				writer.append(';');
				writer.append(rs.getString("Status"));
				writer.append(';');
				writer.append(rs.getString("updated_date"));
				writer.append(';');
				writer.append(rs.getString("updated_time"));
				writer.append(';');
				writer.append(rs.getString("Final_Bal_Amt"));
				writer.append(';');
				if(!reportOfEgen.equalsIgnoreCase("B2BBus"))
				{
					writer.append(rs.getString("Book_IP"));
					writer.append(';');
				}
				writer.append(rs.getString("Remark"));
				writer.append('\n');
			}
			writer.flush();
			writer.close();
			
		}catch(Exception e)
			{
			Status="Norecord";
			System.out.println("Exception in EgenReportDao,getEgenLiveRechargeReport");
			System.out.println(e.toString());
			}
		finally
		{
			try
			{	
				con.close();
				session.flush();
				session.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in EgenReportDao,getEgenLiveRechargeReport");
				System.out.println(e.toString());
			}
			
		}
		return Status;
	}
	
	
	public String getApiJournalReport(String filePath, String fromDate,String toDate, String reportOfEgen, String reportFor) 
	{
		Session session=null;
		String Status="Norecord";
		Connection con=null;
		try
		{
			session = HibernateSession1.getSessionFactory().openSession(); 
			 con=session.connection(); 
			 
			CallableStatement cstmt =null;
			 
			cstmt=con.prepareCall("{call Admin_Egen_reports(?,?,?,?)}");
			cstmt.setString(1,reportOfEgen);
			cstmt.setString(2,reportFor);
			cstmt.setString(3,fromDate);
			cstmt.setString(4,toDate);
              
			ResultSet  rs=cstmt.executeQuery();		
			Status="Success";
			FileWriter writer = new FileWriter(filePath);
				   
			writer.append("ID_No");
			writer.append(';');
			writer.append("API_Agent_Id");
			writer.append(';');
			writer.append("Transaction_Id");
			writer.append(';');
			writer.append("Request_Date");
			writer.append(';');
			writer.append("Request_Time");
			writer.append(';');
			writer.append("Mode_of_payment");
			writer.append(';');
			writer.append("Amount_To_Credit");
			writer.append(';');
			writer.append("Bank_Charges");
			writer.append(';');
			writer.append("Accepted_Amount");
			writer.append(';');
			writer.append("Reciept_No");
			writer.append(';');
			writer.append("Recieving_Bank_Name");
			writer.append(';');
			writer.append("Recieving_Branch_Name");
			writer.append(';');
			writer.append("Recieving_Branch_City");
			writer.append(';');
			writer.append("Recieving_Bank_Acc_No");
			writer.append(';');
			writer.append("Bank_Tran_Id");
			writer.append(';');
			writer.append("Refrence_Id");
			writer.append(';');
			writer.append("Deposit_Bank_Name");
			writer.append(';');
			writer.append("Deposit_Location");
			writer.append(';');
			writer.append("Deposit_Date");
			writer.append(';');
			writer.append("Cheque_DD_No");
			writer.append(';');
			writer.append("Cheque_date");
			writer.append(';');
			writer.append("Cheque_bank_name");
			writer.append(';');
			writer.append("Approved_By");
			writer.append(';');
			writer.append("Approval_Date");
			writer.append(';');
			writer.append("Status");
			writer.append(';');
			writer.append("remark");
			writer.append(';');
			writer.append("remark_admin");
			writer.append('\n');
          	    
			while(rs.next())
			{
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
				writer.append(';');
				writer.append(rs.getString(17));
				writer.append(';');
				writer.append(rs.getString(18));
				writer.append(';');
				writer.append(rs.getString(19));
				writer.append(';');
				writer.append(rs.getString(20));
				writer.append(';');
				writer.append(rs.getString(21));
				writer.append(';');
				writer.append(rs.getString(22));
				writer.append(';');
				writer.append(rs.getString(23));
				writer.append(';');
				writer.append(rs.getString(24));
				writer.append(';');
				writer.append(rs.getString(25));
				writer.append(';');
				writer.append(rs.getString(26));
				writer.append(';');
				writer.append(rs.getString(27));
				writer.append('\n');
			}
			writer.flush();
			writer.close();
		}catch(Exception e)
		{
			Status="Norecord";
			System.out.println("Exception in EgenReportDao,getApiJournalReport");
			System.out.println(e.toString());
		}
		finally
		{
			try
			{ 
				con.close();
				session.flush();
				session.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in EgenReportDao,getApiJournalReport");
				System.out.println(e.toString());
			}
			
		}
		return Status;
	}

	public ArrayList getAPICutoffDetail(String userID) 
	{
		ArrayList list=new ArrayList();
		String sql="";
		Session session=HibernateSession1.getSessionFactory().openSession();
		try{
			if(userID.equalsIgnoreCase("all")){
				//local Server
				//sql="select a.Corporate_Agent_Id,b.agency_name,convert(varchar(10),b.Date_of_Registration,120)as dor,c.status,a.Total_Cash,a.Used_Cash,a.CutOff_Amount,a.Total_Bal_Amount,a.Available_Bal_Amount from OnlineRechAPI_db_2012jul5.dbo.Rech_API_Corporate_Agent_Amount a,OnlineRechAPI_db_2012jul5.dbo.Rech_API_Corporate_Agent_Details b,OnlineRechAPI_db_2012jul5.dbo.Rech_API_Corporate_Agent_Auth_details c where a.Corporate_Agent_Id =b.Corporate_Agent_Id and b.Corporate_Agent_Id=c.Corporate_Agent_Id order by a.Corporate_Agent_Id";
				//live Server
				sql="select a.Corporate_Agent_Id,b.agency_name,convert(varchar(10),b.Date_of_Registration,120)as dor,c.status,a.Total_Cash,a.Used_Cash,a.CutOff_Amount,a.Total_Bal_Amount,a.Available_Bal_Amount from OnlineRechAPI_Live_db.dbo.Rech_API_Corporate_Agent_Amount a,OnlineRechAPI_Live_db.dbo.Rech_API_Corporate_Agent_Details b,OnlineRechAPI_Live_db.dbo.Rech_API_Corporate_Agent_Auth_details c where a.Corporate_Agent_Id =b.Corporate_Agent_Id and b.Corporate_Agent_Id=c.Corporate_Agent_Id order by a.Corporate_Agent_Id";
			}
			else{
				//Local server
				//sql="select a.Corporate_Agent_Id,b.agency_name,convert(varchar(10),b.Date_of_Registration,120) as dor,c.status,a.Total_Cash,a.Used_Cash,a.CutOff_Amount,a.Total_Bal_Amount,a.Available_Bal_Amount from OnlineRechAPI_db_2012jul5.dbo.Rech_API_Corporate_Agent_Amount a,OnlineRechAPI_db_2012jul5.dbo.Rech_API_Corporate_Agent_Details b,OnlineRechAPI_db_2012jul5.dbo.Rech_API_Corporate_Agent_Auth_details c where a.Corporate_Agent_Id =b.Corporate_Agent_Id and b.Corporate_Agent_Id=c.Corporate_Agent_Id and a.Corporate_Agent_Id='"+userID+"'";
				//Live server
				sql="select a.Corporate_Agent_Id,b.agency_name,convert(varchar(10),b.Date_of_Registration,120) as dor,c.status,a.Total_Cash,a.Used_Cash,a.CutOff_Amount,a.Total_Bal_Amount,a.Available_Bal_Amount from OnlineRechAPI_Live_db.dbo.Rech_API_Corporate_Agent_Amount a,OnlineRechAPI_Live_db.dbo.Rech_API_Corporate_Agent_Details b,OnlineRechAPI_Live_db.dbo.Rech_API_Corporate_Agent_Auth_details c where a.Corporate_Agent_Id =b.Corporate_Agent_Id and b.Corporate_Agent_Id=c.Corporate_Agent_Id and a.Corporate_Agent_Id='"+userID+"'";
			}
			
			
			Query query=session.createSQLQuery(sql);
			List result =query.list();
			Iterator itr=result.iterator();
			HashMap info;
			
			while(itr.hasNext())
			{
				info=new HashMap();
				Object[] row=(Object[])itr.next();
				info.put("corporateAgentId", row[0].toString());
				info.put("agencyName", row[1].toString());
				info.put("dateofRegistration",row[2].toString());
				info.put("userStatus",row[3].toString() );
				info.put("totalCash", row[4].toString());
				info.put("usedCash",row[5].toString() );
				info.put("cutOffAmount",row[6].toString());
				info.put("totalBalAmount",row[7].toString());
				info.put("availableBalAmount",row[8].toString());
				
				list.add(info);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally
		{
			try
			{
				session.flush();
				session.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in EgenReportDao,getAPICutoffDetail");
				System.out.println(e.toString());
			}
			
		}
		return list;
		
	}

	public ArrayList getIOReport(String fromdate, String todate, String userID) 
	{
		ArrayList list=new ArrayList();
		String sql="";
		Session session=HibernateSession1.getSessionFactory().openSession();
		try{
			//sql="select Agent_Header_Id,Authorized_IP1,Authorized_IP2 from OnlineRechAPI_db_2012jul5.dbo.Rech_API_Corporate_Agent_Auth_details where Corporate_Agent_Id='"+userID+"'";
			sql="select Agent_Header_Id,Authorized_IP1,Authorized_IP2 from OnlineRechAPI_Live_db.dbo.Rech_API_Corporate_Agent_Auth_details where Corporate_Agent_Id='"+userID+"'";
			
			Query query=session.createSQLQuery(sql);
			List result =query.list();
			Iterator itr=result.iterator();
			String header="";
			String ip1="";
			String ip2="";
			
			while(itr.hasNext())
			{
				Object[] row=(Object[])itr.next();
				header=row[0].toString();
				ip1=row[1].toString();
				ip2=row[2].toString();
			}
			//sql="select top 10 ip_address,date_of_xml from OnlineRechAPI_db_2012jul5.dbo.Rech_API_XML_Recieved_Send where Recieved_Xml like '%"+header+"%' and convert(varchar(10),Date_of_xml,120) between '2012-01-01' and '2012-10-10' order by date_of_xml desc";
			sql="select top 10 ip_address,date_of_xml from OnlineRechAPI_Live_db.dbo.Rech_API_XML_Recieved_Send where Recieved_Xml like '%"+header+"%' and convert(varchar(10),Date_of_xml,120) between '"+fromdate+"' and '"+todate+"' order by date_of_xml desc";
			System.out.println(query);
			query=session.createSQLQuery(sql);
			result =query.list();
			itr=result.iterator();
			HashMap hm;
			
			while(itr.hasNext())
			{
				Object[] row=(Object[])itr.next();
				hm=new HashMap();
				hm.put("UserID", userID);
				hm.put("IP",row[0]);
				hm.put("dateOfXml",row[1]);
				hm.put("IP1",ip1);
				hm.put("IP2",ip2);
				list.add(hm);
			}
		}catch(Exception e){
			System.out.println("Exception in EgenReportDao,getIOReport");
			System.out.println(e.toString());
		}
		finally
		{
			try
			{
				session.flush();
				session.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in EgenReportDao,getIOReport");
				System.out.println(e.toString());
			}
		}
		return list;
	}

	public String getIODetailReport(String filePath, String fromDate,String toDate, String userID)
	{
		String sql="";
		String Status="Norecord";
		Session session=HibernateSession1.getSessionFactory().openSession();
		try{
			//sql="select Agent_Header_Id,Authorized_IP1,Authorized_IP2 from OnlineRechAPI_db_2012jul5.dbo.Rech_API_Corporate_Agent_Auth_details where Corporate_Agent_Id='"+userID+"'";
			sql="select Agent_Header_Id,Authorized_IP1,Authorized_IP2 from OnlineRechAPI_Live_db.dbo.Rech_API_Corporate_Agent_Auth_details where Corporate_Agent_Id='"+userID+"'";
			
			Query query=session.createSQLQuery(sql);
			List result =query.list();
			Iterator itr=result.iterator();
			String header="";
			String ip1="";
			String ip2="";
			
			while(itr.hasNext())
			{
				Object[] row=(Object[])itr.next();
				header=row[0].toString();
				ip1=row[1].toString();
				ip2=row[2].toString();
			}
			System.out.println("ip1 "+ip1);
			System.out.println("ip1 "+ip2);
			//sql="select top 10 ip_address,date_of_xml,Recieved_Xml,Send_Xml from OnlineRechAPI_db_2012jul5.dbo.Rech_API_XML_Recieved_Send where Recieved_Xml like '%"+header+"%' and convert(varchar(10),Date_of_xml,120) between '2012-01-01' and '2012-10-10' order by date_of_xml desc";
			sql="select top 10 ip_address,date_of_xml,Recieved_Xml,Send_Xml from OnlineRechAPI_Live_db.dbo.Rech_API_XML_Recieved_Send where Recieved_Xml like '%"+header+"%' and convert(varchar(10),Date_of_xml,120) between '"+fromDate+"' and '"+toDate+"' order by date_of_xml desc";
			System.out.println(sql);
			query=session.createSQLQuery(sql);
			result =query.list();
			itr=result.iterator();
			
			FileWriter writer = new FileWriter(filePath);
			
			writer.append("User ID"); 
			writer.append(';');
			writer.append("Authorized IP 1"); 
			writer.append(';');
			writer.append("Authorized IP 1"); 
			writer.append(';');
			writer.append("Used IP"); 
			writer.append(';');
			writer.append("Date Of XML");
			writer.append(';');
			writer.append("Input XML"); 
			writer.append(';');
			writer.append("Output XML"); 
			writer.append('\n');
			Status="Success";
			
			while(itr.hasNext())
			{
				System.out.println("we are creaeting xls");
				Object[] row=(Object[])itr.next();
				writer.append(userID);
				writer.append(';');
				writer.append(ip1);
				writer.append(';');
				writer.append(ip2);
				writer.append(';');
				writer.append(row[0].toString());
				writer.append(';');
				writer.append(row[1].toString());
				writer.append(';');
				writer.append(row[2].toString());
				writer.append(';');
				writer.append(row[3].toString());
				writer.append('\n');
			}
			writer.flush();
			writer.close();
		}catch(Exception e)
		{
			Status="Norecord";
			System.out.println("Exception in EgenReportDao,getIODetailReport");
			System.out.println(e.toString());
		}
		finally
		{
			try
			{
				session.flush();
				session.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in EgenReportDao,getIODetailReport");
				System.out.println(e.toString());
			}
		}
		return Status;
	}
	
	public ArrayList<HashMap<String,String>> getBillpayReportView( String fromDate,String toDate, String reportOfEgen, String reportFor) 
	{
		System.out.println("in dao");
		Session session = null;
		String Status = "Norecord";
		ArrayList<HashMap<String,String>> listData=new ArrayList<HashMap<String,String>>();
		Connection con =null;

		try {
			session = HibernateSession1.getSessionFactory().openSession();
			System.out.println("session is :"+session);
			
			con = session.connection();

			CallableStatement cstmt = null;

			cstmt = con.prepareCall("{call Admin_Egen_reports(?,?,?,?)}");
			System.out.println("toDate value is" +toDate);
			cstmt.setString(1, reportOfEgen);
			cstmt.setString(2, reportFor);
			cstmt.setString(3, fromDate);
			cstmt.setString(4, toDate);
			System.out.println("fromDate value is" +fromDate);

			ResultSet rs = cstmt.executeQuery();
			int count=0;
			while (rs.next()) 
			{ 
				count++;
				
				HashMap<String,String> map=new HashMap<String,String>();
				
				map.put("agentId", rs.getString("corporateID"));
				map.put("dor", rs.getString("Date_of_Transaction"));
				map.put("time",rs.getString("Time_of_Transaction"));
				map.put("mobno", rs.getString("Account_No"));
				map.put("Operator", rs.getString("Category_Name"));
				map.put("TepTranId", rs.getString("corpTid"));
				map.put("amt", rs.getString("Amount"));
				map.put("OperatorTranId", rs.getString("Commission"));
				map.put("comm", rs.getString("Tran_Status"));
				
				listData.add(map);
				//System.out.println("listData   :   "+listData);
				if(count==20)
				{
					return listData;
				}
			}
		} catch (Exception e) 
		{
			Status = "Norecord";
			System.out.println("Exception in EgenReportDao");
			e.printStackTrace();
			
		} finally {
			try {
				con.close();
				session.flush();
				session.close();
					
			} catch (Exception e) {
				System.out.println("Exception in EgenReportFinally");
				e.printStackTrace();
			}
		}
		return listData;
	}
	
	public ArrayList<HashMap<String,String>> getliverechargefirstView( String fromDate,String toDate, String reportOfEgen, String reportFor) 
	{
		System.out.println("in dao"+reportOfEgen);
		Session session = null;
		String Status = "Norecord";
		ArrayList<HashMap<String,String>> listData=new ArrayList<HashMap<String,String>>();
		Connection con =null;

		try {
			session = HibernateSession1.getSessionFactory().openSession();
			
			con = session.connection();

			CallableStatement cstmt = null;

			cstmt = con.prepareCall("{call Admin_Egen_reports(?,?,?,?)}");
			System.out.println("toDate value is" +toDate);
			cstmt.setString(1, reportOfEgen);
			cstmt.setString(2, reportFor);
			cstmt.setString(3, fromDate);
			cstmt.setString(4, toDate);
			System.out.println("fromDate value is" +fromDate);

			ResultSet rs = cstmt.executeQuery();
			int count=0;
			while (rs.next()) 
			{ 
				count++;
				HashMap <String,String>map=new HashMap<String,String>();
				if(reportOfEgen.equalsIgnoreCase("B2BDOMFlight"))
				{
					map.put("agentId", rs.getString("id"));
					map.put("dor", rs.getString("transactiondate"));
					map.put("time",rs.getString("Transactiontime"));
					map.put("Partner_Ref_id", rs.getString("Partner_Ref_id"));
					map.put("Booking_Trn", rs.getString("Booking_Trn"));
					map.put("transaction_id", rs.getString("corporate_transaction_id"));
					map.put("amt", rs.getString("Tran_Amount"));
					map.put("OperatorTranId", rs.getString("Commission"));
					map.put("status", rs.getString("Status"));
				}
				else if(reportOfEgen.equalsIgnoreCase("B2BHotel"))
				{
					map.put("agentId", rs.getString("id"));
					map.put("dor", rs.getString("transactiondate"));
					map.put("time",rs.getString("Transactiontime"));
					map.put("Partner_Ref_id", rs.getString("BookingRef"));
					map.put("Booking_Trn", rs.getString("BookingTrn"));
					map.put("transaction_id", rs.getString("corporate_transaction_id"));
					map.put("amt", rs.getString("Tran_Amount"));
					map.put("OperatorTranId", rs.getString("Commission"));
					map.put("status", rs.getString("Status"));
				}
				else if(reportOfEgen.equalsIgnoreCase("B2BBus"))
				{
					map.put("agentId", rs.getString(3));
					map.put("dor", rs.getString("transactiondate"));
					map.put("time",rs.getString("Transactiontime"));
					map.put("Partner_Ref_id", rs.getString("Tentative_Book"));
					map.put("Booking_Trn", rs.getString("Ticket_PNR"));
					map.put("transaction_id", rs.getString("corporate_transaction_id"));
					map.put("amt", rs.getString("Tran_Amount"));
					map.put("OperatorTranId", rs.getString("Commission"));
					map.put("status", rs.getString("Status"));
				}
				else
				{
					map.put("agentId", rs.getString("id"));
					map.put("dor", rs.getString("transactiondate"));
					map.put("time",rs.getString("Transactiontime"));
					map.put("mobno", rs.getString("MobileDth_No"));
					map.put("Operator", rs.getString("MobileDth_Operator"));
					map.put("TepTranId", rs.getString("Transaction_id"));
					map.put("amt", rs.getString("Tran_Amount"));
					map.put("OperatorTranId", rs.getString("Commission"));
					map.put("comm", rs.getString("Status"));
				}
				listData.add(map);
				if(count==20)
				{
					return listData;
				}
			
				//System.out.println("listData   :   "+listData);
			}
			
		} catch (Exception e) 
		{
			Status = "Norecord";
			System.out.println("Exception in EgenReportDao,getliverechargefirstView");
			e.printStackTrace();
			
		} finally {
			try {
				con.close();
				session.flush();
				session.close();
					
			} catch (Exception e) {
				System.out.println("Exception in EgenReportDao,getliverechargefirstView");
				e.printStackTrace();
			}
		}
		return listData;
	}
	
	
	
}
