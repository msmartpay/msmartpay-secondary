package com.msmart.api.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.msmart.service.CommonServices;
import com.msmart.service.PropertyFile;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MSmartController {

	Logger logger=Logger.getLogger(MSmartController.class);
	public HashMap<String,String> callWinusRecharge(String rech_amount,String mobile_no,String oprCode,String transaction_No,
			String service) {
		HashMap<String,String> result=new HashMap<String,String>();
		String recString="",searchResponse="",ServiceType="";
		try {
			if("mobile".equalsIgnoreCase(service))
				ServiceType="MR";
			if("dth".equalsIgnoreCase(service))
				ServiceType="DH";
			if("Postpaid".equalsIgnoreCase(service))
				ServiceType="MR";

			recString="Merchantrefno="+transaction_No+"&opcode="+oprCode+"&mobile="+mobile_no+"&amount="+rech_amount+"&ServiceType="+ServiceType;
			String url = PropertyFile.VENUS_RECHARGE_URL+"Transaction.aspx?authkey="+PropertyFile.VENUS_RECHARGE_USERID+"&authpass="+PropertyFile.VENUS_RECHARGE_PASSWORD+"&"+recString;

			logger.info("Input request from Venus is: "+url);	
			CommonServices.insertEKOLogs("Venus", transaction_No, recString, "");
			
			searchResponse = doHttpUrlConnectionAction(url);
			logger.info(searchResponse);	 
			try {
				CommonServices.updateEKOLogs(transaction_No, searchResponse, "");
			} catch (Exception e) {
				// TODO: handle exception
			}
			
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

	public HashMap<String,String> callWinusFetchBillpay(String customerId,String customerMobile,
			String oprCode,String transaction_No,String service,String optional1) {

		HashMap<String,String> result=new HashMap<String,String>();
		String recString="",searchResponse="",ServiceType="";
		try {

			if("Electricity".equalsIgnoreCase(service))
				ServiceType="EB";

			recString="Merchantrefno="+transaction_No+"&opcode="+oprCode+"&ConsumerID="+customerId+"&ConsumerMobileNo="+customerMobile+
					"&SubDiv="+optional1+"&ServiceType="+ServiceType;
			//String url = "http://venusrecharge.co.in/FetchBill.aspx?authkey=10008&authpass=Arpit@123&"+recString;
			String url =PropertyFile.VENUS_RECHARGE_URL+"FetchBill.aspx?authkey="+PropertyFile.VENUS_RECHARGE_USERID+"&authpass="+PropertyFile.VENUS_RECHARGE_PASSWORD+"&"+recString;
			System.out.println("Input request from Mars is: "+url);			
			searchResponse = doHttpUrlConnectionAction(url);
			System.out.println(searchResponse);	 
			if(searchResponse!=null && searchResponse.length()>0){
				result=GetRespBillpayText(searchResponse,url);	
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

	public HashMap<String,String> callWinusBillpay(String rech_amount,String customerId,String customerMobile,
			String oprCode,String transaction_No,String service,String optional1,String referenceId) {

		HashMap<String,String> result=new HashMap<String,String>();
		String recString="",searchResponse="",ServiceType="";
		try {

			if("Electricity".equalsIgnoreCase(service))
				ServiceType="EB";

			recString="Merchantrefno="+transaction_No+"&opcode="+oprCode+"&ConsumerID="+customerId+"&ConsumerMobileNo="+customerMobile+
					"&SubDiv="+optional1+"&ServiceType="+ServiceType+"&OrderId="+referenceId+"&Amount="+rech_amount;

			//String url = "http://venusrecharge.co.in/PaymentBill.aspx?authkey=10008&authpass=Arpit@123&"+recString;
			String url =PropertyFile.VENUS_RECHARGE_URL+"PaymentBill.aspx?authkey="+PropertyFile.VENUS_RECHARGE_USERID+"&authpass="+PropertyFile.VENUS_RECHARGE_PASSWORD+"&"+recString;
			System.out.println("Input request from Mars is: "+url);			
			searchResponse = doHttpUrlConnectionAction(url);
			System.out.println(searchResponse);	 
			if(searchResponse!=null && searchResponse.length()>0){
				result=GetRespBillpayText(searchResponse,url);	
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

					NodeList description = element.getElementsByTagName("Description");
					Element descriptionline = (Element) description.item(0);
					String descriptionVal= getCharacterDataFromElement(descriptionline);

					hmap.put("responseCode",statusVal); 
					NodeList MerTxnID = element.getElementsByTagName("MerTxnID");
					line = (Element) MerTxnID.item(0);

					String txId= "";
					if(line!=null) {
						txId= getCharacterDataFromElement(line);
					}
					hmap.put("refId",txId); 
					NodeList OperatorTxnID = element.getElementsByTagName("OperatorTxnID");
					line = (Element) OperatorTxnID.item(0);
					String OperatorTxnIDStr="";
					if(line!=null)
						OperatorTxnIDStr= getCharacterDataFromElement(line);

					hmap.put("OperatorTxnId",OperatorTxnIDStr);

					NodeList OrderNo = element.getElementsByTagName("OrderNo");
					line = (Element) OrderNo.item(0);
					String OrderNoStr="";
					if(line!=null)
						OrderNoStr= getCharacterDataFromElement(line);
					hmap.put("OrderNo",OrderNoStr);

					hmap.put("description", descriptionVal);
				}
			}				
		}
		catch (Exception e) {
			hmap.put("Status", "failure");
			hmap.put("description", "Technical failure.");
			hmap.put("refId","");
			hmap.put("OperatorTxnId","");
			e.printStackTrace();
		}
		return hmap;
	}

	public	HashMap<String,String> GetRespBillpayText(String xmlRecords,String url)
	{		
		HashMap<String, String> hmap=new HashMap<String,String>(); 
		try 
		{			
			hmap.put("ResoponseXML", xmlRecords);
			DocumentBuilderFactory dbf =DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xmlRecords));
			Document doc = db.parse(is);						 
			NodeList nodes = doc.getElementsByTagName("Response");
			int xmlnodesize=nodes.getLength();

			Element element=(Element) nodes.item(0);

			if(xmlnodesize>0)
			{
				for (int i = 0; i < nodes.getLength(); i++) {

					element = (Element) nodes.item(i);
					NodeList status = element.getElementsByTagName("ResponseStatus");
					Element line = (Element) status.item(0);
					String statusVal= getCharacterDataFromElement(line);

					NodeList description = element.getElementsByTagName("Description");
					Element descriptionline = (Element) description.item(0);
					String descriptionVal= getCharacterDataFromElement(descriptionline);

					if("TXN".equalsIgnoreCase(statusVal) || "RCS".equalsIgnoreCase(statusVal)) {

						hmap.put("Status","Success");

						String txId="",OrderNoStr="",ConsumerNameVal="",DueAmountVal="",DueDateVal="",BillDateVal="",OperatorTxnIdStr="";

						NodeList MerTxnID = element.getElementsByTagName("MerTxnID");
						line = (Element) MerTxnID.item(0);
						if(line!=null)
							txId= getCharacterDataFromElement(line);
						hmap.put("refId",txId); 

						NodeList OrderNo = element.getElementsByTagName("OrderId");
						line = (Element) OrderNo.item(0);
						if(line!=null)OrderNoStr= getCharacterDataFromElement(line);
						hmap.put("reference_id",OrderNoStr);
						
						NodeList OperatorTxnId = element.getElementsByTagName("OperatorTxnId");
						line = (Element) OperatorTxnId.item(0);
						if(line!=null)OperatorTxnIdStr= getCharacterDataFromElement(line);
						hmap.put("OperatorTxnId",OperatorTxnIdStr);

						NodeList ConsumerName = element.getElementsByTagName("ConsumerName");
						line = (Element) ConsumerName.item(0);
						if(line!=null) ConsumerNameVal= getCharacterDataFromElement(line);
						hmap.put("customername",ConsumerNameVal);

						NodeList DueAmount = element.getElementsByTagName("DueAmount");
						line = (Element) DueAmount.item(0);
						if(line!=null) DueAmountVal= getCharacterDataFromElement(line);
						hmap.put("dueamount",DueAmountVal);

						NodeList DueDate = element.getElementsByTagName("DueDate");
						line = (Element) DueDate.item(0);
						if(line!=null) DueDateVal= getCharacterDataFromElement(line);
						hmap.put("duedate",DueDateVal);

						NodeList BillDate = element.getElementsByTagName("BillDate");
						line = (Element) BillDate.item(0);
						if(line!=null) BillDateVal= getCharacterDataFromElement(line);
						hmap.put("billdate",BillDateVal);

					}
					/*else if( "SAC".equalsIgnoreCase(statusVal) ||  "SDL".equalsIgnoreCase(statusVal) || "TUP".equalsIgnoreCase(statusVal)) {
						hmap.put("Status","Pending");
					}*/else
						hmap.put("Status","Pending");
					hmap.put("response-message", descriptionVal);
				}
			}				
		}
		catch (Exception e) {
			hmap.put("Status", "failure");
			hmap.put("response-message", "Technical failure.");
			e.printStackTrace();
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

	public static void main(String[] args) {

		String xmlRecords="<Response><ResponseStatus>FAILED</ResponseStatus><Description>Invaild Mobile/Account No</Description></Response>";
		MSmartController c=new MSmartController();
		System.out.println(c.GetRespText(xmlRecords, ""));

	}

}
