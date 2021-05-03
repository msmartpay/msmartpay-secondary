package com.controlPanel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import org.apache.log4j.Logger;
import com.db.DBConnection;

final class ControlPanelDao {

	Logger logger=Logger.getLogger(ControlPanelDao.class);

	final String changePassword(String newPass, String agentID) {
		String update="";
		String status="fail";
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;

		try{

			con=DBConnection.getConnection();
			update="update login_details set password=? where user_id=?";
			pstmt = con.prepareStatement(update); 
			pstmt.setString(1, newPass); 
			pstmt.setLong(2, Long.parseLong(agentID));
			pstmt.execute();
			status="success";
		}catch (Exception ex) {
			status="fail";
			logger.info("Exception in ControlPanelDao class method (changePassword)");
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
				logger.info("Exception in ControlPanelDao class method (changePassword) while closing connection");
				e.printStackTrace();
			}
		}
		return status;
	}

	final HashMap<String,String> getAgentViewProfileData(String agentID) {
		HashMap<String,String> mapdata=new HashMap<String,String>();
		String sql="";
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;

		try{

			con=DBConnection.getConnection();
			sql="select agency_name,address1,state,district,city,pin_code,email_id,mobile_no,agent_name from agent_details where agent_id=?";
			pstmt = con.prepareStatement(sql); 
			pstmt.setLong(1, Long.parseLong(agentID)); 
			rs=pstmt.executeQuery();
			while(rs.next()){
				mapdata.put("AgencyName", rs.getString(1));
				mapdata.put("address", rs.getString(2));
				mapdata.put("state", rs.getString(3));
				mapdata.put("district", rs.getString(4));
				mapdata.put("city", rs.getString(5));
				mapdata.put("pinCode", rs.getString(6));
				mapdata.put("emailID", rs.getString(7));
				mapdata.put("mobileNo", rs.getString(8));
				mapdata.put("agentName", rs.getString(9));

			}
		}catch (Exception ex) {
			logger.info("Exception in ControlPanelDao class method (getAgentViewProfileData)");
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
				logger.info("Exception in ControlPanelDao class method (getAgentViewProfileData) while closing connection");
				e.printStackTrace();
			}
		}
		return mapdata;
	}

	final String updateUserProfile(String agentID, String agencyName,String address, String state, String district, String city,
			String pinCode, String emailID, String mobileNo, String emailIdOld, String mobileNoOld, String completeAgentID) {
		String update="";
		String sql="";
		String status="fail";
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;

		try{

			con=DBConnection.getConnection();
			con.setAutoCommit(false);
			update="update agent_details set agency_name=?,address1=?,state=?,district=?,city=?,pin_code=? where agent_id=?";
			pstmt = con.prepareStatement(update); 
			pstmt.setString(1, agencyName); 
			pstmt.setString(2, address);
			pstmt.setString(3, state);
			pstmt.setString(4, district);
			pstmt.setString(5, city);
			pstmt.setString(6, pinCode);
			pstmt.setLong(7, Long.parseLong(agentID));
			pstmt.execute();
			if(!mobileNoOld.equalsIgnoreCase(mobileNo)){
				sql="insert into MobileNo_change_request_tab (complete_user_id,user_id,user_Type,old_mobileNo,new_mobileNo,request_date,request_time) values" +
						"(?,?,?,?,?,getdate(),getdate())";
				pstmt = con.prepareStatement(sql); 
				pstmt.setString(1, completeAgentID); 
				pstmt.setLong(2, Long.parseLong(agentID));
				pstmt.setString(3, "Agent");
				pstmt.setString(4, mobileNoOld);
				pstmt.setString(5, mobileNo);
				pstmt.execute();
			}
			if(!emailIdOld.equalsIgnoreCase(emailID)) {
				sql="insert into EmailID_change_request_tab (complete_user_id,user_id,user_Type,old_mail_id,new_mail_id,request_date,request_time) values" +
						"(?,?,?,?,?,getdate(),getdate())";
				pstmt = con.prepareStatement(sql); 
				pstmt.setString(1, completeAgentID); 
				pstmt.setLong(2, Long.parseLong(agentID));
				pstmt.setString(3, "Agent");
				pstmt.setString(4, emailIdOld);
				pstmt.setString(5, emailID);
				pstmt.execute();
			}
			con.commit();
			status="success";
		}catch (Exception ex) {
			status="fail";
			try {
				con.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			logger.info("Exception in ControlPanelDao class method (updateUserProfile)");
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
				logger.info("Exception in ControlPanelDao class method (updateUserProfile) while closing connection");
				e.printStackTrace();
			}
		}
		return status;
	}
	
	final boolean validateExistingPass(String agentID,String pass) {
		String sql="";
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;

		try{

			con=DBConnection.getConnection();
			sql="select count(user_id) from login_details where (password=? or mob_pass=?) and user_id=?";
			pstmt = con.prepareStatement(sql); 
			pstmt.setString(1, pass);
			pstmt.setString(2, pass);
			pstmt.setLong(3, Long.parseLong(agentID)); 
			rs=pstmt.executeQuery();
			if(rs.next()){
				int count=rs.getInt(1);
				if(count>0)
					return true;
				else 
					return false;

			}
		}catch (Exception ex) {
			logger.info("Exception in ControlPanelDao class method (getAgentViewProfileData)");
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
				logger.info("Exception in ControlPanelDao class method (getAgentViewProfileData) while closing connection");
				e.printStackTrace();
			}
		}
		return false;
	}


}
