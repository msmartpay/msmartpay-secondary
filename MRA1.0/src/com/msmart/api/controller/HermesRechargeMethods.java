package com.msmart.api.controller;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class HermesRechargeMethods 
{
	/*private static String WebProviderLoginId="SoftSolution"; 
	private static String WebProviderPassword="SoftSolution123";
	private static String URL="http://202.54.157.22/HermesMobAPI/HermesMobile.svc/jsonservice/";*/
	
	private static String WebProviderLoginId="Softsolution"; 
	private static String WebProviderPassword="apiSoftsolution";
	private static String URL="http://api.hermes-it.in/mobile/hermesmobile.svc/JSONService/";
	
	/*
	 * Get Recharge Operator Details.
	*/
	public String getRechargeDetails() 
	{  
		String reqproperty=URL+"GetRechargeOperators";
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("LoginId", WebProviderLoginId);
		jsonObject.put("Password", WebProviderPassword);
		JSONObject jsonObjectFinal=new JSONObject();
		jsonObjectFinal.put("Authentication", jsonObject);
		String responseXML=HermesRechargeMethods.callHemers(jsonObjectFinal.toString(), reqproperty);
		
        
        return responseXML;
	}
	/*
	 * Get Mobile Recharge Service.
	*/
	public String mobileBookingDetails(String UsertrackId,String Itemid,String MobileNo,double Amount) 
	{
		String reqproperty=URL+"GetRechargeDone"; 
		
		
		JSONObject jsonObjectFinal=new JSONObject();
		JSONObject Authentication=new JSONObject();
		JSONObject RechargeInput=new JSONObject();
		
		Authentication.put("LoginId", WebProviderLoginId);
		Authentication.put("Password", WebProviderPassword);
		
		RechargeInput.put("OperatorCode", Itemid);
		RechargeInput.put("MobileNumber", MobileNo);
		RechargeInput.put("Amount", Amount);
		
		jsonObjectFinal.put("Authentication", Authentication);
		jsonObjectFinal.put("UserTrackId", UsertrackId);
		jsonObjectFinal.put("RechargeInput", RechargeInput);
		
		String responseJson=HermesRechargeMethods.callHemers(jsonObjectFinal.toString(), reqproperty);
        return responseJson;
	}
	
	/*
	 * Get Transaction Status.
	*/
	public String getTransactionStatus(String UsertrackId) 
	{
		String reqproperty=URL+"GetTransactionStatus"; 
		
		JSONObject jsonObjectFinal=new JSONObject();
		JSONObject Authentication=new JSONObject();
		
		Authentication.put("LoginId", WebProviderLoginId);
		Authentication.put("Password", WebProviderPassword);
		
		jsonObjectFinal.put("Authentication", Authentication);
		jsonObjectFinal.put("UserTrackId", UsertrackId);
		
		String responseJson=HermesRechargeMethods.callHemers(jsonObjectFinal.toString(), reqproperty);
        return responseJson;
	}
	
	/*
	 * Reprint Recharge Status Details.
	*/
	public String reprint(String UsertrackId) 
	{
		//String pstrInput="<CheckTransReq><UserTrackID>"+UsertrackId+"</UserTrackID></CheckTransReq>";
		        
		String reqproperty="http://hermes-it.in/API/HermesMobile/GetReprint";     
		String reqbody="<s:Envelope xmlns:s=\"http://schemas.xmlsoap.org/soap/envelope/\">" +
				"<s:Body><GetReprint xmlns=\"http://hermes-it.in/API\">" +
				"<GetReprintRequest xmlns:i=\"http://www.w3.org/2001/XMLSchema-instance\">"+
				"<Authentication><LoginId>"+WebProviderLoginId+"</LoginId><Password>"+WebProviderPassword+"</Password></Authentication>" +
				"<UserTrackId>"+UsertrackId+"</UserTrackId>"+
				"</GetReprintRequest>"+
				"</GetReprint>"+
				"</s:Body></s:Envelope>";
		String responseXML=HermesRechargeMethods.callHemers(reqbody, reqproperty);
        return responseXML;
	}
	
	/*
	 * Get Bilpayment Operator Details.
	*/
	public String getbillpaymentDetails() 
	{
		
		
		String reqproperty=URL+"GetBillPaymentOperators";
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("LoginId", WebProviderLoginId);
		jsonObject.put("Password", WebProviderPassword);
		JSONObject jsonObjectFinal=new JSONObject();
		jsonObjectFinal.put("Authentication", jsonObject);
		String responseXML=HermesRechargeMethods.callHemers(jsonObjectFinal.toString(), reqproperty);
		
		
        return responseXML;
	}
	
	/*
	 * Get BillPayment Service.
	*/
	public String billpaymentBookingDetails(String UsertrackId,String Itemid,String MobileNo,String Amount,String ad) 
	{
		String reqproperty=URL+"GetBillPaymentDone";     
		JSONObject jsonObjectFinal=new JSONObject();
		JSONObject Authentication=new JSONObject();
		JSONObject BillPaymentInput=new JSONObject();
		
		Authentication.put("LoginId", WebProviderLoginId);
		Authentication.put("Password", WebProviderPassword);
		
		BillPaymentInput.put("OperatorCode", Itemid);
		BillPaymentInput.put("MobileNumber", MobileNo);
		BillPaymentInput.put("Amount", Amount);
		BillPaymentInput.put("OtherDetails", ad);
		
		jsonObjectFinal.put("Authentication", Authentication);
		jsonObjectFinal.put("UserTrackId", UsertrackId);
		jsonObjectFinal.put("BillPaymentInput", BillPaymentInput);
		
		String responseJson=HermesRechargeMethods.callHemers(jsonObjectFinal.toString(), reqproperty);
        return responseJson;
	}
	
	/*
	 * Get List of Recharge History.
	*/
	public String viewRechargeDetails(String fromDate,String toDate) 
	{
		
		String reqproperty=URL+"GetRechargeHistory";     
		String reqbody="<s:Envelope xmlns:s=\"http://schemas.xmlsoap.org/soap/envelope/\">" +
		"<s:Body><GetRechargeHistory xmlns=\"http://hermes-it.in/API\">" +
		"<GetRechargeHistoryRequest xmlns:i=\"http://www.w3.org/2001/XMLSchema-instance\">"+
		"<Authentication><LoginId>"+WebProviderLoginId+"</LoginId><Password>"+WebProviderPassword+"</Password></Authentication>" +
		"<RechargeHistoryInput><FromDate>"+fromDate+"</FromDate><ToDate>"+toDate+"</ToDate></RechargeHistoryInput>"+
		"</GetRechargeHistoryRequest>"+
		"</GetRechargeHistory>"+
		"</s:Body></s:Envelope>";
		String responseXML=HermesRechargeMethods.callHemers(reqbody, reqproperty);
        
        return responseXML;
	}
	/*
	 * Check your balance quota.
	*/
	public String checkQuota(String pstrFinalOutPutVal,String pstrErrorVal) 
	{
		String responseXML="";
		System.out.println("HermesRechargeMethods.checkQuota invoking...");
       
        return responseXML;
	}
	
	/*
	 * Get Account Statement History.
	*/
	public String viewAccountStatement(String fromDate,String toDate) 
	{
		
		String reqproperty="http://hermes-it.in/API/HermesMobile/GetAccountStatement";     
		String reqbody="<s:Envelope xmlns:s=\"http://schemas.xmlsoap.org/soap/envelope/\">" +
		"<s:Body><GetAccountStatement xmlns=\"http://hermes-it.in/API\">" +
		"<GetAccountStatementRequest xmlns:i=\"http://www.w3.org/2001/XMLSchema-instance\">"+
		"<Authentication><LoginId>"+WebProviderLoginId+"</LoginId><Password>"+WebProviderPassword+"</Password></Authentication>" +
		"<AccountStatementInput><FromDate>"+fromDate+"</FromDate><ToDate>"+toDate+"</ToDate></AccountStatementInput>"+
		"</GetAccountStatementRequest>"+
		"</GetAccountStatement>"+
		"</s:Body></s:Envelope>";
		String responseXML=HermesRechargeMethods.callHemers(reqbody, reqproperty);
        return responseXML;
	}
	
	/*
	 * Method for reading property file.
	*/
	public Properties getUserCredential() {
		Properties prop=null;
		try{
			prop=new Properties();
			String propFileName="config.properties";
			InputStream is=getClass().getClassLoader().getResourceAsStream(propFileName);
			if(is!=null){
				prop.load(is);
			}
			else{
				throw new FileNotFoundException("Propery file '"+propFileName+"' not found in existing class");
			}
			
			// get properties
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return prop;
	}

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		//String status=new HermesRechargeMethods().mobileBookingDetails("INRPR03000032G07012012080470120120804", "HACL", "9582153858", 10);
		//HashMap<String,String> status=new HermesRechargeMethods().sndHemesBillpayRequest("HVOP", "9582153858", "10", "INRPR03000032G07012012080470120120804");
		String status=new HermesRechargeMethods().getRechargeDetails();
		//String status=new HermesRechargeMethods().getbillpaymentDetails();
		//HashMap<String,String> status=new HermesRechargeMethods().GetReqText("INRPR03000032G07012012080470120120804", "HACL");
		
		//HashMap<String,String> status=new HermesRechargeMethods().GetTrackStatusText("INRPR03000032G07012012080470120120804", "HACL");
		//String status=new HermesRechargeMethods().getTransactionStatus("INRPR03000032G07012012080470120120804");
		//String status=new HermesRechargeMethods().getRechargeDetails();		
		//String status=new HermesRechargeMethods().billpaymentBookingDetails("INRPR03000032G07012012080470120120804", "HVOP", "9582153858", "10","");

		System.out.println("Response : "+status);
	}

	
	public static String callHemers(String reqbody,String reqproperty) {
		String response=null;
		 try {
			 	URL url = new URL(reqproperty);
			 	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			 	conn.setDoOutput(true);
			 	conn.setRequestMethod("POST");
			 	conn.setRequestProperty("Content-Type","application/json");
			 	conn.setRequestProperty("Accept", "application/json"); 
			 	String input = reqbody;
			 	System.out.println("Request : "+reqbody);
			 	OutputStream os = conn.getOutputStream();
			 	os.write(input.getBytes());
			 	os.flush();
			 	
			 	InputStreamReader ios=new InputStreamReader(conn.getInputStream());
			 	BufferedReader br = new BufferedReader(ios);
			 	
			 	StringBuffer strbf=new StringBuffer();
			 	
			 	String output;
			 	System.out.println("Output from Server .... \n");
			 	
			 	while ((output = br.readLine()) != null) {
			 		strbf.append(output);
			 	}
			 	response=strbf.toString();
			 	//System.out.println(strbf.toString());
			 	conn.disconnect();
		 	} catch (MalformedURLException e) {
		 		e.printStackTrace();
		 	} catch (IOException e) {
		 		e.printStackTrace();
		 	}
		 	catch (Exception e) {
		 		e.printStackTrace();
		 	}
		return response;
	}
	//mobtype, mobileNo, amtrechg, hermesTxnId
	public  HashMap<String,String> sndHemesRequest(String ItemId,String mobno,String amtrechg,String UsertrackId)throws ParserConfigurationException, SAXException, IOException 
	{		  
		System.out.println("HemersRechargeController.sndHemesRequest() calling ...");
		HashMap<String,String> result=new HashMap<String,String>();
		String searchResponse="";
		String request="UsertrackId : "+UsertrackId+" : ItemId : "+ItemId+" : "+mobno+" : amtrechg : "+amtrechg;
		try
			{
				searchResponse = mobileBookingDetails( UsertrackId, ItemId, mobno, Double.parseDouble(amtrechg));
		        System.out.println("Out put response from sndHemesRequest is: "+searchResponse);
		        if(searchResponse!=null && searchResponse.length()>0)
		        	result=GetReqText(searchResponse, request);
		        else{
		        	result.put("Status", "pending");
					result.put("xmlResponse", searchResponse);
					result.put("InputXML", request);
					result.put("chkNode", "response");
					result.put("refId", UsertrackId);
					result.put("OperatorTransactionId", "");
		        }
		        	      
			}
			catch(Exception e)
			{
				e.printStackTrace();
				result.put("Status", "pending");
				result.put("xmlResponse", searchResponse);
				result.put("InputXML", request);
				result.put("chkNode", "response");
				result.put("refId", UsertrackId);
				result.put("OperatorTransactionId", "");
			}
			
		return result;
	}

	public  HashMap<String,String> sndHemesBillpayRequest(String ItemId,String mobno,String amtrechg,String UsertrackId)throws ParserConfigurationException, SAXException, IOException 
	{		  
		System.out.println("HemersRechargeController.sndHemesRequest() calling ...");
		HashMap<String,String> result=new HashMap<String,String>();
		String searchResponse="";
		String request="UsertrackId : "+UsertrackId+" : ItemId : "+ItemId+" : "+mobno+" : amtrechg : "+amtrechg;
		try
			{
				searchResponse = billpaymentBookingDetails(UsertrackId,ItemId,mobno,amtrechg,"");
		        System.out.println("Out put response from sndHemesRequest is: "+searchResponse);
		        if(searchResponse!=null && searchResponse.length()>0)
		        	result=GetBillReqText(searchResponse, request);
		        else{
		        	result.put("Status", "pending");
					result.put("xmlResponse", searchResponse);
					result.put("InputXML", request);
					result.put("chkNode", "response");
					result.put("refId", UsertrackId);
					result.put("OperatorTransactionId", "");
		        }
		        	      
			}
			catch(Exception e)
			{
				e.printStackTrace();
				result.put("Status", "pending");
				result.put("xmlResponse", searchResponse);
				result.put("InputXML", request);
				result.put("chkNode", "response");
				result.put("refId", UsertrackId);
				result.put("OperatorTransactionId", "");
			}
			
		return result;
	}
	
	
	public  HashMap<String,String> getHemesRequestStatus(String agenttranid)throws ParserConfigurationException, SAXException, IOException 
	{		
		System.out.println("HemersRechargeController.getHemesRequestStatus() calling ...");
		HashMap<String,String> result=new HashMap<String,String>();
		String searchResponse=null;
		try
			{
		        searchResponse = getTransactionStatus(agenttranid);
		        System.out.println("Out put response from getHemesRequestStatus is: "+searchResponse);
		        if(searchResponse!=null && searchResponse.length()>0)
		        	result=GetTrackStatusText(searchResponse,agenttranid);
		        else{
		        	result.put("Status", "pending");
					result.put("xmlResponse", searchResponse);
					result.put("InputXML", agenttranid);
					result.put("chkNode", "response");
					result.put("refId", agenttranid);
					result.put("OperatorTransactionId", "");
		        }
			}
			catch(Exception e)
			{
				e.printStackTrace();
				result.put("Status", "pending");
				result.put("xmlResponse", searchResponse);
				result.put("InputXML", agenttranid);
				result.put("chkNode", "response");
				result.put("refId", agenttranid);
				result.put("OperatorTransactionId", agenttranid);
			}
			
		
	  return result;	  
	}
	
	
	public	HashMap<String,String> GetReqText(String jsonRecords,String url)
	{		
			HashMap<String, String> hmap=new HashMap<String, String>();
			hmap.put("xmlResponse", jsonRecords);
			hmap.put("InputXML", url);
			hmap.put("chkNode", "response");
			try{
				JSONParser jsonParser=new JSONParser();
		        JSONObject obj=(JSONObject) jsonParser.parse(jsonRecords);
		        //{obj={"ResponseStatus":1,"RechargeOutput":{"Amount":10,"ReferenceNumber":"FNDI8Y","RechargeDateTime":"Oct\/07\/2016 00:00:00","MobileNumber":"9582153858","OperatorTransactionId":"10764354976","OperatorDescription":"HACL"},"UserTrackId":"INRPR03000032G07012012080470120120804"}}
		        
		        String status=obj.get("ResponseStatus").toString();
		        String UserTrackId=obj.get("UserTrackId").toString();
		        if(status!=null && status.equalsIgnoreCase("1")){
		        	JSONObject RechargeOutput=(JSONObject)obj.get("RechargeOutput");
		        	String OperatorTransactionId=RechargeOutput.get("OperatorTransactionId")+"";
		        	String ReferenceNumber=RechargeOutput.get("ReferenceNumber").toString();
		        	
		        	hmap.put("Status", "success");
		        	hmap.put("OperatorTransactionId", OperatorTransactionId);
		        	hmap.put("ReferenceNumber", ReferenceNumber);
		        }else if(status!=null && (status.equalsIgnoreCase("0")|| status.equalsIgnoreCase("2"))){
		        	hmap.put("message", "pending");
		        	hmap.put("Status", "pending");
		        }else if(status!=null && status.equalsIgnoreCase("3")){
		        	String FailureRemarks=obj.get("FailureRemarks").toString();
		        	hmap.put("message", FailureRemarks);
		        	hmap.put("Status", "Failure");
		        }else{
		        	
		        	hmap.put("message", "Unable to fetch data.");
		        	hmap.put("Status", "pending");
		        }
		        hmap.put("refId", UserTrackId);
		       
							
			}
			catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			return hmap;
	}
	
	public	HashMap<String,String> GetBillReqText(String jsonRecords,String url)
	{		
			HashMap<String, String> hmap=new HashMap<String, String>();
			hmap.put("xmlResponse", jsonRecords);
			hmap.put("InputXML", url);
			hmap.put("chkNode", "response");
			try{
				JSONParser jsonParser=new JSONParser();
		        JSONObject obj=(JSONObject) jsonParser.parse(jsonRecords);
		        //{obj={"ResponseStatus":1,"RechargeOutput":{"Amount":10,"ReferenceNumber":"FNDI8Y","RechargeDateTime":"Oct\/07\/2016 00:00:00","MobileNumber":"9582153858","OperatorTransactionId":"10764354976","OperatorDescription":"HACL"},"UserTrackId":"INRPR03000032G07012012080470120120804"}}
		        
		        String status=obj.get("ResponseStatus").toString();
		        String UserTrackId=obj.get("UserTrackId").toString();
		        if(status!=null && status.equalsIgnoreCase("1")){
		        	JSONObject RechargeOutput=(JSONObject)obj.get("BillPaymentOutput");
		        	String OperatorTransactionId=RechargeOutput.get("OperatorTransactionId")+"";
		        	String ReferenceNumber=RechargeOutput.get("ReferenceNumber").toString();
		        	
		        	hmap.put("Status", "success");
		        	hmap.put("OperatorTransactionId", OperatorTransactionId);
		        	hmap.put("ReferenceNumber", ReferenceNumber);
		        }else if(status!=null && status.equalsIgnoreCase("0")){
		        	String FailureRemarks=obj.get("FailureRemarks").toString();
		        	hmap.put("message", FailureRemarks);
		        	hmap.put("Status", "failure");
		        }else{
		        	
		        	hmap.put("message", "Unable to fetch data.");
		        	hmap.put("Status", "pending");
		        }
		        hmap.put("refId", UserTrackId);
		       
							
			}
			catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			return hmap;
	}
	
	public	HashMap<String,String> GetTrackStatusText(String jsonRecords,String url)
	{		
		//{"ResponseStatus":1,"UserTrackId":"INRPR03000032G07012012080470120120804","TransactionStatusOutput":{"TransactionStatus":1,"TransactionDetails":{"ReferenceNumber":"FNDI8Y","OperatorDescription":"Aircel","MobileNumber":"9944100866","Amount":100,"RechargeDateTime":"Mar\/09\/2016 00:00:00","OperatorTransactionId":"10764354976"},"Remarks":"TRANSACTION GOT SUCCESS"}}

		HashMap<String, String> hmap=new HashMap<String, String>();
		hmap.put("xmlResponse", jsonRecords);
		hmap.put("InputXML", url);
		hmap.put("chkNode", "response");
        try{
			JSONParser jsonParser=new JSONParser();
	        JSONObject obj=(JSONObject) jsonParser.parse(jsonRecords);
	       
	        String status=obj.get("ResponseStatus").toString();
	        String UserTrackId=obj.get("UserTrackId").toString();
	        if(status!=null && status.equalsIgnoreCase("1")){
	        	JSONObject TransactionStatusOutput=(JSONObject)obj.get("TransactionStatusOutput");
	        	String TransactionStatus=TransactionStatusOutput.get("TransactionStatus").toString();
	        	if(TransactionStatus.equalsIgnoreCase("1")){
	        		JSONObject TransactionDetails=(JSONObject)obj.get("TransactionDetails");
		        	
		        	String OperatorTransactionId=TransactionDetails.get("OperatorTransactionId").toString();
		        	String ReferenceNumber=TransactionStatusOutput.get("ReferenceNumber").toString();
		        	hmap.put("OperatorTransactionId", OperatorTransactionId);
		        	hmap.put("ReferenceNumber", ReferenceNumber);
		        	hmap.put("Status", "success");
	        	}else if(TransactionStatus.equalsIgnoreCase("0")){
	        		String Remarks=TransactionStatusOutput.get("Remarks").toString();
	        		hmap.put("message", Remarks);
	        		hmap.put("Status", "pending");
	        	}else if(TransactionStatus.equalsIgnoreCase("2")){
	        		String Remarks=TransactionStatusOutput.get("Remarks").toString();
	        		hmap.put("message", Remarks);
	        		hmap.put("Status", "pending");
	        	}else if(TransactionStatus.equalsIgnoreCase("3")){
	        		String Remarks=TransactionStatusOutput.get("Remarks").toString();
	        		hmap.put("message", Remarks);
	        		hmap.put("Status", "failure");
	        	}else{
	        		hmap.put("message", "Unable to Fetch Data");
	        		hmap.put("Status", "pending");
	        	}
	        	
	        	
	        }else if(status!=null && status.equalsIgnoreCase("0")){
	        	String FailureRemarks=obj.get("FailureRemarks").toString();
	        	hmap.put("message", FailureRemarks);
	        	hmap.put("Status", "failure");
	        }
	        else{
	        	String FailureRemarks=obj.get("FailureRemarks").toString();
	        	hmap.put("message", FailureRemarks);
	        	hmap.put("Status", "pending");
	        }
	        hmap.put("refId", UserTrackId);
	        
	        hmap.put("obj", obj.toString());
						
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return hmap;
	}
}
