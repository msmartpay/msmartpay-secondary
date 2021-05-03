package com.msmart.api.controller;

import java.util.HashMap;

import com.msmart.service.PropertyFile;
import com.smartkinda.api.SszAPICall;

import net.sf.json.JSONObject;

public class SmartKindaBillpay 
{

	public static JSONObject sendAPIBillpayRequest(String Op,String amt,String cn,String reqId,
			String ad1,String ad2,String ad3,String ad4,String referenceId) {
		
		JSONObject response=null;
		JSONObject obj=new JSONObject();
		obj.put("AgentID", PropertyFile.RechargeBillpay_AgentAuthId);
		obj.put("Key", PropertyFile.RechargeBillpay_AgentAuthPassword);
		obj.put("OP", Op);
		obj.put("Account", cn);
		obj.put("Amount", amt);
		obj.put("REQUEST_ID", reqId);
		obj.put("reference_id", referenceId);
		obj.put("Customermobile", ad1);
		obj.put("Optional1", ad1);
		obj.put("Optional2", ad2);
		obj.put("Optional3", ad3);
		obj.put("Optional4", ad4);
		obj.put("Optional5", "");
		obj.put("Optional6", "");
		obj.put("Optional7", "");
		obj.put("Optional8", "Billpay");
		obj.put("Optional9", "28.6207,77.3818|201301");
		obj.put("Outlet_ID", "3669");

		String url=PropertyFile.RechargeBillpay_URL+"Payment";
		SszAPICall apicall=new SszAPICall();
		response=apicall.getJSONobjectPost(url,obj);

		return response;
	}


	@SuppressWarnings("unchecked")
	public static HashMap<String,String> viewBillpayRequest(String tranId,String Op,String cn,String amt,
			String ad1,String ad2,String ad3,String ad4,String referenceId) {
		HashMap<String,String> hashMap=new HashMap<String, String>();
		JSONObject response=null;
		JSONObject obj=new JSONObject();
		obj.put("AgentID", PropertyFile.RechargeBillpay_AgentAuthId);
		obj.put("Key", PropertyFile.RechargeBillpay_AgentAuthPassword);
		obj.put("OP", Op);
		obj.put("Account", cn);
		obj.put("Amount", amt);
		obj.put("REQUEST_ID", tranId);
		obj.put("reference_id", referenceId);
		obj.put("Customermobile", ad4);
		obj.put("Optional1", ad1);
		obj.put("Optional2", ad2);
		obj.put("Optional3", ad3);
		obj.put("Optional4", ad4);
		obj.put("Optional5", "");
		obj.put("Optional6", "");
		obj.put("Optional7", "");
		obj.put("Optional8", "Fetch");
		obj.put("Optional9", "28.6207,77.3818|201301");
		obj.put("Outlet_ID", PropertyFile.Outlet_ID);

		String url=PropertyFile.RechargeBillpay_URL+"ValidatePayment";
		SszAPICall apicall=new SszAPICall();
		response=apicall.getJSONobjectPost(url,obj);
		hashMap=getBillViewText(response,obj.toString());

		System.out.println(hashMap);

		return hashMap;
	}

	public static HashMap<String , String> getBillViewText(JSONObject response,String reqjson) {

		HashMap<String , String> hashMap=new HashMap<String, String>();
		String status="",desc="";

		if(response!=null){
			if(response.containsKey("statuscode"))
				status=response.get("statuscode")+"";
			if(response.containsKey("status"))
				desc=response.get("status")+"";

			if(status.equalsIgnoreCase("TXN") || "IRA".equalsIgnoreCase(status)|| "RCS".equalsIgnoreCase(status) ){

				JSONObject particulars=(JSONObject)response.get("particulars");
				status="Success";
				if(particulars.containsKey("dueamount")){
					hashMap.put("dueamount",particulars.get("dueamount")+"");
				}
				else{
					hashMap.put("dueamount","NA");
				}

				if(particulars.containsKey("billdate")){
					hashMap.put("billdate",particulars.get("billdate")+"");
				}else{
					hashMap.put("billdate","NA");
				}

				if(particulars.containsKey("duedate")){
					hashMap.put("duedate",particulars.get("duedate")+"");
				}else{
					hashMap.put("duedate","NA");
				}

				if(particulars.containsKey("customername")){
					hashMap.put("customername",particulars.get("customername")+"");
				}else{
					hashMap.put("customername","NA");
				}
				
				if(particulars.containsKey("reference_id")){
					hashMap.put("reference_id",particulars.get("reference_id")+"");
				}else{
					hashMap.put("reference_id","NA");
				}
				

			}else{
				status="Failure";
			}
			hashMap.put("Status", status);
			hashMap.put("Description", desc);
		}else{
			hashMap.put("Status", "Failure");
			hashMap.put("Description", "Unable to process your request. Please try after some time.");
		}
		return hashMap;
	}

	public JSONObject sendAPIRechargeRequest(String ST,String Op,String amt,String cn,String reqId,String CIR,String AgentAuthId,String AgentAuthPassword) {

		JSONObject response=null;
		JSONObject obj=new JSONObject();
		obj.put("AgentID", PropertyFile.RechargeBillpay_AgentAuthId);
		obj.put("Key", PropertyFile.RechargeBillpay_AgentAuthPassword);
		obj.put("ST", ST);
		obj.put("CIR", CIR);
		obj.put("OP", Op);
		obj.put("AMT", amt);
		obj.put("CN", cn);
		obj.put("REQID", reqId);

		String url=PropertyFile.RechargeBillpay_URL+"Recharge";
		SszAPICall apicall=new SszAPICall();
		response=apicall.getJSONobjectPost(url,obj);

		return response;
	}

}
