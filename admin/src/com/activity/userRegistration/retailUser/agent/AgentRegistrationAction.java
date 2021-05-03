package  com.activity.userRegistration.retailUser.agent;

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
import com.formBean.agent.AgentDetailsFormBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AgentRegistrationAction extends ActionSupport implements ModelDriven,ServletRequestAware,ServletResponseAware
{

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request; 
	private HttpServletResponse response;
	private Map session;
	private AgentDetailsFormBean agentUser=new AgentDetailsFormBean();

	public Object getModel()
	{
		return agentUser;
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
			String param=agentUser.getParam();
			System.out.println("AgentRegistrationAction param is:"+param);
			if(param.equals("registerAgent"))
			{
				return "registrationPage";
			}
			else if(param.equals("registration"))
			{
				String adminUserType=(String)session.get("adminUserType");
				String distributorId=agentUser.getDistributorId();
				String distributorIdStatus="";
				AgentRegistrationDao daoObj=new AgentRegistrationDao();

				distributorIdStatus=daoObj.checkDistributorIdStatus(distributorId,userId,adminUserType);
				if(distributorIdStatus.equalsIgnoreCase("invalid"))
				{
					request.setAttribute("message","Invalid distributor id,Please enter valid distributor id.");
					return "registrationPage";
				}

				String firstName=agentUser.getFirstName();					 
				String lastName=agentUser.getLastName();                   
				String dateOfBirth=agentUser.getDob();
				String gender=agentUser.getGender();
				String companyType=agentUser.getCompanyType();
				String companyName=agentUser.getCompanyName();
				String addressLine1=agentUser.getAddressLine1();			   
				String addressLine2=agentUser.getAddressLine2();
				String country=agentUser.getCountry();
				String state=agentUser.getState();
				String district=agentUser.getDistrict();
				String cityCode=agentUser.getCity();
				String pincode=agentUser.getPincode();
				String mobileNo=agentUser.getMobileNo();
				String panNo=agentUser.getPanNo();
				String emailId=agentUser.getEmailId();
				int apiStatus=agentUser.getApiStatus();
				String emailIdStatus=daoObj.checkUserName(emailId);

				if(emailIdStatus.equalsIgnoreCase("invalid"))
				{
					request.setAttribute("message","Email ID is Duplicate.");
					return "registrationPage";
				}

				String mobileNoStatus=daoObj.checkMobileNo(mobileNo);
				if(mobileNoStatus.equals("invalid")){
					request.setAttribute("message","Mobile Number is Duplicate.");
					return "registrationPage";
				}

				String password=RandomStringUtils.randomNumeric(6);
				HashMap<String,String> mapAgentDetails=daoObj.agentRegistration(distributorId,firstName,lastName,dateOfBirth,
						gender,companyType,companyName,emailId,mobileNo,addressLine1,addressLine2,
						country,state,district,cityCode,pincode,panNo,password,userId,apiStatus);
				if(mapAgentDetails.size()>0)
				{			    
					String completeAgentId=mapAgentDetails.get("completeAgentId");
					String clientFlag=mapAgentDetails.get("clientFlag");
					String distributorEmailId=mapAgentDetails.get("distributorEmailId");
					String dsid=mapAgentDetails.get("distributorID");
					String loginID="";
					HashMap<String,String> dynamicDetailsMap=daoObj.getDynamicDetails(dsid);
					
					HashMap<String,String> mailerMap=CommonServiceDao.getDynamicDetails(userId);
					
					String mailerId=mailerMap.get("Mailer_id");
					String mailerPassword=mailerMap.get("Mailer_password");
					String smsId=mailerMap.get("SMS_id");
					String smsPassword=mailerMap.get("SMS_password");

					loginID=mobileNo;

					String message="Congrats! Your mobile successfully Activated. Your User Id is "+mobileNo+" and MPIN is "+password+". Delete this SMS after storing MPIN at safe place";
					SendSmsOnMobile.sendMobileSmsSMSZONE(mobileNo,message);		

					String agentLoginUrl=(String)dynamicDetailsMap.get("agentLoginUrl");
					mapAgentDetails.put("agentLoginUrl", agentLoginUrl);
					mapAgentDetails.put("LoginID", emailId);
					mapAgentDetails.put("password", password);
					mapAgentDetails.put("agentLoginUrl", agentLoginUrl);
					String helpEmailID=(String)dynamicDetailsMap.get("helpEmailId");
					
					String recipients[]={emailId};
					String ccRecipients[] = {distributorEmailId,helpEmailID};
			        String bccRecipients[] = {PropertyFile.REGISTRATION_EMAIL};

					String subject="User Registration - Agent ";
					String Messageagnt="<html><BODY cellpadding=\"0\" cellspacing=\"0\"><table border=\"0\"  width=\"100%\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" hspace=\"0\" vspace=\"0\"  bordercolor=\"#FFFFFF\"><tr><td><table height=\"200\" ><tr><td style='font-family:\"Trebuchet MS\";font-size:14px;'>Dear &nbsp;User,</td></tr>"+
							"<p><tr><td>"+
							"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td colspan='3'>&nbsp;</td></tr><tr><td colspan='3' style='font-family:\"Trebuchet MS\";font-size:14px;'>Your Agent ID has been registered. Your Login Details are given below.<br></td></tr>"+
							"<tr><td colspan='3'>&nbsp;<br></td></tr>"+
							"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;width:100px;' align='left'>Login URL&nbsp;</td><td align='center' style='width:50px;font-family:\"Trebuchet MS\";font-size:14px;'>:</td><td align='left' style='font-family:\"Trebuchet MS\";font-size:14px;'><a href=\""+agentLoginUrl+"/\">"+agentLoginUrl+"</a><br></td></tr>"+
							"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;' align='left'>User ID&nbsp;</td><td align='center' style='width:50px;font-family:\"Trebuchet MS\";font-size:14px;'>:</td><td align='left' style='font-family:\"Trebuchet MS\";font-size:14px;'><font>"+loginID+"</font><br></td></tr>"+
							"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;' align='left'>Password&nbsp;</td><td align='center' style='width:50px;font-family:\"Trebuchet MS\";font-size:14px;'>:</td><td align='left' style='font-family:\"Trebuchet MS\";font-size:14px;'><font>"+password+"</font><br></td></tr>"+
							"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;' align='left'>Agent ID&nbsp;</td><td align='center' style='width:50px;font-family:\"Trebuchet MS\";font-size:14px;'>:</td><td align='left' style='font-family:\"Trebuchet MS\";font-size:14px;'><font>"+completeAgentId+"</font><br></td></tr>"+
							"<tr><td colspan='3'>Mobile App Login Details<br></td></tr>"+
							"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;' align='left'>App User ID&nbsp;</td><td align='center' style='width:50px;font-family:\"Trebuchet MS\";font-size:14px;'>:</td><td align='left' style='font-family:\"Trebuchet MS\";font-size:14px;'><font>"+mobileNo+"</font><br></td></tr>"+
							"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;' align='left'>App Password&nbsp;</td><td align='center' style='width:50px;font-family:\"Trebuchet MS\";font-size:14px;'>:</td><td align='left' style='font-family:\"Trebuchet MS\";font-size:14px;'><font>"+password+"</font><br></td></tr>"+
							"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;' align='left'>Partner Email Id&nbsp;</td><td align='center' style='width:50px;font-family:\"Trebuchet MS\";font-size:14px;'>:</td><td align='left' style='font-family:\"Trebuchet MS\";font-size:14px;'><font>"+distributorEmailId+"</font><br></td></tr>"+
							"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;' align='left'>Partner ID&nbsp;</td><td align='center' style='width:50px;font-family:\"Trebuchet MS\";font-size:14px;'>:</td><td align='left' style='font-family:\"Trebuchet MS\";font-size:14px;'><font>"+dsid+"</font><br></td></tr>"+
							"<tr><td colspan='3'>&nbsp;<br></td></tr>"+
							"<tr><td colspan='3' style='font-family:\"Trebuchet MS\";font-size:14px;'>Please always quote your Agent ID in further communication with us.<br><br></tr>"+
							"</table></td></tr></p>"+
							"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;'>Regards,</td></tr>"+
							"<tr><td>&nbsp;</td></tr>"+
							"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;'>Team Activation</td></tr><br>"+				   
							"</tr></td></table></BODY></html>";
					try{
						
						SendSmtpMail.sendSSLMessageBCC(recipients,ccRecipients,bccRecipients,subject,Messageagnt,helpEmailID,mailerId,mailerPassword);
						
					
					}catch (Exception e) {
						e.printStackTrace();
						System.out.println("AgentRegistrationAction.execute()");
					}
					request.setAttribute("agentRegistrationDetails",mapAgentDetails);
					request.setAttribute("message","Process has been completed Successfully.");
					return "registrationCompleted";
				
				}else
				{
					request.setAttribute("message","Process aborted due to Technical Failure.");
					return "registrationPage";
				}
			}
			else if(param.equalsIgnoreCase("checkDSID"))
			{
				String dsID=request.getParameter("dsID");
				String userType=(String)session.get("loginType");
				String user_Id=(String)session.get("userId");
				AgentRegistrationDao daoObj=new AgentRegistrationDao();
				String status=daoObj.checkDistributorIdStatus(dsID,user_Id,userType);
				PrintWriter out=response.getWriter();
				out.print(status);
				return null;
			}
			else if(param.equalsIgnoreCase("checkUserName"))
			{
				String loginUserName=request.getParameter("loginUserName");
				AgentRegistrationDao daoObj=new AgentRegistrationDao();
				String status=daoObj.checkUserName(loginUserName);
				PrintWriter out=response.getWriter();
				out.print(status);
				return null;
			}
			else if(param.equalsIgnoreCase("checkMobileNo"))
			{
				String mobileNo=request.getParameter("mobileNo");
				AgentRegistrationDao daoObj=new AgentRegistrationDao();
				String status=daoObj.checkMobileNo(mobileNo);
				PrintWriter out=response.getWriter();
				out.print(status);
				return null;
			}
		}
		catch(Exception e)
		{
			request.setAttribute("message","Process aborted due to Technical Failure.");
			System.out.println("Exception in AgentRegistrationAction");
			System.out.println(e.toString());
			return "registrationPage";
		}
		return null;   
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
