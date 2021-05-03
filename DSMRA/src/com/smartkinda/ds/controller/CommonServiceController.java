package com.smartkinda.ds.controller;

import java.util.Date;
import java.util.HashMap;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.DistributorDepositRequest.DistributorDepositRequestDao;
import com.PushBalance.PushBalanceAction;
import com.TransactionDetails.CheckDetailsAction;
import com.agentDetails.AgentDetailAction;
import com.agentregistration.AgentRegistrationAction;
import com.common.CommonDetailDao;
import com.common.PropertyFile;
import com.common.SendSmtpMail;
import com.disttributordetails.DistributorProfileDao;
import com.dsamount.DistributorJournalForm;
import com.login.LoginDao;
import com.report.DistributorAccountStatement.DistributorAccountStatementDao;

@Path("/DSCommonService")
public class CommonServiceController {

	Logger logger = Logger.getLogger(LoginController.class);

	@Context HttpServletRequest request;

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/PushBalance")

	public JSONObject pushBalance(JSONObject jsonObject)	
	{

		JSONObject responseJson=new JSONObject();
		//LoginId,Password
		logger.info("pushBalance : "+request.getQueryString());

		try {

			PushBalanceAction pushDao=new PushBalanceAction();
			responseJson=pushDao.pushBalanceAction(jsonObject,request);


		} catch (Exception e) {
			e.printStackTrace();
			responseJson=new JSONObject();
			responseJson.put("message","Process aborted due to Technical Failure.");
			responseJson.put("status", "1");

			return responseJson;
		}
		return responseJson;
	}
	//-------------- Agent Details / View Agent -------------------------//

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/Viewagent")

	public JSONObject agentDetails(JSONObject jsonObject)	
	{

		JSONObject responseJson=new JSONObject();
		//LoginId,Password
		System.out.println("AgentDetails : "+jsonObject.toString());

		try {

			AgentDetailAction detailAction =new AgentDetailAction();
			responseJson=detailAction.viewAgent(jsonObject);


		} catch (Exception e) {
			e.printStackTrace();
			responseJson=new JSONObject();
			responseJson.put("message","Process aborted due to Technical Failure.");
			responseJson.put("status", "1");

			return responseJson;
		}
		return responseJson;
	}

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/ActiveDeactiveAgent")

	public JSONObject activeDeactiveAgent(JSONObject jsonObject)	
	{

		JSONObject responseJson=new JSONObject();
		//LoginId,Password
		logger.info("AgentDetails : "+request.getQueryString());

		try {

			AgentDetailAction detailAction =new AgentDetailAction();
			responseJson=detailAction.viewAgent(jsonObject);


		} catch (Exception e) {
			e.printStackTrace();
			responseJson=new JSONObject();
			responseJson.put("message","Process aborted due to Technical Failure.");
			responseJson.put("status", "1");

			return responseJson;
		}
		return responseJson;
	}

	//----------------- Transaction Status --------------------------------//

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/TransationStatus")

	public JSONObject transactionStatus(JSONObject jsonObject)	
	{

		JSONObject responseJson=new JSONObject();
		//LoginId,Password
		logger.info("transactionPopUp : "+request.getQueryString());

		try {

			CheckDetailsAction checkDetailsAction = new  CheckDetailsAction();
			responseJson=checkDetailsAction.transactionStatus(jsonObject);


		} catch (Exception e) {
			e.printStackTrace();
			responseJson=new JSONObject();
			responseJson.put("message","Process aborted due to Technical Failure.");
			responseJson.put("status", "1");

			return responseJson;
		}
		return responseJson;
	}

	//----------------- Select State --------------------------------//
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/SelectState")

	public JSONObject getState(JSONObject jsonObject )	
	{
		JSONObject registerJsonObject=new JSONObject();
		logger.info("selectState : "+request.getQueryString());
		try 
		{
			AgentRegistrationAction action=new AgentRegistrationAction();
			registerJsonObject=action.getState(jsonObject);
		} catch (Exception e) {

			e.printStackTrace();
			registerJsonObject=new JSONObject();
			registerJsonObject.put("status", "1");
			registerJsonObject.put("message", "Process aborted due to Technical Failure.");
			return registerJsonObject;
		}
		return registerJsonObject;
	}


	//-----------------  StateDistrict	order by state --------------------------------//

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/DistrictByState")

