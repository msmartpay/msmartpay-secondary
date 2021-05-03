package com.msmart.dmr;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.json.JSONObject;
import com.common.GenerateIdDao;
import com.opensymphony.xwork2.ActionSupport;

public class TransactionAction extends ActionSupport{

	Logger logger=Logger.getLogger(TransactionAction.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;

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

			return "home";

		}catch (Exception ex) {
			logger.info("TEP :: Exception in AccountStatementAction class ");
			ex.printStackTrace();
		}
		return "ERROR";
	}

	public String dmrTranStatusPage()
			throws Exception {

		request=ServletActionContext.getRequest();
		HttpSession session=request.getSession(false);

		try {

			if(session.getAttribute("agentID")==null){
				request.setAttribute("message", "Your Login Session has Expired. Please Login Again.");
				return "sessionExp";
			}
			return "dmrTranStatusPage";

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "Unable to process your request. Please try later.");
		}
		return "ERROR";
	}

	public String dmrTranStatus()
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
			
			String SenderId=request.getParameter("SenderId");

			String tranId=request.getParameter("TransactionRefNo");


			String smartId=MoneyTransferDao.getEKOTid(tranId,agentID);
			if(smartId==null){

				request.setAttribute("message", "Transaction not found. Contact to Admin.");

			}else{

				DMRApi dmrApi=new DMRApi();


				JSONObject respObj=dmrApi.liveStatus(SenderId,smartId,tranKey,agentID);
				if(respObj!=null){

					String ststCode=respObj.getString("Status");
					String message=respObj.getString("message");
					if("0".equalsIgnoreCase(ststCode)){

						request.setAttribute("TranDetail", respObj);
						request.setAttribute("tranId", tranId);

					}

					request.setAttribute("message", message);
					request.setAttribute("SenderId", SenderId);


				}else
					request.setAttribute("message", "Transaction not found. Contact to Admin.");

			}

			return "home";

		}catch (Exception ex) {
			logger.info("TEP :: Exception in AccountStatementAction class ");
			ex.printStackTrace();
		}
		return "ERROR";
	}


	public String transaction()
			throws Exception {

		request=ServletActionContext.getRequest();
		HttpSession session=request.getSession(false);

		String agentID="";
		try {

			if(session.getAttribute("agentID")==null){
				request.setAttribute("message", "Your Login Session has Expired. Please Login Again.");
				return "sessionExp";
			}
			agentID=(String)session.getAttribute("agentID");
			String tranId=request.getParameter("TransactionRefNo");
			HashMap<String,String> AgentDetailData=(HashMap<String,String>)session.getAttribute("AgentDetailData");
			String tranKey=AgentDetailData.get("TxnKey");

			String smartId=MoneyTransferDao.getEKOTid(tranId,agentID);
			if(smartId==null){

				request.setAttribute("message", "Transaction not found. Contact to Admin.");

			}else{

				DMRApi dmrApi=new DMRApi();

				JSONObject respObj=dmrApi.liveStatus(smartId, tranId,tranKey,agentID);
				if(respObj!=null){

					String ststCode=respObj.getString("Status");
					String message=respObj.getString("message");
					if("0".equalsIgnoreCase(ststCode)){

						request.setAttribute("TranDetail", respObj);
						request.setAttribute("message", message);

					}else
						request.setAttribute("message", message);

				}else
					request.setAttribute("message", "Transaction not found. Contact to Admin.");

			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "Unable to process your request. Please try later.");
		}
		return "ERROR";
	}

	public String InitiateTransactionPage()
			throws Exception {

		request=ServletActionContext.getRequest();
		HttpSession session=request.getSession(false);

		try
		{
			if(session.getAttribute("agentID")==null){
				request.setAttribute("message", "Your Login Session has Expired. Please Login Again.");
				return "sessionExp";
			}

			String senderId = request.getParameter("SenderId");
			String SenderName = request.getParameter("SenderName");
			String BeneMobile = request.getParameter("BeneMobile");
			String iFSC = request.getParameter("IFSC");
			String BeneAccount = request.getParameter("BeneAccount");
			String beneName = request.getParameter("BeneName");
			String TxnType=request.getParameter("TxnType");
			String BankName=request.getParameter("BankName");
			String RecipientId=request.getParameter("RecipientId");

			request.setAttribute("RecipientId", RecipientId);
			request.setAttribute("SenderId", senderId);
			request.setAttribute("SenderName", SenderName);
			request.setAttribute("BeneMobile", BeneMobile);
			request.setAttribute("IFSC", iFSC);
			request.setAttribute("BeneAccount", BeneAccount);
			request.setAttribute("BeneName", beneName);
			request.setAttribute("TxnType", TxnType);
			request.setAttribute("BankName", BankName);

			return "home";



		}catch (Exception ex) {
			logger.info("TEP :: Exception in AccountStatementAction class ");
			ex.printStackTrace();
		}
		return "ERROR";
	}

	
	
	public String InitiateTransaction ()
			throws Exception {

		request=ServletActionContext.getRequest();
		HttpSession session=request.getSession(false);
		HashMap<String,String> result=new HashMap<String,String>();
		String agentID="",message="";
		try
		{
			if(session.getAttribute("agentID")==null){
				request.setAttribute("message", "Your Login Session has Expired. Please Login Again.");
				return "sessionExp";
			}
			HashMap<String,String> AgentDetailData=(HashMap<String,String>)session.getAttribute("AgentDetailData");
			String tranKey=AgentDetailData.get("TxnKey");


			agentID=(String)session.getAttribute("agentID");
			String SenderId = request.getParameter("SenderId");
			String SenderName = request.getParameter("SenderName");
			String BeneMobile = request.getParameter("BeneMobile");
			String BeneAccount = request.getParameter("BeneAccount");
			String BeneName = request.getParameter("BeneName");
			String IFSC = request.getParameter("IFSC");
			String TxnType=request.getParameter("TxnType");
			String BankName=request.getParameter("BankName");
			String TxnAmount=request.getParameter("Amount");
			String Remark=request.getParameter("Remark");
			String RecipientId=request.getParameter("RecipientId");
			String transactionId=GenerateIdDao.getIdNo();
			
			DMRApi api=new DMRApi();
			JSONObject respJSON=api.getInitiateTransaction(SenderId,SenderName,BeneMobile, BeneAccount, BeneName, 
					IFSC, TxnType,BankName,String.valueOf(TxnAmount),Remark,RecipientId,transactionId,tranKey,agentID);

			if (respJSON != null) {
				
				String Status=respJSON.optString("Status");
				message=respJSON.optString("message");
				
				DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
				DateFormat df1 = new SimpleDateFormat("HH:mm:ss");
				Date dateobj = new Date();
				String date1 = df.format(dateobj);
				String time1 = df1.format(dateobj);

				result.put("SenderId", SenderId);
				result.put("BeneName", BeneName);
				result.put("BeneMobile", BeneMobile);
				result.put("BeneAccount", BeneAccount);
				result.put("BankName", BankName);
				result.put("TxnDate", date1);
				result.put("TxnTime", time1);

				if("0".equalsIgnoreCase(Status)){
				
					result.put("apiTid", respJSON.optString("tid"));
					result.put("tranid", respJSON.optString("tranid"));
					result.put("BankRRN", respJSON.optString("BankRRN"));
					result.put("TxnAmount", respJSON.optString("TxnAmount"));
				}else{
					result.put("apiTid", "");
					result.put("tranid", "");
					result.put("BankRRN", "");
					result.put("TxnAmount", TxnAmount);
				}

				request.setAttribute("TransactionDetail", result);
				request.setAttribute("message", message);
				
				return "success";


			}else{


				request.setAttribute("message", "Transaction Under process Due to Technical Error. Please Try Later.");
				return "dmrHome";

			}
			


		}catch (Exception ex) {
			logger.info("TEP :: Exception in AccountStatementAction class ");
			ex.printStackTrace();
			request.setAttribute("message", "Transaction Failed Due to Technical Error. Please Try Later.");
			return "dmrHome";

		}

	}


	public String verifyAccountpage()
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

	public String verifyAccount()
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
			String SenderId = request.getParameter("SenderId");
			
			HashMap<String,String> AgentDetailData=(HashMap<String,String>)session.getAttribute("AgentDetailData");
			String tranKey=AgentDetailData.get("TxnKey");

			DMRApi api=new DMRApi();
			ArrayList<HashMap<String, Object>> bankList=api.getBankList(SenderId,tranKey,agentID);
			if(bankList!=null && bankList.size()>0)
			{
				request.setAttribute("bankList", bankList);
			}
			request.setAttribute("SenderId", SenderId);

			String AccountNo = request.getParameter("AccountNo");
			String BankName = request.getParameter("BankName");
			String IFSC = request.getParameter("IFSC");

			String REQUEST_ID=GenerateIdDao.getIdNo();

			HashMap<String, Object> verifyResult=api.getaddEbne(SenderId, IFSC, AccountNo, BankName, "", REQUEST_ID,tranKey,agentID);

			if(verifyResult!=null){
				String status=verifyResult.get("Status")+"";
				if("0".equalsIgnoreCase(status))
					request.setAttribute("verifyResult", verifyResult);

				request.setAttribute("message", verifyResult.get("message"));
			}else
			{
				request.setAttribute("message", "Unable to verify your account. Please try with correct details.");
			}


			return "home";

		}catch (Exception ex) {
			logger.info("TEP :: Exception in AccountStatementAction class ");
			ex.printStackTrace();
		}
		return "ERROR";
	}
	
	public String ajaxverifyAccount()
			throws Exception {

		String message="";
		request=ServletActionContext.getRequest();
		response=ServletActionContext.getResponse();
		HttpSession session=request.getSession(false);
		PrintWriter out=null;
		String agentID="";
		JSONObject jsonobj=null;
		try
		{
			out=response.getWriter();
			if(session.getAttribute("agentID")==null){
				message="Your Login Session has Expired. Please Login Again.";
			}
			
			agentID=(String)session.getAttribute("agentID");
			
			
			HashMap<String,String> AgentDetailData=(HashMap<String,String>)session.getAttribute("AgentDetailData");
			String tranKey=AgentDetailData.get("TxnKey");

			DMRApi api=new DMRApi();

			String SenderId = request.getParameter("SenderId");
			String AccountNo = request.getParameter("AccountNo");
			String BankName = request.getParameter("BankName");
			String IFSC = request.getParameter("IFSC");
			String bankCode = request.getParameter("bankCode");

			String REQUEST_ID=GenerateIdDao.getIdNo();


			JSONObject fsJson=new JSONObject();
			fsJson.put("Key", tranKey);
			fsJson.put("AgentID", agentID);
			fsJson.put("SenderId", SenderId);
			fsJson.put("BankName", BankName);
			fsJson.put("BankAccount", AccountNo);
			fsJson.put("BankCode", bankCode);
			fsJson.put("IFSC", IFSC);
			fsJson.put("REQUEST_ID", REQUEST_ID);
			System.out.println("request:"+fsJson);
			jsonobj =api.callApi("AccountVerifyByBankAccountIFSC", fsJson);

			if(jsonobj==null){
				jsonobj=new JSONObject();
				jsonobj.put("Status", "1");	
				jsonobj.put("message", "Unable to verify. Try later.");
			}
			

		}catch (Exception ex) {
			logger.info("TEP :: Exception in AccountStatementAction class ");
			ex.printStackTrace();
			jsonobj=new JSONObject();
			jsonobj.put("Status", "1");	
			jsonobj.put("message", "Unable to verify. Try later.");
		}
		out.print(jsonobj);

		
		return null;
	}

	public String refundTransaction()
			throws Exception {

		request=ServletActionContext.getRequest();
		HttpSession session=request.getSession(false);

		try
		{

			if(session.getAttribute("agentID")==null){
				request.setAttribute("message", "Your Login Session has Expired. Please Login Again.");
				return "sessionExp";
			}

			return "home";

		}catch (Exception ex) {
			logger.info("TEP :: Exception in AccountStatementAction class ");
			ex.printStackTrace();
		}
		return "ERROR";
	}

	public String verifyRefundTransaction()
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
			String tranId=request.getParameter("tranId");
			String SenderId=request.getParameter("SenderId");

			HashMap<String,String> AgentDetailData=(HashMap<String,String>)session.getAttribute("AgentDetailData");
			String tranKey=AgentDetailData.get("TxnKey");

			
			String smartId=MoneyTransferDao.getEKOTid(tranId,agentID);
			if(smartId==null){

				request.setAttribute("message", "Transaction not found. Contact to Admin.");

			}else{

				DMRApi dmrApi=new DMRApi();


				JSONObject respObj=dmrApi.getRefundOTP(SenderId, smartId,tranKey,agentID);
				if(respObj!=null){

					String ststCode=respObj.getString("Status");
					String message=respObj.getString("message");
					if("0".equalsIgnoreCase(ststCode)){

						String Refund_OTP=respObj.getString("Refund_OTP");
						request.setAttribute("OTP", "Y");
						request.setAttribute("tranId", tranId);
						request.setAttribute("SenderId", SenderId);

					}

					request.setAttribute("message", message);

				}else
					request.setAttribute("message", "Transaction not found. Contact to Admin.");

			}


			return "home";

		}catch (Exception ex) {
			logger.info("TEP :: Exception in AccountStatementAction class ");
			ex.printStackTrace();
		}
		return "ERROR";
	}
	
	public JSONObject resendRefundOTP() {
		
		
		String message="";
		request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		HttpSession session=request.getSession(false);
		PrintWriter out=null;
		String agentID="";
		try
		{
			out=response.getWriter();
			if(session.getAttribute("agentID")==null){
				message="Your Login Session has Expired. Please Login Again.";
			}else{
				agentID=(String)session.getAttribute("agentID");
				String tranId=request.getParameter("tranId");
				String SenderId=request.getParameter("SenderId");
				
				HashMap<String,String> AgentDetailData=(HashMap<String,String>)session.getAttribute("AgentDetailData");
				String tranKey=AgentDetailData.get("TxnKey");


				String smartId=MoneyTransferDao.getEKOTid(tranId,agentID);
				if(smartId==null){

					request.setAttribute("message", "Transaction not found. Contact to Admin.");

				}else{

					DMRApi dmrApi=new DMRApi();


					JSONObject respObj=dmrApi.getRefundOTP(SenderId, smartId,tranKey,agentID);
					if(respObj!=null){

						String ststCode=respObj.getString("Status");
						message=respObj.getString("message");
						if("0".equalsIgnoreCase(ststCode)){

							String Refund_OTP=respObj.getString("Refund_OTP");

						}


					}else
						message= "Transaction not found. Contact to Admin.";

				}
			}
			

		

		} catch (Exception e) {
			e.printStackTrace();
			message= "Unable to process your request. Please try later.";
		}
		out.print(message);

		return null;
	}


	public String confirmRefundTransaction()
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
			
			HashMap<String,String> AgentDetailData=(HashMap<String,String>)session.getAttribute("AgentDetailData");
			String tranKey=AgentDetailData.get("TxnKey");

			
			agentID=(String)session.getAttribute("agentID");
			String tranId=request.getParameter("tranId");
			String SenderId=request.getParameter("SenderId");
			String OTP=request.getParameter("OTP");

			String smartId=MoneyTransferDao.getEKOTid(tranId,agentID);
			if(smartId==null){

				request.setAttribute("message", "Transaction not found. Contact to Admin.");

			}else{

				DMRApi dmrApi=new DMRApi();

				JSONObject respObj=dmrApi.confirmRefund(SenderId, smartId,OTP,tranKey,agentID);
				if(respObj!=null){

					request.setAttribute("message", respObj.getString("message"));

				}else
					request.setAttribute("message", "Transaction not found. Contact to Admin.");

			}

			return "home";

		}catch (Exception ex) {
			logger.info("TEP :: Exception in AccountStatementAction class ");
			ex.printStackTrace();
		}
		return "ERROR";
	}
}
