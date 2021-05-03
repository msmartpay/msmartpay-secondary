package com.hemresdmr;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import org.apache.log4j.Logger;
import com.db.DBConnection;

public class HeremsDMRDao {

	Logger logger=Logger.getLogger(HeremsDMRDao.class);
	public HashMap<String,String> getUserDetails(String agentId) {
		
		HashMap<String,String> map=new HashMap<String,String>();
		Connection con=null;
		Statement pstmt=null;
		ResultSet rst=null;
		String ip=null;
		try
		{
			
			con=DBConnection.getConnection();
			String sqlQurry="select user_id,mob_pass from login_details where user_id="+Long.parseLong(agentId);
			logger.info("sqlQurry : "+sqlQurry);
			pstmt=con.createStatement();
			rst=pstmt.executeQuery(sqlQurry);
			
			while(rst.next())
			{
				map.put("AgentId", rst.getLong(1)+"");
				map.put("Pass", rst.getString(2));
			}
			logger.info("getVendorIp()>>>>>>>>>>>>>>>>>>>>>>  ip : "+ip);
			
		}catch(Exception exc)
		{
			exc.printStackTrace();
			logger.info("Exception getVendorIp "+exc);
			return null;
		}
		finally
		{
			try{
				
				con.close();
			}catch(Exception exc){
				exc.printStackTrace();
			}
		}
		return map;
	}
}