	public JSONObject getStateDistrict(JSONObject jsonObject )	
	{
		JSONObject districtJsonObject=new JSONObject();
		logger.info("StateDistrict : "+request.getQueryString());
		try 
		{
			AgentRegistrationAction action=new AgentRegistrationAction();
			districtJsonObject=action.getStateDistrict(jsonObject);
		} catch (Exception e) {

			e.printStackTrace();
			districtJsonObject=new JSONObject();
			districtJsonObject.put("status", "1");
			districtJsonObject.put("message", "Process aborted due to Technical Failure.");
			return districtJsonObject;
		}
		return districtJsonObject;
	}


	//----------------- Agent Registration In DS Panel --------------------------------//

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/AgentRegister")

	public JSONObject agentRegistrationAction(JSONObject jsonObject )	
	{
		JSONObject registerJsonObject=new JSONObject();
		logger.info("AgentRegistration : "+request.getQueryString());
		try 
		{
			AgentRegistrationAction registrationAction=new AgentRegistrationAction();
			registerJsonObject=registrationAction.agentRegistrationAction(jsonObject);
		} catch (Exception e) {

			e.printStackTrace();
			registerJsonObject=new JSONObject();
			registerJsonObject.put("status", "1");
			registerJsonObject.put("message", "Invalid Request!");
			return registerJsonObject;
		}
		return registerJsonObject;
	}

	//----------------- Update Agent Details --------------------------------//

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/UpdateAgent")

	public JSONObject updateAgentDetails(JSONObject jsonObject )	
	{
		JSONObject registerJsonObject=new JSONObject();
		logger.info("AgentRegistration : "+request.getQueryString());
		try 
		{
			AgentDetailAction agentDetailAction=new AgentDetailAction();
			registerJsonObject=agentDetailAction.viewAgent(jsonObject);
		} catch (Exception e) {

			e.printStackTrace();
			registerJsonObject=new JSONObject();
			registerJsonObject.put("status", "1");
			registerJsonObject.put("message", "Invalid Request!");
			return registerJsonObject;
		}
		return registerJsonObject;
	}

	
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/DSHistory")

	public JSONObject dSHistory(JSONObject jsonObject)	
	{

		JSONObject responseJson=new JSONObject();
		//LoginId,Password
		logger.info("AgentDetails : "+request.getQueryString());

		try {

			String userId=jsonObject.getString("distributorId");
			String txnkey=jsonObject.getString("txnkey");
			if(txnkey!=null){

				LoginDao dao=new LoginDao();
				if(dao.varifyTxnKey(Long.parseLong(userId), txnkey)){


					DistributorAccountStatementDao detailAction =new DistributorAccountStatementDao();
					JSONArray arry=detailAction.getDistributorAccountStatementDetails(userId);
					if (arry!=null && arry.size()>0){
						responseJson.put("Statement",arry.toString());
						responseJson.put("message","Success");
						responseJson.put("status","0");
					}else{
						responseJson.put("message","Transaction not available.");
						responseJson.put("status","1");
					}


				}else{
					responseJson.put("message", "Invalid request.");
					responseJson.put("status", "1");
				}
			}else{
				responseJson.put("message", "Invalid request.");
				responseJson.put("status", "1");
			}
			


		} catch (Exception e) {
			e.printStackTrace();
			responseJson=new JSONObject();
			responseJson.put("message","Process aborted due to Technical Failure.");
			responseJson.put("status", "1");

			return responseJson;
		}
		return responseJson;
	}
	
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/GetBalance")

	public JSONObject getBalance(JSONObject requestObject )	
	{
		JSONObject responseJson=new JSONObject();
		logger.info("GetBalance : "+request.getQueryString());
		try 
		{
			
			String userId=requestObject.getString("distributorId");
			String txnkey=requestObject.getString("txnkey");
			if(txnkey!=null){

				LoginDao dao=new LoginDao();
				if(dao.varifyTxnKey(Long.parseLong(userId), txnkey)){
					
					CommonDetailDao commonDetailDao=new CommonDetailDao();
					String bal=commonDetailDao.getDsbalance(userId);
					
					responseJson.put("balaance", bal);
					responseJson.put("status", "0");
					responseJson.put("message", "Success");
				}else{
					responseJson.put("message", "Invalid request.");
					responseJson.put("status", "1");

				}
			}
			
		} catch (Exception e) {

			e.printStackTrace();

			requestObject.put("status", "1");
			requestObject.put("message", "Invalid Request!");
			return requestObject;
		}
		return responseJson;
	}

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/AccountStatement")

