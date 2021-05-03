package com.msmart.ekonew;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.msmart.api.controller.ICICIAPICall;
import com.msmart.dao.CommonServiceDao;
import com.msmart.eko.EkoAPICall;
import com.msmart.eko.EkoDMTDao;
import com.msmart.service.CommonServices;
import com.msmart.service.PropertyFile;
import com.msmart.util.Job;
import com.msmart.util.SendSmsOnMobile;
import com.msmart.util.UtilityP;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class EkoDMTNew {

	Logger log=Logger.getLogger(EkoDMTNew.class);

	public JSONObject findSender(String senderId,String type,String AgentID) {

		JSONObject jsonObject=new JSONObject();

		JSONObject senderDetails=new JSONObject();

		String statusCode="1",message="";

		try {
			EkoDMTDao dao=new EkoDMTDao();
			EkoAPICall ekoAPICall=new EkoAPICall();


			//Merge Beneficiary
			Job.mergeBene(senderId);

			jsonObject=new JSONObject();

			String ekoUsrCode=dao.getEKoUserCode(AgentID);
			if(null== ekoUsrCode || "".equalsIgnoreCase(ekoUsrCode)) {

				jsonObject=new JSONObject();
				jsonObject.put("Status","5");
				jsonObject.put("message", "Invalid User code");
				return jsonObject;
			}

			String getcstmr = PropertyFile.ekocustomerurl + "mobile_number:" + senderId + "?initiator_id="+ PropertyFile.initiator_id + "&pipe=9&user_code="+ekoUsrCode;
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
					String str = "initiator_id=" + PropertyFile.initiator_id+"&pipe=9&user_code="+ekoUsrCode;
					JSONObject resendOtp = ekoAPICall.ekoCall(identityurl, str,"POST");
					if (resendOtp != null) {

						message = (String) resendOtp.get("message");
						status = (int) resendOtp.get("status");

						if(status==0){
							statusCode="2";
							JSONObject data2 = (JSONObject) resendOtp.get("data");
							String otp_ref_id=data2.optString("otp_ref_id");

							int updateStatus=dao.updateOTP(senderId, otp_ref_id);
							log.info("Resend Otp Update Status :: "+updateStatus);
						}


					}else{
						message="Unable to process your request. Please try later.";

						statusCode="1";

					}

				}else if(status==0){

					String name = "",state="",state_desc="";

					JSONArray limit=data.optJSONArray("limit");
					if(limit!=null) {
						for(int i=0;i<limit.size();i++) {
							JSONObject pipe=limit.getJSONObject(i);
							String pipeNo=pipe.getString("pipe");
							if("9".equalsIgnoreCase(pipeNo)) {
								int is_registered=pipe.getInt("is_registered");
								if(is_registered==0) {
									message="Sender not found. Retry with different sender id Or Register Sender";
									statusCode="3";
									jsonObject.put("Status",statusCode);
									jsonObject.put("message", message);
									return jsonObject;
								}
							}
						}
					}

					if (data!=null && data.has("state"))
					{
						state = (String) data.get("state");
						state_desc = (String) data.get("state_desc");

						if("1".equalsIgnoreCase(state) || "Verification pending".equalsIgnoreCase(state_desc)) {


							/*String verificationurl = PropertyFile.ekocustomerurl + "mobile_number:" + senderId + "/otp";
							String str = "initiator_id=" + PropertyFile.initiator_id+"&pipe=9&user_code="+ekoUsrCode;
							JSONObject verificationobj = ekoAPICall.ekoCall(verificationurl, str,"POST");
							if (verificationobj != null) {
								message = (String) verificationobj.get("message");
								status = (int) verificationobj.get("status");

								message = (String) verificationobj.get("message");
								status = (int) verificationobj.get("status");

								if(status==303){
									verificationurl = PropertyFile.ekocustomerurl + "mobile_number:" + senderId + "/otp";
									str = "initiator_id=" + PropertyFile.initiator_id+"&pipe=9&user_code="+ekoUsrCode;
									JSONObject resendOTP = ekoAPICall.ekoCall(verificationurl, str,"POST");
									if (resendOTP != null) {
										message = (String) resendOTP.get("message");
										status = (int) resendOTP.get("status");
										if(status==0){
											statusCode="2";
											JSONObject data2 = (JSONObject) resendOTP.get("data");
											String otp_ref_id=data2.optString("otp_ref_id");

											int updateStatus=dao.updateOTP(senderId, otp_ref_id);
											log.info("Resend Otp Update Status :: "+updateStatus);
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
							return jsonObject;*/

							message="Sender not found. Retry with different sender id Or Register Sender";
							statusCode="3";
							jsonObject.put("Status",statusCode);
							jsonObject.put("message", message);
							return jsonObject;
						}

						/************************************ Sender Beneficiary List Details *************************************/
						JSONArray benelist=fetchBeneficiary(senderId);
						jsonObject.put("BeneList", benelist!=null?benelist.toString():new JSONArray());

						/************************************ Sender Beneficiary List Details *************************************/


						/************************************ Sender Limit Details *************************************/

						JSONObject senderLimitDetails = new JSONObject();

						/*						senderLimitDetails.put("KycStatus", state_desc);
						senderLimitDetails.put("SenderLimit", data.get("total_limit"));
						senderLimitDetails.put("UsedLimit", data.get("used_limit"));
						senderLimitDetails.put("AvailableLimit", data.get("available_limit"));
						 */						
						senderLimitDetails.put("KycStatus", state_desc);
						senderLimitDetails.put("SenderLimit", data.get("total_limit"));
						senderLimitDetails.put("UsedLimit", data.get("used_limit"));
						senderLimitDetails.put("AvailableLimit", data.get("available_limit"));
						senderLimitDetails.put("NonKycLimit", data.get("bc_available_limit"));
						senderLimitDetails.put("KycLimit", data.get("wallet_available_limit"));

						jsonObject.put("SenderLimitDetails", senderLimitDetails);

						/************************************ Sender Limit Details *************************************/
					}

					/************************************ Sender Details *************************************/

					name = (String) data.get("name");
					senderDetails.put("SenderId", senderId);
					senderDetails.put("Name", name);
					jsonObject.put("SenderDetails", senderDetails);
					//log.info("Find sender response : "+jsonObject.toString());

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

	public JSONArray fetchBeneficiary(String senderId) {

		EkoDMTDao ekoDMTDao=new EkoDMTDao();

		return ekoDMTDao.fetchBeneficiary(senderId);


		/*EkoAPICall apiCall=new EkoAPICall();
		 * JSONObject beneData= apiCall.getAllBeneficiary(senderId,
		 * PropertyFile.initiator_id,type,vendorSatus,ekoUsrCode);
		 * 
		 * if(beneData!=null){
		 * JSONArray list=beneData.optJSONArray("recipient_list")==null?new JSONArray()
		 * :beneData.optJSONArray("recipient_list");
		 * return list;
		 * 
		 * }else
		 * return new JSONArray();
		 */

	}

	public JSONArray getAllBeneficiary(String mobile,String userCode) {
		log.info("Execute getAllBeneficiary method of dmr_customer_dao class");
		EkoAPICall ekoAPICall=new EkoAPICall();
		JSONArray jsonArray=new JSONArray();
		String url = PropertyFile.ekokycurl + mobile+ "/recipients?initiator_id=" + PropertyFile.initiator_id + "&user_code="+userCode;
		JSONObject returnobj = ekoAPICall.ekoCall(url,"","GET");
		log.info("url : "+url);

		JSONObject jsonObject = null;
		try {
			if (returnobj != null) {
				//log.info("Bene List resp : "+returnobj.toString());
				int status = (int) returnobj.get("status");
				if (status == 0 && returnobj.containsKey("data") /*&& !returnobj.isNull("data")*/) {
					JSONObject dataobj = (JSONObject) returnobj.get("data");
					JSONArray recipientlist = (JSONArray) dataobj.get("recipient_list");
					String Recipient_id_type = "";
					String account = "";
					String ifsc = "";


					//log.info("recipientlist" + recipientlist);
					for (int i = 0; i < recipientlist.size(); i++) {
						jsonObject = new JSONObject();
						JSONObject json_data = recipientlist.getJSONObject(i);
						int recipient_idint = (int) json_data.get("recipient_id");
						String recipient_id = String.valueOf(recipient_idint);
						Recipient_id_type = json_data.optString("recipient_id_type");
						String name = json_data.getString("recipient_name");
						String channel = json_data.getString("channel");
						account = json_data.getString("account");
						ifsc = json_data.getString("ifsc");
						String bank_name = json_data.getString("bank");
						mobile = json_data.getString("recipient_mobile");

						if(mobile==null || mobile.equalsIgnoreCase("null"))
							mobile="NA";

						jsonObject.put("RecipientId", recipient_id);
						jsonObject.put("RecipientIdType", Recipient_id_type==null?"NA":Recipient_id_type);
						jsonObject.put("BeneName", name);
						jsonObject.put("Account", account);
						jsonObject.put("Ifsc", ifsc);
						jsonObject.put("BankName", bank_name);
						jsonObject.put("BeneMobile", mobile);
						jsonObject.put("IMPS", "2");
						jsonObject.put("NEFT", "1");
						jsonObject.put("Channel", channel);
						jsonArray.add(jsonObject);

					}
				} else {
					jsonArray = null;
				}
			} else {
				jsonArray = null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error in getAllBeneficiary method of dmr_customer_dao class" + e.getMessage());

			return null;
		}
		return jsonArray;
	}

	public int fetchKycStatus(String senderId) {
		int kycStatus=3;
		try {
			EkoAPICall ekoAPICall=new EkoAPICall();
			String getcstmr = PropertyFile.ekocustomerurl + "mobile_number:" + senderId + "?initiator_id="+ PropertyFile.initiator_id + "";
			log.info("Find Sender Request : "+getcstmr);
			JSONObject obj = ekoAPICall.ekoCall(getcstmr,"","GET");
			if(obj!=null) {
				JSONObject data=obj.optJSONObject("data");
				if(data!=null)
					kycStatus=Integer.parseInt(data.getString("state"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return kycStatus;
	}

	public JSONObject resendOtp(String senderId,String AgentID) {

		JSONObject jsonObject=new JSONObject();

		String statusCode="1",message="";

		try {
			EkoDMTDao dao=new EkoDMTDao();
			EkoAPICall ekoAPICall=new EkoAPICall();
			String ekoUsrCode=dao.getEKoUserCode(AgentID);
			if(null== ekoUsrCode || "".equalsIgnoreCase(ekoUsrCode)) {

				jsonObject=new JSONObject();
				jsonObject.put("Status","1");
				jsonObject.put("message", "Invalid User code");
				return jsonObject;
			}
			String identityurl = PropertyFile.ekocustomerurl + "mobile_number:" + senderId + "/otp";
			String str = "initiator_id=" + PropertyFile.initiator_id+"&pipe=9&user_code="+ekoUsrCode;

			log.info("Resend OTP Request : "+identityurl+"?"+str);

			JSONObject resendOtp = ekoAPICall.ekoCall(identityurl, str,"POST");

			log.info("Resend OTP Response : "+resendOtp);

			if (resendOtp != null) {

				message = (String) resendOtp.get("message");
				int status = (int) resendOtp.get("status");

				if(status==0){
					statusCode="0";
					JSONObject data = (JSONObject) resendOtp.get("data");
					String otp_ref_id=data.optString("otp_ref_id");

					int updateStatus=dao.updateOTP(senderId, otp_ref_id);
					log.info("Resend Otp Update Status :: "+updateStatus);
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

	public JSONObject registerSender(String senderid,String senderName,String address,String dob,String AgentID){

		String message="",code="1";
		JSONObject jsonObject=new JSONObject();
		try{
			EkoDMTDao dao=new EkoDMTDao();
			EkoAPICall ekoAPICall= new EkoAPICall();
			String ekoUsrCode=dao.getEKoUserCode(AgentID);
			if(null== ekoUsrCode || "".equalsIgnoreCase(ekoUsrCode)) {

				jsonObject=new JSONObject();
				jsonObject.put("Status","1");
				jsonObject.put("message", "Invalid User code");
				return jsonObject;
			}
			JSONObject data = null;
			String identityurl = PropertyFile.ekocustomerurl + "mobile_number:" + senderid ;
			String str = "name=" + senderName + "&initiator_id=" + PropertyFile.initiator_id+"&user_code="+ekoUsrCode+
					"&pipe=9&residence_address={\"line\":\"ARPIT ENTERPRISES\",\"city\":\"Ghaziabad\",\"state\":\"Uttar Pradesh\",\"pincode\":\""+address+"\",\"district\":\"Ghaziabad\",\"area\":\"Govindpuram\"}&dob="+dob;

			log.info("Register Sender Request : "+identityurl+"?"+str);

			JSONObject identityobj = ekoAPICall.ekoCall(identityurl, str,"PUT");

			log.info("Register Sender Response : "+identityobj);

			if(identityobj!=null){
				message = (String) identityobj.get("message");
				int response_type_id = (int) identityobj.get("response_type_id");
				int response_status_id = (int) identityobj.get("response_status_id");
				int status = (int) identityobj.get("status");
				if (status == 0) {
					if (identityobj.get("data") != null)
						data = identityobj.optJSONObject("data");
					if (data.get("customer_id") != null)
						senderid = (String) data.get("customer_id");
					code="0";
					String otp_ref_id=data.optString("otp_ref_id");

					String updateStatus=dao.senderRegistration(senderid, senderName, address, dob, otp_ref_id);
					log.info("Sender Register Status :: "+updateStatus);

				}else if(status == 17){
					code="2";
				}else{
					code="1";
				}
				jsonObject.put("Status", code);
				jsonObject.put("message", message);
			}else{
				jsonObject.put("Status", code);
				jsonObject.put("message", "Unable to process your request. Please try Later");
			}


		}catch(Exception e){
			e.printStackTrace();

			jsonObject=new JSONObject();
			jsonObject.put("Status", code);
			jsonObject.put("message", "Unable to process your request. Please try Later");
		}

		return jsonObject;
	}

	public JSONObject verifySender(String customerId,String otp,String AgentID){

		String  message="",code="1";
		JSONObject jsonObject=new JSONObject();
		try{
			EkoAPICall ekoAPICall=new EkoAPICall();
			EkoDMTDao dao=new EkoDMTDao();
			String ekoUsrCode=dao.getEKoUserCode(AgentID);
			if(null== ekoUsrCode || "".equalsIgnoreCase(ekoUsrCode)) {

				jsonObject=new JSONObject();
				jsonObject.put("Status","1");
				jsonObject.put("message", "Invalid User code");
				return jsonObject;
			}

			String otpRefId=dao.getEKoOtpRefId(customerId);
			if(null== otpRefId || "".equalsIgnoreCase(otpRefId)) {

				jsonObject=new JSONObject();
				jsonObject.put("Status","1");
				jsonObject.put("message", "Invalid OTP Ref Id");
				return jsonObject;
			}

			String identityurl = PropertyFile.ekocustomerurl + "verification/otp:" + otp.trim();
			String str = "initiator_id=" + PropertyFile.initiator_id + "&id=" + customerId + "&id_type=mobile_number&user_code="+ekoUsrCode+"&pipe=9&otp_ref_id="+otpRefId;

			log.info("Verify Sender Request : "+identityurl+"?"+str);

			JSONObject obj = ekoAPICall.ekoCall(identityurl, str,"PUT");
			log.info("Verify Sender Response  : "+obj);
			if (obj != null) {
				message = (String) obj.get("message");
				int response_type_id = (int) obj.get("response_type_id");
				int response_status_id = (int) obj.get("response_status_id");
				int status = (int) obj.get("status");

				if(status==0){
					int varified = dao.setcustomervarifiedindicator(customerId);
					log.info("Sender verification :: "+varified);
					code="0";
					dao.setcustomervarifiedindicator(customerId);

				}else if(status==302){
					code="2";

				}else if(status==1419){

					JSONObject data=obj.optJSONObject("data");
					if(data!=null) {
						if(data.optString("reason")!=null)
							message=data.getString("reason");
					}

					code="2";
				}else if(status==303){

					String verificationurl = PropertyFile.ekocustomerurl + "mobile_number:" + customerId + "/otp";
					str = "initiator_id=" + PropertyFile.initiator_id+"&user_code="+ekoUsrCode;
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

	public JSONObject ekoTransaction(JSONObject jsonObject,String ipaddress,String agentID,String latitude,String longitude){

		String message="",agent_id="";
		JSONObject traninfomap = new JSONObject();
		try {

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

			int kycStatus=fetchKycStatus(customer_mobile);

			String tid="",tranrefid="",bankrrn="",tranactionStatus="1";


			String remark = jsonObject.getString("Remark");
			double amt = Double.parseDouble(totamt);

			EkoDMTDao trandao=new EkoDMTDao();

			String ekoUsrCode=trandao.getEKoUserCode(agentID);
			if(null== ekoUsrCode || "".equalsIgnoreCase(ekoUsrCode)) {

				jsonObject=new JSONObject();
				jsonObject.put("Status","1");
				jsonObject.put("message", "Invalid User code");
				return jsonObject;
			}

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
								"EKO", ipaddress, amt,recipient_id,channel,service,operator,kycStatus,ekoUsrCode,latitude,longitude);
						tranactionStatus=traninfomap.getString("Status");

						if("0".equalsIgnoreCase(tranactionStatus)){
							transactingamount=transactingamount+amt;
						}
						tid=traninfomap.optString("tid");
						tranrefid=traninfomap.optString("tranid");

						bankrrn=traninfomap.optString("BankRRN");

						message=traninfomap.getString("message");
						traninfomap.put("TxnAmount", transactingamount);
						traninfomap.put("tid", tid);
						traninfomap.put("tranid", tranrefid);
						traninfomap.put("BankRRN", bankrrn);
						traninfomap.put("Status", "0");
						traninfomap.put("message", message);
						return traninfomap;
					}
					else if(amt>splitAmount){

						if(amt%splitAmount==0){
							int count=(int) (amt/splitAmount);
							for(int i=0;i<count;i++){
								double partamt=splitAmount;

								traninfomap=getTransactionResult(remark, agent_id, 
										recepient_mobile, ifsc, remittername, recipient_name, bankname, customer_mobile, ben_account, 
										"EKO", ipaddress, partamt,recipient_id,channel,service,operator,kycStatus,ekoUsrCode,latitude,longitude);
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
										"EKO", ipaddress, partamt,recipient_id,channel,service,operator,kycStatus,ekoUsrCode,latitude,longitude);
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
										"EKO", ipaddress, remainingamount,recipient_id,channel,service,operator,kycStatus,ekoUsrCode,latitude,longitude);
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
			String recipient_id,String channel,String service,String oprtor,int kycStatus,String ekoUsrCode,String latitude,String longitude) {
		log.info("executing getTransactionResult method of DMRTransactionEKO class");
		JSONObject traninfomap = new JSONObject();

		String statusCode="1";

		try {

			EkoDMTDao trandao=new EkoDMTDao();
			String message=null;
			String IdNo="AE"+agentCode+CommonServices.dateTime();
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

			double amtlimit = amt;



			java.util.Date date = new java.util.Date();
			Timestamp timestamp1 = new Timestamp(date.getTime());


			String str = "recipient_id=" + recipient_id + "&amount=" + amtlimit + "&timestamp="
					+ timestamp1 + "&currency=INR&customer_id=" + customer_mobile + "&initiator_id="
					+ PropertyFile.initiator_id + "&client_ref_id=" + transactionId + "&state=1&channel="
					+ channel+"&pipe=9&user_code="+ekoUsrCode+"&latlong="+latitude+","+longitude;

			String url = PropertyFile.ekotranurl;

			log.info("Transaction Request : "+url+"?" + str);

			EkoAPICall ekoAPICall=new EkoAPICall();

			String reqString=url+"?" + str;

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
			}else if (TranResult.equalsIgnoreCase("inserted")) {

				if(null==remittername){
					remittername = "";
				}
				CommonServiceDao.updateATTRequestLog(transactionId, reqString);
				JSONObject jsonobj = ekoAPICall.ekoCall(url, str,"POST");



				if (jsonobj != null) {
					log.info("Transaction Response : "+jsonobj.toString());
					String apiresponse = jsonobj.toString();
					CommonServiceDao.updateATTResponseLog(transactionId, apiresponse);

					int Status = jsonobj.getInt("status");
					log.info("status::::"+Status);
					message =jsonobj.optString("message");
					if (Status == 0) {
						log.info("in status success block");
						JSONObject jsondata =jsonobj.getJSONObject("data");
						String transaction_Id = jsondata.getString("tid");
						bankRrn = jsondata.getString("bank_ref_num");
						String tx_status =jsondata.getString("tx_status");
						double tds=jsondata.optString("tds")!=null?Double.parseDouble(jsondata.optString("tds")):0;
						double fee=jsondata.optString("fee")!=null?Double.parseDouble(jsondata.optString("fee")):0;
						double commission=jsondata.optString("commission")!=null?Double.parseDouble(jsondata.optString("commission")):0;

						double netCharge=fee-commission+tds;

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
					} else if(Status==55) {
						JSONObject dataa = (JSONObject) jsonobj.get("data");
						message=dataa.get("reason")+"";

						trandao.updateStatusDMRTranEKO(customer_mobile,transactionId,
								"Failure", "", message,bankRrn,"1");
					}else {
						log.info(transactionId);
						trandao.updateStatusDMRTranEKO(customer_mobile,transactionId,
								"Failure", "", message,bankRrn,"1");
					}
					traninfomap.put("message", message);
				} else
				{

					traninfomap.put("message", "Transaction In Process.");
					trandao.updateStatusDMRTranEKO(customer_mobile,transactionId,
							"Pending", "", message,bankRrn,"1");
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

			}else if("icici".equalsIgnoreCase(TranResult)) {

				String maskAccount="";
				try {
					maskAccount = UtilityP.maskString(ben_account);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					maskAccount=ben_account;
				}

				DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
				DateFormat df1 = new SimpleDateFormat("HH:mm:ss");
				Date dateobj = new Date();
				String date1 = df.format(dateobj);
				String time1 = df1.format(dateobj);

				ICICIAPICall instantPayAPICall=new ICICIAPICall();

				JSONObject instantPayRequest=new JSONObject();
				if("1".equalsIgnoreCase(channel))
					instantPayRequest.put("sp_key", "RGS");
				else if(bankname.contains("ICICI BANK")) {
					instantPayRequest.put("sp_key", "TPA");
					ifsc="ICIC0000011";
				}else
					instantPayRequest.put("sp_key", "IFS");

				instantPayRequest.put("external_ref", transactionId);
				instantPayRequest.put("credit_account", ben_account);
				instantPayRequest.put("ifs_code", ifsc.toUpperCase());
				instantPayRequest.put("bene_name", UtilityP.replaceSpecialCharacterExcludingSpace(recipient_name));
				instantPayRequest.put("credit_amount",amtlimit );
				instantPayRequest.put("latitude", "28.6890384");
				instantPayRequest.put("longitude", "77.4758088");
				instantPayRequest.put("endpoint_ip", Ipaddress);
				instantPayRequest.put("alert_mobile", PropertyFile.IPAY_SMS_ALERT);
				instantPayRequest.put("alert_email", PropertyFile.IPAY_EMAIL_ALERT);
				instantPayRequest.put("remarks","Settlement" );

				CommonServiceDao.updateATTRequestLog(transactionId, instantPayRequest.toString());

				url=PropertyFile.MSMART_PAYOUT;

				JSONObject jsonobj =instantPayAPICall.getJSONobjectPost(url, instantPayRequest);
				if (jsonobj != null) {
					log.info("Transaction Response : "+jsonobj.toString());
					CommonServiceDao.updateATTResponseLog(transactionId, jsonobj.toString());

					String ipstatusCode="",orderid="";

					JSONObject jsondata =jsonobj.optJSONObject("data");
					if(jsondata!=null){
						ipstatusCode=jsondata.optString("status");
						message =  jsondata.getString("message");
						orderid=jsondata.getString("orderid");
						String transaction_Id=jsondata.getString("uniqueid");
						bankRrn = jsondata.optString("utrnumber");
					}
					if (ipstatusCode!=null && !"".equalsIgnoreCase(ipstatusCode)) {

						if ("SUCCESS".equalsIgnoreCase(ipstatusCode)) 
						{
							log.info("inside the success block");
							// String

							String result=trandao.updateStatusDMRTranEKO(customer_mobile,transactionId,
									"Success", orderid,message, bankRrn,"");

							log.info("Transaction Update status : "+result);
							message="Transaction is successful";
							statusCode="0";

							try {
								String sms="Dear Customer, You have received Rs."+amt+" in your account ending with "+maskAccount+" at "+time1+". (Ref:"+bankRrn+").";
								//String sms="Transaction Successful, TID: "+transactionId+", Amt:Rs."+amt+"Fees:1%, A/C:"+ben_account+", "+bankname+", "+date1+" "+time1+" Thanks, Soft Solution Zone Private Limited";
								SendSmsOnMobile.sendMobileSmsSMSZONE(customer_mobile, sms);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						} else if("FAILURE".equalsIgnoreCase(ipstatusCode)){

							String result=trandao.updateStatusDMRTranEKO(customer_mobile,transactionId,
									"Failure", orderid,message, bankRrn,"");

							statusCode="1";
							if("Insufficient Wallet Balance".equalsIgnoreCase(message))
								message="Service Provider Downtime";


						}else if("INITIATED".equalsIgnoreCase(ipstatusCode)){

							String result=trandao.updateStatusDMRTranEKO(customer_mobile,transactionId,
									"Initiated", orderid,message, bankRrn,"");

							if(message==null || "".equalsIgnoreCase(message)) {
								if("1".equalsIgnoreCase(channel))
									message="Transaction is successfully posted";
								else
									message="Transaction is successfully processed but the final response is awaited";

							}
							statusCode="3";


						}else{
							String result=trandao.updateStatusDMRTranEKO(customer_mobile,transactionId,
									"Pending", orderid,message, bankRrn,"");

							if(message==null || "".equalsIgnoreCase(message)) {

								if("1".equalsIgnoreCase(channel))
									message="Transaction is successfully posted";
								else
									message="Transaction is successfully processed but the final response is awaited";

							}
							statusCode="2";
						}

					} else {
						String result=trandao.updateStatusDMRTranEKO(customer_mobile,transactionId,
								"Pending", orderid,message, bankRrn,"");
						message="Transaction is successfully posted. Please check Status.";
						statusCode="2";
					}

				} else
				{

					message= "Transaction has been posted but final response is yet to be received";

					String result=trandao.updateStatusDMRTranEKO(customer_mobile,transactionId,
							"Pending", "",message, bankRrn,"");
					log.info("message from eko is:::"+message);
					statusCode="2";

				}


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
				traninfomap.put("message", message);

				log.info("RemitTransaction.execute()"+traninfomap);

				return traninfomap;


			}else{
				traninfomap.put("Status",statusCode);
				traninfomap.put("message",TranResult);
				return traninfomap;
			}

		} catch (Exception e) {
			log.error("Error in execute method of RemitTransaction class"+e.getMessage());
			e.printStackTrace();

			traninfomap.put("Status", "0");
			traninfomap.put("message", "We are unable to process your request. Please try later.");

		}
		return traninfomap; 
	}

	public JSONObject verifiAccount(String agentID,String acc_no,String ifsc,String bankCode,
			String senderId,String Ipaddress) {

		JSONObject accnameinfo = new JSONObject();
		try {

			EkoAPICall ekoAPICall=new EkoAPICall();
			EkoDMTDao dmtDao=new EkoDMTDao();

			String ekoUsrCode=dmtDao.getEKoUserCode(agentID);
			if(null== ekoUsrCode || "".equalsIgnoreCase(ekoUsrCode)) {

				accnameinfo=new JSONObject();
				accnameinfo.put("Status","1");
				accnameinfo.put("message", "Invalid User code");
				return accnameinfo;
			}

			String IdNo="AE"+agentID+CommonServices.dateTime();
			String transactionId=CommonServices.getAETranId();

			String ekostatus = "failure", accinfo = "",message="",code="1",tid="";

			//int kycStatus=fetchKycStatus(senderId);

			if (ifsc == null)
				ifsc = "";

			if (bankCode == null)
				bankCode = "";

			String tosaveifsc="", bene_name="",bank="",account="";

			//Fetch short code for eko by master id
			HashMap<String,String> bankDetails=dmtDao.getMasterBankStatus(bankCode);
			bankCode=bankDetails.get("short_code");
			tosaveifsc=bankDetails.get("ifsc");

			String service="DMR-E-ACCOUNT",operator="DMR-E-ACCOUNT";

			String TranResult = dmtDao.DMR_TRANSACTION_INSERT(IdNo, transactionId, agentID, service,operator, senderId, Ipaddress, "EKO", "", "Account verification", "IMPS", "", bankCode, tosaveifsc, acc_no, 1);

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
			}else if (TranResult.equalsIgnoreCase("inserted")) {

				log.info("In API block .................");


				String accdata = "initiator_id=" + PropertyFile.initiator_id + "&customer_id=" + senderId+"&user_code="+ekoUsrCode;
				if (!"".equalsIgnoreCase(ifsc)) 
				{
					accinfo = PropertyFile.ekoAccountVerify+"ifsc:" + ifsc + "/accounts/" + acc_no;
					tosaveifsc=ifsc;
				} else if (bankCode!=null && !"".equalsIgnoreCase(bankCode)) {
					accinfo = PropertyFile.ekoAccountVerify+"bank_code:" + bankCode+ "/accounts/" + acc_no;
				} else 
				{
					accinfo = PropertyFile.ekoAccountVerify+"bank_code:" + bankCode+ "/accounts/" + acc_no;
				}
				String reqString=accdata+"?"+accinfo;
				String respString="";
				log.info("reqString : " + reqString);

				CommonServiceDao.updateATTRequestLog(transactionId, reqString);

				JSONObject obj = ekoAPICall.ekoCall(accinfo, accdata,"POST");
				log.info("account name info" + obj);
				if (obj != null) {

					respString=obj.toString();
					CommonServiceDao.updateATTResponseLog(transactionId, respString);

					message =obj.getString("message");
					int status = obj.getInt("response_type_id");
					JSONObject invalid_params=obj.optJSONObject("invalid_params");
					if(invalid_params!=null && invalid_params.has("account"))
						message=invalid_params.optString("account");

					if (status == 61) {

						JSONObject dataacc = (JSONObject) obj.get("data");
						account = dataacc.optString("account");
						bank = dataacc.optString("bank");
						bene_name =dataacc.optString("recipient_name");
						String is_name_editable =dataacc.optString("is_name_editable");
						String is_Ifsc_required =dataacc.optString("is_Ifsc_required");
						double fee=dataacc.getString("fee")!=null?Double.parseDouble(dataacc.optString("fee")):0;
						// Add key for tid..
						tid =dataacc.optString("tid") ;
						accnameinfo.put("AccountNo", account);
						accnameinfo.put("BankName", bank);
						accnameinfo.put("BeneName", bene_name);
						accnameinfo.put("TxnId",tid);
						accnameinfo.put("BankCode",bankCode);
						accnameinfo.put("is_name_editable",is_name_editable);
						accnameinfo.put("is_Ifsc_required",is_Ifsc_required);
						accnameinfo.put("IFSC", tosaveifsc);

						if(message.contains("INVALID ACCOUNT DETAILS") || message.contains("Invalid Account Number"))
							code="1";//INVALID ACCOUNT DETAILS//Invalid Account Number
						else{
							code="0";
							ekostatus = "Success";
						}


					}else if(status==345) {

						JSONObject dataacc = (JSONObject) obj.get("data");
						account = acc_no;
						bene_name =dataacc.optString("recipient_name");
						String is_name_editable =dataacc.optString("is_name_editable");
						String is_Ifsc_required =dataacc.optString("is_Ifsc_required");
						// Add key for tid..
						accnameinfo.put("AccountNo", account);
						accnameinfo.put("BankName", bank);
						accnameinfo.put("BeneName", bene_name);
						accnameinfo.put("TxnId",transactionId);
						accnameinfo.put("BankCode",bankCode);
						accnameinfo.put("is_name_editable",is_name_editable);
						accnameinfo.put("is_Ifsc_required",is_Ifsc_required);
						accnameinfo.put("IFSC", tosaveifsc);

						code="0";
						ekostatus = "Success";


					}else if(status==55) {
						JSONObject dataa = (JSONObject) obj.get("data");
						message=dataa.get("reason")+"";
					}

					String updateinfo = dmtDao.updateStatusDMRTranEKO(senderId,transactionId,
							ekostatus, tid,"EKO","",String.valueOf(status));
					log.info("Account Verification update Status : " +updateinfo);
				}

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
			String ekoUsrCode=ekoDMTDao.getEKoUserCode(agentID);
			if(null== ekoUsrCode || "".equalsIgnoreCase(ekoUsrCode)) {

				jsonObject=new JSONObject();
				jsonObject.put("Status","1");
				jsonObject.put("message", "Invalid User code");
				return jsonObject;
			}

			String bankName="", verifymessage="",beneId="";

			//Fetch short code for eko by master id
			HashMap<String,String> bankDetails=ekoDMTDao.getMasterBankStatus(BankCode);
			BankCode=bankDetails.get("short_code");
			bankName=bankDetails.get("bank");


			String addbeneficiaryurl="";
			if(ifsc!=null && ifsc.trim().length()>=10)
				addbeneficiaryurl=PropertyFile.ekocustomerurl+"mobile_number:"+SenderId+"/recipients/acc_ifsc:"+account.trim()+"_"+ifsc.trim().toUpperCase()+"";
			else if(BankCode!=null && BankCode.length()>=3)
				addbeneficiaryurl=PropertyFile.ekocustomerurl+"mobile_number:"+SenderId+"/recipients/acc_bankcode:"+account.trim()+"_"+BankCode.trim().toUpperCase()+"";

			else
				addbeneficiaryurl=PropertyFile.ekocustomerurl+"mobile_number:"+SenderId+"/recipients/acc_ifsc:"+account.trim()+"_"+ifsc.trim().toUpperCase()+"";



			String str="recipient_name="+beneficiary_name+"&initiator_id="+PropertyFile.initiator_id+"&mobile="+beneficiary_mob_no.trim()+"&recipient_type=3&user_code="+ekoUsrCode;
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

			jsonObject.put("message", message);
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
			EkoDMTDao ekoDMTDao=new EkoDMTDao();
			String ekoUsrCode=ekoDMTDao.getEKoUserCode(AgentID);
			if(null== ekoUsrCode || "".equalsIgnoreCase(ekoUsrCode)) {

				jsonObject=new JSONObject();
				jsonObject.put("Status","1");
				jsonObject.put("message", "Invalid User code");
				return jsonObject;
			}
			String deletebeneficiaryurl=PropertyFile.ekocustomerurl+"mobile_number:"+senderMobile.trim()+"/recipients/recipient_id:"+recipient_id;
			String str="initiator_id="+PropertyFile.initiator_id+"&user_code="+ekoUsrCode;

			log.info("Delete Beneficiary Request "+deletebeneficiaryurl+"?"+str);

			EkoAPICall ekoAPICall=new EkoAPICall();
			JSONObject obj=ekoAPICall.ekoCall(deletebeneficiaryurl,str,"DELETE");

			log.info("Delete Beneficiary Response "+obj);

			log.info("----objobjobjobj---"+obj);
			if(obj.getString("status").equals("0"))
			{
				message =(String) obj.get("message");
				code="0";

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

	public JSONObject tranStatus(String senderId,String tranId,String AgentID) {

		JSONObject response=new JSONObject();

		try {
			String transactionstatus = "";
			EkoDMTDao ekoDMTDao=new EkoDMTDao();
			String ekoUsrCode=ekoDMTDao.getEKoUserCode(AgentID);
			if(null== ekoUsrCode || "".equalsIgnoreCase(ekoUsrCode)) {

				response=new JSONObject();
				response.put("Status","1");
				response.put("message", "Invalid User code");
				return response;
			}
			String ekoID=EkoDMTDao.getEKOTid(tranId);
			if(ekoID==null){

				response.put("Status", "1");
				response.put("message", "Transaction not found. Contact to Admin.");

			}else{

				String url = PropertyFile.ekotranurl + ekoID + "?initiator_id=" + PropertyFile.initiator_id+"&user_code="+ekoUsrCode;
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

	public JSONObject bankDetails(String bankCode,String ifsc,String AgentID) {

		JSONObject response=new JSONObject();

		try {

			EkoAPICall ekoAPICall=new EkoAPICall();
			EkoDMTDao ekoDMTDao=new EkoDMTDao();
			String ekoUsrCode=ekoDMTDao.getEKoUserCode(AgentID);
			if(null== ekoUsrCode || "".equalsIgnoreCase(ekoUsrCode)) {

				response=new JSONObject();
				response.put("Status","1");
				response.put("message", "Invalid User code");
				return response;
			}

			//Fetch short code for eko by master id
			HashMap<String,String> bankDetails=ekoDMTDao.getMasterBankStatus(bankCode);
			bankCode=bankDetails.get("short_code");
			String url = PropertyFile.ekobankDetails+ "?bank_code="+bankCode+"&initiator_id=" + PropertyFile.initiator_id+"&user_code="+ekoUsrCode;

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
