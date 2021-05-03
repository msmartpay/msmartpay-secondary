package com.msmart.api.controller;

import java.io.StringReader;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.log4j.Logger;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import com.msmart.dao.MobileDthRechargeDao;
import com.msmart.service.CommonServices;
import com.msmart.service.PropertyFile;
import com.msmart.util.UtilityP;

public class CyberHubController {
	
	Logger logger=Logger.getLogger(CyberHubController.class);

	public HashMap<String,String> callRecharge(String rech_amount,String mobile_no,String oprCode,
			String transaction_No,
			String service) {
		HashMap<String,String> result=new HashMap<String,String>();
		String recString="",searchResponse="",url="";
		String requestContentType=MediaType.TEXT_XML;
		try {

			recString="reqid="+transaction_No+"&op="+oprCode+"&mn="+mobile_no+"&amt="+rech_amount+"&field1=&field2=";
			url = "http://www.cyberhubinfotech.com/apiservice.asmx/Recharge?apiToken="+PropertyFile.CYBERHUB_TOKEN+"&"+recString;

			logger.info("Input request from Cyberhub is: "+url);
			
			CommonServices.insertEKOLogs("Cyberhub", transaction_No, url, "");
			searchResponse =UtilityP.post(url, null, requestContentType, "GET",null);
			logger.info(searchResponse);
			
			try {
				CommonServices.updateEKOLogs(transaction_No, searchResponse, "");
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			if(searchResponse!=null && searchResponse.length()>0){
				result=GetRespText(searchResponse);	
			}else{
				result=new HashMap<String,String>();
				result.put("refId", transaction_No);
				result.put("OperatorTxnId", "");
				result.put("Status", "Pending");
			}
			result.put("InputXML", url);
			result.put("ResoponseXML", searchResponse);

		} catch (Exception e) {
			e.printStackTrace();
			result=new HashMap<String,String>();
			result.put("refId", transaction_No);
			result.put("OperatorTxnId", "");
			result.put("Status", "Pending");
			result.put("InputXML", url);
			result.put("ResoponseXML", searchResponse);
			return result;
		}

		return result;
	}

	public	HashMap<String,String> GetRespText(String xmlRecords)
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
			NodeList nodes = doc.getElementsByTagName("Result");
			int xmlnodesize=nodes.getLength();

			Element element=(Element) nodes.item(0);

			if(xmlnodesize>0)
			{
				chkNode="response";
				hmap.put("chkNode",chkNode);
				for (int i = 0; i < nodes.getLength(); i++) {

					element = (Element) nodes.item(i);
					NodeList status = element.getElementsByTagName("status");
					Element line = (Element) status.item(0);
					String statusVal= getCharacterDataFromElement(line);
					if (statusVal.equalsIgnoreCase("Success")) {
						hmap.put("Status", "Success");
					}else if(statusVal.equalsIgnoreCase("FAILED") || statusVal.equalsIgnoreCase("REFUND") || statusVal.equalsIgnoreCase("FREQUENT")){
						hmap.put("Status", "failure");
					}else{
						hmap.put("Status", "Pending");
					}
					

					if(statusVal.equalsIgnoreCase("Success")||statusVal.equalsIgnoreCase("Pending")) {
						
						hmap.put("responseCode",statusVal); 
						NodeList MerTxnID = element.getElementsByTagName("reqid");
						line = (Element) MerTxnID.item(0);
						String txId= getCharacterDataFromElement(line);
						hmap.put("refId",txId); 

						NodeList OrderNo = element.getElementsByTagName("field1");
						line = (Element) OrderNo.item(0);
						if(null!=line) {
							String OrderNoStr= getCharacterDataFromElement(line);
							hmap.put("OrderNo",OrderNoStr);
						}else {
							hmap.put("OrderNo","");
						}
					}
					
					NodeList description = element.getElementsByTagName("remark");
					Element descriptionline = (Element) description.item(0);
					String descriptionVal= getCharacterDataFromElement(descriptionline);

					hmap.put("OperatorTxnId","");
					hmap.put("Description", descriptionVal);
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

						NodeList MerTxnID = element.getElementsByTagName("MerTxnID");
						line = (Element) MerTxnID.item(0);
						String txId= getCharacterDataFromElement(line);
						hmap.put("refId",txId); 

						NodeList OrderNo = element.getElementsByTagName("OrderId");
						line = (Element) OrderNo.item(0);
						String OrderNoStr= getCharacterDataFromElement(line);
						hmap.put("reference_id",OrderNoStr);

						NodeList ConsumerName = element.getElementsByTagName("ConsumerName");
						line = (Element) ConsumerName.item(0);
						String ConsumerNameVal= getCharacterDataFromElement(line);
						hmap.put("customername",ConsumerNameVal);

						NodeList DueAmount = element.getElementsByTagName("DueAmount");
						line = (Element) DueAmount.item(0);
						String DueAmountVal= getCharacterDataFromElement(line);
						hmap.put("dueamount",DueAmountVal);

						NodeList DueDate = element.getElementsByTagName("DueDate");
						line = (Element) DueDate.item(0);
						String DueDateVal= getCharacterDataFromElement(line);
						hmap.put("duedate",DueDateVal);

						NodeList BillDate = element.getElementsByTagName("BillDate");
						line = (Element) BillDate.item(0);
						String BillDateVal= getCharacterDataFromElement(line);
						hmap.put("billdate",BillDateVal);

					}
					else if( "SAC".equalsIgnoreCase(statusVal) ||  "SDL".equalsIgnoreCase(statusVal) || "TUP".equalsIgnoreCase(statusVal)) {
						hmap.put("Status","Pending");
					}else
						hmap.put("Status","Failure");
					hmap.put("Description", descriptionVal);
				}
			}				
		}
		catch (Exception e) {
			hmap.put("Status", "failure");
			hmap.put("Description", "Technical failure.");
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
	
	public void raviresponseHandler(HttpServletRequest request) {
		try {
			String txnStatus="",vendor="CyberHub";
			String reqid=request.getParameter("reqid");
			String Status=request.getParameter("status");
			String OperatorTxnId=request.getParameter("field1");
			MobileDthRechargeDao daoObj=new MobileDthRechargeDao();
			HashMap<String,String> tranDetils=daoObj.getTranDetails(reqid, reqid, reqid);
			String agentID=tranDetils.get("user_id");
			if(Status.equalsIgnoreCase("Success"))
			{
				txnStatus="Success";
				logger.info(" Updateing start on Ravi Success >>>>>>>>>>>>>>>>>  ");
				daoObj.updateStatusTranEGEN(reqid,txnStatus,agentID,"",OperatorTxnId,vendor,"","");
				logger.info(" Updateing Stop on Ravi Success >>>>>>>>>>>>>>>  ");


			}else if(Status.equalsIgnoreCase("Pending"))
			{
				txnStatus="Pending";
				logger.info(" Updateing start on Ravi Pending >>>>>>>>>>>>>>>>>  ");
				daoObj.updateStatusTranEGEN(reqid,txnStatus,agentID,"",OperatorTxnId,vendor,"","");
				logger.info(" Updateing Stop on Ravi Success >>>>>>>>>>>>>>>  ");


			}else if(Status.equalsIgnoreCase("Failed")||Status.equalsIgnoreCase("FREQUENT") ||Status.equalsIgnoreCase("REFUND"))
			{
				txnStatus="Failure";
				logger.info(" Updateing start on Ravi "+Status+" >>>>>>>>>>>>>>>>>  ");
				daoObj.updateStatusTranEGEN(reqid,txnStatus,agentID,"",OperatorTxnId,vendor,"","");
				logger.info(" Updateing Stop on Ravi Success >>>>>>>>>>>>>>>  ");

			}else 
			{
				//		
				txnStatus="Pending";
				daoObj.updateStatusTranEGEN(reqid,txnStatus,agentID,"",OperatorTxnId,vendor,"","");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
