package com.controlPanel.profileMgt.retailUser.mds;

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
 * Updated By 		: Manoj
 * Updated Date 	: 13 June 2013
 * Updated Matter	: Optimization of code as well as data base.
 */
public  class MdsProfileAction extends ActionSupport implements ServletRequestAware,ServletResponseAware
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
				request.setAttribute("message","Your Login Session has Expired. Please Login Again.");
				return "sessionExpire";
			}
			
			String param=request.getParameter("param");
			System.out.println("MdsProfileAction, param is : "+param);
			if(param.equals("viewMdsProfile"))
			{
				String mdId=(String)request.getParameter("mdId");
				MdsProfileDao daoObj=new MdsProfileDao();
				HashMap mdsProfileDetails=daoObj.getMdsProfileDetails(mdId);
				int _mdId=(Integer)mdsProfileDetails.get("mdId");
				ArrayList turnOverDetails=daoObj.getMdsTurnOver(_mdId);
				session.put("mdsProfileDetails",mdsProfileDetails);
				session.put("mdsTurnOverDetails",turnOverDetails);
				if(mdsProfileDetails.size()<=0)
				{
					request.setAttribute("message","Process aborted due to Technical Failure.");
				}
				return "viewMdsProfile";
			}
			else if(param.equals("getOTP"))
			{
				String mdId=(String)request.getParameter("mdId");
				String mdIdFlag=(String)request.getParameter("mdIdFlag");
				String otp=MdsProfileDao.getRandomString(8);
				
				MdsProfileDao daoObj=new MdsProfileDao();
				String status=daoObj.setMdsOTP(mdId,mdIdFlag,otp);
				session.put("mdsOtp",otp);
				return "getOTP";
			}
			else if(param.equals("resetPassword"))
			{
				MdsProfileDao daoObj=new MdsProfileDao();
				String mdId=(String)request.getParameter("mdId");
				String mdIdFlag=(String)request.getParameter("mdIdFlag");
				String password=MdsProfileDao.getRandomString(8);
				String status=daoObj.resetMdsPassword(mdId,mdIdFlag,password);
				String completeMdId=(String)request.getParameter("completeMDID");
				HashMap mdsProfileDetails=daoObj.getMdsProfileDetails(completeMdId);
				int _mdId=(Integer)mdsProfileDetails.get("mdId");
				ArrayList turnOverDetails=daoObj.getMdsTurnOver(_mdId);				  
				String mdEmailId=(String)mdsProfileDetails.get("emailID");
				
//				String recipients[]={"Manoj.k@commsoft.in"};  //mdEmailId
				String recipients[]={mdEmailId};  //mdEmailId
				HashMap dynamicDetails=daoObj.getDynamicDetails(_mdId);
				String mdsLoginUrl=(String)dynamicDetails.get("mdLoginUrl");
				String helpEmailId=(String)dynamicDetails.get("helpEmailId");
				String domainName=(String)dynamicDetails.get("domainName");
				String mdsName=(String)mdsProfileDetails.get("completeMdsName");
				//System.out.println(helpEmailId);
				String subject="Your Account Information";
				String Message="<html><BODY cellpadding=\"0\" cellspacing=\"0\"><table border=\"0\"  width=\"100%\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" hspace=\"0\" vspace=\"0\"  bordercolor=\"#FFFFFF\"><tr><td><table height=\"200\" ><tr><td style='font-family:\"Trebuchet MS\";font-size:14px;'>Dear &nbsp;User,</td></tr>"+
					"<p><tr><td>"+
					"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td colspan='3'>&nbsp;</td></tr><tr><td colspan='3' style='font-family:\"Trebuchet MS\";font-size:14px;'>We have received a password request from you. Your Account Information is as follows:<br></td></tr>"+
					"<tr><td colspan='3'>&nbsp;<br></td></tr>"+
					"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;' align='left'>User Name&nbsp;</td><td align='center' style='width:50px;font-family:\"Trebuchet MS\";font-size:14px;'>:</td><td align='left' style='font-family:\"Trebuchet MS\";font-size:14px;'><font><a href=\"mailto:"+mdEmailId+"/\">"+mdEmailId+"</a></font><br></td></tr>"+
					"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;' align='left'>Password&nbsp;</td><td align='center' style='width:50px;font-family:\"Trebuchet MS\";font-size:14px;'>:</td><td align='left' style='font-family:\"Trebuchet MS\";font-size:14px;'><font>"+password+"</font><br></td></tr>"+
					"<tr><td colspan='3'>&nbsp;<br></td></tr>"+
					"</table></td></tr></p>"+
					"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;'>Regards,</td></tr>"+
					"<tr><td>&nbsp;</td></tr>"+
					"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;'>Team Activation</td></tr><br>"+				   
					"</tr></td></table></BODY></html>";
				
				SendSmtpMail.sendSSLMessage(recipients, subject, Message,helpEmailId);
				session.put("mdsProfileDetails",mdsProfileDetails);
				session.put("mdsTurnOverDetails",turnOverDetails);
				request.setAttribute("message","Password has been Reset Successfully.");
				return "viewMdsProfile";
			}
