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
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.common.CommonDetailDao;
import com.common.LogWriter;
import com.common.SendSmsOnMobile;
import com.common.SendSmtpMail;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class LoginAction extends ActionSupport implements ModelDriven<Object>,ServletRequestAware,ServletResponseAware  {

	Logger logger = Logger.getLogger(LoginAction.class);
	LogWriter log=new LogWriter();	 
	private HttpServletRequest request; 
	private  LoginForm Loginform=new LoginForm();
	public Object getModel()
	{
		return Loginform;
	}    

	@SuppressWarnings("unchecked")
	public String execute() throws Exception { 
		try 
		{
			Map session = ActionContext.getContext().getSession();
			LoginDao Logindao=new LoginDao();


			String param=Loginform.getParam();

			log.print("param value in LoginAction is "+param, logger);
			if(param.equals("ForgetPassword"))
			{
				return "ForgetPassword";
			}
			if(param.equalsIgnoreCase("login")){

				String userid=Loginform.getLoginId();
				session.put("DistributorMailId",userid);
				String password=Loginform.getPassword();
				session.put("password",password);
				String userType=Loginform.getType();
				System.out.println(userid+ "......"+password+"........."+userType);

				session.put("userType",userType);

				String userStatus=(String)Logindao.checkUserLoginDetails(userid,password,userType,session);


				if(userStatus.equalsIgnoreCase("invalid")){
					String message="Invalid User ID and/or Password. Please Try Again.";

					request.setAttribute("Loginmessage",message);
					return "invaliduser";
				}
				if(userStatus.equalsIgnoreCase("Deactivate")){
					String message="Your Account is Not Activated.";

					request.setAttribute("Loginmessage",message);
					return "invaliduser";
				}
				if(userStatus.equalsIgnoreCase("Deactive")){

					String message="Your Account is Not Activated.";
					request.setAttribute("Loginmessage",message);
					return "Deactiveuser";

				}
				if(param.equals("VarifyEmailcode"))
				{
					log.print("inside distributor panel Varifycode Mobile",logger);
					String userId=Loginform.getUserId();
					String VerifiedCode=Loginform.getMobileVerifiedCode();
					String status=(String)Logindao.verifyMobile(userId,VerifiedCode);
					if(status.equals("invalidcode")){
						request.setAttribute("mobilecodemessage", "Sorry! You have entered the wrong code. Please login again to follow the same process.");
						session.put("distribuorID",userId);
						return "verifyMobileCode";
					}
					String message="Congrats! Your Account has been verified.";

					request.setAttribute("Loginmessage",message);
					return "Accountverified";

				}
				if(userStatus.equalsIgnoreCase("Activate")){		
					HashMap DisDetailsmapInfo=(HashMap)Logindao.checkUserProfileStatus(userid,password,userType);
					String profileStatus=(String)DisDetailsmapInfo.get("Status");
					System.out.println(profileStatus );

					if(profileStatus.equalsIgnoreCase("email")){
						String varifyCode=Logindao.getRandomCode();	
						String	Email_id=Logindao.updatePassGetEmailid(userid,varifyCode);
						String distributorId=(String)DisDetailsmapInfo.get("distributorId");
						System.out.println("Email_id id ::::"+Email_id);
						System.out.println("varifyCode id ::::"+varifyCode);
						request.setAttribute("DSId", distributorId);

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
						
						
						String messagepass="";
						request.setAttribute("message", messagepass);
						
						HashMap<String,String> mailerMap=CommonDetailDao.getDynamicMailerDetails(distributorId);
						
						String mailerId=mailerMap.get("Mailer_id");
						String mailerPassword=mailerMap.get("Mailer_password");
						String smsId=mailerMap.get("SMS_id");
						String smsPassword=mailerMap.get("SMS_password");
						
						SendSmtpMail.sendSSLMessage(recipients, subject, Message,helpEmailId,mailerId,mailerPassword);

						return "email";
					}
					

					if(profileStatus.equalsIgnoreCase("login")){

						session.put("odpass",DisDetailsmapInfo.get("odpass"));
						session.put("distributorId",DisDetailsmapInfo.get("distributorId"));
						session.put("distributorInitial",DisDetailsmapInfo.get("distributorInitial"));
						session.put("companyName",DisDetailsmapInfo.get("companyName"));
						session.put("clientId",DisDetailsmapInfo.get("clientId"));
						session.put("loginUrl",DisDetailsmapInfo.get("loginUrl"));
						session.put("innerHeaderImage",DisDetailsmapInfo.get("innerHeaderImage"));
						session.put("helpEmailId",DisDetailsmapInfo.get("helpEmailId"));
						session.put("helpMobileNo",DisDetailsmapInfo.get("helpMobileNo"));
						session.put("titlePage",DisDetailsmapInfo.get("titlePage"));
						session.put("copyRight",DisDetailsmapInfo.get("copyRight"));
						session.put("poweredBy",DisDetailsmapInfo.get("poweredBy"));
						session.put("panelType",DisDetailsmapInfo.get("panelType"));
						session.put("totalCash",DisDetailsmapInfo.get("totalCash"));
						session.put("usedCash",DisDetailsmapInfo.get("usedCash"));
						session.put("cutoffAamount",DisDetailsmapInfo.get("cutoffAamount"));
						session.put("domainName",DisDetailsmapInfo.get("domainName"));
						session.put("mdID",DisDetailsmapInfo.get("mdId"));
						session.put("BalanceDesk",DisDetailsmapInfo.get("BalanceDesk"));
						session.put("WhitelableCompanyName",DisDetailsmapInfo.get("WhitelableCompanyName"));
						session.put("DistributorName",DisDetailsmapInfo.get("DistributorName"));
						session.put("MobileNo",DisDetailsmapInfo.get("MobileNo"));
						session.put("agentLoginUrl",DisDetailsmapInfo.get("agentLoginUrl"));
						session.put("agentCellEmailId",DisDetailsmapInfo.get("agentCellEmailId"));
						session.put("TickerMessage",DisDetailsmapInfo.get("TickerMessage"));

						return "login";
					}
				}
				if(userStatus.equalsIgnoreCase("Blocked")){
					String message="You are not Allowed to Login. Your Account is Blocked.";

					request.setAttribute("Loginmessage",message);
					return "error";

				}
				if(userStatus.equalsIgnoreCase("blockedUpperHirerarchy")){
					String message="Login Disallowed. Please Contact Your Channel Partner.";

					request.setAttribute("Loginmessage",message);
					return "error";

				}
			}  
			/*if(param.equalsIgnoreCase("verifyEmailId"))
	    {
		  String loginID=Loginform.getLoginId();
		  String userId=Loginform.getUserId();
		  String userType=(String)session.get("userType");
		  if(userType== null){
			 return "sessionexpired"; 
		     }
		  String checkEmailid=(String)Logindao.checkEmailID(loginID,userId,userType);
		  if(checkEmailid.equalsIgnoreCase("exit"))
		  {
			  //String message="Email Id   "+loginID+"  already Exists.Please use other  email Id ";
			  String message=" Email ID is Duplicate.";
			  request.setAttribute("AccountVerifymessage", message);
			  return "email";  
		  }
		  String password=Loginform.getPassword();
		  String varificationcode=Logindao.getRandomCode();
		  Logindao.updatePassEmailid(loginID,userId,varificationcode,password);

		  String helpMobileNo=(String) session.get("helpMobileNo");
		  String loginUrl=(String) session.get("loginUrl");
		  String innerHeaderImage=(String) session.get("innerHeaderImage");
		  String domainName=(String) session.get("domainName");
		  String helpEmailId=(String) session.get("helpEmailId");

		  String recipients[]={loginID};  

		  String subject="E-mail id and password changed verification";
		  String Message="<html xmlns=\"http://www.w3.org/1999/xhtml\">"+
			"<head>"+
			"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\" />"+
			"<title>E-mail verification</title>"+
			"<link href=\""+loginUrl+"/css/travel.css\" rel=\"stylesheet\" type=\"text/css\" />"+
			"</head><body>"+
			"<table width=70% border=0 align=center cellpadding=0 cellspacing=0 class=maintext>"+
			"<tr><td><div align=right><img src=images/"+innerHeaderImage+" width=195 height=87 border=0 /></div></td></tr>"+

			"<tr><td>"+
				"<div align=justify><table width=100% height=\"100\" border=0 cellspacing=10 cellpadding=0 class=maintext>"+
			"<tr bgcolor=#EFF3F6><td width=\"300\"  bgcolor=#EFF3F6 class=textnormal>Your password has been changed successfully you can login only after verify the link given below .</td></tr>"+
			"<tr bgcolor=#EFF3F6><td bgcolor=#EFF3F6 class=textnormal ><A HREF=\""+loginUrl+"/doLogin.action?userId="+userId+"&param=verifyEmailCode\">Click here to verify your agent account.</A></td></tr>"+

				"<tr bgcolor=#EFF3F6><td bgcolor=#EFF3F6 class=textnormal>Varification&nbsp;code&nbsp;:&nbsp;"+varificationcode+"</td>"+
			  "</tr>"+



              "</TBODY>"+
		     " </TABLE></td>"+
			  "</tr>"+
			"<tr>"+
			  "<td>&nbsp;</td>"+
			 " <td>&nbsp;</td>"+
			 " <td>&nbsp;</td>"+
			  "</tr>"+
			"</table></div><p align=justify>&nbsp;</p>" +
			"<p align=justify>Warm regards,</p><p align=justify>Team Activation</p><p align=justify>"+domainName+"</p>" +
			"<p></p><p></p></td></tr></table>";
		  SendSmtpMail.sendSSLMessage(recipients, subject, Message,helpEmailId);
		  String messagepass="Password Updated Successfully.";
		  request.setAttribute("changepasmessage",messagepass);

	      return "changepass";                
	    }*/
			/*  if(param.equals("verifyEmailCode"))
	    {
		   String userId=Loginform.getUserId();
		   session.put("distribuorID",userId);
		   return "verifyEmailCode";

	    }*/

			/*  if(param.equals("VarifycodeEmail"))
	    {
	       log.print("inside distributor panel VarifycodeEmail",logger);
		   String userId=Loginform.getUserId();
		   String VerifiedCode=Loginform.getEmailVerifiedCode();
		   HashMap mapInfo=(HashMap)Logindao.verifyEmail(userId,VerifiedCode);
		   String status=(String)mapInfo.get("Status");
		   if(status.equals("invalidcode")){
			   session.put("distribuorID",userId);
				  String message="The code you enter is not valid ,Plz try again with valid code";

			   request.setAttribute("emailcodemessage", message);
			   return "verifyEmailCode";
		     }
		   String  mobilestatus=(String)mapInfo.get("mobileStatus");

		   if(mobilestatus.equalsIgnoreCase("N")){

		   String mobilenumber=(String)mapInfo.get("mobilenumber");
		   SendSmsOnMobile SendSmsOnMobile=new SendSmsOnMobile();
		   String message="Dear User,Your mobile authentication code for distributor panel is"+status;
		   log.print(message,logger);
		   SendSmsOnMobile.sendMobileSmsSMSZONE(mobilenumber,message);
           SendSmsOnMobile.sendMobileSmsACL(mobilenumber,message);
           request.setAttribute("Loginmessage", "The code you enter is not valid ,Plz try again with valid code");
           session.put("distribuorID",userId);
		   return "verifyMobileCode";
		   }
			  String message="Your Account has been Verified Successfully";

		   request.setAttribute("Loginmessage",message);
		   return "Accountverified";

	    }*/

			if(param.equals("VarifycodeMobile"))
			{
				log.print("inside distributor panel Varifycode Mobile",logger);
				String userId=Loginform.getUserId();
				String EmailVerifiedCode=Loginform.getEmailVerifiedCode();
				System.out.println( "EmailVerifiedCode :L "+ EmailVerifiedCode);
				System.out.println("userId  : "+userId);
				String status=(String)Logindao.verifyMobile(userId,EmailVerifiedCode);
				if(status.equals("invalidcode")){
					request.setAttribute("Loginmessage", "The code you enter is not valid ,Plz try again with valid code.");
					session.put("distribuorID",userId);
					return "email";
				}
				String message="Your Account has been Verified Successfully";

				request.setAttribute("Loginmessage",message);
				return "login";

			}


		}
		catch(Exception ex){
			ex.printStackTrace();
			request.setAttribute("Loginmessage", "Process aborted due to Technical Failure.");
			return "error";
		}
		return "error";

	}

	@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
	}


	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub

	}


}
