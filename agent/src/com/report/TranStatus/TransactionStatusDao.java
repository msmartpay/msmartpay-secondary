package com.report.TranStatus;

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
final class TransactionStatusDao {
	
	Logger logger=Logger.getLogger(TransactionStatusDao.class);
	
	final ArrayList<HashMap<String,String>> getTransactionStatusData(String connectionNo,String agentID) {
		ArrayList<HashMap<String,String>> listData=new ArrayList<HashMap<String,String>>();
		String sql="";
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try
		{
			
			con=DBConnection.getConnection();
			con.setAutoCommit(false);
			sql="select top(5) l.mobile_number,l.mobile_operator,l.date_of_recharge,convert(varchar(10),l.date_time,108) as time,str(l.amount,10,2) as amount,l.status from live_recharge (nolock) l where l.mobile_number=? and l.user_id=? order by l.date_time desc";
			pstmt = con.prepareStatement(sql); 
			pstmt.setString(1, connectionNo); 
			pstmt.setLong(2, Long.parseLong(agentID)); 
			
			rs=pstmt.executeQuery();
			HashMap<String,String> map;
			while(rs.next())
			{
				map=new HashMap<String,String>();
				map.put("mobileNo", rs.getString(1));
				map.put("mobileOperator", rs.getString(2));
				map.put("dor", rs.getString(3));
				map.put("tor", rs.getString(4));
				map.put("amount", rs.getString(5));
				map.put("status", rs.getString(6));
				listData.add(map);
			}
			
		}catch (Exception ex) {
			logger.info("Exception in TransactionStatusDao class method (getTransactionStatusData)");
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
				logger.info("Exception in TransactionStatusDao class method (getTransactionStatusData) while closing connection");
				e.printStackTrace();
			}
		}
		return listData;
	}

}
