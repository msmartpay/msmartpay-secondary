package com.login;

import java.sql.Connection;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.common.SendSmtpMail;

public class LoginInfoAction extends Action {

	@SuppressWarnings("static-access")
	public ActionForward execute(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
					throws Exception {
		HttpSession session=request.getSession(false);
		Connection con = null;

		try
		{
			String param=(String)request.getParameter("param");
			String md_login_url=(String)session.getAttribute("md_login_url");
			System.out.println("this is the value of md login url"+md_login_url);
			String logo_name=(String)session.getAttribute("md_header_home_image");
			//System.out.println("the value for the md header"+logo_name);
			String help_mobile_no=(String)session.getAttribute("Help_mobileNo1");
			String help_email_id=(String)session.getAttribute("Agent_cell_email_id");
			String domain_name=(String)session.getAttribute("domain_name");				

			if("checkloginInfo".equals(param))
			{ 				
				LoginInfoDao loginInfoDao=new LoginInfoDao();
				String mdId=(String)session.getAttribute("mdId");
				//System.out.println("inside check login info... :) "+param);

				if(mdId==null){
					request.setAttribute("message","Your login session has expired, Please relogin to continue");						
					return mapping.findForward("sessionexp");
				}					

				HashMap mapdata=(HashMap)loginInfoDao.chekLoginInfo(con,mdId);

				String password=(String)mapdata.get("password");
				String oldpassword=(String)mapdata.get("old_Pass");	
				String EmailVerified=(String)mapdata.get("EmailVerified");	
				String MobVerified=(String)mapdata.get("MobVerified");								
				String Profile_Status=(String)mapdata.get("Profile_Status");

				System.out.println("validations  :: password : "+password+"oldpassword :: "+oldpassword +"EmailVerified :: "+EmailVerified+" MobVerified : "+MobVerified);


				if("N".equals(EmailVerified))
				{
					String varifyCode=loginInfoDao.getRandomCode();	
					String	Email_id=loginInfoDao.updateNewvarificationCode(con,mdId,session,varifyCode);
					//	String emailid=(String)session.getAttribute("Help_email_id1");
					System.out.println("Email_id id ::::"+Email_id);
					System.out.println("varifyCode id ::::"+varifyCode);


					String recipients[]={Email_id};  //emailid
					//String recipients[]={"monika@commsoft.in"};  //emailid

					//   String subject="E-mail  id  and password changed verification";

					System.out.println("mdId----------------------------------------"+mdId);
					System.out.println("md_login_url----------------------------------------"+md_login_url);

					//String check="http://localhost:8080/mds";           // done to test the project on local server, 


					// Updated by Ravi Maharshi on 05/01/2013

					String subject="User Verification Mail";	
					String Message="<html xmlns=\"http://www.w3.org/1999/xhtml\">"+
							"<head>"+
							"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\" />"+
							"<title>Untitled Document</title>"+
							"<link href=\""+md_login_url+"/css/travel.css\" rel=\"stylesheet\" type=\"text/css\" />"+
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

					SendSmtpMail.sendSSLMessage(recipients, subject, Message,help_email_id);//help@travelepoint.in//help_email_id

					String messagepass="<html><body><table><tr><td style=\"font-family: arial,sans-serif; font-size: 9pt;\">* Your Password is Successfully Changed. A Confirmation is sent on your E-Mail Id.<br> * Verify your E-Mail ID in order to access your Services.<br>* If you have any problem in verifying your email ID, call us at "+help_mobile_no+".</td></tr></table></body><html>";
					//System.out.println("before setting messgae>>>>>>>>>>>>>>>>>>>>>>>>>>>");
					session.setAttribute("messagepass",messagepass);
					return mapping.findForward("loginpage"); 				
				}			
				return mapping.findForward("mdHome");				
			}

			if("changePwd".equals(param)){

				String mdId=(String)session.getAttribute("mdId");
				HashMap info=LoginInfoDao.getMdInfo(mdId);
				request.setAttribute("oldPass",info.get("password"));
				session.setAttribute("companyName",info.get("company_name"));
				return mapping.findForward("changePwd");	
			}
			if("updateNewPass".equals(param)){
				System.out.println("param :"+param);
				String userId=(String)session.getAttribute("mdId");
				if(userId==null){
					request.setAttribute("message","Your login session has expired, Please relogin to continue.");
					return mapping.findForward("sessionexp");
				}

				String mdId=(String)session.getAttribute("mdId");
				String newPass=(String)request.getParameter("newpass");
				System.out.println("newPass :"+newPass);
				String status=LoginInfoDao.changeMdpassword(con,mdId,newPass);

				if(status.equalsIgnoreCase("updated")){
					request.setAttribute("message","Password has been changed successfully.");

				}
				else{
					request.setAttribute("message","We are facing some Technical problem.");

				}

				return mapping.findForward("changePwd");

			}	
			if("VarifyEmailcode".equals(param)){

				//LoginInfoDao loginInfoDao=new LoginInfoDao();
				String mdId=(String)request.getParameter("mdId");
				String varifyCode=(String)request.getParameter("varifyCode");
				//String	varificationcode=loginInfoDao.updateNewvarificationCode(con,mdId,session,varifyCode);

				String Status=LoginInfoDao.updateLoginStatus(con,varifyCode,mdId,session);

				System.out.println("status is "+Status);
				if(Status.equalsIgnoreCase("notVerified"))
				{
					System.out.println("we are inside notverified code");
					String message2="Sorry! You have entered the wrong code. Please login again to follow the same process.";
					request.setAttribute("message",message2);
					session.setAttribute("mdcodeId",mdId);
					response.sendRedirect("/mds");					  
				}
				else{
					System.out.println("we are inside Y block");

					String message2="Congrats! Your Account has been verified.";
					session.setAttribute("message",message2);
					response.sendRedirect("/mds");
				}

			} 

		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Hello exception in logininfoAction class<<>>>>>>>>>"+e.toString());
		}

		return null;
	}

}
