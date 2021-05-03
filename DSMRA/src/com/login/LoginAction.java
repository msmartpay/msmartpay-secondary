/*
  Class Property :  This class (LoginAction) is created for controlling user login system.

  Created Date   : 11-Nov-2011 at 9:40 PM.
  Created By     : Bharatveer gh.
Sin
  Updated Date   : 11-Nov-2011.
  Update By      : Bharatveer Singh

 */

package com.login;

import java.util.HashMap;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;

import com.Password.PasswordDao;
import com.common.LogWriter;
import com.common.SendSmtpMail;
import net.sf.json.JSONObject;

public class LoginAction{

	Logger logger = Logger.getLogger(LoginAction.class);
	LogWriter log=new LogWriter();	 

	public JSONObject dsLogin(JSONObject requestObject) {

		JSONObject responseJson=new JSONObject();

		String statusCode="1",message="";


		try 
		{
			LoginDao Logindao=new LoginDao();


			String param=requestObject.getString("param");

			log.print("param value in LoginAction is "+param, logger);
			if(param.equalsIgnoreCase("login")){

				String userid=requestObject.getString("userName");
				String password=requestObject.getString("password");
				String userType=requestObject.getString("userType");

				logger.info(userid+ "......"+password+"........."+userType);


				String userStatus=Logindao.checkUserLoginDetails(userid,password,userType);


				if(userStatus.equalsIgnoreCase("invalid")){
					message="Invalid User ID and/or Password. Please Try Again.";
				}
				else if(userStatus.equalsIgnoreCase("Deactivate")){
					message="Your Account is Not Activated.";
				}
				else if(userStatus.equalsIgnoreCase("Deactive")){
					message="Your Account is Not Activated.";

				}else if(userStatus.equalsIgnoreCase("Blocked")){
					message="You are not Allowed to Login. Your Account is Blocked.";

				}
				else if(userStatus.equalsIgnoreCase("blockedUpperHirerarchy")){
					message="Login Disallowed. Please Contact Your Channel Partner.";

				}else if(userStatus.equalsIgnoreCase("Activate")){		

					HashMap<String,Object> DisDetailsmapInfo=(HashMap<String,Object>)Logindao.checkUserProfileStatus(userid,password);
					String profileStatus=(String)DisDetailsmapInfo.get("Status");
					System.out.println(profileStatus );

					if(profileStatus.equalsIgnoreCase("email")){
						String varifyCode=Logindao.getRandomCode();	
						String	Email_id=Logindao.updatePassGetEmailid(userid,varifyCode);


						String recipients[]={Email_id};  //emailid

						String helpEmailId=  (String) DisDetailsmapInfo.get("agentCellEmailId");


						String subject="User Verification Mail";	
						String Message="<html xmlns=\"http://www.w3.org/1999/xhtml\">"+
						"<head>"+
						"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\" />"+
						"<title>Untitled Document</title>"+
						"</head><body>"+
						"<table width=100% border=0 align=center style=\"font-family:'Trebuchet MS';\" cellpadding=0 cellspacing=0 class=maintext>"+

						"<tr><td>"+
						"<div align=justify><table width=100% height=\"100\" style=\"font-family:'Trebuchet MS';\" border=0 cellspacing=10 cellpadding=0 class=maintext>"+
						"<tr><td align='left' >Dear User,</td></tr>"+
						"<tr>"+
						"<td>&nbsp;</td>"+
						"</tr>"+	
						"<tr><td width=\"300\">Your account has been created.Your Email Verification Code is : "+varifyCode+"</td>"+
						"</tr>"+
						"<tr>"+
						" <td>Please enter the above verification code to verify your account.</td>"+
						"</tr>"+

						"</TBODY>"+
						" </TABLE></td>"+
						"</tr>"+

						"</table></div><p align=justify>&nbsp;</p>" +
						"<p style=\"font-family:'Trebuchet MS';\" align=justify>Regards,</p>"+
						"<p style=\"font-family:'Trebuchet MS';\" align=justify>Team Activation</p>" +
						"<p></p></td></tr></table>";

						SendSmtpMail.sendSSLMessage(recipients, subject, Message,helpEmailId);

					}
					else if(profileStatus.equalsIgnoreCase("login")){

						String txnKey=RandomStringUtils.randomAlphanumeric(80);
						Logindao.updateTxnKey(Long.parseLong(String.valueOf(DisDetailsmapInfo.get("distributorId"))), txnKey);
						
						responseJson.put("distributorId",DisDetailsmapInfo.get("distributorId"));
						responseJson.put("distributorInitial",DisDetailsmapInfo.get("distributorInitial"));
						responseJson.put("companyName",DisDetailsmapInfo.get("companyName"));
						responseJson.put("clientId",DisDetailsmapInfo.get("clientId"));
						responseJson.put("loginUrl",DisDetailsmapInfo.get("loginUrl"));
						responseJson.put("innerHeaderImage",DisDetailsmapInfo.get("innerHeaderImage"));
						responseJson.put("helpEmailId",DisDetailsmapInfo.get("helpEmailId"));
						responseJson.put("helpMobileNo",DisDetailsmapInfo.get("helpMobileNo"));
						responseJson.put("titlePage",DisDetailsmapInfo.get("titlePage"));
						responseJson.put("copyRight",DisDetailsmapInfo.get("copyRight"));
						responseJson.put("poweredBy",DisDetailsmapInfo.get("poweredBy"));
						responseJson.put("panelType",DisDetailsmapInfo.get("panelType"));
						responseJson.put("balance",DisDetailsmapInfo.get("balance"));
						responseJson.put("cutoffAamount",DisDetailsmapInfo.get("cutoffAamount"));
						responseJson.put("domainName",DisDetailsmapInfo.get("domainName"));
						responseJson.put("mdID",DisDetailsmapInfo.get("mdId"));
						responseJson.put("BalanceDesk",DisDetailsmapInfo.get("BalanceDesk"));
						responseJson.put("WhitelableCompanyName",DisDetailsmapInfo.get("WhitelableCompanyName"));
						responseJson.put("DistributorName",DisDetailsmapInfo.get("DistributorName"));
						responseJson.put("MobileNo",DisDetailsmapInfo.get("MobileNo"));
						responseJson.put("agentLoginUrl",DisDetailsmapInfo.get("agentLoginUrl"));
						responseJson.put("agentCellEmailId",DisDetailsmapInfo.get("agentCellEmailId"));
						responseJson.put("TickerMessage",DisDetailsmapInfo.get("TickerMessage"));
						responseJson.put("txnKey",txnKey);

						statusCode="0";
						message="Login Success";

					}else {
						message="We are unable to process your request.";
					}
				}else{
					message="We are unable to process your request.";
				}

				responseJson.put("message", message);
				responseJson.put("status", statusCode);
				return responseJson;
			}  

			else if(param.equals("VarifyEmailcode"))
			{
				log.print("inside distributor panel Varifycode Mobile",logger);
				String userId=requestObject.getString("userId");
				String VerifiedCode=requestObject.getString("verifyCode");
				String status=(String)Logindao.verifyMobile(userId,VerifiedCode);
				if(status.equals("invalidcode")){
					message= "Sorry! You have entered the wrong code. Please login again to follow the same process.";
				}else{
					message="Congrats! Your Account has been verified.";
					statusCode="0";	
				}

				responseJson.put("message", message);
				responseJson.put("status", statusCode);
				return responseJson;
			}

			else if(param.equals("VarifycodeMobile"))
			{
				log.print("inside distributor panel Varifycode Mobile",logger);
				String userId=requestObject.getString("userId");
				String EmailVerifiedCode=requestObject.getString("emailVerifyCode");
				System.out.println( "EmailVerifiedCode :L "+ EmailVerifiedCode);
				System.out.println("userId  : "+userId);
				String status=(String)Logindao.verifyMobile(userId,EmailVerifiedCode);
				if(status.equals("invalidcode")){
					message ="The code you enter is not valid ,Plz try again with valid code.";
				}else{
					message="Your Account has been Verified Successfully";
					statusCode="0";	
				}
				responseJson.put("message", message);
				responseJson.put("status", statusCode);
				return responseJson;
			}


		}
		catch(Exception ex){
			ex.printStackTrace();
			responseJson.put("message", "Process aborted due to Technical Failure.");
			responseJson.put("status", "1");
		}
		return responseJson;

	}
}
