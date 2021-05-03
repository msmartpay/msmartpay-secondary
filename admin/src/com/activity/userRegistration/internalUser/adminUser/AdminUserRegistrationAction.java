package com.activity.userRegistration.internalUser.adminUser;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.common.ConvertUtility;
import com.common.Encryption;
import com.common.SendSmtpMail;
import com.formBean.adminUser.AdminUserFormBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * Updated By :Manoj
 * Updated Date : 1 Jun 2013
 * Updated Matter : Optimization of code as well as data base.
 */

public class AdminUserRegistrationAction extends ActionSupport implements ModelDriven,ServletRequestAware,ServletResponseAware
{
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request; 
	private HttpServletResponse response;
	private Map session;
	   
	private AdminUserFormBean adminUser=new AdminUserFormBean();
	public Object getModel(){return adminUser;}
       
	public String execute() throws Exception
	{ 
		Map session=ActionContext.getContext().getSession();
		try
		{
			String ActivityUserType="";
			String loginPassword=""; 
			String userId=(String)session.get("userId");

			if(userId==null)
			{
				request.setAttribute("message","Your Login Session has Expired. Please Login Again.");
				return "sessionExpire";
			}
			   
			String param=adminUser.getParam();
            System.out.println("AdminUserRegistrationAction Param is : "+param);
			
			if(param.equalsIgnoreCase("registerAdmin"))
			{
				return "registrationPage";
			}

			else if(param.equals("registration"))
			{
				String portalId="";
				String userType=(String)session.get("adminUserType");
				AdminUserRegistrationDao daoObj=new AdminUserRegistrationDao();
				if(userType.equalsIgnoreCase("superadmin"))
				{	
					portalId=(String)session.get("clientId");
				}

				else
				{
					portalId=daoObj.getPortalId(userId);;
				}

				System.out.println("portalId : "+portalId);
				String loginType =(String)session.get("loginType");
                    
				if(loginType.equalsIgnoreCase("superadmin"))
				{
					ActivityUserType="activityAdmin"; 
				}
                   
				if(!loginType.equalsIgnoreCase("superadmin")&& (portalId.equalsIgnoreCase("10001")|| portalId.equalsIgnoreCase("10003")))
				{
					ActivityUserType="activityUser"; 
				}
				else
				{
					ActivityUserType="portalUser"; 
				}
				// All Parameter Start Here
				String firstName=adminUser.getFirstName();
				String lastName=adminUser.getLastName();
				String dateOfBirth=adminUser.getDob();
				String gender=adminUser.getGender();
				String companyType=adminUser.getCompanyType();
				String companyName=adminUser.getCompanyName();
				String emailId=adminUser.getEmailId();
				String officialNumber=adminUser.getOfficialNumber();
				String addressLine1=adminUser.getAddressLine1();
				String addressLine2=adminUser.getAddressLine2();
				String country=adminUser.getCountry();
				String state=adminUser.getState();
				String district=adminUser.getDistrict();
				String cityCode=adminUser.getCity();
				String pincode=adminUser.getPincode();
				String panNo=adminUser.getPanNo();
				String userNameStatus=daoObj.checkUserName(emailId);
				
				if(userNameStatus.equalsIgnoreCase("invalid"))
				{
					request.setAttribute("message","Email ID is Duplicate.");
					return "registrationPage";
				}
				String userEmailStatus=daoObj.checkUserEmail(emailId); 
				if(userEmailStatus.equalsIgnoreCase("invalid"))
				{
					request.setAttribute("message","Email ID is Duplicate.");
					return "registrationPage";
				}
				String userMobileStatus=daoObj.checkMobileNo(officialNumber);
				if(userMobileStatus.equalsIgnoreCase("invalid"))
				{

					request.setAttribute("message","Mobile Number is Duplicate.");
					return "registrationPage";
				}
				
				loginPassword=ConvertUtility.getRandomNumber();
				String password=Encryption.SHA1(loginPassword);
				
				HashMap<String,String> adminUserDetails=daoObj.AdminUserRegistration(portalId,firstName,lastName,dateOfBirth,gender,companyType,companyName,officialNumber,emailId,addressLine1,addressLine2,country,state,district,cityCode,pincode,password,ActivityUserType,panNo,userId);
				
				if(adminUserDetails.size()>0)
				{
					String id=adminUserDetails.get("UserID");
					emailId=adminUserDetails.get("EmailID");
					HashMap<String,String> dynamicDetailsMap=daoObj.getDynamicDetails(portalId);
					String helpEmailId=(String)dynamicDetailsMap.get("helpEmailId");
					String adminLoginUrl=(String)dynamicDetailsMap.get("adminLoginUrl");
					adminUserDetails.put("password",loginPassword);
					adminUserDetails.put("adminLoginUrl", adminLoginUrl);
					String recipients[]={emailId,helpEmailId,"support@softsolutionzone.in"};
					//String recipients[]={"Manoj.k@commsoft.in"};
					String subject="User Registration - Admin";

					String message="<html><BODY cellpadding=\"0\" cellspacing=\"0\"><table border=\"0\"  width=\"100%\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" hspace=\"0\" vspace=\"0\"  bordercolor=\"#FFFFFF\"><tr><td><table height=\"200\" ><tr><td style='font-family:\"Trebuchet MS\";font-size:14px;'>Dear &nbsp;User,</td></tr>"+
					"<p><tr><td>"+
					"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td colspan='3'>&nbsp;</td></tr><tr><td colspan='3' style='font-family:\"Trebuchet MS\";font-size:14px;'>Your Admin ID has been registered. Your Login Details are given below.<br></td></tr>"+
					"<tr><td colspan='3'>&nbsp;<br></td></tr>"+
					"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;width:100px;' align='left'>Login URL&nbsp;</td><td align='center' style='width:50px;font-family:\"Trebuchet MS\";font-size:14px;'>:</td><td align='left' style='font-family:\"Trebuchet MS\";font-size:14px;'><a href=\""+adminLoginUrl+"/\">"+adminLoginUrl+"</a><br></td></tr>"+
					"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;' align='left'>Login ID&nbsp;</td><td align='center' style='width:50px;font-family:\"Trebuchet MS\";font-size:14px;'>:</td><td align='left' style='font-family:\"Trebuchet MS\";font-size:14px;'><font><a href=\"mailto:"+emailId+"/\">"+emailId+"</a></font><br></td></tr>"+
					"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;' align='left'>Password&nbsp;</td><td align='center' style='width:50px;font-family:\"Trebuchet MS\";font-size:14px;'>:</td><td align='left' style='font-family:\"Trebuchet MS\";font-size:14px;'><font>"+loginPassword+"</font><br></td></tr>"+
					"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;' align='left'>Admin ID&nbsp;</td><td align='center' style='width:50px;font-family:\"Trebuchet MS\";font-size:14px;'>:</td><td align='left' style='font-family:\"Trebuchet MS\";font-size:14px;'><font>"+id+"</font><br></td></tr>"+
					"<tr><td colspan='3'>&nbsp;<br></td></tr>"+
					"<tr><td colspan='3' style='font-family:\"Trebuchet MS\";font-size:14px;'>Please always quote your Admin ID in further communication with us.<br><br></tr>"+
					"</table></td></tr></p>"+
					"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;'>Regards,</td></tr>"+
					"<tr><td>&nbsp;</td></tr>"+
					"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;'>Team Activation</td></tr><br>"+				   
					"</tr></td></table></BODY></html>";
					try{
					SendSmtpMail.sendSSLMessage(recipients, subject, message,helpEmailId);	
					}catch (Exception e) {
						e.printStackTrace();
						System.out
								.println("AdminUserRegistrationAction.execute()");
					}
					
					request.setAttribute("message","Process has been completed Successfully.");
					request.setAttribute("AdminUserRegDetailsMap",adminUserDetails );
					return "registrationCompleted"; 
				}
				else
				{
					request.setAttribute("message","Process aborted due to Technical Failure."); 
					return "registrationPage";
				}
			}
			  
			else if(param.equalsIgnoreCase("checkUserName"))
			{
				String loginUserName=request.getParameter("loginUserName");
				AdminUserRegistrationDao daoObj=new AdminUserRegistrationDao();
				String status=daoObj.checkUserName(loginUserName);
				PrintWriter out=response.getWriter();
				out.print(status);
				return null;
			}
			else if(param.equalsIgnoreCase("checkUserEmail"))
			{
				String loginUserName=request.getParameter("loginUserEmail");
				AdminUserRegistrationDao daoObj=new AdminUserRegistrationDao();
				String status=daoObj.checkUserEmail(loginUserName);
				PrintWriter out=response.getWriter();
				out.print(status);
				return null;
			}
			else if(param.equalsIgnoreCase("checkUserMobile"))
			{
				String MobileNo=request.getParameter("Mobile");
				AdminUserRegistrationDao daoObj=new AdminUserRegistrationDao();
				String status=daoObj.checkMobileNo(MobileNo);
				PrintWriter out=response.getWriter();
				out.print(status);
				return null;
			}
	
		}catch(Exception e)
		{
			request.setAttribute("message","Process aborted due to Technical Failure.");
			System.out.println("Exception in AdminUserRegistrationAction");
			System.out.println(e.toString());
			return "registrationPage";
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
