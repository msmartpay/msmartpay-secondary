package com.utility;



import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.common.PropertyFile;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.client.urlconnection.HTTPSProperties;

public class WebServicesUtility 
{
	public static String GetText(Element inNode, String nodeName)
	{	
		String value="NA";
		if(inNode!=null)
		{
			NodeList tempNodes = inNode.getElementsByTagName(nodeName);
			if (tempNodes.getLength() == 0)
				value="NA";      
			else
			{		 	
				value = tempNodes.item(0).getNodeValue();
				if(value.length()<1)
					value="NA";
			}
		}		 	
		return value;
	}

	public static Element string2Element(String s) throws ParserConfigurationException, SAXException, IOException
	{
		return org.apache.axis.utils.XMLUtils.newDocument(
				new org.xml.sax.InputSource(new StringReader(s))).getDocumentElement();
	}

	public static String checkImagePath(String path)
	{
		if(!path.contains("http://"))
			path="http://"+path;
		else if(path.contains("http//"))
			path.replace("http//","http://");

		return path;
	}

	public static String getNodeContent(Node node,String attributeName)
	{
		NamedNodeMap hotelnodeattribute=node.getAttributes();
		Node nodehotelid=hotelnodeattribute.getNamedItem(attributeName);
		String nodevalue = nodehotelid.getNodeValue();

		return nodevalue;
	}

	public static void print(String arg)
	{
		System.out.println(arg);
	}

	public static ArrayList sortArrayHash(ArrayList array)
	{
		int arraylength =  array.size();		 
		for(int arraycounter=0;arraycounter<arraylength-1;arraycounter++)
		{			
			for(int arraycounter2=arraycounter+1;arraycounter2<arraylength;arraycounter2++)
			{
				HashMap hash1= (HashMap) array.get(arraycounter);
				HashMap hash2= (HashMap) array.get(arraycounter2);
				String val1= (String)hash1.get("MinPrice");
				String val2= (String)hash2.get("MinPrice");
				if(Double.parseDouble(val1)>Double.parseDouble(val2))
				{
					//print(val1+"  :  "+val2);
					array.remove(arraycounter);
					array.add(arraycounter, hash2);
					array.remove(arraycounter2);
					array.add(arraycounter2, hash1);										
				}				
			}			
		}
		return array; 
	}

	public static String elementToString(Element response)
	{
		try
		{
			DOMSource domSource = new DOMSource(response);
			StringWriter writer = new StringWriter();
			StreamResult result = new StreamResult(writer);
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.transform(domSource, result);
			return writer.toString();
		}
		catch(TransformerException ex)
		{
			ex.printStackTrace();
			return null;
		}
	}
	
	public static String instantPayBillpayStatusCheck(String reqId) {
		
		JSONObject apiResponse=null;
		try {
			
			JSONObject reqObj=new JSONObject();
			reqObj.put("REQUEST_ID", reqId);
			
			String fUrl=PropertyFile.SSZPL_URL;
			
			apiResponse=WebServicesUtility.webservicecall(reqObj, fUrl);
			
			if(apiResponse==null){
				apiResponse=new JSONObject();
				apiResponse.put("statuscode", "1");
				apiResponse.put("status", "Status not found");
			}

			
		} catch (Exception e) {
			// TODO: handle exception
			apiResponse=new JSONObject();
			apiResponse.put("statuscode", "1");
			apiResponse.put("status", "Technical failure");
		}
		return apiResponse.toString();
	}
	public static String iciciStatusCheck(String reqId) {
		
		JSONObject apiResponse=null;
		try {
			
			JSONObject reqObj=new JSONObject();
			
			String fUrl=PropertyFile.ICICI_STATUS_URL+"/"+reqId;
			
			if(apiResponse==null){
				apiResponse=new JSONObject();
				apiResponse.put("statuscode", "1");
				apiResponse.put("status", "Status not found");
			}
			apiResponse=WebServicesUtility.webServiceGetCall(fUrl);
			
			if(apiResponse==null){
				apiResponse=new JSONObject();
				apiResponse.put("statuscode", "1");
				apiResponse.put("status", "Status not found");
			}

			
		} catch (Exception e) {
			// TODO: handle exception
			apiResponse=new JSONObject();
			apiResponse.put("statuscode", "1");
			apiResponse.put("status", "Technical failure");
		}
		return apiResponse.toString();
	}
	
	public static JSONObject webServiceGetCall(String fUrl) {
		JSONObject json_obj=null;
		try {

			String[] urlArr=fUrl.split(":");
			String uri=urlArr[0];
			
			Client client =null;
			if(uri.contains("https"))
				client=getClient();
			else
				Client.create();
			
			System.out.println("URL :: "+fUrl);

			WebResource webResource = client.resource(fUrl);

			ClientResponse response = webResource.type("application/json").get(ClientResponse.class);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}

			String output = response.getEntity(String.class);
			JSONParser parser = new JSONParser();
			Object object = parser.parse(output);
			json_obj = (JSONObject) object;

			System.out.println("output :: "+output);
			//logger.info("Server response : \n");
			System.out.println(output);


		} catch (Exception e) {

			e.printStackTrace();

		}
		return json_obj;
	}
	
	public static JSONObject webservicecall(JSONObject obj ,String fUrl) {
		JSONObject json_obj=null;
		try {

			String[] urlArr=fUrl.split(":");
			String uri=urlArr[0];
			
			Client client =null;
			if(uri.contains("https"))
				client=getClient();
			else
				Client.create();
			
			System.out.println("URL :: "+fUrl);

			WebResource webResource = client.resource(fUrl);

			ClientResponse response = webResource.type("application/json").post(ClientResponse.class,obj.toString());

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}

			String output = response.getEntity(String.class);
			JSONParser parser = new JSONParser();
			Object object = parser.parse(output);
			json_obj = (JSONObject) object;

			System.out.println("output :: "+output);
			//logger.info("Server response : \n");
			System.out.println(output);


		} catch (Exception e) {

			e.printStackTrace();

		}
		return json_obj;
	}
	
	private static Client getClient() {
		Client client=null;
		try {
			// Create a trust manager that does not validate certificate chains
			final TrustManager[] trustAllCerts = new TrustManager[] {
					new X509TrustManager() {
						@Override
						public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
						}

						@Override
						public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
						}

						@Override
						public java.security.cert.X509Certificate[] getAcceptedIssuers() {
							return new java.security.cert.X509Certificate[]{};
						}
					}
			};

			HostnameVerifier hostnameVerifier = HttpsURLConnection.getDefaultHostnameVerifier();
			ClientConfig config = new DefaultClientConfig();
			SSLContext ctx = SSLContext.getInstance("SSL");
			ctx.init(null, trustAllCerts, null);
			config.getProperties().put(HTTPSProperties.PROPERTY_HTTPS_PROPERTIES, new HTTPSProperties(hostnameVerifier, ctx));
			client = Client.create(config);
			return client;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void main(String[] args) {
		
		System.out.println("Response : "+instantPayBillpayStatusCheck("14920610403167"));
		
	}
}
