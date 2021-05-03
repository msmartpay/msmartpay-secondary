package com.msmart.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import org.apache.log4j.Logger;
import org.json.JSONException;
import com.msmart.dao.MobileAppRechargeDao;
import com.msmart.dao.StatementNReportDao;
import com.smartkinda.api.SszDMT;
import com.smartkinda.api.PayeneLoanCCD;
import com.smartkinda.api.SszAPICall;

import net.sf.json.JSONObject;

@Path("SKDMR")
public class SSZDMRController {

	Logger logger = Logger.getLogger(LoginController.class);

	@Context HttpServletRequest request;

	@POST
	@Produces("application/JSON")
	@Consumes("application/JSON")
	@Path("/FindSender")
	public JSONObject  findSender(JSONObject reqObj) throws JSONException
	{
		logger.info(reqObj.toString());

		String key=reqObj.getString("Key");
		String AgentID=reqObj.getString("AgentID");
		String senderId=reqObj.getString("SenderId");
		JSONObject jsonObject=null;
		try {
			MobileAppRechargeDao services=new MobileAppRechargeDao();
			String txn_key_val=services.txn_key_Validation(key, AgentID);
			if(!txn_key_val.equalsIgnoreCase("Y"))
			{
				jsonObject=new JSONObject();
				jsonObject.put("Status", "1");
				jsonObject.put("message", "Invalid request!");
			}else if(senderId==null || senderId.length()<10){
				jsonObject=new JSONObject();
				jsonObject.put("Status", "1");
				jsonObject.put("message", "Invalid Sender Id!");
			}else{

				SszAPICall.setSKDealerId(reqObj);

				SszDMT ekoDMT=new SszDMT();
				jsonObject=ekoDMT.findSender(reqObj);
			}

			System.err.println("Response : "+jsonObject.toString());

		} catch (Exception e) {
			e.printStackTrace();
			jsonObject=new JSONObject();
			jsonObject.put("Status", "1");
			jsonObject.put("message", "Unable to process your request. Please try Later");
			return jsonObject;
		}



		return jsonObject;
	}

	@POST
	@Produces("application/JSON")
	@Consumes("application/JSON")
	@Path("/ReSendOTPRegisterSender")
	public JSONObject  reSendOTPRegisterSender(JSONObject reqObj) throws JSONException
	{
		logger.info(reqObj.toString());

		String key=reqObj.getString("Key");
		String AgentID=reqObj.getString("AgentID");
		String senderId=reqObj.getString("SenderId");
		JSONObject jsonObject=null;
		try {
			MobileAppRechargeDao services=new MobileAppRechargeDao();
			String txn_key_val=services.txn_key_Validation(key, AgentID);
			if(!txn_key_val.equalsIgnoreCase("Y"))
			{
				jsonObject=new JSONObject();
				jsonObject.put("Status", "1");
				jsonObject.put("message", "Invalid request!");
			}else if(senderId==null || senderId.length()<10){
				jsonObject=new JSONObject();
				jsonObject.put("Status", "1");
				jsonObject.put("message", "Invalid Sender Id!");
			}else{

				SszAPICall.setSKDealerId(reqObj);

				SszDMT sKindaDMT=new SszDMT();
				jsonObject=sKindaDMT.resendOtp(reqObj);
			}

			System.err.println("Response : "+jsonObject.toString());

		} catch (Exception e) {
			e.printStackTrace();
			jsonObject=new JSONObject();
			jsonObject.put("Status", "1");
			jsonObject.put("message", "Unable to process your request. Please try Later");
			return jsonObject;
		}



		return jsonObject;
	}

