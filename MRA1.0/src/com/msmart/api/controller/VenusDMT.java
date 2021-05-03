package com.msmart.api.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;

import com.msmart.dao.CommonServiceDao;
import com.msmart.dao.OtpValidationDao;
import com.msmart.eko.EkoAPICall;
import com.msmart.eko.EkoDMTDao;
import com.msmart.service.CommonServices;
import com.msmart.service.PropertyFile;
import com.msmart.util.SendSmsOnMobile;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import sun.misc.BASE64Encoder;

public class VenusDMT {

	

	Logger log=Logger.getLogger(VenusDMT.class);

	public JSONObject findSender(String senderId) {

		JSONObject reqJson=null,respJson=null;
		respJson=new JSONObject();
		JSONObject senderDetails=new JSONObject();

		try {

			reqJson=new JSONObject();

			String transactionId=RandomStringUtils.randomNumeric(16);

			String url="check_wallet";
			reqJson.put("request_id", transactionId);
			reqJson.put("sender_mobile", senderId);

			JSONObject venusResp=webservicecall(reqJson, url);

			if(venusResp!=null) {

				JSONObject response=venusResp.getJSONObject("response");
				String status_code=response.getString("status_code");
				String desc=response.getString("desc");
				if("RCS".equalsIgnoreCase(status_code)) {

					String WalletExist=response.getString("WalletExist");
					if("N".equalsIgnoreCase(WalletExist)) {
						respJson.put("Status","3");
						respJson.put("message", "Sender not found. Retry with different sender id Or Register Sender");
					}else {
						venusResp=null;
						transactionId=RandomStringUtils.randomNumeric(16);
						reqJson=new JSONObject();
						url="check_balance";
						reqJson.put("request_id", transactionId);
						reqJson.put("sender_mobile", senderId);
						venusResp=webservicecall(reqJson, url);

						if(venusResp!=null) {

							response=venusResp.getJSONObject("response");
							status_code=response.getString("status_code");
							desc=response.getString("desc");
							if("RCS".equalsIgnoreCase(status_code)) {

								String balance=response.getString("Balance");
								double limit=25000;
								double usedLimit=limit-Double.parseDouble(balance);


								senderDetails.put("SenderId", senderId);
								senderDetails.put("Name", senderId);
								respJson.put("SenderDetails", senderDetails);

								JSONObject senderLimitDetails = new JSONObject();

								senderLimitDetails.put("KycStatus", "NA");
								senderLimitDetails.put("SenderLimit", String.valueOf(limit));
								senderLimitDetails.put("UsedLimit", String.valueOf(usedLimit));
								senderLimitDetails.put("AvailableLimit", balance);
								senderLimitDetails.put("NonKycLimit", String.valueOf(limit));
								senderLimitDetails.put("KycLimit", "NA");

								respJson.put("SenderLimitDetails", senderLimitDetails);

								//Beneficiary details

								JSONArray Beneficiary=fetchBeneficiary(senderId);
								respJson.put("BeneList", Beneficiary.toString());

								respJson.put("Status","0");
								respJson.put("message", "Success");

							}
						}else {
							respJson.put("Status","1");
							respJson.put("message", desc);
						}
					}

				}else {
					respJson.put("Status","1");
					respJson.put("message", desc);
				}

			}else {
				respJson.put("Status","1");
				respJson.put("message", "Unable to process your request. Please try later.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			respJson=new JSONObject();
			respJson.put("Status","1");
			respJson.put("message", "Unable to process your request. Please try later.");
		}
		return respJson;
	}

	public JSONArray fetchBeneficiary(String senderId) {

		JSONArray list=new JSONArray();;
		try {

			/************************************ Sender Beneficiary List Details *************************************/

			JSONObject reqJson=new JSONObject();;
			String transactionId=RandomStringUtils.randomNumeric(16);
			String url="fetch_beneficiary";
			reqJson.put("request_id", transactionId);
			reqJson.put("sender_mobile", senderId);
			JSONObject venusResp=webservicecall(reqJson, url);
			if(venusResp!=null) {

				JSONObject response=venusResp.getJSONObject("response");
				String status_code=response.getString("status_code");
				if("RCS".equalsIgnoreCase(status_code)) {

					JSONArray Beneficiary=response.getJSONArray("Beneficiary");
					if(Beneficiary!=null && Beneficiary.size()>0) {

						for (int i = 0; i < Beneficiary.size(); i++) {

							JSONObject bene=Beneficiary.getJSONObject(i);

							JSONObject jsonObject=new JSONObject();

							jsonObject.put("RecipientId", bene.getString("BeneficiaryCode"));
							jsonObject.put("RecipientIdType", "NA");
							jsonObject.put("BeneName", bene.getString("BeneficiaryName"));
							jsonObject.put("Account", bene.getString("AccountNumber"));
							jsonObject.put("Ifsc", bene.getString("IFSC"));
							jsonObject.put("BankName", bene.getString("Bankname"));
							jsonObject.put("BeneMobile", "NA");
							jsonObject.put("IMPS", "2");
							jsonObject.put("NEFT", "1");

							jsonObject.put("Channel", "0");

							jsonObject.put("IsVerified", "0");
							list.add(jsonObject);
						}
					}

				}
			}else {
				return list;
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

	public JSONObject resendOtp(String senderId) {

		JSONObject reqJson=null,respJson=null;
		respJson=new JSONObject();

		try {

			reqJson=new JSONObject();

			String transactionId=RandomStringUtils.randomNumeric(16);

			OtpValidationDao dao=new OtpValidationDao();
			String refNo=dao.getRefNo(senderId);

			String url="resend_create_wallet_otp";
			reqJson.put("request_id", transactionId);
			reqJson.put("sender_mobile", senderId);
			reqJson.put("create_wallet_id", refNo);

			JSONObject venusResp=webservicecall(reqJson, url);

			if(venusResp!=null) {

				JSONObject response=venusResp.getJSONObject("response");
				String status_code=response.getString("status_code");
				String desc=response.getString("desc");

				respJson.put("Status","0");
				respJson.put("message", desc);

			}else {
				respJson.put("Status","1");
				respJson.put("message", "Unable to process your request. Please try later.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			respJson.put("Status","1");
			respJson.put("message", "Unable to process your request. Please try later.");
		}
		return respJson;
	}

	public JSONObject registerSender(String senderid,String senderName,String address,String dob){

		JSONObject reqJson=null,respJson=null;
		respJson=new JSONObject();

		try {

			reqJson=new JSONObject();

			String transactionId=RandomStringUtils.randomNumeric(16);

			OtpValidationDao dao=new OtpValidationDao();

			String [] arr=senderName.split(" ");
			if(arr.length>1) {
				reqJson.put("sender_first_name", arr[0]);
				reqJson.put("sender_last_name", arr[1].replaceAll(" ","_"));
			}else {
				reqJson.put("sender_first_name", arr[0]);
				reqJson.put("sender_last_name", "Kumar");
			}
			if(dob==null)
				dob="201013";

			String url="create_wallet";
			reqJson.put("request_id", transactionId);
			reqJson.put("sender_mobile", senderid);
			reqJson.put("sender_address", "Ghaziabad");
			reqJson.put("sender_address_area", "Ghaziabad");
			reqJson.put("sender_address_pincode", dob);

			JSONObject venusResp=webservicecall(reqJson, url);

			if(venusResp!=null) {

				JSONObject response=venusResp.getJSONObject("response");
				String status_code=response.getString("status_code");
				String desc=response.getString("desc");
				if("RCS".equalsIgnoreCase(status_code)) {

					String RequestNo=response.getString("RequestNo");
					dao.insertOTP(RequestNo, senderid);

					respJson.put("Status","0");
					respJson.put("message", desc);
				}else {
					respJson.put("Status","1");
					respJson.put("message", desc);
				}

			}else {
				respJson.put("Status","1");
				respJson.put("message", "Unable to process your request. Please try later.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			respJson.put("Status","1");
			respJson.put("message", "Unable to process your request. Please try later.");
		}
		return respJson;
	}

	public JSONObject verifySender(String customerId,String otp){

		JSONObject reqJson=null,respJson=null;
		respJson=new JSONObject();

		try {

			reqJson=new JSONObject();

			String transactionId=RandomStringUtils.randomNumeric(16);

			OtpValidationDao dao=new OtpValidationDao();
			String refNo=dao.getRefNo(customerId);

			String url="verify_wallet";
			reqJson.put("request_id", transactionId);
			reqJson.put("sender_mobile", customerId);
			reqJson.put("create_wallet_id", refNo);
			reqJson.put("otp", otp);

			JSONObject venusResp=webservicecall(reqJson, url);

			if(venusResp!=null) {

				JSONObject response=venusResp.getJSONObject("response");
				String status_code=response.getString("status_code");
				String desc=response.getString("desc");

				respJson.put("Status","0");
				respJson.put("message", desc);

			}else {
				respJson.put("Status","1");
				respJson.put("message", "Unable to process your request. Please try later.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			respJson.put("Status","1");
			respJson.put("message", "Unable to process your request. Please try later.");
		}
		return respJson;
	}

	public JSONObject ekoTransaction(JSONObject jsonObject,String ipaddress){

		String message="",agent_id="";
		JSONObject traninfomap = new JSONObject();
		try {

			String bankname="NA",recipient_id = "",vendor="PayTM";
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

			int kycStatus=0;

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
								vendor, ipaddress, amt,recipient_id,channel,service,operator,kycStatus);
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
										vendor, ipaddress, partamt,recipient_id,channel,service,operator,kycStatus);
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
										vendor, ipaddress, partamt,recipient_id,channel,service,operator,kycStatus);
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
										vendor, ipaddress, remainingamount,recipient_id,channel,service,operator,kycStatus);
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


			}else{
				traninfomap=new JSONObject();
				traninfomap.put("Status", "1");
				traninfomap.put("message", status);

			} 

			return traninfomap;
		} catch (Exception e) {
			e.printStackTrace();

			traninfomap.put("Status", "1");
			traninfomap.put("message", "We are unable to process your request. Please try later.");
			return traninfomap;
		}

	}


	public JSONObject getTransactionResult(String Remarks,String agentCode,String recepient_mobile,String ifsc,String remittername,
			String recipient_name,String bankname,String customer_mobile,String ben_account,String vendor,String Ipaddress,double amt,
			String recipient_id,String channel,String service,String oprtor,int kycStatus) {
		log.info("executing getTransactionResult method of DMRTransactionEKO class");
		JSONObject traninfomap = new JSONObject();

		String statusCode="1";

		try {

			EkoDMTDao trandao=new EkoDMTDao();
			String message=null;
			String IdNo=CommonServices.dateTime();
			String transactionId=CommonServices.createTransactionID();
			String bankRrn="";
			traninfomap.put("tranid",IdNo);

			String transactionType="";
			if("1".equalsIgnoreCase(channel))
				transactionType="NEFT";
			else if("2".equalsIgnoreCase(channel))
				transactionType="IMPS";
			else
				transactionType="IMPS";

			double amtlimit = amt;

			if (amtlimit <= 5000) {

				String url="transaction";
				JSONObject reqJson=new JSONObject();
				reqJson.put("request_id", transactionId);
				reqJson.put("sender_mobile", customer_mobile);
				reqJson.put("amount", String.valueOf(amt));
				reqJson.put("beneficiary_id", recipient_id);
				reqJson.put("transaction_type", transactionType);

				String TranResult = trandao.DMR_TRANSACTION_INSERT(IdNo, transactionId, agentCode, service, oprtor, customer_mobile, Ipaddress, 
						vendor, recipient_id, Remarks, transactionType,  recipient_name,bankname, ifsc, ben_account, amt);


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
				}else if (TranResult.equalsIgnoreCase("down")) {
					message="IMPS Service is down. Please try after some time.";
					traninfomap.put("Status",statusCode);
					traninfomap.put("message",message);
					return traninfomap;
				}  else if (TranResult.equalsIgnoreCase("commisson_error")) {
					message="Transaction Failed as Your Commission is Not Set Properly.";
					traninfomap.put("Status",statusCode);
					traninfomap.put("message",message);
					return traninfomap;
				}else if (TranResult.equalsIgnoreCase("Initiated")) {

					CommonServiceDao.updateATTRequestLog(transactionId, reqJson.toString());

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

					traninfomap.put("Status","0");

					traninfomap.put("message","Transaction successfully posted.");

					log.info("RemitTransaction.execute()"+traninfomap);

					try {
						String sms="Transaction Posted Successfully, TID: "+transactionId+", Amt:Rs."+amt+"Fees:1%, A/C:"+ben_account+", "+bankname+", "+date1+" "+time1+" Thanks, Soft Solution Zone Private Limited";
						SendSmsOnMobile.sendMobileSmsSMSZONE(customer_mobile, sms);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					return traninfomap;
				}
				else if (TranResult.equalsIgnoreCase("inserted")) {

					if(null==remittername){
						remittername = "";
					}
					CommonServiceDao.updateATTRequestLog(transactionId, reqJson.toString());
					JSONObject venusResp=webservicecall(reqJson, url);

					double netCharge=0;

					if (venusResp != null) {
						log.info("Transaction Response : "+venusResp.toString());
						String apiresponse = venusResp.toString();
						CommonServiceDao.updateATTResponseLog(transactionId, apiresponse.toString());

						JSONObject response=venusResp.getJSONObject("response");
						String status_code=response.getString("status_code");
						String desc=response.getString("desc");
						String Status=response.optString("Status");
						if("TXN".equalsIgnoreCase(status_code) || "RCS".equalsIgnoreCase(status_code) 
								|| "OUE".equalsIgnoreCase(status_code) || "IPE".equalsIgnoreCase(status_code)) {

							if(null!=Status){
								String transaction_Id=response.getString("OrderId");
								bankRrn=response.getString("UTR");

								if("COMPLETED".equalsIgnoreCase(Status)) {

									//JSONObject billing=venusResp.getJSONObject("billing");

									String result=trandao.updateStatusDMRTranEKO(customer_mobile,transactionId,
											"Success", transaction_Id,message, bankRrn,"0");

									log.info("Transaction Update status : "+result);
									message="Transaction is successful";

									statusCode="0";

								}else if("FAILED".equalsIgnoreCase(Status)) {

									trandao.updateStatusDMRTranEKO(customer_mobile,transactionId,
											"Failure", transaction_Id, message,bankRrn,"0");

									message=desc;

								}else {

									trandao.updateStatusDMRTranEKO(customer_mobile,transactionId,"Pending", transaction_Id, message,bankRrn,"0");

									message=desc;
								}

							}
						}else if("IAB".equalsIgnoreCase(status_code) || "SNA".equalsIgnoreCase(status_code) 
								|| "ISR".equalsIgnoreCase(status_code)|| "SPE".equalsIgnoreCase(status_code)
								|| "IRP".equalsIgnoreCase(status_code)|| "DRD".equalsIgnoreCase(status_code)
								|| "DTX".equalsIgnoreCase(status_code)|| "ISE".equalsIgnoreCase(status_code)
								|| "SPD".equalsIgnoreCase(status_code)|| "IRI".equalsIgnoreCase(status_code)
								|| "TRP".equalsIgnoreCase(status_code)){
							
							trandao.updateStatusDMRTranEKO(customer_mobile,transactionId,
									"Failure", "", message,bankRrn,"0");

							message=desc;
							
						}else {

							if("1".equalsIgnoreCase(channel))
								message="Transaction is successfully posted";
							else
								message="Transaction is successfully processed but the final response is awaited";
							statusCode="0";
						}

					} else
					{

						traninfomap.put("message", "Transaction In Process.");
						trandao.updateStatusDMRTranEKO(customer_mobile,transactionId, "Pending", "", "Response not Received","","0");
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
					if(message!=null && message.contains("Insufficient"))
						message="Remitance service has been down for while. Please wait and try after some time.";

					traninfomap.put("message",message);

					log.info("RemitTransaction.execute()"+traninfomap);

					return traninfomap;

				}else{
					message="Transaction Failed. Please contact to your channel partner.";
					traninfomap.put("Status",statusCode);
					traninfomap.put("message",message);
					return traninfomap;
				}
			}else{
				message="Exceed Month Limit";
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

			EkoDMTDao dmtDao=new EkoDMTDao();

			String IdNo=CommonServices.dateTime();
			String transactionId=CommonServices.createTransactionID();

			String ekostatus = "failure",message="",code="1",tid="";

			String bankName="";



			String service="DMR-E-ACCOUNT",operator="DMR-E-ACCOUNT";

			String TranResult = dmtDao.DMR_TRANSACTION_INSERT(IdNo, transactionId, agentID, service,operator, 
					senderId, Ipaddress, "PayTM", "", "Account verification", "IMPS", "", bankName, ifsc, acc_no, 1);

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

				String url="verify_beneficiary_account";
				JSONObject reqJson=new JSONObject();
				reqJson.put("request_id", transactionId);
				reqJson.put("sender_mobile", senderId);
				reqJson.put("account", acc_no);

				if(ifsc!=null && !"".equalsIgnoreCase(ifsc))
					reqJson.put("bank_ifsc", ifsc);
				else {
					reqJson.put("bank_ifsc", BankCode+acc_no.substring(0, 7));
				}

				log.info("reqString : " + reqJson);

				CommonServiceDao.updateATTRequestLog(transactionId, reqJson.toString());

				JSONObject venusResp=webservicecall(reqJson, url);
				log.info("account name info" + venusResp);
				if (venusResp != null) {

					String updateinfo ="";
					double fee=0;
					CommonServiceDao.updateATTResponseLog(transactionId, venusResp.toString());

					JSONObject response=venusResp.getJSONObject("response");
					String status_code=response.getString("status_code");
					String desc=response.getString("desc");
					String Status=response.optString("Status");
					if("TXN".equalsIgnoreCase(status_code) || "RCS".equalsIgnoreCase(status_code) || "OUE".equalsIgnoreCase(status_code) || "IPE".equalsIgnoreCase(status_code)) {

						if(null!=Status){

							if("COMPLETED".equalsIgnoreCase(Status)) {

								//JSONObject billing=venusResp.getJSONObject("billing");

								ekostatus="Success";
								updateinfo = dmtDao.updateStatusDMRTranEKO(senderId,transactionId,ekostatus, tid,"PayTM","","0");

							}else if("FAILED".equalsIgnoreCase(Status)) {

								ekostatus="Failure";
								updateinfo = dmtDao.updateStatusDMRTranEKO(senderId,transactionId,ekostatus, tid,"Venus","","0");

							}else {
								ekostatus="Pending";
								updateinfo = dmtDao.updateStatusDMRTranEKO(senderId,transactionId,ekostatus, tid,"Venus","","0");
							}

						}else {

							updateinfo = dmtDao.updateStatusDMRTranEKO(senderId,transactionId,ekostatus, tid,"Venus","","0");
						}
						message=desc;

					}else if("IAB".equalsIgnoreCase(status_code) || "SNA".equalsIgnoreCase(status_code) 
							|| "ISR".equalsIgnoreCase(status_code)|| "SPE".equalsIgnoreCase(status_code)
							|| "IRP".equalsIgnoreCase(status_code)|| "DRD".equalsIgnoreCase(status_code)
							|| "DTX".equalsIgnoreCase(status_code)|| "ISE".equalsIgnoreCase(status_code)
							|| "SPD".equalsIgnoreCase(status_code)|| "IRI".equalsIgnoreCase(status_code)
							|| "TRP".equalsIgnoreCase(status_code)){
						
						ekostatus="Failure";
						updateinfo = dmtDao.updateStatusDMRTranEKO(senderId,transactionId,ekostatus, tid,"Venus","","0");
					}else {
						ekostatus="Pending";
						updateinfo = dmtDao.updateStatusDMRTranEKO(senderId,transactionId,ekostatus, tid,"Venus","","0");
						message="Response not found";
					}
					code="0";
					

					log.info("Account Verification update Status : " +updateinfo);
					accnameinfo.put("AccountNo", acc_no);
					accnameinfo.put("BankName", BankCode);
					accnameinfo.put("BeneName", response.optString("BeneficiaryName"));
					accnameinfo.put("TxnId",response.optString("OrderId"));
					accnameinfo.put("BankCode",BankCode);
					accnameinfo.put("IFSC",ifsc);
					accnameinfo.put("is_name_editable","0");
					accnameinfo.put("is_Ifsc_required","0");


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


	public JSONObject AddBeneAfterVerify(String agentID,String account,String ifsc,String beneficiary_name,
			String beneficiary_mob_no,String SenderId,String BankCode,String verify,String ipAddress) {

		JSONObject reqJson=null,respJson=null;
		respJson=new JSONObject();
		String verifymessage="";
		try {

			reqJson=new JSONObject();
			
			if("Y".equalsIgnoreCase(verify)){

				JSONObject verifyRep=verifiAccount(agentID,account,ifsc,BankCode,SenderId,ipAddress);
				String resStatus=verifyRep.optString("Status");
				if("0".equalsIgnoreCase(resStatus)){

					verifymessage="Account verified successfully.";

				}else
					verifymessage="Account verification failed.";

			}
			

			String transactionId=RandomStringUtils.randomNumeric(16);

			String url="add_beneficiary";
			reqJson.put("request_id", transactionId);
			reqJson.put("sender_mobile", SenderId);
			reqJson.put("beneficiary_name", beneficiary_name);
			reqJson.put("account", account);

			if(ifsc!=null && !"".equalsIgnoreCase(ifsc))
				reqJson.put("bank_ifsc", ifsc);
			else {
				reqJson.put("bank_ifsc", BankCode+account.substring(0, 7));
			}

			JSONObject venusResp=webservicecall(reqJson, url);

			if(venusResp!=null) {

				JSONObject response=venusResp.getJSONObject("response");
				String status_code=response.getString("status_code");
				String desc=response.getString("desc");

				if("RCS".equalsIgnoreCase(status_code)) {


					respJson.put("BankName", BankCode);
					respJson.put("BeneId", response.optString("BeneficiaryCode"));
				}

				respJson.put("Status","0");
				respJson.put("message", desc+" "+verifymessage);

			}else {
				respJson.put("Status","1");
				respJson.put("message", "Unable to process your request. Please try later.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			respJson.put("Status","1");
			respJson.put("message", "Unable to process your request. Please try later.");
		}
		return respJson;
	}


	public JSONObject deleteBene(String senderMobile,String AgentID,String recipient_id){
		JSONObject jsonObject=new JSONObject();

		try {
			jsonObject.put("message", "Delete service not available");
			jsonObject.put("Status", "1");
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("message", "Unable to process your request. Please Try later.");
			jsonObject.put("Status", "1");
		}
		return jsonObject;
	}

	public JSONObject tranStatus(String senderId,String tranId) {

		JSONObject response=new JSONObject();

		try {
			String initiator = "";
			String transactionstatus = "";

			String ekoID=EkoDMTDao.getEKOTid(tranId);
			if(ekoID==null){

				response.put("Status", "1");
				response.put("message", "Transaction not found. Contact to Admin.");

			}else{

				/*response.put("SenderId", customer_id);
				response.put("TransactionRefNo", tranId);
				response.put("Tran_Status", transactionstatus);
				response.put("Amount", amount);
				response.put("Tran_Status", transactionstatus);
				response.put("Tran_Desc", txstatus_desc);
				response.put("Tran_Type", tx_desc);
				response.put("Bank_Ref_Id", bank_ref_num);
				response.put("timestamp", timestamp);
				response.put("Status", "0");
				response.put("message", ekomessage);*/

			}
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			response.put("Status","1");
			response.put("message", "Unable to process your request. Please try later.");
		}
		return response;
	}

	/*public JSONObject initiateRefundTransaction(String senderId,String tranId) {

		JSONObject response=new JSONObject();
		String message = "",statusCode="1";
		try {


			String ekoID=EkoDMTDao.getEKOTid(tranId);
			if(ekoID==null){

				message="Transaction not found. Contact to Admin.";

			}else{

				response.put("Status", "1");
				response.put("message", "Service not available");

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
				CommonServices.insertEKOLogs("EKO", RefundtransactionId, reqString, agentId);
				JSONObject jsonobj = ekoAPICall.ekoCall(url, str,"POST");

				log.info("Refund Response "+jsonobj);

				if (jsonobj != null) {

					String apiResponse=jsonobj.toString();

					CommonServices.updateEKOLogs(RefundtransactionId, apiResponse, agentId);

					message = (String) jsonobj.get("message");
					//response_type_id = (int) jsonobj.get("response_type_id");
					//response_status_id = (int) jsonobj.get("response_status_id");
					int status = (int) jsonobj.get("status");
					JSONObject json = (JSONObject) jsonobj.get("data");
					if (status == 0) {

						//String refunded_amount = json.get("refunded_amount")+"";
						String refund_tid = json.get("refund_tid")+"";
						response.put("RefundTranId", refund_tid);

						String insertinfo = dao.updateStatusDMRTranEKO(senderId,tranId,"Refund", refund_tid, message,"",String.valueOf(status),0);
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
	}*/

	public JSONObject bankDetails(String bankCode,String ifsc) {

		JSONObject response=new JSONObject();

		try {

			response.put("Status", "0");
			response.put("message", "Available");
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			response.put("Status","1");
			response.put("message", "Unable to process your request. Please try later.");
		}
		return response;
	}

	public static JSONObject webservicecall(JSONObject obj ,String url) {
		JSONObject json_obj=null;
		try {

			String password = PropertyFile.VENUS_USERID+":"+PropertyFile.VENUS_AUTH_KEY;
			BASE64Encoder encoder = new BASE64Encoder();
			String authString = "Basic "+ encoder.encode(password.getBytes());
			
			Client client = Client.create();
			
			String fUrl=PropertyFile.VENUS_URL+url;
			System.out.println("URL :: "+fUrl);
			
			

			WebResource webResource = client.resource(fUrl);

			ClientResponse response = webResource.type("application/json").header("Authorization", authString).post(ClientResponse.class,obj.toString());

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}

			String output = response.getEntity(String.class);
			json_obj = JSONObject.fromObject(output);


		} catch (Exception e) {

			e.printStackTrace();

		}
		return json_obj;
	}

	/*public  static JSONObject post(JSONObject obj ,String urll) throws IOException {
		JSONObject json_obj=null;
		String response = null;
		String urlLocation=PropertyFile.VENUS_URL+urll;
		if (urlLocation != null) {
			BufferedReader reader = null;
			OutputStreamWriter writer = null;
			HttpURLConnection connection = null;
			//			   logger.info("before try--------....");
			try {
				BASE64Encoder encoder = new BASE64Encoder();
				// Enter your basic authentication userid,pwd here
				String password = PropertyFile.VENUS_USERID+":"+PropertyFile.VENUS_AUTH_KEY; 
				String authString = "Basic "+ encoder.encode(password.getBytes());
				//				   logger.info("password getbytes is"+password.getBytes());
				URL url = new URL(urlLocation);
				connection = (HttpURLConnection) url.openConnection();
				connection.setConnectTimeout(150000);
				connection.setReadTimeout(150000);
				connection.setDoOutput(true);
				connection.setRequestMethod("POST");
				connection.setRequestProperty("Authorization", authString);

				connection.setRequestProperty("Content-Type","application/json");
				writer = new OutputStreamWriter(connection.getOutputStream());
				writer.write(obj.toString());
				writer.flush();

				StringBuilder sb = new StringBuilder();
				String line = null;

				reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

				while ((line = reader.readLine()) != null) 
				{
					sb.append(line);
				}
				response = sb.toString();
				json_obj=JSONObject.fromObject(response);
			} finally {
				try {
					if (writer != null) {
						writer.close();
					}
					if (reader != null) {
						reader.close();
					}
					if (connection != null) {
						connection.disconnect();
					}
				} catch (Exception e) {
				}
			}
		}
		
		return json_obj;
	}*/

}
