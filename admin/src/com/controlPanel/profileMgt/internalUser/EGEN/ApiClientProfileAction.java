package com.controlPanel.profileMgt.internalUser.EGEN;

import java.io.PrintWriter;
import java.util.ArrayList;
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



public class ApiClientProfileAction extends ActionSupport implements ServletRequestAware,ServletResponseAware
{
	private HttpServletRequest request; 
	private HttpServletResponse response;
	private Map session;
	
	public String execute() throws Exception{ 
		Map session=ActionContext.getContext().getSession();
		try
		{
			String userId=(String)session.get("userId");
			if(userId==null){
				session.put("Loginmessage","Session has been expired,Please relogin to continue.");
				return "sessionExpire";
			}
			String param=request.getParameter("param");
			System.out.println(" ApiClientProfileAction and param:"+param);
			if(param.equals("viewApiClientProfile"))
			{
				String apiClientId=(String)request.getParameter("apiClientId");
				HashMap apiClientProfileDetails=ApiClientProfileDao.getApiClientProfileDetails(apiClientId);
				int _apiClientId=Integer.parseInt(apiClientId);
				//System.out.println("_apiClientId in action class is>>>>>>>>>>>"+_apiClientId);
				ArrayList turnOverDetails=ApiClientProfileDao.getApiClientTurnOver(_apiClientId);
				//System.out.println("turnOverDetails in action class is>>>>>>>>>>>"+turnOverDetails);
				session.put("apiClientProfileDetails",apiClientProfileDetails);
				session.put("apiClientTurnOverDetails",turnOverDetails);
				session.put("apiClientProfileMessage","");
				return "viewApiClientProfile";
			}
			else if(param.equals("getOTP")){
				String apiClientId=(String)request.getParameter("apiClientId");
				String otp=ApiClientProfileDao.getRandomString(8);
				//System.out.println("otp is>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+otp);
				String status=ApiClientProfileDao.setApiClientOTP(apiClientId,otp);
				session.put("apiClientOtp",otp);
				return "getOTP";
			}
			else if(param.equals("resetPassword"))
			{
				String apiClientId=(String)request.getParameter("apiClientId");
				//System.out.println("apiClientId>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+apiClientId);
				String password=ApiClientProfileDao.getRandomString(8);
				String  pass=Encryption.SHA1(password);
				String status=ApiClientProfileDao.resetApiClientPassword(apiClientId,pass);
				String completeApiClientId=(String)request.getParameter("completeApiClientId");
				HashMap apiClientProfileDetails=ApiClientProfileDao.getApiClientProfileDetails(completeApiClientId);
				int _apiClientId=Integer.parseInt(apiClientId);
				ArrayList turnOverDetails=ApiClientProfileDao.getApiClientTurnOver(_apiClientId);				  
				String apiClientEmailId=(String)apiClientProfileDetails.get("apiClientEmailId");
				String recipients[]={apiClientEmailId};  //apiClientEmailId
				String helpEmailId="support@commsoft.in";
				String completeApiClientName=(String)apiClientProfileDetails.get("completeApiClientName");

				String subject="Your Account Information";
				String Message="<html><BODY cellpadding=\"0\" cellspacing=\"0\"><table border=\"0\"  width=\"100%\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" hspace=\"0\" vspace=\"0\"  bordercolor=\"#FFFFFF\"><tr><td><table height=\"200\" ><tr><td style='font-family:\"Trebuchet MS\";font-size:14px;'>Dear &nbsp;User,</td></tr>"+
				"<p><tr><td>"+
				"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td colspan='3'>&nbsp;</td></tr><tr><td colspan='3' style='font-family:\"Trebuchet MS\";font-size:14px;'>We have received a password request from you. Your Account Information is as follows:<br></td></tr>"+
				"<tr><td colspan='3'>&nbsp;<br></td></tr>"+
				"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;' align='left'>User Name&nbsp;</td><td align='center' style='width:50px;font-family:\"Trebuchet MS\";font-size:14px;'>:</td><td align='left' style='font-family:\"Trebuchet MS\";font-size:14px;'><font>"+apiClientEmailId+"</font><br></td></tr>"+
				"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;' align='left'>Password&nbsp;</td><td align='center' style='width:50px;font-family:\"Trebuchet MS\";font-size:14px;'>:</td><td align='left' style='font-family:\"Trebuchet MS\";font-size:14px;'><font>"+password+"</font><br></td></tr>"+
				"<tr><td colspan='3'>&nbsp;<br></td></tr>"+
				"</table></td></tr></p>"+
				"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;'>Regards,</td></tr>"+
				"<tr><td>&nbsp;</td></tr>"+
				"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;'>Team Activation</td></tr><br>"+				   
				"</tr></td></table></BODY></html>";
             
				SendSmtpMail.sendSSLMessage(recipients, subject, Message,helpEmailId);
				session.put("apiClientProfileDetails",apiClientProfileDetails);
				session.put("apiClientTurnOverDetails",turnOverDetails);

				session.put("apiClientProfileMessage","Password has been Reset Successfully.");
				
				return "viewApiClientProfile";
			}
			else if(param.equalsIgnoreCase("updateEgenProfile"))
			{
				String apiClientId=request.getParameter("apiClientId");
				String firstName=request.getParameter("firstName");
				String lastName=request.getParameter("lastName");
				String dob=request.getParameter("dob");
				String companyType=request.getParameter("companyType");
				String companyName=request.getParameter("companyName");
				String emailId=request.getParameter("emailId");
				String mobileNo=request.getParameter("mobileNo");
				ApiClientProfileDao daoObj=new ApiClientProfileDao();
				String emailIdStatus=daoObj.checkEmailID(emailId,apiClientId);
				if(emailIdStatus.equalsIgnoreCase("invalid"))
				{
					request.setAttribute("message","Email ID is Duplicate.");
					return "viewApiClientProfile";
				}
				String mobileStatus=daoObj.checkMobileNo(mobileNo,apiClientId);
				if(mobileStatus.equalsIgnoreCase("invalid"))
				{
					request.setAttribute("message","Mobile Number is Duplicate.");
					return "viewApiClientProfile";
				}
				String addressLine1=request.getParameter("addressLine1");
				String state=request.getParameter("state");
				String District=request.getParameter("District");
				String pincode=request.getParameter("pincode");
				String status=daoObj.updateEGENProfile(firstName,lastName,dob,companyType,companyName,emailId,mobileNo,addressLine1,state,District,pincode,apiClientId);
				if(status.equalsIgnoreCase("success"))
				{
					HashMap apiClientProfileDetails=ApiClientProfileDao.getApiClientProfileDetails(apiClientId);
					int _apiClientId=Integer.parseInt(apiClientId);
					//System.out.println("_apiClientId in action class is>>>>>>>>>>>"+_apiClientId);
					ArrayList turnOverDetails=ApiClientProfileDao.getApiClientTurnOver(_apiClientId);
					//System.out.println("turnOverDetails in action class is>>>>>>>>>>>"+turnOverDetails);
					session.put("apiClientProfileDetails",apiClientProfileDetails);
					session.put("apiClientTurnOverDetails",turnOverDetails);
					session.put("apiClientProfileMessage","");
					request.setAttribute("message", "Proceess has been completed.");
					
					return "viewApiClientProfile";
				}
				else 
				{
					request.setAttribute("message", "Process aborted due to Technical Failure.");
					return "viewApiClientProfile";
				} 
			}
			else if(param.equalsIgnoreCase("checkEmailID"))
			{
				   String email=request.getParameter("email");
					System.out.println(email);
					String id=request.getParameter("id");
					System.out.println(id);
					ApiClientProfileDao daoObj=new ApiClientProfileDao();
					String status=daoObj.checkEmailID(email,id);
					PrintWriter out=response.getWriter();
					out.print(status);
					return null;
			}
			else if(param.equalsIgnoreCase("checkMobileNo")){
				   String MobileNo=request.getParameter("MobileNo");
					String id=request.getParameter("id");
					System.out.println(id);
					ApiClientProfileDao daoObj=new ApiClientProfileDao();
					String status=daoObj.checkMobileNo(MobileNo,id);
					PrintWriter out=response.getWriter();
					out.print(status);
					return null;
			}
		}catch(Exception e){
			request.setAttribute("message","Process aborted due to Technical Failure.");
			System.out.println("Exception in ApiClientProfileAction");
			System.out.println(e.toString());
			return "viewApiClientProfile";
		}
		return null;   
	}
	public void setServletRequest(HttpServletRequest request){
			 this.request=request;
			 
		 }

		  public void setSession(Map session){
           session = this.getSession();
          }

         public Map getSession(){
            return session;
         }
	
         public void setServletResponse(HttpServletResponse response) {
 			this.response=response;
         }
}
