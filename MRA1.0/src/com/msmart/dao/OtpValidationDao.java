package com.msmart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.msmart.dbconnection.DBConnection;

import net.sf.json.JSONObject;

public class OtpValidationDao {

	public String getRefNo(String mobile) {
		PreparedStatement pstmt=null;
		Connection con = null;
		String refNo="";
		try {
			String sql="select OTP from Pre_Agent_Registration where mobile_no=?";
			con=DBConnection.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, mobile);
			ResultSet rs=pstmt.executeQuery();

			while(rs.next())
				refNo=rs.getString(1);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("getRefNo exception");
		}finally{
			try {
				pstmt.close();
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return refNo;
	}
	public String validateMobileNo(String mob)
	{
		System.out.println("Mobile Number "+mob);
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rst=null;
		String agentId=null;
		try
		{
			con=DBConnection.getConnection();
			pstmt=con.prepareStatement("select agent_id from agent_details where mobile_no=?");
			pstmt.setString(1, mob);
			rst=pstmt.executeQuery();
			while(rst.next())
				agentId=rst.getString(1);

		}catch(Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
		finally
		{
			try
			{
				if(rst!=null)
				{
					rst.close();
				}
				if(pstmt!=null)
				{
					pstmt.close();
				}
				if(con!=null)
				{
					con.close();
				}
			}catch(Exception ex){

			}
		}
		return agentId;
	}

	public JSONObject insertOTP(String otp,String mobile)
	{
		System.out.println("Mobile Number "+mobile);
		JSONObject dataJson=new JSONObject();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rst=null;
		int inseteddata=0;
		try
		{	

			if(mobile!=null && mobile.length()>0){   


				String sql="update Pre_Agent_Registration set OTP=?   where mobile_no=?";

				con = DBConnection.getConnection();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, otp);
				pstmt.setString(2, mobile);
				int status=pstmt.executeUpdate();
				if(status==1)
				{
					dataJson=new JSONObject();
					dataJson.put("mobile", otp);
					dataJson.put("otp",mobile);


					return dataJson;


				}

				else{


					String sqlquery="INSERT INTO Pre_Agent_Registration (mobile_no,OTP,DateTime) VALUES ( '"+mobile+"',  '"+otp+"',  getdate() );";

					System.out.println("sqlquery::::::::::::::::"+sqlquery);
					PreparedStatement pstm=con.prepareStatement(sqlquery);
					inseteddata=pstm.executeUpdate();
					if(inseteddata>0)
					{
						dataJson.put("mobile", mobile);
						dataJson.put("otp", otp);
					}

				}

			}
		}catch(Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
		finally
		{
			try
			{
				if(rst!=null)
				{
					rst.close();
				}
				if(pstmt!=null)
				{
					pstmt.close();
				}
				if(con!=null)
				{
					con.close();
				}
			}catch(Exception ex){

			}
		}
		return dataJson;

	}

	public String validateOTP(String mobile,String otp) {
		PreparedStatement pstmt=null;
		Connection con = null;
		try {
			String sql="select OTP from Pre_Agent_Registration where mobile_no=?";
			con=DBConnection.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, mobile);
			ResultSet rs=pstmt.executeQuery();
			String dbOTP="";

			if(rs.next())
				dbOTP=rs.getString(1);

			if(otp.equals(dbOTP))
				return "Y";
			else
				return "N";

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Otp exception");
		}finally{
			try {
				pstmt.close();
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return "N";
	}




}
