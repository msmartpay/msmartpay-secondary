/*
  Class Property :  This class (AgentRegistrationAction) is created for adding new agents in system.

  Created Date   : 19-Nov-2011 at 10:20 PM.
  Created By     : Amit Pathak

  Updated Date   : 13-DEC-2012 
  Update By      : Monika Nethani

 */

package com.agentregistration;


import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.agentDetails.AgentDetailForm;
import com.common.CommonDetailDao;
import com.common.LogWriter;
import com.common.PropertyFile;
import com.common.SendSmsOnMobile;
import com.common.SendSmtpMail;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AgentRegistrationAction extends ActionSupport implements ModelDriven<Object>,ServletRequestAware,ServletResponseAware {

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;

	Logger logger = Logger.getLogger(AgentRegistrationAction.class);
	LogWriter log=new LogWriter();
	private  AgentDetailForm AgentDetailForm=new AgentDetailForm();

	public Object getModel(){
		return AgentDetailForm;		
	}	
	public String execute() throws Exception { 		
		try{				
			Map session = ActionContext.getContext().getSession();
			String param=AgentDetailForm.getParam();
			log.print("param value in AgentRegistrationAction is "+param, logger);	

			if(session.get("distributorId")==null){
				String  message2="Your Login session has Expired. Please Login to Continue.";
				request.setAttribute("Loginmessage",message2);		  
				return "sessionexpired"; 
			}		
			String distributorInitial=(String)session.get("distributorInitial");
			String userId=(String)session.get("distributorId");
			AgentRegistrationDao AgentRegistrationDao=new AgentRegistrationDao();

			if("AgentRegistration".equalsIgnoreCase(param)){			
				String PanNo="";
				String firstName=AgentDetailForm.getFirstname();   			 
				String lastname=AgentDetailForm.getLastname(); 
				if(lastname==null){
					lastname="";
				}		
				String dateofbirth =(String)request.getParameter("dateofbirth");
				String gender=AgentDetailForm.getGender();			
				String companyType=AgentDetailForm.getCompanyType();			
				String companyName=AgentDetailForm.getAgencyName();			
				String officeAddress1=AgentDetailForm.getAddress1();
				String officeAddress2=AgentDetailForm.getAddress2();
				String officeState=AgentDetailForm.getState();
				String officeDistrict=AgentDetailForm.getDistrict();
				String officeCity=AgentDetailForm.getCity();					
				String officeCountry=AgentDetailForm.getOfficeCountry();
				String officePincode=AgentDetailForm.getOfficePinCode();
				//	String officeMobileNo=AgentDetailForm.getOfficeMobileNo();				
				String ClientType=(String)session.get("clientType");			
				String AuthoEmailId=AgentDetailForm.getEmailId();
				String AthoMobile=AgentDetailForm.getAthoMobile();	
				String PanStatus=request.getParameter("status");				
				PanNo=AgentDetailForm.getPanNo();
				String emailResponse=AgentRegistrationDao.chkEmail(AuthoEmailId);
				if(emailResponse.equals("invalid")){					
					String message="User Name is Duplicate.";
					request.setAttribute("message",message);
					return "regAgent";
				}			
				String mobileResponse=AgentRegistrationDao.chkMobile(AthoMobile);
				if(mobileResponse.equals("invalid")){
					String message="Mobile Number is Duplicate.";					
					request.setAttribute("message",message);
					return "regAgent";
				}			
				String password=RandomStringUtils.randomNumeric(6);				
				String clientType=(String)session.get("clientType");			

				HashMap<String,Object> mapAgentDetails=AgentRegistrationDao.registerAgent(distributorInitial,userId,firstName,lastname,dateofbirth,gender,companyType,companyName,PanNo,officeAddress1,officeAddress2,officeState,officeDistrict,officeCountry,officeCity,officePincode,AuthoEmailId,password,AthoMobile);
				if(mapAgentDetails==null){
					request.setAttribute("message", "Process aborted due to Technical Failure.");	
					return "home";	
				}

				//String agentId=(String)mapAgentDetails.get("agentId");				
				String completeAgentId=(String)mapAgentDetails.get("completeAgentId");			
				String distributorEmailId=(String)mapAgentDetails.get("distributorEmailId");
				String clientFlag=(String)mapAgentDetails.get("clientFlag");			
				System.out.println(mapAgentDetails);		
				
				String agentLoginUrl=(String)session.get("agentLoginUrl");			  
				String agentCellEmailId=(String)session.get("agentCellEmailId");
				System.out.println("agentCellEmailId----->>>>>>>>>>>>>>>>>>"+agentCellEmailId);		   


				String distributor_initial=(String)session.get("distributorInitial");
				String disCompleteId=distributor_initial+userId;
				
				HashMap<String,String> mailerMap=CommonDetailDao.getDynamicMailerDetails(userId);
				
				String mailerId=mailerMap.get("Mailer_id");
				String mailerPassword=mailerMap.get("Mailer_password");
				String smsId=mailerMap.get("SMS_id");
				String smsPassword=mailerMap.get("SMS_password");

				System.out.println("clientFlag : "+clientFlag);
				System.out.println(AuthoEmailId+" : "+distributorEmailId+" : "+agentCellEmailId);

				String recipientsagnt[]={AuthoEmailId};//emailId
		        String bccRecipients[] = {distributorEmailId,agentCellEmailId,PropertyFile.supportMail};
				String subjectagnt="User Registration – Retailer";
				String Messageagnt="<html><BODY cellpadding=\"0\" cellspacing=\"0\"><table border=\"0\"  width=\"100%\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" hspace=\"0\" vspace=\"0\"  bordercolor=\"#FFFFFF\"><tr><td><table height=\"200\" ><tr><td style='font-family:\"Trebuchet MS\";font-size:14px;'>Dear &nbsp;User,</td></tr>"+
						"<p><tr><td>"+
						"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td colspan='3'>&nbsp;</td></tr><tr><td colspan='3' style='font-family:\"Trebuchet MS\";font-size:14px;'>Your Retailer ID has been registered. Your Login Details are given below.<br></td></tr>"+
						"<tr><td colspan='3'>Web Login Details<br></td></tr>"+
						"<tr><td colspan='3'><br></td></tr>"+
						"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;width:150px;' align='left'>Login URL&nbsp;</td><td align='center' style='width:50px;font-family:\"Trebuchet MS\";font-size:14px;'>:</td><td align='left' style='font-family:\"Trebuchet MS\";font-size:14px;'><a href=\""+agentLoginUrl+"\">"+agentLoginUrl+"</a><br></td></tr>"+
						"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;' align='left'>Agent ID&nbsp;</td><td align='center' style='width:50px;font-family:\"Trebuchet MS\";font-size:14px;'>:</td><td align='left' style='font-family:\"Trebuchet MS\";font-size:14px;'><font>"+completeAgentId+"</font><br></td></tr>"+
						"<tr><td colspan='3'>Mobile App Login Details<br></td></tr>"+
						"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;' align='left'>User ID&nbsp;</td><td align='center' style='width:50px;font-family:\"Trebuchet MS\";font-size:14px;'>:</td><td align='left' style='font-family:\"Trebuchet MS\";font-size:14px;'><font>"+AthoMobile+"</font><br></td></tr>"+
						"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;' align='left'>Password&nbsp;</td><td align='center' style='width:50px;font-family:\"Trebuchet MS\";font-size:14px;'>:</td><td align='left' style='font-family:\"Trebuchet MS\";font-size:14px;'><font>"+password+"</font><br></td></tr>"+
						"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;' align='left'>Partner Email Id&nbsp;</td><td align='center' style='width:50px;font-family:\"Trebuchet MS\";font-size:14px;'>:</td><td align='left' style='font-family:\"Trebuchet MS\";font-size:14px;'><font>"+distributorEmailId+"</font><br></td></tr>"+
						"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;' align='left'>Partner ID&nbsp;</td><td align='center' style='width:50px;font-family:\"Trebuchet MS\";font-size:14px;'>:</td><td align='left' style='font-family:\"Trebuchet MS\";font-size:14px;'><font>"+disCompleteId+"</font><br></td></tr>"+
						"<tr><td colspan='3'>&nbsp;<br></td></tr>"+
						"<tr><td colspan='3' style='font-family:\"Trebuchet MS\";font-size:14px;'>Please always quote your Retailer ID in further communication with us.<br><br></tr>"+
						"</table></td></tr></p>"+
						"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;'>Regards,</td></tr>"+
						"<tr><td>&nbsp;</td></tr>"+
						"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;'>Team Activation</td></tr><br>"+				   
						"</tr></td></table></BODY></html>";				
				try {
					
					String smsmessage="Congrats! Your mobile successfully Activated. Your User Id is "+AthoMobile+" and MPIN is "+password+". Delete this SMS after storing MPIN at safe place";
					SendSmsOnMobile.sendMobileSmsSMSZONE(AthoMobile,smsmessage);	
					
					SendSmtpMail.sendSSLMessageBCC(recipientsagnt,bccRecipients,subjectagnt,Messageagnt,agentCellEmailId,mailerId,mailerPassword);
					
				} catch (Exception e) {
					System.out.println("AgentRegistrationAction.execute()"+e.getMessage());
				}



				//double dutAmount =(Double)session.get("agent_creation_charge");
				//System.out.println("dutAmount   "+dutAmount);
				//String IpAdress=(String)request.getRemoteAddr();						
				//String trnId=AgentRegistrationDao.getTranId();
				//String refrId=AgentRegistrationDao.getTranId();		
				//String update = AgentRegistrationDao.updateAgentCreationCharge(userId,trnId,refrId,IpAdress,dutAmount,completeAgentId);					
				String message="Thanks for Registering with us. Please check your Email for Information regarding User ID & Password.";
				request.setAttribute("message",message);
				return "home";				
			}				
			if(param.equalsIgnoreCase("CheckEmail")){				 
				String email=(String)request.getParameter("email");	
				String status=AgentRegistrationDao.checkEmail(email);
				System.out.println("Status :: "+status);
				PrintWriter out=response.getWriter();
				System.out.println(out);
				out.print(status);
				return null;				
			}
			if(param.equalsIgnoreCase("CheckMob")){				 
				String mob=request.getParameter("mob");				  
				String status=AgentRegistrationDao.checkMob(mob);
				System.out.println("Status :: "+status);
				PrintWriter out=response.getWriter();		  
				out.print(status);
				return null;		  
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
			request.setAttribute("message", "Process aborted due to Technical Failure.");
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
		this.response =arg0;
	}	

}