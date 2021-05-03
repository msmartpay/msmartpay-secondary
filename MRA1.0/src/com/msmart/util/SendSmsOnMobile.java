package com.msmart.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.msmart.dbconnection.DBConnection;

public class SendSmsOnMobile
{
	public static void  sendMobileSmsSMSZONE(String mobileNo,String message) {
		PreparedStatement pst = null;
		Connection con = null;
		try{
			
			con = DBConnection.getConnection();
			pst = con.prepareStatement("insert into send_message"
					+ "(type,destination,message,status)"
					+ " values('SMS',?,?,0)");
			
			pst.setString(1, mobileNo);
			pst.setString(2, message);
			
			pst.executeUpdate();
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {

			try {
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}