package com.msmart.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.msmart.dbconnection.DBConnection;

public class EkoAepsDao {
	
	Logger log=Logger.getLogger(EkoAepsDao.class);

	public String saveAepsLog(String userCode,String refId,String serviceType,String data,String action,String ip,String type,double amount) {
		PreparedStatement pstmt=null;
		Connection con = null;
		try {


			String sql="insert into aeps_transaction_log ( user_code, client_ref_id, service_type, date_time, raquest_data, action, ip,type,amount) "
					+ "values  (?,?,?,getdate(),?,?,?,?,?)";
			con=DBConnection.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, userCode);
			pstmt.setString(2, refId);
			pstmt.setString(3, serviceType);
			pstmt.setString(4, data);
			pstmt.setString(5, action);
			pstmt.setString(6, ip);
			pstmt.setString(7, type);
			pstmt.setDouble(8, amount);
			
			int count=pstmt.executeUpdate();
			if(count>0)
				return "Y";
			else
				return "N";

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			try {
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	public int updateAepsLog(String userCode,String clientRefId,String tranData)
	{
		log.info("Execute updateaepsLog");
		int Status = 0;
		PreparedStatement pst = null;
		Connection con = null;
		try {
			con = DBConnection.getConnection();
			pst = con.prepareStatement("update aeps_transaction_log set tran_data=? where user_code=? and client_ref_id=?");
			pst.setString(1, tranData);
			pst.setString(2, userCode);
			pst.setString(3, clientRefId);
			
			Status = pst.executeUpdate();
			
			log.info("Log update : "+Status);

		} catch (Exception e) {
			
			log.info("Error in updateaepsLog");
			e.printStackTrace();
			
		} finally {

			try {
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.info("Error in updateaepsLog while closing the connection"+ e.getMessage());
			}

		}
		return Status;

	}
	public String aepsCredit(String tid,String clientRefId,String bankRefId,String userCode,
			double commission,double tds,double amount,String ip,String status)

	{
		log.info("Execute insertEKOKYCRequest");
		String Status = "Failure";
		CallableStatement cstmt = null;
		Connection con = null;
		try {
			con = DBConnection.getConnection();
			cstmt = con.prepareCall("{call aeps_transaction_insert(?,?,?,?,?,?,?,?,?,?)}");
			cstmt.setString(1, clientRefId);
			cstmt.setString(2, userCode);
			cstmt.setDouble(3, amount);
			cstmt.setDouble(4, commission);
			cstmt.setDouble(5, tds);
			cstmt.setString(6, tid);
			cstmt.setString(7, bankRefId);
			cstmt.setString(8, ip);
			cstmt.setString(9, status);
			cstmt.registerOutParameter(10, 12);
			cstmt.execute();
			Status=cstmt.getString(10);
			
		} catch (Exception e) {
			
			log.info("Error in insertEKOKYCRequest");
			e.printStackTrace();
			
		} finally {

			try {
				if (cstmt != null)
					cstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.info("Error in insertEKOKYCRequest while closing the connection"+ e.getMessage());
			}

		}
		return Status;

	}
}
