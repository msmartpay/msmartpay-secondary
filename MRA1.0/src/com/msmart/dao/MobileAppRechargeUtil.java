package com.msmart.dao;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.NumberFormat;
import java.util.HashMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXParseException;

import sun.misc.BASE64Encoder;

public class MobileAppRechargeUtil {
	static final Logger logger = Logger.getLogger(MobileAppRechargeUtil.class);

	MobileAppRechargeDao mad=null;

	public HashMap<String, String> getActualRechAmonut(String agent_Id,String rechargeamount,String operator,String dist_id,String service)
	{
		HashMap<String, String> rechMap=new HashMap<String, String>();
		mad=new MobileAppRechargeDao();
		double rech_amt=Double.parseDouble(rechargeamount);

		String comm=mad.getAgentCommission_Agent(agent_Id,rechargeamount,operator,service);
		double agntcom=Double.parseDouble(comm);

		agntcom=Math.floor(agntcom * 1e4) / 1e4;
		//		String a=String.valueOf(agentcommision);

		//		DecimalFormat df = new DecimalFormat("##.#### ");
		//		double deductedAmount=rech_amt-agntcom;
		double deductedAmount=Math.floor((rech_amt-agntcom) * 1e4) / 1e4;

		System.out.println("Commission gained  "+agntcom);
		String act_rech_amount=""+(deductedAmount);

		rechMap.put("act_rech_amount",act_rech_amount);
		rechMap.put("comm_gain",""+agntcom);
		System.out.println("Map Value   "+rechMap);
		rechMap.put("dist_comm_gain","0.0");

		return rechMap;
	}


	public HashMap<String, String> serviceTransaction(String transaction_id,String operator,String area_circle,String mobile_no,String act_rech_amt,String service,String agent_detail)
	{
		HashMap<String, String> cpauth = null;
		String trans_url = (new StringBuilder("http://api.commsoft.co.in/MNDR/SERAPI?SEROPTR=")).append(operator).append("&SEROPTRCIR=").append(area_circle).append("&SEROPTRSUBACNO=").append(mobile_no).append("&TXNAMT=").append(act_rech_amt).append("&OPTRTP=4&SERTP=").append(service).append("&APICORPAGTXNAUTHID=Rishi123&APICORPAGTXNPWD=534fc28a8f1e2344d04383ee7de0975dbefc98a5&APICORPSAGTXNAUTHID=222843&APICORPSAGTXNAUTHPWD=123456&APICORPSAGREGMOBNO=").append(agent_detail).append("&APICORPUTXNID=").append(transaction_id).toString();
		System.out.println((new StringBuilder("Request Url ")).append(trans_url).toString());
		String urlResponse = null;
		try
		{
			urlResponse = post(trans_url, "text/xml", "GET");
			System.out.println((new StringBuilder("Response ")).append(urlResponse).toString());
			cpauth = GetCyberText(urlResponse);
			if(cpauth.size() < 2)
				throw new NullPointerException();
			logger.info("Success serviceTransaction()<<<<<<<<<<<<<<<<<<<");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in MobileAppRechargeUtil");
			urlResponse="<?xml version='1.0' encoding='utf-8'?><Response><Response-code>144</Response-code><Response-status>PENDING</Response-status></Response>";
			cpauth=GetCyberText(urlResponse);
			logger.info("Exception serviceTransaction()<<<<<<<<<<<<<<<<<<<"+e);
		}
		System.out.println("serviceTransaction Hmap "+cpauth);
		return cpauth;
	}

	public String post(String urlLocation,String requestContentType, String requestMethod) throws IOException {
		String response = null;
		logger.info("Start post()>>>>>>>>>>>>>>>>>>>");
		BufferedReader reader = null;
		OutputStreamWriter writer = null;
		HttpURLConnection connection = null;
		URL url =null;

		try {
			BASE64Encoder encoder = new BASE64Encoder();

			String password = "bharat"; 
			String authString = "Basic"+encoder.encode(password.getBytes());

			url= new URL(urlLocation);
			connection = (HttpURLConnection) url.openConnection();
			connection.setConnectTimeout(220000);
			connection.setReadTimeout(220000);
			connection.setDoOutput(true);
			connection.setRequestMethod(requestMethod);
			connection.setRequestProperty("Authorization", authString);

			if (requestContentType != null) {
				logger.info("requestContentType "+requestContentType);
				connection.setRequestProperty("Content-Type",requestContentType);
			}

			StringBuilder sb = new StringBuilder();
			String line = null;

			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			while ((line = reader.readLine()) != null)
			{
				sb.append(line);
			}
			response = sb.toString();
			logger.info("Success post()<<<<<<<<<<<<<<<<<<< "+response);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			logger.info("Exception post()<<<<<<<<<<<<<<<<<<<"+ex);
		}
		finally {

			url=null;
			try
			{
				if (writer != null) {
					writer.close();
				}
				if (reader != null) {
					reader.close();
				}
				if (connection != null) {
					connection.disconnect();
				}
			} catch (Exception e)
			{
				logger.info("Exception occurs in post()"+e);
			}
		}
		return response;
	}

	public HashMap<String,String> GetCyberText(String xmlRecords){

		logger.info("Start GetCyberText()>>>>>>>>>>>>>>>>>>>");
		HashMap<String,String> hmap=new HashMap<String,String>();

		try {

			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();

			InputStream is = new ByteArrayInputStream(xmlRecords.getBytes());
			Document doc = docBuilder.parse(is);

			doc.getDocumentElement ().normalize ();

			NodeList Nodenode = doc.getElementsByTagName("Response");
			int numNodenode = Nodenode.getLength();

			if(numNodenode>0)
			{
				for(int s=0; s<Nodenode.getLength() ; s++)
				{
					Node firstNode = Nodenode.item(s);
					if(firstNode.getNodeType() == Node.ELEMENT_NODE){

						Element firstElement = (Element)firstNode;

						NodeList res_code_List = firstElement.getElementsByTagName("Response-code");
						Element res_code_Element = (Element)res_code_List.item(0);

						NodeList textres_code_List = res_code_Element.getChildNodes();
						hmap.put("res_code",((Node)textres_code_List.item(0)).getNodeValue().trim());

						NodeList res_status_List = firstElement.getElementsByTagName("Response-status");
						Element res_status_Element = (Element)res_status_List.item(0);

						NodeList textres_status_List = res_status_Element.getChildNodes();
						hmap.put("res_msg", ((Node)textres_status_List.item(0)).getNodeValue().trim());

					}
				}
			}
			else
			{
				System.out.println("else block executed");
			}
		}catch (SAXParseException err) {
			logger.info(" " + err.getMessage ());
		}
		catch (Exception e) {
			logger.info("Exception GetCyberText()<<<<<<<<<<<<<<<<<<<"+e);
			e.printStackTrace();
		}
		logger.info("Success GetCyberText()<<<<<<<<<<<<<<<<<<<<<<");
		return hmap;
	}

	public String decimalFormat(double value)
	{
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(4);
		nf.setGroupingUsed(false);
		String formattedvalue = nf.format(value);

		return formattedvalue;


	}

}
