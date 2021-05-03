
/**
 *  Created Date---15/6/2012
 *  Created By---Amit Pathak 
 *  Purpose- The class(ForgotPasswordAction)is created for getting new password.
 *  Updated Date-15/6/2012
 *  Updated By--Amit Pathak 
 *  Update Purpose-
*/


package com.forgotPassword;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.UUID;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import com.common.Encryption;
import com.common.SendSmtpMail;
import com.opensymphony.xwork2.ActionSupport;

public class ForgotPasswordAction extends ActionSupport implements ServletRequestAware,ServletResponseAware 
{
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	ForgotPasswordDao dao=new ForgotPasswordDao();
	
	
	 public String sendForgetPassword()
	    {
	        System.out.println("ForgotPasswordAction class and Method is : sendForgetPassword");
	        String url = "";
	        String emailId = request.getParameter("emailID");
	        int existenceId = 0;
	        HashMap existDetails = dao.getStatus(emailId);
	        if(existDetails.size() == 0)
	        {
	            request.setAttribute("message", "You have entered wrong E-mail ID.");
	        } else
	        {
	            existenceId = ((Integer)existDetails.get("userId")).intValue();
	            if(existenceId == 1)
	                url = "http://smartkinda.com";
	            else
	                url = (String)existDetails.get("adminLoginUrl");
	            String password = UUID.randomUUID().toString();
	            password = password.substring(0, password.indexOf("-"));
	            String pass = "";
	            try
	            {
	                pass = Encryption.SHA1(password);
	            }
	            catch(NoSuchAlgorithmException e)
	            {
	                e.printStackTrace();
	            }
	            catch(UnsupportedEncodingException e)
	            {
	                e.printStackTrace();
	            }
	            HashMap getDetails = dao.getDetails(existenceId, pass);
	            if(getDetails.size() > 0)
	            {
	                String userName = (String)getDetails.get("userName");
	                String emailFrom = "support@smartkinda.com";
	                String recipients[] = {
	                    emailId
	                };
	                String subject = "Account Information - Admin User";
	                
	                String Message="<html><BODY cellpadding=\"0\" cellspacing=\"0\"><table border=\"0\"  width=\"100%\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" hspace=\"0\" vspace=\"0\"  bordercolor=\"#FFFFFF\"><tr><td><table height=\"200\" ><tr><td style='font-family:\"Trebuchet MS\";font-size:14px;'>Dear &nbsp;Sir/Madam,</td></tr>"+
					"<p><tr><td>"+
					"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td colspan='3'>&nbsp;</td></tr><tr><td colspan='3' style='font-family:\"Trebuchet MS\";font-size:14px;'>We have received a password request from you. Your Account Information is as follows:<br></td></tr>"+
					"<tr><td colspan='3'>&nbsp;<br></td></tr>"+
					"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;width:100px;' align='left'>Login URL&nbsp;</td><td align='center' style='width:50px;font-family:\"Trebuchet MS\";font-size:14px;'>:</td><td align='left' style='font-family:\"Trebuchet MS\";font-size:14px;'><a href=\""+url+"/\">"+url+"</a><br></td></tr>"+
					"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;' align='left'>User ID&nbsp;</td><td align='center' style='width:50px;font-family:\"Trebuchet MS\";font-size:14px;'>:</td><td align='left' style='font-family:\"Trebuchet MS\";font-size:14px;'><font>"+userName+"</font><br></td></tr>"+
					"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;' align='left'>Password&nbsp;</td><td align='center' style='width:50px;font-family:\"Trebuchet MS\";font-size:14px;'>:</td><td align='left' style='font-family:\"Trebuchet MS\";font-size:14px;'><font>"+password+"</font><br></td></tr>"+
					"<tr><td colspan='3'>&nbsp;<br></td></tr>"+
					"</table></td></tr></p>"+
					"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;'>Regards,</td></tr>"+
					"<tr><td>&nbsp;</td></tr>"+
					"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;'>Team Activation</td></tr><br>"+				   
					"</tr></td></table></BODY></html>";
	                
	                try
	                {
	                    String mailStatus = SendSmtpMail.sendSSLMessage(recipients, subject, Message, emailFrom);
	                    if("MailSuccesfullysend".equalsIgnoreCase(mailStatus))
	                        request.setAttribute("message", "Your Password has been sent on your E-mail ID.");
	                }
	                catch(MessagingException e)
	                {
	                    e.printStackTrace();
	                }
	            } else
	            {
	                request.setAttribute("message", "Process aborted due to Technical Failure.");
	            }
	        }
	        return "forgetPass";
	    }

	
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
	}

	public void setServletResponse(HttpServletResponse response) {
	}
}