	public JSONObject accountStatement(JSONObject requestObject )	
	{
		JSONObject registerJsonObject=new JSONObject();
		logger.info("selectState : "+requestObject.toString());
		try 
		{
			String userId=requestObject.getString("distributorId");
			String txnkey=requestObject.getString("txnkey");
			if(txnkey!=null){

				LoginDao dao=new LoginDao();
				if(dao.varifyTxnKey(Long.parseLong(userId), txnkey)){
					DistributorAccountStatementDao accountStatementDao=new DistributorAccountStatementDao();
					JSONArray list=accountStatementDao.getDistributorAccountStatementDetails(userId);
					if(list!=null && list.size()>0){
						registerJsonObject.put("statement", list.toString());
						registerJsonObject.put("status", "0");
						registerJsonObject.put("message", "Success");
					}
				}else{
					registerJsonObject.put("message", "Invalid request.");
					registerJsonObject.put("status", "1");
				}
			}else{
				
			}
		} catch (Exception e) {

			e.printStackTrace();
			registerJsonObject=new JSONObject();
			registerJsonObject.put("status", "1");
			registerJsonObject.put("message", "Process aborted due to Technical Failure.");
			return registerJsonObject;
		}
		return registerJsonObject;
	}
	
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/CollectionBanks")

	public JSONObject collectionBanks(JSONObject requestObject )	
	{
		JSONObject registerJsonObject=new JSONObject();
		logger.info("selectState : "+requestObject.toString());
		try 
		{
			String userId=requestObject.getString("distributorId");
			String txnkey=requestObject.getString("txnkey");
			if(txnkey!=null){

				LoginDao dao=new LoginDao();
				if(dao.varifyTxnKey(Long.parseLong(userId), txnkey)){
					DistributorDepositRequestDao depositRequestDao=new DistributorDepositRequestDao();
					JSONArray list=depositRequestDao.collectionBank(Long.parseLong(userId));
					if(list!=null && list.size()>0){
						registerJsonObject.put("data", list.toString());
						registerJsonObject.put("status", "0");
						registerJsonObject.put("message", "Success");
					}
				}else{
					registerJsonObject.put("message", "Invalid request.");
					registerJsonObject.put("status", "1");
				}
			}else{
				
			}
		} catch (Exception e) {

			e.printStackTrace();
			registerJsonObject=new JSONObject();
			registerJsonObject.put("status", "1");
			registerJsonObject.put("message", "Process aborted due to Technical Failure.");
			return registerJsonObject;
		}
		return registerJsonObject;
	}

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/WalletBalRequest")

