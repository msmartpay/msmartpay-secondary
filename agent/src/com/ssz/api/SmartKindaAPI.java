package com.ssz.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.common.PropertyFile;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class SmartKindaAPI 
{

	public static JSONObject sendAPIRechargeRequest(String Op,String amt,
			String cn,String reqId,String ip,String service,String agentId,String key) {
		JSONObject response=null;
		JSONObject obj=new JSONObject();
		obj.put("agent_id", agentId);
		obj.put("txn_key", key);
		obj.put("OP", Op);
		obj.put("Account", cn);
		obj.put("Amount", amt);
		obj.put("Service", service);
		obj.put("REQUEST_ID", reqId);
		obj.put("IP", ip);

		String url="SKPRE/Recharge";
		response=webservicecall(obj ,url);

		return response;
	}

	public static JSONObject sendAPIBillpayRequest(String Op,String amt,String cn,String reqId,String ad1,String ad2,String ad3,
			String ad4,String opName,String reference_id,String service,String agentId,String key) {
		
		JSONObject response=null;
		try {
			
			JSONObject obj=new JSONObject();
			obj.put("agent_id", agentId);
			obj.put("txn_key", key);
			obj.put("OP", Op);
			obj.put("OPName", opName);
			obj.put("CN", cn);
			obj.put("AMT", amt);
			obj.put("service", service);
			obj.put("REQUEST_ID", reqId);
			obj.put("AD4", ad4);
			obj.put("AD1", ad1);
			obj.put("AD2", ad2);
			obj.put("AD3", ad3);
			obj.put("reqType", "Pay");
			obj.put("reference_id",reference_id);

			String url="SKPOST/Billpay";
			response=webservicecall(obj ,url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return response;
	}

	public static JSONObject viewBillpayRequest(String tranId,String Op,String cn,String amt,
			String ad1,String ad2,String ad3,String ad4,String OPName,String service,String agentId,String key) {
		JSONObject response=null;
		
		try {
			JSONObject obj=new JSONObject();
			obj.put("agent_id", agentId);
			obj.put("txn_key", key);
			obj.put("OP", Op);
			obj.put("OPName", OPName);
			obj.put("CN", cn);
			obj.put("AMT", amt);
			obj.put("service", service);
			obj.put("REQUEST_ID", tranId);
			obj.put("AD4", ad4);
			obj.put("AD1", ad1);
			obj.put("AD2", ad2);
			obj.put("AD3", ad3);
			obj.put("reqType", "ViewBill");
			obj.put("reference_id", "");

			String url="SKPOST/Billpay";
			response=webservicecall(obj ,url);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return response;
	}


	public static List<HashMap<String,String>> fetchOperator(JSONObject obj,String agentId,String key){
		List<HashMap<String,String>> list=new ArrayList<HashMap<String,String>>();

		JSONObject respObj=new JSONObject();
		try {

			obj.put("agent_id", agentId);
			obj.put("txn_key", key);
			obj.put("service", "all");

			System.out.println("Request :: "+obj.toString());
			respObj=webservicecall(obj, "SKPOST/billpayOperator");
			if(respObj!=null && respObj.get("response-code").toString().equalsIgnoreCase("0")){
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

			Client client = Client.create();

			String fUrl=PropertyFile.MRA_URL+url;
			System.out.println("URL :: "+fUrl);

			WebResource webResource = client.resource(fUrl);

			ClientResponse response = webResource.type("application/json").post(ClientResponse.class,obj.toString());

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}

			String output = response.getEntity(String.class);
			JSONParser parser = new JSONParser();
			Object object = parser.parse(output);
			json_obj = (JSONObject) object;

			//logger.info("Server response : \n");
			System.out.println(output);


		} catch (Exception e) {

			e.printStackTrace();

		}
		return json_obj;
	}


}
