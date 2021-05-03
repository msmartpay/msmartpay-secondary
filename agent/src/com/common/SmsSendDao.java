package com.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.db.DBConnection;

public final class SmsSendDao {

	/*
	 * public static String sendMobileSms(String mobileNo,String message) {
	 * 
	 * 
	 * String result=""; try { message=message.replaceAll(" ", "%20");
	 * message=message.replaceAll("&", "and"); String
	 * url="http://api.msg91.com/api/sendhttp.php?country=91&sender="+PropertyFile.
	 * SMS_SENDER_ID+"&route=4&mobiles="+mobileNo+"&authkey="+PropertyFile.
	 * SMS_AUTH_KEY+"&message="+message; String
	 * sendingResponsesmszone=UtilityP.post(url, null, "text", "GET", null); if
	 * (sendingResponsesmszone==null) { result="unsuccess"; } else{
	 * result="success"; } } catch (Exception e) { } return result; }
	 */
	
	
	public static void sendMobileSms(String mobileNo,String message)
	{
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
