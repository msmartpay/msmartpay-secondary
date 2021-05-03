package com.msmart.util;

import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.dbConnection.DBConnection;

public final class SmsSendDao {

	public static void main(String[] args) {
		String sms="Dear Partner, Your username is manoj and password is 123456. Its confidential. Please change your password. Regards, Arpit Enterprises";
		SmsSendDao smsSendDao=new SmsSendDao();
		String status=smsSendDao.msg91("9582152616", sms, "1007292453545896111");
		System.out.println(status);
	}

	public  String msg91(String mobileNo,String message) {


		String result=""; 
		try { 
			message=message.replaceAll(" ", "%20");
			message=message.replaceAll("&", "and"); 
			String url="http://api.msg91.com/api/sendhttp.php?country=91&sender="+PropertyFile.
					SMS_SENDER_ID+"&route=4&mobiles="+mobileNo+"&authkey="+PropertyFile.
					SMS_AUTH_KEY+"&message="+message;
			String sendingResponsesmszone=UtilityP.post(url, null, "text", "GET", null); 
			if(sendingResponsesmszone==null) 
			{ 
				result="unsuccess"; 
			} else{
				result="success"; 
			} 
		} catch (Exception e) {

		} return result; 
	}
	
	public  String msg91(String mobileNo,String message,String template_id) {


		String result=""; 
		try { 
			//message=message.replaceAll(" ", "%20");
			//message=message.replaceAll("&", "and"); 
			String url="https://api.msg91.com/api/sendhttp.php?country=91&sender="+PropertyFile.
					SMS_SENDER_ID+"&route=4&mobiles="+mobileNo+"&authkey="+PropertyFile.
					SMS_AUTH_KEY+"&message="+URLEncoder.encode(message)+"&template_id="+template_id;
			System.out.println(url);
			String sendingResponsesmszone=UtilityP.post(url, null, "text", "GET", null); 
			if(sendingResponsesmszone==null) 
			{ 
				result="unsuccess"; 
			} else{
				result="success"; 
			} 
		} catch (Exception e) {

		} return result; 
	}

	public  String arnaventerprises(String mobileNo,String message) {


		String result=""; 
		try { 
			message=message.replaceAll(" ", "%20");
			message=message.replaceAll("&", "and"); 
			String url="http://sms.arnaventerprises.co.in/sms.aspx?ID="+PropertyFile.ARNAV_SMS_ID+"&Pwd="+PropertyFile.ARNAV_SMS_PWD+"&PhNo="+mobileNo+"&Text="+message+"&SMSType=10";
			String sendingResponsesmszone=UtilityP.post(url, null, "text", "GET", null); 
			if(sendingResponsesmszone==null) 
			{ 
				result="unsuccess"; 
			} else{
				result="success"; 
			} 
		} catch (Exception e) {

		} return result; 
	}


	public List<HashMap<String, String>> getPendingSMS(int status,String type)
	{
		List<HashMap<String, String>> sms=new ArrayList<HashMap<String,String>>();
		PreparedStatement pst = null;
		Connection con = null;
		ResultSet rs=null;
		try{

			con = DBConnection.getConnection();
			pst = con.prepareStatement("select top 10 destination ,message,id from send_message where status=? and type=? order by id asc");
			pst.setInt(1, status);
			pst.setString(2, type);

			rs=pst.executeQuery();
			while (rs.next()) {
				HashMap<String, String> data=new HashMap<String, String>();
				data.put("destination", rs.getString(1));
				data.put("message", rs.getString(2));
				data.put("id", rs.getLong(3)+"");
				sms.add(data);
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {

			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return sms;
	}

	public void updateSMSStatus(int status,Long id)
	{
		PreparedStatement pst = null;
		Connection con = null;
		try{
			
			con = DBConnection.getConnection();
			pst = con.prepareStatement("update send_message set status=? where id=?");
			
			pst.setInt(1, status);
			pst.setLong(2, id);
			
			System.out.println(pst.executeUpdate());
			
			
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

	public String getVendor(String service)
	{
		String vendor=null;
		PreparedStatement pst = null;
		Connection con = null;
		ResultSet rs=null;
		try{

			con = DBConnection.getConnection();
			pst = con.prepareStatement("select Vendor from Vendor_Management where service=?");
			pst.setString(1, service);

			rs=pst.executeQuery();
			while (rs.next()) {
				vendor=rs.getString(1);
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {

			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return vendor;
	}
}