	@POST
	@Produces("application/JSON")
	@Consumes("application/JSON")
	@Path("/RegisterSender")
	public JSONObject  registerSender(JSONObject reqObj) throws JSONException
	{
		logger.info(reqObj.toString());

		String key=reqObj.getString("Key");
		String AgentID=reqObj.getString("AgentID");
		JSONObject jsonObject=null;
		try {
			MobileAppRechargeDao services=new MobileAppRechargeDao();
			String txn_key_val=services.txn_key_Validation(key, AgentID);
			if(!txn_key_val.equalsIgnoreCase("Y"))
			{
				jsonObject=new JSONObject();
				jsonObject.put("Status", "1");
				jsonObject.put("message", "Invalid request!");
			}else{

				SszAPICall.setSKDealerId(reqObj);

				SszDMT ekoDMT=new SszDMT();
				jsonObject=ekoDMT.registerSender(reqObj);
			}

		} catch (Exception e) {
			e.printStackTrace();
			jsonObject=new JSONObject();
			jsonObject.put("Status", "1");
			jsonObject.put("message", "Unable to process your request. Please try Later");
			return jsonObject;
		}

		System.err.println("Response : "+jsonObject.toString());

		return jsonObject;
	}

	@POST
	@Produces("application/JSON")
	@Consumes("application/JSON")
	@Path("/VerifySender")
	public JSONObject  verifySender(JSONObject reqObj) throws JSONException
	{
		logger.info(reqObj.toString());


		JSONObject jsonObject=null;
		try {
			String key=reqObj.getString("Key");
			String AgentID=reqObj.getString("AgentID");
			String senderId=reqObj.getString("SenderId");

			MobileAppRechargeDao services=new MobileAppRechargeDao();
			String txn_key_val=services.txn_key_Validation(key, AgentID);
			if(!txn_key_val.equalsIgnoreCase("Y"))
			{
				jsonObject=new JSONObject();
				jsonObject.put("Status", "1");
				jsonObject.put("message", "Invalid request!");
			}else if(senderId==null || senderId.length()<10){
				jsonObject=new JSONObject();
				jsonObject.put("Status", "1");
				jsonObject.put("message", "Invalid Sender Id!");
			}else{

				SszAPICall.setSKDealerId(reqObj);

				SszDMT ekoDMT=new SszDMT();
				jsonObject=ekoDMT.verifySender(reqObj);
			}

		} catch (Exception e) {
			e.printStackTrace();
			jsonObject=new JSONObject();
			jsonObject.put("Status", "1");
			jsonObject.put("message", "Unable to process your request. Please try Later");
			return jsonObject;
		}

		System.err.println("Response : "+jsonObject.toString());

		return jsonObject;
	}

	//Add Beneficiary

	@POST
	@Produces("application/JSON")
	@Consumes("application/JSON")
	@Path("/AddBeneAfterVerify")
	public JSONObject addBene(JSONObject reqObj) throws JSONException
	{
		logger.info(reqObj.toString());

		String key=reqObj.getString("Key");
		String AgentID=reqObj.getString("AgentID");

		JSONObject jsonObject=null;
		try {
			reqObj.put("ipAddress", request.getRemoteAddr());

			MobileAppRechargeDao services=new MobileAppRechargeDao();
			String txn_key_val=services.txn_key_Validation(key, AgentID);
			if(!txn_key_val.equalsIgnoreCase("Y"))
			{
				jsonObject=new JSONObject();
				jsonObject.put("Status", "1");
				jsonObject.put("message", "Invalid request!");
			}else{

				SszAPICall.setSKDealerId(reqObj);
				SszDMT ekoDMT=new SszDMT();
				jsonObject=ekoDMT.AddBeneAfterVerify(reqObj,AgentID);
			}

		} catch (Exception e) {
			e.printStackTrace();
			jsonObject=new JSONObject();
			jsonObject.put("Status", "1");
			jsonObject.put("message", "Unable to process your request. Please try Later");
			return jsonObject;
		}

		System.err.println("Response : "+jsonObject.toString());

		return jsonObject;
	}

	//get Bank List

