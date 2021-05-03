package com.msmart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.msmart.dbconnection.DBConnection;

public class ForgetPasswordDao {

	public String validateMobileNo(String mob, String oTP)
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
			if(agentId!=null)
			{
				String sql="update agent_details set OTP=? where agent_id=?";


				PreparedStatement pstnt = con.prepareStatement(sql);
				pstnt.setString(1, oTP);
				pstnt.setString(2, agentId);
				int status=pstnt.executeUpdate();
				if(status==1)
				{


					return agentId;


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
			}catch(Exception ex){

			}
		}
		return agentId;
	}
	public String updatePasswordProcess(long userID, String OTP,String pass)
	{
		System.out.println("agentId "+userID);
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rst=null;
		String OldOTP=null;

		try
		{
			con=DBConnection.getConnection();
			pstmt=con.prepareStatement("select OTP from agent_details where agent_id=?");
			pstmt.setLong(1, userID);
			rst=pstmt.executeQuery();
			while(rst.next())
				OldOTP=rst.getString(1);
			if(OldOTP.equals(OTP))
			{
				String sql="update login_details set password=? where user_id=?";


				PreparedStatement pstnt = con.prepareStatement(sql);
				pstnt.setString(1, pass);
				pstnt.setLong(2, userID);
				int status=pstnt.executeUpdate();
				if(status==1)
				{


					return "Y";


				}
			}

		}catch(Exception ex)
		{
			ex.printStackTrace();
			return "N";
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
		return "N";
	}
	
	public String updatePasswordProcess(long userID,String pass)
	{
		System.out.println("agentId "+userID);
		Connection con=null;
		PreparedStatement pstmt=null;

		try
		{
			con=DBConnection.getConnection();

			String sql="update login_details set password=? where user_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pass);
			pstmt.setLong(2, userID);
			int status=pstmt.executeUpdate();
			if(status==1)
			{


				return "Y";


			}
		

		}catch(Exception ex)
		{
			ex.printStackTrace();
			return "N";
		}
		finally
		{
			try
			{
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
		return "N";
	}

}

