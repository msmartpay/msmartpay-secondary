package com.ssz.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.core.MediaType;

import com.msmart.service.CommonServices;
import com.msmart.service.PropertyFile;
import com.msmart.util.UtilityP;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class SmartKindaAPI 
{

	public static JSONObject sendAPIRechargeRequest(String Op,String amt,
			String cn,String reqId,String ip,String service) {
		JSONObject response=null;
		JSONObject obj=new JSONObject();
		obj.put("AgentID", PropertyFile.RechargeBillpay_AgentAuthId);
		obj.put("Key", PropertyFile.RechargeBillpay_AgentAuthPassword);
		obj.put("OP", Op);
		obj.put("Account", cn);
		obj.put("Amount", amt);
		obj.put("Service", service);
		obj.put("REQUEST_ID", reqId);
		obj.put("IP", ip);

		String url="Recharge";
		response=webservicecall(obj ,url);

		return response;
	}
	
	public static JSONObject sendAPIBillpayRequest(String Op,String amt,String cn,String reqId,
			String ad1,String ad2,String ad3,String ad4,String referenceId) {
		JSONObject hashMap=new JSONObject();
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

		String url="Payment";
		response=webservicecall(obj ,url);
		hashMap=getTextAPI(response,obj);

		return hashMap;
	}

	public static JSONObject viewBillpayRequest(String AgentAuthId,String AgentAuthPassword,String tranId,String Op,String cn,String amt,String ad1,String ad2,String ad3,String ad4) {
		JSONObject hashMap=new JSONObject();
		JSONObject response=null;
		JSONObject obj=new JSONObject();
		obj.put("AgentID", PropertyFile.RechargeBillpay_AgentAuthId);
		obj.put("Key", PropertyFile.RechargeBillpay_AgentAuthPassword);
		obj.put("OP", Op);
		obj.put("Account", cn);
		obj.put("Amount", amt);
		obj.put("REQUEST_ID", tranId);
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
		obj.put("Outlet_ID", "3669");

		String url="ValidatePayment";
		response=webservicecall(obj ,url);
		hashMap=getBillViewText(response,obj.toString());

		System.out.println(hashMap);

		return hashMap;
	}

	public static JSONObject getBillViewText(JSONObject response,String reqjson) {

		JSONObject hashMap=new JSONObject();
		String status="",desc="",maxBillAmt="",billdate="",duedate="",customername="";

		if(response!=null){
			if(response.containsKey("statuscode"))
				status=response.get("statuscode")+"";
			if(response.containsKey("status"))
				desc=response.get("status")+"";

			if(status.equalsIgnoreCase("TXN") || "IRA".equalsIgnoreCase(status) ){

				JSONObject particulars=(JSONObject)response.get("particulars");
				status="Success";
				if(particulars.containsKey("dueamount")){
					maxBillAmt=particulars.get("dueamount")+"";
					hashMap.put("dueamount",maxBillAmt);
				}
				else{
					hashMap.put("dueamount","NA");
				}

				if(particulars.containsKey("billdate")){
					billdate=particulars.get("billdate")+"";
					hashMap.put("billdate",billdate);
				}else{
					hashMap.put("billdate","NA");
				}

				if(particulars.containsKey("duedate")){
					duedate=particulars.get("duedate")+"";
					hashMap.put("duedate",duedate);
				}else{
					hashMap.put("duedate","NA");
				}

				if(particulars.containsKey("customername")){
					customername=particulars.get("customername")+"";
					hashMap.put("customername",customername);
				}else{
					hashMap.put("customername","NA");
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
		//logger.info("MRA AESApiController.getBillViewTextAES()...."+hashMap);
		return hashMap;
	}

	public static JSONObject getTextAPI(JSONObject response,JSONObject reqjson) {

		JSONObject hashMap=new JSONObject();
		hashMap.put("InputXML", reqjson);
		hashMap.put("xmlResponse", response);
		String status="",desc="",apitranId="",REQID="",opr_id="";

		if(response!=null){
			if(response.containsKey("statuscode"))
				status=response.get("statuscode")+"";
			if(response.containsKey("status"))
				desc=response.get("status")+"";

			if(response.containsKey("ipay_id"))
				apitranId=response.get("ipay_id")+"";
			if(response.containsKey("agent_id"))
				REQID=response.get("agent_id")+"";
			if(response.containsKey("opr_id"))
				opr_id=response.get("opr_id")+"";

			if(status.equalsIgnoreCase("TXN") ){
				hashMap.put("Status", "success");
			}else if("TUP".equalsIgnoreCase(status) || "".equalsIgnoreCase(status)){
				hashMap.put("Status", "pending");
			}else if("RPI".equalsIgnoreCase(status) 
					|| "UAD".equalsIgnoreCase(status)
					|| "IAC".equalsIgnoreCase(status)
					|| "IAT".equalsIgnoreCase(status)
					|| "AAB".equalsIgnoreCase(status)
					|| "IAB".equalsIgnoreCase(status)
					|| "ISP".equalsIgnoreCase(status)
					|| "DID".equalsIgnoreCase(status)
					|| "IRA".equalsIgnoreCase(status)
					|| "IAN".equalsIgnoreCase(status)
					|| "ERR".equalsIgnoreCase(status)
					|| "IUA".equalsIgnoreCase(status)
					|| "SNA".equalsIgnoreCase(status)
					|| "OUI".equalsIgnoreCase(status)
					|| "ODI".equalsIgnoreCase(status)
					|| "IPE".equalsIgnoreCase(status)

					|| "IEC".equalsIgnoreCase(status)
					|| "IRT".equalsIgnoreCase(status)
					|| "ITI".equalsIgnoreCase(status)
					|| "TSU".equalsIgnoreCase(status)
					|| "SPD".equalsIgnoreCase(status)
					|| "SPE".equalsIgnoreCase(status)
					|| "ISE".equalsIgnoreCase(status)
					|| "1".equalsIgnoreCase(status)){

					
					hashMap.put("Status", "failure");
			}else{
				hashMap.put("Status","pending");
			}
			hashMap.put("txid", (REQID!=null?REQID:""));
			hashMap.put("refId", (apitranId!=null?apitranId:""));
			hashMap.put("opr_id", (opr_id!=null?opr_id:""));
			hashMap.put("Description",desc);
		}
		return hashMap;
	}

	public static JSONObject fetchOperator(String service,JSONObject obj) {

		JSONObject resp=new JSONObject();

		try {

			resp=webservicecall(obj, "SKIPDMR/fetchAllUtilityOperator");
			JSONArray newdata=new JSONArray();
			if(resp!=null && "TXN".equalsIgnoreCase(resp.get("status").toString())){
				JSONArray data=(JSONArray)resp.get("data");
				if(data!=null && data.size()>0){
					
					for (int i = 0; i < data.size(); i++) {
						JSONObject operator=(JSONObject)data.get(i);
						if(service.equalsIgnoreCase(operator.get("Service").toString())){
							newdata.add(operator);
						}
					}
					
				}
				
				resp.put("data", newdata);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			resp.put("status", "1");
			resp.put("message", "Unable to process your request.");
		}
		return resp;
	}
	
	
	public static List<HashMap<String,String>> fetchOperator(JSONObject obj){
		List<HashMap<String,String>> list=new ArrayList<HashMap<String,String>>();
		
		JSONObject respObj=new JSONObject();
		try {

			System.out.println("Request :: "+obj.toString());
			respObj=webservicecall(obj, "SKIPDMR/fetchAllUtilityOperator");
			if(respObj!=null && respObj.get("status").toString().equalsIgnoreCase("TXN")){
				JSONArray jsonArray=(JSONArray)respObj.get("data");

				for (int i = 0; i < jsonArray.size(); i++) {
					
					JSONObject operator=(JSONObject)jsonArray.get(i);
					
					HashMap<String,String> opMap=new HashMap<>();
					opMap.put("Service", operator.get("Service")+"");
					opMap.put("OpCode", operator.get("OpCode")+"");
					opMap.put("BillFetch", operator.get("BillFetch")+"");
					opMap.put("OperatorName", operator.get("OperatorName")+"");
					opMap.put("DisplayName", operator.get("DisplayName")+"");
					list.add(opMap);
				}

			}else
				return null;

		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	
	public static JSONObject webservicecall(JSONObject obj ,String url) {
		JSONObject json_obj=null;
		try {
			CommonServices.insertEKOLogs("Venfone", obj.getString("REQUEST_ID"), obj.toString(), "");
			String response=UtilityP.post(url, obj.toString(), MediaType.APPLICATION_JSON, "POST", null);
			CommonServices.updateEKOLogs(obj.getString("REQUEST_ID"), response, "");
			json_obj=JSONObject.fromObject(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json_obj;
	}


}
