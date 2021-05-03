package  com.activity.userRegistration.retailUser.distributor;

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
import com.formBean.distributor.DistributorDetailsFormBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public  class DistributorRegistrationAction extends ActionSupport implements ModelDriven,ServletRequestAware,ServletResponseAware
{

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request; 
	private HttpServletResponse response;
	private Map session;
	
	private DistributorDetailsFormBean distributorUser=new DistributorDetailsFormBean();
	
	public Object getModel()
	{
		return distributorUser;
	}
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
			String param=distributorUser.getParam();
			System.out.println("DistributorRegistrationAction param is:"+param);
			if(param.equals("registerDistributor"))
			{
				return "registrationPage";
			}
			else if(param.equalsIgnoreCase("registration"))
			{
				
				String mdId=distributorUser.getMdId();
				String userType=(String)session.get("loginType");
				String status="Invalid";
				DistributorRegistrationDao daoObj=new DistributorRegistrationDao();
				status=daoObj.getMDIDStatus(mdId,userId,userType);
				if("Invalid".equalsIgnoreCase(status))
				{
					request.setAttribute("message", "Please Check Input Value.");
					return "registrationPage";
				}
				else if("valid".equalsIgnoreCase(status))
				{
					
					String firstName=distributorUser.getFirstName();					 
					String lastName=distributorUser.getLastName();                   
					String dateOfBirth=distributorUser.getDob();
					String gender=distributorUser.getGender();
					String companyType=distributorUser.getCompanyType();
					String companyName=distributorUser.getCompanyName();
					String addressLine1=distributorUser.getAddressLine1();			   
					String addressLine2=distributorUser.getAddressLine2();
					String country=distributorUser.getCountry();
					String state=distributorUser.getState();
					String district=distributorUser.getDistrict();
					String cityCode=distributorUser.getCity();
					String pincode=distributorUser.getPincode();
					String panNo=distributorUser.getPanNo();
					String emailId=distributorUser.getEmailId();
					String mobileNo=distributorUser.getMobileNo();
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
					HashMap<String,String> mapDistributorDetails=daoObj.distributorRegistration(mdId,firstName,lastName,dateOfBirth,gender,companyType,companyName,panNo,addressLine1,addressLine2,state,district,country,cityCode,pincode,emailId,mobileNo,password,userId);
					if(mapDistributorDetails.size()>0)
					{		
						String regStatus = ((String)mapDistributorDetails.get("status")).toString();
			        	if(regStatus.equalsIgnoreCase("limit_exceeded"))
			        	{
			        		request.setAttribute("message", "Registration Limit Has Been Crossed, Please Contact Administrator ");
			        		return "registrationPage";
			        	}
						String completeDistributorId=mapDistributorDetails.get("completeDistributorId");
						String mdsID=mapDistributorDetails.get("mdID");
						String mdEmailId=(String)mapDistributorDetails.get("mdEmailId");
						
						HashMap<String,String> dynamicDetailsMap=daoObj.getDynamicDetails(Integer.parseInt(mdsID));
						String distributorLoginUrl=dynamicDetailsMap.get("distributorLoginUrl");
						String helpEmailId=dynamicDetailsMap.get("helpEmailId");
						mapDistributorDetails.put("password", password);
						mapDistributorDetails.put("distributorLoginUrl", distributorLoginUrl);
						mapDistributorDetails.put("emailId", emailId);
						
						
						HashMap<String,String> mailerMap=CommonServiceDao.getDynamicDetails(userId);
						
						String mailerId=mailerMap.get("Mailer_id");
						String mailerPassword=mailerMap.get("Mailer_password");
						String smsId=mailerMap.get("SMS_id");
						String smsPassword=mailerMap.get("SMS_password");
						
						String recipients[] = {emailId};
				        String ccRecipients[] = {mdEmailId,helpEmailId};
				        String bccRecipients[] = {PropertyFile.REGISTRATION_EMAIL};
						String subject="User Registration - Distributor ";
						
						String Message = (new StringBuilder("<html><BODY cellpadding=\"0\" cellspacing=\"0\"><table border=\"0\"  width=\"100%\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" hspace=\"0\" vspace=\"0\"  bordercolor=\"#FFFFFF\"><tr><td><table height=\"200\" ><tr><td style='font-family:\"Trebuchet MS\";font-size:14px;'>Dear &nbsp;User,</td></tr><p><tr><td><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td colspan='3'>&nbsp;<br></td></tr><tr><td colspan='3' style='font-family:\"Trebuchet MS\";font-size:14px;'>Your Distributor ID has been registered. Your Login Details are given below.<br></td></tr><tr><td colspan='3'>&nbsp;<br></td></tr><tr><td style='font-family:\"Trebuchet MS\";font-size:14px;width:100px;' align='left'>Login URL&nbsp;</td><td align='center' style='width:50px;font-family:\"Trebuchet MS\";font-size:14px;'>:</td><td align='left' style='font-family:\"Trebuchet MS\";font-size:14px;'><a href=\"")).append(distributorLoginUrl).append("/\">").append(distributorLoginUrl).append("</a><br></td></tr>").append("<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;' align='left'>User ID&nbsp;</td><td align='center' style='width:50px;font-family:\"Trebuchet MS\";font-size:14px;'>:</td><td align='left' style='font-family:\"Trebuchet MS\";font-size:14px;'><font><a href=\"mailto:").append(emailId).append("/\">").append(emailId).append("</a></font><br></td></tr>").append("<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;' align='left'>Password&nbsp;</td><td align='center' style='width:50px;font-family:\"Trebuchet MS\";font-size:14px;'>:</td><td align='left' style='font-family:\"Trebuchet MS\";font-size:14px;'><font>").append(password).append("</font><br></td></tr>").append("<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;' align='left'>Disributor ID&nbsp;</td><td align='center' style='width:50px;font-family:\"Trebuchet MS\";font-size:14px;'>:</td><td align='left' style='font-family:\"Trebuchet MS\";font-size:14px;'><font>").append(completeDistributorId).append("</font><br></td></tr>").append("<tr><td colspan='3'>&nbsp;<br></td></tr>").append("<tr><td colspan='3' style='font-family:\"Trebuchet MS\";font-size:14px;'>Please always quote your Distributor ID in further communication with us.<br><br></tr>").append("</table></td></tr></p>").append("<tr><td ><p style='font-family:\"Trebuchet MS\";font-size:14px;'>Regards,</p></td></tr>").append("<tr><td ><p style='font-family:\"Trebuchet MS\";font-size:14px;'>Team Activation</p></td></tr><br>").append("</tr></td></table></BODY></html>").toString();
						try{
							
							String message="Congrats! Your mobile successfully Activated for Distributor Ship. Your User Id is "+mobileNo+" and Password is "+password+". Delete this SMS after storing password at safe place";
							SendSmsOnMobile.sendMobileSmsSMSZONE(mobileNo,message);
							
							SendSmtpMail.sendSSLMessageBCC(recipients,ccRecipients,bccRecipients,subject,Message,helpEmailId,mailerId,mailerPassword);
						
						}catch (Exception e) {
							e.printStackTrace();
							System.out
									.println("DistributorRegistrationAction.execute()");
						}
						request.setAttribute("distributorRegistrationDetails",mapDistributorDetails);
						request.setAttribute("message","Process has been completed Successfully.");
						return "registrationCompleted";  
					}
					else
					{
						request.setAttribute("message","Process aborted due to Technical Failure."); 
						return "registrationPage";  
					}
				}
				
			}else if(param.equalsIgnoreCase("checkMdsID"))
			{
				String mdsID=request.getParameter("mdsID");
				String userType=(String)session.get("loginType");
				String user_Id=(String)session.get("userId");
				
				DistributorRegistrationDao daoObj=new DistributorRegistrationDao();
				String status=daoObj.getMDIDStatus(mdsID,user_Id,userType);
				PrintWriter out=response.getWriter();
				out.print(status);
				return null;
			}
			else if(param.equalsIgnoreCase("checkUserName"))
			{
				String loginUserName=request.getParameter("loginUserName");
				DistributorRegistrationDao daoObj=new DistributorRegistrationDao();
				String status=daoObj.checkUserName(loginUserName);
				PrintWriter out=response.getWriter();
				out.print(status);
				return null;
			}
			else if(param.equalsIgnoreCase("checkMobileNo"))
			{
				String mobileNO=request.getParameter("mobileNo");
				DistributorRegistrationDao daoObj=new DistributorRegistrationDao();
				String status=daoObj.checkMobileNo(mobileNO);
				PrintWriter out=response.getWriter();
				out.print(status);
				return null;
			}
		}
		catch(Exception e)
		{
			request.setAttribute("message","Process aborted due to Technical Failure.");
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