	@POST
	@Produces("application/JSON")
	@Consumes("application/JSON")
	@Path("/GetBankList")
	public JSONObject getBankList(JSONObject reqObj) throws JSONException
	{
		logger.info(reqObj.toString());

		String key=reqObj.getString("Key");
		String AgentID=reqObj.getString("AgentID");
		JSONObject jsonObject=new JSONObject();
		try {
			MobileAppRechargeDao services=new MobileAppRechargeDao();
			String txn_key_val=services.txn_key_Validation(key, AgentID);
			if(!txn_key_val.equalsIgnoreCase("Y"))
			{
				jsonObject=new JSONObject();
				jsonObject.put("Status", "1");
				jsonObject.put("message", "Invalid request!");

			}else{

				SszAPICall.setSKDealerId(reqObj);
				SszDMT smartKindaDMT=new SszDMT();
				jsonObject=smartKindaDMT.bankList(reqObj);
			}

		} catch (Exception e) {
			e.printStackTrace();
			jsonObject=new JSONObject();
			jsonObject.put("Status", "1");
			jsonObject.put("message", "Unable to process your request. Please try Later");
			return jsonObject;
		}

		System.err.println("Response : "+jsonObject.toString());

		return jsonObject;
	}

	//Account Verify By BankAccount IFSC

	@POST
	@Produces("application/JSON")
	@Consumes("application/JSON")
	@Path("/AccountVerifyByBankAccountIFSC")
	public JSONObject accountVerifyByBankIFSC(JSONObject reqObj) throws JSONException
	{
		logger.info(reqObj.toString());

		String ip=request.getRemoteAddr();
		String key=reqObj.getString("Key");
		String AgentID=reqObj.getString("AgentID");
		JSONObject jsonObject=null;
		try {

			MobileAppRechargeDao services=new MobileAppRechargeDao();
			String txn_key_val=services.txn_key_Validation(key, AgentID);
			if(!txn_key_val.equalsIgnoreCase("Y"))
			{
				jsonObject=new JSONObject();
				jsonObject.put("Status", "1");
				jsonObject.put("message", "Invalid request!");
			}else{

				SszAPICall.setSKDealerId(reqObj);
				SszDMT ekoDMT=new SszDMT();
				jsonObject=ekoDMT.verifiAccount(reqObj,AgentID,ip);
			}


		} catch (Exception e) {
			e.printStackTrace();
			jsonObject=new JSONObject();
			jsonObject.put("Status", "1");
			jsonObject.put("response-message", "Unable to process your request. Please try Later");
			return jsonObject;
		}

		System.err.println("Response : "+jsonObject.toString());

		return jsonObject;
	}


	//Delete Beneficiary

	@POST
	@Produces("application/JSON")
	@Consumes("application/JSON")
	@Path("/DeleteBene")
	public JSONObject deleteBene(JSONObject reqObj) throws JSONException
	{
		logger.info(reqObj.toString());

		String key=reqObj.getString("Key");
		String AgentID=reqObj.getString("AgentID");

		JSONObject jsonObject=null;
		try {
			MobileAppRechargeDao services=new MobileAppRechargeDao();
			String txn_key_val=services.txn_key_Validation(key, AgentID);
			if(!txn_key_val.equalsIgnoreCase("Y"))
			{
				jsonObject=new JSONObject();
				jsonObject.put("Status", "1");
				jsonObject.put("message", "Invalid request!");
			}else{

				SszAPICall.setSKDealerId(reqObj);
				SszDMT ekoDMT=new SszDMT();
				jsonObject=ekoDMT.deleteBene(reqObj);
			}

		} catch (Exception e) {
			e.printStackTrace();
			jsonObject=new JSONObject();
			jsonObject.put("Status", "1");
			jsonObject.put("message", "Unable to process your request. Please try Later");
			return jsonObject;
		}

		System.err.println("Response : "+jsonObject.toString());

		return jsonObject;
	}

