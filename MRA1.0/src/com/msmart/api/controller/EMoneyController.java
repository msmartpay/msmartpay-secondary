package com.msmart.api.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class EMoneyController {

	public static void main(String[] args) {


		
		System.out.println("SmartRechargeController.main()"+new EMoneyController().callEMoneyRecharge("10","9716025028","AR","11414434333141111"));

	}

	public HashMap<String,String> callEMoneyRecharge(String rech_amount,String mobile_no,String oprCode,String transaction_No) {
		HashMap<String,String> result=new HashMap<String,String>();
		String recString="",searchResponse="";
		try {

			recString="client_key="+transaction_No+"&opr_code="+oprCode+"&rech_num="+mobile_no+"&amount="+rech_amount;
			String url = "http://emoneygroup.co.in/web-services/httpapi/recharge-request?api_key=cc9522a1-0da4-4c03-9ef9-bb5a7c02de85&acc_no=ACC13287&"+recString;

			System.out.println("Input request from Mars is: "+url);			
			searchResponse = doHttpUrlConnectionAction(url);
			System.out.println(searchResponse);	 
			if(searchResponse!=null && searchResponse.length()>0){
				result=GetRespText(searchResponse,url);	
			}else{
				result=new HashMap<String,String>();
				result.put("refId", transaction_No);
				result.put("emoneyRefId", "");
				result.put("OperatorTxnId", "");
				result.put("Status", "Pending");
			}
			result.put("InputXML", recString);
			result.put("ResoponseXML", searchResponse);

		} catch (Exception e) {
			e.printStackTrace();
			result=new HashMap<String,String>();
			result.put("refId", transaction_No);
			result.put("emoneyRefId", "");
			result.put("OperatorTxnId", "");
			result.put("Status", "Pending");
			result.put("InputXML", recString);
			result.put("ResoponseXML", searchResponse);
			return result;
		}

		return result;
	}

	HashMap<String, String> GetRespText(String rechargeResponse,String url)
	{		
		//rechargeResponse="received,tn12345,ck12345,9839098390,100,vf,,recharge request has accepted";
		//rechargeResponse="success,tn12345,ck12345,9839098390,100,vf,UE15283892,recharge request was succeeded";
		//rechargeResponse="failure,tn12345,ck12345,9839098390,100,vf,,recharge request was failed";
		//rechargeResponse="error,,ck12345,9839098390,100,vf,,insufficient balance in account";
		HashMap<String, String> hmap=new HashMap<String, String>();
		String refId="",emoneyRefId="",OperatorTxnId="",status="";
		try 
		{	

			if(rechargeResponse!=null && rechargeResponse.length()>0){

				String[] arr=rechargeResponse.split(",");
				
				refId=arr[2];
				emoneyRefId=arr[1];
				OperatorTxnId=arr[6];
				status=arr[0];
				if (status.equalsIgnoreCase("Success"))
				{
					hmap.put("refId", refId);
					hmap.put("emoneyRefId", emoneyRefId);
					hmap.put("OperatorTxnId", OperatorTxnId);
					hmap.put("Status", "Success");
				}else if(status.equalsIgnoreCase("received")){
					hmap.put("refId", refId);
					hmap.put("emoneyRefId", emoneyRefId);
					hmap.put("OperatorTxnId", OperatorTxnId);
					hmap.put("Status", "Accepted");
				}else if(status.equalsIgnoreCase("failure")){
					hmap.put("refId", refId);
					hmap.put("emoneyRefId", emoneyRefId);
					hmap.put("OperatorTxnId", OperatorTxnId);
					hmap.put("Status", "Failure");
				}else if(status.equalsIgnoreCase("suspense")){
					hmap.put("refId", refId);
					hmap.put("emoneyRefId", emoneyRefId);
					hmap.put("OperatorTxnId", OperatorTxnId);
					hmap.put("Status", "Pending");
				}else if(status.equalsIgnoreCase("error")){
					hmap.put("refId", refId);
					hmap.put("emoneyRefId", emoneyRefId);
					hmap.put("OperatorTxnId", OperatorTxnId);
					hmap.put("Status", "Error");
				}else{
					hmap.put("refId", refId);
					hmap.put("emoneyRefId", emoneyRefId);
					hmap.put("OperatorTxnId", OperatorTxnId);
					hmap.put("Status", "Pending");
				}


			}

		}
		catch (Exception e) {
			e.printStackTrace();
			hmap.put("refId", refId);
			hmap.put("emoneyRefId", emoneyRefId);
			hmap.put("OperatorTxnId", OperatorTxnId);
			hmap.put("Status", "Error");
			return hmap;
		}
		return hmap;
	}


	private String doHttpUrlConnectionAction(String desiredUrl)throws Exception
	{
		URL url = null;
		BufferedReader reader = null;
		StringBuilder stringBuilder;
		try
		{
			url = new URL(desiredUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();	      
			connection.setRequestMethod("GET");	   
			connection.setReadTimeout(220000);
			connection.connect();

			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			stringBuilder = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null)
			{
				stringBuilder.append(line + "\n");
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}
		finally
		{
			if (reader != null)
			{
				try
				{
					reader.close();
				}
				catch (IOException ioe)
				{
					ioe.printStackTrace();
				}
			}
		}
		return stringBuilder.toString();
	}

}
