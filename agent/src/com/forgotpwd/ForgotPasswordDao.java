package com.forgotpwd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.log4j.Logger;

import com.common.SmsSendDao;
import com.db.DBConnection;
import com.utility.SendSmtpMail;

/**
 *  Created By 	: Arshad
 *  Created Date : 05/03/2013
 */
final class ForgotPasswordDao {

	Logger logger=Logger.getLogger(ForgotPasswordDao.class);
	final String sendPassword(String serchId, String helpMailID,String newPass) {
		String email="",agentId="",mobile="";
		String status="invalid";
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try
		{  
			
			con=DBConnection.getConnection();
			String sql="select agent_id,email_id,mobile_no from agent_details where mobile_no=? or email_id=?";
			//logger.info(sql);
			pstmt = con.prepareStatement(sql); 
			pstmt.setString(1, serchId);
			pstmt.setString(2, serchId);
			rs=pstmt.executeQuery();
			if(rs.next())
			{
				agentId=rs.getString(1);
				email=rs.getString(2);
				mobile=rs.getString(3);
						
				sql="update login_details set password=? where user_id=?";
				//logger.info(sql);
				pstmt = con.prepareStatement(sql); 
				pstmt.setString(1, newPass); 
				pstmt.setLong(2, Long.parseLong(agentId)); 
				int count=pstmt.executeUpdate();
				if(count>0)
				{
					if(email!=null && email.length()>0){
						String recipients[]={email};  
						String subject="Your Account Information";
			
						String Message="<html><BODY cellpadding=\"0\" cellspacing=\"0\"><table border=\"0\"  width=\"100%\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" hspace=\"0\" vspace=\"0\"  bordercolor=\"#FFFFFF\"><tr><td><table height=\"200\" ><tr><td style='font-family:\"Trebuchet MS\";font-size:14px;'>Dear &nbsp;Agent,</td></tr>"+
						
						"<p><tr><td>"+
						"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td>&nbsp;<br></td></tr><tr><td style='font-family:\"Trebuchet MS\";font-size:14px;'>We have received a password request from you.Your Account Information is as follows:<br></tr></td>"+
			            "<tr><td>&nbsp;<br></td></tr>"+
						"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;'>Username:&nbsp;<font>"+mobile+"</font><br></td></tr>"+
						"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;'>Password:&nbsp;<font>"+newPass+"<br></td></tr><tr><td>&nbsp;<br></td></tr>"+
						"</table></td></tr></p>"+						
						"<tr><td ><p style='font-family:\"Trebuchet MS\";font-size:14px;'>Regards</p></td></tr>"+
						"<tr><td ><p style='font-family:\"Trebuchet MS\";font-size:14px;'>Team Activation</p></td></tr><br>"+
						"</table></td></tr>"+
						"</tr></td></table></BODY></html>";
	  
						SendSmtpMail.sendSSLMessage(recipients, subject, Message,helpMailID);
					}
					if(mobile!=null && mobile.length()>0){
						String message="Dear Retailer, Your login Username is "+mobile+" and Password is "+newPass+", Please change your APIN and delete this SMS for security of your account.";
						SmsSendDao.sendMobileSms(mobile,message);
					}
					
					status="valid";
						 			      
				}
			}
			else {
				status="invalid";
			}

		}catch (Exception ex) {
			logger.info("Exception in ForgotPasswordDao class method (sendPassword)");
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
				logger.info("Exception in ForgotPasswordDao class method (sendPassword) while closing connection");
				e.printStackTrace();
			}
		}
		return status;
	}

}
