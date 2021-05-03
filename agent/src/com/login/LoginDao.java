package com.login;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import com.db.DBConnection;

public class LoginDao 
{

	Logger logger=Logger.getLogger(LoginDao.class);
	
	public boolean validateAgent(String agentID,String txnKey) {
		String sql="";
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		boolean status=false;
		try
		{

			con=DBConnection.getConnection();
			con.setAutoCommit(false);
			sql="select agent_id from agent_details where agent_id=? and Tran_key=?";
			pstmt = con.prepareStatement(sql); 
			pstmt.setLong(1, Long.parseLong(agentID));
			pstmt.setString(2,txnKey);
			
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				status=true;
			}

		}catch (Exception ex) {
			logger.info("Exception in TransactionStatusDao class method (getTransactionStatusData)");
			ex.printStackTrace();
			status= false;
		}
		finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				logger.info("Exception in TransactionStatusDao class method (getTransactionStatusData) while closing connection");
				e.printStackTrace();
			}
		}
		return status;
	}
	
	public String getLoginStatus(String userName,String pass, String userType,HttpServletRequest request) 
	{
		HttpSession session=request.getSession();
		String url="error",loginStatus="",blockStatus="",clientBlock="",mdBlockStatus="",DSBlockStatus="";
		String mobVerifyStatus="",Recharge="";

		HashMap<String,String> AgentDetailData=new HashMap<String,String>();
		/*ArrayList<String> DthConnLocation= new ArrayList<String>();
		ArrayList<String> DthConnProduct= new ArrayList<String>();*/
		Connection con = null;
		ResultSet rs = null;
		CallableStatement cstmt = null;
		try
		{

			con=DBConnection.getConnection();

			cstmt = con.prepareCall("{call AgentLoginStatus(?,?,?)}");

			cstmt.setString(1, userName);
			cstmt.setString(2, pass);
			cstmt.setString(3, userType);

			rs = cstmt.executeQuery();

			if(rs.next())
			{	
				AgentDetailData.put("agentID", rs.getString(1));
				session.setAttribute("agentID", rs.getString(1));
				
				loginStatus=rs.getString(2);
				blockStatus=rs.getString(3);
				mobVerifyStatus=rs.getString(4);
				
				clientBlock=rs.getString(27);
				mdBlockStatus=rs.getString(28);
				DSBlockStatus=rs.getString(29);
				
				if(loginStatus.equalsIgnoreCase("Activate")){
					if(blockStatus.equalsIgnoreCase("blocked")
							|| clientBlock.equalsIgnoreCase("Blocked")
							|| mdBlockStatus.equalsIgnoreCase("Blocked")
							|| DSBlockStatus.equalsIgnoreCase("Blocked")){
						url="blockedUser";  
						return url;
					}								
					else{
						
						if(mobVerifyStatus.equalsIgnoreCase("Y")){
							
							AgentDetailData.put("emailID", rs.getString(5));
							AgentDetailData.put("userName",rs.getString(5));
							AgentDetailData.put("agentMobileNo", rs.getString(6));
							AgentDetailData.put("completeAgentID",rs.getString(7));
							
							AgentDetailData.put("distributorID", rs.getString(8));
							AgentDetailData.put("agencyName", rs.getString(9));
							AgentDetailData.put("agent_name", rs.getString(10));
							AgentDetailData.put("address", rs.getString(11));
							AgentDetailData.put("completeDSID",rs.getString(13));
							AgentDetailData.put("fName", rs.getString(14));
							AgentDetailData.put("lName", rs.getString(15));
							AgentDetailData.put("Flights", rs.getString(16));
							AgentDetailData.put("Bus", rs.getString(17));
							AgentDetailData.put("Hotel", rs.getString(18));
							AgentDetailData.put("Recharge", rs.getString(19));
							Recharge=rs.getString(19);
							AgentDetailData.put("DTHX", rs.getString(20));
							AgentDetailData.put("Utility", rs.getString(21));
							AgentDetailData.put("PayCard", rs.getString(22));
							AgentDetailData.put("TestMerit", rs.getString(23));
							AgentDetailData.put("Rail", rs.getString(24));
							AgentDetailData.put("DMR", rs.getString(25));
							AgentDetailData.put("W2W", rs.getString(26));	
							AgentDetailData.put("TxnKey", rs.getString(12));
							AgentDetailData.put("AgentAuthKey", rs.getString(31));
							session.setAttribute("AgentAuthKey", rs.getString(31));	
							session.setAttribute("AgentBalance", rs.getString(30));	
							session.setAttribute("AgentDetailData", AgentDetailData);
							
							session.setAttribute("TxnKey", AgentDetailData.get("TxnKey"));
							session.setAttribute("api_status", rs.getString(32));
							session.setAttribute("call_back", rs.getString(33));
							
							if("Y".equalsIgnoreCase(Recharge)){
								url="Home";
							}else{
								url="agentHome";
							}
							
						}else{
							url="mobVerifyPage";
						}
						
					}
				}else{
					url="deactiveUser";
				}

			}else{
				url="invalidUser";
			}	
			
		}catch (Exception ex) {
			url="error";
			logger.info("Exception in LoginDao class method (getLoginStatus)");
			ex.printStackTrace();
		}
		finally {
			try {
				if (rs != null)
					rs.close();
				if (cstmt != null)
					cstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				logger.info("Exception in LoginDao class method (getLoginStatus) while closing connection");
				e.printStackTrace();
			}
		}
		logger.info(" URL IS "+url);
		return url;

	}
	
	public ArrayList<String> getDthConnectionOperator() 
	{

		ArrayList<String> DthConnProduct=new ArrayList<String>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try
		{

			con=DBConnection.getConnection();
			String selectQuery="Select distinct Product as product from DthConnectionOperator";
		    pstmt=con.prepareStatement(selectQuery);   
			rs=pstmt.executeQuery();
			while(rs.next()){
				DthConnProduct.add(rs.getString(1));  

			}

		}catch(Exception exc)
		{
			exc.printStackTrace();
			logger.info("Exception occurs in LiVE MRA App "+exc);
		}
		finally
		{
			try{
				if(pstmt!=null)
					pstmt.close();
				if(pstmt!=null)
					pstmt.close();
				if(con!=null)
					con.close();
			}catch(Exception exc){

			}
		}
		return DthConnProduct;
	}
	
	
	public String fetchVendor(String service){
		Connection con=null;
		String vendorName="";
		try {
			con=DBConnection.getConnection();
			String sql="select Vendor from Vendor_Management where Service='"+service+"'";
			ResultSet rs=con.createStatement().executeQuery(sql);
			if(rs.next())
				
				vendorName=rs.getString(1);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			
				try {
					if(con!=null)
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return vendorName;
	}
	public ArrayList<String> getDthConnLocation() 
	{

		ArrayList<String> DthConnLocation=new ArrayList<String>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try
		{

			con=DBConnection.getConnection();
			String selectQuery="Select distinct location from DthConnectionOperator";
		    pstmt=con.prepareStatement(selectQuery);   
			rs=pstmt.executeQuery();
			while(rs.next()){
				DthConnLocation.add(rs.getString(1));  

			}

		}catch(Exception exc)
		{
			exc.printStackTrace();
			logger.info("Exception occurs in LiVE MRA App "+exc);
		}
		finally
		{
			try{
				if(pstmt!=null)
					pstmt.close();
				if(pstmt!=null)
					pstmt.close();
				if(con!=null)
					con.close();
			}catch(Exception exc){

			}
		}
		return DthConnLocation;
	}

	final String changePassword(String newpassword, String emailid,String agentID, String varificationCode) {
		String sql="";
		String status="No";
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try
		{

			con=DBConnection.getConnection();
			con.setAutoCommit(false);
			sql="select email_id from agent_details where email_id=? and agent_id!=?";

			pstmt = con.prepareStatement(sql); 
			pstmt.setString(1, emailid); 
			pstmt.setString(2, agentID); 

			rs=pstmt.executeQuery();
			while(rs.next())
			{
				status="yes";
			}
			if(status.equalsIgnoreCase("No"))
			{
				String update="update recharge_e_point_login_info set password=?,varify_code=?,pass_status=?,Verified=? where user_id=?";
				pstmt = con.prepareStatement(update); 
				pstmt.setString(1, newpassword); 
				pstmt.setString(2, varificationCode);
				pstmt.setString(3, "newpass");
				pstmt.setString(4, "N");
				pstmt.setString(5, agentID);

				pstmt.execute();
				update="update agent_details set email_id=? where agent_id=?";
				pstmt = con.prepareStatement(update); 
				pstmt.setString(1, emailid); 
				pstmt.setLong(2, Long.parseLong(agentID));
				pstmt.execute();
				update="update login_details set user_name=?,password=? where user_id=?";
				pstmt = con.prepareStatement(update); 
				pstmt.setString(1, emailid); 
				pstmt.setString(2, newpassword);
				pstmt.setLong(3, Long.parseLong(agentID));
				pstmt.execute();
				con.commit();
			}

		}catch (Exception ex) {
			status="error";
			try {
				con.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			logger.info("Exception in LoginDao class method (changePassword)");
			ex.printStackTrace();
		}
		finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				logger.info("Exception in LoginDao class method (changePassword) while closing connection");
				e.printStackTrace();
			}
		}
		return status;
	}

	final String  getRandomCode() {
		String randomCode="";
		try
		{   
			int n = 8;
			Random randGen = new Random();	    
			int startNum = (int) Math.pow(10, n-1);
			int range = (int) (Math.pow(10, n) - startNum);	    
			int randomNum = randGen.nextInt(range) + startNum;
			randomCode = String.valueOf(randomNum);
		}catch(Exception e)
		{
			logger.info("Exception in LoginDao class (getRandomCode) method ");
			e.printStackTrace();
		}

		return randomCode;
	}

	final String checkMobileVerificationCode(String agentID,String varifyMobileCode) {
		String sql="";
		String status="error";
		String mobileVerifyCode="";
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try
		{

			con=DBConnection.getConnection();
			con.setAutoCommit(false);
			sql="select Mob_Verify_Code from login_details where user_id=?";
			pstmt = con.prepareStatement(sql); 
			pstmt.setString(1, agentID); 

			rs=pstmt.executeQuery();
			while(rs.next())
			{
				mobileVerifyCode=rs.getString(1);
			}
			if(mobileVerifyCode.equalsIgnoreCase(varifyMobileCode))
			{

				String update="update login_details set Mob_verify_status=? where user_id=?";
				pstmt = con.prepareStatement(update); 
				pstmt.setString(1, "Y"); 
				pstmt.setString(2, agentID);
				pstmt.execute();
				con.commit();
				status="success";
			}
			else
			{
				status="fail";
			}

		}catch (Exception ex) {
			status="error";
			try {
				con.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			logger.info("Exception in LoginDao class method (checkMobileVerificationCode)");
			ex.printStackTrace();
		}
		finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				logger.info("Exception in LoginDao class method (checkMobileVerificationCode) while closing connection");
				e.printStackTrace();
			}
		}
		logger.info("status is "+status);
		return status;
	}
}
