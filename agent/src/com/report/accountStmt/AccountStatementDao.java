package com.report.accountStmt;

import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.db.DBConnection;

/**
 *  Created By 	: Arshad
 *  Created Date : 19/04/2013
 */
public class AccountStatementDao {

	Logger logger=Logger.getLogger(AccountStatementDao.class);

	public ArrayList<HashMap<String,String>> getAccountStmtOfAgentOnClick(String agentID) {
		ArrayList<HashMap<String,String>> data=new ArrayList<HashMap<String,String>>();
		String sql="";
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try
		{

			con=DBConnection.getConnection();
			//sql="select convert(varchar(10),Date_of_Transaction,120),convert(varchar(10),Time_of_Transaction,108),Service,str(ReqAmount,10,2) as req,str(DeductedAmt,10,2) as ded,Action_on_bal_amt,str(Agent_F_balAmt,10,2),Tran_status,transaction_id,Transaction_No from agent_transaction_details where Date_of_Transaction=convert(varchar(10),getdate(),120) and agent_id=? order by Time_of_Transaction asc";
			sql="select convert(varchar(10),atd.Date_of_Transaction,120),convert(varchar(10),atd.Time_of_Transaction,108),atd.Service,str(atd.ReqAmount,10,2) as req,str(atd.DeductedAmt,10,2) as ded,"+
					"atd.Action_on_bal_amt,str(atd.Agent_F_balAmt,10,2),atd.Tran_status,atd.transaction_id,atd.Transaction_No,lrt.mobile_number,atd.Service_charge1 as service_charge,atd.Commession as comm ,isnull(atd.Service_charge2,0) as Service_charge2, "
					+ "isnull(lrt.mobile_operator,'NA') as mobile_operator from agent_transaction_details (nolock) atd left join live_recharge (nolock) lrt on " +
					"atd.transaction_no=lrt.tran_id where atd.Date_of_Transaction=convert(varchar(10),getdate(),120) and atd.agent_id=? order by atd.Id_No desc";
			logger.info("SQL : "+sql);
			pstmt=con.prepareStatement(sql);
			pstmt.setLong(1,Long.parseLong(agentID) );
			rs=pstmt.executeQuery();
			HashMap<String,String> temp;
			while(rs.next()){
				temp=new HashMap<String,String>(); 

				temp.put("dot",rs.getString(1)); 
				temp.put("tot",rs.getString(2)); 
				temp.put("service",rs.getString(3));
				temp.put("reqAmount",rs.getString(4)); 
				temp.put("deductedAmount",rs.getString(5)); 
				temp.put("action",rs.getString(6));
				temp.put("agentFbal",rs.getString(7));
				temp.put("status",rs.getString(8));
				temp.put("AgenttranID",rs.getString(9)==null?"NA":rs.getString(9));
				temp.put("AgenttranNo",rs.getString(10)==null?"NA":rs.getString(10));
				temp.put("mobileNo",rs.getString(11)==null?"NA":rs.getString(11));
				temp.put("service_charge",rs.getString(12));
				temp.put("comm",rs.getString(13));
				temp.put("Tax",rs.getString(14));
				temp.put("operator",rs.getString(15));
				data.add(temp);			 

			}
			//logger.info("---------------------AccountStatementDao--------------"+data+"\n");

		}catch (Exception ex) {
			logger.info("Exception in AccountStatementDao class method (getAccountStmtOfAgentOnClick)");
			ex.printStackTrace();
		}
		finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				logger.info("Exception in AccountStatementDao class method (getAccountStmtOfAgent) while closing connection");
				e.printStackTrace();
			}
		}
		return data;
	}

	public ArrayList<HashMap<String,String>> getSenderAccountStmtOfAgentOnClick(String agentID,String SenderId) {
		ArrayList<HashMap<String,String>> data=new ArrayList<HashMap<String,String>>();
		String sql="";
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try
		{

			con=DBConnection.getConnection();
			sql="select top 50 convert(varchar(10),a.Date_of_Transaction,120),convert(varchar(8),a.Time_of_Transaction),a.ReqAmount,b.Tran_status,b.Transaction_ID,isnull(a.Reference_id,'NA') as UTR,isnull(a.Bene_Name,'NA'),isnull(a.Bene_Account,'NA'),isnull(a.Bene_Bank_Name,'NA'),isnull(a.Bene_Bank_IFSC,'NA'),isnull(a.TransactionType,'NA') from DMR_SENDER_TRANSACTION_DETAILS (nolock) a join"
					+ " AGENT_TRANSACTION_DETAILS (nolock) b on a.Transaction_no=b.Transaction_no  and a.Sender_Id= ? order by b.ID_no desc";
			logger.info("SQL : "+sql);
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,SenderId );
			rs=pstmt.executeQuery();
			HashMap<String,String> temp;
			while(rs.next()){
				temp=new HashMap<String,String>(); 

				temp.put("dot",rs.getString(1)); 
				temp.put("tot",rs.getString(2)); 
				temp.put("reqAmount",rs.getString(3)); 
				temp.put("status",rs.getString(4));
				temp.put("TranNo",rs.getString(10)==null?"NA":rs.getString(5));
				temp.put("BankReferenceId",rs.getString(10)==null?"NA":rs.getString(6));
				temp.put("BeneName",rs.getString(7));
				temp.put("BeneAcount",rs.getString(8));
				temp.put("BankName",rs.getString(9));
				temp.put("BankIfsc",rs.getString(10));
				temp.put("TransactionType",rs.getString(11));
				data.add(temp);			 

			}
			//logger.info("---------------------AccountStatementDao--------------"+data+"\n");

		}catch (Exception ex) {
			logger.info("AccountStatementDao.getSenderAccountStmtOfAgentOnClick()");
			ex.printStackTrace();
		}
		finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				logger.info("Exception in AccountStatementDao class method (getAccountStmtOfAgent) while closing connection");
				e.printStackTrace();
			}
		}
		return data;
	}

	public HashMap<String,String> getDMRAccountStmtDetails(String agentID,String tranNo,String transactionId) {
		HashMap<String,String> temp=null;
		String sql="";
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try
		{

			con=DBConnection.getConnection();
			sql="select convert(varchar(10),a.Date_of_Transaction),convert(varchar(8),a.Time_of_Transaction),a.ReqAmount,b.Tran_status,b.Transaction_Id,isnull(a.Reference_id,'NA') as UTR,isnull(a.Bene_Name,'NA'),isnull(a.Bene_Account,'NA'),isnull(a.Bene_Bank_Name,'NA'),isnull(a.Bene_Bank_IFSC,'NA') from DMR_SENDER_TRANSACTION_DETAILS (nolock) a join "
					+ " AGENT_TRANSACTION_DETAILS (nolock) b on a.Transaction_no=b.Transaction_no and a.Agent_id=? and a.Transaction_No= ?  order by a.ID_no desc";
			logger.info("SQL : "+sql);
			pstmt=con.prepareStatement(sql);
			pstmt.setLong(1,Long.parseLong(agentID) );
			pstmt.setString(2,transactionId );
			rs=pstmt.executeQuery();

			while(rs.next()){
				temp=new HashMap<String,String>(); 

				temp.put("dot",rs.getString(1)); 
				temp.put("tot",rs.getString(2)); 
				temp.put("reqAmount",rs.getString(3)); 
				temp.put("status",rs.getString(4));
				temp.put("TranNo",rs.getString(10)==null?"NA":rs.getString(5));
				temp.put("BandReferenceId",rs.getString(10)==null?"NA":rs.getString(6));
				temp.put("BeneName",rs.getString(7));
				temp.put("BeneAcount",rs.getString(8));
				temp.put("BankName",rs.getString(9));
				temp.put("BankIfsc",rs.getString(10));

			}
			//logger.info("---------------------AccountStatementDao--------------"+data+"\n");

		}catch (Exception ex) {
			logger.info("AccountStatementDao.getDMRAccountStmtDetails()");
			ex.printStackTrace();
		}
		finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				logger.info("Exception in AccountStatementDao class method (getAccountStmtOfAgent) while closing connection");
				e.printStackTrace();
			}
		}
		return temp;
	}

	final ArrayList<HashMap<String,String>> getAccountStmtOfAgent(String agentID, String fromdate,String todate) {
		ArrayList<HashMap<String,String>> data=new ArrayList<HashMap<String,String>>();
		String sql="";
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try
		{

			con=DBConnection.getConnection();
			sql="select convert(varchar(10),atd.Date_of_Transaction,120),convert(varchar(10),atd.Time_of_Transaction,108),atd.Service,str(atd.ReqAmount,10,2) as req,str(atd.DeductedAmt,10,2) as ded,"+
					"atd.Action_on_bal_amt,str(atd.Agent_F_balAmt,10,2),atd.Tran_status,atd.transaction_id,atd.Transaction_No,lrt.mobile_number,atd.Service_charge1 as service_charge,atd.Commession as comm,isnull(atd.Service_charge2,0) as Service_charge2, "
					+ "isnull(lrt.mobile_operator,'NA') as mobile_operator from agent_transaction_details (nolock) atd left join live_recharge (nolock) lrt" +
					" on atd.transaction_no=lrt.tran_id where atd.date_of_Transaction between ? and ? and atd.agent_id=? order by atd.ID_no asc";
			logger.info("SQL : "+sql);
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, fromdate);
			pstmt.setString(2, todate);
			pstmt.setLong(3, Long.parseLong(agentID));
			rs=pstmt.executeQuery();
			HashMap<String,String> temp;
			while(rs.next()){
				temp=new HashMap<String,String>(); 
				temp.put("dot",rs.getString(1)); 
				temp.put("tot",rs.getString(2)); 
				temp.put("service",rs.getString(3));
				temp.put("reqAmount",rs.getString(4)); 
				temp.put("deductedAmount",rs.getString(5)); 
				temp.put("action",rs.getString(6));
				temp.put("agentFbal",rs.getString(7));
				temp.put("status",rs.getString(8));
				temp.put("AgenttranID",rs.getString(9)==null?"NA":rs.getString(9));
				temp.put("AgenttranNo",rs.getString(10)==null?"NA":rs.getString(10));
				temp.put("mobileNo",rs.getString(11)==null?"NA":rs.getString(11));
				temp.put("service_charge",rs.getString(12));
				temp.put("comm",rs.getString(13));
				temp.put("Tax",rs.getString(14));
				temp.put("operator",rs.getString(15));
				data.add(temp);			 
			}

		}catch (Exception ex) {
			logger.info("Exception in AccountStatementDao class method (getAccountStmtOfAgent)");
			ex.printStackTrace();
		}
		finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				logger.info("Exception in AccountStatementDao class method (getAccountStmtOfAgent) while closing connection");
				e.printStackTrace();
			}
		}
		return data;
	}
	final HashMap<String,String> getAccountWIndowInfoForRecharge(String tranNo) {
		String sql="";
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		HashMap<String,String> temp=new HashMap<String,String>(); 
		try
		{

			con=DBConnection.getConnection();
			sql="select Agent_tran_id,USessionID,mobile_operator,mobile_number,str(amount,10,2) as amount,date_of_recharge,status,convert(varchar(10),date_time,108) "
					+ "from live_recharge (nolock) where Agent_tran_id=? OR tran_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, tranNo);
			pstmt.setString(2, tranNo);
			rs=pstmt.executeQuery();

			while(rs.next()){
				temp.put("AgentTranID",rs.getString(1)); 
				temp.put("UsessionID",rs.getString(2)); 
				temp.put("mobileOperator",rs.getString(3));
				temp.put("mobileNumber",rs.getString(4)); 
				temp.put("amount",rs.getString(5)); 
				temp.put("dateOfRec",rs.getString(6));
				temp.put("status",rs.getString(7));
				temp.put("timeOfRec",rs.getString(8));

			}

		}catch (Exception ex) {
			logger.info("Exception in AccountStatementDao class method (getAccountStmtOfAgent)");
			ex.printStackTrace();
		}
		finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				logger.info("Exception in AccountStatementDao class method (getAccountStmtOfAgent) while closing connection");
				e.printStackTrace();
			}
		}
		return temp;
	}
	final HashMap<String, String> getAccountWIndowInfoForSms(String tranNo) {
		String sql="";
		HashMap<String,String> temp=new HashMap<String,String>(); 
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try
		{

			con=DBConnection.getConnection();
			sql="select Agent_tran_id,USessionID,mobile_operator,mobile_number,amount,date_of_recharge,status,convert(varchar(10),date_time,108) from live_recharge where tran_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, tranNo);
			rs=pstmt.executeQuery();

			while(rs.next()){
				temp.put("AgentTranID",rs.getString(1)); 
				temp.put("UsessionID",rs.getString(2)); 
				temp.put("mobileOperator",rs.getString(3));
				temp.put("mobileNumber",rs.getString(4)); 
				temp.put("amount",rs.getString(5)); 
				temp.put("dateOfRec",rs.getString(6));
				temp.put("status",rs.getString(7));
				temp.put("timeOfRec",rs.getString(8));

			}

		}catch (Exception ex) {
			logger.info("Exception in AccountStatementDao class method (getAccountWIndowInfoForSms)");
			ex.printStackTrace();
		}
		finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				logger.info("Exception in AccountStatementDao class method (getAccountWIndowInfoForSms) while closing connection");
				e.printStackTrace();
			}
		}
		return temp;
	}
	final HashMap<String, String> getAccountWIndowInfoForAll(String tranNo) {
		String sql="";
		HashMap<String,String> temp=new HashMap<String,String>(); 
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try
		{

			con=DBConnection.getConnection();
			sql="select transaction_id,Transaction_No,date_of_transaction,convert(varchar(10),Time_of_Transaction,108),str(ReqAmount,10,2),Action_on_bal_amt,"
					+ "Tran_status from agent_transaction_details (nolock) where Transaction_Id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, tranNo);
			rs=pstmt.executeQuery();

			while(rs.next()){
				temp.put("AgentTranID",rs.getString(1)); 
				temp.put("TranNo",rs.getString(2)); 
				temp.put("dateOfRec",rs.getString(3));
				temp.put("timeOfRec",rs.getString(4)); 
				temp.put("amount",rs.getString(5)); 
				temp.put("actionOnBal",rs.getString(6));
				temp.put("status",rs.getString(7));

			}

		}catch (Exception ex) {
			logger.info("Exception in AccountStatementDao class method (getAccountWIndowInfoForRefund)");
			ex.printStackTrace();
		}
		finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				logger.info("Exception in AccountStatementDao class method (getAccountWIndowInfoForRefund) while closing connection");
				e.printStackTrace();
			}
		}
		return temp;
	}

	final String getAgentAccountStmtReport(String filePath,String date, String agentID) {

		String sql="";
		String status="";
		int count=0;
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try
		{

			con=DBConnection.getConnection();

			//logger.info("inside count ");
			sql="select convert(varchar(10),Date_of_Transaction,120),convert(varchar(10),Time_of_Transaction,108) as time,','+Transaction_Id as TranID,Service,ReqAmount,Commession,Service_charge1,DeductedAmt,Action_on_bal_amt,Agent_F_balAmt,Tran_status,Remark,isnull(Service_charge2,0) as Service_charge2 from agent_transaction_details where Date_of_Transaction=? and agent_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, date);
			pstmt.setLong(2, Long.parseLong(agentID));
			rs=pstmt.executeQuery();


			FileWriter writer = new FileWriter(filePath);
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
			writer.append("Tax");
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
			String service="";
			String action="";
			while (rs.next()) {
				count++;
				writer.append(rs.getString(1));
				writer.append('\t');
				writer.append(rs.getString(2));
				writer.append('\t');
				writer.append(rs.getString(3));
				writer.append('\t');
				service=rs.getString(4);

				if(service==null || service.equalsIgnoreCase("null")){
					service="  ";
				}
				else if(service.equalsIgnoreCase("DisttoAgent")){
					perticulers="Trade Balance - Taken";
				}
				else if(service.equalsIgnoreCase("accountadjustment")){
					perticulers="Account Adjustment";
				}
				else if(service.equalsIgnoreCase("liveMobRech")){
					perticulers="Recharge-MOB";
				}else if(service.equalsIgnoreCase("liveDTHRech")){
					perticulers="Recharge-DTH";
				}else if(service.equalsIgnoreCase("liveDCRech")){
					perticulers="Recharge-Data Card";
				}else if(service.equalsIgnoreCase("RliveDCRech")){
					perticulers="Refund-Data Card";
				}else if(service.equalsIgnoreCase("RliveDTHRech")){
					perticulers="Refund-DTH";
				}else if(service.equalsIgnoreCase("RliveMobRech")){
					perticulers="Refund-Mobile";
				}else if(service.equalsIgnoreCase("smsBal") || service.equalsIgnoreCase("smsChmpin") || service.equalsIgnoreCase("smsGetmpin") || service.equalsIgnoreCase("smsInvalidKeyword") || service.equalsIgnoreCase("SMSRechActivation") || service.equalsIgnoreCase("smsTxn") || service.equalsIgnoreCase("smsUpdate")){
					perticulers="SMS Charge";
				}
				else{
					perticulers=service;
				}

				writer.append(perticulers);
				writer.append('\t');
				writer.append(rs.getString(5));
				writer.append('\t');
				writer.append(rs.getString(6));
				writer.append('\t');
				writer.append(rs.getString(7));
				writer.append('\t');
				writer.append(rs.getString(13));
				writer.append('\t');
				writer.append(rs.getString(8));
				writer.append('\t');


				action=rs.getString(5);
				if (action.equalsIgnoreCase("credit")){ 
					type="Cr";
				} 
				else if (action.equalsIgnoreCase("debit")){ 
					type="Dr";
				} 
				else{
					type=action;   
				}
				writer.append(type);
				writer.append('\t');
				writer.append(rs.getString(10));
				writer.append('\t');
				writer.append(rs.getString(11));
				writer.append('\t');
				remark=rs.getString(12);
				if(remark==null){
					remark="";
				}
				writer.append(remark);
				writer.append('\n');

			}

			writer.flush();
			writer.close();
			
		
			if(count>0){
				status="RECORD";
			}else{
				status="NORECORD";
			}

		}catch (Exception ex) {
			status="error";
			logger.info("Exception in AccountStatementDao class method (getAgentAccountStmtReport)");
			ex.printStackTrace();
		}
		finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				logger.info("Exception in AccountStatementDao class method (getAgentAccountStmtReport) while closing connection");
				e.printStackTrace();
			}
		}
		return status;
	}

	final HashMap<String, String> getAccountWIndowInfoForOffline(String AgenttranNo, String tranNo) {
		String sql="";
		HashMap<String,String> temp=new HashMap<String,String>(); 
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try
		{

			con=DBConnection.getConnection();
			sql="select Transactions_Id,mobile_operator,mobile_no,str(amount,10,2),Date_of_Booking,convert(varchar(10),Request_DATE,108),status from recharge_mobile where Transactions_Id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, AgenttranNo);
			rs=pstmt.executeQuery();

			while(rs.next()){
				temp.put("AgentTranID",tranNo); 
				temp.put("UsessionID",rs.getString(1)); 
				temp.put("mobileOperator",rs.getString(2));
				temp.put("mobileNumber",rs.getString(3)); 
				temp.put("amount",rs.getString(4)); 
				temp.put("dateOfRec",rs.getString(5));
				temp.put("timeOfRec",rs.getString(6));
				temp.put("status",rs.getString(7));


			}

		}catch (Exception ex) {
			logger.info("Exception in AccountStatementDao class method (getAccountWIndowInfoForRefund)");
			ex.printStackTrace();
		}
		finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				logger.info("Exception in AccountStatementDao class method (getAccountWIndowInfoForRefund) while closing connection");
				e.printStackTrace();
			}
		}
		return temp;
	}
	final HashMap<String, String> getAccountWIndowInfoForOps(String AgenttranNo,String tranNo) {
		String sql="";
		HashMap<String,String> temp=new HashMap<String,String>();
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try
		{
			//INSERT INTO (Ticket_id,Client_Id,Client_Type,Txn_id,Opened_Date,Opened_Time,Closed_Date,Closed_Time,Status,Query_Message,Solution_Message)
			con=DBConnection.getConnection();
			sql="select service,service_operator_name,amount,date_of_recharge,convert(varchar(10),time_of_recharge,108) as time,status from ops_process where tran_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, AgenttranNo);
			rs=pstmt.executeQuery();

			while(rs.next()){
				temp.put("AgentTranID",tranNo); 
				temp.put("UsessionID",AgenttranNo); 
				temp.put("mobileOperator",rs.getString(1));
				temp.put("mobileNumber",rs.getString(2)); 
				temp.put("amount",rs.getString(3)); 
				temp.put("dateOfRec",rs.getString(4));
				temp.put("timeOfRec",rs.getString(5));
				temp.put("status",rs.getString(6));


			}

		}catch (Exception ex) {
			logger.info("Exception in AccountStatementDao class method (getAccountWIndowInfoForRefund)");
			ex.printStackTrace();
		}
		finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				logger.info("Exception in AccountStatementDao class method (getAccountWIndowInfoForRefund) while closing connection");
				e.printStackTrace();
			}
		}
		return temp;

	}

	final String getSenderAccountStmtReport(String filePath,String SenderId,String fromDate,String toDate) {

		String sql="";
		String status="";
		int count=0;
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try
		{

			con=DBConnection.getConnection();

			//logger.info("inside count ");
			sql="select convert(varchar(10),l.date_of_recharge),convert(varchar(8),l.RequestTime),l.tran_id,isnull(sstd.Reference_id,'NA') as UTR,"
					+ "l.mobile_number,l.service,l.amount,l.status,isnull(sstd.Bene_Name,'NA'),isnull(sstd.Bene_Account,'NA'),isnull(sstd.Bene_Bank_Name,'NA'),"
					+ "isnull(sstd.Bene_Bank_IFSC,'NA'),isnull(sstd.TransactionType,'NA') from live_recharge (nolock) l join DMR_SENDER_TRANSACTION_DETAILS (nolock) sstd on  "
					+ "l.user_id=sstd.Agent_id and l.tran_id=sstd.Transaction_No and l.date_of_recharge=sstd.Date_of_Transaction and l.mobile_number=sstd.Sender_Id "
					+ "where l.mobile_number= ? and l.Date_of_Recharge between ? and ? order by sstd.ID_no asc";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, SenderId);
			pstmt.setString(2, fromDate);
			pstmt.setString(3, toDate);
			rs=pstmt.executeQuery();


			FileWriter writer = new FileWriter(filePath);
			writer.append("Date");
			writer.append('\t');
			writer.append("Time");
			writer.append('\t');
			writer.append("Service Delivery Tid");
			writer.append('\t');
			writer.append("UTR No.");
			writer.append('\t');
			writer.append("Sender Id");
			writer.append('\t');
			writer.append("Service");
			writer.append('\t');
			writer.append("Transaction Amount");
			writer.append('\t');
			writer.append("Transaction Status");
			writer.append('\t');
			writer.append("Beneficiary Name");
			writer.append('\t');
			writer.append("Bene A/C");
			writer.append('\t');
			writer.append("Bene Bank Name");
			writer.append('\t');
			writer.append("Bank IFSC");
			writer.append('\t');
			writer.append("Transacction Type");
			writer.append('\n'); 
			while (rs.next()) {
				count++;
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
				writer.append('\n');

			}

			writer.flush();
			writer.close();
			
		
			if(count>0){
				status="RECORD";
			}else{
				status="NORECORD";
			}

		}catch (Exception ex) {
			status="error";
			logger.info("Exception in AccountStatementDao class method (getAgentAccountStmtReport)");
			ex.printStackTrace();
		}
		finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				logger.info("Exception in AccountStatementDao class method (getAgentAccountStmtReport) while closing connection");
				e.printStackTrace();
			}
		}
		return status;
	}

	final String getSenderAccountStmtReport(String filePath,String SenderId) {

		String sql="";
		String status="";
		int count=0;
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try
		{

			con=DBConnection.getConnection();

			//logger.info("inside count ");
			sql="select convert(varchar(10),l.date_of_recharge),convert(varchar(8),l.RequestTime),l.tran_id,isnull(sstd.Reference_id,'NA') as UTR,l.mobile_number,"
					+ "l.service,l.amount,l.status,isnull(sstd.Bene_Name,'NA'),isnull(sstd.Bene_Account,'NA'),isnull(sstd.Bene_Bank_Name,'NA'),"
					+ "isnull(sstd.Bene_Bank_IFSC,'NA'),isnull(sstd.TransactionType,'NA') from live_recharge (nolock) l left join DMR_SENDER_TRANSACTION_DETAILS (nolock) sstd on "
					+ " l.user_id=sstd.Agent_id and l.tran_id=sstd.Transaction_No and l.date_of_recharge=sstd.Date_of_Transaction and l.mobile_number=sstd.Sender_Id "
					+ "where l.mobile_number= ? and l.Date_of_Recharge = convert(varchar(10),getdate(),120) order by sstd.Id_no asc";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, SenderId);
			rs=pstmt.executeQuery();


			FileWriter writer = new FileWriter(filePath);
			writer.append("Date");
			writer.append('\t');
			writer.append("Time");
			writer.append('\t');
			writer.append("Service Delivery Tid");
			writer.append('\t');
			writer.append("UTR No.");
			writer.append('\t');
			writer.append("Sender Id");
			writer.append('\t');
			writer.append("Service");
			writer.append('\t');
			writer.append("Transaction Amount");
			writer.append('\t');
			writer.append("Transaction Status");
			writer.append('\t');
			writer.append("Beneficiary Name");
			writer.append('\t');
			writer.append("Bene A/C");
			writer.append('\t');
			writer.append("Bene Bank Name");
			writer.append('\t');
			writer.append("Bank IFSC");
			writer.append('\t');
			writer.append("Transacction Type");
			writer.append('\n'); 
			while (rs.next()) {
				count++;
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
				writer.append('\n');

			}

			writer.flush();
			writer.close();
			
		
			if(count>0){
				status="RECORD";
			}else{
				status="NORECORD";
			}

		}catch (Exception ex) {
			status="error";
			logger.info("Exception in AccountStatementDao class method (getAgentAccountStmtReport)");
			ex.printStackTrace();
		}
		finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				logger.info("Exception in AccountStatementDao class method (getAgentAccountStmtReport) while closing connection");
				e.printStackTrace();
			}
		}
		return status;
	}

	final String getAgentAccountStmtReport(String filePath, String agentID,String fromDate, String todate) {

		String sql="";
		String status="";
		int count=0;
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try
		{

			con=DBConnection.getConnection();

			//logger.info("inside count ");
			sql="select convert(varchar(10),atd.Date_of_Transaction,120),convert(varchar(10),atd.Time_of_Transaction,108) as time,atd.Transaction_Id+',' as TranID, " + 
			"atd.Service,atd.ReqAmount,atd.Commession,atd.Service_charge1,atd.DeductedAmt,atd.Action_on_bal_amt,atd.Agent_F_balAmt, " + 
			"atd.Tran_status,isnull(sstd.Bene_Account,'NA') as beneAcount,isnull(sstd.Bene_Bank_Name,'NA') as beneBankName, " + 
			"isnull(sstd.Bene_Name,'NA') as beneName,isnull(sstd.TransactionType,'NA') as tranType,isnull(atd.Remark,'') as remark, " + 
			"isnull(atd.Service_charge2,0) as Service_charge2 ,l.mobile_operator " + 
			"from agent_transaction_details (nolock) atd  " + 
			"left join live_recharge(nolock) l on l.tran_id=atd.Transaction_No " + 
			"left join DMR_SENDER_TRANSACTION_DETAILS (nolock) sstd on sstd.Transaction_No=atd.Transaction_No  " + 
			"and atd.Agent_id=sstd.Agent_id and atd.Date_of_Transaction=sstd.Date_of_Transaction  " + 
			"where atd.Date_of_Transaction between ? and ? and atd.agent_id=? order by atd.ID_no asc";
			
			/*sql="select convert(varchar(10),atd.Date_of_Transaction,120),convert(varchar(10),atd.Time_of_Transaction,108) as time,atd.Transaction_Id as TranID,"
					+ "atd.Service,atd.ReqAmount,atd.Commession,atd.Service_charge1,atd.DeductedAmt,atd.Action_on_bal_amt,atd.Agent_F_balAmt,atd.Tran_status,"
					+ "isnull(sstd.Bene_Account,'NA') as beneAcount,isnull(sstd.Bene_Bank_Name,'NA') as beneBankName,isnull(sstd.Bene_Name,'NA') as beneName,"
					+ "isnull(sstd.TransactionType,'NA') as tranType,isnull(atd.Remark,'') as remark,isnull(atd.Service_charge2,0) as Service_charge2 from agent_transaction_details (nolock) atd left join "
					+ "DMR_SENDER_TRANSACTION_DETAILS (nolock) sstd on sstd.Transaction_No=atd.Transaction_No and atd.Agent_id=sstd.Agent_id and "
					+ "atd.Date_of_Transaction=sstd.Date_of_Transaction where atd.Date_of_Transaction between ? and ? and atd.agent_id=? order by atd.ID_no asc";*/
			
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, fromDate);
			pstmt.setString(2, todate);
			pstmt.setLong(3, Long.parseLong(agentID));
			rs=pstmt.executeQuery();


			FileWriter writer = new FileWriter(filePath);
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
			writer.append("Tax");
			writer.append('\t');
			writer.append("Net Amount");
			writer.append('\t');
			writer.append("Type");
			writer.append('\t');
			writer.append("Current Balance");
			writer.append('\t');
			writer.append("Transacction Status");
			writer.append('\t');
			writer.append("Bank Acount");
			writer.append('\t');
			writer.append("Bank Name");
			writer.append('\t');
			writer.append("Beneficiary Name");
			writer.append('\t');
			writer.append("Transaction Type");
			writer.append('\t');
			writer.append("Remark");
			writer.append('\t');
			writer.append("Operator");
			writer.append('\n'); 
			String perticulers="";
			String type="";
			String remark="";
			String service="";
			String action="";
			while (rs.next()) {
				count++;
				writer.append(rs.getString(1));
				writer.append('\t');
				writer.append(rs.getString(2));
				writer.append('\t');
				writer.append(rs.getString(3));
				writer.append('\t');
				service=rs.getString(4);

				if(service==null || service.equalsIgnoreCase("null")){
					service="  ";
				}
				else if(service.equalsIgnoreCase("DisttoAgent")){
					perticulers="Trade Balance - Taken";
				}
				else if(service.equalsIgnoreCase("accountadjustment")){
					perticulers="Account Adjustment";
				}
				else if(service.equalsIgnoreCase("liveMobRech")){
					perticulers="Recharge-MOB";
				}else if(service.equalsIgnoreCase("liveDTHRech")){
					perticulers="Recharge-DTH";
				}else if(service.equalsIgnoreCase("liveDCRech")){
					perticulers="Recharge-Data Card";
				}else if(service.equalsIgnoreCase("RliveDCRech")){
					perticulers="Refund-Data Card";
				}else if(service.equalsIgnoreCase("RliveDTHRech")){
					perticulers="Refund-DTH";
				}else if(service.equalsIgnoreCase("RliveMobRech")){
					perticulers="Refund-Mobile";
				}else if(service.equalsIgnoreCase("smsBal") || service.equalsIgnoreCase("smsChmpin") || service.equalsIgnoreCase("smsGetmpin") || service.equalsIgnoreCase("smsInvalidKeyword") || service.equalsIgnoreCase("SMSRechActivation") || service.equalsIgnoreCase("smsTxn") || service.equalsIgnoreCase("smsUpdate")){
					perticulers="SMS Charge";
				}
				else{
					perticulers=service;
				}

				writer.append(perticulers);
				writer.append('\t');
				writer.append(rs.getString(5));
				writer.append('\t');
				writer.append(rs.getString(6));
				writer.append('\t');
				writer.append(rs.getString(7));
				writer.append('\t');
				writer.append(rs.getString(17));
				writer.append('\t');
				writer.append(rs.getString(8));
				writer.append('\t');


				action=rs.getString(9);
				if (action.equalsIgnoreCase("credit")){ 
					type="Credit";
				} 
				else if (action.equalsIgnoreCase("debit")){ 
					type="Debit";
				} 
				else{
					type=action;   
				}
				writer.append(type);
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
				writer.append(rs.getString("mobile_operator"));
				writer.append('\n');

			}

			writer.flush();
			writer.close();
			
		
			if(count>0){
				status="RECORD";
			}else{
				status="NORECORD";
			}

		}catch (Exception ex) {
			status="error";
			logger.info("Exception in AccountStatementDao class method (getAgentAccountStmtReport)");
			ex.printStackTrace();
		}
		finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				logger.info("Exception in AccountStatementDao class method (getAgentAccountStmtReport) while closing connection");
				e.printStackTrace();
			}
		}
		return status;
	}
	
	final String getAgentAccountStmtReport(String filePath, String agentID) {
		
		String sql="";
		String status="";
		int count=0;
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try
		{
			
			con=DBConnection.getConnection();
			//logger.info("inside count ");
			sql="select convert(varchar(10),atd.Date_of_Transaction,120),convert(varchar(10),atd.Time_of_Transaction,108) as time,atd.Transaction_Id+',' as TranID, " + 
					"atd.Service,atd.ReqAmount,atd.Commession,atd.Service_charge1,atd.DeductedAmt,atd.Action_on_bal_amt,atd.Agent_F_balAmt, " + 
					"atd.Tran_status,isnull(sstd.Bene_Account,'NA') as beneAcount,isnull(sstd.Bene_Bank_Name,'NA') as beneBankName, " + 
					"isnull(sstd.Bene_Name,'NA') as beneName,isnull(sstd.TransactionType,'NA') as tranType,isnull(atd.Remark,'') as remark, " + 
					"isnull(atd.Service_charge2,0) as Service_charge2 ,l.mobile_operator " + 
					"from agent_transaction_details (nolock) atd  " + 
					"left join live_recharge(nolock) l on l.tran_id=atd.Transaction_No " + 
					"left join DMR_SENDER_TRANSACTION_DETAILS (nolock) sstd on sstd.Transaction_No=atd.Transaction_No  " + 
					"and atd.Agent_id=sstd.Agent_id and atd.Date_of_Transaction=sstd.Date_of_Transaction  " + 
					"where atd.Date_of_Transaction=convert(varchar(10),getdate(),120) and atd.agent_id=? order by atd.Id_no asc";
			pstmt=con.prepareStatement(sql);
			pstmt.setLong(1, Long.parseLong(agentID));
			rs=pstmt.executeQuery();
		
		
			FileWriter writer = new FileWriter(filePath);
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
			writer.append("Bank Acount");
			writer.append('\t');
			writer.append("Bank Name");
			writer.append('\t');
			writer.append("Beneficiary Name");
			writer.append('\t');
			writer.append("Transaction Type");
			writer.append('\t');
			writer.append("Remark");
			writer.append('\t');
			writer.append("Operator");
			writer.append('\n'); 
			String perticulers="";
			String type="";
			String service="";
			String action="";
			while (rs.next()) {
				count++;
		
				writer.append(rs.getString(1));
				writer.append('\t');
				writer.append(rs.getString(2));
				writer.append('\t');
				writer.append(rs.getString(3));
				writer.append('\t');
				service=rs.getString(4);
		
				if(service==null || service.equalsIgnoreCase("null")){
					service="  ";
				}
				else if(service.equalsIgnoreCase("DisttoAgent")){
					perticulers="Trade Balance - Taken";
				}
				else if(service.equalsIgnoreCase("accountadjustment")){
					perticulers="Account Adjustment";
				}
				else if(service.equalsIgnoreCase("liveMobRech")){
					perticulers="Recharge-MOB";
				}else if(service.equalsIgnoreCase("liveDTHRech")){
					perticulers="Recharge-DTH";
				}else if(service.equalsIgnoreCase("liveDCRech")){
					perticulers="Recharge-Data Card";
				}else if(service.equalsIgnoreCase("RliveDCRech")){
					perticulers="Refund-Data Card";
				}else if(service.equalsIgnoreCase("RliveDTHRech")){
					perticulers="Refund-DTH";
				}else if(service.equalsIgnoreCase("RliveMobRech")){
					perticulers="Refund-Mobile";
				}else if(service.equalsIgnoreCase("smsBal") || service.equalsIgnoreCase("smsChmpin") || service.equalsIgnoreCase("smsGetmpin") || service.equalsIgnoreCase("smsInvalidKeyword") || service.equalsIgnoreCase("SMSRechActivation") || service.equalsIgnoreCase("smsTxn") || service.equalsIgnoreCase("smsUpdate")){
					perticulers="SMS Charge";
				}
				else{
					perticulers=service;
				}
		
				writer.append(perticulers);
				writer.append('\t');
				writer.append(rs.getString(5));
				writer.append('\t');
				writer.append(rs.getString(6));
				writer.append('\t');
				writer.append(rs.getString(7));
				writer.append('\t');
				writer.append(rs.getString(17));
				writer.append('\t');
				writer.append(rs.getString(8));
				writer.append('\t');
		
		
				action=rs.getString(9);
				if (action.equalsIgnoreCase("credit")){ 
					type="Credit";
				} 
				else if (action.equalsIgnoreCase("debit")){ 
					type="Debit";
				} 
				else{
					type=action;   
				}
				writer.append(type);
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
				writer.append(rs.getString("mobile_operator"));
				writer.append('\n');

			}

			writer.flush();
			writer.close();
			
		
			
			logger.info("Count ---------------- "+count);
			if(count>0){
				status="RECORD";
			}else{
				status="NORECORD";
			}
			
		}catch (Exception ex) {
			status="error";
			logger.info("Exception in AccountStatementDao class method (getAgentAccountStmtReport)");
			ex.printStackTrace();
		}
		finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				logger.info("Exception in AccountStatementDao class method (getAgentAccountStmtReport) while closing connection");
				e.printStackTrace();
			}
		}
		return status;
	}


}
