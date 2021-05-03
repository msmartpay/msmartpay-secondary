package com.msmart.api.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Random;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.client.urlconnection.HTTPSProperties;
import com.sun.net.ssl.SSLContext;

public class AESApiController {

	private static final String AgentAuthId="Pay Enething Services Pvt";
	private static final String AgentAuthPassword="d1rth7avsx";

	public static void main(String[] args) throws ParseException {
		
		
		/*String SessionId=YesMoneyTransferServices.generateSessionId();
		String ecSessionId=EncodeDecoder.encodeData(SessionId);
		String agentId="2006";
		String ecAgentId=EncodeDecoder.encodeData(agentId);
		
		IYesBankMoneyAndPrepaidCardDao dao=new YesBankMoneyAndPrepaidCardDao();
		boolean status=dao.insertSession(SessionId,agentId,"MoneyTransfer");
		if(status)
			System.out.println("AESMoneyTransferClient.main() Session Id Update");
		else
			System.out.println("AESMoneyTransferClient.main() Session Id Not Update");
		System.out.println("AESMoneyTransferClient.saveSessionId() "+ecSessionId.length());
		System.out.println("AESMoneyTransferClient.saveSessionId() "+ecAgentId.length());
		
		
		String refId=YesMoneyTransferServices.generateRefNo();
		
		ValidateRequest validatorRequest = new ValidateRequest(agentId, ecSessionId, "Testing");
		webservicecall(validatorRequest ,"validate");
		
		QuotaRequest quotaRequest=new QuotaRequest(ecAgentId, ecSessionId, "sfsg435", new Double("100"), "Testing");
		//webservicecall(quotaRequest,"quotacheck" );
		//16011212283408424
		//16011212303106795
		Date curDate = new Date();
		DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		String DateToStr = sdf.format(curDate);
		
		TransactionRequest request=new TransactionRequest(ecAgentId, ecSessionId, new Double("100"), new Double("11"),"12121222111131", "12030411415105719", DateToStr, "testing", "testing");
		//webservicecall(request,"transaction" );
		
		RefundRequest refundRequest=new RefundRequest(ecAgentId, ecSessionId, new Double("100"), new Double("6"), "121121222111131", "16020411415105719", "refund");
		//webservicecall(refundRequest,"refund" );
		
		CreditRequest creditRequest=new CreditRequest(ecAgentId, ecSessionId, "PrepaidCard",new Double("120"), new Double("10"), new Double("110"), refId, "", "testing","", "");
		webservicecall(creditRequest,"credit" );
		
		
		DebitRequest debitRequest=new DebitRequest(ecAgentId, ecSessionId, "PrepaidCard", new Double("120"), new Double("10"), new Double("110"), refId, "", "testing", "", "");
		//webservicecall(debitRequest,"debit" );
		
		com.aadhaar.yesprepaidcard.entity.RefundRequest refundRequest=new com.aadhaar.yesprepaidcard.entity.RefundRequest(ecAgentId, ecSessionId, "PrepaidCard", new Double("120"), new Double("10"), new Double("110"), "15120915094709165", "", "CR","Testing", "", "");
		//webservicecall(refundRequest,"refund");
		 	
		System.out.println(DateToStr);*/
		//{"AgentAuthId":"test","AgentAuthPassword":"test","ST":"electricity","OP":"1501","AMT":"10","CN":"150018384","AD1":"","AD2":"","AD3":"","AD4":""}
		JSONObject obj=new JSONObject();
		obj.put("AgentAuthId", AgentAuthId);
		obj.put("AgentAuthPassword", AgentAuthPassword);
		obj.put("ST", "mobile");
		obj.put("OP", "1101");
		obj.put("AMT", "10");
		obj.put("CN", "9716025028");
		obj.put("REQID", egenTrnId());
		/*obj.put("AD1", "");
		obj.put("AD2", "");
		obj.put("AD3", "");
		obj.put("AD4", "");*/
		
		String url="Recharge";
		webservicecall(obj ,url);
	
	}
	
	public HashMap<String,String> getBillDetails(String billpayOp,String st,String connectionNo,String billAmount,String ad1,String ad4,String ad3,String ad2) {
		System.out.println("getBillDetails----"+billpayOp+connectionNo+billAmount);
		HashMap<String,String> hashMap=null;
		
		JSONObject response=null;
		try
			{
				st=st.toLowerCase();
				JSONObject obj=new JSONObject();
				obj.put("AgentAuthId", AgentAuthId);
				obj.put("AgentAuthPassword", AgentAuthPassword);
				obj.put("ST", st);
				obj.put("OP", billpayOp);
				obj.put("AMT", billAmount);
				obj.put("CN",connectionNo);
				obj.put("AD1", ad1);
				obj.put("AD2", ad2);
				obj.put("AD3", ad3);
				obj.put("AD4", ad4);
				
				String url="APIBillView";
				response=webservicecall(obj ,url);
				hashMap=getBillViewTextAES(response,url);
			 }
			 catch(Exception e)
			 {
				 e.printStackTrace();
			 } 
			 
		
		 return hashMap;	
	}
	
