package com.msmart.service;

import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

import com.msmart.api.controller.MRobotics;
import com.msmart.api.controller.SmartKindaBillpay;
import com.msmart.api.controller.MSmartController;
import com.msmart.dao.CommonServiceDao;
import com.msmart.dao.MobileAppRechargeDao;
import com.msmart.dao.UtilityServiceDao;
import com.ssz.api.SmartKindaAPI;

import net.sf.json.JSONObject;

public class PostpaidService {

	Logger logger=Logger.getLogger(PostpaidService.class);


	public JSONObject newpostpaidService(HttpServletRequest request,String agentID,String txn_key,String queryString,String OP,
			String OPName,String CN,String AMT,String reqType,String AD1,String AD2,String AD3,String AD4,String txnId,
			String referenceId,String service){


		JSONObject responseJson=new JSONObject();
		String responseCode="0";
		try
		{
			MobileAppRechargeDao mrd=new MobileAppRechargeDao();
			String txn_key_val=mrd.txn_key_Validation(txn_key, agentID);
			if(!txn_key_val.equalsIgnoreCase("Y"))
			{
				responseJson.put("response-code", responseCode);
				responseJson.put("response-message", "Invalid request!");
				return responseJson;
			}

			if("ViewBill".equalsIgnoreCase(reqType))
			{
				logger.info("Bill Pay operator       MobileAPP : "+OP);
				logger.info("Bill Pay Number         MobileAPP : "+CN);
				logger.info("Bill Pay AD1    MobileAPP : "+AD1);
				logger.info("Bill Pay AD2    MobileAPP : "+AD2);
				logger.info("Bill Pay AD3    MobileAPP : "+AD3);
				logger.info("Bill Pay AD4    MobileAPP : "+AD4);

				logger.info("Bill Pay AD4    MobileAPP : "+AD4);
				UtilityServiceDao utilityServiceDao=new UtilityServiceDao();
				HashMap<String,String> resultMap=utilityServiceDao.getRechargeStatusOfBillpay(agentID,"Billpay",OP,AMT);
				System.out.println("Billpay Operator Status : "+resultMap);
				String srviceStatus=resultMap.get("status");
				String vendor= resultMap.get("vendor");
				String vendorCode= resultMap.get("opCode");
				HashMap<String,String> viewBillMap=null;
				if("Y".equalsIgnoreCase(srviceStatus)) {
					MSmartController rechargeCl=new MSmartController();
					if("SmartKinda".equalsIgnoreCase(vendor)) {
						viewBillMap=SmartKindaBillpay.viewBillpayRequest(txnId, vendorCode, CN,AMT, AD1, AD2, AD3, AD4,referenceId);
					}else if("MSMART".equalsIgnoreCase(vendor)) {
						viewBillMap=rechargeCl.callWinusFetchBillpay(CN, AD4, vendorCode, txnId, service, AD1);
					}else {
						viewBillMap=rechargeCl.callWinusFetchBillpay(CN, AD4, vendorCode, txnId, service, AD1);
					}

					String viewStatus=viewBillMap.get("Status");
					if("Success".equalsIgnoreCase(viewStatus)){
						responseJson.put("response-code", "0");
						responseJson.put("response-message", viewBillMap.get("response-message"));
						responseJson.put("dueamount", viewBillMap.get("dueamount"));
						responseJson.put("billdate", viewBillMap.get("billdate"));
						responseJson.put("duedate", viewBillMap.get("duedate"));
						responseJson.put("customername", viewBillMap.get("customername"));
						responseJson.put("reference_id", viewBillMap.get("reference_id"));
						responseJson.put("REQUEST_ID", txnId);
					}else{
						responseJson.put("response-code", "1");
						responseJson.put("response-message", viewBillMap.get("response-message"));
						responseJson.put("REQUEST_ID", txnId);
					}

				}else if(srviceStatus.equalsIgnoreCase("Ag_Sr_Blk")){
					responseJson.put("response-message", "You Are Not Authorized To Use This Service. Please Contact Your Channel Partner.");

				}else if(srviceStatus.equalsIgnoreCase("invalid_operator"))
				{
					responseJson.put("response-message", "Invalid operator.");
				}else if(srviceStatus.equalsIgnoreCase("Op_Sr_Blk")){
					responseJson.put("response-message", "This Operator is Not Available. Please Try Later.");
				}else if(srviceStatus.equalsIgnoreCase("insufficient_balance")){
					responseJson.put("response-message", "You have Insufficient Balance.");
				}else if(srviceStatus.equalsIgnoreCase("NA")){
					responseJson.put("response-message", "Transaction Failed Due to Technical Error. Please Try Later.");
				}
				else {
					responseJson.put("response-message", "Transaction Failed Due to Technical Error. Please Try Later.");
				}

				return responseJson;
			}else{


				logger.info("Bill Pay operator Code       MobileAPP : "+OP);
				logger.info("Bill Pay operator       MobileAPP : "+OPName);
				logger.info("Bill Pay Number         MobileAPP : "+CN);
				logger.info("Bill Pay Amount         MobileAPP : "+AMT);

				UtilityServiceDao  utilityServiceDao=new UtilityServiceDao();

				HashMap<String,String> resultMap=utilityServiceDao.getRechargeStatusOfBillpay(agentID,"Billpay",OP,AMT);
				System.out.println("Billpay Operator Status : "+resultMap);
				String status=resultMap.get("status");
				String vendor=resultMap.get("vendor");
				String vendorCode= resultMap.get("opCode");

				if(status.equalsIgnoreCase("Ag_Sr_Blk")){
					responseJson.put("response-message", "You Are Not Authorized To Use This Service. Please Contact Your Channel Partner.");

				}else if(status.equalsIgnoreCase("invalid_operator"))
				{
					responseJson.put("response-message", "Invalid operator.");
				}else if(status.equalsIgnoreCase("Op_Sr_Blk")){
					responseJson.put("response-message", "This Operator is Not Available. Please Try Later.");
				}else if(status.equalsIgnoreCase("insufficient_balance")){
					responseJson.put("response-message", "You have Insufficient Balance.");
				}else if(status.equalsIgnoreCase("NA")){
					responseJson.put("response-message", "Transaction Failed Due to Technical Error. Please Try Later.");
				}
				else if(status.equalsIgnoreCase("Y"))
				{

					String ipaddress=request.getRemoteAddr();
					String transactionId=CommonServices.createTransactionID();

					String TranResult = utilityServiceDao.billpayInsert(txnId,transactionId,agentID,OP,CN,Double.parseDouble(AMT),
							"Billpay",ipaddress,"",vendor);
					System.out.println("Inserte Result----------"+TranResult);

					if(TranResult.equalsIgnoreCase("insufficient_balance"))
					{
						responseJson.put("response-message", "You have Insufficient Balance.");
					}
					else if(TranResult.equalsIgnoreCase("notinserted"))
					{
						responseJson.put("response-message", "Transaction Failed Due to Technical Error. Please Try Later.");
					}else if(TranResult.equalsIgnoreCase("invalid_operator"))
					{
						responseJson.put("response-message", "Invalid operator.");
					}else if (TranResult.equalsIgnoreCase("commisson_error"))
					{
						responseJson.put("response-message", "Transaction Failed as Your Commission is Not Set Properly.");
					}
					else if(TranResult.equalsIgnoreCase("TimeValidation"))
					{	
						responseJson.put("response-message", "Repeat Transaction. Please try after One Hour.");
					}else if(TranResult.equalsIgnoreCase("inserted"))
					{

						if("Self".equalsIgnoreCase(vendor)) {
							responseCode="0";
							responseJson.put("response-message", "Billpay Request Submitted Sucessfully.");
							utilityServiceDao.updateStatusBillPayTran(txnId, "Pending", agentID, "", vendor);
						}else if("MSMART".equalsIgnoreCase(vendor)){

							HashMap<String,String> mapResult=null;
							String reqString= OP+"|"+OPName+"|"+CN+"|"+AMT+"|"+AD1+"|"+AD2+"|"+AD4;
							MSmartController rechargeCl=new MSmartController();
							CommonServices.insertEKOLogs(vendor, transactionId, reqString, agentID);

							if("POSTPAID".equalsIgnoreCase(service)|| "LANDLINE".equalsIgnoreCase(service)) {
								mapResult=rechargeCl.callWinusRecharge(AMT,CN,vendorCode,txnId,"Postpaid");
							}else {
								mapResult=rechargeCl.callWinusBillpay(AMT, CN, AD4, vendorCode, txnId, service, AD1, referenceId);
							}

							String Status=mapResult.get("Status");
							String refId=mapResult.get("refId"); 
							if(refId==null || refId.length()<1)
								refId=txnId;

							String OperatorTxnId=mapResult.get("OperatorTxnId"); 
							String ResoponseXML=mapResult.get("ResoponseXML");

							try {
								CommonServices.updateEKOLogs(transactionId, ResoponseXML, agentID);
							} catch (Exception e) {
								// TODO: handle exception
							}

							if(Status.equalsIgnoreCase("Success"))
							{
								responseCode="0";
								responseJson.put("response-message", "Billpay Request Submitted Sucessfully.");
								utilityServiceDao.updateStatusBillPayTran(refId, "Success", agentID, OperatorTxnId, vendor);


							}else if(Status.equalsIgnoreCase("Pending"))
							{
								responseCode="0";
								responseJson.put("response-message", "Billpay Request Submitted Sucessfully. Please check status.");
								utilityServiceDao.updateStatusBillPayTran(refId, "Pending", agentID, OperatorTxnId, vendor);


							}else if(Status.equalsIgnoreCase("Failure")||Status.equalsIgnoreCase("Error"))
							{
								utilityServiceDao.updateStatusBillPayTran(refId, "Failure", agentID, OperatorTxnId, vendor);

								responseJson.put("response-message", mapResult.get("response-message"));

							}else 
							{
								//											Fail
								responseCode="0";
								responseJson.put("response-message", mapResult.get("response-message"));
								utilityServiceDao.updateStatusBillPayTran(refId, "Pending", agentID, OperatorTxnId, vendor);

							}


						}else if("SmartKinda".equalsIgnoreCase(vendor)){

							String reqString= OP+"|"+OPName+"|"+CN+"|"+AMT+"|"+AD1+"|"+AD2+"|"+AD4;

							CommonServices.insertEKOLogs(vendor, transactionId, reqString, agentID);
							String skop= OP.substring(2);
							JSONObject respObj=SmartKindaAPI.sendAPIBillpayRequest(skop, AMT, CN, txnId, AD1, AD2, "", AD4,referenceId);

							logger.info("Response :: "+respObj);

							if(respObj==null){
								responseJson.put("response-message", PropertyFile.TECHNICAL_FAILURE);
							}else{

								CommonServices.updateEKOLogs(transactionId, respObj.toString(), agentID);

								String updateStatus="";

								String Status=respObj.get("Status")+"";
								String vendorMessage=respObj.get("Description")+"";
								String ourTransactionId=respObj.get("txid")+"";
								String refId=respObj.get("refId")+"";
								//String response=respObj.toString();

								//String opr_id=respObj.get("opr_id")+""; 

								if("Success".equalsIgnoreCase(Status))
								{
									updateStatus=utilityServiceDao.updateStatusBillPayTran(ourTransactionId, "Success", agentID, refId, vendor);
									responseCode="0";
									responseJson.put("response-message", "Billpay Request Submitted Sucessfully.");

								}else if("Pending".equalsIgnoreCase(Status))
								{
									updateStatus=utilityServiceDao.updateStatusBillPayTran(ourTransactionId, "Pending", agentID, refId, vendor);
									responseCode="0";
									responseJson.put("response-message", "Billpay Request Submitted Sucessfully.");

								}else if("Failure".equalsIgnoreCase(Status)){
									updateStatus=utilityServiceDao.updateStatusBillPayTran(ourTransactionId, "Failure", agentID,  refId, vendor);

									if(vendorMessage!=null && vendorMessage.length()>0)
										responseJson.put("response-message", vendorMessage);
									else
										responseJson.put("response-message", "Transaction Failed. Your Transaction has been Refunded.");
									responseCode="0";

								}else{
									updateStatus=utilityServiceDao.updateStatusBillPayTran(ourTransactionId, "Pending", agentID, refId, vendor);

									responseJson.put("response-message", "Billpay Request Submitted Sucessfully.");
									responseCode="0";
								}
								logger.info("updateStatus :: "+updateStatus);
							}

						}else if("mrobotics".equalsIgnoreCase(vendor)){

							HashMap<String,String> mapResult=null;
							MRobotics mRobotics=new MRobotics();

							mapResult=mRobotics.callBillpayents(AMT, CN, vendorCode, transactionId, service);

							String Status=mapResult.get("Status");
							String refId=mapResult.get("refId"); 
							if(refId==null || refId.length()<1)
								refId=txnId;

							String OperatorTxnId=mapResult.get("OperatorTxnId"); 

							if(Status.equalsIgnoreCase("Success"))
							{
								responseCode="0";
								responseJson.put("response-message", "Billpay Request Submitted Sucessfully.");
								utilityServiceDao.updateStatusBillPayTran(refId, "Success", agentID, OperatorTxnId, vendor);


							}else if(Status.equalsIgnoreCase("Pending"))
							{
								responseCode="0";
								responseJson.put("response-message", "Billpay Request Submitted Sucessfully. Please check status.");
								utilityServiceDao.updateStatusBillPayTran(refId, "Pending", agentID, OperatorTxnId, vendor);


							}else if(Status.equalsIgnoreCase("Failure")||Status.equalsIgnoreCase("Error"))
							{
								utilityServiceDao.updateStatusBillPayTran(refId, "Failure", agentID, OperatorTxnId, vendor);

								responseJson.put("response-message", mapResult.get("Description"));

							}else 
							{
								//											Fail
								responseCode="0";
								responseJson.put("response-message", mapResult.get("Description"));
								utilityServiceDao.updateStatusBillPayTran(refId, "Pending", agentID, OperatorTxnId, vendor);

							}


						}else{
							responseJson.put("response-message", "Vendor not available.");
						}


					}else{
						responseJson.put("response-message", "Transaction failed due to technical failure.");
					}

				}

				responseJson.put("response-code", responseCode);
				return responseJson;

			}


		}
		catch (Exception e) 
		{
			e.printStackTrace();
			responseJson.put("response-code", responseCode);
			responseJson.put("response-message", PropertyFile.TECHNICAL_FAILURE);
			return responseJson;
		}


	}

	public static ArrayList<HashMap<String, Object>> fetchOperator(String service){
		try {

			CommonServiceDao dao=new CommonServiceDao();
			return dao.getOprator(service);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
