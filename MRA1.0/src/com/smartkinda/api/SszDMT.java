package com.smartkinda.api;

import java.util.HashMap;
import org.apache.log4j.Logger;
import com.msmart.service.CommonServices;
import com.msmart.service.PropertyFile;
import net.sf.json.JSONObject;

public class SszDMT {

	Logger log=Logger.getLogger(SszDMT.class);

	public JSONObject findSender(JSONObject reqObj) {

		JSONObject responseJson=null;


		try {

			String findSenderUrl=PropertyFile.SmartKindaURL+"FindSender";

			SszAPICall sApiCall=new SszAPICall();

			responseJson=sApiCall.getJSONobjectPost(findSenderUrl, reqObj);

		} catch (Exception e) {
			e.printStackTrace();
			responseJson=new JSONObject();
			responseJson.put("Status","1");
			responseJson.put("message", "Unable to process your request. Please try later.");
		}
		return responseJson;
	}

	public JSONObject bankDetails(JSONObject reqObj) {

		JSONObject responseJson=null;


		try {

			String findSenderUrl=PropertyFile.SmartKindaURL+"BankDetails";

			SszAPICall sApiCall=new SszAPICall();

			responseJson=sApiCall.getJSONobjectPost(findSenderUrl, reqObj);
			

		} catch (Exception e) {
			e.printStackTrace();
			responseJson=new JSONObject();
			responseJson.put("Status","1");
			responseJson.put("message", "Unable to process your request. Please try later.");
		}
		return responseJson;
	}

	public JSONObject resendOtp(JSONObject reqObj) {

		JSONObject responseJson=null;


		try {

			String findSenderUrl=PropertyFile.SmartKindaURL+"FindSender";

			SszAPICall sApiCall=new SszAPICall();

			responseJson=sApiCall.getJSONobjectPost(findSenderUrl, reqObj);

		} catch (Exception e) {
			e.printStackTrace();
			responseJson=new JSONObject();
			responseJson.put("Status","1");
			responseJson.put("message", "Unable to process your request. Please try later.");
		}
		return responseJson;
	}

	public JSONObject registerSender(JSONObject reqObj){

		JSONObject responseObj=new JSONObject();
		try{

			String senderId=reqObj.getString("SenderId");
			String senderName=reqObj.getString("SenderName");
			String pincode=reqObj.getString("Pincode");
			String dob=reqObj.getString("DOB");

			SszAPICall sApiCall= new SszAPICall();

			String findSenderUrl=PropertyFile.SmartKindaURL+"RegisterSender";

			responseObj=sApiCall.getJSONobjectPost(findSenderUrl, reqObj);

			if(responseObj!=null){
				String statusCode=responseObj.getString("Status");
				if("0".equalsIgnoreCase(statusCode)){

					SSZDao dao=new SSZDao();

					HashMap<String,Object> senderDetailsMap= dao.getcustomerName(senderId);
					if(senderDetailsMap==null){

						String insertStatus=dao.senderRegistration(senderId, senderName, pincode, dob, "");
						log.info("Sender insertStatus :: "+insertStatus);

					}
				}
			}


		}catch(Exception e){
			e.printStackTrace();
			responseObj=new JSONObject();
			responseObj.put("Status", "1");
			responseObj.put("message", "Unable to process your request. Please try Later");
		}
		return responseObj;
	}

	public JSONObject verifySender(JSONObject reqObj){

		JSONObject responseObj=new JSONObject();
		try{

			String senderId=reqObj.getString("SenderId");
			SszAPICall sApiCall= new SszAPICall();

			String findSenderUrl=PropertyFile.SmartKindaURL+"VerifySender";

			responseObj=sApiCall.getJSONobjectPost(findSenderUrl, reqObj);

			if(responseObj!=null){
				String statusCode=responseObj.getString("Status");
				if("0".equalsIgnoreCase(statusCode)){

					SSZDao dao=new SSZDao();

					dao.setcustomervarifiedindicator(senderId);
				}
			}


		}catch(Exception e){
			e.printStackTrace();
			responseObj=new JSONObject();
			responseObj.put("Status", "1");
			responseObj.put("message", "Unable to process your request. Please try Later");
		}

		return responseObj;
	}

