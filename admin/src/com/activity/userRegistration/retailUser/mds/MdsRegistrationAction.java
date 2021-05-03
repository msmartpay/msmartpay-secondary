package  com.activity.userRegistration.retailUser.mds;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.common.CommonServiceDao;
import com.common.PropertyFile;
import com.common.SendSmsOnMobile;
import com.common.SendSmtpMail;
import com.formBean.mds.MdsDetailsFormBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;


public class MdsRegistrationAction extends ActionSupport implements ModelDriven,ServletRequestAware,ServletResponseAware
{

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request; 
	private HttpServletResponse response;
	private Map session;
	private MdsDetailsFormBean mdsUser=new MdsDetailsFormBean();
	   
	public Object getModel(){return mdsUser;}
		
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
			
			String param=mdsUser.getParam();
			System.out.println("MdsRegistrationAction Param is : "+param+"  userId : "+userId);
			
			if(param.equals("registerMds"))
			{
				return "registrationPage";
			}
			else if(param.equals("registration"))
			{
				String portalId="";
				String mdPortalID="";
				String userType=(String)session.get("loginType");
				MdsRegistrationDao daoObj=new MdsRegistrationDao();
				System.out.println("userType : "+userType);
				if(userType.equalsIgnoreCase("superadmin")||userType.equalsIgnoreCase("activityAdmin")||userType.equalsIgnoreCase("activityUser"))
				{	
					portalId=request.getParameter("portalId");
					
				}
				else
				{
					portalId=daoObj.getPortalId(userId);
					
				}
				mdPortalID=userId;
				String firstName=mdsUser.getFirstName();					 
				String lastName=mdsUser.getLastName();
				String dateOfBirth=mdsUser.getDob();
				String gender=mdsUser.getGender();				
				String companyType=mdsUser.getCompanyType();
				String companyName=mdsUser.getCompanyName();
				String emailId=mdsUser.getEmailId();
				String mobileNo=mdsUser.getMobileNo(); 
				String addressLine1=mdsUser.getAddressLine1();				   
				String addressLine2=mdsUser.getAddressLine2();
				String country=mdsUser.getCountry();
				String state=mdsUser.getState();
				String district=mdsUser.getDistrict();
				String cityCode=mdsUser.getCity();
				String pincode=mdsUser.getPincode();
				String panNo=mdsUser.getPanNo();
				String emailIdStatus=daoObj.checkUserName(emailId);
				
				if(emailIdStatus.equalsIgnoreCase("invalid"))
				{
					request.setAttribute("message","Email ID is Duplicate.");
					return "registrationPage";
				}
				String mobileStatus=daoObj.checkMobileNo(mobileNo);
				if(mobileStatus.equalsIgnoreCase("invalid"))
				{
					request.setAttribute("message","Mobile Number is Duplicate.");
					return "registrationPage";
				}
				
				String password=RandomStringUtils.randomNumeric(6);
				HashMap<String,String> mdsUserDetails=daoObj.MDSRegistration(portalId,firstName,lastName,dateOfBirth,gender,companyType,companyName,emailId,mobileNo,addressLine1,addressLine2,state,district,country,cityCode,pincode,panNo,password,userId,mdPortalID);
				
				if(mdsUserDetails!=null && !mdsUserDetails.isEmpty())
				{
					String completeMdId=(String)mdsUserDetails.get("complteMDId"); 
					
					HashMap<String,String> dynamicDetailsMap=daoObj.getDynamicDetails(portalId);
					String mdLoginUrl=(String)dynamicDetailsMap.get("mdLoginUrl");
					String helpEmailId=(String)dynamicDetailsMap.get("helpEmailId");
					mdsUserDetails.put("mdLoginUrl",mdLoginUrl);
					mdsUserDetails.put("password",password);
					mdsUserDetails.put("LoginID",emailId);
					
					HashMap<String,String> mailerMap=CommonServiceDao.getDynamicDetails(userId);
					
					String mailerId=mailerMap.get("Mailer_id");
					String mailerPassword=mailerMap.get("Mailer_password");
					String smsId=mailerMap.get("SMS_id");
					String smsPassword=mailerMap.get("SMS_password");

					String recipients[]={emailId};
					String ccRecipients[] = {helpEmailId};
			        String bccRecipients[] = {PropertyFile.REGISTRATION_EMAIL};
					String subject="User Registration - Master Distributor ";
					
					String Message="<html><BODY cellpadding=\"0\" cellspacing=\"0\"><table border=\"0\"  width=\"100%\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" hspace=\"0\" vspace=\"0\"  bordercolor=\"#FFFFFF\"><tr><td><table height=\"200\" ><tr><td style='font-family:\"Trebuchet MS\";font-size:14px;'>Dear &nbsp;User,</td></tr>"+
					"<p><tr><td>"+
					"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td colspan='3'>&nbsp;</td></tr><tr><td colspan='3' style='font-family:\"Trebuchet MS\";font-size:14px;'>Your MDS ID has been registered. Your Login Details are given below.<br></td></tr>"+
					"<tr><td colspan='3'>&nbsp;<br></td></tr>"+
					"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;width:100px;' align='left'>Login URL&nbsp;</td><td align='center' style='width:50px;font-family:\"Trebuchet MS\";font-size:14px;'>:</td><td align='left' style='font-family:\"Trebuchet MS\";font-size:14px;'><a href=\""+mdLoginUrl+"/\">"+mdLoginUrl+"</a><br></td></tr>"+
					"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;' align='left'>Login ID&nbsp;</td><td align='center' style='width:50px;font-family:\"Trebuchet MS\";font-size:14px;'>:</td><td align='left' style='font-family:\"Trebuchet MS\";font-size:14px;'><font><a href=\"mailto:"+emailId+"/\">"+emailId+"</a></font><br></td></tr>"+
					"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;' align='left'>Password&nbsp;</td><td align='center' style='width:50px;font-family:\"Trebuchet MS\";font-size:14px;'>:</td><td align='left' style='font-family:\"Trebuchet MS\";font-size:14px;'><font>"+password+"</font><br></td></tr>"+
					"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;' align='left'>MDS ID&nbsp;</td><td align='center' style='width:50px;font-family:\"Trebuchet MS\";font-size:14px;'>:</td><td align='left' style='font-family:\"Trebuchet MS\";font-size:14px;'><font>"+completeMdId+"</font><br></td></tr>"+
					"<tr><td colspan='3'>&nbsp;<br></td></tr>"+
					"<tr><td colspan='3' style='font-family:\"Trebuchet MS\";font-size:14px;'>Please always quote your MDS ID in further communication with us.<br><br></tr>"+
					"</table></td></tr></p>"+
					"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;'>Regards,</td></tr>"+
					"<tr><td>&nbsp;</td></tr>"+
					"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;'>Team Activation</td></tr><br>"+				   
					"</tr></td></table></BODY></html>";
					try{
						
						String message="Congrats! Your mobile successfully Activated for Master Distributor Ship. Your User Id is "+mobileNo+" and Password is "+password+". Delete this SMS after storing password at safe place";
						SendSmsOnMobile.sendMobileSmsSMSZONE(mobileNo,message);
						
						SendSmtpMail.sendSSLMessageBCC(recipients,ccRecipients,bccRecipients,subject,Message,helpEmailId,mailerId,mailerPassword);
						
					}catch (Exception e) {
						e.printStackTrace();
						System.out.println("MdsRegistrationAction.execute()");
					}
					request.setAttribute("mdsRegistrationDetails",mdsUserDetails);
					request.setAttribute("message","Process has been completed Successfully.");
					return "registrationCompleted";
				}
				else
				{
					request.setAttribute("message","Process aborted due to Technical Failure.");
					return "registrationPage";
				}
			}
			else if(param.equalsIgnoreCase("checkClientID"))
			{
				String userType=(String)session.get("loginType");
				String clientID=request.getParameter("clientID");
				String user_Id=(String)session.get("userId");
				MdsRegistrationDao daoObj=new MdsRegistrationDao();
				String status=daoObj.checkClientID(clientID,user_Id,userType);
				PrintWriter out=response.getWriter();
				out.print(status);
				return null;
			}
			else if(param.equalsIgnoreCase("checkMaildID"))
			{
				String loginUserName=request.getParameter("loginUserName");
				MdsRegistrationDao daoObj=new MdsRegistrationDao();
				String status=daoObj.checkUserName(loginUserName);
				PrintWriter out=response.getWriter();
				out.print(status);
				return null;
			}
			else if(param.equalsIgnoreCase("checkMobile"))
			{
				String MobileNo=request.getParameter("MobileNo");
				MdsRegistrationDao daoObj=new MdsRegistrationDao();
				String status=daoObj.checkMobileNo(MobileNo);
				PrintWriter out=response.getWriter();
				out.print(status);
				return null;
			}
			
		}catch(Exception e)
		{
			request.setAttribute("message","Process aborted due to Technical Failure.");
			System.out.println("Exception in MdsRegistrationAction");
			System.out.println(e.toString());
			e.printStackTrace();
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
