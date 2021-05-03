package com.msmart.api.controller;

import java.util.HashMap;
import java.util.Random;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class APIController
{
	private static final String AgentAuthId="SMARTKINDA";
	private static final String AgentAuthPassword="8cb2237d0679ca88db6464eac60da96345513964";
	
	
	
	
	public	String APITrnId(){
		String tranid="";
		try{
			int n = 7;
			Random randGen = new Random();
			int startNum = (int) Math.pow(10, n-1);
			int range = (int) (Math.pow(10, n) - startNum);
			int randomNum = randGen.nextInt(range) + startNum;			
			String str = String.valueOf(randomNum);
			int j = 6;
			Random randGn = new Random();
			int startNm = (int) Math.pow(10, j-1);			
			int range1 = (int) (Math.pow(10, j) - startNm);		
			int randomNum2=randGn.nextInt(range1)+startNm;		
			String str1=String.valueOf(randomNum2);
			tranid=str+"0"+str1;
			System.out.println("traniod  :::"+tranid);		
			
		}
		catch (Exception e) 
		{
			// TODO: handle exception
			e.printStackTrace();
		}		
		return tranid;	
	}
	
	public static void main(String[] args) {
		new APIController().APITrnId();
	}

	
	public HashMap<String,String> sendAPIBillpayRequest(String ST,String Op,String amt,String cn,String reqId,String ad1,String ad2,String ad3,String ad4) {
		HashMap<String,String> hashMap=new HashMap<String, String>();
		JSONObject response=null;
		JSONObject obj=new JSONObject();
		obj.put("AgentAuthId", AgentAuthId);
		obj.put("AgentAuthPassword", AgentAuthPassword);
		obj.put("ST", ST);
		obj.put("OP", Op);
		obj.put("AMT", amt);
		obj.put("CN", cn);
		obj.put("REQID", reqId);
		
		obj.put("AD1", ad1);
		obj.put("AD2", ad2);
		obj.put("AD3", ad3);
		obj.put("AD4", ad4);
		
		String url="Billpay";
		response=webservicecall(obj ,url);
		hashMap=getTextAPI(response,obj.toString());
		
		return hashMap;
	}
	
	public HashMap<String,String> viewBillpayRequest(String ST,String Op,String cn,String ad1,String ad2,String ad3,String ad4) {
		HashMap<String,String> hashMap=new HashMap<String, String>();
		JSONObject response=null;
		JSONObject obj=new JSONObject();
		obj.put("AgentAuthId", AgentAuthId);
		obj.put("AgentAuthPassword", AgentAuthPassword);
		obj.put("ST", ST);
		obj.put("OP", Op);
		obj.put("CN", cn);
		
		obj.put("AD1", ad1);
		obj.put("AD2", ad2);
		obj.put("AD3", ad3);
		obj.put("AD4", ad4);
		
		String url="APIBillView";
		response=webservicecall(obj ,url);
		hashMap=getBillViewText(response,obj.toString());
		
		return hashMap;
	}
	
	public HashMap<String , String> getBillViewText(JSONObject response,String reqjson) {
		
		HashMap<String , String> hashMap=new HashMap<String, String>();
		String status="",desc="",maxBillAmt="",billAmt="";
		
		if(response!=null){
			if(response.containsKey("Status"))
				status=response.get("Status")+"";
			if(response.containsKey("Description"))
				desc=response.get("Description")+"";
			
			if(status.equalsIgnoreCase("1") ){
				if(response.containsKey("maxBillAmount")){
					maxBillAmt=response.get("Status")+"";
					hashMap.put("maxBillAmount",maxBillAmt);
				}
				if(response.containsKey("billAmount")){
					billAmt=response.get("billAmount")+"";
					hashMap.put("billAmount",billAmt);
				}
			}
			hashMap.put("Status", status);
			hashMap.put("Description", desc);
		}else{
			hashMap.put("Status", "0");
			hashMap.put("Description", "Unable to process your request. Please try after some time.");
		}
		System.out.println("MRA AESApiController.getBillViewTextAES()...."+hashMap);
		return hashMap;
	}
	
	public HashMap<String,String> sendAPIRechargeRequest(String ST,String Op,String amt,String cn,String reqId,String CIR) {
		HashMap<String,String> hashMap=null;
		hashMap.put("chkNode", "response");
		
		
		JSONObject response=null;
		JSONObject obj=new JSONObject();
		obj.put("AgentAuthId", AgentAuthId);
		obj.put("AgentAuthPassword", AgentAuthPassword);
		obj.put("ST", ST);
		obj.put("CIR", CIR);
		obj.put("OP", Op);
		obj.put("AMT", amt);
		obj.put("CN", cn);
		obj.put("REQID", reqId);
		
		String url="Recharge";
		response=webservicecall(obj ,url);
		hashMap=getTextAPI(response,obj.toString());
		
		return hashMap;
	}
	
	public HashMap<String , String> getTextAPI(JSONObject response,String reqjson) {
		
		HashMap<String , String> hashMap=new HashMap<String, String>();
		hashMap.put("InputXML", reqjson.toString());
		hashMap.put("xmlResponse", response.toString());
		String status="",desc="",apitranId="",REQID="",balance="";
		
		if(response!=null){
			if(response.containsKey("Status"))
				status=response.get("Status")+"";
			if(response.containsKey("Description"))
				desc=response.get("Description")+"";
			if(response.containsKey("aesApiTranId"))
				apitranId=response.get("aesApiTranId")+"";
			if(response.containsKey("REQID"))
				REQID=response.get("REQID")+"";
			if(response.containsKey("balance"))
				balance=response.get("balance")+"";
			
			if(status.equalsIgnoreCase("1") ){
				hashMap.put("Status", "success");
			}else if(status.equalsIgnoreCase("2")){
				hashMap.put("description",desc);
				hashMap.put("Status", "pending");
			}else if(status.equalsIgnoreCase("0")){
				hashMap.put("description",desc);
				hashMap.put("Status", "failure");
			}else{
				hashMap.put("Status","pending");
			}
			hashMap.put("txid", (REQID!=null?REQID:""));
			hashMap.put("refId", (apitranId!=null?apitranId:""));
		}
		return hashMap;
	}
	
	public static JSONObject webservicecall(JSONObject obj ,String url) {
		JSONObject json_obj=null;
		try {

			Client client = Client.create();

			WebResource webResource = client.resource("http://192.168.43.98:8081/API/resources/API/"+url);

			ClientResponse response = webResource.type("application/json").post(ClientResponse.class,obj.toString());

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}

			String output = response.getEntity(String.class);
			JSONParser parser = new JSONParser();
			Object object = parser.parse(output);
			json_obj = (JSONObject) object;
			
			System.out.println("Server response : \n");
			System.out.println(output);

					  
		} catch (Exception e) {

			e.printStackTrace();

		}
		  return json_obj;
	}

}