	public JSONObject bankList(JSONObject reqObj) {

		JSONObject respObj=new JSONObject();

		try {

			SszAPICall sApiCall=new SszAPICall();

			String findSenderUrl=PropertyFile.SmartKindaURL+"GetBankList";

			respObj=sApiCall.getJSONobjectPost(findSenderUrl, reqObj);


		} catch (Exception e) {
			e.printStackTrace();
			respObj = new JSONObject();
			respObj.put("message", "Unable to process your request. Please Try later.");
			respObj.put("Status", "1");
		}
		return respObj;
	}

	/*public JSONObject ekoTransaction(JSONObject jsonObject,String ipaddress,HttpServletRequest request){

		String message="",agent_id="";
		JSONObject traninfomap = new JSONObject();
		try {

			String tranId=jsonObject.getString("REQUEST_ID");
			String bankname="NA",recipient_id = "";
			String customer_mobile = jsonObject.getString("SenderId");
			String ifsc =jsonObject.getString("Ifsc");
			String ben_account = jsonObject.getString("BeneAccount");
			recipient_id = jsonObject.getString("RecipientId");
			log.info(recipient_id);
			agent_id = jsonObject.getString("AgentID");
			bankname=jsonObject.getString("BankName");
			String recepient_mobile = jsonObject.getString("BeneMobile");
			String remittername = jsonObject.getString("SenderName");
			String recipient_name = jsonObject.getString("BeneName");
			String totamt = jsonObject.getString("TxnAmount");

			String tid="",tranrefid="",bankrrn="",tranactionStatus="1";


			String remark = jsonObject.getString("Remark");
			double amt = Double.parseDouble(totamt);

			SmartKindaDao trandao=new SmartKindaDao();

			String channel = jsonObject.getString("TxnType");

			String status = trandao.validateService(agent_id,"DMR");


			String service="DMR-E-REMIT",operator="DMR-E-REMIT";

			log.info("DMR Service Status : " + status);
			if (status.equalsIgnoreCase("Y")) {

				if(amt<=5000){

					traninfomap=getTransactionResult(tranId,remark, agent_id, 
							recepient_mobile, ifsc, remittername, recipient_name, bankname, customer_mobile, ben_account, 
							"EKO", ipaddress, amt,
							recipient_id,channel,service,operator);

					tranactionStatus=traninfomap.getString("Status");
					tranrefid=traninfomap.optString("tranid");
					if("0".equalsIgnoreCase(tranactionStatus)){

						tid=traninfomap.optString("tid");
						tranactionStatus=traninfomap.getString("Status");
						bankrrn=traninfomap.getString("BankRRN");

					}
					message=traninfomap.getString("message");

				}else{

					message= "Transfer amount should be less than Availaible limit";
					traninfomap.put("Status", "1");
					traninfomap.put("message", message);
					return traninfomap;
				}


			}else if (status.equalsIgnoreCase("Y")) {
				traninfomap=new JSONObject();
				traninfomap.put("Status", "1");
				message= "Service Deactive. Please contact to customer care.";
				traninfomap.put("message", message);
				return traninfomap;
			}else{
				traninfomap=new JSONObject();
				traninfomap.put("Status", "1");
				message= "Transaction Failed Due to Technical Error. Please Try Later.";
				traninfomap.put("message", message);
				return traninfomap;
			} 

			logger.info("Transaction >>>>>>>>>>>>>> "+traninfomap.toString());

			if(tid==null)
				tid="NA";
			if(tranrefid==null)
				tranrefid="NA";

			traninfomap.put("tid", tid);
			traninfomap.put("tranid", tranrefid);
			traninfomap.put("BankRRN", bankrrn);
			traninfomap.put("Status",tranactionStatus);
			return traninfomap;

		} catch (Exception e) {
			e.printStackTrace();

			traninfomap.put("Status", "1");
			traninfomap.put("message", "We are unable to process your request. Please try later.");
			return traninfomap;
		}

	}*/

