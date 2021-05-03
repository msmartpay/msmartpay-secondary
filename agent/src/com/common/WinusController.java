package com.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class WinusController {


	public HashMap<String,String> callWinusRecharge(String rech_amount,String mobile_no,String oprCode,String transaction_No,String service) {
		HashMap<String,String> result=new HashMap<String,String>();
		String recString="",searchResponse="",ServiceType="";
		try {
			if("mobile".equalsIgnoreCase(service))
				ServiceType="MR";
			if("dth".equalsIgnoreCase(service))
				ServiceType="DH";
			
			recString="Merchantrefno="+transaction_No+"&opcode="+oprCode+"&mobile="+mobile_no+"&amount="+rech_amount+"&ServiceType="+ServiceType;
			String url = "http://venusrecharge.co.in/Transaction.aspx?authkey=10008&authpass=Arpit@123&"+recString;
			//String url = "http://venusrecharge.co.in/Transaction.aspx?authkey=10006&authpass=Kinda@123&"+recString;
			
			System.out.println("Input request from Mars is: "+url);			
			searchResponse = doHttpUrlConnectionAction(url);
			System.out.println(searchResponse);	 
			if(searchResponse!=null && searchResponse.length()>0){
				result=GetRespText(searchResponse,url);	
			}else{
				result=new HashMap<String,String>();
				result.put("refId", transaction_No);
				result.put("OperatorTxnId", "");
				result.put("Status", "Pending");
			}
			result.put("InputXML", recString);
			result.put("ResoponseXML", searchResponse);

		} catch (Exception e) {
			e.printStackTrace();
			result=new HashMap<String,String>();
			result.put("refId", transaction_No);
			result.put("OperatorTxnId", "");
			result.put("Status", "Pending");
			result.put("InputXML", recString);
			result.put("ResoponseXML", searchResponse);
			return result;
		}

		return result;
	}

	public	HashMap<String,String> GetRespText(String xmlRecords,String url)
	{		
		HashMap<String, String> hmap=new HashMap<String,String>(); 
		try 
		{			
			DocumentBuilderFactory dbf =DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			String chkNode="";
			is.setCharacterStream(new StringReader(xmlRecords));
			Document doc = db.parse(is);						 
			NodeList nodes = doc.getElementsByTagName("Response");
			int xmlnodesize=nodes.getLength();

			Element element=(Element) nodes.item(0);

			if(xmlnodesize>0)
			{
				chkNode="response";
				hmap.put("chkNode",chkNode);
				for (int i = 0; i < nodes.getLength(); i++) {

					element = (Element) nodes.item(i);
					NodeList status = element.getElementsByTagName("ResponseStatus");
					Element line = (Element) status.item(0);
					String statusVal= getCharacterDataFromElement(line);
					hmap.put("message",statusVal);
					if (statusVal.equalsIgnoreCase("Success")) {
						hmap.put("Status", "Success");
					}else if(statusVal.equalsIgnoreCase("FAILED")){
						hmap.put("Status", "failure");
					}else{
						hmap.put("Status", "Pending");
					}
					
					hmap.put("responseCode",statusVal); 
					NodeList MerTxnID = element.getElementsByTagName("MerTxnID");
					line = (Element) MerTxnID.item(0);
					String txId= getCharacterDataFromElement(line);
					hmap.put("refId",txId); 
					
					NodeList OperatorTxnID = element.getElementsByTagName("OperatorTxnID");
					line = (Element) OperatorTxnID.item(0);
					String OperatorTxnIDStr= getCharacterDataFromElement(line);
					hmap.put("OperatorTxnId",OperatorTxnIDStr);
					
					NodeList OrderNo = element.getElementsByTagName("OrderNo");
					line = (Element) OrderNo.item(0);
					String OrderNoStr= getCharacterDataFromElement(line);
					hmap.put("OrderNo",OrderNoStr);
				}
			}				
		}
		catch (Exception e) {
			hmap.put("Status", "failure");
			hmap.put("refId","");
			hmap.put("OperatorTxnId","");
		}
		return hmap;
	}

	public  String getCharacterDataFromElement(Element e) {
		Node child = e.getFirstChild();
		if (child instanceof CharacterData) {
			CharacterData cd = (CharacterData) child;
			return cd.getData();
		}
		return "?";
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
