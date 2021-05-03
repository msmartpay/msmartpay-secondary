package com.report;

import java.io.FileWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.db.DBConnection;


public class RarAgentReportDao {

	public final String downloadLRTReport(String mdId,String filePath,String from,String to) {

		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		String status="NoRecord";
		try {
			con = DBConnection.getConnection();


			String sql="select a.aid as AgentID,d.DSID as DSID," +
					"m.MDID as MDID,l.Agent_tran_id ,l.mobile_number,l.mobile_operator," +
					"l.date_of_recharge,convert(varchar(10),l.date_time,108) as time,l.amount,l.service,l.mob_commission," +
					"l.status from live_recharge  (nolock) l,agent_details  (nolock) a ,distributor_details  (nolock) d,md_details  (nolock) m " +
					"where a.agent_id=l.user_id and a.distributor_id=d.distributor_id and d.md_id=m.md_id and " +
					"l.user_id in (select agent_id from agent_details where distributor_id in " +
					"(select distributor_id from distributor_details where md_id='"+mdId+"')) and " +
					"date_of_recharge between '"+from+"' and '"+to+"' order by date_of_recharge desc";
			System.out.println(sql);
			stmt = con.createStatement();
			rs=stmt.executeQuery(sql);
			status = "Success";
			FileWriter writer = new FileWriter(filePath);

			writer.append("Lead Associate Id");
			writer.append('\t');
			writer.append("Area Facility Center Id");
			writer.append('\t');
			writer.append("Master Facility Center ID");
			writer.append('\t');
			writer.append("User Type");
			writer.append('\t');
			writer.append("LA Txn ID");
			writer.append('\t');
			writer.append("Connection Number");
			writer.append('\t');
			writer.append("Connection Operator");
			writer.append('\t');
			writer.append("Date of Recharge");
			writer.append('\t');
			writer.append("Time of Recharge");
			writer.append('\t');
			writer.append("Transaction Amount");
			writer.append('\t');
			writer.append("Service");
			writer.append('\t');
			writer.append("Commission");
			writer.append('\t');
			writer.append("Status");
			writer.append('\n');

			while (rs.next()) { 

				writer.append(rs.getString(1));
				writer.append('\t');
				writer.append(rs.getString(2));
				writer.append('\t');
				writer.append(rs.getString(3));
				writer.append('\t');
				writer.append("Lead Associate");
				writer.append('\t');
				writer.append(","+rs.getString(4));
				writer.append('\t');
				writer.append(","+rs.getString(5));
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

		}
		catch (Exception e) {
			status = "ERROR";
			System.out.println("Exception in  getSuspectDataReport ");
			e.printStackTrace();
		} finally {
			try {
				if(con!=null)
					con.close();
				if(stmt!=null)
					stmt.close();
				if(rs!=null)
					rs.close();

			} catch (Exception e) {
				System.out.println("Exception in  getSuspectDataReport while closing connection");
				e.printStackTrace();
			}

		}
		return status;
	}

	public final String downloadATTReport(String mdId,String filePath,String from,String to) {
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		String status="NoRecord";
		try {
			con = DBConnection.getConnection();


				String sql="select b.aid as AGID,a.Transaction_Id,a.UserType,a.Date_of_Transaction,a.Time_of_Transaction,a.Service,a.Agent_balAmt_b_Ded, a.ReqAmount,a.Commession,a.Service_charge1,a.DeductedAmt,a.Action_on_bal_amt,a.Agent_balAmt_A_Ded,a.Tran_status,a.Updated_date,a.updated_time,a.Remark from dbo.agent_transaction_details  (nolock) a,agent_details  (nolock) b where a.agent_id in (select agent_id from agent_details  (nolock) where distributor_id in(select distributor_id from distributor_details  (nolock) where md_id ='"+mdId+"')) and a.agent_id=b.agent_id and a.Date_of_Transaction between '"+from+"' and '"+to+"' order by a.Date_of_Transaction desc";
				System.out.println(sql);
				stmt = con.createStatement();
				rs=stmt.executeQuery(sql);
				status = "Success";
				FileWriter writer = new FileWriter(filePath);


				writer.append("LA Id");
				writer.append('\t');
				writer.append("Txn Id");
				writer.append('\t');
				writer.append("User Type");
				writer.append('\t');
				writer.append("Date of Recharge");
				writer.append('\t');
				writer.append("Time of Recharge");
				writer.append('\t');
				writer.append("Service");
				writer.append('\t');
				writer.append("Agent Bal. before Deduction");
				writer.append('\t');
				writer.append("Requested Amount");
				writer.append('\t');
				writer.append("Commission");
				writer.append('\t');
				writer.append("Service Charge1");
				writer.append('\t');
				writer.append("Deducted Amount");
				writer.append('\t');
				writer.append("Action_on_bal_amt");
				writer.append('\t');
				writer.append("Agent Bal. After Deduction");
				writer.append('\t');
				writer.append("Tran_status");
				writer.append('\t');
				writer.append("Updated_date");
				writer.append('\t');
				writer.append("updated_time");
				writer.append('\t');
				writer.append("Remark");
				writer.append('\n');

				while (rs.next()) {  


					writer.append(rs.getString(1));
					writer.append('\t');
					writer.append(","+rs.getString(2));
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
					writer.append('\n');
				}
				writer.flush();
				writer.close();

		}
		catch (Exception e) {
			status = "ERROR";
			System.out.println("Exception in  getSuspectDataReport ");
			e.printStackTrace();
		} finally {
			try {
				if(con!=null)
					con.close();
				if(stmt!=null)
					stmt.close();
				if(rs!=null)
					rs.close();

			} catch (Exception e) {
				System.out.println("Exception in  getSuspectDataReport while closing connection");
				e.printStackTrace();
			}

		}
		return status;
	}
	public final String downloadDMRReport(String mdId,String filePath,String from,String to,String senderId) {

		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		String status="NoRecord";
		try {
			con = DBConnection.getConnection();
				String sql="";
				if(senderId!=null && senderId.length()==10 )
					sql="select b.aid as AGID,a.Transaction_Id,a.Date_of_Transaction,a.Time_of_Transaction,a.Service,a.Agent_balAmt_b_Ded, a.ReqAmount,a.Commession,a.Service_charge1,a.DeductedAmt,a.Action_on_bal_amt,a.Agent_balAmt_A_Ded,d.Tran_status,d.Sender_Id,isnull(d.Bene_Name,'NA') as Bene_Name,isnull(d.Bene_Account,'NA') as Bene_Account,isnull(d.Bene_Bank_Name,'NA') as Bene_Bank_Name,isnull(d.Bene_Bank_IFSC,'NA') as Bene_Bank_IFSC,a.Updated_date,a.updated_time,a.Remark " + 
						"from DMR_SENDER_TRANSACTION_DETAILS (nolock) d join agent_transaction_details (nolock) a on d.Agent_id=a.Agent_id and d.Transaction_No=a.Transaction_No join agent_details (nolock) b on a.Agent_id=b.agent_id join MD_Details (nolock) c on b.MDS_ID=c.MDID and c.MD_id="+mdId+" and a.Date_of_Transaction between '"+from+"' and '"+to+"' and d.Sender_Id='"+senderId+"' order by a.Id_No desc";
				else
					sql="select b.aid as AGID,a.Transaction_Id,a.Date_of_Transaction,a.Time_of_Transaction,a.Service,a.Agent_balAmt_b_Ded, a.ReqAmount,a.Commession,a.Service_charge1,a.DeductedAmt,a.Action_on_bal_amt,a.Agent_balAmt_A_Ded,d.Tran_status,d.Sender_Id,isnull(d.Bene_Name,'NA') as Bene_Name,isnull(d.Bene_Account,'NA') as Bene_Account,isnull(d.Bene_Bank_Name,'NA') as Bene_Bank_Name,isnull(d.Bene_Bank_IFSC,'NA') as Bene_Bank_IFSC,a.Updated_date,a.updated_time,a.Remark " + 
							"from DMR_SENDER_TRANSACTION_DETAILS (nolock) d join agent_transaction_details (nolock) a on d.Agent_id=a.Agent_id and d.Transaction_No=a.Transaction_No join agent_details (nolock) b on a.Agent_id=b.agent_id join MD_Details (nolock) c on b.MDS_ID=c.MDID and c.MD_id="+mdId+" and a.Date_of_Transaction between '"+from+"' and '"+to+"' order by a.Id_No desc";
				
				System.out.println(sql);
				stmt = con.createStatement();
				rs=stmt.executeQuery(sql);
				status = "Success";
				FileWriter writer = new FileWriter(filePath);


				writer.append("Agent Id");
				writer.append('\t');
				writer.append("Txn Id");
				writer.append('\t');
				writer.append("Date of Recharge");
				writer.append('\t');
				writer.append("Time of Recharge");
				writer.append('\t');
				writer.append("Service");
				writer.append('\t');
				writer.append("Agent Bal. before Deduction");
				writer.append('\t');
				writer.append("Requested Amount");
				writer.append('\t');
				writer.append("Commission");
				writer.append('\t');
				writer.append("Service Charge1");
				writer.append('\t');
				writer.append("Deducted Amount");
				writer.append('\t');
				writer.append("Action_on_bal_amt");
				writer.append('\t');
				writer.append("Agent Bal. After Deduction");
				writer.append('\t');
				writer.append("Tran_status");
				writer.append('\t');
				writer.append("Sender Mobile");
				writer.append('\t');
				writer.append("Bene Name");
				writer.append('\t');
				writer.append("Bane Account");
				writer.append('\t');
				writer.append("Bane Bank Name");
				writer.append('\t');
				writer.append("IFSC");
				writer.append('\t');
				writer.append("Updated_date");
				writer.append('\t');
				writer.append("updated_time");
				writer.append('\t');
				writer.append("Remark");
				writer.append('\n');

				while (rs.next()) {  


					writer.append(rs.getString(1));
					writer.append('\t');
					writer.append(","+rs.getString(2));
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
					writer.append('\n');
				}
				writer.flush();
				writer.close();

		}
		catch (Exception e) {
			status = "ERROR";
			System.out.println("Exception in  getSuspectDataReport ");
			e.printStackTrace();
		} finally {
			try {
				if(con!=null)
					con.close();
				if(stmt!=null)
					stmt.close();
				if(rs!=null)
					rs.close();

			} catch (Exception e) {
				System.out.println("Exception in  getSuspectDataReport while closing connection");
				e.printStackTrace();
			}

		}
		return status;
	}
	public final String downloadDSReport(String mdId,String filePath,String CompleteMDID,String dateStringfrom,String dateStringto) {
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		String status="NoRecord";
		try {
			con = DBConnection.getConnection();

				String sql="select a.Transaction_No ,b.DSID as DSID,a.User_Type,a.Date_of_Transaction,a.Time_of_Transaction,a.Service,a.Tran_amount,a.Commission,a.Service_charge,a.Bank_charge,a.Other_charge,a.Net_Tran_amount,a.Action_on_Bal_amount,a.Tran_status,a.Final_Bal_amount,a.Updated_Date,a.Updated_time,a.Remarks from dbo.distributor_Transaction_details a,distributor_details b where a.distributor_id in(select distributor_id from distributor_details where md_id in(select md_id from md_details where md_id='"+mdId+"'))and a.distributor_id=b.distributor_id and a.Date_of_Transaction between '"+dateStringfrom+"'  and '"+dateStringto+"' order by a.Date_of_Transaction desc";
				System.out.println(sql);
				stmt = con.createStatement();
				rs=stmt.executeQuery(sql);
				status = "Success";
				FileWriter writer = new FileWriter(filePath);

				writer.append("Transaction_No");
				writer.append('\t');
				writer.append("Area Facility Center Id");
				writer.append('\t');
				writer.append("Master Facility Center ID");
				writer.append('\t');
				writer.append("User Type");
				writer.append('\t');
				writer.append("Date Of Transaction");
				writer.append('\t');
				writer.append("Time Of Transaction");
				writer.append('\t');
				writer.append("Service");
				writer.append('\t');
				writer.append("Tran_amount");
				writer.append('\t');
				writer.append("Commission");
				writer.append('\t');
				writer.append("Service_charge");
				writer.append('\t');
				writer.append("Bank_charge");
				writer.append('\t');
				writer.append("Other_charge");
				writer.append('\t');
				writer.append("Net_Tran_amount");
				writer.append('\t');
				writer.append("Action_on_Bal_amount");
				writer.append('\t');
				writer.append("Tran_status");
				writer.append('\t');
				writer.append("Final_Bal_amount");
				writer.append('\t');
				writer.append("Updated_Date");
				writer.append('\t');
				writer.append("Updated_time");
				writer.append('\t');
				writer.append("Remarks");
				writer.append('\n');

				while (rs.next()) { 

					writer.append(","+rs.getString(1));
					writer.append('\t');
					writer.append(rs.getString(2));
					writer.append('\t');
					writer.append(CompleteMDID);
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
					writer.append(rs.getDate(16).toString());
					writer.append('\t');
					writer.append(rs.getString(17));
					writer.append('\t');
					writer.append(rs.getString(18));
					writer.append('\n');
				}
				writer.flush();
				writer.close();

		}
		catch (Exception e) {
			status = "ERROR";
			System.out.println("Exception in  getSuspectDataReport ");
			e.printStackTrace();
		} finally {
			try {
				if(con!=null)
					con.close();
				if(stmt!=null)
					stmt.close();
				if(rs!=null)
					rs.close();

			} catch (Exception e) {
				System.out.println("Exception in  getSuspectDataReport while closing connection");
				e.printStackTrace();
			}

		}
		return status;
	}
}