	public JSONObject ekoTransaction(JSONObject jsonObject,String Ipaddress,String agentId,String senderId){

		JSONObject traninfomap = new JSONObject();
		try {

			String totamt = jsonObject.getString("TxnAmount");

			String tid="",tranrefid="",tranactionStatus="",bankrrn="";
			String service="DMR-E-REMIT",operator="DMR-E-REMIT";
			double transactingamount=0;
			double amt = Double.parseDouble(totamt);

			SSZDao dao=new SSZDao();
			String status = dao.validateService(agentId, service, operator, amt, senderId,jsonObject.getString("BeneAccount"));


			log.info("DMR Transaction Status : " + status);

			if ("duplicate".equalsIgnoreCase(status)) {
				traninfomap.put("Status", "1");
				traninfomap.put("message", "Duplicate transaction.");
			} else  if ("error".equalsIgnoreCase(status)) {
				traninfomap.put("Status", "1");
				traninfomap.put("message", "Transaction Failed Due to Technical Error. Please Try Later.");
			}else  if ("limit".equalsIgnoreCase(status)) {
				traninfomap.put("Status", "1");
				traninfomap.put("message", "Customer monthly limit exceeds. Try with different number.");
			} else if ("Y".equalsIgnoreCase(status)) {


				if(amt<=25000){
					if(amt<=5000 && amt>0){
						traninfomap=getTransactionResult(jsonObject,Ipaddress,amt,agentId,service,operator);
						tranactionStatus=traninfomap.getString("Status");

						if("0".equalsIgnoreCase(tranactionStatus))
							transactingamount=transactingamount+amt;

						tid=traninfomap.optString("tid");
						tranrefid=traninfomap.optString("tranid");

						bankrrn=traninfomap.optString("BankRRN");

					}
					else if(amt>5000){

						if(amt%5000==0){
							int count=(int) (amt/5000);
							for(int i=0;i<count;i++){
								double partamt=5000;

								traninfomap=getTransactionResult(jsonObject,Ipaddress,partamt,agentId,service,operator);

								tranactionStatus=traninfomap.getString("Status");

								if("0".equalsIgnoreCase(tranactionStatus)){
									transactingamount=transactingamount+partamt;

								}
								if(i==0){
									tid=traninfomap.optString("tid");
									tranrefid=traninfomap.optString("tranid");

									bankrrn=traninfomap.optString("BankRRN");
								}else{
									tid=tid+","+traninfomap.get("tid");
									tranrefid=tranrefid+","+traninfomap.optString("tranid");
									bankrrn=bankrrn+","+traninfomap.optString("BankRRN");
								}

							}
						}else{

							double remainingamount=amt;
							boolean splitstatus=true;
							int count=(int) (amt/5000);
							for(int k=0;k<count;k++){
								remainingamount-=5000;
								double partamt=5000;

								traninfomap=getTransactionResult(jsonObject,Ipaddress,partamt,agentId,service,operator);

								tranactionStatus=traninfomap.optString("Status");

								if("0".equalsIgnoreCase(tranactionStatus)){

									transactingamount=transactingamount+partamt;

								}

								if(k==0){
									tid=traninfomap.getString("tid");
									tranrefid=traninfomap.optString("tranid");
									tranactionStatus=traninfomap.optString("Status");
									bankrrn=traninfomap.optString("BankRRN");
								}else{
									tid=tid+","+traninfomap.optString("tid");
									tranrefid=tranrefid+","+traninfomap.optString("tranid");
									tranactionStatus=tranactionStatus+","+traninfomap.optString("Status");
									bankrrn=bankrrn+","+traninfomap.optString("BankRRN");
								}

							}
							if(remainingamount>0 && splitstatus){
								traninfomap=getTransactionResult(jsonObject,Ipaddress,remainingamount,agentId,service,operator);

								tranactionStatus=traninfomap.getString("Status");

								if("0".equalsIgnoreCase(tranactionStatus)){

									transactingamount=transactingamount+remainingamount;

								}
								tid=tid+","+traninfomap.optString("tid");
								tranrefid=tranrefid+","+traninfomap.optString("tranid");
								tranactionStatus=tranactionStatus+","+traninfomap.optString("Status");
								bankrrn=bankrrn+","+traninfomap.get("BankRRN");
							}
						}
						traninfomap.put("TxnAmount", transactingamount);
						traninfomap.put("tid", tid);
						traninfomap.put("tranid", tranrefid);
						traninfomap.put("BankRRN", bankrrn);
						traninfomap.put("Status", "0");
						return traninfomap;
					}else{
						traninfomap.put("Status",  "1");
						traninfomap.put("message", "Invalid Amount");
					}

				}else{

					traninfomap.put("Status", "1");
					traninfomap.put("message", status);
				}


			}else{
				traninfomap=new JSONObject();
				traninfomap.put("Status", "1");
				traninfomap.put("message", "Transaction Failed Due to Technical Error. Please Try Later.");
			} 

			log.info("Transaction >>>>>>>>>>>>>> "+traninfomap.toString());

			return traninfomap;

		} catch (Exception e) {
			e.printStackTrace();

			traninfomap.put("Status", "1");
			traninfomap.put("message", "We are unable to process your request. Please try later.");
			return traninfomap;
		}

	}

