package com.msmart.api.controller;

import java.util.HashMap;

import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.msmart.service.CommonServices;
import com.msmart.util.UtilityP;

import net.sf.json.JSONObject;

public class MRobotics {

	Logger logger=Logger.getLogger(MRobotics.class);
	String token="72aef67d-fc63-450e-a2ec-c1f758f0dfbc";

	public HashMap<String,String> callRecharge(String rech_amount,String mobile_no,String oprCode,String transactionId,
			String Operator) {
		HashMap<String,String> result=new HashMap<String,String>();
		String recString="",searchResponse="";
		String requestContentType=MediaType.APPLICATION_JSON;
		String url="https://mrobotics.in/api/recharge";
		//data : &mobile_no=9876543210&amount=999&company_id=1&order_id=5813271&is_stv=false
		try {

			
			recString="api_token="+token+"&mobile_no="+mobile_no+"&amount="+rech_amount+"&order_id="+transactionId+"&company_id="+oprCode+"&is_stv=false";
			
			logger.info("Input request from MRobotics is: "+url);
			CommonServices.insertEKOLogs("MRobotics", transactionId, recString, "");
			searchResponse = UtilityP.post(url, recString,null, "POST", null);
			logger.info(searchResponse);	 
			try {
				CommonServices.updateEKOLogs(transactionId, searchResponse, "");
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			
			if(searchResponse!=null && searchResponse.length()>0){
				result=getRespText(searchResponse);

			}else{
				result=new HashMap<String,String>();
				result.put("OperatorTxnId", "");
				result.put("Status", "Pending");
				result.put("Description", "Pending");
			}
			result.put("InputXML", url);
			result.put("ResoponseXML", searchResponse);

		} catch (Exception e) {
			e.printStackTrace();
			result=new HashMap<String,String>();
			result.put("OperatorTxnId", "");
			result.put("Status", "Pending");
			result.put("Description", "Pending");
			return result;
		}

		return result;
	}
	
	public HashMap<String,String> callBillpayents(String rech_amount,String mobile_no,String oprCode,String transactionId,String service) {
		HashMap<String,String> result=new HashMap<String,String>();
		String recString="",searchResponse="";
		String requestContentType=MediaType.APPLICATION_JSON;
		String url="https://mrobotics.in/api/recharge";
		//data :   &mobile_no=9876543210&amount=999&company_id=1&order_id=5813271&is_stv=false
		try {
			
			String company_id="";
			if("POSTPAID".equalsIgnoreCase(service)) {
				company_id="15";
			}else if("ELECTRICITY".equalsIgnoreCase(service)) {
				company_id="14";
			}

			recString="api_token="+token+"&mobile_no="+mobile_no+"&amount="+rech_amount+"&order_id="+transactionId+"&company_id="+company_id+"&subcompany_id="+oprCode+"&is_stv=false";
			
			logger.info("Input request from MRobotics is: "+url);
			CommonServices.insertEKOLogs("MRobotics", transactionId, recString, "");
			searchResponse = UtilityP.post(url, recString, null, "POST", null);
			logger.info(searchResponse);	 
			try {
				CommonServices.updateEKOLogs(transactionId, searchResponse, "");
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			
			if(searchResponse!=null && searchResponse.length()>0){
				result=getRespText(searchResponse);

			}else{
				result=new HashMap<String,String>();
				result.put("OperatorTxnId", "");
				result.put("Status", "Pending");
				result.put("Description", "Pending");
			}

		} catch (Exception e) {
			e.printStackTrace();
			result=new HashMap<String,String>();
			result.put("OperatorTxnId", "");
			result.put("Status", "Pending");
			result.put("Description", "Pending");
		}
		result.put("refId", transactionId);
		return result;
	}
	

	public	HashMap<String,String> getRespText(String jsonString)
	{		
		HashMap<String, String> hmap=new HashMap<String,String>(); 
		try 
		{			
			if(null!=jsonString)
			{
				JSONObject obj=JSONObject.fromObject(jsonString);
				String statusVal= obj.getString("status");
				if (statusVal.equalsIgnoreCase("success")) {
					hmap.put("Status", "Success");
				}else if(statusVal.equalsIgnoreCase("Failed") || "failure".equalsIgnoreCase(statusVal)){
					hmap.put("Status", "failure");
				}else{
					hmap.put("Status", "Pending");
				}

				hmap.put("OperatorTxnId",obj.optString("tnx_id"));

				hmap.put("Description", obj.optString("response"));
			}				
		}
		catch (Exception e) {
			hmap.put("OperatorTxnId", "");
			hmap.put("Status", "failure");
			hmap.put("Description", "Technical failure.");
			e.printStackTrace();
		}
		return hmap;
	}
}
