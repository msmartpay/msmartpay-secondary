package com.msmart.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

import com.msmart.dbconnection.DBConnection;

public class MoneyTransferDao {
	
	public String walletToWalletTransfer(String transactionId,String agentId,String toAgentId,String idNo,double amount,String serviceName,String remark,String ip){
		
		System.out.println("MoneyTransferDao.walletToWalletTransfer() idNo : "+idNo+":transactionId:"+transactionId+":amount"+amount+":serviceName:"+serviceName+":remark:"+remark);
		Connection con = null;
		CallableStatement cstmt=null;
		String status="failure";
		try
		{
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
			System.out.println("Wallet Transfer :: "+status);
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
	public HashMap<String, String> turnOver(String agentId,String date) {
		
		HashMap<String,String> map=null;
		Connection con = null;
		Statement stmt=null;
		String status="failure";
		try
		{
			con=DBConnection.getConnection();
			String sql="select SUM(ReqAmount) as request,SUM(Commession) as comm,SUM(DeductedAmt) as deducted from agent_transaction_details where agent_id="+agentId+" and Action_on_bal_Amt='Debit' and tran_status='Success' and date_of_transaction='"+date+"'";
			stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			while (rs.next()) {
				map=new HashMap<String, String>();
				if(rs.getString(1)==null)
					return null;
				map.put("totalTran", rs.getDouble(1)+"");
				map.put("totalComm", rs.getDouble(2)+"");
				map.put("totalDeduction", rs.getDouble(3)+"");
			}
			System.out.println("Wallet Transfer :: "+status);
		}catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		finally {
			try {
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return map;
	}

}
