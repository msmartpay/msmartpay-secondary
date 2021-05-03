package com.msmart.service;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONException;

import com.msmart.api.controller.CyberHubController;
import com.msmart.api.controller.MRobotics;
import com.msmart.api.controller.MSmartController;
import com.msmart.api.controller.MarsController;
import com.msmart.api.controller.VastIndia;
import com.msmart.api.controller.VenFone;
import com.msmart.dao.MobileAppRechargeDao;
import com.msmart.dao.MobileDthRechargeDao;
import com.msmart.dao.UtilityServiceDao;
import com.ssz.api.SmartKindaAPI;

import net.sf.json.JSONObject;

public class PrepaidService {

	Logger logger=Logger.getLogger(PrepaidService.class);

	MobileAppRechargeDao mrd;
	JSONObject responseJson=new JSONObject();
	String responseCode="1";
	public JSONObject rechargeController(String agentID,String txn_key,String cleintIpaddress,
			String servicename,String OperatorCode,String mobileNo,String amtrechg,String requestId,HttpServletRequest request) throws JSONException
	{
		MobileAppRechargeDao mrd=new MobileAppRechargeDao();

		String OperatorTxnId="", response_reslt="",balance="",txnStatus="";
		String transactionId=CommonServices.getIdNo(agentID);
		logger.info("mobile recharge called");

		try
		{
			String txn_key_val=mrd.txn_key_Validation(txn_key, agentID);
			if(!txn_key_val.equalsIgnoreCase("Y"))
			{
				responseJson.put("response-code", responseCode);
				responseJson.put("response-message", "Unauthorized User Request!");
				return responseJson;
				
			}else{

				balance=mrd.getAgentBalance(agentID);
				logger.info("OperatorCode = "+OperatorCode+" No = "+mobileNo +" Amount = "+amtrechg +" Service = "+servicename);

				if (null ==OperatorCode || "".equalsIgnoreCase(OperatorCode) || mobileNo.equalsIgnoreCase("") || mobileNo==null ||amtrechg==null || amtrechg.equalsIgnoreCase("") )
				{
					responseJson.put("response-code", responseCode);
					responseJson.put("response-message", "Invalid Parameters. Please send Valid Details.");
					responseJson.put("balance", balance);
					return responseJson;
				}
				else if(Long.parseLong(amtrechg)<5)
				{
					responseJson.put("response-code", responseCode);
					responseJson.put("response-message", "Invalid recharge amount. plz enter valid amount.");
					responseJson.put("balance", balance);
					return responseJson;
				}else{

					MobileDthRechargeDao daoObj=new MobileDthRechargeDao();

					String ip=request.getRemoteHost();

					HashMap<String,String> resultMap=daoObj.getRechargeStatus(agentID,servicename,OperatorCode,amtrechg);
					String status=resultMap.get("status");
					String vendor=resultMap.get("vendor");
					String vendorCode=resultMap.get("vendorCode");
					String operator=resultMap.get("operator");

					if(status.equalsIgnoreCase("Ag_Sr_Blk")){
						response_reslt="This Operator is Not Available. Please Try Later.";
						txnStatus="Failure";
					}else if(status.equalsIgnoreCase("Op_Sr_Blk")){
						response_reslt="This Operator is Not Available. Please Try Later.";
						txnStatus="Failure";
					}else if(status.equalsIgnoreCase("insufficient_balance")){
						response_reslt="You have Insufficient Balance.";
						txnStatus="Failure";
					}else if(status.equalsIgnoreCase("NA")){
						response_reslt="Transaction Failed Due to Technical Error. Please Try Later.";
						txnStatus="Failure";
					}else if(status.equalsIgnoreCase("Y")){

						String update="";

						if(vendor.equalsIgnoreCase("NA"))
						{			
							logger.info("Vendor not available");
							response_reslt="Transaction Failed Due to Technical Error. Please Try Later.";
						}else{

							//Save Transaction history

							String TranResult=daoObj.insertRecordNewQuery(requestId,agentID,operator,mobileNo,Double.parseDouble(amtrechg),transactionId,servicename,ip,vendor,mobileNo);
							if(TranResult.equalsIgnoreCase("insufficient_balance")){
								response_reslt="You Do Not have Sufficient Balance.";
								txnStatus="Failure";
							}else if(TranResult.equalsIgnoreCase("notinserted")){
								response_reslt="Transaction Failed Due to Technical Error. Please Try Later.";
								txnStatus="Failure";
							}else if(TranResult.equalsIgnoreCase("duplicate")){
								response_reslt="Transaction Failed Due to Duplicate request.";
								txnStatus="Failure";
							}
							else if (TranResult.equalsIgnoreCase("commisson_error")){
								response_reslt="Transaction Failed as Your Commission is Not Set Properly.";
								txnStatus="Failure";
							}
							else if (TranResult.equalsIgnoreCase("TimeValidation"))
							{
								response_reslt="Repeat Transaction. Please try after One Hour.";
								txnStatus="Failure";
							}
							else if(TranResult.equalsIgnoreCase("inserted")){
								String resultCode="",vendorSessionId="";

								if(vendor.equalsIgnoreCase("SELF") )
								{
									resultCode="00";
									txnStatus="Success";
									update=daoObj.updateStatusTranEGEN(transactionId,txnStatus,agentID,resultCode,vendorSessionId,OperatorTxnId,"","");
									response_reslt="Your connection Number is Recharged Successfully ";

								}else if(vendor.equalsIgnoreCase("MSMART")){

									String ERROR="";

									MSmartController rechargeCl=new MSmartController();
									String Status="";

									HashMap<String,String> mapResult=rechargeCl.callWinusRecharge(amtrechg,mobileNo,vendorCode,transactionId,servicename);

									Status=mapResult.get("Status");
									String refId=mapResult.get("refId"); 
									if(refId==null || refId.length()<1)
										refId=requestId;

									String OrderNo=mapResult.get("OrderNo");
									OperatorTxnId=mapResult.get("OperatorTxnId"); 


									if(Status.equalsIgnoreCase("Success"))
									{
										ERROR="01";
										responseCode="0";
										response_reslt="Your connection Number is Recharged Successfully ";
										txnStatus="Success";
										logger.info(" Updateing start on Mars Success >>>>>>>>>>>>>>>>>  ");
										update=daoObj.updateStatusTranEGEN(transactionId,txnStatus,agentID,resultCode,OrderNo,OperatorTxnId,"","");
										logger.info(" Updateing Stop on Mars Success >>>>>>>>>>>>>>>  "+update);


									}else if(Status.equalsIgnoreCase("Pending"))
									{
										responseCode="0";
										response_reslt="Your connection Number is Recharged Successfully. Please check status.";
										ERROR="01";
										txnStatus="Pending";
										logger.info(" Updateing start on Mars Success >>>>>>>>>>>>>>>>>  ");
										update=daoObj.updateStatusTranEGEN(transactionId,txnStatus,agentID,ERROR,OrderNo,OperatorTxnId,"","");
										logger.info(" Updateing Stop on Mars Success >>>>>>>>>>>>>>>  "+update);


									}else if(Status.equalsIgnoreCase("Failure")||Status.equalsIgnoreCase("Error"))
									{
										ERROR="02";
										txnStatus="Failure";
										update=daoObj.updateStatusTranEGEN(transactionId,txnStatus,agentID,ERROR,OrderNo,OperatorTxnId,"","");
										response_reslt="Recharge request failed.";

									}else 
									{
										//											Fail
										ERROR="01";
										responseCode="0";
										response_reslt="Your connection Number is Recharged Successfully. Please check status. ";
										txnStatus="Pending";
										update=daoObj.updateStatusTranEGEN(transactionId,txnStatus,agentID,ERROR,OrderNo,OperatorTxnId,"","");
									}



								}else if(vendor.equalsIgnoreCase("SmartKinda")){

									//Call API for Recharge

									JSONObject respObj=SmartKindaAPI.sendAPIRechargeRequest(vendorCode, amtrechg, mobileNo, transactionId, ip, servicename);

									logger.info("Response :: "+respObj);
									if(respObj==null){
										response_reslt= PropertyFile.TECHNICAL_FAILURE;
									}else{

										if(respObj.containsKey("Api_txn_id"))
											vendorSessionId=respObj.get("Api_txn_id")+"";

										String statusCode=respObj.get("statuscode")+"";
										if("0".equalsIgnoreCase(statusCode)){
											responseCode="0";
											txnStatus="Success";
											update=daoObj.updateStatusTranEGEN(transactionId,txnStatus,agentID,resultCode,vendorSessionId,vendor,"","");
											response_reslt="Your connection Number is Recharged Successfully ";
										}else if("1".equalsIgnoreCase(statusCode)){

											txnStatus="Failure";
											update=daoObj.updateStatusTranEGEN(transactionId,txnStatus,agentID,resultCode,vendorSessionId,vendor,"","");
											response_reslt="Recharge request failed.";
										}else{
											responseCode="0";
											response_reslt="Your connection Number is Recharged Successfully. Please check status. ";

											txnStatus="Pending";
											update=daoObj.updateStatusTranEGEN(transactionId,txnStatus,agentID,resultCode,vendorSessionId,vendor,"","");
										}

									}


								}else if(vendor.equalsIgnoreCase("MARS")){

									MarsController marsCl=new MarsController();
									String Status="",ERROR="";

									HashMap<String,String> mapResult=marsCl.sndMarsRequest(mobileNo,amtrechg,transactionId,vendorCode);

									logger.info("Result of WEB Mars recharge is : "+mapResult);	

									Status=mapResult.get("Status");
									String marsRefId=mapResult.get("marsRefId"); 
									if(marsRefId!=null)
										marsRefId=marsRefId.trim();
									else
										marsRefId="";

									String InputXML=mapResult.get("InputXML");
									String ResoponseXML=mapResult.get("ResoponseXML");
									if(Status.equalsIgnoreCase("Success"))
									{
										ERROR="01";
										txnStatus="Success";
										logger.info(" Updateing start on Mars Success >>>>>>>>>>>>>>>>>  ");
										update=daoObj.updateStatusTranEGEN(transactionId,txnStatus,agentID,ERROR,marsRefId,vendor,InputXML,ResoponseXML);
										responseCode="0";
										response_reslt="Your connection Number is Recharged Successfully ";


									}else if(Status.equalsIgnoreCase("Accepted"))
									{
										ERROR="01";
										txnStatus="Success";
										logger.info(" Updateing start on Mars Success >>>>>>>>>>>>>>>>>  ");
										update=daoObj.updateStatusTranEGEN(transactionId,txnStatus,agentID,ERROR,marsRefId,vendor,InputXML,ResoponseXML);
										responseCode="0";
										response_reslt="Your connection Number is Recharged Successfully ";


									}
									else if(Status.equalsIgnoreCase("Invalid")|| Status.equalsIgnoreCase("Pending")|| Status.equalsIgnoreCase("RESPWAIT") || Status.equalsIgnoreCase("SUSPENSE"))
									{
										ERROR="01";
										txnStatus="Pending";
										update=daoObj.updateStatusTranEGEN(transactionId,txnStatus,agentID,ERROR,marsRefId,vendor,InputXML,ResoponseXML);
										responseCode="0";
										response_reslt="Your connection Number is Recharged Successfully ";
									}
									else if(Status.equalsIgnoreCase("Error"))
									{
										ERROR="02";
										txnStatus="Failure";
										update=daoObj.updateStatusTranEGEN(transactionId,txnStatus,agentID,ERROR,marsRefId,vendor,InputXML,ResoponseXML);
										if(update.equalsIgnoreCase("error")){
											response_reslt= "Recharge is Failed but Transaction Status is not updatded due to technical problem.";
										}else{
											response_reslt= "Transaction Failed. Your Transaction has been Refunded.";
										}
									}
									else 
									{
										//											Fail
										ERROR="01";
										txnStatus="Pending";
										update=daoObj.updateStatusTranEGEN(transactionId,txnStatus,agentID,ERROR,marsRefId,vendor,InputXML,ResoponseXML);
										if(update.equalsIgnoreCase("error")){
											response_reslt= "Your transaction is Processing but Transaction Status is not updatded due to technical problem.";
										}else{
											response_reslt= "Your transaction Submited. Please wait for 24 hours and Do Not repeat the Transaction.";
										}
									}


								}else if(vendor.equalsIgnoreCase("CyberHub")){

									String InputXML="",ResoponseXML="",ERROR;

									CyberHubController rechargeCl=new CyberHubController();
									String Status="";

									HashMap<String,String> mapResult=rechargeCl.callRecharge(amtrechg,mobileNo,vendorCode,transactionId,servicename);

									Status=mapResult.get("Status");
									String refId=mapResult.get("refId"); 
									if(refId==null || refId.length()<1)
										refId=requestId;

									OperatorTxnId=mapResult.get("OperatorTxnId");  
									InputXML=mapResult.get("InputXML");
									ResoponseXML=mapResult.get("ResoponseXML");

									try {
										CommonServices.updateEKOLogs(transactionId, ResoponseXML, agentID);
									} catch (Exception e) {
										// TODO: handle exception
									}

									if(Status.equalsIgnoreCase("Success"))
									{
										ERROR="01";
										responseCode="0";
										response_reslt="Your connection Number is Recharged Successfully ";
										txnStatus="Success";
										logger.info(" Updateing start on Mars Success >>>>>>>>>>>>>>>>>  ");
										update=daoObj.updateStatusTranEGEN(transactionId,txnStatus,agentID,resultCode,OperatorTxnId,vendor,InputXML,"");
										logger.info(" Updateing Stop on Mars Success >>>>>>>>>>>>>>>  "+update);


									}else if(Status.equalsIgnoreCase("Pending"))
									{
										responseCode="0";
										response_reslt="Your connection Number is Recharged Successfully. Please check status.";
										ERROR="01";
										txnStatus="Pending";
										logger.info(" Updateing start on Mars Success >>>>>>>>>>>>>>>>>  ");
										update=daoObj.updateStatusTranEGEN(transactionId,txnStatus,agentID,ERROR,OperatorTxnId,vendor,InputXML,"");
										logger.info(" Updateing Stop on Mars Success >>>>>>>>>>>>>>>  "+update);


									}else if(Status.equalsIgnoreCase("Failure")||Status.equalsIgnoreCase("FREQUENT") ||Status.equalsIgnoreCase("REFUND"))
									{
										ERROR="02";
										txnStatus="Failure";
										update=daoObj.updateStatusTranEGEN(transactionId,txnStatus,agentID,ERROR,OperatorTxnId,vendor,InputXML,"");
										response_reslt="Recharge request failed.";

									}else 
									{
										//Fail
										ERROR="01";
										responseCode="0";
										response_reslt="Your connection Number is Recharged Successfully. Please check status. ";
										txnStatus="Pending";
										update=daoObj.updateStatusTranEGEN(transactionId,txnStatus,agentID,ERROR,OperatorTxnId,vendor,InputXML,"");
									}

								}else if(vendor.equalsIgnoreCase("Venfone") || vendor.equalsIgnoreCase("Venfone2")){

									String InputXML="",ResoponseXML="",ERROR;

									VenFone venFone=new VenFone();
									String Status="";

									HashMap<String,String> mapResult=venFone.callRecharge(amtrechg,mobileNo,vendorCode,transactionId,OperatorCode);

									Status=mapResult.get("Status");
									String refId=mapResult.get("refId"); 
									if(refId==null || refId.length()<1)
										refId=requestId;

									ResoponseXML=mapResult.get("ResoponseXML");
									String OrderNo=mapResult.get("OrderNo");
									OperatorTxnId=mapResult.get("OperatorTxnId"); 

									try {
										CommonServices.updateEKOLogs(transactionId, ResoponseXML, agentID);
									} catch (Exception e) {
										// TODO: handle exception
									}

									if(Status.equalsIgnoreCase("Success"))
									{
										ERROR="01";
										responseCode="0";
										response_reslt="Your connection Number is Recharged Successfully ";
										txnStatus="Success";
										logger.info(" Updateing start on Mars Success >>>>>>>>>>>>>>>>>  ");
										update=daoObj.updateStatusTranEGEN(transactionId,txnStatus,agentID,resultCode,OrderNo,OperatorTxnId,InputXML,"");
										logger.info(" Updateing Stop on Mars Success >>>>>>>>>>>>>>>  "+update);


									}else if(Status.equalsIgnoreCase("Pending"))
									{
										responseCode="0";
										response_reslt="Your connection Number is Recharged Successfully. Please check status.";
										ERROR="01";
										txnStatus="Pending";
										logger.info(" Updateing start on Mars Success >>>>>>>>>>>>>>>>>  ");
										update=daoObj.updateStatusTranEGEN(transactionId,txnStatus,agentID,ERROR,OrderNo,OperatorTxnId,InputXML,"");
										logger.info(" Updateing Stop on Mars Success >>>>>>>>>>>>>>>  "+update);


									}else if(Status.equalsIgnoreCase("Failure"))
									{
										ERROR="02";
										txnStatus="Failure";
										update=daoObj.updateStatusTranEGEN(transactionId,txnStatus,agentID,ERROR,OrderNo,OperatorTxnId,InputXML,"");
										response_reslt="Recharge request failed.";

									}else 
									{
										//Fail
										ERROR="01";
										responseCode="0";
										response_reslt="Your connection Number is Recharged Successfully. Please check status. ";
										txnStatus="Pending";
										update=daoObj.updateStatusTranEGEN(transactionId,txnStatus,agentID,ERROR,OrderNo,OperatorTxnId,InputXML,"");
									}

								}else if(vendor.equalsIgnoreCase("mrobotics") || vendor.equalsIgnoreCase("jiolite") || vendor.equalsIgnoreCase("airtelthanks")
										|| vendor.equalsIgnoreCase("mrobotics2")){

									String InputXML="",ERROR;

									MRobotics mRobotics=new MRobotics();
									String Status="";

									HashMap<String,String> mapResult=mRobotics.callRecharge(amtrechg,mobileNo,vendorCode,transactionId,OperatorCode);

									Status=mapResult.get("Status");

									OperatorTxnId=mapResult.get("OperatorTxnId"); 
									if(Status.equalsIgnoreCase("Success"))
									{
										ERROR="01";
										responseCode="0";
										response_reslt="Your connection Number is Recharged Successfully ";
										txnStatus="Success";
										logger.info(" Updateing start on Mars Success >>>>>>>>>>>>>>>>>  ");
										update=daoObj.updateStatusTranEGEN(transactionId,txnStatus,agentID,resultCode,"",OperatorTxnId,InputXML,"");
										logger.info(" Updateing Stop on Mars Success >>>>>>>>>>>>>>>  "+update);


									}else if(Status.equalsIgnoreCase("Pending"))
									{
										responseCode="0";
										response_reslt="Your connection Number is Recharged Successfully. Please check status.";
										ERROR="01";
										txnStatus="Pending";
										logger.info(" Updateing start on Mars Success >>>>>>>>>>>>>>>>>  ");
										update=daoObj.updateStatusTranEGEN(transactionId,txnStatus,agentID,ERROR,"",OperatorTxnId,InputXML,"");
										logger.info(" Updateing Stop on Mars Success >>>>>>>>>>>>>>>  "+update);


									}else if(Status.equalsIgnoreCase("Failure"))
									{
										ERROR="02";
										txnStatus="Failure";
										update=daoObj.updateStatusTranEGEN(transactionId,txnStatus,agentID,ERROR,"",OperatorTxnId,InputXML,"");
										response_reslt="Recharge request failed.";

									}else 
									{
										//Fail
										ERROR="01";
										responseCode="0";
										response_reslt="Your connection Number is Recharged Successfully. Please check status. ";
										txnStatus="Pending";
										update=daoObj.updateStatusTranEGEN(transactionId,txnStatus,agentID,ERROR,"",OperatorTxnId,InputXML,"");
									}


								}else if(vendor.equalsIgnoreCase("VastIndia")){

									String InputXML="",ERROR;

									VastIndia vastIndia=new VastIndia();
									String Status="";

									HashMap<String,String> mapResult=vastIndia.callRecharge(amtrechg,mobileNo,vendorCode,transactionId,OperatorCode);

									Status=mapResult.get("Status");

									OperatorTxnId=mapResult.get("OperatorTxnId"); 
									if(Status.equalsIgnoreCase("Success"))
									{
										ERROR="01";
										responseCode="0";
										response_reslt="Your connection Number is Recharged Successfully ";
										txnStatus="Success";
										logger.info(" Updateing start on Mars Success >>>>>>>>>>>>>>>>>  ");
										update=daoObj.updateStatusTranEGEN(transactionId,txnStatus,agentID,resultCode,"",OperatorTxnId,InputXML,"");
										logger.info(" Updateing Stop on Mars Success >>>>>>>>>>>>>>>  "+update);


									}else if(Status.equalsIgnoreCase("Pending"))
									{
										responseCode="0";
										response_reslt="Your connection Number is Recharged Successfully. Please check status.";
										ERROR="01";
										txnStatus="Pending";
										logger.info(" Updateing start on Mars Success >>>>>>>>>>>>>>>>>  ");
										update=daoObj.updateStatusTranEGEN(transactionId,txnStatus,agentID,ERROR,"",OperatorTxnId,InputXML,"");
										logger.info(" Updateing Stop on Mars Success >>>>>>>>>>>>>>>  "+update);


									}else if(Status.equalsIgnoreCase("Failure"))
									{
										ERROR="02";
										txnStatus="Failure";
										update=daoObj.updateStatusTranEGEN(transactionId,txnStatus,agentID,ERROR,"",OperatorTxnId,InputXML,"");
										response_reslt="Recharge request failed.";

									}else 
									{
										//Fail
										ERROR="01";
										responseCode="0";
										response_reslt="Your connection Number is Recharged Successfully. Please check status. ";
										txnStatus="Pending";
										update=daoObj.updateStatusTranEGEN(transactionId,txnStatus,agentID,ERROR,"",OperatorTxnId,InputXML,"");
									}


								}else{
									response_reslt="Transaction Failed as Vendor is Not Set Properly.";
								}

							}else{
								response_reslt="Transaction Failed Due to Technical Error. Please Try Later.";
							}

						}

					}else{
						response_reslt="Transaction Failed Due to Technical Error. Please Try Later.";
					}

				}
				if(OperatorTxnId==null || "null".equalsIgnoreCase(OperatorTxnId))
					OperatorTxnId="NA";

				responseJson.put("response-code", responseCode);
				responseJson.put("response-message", response_reslt);
				responseJson.put("balance", balance);
				responseJson.put("Api_txn_id", transactionId);
				responseJson.put("OperatorTxnId", OperatorTxnId);
				responseJson.put("mobile", mobileNo);
				responseJson.put("amount", amtrechg);
				responseJson.put("request_id", requestId);
				responseJson.put("txnStatus", txnStatus);

				logger.info("responseJson2 :: "+responseJson);

				return responseJson;
			}


		}catch (Exception ex) {
			ex.printStackTrace();
			response_reslt= "Transaction under process. Please check status.";
			responseJson.put("response-code", "1");
			responseJson.put("response-message", response_reslt);
			responseJson.put("balance", balance);
			responseJson.put("Api_txn_id", "");
			responseJson.put("OperatorTxnId", "");
			responseJson.put("mobile", mobileNo);
			responseJson.put("amount", amtrechg);
			responseJson.put("request_id", requestId);
			return responseJson;
		}
	}

