
/*
 Class Property :  This class (PasswordAction) is created to get request data of transaction details and process it accordingly.

  Created Date   : 4 january-2012 at 11.00 AM.
  Created By     : Amit Pathak

  Updated Date   : 3 january-2012
  Update By      :Amit Pathak

 */

package com.Password;

import java.util.HashMap;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import com.common.LogWriter;
import com.common.SendSmsOnMobile;
import com.common.SendSmtpMail;
import com.login.LoginDao;

public class PasswordAction 
{

	private  PasswordFormBean PasswordFormBean=new PasswordFormBean();
	public Object getModel(){return PasswordFormBean;}
	Logger logger = Logger.getLogger(PasswordAction.class);
	LogWriter log=new LogWriter();	 

	public JSONObject changePassword(JSONObject requestObject) {

		JSONObject responseJson=new JSONObject();

		String statusCode="1",message="0";

		try 
		{ 
			String param=requestObject.getString("param");		
			log.print("param value in passwordAction is "+param, logger);
			PasswordDao PasswordDao=new PasswordDao();


			if(param.equals("changePassword")){

				String userId=requestObject.getString("distributorId");
				String txnkey=requestObject.getString("txnkey");
				if(txnkey!=null){
					
					LoginDao dao=new LoginDao();
					if(dao.varifyTxnKey(Long.parseLong(userId), txnkey)){
						
						String oldPassword=requestObject.getString("OldPassword");
						System.out.println("oldPassword--"+oldPassword);
						String NewPassword=requestObject.getString("NewPassword");
						System.out.println("NewPassword--"+NewPassword);
						String clientType=requestObject.getString("clientType"); 
						System.out.println("clientType----->>>"+clientType);


						//----------------------Call to get original password of user-------------------------

						String CheckOldPassword=PasswordDao.CheckOldPassword(userId,clientType);
						if(CheckOldPassword.equalsIgnoreCase(oldPassword))
						{
							String  changepassword=PasswordDao.changePassword(userId,NewPassword,clientType);
							message="Password Has Been change Sucessfully.";
							responseJson.put("message",message);
							responseJson.put("status", "0");
							return responseJson;
						}
						else
						{
							message="Old Password Is Not Correct";
							responseJson.put("message", message);
							responseJson.put("status", "1");
							return responseJson;
						}
						
					}else{
						responseJson.put("message", "Invalid request.");
						responseJson.put("status", "1");
					}
					
					
				}else{
					
					
					responseJson.put("message", "Invalid request.");
					responseJson.put("status", "1");
				}
				
				

			}

		}catch(Exception ex){
			ex.printStackTrace();
			responseJson.put("message", "Due to some technical issue we are unable to proceed your request");
			responseJson.put ("status",statusCode);
		}
		return responseJson;

	}

	//--------------------------------Block for Reset Password-------------------//

