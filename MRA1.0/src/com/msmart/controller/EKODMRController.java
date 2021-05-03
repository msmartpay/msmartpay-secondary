package com.msmart.controller;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.json.JSONException;

import com.msmart.dao.CommonServiceDao;
import com.msmart.dao.MobileAppRechargeDao;
import com.msmart.dao.StatementNReportDao;
import com.msmart.eko.EkoDMT;
import com.msmart.eko.EkoDMTDao;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Path("AEDMRR")
public class EKODMRController {


	Logger logger = Logger.getLogger(EKODMRController.class);

	@Context HttpServletRequest request;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/EFindSender")
	public JSONObject  findSender(JSONObject reqObj) throws JSONException
	{
		logger.info(reqObj.toString());

		String key=reqObj.getString("Key");
		String AgentID=reqObj.getString("AgentID");
		String senderId=reqObj.getString("SenderId");
		JSONObject jsonObject=null;
		try {
			CommonServiceDao commonServiceDao=new CommonServiceDao();
			String txn_key_val=commonServiceDao.txn_key_Validation(key, AgentID);
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
				EkoDMT ekoDMT=new EkoDMT();
				jsonObject=ekoDMT.findSender(senderId);
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
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/EReSendOTPRegisterSender")
	public JSONObject  reSendOTPRegisterSender(JSONObject reqObj) throws JSONException
	{
		logger.info(reqObj.toString());

		String key=reqObj.getString("Key");
		String AgentID=reqObj.getString("AgentID");
		String senderId=reqObj.getString("SenderId");
		JSONObject jsonObject=null;
		try {
			CommonServiceDao commonServiceDao=new CommonServiceDao();
			String txn_key_val=commonServiceDao.txn_key_Validation(key, AgentID);
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
				EkoDMT ekoDMT=new EkoDMT();
				jsonObject=ekoDMT.resendOtp(senderId);
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
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/ERegisterSender")
	public JSONObject  registerSender(JSONObject reqObj) throws JSONException
	{
		logger.info(reqObj.toString());

		String key=reqObj.getString("Key");
		String AgentID=reqObj.getString("AgentID");
		String senderId=reqObj.getString("SenderId");
		String senderName=reqObj.getString("SenderName");
		String address=reqObj.getString("Address");
		String dob=reqObj.getString("DOB");
		JSONObject jsonObject=null;
		try {
			CommonServiceDao commonServiceDao=new CommonServiceDao();
			String txn_key_val=commonServiceDao.txn_key_Validation(key, AgentID);
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
				EkoDMT ekoDMT=new EkoDMT();
				jsonObject=ekoDMT.registerSender(senderId,senderName,address,dob);
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
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/EVerifySender")
	public JSONObject  verifySender(JSONObject reqObj) throws JSONException
	{
		logger.info(reqObj.toString());

		String key=reqObj.getString("Key");
		String AgentID=reqObj.getString("AgentID");
		String senderId=reqObj.getString("SenderId");
		String otp=reqObj.getString("OTP");
		JSONObject jsonObject=null;
		try {
			CommonServiceDao commonServiceDao=new CommonServiceDao();
			String txn_key_val=commonServiceDao.txn_key_Validation(key, AgentID);
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
				EkoDMT ekoDMT=new EkoDMT();
				jsonObject=ekoDMT.verifySender(senderId,otp);
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
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/EAddBeneAfterVerify")
	public JSONObject addBene(JSONObject reqObj) throws JSONException
	{
		logger.info(reqObj.toString());

		String key=reqObj.getString("Key");
		String AgentID=reqObj.getString("AgentID");
		String senderId=reqObj.getString("SenderId");
		String BankAccount=reqObj.getString("BankAccount");
		String BankCode=reqObj.optString("BankCode");
		String IFSC=reqObj.getString("IFSC");
		String BeneName=reqObj.getString("BeneName");
		String BeneMobile=reqObj.getString("BeneMobile");
		String verify=reqObj.getString("varify");
		String ip=request.getRemoteAddr();
		JSONObject jsonObject=null;
		try {
			CommonServiceDao commonServiceDao=new CommonServiceDao();
			String txn_key_val=commonServiceDao.txn_key_Validation(key, AgentID);
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
				EkoDMT ekoDMT=new EkoDMT();
				jsonObject=ekoDMT.AddBeneAfterVerify(AgentID,BankAccount, IFSC, BeneName, BeneMobile, senderId,BankCode,verify,ip);
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
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/EGetBankList")
	public JSONObject getBankList(JSONObject reqObj) throws JSONException
	{
		logger.info(reqObj.toString());

		String key=reqObj.getString("Key");
		String AgentID=reqObj.getString("AgentID");
		String senderId=reqObj.getString("SenderId");
		JSONObject jsonObject=new JSONObject();
		try {
			CommonServiceDao commonServiceDao=new CommonServiceDao();
			String txn_key_val=commonServiceDao.txn_key_Validation(key, AgentID);
			if(senderId==null || senderId.length()<10){
				jsonObject.put("Status", "1");
				jsonObject.put("message", "Invalid Sender Id!");
			}else if(txn_key_val.equalsIgnoreCase("Y"))
			{

				JSONArray bankList=EkoDMTDao.getAllBankNameandCodeNEFT();
				if(bankList!=null && bankList.size()>0){
					jsonObject.put("Status", "0");
					jsonObject.put("message", "Success");
					jsonObject.put("BankList", bankList.toString());
				}else{
					jsonObject.put("Status", "1");
					jsonObject.put("message", "Bank Not Available");
				}

			}else{
				jsonObject.put("Status", "1");
				jsonObject.put("message", "Invalid request!");
			}

		} catch (Exception e) {
			e.printStackTrace();
			jsonObject=new JSONObject();
			jsonObject.put("Status", "1");
			jsonObject.put("message", "Unable to process your request. Please try Later");
			return jsonObject;
		}


		return jsonObject;
	}

	//Account Verify By BankAccount IFSC

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/EAccountVerifyByBankAccountIFSC")
	public JSONObject accountVerifyByBankIFSC(JSONObject reqObj) throws JSONException
	{
		logger.info(reqObj.toString());

		String key=reqObj.getString("Key");
		String AgentID=reqObj.getString("AgentID");
		String senderId=reqObj.getString("SenderId");
		String BankAccount=reqObj.getString("BankAccount");
		String BankCode=reqObj.getString("BankCode");
		String IFSC=reqObj.getString("IFSC");
		JSONObject jsonObject=null;
		try {

			String ip=request.getRemoteAddr();

			CommonServiceDao commonServiceDao=new CommonServiceDao();
			String txn_key_val=commonServiceDao.txn_key_Validation(key, AgentID);
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
				EkoDMT ekoDMT=new EkoDMT();
				jsonObject=ekoDMT.verifiAccount(AgentID, BankAccount, IFSC, BankCode, senderId,ip);
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
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/EGetBankDetailsByIFSC")
	public JSONObject getBankByIFSC(JSONObject reqObj) throws JSONException
	{
		logger.info(reqObj.toString());

		String key=reqObj.getString("Key");
		String AgentID=reqObj.getString("AgentID");
		String senderId=reqObj.getString("SenderId");
		JSONObject jsonObject=null;
		try {
			CommonServiceDao commonServiceDao=new CommonServiceDao();
			String txn_key_val=commonServiceDao.txn_key_Validation(key, AgentID);
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
				EkoDMT ekoDMT=new EkoDMT();
				jsonObject=ekoDMT.findSender(senderId);
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


	//Delete Beneficiary

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/EDeleteBene")
	public JSONObject deleteBene(JSONObject reqObj) throws JSONException
	{
		logger.info(reqObj.toString());

		String key=reqObj.getString("Key");
		String AgentID=reqObj.getString("AgentID");
		String senderId=reqObj.getString("SenderId");
		String beneficiaryId=reqObj.getString("BeneficiaryId");

		JSONObject jsonObject=null;
		try {
			CommonServiceDao commonServiceDao=new CommonServiceDao();
			String txn_key_val=commonServiceDao.txn_key_Validation(key, AgentID);
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
				EkoDMT ekoDMT=new EkoDMT();
				jsonObject=ekoDMT.deleteBene( senderId, AgentID,beneficiaryId);
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
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/EInitiateTransaction")
	public JSONObject  initiateTransaction(JSONObject reqObj) throws JSONException
	{
		logger.info(reqObj.toString());
		String ip=request.getRemoteHost();
		String key=reqObj.getString("Key");
		String AgentID=reqObj.getString("AgentID");
		String senderId=reqObj.getString("SenderId");

		JSONObject jsonObject=null;

		try {
			CommonServiceDao commonServiceDao=new CommonServiceDao();
			String txn_key_val=commonServiceDao.txn_key_Validation(key, AgentID);
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
				EkoDMT ekoDMT=new EkoDMT();
				jsonObject=ekoDMT.ekoTransaction(reqObj,ip,request);
			}

		} catch (Exception e) {
			e.printStackTrace();
			jsonObject=new JSONObject();
			jsonObject.put("Status", "1");
			jsonObject.put("message", "Unable to process your request. Please try Later");
			return jsonObject;
		}

		System.out.println("Transaction Response : "+jsonObject);
		return jsonObject;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/ESenderHistory")

	public JSONObject senderHistory(JSONObject jsonObject)	
	{
		JSONObject response=new JSONObject(); 
		try {
			logger.info("DMRController.senderHistory() : "+jsonObject.toString());
			String key=jsonObject.getString("Key");
			String AgentID=jsonObject.getString("AgentID");

			String SenderId=jsonObject.getString("SenderId");

			CommonServiceDao commonServiceDao=new CommonServiceDao();
			String txn_key_val=commonServiceDao.txn_key_Validation(key, AgentID);
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
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/ETransStatus")

	public JSONObject transStatus(JSONObject jsonObject)	
	{
		JSONObject response=new JSONObject(); 
		try {
			String key=jsonObject.getString("Key");
			String AgentID=jsonObject.getString("AgentID");
			String SenderId=jsonObject.getString("SenderId");
			logger.info("DMRController.findSender()");

			CommonServiceDao commonServiceDao=new CommonServiceDao();
			String txn_key_val=commonServiceDao.txn_key_Validation(key, AgentID);
			if(!txn_key_val.equalsIgnoreCase("Y"))
			{
				response.put("Status", "1");
				response.put("message", "Invalid request!");

			}else if(SenderId==null || SenderId.length()<10){
				jsonObject.put("Status", "1");
				jsonObject.put("message", "Invalid Sender Id!");
			}else{

				String TransactionRefNo=jsonObject.getString("TransactionRefNo");

				EkoDMT EkoDMT=new EkoDMT();
				response=EkoDMT.tranStatus(SenderId,TransactionRefNo);
			}


		} catch (Exception e) {
			e.printStackTrace();
			response=new JSONObject();
			response.put("Status", "1");
			response.put("message", "Failure");
			return response;
		}

		return response;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/ERefundTransaction")

	public JSONObject refundDMR(JSONObject jsonObject)	
	{
		JSONObject response=new JSONObject(); 
		try {
			String key=jsonObject.getString("Key");
			String AgentID=jsonObject.getString("AgentID");
			String SenderId=jsonObject.getString("SenderId");
			logger.info("DMRController.refundDMR() "+jsonObject.toString());

			CommonServiceDao commonServiceDao=new CommonServiceDao();
			String txn_key_val=commonServiceDao.txn_key_Validation(key, AgentID);
			if(!txn_key_val.equalsIgnoreCase("Y"))
			{
				response.put("Status", "1");
				response.put("message", "Invalid request!");

			}else{

				String TransactionRefNo=jsonObject.getString("TransactionRefNo");

				EkoDMT dao=new EkoDMT();
				response=dao.initiateRefundTransaction(SenderId, TransactionRefNo)	;		
			}
			logger.info(response);

		} catch (Exception e) {
			e.printStackTrace();
			response=new JSONObject();
			response.put("Status", "1");
			response.put("message", "Failure");
			return response;
		}

		return response;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/ERefundDMRConfirm")

	public JSONObject refundDMRConfirm(JSONObject jsonObject)	
	{
		JSONObject response=new JSONObject(); 
		try {
			String key=jsonObject.getString("Key");
			String AgentID=jsonObject.getString("AgentID");
			String SenderId=jsonObject.getString("SenderId");
			logger.info("DMRController.findSender()");

			CommonServiceDao commonServiceDao=new CommonServiceDao();
			String txn_key_val=commonServiceDao.txn_key_Validation(key, AgentID);
			if(!txn_key_val.equalsIgnoreCase("Y"))
			{
				response.put("Status", "1");
				response.put("message", "Invalid request!");

			}else{

				String TransactionRefNo=jsonObject.getString("TransactionRefNo");
				String OTP=jsonObject.getString("OTP");
				EkoDMT dao=new EkoDMT();
				response=dao.confirmRefundTransaction(SenderId, TransactionRefNo,OTP,AgentID)	;		
			}


		} catch (Exception e) {
			e.printStackTrace();
			response=new JSONObject();
			response.put("Status", "1");
			response.put("message", "Failure");
			return response;
		}

		return response;
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/EBankDetails")

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

				String BankCode=jsonObject.getString("BankCode");
				String IFSC=jsonObject.optString("IFSC");

				EkoDMT EkoDMT=new EkoDMT();
				response=EkoDMT.bankDetails(BankCode,IFSC);
			}


		} catch (Exception e) {
			e.printStackTrace();
			response=new JSONObject();
			response.put("Status", "1");
			response.put("message", "Failure");
			return response;
		}

		return response;
	}

}