	@POST
	@Produces("application/JSON")
	@Consumes("application/JSON")
	@Path("/InitiateTransaction")
	public JSONObject initiateTransaction(JSONObject reqObj) throws JSONException
	{
		logger.info(reqObj.toString());
		String ip=request.getRemoteHost();
		String agentID="",account="";

		JSONObject jsonObject=new JSONObject();
		ServletContext context = null;
		try {
			
			context=request.getServletContext();

			account=reqObj.getString("BeneAccount");
			String key=reqObj.getString("Key");
			agentID=reqObj.getString("AgentID");

			if(context.getAttribute(account)==null||!"".equalsIgnoreCase(context.getAttribute(account)+"")){
				context.setAttribute(account, account);	


				String senderId=reqObj.getString("SenderId");

				MobileAppRechargeDao services=new MobileAppRechargeDao();
				String txn_key_val=services.txn_key_Validation(key, agentID);
				if(!txn_key_val.equalsIgnoreCase("Y"))
				{
					jsonObject=new JSONObject();
					jsonObject.put("Status", "1");
					jsonObject.put("message", "Invalid request!");
				}else if(senderId==null || senderId.length()<10){
					jsonObject=new JSONObject();
					jsonObject.put("Status", "1");
					jsonObject.put("message", "Invalid Sender Id!");
				}else{
					SszAPICall.setSKDealerId(reqObj);
					SszDMT ekoDMT=new SszDMT();
					jsonObject=ekoDMT.ekoTransaction(reqObj,ip,agentID,senderId);
				}

			}else {
				jsonObject.put("response-code", "1");
				jsonObject.put("response-message", "Transaction is Under Process. Please Wait.");
			}

		} catch (Exception e) {
			
			if(context.getAttribute(account)!=null)
				context.removeAttribute(account);
			
			
			e.printStackTrace();
			jsonObject=new JSONObject();
			jsonObject.put("Status", "1");
			jsonObject.put("message", "Unable to process your request. Please try Later");
			return jsonObject;
		}

		logger.info("Transaction Response : "+jsonObject);
		return jsonObject;
	}

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/SenderHistory")

	public JSONObject senderHistory(JSONObject jsonObject)	
	{
		JSONObject response=new JSONObject(); 
		try {
			logger.info("DMRController.findSender() : "+jsonObject.toString());
			String key=jsonObject.getString("Key");
			String AgentID=jsonObject.getString("AgentID");

			String SenderId=jsonObject.getString("SenderId");

			MobileAppRechargeDao services=new MobileAppRechargeDao();
			String txn_key_val=services.txn_key_Validation(key, AgentID);
			if(!txn_key_val.equalsIgnoreCase("Y"))
			{
				response.put("Status", "1");
				response.put("message", "Invalid request!");
			}else if(SenderId==null || SenderId.length()<10){
				jsonObject.put("Status", "1");
				jsonObject.put("message", "Invalid Sender Id!");
			}else{

				StatementNReportDao nReportDao=new StatementNReportDao();
				response=nReportDao.sendrHistory(SenderId);
			}


		} catch (Exception e) {
			e.printStackTrace();
			response.put("Status", "1");
			response.put("message", "Process aborted due to technical failure.");
			return response;
		}

		return response;
	}

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/TransStatus")

	public JSONObject transStatus(JSONObject jsonObject)	
	{
		JSONObject response=new JSONObject(); 
		try {
			String key=jsonObject.getString("Key");
			String AgentID=jsonObject.getString("AgentID");
			logger.info("DMRController.findSender()");

			MobileAppRechargeDao services=new MobileAppRechargeDao();
			String txn_key_val=services.txn_key_Validation(key, AgentID);
			if(!txn_key_val.equalsIgnoreCase("Y"))
			{
				response.put("Status", "1");
				response.put("message", "Invalid request!");

			}else{

				SszAPICall.setSKDealerId(jsonObject);
				SszDMT sKindaDMT=new SszDMT();


				response=sKindaDMT.tranStatus(jsonObject);
			}


		} catch (Exception e) {
			e.printStackTrace();
			response=new JSONObject();
			response.put("response-code", "1");
			response.put("response-message", "Failure");
			return response;
		}

		return response;
	}

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/RefundTransaction")

