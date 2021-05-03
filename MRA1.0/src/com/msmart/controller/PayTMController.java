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

import com.msmart.api.controller.VenusDMT;
import com.msmart.dao.CommonServiceDao;
import com.msmart.dao.MobileAppRechargeDao;
import com.msmart.dao.StatementNReportDao;
import com.msmart.eko.EkoDMTDao;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Path("Venus")
public class PayTMController {


	Logger logger = Logger.getLogger(EKODMRController.class);

	@Context HttpServletRequest request;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/FindSender")
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
				VenusDMT VenusDMT=new VenusDMT();
				jsonObject=VenusDMT.findSender(senderId);
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
	@Path("/ReSendOTPRegisterSender")
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
				VenusDMT VenusDMT=new VenusDMT();
				jsonObject=VenusDMT.resendOtp(senderId);
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
	@Path("/RegisterSender")
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
				VenusDMT VenusDMT=new VenusDMT();
				jsonObject=VenusDMT.registerSender(senderId,senderName,address,dob);
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
	@Path("/VerifySender")
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
				VenusDMT VenusDMT=new VenusDMT();
				jsonObject=VenusDMT.verifySender(senderId,otp);
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
	@Path("/AddBeneAfterVerify")
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
				VenusDMT VenusDMT=new VenusDMT();
				jsonObject=VenusDMT.AddBeneAfterVerify(AgentID,BankAccount, IFSC, BeneName, BeneMobile, senderId,BankCode,verify,ip);
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
	@Path("/GetBankList")
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
	@Path("/AccountVerifyByBankAccountIFSC")
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
				VenusDMT VenusDMT=new VenusDMT();
				jsonObject=VenusDMT.verifiAccount(AgentID, BankAccount, IFSC, BankCode, senderId,ip);
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
	@Path("/DeleteBene")
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
				VenusDMT VenusDMT=new VenusDMT();
				jsonObject=VenusDMT.deleteBene( senderId, AgentID,beneficiaryId);
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
	@Path("/InitiateTransaction")
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
				VenusDMT VenusDMT=new VenusDMT();
				jsonObject=VenusDMT.ekoTransaction(reqObj,ip);
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
	@Path("/SenderHistory")

	public JSONObject senderHistory(JSONObject jsonObject)	
	{
		JSONObject response=new JSONObject(); 
		try {
			logger.info("PayTMController.senderHistory() : "+jsonObject.toString());
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
	@Path("/TransStatus")

	public JSONObject transStatus(JSONObject jsonObject)	
	{
		JSONObject response=new JSONObject(); 
		try {
			String key=jsonObject.getString("Key");
			String AgentID=jsonObject.getString("AgentID");
			String SenderId=jsonObject.getString("SenderId");
			logger.info("PayTMController.TransStatus()");

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

				VenusDMT VenusDMT=new VenusDMT();
				response=VenusDMT.tranStatus(SenderId,TransactionRefNo);
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

	/*@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/RefundTransaction")

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

				VenusDMT dao=new VenusDMT();
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
	@Path("/RefundDMRConfirm")

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
				VenusDMT dao=new VenusDMT();
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
	}*/
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/BankDetails")

	public JSONObject bankDetails(JSONObject jsonObject)	
	{
		JSONObject response=new JSONObject(); 
		try {
			String key=jsonObject.getString("Key");
			String AgentID=jsonObject.getString("AgentID");
			logger.info("PayTMController.BankDetails()");

			MobileAppRechargeDao services=new MobileAppRechargeDao();
			String txn_key_val=services.txn_key_Validation(key, AgentID);
			if(!txn_key_val.equalsIgnoreCase("Y"))
			{
				response.put("Status", "1");
				response.put("message", "Invalid request!");

			}else{

				response.put("Status", "0");
				response.put("message", "Bank Available");
				response.put("ifsc_status", "1");
				response.put("isverificationavailable", "1");
				response.put("available_channels", "0");
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