//			else if(param.equals("resetMPIN")){
//				MdsProfileDao daoObj=new MdsProfileDao();
//				String mdId=(String)request.getParameter("mdId");
//				String mdIdFlag=(String)request.getParameter("mdIdFlag");
//				String mpin=MdsProfileDao.getRandomString(6);
//				String status=daoObj.resetMdsMPIN(mdId,mdIdFlag,mpin);
//				String completeMdId=(String)request.getParameter("completeMdId");
//				HashMap mdsProfileDetails=daoObj.getMdsProfileDetails(completeMdId);
//				int _mdId=(Integer)mdsProfileDetails.get("mdId");
//				ArrayList turnOverDetails=daoObj.getMdsTurnOver(_mdId);
//				String mdsMobileNo=(String)mdsProfileDetails.get("mdsMobileNo");
//				SendSmsOnMobile sendsms=new SendSmsOnMobile();
//				String mpinMessage="Dear Agent, your MPIN just reset and new MPIN is <"+mpin+">. Kindly delete this message after  for your security purpose.";
//				sendsms.sendMobileSmsSMSZONE(mdsMobileNo,mpinMessage);
//				sendsms.sendMobileSmsACL(mdsMobileNo,mpinMessage);
//				session.put("mdsProfileDetails",mdsProfileDetails);
//				session.put("mdsTurnOverDetails",turnOverDetails);
//				request.setAttribute("message","MPIN has been reset successfully");
//				return "viewMdsProfile";
//			}
			else if("updateMDProfile".equalsIgnoreCase(param))
			{
				String mdID=request.getParameter("UpdateProfilemd");
				String CompletemdID=request.getParameter("UpdateProfileCompletemd");
				String firstName=request.getParameter("firstName");
				String lastName=request.getParameter("lastName");
				String dob=request.getParameter("dob");
				String gender=request.getParameter("gender");
				String companyType=request.getParameter("companyType");
				String companyName=request.getParameter("companyName");
				String emailId=request.getParameter("emailId");
				String mobileNo=request.getParameter("mobileNo");
				
				MdsProfileDao  daoObj=new MdsProfileDao();
				String emailIdStatus=daoObj.checkEmailID(emailId,mdID);
				if(emailIdStatus.equalsIgnoreCase("invalid"))
				{
					request.setAttribute("message","Email ID is Duplicate.");
					return "viewMdsProfile";
				}
				String userNameStatus=daoObj.checkUserName(emailId,mdID);
				if(userNameStatus.equalsIgnoreCase("invalid"))
				{
					request.setAttribute("message","User Login ID is Duplicate.");
					return "viewMdsProfile";
				}
				String mobileStatus=daoObj.checkMobileNo(mobileNo,mdID);
				if(mobileStatus.equalsIgnoreCase("invalid"))
				{
					request.setAttribute("message","Mobile Number is Duplicate.");
					return "viewMdsProfile";
				}
				
				String addressLine1=request.getParameter("addressLine1");
				String addressLine2=request.getParameter("addressLine2");
				String state=request.getParameter("state");
				String District=request.getParameter("District");
				String city=request.getParameter("city");
				String pincode=request.getParameter("pincode");
				String panNo=request.getParameter("panNo");
				
				String status=daoObj.updateMdProfile(firstName,lastName,dob,gender,companyType,companyName,emailId,mobileNo,addressLine1,addressLine2,state,District,city,pincode,panNo,mdID);
				if(status.equalsIgnoreCase("success"))
				{
					request.setAttribute("message", "Proceess has been completed.");
				}else 
				{
					request.setAttribute("message", "Process aborted due to Technical Failure.");
				}
				HashMap mdsProfileDetails=daoObj.getMdsProfileDetails(CompletemdID);
				int _mdId=(Integer)mdsProfileDetails.get("mdId");
				
				ArrayList turnOverDetails=daoObj.getMdsTurnOver(_mdId);
				session.put("mdsProfileDetails",mdsProfileDetails);
				session.put("mdsTurnOverDetails",turnOverDetails);
				return "viewMdsProfile";
			}
			else if("checkEmailID".equalsIgnoreCase(param))
			{
				String loginUserName=request.getParameter("loginUserName");
				//System.out.println(loginUserName);
				String mdid=request.getParameter("mdid");
				
				MdsProfileDao  daoObj=new MdsProfileDao();
				String status=daoObj.checkUserName(loginUserName,mdid);
				PrintWriter out=response.getWriter();
				out.print(status);
				return null;
				
			}else if("checkMobileNo".equalsIgnoreCase(param))
			{
				String MobileNo=request.getParameter("MobileNo");
				String mdid=request.getParameter("mdid");
				
				MdsProfileDao  daoObj=new MdsProfileDao();
				String status=daoObj.checkMobileNo(MobileNo,mdid);
				PrintWriter out=response.getWriter();
				out.print(status);
				return null;
			}
		}catch(Exception e)
		{
			request.setAttribute("message", "Process aborted due to Technical Failure.");
			System.out.println("Exception in MdsProfileAction");
			System.out.println(e.toString());
			return "viewMdsProfile";
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
