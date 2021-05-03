package com.msmart.api.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class SmartRechargeController {
	
	
	public HashMap<String,String> callSmartRecharge(String amt,String conno,String optr,String tranid) {
		HashMap<String,String> result=new HashMap<String,String>();
		
		try {
			
			//180.188.239.14:91
			//http://rr.apistore.in/RechargeRequest.aspx?Operator=Idea&Number=7206671701&Amount=10
			//&RechargeType=R&ReferenceID=R45151625556&UserID=36&Password=rj45ds@wtp4b&Key=RJ11&Result=1
			//Input request from Mars is: http://smartrecharge.online/recharge/api/?process=recharge&userid=9582153858&pass=123456&reqString=AR&nbps9716025028&nbps20&txnid=32890780588689
			//http://smartrecharge.online/recharge/api/?process=recharge&userid=9582153858&pass=123456&reqString=AR%209716025028%2010&txnid=32890780588680
			//Success=>9804543159=>10=>39137486470=>16100319342500784 =>470.00
			//Suspense=>9931447823=>10=>39180615002
			//* Error: Invalid Transaction Id
			String recString=optr+"%20"+conno+"%20"+amt;
			String url = "http://smartrecharge.online/recharge/api/?process=recharge&userid=9582153858&pass=123456&reqString="+recString+"&txnid="+tranid;
			
			System.out.println("Input request from Mars is: "+url);			
			String searchResponse = doHttpUrlConnectionAction(url);
	        System.out.println("Smart Recharge searchResponse : "+searchResponse);	       
	        result=GetRespText(searchResponse,url);	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	HashMap<String, String> GetRespText(String rechargeResponse,String url)
	{		
		//rechargeResponse="Success=>9804543159=>10=>39137486470=>16100319342500784 =>470.00";
		HashMap<String, String> hmap=new HashMap<String, String>();
		String refId="",smartRefId="";
		hmap.put("ReqString", url);
		try 
		{	

			if(rechargeResponse!=null && rechargeResponse.length()>0){
				if(rechargeResponse.contains("Success")){

					ArrayList<String> s1=new ArrayList<String>();
					StringTokenizer st = new StringTokenizer(rechargeResponse, "=>");
			        while (st.hasMoreTokens()) 
			        {
			        	s1.add(st.nextToken());
			        }
					if (s1.get(0).equalsIgnoreCase("Success"))
					{
						refId=s1.get(3);
						smartRefId=s1.get(4);

						hmap.put("res_code", "00");
						hmap.put("res_msg", "Recharge Request Submitted Sucessfully");
						hmap.put("refId", refId);
						hmap.put("smartRefId", smartRefId);
						hmap.put("Status", "Success");
					}
					
				
				}if(rechargeResponse.contains("Error")){
					hmap.put("Status", "Failure");
				}

			}else{
				
			}
		
		}
		catch (Exception e) {
			e.printStackTrace();
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