	public JSONObject refundDMR(JSONObject jsonObject)	
	{
		JSONObject response=new JSONObject(); 
		try {
			String key=jsonObject.getString("Key");
			String AgentID=jsonObject.getString("AgentID");
			String SenderId=jsonObject.getString("SenderId");
			logger.info("DMRController.findSender()");

			MobileAppRechargeDao services=new MobileAppRechargeDao();
			String txn_key_val=services.txn_key_Validation(key, AgentID);
			if(!txn_key_val.equalsIgnoreCase("Y"))
			{
				response.put("Status", "1");
				response.put("message", "Invalid request!");

			}else if(SenderId==null || SenderId.length()<10){
				jsonObject.put("Status", "1");
				jsonObject.put("message", "Invalid Sender Id!");
			}else{

				String TransactionRefNo=jsonObject.getString("TransactionRefNo");
				SszAPICall.setSKDealerId(jsonObject);
				SszDMT dao=new SszDMT();
				response=dao.initiateRefundTransaction(jsonObject, TransactionRefNo)	;		
			}


		} catch (Exception e) {
			e.printStackTrace();
			response=new JSONObject();
			response.put("response-code", "1");
			response.put("response-message", "Failure");
			return response;
		}

		return response;
	}

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/RefundDMRConfirm")

	public JSONObject refundDMRConfirm(JSONObject jsonObject)	
	{
		JSONObject response=new JSONObject(); 
		try {
			String key=jsonObject.getString("Key");
			String AgentID=jsonObject.getString("AgentID");
			String SenderId=jsonObject.getString("SenderId");
			logger.info("DMRController.findSender()");

			MobileAppRechargeDao services=new MobileAppRechargeDao();
			String txn_key_val=services.txn_key_Validation(key, AgentID);
			if(!txn_key_val.equalsIgnoreCase("Y"))
			{
				response.put("Status", "1");
				response.put("message", "Invalid request!");

			}else if(SenderId==null || SenderId.length()<10){
				jsonObject.put("Status", "1");
				jsonObject.put("message", "Invalid Sender Id!");
			}else{

				SszAPICall.setSKDealerId(jsonObject);
				SszDMT dao=new SszDMT();
				response=dao.confirmRefundTransaction(jsonObject,AgentID)	;		
			}


		} catch (Exception e) {
			e.printStackTrace();
			response=new JSONObject();
			response.put("response-code", "1");
			response.put("response-message", "Failure");
			return response;
		}

		return response;
	}

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/BankDetails")

	public JSONObject bankDetails(JSONObject jsonObject)	
	{
		JSONObject response=new JSONObject(); 
		try {
			String key=jsonObject.getString("Key");
			String AgentID=jsonObject.getString("AgentID");
			String SenderId=jsonObject.getString("SenderId");
			logger.info("DMRController.findSender()");

			MobileAppRechargeDao services=new MobileAppRechargeDao();
			String txn_key_val=services.txn_key_Validation(key, AgentID);
			if(!txn_key_val.equalsIgnoreCase("Y"))
			{
				response.put("Status", "1");
				response.put("message", "Invalid request!");

			}else if(SenderId==null || SenderId.length()<10){
				jsonObject.put("Status", "1");
				jsonObject.put("message", "Invalid Sender Id!");
			}else{

				SszAPICall.setSKDealerId(jsonObject);
				SszDMT sKindaDMT=new SszDMT();
				response=sKindaDMT.bankDetails(jsonObject);
			}


		} catch (Exception e) {
			e.printStackTrace();
			response=new JSONObject();
			response.put("response-code", "1");
			response.put("response-message", "Failure");
			return response;
		}

		return response;
	}


	// Loan Payment and CCD payment