	public JSONObject getTransactionResult(JSONObject reqObj,String ipAddress,double amt,String AgentID,String service,String operator) {
		log.info("executing getTransactionResult method of DMRTransactionEKO class");
		String statusCode="1";

		JSONObject repObj=new JSONObject();
		try {


			String SenderId = reqObj.getString("SenderId");
			String ifsc =reqObj.getString("Ifsc");
			String BeneAccount = reqObj.getString("BeneAccount");
			String RecipientId = reqObj.getString("RecipientId");
			//String bankname=reqObj.getString("BankName");
			//String BeneMobile = reqObj.getString("BeneMobile");
			//String SenderName = reqObj.getString("SenderName");
			String BeneName = reqObj.getString("BeneName");
			String remark = reqObj.getString("Remark");
			String channel = reqObj.getString("TxnType");

			String tranType="NEFT";

			if("1".equalsIgnoreCase(channel))
				tranType="NEFT";
			else if("2".equalsIgnoreCase(channel))
				tranType="IMPS";

			reqObj.put("TxnAmount", String.valueOf(amt));

			SSZDao trandao=new SSZDao();

			String message=null;
			String IdNo=CommonServices.createTransactionID();
			String REQUEST_ID=CommonServices.getIdNo(AgentID);
			String bankRrn="";

			String TranResult = trandao.DMR_TRANSACTION_INSERT(IdNo, REQUEST_ID, AgentID, service, operator, SenderId, ipAddress, "SKAPI", RecipientId, remark, tranType, "", BeneName, ifsc, BeneAccount, amt);

			log.info("Inserte Result----------" + TranResult);
			if (TranResult.equalsIgnoreCase("insufficient_balance")) {
				message="You Do Not have Sufficient Balance.";
				repObj.put("Status",statusCode);
				repObj.put("message",message);
				return repObj;
			} else if (TranResult.equalsIgnoreCase("noinserted")) {
				message="Transaction Failed Due to Technical Error. Please Try Later.";
				repObj.put("Status",statusCode);
				repObj.put("message",message);
				return repObj;
			}else if (TranResult.equalsIgnoreCase("duplicate")) {
				message="Duplicate transaction.Please Try again.";
				repObj.put("Status",statusCode);
				repObj.put("message",message);
				return repObj;
			} else if (TranResult.equalsIgnoreCase("commisson_error")) {
				message="Transaction Failed as Your Commission is Not Set Properly.";
				repObj.put("Status",statusCode);
				repObj.put("message",message);
				return repObj;
			}
			else if (TranResult.equalsIgnoreCase("inserted")) {

				double amtlimit = amt;

				if (amtlimit <= 5000) {

					SszAPICall sApiCall=new SszAPICall();

					String reqString=PropertyFile.SmartKindaURL+"InitiateTransaction";

					reqObj.put("REQUEST_ID", REQUEST_ID);

					trandao.insertEKOLogs("SKAPI", REQUEST_ID, reqString+"?"+reqObj.toString(), AgentID);

					repObj = sApiCall.getJSONobjectPost(reqString, reqObj);
					log.info("Transaction Response : "+repObj);


					if (repObj != null) {
						message=repObj.optString("message");
						trandao.updateEKOLogs(REQUEST_ID, repObj.toString(), AgentID);

						String respStatus=repObj.getString("Status");
						String tid=repObj.optString("tid");
						bankRrn=repObj.optString("BankRRN");

						if ("0".equalsIgnoreCase(respStatus)) 
						{
							log.info("inside the success block");
							// String

							String result=trandao.updateStatusDMRTranEKO(SenderId,REQUEST_ID,
									"Success", tid,message, bankRrn);

							log.info("Transaction Update status : "+result);

							statusCode="0";

						} else if("1".equalsIgnoreCase(respStatus)) {

							trandao.updateStatusDMRTranEKO(SenderId,REQUEST_ID,
									"Failure", tid, message,bankRrn);

							statusCode="0";

						}else if("3".equalsIgnoreCase(respStatus)) {
							trandao.updateStatusDMRTranEKO(SenderId,REQUEST_ID,
									"Initiated", tid, message,bankRrn);

							statusCode="0";
						}else{
							trandao.updateStatusDMRTranEKO(SenderId,REQUEST_ID,
									"Pending", tid, message,bankRrn);

							statusCode="0";
						}

						if("You Do Not have Sufficient Balance.".equalsIgnoreCase(message))
							message="Bank service is down. Please try later.";

					} else
					{

						trandao.updateStatusDMRTranEKO(SenderId,REQUEST_ID,
								"Pending", "", message,bankRrn);
						log.info("message from eko is:::"+message);
						message="Transaction is under process. Please wait.";


					}
					repObj.put("Status", statusCode);
					repObj.put("message",message);

					return repObj;

				}else 
				{
					trandao.updateStatusDMRTranEKO(SenderId,REQUEST_ID,
							"Failure", "", message,"");

					statusCode="0";

					message="Exceed Month Limit";
					repObj.put("Status",statusCode);
					repObj.put("message",message);
					return repObj;
				}
			}else{
				message="Transaction Failed. Please contact to your channel partner.";
				repObj.put("Status",statusCode);
				repObj.put("message",message);
				return repObj;
			}
		} catch (Exception e) {
			log.error("Error in execute method of RemitTransaction class"+e.getMessage());
			e.printStackTrace();
			repObj=new JSONObject();
			repObj.put("Status", "1");
			repObj.put("message", "We are unable to process your request. Please try later.");

		}
		return repObj; 
	}

