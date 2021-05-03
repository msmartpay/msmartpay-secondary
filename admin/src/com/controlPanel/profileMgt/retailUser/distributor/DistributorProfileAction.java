package com.controlPanel.profileMgt.retailUser.distributor;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.common.SendSmtpMail;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
* Updated By :Arvind
* Updated Date : 11 Jun 2013
* Updated Matter : Format of class.
*/

/**
* Updated By 		 : Manoj
* Updated Date		 : 18 Jun 2013
* Updated Matter	 : Optimization of code and database .
*/
public class DistributorProfileAction extends ActionSupport implements ServletRequestAware,ServletResponseAware
{
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request; 
	private HttpServletResponse response;
	private Map session;
		
	public String execute() throws Exception
	{ 
		Map session=ActionContext.getContext().getSession();
		try
		{
			String userId=(String)session.get("userId");
			if(userId==null)
			{
				request.setAttribute("message","Session has been expired,Please relogin to continue.");
				return "sessionExpire";
			}
			
			String param=request.getParameter("param");
			System.out.println("DistributorProfileAction , param is:"+param);
			if(param.equals("viewDistributorProfile"))
			{
				DistributorProfileDao daoObj=new DistributorProfileDao();
				String distributorId=(String)request.getParameter("distributorId");
				
				HashMap distributorProfileDetails=daoObj.getDistributorProfileDetails(distributorId);
				int _distributorId=(Integer)distributorProfileDetails.get("distributorId");
				int distributorIdFlag=Integer.parseInt(distributorProfileDetails.get("distributorIdFlag").toString());
				ArrayList turnOverDetails=daoObj.getDistributorTurnOver(_distributorId);
				session.put("distributorProfileDetails",distributorProfileDetails);
				session.put("distributorTurnOverDetails",turnOverDetails);
				session.put("profileDistributorId", _distributorId);
				session.put("",distributorIdFlag);
				if(distributorProfileDetails.size()<=0)
				{
					request.setAttribute("message","Process aborted due to Technical Failure.");
				}
				return "viewDistributorProfile";
			}
			else if(param.equals("getOTP"))
			{
				DistributorProfileDao daoObj=new DistributorProfileDao();
				String distributorId=(String)request.getParameter("distributorId");
				String distributorIdFlag=(String)request.getParameter("distributorIdFlag");
				String otp=daoObj.getRandomString(8);
				String status=daoObj.setDistributorOTP(distributorId,distributorIdFlag,otp);
				session.put("distributorOtp",otp);
				return "getOTP";
			}
			else if(param.equals("resetPassword"))
			{
				DistributorProfileDao daoObj=new DistributorProfileDao();
				String distributorId=(String)request.getParameter("distributorId");
				String distributorIdFlag=(String)request.getParameter("distributorIdFlag");
				String password=daoObj.getRandomString(8);
				String status=daoObj.resetDistributorPassword(distributorId,distributorIdFlag,password);
				String completeDistributorId=(String)request.getParameter("completeDistributorId");
				HashMap distributorProfileDetails=daoObj.getDistributorProfileDetails(completeDistributorId);
				int _distributorId=(Integer)distributorProfileDetails.get("distributorId");
				ArrayList turnOverDetails=daoObj.getDistributorTurnOver(_distributorId);				  
				String distributorEmailId=(String)distributorProfileDetails.get("emailID");
//				String recipients[]={"Manoj.k@commsoft.in"};  //distributorEmailId
				String recipients[]={distributorEmailId};
				HashMap dynamicDetails=daoObj.getDynamicDetails(_distributorId);
				String distributorLoginUrl=(String)dynamicDetails.get("distributorLoginUrl");
				String helpEmailId=(String)dynamicDetails.get("helpEmailId");
				String domainName=(String)dynamicDetails.get("domainName");
				String distributorName=(String)distributorProfileDetails.get("completeDistributorName");
				String subject="Your Account Information";
				
				String Message="<html><BODY cellpadding=\"0\" cellspacing=\"0\"><table border=\"0\"  width=\"100%\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" hspace=\"0\" vspace=\"0\"  bordercolor=\"#FFFFFF\"><tr><td><table height=\"200\" ><tr><td style='font-family:\"Trebuchet MS\";font-size:14px;'>Dear &nbsp;User,</td></tr>"+
					"<p><tr><td>"+
					"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td colspan='3'>&nbsp;</td></tr><tr><td colspan='3' style='font-family:\"Trebuchet MS\";font-size:14px;'>We have received a password request from you. Your Account Information is as follows:<br></td></tr>"+
					"<tr><td colspan='3'>&nbsp;<br></td></tr>"+
					"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;' align='left'>User Name&nbsp;</td><td align='center' style='width:50px;font-family:\"Trebuchet MS\";font-size:14px;'>:</td><td align='left' style='font-family:\"Trebuchet MS\";font-size:14px;'><font><a href=\"mailto:"+distributorEmailId+"/\">"+distributorEmailId+"</a></font><br></td></tr>"+
					"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;' align='left'>Password&nbsp;</td><td align='center' style='width:50px;font-family:\"Trebuchet MS\";font-size:14px;'>:</td><td align='left' style='font-family:\"Trebuchet MS\";font-size:14px;'><font>"+password+"</font><br></td></tr>"+
					"<tr><td colspan='3'>&nbsp;<br></td></tr>"+
					"</table></td></tr></p>"+
					"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;'>Regards,</td></tr>"+
					"<tr><td>&nbsp;</td></tr>"+
					"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;'>Team Activation</td></tr><br>"+				   
					"</tr></td></table></BODY></html>";
				
				SendSmtpMail.sendSSLMessage(recipients, subject, Message,helpEmailId);
				session.put("distributorProfileDetails",distributorProfileDetails);
				session.put("distributorTurnOverDetails",turnOverDetails);
				request.setAttribute("message","Password has been Reset Successfully.");
				return "viewDistributorProfile";
			}
//			if(param.equals("resetMPIN")){
//				DistributorProfileDao daoObj=new DistributorProfileDao();
//				String distributorId=(String)request.getParameter("distributorId");
//				String distributorIdFlag=(String)request.getParameter("distributorIdFlag");
//				String mpin=daoObj.getRandomString(6);
//				String status=daoObj.resetDistributorMPIN(distributorId,distributorIdFlag,mpin);
//				String completeDistributorId=(String)request.getParameter("completeDistributorId");
//				HashMap distributorProfileDetails=daoObj.getDistributorProfileDetails(completeDistributorId);
//				int _distributorId=(Integer)distributorProfileDetails.get("distributorId");
//				ArrayList turnOverDetails=daoObj.getDistributorTurnOver(_distributorId);
//				String distributorMobileNo=(String)distributorProfileDetails.get("distributorMobileNo");
//				SendSmsOnMobile sendsms=new SendSmsOnMobile();
//				String mpinMessage="Dear Agent, your MPIN just reset and new MPIN is <"+mpin+">. Kindly delete this message after  for your security purpose.";
//				sendsms.sendMobileSmsSMSZONE(distributorMobileNo,mpinMessage);
//				sendsms.sendMobileSmsACL(distributorMobileNo,mpinMessage);
//				session.put("distributorProfileDetails",distributorProfileDetails);
//				session.put("distributorTurnOverDetails",turnOverDetails);
//				request.setAttribute("message","MPIN has been reset successfully");
//				return "viewDistributorProfile";
//			}
			else if("updateDSProfile".equalsIgnoreCase(param))
			{
				String dsId=request.getParameter("UpdateProfileds");
				String CompletedsID=request.getParameter("UpdateProfileCompleteds");
				String firstName=request.getParameter("firstName");
				String lastName=request.getParameter("lastName");
				String dob=request.getParameter("dob");
				String gender=request.getParameter("gender");
				String companyType=request.getParameter("companyType");
				String companyName=request.getParameter("companyName");
				String emailId=request.getParameter("emailId");
				String mobileNo=request.getParameter("mobileNo");
				
				DistributorProfileDao  daoObj=new DistributorProfileDao();
				String emailIdStatus=daoObj.checkEmailID(emailId,dsId);
				if(emailIdStatus.equalsIgnoreCase("invalid"))
				{
					request.setAttribute("message","Email ID is Duplicate.");
					return "viewDistributorProfile";
				}
				String userNameStatus=daoObj.checkUserName(emailId,dsId);
				if(userNameStatus.equalsIgnoreCase("invalid"))
				{
					request.setAttribute("message","User Login ID is Duplicate.");
					return "viewDistributorProfile";
				}
				String mobileStatus=daoObj.checkMobileNo(mobileNo,dsId);
				if(mobileStatus.equalsIgnoreCase("invalid"))
				{
					request.setAttribute("message","Mobile Number is Duplicate.");
					return "viewDistributorProfile";
				}
				
				String addressLine1=request.getParameter("addressLine1");
				String addressLine2=request.getParameter("addressLine2");
				String state=request.getParameter("state");
				String District=request.getParameter("District");
				String city=request.getParameter("city");
				String pincode=request.getParameter("pincode");
				String panNo=request.getParameter("panNo");
				
				String status=daoObj.updateDSProfile(firstName,lastName,dob,gender,companyType,companyName,emailId,mobileNo,addressLine1,addressLine2,state,District,city,pincode,panNo,dsId);
				if(status.equalsIgnoreCase("success"))
				{
					request.setAttribute("message", "Proceess has been completed.");
				}else 
				{
					request.setAttribute("message", "Process aborted due to Technical Failure.");
				}
				//System.out.println(status);
				HashMap distributorProfileDetails=daoObj.getDistributorProfileDetails(CompletedsID);
				int _distributorId=(Integer)distributorProfileDetails.get("distributorId");
				ArrayList turnOverDetails=daoObj.getDistributorTurnOver(_distributorId);
				session.put("distributorProfileDetails",distributorProfileDetails);
				session.put("distributorTurnOverDetails",turnOverDetails);
				
				return "viewDistributorProfile";
			}
			else if("checkEmailID".equalsIgnoreCase(param))
			{
				String loginUserName=request.getParameter("loginUserName");
				//System.out.println(loginUserName);
				String dsid=request.getParameter("dsid");
				//System.out.println(dsid);
				DistributorProfileDao  daoObj=new DistributorProfileDao();
				String status=daoObj.checkUserName(loginUserName,dsid);
				PrintWriter out=response.getWriter();
				out.print(status);
				return null;
				
			}else if("checkMobileNo".equalsIgnoreCase(param))
			{
				String MobileNo=request.getParameter("MobileNo");
				String dsid=request.getParameter("dsid");
				//System.out.println(dsid);
				DistributorProfileDao  daoObj=new DistributorProfileDao();
				String status=daoObj.checkMobileNo(MobileNo,dsid);
				PrintWriter out=response.getWriter();
				out.print(status);
				return null;
			}
		}catch(Exception e)
		{
			request.setAttribute("message", "Process aborted due to Technical Failure.");
			System.out.println("Exception in DistributorProfileAction");
			System.out.println(e.toString());
			return "viewDistributorProfile";
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
	public void setServletResponse(HttpServletResponse response)
	{
		this.response=response;
	}
}
