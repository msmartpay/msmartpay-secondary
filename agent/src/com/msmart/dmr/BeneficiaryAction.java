package com.msmart.dmr;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.json.JSONObject;
import com.opensymphony.xwork2.ActionSupport;

public class BeneficiaryAction extends ActionSupport{

	Logger logger=Logger.getLogger(BeneficiaryAction.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public String execute()
			throws Exception {

		request=ServletActionContext.getRequest();
		response=ServletActionContext.getResponse();
		HttpSession session=request.getSession(false);

		String agentID="";
		try
		{

			if(session.getAttribute("agentID")==null){
				request.setAttribute("message", "Your Login Session has Expired. Please Login Again.");
				return "sessionExp";
			}
			agentID=(String)session.getAttribute("agentID");
			
			return "home";

		}catch (Exception ex) {
			logger.info("TEP :: Exception in AccountStatementAction class ");
			ex.printStackTrace();
		}
		return "ERROR";
	}
	public String addbeneficiarypage()
			throws Exception {

		request=ServletActionContext.getRequest();
		response=ServletActionContext.getResponse();
		HttpSession session=request.getSession(false);

		String agentID="";
		try
		{

			if(session.getAttribute("agentID")==null){
				request.setAttribute("message", "Your Login Session has Expired. Please Login Again.");
				return "sessionExp";
			}
			
			    	
			agentID=(String)session.getAttribute("agentID");
			HashMap<String,String> AgentDetailData=(HashMap<String,String>)session.getAttribute("AgentDetailData");
			String tranKey=AgentDetailData.get("TxnKey");
			String SenderId = request.getParameter("SenderId");
			

			DMRApi api=new DMRApi();
			ArrayList<HashMap<String, Object>> bankList=api.getBankList(SenderId,tranKey,agentID);
			if(bankList!=null && bankList.size()>0)
			{
				request.setAttribute("bankList", bankList);
				

			}
			request.setAttribute("SenderId", SenderId);
			return "home";
		}catch (Exception ex) {
			logger.info("TEP :: Exception in AccountStatementAction class ");
			ex.printStackTrace();
		}
		return "ERROR";
	}
	

	public String AddBeneAfterVerify()
			throws Exception {

		request=ServletActionContext.getRequest();
		response=ServletActionContext.getResponse();
		HttpSession session=request.getSession(false);

		String agentID="";
		try
		{
			if(session.getAttribute("agentID")==null){
				request.setAttribute("message", "Your Login Session has Expired. Please Login Again.");
				return "sessionExp";
			}
			HashMap<String,String> AgentDetailData=(HashMap<String,String>)session.getAttribute("AgentDetailData");
			String tranKey=AgentDetailData.get("TxnKey");
			
			agentID=(String)session.getAttribute("agentID");
			String senderId = request.getParameter("SenderId");
			String bankAccount = request.getParameter("BankAccount");
			String beneName = request.getParameter("BeneName");
			String iFSC = request.getParameter("IFSC");
			String BeneMobile=request.getParameter("BeneMobile");
			String bankcode=request.getParameter("bankcode");
			String BankName=request.getParameter("BankName");
			
			if(iFSC!=null && iFSC.length()==11)
				bankcode="";
			else{
				request.setAttribute("message", "Please enter valid IFSC Code.");
			}
				

			DMRApi api=new DMRApi();
			JSONObject respObj=api.confirmaddbene(senderId, bankAccount, beneName, iFSC, BeneMobile,bankcode,tranKey,agentID,BankName);
			if(respObj!=null)
			{
				String message=respObj.getString("message");
				String respStatus=respObj.getString("Status");
				if("0".equalsIgnoreCase(respStatus)){

					MoneyTransferDao dao=new MoneyTransferDao();
					
					String beneId=respObj.getString("BeneId");

					String addBeneStatus=dao.addBeneficiary(beneName, BeneMobile, BankName, bankcode, bankAccount, senderId, beneId);

					logger.info("addBeneStatus :: "+addBeneStatus);
				}
				request.setAttribute("message", message);
			}
			else{
				request.setAttribute("message", "Technical Failure");
			}
			
			ArrayList<HashMap<String, Object>> bankList=api.getBankList(senderId,tranKey,agentID);
			if(bankList!=null && bankList.size()>0)
			{
				request.setAttribute("bankList", bankList);
			}
			request.setAttribute("SenderId", senderId);
			
			return "home";
		}catch (Exception ex) {
			logger.info("TEP :: Exception in AccountStatementAction class ");
			ex.printStackTrace();
		}
		return "ERROR";
	}
	
	public String deleteBeneficiary()
			throws Exception {

		request=ServletActionContext.getRequest();
		HttpSession session=request.getSession(false);

		String agentID="";
		try
		{
			if(session.getAttribute("agentID")==null){
				request.setAttribute("message", "Your Login Session has Expired. Please Login Again.");
				return "sessionExp";
			}
			agentID=(String)session.getAttribute("agentID");
			HashMap<String,String> AgentDetailData=(HashMap<String,String>)session.getAttribute("AgentDetailData");
			String tranKey=AgentDetailData.get("TxnKey");
			String beneId=request.getParameter("BeneId");
			String senderId=request.getParameter("SenderId");
			
			DMRApi api=new DMRApi();
			
			JSONObject res=api.deleteBene(senderId, beneId,tranKey,agentID);
			String status=res.getString("Status");
			String msg=res.getString("message");
			
			HashMap<String, Object>findSenderMap=api.getFindSender(senderId,tranKey,agentID);
			if(findSenderMap!=null)
			{
				request.setAttribute("findSenderlist", findSenderMap);
				System.out.println(findSenderMap);
			}
			
			request.setAttribute("message",msg);
			
			return "home";

		}catch (Exception ex) {
			logger.info("TEP :: Exception in AccountStatementAction class ");
			ex.printStackTrace();
		}
		return "ERROR";
	}
	
}
