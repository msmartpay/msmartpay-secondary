package com.ticket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.db.DBConnection;

public class TicketDao {

	Logger logger=Logger.getLogger(TicketDao.class);
	
	public boolean submitTicket(String ticketId,String tranNo,long agentId,String clientType,String remark) {
		String sql="";
		HashMap<String,String> temp=new HashMap<String,String>();
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try
		{
			con=DBConnection.getConnection();
			sql="INSERT INTO Client_Ticket_Details (Ticket_id,Client_Id,Client_Type,Txn_id,Opened_Date,Opened_Time,Status,Query_Message) values (?,?,?,?,convert(varchar(10),getdate(),120),getdate(),'Opened',?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, ticketId);
			pstmt.setLong(2, agentId);
			pstmt.setString(3, clientType);
			pstmt.setString(4, tranNo);
			pstmt.setString(5, remark);
			int count=pstmt.executeUpdate();
			if(count>0)
				return true;
			else 
				return false;

		}catch (Exception ex) {
			ex.printStackTrace();
			return false;
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
				e.printStackTrace();
				return false;
			}
		}
		
	}
	
	final ArrayList<HashMap<String,String>> getTicketDetails(long agentID) {
		ArrayList<HashMap<String,String>> data=new ArrayList<HashMap<String,String>>();
		String sql="";
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try
		{
			
			con=DBConnection.getConnection();
			//sql="select convert(varchar(10),Date_of_Transaction,120),convert(varchar(10),Time_of_Transaction,108),Service,str(ReqAmount,10,2) as req,str(DeductedAmt,10,2) as ded,Action_on_bal_amt,str(Agent_F_balAmt,10,2),Tran_status,transaction_id,Transaction_No from agent_transaction_details where Date_of_Transaction=convert(varchar(10),getdate(),120) and agent_id=? order by Time_of_Transaction asc";
			sql="select top 10 Ticket_id,Txn_id,Opened_Date,Opened_Time,Closed_Date,Closed_Time,Status,Query_Message,Solution_Message from Client_Ticket_Details where Client_Id=? and Client_Type='AG'"
					+"order by Opened_Date desc,Opened_Time desc";
			logger.info("SQL : "+sql);
			pstmt=con.prepareStatement(sql);
			pstmt.setLong(1, agentID);
			rs=pstmt.executeQuery();
			HashMap<String,String> temp;
			while(rs.next()){
				temp=new HashMap<String,String>(); 
				
				temp.put("Ticket_id",rs.getString(1)); 
				temp.put("Txn_id",rs.getString(2)); 
				temp.put("Opened_Date",rs.getString(3));
				temp.put("Opened_Time",rs.getString(4)); 
				temp.put("Closed_Date",rs.getString(5)==null?"NA":rs.getString(5)); 
				temp.put("Closed_Time",rs.getString(6)==null?"NA":rs.getString(6));
				temp.put("Status",rs.getString(7));
				temp.put("Query_Message",rs.getString(8)==null?"NA":rs.getString(8));
				temp.put("Solution_Message",rs.getString(9)==null?"NA":rs.getString(9));
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
	
	final boolean checkTicketExistance(long agentID,String txnId) {
		String sql="";
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try
		{
			
			con=DBConnection.getConnection();
			//sql="select convert(varchar(10),Date_of_Transaction,120),convert(varchar(10),Time_of_Transaction,108),Service,str(ReqAmount,10,2) as req,str(DeductedAmt,10,2) as ded,Action_on_bal_amt,str(Agent_F_balAmt,10,2),Tran_status,transaction_id,Transaction_No from agent_transaction_details where Date_of_Transaction=convert(varchar(10),getdate(),120) and agent_id=? order by Time_of_Transaction asc";
			sql="select Ticket_id,Txn_id,Opened_Date,Opened_Time,Closed_Date,Closed_Time,Status,Query_Message,Solution_Message from Client_Ticket_Details where Client_Id=? and Client_Type='AG' and Txn_id=? and status='Opened'";
			logger.info("SQL : "+sql);
			pstmt=con.prepareStatement(sql);
			pstmt.setLong(1, agentID);
			pstmt.setString(2, txnId);
			rs=pstmt.executeQuery();
			while(rs.next()){
				return false;			 
				
			}
			return true;
			//logger.info("---------------------AccountStatementDao--------------"+data+"\n");

		}catch (Exception ex) {
			logger.info("Exception in AccountStatementDao class method (getAccountStmtOfAgentOnClick)");
			ex.printStackTrace();
			return false;
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
	}
	
	final ArrayList<HashMap<String,String>> getTicketDetails(long agentID,String fromdate,String toDate) {
		ArrayList<HashMap<String,String>> data=new ArrayList<HashMap<String,String>>();
		String sql="";
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try
		{
			
			con=DBConnection.getConnection();
			//sql="select convert(varchar(10),Date_of_Transaction,120),convert(varchar(10),Time_of_Transaction,108),Service,str(ReqAmount,10,2) as req,str(DeductedAmt,10,2) as ded,Action_on_bal_amt,str(Agent_F_balAmt,10,2),Tran_status,transaction_id,Transaction_No from agent_transaction_details where Date_of_Transaction=convert(varchar(10),getdate(),120) and agent_id=? order by Time_of_Transaction asc";
			sql="select top 10 Ticket_id,Txn_id,convert(varchar(10),Opened_Date,120),Opened_Time,convert(varchar(16),Closed_Date),Closed_Time,Status,Query_Message,Solution_Message from Client_Ticket_Details where Client_Id=? and Client_Type='AG' and Opened_Date between '"+fromdate+"' and '"+toDate+"' "
					+"order by Opened_Date desc,Opened_Time desc";
			logger.info("SQL : "+sql);
			pstmt=con.prepareStatement(sql);
			pstmt.setLong(1, agentID);
			rs=pstmt.executeQuery();
			HashMap<String,String> temp;
			while(rs.next()){
				temp=new HashMap<String,String>(); 
				
				temp.put("Ticket_id",rs.getString(1)); 
				temp.put("Txn_id",rs.getString(2)); 
				temp.put("Opened_Date",rs.getString(3));
				temp.put("Opened_Time",rs.getString(4)); 
				temp.put("Closed_Date",rs.getString(5)==null?"NA":rs.getString(5)); 
				temp.put("Closed_Time",rs.getString(6)==null?"NA":rs.getString(6));
				temp.put("Status",rs.getString(7));
				temp.put("Query_Message",rs.getString(8)==null?"NA":rs.getString(8));
				temp.put("Solution_Message",rs.getString(9)==null?"NA":rs.getString(9));
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
	final HashMap<String,String> getAccountWIndowInfoForRecharge(String tranNo) {
		String sql="";
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		HashMap<String,String> temp=new HashMap<String,String>(); 
		try
		{
			
			con=DBConnection.getConnection();
			sql="select Agent_tran_id,USessionID,mobile_operator,mobile_number,str(amount,10,2) as amount,date_of_recharge,status,convert(varchar(10),date_time,108) from live_recharge where Agent_tran_id=? OR tran_id=?";
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
}
