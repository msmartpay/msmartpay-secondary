package com.msmart.api.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class RechargeController {

	private String USERID="36";
	private String PASSWORD="rj45ds@wtp4b";
	private String KEY="RJ11";
	public String rechargeTrnId()
	{
		String tranid="";
		try
		{
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
			tranid="R"+str+str1;
			System.out.println("traniod  :::"+tranid);		
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}		
		return tranid;	
	}
	
	HashMap<String, String> GetReqText(String rechargeResponse,String url)
	{		
		HashMap<String, String> hmap=new HashMap<String, String>();
		String refId="";
		String rechargeRefId="";
		try 
		{			
			//RequestAccepted^R45151625556^RJ1723294618718718720ID1^Idea^10^Request Accepted;
			//RequestAccepted^R45151625556^RJ1723294618718718720ID1^Idea^10^Request Accepted;
			hmap.put("ResoponseXML", rechargeResponse);
			hmap.put("InputXML", url);
			if(rechargeResponse!=null)
			{
				if(rechargeResponse.contains("RequestAccepted")){
					ArrayList<String> s1=new ArrayList<String>();
					StringTokenizer st = new StringTokenizer(rechargeResponse, " //^");
			        while (st.hasMoreTokens()) 
			        {
			        	s1.add(st.nextToken());
			        }
					if (s1.get(0).equalsIgnoreCase("RequestAccepted"))
					{
						refId=s1.get(1);
						rechargeRefId=s1.get(2);

						hmap.put("res_code", "00");
						hmap.put("res_msg", "Recharge Request Submitted Sucessfully");
						hmap.put("refId", refId);
						hmap.put("rechargeRefId", rechargeRefId);
						hmap.put("Status", "Success");
					}else{
						hmap.put("Status", "Error");
						hmap.put("mars_reason", rechargeRefId);
						hmap.put("res_code", "02");
						hmap.put("res_msg", "Transaction Failure");
					}
					
				}else if (rechargeResponse.contains("Server Error")){
					hmap.put("Status", "Error");
					hmap.put("mars_reason", rechargeRefId);
					hmap.put("res_code", "02");
					hmap.put("res_msg", "Transaction Failure");
				}
			}
			else
			{
				hmap.put("Status", "Invalid");
				hmap.put("res_code", "04");
				hmap.put("res_msg", "Pending");
			}
			
			 System.out.println("Recharge Response hmap : "+hmap);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			hmap.put("res_code", "04");
			hmap.put("res_msg", "Pending");
			hmap.put("Status", "Invalid");
		}
		return hmap;
	}
	
	HashMap<String, String> GetRespText(String rechargeResponse,String url)
	{		
		HashMap<String, String> hmap=new HashMap<String, String>();
		try 
		{	String ReqS="0";
			hmap.put("ReqS",ReqS );
			
			DocumentBuilderFactory dbf =
			DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(rechargeResponse));

			Document doc = db.parse(is);						 
			NodeList nodes = doc.getElementsByTagName("RequestResponse");
			hmap.put("xmlResponse", rechargeResponse);
			hmap.put("InputXML", url);
			int xmlnodesize=nodes.getLength();
	
			Element element=(Element) nodes.item(0);

			if(xmlnodesize>0)
			{
				
					NodeList ReqSNode = element.getElementsByTagName("ReqS");
					Element ReqSEle = (Element) ReqSNode.item(0);
					ReqS= getCharacterDataFromElement(ReqSEle);
					
					NodeList CodeNode = element.getElementsByTagName("Code");
					Element CodeEle = (Element) CodeNode.item(0);
					String Code= getCharacterDataFromElement(CodeEle);
					
					NodeList RecSNode = element.getElementsByTagName("RecS");
					Element RecSEle = (Element) RecSNode.item(0);
					String RecS= getCharacterDataFromElement(RecSEle);
					
					NodeList APIRefNode = element.getElementsByTagName("APIRef");
					Element APIRefEle = (Element) APIRefNode.item(0);
					String APIRef= getCharacterDataFromElement(APIRefEle);
					
					NodeList YRefNode = element.getElementsByTagName("YRef");
					Element YRefEle = (Element) YRefNode.item(0);
					String YRef= getCharacterDataFromElement(YRefEle);
					
					NodeList ORefNode = element.getElementsByTagName("ORef");
					Element ORefEle = (Element) ORefNode.item(0);
					String ORef= getCharacterDataFromElement(ORefEle);
					
					hmap.put("ReqS",ReqS );
					hmap.put("Code", Code);
					hmap.put("RecS", RecS);
					hmap.put("APIRef",APIRef );
					hmap.put("YRef",YRef );
					hmap.put("ORef",ORef );
							  
				}
		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return hmap;
	}

	public String getCharacterDataFromElement(Element e) 
	{
		Node child = e.getFirstChild();
		if (child instanceof CharacterData) 
		{
			CharacterData cd = (CharacterData) child;
			return cd.getData();
		}
		return "?";
	}	
	
	public  HashMap<String,String> sndRechargeRequest(String mobno,String amtrechg,String agenttranno,String Opcode,String RechargeType)throws ParserConfigurationException, SAXException, IOException 
	{		  
		HashMap result =new HashMap();
		String url="";	
		String searchResponse=null;
		try
		{
			//180.188.239.14:91
			//http://rr.apistore.in/RechargeRequest.aspx?Operator=Idea&Number=7206671701&Amount=10
			//&RechargeType=R&ReferenceID=R45151625556&UserID=36&Password=rj45ds@wtp4b&Key=RJ11&Result=1
			url = "http://rr.apistore.in/RechargeRequest.aspx?Operator=" + Opcode + "&Number=" + mobno + 
			"&Amount=" + amtrechg + "&RechargeType="+RechargeType+"&ReferenceID=" + agenttranno+"&UserID="+USERID+"&Password="+PASSWORD+"&Key="+KEY+"&Result=1";
			
			System.out.println("Recharge Input request from Mars is: "+url);			
			searchResponse = getResponseFromRecharge(url, null, "text/xml", "GET", null);
	        System.out.println("Recharge Out put response from Mars is: "+searchResponse);	       
	        result=GetReqText(searchResponse,url);	      
		}
		catch(Exception e)
		{
			searchResponse="REQUEST ERROR your ref=111111 mars_reason=due to technical failure";
			result=GetReqText(searchResponse,url);
			System.out.println("exception in RechargeControler"+e.toString());
		}
		return result;
	}
	
	public String getResponseFromRecharge(String urlLocation, String postMessage,String requestContentType, String requestMethod,
			Map<String, String> requestHeaders) throws NoSuchAlgorithmException, KeyManagementException, MalformedURLException, IOException
	{
		String results=null;
		try
		{
			results = doHttpUrlConnectionAction(urlLocation);
			//System.out.println(results);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return results;
		}
		return results;		
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
