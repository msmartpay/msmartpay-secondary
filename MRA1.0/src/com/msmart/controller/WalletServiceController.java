package com.msmart.controller;
import java.util.HashMap;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.apache.log4j.Logger;

import com.msmart.dao.CommonServiceDao;
import com.msmart.dao.MobileAppRechargeDao;
import com.msmart.dao.MoneyTransferDao;
import com.msmart.dao.TBRequestDao;
import com.msmart.dao.TicketDao;
import com.msmart.service.CommonServices;
import com.msmart.service.PropertyFile;
import com.msmart.util.SendSmtpMail;
import com.msmart.util.UtilityP;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Path("SKWS")
public class WalletServiceController {


	Logger logger = Logger.getLogger(WalletServiceController.class);


	@Context HttpServletRequest request;

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/WalletTransfer")

	public JSONObject getCity(JSONObject jsonObject)	
	{
		JSONObject transresponseJsonObject=new JSONObject(); 
		try {

			String serviceName="Wallet to Wallet";
			
			String agentId=jsonObject.getString("agent_id");

			CommonServiceDao commonServiceDao=new CommonServiceDao();
			MoneyTransferDao moneyTransferDao=new MoneyTransferDao();

			String txn_key=jsonObject.getString("txn_key");
			String txn_key_val=commonServiceDao.txn_key_Validation(txn_key, agentId);
			if(!txn_key_val.equalsIgnoreCase("Y"))
			{
				transresponseJsonObject.put("response-code", "1");
				transresponseJsonObject.put("response-message", "Unauthorized User Request!");
				return transresponseJsonObject;
			}

			String toAgentId=jsonObject.getString("toAgentId");
			String amount=jsonObject.getString("amount");
			String remark=jsonObject.getString("remark");
			toAgentId=toAgentId.replaceAll("AG", "").replaceAll("Ag", "").replaceAll("aG","").replaceAll("ag","");

			String updBal=commonServiceDao.getUpdatedBalance(agentId);

			if(toAgentId==null || toAgentId.equalsIgnoreCase("")|| amount==null || amount.equalsIgnoreCase("")||toAgentId==null || toAgentId.equalsIgnoreCase("")){
				String description="Please fill the required fields.";


				transresponseJsonObject.put("response-code", "1");
				transresponseJsonObject.put("response-message",description);
				transresponseJsonObject.put("balance", updBal);

			}else{

				String transactionId=UtilityP.getTranId_14(agentId); 			
				String IdNo=UtilityP.getTranId_20();
				String ip=request.getRemoteAddr();
				String status=moneyTransferDao.walletToWalletTransfer(IdNo, agentId, toAgentId, transactionId, Double.parseDouble(amount), serviceName, remark, ip);
				System.out.println("MoneyTransferAction.execute() : "+status);
				if("Success".equalsIgnoreCase(status))
				{
					updBal=commonServiceDao.getUpdatedBalance(agentId);
					String description="Wallet Transfer Successful";
					transresponseJsonObject.put("response-code", "0");
					transresponseJsonObject.put("response-message",description);
					transresponseJsonObject.put("balance", updBal);

				}else if("invalid".equalsIgnoreCase(status)){
					String description="Wallet Transfer Failure. Invalid Receiver Agent Id";

					transresponseJsonObject.put("response-code", "1");
					transresponseJsonObject.put("response-message",description);
					transresponseJsonObject.put("balance", updBal);
				}else{
					String description="Wallet Transfer Failure.";

					transresponseJsonObject.put("response-code", "1");
					transresponseJsonObject.put("response-message",description);
					transresponseJsonObject.put("balance", updBal);

				}
			}

			return transresponseJsonObject;

		} catch (Exception e) {
			e.printStackTrace();
			transresponseJsonObject=new JSONObject();
			transresponseJsonObject.put("response-code", "1");
			transresponseJsonObject.put("response-message", "Unable to process your request.");
			return transresponseJsonObject;
		}

	}

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/WalletBalRequest")

