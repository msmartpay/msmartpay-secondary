package com.msmart.eko;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import com.msmart.service.CommonServices;
import com.msmart.service.PropertyFile;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class EkoDMT {

	Logger log=Logger.getLogger(EkoDMT.class);

	public JSONObject findSender(String senderId) {

		JSONObject jsonObject=null;

		JSONObject senderDetails=new JSONObject();

		String statusCode="1",message="";

		try {

			EkoDMTDao dao=new EkoDMTDao();
			EkoAPICall ekoAPICall=new EkoAPICall();



			jsonObject=new JSONObject();

			String getcstmr = PropertyFile.ekocustomerurl + "mobile_number:" + senderId + "?initiator_id="+ PropertyFile.initiator_id + "";
			log.info("Find Sender Request : "+getcstmr);
			JSONObject obj = ekoAPICall.ekoCall(getcstmr,"","GET");
			log.info("Find Sender Response : "+obj);
			if (obj != null) 
			{
				message = (String) obj.get("message");
				int status = (int) obj.get("status");

				JSONObject data = (JSONObject) obj.get("data");


				if(status==463 ){

					message="Sender not found. Retry with different sender id Or Register Sender";
					statusCode="3";
				}else if(status==323){

					message="Sender not found. Retry with different sender id Or Register Sender";
					statusCode="3";
				}else if(status==303){


					String identityurl = PropertyFile.ekocustomerurl + "mobile_number:" + senderId + "/otp";
					String str = "initiator_id=" + PropertyFile.initiator_id;
					JSONObject resendOtp = ekoAPICall.ekoCall(identityurl, str,"POST");
					if (resendOtp != null) {

						message = (String) resendOtp.get("message");
						status = (int) resendOtp.get("status");

						if(status==0){
							statusCode="2";
						}


					}else{
						message="Unable to process your request. Please try later.";

						statusCode="1";

					}

				}else if(status==0){

					String name = "",state="",state_desc="";

					if (data!=null && data.has("state"))
					{
						state = (String) data.get("state");
						state_desc = (String) data.get("state_desc");

						if("1".equalsIgnoreCase(state) || "Verification pending".equalsIgnoreCase(state_desc)) {

							String verificationurl = PropertyFile.ekocustomerurl + "mobile_number:" + senderId + "/otp";
							String str = "initiator_id=" + PropertyFile.initiator_id;
							JSONObject verificationobj = ekoAPICall.ekoCall(verificationurl, str,"POST");
							if (verificationobj != null) {
								message = (String) verificationobj.get("message");
								status = (int) verificationobj.get("status");

								message = (String) verificationobj.get("message");
								status = (int) verificationobj.get("status");

								if(status==303){
									verificationurl = PropertyFile.ekocustomerurl + "mobile_number:" + senderId + "/otp";
									str = "initiator_id=" + PropertyFile.initiator_id;
									JSONObject resendOTP = ekoAPICall.ekoCall(verificationurl, str,"POST");
									if (resendOTP != null) {
										message = (String) resendOTP.get("message");
										status = (int) resendOTP.get("status");
										if(status==0){
											statusCode="2";
										}

									}else{
										message="Unable to process your request. Please try later.";

									}
								}
								name = (String) data.get("name");
								senderDetails.put("Name", name);
								senderDetails.put("SenderId", senderId);

								jsonObject.put("SenderDetails", senderDetails);
								statusCode="2";


							}else{
								message="Unable to process your request. Please try later.";

								senderDetails.put("Name", name);
								senderDetails.put("SenderId", senderId);
								jsonObject.put("SenderDetails", senderDetails);


							}
							jsonObject.put("Status",statusCode);
							jsonObject.put("message", message);
							return jsonObject;
						}
						String senderLimit="NA";

						/************************************ Sender Beneficiary List Details *************************************/
						JSONObject beneData= dao.getAllBeneficiary(senderId, ekoAPICall, PropertyFile.initiator_id);


						log.info("senderLimit :: "+senderLimit);

						if(beneData!=null){

							jsonObject.put("BeneList", beneData.optJSONArray("recipient_list")==null?new JSONArray() :beneData.optJSONArray("recipient_list").toString());

						}else
							jsonObject.put("BeneList", new JSONArray().toString());

						/************************************ Sender Beneficiary List Details *************************************/


						/************************************ Sender Limit Details *************************************/

						JSONObject senderLimitDetails = new JSONObject();

						senderLimitDetails.put("KycStatus", state_desc);
						senderLimitDetails.put("SenderLimit", data.get("total_limit"));
						senderLimitDetails.put("UsedLimit", data.get("used_limit"));
						senderLimitDetails.put("AvailableLimit", data.get("available_limit"));

						jsonObject.put("SenderLimitDetails", senderLimitDetails);

						/************************************ Sender Limit Details *************************************/
					}

					/************************************ Sender Details *************************************/

					name = (String) data.get("name");
					senderDetails.put("SenderId", senderId);
					senderDetails.put("Name", name);
					jsonObject.put("SenderDetails", senderDetails);

					/************************************ Sender Details *************************************/

					statusCode="0";

				}else {
					message="Unable to process your request. Please try later.";
				}


			}else{
				message="Unable to process your request. Please try later.";
			}



			senderDetails.put("SenderId", senderId);

			jsonObject.put("Status",statusCode);
			jsonObject.put("message", message);





		} catch (Exception e) {
			e.printStackTrace();
			jsonObject=new JSONObject();
			jsonObject.put("Status","1");
			jsonObject.put("message", "Unable to process your request. Please try later.");
		}
		return jsonObject;
	}

	public JSONObject resendOtp(String senderId) {

		JSONObject jsonObject=new JSONObject();

		String statusCode="1",message="";

		try {

			EkoAPICall ekoAPICall=new EkoAPICall();

			String identityurl = PropertyFile.ekocustomerurl + "mobile_number:" + senderId + "/otp";
			String str = "initiator_id=" + PropertyFile.initiator_id;

			log.info("Resend OTP Request : "+identityurl+"?"+str);

			JSONObject resendOtp = ekoAPICall.ekoCall(identityurl, str,"POST");

			log.info("Resend OTP Response : "+resendOtp);

			if (resendOtp != null) {

				message = (String) resendOtp.get("message");
				int status = (int) resendOtp.get("status");

				if(status==0){
					statusCode="0";
				}


			}else{
				message="Unable to process your request. Please try later.";

				statusCode="1";

			}

			jsonObject.put("Status",statusCode);
			jsonObject.put("message", message);


		} catch (Exception e) {
			e.printStackTrace();
			jsonObject=new JSONObject();
			jsonObject.put("Status","1");
			jsonObject.put("message", "Unable to process your request. Please try later.");
		}
		return jsonObject;
	}

	public JSONObject registerSender(String senderid,String senderName,String address,String dob){

		String message="",code="1";
		JSONObject jsonObject=new JSONObject();
		try{

			EkoAPICall ekoAPICall= new EkoAPICall();

			JSONObject data = null;
			String identityurl = PropertyFile.ekocustomerurl + "mobile_number:" + senderid + "";
			String str = "name=" + senderName + "&initiator_id=" + PropertyFile.initiator_id;

			log.info("Register Sender Request : "+identityurl+"?"+str);

			JSONObject identityobj = ekoAPICall.ekoCall(identityurl, str,"PUT");

			log.info("Register Sender Response : "+identityobj);

			if(identityobj!=null){
				message = (String) identityobj.get("message");
				/*int response_type_id = (int) identityobj.get("response_type_id");
				int response_status_id = (int) identityobj.get("response_status_id");*/
				int status = (int) identityobj.get("status");
				if (status == 0) {
					if (identityobj.get("data") != null)
						data = (JSONObject) identityobj.get("data");
					if (data.get("customer_id") != null)
						senderid = (String) data.get("customer_id");
					code="0";

					String otp=(String) data.get("otp");
					jsonObject.put("Otp", otp);
					EkoDMTDao dao=new EkoDMTDao();

					HashMap<String,Object> senderDetailsMap= dao.getcustomerName(senderid);
					if(senderDetailsMap==null){

						String insertStatus=dao.senderRegistration(senderid, senderName, address, dob, "");
						log.info("Sender insertStatus :: "+insertStatus);

					}

				}else if(status == 17){
					code="2";
				}else{
					code="1";
				}
			}else{

			}


		}catch(Exception e){
			e.printStackTrace();

			jsonObject=new JSONObject();
			jsonObject.put("Status", code);
			jsonObject.put("message", "Unable to process your request. Please try Later");
		}

		jsonObject.put("Status", code);
		jsonObject.put("message", message);

		return jsonObject;
	}

	public JSONObject verifySender(String customerId,String otp){

		String  message="",code="1";
		JSONObject jsonObject=new JSONObject();
		try{
			EkoAPICall ekoAPICall=new EkoAPICall();
			EkoDMTDao dao=new EkoDMTDao();

			String identityurl = PropertyFile.ekocustomerurl + "verification/otp:" + otp.trim();
			String str = "initiator_id=" + PropertyFile.initiator_id + "&id=" + customerId + "&id_type=mobile_number";

			log.info("Verify Sender Request : "+identityurl+"?"+str);

			JSONObject obj = ekoAPICall.ekoCall(identityurl, str,"PUT");
			log.info("Verify Sender Response  : "+obj);
			if (obj != null) {
				message = (String) obj.get("message");
				/*int response_type_id = (int) obj.get("response_type_id");
				int response_status_id = (int) obj.get("response_status_id");*/
				int status = (int) obj.get("status");

				if(status==0){
					int varified = dao.setcustomervarifiedindicator(customerId);
					log.info("Sender verification :: "+varified);
					code="0";
					dao.setcustomervarifiedindicator(customerId);

				}else if(status==302){
					code="2";

				}else if(status==303){

					String verificationurl = PropertyFile.ekocustomerurl + "mobile_number:" + customerId + "/otp";
					str = "initiator_id=" + PropertyFile.initiator_id;
					JSONObject verificationobj = ekoAPICall.ekoCall(verificationurl, str,"PUT");
					if (verificationobj != null) {
						message = (String) verificationobj.get("message");
						status = (int) verificationobj.get("status");
						if(status==0){
							code="2";
						}

					}else{
						message="Unable to process your request. Please try later.";

						code="1";

					}

				}else{
					code="1";
					message="Unable to process your request. Please try Later";
				}


				jsonObject.put("Status", code);
				jsonObject.put("message", message);


			}else{

			}


		}catch(Exception e){
			e.printStackTrace();

			jsonObject=new JSONObject();
			jsonObject.put("Status", code);
			jsonObject.put("message", "Unable to process your request. Please try Later");
		}

		jsonObject.put("Status", code);
		jsonObject.put("message", message);

		return jsonObject;
	}

	public JSONObject ekoTransaction(JSONObject jsonObject,String ipaddress,HttpServletRequest request){

		String message="",agent_id="";
		JSONObject traninfomap = new JSONObject();
		try {

			//String tranId=jsonObject.getString("REQUEST_ID");
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

			EkoDMTDao trandao=new EkoDMTDao();

			String channel = jsonObject.getString("TxnType");



			String service="DMR-E-REMIT",operator="DMR-E-REMIT";
			double transactingamount=0;

			String status = trandao.validateService(agent_id, service, operator, amt, customer_mobile,ben_account);


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

				double limitAmount=25000;
				double splitAmount=5000;

				if(amt<=limitAmount){

					if(amt<=splitAmount && amt>0){

						traninfomap=getTransactionResult(remark, agent_id, 
								recepient_mobile, ifsc, remittername, recipient_name, bankname, customer_mobile, ben_account, 
								"EKO", ipaddress, amt,
								recipient_id,channel,service,operator);
						tranactionStatus=traninfomap.getString("Status");

						if("0".equalsIgnoreCase(tranactionStatus)){
							transactingamount=transactingamount+amt;
						}
						tid=traninfomap.optString("tid");
						tranrefid=traninfomap.optString("tranid");

						bankrrn=traninfomap.optString("BankRRN");

					}
					else if(amt>splitAmount){

						if(amt%splitAmount==0){
							int count=(int) (amt/splitAmount);
							for(int i=0;i<count;i++){
								double partamt=splitAmount;

								traninfomap=getTransactionResult(remark, agent_id, 
										recepient_mobile, ifsc, remittername, recipient_name, bankname, customer_mobile, ben_account, 
										"EKO", ipaddress, partamt,
										recipient_id,channel,service,operator);
								tranactionStatus=traninfomap.optString("Status");

								if("0".equalsIgnoreCase(tranactionStatus)){
									transactingamount=transactingamount+partamt;
								}

								if(i==0){
									tid=traninfomap.optString("tid");
									tranrefid=traninfomap.optString("tranid");

									bankrrn=traninfomap.optString("BankRRN");
								}else{
									tid=tid+","+traninfomap.optString("tid");
									tranrefid=tranrefid+","+traninfomap.optString("tranid");
									bankrrn=bankrrn+","+traninfomap.optString("BankRRN");
								}


							}
						}else{
							double remainingamount=amt;
							boolean splitstatus=true;
							int count=(int) (amt/splitAmount);
							for(int k=0;k<count;k++){
								remainingamount-=splitAmount;
								double partamt=splitAmount;

								traninfomap=getTransactionResult(remark, agent_id, 
										recepient_mobile, ifsc, remittername, recipient_name, bankname, customer_mobile, ben_account, 
										"EKO", ipaddress, partamt,
										recipient_id,channel,service,operator);
								tranactionStatus=traninfomap.optString("Status");

								if("0".equalsIgnoreCase(tranactionStatus)){
									transactingamount=transactingamount+partamt;
								}

								if(k==0){
									tid=traninfomap.optString("tid");
									tranrefid=traninfomap.optString("tranid");

									bankrrn=traninfomap.optString("BankRRN");
								}else{
									tid=tid+","+traninfomap.optString("tid");
									tranrefid=tranrefid+","+traninfomap.optString("tranid");
									bankrrn=bankrrn+","+traninfomap.optString("BankRRN");
								}

							}
							if(remainingamount>0 && splitstatus){
								traninfomap=getTransactionResult(remark, agent_id, 
										recepient_mobile, ifsc, remittername, recipient_name, bankname, customer_mobile, ben_account, 
										"EKO", ipaddress, remainingamount,
										recipient_id,channel,service,operator);
								tranactionStatus=traninfomap.optString("Status");

								if("0".equalsIgnoreCase(tranactionStatus)){
									transactingamount=transactingamount+remainingamount;
								}

								tid=tid+","+traninfomap.optString("tid");
								tranrefid=tranrefid+","+traninfomap.optString("tranid");
								bankrrn=bankrrn+","+traninfomap.optString("BankRRN");

							}
						}
						message=traninfomap.getString("message");
						traninfomap.put("TxnAmount", transactingamount);
						traninfomap.put("tid", tid);
						traninfomap.put("tranid", tranrefid);
						traninfomap.put("BankRRN", bankrrn);
						traninfomap.put("Status", "0");
						traninfomap.put("message", message);
						return traninfomap;


					}else{
						traninfomap.put("Status",  "1");
						traninfomap.put("message", "Invalid Amount");
					}


				}else{

					message= "Transfer amount should be less than Availaible limit";
					traninfomap.put("Status", "1");
					traninfomap.put("message", message);
				}


			}else if (status.equalsIgnoreCase("Y")) {
				traninfomap=new JSONObject();
				traninfomap.put("Status", "1");
				message= "Service Deactive. Please contact to customer care.";
				traninfomap.put("message", message);
			}else{
				traninfomap=new JSONObject();
				traninfomap.put("Status", "1");
				message= "Transaction Failed Due to Technical Error. Please Try Later.";
				traninfomap.put("message", message);

			} 

			return traninfomap;
		} catch (Exception e) {
			e.printStackTrace();

			traninfomap.put("Status", "1");
			traninfomap.put("message", "We are unable to process your request. Please try later.");
			return traninfomap;
		}

	}

	public JSONObject getTransactionResult(String Remarks,String agentCode,String recepient_mobile,String ifsc,
			String remittername,String recipient_name,
			String bankname,String customer_mobile,String ben_account,String vendor,String Ipaddress,double amt,
			String recipient_id,String channel,String service,String oprtor) {
		log.info("executing getTransactionResult method of DMRTransactionEKO class");
		JSONObject traninfomap = new JSONObject();

		String statusCode="1";

		try {

			EkoDMTDao trandao=new EkoDMTDao();
			String message=null;
			String IdNo=CommonServices.dateTime();
			String transactionId=CommonServices.getAETranId();
			String bankRrn="";
			traninfomap.put("tranid",IdNo);

			String transactionType="";
			if("1".equalsIgnoreCase(channel))
				transactionType="NEFT";
			else if("2".equalsIgnoreCase(channel))
				transactionType="IMPS";
			else
				transactionType="IMPS";

			String TranResult = trandao.DMR_TRANSACTION_INSERT(IdNo, transactionId, agentCode, service, oprtor, customer_mobile, Ipaddress, "EKO", recipient_id, Remarks, transactionType, recipient_name, bankname, ifsc, ben_account, amt);


			log.info("Inserte Result----------" + TranResult);
			if (TranResult.equalsIgnoreCase("insufficient_balance")) {
				message="You Do Not have Sufficient Balance.";
				traninfomap.put("Status",statusCode);
				traninfomap.put("message",message);
				return traninfomap;
			} else if (TranResult.equalsIgnoreCase("noinserted")) {
				message="Transaction Failed Due to Technical Error. Please Try Later.";
				traninfomap.put("Status",statusCode);
				traninfomap.put("message",message);
				return traninfomap;
			}else if (TranResult.equalsIgnoreCase("duplicate")) {
				message="Duplicate transaction.Please Try again.";
				traninfomap.put("Status",statusCode);
				traninfomap.put("message",message);
				return traninfomap;
			} else if (TranResult.equalsIgnoreCase("commisson_error")) {
				message="Transaction Failed as Your Commission is Not Set Properly.";
				traninfomap.put("Status",statusCode);
				traninfomap.put("message",message);
				return traninfomap;
			}
			else if (TranResult.equalsIgnoreCase("inserted")) {

				double amtlimit = amt;

				if (amtlimit <= 5000) {
					if(null==remittername){
						remittername = "";
					}

					java.util.Date date = new java.util.Date();
					Timestamp timestamp1 = new Timestamp(date.getTime());


					String str = "recipient_id=" + recipient_id + "&amount=" + amtlimit + "&timestamp="
							+ timestamp1 + "&currency=INR&customer_id=" + customer_mobile + "&initiator_id="
							+ PropertyFile.initiator_id + "&client_ref_id=" + transactionId + "&hold_timeout=100&state=1&channel="
							+ channel+"&merchant_document_id_type=1&merchant_document_id=BFUPM3499H&pincode=302021";

					String url = PropertyFile.ekotranurl;

					log.info("Transaction Request : "+url+"?" + str);

					EkoAPICall ekoAPICall=new EkoAPICall();

					String reqString=url+"?" + str;
					trandao.insertEKOLogs("EKO", transactionId, reqString, agentCode);

					JSONObject jsonobj = ekoAPICall.ekoCall(url, str,"POST");



					if (jsonobj != null) {
						log.info("Transaction Response : "+jsonobj.toString());
						String apiresponse = jsonobj.toString();
						trandao.updateEKOLogs(transactionId, apiresponse, agentCode);

						int Status = (int) jsonobj.get("status");
						log.info("status::::"+Status);
						message = (String) jsonobj.get("message");
						if (Status == 0) {
							log.info("in status success block");
							JSONObject jsondata = (JSONObject) jsonobj.get("data");
							String transaction_Id = jsondata.get("tid")+"";
							bankRrn = jsondata.get("bank_ref_num")+"";
							String tx_status =jsondata.get("tx_status")+"";

							/*String txstatus_desc = (String) jsondata.get("txstatus_desc");
							String commitstate = (String) jsondata.get("state");*/

							if ("0".equalsIgnoreCase(tx_status)) 
							{
								log.info("inside the success block");
								// String
								String result=trandao.updateStatusDMRTranEKO(customer_mobile,transactionId,
										"Success", transaction_Id,message, bankRrn,tx_status);

								log.info("Transaction Update status : "+result);
								message="Transaction is successful";

								statusCode="0";

							} else if("1".equalsIgnoreCase(tx_status)){

								trandao.updateStatusDMRTranEKO(customer_mobile,transactionId,
										"Failure", transaction_Id, message,bankRrn,tx_status);

								message="Transaction failed/ declined";

							}else if("2".equalsIgnoreCase(tx_status)){

								trandao.updateStatusDMRTranEKO(customer_mobile,transactionId,
										"Success", transaction_Id, message,bankRrn,tx_status);

								if("1".equalsIgnoreCase(channel))
									message="Transaction is successfully posted";
								else
									message="Transaction is successfully processed but the final response is awaited";
								statusCode="0";
							}else{

								trandao.updateStatusDMRTranEKO(customer_mobile,transactionId,
										"Pending", transaction_Id, message,bankRrn,tx_status);

								if("1".equalsIgnoreCase(channel))
									message="Transaction is successfully posted";
								else
									message="Transaction is successfully processed but the final response is awaited";
								statusCode="0";
							}

						} else {
							log.info(transactionId);
							log.info(str);
							String agent_transaction_update = trandao.updateStatusDMRTranEKO(customer_mobile,transactionId,
									"Failure", "", message,"","");
							log.info("agent_transaction_update:::::"+agent_transaction_update);
						}
						traninfomap.put("message", message);
					} else
					{

						traninfomap.put("message", "Transaction In Process.");
						trandao.updateStatusDMRTranEKO(customer_mobile,transactionId, "Pending", "", "Response not Received","","");
						log.info("message from eko is:::"+message);
						statusCode="0";

					}


					DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
					DateFormat df1 = new SimpleDateFormat("HH:mm:ss");
					Date dateobj = new Date();
					String date1 = df.format(dateobj);
					String time1 = df1.format(dateobj);

					traninfomap.put("BeneName", recipient_name);
					traninfomap.put("TxnDate", date1);
					traninfomap.put("TxnTime", time1);
					traninfomap.put("BeneMobile", recepient_mobile);
					traninfomap.put("TxnAmount",String.valueOf(amt));
					traninfomap.put("tid", transactionId);
					traninfomap.put("BankRRN", bankRrn);
					traninfomap.put("ifsc", ifsc.toUpperCase());
					traninfomap.put("BankName", bankname);
					traninfomap.put("BeneAccount", ben_account);
					traninfomap.put("SenderName", remittername);
					traninfomap.put("SenderId", customer_mobile);

					traninfomap.put("Status",statusCode);
					traninfomap.put("message",message);

					log.info("RemitTransaction.execute()"+traninfomap);

					return traninfomap;

				}else 
				{
					message="Exceed Month Limit";
					traninfomap.put("Status",statusCode);
					traninfomap.put("message",message);
					return traninfomap;
				}
			}else{
				message="Transaction Failed. Please contact to your channel partner.";
				traninfomap.put("Status",statusCode);
				traninfomap.put("message",message);
				return traninfomap;
			}
		} catch (Exception e) {
			log.error("Error in execute method of RemitTransaction class"+e.getMessage());
			e.printStackTrace();

			traninfomap.put("Status", "1");
			traninfomap.put("message", "We are unable to process your request. Please try later.");

		}
		return traninfomap; 
	}

	public JSONObject verifiAccount(String agentID,String acc_no,String ifsc,String BankCode,
			String senderId,String Ipaddress) {


		JSONObject accnameinfo = new JSONObject();
		try {

			EkoAPICall ekoAPICall=new EkoAPICall();
			EkoDMTDao dmtDao=new EkoDMTDao();

			String IdNo="AE"+agentID+CommonServices.dateTime();
			String transactionId=CommonServices.getAETranId();

			String ekostatus = "failure", accinfo = "",message="",code="1",tid="";
			int status=-1;

			if (ifsc == null)
				ifsc = "";

			if (BankCode == null)
				BankCode = "";


			String bankName="";

			String service="DMR-E-ACCOUNT",operator="DMR-E-ACCOUNT";

			String TranResult = dmtDao.DMR_TRANSACTION_INSERT(IdNo, transactionId, agentID, service,operator, senderId, Ipaddress, "EKO", "", "Account verification", "IMPS", "", bankName, ifsc, acc_no, 1);

			if (TranResult.equalsIgnoreCase("insufficient_balance")) {
				message="You Do Not have Sufficient Balance.";
				accnameinfo.put("Status",code);
				accnameinfo.put("message",message);
				return accnameinfo;
			} else if (TranResult.equalsIgnoreCase("noinserted")) {
				message="Transaction Failed Due to Technical Error. Please Try Later.";
				accnameinfo.put("Status",code);
				accnameinfo.put("message",message);
				return accnameinfo;
			}else if (TranResult.equalsIgnoreCase("duplicate")) {
				message="Duplicate transaction.Please Try again.";
				accnameinfo.put("Status",code);
				accnameinfo.put("message",message);
				return accnameinfo;
			} else if (TranResult.equalsIgnoreCase("commisson_error")) {
				message="Transaction Failed as Your Commission is Not Set Properly.";
				accnameinfo.put("Status",code);
				accnameinfo.put("message",message);
				return accnameinfo;
			}
			else if ("inserted".equalsIgnoreCase(TranResult)) {

				String accdata = "initiator_id=" + PropertyFile.initiator_id + "&customer_id=" + senderId;
				if (BankCode!=null && !"".equalsIgnoreCase(BankCode)) {
					accinfo = PropertyFile.ekoAccountVerify+"bank_code:" + BankCode+ "/accounts/" + acc_no;

				} else if (!"".equalsIgnoreCase(ifsc)) 
				{
					accinfo = PropertyFile.ekoAccountVerify+"ifsc:" + ifsc + "/accounts/" + acc_no;
				} else
				{
					accinfo = PropertyFile.ekoAccountVerify+"bank_code:" + BankCode+ "/accounts/" + acc_no;
				}
				String reqString=accdata+"?"+accinfo;
				String respString="";
				log.info("reqString : " + reqString);

				dmtDao.insertEKOLogs("EKO", transactionId, reqString, agentID);

				JSONObject obj = ekoAPICall.ekoCall(accinfo, accdata,"POST");
				log.info("account name info" + obj);
				if (obj != null) {

					respString=obj.toString();
					dmtDao.updateEKOLogs(transactionId, respString, agentID);

					message = (String) obj.get("message");
					status = (int) obj.get("response_type_id");
					JSONObject invalid_params=obj.optJSONObject("invalid_params");
					if(invalid_params!=null && invalid_params.has("account"))
						message=invalid_params.optString("account");


					if (status == 61) {

						JSONObject dataacc = (JSONObject) obj.get("data");
						String account = dataacc.optString("account");
						String bank = dataacc.optString("bank");
						String bene_name =dataacc.optString("recipient_name");
						String is_name_editable =dataacc.optString("is_name_editable");
						String is_Ifsc_required =dataacc.optString("is_Ifsc_required");
						if(!"".equalsIgnoreCase(bene_name))
							message=message+" Beneficiary name is "+bene_name;
						// Add key for tid..
						tid =dataacc.get("tid")+"" ;
						accnameinfo.put("AccountNo", account);
						accnameinfo.put("BankName", bank);
						accnameinfo.put("BeneName", bene_name);
						accnameinfo.put("TxnId",tid);
						accnameinfo.put("BankCode",BankCode);
						accnameinfo.put("IFSC",ifsc);
						accnameinfo.put("is_name_editable",is_name_editable);
						accnameinfo.put("is_Ifsc_required",is_Ifsc_required);

						if(message.contains("INVALID ACCOUNT DETAILS") || message.contains("Invalid Account Number"))
							code="1";//INVALID ACCOUNT DETAILS//Invalid Account Number
						else{
							code="0";
							ekostatus = "Success";
						}
						// getting IFSC code of banks..						
						/*String shortIfsc=dmtDao.getImpsEnableStatusBankCode(bank);
						if(shortIfsc!=null && shortIfsc.length()>=0)
							accnameinfo.put("IFSC", shortIfsc);
						else */
						accnameinfo.put("IFSC", ifsc);

					}

					String updateinfo = dmtDao.updateStatusDMRTranEKO(senderId,transactionId,
							ekostatus, tid,"EKO","",String.valueOf(status));
					log.info("Account Verification update Status : " +updateinfo);
				}

			} else {

				if (TranResult.equalsIgnoreCase("Failure"))
					message="Verify Account Failure";
				else if (TranResult.equalsIgnoreCase("insufficient_balance"))
					message="Insufficient balance";


			}

			accnameinfo.put("message", message);
			accnameinfo.put("Status", code);

			return accnameinfo;

		} catch (Exception e) {
			e.printStackTrace();
			accnameinfo = new JSONObject();
			accnameinfo.put("message", "Unable to process your request. Please Try later.");
			accnameinfo.put("Status", "1");
			return accnameinfo;
		}

	}

	public JSONObject AddBeneAfterVerify(String agentID,String account,String ifsc,String beneficiary_name,String beneficiary_mob_no,
			String SenderId,String BankCode,String verify,String ipAddress) {

		String message="",code="1";
		JSONObject jsonObject=new JSONObject();

		try {

			EkoAPICall ekoAPICall=new EkoAPICall();
			EkoDMTDao ekoDMTDao=new EkoDMTDao();
			int status;


			String verifymessage="";
			String addbeneficiaryurl="",beneId="",bankName="";
			//Fetch short code for eko by master id
			HashMap<String,String> bankDetails=ekoDMTDao.getMasterBankStatus(BankCode);
			BankCode=bankDetails.get("short_code");
			bankName=bankDetails.get("bank");
			
			if(BankCode!=null && BankCode.length()>=3)
				addbeneficiaryurl=PropertyFile.ekocustomerurl+"mobile_number:"+SenderId+"/recipients/acc_bankcode:"+account.trim()+"_"+BankCode.trim().toUpperCase()+"";

			else
				addbeneficiaryurl=PropertyFile.ekocustomerurl+"mobile_number:"+SenderId+"/recipients/acc_ifsc:"+account.trim()+"_"+ifsc.trim().toUpperCase()+"";


			String str="recipient_name="+beneficiary_name+"&initiator_id="+PropertyFile.initiator_id+"&mobile="+beneficiary_mob_no.trim()+"&recipient_type=3";
			log.info("Add Beneficiary Request : "+addbeneficiaryurl+"?"+str);
			JSONObject obj=ekoAPICall.ekoCall(addbeneficiaryurl,str,"PUT");
			log.info("Add Beneficiary Response : "+obj);
			if(obj!=null)
			{
				message=(String) obj.get("message");
				status =(int) obj.get("status");
				int response_status_id =(int) obj.get("response_status_id");
				int response_type_id =obj.getInt("response_type_id");
				if((status==0) && (response_status_id==0) && response_type_id==43)
				{
					JSONObject json=(JSONObject) obj.get("data");
					beneId=json.getString("recipient_id");

				}else{
					log.info("Status::::"+status);
					beneId=ekoDMTDao.generateBeneId();
				}

			}else{
				beneId=ekoDMTDao.generateBeneId();
			}
			
			ifsc=bankDetails.get("ifsc");
			String addbeneficiarystatus=ekoDMTDao.addBeneficiary(beneficiary_name, beneficiary_mob_no, bankName, ifsc, account, SenderId, beneId);

			log.info("add beneficiary status"+addbeneficiarystatus);

			code="0";

			jsonObject.put("BankName", bankName);
			jsonObject.put("BeneId", beneId);
			message="Beneficiary Added Successfully.";
			
			jsonObject.put("message", message+" "+verifymessage);
			jsonObject.put("Status", code);

			return jsonObject;

		} catch (Exception e) {
			e.printStackTrace();
			jsonObject = new JSONObject();
			jsonObject.put("message", "Unable to process your request. Please Try later.");
			jsonObject.put("Status", "1");

			return jsonObject;
		}
	}

	public JSONObject deleteBene(String senderMobile,String AgentID,String recipient_id){
		String message="",code="1";
		JSONObject jsonObject=new JSONObject();

		try {

			log.info("Delete Beneficiary");

			String deletebeneficiaryurl=PropertyFile.ekocustomerurl+"mobile_number:"+senderMobile.trim()+"/recipients/recipient_id:"+recipient_id;
			String str="initiator_id="+PropertyFile.initiator_id;

			log.info("Delete Beneficiary Request "+deletebeneficiaryurl+"?"+str);

			EkoAPICall ekoAPICall=new EkoAPICall();
			JSONObject obj=ekoAPICall.ekoCall(deletebeneficiaryurl,str,"DELETE");

			log.info("Delete Beneficiary Response "+obj);

			log.info("----objobjobjobj---"+obj);
			if(obj.getString("status").equals("0"))
			{
				message =(String) obj.get("message");
				code="0";

				EkoDMTDao ekoDMTDao=new EkoDMTDao();
				ekoDMTDao.deleteReceipent(recipient_id);

			}else{
				message="Recipient Not Deleted";
			}

			jsonObject.put("message", message);
			jsonObject.put("Status", code);

		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("message", "Unable to process your request. Please Try later.");
			jsonObject.put("Status", code);
		}
		return jsonObject;
	}

	public JSONObject tranStatus(String senderId,String tranId) {

		JSONObject response=new JSONObject();

		try {
			String transactionstatus = "";

			String ekoID=EkoDMTDao.getEKOTid(tranId);
			if(ekoID==null){

				response.put("Status", "1");
				response.put("message", "Transaction not found. Contact to Admin.");

			}else{

				String url = PropertyFile.ekotranurl + ekoID + "?initiator_id=" + PropertyFile.initiator_id;
				EkoAPICall ekoAPICall=new EkoAPICall();

				log.info("Transaction Enquiry Request "+url);

				JSONObject jsonobj = ekoAPICall.ekoCall(url,"","GET");


				if (jsonobj != null) {
					log.info("Transaction Enquiry Response "+jsonobj.toString());

					String ekomessage = (String) jsonobj.get("message");
					JSONObject jsondata = (JSONObject) jsonobj.get("data");
					String amount = (String)jsondata.get("amount");
					String txstatus_desc = (String)jsondata.get("txstatus_desc");
					String tx_desc = (String)jsondata.get("tx_desc");
					String bank_ref_num = (String)jsondata.get("bank_ref_num");
					String customer_id = (String) jsondata.get("customer_id");
					String timestamp = (String) jsondata.get("timestamp");
					String tx_status = (String) jsondata.get("tx_status");
					if (tx_status.equals("0"))
						transactionstatus = "Success";
					if (tx_status.equals("1") || tx_status.equals("2"))
						transactionstatus = "Initiated";

					if (tx_status.equals("3"))
						transactionstatus = "Refund Pending";
					if (tx_status.equals("4"))
						transactionstatus = "Refunded";


					response.put("SenderId", customer_id);
					response.put("TransactionRefNo", tranId);
					response.put("Tran_Status", transactionstatus);
					response.put("Amount", amount);
					response.put("Tran_Status", transactionstatus);
					response.put("Tran_Desc", txstatus_desc);
					response.put("Tran_Type", tx_desc);
					response.put("Bank_Ref_Id", bank_ref_num);
					response.put("timestamp", timestamp);
					response.put("Status", "0");
					response.put("message", ekomessage);


				}else{
					response.put("Status", "1");
					response.put("message", "Transaction not found.");
				}

			}
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			response.put("Status","1");
			response.put("message", "Unable to process your request. Please try later.");
		}
		return response;
	}

	public JSONObject initiateRefundTransaction(String senderId,String tranId) {

		JSONObject response=new JSONObject();
		String message = "",statusCode="1";
		try {


			String ekoID=EkoDMTDao.getEKOTid(tranId);
			if(ekoID==null){

				message="Transaction not found. Contact to Admin.";

			}else{

				String str = "initiator_id=mobile_number:" + PropertyFile.initiator_id;
				log.info(str);
				String url = PropertyFile.ekotranurl + ekoID + "/refund/otp";

				log.info("Refund Transaction >>>>>>>>>>>>>>>>>>>>.. :: "+url);

				EkoAPICall ekoAPICall=new EkoAPICall();

				log.info("Refund OTP Request "+url+"?"+str);

				JSONObject jsonobj = ekoAPICall.ekoCall(url, str,"POST");

				log.info("Refund OTP Response "+jsonobj);

				if (jsonobj != null)
				{
					message = (String) jsonobj.get("message");
					//					/response_type_id = (int) jsonobj.get("response_type_id");
					//int response_status_id = (int) jsonobj.get("response_status_id");
					int status = (int) jsonobj.get("status");
					if (status == 0) {
						JSONObject json = (JSONObject) jsonobj.get("data");
						String tran_id = (String) json.get("tid");
						//String otp = (String) json.get("otp");

						tranId= tran_id;
						response.put("Refund_OTP", "");
						statusCode="0";
					}

				}else{

					message="Transaction not found.";
				}

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

	public JSONObject confirmRefundTransaction(String senderId,String tranId,String otp,String agentId) {

		JSONObject response=new JSONObject();
		String message = "",statusCode="1";
		try {



			String ekoID=EkoDMTDao.getEKOTid(tranId);
			if(ekoID==null){

				response.put("Status", "1");
				message= "Transaction not found. Contact to Admin.";

			}else{

				EkoDMTDao dao=new EkoDMTDao();
				log.info("transactionid:::::::::"+ekoID);

				String str = "otp=" + otp + "&initiator_id=" + PropertyFile.initiator_id + "&state=1";
				String url = PropertyFile.ekotranurl + ekoID + "/refund";
				EkoAPICall ekoAPICall=new EkoAPICall();

				log.info("Refund Request "+url+"?"+str);


				String RefundtransactionId="R"+ekoID;
				String reqString=url+"?" + str;
				dao.insertEKOLogs("EKO", RefundtransactionId, reqString, agentId);
				JSONObject jsonobj = ekoAPICall.ekoCall(url, str,"POST");

				log.info("Refund Response "+jsonobj);

				if (jsonobj != null) {

					String apiResponse=jsonobj.toString();

					dao.updateEKOLogs(RefundtransactionId, apiResponse, agentId);

					message = (String) jsonobj.get("message");
					//response_type_id = (int) jsonobj.get("response_type_id");
					//response_status_id = (int) jsonobj.get("response_status_id");
					int status = (int) jsonobj.get("status");
					JSONObject json = (JSONObject) jsonobj.get("data");
					if (status == 0) {

						//String refunded_amount = json.get("refunded_amount")+"";
						String refund_tid = json.get("refund_tid")+"";
						response.put("RefundTranId", refund_tid);

						String insertinfo = dao.updateStatusDMRTranEKO(senderId,tranId,"Refund", refund_tid, message,"",String.valueOf(status));
						log.info("insert infffffffffffffff" + insertinfo);

						statusCode="0";

					}


				}else{
					message= "Technical failure. Please try later.";
				}
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

	public JSONObject bankDetails(String bankCode,String ifsc) {

		JSONObject response=new JSONObject();

		try {

			String url = PropertyFile.ekobankDetails+ "?bank_code="+bankCode+"&initiator_id=" + PropertyFile.initiator_id;
			EkoAPICall ekoAPICall=new EkoAPICall();

			log.info("Bank Enquiry Request "+url);

			JSONObject jsonobj = ekoAPICall.ekoCall(url,"","GET");


			if (jsonobj != null) {
				log.info("Bank Enquiry Response "+jsonobj.toString());

				String response_type_id=jsonobj.get("response_type_id")+"";
				String status=jsonobj.get("status")+"";
				String ekomessage = (String) jsonobj.get("message");
				if("467".equalsIgnoreCase(response_type_id)){
					response.put("Status", "1");
				}else if("0".equalsIgnoreCase(status) && !"467".equalsIgnoreCase(response_type_id)){

					JSONObject jsondata = (JSONObject) jsonobj.get("data");
					String isverificationavailable = (String)jsondata.get("isverificationavailable");
					String code = (String)jsondata.get("code");
					String available_channels = jsondata.get("available_channels")+"";
					String ifsc_status = jsondata.get("ifsc_status")+"";
					response.put("isverificationavailable", isverificationavailable);
					response.put("BankCode", code);
					response.put("available_channels", available_channels);
					response.put("ifsc_status", ifsc_status);
					response.put("Status", "0");
				}else{
					response.put("Status", "1");
				}

				response.put("message", ekomessage);

			}else{
				response.put("Status", "1");
				response.put("message", "Bank not found.");
			}

			return response;
		} catch (Exception e) {
			e.printStackTrace();
			response.put("Status","1");
			response.put("message", "Unable to process your request. Please try later.");
		}
		return response;
	}

}