	public JSONObject verifiAccount(JSONObject reqObj,String agentID,String Ipaddress) {


		JSONObject respObj = new JSONObject();
		String message="";
		try {

			SszAPICall sApiCall=new SszAPICall();

			String senderId=reqObj.getString("SenderId");
			String BankAccount=reqObj.getString("BankAccount");
			String BankName=reqObj.getString("BankName");
			String BankCode=reqObj.getString("BankCode");
			String IFSC=reqObj.getString("IFSC");


			String bankDetailsURL=PropertyFile.SmartKindaURL+"BankDetails";

			JSONObject bankDetailRequestJson=new JSONObject();
			bankDetailRequestJson.put("Key",reqObj.getString("Key"));
			bankDetailRequestJson.put("AgentID",reqObj.getString("AgentID"));
			bankDetailRequestJson.put("SenderId",senderId);
			bankDetailRequestJson.put("BankCode",BankCode);
			bankDetailRequestJson.put("IFSC",IFSC);

			JSONObject bankDetails=sApiCall.getJSONobjectPost(bankDetailsURL, bankDetailRequestJson);

			if(bankDetails!=null ){
				log.info("bankDetails : "+bankDetails.toString());
				String isverificationavailable=bankDetails.optString("isverificationavailable");
				if(isverificationavailable!=null && "0".equalsIgnoreCase(isverificationavailable)){
					respObj.put("Status","1");
					respObj.put("message","Bank service is down. Please try later.");
					return respObj;
				}
			}


			SSZDao dmtDao=new SSZDao();

			String IdNo=CommonServices.createTransactionID();
			String REQUEST_ID=CommonServices.getIdNo(agentID);

			String service="DMR-E-ACCOUNT",operator="DMR-E-ACCOUNT";

			String resultStatus = dmtDao.validateService(agentID, service, operator, 1, senderId,BankAccount);

			log.info("DMR Transaction Status : " + resultStatus);

			if ("duplicate".equalsIgnoreCase(resultStatus)) {
				respObj.put("Status", "1");
				respObj.put("message", "Duplicate transaction.");
			} else  if ("error".equalsIgnoreCase(resultStatus)) {
				respObj.put("Status", "1");
				respObj.put("message", "Transaction Failed Due to Technical Error. Please Try Later.");
			}else  if ("limit".equalsIgnoreCase(resultStatus)) {
				respObj.put("Status", "1");
				respObj.put("message", "Customer monthly limit exceeds. Try with different number.");
			} else if ("Y".equalsIgnoreCase(resultStatus)) {


				String TranResult = dmtDao.DMR_TRANSACTION_INSERT(IdNo, REQUEST_ID, agentID, service,operator, senderId, Ipaddress, "SKAPI", "", "Account verification", "IMPS", "", BankName, IFSC, BankAccount, 1);

				if (TranResult.equalsIgnoreCase("insufficient_balance")) {
					respObj.put("Status","1");
					respObj.put("message","You Do Not have Sufficient Balance.");
					return respObj;
				} else if (TranResult.equalsIgnoreCase("noinserted")) {
					respObj.put("Status","1");
					respObj.put("message","Transaction Failed Due to Technical Error. Please Try Later.");
					return respObj;
				}else if (TranResult.equalsIgnoreCase("duplicate")) {
					respObj.put("Status","1");
					respObj.put("message","Duplicate transaction.Please Try again.");
					return respObj;
				} else if (TranResult.equalsIgnoreCase("commisson_error")) {
					respObj.put("Status","1");
					respObj.put("message","Transaction Failed as Your Commission is Not Set Properly.");
					return respObj;
				}
				else if ("inserted".equalsIgnoreCase(TranResult)) {


					String verifyURL=PropertyFile.SmartKindaURL+"AccountVerifyByBankAccountIFSC";
					reqObj.put("REQUEST_ID", REQUEST_ID);
					respObj=sApiCall.getJSONobjectPost(verifyURL, reqObj);
					String updateStatus="Pending",TxnId="";
					if(respObj!=null){
						message=respObj.getString("message");
						String resStatus=respObj.getString("Status");
						TxnId=respObj.optString("TxnId");
						if("0".equalsIgnoreCase(resStatus)){
							updateStatus="Success";
						}else{
							updateStatus="Failure";
						}

						if("You Do Not have Sufficient Balance.".equalsIgnoreCase(message))
							respObj.put("message","Bank service is down. Please try later.");

					}else{
						respObj=new JSONObject();
						respObj.put("Status","1");
						respObj.put("message","Unable to process your request. Please Try later.");
					}

					String updateinfo = dmtDao.updateStatusDMRTranEKO(senderId,REQUEST_ID,
							updateStatus, TxnId,message,"");
					log.info("Account Verification update Status : " +updateinfo);

				} else {

					respObj.put("Status","1");
					respObj.put("message","Unable to process your request. Please Try later.");
					return respObj;

				}


			}else{
				respObj.put("Status", "1");
				respObj.put("message",resultStatus);
			}


		} catch (Exception e) {
			e.printStackTrace();
			respObj = new JSONObject();
			respObj.put("message", "Unable to process your request. Please Try later.");
			respObj.put("Status", "1");

		}

		return respObj;

	}

