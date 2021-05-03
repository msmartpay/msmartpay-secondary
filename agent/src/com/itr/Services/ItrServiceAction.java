package com.itr.Services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.utility.SendSmtpMail;



public class ItrServiceAction extends ActionSupport {

	Logger logger=Logger.getLogger(ItrServiceAction.class);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String execute() throws Exception{

		HttpServletRequest request;
		HttpSession session;
		HttpServletResponse response;
		String agentID="";
		try{

			request=ServletActionContext.getRequest();
			response=ServletActionContext.getResponse();
			session=request.getSession();
			agentID=(String)session.getAttribute("agentID");
			if(agentID==null){
				request.setAttribute("message", "Your Login Session has Expired. Please Login Again.");
				return "sessionExp";
			}

			String param=request.getParameter("param");
			logger.info("TEP ,Class is ITR Services ,Param is "+param);

			if("ITRService".equalsIgnoreCase(param)){
				return "ITRService";
			}

			else if("ITRServicesubmit".equalsIgnoreCase(param)) {

				String name=request.getParameter("name");
				String emailID=request.getParameter("emailID");
				String mobileNo=request.getParameter("mobileNo");
				String address=request.getParameter("address");
				String serviceType=request.getParameter("serviceType");

				String subject="Entity Regitration Request :: "+serviceType;

				String[] recipients={"corporatetaxsolutions@gmail.com","support@smartkinda.com"};
				String Messageagnt="<html><BODY cellpadding=\"0\" cellspacing=\"0\">" +
						"<table border=\"0\"  width=\"100%\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" hspace=\"0\" vspace=\"0\"  bordercolor=\"#FFFFFF\"><tr><td><table height=\"200\" ><tr><td style='font-family:\"Trebuchet MS\";font-size:14px;'>Dear &nbsp;Team,</td></tr>"+
						"<p><tr><td>"+
						"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\">"+
						"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;width:100px;' align='left'>Name</td><td align='center' style='width:50px;font-family:\"Trebuchet MS\";font-size:14px;'>:</td><td align='left' style='font-family:\"Trebuchet MS\";font-size:14px;'>"+name+"<br></td></tr>"+
						"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;width:100px;' align='left'>Email Id</td><td align='center' style='width:50px;font-family:\"Trebuchet MS\";font-size:14px;'>:</td><td align='left' style='font-family:\"Trebuchet MS\";font-size:14px;'><font>"+emailID+"</font><br></td></tr>"+
						"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;width:100px;' align='left'>Mobile No.</td><td align='center' style='width:50px;font-family:\"Trebuchet MS\";font-size:14px;'>:</td><td align='left' style='font-family:\"Trebuchet MS\";font-size:14px;'><font>"+mobileNo+"</font><br></td></tr>"+
						"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;width:100px;' align='left'>Addresss</td><td align='center' style='width:50px;font-family:\"Trebuchet MS\";font-size:14px;'>:</td><td align='left' style='font-family:\"Trebuchet MS\";font-size:14px;'><font>"+address+"</font><br></td></tr>"+
						"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;width:100px;' align='left'>Service Type</td><td align='center' style='width:50px;font-family:\"Trebuchet MS\";font-size:14px;'>:</td><td align='left' style='font-family:\"Trebuchet MS\";font-size:14px;'><font>"+serviceType+"</font><br></td></tr>"+
						"<tr><td colspan='3'>&nbsp;<br></td></tr>" +
						"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;'Thank You,</td></tr>"+
						"<tr><td>&nbsp;</td></tr>"+
						"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;'>SmartKinda Team\nHelpDesk No. +91-9711402774 Website : www.smartkinda.com</td></tr><br>"+				   
						"</tr></td></table></BODY></html>";

				try{
					
					SendSmtpMail.sendSSLMessage(recipients, subject, Messageagnt, "support@softsolutionzone.in");

					subject=serviceType;
					String[] recipients1={emailID};
					Messageagnt="<html><BODY cellpadding=\"0\" cellspacing=\"0\">" +
							"<table border=\"0\"  width=\"100%\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" hspace=\"0\" vspace=\"0\"  bordercolor=\"#FFFFFF\"><tr><td><table height=\"200\" ><tr><td style='font-family:\"Trebuchet MS\";font-size:14px;'>Dear &nbsp;"+name+",</td></tr>"+
							"<p><tr><td>"+
							"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\">"+
							"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;width:100px;' align='left'>Congratulations! Your request has been submited successfully. One of our concern team member will contact you soon.</td></tr>"+
							"<tr><td >&nbsp;<br></td></tr>" +
							"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;'Thank You,</td></tr>"+
							"<tr><td>&nbsp;</td></tr>"+
							"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;'>SmartKinda Team\n HelpDesk No. +91-9810917490, +91-9990180409, +91-8860198994</td></tr><br>"+				   
							"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;'>Website : www.smartkinda.com</td></tr><br>"+				   
							"</tr></td></table></BODY></html>";

					SendSmtpMail.sendSSLMessage(recipients1, subject, Messageagnt, "support@softsolutionzone.in");

					request.setAttribute("message", "Request successfully submitted. ");
					

				}catch(Exception e){
					e.printStackTrace();
				}


				return "ITRService";
			}
		}
		catch (Exception e) {
			logger.info("Exception in ITRServicesAction class");
			e.printStackTrace();
			return "ERROR";	
		}
		return null;

	}

}
