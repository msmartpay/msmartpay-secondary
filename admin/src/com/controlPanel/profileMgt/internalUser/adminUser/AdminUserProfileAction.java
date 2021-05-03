package com.controlPanel.profileMgt.internalUser.adminUser;
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


public class AdminUserProfileAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{
	
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request; 
	private HttpServletResponse response;
	private Map session;
	public String execute() throws Exception{ 
		
		Map session=ActionContext.getContext().getSession();
		try{
			String userId=(String)session.get("userId");
			if(userId==null){
				request.setAttribute("message","Your Login Session has Expired. Please Login Again.");
				return "sessionExpire";
			}
	    	String param=request.getParameter("param");
			System.out.println("AdminUserProfileAction Class and Param is "+param);
			if(param.equals("viewAdminUserProfile")){
				String adminUserId=(String)request.getParameter("adminUserId");
				//System.out.println(adminUserId +" This is Admin ID" );
				AdminUserProfileDao daoObj=new AdminUserProfileDao();
				HashMap adminUserProfileDetails=daoObj.getAdminUserProfileDetails(adminUserId);
				int _adminUserId=(Integer)adminUserProfileDetails.get("adminUserId");
				ArrayList turnOverDetails=daoObj.getAdminUserTurnOver(_adminUserId);
				//System.out.println("turnOverDetails in action class is>>>>>>>>>>>"+turnOverDetails);
				request.setAttribute("adminUserProfileDetails",adminUserProfileDetails);
				request.setAttribute("adminUserTurnOverDetails",turnOverDetails);
				String errorStatus=(String)adminUserProfileDetails.get("error");
				//System.out.println(errorStatus);
				if(errorStatus==null){errorStatus="";}
				if(errorStatus.equalsIgnoreCase("error")){
					request.setAttribute("message", "Process aborted due to Technical Failure.");
					return "err";
				}
				return "viewAdminUserProfile";
	    	}
			else if(param.equals("getOTP")){
				AdminUserProfileDao daoObj=new AdminUserProfileDao();
				String adminUserId=(String)request.getParameter("adminUserId");
				String otp=daoObj.getRandomString(8);
				String status=daoObj.setAdminUserOTP(adminUserId,otp);
				session.put("adminUserOtp",otp);
				
				return "getOTP";
			}
			else if(param.equals("resetPassword")){
				
				String adminUserId=request.getParameter("adminUserId");
				 
				AdminUserProfileDao daoObj=new AdminUserProfileDao();
				String password=daoObj.getRandomString(8);
				String  pass=Encryption.SHA1(password);
				String status=daoObj.resetAdminUserPassword(adminUserId,pass);
				if("updated".equalsIgnoreCase(status)){
					request.setAttribute("message","Password has been Reset Successfully.");
				}else{
					request.setAttribute("message","Process aborted due to Technical Failure.");
				}
				HashMap adminUserProfileDetails=daoObj.getAdminUserProfileDetails(adminUserId);
				  
				int _adminUserId=(Integer)adminUserProfileDetails.get("adminUserId");
				  
				ArrayList turnOverDetails=daoObj.getAdminUserTurnOver(_adminUserId);				  

				String adminUserEmailId=(String)adminUserProfileDetails.get("emailID");
//				String recipients[]={"Manoj.k@commsoft.in"};
				String recipients[]={adminUserEmailId};  //adminUserEmailId
				HashMap dynamicDetails=daoObj.getDynamicDetails(_adminUserId);
				String helpEmailId=(String)dynamicDetails.get("helpEmailId");
				String subject="Your Account Information";
				String Message="<html><BODY cellpadding=\"0\" cellspacing=\"0\"><table border=\"0\"  width=\"100%\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" hspace=\"0\" vspace=\"0\"  bordercolor=\"#FFFFFF\"><tr><td><table height=\"200\" ><tr><td style='font-family:\"Trebuchet MS\";font-size:14px;'>Dear &nbsp;User,</td></tr>"+
					"<p><tr><td>"+
					"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td colspan='3'>&nbsp;</td></tr><tr><td colspan='3' style='font-family:\"Trebuchet MS\";font-size:14px;'>We have received a password request from you. Your Account Information is as follows:<br></td></tr>"+
					"<tr><td colspan='3'>&nbsp;<br></td></tr>"+
					"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;' align='left'>User Name&nbsp;</td><td align='center' style='width:50px;font-family:\"Trebuchet MS\";font-size:14px;'>:</td><td align='left' style='font-family:\"Trebuchet MS\";font-size:14px;'><font><a href=\"mailto:"+adminUserEmailId+"/\">"+adminUserEmailId+"</a></font><br></td></tr>"+
					"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;' align='left'>Password&nbsp;</td><td align='center' style='width:50px;font-family:\"Trebuchet MS\";font-size:14px;'>:</td><td align='left' style='font-family:\"Trebuchet MS\";font-size:14px;'><font>"+password+"</font><br></td></tr>"+
					"<tr><td colspan='3'>&nbsp;<br></td></tr>"+
					"</table></td></tr></p>"+
					"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;'>Regards,</td></tr>"+
					"<tr><td>&nbsp;</td></tr>"+
					"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;'>Team Activation</td></tr><br>"+				   
					"</tr></td></table></BODY></html>";
				SendSmtpMail.sendSSLMessage(recipients, subject, Message,helpEmailId);

				
				request.setAttribute("adminUserProfileDetails",adminUserProfileDetails);
				request.setAttribute("adminUserTurnOverDetails",turnOverDetails);
				return "viewAdminUserProfile";
				  
			}
			else if("updatePortalProfile".equalsIgnoreCase(param)){
				String adminID=request.getParameter("adminID");
				String firstName=request.getParameter("firstName");
				String lastName=request.getParameter("lastName");
				String dob=request.getParameter("dob");
				String gender=request.getParameter("gender");
				String companyType=request.getParameter("companyType");
				String companyName=request.getParameter("companyName");
				String emailId=request.getParameter("emailId");
				String mobileNo=request.getParameter("mobileNo");
				
				AdminUserProfileDao daoObj=new AdminUserProfileDao();
				String emailIdStatus=daoObj.checkEmailID(emailId,adminID);
				if(emailIdStatus.equalsIgnoreCase("invalid")){
					request.setAttribute("message","Email ID is Duplicate.");
					HashMap adminUserProfileDetails=daoObj.getAdminUserProfileDetails(adminID);
					int _adminUserId=(Integer)adminUserProfileDetails.get("adminUserId");
					ArrayList turnOverDetails=daoObj.getAdminUserTurnOver(_adminUserId);
					//System.out.println("turnOverDetails in action class is>>>>>>>>>>>"+turnOverDetails);
					request.setAttribute("adminUserProfileDetails",adminUserProfileDetails);
					request.setAttribute("adminUserTurnOverDetails",turnOverDetails);
					return "viewAdminUserProfile";
				}
				String userNameStatus=daoObj.checkUserName(emailId,adminID);
				if(userNameStatus.equalsIgnoreCase("invalid")){
					request.setAttribute("message","User Login ID is Duplicate.");
					HashMap adminUserProfileDetails=daoObj.getAdminUserProfileDetails(adminID);
					int _adminUserId=(Integer)adminUserProfileDetails.get("adminUserId");
					ArrayList turnOverDetails=daoObj.getAdminUserTurnOver(_adminUserId);
					//System.out.println("turnOverDetails in action class is>>>>>>>>>>>"+turnOverDetails);
					request.setAttribute("adminUserProfileDetails",adminUserProfileDetails);
					request.setAttribute("adminUserTurnOverDetails",turnOverDetails);
					return "viewAdminUserProfile";
				}
				String mobileStatus=daoObj.checkMobileNo(mobileNo,adminID);
				if(mobileStatus.equalsIgnoreCase("invalid")){
					request.setAttribute("message","Mobile Number is Duplicate.");
					HashMap adminUserProfileDetails=daoObj.getAdminUserProfileDetails(adminID);
					int _adminUserId=(Integer)adminUserProfileDetails.get("adminUserId");
					ArrayList turnOverDetails=daoObj.getAdminUserTurnOver(_adminUserId);
					//System.out.println("turnOverDetails in action class is>>>>>>>>>>>"+turnOverDetails);
					request.setAttribute("adminUserProfileDetails",adminUserProfileDetails);
					request.setAttribute("adminUserTurnOverDetails",turnOverDetails);
					return "viewAdminUserProfile";
				}
				String addressLine1=request.getParameter("addressLine1");
				String addressLine2=request.getParameter("addressLine2");
				String state=request.getParameter("state");
				String District=request.getParameter("District");
				String city=request.getParameter("city");
				String pincode=request.getParameter("pincode");
				String panNo=request.getParameter("panNo");
				
				String status=daoObj.updatePortalUserProfile(firstName,lastName,dob,gender,companyType,companyName,emailId,mobileNo,addressLine1,addressLine2,state,District,city,pincode,panNo,adminID);
				if(status.equalsIgnoreCase("success")){
					request.setAttribute("message", "Proceess has been completed.");
				}else {
					request.setAttribute("message", "Process aborted due to Technical Failure.");
				}
				HashMap adminUserProfileDetails=daoObj.getAdminUserProfileDetails(adminID);
				int _adminUserId=(Integer)adminUserProfileDetails.get("adminUserId");
				ArrayList turnOverDetails=daoObj.getAdminUserTurnOver(_adminUserId);
				//System.out.println("turnOverDetails in action class is>>>>>>>>>>>"+turnOverDetails);
				request.setAttribute("adminUserProfileDetails",adminUserProfileDetails);
				request.setAttribute("adminUserTurnOverDetails",turnOverDetails);
				return "viewAdminUserProfile";
			}
			else if("checkEmailID".equalsIgnoreCase(param)){
				String loginUserName=request.getParameter("loginUserName");
				//System.out.println(loginUserName);
				String mdid=request.getParameter("mdid");
				
				AdminUserProfileDao daoObj=new AdminUserProfileDao();
				String status=daoObj.checkUserName(loginUserName,mdid);
				PrintWriter out=response.getWriter();
				out.print(status);
				return null;
			}else if("checkMobileNo".equalsIgnoreCase(param)){
				String MobileNo=request.getParameter("MobileNo");
				String mdid=request.getParameter("mdid");
				
				AdminUserProfileDao daoObj=new AdminUserProfileDao();
				String status=daoObj.checkMobileNo(MobileNo,mdid);
				PrintWriter out=response.getWriter();
				out.print(status);
				return null;
			}
		}catch(Exception e){
			System.out.println("Exception in AdminUserActivationAction");
			request.setAttribute("message", "Process aborted due to Technical Failure.");
			System.out.println(e.toString());
			return "viewAdminUserProfile";
		}
	    return "err";   
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
