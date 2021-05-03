package com.common;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.db.DBConnection;

public class ChartJSDao {
	Logger logger=Logger.getLogger(ChartJSDao.class);
	
	public JSONArray findLast15Days(String agentID){
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		JSONArray arry=new JSONArray();
		try
		{
			
			con=DBConnection.getConnection();
			String sql="select distinct convert(varchar(10),date_,120)  as date_ from Day_Service_Wise_Business where agent_id=? and date_ between convert(varchar(10),getdate()-10,120) and convert(varchar(10),getdate(),120) order by date_ desc";
			pstmt = con.prepareStatement(sql); 
			pstmt.setLong(1, Long.parseLong(agentID)); 
			
			rs=pstmt.executeQuery();
			while(rs.next())
            {
				arry.put(rs.getString(1));
            }
		}catch (Exception ex) {
			logger.info("Exception in ChartJSDao class method (findLast15Days)");
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
				logger.info("Exception in ChartJSDao class method (findLast15Days) while closing connection");
				e.printStackTrace();
			}
		}
		return arry;
	}
	
	public JSONArray todayBusinessOperatorWise(String agentID, String fromDate,String toDate){
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		JSONArray arry=new JSONArray();
		JSONObject obj=null;
		try
		{
			
			con=DBConnection.getConnection();
			String sql="select mobile_operator,sum(convert(decimal(16,2),amount)) as tot_amount,sum(convert(decimal(16,2),mob_commission)) as comm from live_recharge where date_of_recharge between ? and ? and status='Success' and user_id=? group by mobile_operator ";
			pstmt = con.prepareStatement(sql); 
			pstmt.setString(1, fromDate);
			pstmt.setString(2,toDate);
			pstmt.setLong(3, Long.parseLong(agentID)); 
			
			rs=pstmt.executeQuery();
			while(rs.next())
            {
				obj=new JSONObject();
				obj.put("operator", rs.getString(1));
				obj.put("tot_amount", rs.getDouble(2));
				obj.put("comm", rs.getDouble(3));
				arry.put(obj);
            }
		}catch (Exception ex) {
			logger.info("Exception in ChartJSDao class method (findLast15Days)");
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
				logger.info("Exception in ChartJSDao class method (findLast15Days) while closing connection");
				e.printStackTrace();
			}
		}
		return arry;
	}
	public JSONArray findLast15DaysService(String agentID){
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		JSONArray arry=new JSONArray();
		try
		{
			
			con=DBConnection.getConnection();
			String sql="select distinct service_ from Day_Service_Wise_Business where agent_id=? order by service_ asc";
			pstmt = con.prepareStatement(sql); 
			pstmt.setLong(1, Long.parseLong(agentID)); 
			
			rs=pstmt.executeQuery();
			while(rs.next())
            {
				arry.put(rs.getString(1));
            }
		}catch (Exception ex) {
			logger.info("Exception in ChartJSDao class method (findLast15Days)");
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
				logger.info("Exception in ChartJSDao class method (findLast15Days) while closing connection");
				e.printStackTrace();
			}
		}
		return arry;
	}
	
	public Double findLast15DaysBusiness(String agentID,String service,String day){
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		Double data=0.0;
		try
		{
			con=DBConnection.getConnection();
			String sql="select amount from Day_Service_Wise_Business where agent_id=? and service_=? and date_ =? order by date_ desc";
			pstmt = con.prepareStatement(sql); 
			pstmt.setLong(1, Long.parseLong(agentID)); 
			pstmt.setString(2, service);
			pstmt.setString(3, day);
			
			rs=pstmt.executeQuery();
			while(rs.next())
            {
				data=rs.getDouble(1);
            }
		}catch (Exception ex) {
			logger.info("Exception in ChartJSDao class method (findLast15Days)");
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
				logger.info("Exception in ChartJSDao class method (findLast15Days) while closing connection");
				e.printStackTrace();
			}
		}
		return data;
	}
	
	public JSONArray todayServiceWiseBusiness(String agentID){
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		JSONArray arry=new JSONArray();
		JSONObject obj=null;
		try
		{
			
			con=DBConnection.getConnection();
			String sql="select Service,sum(ReqAmount) from agent_transaction_details (nolock) " + 
					"where Tran_status='Success' and Date_of_Transaction = convert(varchar(10),getdate(),120) and agent_id=? " + 
					"and Service in ('AEPS-Withdrawal','liveBillpay','liveDTHRech','liveMobRech','liveDCRech','DMR-E-REMIT','DMR-E-ACCOUNT')" + 
					"group by Service order by Service asc";
			pstmt = con.prepareStatement(sql); 
			pstmt.setLong(1, Long.parseLong(agentID)); 
			
			rs=pstmt.executeQuery();
			while(rs.next())
            {
				obj=new JSONObject();
				obj.put("service", rs.getString(1));
				obj.put("amount", rs.getString(2));
				arry.put(obj);
            }
		}catch (Exception ex) {
			logger.info("Exception in ChartJSDao class method (dayAndServiceWise)");
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
				logger.info("Exception in ChartJSDao class method (dayAndServiceWise) while closing connection");
				e.printStackTrace();
			}
		}
		return arry;
	}
	
}
