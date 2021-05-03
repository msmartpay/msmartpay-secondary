package com.msmart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import com.msmart.dbconnection.DBConnection;

public class CommonServiceDao {

	public String getDMRVendor() 
	{

		Connection con=null;
		Statement pstmt=null;
		ResultSet rst=null;
		String vendor="";
		try
		{
			con=DBConnection.getConnection();
			String sqlQurry="select Vendor from Vendor_Management where service='DMR'";
			System.out.println("sqlQurry : "+sqlQurry);
			pstmt=con.createStatement();
			rst=pstmt.executeQuery(sqlQurry);

			while(rst.next())
			{
				vendor=rst.getString(1);
			}

		}catch(Exception exc)
		{
			exc.printStackTrace();
			System.out.println("UtilityServiceDao.getUpdatedBalance()");
			return null;
		}
		finally
		{
			try{
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
			}catch(Exception exc){
				exc.printStackTrace();
			}
		}
		return vendor;
	}
	
	public String getUpdatedBalance(String agentId) 
	{

		System.out.println("AG ID :>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> "+agentId);
		Connection con=null;
		Statement pstmt=null;
		ResultSet rst=null;
		String bal="";
		try
		{
			con=DBConnection.getConnection();
			String sqlQurry="select (TotCash-usedcash) as avlBal from agent_amount where agent_id="+Long.parseLong(agentId);
			System.out.println("sqlQurry : "+sqlQurry);
			pstmt=con.createStatement();
			rst=pstmt.executeQuery(sqlQurry);

			while(rst.next())
			{
				bal=rst.getString(1);
				DecimalFormat df=new DecimalFormat("#######.##");
				
				bal=df.format(Double.parseDouble(bal))+"";
			}
			System.out.println("getUpdatedBalance()>>>>>>>>>>>>>>>>>>>>>>  Balance : "+bal);

		}catch(Exception exc)
		{
			exc.printStackTrace();
			System.out.println("UtilityServiceDao.getUpdatedBalance()");
			return null;
		}
		finally
		{
			try{
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
			}catch(Exception exc){
				exc.printStackTrace();
			}
		}
		return bal;
	}
	
	
	public String txn_key_Validation(String txn_key,String agent_id)
	{
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rst=null;
		String txn_key_DB=null;
		try
		{
			if("".equalsIgnoreCase(agent_id) || null==agent_id) {
				agent_id="0";
			}

			con=DBConnection.getConnection();
			pstmt=con.prepareStatement("select Tran_key from dbo.agent_details where agent_id=?");
			pstmt.setLong(1,Long.parseLong(agent_id));
			rst=pstmt.executeQuery();
			while(rst.next())
			{
				txn_key_DB=rst.getString(1);
			}

			if(txn_key_DB!=null && txn_key_DB.equals(txn_key))
			{
				return "Y";
			}
			else
			{
				return "N";
			}
		}catch(SQLException ex){
			ex.printStackTrace();
			System.out.println(" inside MRA Live Application  ");
		}
		finally
		{
			try{
				if(con!=null)
				{
					con.close();
					con=null;
				}
				if(pstmt!=null)
				{
					pstmt.close();
					pstmt=null;
				}
				if(rst!=null)
				{
					rst.close();
					rst=null;
				}
			}catch(Exception ex){
				ex.printStackTrace();
				System.out.println(" inside MRA Live Application  ");
			}
		}
		return "N";
	}
	public String tokenValidation(String agent_id,String token) {
		PreparedStatement pstmt=null;
		Connection con = null;
		try {


			String sql="update agent_details set token=? where agent_id=?";
			con=DBConnection.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, token);
			pstmt.setLong(2, Long.parseLong(agent_id));
			System.out.println("sql::"+sql);
			int count=pstmt.executeUpdate();
			
			if(count>0)
			{
				return "Y";
			}
			else{
				return "N";
			}


		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			try {
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public ArrayList<HashMap<String, Object>> getOprator(String service) {

		ArrayList<HashMap<String, Object>> list=new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> object= null;
		Connection con=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		try
		{
			con=DBConnection.getConnection();
			if("all".equalsIgnoreCase(service))
				psmt = con.prepareStatement("select distinct a.Service,a.Operator_Name,a.Display_Name,a.Operator_Code,a.BillFetch from Agent_Recharge_Billpay_Operator_Details a join newagentservice b on a.Main_Service=b.service and b.Active='Y' and b.MD_id=4001 and a.SK_Code is not null order by Service asc");
			else
				psmt = con.prepareStatement("select distinct a.Service,a.Operator_Name,a.Display_Name,a.Operator_Code,a.BillFetch from Agent_Recharge_Billpay_Operator_Details a join newagentservice b on a.Main_Service=b.service and b.Active='Y' and b.MD_id=4001 and a.service='"+service+"' and a.SK_Code is not null order by Service asc");
			
			rs = psmt.executeQuery();

			while(rs.next()){
				object=new HashMap<>();
				object.put("Service", rs.getString(1));
				object.put("OperatorName", rs.getString(2));
				object.put("DisplayName", rs.getString(3));
				object.put("OpCode", rs.getString(4));
				object.put("BillFetch", rs.getString(5));
				
				list.add(object);
			}
		}catch(Exception exc)
		{
			exc.printStackTrace();
			System.out.println("Exception occurs in LiVE MRA App "+exc);
		}
		finally
		{
			try{
				if(rs!=null)
					rs.close();
				if(psmt!=null)
					psmt.close();
				if(con!=null)
					con.close();
			}catch(Exception exc){

			}
		}
		return list;
	}
	
	public static JSONArray getOpertorDetails() 
	{

		JSONArray list=new JSONArray();
		JSONObject map= null;
		Connection con=null;
		Statement pstmt=null;
		ResultSet rst=null;
		try
		{

			con=DBConnection.getConnection();
			String sqlQurry="select Service,Sub_Service,Operator_Name,Display_Name,SK_Code from Agent_Recharge_Billpay_Operator_Details order by Operator_Name";
			pstmt=con.createStatement();
			rst=pstmt.executeQuery(sqlQurry);

			while(rst.next())
			{

				map= new JSONObject();
				map.put("Service", rst.getString(1));
				map.put("SubService", rst.getString(2));
				map.put("OperatorName", rst.getString(3));
				map.put("DisplayName", rst.getString(4));
				map.put("Code", rst.getString(5));
				list.put(map);

			}

		}catch(Exception exc)
		{
			exc.printStackTrace();
		}
		finally
		{
			try{
				if(rst!=null)
					rst.close();
				if(pstmt!=null)
					pstmt.close();
				if(con!=null)
					con.close();
			}catch(Exception exc){

			}
		}
		return list;
	}

	public static int updateATTRequestLog(String tranno,String respString)
	{
		int Status = 0;
		PreparedStatement pst = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			pst = con.prepareStatement("update agent_transaction_details set requestString=? where Transaction_No=?");
			pst.setString(1, respString);
			pst.setString(2, tranno);

			int count = pst.executeUpdate();


		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
			}

		}
		return Status;

	}

	public static int updateATTResponseLog(String tranno,String respString)
	{
		int Status = 0;
		PreparedStatement pst = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			pst = con.prepareStatement("update agent_transaction_details set responseString=? where Transaction_No=?");
			pst.setString(1, respString);
			pst.setString(2, tranno);
			
			int count = pst.executeUpdate();
			

		} catch (Exception e) {
			
			e.printStackTrace();
			
		} finally {

			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
			}

		}
		return Status;

	}

	public int saveLocation(long userId,String data,String type) {
		PreparedStatement pstmt=null;
		Connection con = null;
		int count=0;
		try {


			String sql="insert into location_tracker ( user_id, type, location, datetime) "
					+ "values  (?,?,?,getdate())";
			con=DBConnection.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setLong(1, userId);
			pstmt.setString(2, type);
			pstmt.setString(3, data);
			
			count=pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			
		}finally{
			try {
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return count;
	}
	
}
