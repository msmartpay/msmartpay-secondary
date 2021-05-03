package com.TickerMessage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import org.apache.log4j.Logger;
import com.db.DBConnection;

public final class TickerMessageDao {

	Logger logger=Logger.getLogger(TickerMessageDao.class);
	
	public final HashMap<String,String> getDynamicDetails(String ServerName) {
		HashMap<String,String> map=new HashMap<String,String>();
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try {
			
			
			con = DBConnection.getConnection();
			String domainName = ServerName.replaceAll("www.", "");
			domainName = "www." + domainName;
			
			
			String sql = "select w.client_id,w.Agent_user_type,w.company_name,w.Logo_Image,w.Help_Desk_MobileNo,w.domain_name,w.agent_login_url," +
					"w.Help_Desk_EmailID,w.Powered_By,w.agent_Ticker,w.banner_status from white_label_details w where w.domain_name=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, domainName);
			rs=pstmt.executeQuery();
			while (rs.next()) {
		
				map.put("clientId", rs.getString("client_id"));
				map.put("userType", rs.getString("Agent_user_type"));
				map.put("companyName", rs.getString("company_name"));
				map.put("headerHomeImage", rs.getString("Logo_Image"));
				map.put("mobileNo1", rs.getString("Help_Desk_MobileNo"));
				map.put("domainName", rs.getString("domain_name"));
				map.put("agentLoginURL", rs.getString("agent_login_url"));
				map.put("helpEmailID", rs.getString("Help_Desk_EmailID"));
				map.put("powerBy", rs.getString("Powered_By"));
				map.put("tickerMessage", rs.getString("agent_Ticker"));
				map.put("bannerStatus", rs.getString("banner_status"));
			}

		} catch (Exception ex) {
			logger.info("Exception in TickerMessage class method (getDynamicDetails)");
			logger.info(ex.toString());
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
				logger.info("Exception in TickerMessage class method (getDynamicDetails) while closing connection");
				logger.info(e.toString());
			}
		}
		return map;
	}
}