	public JSONObject resetPassword(JSONObject requestObject) {

		JSONObject responseJson=new JSONObject();

		String statusCode="1",message="";
		try{
			String param=requestObject.getString("param");
			if(param.equals("ResetPassword"))

			{
				PasswordDao passDao=new PasswordDao();
				String userId=requestObject.getString("distributorId");
				if(userId==null){
					message="Your session has been expire ,relogin to continue.";
					responseJson.put("Loginmessage",message);
					responseJson.put("Loginmessage",statusCode);
					return responseJson; 
				}
				// String AgentCompleteId=(String)request.getParameter("AgentCompleteId");
				// System.out.println("AgentCompleteId--"+AgentCompleteId);
				String AgentName=requestObject.getString("AgentName");
				// System.out.println("AgentName--"+AgentName);

				String agenEmailId=requestObject.getString("AgenEmailId"); 
				//System.out.println("agenEmailId----->>>"+agenEmailId);
				String agentOnlyId=requestObject.getString("AgentOnlyId");; 
				//System.out.println("agentOnlyId----->>>"+agentOnlyId);
				String mobileNo=requestObject.getString("MobileNo"); 
				//System.out.println("mobileNo----->>>"+mobileNo);
				String clientType=requestObject.getString("clientType"); 
				//System.out.println("clientType----->>>"+clientType);
				//---------------------call to get password----------------------------------------------------	  
				String  password=passDao.getRandomString(10);
				//System.out.println("password----"+password);
				String domainName=requestObject.getString("domainName"); 
				//System.out.println("domainName----->>>"+domainName);
				String DistributorMailId=requestObject.getString("DistributorMailId"); 
				//System.out.println("DistributorMailId----->>>"+DistributorMailId);

				String resetpassword=passDao.setPassword(password,agentOnlyId,clientType);
				String WhitelableCompanyName=requestObject.getString("WhitelableCompanyName");

				//System.out.println("WhitelableCompanyName-------------"+WhitelableCompanyName);
				if(resetpassword.equalsIgnoreCase("reset"))
				{
					String recipients[]={agenEmailId};
					String subject="Password Reset for Your "+WhitelableCompanyName+" Account";
					String Message="<html xmlns=\"http://www.w3.org/1999/xhtml\">"+
					"<head>"+
					"<style type=\"text/css\">"+
					"td.border "+
					"{"+
					"border-width:1px;"+
					"border-color: 	#be9b61;"+
					"border-style:solid;"+
					"}"+
					"</style>"+
					"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\" />"+

					"</head><body>"+
					"<table>"+
					"<p align=justify>Dear "+AgentName+",</p><p align=justify>Your Password has been reset for your "+WhitelableCompanyName+" account. Password is "+password+"."+ 
					". </p><p align=justify>"+
					"</div>"+
					"<p style=\"font-family:Trebuchet MS\">Kindly change your password for your security purpose<br />"+
					"</p>"+
					"<p style=\"font-family:Trebuchet MS\">Warm regards,<br />"+
					"Team Activation<br />"+
					"<a href="+domainName+">"+WhitelableCompanyName+"</a> "+
					"</p></td>"+
					"</tr></table>"+
					"</center>"+
					"</body></html>";

					SendSmtpMail.sendSSLMessage(recipients,subject,Message,DistributorMailId);
					String testmessage="Dear Agent, your password just reset and new password is "+password+". Kindly change your password for your security purpose.";
					testmessage=testmessage.replaceAll(" ", "%20"); 
					SendSmsOnMobile SendSmsOnMobile=new SendSmsOnMobile();
					SendSmsOnMobile.sendMobileSmsSMSZONE(mobileNo,testmessage);

					responseJson.put("message", "Your password has been changed successfully.");
					responseJson.put ("status","0");
					return responseJson; 

				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
			responseJson.put("message", "Due to some technical issue we are unable to proceed your request.");
			responseJson.put ("status",statusCode);
		}
		return responseJson;

	}


	//----------------------Call to forget password of user-------------------------//

	public JSONObject forgetPassword(JSONObject requestObject) {

		JSONObject responseJson=new JSONObject();

		try 
		{

			PasswordDao passDao=new PasswordDao();
			String param=PasswordFormBean.getParam();

			log.print("param value in LoginAction is "+param, logger);


			String userid=requestObject.getString("userName");
			String userType=requestObject.getString("userType");

			logger.info(userid+ "........."+userType);


			HashMap<String,Object> hm=passDao.forgetPassword(userid,userType);
			String status=(String)hm.get("status");

			System.out.println("Status is"+" "+status);
			if(status.equals("invalid"))
			{
				String msg="Wrong E-mail id or Mobile Number, Please enter your registered e-mail id or Mobile Number.";
				responseJson.put("message",msg);
				responseJson.put("status", "1");
			}
			else
			{
				String userId=(String)hm.get("distributorId");
				String userIdInitial=(String)hm.get("distributorInitial");
				String fullId=userIdInitial+userId;
				String pwd=(String)hm.get("password");
				String mobileNo=(String)hm.get("mobileNo");
				String emailId=(String)hm.get("emailId");
				String recipients[]={emailId};  //email entered by client

				String subject="Account information";
				String Message="<html><BODY cellpadding=\"0\" cellspacing=\"0\"><table border=\"0\"  width=\"100%\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" hspace=\"0\" vspace=\"0\"  bordercolor=\"#FFFFFF\"><tr><td><table height=\"200\" ><tr><td >Dear &nbsp;Sir,</td></tr><br>"+
				"<tr><td><p> We have received a password request and your password is&nbsp;<b>"+pwd+"</b>. Please change it in next login. </p> </td></tr><br>"+
				"<p><tr><td>"+
				"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td>&nbsp;&nbsp;&nbsp;&nbsp;Use the following username and password to login<br></tr></td>"+
				"<tr><td>&nbsp;<br></td></tr>"+
				"<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Username:&nbsp;<font color=\"green\" >"+userid+"</font><br></td></tr>"+
				"<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Password:&nbsp;<font color=\"green\" >"+pwd+"<br></td></tr>"+
				"</table></td></tr></p><br>"+

				"<td><tr><p>Thanks for your cooperation.</p></td></tr><br>"+
				"<tr><td ><p> Keep writing us to make it more users friendly.</p><br></td></tr>"+

				"<tr><td ><p>Regards</p></td></tr><br>"+
				"</tr></td></table></BODY></html>";

				String testmessage="Dear Agent, your password just reset and new password is "+pwd+". Kindly change your password for your security purpose.";
				testmessage=testmessage.replaceAll(" ", "%20"); 

				if(userid.length()==10){
					SendSmsOnMobile SendSmsOnMobile=new SendSmsOnMobile();
					SendSmsOnMobile.sendMobileSmsSMSZONE(mobileNo,testmessage);

				}

				SendSmtpMail.sendSSLMessage(recipients, subject, Message,"support@smartkinda.com");
				String msg="Dear Sir, we have sent your password on you registered email address and mobile number.";
				responseJson.put("message",msg);
				responseJson.put("status", "0");

			}




		}catch(Exception ex){
			ex.printStackTrace();
			responseJson.put("message", "Due to some technical issue we are unable to proceed your request");
			responseJson.put ("status","1");
		}
		return responseJson;

	}
}