	public HashMap<String , String> getBillViewTextAES(JSONObject response,String reqjson) {
		
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
		//System.out.println("AESApiController.getBillViewTextAES()...."+hashMap);
		return hashMap;
	}
	
	public HashMap<String,String> sendBillpayRequest(String ST,String Op,String amt,String cn,String reqId,String ad1,String ad2,String ad3,String ad4) {
		HashMap<String,String> hashMap=new HashMap<String, String>();
		JSONObject response=null;
		JSONObject obj=new JSONObject();
		if(ST.equalsIgnoreCase("MobileBill"))
			ST="mobile";
		
		ST=ST.toLowerCase();
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
		hashMap=getTextAES(response,obj.toString());
		
		return hashMap;
	}
	
	public HashMap<String,String> sendRechargeRequest(String ST,String CIR,String Op,String amt,String cn,String reqId) {
		HashMap<String,String> hashMap=new HashMap<String, String>();
		try{
			ST=ST.toLowerCase();
			JSONObject response=null;
			JSONObject obj=new JSONObject();
			obj.put("AgentAuthId", AgentAuthId);
			obj.put("AgentAuthPassword", AgentAuthPassword);
			obj.put("ST", ST);
			obj.put("CIR",CIR);
			obj.put("OP", Op);
			obj.put("AMT", amt);
			obj.put("CN", cn);
			obj.put("REQID", reqId);
			
			String url="Recharge";
			response=webservicecall(obj ,url);
			hashMap=getTextAES(response,obj.toString());
			hashMap.put("chkNode", "response");
		}catch (Exception e) {
			e.printStackTrace();
		}
		return hashMap;
	}
	
	public HashMap<String , String> getTextAES(JSONObject response,String reqjson) {
		
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
				hashMap.put("status", "success");
			}else if(status.equalsIgnoreCase("2")){
				hashMap.put("description",desc);
				hashMap.put("status", "pending");
			}else if(status.equalsIgnoreCase("0")){
				hashMap.put("description",desc);
				hashMap.put("status", "failure");
			}else{
				hashMap.put("status","pending");
			}
			hashMap.put("txid", (REQID!=null?REQID:""));
			hashMap.put("refId", (apitranId!=null?apitranId:""));
			hashMap.put("operatorTid", "");
		}
		return hashMap;
	}
	
	public static JSONObject webservicecall(JSONObject obj ,String url) {
		System.out.println("AESApiController.webservicecall() called ........................");
		JSONObject json_obj=null;
		try {
			Client client = Client.create();
			WebResource webResource = client.resource("https://services.myaadhaarshila.com:8443/AESAPI/resources/AESAPI/"+url);

			ClientResponse response = webResource.type("application/json").post(ClientResponse.class,obj.toString());

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}

			String output = response.getEntity(String.class);
			JSONParser parser = new JSONParser();
			Object object = parser.parse(output);
			json_obj = (JSONObject) object;
			
			System.out.println("Server response : \n"+json_obj.toString());
			//System.out.println(output);

					  
		} catch (Exception e) {

			e.printStackTrace();

		}
		  return json_obj;
	}
	
	public static String egenTrnId(){
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
	
	/*public void saveSessionId(String sessionId) {
	
		HashMap<String,Object> dateTimeMap=YesMoneyTransferServices.getDateAndTime();
		AESSessionIdDetails requestXml=new AESSessionIdDetails();
		requestXml.setAgentId("2004");
		requestXml.setDate((java.sql.Date)dateTimeMap.get("date"));
		requestXml.setTime((java.sql.Time)dateTimeMap.get("time"));
		requestXml.setServiceType(PropertyFile.moneytransferService);
		requestXml.setServiceType(sessionId);
		
		Session ses=null;
		SessionFactory sesf=null;
		Transaction txn=null;
		
		try {
			
			sesf=HibernateUtility.getSessionFactory();
			ses=sesf.openSession();
			txn=ses.beginTransaction();
			ses.saveOrUpdate(requestXml);
			txn.commit();
			
		} catch (Exception e) {
			System.out.println("AESMoneyTransferDao.validateAgent()");
			e.printStackTrace();
			txn.rollback();
		}finally{
			ses.close();
			txn=null;
		}
		
	}
	
	*/
	

	
}
