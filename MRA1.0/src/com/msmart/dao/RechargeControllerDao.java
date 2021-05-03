package com.msmart.dao;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.msmart.dbconnection.DBConnection;

import net.sf.json.JSONObject;

public class RechargeControllerDao {
	
	Logger logger=Logger.getLogger(RechargeControllerDao.class);

	public JSONObject agentAuthenticationDao(String mobileNo,String password)
	{
		JSONObject jsonObject=null;
		JSONObject authjsn=null;
		RechargeColUtil rcu=new RechargeColUtil();
		CallableStatement  cstmt=null;
		Connection con=null;
		ResultSet rst=null;
		try{
			con=DBConnection.getConnection();
			cstmt=con.prepareCall("{call Mobile_User_Authtication(?,?)}");
			cstmt.setString(1,mobileNo);
			cstmt.setString(2,password);
			cstmt.execute();
			rst = cstmt.getResultSet(); 

			while(rst.next())
			{
				authjsn=new JSONObject();
				authjsn.put("Status", rst.getString("Status"));
				authjsn.put("agentId_initial",rst.getString("agentId_initial"));
				authjsn.put("agent_Id",rst.getString("agent_Id"));
				DecimalFormat df = new DecimalFormat("#.00");
				double bal=Double.parseDouble(rst.getString("Balance")==null?"0":rst.getString("Balance").toString());
				authjsn.put("Balance",df.format(bal)+"");
				authjsn.put("mobileNumber",rst.getString("mobileNumber"));
				authjsn.put("agent_dst_id",rst.getString("agent_dst_id"));
				authjsn.put("appText",rst.getString("appText"));
				authjsn.put("agentcommissin",rst.getString("agentcommissin"));
				authjsn.put("agentmark",rst.getString("agentmark"));
				authjsn.put("agentName",rst.getString("agent_name"));
				authjsn.put("emailId",rst.getString("email_id"));
				authjsn.put("walletStatus",rst.getString("walletStatus"));
				authjsn.put("DMR",rst.getString("DMR"));
				authjsn.put("agentUrl",rst.getString("agentUrl"));
				authjsn.put("supportMobile",rst.getString("supportMobile"));
				authjsn.put("supportEmail",rst.getString("supportEmail"));
				authjsn.put("userType",rst.getString("userType"));
				authjsn.put("addmoney",rst.getString("addMoney"));
				authjsn.put("kycStatus", rst.getString("kycStatus"));
				authjsn.put("about",rst.getString("aboutus"));
				authjsn.put("DailyNeedStatus",rst.getString("dailyNeeds"));
				authjsn.put("CleaningRepairStatus",rst.getString("cleaningRepair"));
				authjsn.put("dmrVendor",rst.getString("dmrVendor"));
				authjsn.put("partner","");
				authjsn.put("seller", "");
				authjsn.put("recharge", "N");
				authjsn.put("flightStatus", "N");
				authjsn.put("busStatus", "Y");
				authjsn.put("HotelStatus", "N");
				authjsn.put("addAmtStatus", "Y");
				authjsn.put("cancelationPolicy", "");
				authjsn.put("Pass","");
				authjsn.put("token",rst.getString("token")==null?"":rst.getString("token"));
				
			}
			logger.info("Login Response  "+authjsn);
			if(authjsn!=null)
				jsonObject=rcu.generateXmlResponse(authjsn);
			
			return jsonObject;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
		finally
		{
			try{
				if(rst!=null)
					rst.close();
				if(cstmt!=null)
					cstmt.close();
				if(con!=null)
					con.close();
			}
			catch(Exception ex)
			{
				System.out.println("Exception in closing connection "+ex);
				return null;
			}
		}
	}

	public void insert_Txn_Key(String tran_key,String agent_id)
	{
		Connection con=null;
		PreparedStatement pstmt=null;

		try
		{

			con=DBConnection.getConnection();
			pstmt=con.prepareStatement("update dbo.agent_details set Tran_key=? where agent_id=?");
			pstmt.setString(1,tran_key);
			pstmt.setString(2, agent_id);
			pstmt.executeUpdate();
			

		}catch(SQLException ex)
		{
			ex.printStackTrace();
			
		}
		finally
		{
			try{
				if(pstmt!=null)
					pstmt.close();
				if(con!=null)
					con.close();
			}catch(SQLException ex)
			{
				ex.printStackTrace();
			}
		}
	}
	public HashMap<String,String> getUrl(String agent_id)
	{
		HashMap<String,String> map=null;
		Connection con=null;
		PreparedStatement pstmt=null;

		try
		{

			con=DBConnection.getConnection();
			pstmt=con.prepareStatement("select Agent_login_url,Agent_user_type from white_label_details where Client_Id=(select Client_ID from dbo.agent_details where agent_id=?)");
			pstmt.setLong(1, Long.parseLong(agent_id));
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()){
				map=new HashMap<String,String>();
				map.put("url", rs.getString(1));
				map.put("type", rs.getString(2));
			}

		}catch(SQLException ex)
		{
			ex.printStackTrace();
			return null;
		}
		finally
		{
			try{
				if(pstmt!=null)
					pstmt.close();
				if(con!=null)
					con.close();
			}catch(SQLException ex)
			{
				ex.printStackTrace();
			}
		}
		return map;
	}
}
