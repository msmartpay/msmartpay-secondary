package com.common;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.login.LoginDao;
import com.opensymphony.xwork2.ActionSupport;

public final class BackButtonAction extends ActionSupport{
	
	Logger logger=Logger.getLogger(BackButtonAction.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpSession session;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public String execute()
	throws Exception {
		
		
		
		String agentID="";
		try
		{
			request=ServletActionContext.getRequest();
			response=ServletActionContext.getResponse();
			session=request.getSession();
			PrintWriter printWriter=response.getWriter();
			
			if(session.getAttribute("agentID")==null)
			{
				request.setAttribute("message", "Your Login Session has Expired. Please Login Again.");
				return "sessionExp";
			}
			agentID=(String)session.getAttribute("agentID");
			String param=request.getParameter("param");
			
			logger.info("TEP ,Class is BackButtonAction ,Param is "+param);

			if("getPage".equalsIgnoreCase(param))
			{
				LoginDao loginDao=new LoginDao();
				session.setAttribute("DMRVendor", loginDao.fetchVendor("DMR"));
				BackButtonDao daoObj=new BackButtonDao();
				String balance=daoObj.getAgentBalance(agentID);
				session.setAttribute("AgentBalance", balance);
				return "HomePage";
			}else if("getBalance".equalsIgnoreCase(param))
			{
				agentID=(String)session.getAttribute("agentID");
				if(agentID==null)
				{
					logger.info("Your Login Session has Expired. Please Login Again.");
					printWriter.println(agentID);
					return null;
				}
				BackButtonDao daoObj=new BackButtonDao();
				String balance=daoObj.getAgentBalance(agentID);
				printWriter.println(balance);
				return null;
				//session.setAttribute("AgentBalance", balance);
				//return mapping.findForward("HomePage");
			}else if("getRechargePage".equalsIgnoreCase(param))
			{
				BackButtonDao daoObj=new BackButtonDao();
				String balance=daoObj.getAgentBalance(agentID);
				session.setAttribute("AgentBalance", balance);
				return "home";
			}
			else if("Recharge".equalsIgnoreCase(param))
			{
				BackButtonDao daoObj=new BackButtonDao();
				String balance=daoObj.getAgentBalance(agentID);
				session.setAttribute("AgentBalance", balance);
				return "Recharge";
			}else if("Billpay".equalsIgnoreCase(param))
			{
				BackButtonDao daoObj=new BackButtonDao();
				String balance=daoObj.getAgentBalance(agentID);
				session.setAttribute("AgentBalance", balance);
				return "Billpay";
			}
			else if("getPageCC".equalsIgnoreCase(param))
			{
				BackButtonDao daoObj=new BackButtonDao();
				String balance=daoObj.getAgentBalance(agentID);
				session.setAttribute("AgentBalance", balance);
				return "CCPage";
			}
			else if("GTOC".equalsIgnoreCase(param))
			{
				BackButtonDao daoObj=new BackButtonDao();
				String balance=daoObj.getAgentBalance(agentID);
				session.setAttribute("AgentBalance", balance);
				return "GTOC";
			}else if("w2w".equalsIgnoreCase(param))
			{
				BackButtonDao daoObj=new BackButtonDao();
				String balance=daoObj.getAgentBalance(agentID);
				session.setAttribute("AgentBalance", balance);
				return "w2w";
			}else if("download-api-doc".equalsIgnoreCase(param)) {
				response.sendRedirect("file/MSmart_Recharge_API.pdf");
				return null;
			}else if("update-callback".equalsIgnoreCase(param)) {
				String call_back=request.getParameter("call_back");
				String agentId=(String)session.getAttribute("agentID");
				BackButtonDao backButtonDao=new BackButtonDao();
				int count=backButtonDao.updateAPICallbackUrl(call_back, agentId);
				if(count>0) {
					request.setAttribute("message", "Callback URL Updated Successfully");
				}else {
					request.setAttribute("message", "Callback URL Not Updated");
				}
				return "authdetails";
			}
			else
			{
				request.setAttribute("message","Your request can not be processed due to some technical problem.");
				return "HomePage";
			}
			
		}catch (Exception ex) {
			logger.info("Exception in BackButtonAction class ");
			ex.printStackTrace();
		}
		return "ERROR";
	}

}
