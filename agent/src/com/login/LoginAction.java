package com.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public final class LoginAction extends ActionSupport {
	
	Logger logger=Logger.getLogger(LoginAction.class);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpSession session;
	
	public String execute()throws Exception {
		
		request=ServletActionContext.getRequest();
		session=request.getSession();
		
		String clientId="";
		try
		{
			String url="";
			clientId=(String)session.getAttribute("clientId");
			if(clientId==null){
				request.setAttribute("message", "Your Login Session has Expired. Please Login Again.");
				return "sessionExp";
			}
			String param=request.getParameter("param");
			
			logger.info("TEP ,Class is LoginAction ,Param is "+param);

			if("login".equalsIgnoreCase(param)){
				String userName=request.getParameter("userName");
				String pass=request.getParameter("pass");
				String userType=request.getParameter("userType");
				
				LoginDao daoObj=new LoginDao();
				
				url=daoObj.getLoginStatus(userName,pass,userType,request);
				if(url.equalsIgnoreCase("invalidUser")){
					request.setAttribute("message", "Your User Name or Password is Invalid.");
				}
				else if(url.equalsIgnoreCase("deactiveUser")){
					request.setAttribute("message", "Your Account is not Active. Please contact your Channel Partner.");
				}
				else if(url.equalsIgnoreCase("blockedUser")){
					request.setAttribute("message", "Login Disallowed. Please contact your Channel Partner.");
				}
				else if(url.equalsIgnoreCase("mobVerifyPage")){
					request.setAttribute("message", "Please enter Verification Code sent on your Mobile Number. If DND is Registered on Your Mobile Number, Contact your Channel Partner.");
				}else if(url.equalsIgnoreCase("error")){
					request.setAttribute("message", "We are facing technical issues. Please try later.");
				}else{
					
					session.setAttribute("DMRVendor", daoObj.fetchVendor("DMR"));
					
					session.setAttribute("operatorListDthConLoc",daoObj.getDthConnLocation());
					session.setAttribute("operatorListDthConProduct",daoObj.getDthConnectionOperator());
					
				}
				daoObj=null;
				logger.info("url : "+url);
				return url;
				
			}
			else if(param.equalsIgnoreCase("verifyMobileId")){
				
				String varifyMobileCode=request.getParameter("varifyMobileCode");
				if(varifyMobileCode==null){
					return "sessionExp";
				}
				String agentID=request.getParameter("agentID");
				LoginDao daoObj=new LoginDao();
				String status=daoObj.checkMobileVerificationCode(agentID,varifyMobileCode);
				if(status.equalsIgnoreCase("fail")){
					request.setAttribute("message", "Please enter correct code.");
					url="varifyCode";
				}else if(status.equalsIgnoreCase("error")){
					request.setAttribute("message", "We are facing technical issues. Please try later.");
					url="varifyCode";
				}else if(status.equalsIgnoreCase("success")){
					url="agentHome";
				}
				return url;
			}	
			else if(param.equalsIgnoreCase("logout")){
				request.setAttribute("message", "You are logged out Successfully");
				session.invalidate();
				return "logout";
			}
		}  
		catch (Exception ex) {
			logger.info("TEP :: Exception in LoginAction class ");
			logger.info("LoginAction.execute()"+ex.getMessage());
		}
		return "ERROR";

  }
	
}
