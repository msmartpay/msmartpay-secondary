package com.forgotpwd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

public final class ForgotPasswordAction extends ActionSupport{

	Logger logger=Logger.getLogger(ForgotPasswordAction.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;

	public String execute()
			throws Exception {

		request=ServletActionContext.getRequest();
		HttpSession session=request.getSession(false);

		try
		{
			String param=request.getParameter("param");	

			logger.info("TEP ,Class is ForgotPasswordAction, Param is "+param);
			if (param.equalsIgnoreCase("forgotPwdPage")){			
				return "forgotPwdPage";

			}else if(param.equalsIgnoreCase("getPassword")){
				String url="";
				String newPass=RandomStringUtils.randomNumeric(6);
				String EmailID=request.getParameter("EmailID");
				ForgotPasswordDao daoObj=new ForgotPasswordDao();
				String helpMailID=(String)session.getAttribute("helpEmailID");
				String status=daoObj.sendPassword(EmailID,helpMailID,newPass);
				if(status.equalsIgnoreCase("valid")){
					request.setAttribute("message", "Your password has been sent at your registered  mobile number and email id");
					url="loginPage";
				}else if(status.equalsIgnoreCase("invalid")){
					request.setAttribute("message", "You have entered wrong E-mail ID or Mobile No.");
					url="forgotPwdPage";
				}else if(status.equalsIgnoreCase("incompleteReg")){
					request.setAttribute("message", "Your Registration is Unsuccessful");
					url="loginPage";
				}
				daoObj=null;
				return url;
			}else{
				return "ERROR";
			}


		}catch (Exception ex) {
			logger.info("Exception in ForgotPasswordAction class ");
			logger.info("ForgotPasswordAction.execute()"+ex.getMessage());
		}
		return "ERROR";
	}
}