	public JSONObject walletBalReq(JSONObject jsonObject )	
	{

		JSONObject transresponseJson=new JSONObject();
		System.out.println("WalletServiceController.walletBalReq()");

		try {
			MobileAppRechargeDao mrd=new MobileAppRechargeDao();

			String agent_id=jsonObject.getString("agent_id");
			String txn_key=jsonObject.getString("txn_key");
			
			final String  agent="AG"+agent_id;

			String txn_key_val=mrd.txn_key_Validation(txn_key, agent_id);
			if(!txn_key_val.equalsIgnoreCase("Y"))
			{
				transresponseJson.put("response-code", "1");
				transresponseJson.put("response-message", "Invalid request!");
				return transresponseJson;

			}else{

				final String bankName=jsonObject.getString("bankName");
				final String refId=jsonObject.getString("refId");
				final String amount=jsonObject.getString("amount");
				final String depositDate=jsonObject.getString("depositDate");
				final String remark=jsonObject.getString("remark");

				final String mode=jsonObject.getString("mode");
				agent_id=agent_id.replaceAll("AG", "").replaceAll("ag", "");
				if((agent_id.length()>0 && bankName.length()>0) && (amount.length()>0 && depositDate.length()>0 )){

					final String  TransactionId=CommonServices.getIdNo(agent_id);

					TBRequestDao dao=new TBRequestDao();
					String status=dao.insertRecordForBalanceReq(agent_id, TransactionId, depositDate, amount, refId, 
							bankName, amount, remark, mode);
					if(status.equalsIgnoreCase("Success"))
					{
						
						try {

							Thread thread = new Thread(new Runnable() {
								@Override
								public void run() {

									try {
										
										String[] receipient={PropertyFile.SUPPORT_EMAIL};
										String subject="Wallet balance request from Agent :: "+agent;
										String message="<html><BODY cellpadding=\"0\" cellspacing=\"0\">"
												+ "<table width=\"100%\" >"
												+ "<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;'>Dear &nbsp;Team,</td></tr>"+
												"<p><tr><td>"+
												"<table width=\"100%\" border=\"1\">"+
												"<tr><td width=\"40%\">Retailer ID</td><td width=\"6%\"><div align=\"center\"><strong>:</strong></div></td><td width=\"54%\">"+agent+"</td></tr>"+
												"<tr><td width=\"40%\">Payment Mode</td><td width=\"6%\"><div align=\"center\"><strong>:</strong></div></td><td width=\"54%\">"+mode+"</td></tr>"+
												"<tr><td width=\"40%\">Request Amount</td><td width=\"6%\"><div align=\"center\"><strong>:</strong></div></td><td width=\"54%\">"+amount+"</td></tr>"+
												"<tr><td width=\"40%\">Deposit Date</td><td width=\"6%\"><div align=\"center\"><strong>:</strong></div></td><td width=\"54%\">"+depositDate+"</td></tr>"+
												"<tr><td width=\"40%\">Receiving Bank Name</td><td width=\"6%\"><div align=\"center\"><strong>:</strong></div></td><td width=\"54%\">"+bankName+"</td></tr>"+
												"<tr><td width=\"40%\">Transaction Id</td><td width=\"6%\"><div align=\"center\"><strong>:</strong></div></td><td width=\"54%\">"+TransactionId+"</td></tr>"+
												"<tr><td width=\"40%\">Bank Reference Id</td><td width=\"6%\"><div align=\"center\"><strong>:</strong></div></td><td width=\"54%\">"+refId+"</td></tr>"+
												"</table></td></tr></p>"+
												"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;'Thank You,</td></tr>"+
												"<tr><td>&nbsp;</td></tr>"+
												"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;'>Team MSmartPay</td></tr><br>"+				   
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
						
						
						transresponseJson.put("response-code", "0");
						transresponseJson.put("response-message", "Balance Request Submitted Successfully.");

					}
					else
					{
						transresponseJson.put("response-code", "1");
						transresponseJson.put("response-message", "Request Failure. Try Again!");
					}

				}else{

					transresponseJson.put("response-code", "1");
					transresponseJson.put("response-message", "Request Failure. Try Again!");


				}
			}


			return transresponseJson;



		} catch (Exception e) {

			e.printStackTrace();
			transresponseJson=new JSONObject();
			transresponseJson.put("status", "1");
			transresponseJson.put("message", "Unable to process your request.");
			return transresponseJson;
		}
	}
	
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/WalletBalReqDetails")

	public JSONObject balReqDetails(JSONObject jsonObject )	
	{

		JSONObject transresponseJson=new JSONObject();
		System.out.println("WalletServiceController.getRestaurantsByArea()");

		try {
			MobileAppRechargeDao mrd=new MobileAppRechargeDao();

			String agent_id=jsonObject.getString("agent_id");
			String txn_key=jsonObject.getString("txn_key");

			String txn_key_val=mrd.txn_key_Validation(txn_key, agent_id);
			if(!txn_key_val.equalsIgnoreCase("Y"))
			{
				transresponseJson.put("response-code", "1");
				transresponseJson.put("response-message", "Invalid request!");
				return transresponseJson;

			}else{
				TBRequestDao daoObj=new TBRequestDao();
				//HashMap<String,String> accountData=daoObj.getAccountDetail(agent_id);
				JSONArray tranStatusData=daoObj.getDepositDetail(agent_id);
				if(tranStatusData!=null && tranStatusData.size()>0){
					transresponseJson.put("response-code", "0");
					transresponseJson.put("response-message", "Success");
					transresponseJson.put("data", tranStatusData.toString());
				}else{
					transresponseJson.put("response-code", "1");
					transresponseJson.put("response-message", "Data Not Available.");
				}
				
			}

			
		} catch (Exception e) {

			e.printStackTrace();
			transresponseJson=new JSONObject();
			transresponseJson.put("response-code", "1");
			transresponseJson.put("response-message", "Unable to process your request.");
			return transresponseJson;
		}
		return transresponseJson;
	}
	
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/CollectionBanks")

	public JSONObject collectionBanks(JSONObject jsonObject )	
	{

		JSONObject transresponseJson=new JSONObject();
		System.out.println("WalletServiceController.getRestaurantsByArea()");

		try {
			MobileAppRechargeDao mrd=new MobileAppRechargeDao();

			String agent_id=jsonObject.getString("agent_id");
			String txn_key=jsonObject.getString("txn_key");

			String txn_key_val=mrd.txn_key_Validation(txn_key, agent_id);
			if(!txn_key_val.equalsIgnoreCase("Y"))
			{
				transresponseJson.put("response-code", "1");
				transresponseJson.put("response-message", "Invalid request!");
				return transresponseJson;

			}else{
				TBRequestDao daoObj=new TBRequestDao();
				JSONArray banks=daoObj.collectionBank(Long.parseLong(agent_id));
				if(banks!=null && banks.size()>0){
					transresponseJson.put("response-code", "0");
					transresponseJson.put("response-message", "Success");
					transresponseJson.put("data", banks.toString());
				}else{
					transresponseJson.put("response-code", "1");
					transresponseJson.put("response-message", "Data Not Available.");
				}
				
			}

			
		} catch (Exception e) {

			e.printStackTrace();
			transresponseJson=new JSONObject();
			transresponseJson.put("response-code", "1");
			transresponseJson.put("response-message", "Unable to process your request.");
			return transresponseJson;
		}
		return transresponseJson;
	}

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/WalletBalance")

	public JSONObject getRestaurantsByArea(JSONObject jsonObject )	
	{

		JSONObject transresponseJson=new JSONObject();
		System.out.println("WalletServiceController.getRestaurantsByArea()");

		try {
			MobileAppRechargeDao mrd=new MobileAppRechargeDao();

			String agent_id=jsonObject.getString("agent_id");
			String txn_key=jsonObject.getString("txn_key");

			String txn_key_val=mrd.txn_key_Validation(txn_key, agent_id);
			if(!txn_key_val.equalsIgnoreCase("Y"))
			{
				transresponseJson.put("response-code", "1");
				transresponseJson.put("response-message", "Invalid request!");
				return transresponseJson;

			}else{
				CommonServiceDao commonServiceDao=new CommonServiceDao();
				
				String dmrVendor=commonServiceDao.getDMRVendor();
				
				String updBal=commonServiceDao.getUpdatedBalance(agent_id);
				if(updBal!=null){
					
					transresponseJson.put("updBal", updBal);
					transresponseJson.put("balance", updBal);
					transresponseJson.put("dmr_vendor", dmrVendor);
					transresponseJson.put("response-code","0");
					transresponseJson.put("response-message", "Success");
				}else{
					transresponseJson.put("response-code","1" );
					transresponseJson.put("response-message", "Failure");
				}
			}

			
		} catch (Exception e) {

			e.printStackTrace();
			transresponseJson=new JSONObject();
			transresponseJson.put("status", "1");
			transresponseJson.put("message", "Unable to process your request.");
			return transresponseJson;
		}
		return transresponseJson;
	}

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/TurnOver")

	public JSONObject turnOver(JSONObject jsonObject )	
	{

		JSONObject transresponseJsonObject=new JSONObject();

		try {

			String agentId=jsonObject.getString("agent_id");

			CommonServiceDao commonServiceDao=new CommonServiceDao();
			MoneyTransferDao moneyTransferDao=new MoneyTransferDao();

			String txn_key=jsonObject.getString("txn_key");
			String txn_key_val=commonServiceDao.txn_key_Validation(txn_key, agentId);
			if(!txn_key_val.equalsIgnoreCase("Y"))
			{
				transresponseJsonObject.put("response-code", "1");
				transresponseJsonObject.put("response-message", "Unauthorized User Request!");
				return transresponseJsonObject;
			}

			String date=jsonObject.getString("date");
			HashMap<String, String> map=moneyTransferDao.turnOver(agentId,date);
			if(map!=null){

				transresponseJsonObject.put("response-code", "0");
				transresponseJsonObject.put("response-message","Success");
				transresponseJsonObject.put("response-reqamt", map.get("totalTran"));
				transresponseJsonObject.put("response-comm", map.get("totalComm"));
				transresponseJsonObject.put("response-deduction",map.get("totalDeduction"));

			}else{

				transresponseJsonObject.put("response-code", "1");
				transresponseJsonObject.put("response-message","We can not process your right now, Please try later.");
			}

			return transresponseJsonObject;

		} catch (Exception e) {

			e.printStackTrace();
			transresponseJsonObject=new JSONObject();
			transresponseJsonObject.put("status", "1");
			transresponseJsonObject.put("message", "Failure");
			return transresponseJsonObject;
		}

	}

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/TicketDetails")

	public JSONObject turnticketDetailsOver(JSONObject jsonObject )	
	{

		JSONObject transresponseJsonObject=new JSONObject();

		try {

			String agentId=jsonObject.getString("agent_id");

			CommonServiceDao commonServiceDao=new CommonServiceDao();

			String txn_key=jsonObject.getString("txn_key");
			String txn_key_val=commonServiceDao.txn_key_Validation(txn_key, agentId);
			if(!txn_key_val.equalsIgnoreCase("Y"))
			{
				transresponseJsonObject.put("response-code", "1");
				transresponseJsonObject.put("response-message", "Unauthorized User Request!");
				return transresponseJsonObject;
			}else{
				String date="";

				TicketDao ticketDao=new TicketDao();
				transresponseJsonObject=ticketDao.getTicketDetails(Long.parseLong(agentId), date);

				return transresponseJsonObject;
			}

		} catch (Exception e) {

			e.printStackTrace();
			transresponseJsonObject=new JSONObject();
			transresponseJsonObject.put("status", "1");
			transresponseJsonObject.put("message", "Failure");
			return transresponseJsonObject;
		}

	}

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/TicketT")

	public JSONObject ticket(JSONObject jsonObject )	
	{

		JSONObject transresponseJsonObject=new JSONObject();

		try {

			String agentId=jsonObject.getString("agent_id");

			CommonServiceDao commonServiceDao=new CommonServiceDao();

			String txn_key=jsonObject.getString("txn_key");
			String txn_key_val=commonServiceDao.txn_key_Validation(txn_key, agentId);
			if(!txn_key_val.equalsIgnoreCase("Y"))
			{
				transresponseJsonObject.put("response-code", "1");
				transresponseJsonObject.put("response-message", "Unauthorized User Request!");
				return transresponseJsonObject;
			}else{
				String transactionId=jsonObject.getString("transactionId");
				String ticketmessage=jsonObject.getString("ticketmessage");
				//String stat=jsonObject.getString("status");
				agentId=agentId.replaceAll("AG", "").replaceAll("ag", "");
				String description="";
				System.out.println("transactionId : "+transactionId+" : ticketmessage : "+ticketmessage);

				if(ticketmessage==null )
					ticketmessage="";

				TicketDao ticketDao=new TicketDao();
				if(ticketDao.checkTicketExistance(Long.parseLong(agentId), transactionId)){
					String ticketid=TicketDao.getTicketId();

					JSONObject temp=ticketDao.getAccountWIndowInfoForRecharge(transactionId,Long.parseLong(agentId));
					if(temp!=null )
					{

						boolean status=ticketDao.submitTicket(ticketid,transactionId,Long.parseLong(agentId),"AG",ticketmessage);
						if(status){
							description="Your Ticket "+ticketid+" Has Been Submitted Successfully and Will Be Resolved Within 24 hours.";

							transresponseJsonObject.put("response-code", "0");
							transresponseJsonObject.put("response-message",description);

						}
						else
						{
							description="Ticket Not Submitted. Please Try Again.";

							transresponseJsonObject.put("response-code", "1");
							transresponseJsonObject.put("response-message",description);
						}

					}
					else{

						description="There is No Transaction Available with this transaction No.";

						transresponseJsonObject.put("response-code", "1");
						transresponseJsonObject.put("response-message",description);
					}
				}
				else{
					description="Ticket already exist for this transaction Id. Please view ticket Status.";

					String updBal=commonServiceDao.getUpdatedBalance(agentId);
					transresponseJsonObject.put("response-code", "1");
					transresponseJsonObject.put("response-message",description);
					transresponseJsonObject.put("balance", updBal);
				}
			}

			return transresponseJsonObject;

		} catch (Exception e) {

			e.printStackTrace();
			transresponseJsonObject=new JSONObject();
			transresponseJsonObject.put("status", "1");
			transresponseJsonObject.put("message", "Unable to process your request.");
			return transresponseJsonObject;
		}

	}

}
