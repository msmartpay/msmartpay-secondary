package com.msmart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.msmart.dbconnection.DBConnection;


public class ChangePasswordDao {

	public int changePassword(long user_id,String new_pass,String old_pass)
	{
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rst=null;
		String flag=null;
		String up_qurry=null;
		int up_flg=0;

		try
		{

			con=DBConnection.getConnection();
			up_qurry="update dbo.login_details set password='"+new_pass+"' where user_id="+user_id+" and password='"+old_pass+"'";
			pstmt=con.prepareStatement(up_qurry);
			up_flg=pstmt.executeUpdate();


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
		System.out.println("up_flg  "+up_flg);
		return up_flg;
	}

	

}