	public JSONObject walletBalReq(JSONObject requestObject )	
	{

		JSONObject registerJsonObject=new JSONObject();
		logger.info("selectState : "+requestObject.toString());

		try {

			String userId=requestObject.getString("distributorId");
			String txnkey=requestObject.getString("txnkey");

			if(txnkey!=null){

				LoginDao dao=new LoginDao();
				if(dao.varifyTxnKey(Long.parseLong(userId), txnkey)){

					final String mdID=requestObject.getString("mdID");
					final String bankName=requestObject.getString("bankName");
					final String refId=requestObject.getString("refId");
					final String amount=requestObject.getString("amount");
					final String depositDate=requestObject.getString("depositDate");
					final String remark=requestObject.getString("remark");

					final String mode=requestObject.getString("mode");

					String prefix="";

					Date now = new Date();
					long logntranId = now.getTime();

					prefix="100"+userId;
					String suffix = new String();
					suffix=String.valueOf(logntranId);
					final String TransactionId=prefix+suffix;
					
					DistributorJournalForm DistributorJournalForm=new DistributorJournalForm();

					DistributorJournalForm.setMdId(Integer.parseInt(mdID));
					DistributorJournalForm.setDistributorId(userId);
					DistributorJournalForm.setTransactionJournalId(TransactionId);
					DistributorJournalForm.setInitial("DISTJ");
					DistributorJournalForm.setModeOfPayment(mode);
					DistributorJournalForm.setAmountToCredit(Double.parseDouble(amount));
					DistributorJournalForm.setBankCharges(0);
					DistributorJournalForm.setAcceptedAmount(Double.parseDouble(amount));
					DistributorJournalForm.setBankName(bankName);
					DistributorJournalForm.setBranchName("");
					DistributorJournalForm.setAccountNumber("");
					DistributorJournalForm.setBankTransactionId(refId);
					DistributorJournalForm.setReferenceNo(refId);
					DistributorJournalForm.setDepositorBankName("");
					DistributorJournalForm.setDepositDate(depositDate);
					DistributorJournalForm.setStatus("Pending");
					DistributorJournalForm.setRemarks(remark);
					DistributorJournalForm.setDepositorAccountNumber("");
					DistributorJournalForm.setDepositorName("");
					
					DistributorDepositRequestDao depositRequestDao=new DistributorDepositRequestDao();
					String result=depositRequestDao.saveETransferDep(DistributorJournalForm);
					if("success".equalsIgnoreCase(result)){
						registerJsonObject.put("message", "Balance request submitted successfully.");
						registerJsonObject.put("status", "0");
						
						try {
							
							DistributorDepositRequestDao requestDao=new DistributorDepositRequestDao();
							HashMap<String,String> map=requestDao.getMDSMailId(userId);
							
							final String DSID=map.get("DSID");

							Thread thread = new Thread(new Runnable() {
								@Override
								public void run() {

									try {
										
										String[] receipient={PropertyFile.supportMail};
										String subject="Wallet balance request from DS :: "+DSID;
										String message="<html><BODY cellpadding=\"0\" cellspacing=\"0\">"
												+ "<table width=\"100%\" >"
												+ "<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;'>Dear &nbsp;Team,</td></tr>"+
												"<p><tr><td>"+
												"<table width=\"100%\" border=\"1\">"+
												"<tr><td width=\"40%\">DS ID</td><td width=\"6%\"><div align=\"center\"><strong>:</strong></div></td><td width=\"54%\">"+DSID+"</td></tr>"+
												"<tr><td width=\"40%\">Payment Mode</td><td width=\"6%\"><div align=\"center\"><strong>:</strong></div></td><td width=\"54%\">"+mode+"</td></tr>"+
												"<tr><td width=\"40%\">Request Amount</td><td width=\"6%\"><div align=\"center\"><strong>:</strong></div></td><td width=\"54%\">"+amount+"</td></tr>"+
												"<tr><td width=\"40%\">Deposit Date</td><td width=\"6%\"><div align=\"center\"><strong>:</strong></div></td><td width=\"54%\">"+depositDate+"</td></tr>"+
												"<tr><td width=\"40%\">Receiving Bank Name</td><td width=\"6%\"><div align=\"center\"><strong>:</strong></div></td><td width=\"54%\">"+bankName+"</td></tr>"+
												"<tr><td width=\"40%\">Transaction Id</td><td width=\"6%\"><div align=\"center\"><strong>:</strong></div></td><td width=\"54%\">"+TransactionId+"</td></tr>"+
												"<tr><td width=\"40%\">Bank Reference Id</td><td width=\"6%\"><div align=\"center\"><strong>:</strong></div></td><td width=\"54%\">"+refId+"</td></tr>"+
												"</table></td></tr></p>"+
												"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;'Thank You,</td></tr>"+
												"<tr><td>&nbsp;</td></tr>"+
												"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;'>Team SmartKinda\nHelpDesk No. +91-9711402774 Website : http://partner.smartkinda.com</td></tr><br>"+				   
												"</tr></td></table></BODY></html>";
						            	
										SendSmtpMail.sendSSLMessage(receipient, subject, message, "no-reply@smartkinda.com");
										
									} catch (MessagingException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									
								}

							});
							thread.start();



						} catch (Exception e) {
							e.printStackTrace();
						}
						
						
					}else{
						registerJsonObject.put("message", "Unable to submit request. Please try later.");
						registerJsonObject.put("status", "1");
					}
					
					
				}else{
					registerJsonObject.put("message", "Invalid request.");
					registerJsonObject.put("status", "1");
				}
			}else{
				
			}
			

			return registerJsonObject;



		} catch (Exception e) {

			e.printStackTrace();
			e.printStackTrace();
			registerJsonObject=new JSONObject();
			registerJsonObject.put("status", "1");
			registerJsonObject.put("message", "Process aborted due to Technical Failure.");
			return registerJsonObject;
		}
	}
	
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/WalletBalReqDetails")

	public JSONObject balReqDetails(JSONObject requestObject )	
	{

		JSONObject registerJsonObject=new JSONObject();
		logger.info("selectState : "+requestObject.toString());
		try 
		{
			String userId=requestObject.getString("distributorId");
			String txnkey=requestObject.getString("txnkey");
			if(txnkey!=null){

				LoginDao dao=new LoginDao();
				if(dao.varifyTxnKey(Long.parseLong(userId), txnkey)){
					DistributorProfileDao distributorProfileDao=new DistributorProfileDao();
					JSONArray list=distributorProfileDao.getDSJournal(userId);
					if(list!=null && list.size()>0){
						registerJsonObject.put("data", list.toString());
						registerJsonObject.put("status", "0");
						registerJsonObject.put("message", "Success");
					}
				}else{
					registerJsonObject.put("message", "Invalid request.");
					registerJsonObject.put("status", "1");
				}
			}else{
				
			}
		} catch (Exception e) {

			e.printStackTrace();
			registerJsonObject=new JSONObject();
			registerJsonObject.put("status", "1");
			registerJsonObject.put("message", "Process aborted due to Technical Failure.");
			return registerJsonObject;
		}
		return registerJsonObject;
	}

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/AccountStatementByDate")

	public JSONObject accountStatementbyDate(JSONObject requestObject )	
	{
		JSONObject registerJsonObject=new JSONObject();
		logger.info("selectState : "+request.getQueryString());
		try 
		{
			String userId=requestObject.getString("distributorId");
			String txnkey=requestObject.getString("txnkey");
			String fromDate=requestObject.getString("fromDate");
			String toDate=requestObject.getString("toDate");
			if(txnkey!=null){

				LoginDao dao=new LoginDao();
				if(dao.varifyTxnKey(Long.parseLong(userId), txnkey)){

					DistributorAccountStatementDao accountStatementDao=new DistributorAccountStatementDao();
					JSONArray list=accountStatementDao.getDistributorAccountStatementDetailsByDate(userId,fromDate,toDate);
					if(list!=null && list.size()>0){
						registerJsonObject.put("statement", list.toString());
						registerJsonObject.put("status", "0");
						registerJsonObject.put("message", "Success");
					}else{
						registerJsonObject.put("status", "1");
						registerJsonObject.put("message", "No date available");
					}

				}
			}else{

			}
		} catch (Exception e) {

			e.printStackTrace();
			registerJsonObject=new JSONObject();
			registerJsonObject.put("status", "1");
			registerJsonObject.put("message", "Process aborted due to Technical Failure.");
			return registerJsonObject;
		}
		return registerJsonObject;
	}


	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/GetLogoUrlAndCompanyName")

	public JSONObject GetLogoUrlAndCompanyName(JSONObject reqObj)	
	{

		JSONObject responseJson=null;
		String logoUrl=null;
		
		logger.info("GetLogoUrl : "+request.getQueryString());
		String clientId=reqObj.getString("ClientId");

		try {
			
			CommonDetailDao commonDetailDao=new CommonDetailDao();
			responseJson=commonDetailDao.getLogoUrlAndCompanyName(clientId);
			
			if(null!=responseJson){
				responseJson.put("message","Request Successful.One Url found.");
				responseJson.put("status", "0");
			}
			else
			{
				responseJson=new JSONObject();
				responseJson.put("message","Request Failed.No Url found for logo.");
				responseJson.put("status", "1");
			}

		} catch (Exception e) {
			e.printStackTrace();
			responseJson=new JSONObject();
			responseJson.put("message","Request Failed.No any Url found for logo.");
			responseJson.put("status", "1");

			return responseJson;
		}
		return responseJson;
	}
	
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/GetAllServiceBuisinessDone")

	public JSONObject getAllServiceBuisinessDone(JSONObject jsonObject)	
	{

		JSONObject responseJson=null;
		//LoginId,Password
		logger.info("pushBalance : "+request.getQueryString());

		try {

			CommonDetailDao dao =new CommonDetailDao();
			JSONArray distributorBuisinessDetails=dao.getAllServiceBuisinessDone(jsonObject.getString("distributor_id"));
			if(null!=distributorBuisinessDetails)
			{
				responseJson=new JSONObject();
				responseJson.put("distributorBuisinessDetails",distributorBuisinessDetails );
				responseJson.put("message","Buisiness details fetched successfully");
				responseJson.put("status", "0");
			}
			else
			{
				responseJson=new JSONObject();
				responseJson.put("message","Currently no buisiness details available");
				responseJson.put("status", "1");
			}


		} catch (Exception e) {
			e.printStackTrace();
			responseJson=new JSONObject();
			responseJson.put("message","Process aborted due to Technical Failure.");
			responseJson.put("status", "1");

			return responseJson;
		}
		return responseJson;
	}
	
}
