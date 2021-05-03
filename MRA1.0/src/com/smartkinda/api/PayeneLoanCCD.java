package com.smartkinda.api;

import org.apache.log4j.Logger;

import com.msmart.service.PropertyFile;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class PayeneLoanCCD {

	Logger log=Logger.getLogger(PayeneLoanCCD.class);

	public JSONObject findLoanCCDSender(JSONObject reqObj,String senderId,String accountType) {

		JSONObject responseJson=null;

		try {

			String findSenderUrl=PropertyFile.SmartKindaURL+"FindSender";

			SszAPICall sApiCall=new SszAPICall();
			responseJson=sApiCall.getJSONobjectPost(findSenderUrl, reqObj);
			if(responseJson!=null && "0".equalsIgnoreCase(responseJson.getString("Status"))){

				JSONArray BeneList=responseJson.getJSONArray("BeneList");
				JSONArray newBeneList=new JSONArray();
				int size=BeneList.size();
				if(BeneList!=null && size>0){
					for (int i = 0; i < size; i++) {
						JSONObject bank=BeneList.getJSONObject(i);

						String recipientId=bank.getString("RecipientId");

						if(SSZDao.validateLoanCCDBeneficiary(senderId, accountType, recipientId)){
							newBeneList.add(bank);
						}
					}
					responseJson.put("BeneList", newBeneList);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			responseJson=new JSONObject();
			responseJson.put("Status","1");
			responseJson.put("message", "Unable to process your request. Please try later.");
		}
		return responseJson;
	}

	public JSONObject payLoanAndCCDPayment(JSONObject reqObj,String Ipaddress,String agentId){

		JSONObject respObj=new JSONObject();

		try {

			String senderId=reqObj.getString("SenderId");
			String SenderName = reqObj.getString("SenderName");
			String BankAccount=reqObj.getString("BankAccount");
			String BankName=reqObj.getString("BankName");
			String IFSC=reqObj.getString("IFSC");
			String TxnAmount=reqObj.optString("TxnAmount");
			String BeneName=reqObj.getString("BeneName");
			reqObj.put("BeneMobile", senderId);
			String accountType=reqObj.getString("Type");
			String remark = reqObj.getString("Remark");
			String add = reqObj.getString("Add");
			
			if(IFSC!=null && IFSC.length()<11)
				reqObj.put("BankCode", IFSC);
			
			String beneId=null;

			SszAPICall sApiCall=new SszAPICall();
			SSZDao dao=new SSZDao();

			boolean addStatus=false;
			if("Y".equalsIgnoreCase(add)){
				String findSenderUrl=PropertyFile.SmartKindaURL+"AddBeneAfterVerify";

				respObj=sApiCall.getJSONobjectPost(findSenderUrl, reqObj);
				if(respObj!=null){

					String respStatus=respObj.getString("Status");
					if("0".equalsIgnoreCase(respStatus)){

						beneId=respObj.getString("BeneId");
						
						addStatus=true;
						
						try {
							String addBeneStatus=dao.addBeneficiary(BeneName, senderId, BankName, IFSC, BankAccount, senderId, beneId);
							log.info("addBeneStatus : "+addBeneStatus);

							if("success".equalsIgnoreCase(addBeneStatus) || "updated".equalsIgnoreCase(addBeneStatus)){
								String addSpecialBeneStatus=dao.addSpecialBeneficiary(accountType, senderId, beneId);

								log.info("addSpecialBeneStatus : "+addSpecialBeneStatus);

								respObj.put("message", "Account Added Successfully");
							}

						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
					}
					
				}else{
					respObj = new JSONObject();
					respObj.put("message", "Unable to process your request. Please Try later.");
					respObj.put("Status", "1");
				}
				
				
			}else{
				beneId=reqObj.getString("BeneId");
			}
			
			if(beneId!=null && beneId.length()>1){
				SszDMT payeneDMT=new SszDMT();
				
				JSONObject transactionObject=new JSONObject();
				
				transactionObject.put("TxnAmount", TxnAmount);
				transactionObject.put("BeneAccount", BankAccount);
				transactionObject.put("Ifsc", IFSC);
				transactionObject.put("SenderId", senderId);
				transactionObject.put("SenderName", SenderName);
				transactionObject.put("BankName", BankName);
				transactionObject.put("BankCode", IFSC);
				transactionObject.put("BeneName", BeneName);
				transactionObject.put("BeneMobile", senderId);
				transactionObject.put("Remark", remark);
				transactionObject.put("TxnType", "1");
				transactionObject.put("RecipientId", beneId);
				
				SszAPICall.setSKDealerId(transactionObject);
				
				respObj=payeneDMT.ekoTransaction(transactionObject,Ipaddress,agentId,senderId);
			}else{
				respObj.put("message", "Unable to process your request. Please Try later.");
				respObj.put("Status", "1");
			}

		}catch(Exception e){
			e.printStackTrace();
			respObj = new JSONObject();
			respObj.put("message", "Unable to process your request. Please Try later.");
			respObj.put("Status", "1");
		}

		return respObj;

	}

	public JSONObject loanCCDBankList(JSONObject reqObj) {

		JSONObject respObj=new JSONObject();

		try {

			SSZDao smartKindaDao=new SSZDao();

			JSONArray bankList=smartKindaDao.loanCCDBankList(reqObj.getString("Type"));
			if(bankList!=null && bankList.size()>0){
				respObj.put("BankList", bankList);
				respObj.put("message", "Success");
				respObj.put("Status", "0");
			}else{
				respObj.put("message", "Bank list not found");
				respObj.put("Status", "1");
			}

		} catch (Exception e) {
			e.printStackTrace();
			respObj = new JSONObject();
			respObj.put("message", "Unable to process your request. Please Try later.");
			respObj.put("Status", "1");
		}
		return respObj;
	}


}