	public JSONObject AddBeneAfterVerify(JSONObject reqObj,String AgentID) {

		JSONObject respObj=new JSONObject();

		try {

			String senderId=reqObj.getString("SenderId");
			String BankAccount=reqObj.getString("BankAccount");
			String BankName=reqObj.getString("BankName");
			String BankCode=reqObj.optString("BankCode");
			String IFSC=reqObj.getString("IFSC");
			String BeneName=reqObj.getString("BeneName");
			String BeneMobile=reqObj.getString("BeneMobile");
			String verify=reqObj.optString("varify");

			String verifymessage="";

			if("Y".equalsIgnoreCase(verify)){
				String ipAddress=reqObj.getString("ipAddress");

				JSONObject verifyRep=verifiAccount(reqObj,AgentID,ipAddress);
				String resStatus=verifyRep.optString("Status");
				if("0".equalsIgnoreCase(resStatus)){
					verifymessage="Account verified successfully.";

				}else
					verifymessage="Account verification failed.";

			}

			SszAPICall sApiCall=new SszAPICall();
			SSZDao dao=new SSZDao();

			String findSenderUrl=PropertyFile.SmartKindaURL+"AddBeneAfterVerify";

			respObj=sApiCall.getJSONobjectPost(findSenderUrl, reqObj);
			if(respObj!=null){

				String message=respObj.getString("message");
				message=message+". "+verifymessage;

				respObj.put("message", message);

				String respStatus=respObj.getString("Status");
				if("0".equalsIgnoreCase(respStatus)){

					String beneId=respObj.getString("BeneId");

					String addBeneStatus=dao.addBeneficiary(BeneName, BeneMobile, BankName, BankCode, BankAccount, senderId, beneId);

					log.info("addBeneStatus :: "+addBeneStatus);
				}
			}



		} catch (Exception e) {
			e.printStackTrace();
			respObj = new JSONObject();
			respObj.put("message", "Unable to process your request. Please Try later.");
			respObj.put("Status", "1");
		}
		return respObj;
	}

