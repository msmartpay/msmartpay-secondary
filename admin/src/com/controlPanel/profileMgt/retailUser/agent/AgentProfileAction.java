package com.controlPanel.profileMgt.retailUser.agent;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.common.SendSmsOnMobile;
import com.common.SendSmtpMail;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class AgentProfileAction extends ActionSupport implements ServletRequestAware,ServletResponseAware
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
			System.out.println("AgentProfileAction.execute()");
			if(param.equals("viewAgentProfile"))
			{
				AgentProfileDao daoObj=new AgentProfileDao();
				String agentId=(String)request.getParameter("agentId");
				HashMap agentProfileDetails=daoObj.getAgentProfileDetails(agentId);
				int _agentId=(Integer)agentProfileDetails.get("agentId");
				ArrayList turnOverDetails=daoObj.getAgentTurnOver(_agentId);
				session.put("agentProfileDetails",agentProfileDetails);
				session.put("agentTurnOverDetails",turnOverDetails);
				if(agentProfileDetails.size()<=0)
				{
					request.setAttribute("message","Process aborted due to Technical Failure.");
				}
				return "viewAgentProfile";
			}
			else if(param.equals("getOTP"))
			{
				AgentProfileDao daoObj=new AgentProfileDao();
				String agentId=(String)request.getParameter("agentId");
				String agentIdFlag=(String)request.getParameter("agentIdFlag");
				String otp=RandomStringUtils.randomNumeric(6);
				String status=daoObj.setAgentOTP(agentId,agentIdFlag,otp);
				session.put("agentOtp",otp);
				return "getOTP";
			}
			else if(param.equals("resetPassword"))
			{
				AgentProfileDao daoObj=new AgentProfileDao();
				String agentId=(String)request.getParameter("agentId");
				String agentIdFlag=(String)request.getParameter("agentIdFlag");
				String password=RandomStringUtils.randomNumeric(6);
				String status=daoObj.resetAgentPassword(agentId,agentIdFlag,password);
				String completeAgentId=(String)request.getParameter("completeAgentId");
				HashMap agentProfileDetails=daoObj.getAgentProfileDetails(completeAgentId);
				int _agentId=(Integer)agentProfileDetails.get("agentId");
				String clientFlag=(String)agentProfileDetails.get("agentIdFlag");

				ArrayList turnOverDetails=daoObj.getAgentTurnOver(_agentId);				  
				String agentEmailId=(String)agentProfileDetails.get("emailID");
				//				String recipients[]={"Manoj.k@commsoft.in"}; 
				String recipients[]={agentEmailId}; 
				String loginId="";

				if(clientFlag.equalsIgnoreCase("0") || clientFlag.equalsIgnoreCase("2"))
				{
					loginId=agentEmailId;
				}else
				{
					loginId=(String)agentProfileDetails.get("mobileNo");
				}
				HashMap dynamicDetails=daoObj.getDynamicDetails(_agentId);
				String agentLoginUrl=(String)dynamicDetails.get("agentLoginUrl");
				String helpEmailId=(String)dynamicDetails.get("helpEmailId");
				String domainName=(String)dynamicDetails.get("domainName");
				String agentName=(String)agentProfileDetails.get("completeAgentName");
				String subject="Your Account Information";
				String Message="<html><BODY cellpadding=\"0\" cellspacing=\"0\"><table border=\"0\"  width=\"100%\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" hspace=\"0\" vspace=\"0\"  bordercolor=\"#FFFFFF\"><tr><td><table height=\"200\" ><tr><td style='font-family:\"Trebuchet MS\";font-size:14px;'>Dear &nbsp;User,</td></tr>"+
						"<p><tr><td>"+
						"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td colspan='3'>&nbsp;</td></tr><tr><td colspan='3' style='font-family:\"Trebuchet MS\";font-size:14px;'>We have received a password request from you. Your Account Information is as follows:<br></td></tr>"+
						"<tr><td colspan='3'>&nbsp;<br></td></tr>"+
						"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;' align='left'>User Name&nbsp;</td><td align='center' style='width:50px;font-family:\"Trebuchet MS\";font-size:14px;'>:</td><td align='left' style='font-family:\"Trebuchet MS\";font-size:14px;'><font>"+loginId+"</font><br></td></tr>"+
						"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;' align='left'>Password&nbsp;</td><td align='center' style='width:50px;font-family:\"Trebuchet MS\";font-size:14px;'>:</td><td align='left' style='font-family:\"Trebuchet MS\";font-size:14px;'><font>"+password+"</font><br></td></tr>"+
						"<tr><td colspan='3'>&nbsp;<br></td></tr>"+
						"</table></td></tr></p>"+
						"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;'>Regards,</td></tr>"+
						"<tr><td>&nbsp;</td></tr>"+
						"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;'>Team Activation</td></tr><br>"+				   
						"</tr></td></table></BODY></html>";

				SendSmtpMail.sendSSLMessage(recipients, subject, Message,helpEmailId);
				session.put("agentProfileDetails",agentProfileDetails);
				session.put("agentTurnOverDetails",turnOverDetails);
				request.setAttribute("message","Password has been Reset Successfully.");
				return "viewAgentProfile";
			}
			else if(param.equals("resetMPIN"))
			{
				String agentId=(String)request.getParameter("agentId");
				String agentIdFlag=(String)request.getParameter("agentIdFlag");
				AgentProfileDao daoObj=new AgentProfileDao();
				String mpin=RandomStringUtils.randomNumeric(6);
				String status=daoObj.resetAgentMPIN(agentId,agentIdFlag,mpin);
				String completeAgentId=(String)request.getParameter("completeAgentId");
				HashMap agentProfileDetails=daoObj.getAgentProfileDetails(completeAgentId);
				int _agentId=(Integer)agentProfileDetails.get("agentId");
				ArrayList turnOverDetails=daoObj.getAgentTurnOver(_agentId);
				String agentMobileNo=(String)agentProfileDetails.get("agentMobileNo");
				SendSmsOnMobile sendsms=new SendSmsOnMobile();
				String mpinMessage="Your MPIN has been Reset. New MPIN is "+mpin+" Please do not share MPIN.";
				sendsms.sendMobileSmsSMSZONE(agentMobileNo,mpinMessage);

				session.put("agentProfileDetails",agentProfileDetails);
				session.put("agentTurnOverDetails",turnOverDetails);
				if(status.equalsIgnoreCase("updated"))
				{
					request.setAttribute("message","MPIN has been Reset Successfully.");
				}else if(status.equalsIgnoreCase("notupdated"))
				{
					request.setAttribute("message","Process aborted due to Technical Failure.");
				}
				return "viewAgentProfile";
			}
			else if(param.equals("resetAPIN"))
			{
				String agentId=(String)request.getParameter("agentId");
				String agentIdFlag=(String)request.getParameter("agentIdFlag");
				AgentProfileDao daoObj=new AgentProfileDao();
				String mpin=RandomStringUtils.randomNumeric(6);
				String status=daoObj.resetAgentAPIN(agentId,agentIdFlag,mpin);
				String completeAgentId=(String)request.getParameter("completeAgentId");
				HashMap agentProfileDetails=daoObj.getAgentProfileDetails(completeAgentId);
				int _agentId=(Integer)agentProfileDetails.get("agentId");
				ArrayList turnOverDetails=daoObj.getAgentTurnOver(_agentId);
				String agentMobileNo=(String)agentProfileDetails.get("agentMobileNo");
				SendSmsOnMobile sendsms=new SendSmsOnMobile();
				String mpinMessage="Your APIN has been Reset. New APIN is "+mpin+" Please do not share APIN.";
				sendsms.sendMobileSmsSMSZONE(agentMobileNo,mpinMessage);

				session.put("agentProfileDetails",agentProfileDetails);
				session.put("agentTurnOverDetails",turnOverDetails);

				if(status.equalsIgnoreCase("update"))
				{
					request.setAttribute("message","APIN has been Reset Successfully.");
				}else if(status.equalsIgnoreCase("notupdated"))
				{
					request.setAttribute("message","Process aborted due to Technical Failure.");
				}
				return "viewAgentProfile";
			}
			else if(param.equalsIgnoreCase("agentProfileView"))
			{
				AgentProfileDao daoObj=new AgentProfileDao();
				String agentId=request.getParameter("userId");
				String status=daoObj.checkAgentIdStatus(agentId,userId);
				if(status.equals("valid"))
				{
					HashMap agentProfileDetails=daoObj.getAgentProfileDetailsForUpdate(agentId);
					request.setAttribute("agentDetailsForProfileUpdate",agentProfileDetails);
					request.setAttribute("updateProfileAgentId",agentId);
					return "updateAgentProfile";
				}
				else
				{
					request.setAttribute("message","invalid Agent Id");
					return "noUpdateAgentProfile";
				}
			}
			else if("updateAGProfile".equalsIgnoreCase(param))
			{
				String agId=request.getParameter("UpdateProfileag");
				String CompleteAgID=request.getParameter("UpdateProfileCompleteag");
				String firstName=request.getParameter("firstName");
				String lastName=request.getParameter("lastName");
				String dob=request.getParameter("dob");
				String gender=request.getParameter("gender");
				String companyType=request.getParameter("companyType");
				String companyName=request.getParameter("companyName");
				String emailId=request.getParameter("emailId");
				String mobileNo=request.getParameter("mobileNo");
				String clientFlag=request.getParameter("clientFlag");
				AgentProfileDao  daoObj=new AgentProfileDao();
				String emailIdStatus=daoObj.checkEmailID(emailId,agId);

				if(emailIdStatus.equalsIgnoreCase("invalid"))
				{
					request.setAttribute("message","Email ID is Duplicate.");
					return "viewAgentProfile";
				}

				String userNameStatus=daoObj.checkUserName(emailId,agId);
				if(userNameStatus.equalsIgnoreCase("invalid"))
				{
					request.setAttribute("message","Email ID is Duplicate.");
					return "viewAgentProfile";
				}
				String mobileStatus=daoObj.checkMobileNo(mobileNo,agId);
				if(mobileStatus.equalsIgnoreCase("invalid"))
				{
					request.setAttribute("message","Mobile Number is Duplicate.");
					return "viewAgentProfile";
				}
				String addressLine1=request.getParameter("addressLine1");
				String addressLine2=request.getParameter("addressLine2");
				String state=request.getParameter("state");
				String District=request.getParameter("District");
				String city=request.getParameter("city");
				String pincode=request.getParameter("pincode");
				String panNo=request.getParameter("panNo");
				String kycStatus=request.getParameter("kycStatus");
				String aadhaarNo=request.getParameter("aadhaarNo");

				String status=daoObj.updateAGProfile(aadhaarNo,kycStatus,firstName,lastName,dob,gender,companyType,companyName,emailId,mobileNo,addressLine1,addressLine2,state,District,city,pincode,panNo,agId,clientFlag);
				if(status.equalsIgnoreCase("success"))
				{
					request.setAttribute("message", "Proceess has been completed.");
				}else 
				{
					request.setAttribute("message", "Process aborted due to Technical Failure.");
				}
				HashMap agentProfileDetails=daoObj.getAgentProfileDetails(CompleteAgID);
				int _agentId=(Integer)agentProfileDetails.get("agentId");
				ArrayList turnOverDetails=daoObj.getAgentTurnOver(_agentId);
				session.put("agentProfileDetails",agentProfileDetails);
				session.put("agentTurnOverDetails",turnOverDetails);
				return "viewAgentProfile";
			}
			else if("checkEmailID".equalsIgnoreCase(param))
			{
				String loginUserName=request.getParameter("loginUserName");
				//System.out.println(loginUserName);
				String agid=request.getParameter("agid");
				//System.out.println(agid);
				AgentProfileDao  daoObj=new AgentProfileDao();
				String status=daoObj.checkUserName(loginUserName,agid);
				PrintWriter out=response.getWriter();
				out.print(status);
				return null;

			}else if("checkMobileNo".equalsIgnoreCase(param))
			{
				String MobileNo=request.getParameter("MobileNo");
				String agid=request.getParameter("agid");
				//System.out.println(agid);
				AgentProfileDao  daoObj=new AgentProfileDao();
				String status=daoObj.checkMobileNo(MobileNo,agid);
				PrintWriter out=response.getWriter();
				out.print(status);
				return null;
			}
		}catch(Exception e)
		{
			request.setAttribute("message", "Process aborted due to Technical Failure.");
			System.out.println("Exception in AgentProfileAction");
			System.out.println(e.toString());
			return "viewAgentProfile";
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
