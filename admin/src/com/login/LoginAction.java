package com.login;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import com.common.Encryption;
import com.common.SendSmtpMail;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class LoginAction extends ActionSupport implements ModelDriven<Object>,ServletRequestAware,ServletResponseAware
{
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request; 
	private HttpServletResponse response;
	private Map session;
	private LoginInfoFormBean loginform=new LoginInfoFormBean();	
	public Object getModel(){return loginform;}
	
	public String execute() throws Exception
	{ 
		Map session=ActionContext.getContext().getSession();
		try
		{
			String param=loginform.getParam();
			System.out.println("LoginAction Class, Param :"+param);
			if(param.equals("login"))
			{
				String password=loginform.getPassword();
				String Ip=request.getParameter("IP");
				String pass=Encryption.SHA1(password);
				System.out.println(pass);
				String userName=loginform.getUserName();
				LoginDao daoObj=new LoginDao();
				String loginStatus=daoObj.checkLoginDetails(userName,pass,session,Ip);
				
				if(loginStatus.equalsIgnoreCase("valid"))
				{
					return "login";
				}else if(loginStatus.equalsIgnoreCase("invalid"))
				{
					request.setAttribute("message", "Your User Name or Password is Invalid.");
					return "loginFailed";
				}
				else if(loginStatus.equalsIgnoreCase("Blocked"))
				{
					request.setAttribute("message", "Login Disallowed. Please contact your Channel Partner.");
					return "loginFailed";
				}else if(loginStatus.equalsIgnoreCase("Deactive"))
				{
					request.setAttribute("message", "Your Account is not Active. Please contact your Channel Partner. ");
					return "loginFailed";
				}else if("error".equalsIgnoreCase(loginStatus))
				{
					request.setAttribute("message", "Process aborted due to Technical Failure.");
					return "loginFailed";
				}
			}
			else if("checkloginInfo".equalsIgnoreCase(param))
			{
				String userId=request.getParameter("userId");
				System.out.println(userId);
				LoginDao daoObj=new LoginDao();
				
				HashMap<String,Object> serviceMap=daoObj.getUserServiceAuthenticationDetails(userId);
				HashMap<String,Object> dynamicDetailsMap=daoObj.getDynamicDetails(userId);
				session.put("dynamicDetails",dynamicDetailsMap);
				session.put("adminServiceDetails",serviceMap);
				
				
				String clientId="";
				if(Long.parseLong(userId)!=1)
					clientId=session.get("clientId")+"";
				
				long userIdlong=Long.parseLong(clientId);
				HashMap<String,Object> dashboardMap=daoObj.dashboard(userIdlong);
				
				request.setAttribute("dashboardMap",dashboardMap);
				return "adminHome";
			}
			else if("changePassword".equals(param))
			{  
				String userId=(String)request.getParameter("userId");
				String password=(String)request.getParameter("password");
				String  pass=Encryption.SHA1(password);
				String emailId=(String)request.getParameter("emailId");
				LoginDao daoObj=new LoginDao();
				String varificationcode=daoObj.getRandomCode();
				String exist=daoObj.changePasswordOnLogin(pass,userId,emailId,varificationcode);
				if(exist.equalsIgnoreCase("yes"))
				{
					request.setAttribute("changePasswordMessage","Email Id already exist.Please enter different email id");
					return "changePassword";
				}
				HashMap<String,Object> dynamicDetails=daoObj.getDynamicDetails(userId);//(HashMap)session.get("dynamicDetails");
				String adminLoginUrl=(String)dynamicDetails.get("adminLoginUrl");
				String mdLoginUrl=(String)dynamicDetails.get("mdLoginUrl");
				String logoName=(String)dynamicDetails.get("headerHomeImage");
				String helpEmailId=(String)dynamicDetails.get("helpEmailId");
				String helpMobileNo=(String)dynamicDetails.get("helpMobileNo");
				String domainName=(String)dynamicDetails.get("domainName");
				session.put("admin_login_url", adminLoginUrl);
				String recipients[]={emailId}; 
				
				String subject="E-mail  id  and password changed verification";
				String Message="<html xmlns=\"http://www.w3.org/1999/xhtml\">"+
				"<head>"+
				"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\" />"+
				"<title>Untitled Document</title>"+
				"<link href=\""+adminLoginUrl+"/css/travel.css\" rel=\"stylesheet\" type=\"text/css\" />"+
				"</head><body>"+
				"<table width=70% border=0 align=center cellpadding=0 cellspacing=0 class=maintext>"+
				"<tr><td><div align=right><img src="+adminLoginUrl+"/images/"+logoName+" width=195 height=87 border=0 /></div></td></tr>"+
				"<tr><td>"+
				"<div align=justify><table width=100% height=\"100\" border=0 cellspacing=10 cellpadding=0 class=maintext>"+
				"<tr bgcolor=#EFF3F6><td width=\"300\"  bgcolor=#EFF3F6 class=textnormal>Your password has been changed successfully you can login only after verify the link given below </td></tr>"+
				"<tr bgcolor=#EFF3F6><td bgcolor=#EFF3F6 class=textnormal ><A HREF=\""+adminLoginUrl+"/login.action?userId="+userId+"&param=varify\">Click here to verify your Admin account</A></td></tr>"+
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
				"<p>"+helpMobileNo+"</p><p></p></td></tr></table>";
				SendSmtpMail.sendSSLMessage(recipients, subject, Message,helpEmailId);//help@travelepoint.in//help_email_id
                request.setAttribute("message","Your Password is Successfully Changed. A Confirmation is sent on your E-Mail Id.");
                return "loginPage";
			}
			else if("sendmail".equals(param))
			{  
				String userId=(String)request.getParameter("userId");
				String emailId=(String)request.getParameter("emailId");
				LoginDao daoObj=new LoginDao();
				HashMap<String,Object> verificationCodeDetails=daoObj.getOldvarificationCode(userId,emailId);					
				String verificationCode=(String)verificationCodeDetails.get("code");
				
				if(verificationCode==null)
				{
					String code=daoObj.getRandomCode();
					String result=daoObj.saveVerificationCode(code, userId, emailId);
					verificationCode=code;
				}
				String status=(String)verificationCodeDetails.get("exist");
				if(status.equalsIgnoreCase("yes")){
					request.setAttribute("message","Email Id already Exists.Please enter correct email Id");
					return "emailVerification";
				}
				
				String recipients[]={emailId};  //emailId
				String subject="E-mail  id  and password changed verification";
				HashMap dynamicDetails=daoObj.getDynamicDetails(userId);
				String adminLoginUrl=(String)dynamicDetails.get("adminLoginUrl");
				String mdLoginUrl=(String)dynamicDetails.get("mdLoginUrl");
				String logoName=(String)dynamicDetails.get("headerHomeImage");
				String helpEmailId=(String)dynamicDetails.get("helpEmailId");
				String helpMobileNo=(String)dynamicDetails.get("helpMobileNo");
				String domainName=(String)dynamicDetails.get("domainName");
				session.put("admin_login_url", adminLoginUrl);
				
				String Message="<html xmlns=\"http://www.w3.org/1999/xhtml\">"+
				"<head>"+
				"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\" />"+
				"<title>Untitled Document</title>"+
				"<link href=\""+adminLoginUrl+"/css/travel.css\" rel=\"stylesheet\" type=\"text/css\" />"+
				"</head><body>"+
				"<table width=70% border=0 align=center cellpadding=0 cellspacing=0 class=maintext>"+
				"<tr><td><div align=right><img src="+adminLoginUrl+"/images/"+logoName+" width=195 height=87 border=0 /></div></td></tr>"+
				"<tr><td>"+
				"<div align=justify><table width=100% height=\"100\" border=0 cellspacing=10 cellpadding=0 class=maintext>"+
				"<tr bgcolor=#EFF3F6><td width=\"300\"  bgcolor=#EFF3F6 class=textnormal>Your password has been changed successfully you can login only after verify the link given below .</td></tr>"+
				"<tr bgcolor=#EFF3F6><td bgcolor=#EFF3F6 class=textnormal ><A HREF=\""+adminLoginUrl+"/login.action?userId="+userId+"&param=varify\">Click here to verify your Admin account.</A></td></tr>"+
				"<tr bgcolor=#EFF3F6><td bgcolor=#EFF3F6 class=textnormal>Varification&nbsp;code&nbsp;:&nbsp;"+verificationCode+"</td>"+
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
				"<p>092-122-44-790 </p><p></p></td></tr></table>";
				SendSmtpMail.sendSSLMessage(recipients, subject, Message,helpEmailId);//help@travelepoint.in//help_email_id
				request.setAttribute("message","Your Password is Successfully Changed. A Confirmation is sent on your E-Mail Id.");
				return "loginPage";
			}
			else if("varify".equals(param))
			{
				String userId=(String)request.getParameter("userId");
				String verificationMessage="  ";
				session.put("verificationMessage",verificationMessage);
				session.put("userId",userId);
				return "verificationCode";
			}
			else if("checkVerificationCode".equals(param))
			{
				String verificationCode=(String)request.getParameter("verificationCode");
				String userId=(String)request.getParameter("userId");
				LoginDao daoObj=new LoginDao();
				String Status=daoObj.updateLoginStatus(verificationCode,userId);
				
				if(Status.equals("Verified")) 
				{
					String verificationMessage="Congratulations, your account is verified.";
					session.put("verificationMessage",verificationMessage);
					return "verificationCodeSuccess";
				} else 
				{
					String verificationMessage="Invalid code Plz try again with valid code.";
					request.setAttribute("message","Invalid code Plz try again with valid code.");
					session.put("userId",userId);
				     return "verificationCode";
				}
			}
			else if("checkMobileVerificationCode".equals(param))
			{
				String mobVerUserId=request.getParameter("userId");
				String verificationCode=request.getParameter("mobileVerificationCode");
				LoginDao daoObj=new LoginDao();
				String Status=daoObj.updateFinalLoginStatus(verificationCode,mobVerUserId);
				if(Status.equalsIgnoreCase("Verified"))
				{
					String message2="Your Account has been Verified Successfully.Login to continue";
					request.setAttribute("message","Your Account has been Verified Successfully.Login to continue");
					response.sendRedirect("/superAdmin");
				} else
				{
					session.put("userId",mobVerUserId);
					request.setAttribute("message","Invalid code Plz try again with valid code.");
					return "verifyMobileNo";
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception in LoginAction");
			System.out.println(e.toString());
			request.setAttribute("message", "Process aborted due to Technical Failure.");
			return "loginFailed";
		}
		return "err";  
	}
  
	public void setServletRequest(HttpServletRequest request)
	{
		this.request=request;
	}
	public void setSession(Map session)
	{
		session = this.getSession();
	}
	public Map getSession()
	{
		return session;
	}
	@Override
	public void setServletResponse(HttpServletResponse response) 
	{
		this.response=response;
	}
}