	public JSONObject deleteBene(JSONObject reqObj){
		JSONObject respObj=new JSONObject();

		try {

			String beneficiaryId=reqObj.getString("BeneficiaryId");

			log.info("Delete Beneficiary");

			SszAPICall sApiCall=new SszAPICall();
			SSZDao dao=new SSZDao();

			String findSenderUrl=PropertyFile.SmartKindaURL+"DeleteBene";

			respObj=sApiCall.getJSONobjectPost(findSenderUrl, reqObj);
			if(respObj!=null){
				String respStatus=respObj.getString("Status");
				if("0".equalsIgnoreCase(respStatus)){

					dao.deleteReceipent(beneficiaryId);
				}
			}


		} catch (Exception e) {
			e.printStackTrace();
			respObj=new JSONObject();
			respObj.put("message", "Unable to process your request. Please Try later.");
			respObj.put("Status", "1");
		}
		return respObj;
	}

	public JSONObject tranStatus(JSONObject reqObj) {

		JSONObject response=new JSONObject();

		try {

			String tranId=reqObj.getString("TransactionRefNo");

			String smartId=SSZDao.getEKOTid(tranId);
			if(smartId==null){

				response.put("Status", "1");
				response.put("message", "Transaction not found. Contact to Admin.");

			}else{

				reqObj.put("TransactionRefNo", smartId);

				String statusUrl=PropertyFile.SmartKindaURL+"TransStatus";
				SszAPICall apiCall=new SszAPICall();

				response=apiCall.getJSONobjectPost(statusUrl, reqObj);

			}
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			response.put("Status","1");
			response.put("message", "Unable to process your request. Please try later.");
		}
		return response;
	}

	public JSONObject initiateRefundTransaction(JSONObject reqObj,String tranId) {

		JSONObject response=new JSONObject();
		String message = "",statusCode="1";
		try {


			String smartId=SSZDao.getEKOTid(tranId);
			if(smartId==null){

				response.put("Status", "0");
				response.put("message", "Transaction not found. Contact to Admin.");

			}else{

				String refundUrl=PropertyFile.SmartKindaURL+"RefundTransaction";
				SszAPICall apiCall=new SszAPICall();

				reqObj.put("TransactionRefNo", smartId);

				JSONObject respObj=apiCall.getJSONobjectPost(refundUrl, reqObj);
				if(respObj!=null){

					String ststCode=respObj.getString("Status");
					message=respObj.getString("message");
					if("0".equalsIgnoreCase(ststCode)){

						//String Refund_OTP=respObj.optString("Refund_OTP");
						statusCode="0";
						response.put("Refund_OTP", "");
					}


				}else
					message="Transaction not found. Contact to Admin.";
			}

		} catch (Exception e) {
			e.printStackTrace();
			message= "Unable to process your request. Please try later.";
		}

		response.put("TransactionRefNo", tranId);
		response.put("Status", statusCode);
		response.put("message", message);

		return response;
	}

	public JSONObject confirmRefundTransaction(JSONObject reqObj,String agentId) {

		JSONObject response=new JSONObject();
		String message = "",statusCode="1",tranId="",senderId="";
		try {


			tranId=reqObj.getString("TransactionRefNo");
			String smartId=SSZDao.getEKOTid(tranId);
			if(smartId==null){

				response.put("Status", "0");
				response.put("message", "Transaction not found. Contact to Admin.");

			}else{

				String confirmRefundUrl=PropertyFile.SmartKindaURL+"RefundDMRConfirm";
				SszAPICall apiCall=new SszAPICall();

				reqObj.put("TransactionRefNo", smartId);

				JSONObject respObj=apiCall.getJSONobjectPost(confirmRefundUrl, reqObj);
				if(respObj!=null){

					String ststCode=respObj.optString("Status");
					message=respObj.optString("message");
					if("0".equalsIgnoreCase(ststCode)){

						statusCode="0";
						String refundTxnId=respObj.optString("refundTxnId");
						SSZDao dmtDao=new SSZDao();
						String updateinfo = dmtDao.updateStatusDMRTranEKO(senderId,tranId,
								"Refund", refundTxnId,message,"");

						log.info("Refund Status : "+updateinfo);
					}


				}else
					message="Transaction not found. Contact to Admin.";
			}
		} catch (Exception e) {
			e.printStackTrace();
			message= "Unable to process your request. Please try later.";
		}
		response.put("TransactionRefNo", tranId);
		response.put("Status", statusCode);
		response.put("message", message);

		return response;
	}

}
