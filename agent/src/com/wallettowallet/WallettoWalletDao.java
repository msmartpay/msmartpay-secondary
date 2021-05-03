package com.wallettowallet;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import org.apache.log4j.Logger;

import com.db.DBConnection;


public class WallettoWalletDao {

	Logger logger=Logger.getLogger(WallettoWalletDao.class);
	
	public ArrayList<HashMap<String,String>> collectionBank(long agentID) {
		String sql="";
		ArrayList<HashMap<String,String>> data=new ArrayList<HashMap<String,String>>();
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try
		{

			con=DBConnection.getConnection();
			sql="select bank_name,bank_account,bank_account_name,bnk_ifsc from wb_collection_bank_detils (nolock) a join agent_details (nolock) b on a.client_id=b.Client_ID and b.agent_id=? and a.status=1";
			pstmt=con.prepareStatement(sql);
			pstmt.setLong(1, agentID);
			rs=pstmt.executeQuery();
			HashMap<String,String> temp;
			while(rs.next()){
				temp=new HashMap<String,String>(); 
				temp.put("bank_name",rs.getString(1)); 
				temp.put("bank_account",rs.getString(2)); 
				temp.put("bank_account_name",rs.getString(3));
				temp.put("bnk_ifsc",rs.getString(4));  
				data.add(temp);			 
			}

		}catch (Exception ex) {
			logger.info("Exception in TransaferRequestDao class method (getDepositDetail)");
			ex.printStackTrace();
			return null;
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
				logger.info("Exception in TransaferRequestDao class method (getDepositDetail) while closing connection");
				e.printStackTrace();
			}
		}
		return data;
	}
	
	public String walletToWalletTransfer(String transactionId,String agentId,String toAgentId,String idNo,double amount,String serviceName,String remark,String ip){

		logger.info("MoneyTransferDao.walletToWalletTransfer() idNo : "+idNo+":transactionId:"+transactionId+":amount"+amount+":serviceName:"+serviceName+":remark:"+remark);
		Connection con = null;
		CallableStatement cstmt=null;
		String status="failure";
		try
		{
			//idNo : 8421m939091704:transactionId:1611122236440920M738:amount1.0:serviceName:Wallet to Wallet:remark:Test
			con=DBConnection.getConnection();
			cstmt=con.prepareCall("{call Wallet_to_Wallet_Transfer(?,?,?,?,?,?,?,?,?)}");
			cstmt.setString(1,transactionId);
			cstmt.setString(2,agentId);
			cstmt.setString(3,toAgentId);
			cstmt.setDouble(4,amount);
			cstmt.setString(5,idNo);
			cstmt.setString(6,serviceName);
			cstmt.setString(7,ip);
			cstmt.setString(8,remark);
			cstmt.registerOutParameter(9, java.sql.Types.VARCHAR);
			cstmt.execute();
			status=cstmt.getString(9);
			logger.info("Wallet Transfer :: "+status);
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			try {
				if (cstmt != null)
					cstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return status;
	}
	public String getTranId_20()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmssSSSS");
		Date date=new Date();
		String tran_id=sdf.format(date);
		int n = 3;
		Random randGen = new Random();
		int startNum = (int) Math.pow(10, n-1);
		int range = (int) (Math.pow(10, n) - startNum);
		int randomNum = randGen.nextInt(range) + startNum;
		String ran = String.valueOf(randomNum);

		return tran_id+"M"+ran;
	}

	public  String getTranId_14(String tran_Id)
	{
		int n = 4;
		Random randGen = new Random();	    
		int startNum = (int) Math.pow(10, n-1);
		int range = (int) (Math.pow(10, n) - startNum);	    
		int randomNum = randGen.nextInt(range) + startNum;
		String txn_4 = String.valueOf(randomNum);

		n = 9;
		randGen = new Random();	    
		startNum = (int) Math.pow(10, n-1);
		range = (int) (Math.pow(10, n) - startNum);	    
		randomNum = randGen.nextInt(range) + startNum;
		String txn_14 =txn_4+"m"+String.valueOf(randomNum);

		logger.info("txn _ 14  "+txn_14);
		return txn_14;
	}
	final HashMap<String,String> getAgentDetails(String agentID) {
		HashMap<String,String> map=new HashMap<String,String>();
		String sql="";
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try
		{

			con=DBConnection.getConnection();
			con.setAutoCommit(false);
			sql="select agent_id,agent_name from agent_details where agent_id="+Long.parseLong(agentID);
			pstmt = con.prepareStatement(sql); 

			rs=pstmt.executeQuery();
			while(rs.next())
			{
				map=new HashMap<String,String>();
				map.put("agentId", rs.getString(1));
				map.put("agentName", rs.getString(2));
			}

		}catch (Exception ex) {
			logger.info("Exception in TransactionStatusDao class method (getTransactionStatusData)");
			ex.printStackTrace();
			return null;
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
				logger.info("Exception in TransactionStatusDao class method (getTransactionStatusData) while closing connection");
				e.printStackTrace();
			}
		}
		return map;
	}

	final ArrayList<HashMap<String,String>> getDepositDetail(long agentID) {
		String sql="";
		ArrayList<HashMap<String,String>> data=new ArrayList<HashMap<String,String>>();
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try
		{

			con=DBConnection.getConnection();
			sql="select top 10 mode_of_payment,ammount_to_credit,request_date,approval_date,isnull(Referece_no,'') as ref_id,status,isnull(remarks,'') from agent_journal where agent_id=? order by request_date desc,request_time desc";
			pstmt=con.prepareStatement(sql);
			pstmt.setLong(1, agentID);
			rs=pstmt.executeQuery();
			HashMap<String,String> temp;
			while(rs.next()){
				temp=new HashMap<String,String>(); 
				temp.put("mod",rs.getString(1)); 
				temp.put("amount",rs.getString(2)); 
				temp.put("reqDate",rs.getString(3));
				temp.put("appDate",rs.getString(4));  
				temp.put("refId",rs.getString(5));
				temp.put("status",rs.getString(6));
				temp.put("remark",rs.getString(7));
				data.add(temp);			 
			}

		}catch (Exception ex) {
			logger.info("Exception in TransaferRequestDao class method (getDepositDetail)");
			ex.printStackTrace();
			return null;
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
				logger.info("Exception in TransaferRequestDao class method (getDepositDetail) while closing connection");
				e.printStackTrace();
			}
		}
		return data;
	}

	public String insertRecordForBalanceReq(String agentID, String transactionId,String depDate, String amount,String cbDepBrTid, 
			String cbRecBankName, String acceptAmt,String cbremarks,String mode) {
		String sql="";
		String status="fail";
		Connection con = null;
		PreparedStatement pstmt=null;
		try
		{

			con=DBConnection.getConnection();
			con.setAutoCommit(false);
			sql="INSERT INTO agent_journal (agent_id,journal_id,journal_date,Request_Date,Request_Time,Mode_of_payment,Ammount_To_Credit,Bank_charges,"
					+ "Accepted_Amount,bank_name,transaction_id,Deposit_Date,Status,distributor_id,Referece_no) values" +
					"(?,?,getdate(),getdate(),getdate(),?,?,?,?,?,?,?,'Pending',(select distributor_id from agent_details where agent_id="+Long.parseLong(agentID)+"),?)";
			//logger.info(sql);
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, agentID);
			pstmt.setString(2, transactionId);
			pstmt.setString(3, mode);
			pstmt.setString(4, amount);
			pstmt.setString(5, "0");
			pstmt.setString(6, acceptAmt);
			pstmt.setString(7, cbRecBankName);
			pstmt.setString(8, cbDepBrTid);
			pstmt.setString(9, depDate);
			pstmt.setString(10, cbDepBrTid);
			pstmt.execute();
			con.commit();
			status="Success";
		}catch (Exception ex) {
			status="fail";
			try {
				con.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			logger.info("Exception in TransaferRequestDao class method (insertRecordForCashDeposite)");
			ex.printStackTrace();
		}
		finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				logger.info("Exception in TransaferRequestDao class method (insertRecordForCashDeposite) while closing connection");
				e.printStackTrace();
			}
		}
		return status;
	}
}
