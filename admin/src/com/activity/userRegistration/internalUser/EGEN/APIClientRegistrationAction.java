package  com.activity.userRegistration.internalUser.EGEN;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import com.common.SendSmtpMail;
import com.formBean.EGEN.APIClientDetailsFormBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class APIClientRegistrationAction extends ActionSupport implements ModelDriven,ServletRequestAware,ServletResponseAware
{
	
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request; 
	private HttpServletResponse response;
	private Map session;
	private APIClientDetailsFormBean apiclientdetails=new APIClientDetailsFormBean();
	
	public Object getModel()
	{
		return apiclientdetails;
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
			
			String param=apiclientdetails.getParam();
			System.out.println("APIClientRegistrationAction and Param is : "+param);
			if(param.equals("register"))
			{
				return "registrationPage";
			}
			
			else if(param.equals("registration"))
			{
				String firstName=(String)apiclientdetails.getFirstName();
				String lastName=(String)apiclientdetails.getLastName();
				String dob=apiclientdetails.getDob();
				String panNo=apiclientdetails.getPanNo();
				String agencyName=apiclientdetails.getCompanyName();
				String officeAddress=(String)apiclientdetails.getOfficeAddress();
				String addressLine2=request.getParameter("addressLine2");
				officeAddress=officeAddress+" "+addressLine2;
				String officeState=(String)apiclientdetails.getState();
				String officeDistrict=(String)apiclientdetails.getDistrict();
				String officeCountry=(String)apiclientdetails.getCountry();
				String officePincode=(String)apiclientdetails.getPincode();
				String officeMobileNo=(String)apiclientdetails.getMobileNo();
				String officeEmailId=(String)apiclientdetails.getEmailId();
				SimpleDateFormat sdf = new SimpleDateFormat("ddmmss");
				Date date=new Date();
				String randaom=sdf.format(date);
				String headerUserName=firstName+randaom;
				String userNameResponse=APIClientRegistrationDao.checkUserName(headerUserName);
				
				if(userNameResponse.equalsIgnoreCase("invalid"))
				{
					request.setAttribute("message","User name is Duplicate.");
					return "registrationPage";
				}
				String emailResponse=APIClientRegistrationDao.checkEmail(officeEmailId);
				
				if(emailResponse.equals("invalid"))
				{
					request.setAttribute("message","Email ID is Duplicate.");
					return "registrationPage";
				}
				String mobileResponse=APIClientRegistrationDao.checkMobileNo(officeMobileNo);
				
				if(mobileResponse.equals("invalid"))
				{
					request.setAttribute("message","Mobile Number is Duplicate.");
					return "registrationPage";
				}
				String headerPassword=APIClientRegistrationDao.getRandomString(10);
				String pass="";
				
				/*try 
				{ 
					pass= Encryption.SHA1(headerPassword);
				} catch (NoSuchAlgorithmException e)
				{ 
					request.setAttribute("message","Process aborted due to Technical Failure.");
					System.out.println("Exception in APIClientRegistrationAction");
					System.out.println(e.toString());
					return "err";
					
				} catch (UnsupportedEncodingException e)
				{ 
					request.setAttribute("message","Process aborted due to Technical Failure.");
					System.out.println("Exception in APIClientRegistrationAction");
					System.out.println(e.toString());
					return "err";
				} */
				pass=headerPassword;
				HashMap hamdata=APIClientRegistrationDao.APIRegistration(firstName, lastName,dob, agencyName,panNo,officeAddress,officeState, officeDistrict, officeCountry,officePincode,officeEmailId,pass,officeMobileNo, headerUserName);
             
				String regStatus=(String)hamdata.get("status");
				if(regStatus.equalsIgnoreCase("inserted"))
				{
					hamdata.put("customerAuthId",headerUserName);
					hamdata.put("customerAuthPass",pass);
					hamdata.put("aesAPIId",hamdata.get("aesApiId"));
					hamdata.put("loginUserName",officeEmailId);
					hamdata.put("loginPassword",headerPassword);
					hamdata.put("officeEmailId",officeEmailId);
					session.put("apiRegDetails",hamdata);
					String recipients[]={officeEmailId,"manoj.kumar@myaadhaarshila.com"};		
					String subject="User Registration - API Client ";
					String LoginUrl="http://api.commsoft.co.in/EGENAdmin/";
					String Messageagnt="<html><BODY cellpadding=\"0\" cellspacing=\"0\"><table border=\"0\"  width=\"100%\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" hspace=\"0\" vspace=\"0\"  bordercolor=\"#FFFFFF\"><tr><td><table height=\"200\" ><tr><td style='font-family:\"Trebuchet MS\";font-size:14px;'>Dear &nbsp;User,</td></tr>"+
					"<p><tr><td>"+
					"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td colspan='3'>&nbsp;</td></tr><tr><td colspan='3' style='font-family:\"Trebuchet MS\";font-size:14px;'>Your API ID has been registered. Your Login Details are given below.<br></td></tr>"+
					"<tr><td colspan='3'>&nbsp;<br></td></tr>"+
					"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;width:100px;' align='left'>Login URL&nbsp;</td><td align='center' style='width:50px;font-family:\"Trebuchet MS\";font-size:14px;'>:</td><td align='left' style='font-family:\"Trebuchet MS\";font-size:14px;'><a href=\""+LoginUrl+"/\">"+LoginUrl+"</a><br></td></tr>"+
					"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;' align='left'>User ID&nbsp;</td><td align='center' style='width:50px;font-family:\"Trebuchet MS\";font-size:14px;'>:</td><td align='left' style='font-family:\"Trebuchet MS\";font-size:14px;'><font>"+officeEmailId+"</font><br></td></tr>"+
					"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;' align='left'>Password&nbsp;</td><td align='center' style='width:50px;font-family:\"Trebuchet MS\";font-size:14px;'>:</td><td align='left' style='font-family:\"Trebuchet MS\";font-size:14px;'><font>"+headerPassword+"</font><br></td></tr>"+
					"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;' align='left'>Agent ID&nbsp;</td><td align='center' style='width:50px;font-family:\"Trebuchet MS\";font-size:14px;'>:</td><td align='left' style='font-family:\"Trebuchet MS\";font-size:14px;'><font>"+hamdata.get("aesApiId")+"</font><br></td></tr>"+
					"<tr><td colspan='3'>&nbsp;<br></td></tr>"+
					"<tr><td colspan='3' style='font-family:\"Trebuchet MS\";font-size:14px;'>Please always quote your Agent ID in further communication with us.<br><br></tr>"+
					"</table></td></tr></p>"+
					"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;'>Regards,</td></tr>"+
					"<tr><td>&nbsp;</td></tr>"+
					"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;'>Team Activation</td></tr><br>"+				   
					"</tr></td></table></BODY></html>";
					SendSmtpMail.sendSSLMessage(recipients,subject,Messageagnt,"support@myaadhaarshila.com");
					request.setAttribute("message","Process has been completed Successfully.");
					return "registrationCompleted";
	    		}
				else
				{
					request.setAttribute("message", "Process aborted due to Technical Failure.");
					return "registrationPage";
	    		}
			}
			else if(param.equalsIgnoreCase("checkMobileNo"))
			{
				String mobileNo=request.getParameter("mobileNo");
				String status=APIClientRegistrationDao.checkMobileNo(mobileNo);
				PrintWriter out=response.getWriter();
				out.print(status);
				return null;
			}
			else if(param.equalsIgnoreCase("checkEmailId"))
			{
				String emailId=request.getParameter("emailId");
				String status=APIClientRegistrationDao.checkEmail(emailId);
				PrintWriter out=response.getWriter();
				out.print(status);
				return null;
			}
		}
		catch(Exception e)
		{
			request.setAttribute("message","Process aborted due to Technical Failure.");
			System.out.println("Exception in APIClientRegistrationAction");
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
