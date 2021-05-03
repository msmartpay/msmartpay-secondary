package com.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import com.db.DBConnection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;

import java.util.HashMap;


public class LoginDao {
	public static String updateLoginStatus(String mdId){
		Statement stmt=null;
		ResultSet rs=null;
		Connection con=null;
		String status="";
		try
		{ 
			con=DBConnection.getConnection();
			stmt=con.createStatement();
			String sql="update md_login_details set current_status='loggedout' where user_id="+mdId+"";
			//System.out.println("query is"+"" +sql);
			int x=stmt.executeUpdate(sql);
			if(x>0){
				status="updated";
			}
			else{
				status="notupdated";
			}
		}
		catch(Exception e){
			status="notupdated";
			System.out.println("Exception in LoginDao class, method updateLoginStatus"+e.toString());
		}
		finally
		{
			try
			{				
				if(rs!=null)
					rs.close();
				if(stmt!=null)
					stmt.close();
				if(con!=null)
					con.close();
			}
			catch(SQLException e)
			{
				System.out.println("Exception in LoginDao class, method updateLoginStatus during closing connection"+e.toString());
			}				
		}
		return status;
	}

	public static String getMDBalance(String mdId,HttpServletRequest request){

		HttpSession session=request.getSession(true);
		Statement stmt=null;
		ResultSet rs=null;
		Connection con=null;
		String balance="";
		try
		{	
			con=DBConnection.getConnection();
			stmt=con.createStatement();
			String sql="select convert(decimal(18,2),(Total_Cash-Used_Cash)) as balance from MD_amount where md_id="+mdId+"";
			//System.out.println("query is"+"" +sql);
			rs=stmt.executeQuery(sql);
			while (rs.next())
			{
				balance=rs.getString(1);
			}
			session.setAttribute("balance",balance);		
		}
		catch(Exception e){
			System.out.println("Exception in getMDBalance"+e.toString());
		}
		finally
		{
			try
			{			
				if(rs!=null)
					rs.close();
				if(stmt!=null)
					stmt.close();
				if(con!=null)
					con.close();
			}
			catch(SQLException e)
			{
				System.out.println("Exception in LoginDao class, method getMDBalance during closing connection"+e.toString());
			}			
		}
		return balance;	
	}
	public static String chekLoginStatus(Connection con, String userId,String pass,HttpServletRequest request,String clientId,String usrType) {		
		HttpSession session=request.getSession(true);
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		Statement stmt=null;
		Statement stmt1=null;
		Statement stmt2=null;	
		String url="";
		//String userId="";
		String userName="";
		String userType="";
		String loginStatus="";
		String BlockStatus="";
		int countUser=0;
		String exists="No";
		String mdName="";
		String completeId="";
		String mdId="";
		String client_block_status="";
		double balance=0.00;
		DecimalFormat df = new DecimalFormat("0.00");
		try
		{ 
			con=DBConnection.getConnection();
			stmt=con.createStatement();
			String sql="select user_id, user_name, password, login_status, user_type, Block_status from md_login_details where user_name=? and password=? and user_type=? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, pass);
			pstmt.setString(3, usrType);


			//System.out.println("query is============="+"" +sql);
			rs=pstmt.executeQuery();
			while (rs.next())
			{	
				userId=rs.getString("user_id");
				session.setAttribute("user_id",userId);
				userName=rs.getString("user_name");
				session.setAttribute("name",userName);
				loginStatus=rs.getString("login_status");
				userType=rs.getString("user_type");
				session.setAttribute("user_type",userType);
				BlockStatus=rs.getString("Block_status");
				System.out.println("BlockStatus"+BlockStatus);
				if (BlockStatus==null){
					BlockStatus="";
				}						
				exists="yes";			  
			}
			if(exists.equals("yes"))
			{
				if(loginStatus.equalsIgnoreCase("Activate") || loginStatus.equalsIgnoreCase("PartiallyActive"))
				{
					if(BlockStatus.equalsIgnoreCase("blocked") ){
						url="NotActivateMD";
						session.setAttribute("message","You are not Allowed to Login. Your Account is Blocked.");  
						return url;
					}							
					else {
						String sqlQry="select Block_Status from dbo.white_label_details where client_id='"+clientId+"'";
						rs=stmt.executeQuery(sqlQry);					
						System.out.println("");
						while(rs.next()){
							client_block_status=rs.getString("Block_Status");				
							System.out.println("client_block_status:"+client_block_status);
							if (client_block_status==null){
								client_block_status="";
							}
						}
						if(client_block_status.equalsIgnoreCase("Blocked")){
							url="NotActivateMD";
							session.setAttribute("message","Login Disallowed. Please Contact Your Channel Partner.");
							System.out.println("url");
							return url;						  
						}			
					}	
					System.out.println("url");
					url="mdHome";
					String selectQuery="select b.md_name as name,(b.md_initial+convert(varchar,b.md_id)) as complete_id,b.md_id,c.available_bal_amount as balance  from md_login_details a,md_details b,md_amount c where a.user_id=b.md_id and b.md_id=c.md_id  and a.user_id=?";

					pstmt = con.prepareStatement(selectQuery);
					pstmt.setString(1, userId);

					System.out.println(selectQuery);
					rs1=pstmt.executeQuery();
					while(rs1.next())
					{	
						mdName=rs1.getString("name");
						session.setAttribute("mdName",mdName);
						completeId=rs1.getString("complete_id");
						session.setAttribute("completeId",completeId);
						mdId=rs1.getString("md_id");
						session.setAttribute("mdId",mdId);
						balance = rs1.getDouble("balance");
						session.setAttribute("balance",df.format(balance));
					}				
					stmt1=con.createStatement();
					stmt1.executeUpdate("update md_login_details set login_datetime=GETDATE(), current_status='loggedin' where user_id='"+userId+"'");

					String selectQuery1="select md_login_url,domain_name,company_name,distributor_login_url,Client_Type,Help_Desk_EmailID,Help_Desk_MobileNo,MD_Ticker,Logo_Image from white_label_details  where client_id=?";

					System.out.println(selectQuery1);                 
					pstmt = con.prepareStatement(selectQuery1);
					pstmt.setString(1, clientId);				
					rs2=pstmt.executeQuery();				
					while(rs2.next()){							
						session.setAttribute("md_login_url",rs2.getString(1));
						session.setAttribute("domain_name",rs2.getString(2));
						session.setAttribute("company_name",rs2.getString(3));
						session.setAttribute("distributor_login_url",rs2.getString(4));
						session.setAttribute("client_type10",rs2.getString(5));						
						session.setAttribute("Agent_cell_email_id",rs2.getString(6));
						session.setAttribute("Help_email_id1",rs2.getString(6));
						session.setAttribute("Help_mobileNo1",rs2.getString(7));
						session.setAttribute("md_message",rs2.getString(8));
						session.setAttribute("md_page_title",rs2.getString(3));
						session.setAttribute("md_header_home_image",rs2.getString(9));							
					}                 
				}			
				else
				{
					url="NotActivateMD";
					session.setAttribute("message","Your Account is Not Activated.");
				}
			}
			else
			{
				url="NotActivateMD";
				session.setAttribute("message","Invalid User ID and/or Password. Please Try Again."); 				
			}			
		}
		catch(Exception e)
		{
			System.out.println("Exception in LoginDao class, method checkLoginStatus while checking user type"+e.toString());
		}
		finally
		{
			try
			{
				if(rs!=null)
					rs.close();
				if(rs1!=null)
					rs1.close();
				if(rs2!=null)
					rs2.close();
				if(pstmt!=null)
					pstmt.close();
				if(stmt!=null)
					stmt.close();
				if(stmt1!=null)
					stmt1.close();
				if(stmt2!=null)
					stmt2.close();
				if(con!=null)
					con.close();
			}
			catch(SQLException e)
			{
				System.out.println("Exception in LoginDao class, method checkLoginStatus during closing connection"+e.toString());
			}		
		}
		return url;	
	}
	public static HashMap getDynamicDetails(String ServerName)
	{	

		Statement stmt=null;
		ResultSet rs=null;
		Connection con=null;
		String status="";
		HashMap hm=new HashMap();
		//HttpSession session=request.getSession(true);		
		try
		{			

			System.out.println("ServerName is====================================="+ServerName);
			con=DBConnection.getConnection();
			stmt = con.createStatement();

			String newDomain = ServerName.replaceAll("www.", "");
			//System.out.println(newDomain);


			newDomain = (new StringBuilder("www.")).append(newDomain).toString();

			String selectQuery = (new StringBuilder("select Logo_Image,Company_name,Help_Desk_MobileNo,Help_Desk_EmailID,user_type,a.client_id,MD_Ticker,flag,powered_by from white_label_details a where domain_name='")).append(newDomain).append("'").toString();
			System.out.println(selectQuery);

			rs=stmt.executeQuery(selectQuery);			
			while(rs.next())
			{			
				hm.put("md_header_home_image",rs.getString(1));
				hm.put("md_page_title",rs.getString(2));
				//hm.put("md_footer_copyright",rs.getString(3));
				hm.put("Help_mobileNo1",rs.getString(3));
				hm.put("Help_email_id1",rs.getString(4));
				hm.put("user_type",rs.getString(5));
				hm.put("clientId",rs.getString(6));
				hm.put("md_message",rs.getString(7));
				hm.put("flag",rs.getString(8));
				hm.put("poweredBy",rs.getString(9));		
				//System.out.println("--------"+hm+"-----");
			}     		
		}catch(Exception E)
		{
			status="notupdate";
			System.out.println("Exception in getDynamicDetails======"+E.toString());
		}
		finally
		{
			try
			{
				if(rs!=null)
					rs.close();
				if(stmt!=null)
					stmt.close();
				if(con!=null)
					con.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in getDynamicDetails while closing connection"+e.toString());
			}		
		}
		return hm;
	}

}
