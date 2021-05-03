package com.forgetpassword;

import java.io.PrintWriter;
import java.sql.Connection;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//import javax.sql.DataSource;
//import com.agentDep.SendSmtpMail;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.common.SendSmtpMail;
import com.login.LoginDao;
public class ForgetPwdAction extends Action {
	
	public ActionForward execute(ActionMapping mapping,
			 ActionForm form,
			 HttpServletRequest request,
			 HttpServletResponse response)
throws Exception {
				
		        HttpSession session=request.getSession(true);
	     
			    HashMap info=(HashMap)session.getAttribute("info");
			    String clientId=(String)session.getAttribute("clientId");
				HashMap map=ForgetPwdBusinessDelegater.getDynamicDetails(clientId);
			    
				String md_login_url=(String)map.get("md_login_url");
				String logo_name=(String)info.get("md_header_home_image");
				String help_mobile_no=(String)info.get("Help_mobileNo1");
				String help_email_id=(String)map.get("Help_email_id1");
				String domain_name=(String)map.get("domain_name");
				String company_name=(String)map.get("company_name");
				String agent_cell_email_id=(String)map.get("Agent_cell_email_id");
				session.setAttribute("md_header_home_image",logo_name);
				session.setAttribute("Help_mobileNo1",help_mobile_no);
			    String param=(String)request.getParameter("param");
			    System.out.println("info >>>>>>>>>>>>>>>>>>>>>>>>>is"+" "+info);
			if (param.equals("forgetPwd"))
			{
				
				return mapping.findForward("forgetpassword");
			}	
            
			if(param.equals("sendEmail"))
		{
				String  clientIp= request.getParameter("clientIp");
				System.out.println("Ip address is"+clientIp); 
				String emailId=request.getParameter("emailId");
				String msg="";
				
                HashMap hm=ForgetPwdBusinessDelegater.chkEmail(emailId,clientIp,session);
			    String status=(String)session.getAttribute("status");
                String userId=(String)hm.get("userId");
				System.out.println("userid========"+userId);
				String completeId=(String)hm.get("completeId");
				String userName=(String)hm.get("userName");
				String pwd=(String)hm.get("pwd");

             //   System.out.println("hello we r cuming here after calling dao method");
                System.out.println("Status is==========="+" "+status);
				if(status.equalsIgnoreCase("invalid"))
			{
              msg="Email-Id Doesn't Exists.Please Enter Correct E-mail Address.";
			  request.setAttribute("msg",msg);
			  return  mapping.findForward("forgetpassword");
			}
			else
			{
		 String recipients[]={emailId};  
		 // updated by Ravi Maharshi on 05/01/2013
			
		   String subject="Your Account Information";
			
			String Message="<html><BODY cellpadding=\"0\" cellspacing=\"0\"><table border=\"0\"  width=\"100%\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" hspace=\"0\" vspace=\"0\"  bordercolor=\"#FFFFFF\"><tr><td><table height=\"200\" ><tr><td style='font-family:\"Trebuchet MS\";font-size:14px;'>Dear &nbsp;User,</td></tr>"+
		
			"<p><tr><td>"+
				"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td>&nbsp;<br></td></tr><tr><td style='font-family:\"Trebuchet MS\";font-size:14px;'>Please use the following User ID & Password:<br></tr></td>"+
	             "<tr><td>&nbsp;<br></td></tr>"+
				"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;'>User ID:&nbsp;<font>"+userName+"</font><br></td></tr>"+
				"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;'>Password:&nbsp;<font>"+pwd+"<br></td></tr><tr><td>&nbsp;<br></td></tr>"+
				"<tr><td>&nbsp;<br></td></tr>"+
				"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;'>Please Change the Password at the Time of Next Login.<br><br></tr>"+
				"</table></td></tr></p>"+

	         
	             "<tr><td ><p style='font-family:\"Trebuchet MS\";font-size:14px;'>Regards,</p></td></tr>"+
			 "<tr><td ><p style='font-family:\"Trebuchet MS\";font-size:14px;'>Team Activation</p></td></tr><br>"+				   
			    "</tr></td></table></BODY></html>";
		   
		    
		   String mailStatus=SendSmtpMail.sendSSLMessage(recipients, subject, Message,help_email_id);//help@travelepoint.in 
			System.out.println("mail status is"+" "+mailStatus);
			 if (mailStatus.equalsIgnoreCase("MailSuccesfullysend"))
			 {
				 System.out.println("Mail was sent successfully,Now we r sending mail to another recipient");
				 String recipient[]={agent_cell_email_id};//agent@travelepoint.in   
				 String Sub="Password Successfully sent to "+completeId+"";
				 String mesg="<html><BODY cellpadding=\"0\" cellspacing=\"0\"><table border=\"0\"  width=\"100%\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" hspace=\"0\" vspace=\"0\"  bordercolor=\"#FFFFFF\"><tr><td><table height=\"200\" ><tr><td >Dear &nbsp;help team,</td></tr><br>"+
		"<tr><td><p> Forget Password has been sent successfully to this mds &nbsp;<b>"+completeId+"&nbsp;</b>at&nbsp;<b>"+emailId+"</b> </p> </td></tr><br>"+
		"<tr><td><p>Request was sent by mds from &nbsp;<b>"+clientIp+"&nbsp; IP address.</b></p> <br></tr></td>"+
           "<p><tr><td >Regards</td></tr></p><br>"+
		   "<tr><td><p>"+company_name+"</p><br></td></tr></table></td></tr>"+
		    "</tr></td></table></BODY></html>";
               String Status=SendSmtpMail.sendSSLMessage(recipients, Sub, mesg,help_email_id);//help@travelepoint.in  
			msg="Your Password has been sent on your E-mail ID.";
			request.setAttribute("msg",msg);
			return  mapping.findForward("forgetpassword");
			}
		}
		
	
	
	
}
return null;
}
}

