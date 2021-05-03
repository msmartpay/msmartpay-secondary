package com.report.TranStatus;

import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.report.accountStmt.AccountStatementDao;

public final class TransactionStatusAction extends ActionSupport {

	Logger logger=Logger.getLogger(TransactionStatusAction.class);
	
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
			
			if(session.getAttribute("agentID")==null){
				request.setAttribute("message", "Your Login Session has Expired. Please Login Again.");
				return "sessionExp";
			}
			String agentID=(String)session.getAttribute("agentID");
			String param=request.getParameter("param");
			
			logger.info("TEP ,Class is LoginAction ,Param is "+param);

			if(param.equalsIgnoreCase("transactionStatusPage")){
				return "TranStatusPage";
			}else if(param.equalsIgnoreCase("checkTranStatus")){
				String connectionNo=request.getParameter("connectionNo");
				logger.info(connectionNo);
				TransactionStatusDao daoObj=new TransactionStatusDao();
				ArrayList<HashMap<String,String>> tranStatusData=daoObj.getTransactionStatusData(connectionNo,agentID);
			
				if(tranStatusData.size()<=0){
					request.setAttribute("message", "Connection Number Not Found.");
				}else{
					request.setAttribute("tranStatusData", tranStatusData);
				}
				return "TranStatusPage";
			}
		}catch (Exception ex) {
			logger.info("TEP :: Exception in LoginAction class ");
			ex.printStackTrace();
		}
	return "ERROR";
	}
}