	public void venusCallback(String status,String OperatorTxnID,String OrderNo,String refId) {

		try {
			if(status!=null && !status.equalsIgnoreCase(""))
			{
				String ERROR="";
				String updateStatus="";
				String checkVendor="Venus",ResoponseXML="";
				String InputXML="";

				MobileDthRechargeDao daoObj=new MobileDthRechargeDao();

				HashMap<String, String> map=daoObj.getTranDetails(OperatorTxnID,OrderNo,refId);
				logger.info("Recharge getTranDetails map .................................."+map);
				String agentID="";
				if(map!=null){
					String transactionId=(String)map.get("tran_id");
					agentID=(String)map.get("user_id");
					String service=null;
					if(map.get("service")==null){
						service=(String)map.get("service");
					}
					else
					{
						service="";
					}
					logger.info("Venus Response page work start ..................................");

					if(service.equalsIgnoreCase("BillPay")){
						UtilityServiceDao uobj=new UtilityServiceDao();
						//(String tranId,String status,String agentID,String rechargeRefId,String operatorId)
						updateStatus=uobj.updateStatusBillPayTran(transactionId,status,agentID,OrderNo,OperatorTxnID);
					}else
						updateStatus=daoObj.updateStatusTranEGEN(transactionId,status,agentID,ERROR,OperatorTxnID,OperatorTxnID,InputXML,ResoponseXML);

					logger.info("Sending Response work start ..................................");

					logger.info("Sending Response work End ..................................");
				}else{
					logger.info("Transaction not updated  .................................. "+updateStatus);
					logger.info("Venus page work Stop .................................. "+updateStatus);


				}

				agentID=null;




			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
