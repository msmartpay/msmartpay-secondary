package com.Password;



import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.common.CommonDetailDao;
import com.common.LogWriter;
import com.common.SendSmsOnMobile;
import com.common.SendSmtpMail;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;


public class PasswordAction extends ActionSupport implements ModelDriven<Object>, ServletRequestAware,ServletResponseAware 
{

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	Logger logger = Logger.getLogger(PasswordAction.class);
	LogWriter log=new LogWriter();
	private  PasswordFormBean PasswordFormBean=new PasswordFormBean();
	public Object getModel(){return PasswordFormBean;}
	public String execute() throws Exception { 
		try 
		{ 
			Map session = ActionContext.getContext().getSession();
			String param=PasswordFormBean.getParam();
			log.print("param value in PasswordAction is "+param, logger);


			PasswordDao PasswordDao=new PasswordDao();

			if(param.equals("changePassword"))

			{
				String userId=(String)session.get("distributorId");
				if(userId==null){
					String  message2="Your session has been expire ,relogin to continue.";

					request.setAttribute("Loginmessage",message2);
					return "sessionexpired"; 
				}
				String oldPassword=PasswordFormBean.getOldPassword();
				System.out.println("oldPassword--"+oldPassword);
				String NewPassword=PasswordFormBean.getNewPassword();
				System.out.println("NewPassword--"+NewPassword);

				String clientType=(String)session.get("clientType"); 
				System.out.println("clientType----->>>"+clientType);


				//----------------------Call to get original password of user-------------------------
				String CheckOldPassword=PasswordDao.CheckOldPassword(userId,clientType);

				if(CheckOldPassword.equalsIgnoreCase(oldPassword))
				{
					String  changepassword=PasswordDao.changePassword(userId,NewPassword,clientType);
					String message="Password Has Been change Sucessfully.";
					request.setAttribute("message",message);
					return  "changePassword";
				}
				else
				{
					String message="Old Password Is Not Correct";
					request.setAttribute("message",message);

					return  "changePassword";
				}

			}


			//--------------------------------Block for Reset Password-------------------
			if(param.equals("ResetPassword"))

			{
				String userId=(String)session.get("distributorId");
				if(userId==null){
					String  message2="Your session has been expire ,relogin to continue.";
					request.setAttribute("Loginmessage",message2);
					return "sessionexpired"; 
				}
				// String AgentCompleteId=(String)request.getParameter("AgentCompleteId");
				// System.out.println("AgentCompleteId--"+AgentCompleteId);
				String AgentName=PasswordFormBean.getAgentName();
				// System.out.println("AgentName--"+AgentName);

				String agenEmailId=PasswordFormBean.getAgenEmailId(); 
				//System.out.println("agenEmailId----->>>"+agenEmailId);
				String agentOnlyId=PasswordFormBean.getAgentOnlyId();; 
				//System.out.println("agentOnlyId----->>>"+agentOnlyId);
				String mobileNo=PasswordFormBean.getMobileNo(); 
				//System.out.println("mobileNo----->>>"+mobileNo);
				String clientType=(String)session.get("clientType"); 
				//System.out.println("clientType----->>>"+clientType);
				//---------------------call to get password----------------------------------------------------	  
				String  password=RandomStringUtils.randomNumeric(6);
				//System.out.println("password----"+password);
				String domainName=(String)session.get("domainName"); 
				//System.out.println("domainName----->>>"+domainName);
				String DistributorMailId=(String)session.get("DistributorMailId"); 
				//System.out.println("DistributorMailId----->>>"+DistributorMailId);

				String resetpassword=PasswordDao.setPassword(password,agentOnlyId,clientType);
				String WhitelableCompanyName=(String)session.get("WhitelableCompanyName");
				String agentLoginUrl="";
				if(clientType.equalsIgnoreCase("TEP"))
				{
					agentLoginUrl= domainName+"/"+"agent";
				}
				if(clientType.equalsIgnoreCase("REP"))
				{
					agentLoginUrl= domainName+"/"+"arep";  
				}

				//System.out.println("WhitelableCompanyName-------------"+WhitelableCompanyName);
				if(resetpassword.equalsIgnoreCase("reset"))
				{
					// String recipients[]={"amit.pathak@commsoft.in"};
					String recipients[]={agenEmailId};
					String subject="Password Reset for Your "+WhitelableCompanyName+" Account";
					String Message="<html xmlns=\"http://www.w3.org/1999/xhtml\">"+
							"<head>"+
							"<style type=\"text/css\">"+
							"td.border "+
							"{"+
							"border-width:1px;"+
							"border-color: 	#be9b61;"+
							"border-style:solid;"+
							"}"+
							"</style>"+
							"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\" />"+


	 			"</head><body>"+
	 			"<table>"+
	 			"<p align=justify>Dear "+AgentName+",</p><p align=justify>Your Password has been reset for your "+WhitelableCompanyName+" account. Password is "+password+"."+ 
	 			"Please go through below URL to access your account."+
	 			"<a href=\""+agentLoginUrl+"\">"+agentLoginUrl+"</a>"+
	 			". </p><p align=justify>"+
	 			"</div>"+
	 			"<p style=\"font-family:Trebuchet MS\">Kindly change your password for your security purpose<br />"+
	 			"</p>"+
	 			"<p style=\"font-family:Trebuchet MS\">Warm regards,<br />"+
	 			"Team Activation<br />"+
	 			"<a href="+domainName+">"+WhitelableCompanyName+"</a> "+
	 			"</p></td>"+
	 			"</tr></table>"+
	 			"</center>"+
	 			"</body></html>";
					
					HashMap<String,String> mailerMap=CommonDetailDao.getDynamicMailerDetails(userId);
					
					String mailerId=mailerMap.get("Mailer_id");
					String mailerPassword=mailerMap.get("Mailer_password");
					String smsId=mailerMap.get("SMS_id");
					String smsPassword=mailerMap.get("SMS_password");
					
					SendSmtpMail.sendSSLMessage(recipients, subject, Message,DistributorMailId,mailerId,mailerPassword);

					String testmessage="Dear Agent, your password just reset and new password is "+password+". Kindly change your password for your security purpose.";
					testmessage=testmessage.replaceAll(" ", "%20"); 
					SendSmsOnMobile.sendMobileSmsSMSZONE(mobileNo,testmessage);

					String  message2="Activate";

					System.out.println("befre---------------semding");
					session.put("checkStatusReset",message2); 
					return "reset"; 

				}


			} 
			//----------------------Call to forget password of user-------------------------
			if(param.equals("ForgetPassword"))

			{


				System.out.println("in ForgetPassword");
				String agenEmailId=PasswordFormBean.getAgenEmailId(); 


				HashMap hmticker=(HashMap)session.get("hashmap");

				String companyname=(String)hmticker.get("company_name");
				//System.out.println("companyname--"+companyname);
				String Distributor_login_url=(String)hmticker.get("Distributor_login_url");
				//System.out.println("Distributor_login_url--"+Distributor_login_url);

				String distributor_help_mobile_no1=(String)hmticker.get("distributor_help_mobile_no1");
				//System.out.println("distributor_help_mobile_no1--"+distributor_help_mobile_no1);
				String distributor_help_email_id1=(String)hmticker.get("distributor_help_email_id1");
				//System.out.println("distributor_help_email_id1@@@@@"+distributor_help_email_id1);
				String clientID=(String)hmticker.get("Client_Id");
				String Client_Type=(String)hmticker.get("Client_Type");


				String msg="";


				HashMap<String,Object> hm=PasswordDao.forgetPassword(agenEmailId,Client_Type);
				String status=(String)hm.get("status");
				String userId=(String)hm.get("distributorId");
				String userIdInitial=(String)hm.get("distributorInitial");
				String fullId=userIdInitial+userId;
				String pwd=(String)hm.get("password");
				System.out.println("Status is"+" "+status);
				if(status.equals("invalid"))
				{
					msg="<html><body><table><tr><td style=\"font-family: arial,sans-serif; font-size: 9pt;color:red;\">Wrong E-mail id, Please enter your registered e-mail id.</td></tr></table></body></html>";
					request.setAttribute("message",msg);
					return  "mailsent";
				}
				else
				{
					String recipients[]={agenEmailId};  //email entered by client
					String subject=""+companyname+" password";
					String Message="<html><BODY cellpadding=\"0\" cellspacing=\"0\"><table border=\"0\"  width=\"100%\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" hspace=\"0\" vspace=\"0\"  bordercolor=\"#FFFFFF\"><tr><td><table height=\"200\" ><tr><td >Dear &nbsp;Sir,</td></tr><br>"+
							"<tr><td><p> We have received a password request and your password is&nbsp;<b>"+pwd+"</b>. Please change it in next login. </p> </td></tr><br>"+
							"<p><tr><td>"+
							"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td>&nbsp;&nbsp;&nbsp;&nbsp;Use the following username and password to login<br></tr></td>"+
							"<tr><td>&nbsp;<br></td></tr>"+
							"<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Username:&nbsp;<font color=\"green\" >"+agenEmailId+"</font><br></td></tr>"+
							"<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Password:&nbsp;<font color=\"green\" >"+pwd+"<br></td></tr>"+
							"<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;<a href="+Distributor_login_url+"  style=\" text-decoration:none; color:green;  font-size: 9pt;\" > Click here to login</a></td></tr>"+
							"</table></td></tr></p><br>"+

						  	"<td><tr><p>Thanks for your cooperation.</p></td></tr><br>"+
						  	"<tr><td ><p>For more details you can contact @ our helpdesk "+distributor_help_mobile_no1+" or mail us "+distributor_help_email_id1+"</p></td></tr><br>"+
						  	"<tr><td ><p> Keep writing us to make it more users friendly.</p><br></td></tr>"+

							"<tr><td ><p>Regards</p></td></tr><br>"+
							"<tr><td><p>"+companyname+"</p><br></td></tr></table></td></tr>"+
							"</tr></td></table></BODY></html>";

					HashMap<String,String> mailerMap=CommonDetailDao.getDynamicMailerDetails(userId);
					
					String mailerId=mailerMap.get("Mailer_id");
					String mailerPassword=mailerMap.get("Mailer_password");
					String smsId=mailerMap.get("SMS_id");
					String smsPassword=mailerMap.get("SMS_password");
					
					SendSmtpMail.sendSSLMessage(recipients, subject, Message,distributor_help_email_id1,mailerId,mailerPassword);

					msg="<html><body><table><tr><td style=\"font-family: arial,sans-serif; font-size: 9pt;color:Green;\">Dear Sir, we have sent your password on you registered email address.</td></tr></table></body></html>";
					request.setAttribute("message",msg);
					return  "mailsent";

				}

			}
		}

		catch(Exception ex){
			ex.printStackTrace();
			request.setAttribute("message", "Due to some technical issue we are unable to proceed your request");
			return "error";
		}
		return "error";

	}

	@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
	}

	@Override
	public void setServletResponse(HttpServletResponse arg0) {

	}





}