	@POST
	@Produces("application/JSON")
	@Consumes("application/JSON")
	@Path("/FindLoanCCDSender")
	public JSONObject  findLoanCCDSender(JSONObject reqObj) throws JSONException
	{
		logger.info(reqObj.toString());

		String key=reqObj.getString("Key");
		String AgentID=reqObj.getString("AgentID");
		String senderId=reqObj.getString("SenderId");
		String accountType=reqObj.getString("Type");
		JSONObject jsonObject=null;
		try {
			MobileAppRechargeDao services=new MobileAppRechargeDao();
			String txn_key_val=services.txn_key_Validation(key, AgentID);
			if(!txn_key_val.equalsIgnoreCase("Y"))
			{
				jsonObject=new JSONObject();
				jsonObject.put("Status", "1");
				jsonObject.put("message", "Invalid request!");
			}else if(senderId==null || senderId.length()<10){
				jsonObject=new JSONObject();
				jsonObject.put("Status", "1");
				jsonObject.put("message", "Invalid Sender Id!");
			}else{

				SszAPICall.setSKDealerId(reqObj);

				PayeneLoanCCD ekoDMT=new PayeneLoanCCD();
				jsonObject=ekoDMT.findLoanCCDSender(reqObj,senderId,accountType);
			}

			System.err.println("Response : "+jsonObject.toString());

		} catch (Exception e) {
			e.printStackTrace();
			jsonObject=new JSONObject();
			jsonObject.put("Status", "1");
			jsonObject.put("message", "Unable to process your request. Please try Later");
			return jsonObject;
		}



		return jsonObject;
	}

	@POST
	@Produces("application/JSON")
	@Consumes("application/JSON")
	@Path("/GetLoanCCDBankList")
	public JSONObject getLoanCCDBankList(JSONObject reqObj) throws JSONException
	{
		logger.info(reqObj.toString());

		String key=reqObj.getString("Key");
		String AgentID=reqObj.getString("AgentID");
		JSONObject jsonObject=new JSONObject();
		try {
			MobileAppRechargeDao services=new MobileAppRechargeDao();
			String txn_key_val=services.txn_key_Validation(key, AgentID);
			if(!txn_key_val.equalsIgnoreCase("Y"))
			{
				jsonObject=new JSONObject();
				jsonObject.put("Status", "1");
				jsonObject.put("message", "Invalid request!");

			}else{

				PayeneLoanCCD smartKindaDMT=new PayeneLoanCCD();
				jsonObject=smartKindaDMT.loanCCDBankList(reqObj);
			}

		} catch (Exception e) {
			e.printStackTrace();
			jsonObject=new JSONObject();
			jsonObject.put("Status", "1");
			jsonObject.put("message", "Unable to process your request. Please try Later");
			return jsonObject;
		}

		System.err.println("Response : "+jsonObject.toString());

		return jsonObject;
	}

	@POST
	@Produces("application/JSON")
	@Consumes("application/JSON")
	@Path("/PayLoanAndCCDPayment")
	public JSONObject payLoanAndCCDPayment(JSONObject reqObj) throws JSONException
	{
		logger.info(reqObj.toString());

		String key=reqObj.getString("Key");
		String AgentID=reqObj.getString("AgentID");

		JSONObject jsonObject=null;
		try {

			String ipAddress=request.getRemoteAddr();

			MobileAppRechargeDao services=new MobileAppRechargeDao();
			String txn_key_val=services.txn_key_Validation(key, AgentID);
			if(!txn_key_val.equalsIgnoreCase("Y"))
			{
				jsonObject=new JSONObject();
				jsonObject.put("Status", "1");
				jsonObject.put("message", "Invalid request!");
			}else{

				SszAPICall.setSKDealerId(reqObj);
				PayeneLoanCCD payeneLoanCCD=new PayeneLoanCCD();
				jsonObject=payeneLoanCCD.payLoanAndCCDPayment(reqObj,ipAddress,AgentID);
			}

		} catch (Exception e) {
			e.printStackTrace();
			jsonObject=new JSONObject();
			jsonObject.put("Status", "1");
			jsonObject.put("message", "Unable to process your request. Please try Later");
			return jsonObject;
		}

		System.err.println("Response : "+jsonObject.toString());

		return jsonObject;
	}

}
