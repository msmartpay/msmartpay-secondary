package com.common;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import com.db.DBConnection;

public class BackButtonDao {
	Logger logger=Logger.getLogger(BackButtonDao.class);
	
	public String getAgentBalance(String agentID){
		String sql="";
		String balance="";
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try
		{
			
			con=DBConnection.getConnection();
			sql="select str((TotCash-usedcash+cutoff_amount),10,2) as bal from agent_amount  where agent_id=?";
			pstmt = con.prepareStatement(sql); 
			pstmt.setLong(1, Long.parseLong(agentID)); 
			
			rs=pstmt.executeQuery();
			while(rs.next())
            {
				balance=rs.getString(1);
            }
		}catch (Exception ex) {
			logger.info("Exception in BackButtonDao class method (getAgentBalance)");
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
				logger.info("Exception in BackButtonDao class method (getAgentBalance) while closing connection");
				e.printStackTrace();
			}
		}
		return balance;
	}
	public int insertEKOLogs(String vendorName,String tranId,String reqString,String agentId)

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
	
	public int updateEKOLogs(String tranId,String respString,String agentId)
	{
		logger.info("Execute getCustomerKycStatus method of dmr_customer_dao class");
		int Status = 0;
		PreparedStatement pst = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			pst = con.prepareStatement("update EGEN_recharge_log_details set Output_XML=? where transaction_id=? and agent_id=?");
			pst.setString(1, respString);
			pst.setString(2, tranId);
			pst.setString(3, agentId);
			
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

	public String accountStatementInsert(String transactionId,long agentID, String operator, double amt, String tranNo, 
			String service, String ipaddress,String provider,String mobile) {

		Connection con = null;
		CallableStatement cstmt=null;
		String status="NA";
		try
		{

			con=DBConnection.getConnection();
			cstmt=con.prepareCall("{call agent_account_debit(?,?,?,?,?,?,?,?,?,?)}");
			cstmt.setString(1,transactionId);
			cstmt.setLong(2,agentID);
			cstmt.setDouble(3,amt);
			cstmt.setString(4,tranNo);
			cstmt.setString(5,service);
			cstmt.setString(6,operator);
			cstmt.setString(7,ipaddress);
			cstmt.setString(8,provider);
			cstmt.setString(9,mobile);
			cstmt.registerOutParameter(10, java.sql.Types.VARCHAR);
			cstmt.execute();
			status=cstmt.getString(10);
			logger.info("accountStatementInsert Insert Record-------------------- :: "+status);
		}catch (Exception ex) {
			logger.info("accountStatementInsert "+ex.getMessage());
		}
		finally {
			try {
				if (cstmt != null)
					cstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				logger.info("accountStatementInsert "+e.getMessage());
			}
		}
		return status;
	}

	public String accountStatementUpdateStatus(String transactionId,long agentID, String status) {

		Connection con = null;
		CallableStatement cstmt=null;
		try
		{

			con=DBConnection.getConnection();
			cstmt=con.prepareCall("{call agent_account_debit_update(?,?,?,?)}");
			cstmt.setLong(1,agentID);
			cstmt.setString(2,transactionId);
			cstmt.setString(3,status);
			cstmt.registerOutParameter(4, java.sql.Types.VARCHAR);
			cstmt.execute();
			status=cstmt.getString(4);
			logger.info("accountStatementUpdateStatus Insert Record-------------------- :: "+status);
		}catch (Exception ex) {
			logger.info("accountStatementUpdateStatus "+ex.getMessage());
		}
		finally {
			try {
				if (cstmt != null)
					cstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				logger.info("accountStatementUpdateStatus "+e.getMessage());
			}
		}
		return status;
	}

	public int updateAPICallbackUrl(String url,String agentId)
	{
		logger.info("Execute updateAPICallbackUrl");
		PreparedStatement pst = null;
		Connection con = null;
		int count=0;
		try {
			con = DBConnection.getConnection();
			pst = con.prepareStatement("update agent_details set callback_url=? where agent_id=?");
			pst.setString(1, url);
			pst.setString(2, agentId);
			count= pst.executeUpdate();
			logger.info("Log Inserted : "+count);

		} catch (Exception e) {
			
			logger.info("Error in updateAPICallbackUrl");
			e.printStackTrace();
			
		} finally {

			try {
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.info("Error in updateAPICallbackUrl while closing the connection"+ e.getMessage());
			}

		}
		return count;

	}

}
