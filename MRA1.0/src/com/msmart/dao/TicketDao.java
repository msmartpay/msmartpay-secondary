package com.msmart.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONException;

import com.msmart.dbconnection.DBConnection;

import net.sf.json.JSONObject;

public class TicketDao {
	JSONObject fnaljsn =new JSONObject();

	public boolean submitTicket(String ticketId,String tranNo,long agentId,String clientType,String remark) {
		String sql="";
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

	public String insertSendToAccountRecord(String transactionId,
			String agentID, String bankName, String accountNo, double amt, String tRANSNO, String service, String ipaddress, 
			String accountHolderName,String ifsc, String remark) {


		Connection con = null;
		CallableStatement cstmt=null;
		String status="NA";

		try
		{
			con=DBConnection.getConnection();
			cstmt=con.prepareCall("{call Agent_Send_To_Account_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			cstmt.setString(1,transactionId);
			cstmt.setString(2,agentID);
			cstmt.setString(3,bankName);
			cstmt.setString(4,accountNo);
			cstmt.setDouble(5,amt);
			cstmt.setString(6,tRANSNO);
			cstmt.setString(7,service);
			cstmt.setString(8,ipaddress);
			cstmt.setString(9,accountNo);
			cstmt.setString(10,"SK");
			cstmt.setString(11,"ALL");
			cstmt.setString(12,accountHolderName);
			cstmt.setString(13,ifsc);
			cstmt.setString(14,remark);
			cstmt.registerOutParameter(15, java.sql.Types.VARCHAR);
			cstmt.execute();
			status=cstmt.getString(15);
			System.out.println("--------------Inserted--------------");
			//	System.out.println("Insert Record :: "+status);
		}catch (Exception ex) {
			System.out.println("TicketDao.insertSendToAccountRecord()");
			ex.printStackTrace();
		}
		finally {
			try {
				if (cstmt != null)
					cstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				System.out.println("TicketDao.insertSendToAccountRecord()");
				e.printStackTrace();
			}
		}
		return status;
	}

	public JSONObject getTicketDetails(long agentID,String date) throws JSONException {
		JSONArray data=new JSONArray();
		String sql="";
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try
		{
			System.out.println("TicketDao.getTicketDetails()");
			con=DBConnection.getConnection();
			//sql="select convert(varchar(10),Date_of_Transaction,120),convert(varchar(10),Time_of_Transaction,108),Service,str(ReqAmount,10,2) as req,str(DeductedAmt,10,2) as ded,Action_on_bal_amt,str(Agent_F_balAmt,10,2),Tran_status,transaction_id,Transaction_No from agent_transaction_details where Date_of_Transaction=convert(varchar(10),getdate(),120) and agent_id=? order by Time_of_Transaction asc";
			if(date!=null && !"".equalsIgnoreCase(date)){
				sql="select top 10 Ticket_id,Txn_id,Opened_Date,Opened_Time,Closed_Date,Closed_Time,Status,Query_Message,Solution_Message from Client_Ticket_Details where Client_Id=? and Client_Type='AG'"
					+" and Opened_Date=? order by Opened_Date desc,Opened_Time desc";
				pstmt=con.prepareStatement(sql);
				pstmt.setLong(1, agentID);
				pstmt.setString(2, date);
			}else{
				sql="select top 10 Ticket_id,Txn_id,Opened_Date,Opened_Time,Closed_Date,Closed_Time,Status,Query_Message,Solution_Message from Client_Ticket_Details where Client_Id=? and Client_Type='AG'"
					+" order by Opened_Date desc,Opened_Time desc";
				pstmt=con.prepareStatement(sql);
				pstmt.setLong(1, agentID);
			}
			System.out.println("SQL : "+sql);
			
			rs=pstmt.executeQuery();
			JSONObject temp;
			while(rs.next()){
				temp=new JSONObject(); 

				temp.put("Ticket_id",rs.getString(1)); 
				temp.put("Txn_id",rs.getString(2)); 
				temp.put("Opened_Date",rs.getString(3));
				temp.put("Opened_Time",rs.getString(4)); 
				temp.put("Closed_Date",rs.getString(5)==null?"NA":rs.getString(5)); 
				temp.put("Closed_Time",rs.getString(6)==null?"NA":rs.getString(6));
				temp.put("Status",rs.getString(7));
				temp.put("Query_Message",rs.getString(8)==null?"NA":rs.getString(8));
				temp.put("Solution_Message",rs.getString(9)==null?"NA":rs.getString(9));
				data.put(temp);	

			}
			if(data.length()>0){
				fnaljsn.put("ticketDetails", data.toString());
				fnaljsn.put("response-code", "0");
				fnaljsn.put("response-message", "Success");
			}else{
				fnaljsn.put("response-code", "1");
				fnaljsn.put("response-message", "Ticket not Available.");
			}
			
			
			//System.out.println("---------------------AccountStatementDao--------------"+data+"\n");

		}catch (Exception ex) {
			System.out.println("Exception in AccountStatementDao class method (getAccountStmtOfAgentOnClick)");
			ex.printStackTrace();
			fnaljsn.put("response-code", "1");
			fnaljsn.put("response-message", "Ticket not Available.");
			return fnaljsn;
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
				System.out.println("Exception in AccountStatementDao class method (getAccountStmtOfAgent) while closing connection");
				e.printStackTrace();
			}
		}
		return fnaljsn;
	}

	public boolean checkTicketExistance(long agentID,String txnId) {
		String sql="";
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try
		{

			con=DBConnection.getConnection();
			//sql="select convert(varchar(10),Date_of_Transaction,120),convert(varchar(10),Time_of_Transaction,108),Service,str(ReqAmount,10,2) as req,str(DeductedAmt,10,2) as ded,Action_on_bal_amt,str(Agent_F_balAmt,10,2),Tran_status,transaction_id,Transaction_No from agent_transaction_details where Date_of_Transaction=convert(varchar(10),getdate(),120) and agent_id=? order by Time_of_Transaction asc";
			sql="select Ticket_id,Txn_id,Opened_Date,Opened_Time,Closed_Date,Closed_Time,Status,Query_Message,Solution_Message from Client_Ticket_Details where Client_Id=? and Client_Type='AG' and Txn_id=? and status='Opened'";
			System.out.println("SQL : "+sql);
			pstmt=con.prepareStatement(sql);
			pstmt.setLong(1, agentID);
			pstmt.setString(2, txnId);
			rs=pstmt.executeQuery();
			while(rs.next()){
				return false;			 

			}
			return true;
			//System.out.println("---------------------AccountStatementDao--------------"+data+"\n");

		}catch (Exception ex) {
			System.out.println("Exception in AccountStatementDao class method (getAccountStmtOfAgentOnClick)");
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
				System.out.println("Exception in AccountStatementDao class method (getAccountStmtOfAgent) while closing connection");
				e.printStackTrace();
			}
		}
	}

	
	public JSONObject getAccountWIndowInfoForRecharge(String tranNo,long agentId) {
		String sql="";
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		JSONObject temp=new JSONObject(); 
		try
		{

			con=DBConnection.getConnection();
			sql="select Agent_tran_id,USessionID,mobile_operator,mobile_number,str(amount,10,2) as amount,date_of_recharge,status,convert(varchar(10),date_time,108) from live_recharge where Agent_tran_id=? OR tran_id=? and user_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, tranNo);
			pstmt.setString(2, tranNo);
			pstmt.setLong(3, agentId);
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
			System.out.println("Exception in AccountStatementDao class method (getAccountStmtOfAgent)");
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
				System.out.println("Exception in AccountStatementDao class method (getAccountStmtOfAgent) while closing connection");
				e.printStackTrace();
			}
		}
		return temp;
	}
	public static String  getTicketId() {
		String transaction_id="";
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmssSSSS");
			Date date=new Date();
			String tran_id=sdf.format(date);
			transaction_id="T"+tran_id;
			System.out.println("IdNo-----"+transaction_id);
		}
		catch(Exception e)
		{
			System.out.println("Execption in GenerateIdDao");
			e.printStackTrace();
		}
		return transaction_id;
	}
}
