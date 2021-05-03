package com.utilityService;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.db.DBConnection;

public class UtilityServiceDao {

	Logger logger=Logger.getLogger(UtilityServiceDao.class);
	public HashMap<String,String> getRechargeStatusOfBillpay(String agentID, String service,String billpayOp, String billAmount) {
		String status="NA";
		String vendor="NA";
		HashMap<String,String> hm=new HashMap<String,String>();
		Connection con = null;
		CallableStatement cstmt=null;
		try
		{

			con=DBConnection.getConnection();
			cstmt=con.prepareCall("{call Billpay_Validation(?,?,?,?,?,?)}");
			cstmt.setString(1,agentID);
			cstmt.setString(2,service);
			cstmt.setString(3,billpayOp);
			cstmt.setString(4,billAmount);

			cstmt.registerOutParameter(5, java.sql.Types.VARCHAR);
			cstmt.registerOutParameter(6, java.sql.Types.VARCHAR);
			cstmt.execute();
			status=cstmt.getString(5);
			vendor=cstmt.getString(6);
			hm.put("status",status);
			hm.put("vendor",vendor);
		}catch (Exception ex) {
			vendor="NA";
			logger.info("Exception in UtilityServiceDao class method (getRechargeStatusOfBillpay)");
			ex.printStackTrace();
		}
		finally {
			try {
				if (cstmt != null)
					cstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				logger.info("Exception in UtilityServiceDao class method (getRechargeStatusOfBillpay) while closing connection");
				e.printStackTrace();
			}
		}
		return hm;
	}
	
	public String billpayInsert(String transactionId,String transactionNo,String agentID, String billpayOp, String connectionNo,
			double amt,  String service, String ipaddress, String billContactNo,String vendor) {


		Connection con = null;
		CallableStatement cstmt=null;
		String status="NA";

		try
		{
			con=DBConnection.getConnection();
			cstmt=con.prepareCall("{call BillPay_Insert(?,?,?,?,?,?,?,?,?,?,?)}");
			cstmt.setString(1,transactionId);
			cstmt.setString(2,agentID);
			cstmt.setString(3,billpayOp);
			cstmt.setString(4,connectionNo);
			cstmt.setDouble(5,amt);
			cstmt.setString(6,transactionNo);
			cstmt.setString(7,service);
			cstmt.setString(8,ipaddress);
			cstmt.setString(9,billContactNo);
			cstmt.setString(10,vendor);
			cstmt.registerOutParameter(11, java.sql.Types.VARCHAR);
			cstmt.execute();
			status=cstmt.getString(11);
			System.out.println("--------------Inserted--------------");
		}catch (Exception ex) {
			System.out.println("Exception in UtilityServiceDao class method (insertBillPayRecord)");
			ex.printStackTrace();
		}
		finally {
			try {
				if (cstmt != null)
					cstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				System.out.println("Exception in UtilityServiceDao class method (insertBillPayRecord) while closing connection");
				e.printStackTrace();
			}
		}
		return status;
	}
	

	public String updateStatusBillPayTran(String tranId,String status,String agentID,String RESULT,String ERROR,
			String rechargeRefId,String checkVendor){
		String result="error";
		Connection con = null;
		CallableStatement cstmt=null;
		try 
		{

			con=DBConnection.getConnection();
			cstmt=con.prepareCall("{call Update_BillPay_Tran_Status(?,?,?,?,?,?)}");
			cstmt.setString(1,tranId);
			cstmt.setString(2,status);
			cstmt.setLong(3,Long.parseLong(agentID));
			cstmt.setString(4,rechargeRefId);
			cstmt.setString(5,checkVendor);
			cstmt.registerOutParameter(6, java.sql.Types.VARCHAR);
			cstmt.execute();
			result=cstmt.getString(6);

		}catch (Exception ex) {
			logger.info("Exception in UtilityServiceDao class method (updateStatusBillPayTran)");
			ex.printStackTrace();
		}
		finally 
		{
			try 
			{
				if (cstmt != null)
					cstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				logger.info("Exception in UtilityServiceDao class method (updateStatusBillPayTran) while closing connection");
				e.printStackTrace();
			}
		}
		return result;
	}

	public HashMap<String,String> getAgentDtails(String agentId) {
		HashMap<String,String> map=new HashMap<String, String>();
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {

			con=DBConnection.getConnection();

			psmt = con.prepareStatement("select email_id,mobile_no from agent_details where agent_id=?");
			psmt.setLong(1, Long.parseLong(agentId));
			rs = psmt.executeQuery();

			while(rs.next()){
				map.put("MailId", rs.getString(1));
				map.put("Mobile", rs.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			try {
				if(con!=null)
					con.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return map;
	}
}
