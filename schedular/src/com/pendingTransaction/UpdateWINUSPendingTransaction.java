
package com.pendingTransaction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.dbConnection.DBConnection;

public class UpdateWINUSPendingTransaction  implements Job {

	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {

		System.out.println("UpdateWINUSPendingTransaction.execute() >>>>>>>>>>>>>>>>>>>>>>>>>>>  started");
		System.out.println(new Date());
		updateWinusPendingTransaction();
		System.out.println("UpdateWINUSPendingTransaction.execute() >>>>>>>>>>>>>>>>>>>>>>>>>>>  Stoped");
		System.out.println(new Date());
	}

	
	public void updateWinusPendingTransaction() {
		
		System.out.println("UpdateWINUSPendingTransaction.updateWinusPendingTransaction() VENUS started ............");
		UpdateRechPendingTransactionDao dao=new UpdateRechPendingTransactionDao();
		
		try {
			HashMap<String, String> map=null;
			ArrayList<HashMap<String,String>> newlist=new ArrayList<HashMap<String,String>>();
			ArrayList<HashMap<String,String>> list=dao.updateWinusPendingTransaction();
			System.out.println("updateWinusPendingTransaction Pending List >>>>>>>>>>>>>>>>>>>>>>> "+list);
			
			if(list!=null && list.size()>0){
				
				for (int i = 0; i < list.size(); i++) {
					map=list.get(i);
					String Merchantrefno=map.get("tran_id");
					String OrderNo="";
					
					HashMap<String, String> resMap=getWinusRequestStatus(Merchantrefno,OrderNo);
					System.out.println("WINUS Response : "+resMap);
					if(resMap!=null){
						if(resMap.containsKey("Status") && (resMap.get("Status").equalsIgnoreCase("Success")||resMap.get("Status").equalsIgnoreCase("Failure"))){
							map.put("Status", resMap.get("Status"));
							map.put("OrderNo", OrderNo);
							newlist.add(map);
						}
					}
				}
				// Success list
				String ERROR="",transactionId="",agentID="",checkVendor="WINUS",ResoponseXML="Updated by schedular",InputXML="";
				for (int i = 0; i < newlist.size(); i++) {
					HashMap<String, String> newMap=newlist.get(i);
					agentID=newMap.get("user_id");
					transactionId=newMap.get("tran_id");
					String OperatorTxnId="";
					String TranStatus=newMap.get("Status");
					String updateStatus="";
					if(TranStatus.equalsIgnoreCase("success")){
						updateStatus="Success";
						OperatorTxnId=newMap.get("OrderNo");
					}
					else if(TranStatus.equalsIgnoreCase("Failure"))
						updateStatus="Failure";
					
					if(updateStatus.length()>0 && transactionId.length()>0 ){
						String updatestatus=dao.updateStatusTranEGEN(transactionId,updateStatus,agentID,ERROR,OperatorTxnId,checkVendor,InputXML,ResoponseXML);
				
						System.out.println("UpdateRechPendingTransaction.updateRechPendingTransaction() schedular update >> "+updatestatus);
				
					}
				}
			}
			
			System.out.println("UpdateRechPendingTransaction.updateMARSRechPendingTransaction() VENUS End ............");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public  HashMap<String,String> getWinusRequestStatus(String Merchantrefno,String OrderNo) throws ParserConfigurationException, SAXException, IOException 
	{		
		HashMap<String,String> result =new HashMap<String,String>();
		String searchResponse=null;
		String url="";
		try
		{
			if(OrderNo==null)
				OrderNo="";
			if("FAILED".equalsIgnoreCase(OrderNo))
				OrderNo="";
			
			url = "http://venusrecharge.co.in/TransactionStatus.aspx?authkey=10008&authpass=Arpit@123&ServiceType=&OrderNo="+OrderNo+"&Merchantrefno="+Merchantrefno;
			result.put("InputXML", url);
			System.out.println("Input request from go proccess is: "+url);
	        searchResponse = getResponseFromSmart(url, null, "text/xml", "GET", null);
	        System.out.println("searchResponse for egen :"+searchResponse);
            
			result=GetRespText(searchResponse,url);
		}
		catch(Exception e)
		{
			searchResponse="Suspense=>9804543159=>10=>39137486470=>16100319342500784 =>470.00";			
			result=GetRespText(searchResponse,url);
			//System.out.println("exception in EgenControler"+e.toString());
		}	      				
	  return result;	  
	}
	
	
	HashMap<String, String> GetRespText(String rechargeResponse,String url)
	{		
		//rechargeResponse="Success=>9804543159=>10=>39137486470=>16100319342500784 =>470.00";
		HashMap<String, String> hmap=new HashMap<String, String>();
		String refId="",smartRefId="";
		try 
		{			
			DocumentBuilderFactory dbf =DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			String chkNode="";
			is.setCharacterStream(new StringReader(rechargeResponse));
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
					}else if(statusVal.equalsIgnoreCase("FAILED")||statusVal.equalsIgnoreCase("FAILURE")){
						hmap.put("Status", "failure");
					}else{
						hmap.put("Status", "Pending");
					}
					
					hmap.put("responseCode",statusVal); 
					NodeList MerTxnID = element.getElementsByTagName("MerTxnID");
					if(MerTxnID!=null){
						line = (Element) MerTxnID.item(0);
						String txId= getCharacterDataFromElement(line);
						hmap.put("refId",txId); 
					}else{
						hmap.put("refId","");
					}
					NodeList OperatorTxnID = element.getElementsByTagName("OperatorTxnID");
					if(OperatorTxnID!=null){
						
						line = (Element) OperatorTxnID.item(0);
						String OperatorTxnIDStr= getCharacterDataFromElement(line);
						hmap.put("OperatorTxnId",OperatorTxnIDStr);
					}else{
						hmap.put("OperatorTxnId","");
					}
				}
			}				
		}
		catch (Exception e) {
			e.printStackTrace();
			hmap.put("Status", "Pending");
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
	public String getResponseFromSmart(String urlLocation, String postMessage,String requestContentType, String requestMethod,
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
			 // create the HttpURLConnection
			 url = new URL(desiredUrl);
			 HttpURLConnection connection = (HttpURLConnection) url.openConnection();	      
			 // just want to do an HTTP GET here
			 connection.setRequestMethod("GET");	   
			 //connection.setDoOutput(true);	      
			 // give it 15 seconds to respond
			 connection.setReadTimeout(220000);
			 connection.connect();
			 // read the output from the server
			 
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
	
	public static String getVendorIp() 
	{
		
		System.out.println("getVendorIp >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ");
		Connection con=null;
		Statement pstmt=null;
		ResultSet rst=null;
		String ip=null;
		try
		{
			con=DBConnection.getConnection();
			String sqlQurry="select IP from Vendor_IP_Details";
			System.out.println("sqlQurry : "+sqlQurry);
			pstmt=con.createStatement();
			rst=pstmt.executeQuery(sqlQurry);
			
			while(rst.next())
			{
				ip=rst.getString("IP");
			}
			System.out.println("getVendorIp()>>>>>>>>>>>>>>>>>>>>>>  ip : "+ip);
			
		}catch(Exception exc)
		{
			exc.printStackTrace();
			System.out.println("Exception getVendorIp "+exc);
		}
		finally
		{
			try{
				if(rst!=null)
				{
					rst.close();
					rst=null;
				}
				if(pstmt!=null)
				{
					pstmt.close();
					pstmt=null;
				}
				if(con!=null)
				{
					con.close();
					con=null;
				}
			}catch(Exception exc){
				
			}
		}
		return ip;
	}
}
