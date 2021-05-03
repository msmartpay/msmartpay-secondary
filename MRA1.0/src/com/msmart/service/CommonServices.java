package com.msmart.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;

import com.msmart.dao.MobileAppRechargeDao;
import com.msmart.dbconnection.DBConnection;

public class CommonServices {
	
	static Logger logger=Logger.getLogger(CommonServices.class);
	
	public static String dateTime()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyymmddhhmmss");
		Date date=new Date();
		String tran_id=sdf.format(date);

		return RandomStringUtils.randomAlphanumeric(4).toUpperCase()+tran_id;
	}
	public static String getAETranId()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyymmddhhmmss");
		Date date=new Date();
		String tran_id=sdf.format(date);
		String ran = RandomStringUtils.randomAlphanumeric(4).toUpperCase()+String.valueOf(tran_id);

		return ran;
	}
	
	public static String  getIdNo(String agentID) {

		String transaction_id="";
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
			Date date=new Date();
			String tran_id=sdf.format(date);
			transaction_id=tran_id+RandomStringUtils.randomAlphanumeric(6).toUpperCase();

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return transaction_id;
	}

	public static String createTransactionID()
	{
		String AgentTranID = "";
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmssSSSS");
			Date date = new Date();
			String tranID = sdf.format(date);
			int n = 4;
			Random randGen = new Random();
			int startNum = (int)Math.pow(10D, n - 1);
			int range = (int)(Math.pow(10D, n) - (double)startNum);
			int randomNum = randGen.nextInt(range) + startNum;
			String ran = String.valueOf(randomNum);
			AgentTranID = (new StringBuilder(String.valueOf(tranID))).append(ran).toString();
		}
		catch(Exception e)
		{
		}
		return AgentTranID;
	}

	public static int insertEKOLogs(String vendorName,String tranId,String reqString,String agentId)

	{
		logger.info("Execute getCustomerKycStatus method of dmr_customer_dao class");
		int Status = 0;
		PreparedStatement pst = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			pst = con.prepareStatement("insert into EGEN_recharge_log_details"
					+ "(vendor_name,transaction_id,Input_XML,transaction_date,agent_id)"
					+ " values(?,?,?,getdate(),?)");
			
			pst.setString(1, vendorName);
			pst.setString(2, tranId);
			pst.setString(3, reqString);
			pst.setString(4, agentId);
			
			int count = pst.executeUpdate();
			
			logger.info("Log Inserted : "+count);

		} catch (Exception e) {
			
			logger.info("Error in getCustomerKycStatus method of dmr_customer_dao class");
			e.printStackTrace();
			
		} finally {

			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.info("Error in getCustomerKycStatus method of dmr_customer_dao class while closing the connection"+ e.getMessage());
			}

		}
		return Status;

	}
	
	public static int updateEKOLogs(String tranId,String respString,String agentId)
	{
		logger.info("Execute getCustomerKycStatus method of dmr_customer_dao class");
		int Status = 0;
		PreparedStatement pst = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			pst = con.prepareStatement("update EGEN_recharge_log_details set Output_XML=? where transaction_id=?");
			pst.setString(1, respString);
			pst.setString(2, tranId);
			
			int count = pst.executeUpdate();
			
			logger.info("Log Inserted : "+count);

		} catch (Exception e) {
			
			logger.info("Error in getCustomerKycStatus method of dmr_customer_dao class");
			e.printStackTrace();
			
		} finally {

			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.info("Error in getCustomerKycStatus method of dmr_customer_dao class while closing the connection"+ e.getMessage());
			}

		}
		return Status;

	}
	
	public HashMap<String, String> getBalanStatus(String agent_Id,String rech_amount)
	{
		HashMap<String, String> bal_map=new HashMap<String, String>();
		MobileAppRechargeDao mad=new MobileAppRechargeDao();
		String agent_bala=mad.getAgentBalance(agent_Id);

		double result=Double.parseDouble(agent_bala)-Double.parseDouble(rech_amount);

		if(result>0)
		{
			bal_map.put("Status", "Y");
			bal_map.put("account_bal", agent_bala);

			return bal_map;
		}
		bal_map.put("Status", "N");	
		return bal_map;
	}

}
