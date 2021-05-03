package com.listener;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import org.apache.log4j.Logger;

import com.db.DBConnection;

public class StateDistrictDetailsDao {

	Logger logger=Logger.getLogger(StateDistrictDetailsDao.class);
	
	
	public  HashMap <String,ArrayList<String>> getStateDistrict() 
	{
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		HashMap <String,ArrayList<String>> districtData=new HashMap<String, ArrayList<String>>();
		try
		{
	    	
	    	con=DBConnection.getConnection();
	    	String sql="SELECT DISTINCT p.State,STUFF((SELECT distinct ',' + p1.District FROM tep_state_district_details p1 (nolock) WHERE p.State = p1.State FOR XML PATH(''), TYPE ).value('.', 'NVARCHAR(MAX)') ,1,1,'') district FROM tep_state_district_details p (nolock)";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				String district=rs.getString(2);
				districtData.put(rs.getString(1), new ArrayList(Arrays.asList(district.split("\\s*,\\s*"))));
			}
		}
	    catch(Exception e) {
	    	logger.info("Exception In StateDistrictDetailsDao class (getStateDistrict) ");
	    	e.printStackTrace();
	    }finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				logger.info("Exception in ForgotPasswordDao class method (sendPassword) while closing connection");
				e.printStackTrace();
			}
		}
		return districtData;
		
	}

}
