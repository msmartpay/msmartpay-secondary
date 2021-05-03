package com.social.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.commons.lang.RandomStringUtils;

import com.msmart.dbconnection.DBConnection;


public class SocialDao {

	public String getReferralCode(String agent_id)
	{
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rst=null;
		String updateQuery=null;
		String ReferrelCode=null;
		int up_status=0;
		String ref=RandomStringUtils.randomAlphanumeric(6).toUpperCase();

		try
		{

			con=DBConnection.getConnection();
			pstmt=con.prepareStatement("select Refferal_Cod from  agent_details where agent_id="+agent_id);
			rst=pstmt.executeQuery();
			if(rst.next())
			{
				if(rst.getString(1)==null)
				{
					updateQuery="update agent_details set Refferal_Cod='"+ref+"' where agent_id="+agent_id;

					pstmt.close();
					pstmt=con.prepareStatement(updateQuery);
					up_status=pstmt.executeUpdate();
					if(up_status==1)
					{
						ReferrelCode=ref;	
					}
				}
				else{
					ReferrelCode=rst.getString(1);
				}


			}

		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			try
			{
				if(rst!=null)
				{
					rst.close();
					rst=null;
				}
				if(pstmt!=null)
				{
					pstmt.close();
					pstmt=null;
				}
				if(con!=null)
				{
					con.close();
					con=null;
				}
			}catch(Exception ex){}
		}
		return ReferrelCode;
	}


}
