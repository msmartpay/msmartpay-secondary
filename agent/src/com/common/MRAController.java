package com.common;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.core.MediaType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.smartkinda.model.AEPSForm;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.client.urlconnection.HTTPSProperties;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.MultiPart;
import com.sun.jersey.multipart.file.FileDataBodyPart;

public class MRAController 
{

	public static org.json.JSONObject userServiceEnquiry(String userCode) {
		org.json.JSONObject respObj=null;
		try {
			String url=PropertyFile.USER_ENQUIRY+":"+userCode+"?initiator_id="+PropertyFile.Initiator_id;
			System.out.println("url "+url);
			String respString=ekoCall(url, null, "GET");
			respObj=new org.json.JSONObject(respString);
			System.out.println("Response  "+respObj);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return respObj;
	}
	public static String activateAEPS(String data,AEPSForm bean) {
		String respString="";
		try {
			System.out.println("data : "+data);
			String baseUrl = PropertyFile.ACTIVATE_USER;
			
			Client client = getClient();
			WebResource service = client.resource(baseUrl);
			Builder builder = service.accept(MediaType.APPLICATION_JSON);
			builder.header("developer_key", PropertyFile.DEVELOPER_KEY);
			builder.header("secret-key", PropertyFile.secret_key);
			builder.header("secret-key-timestamp",PropertyFile.secret_key_timestamp);
			
			FileDataBodyPart aadharFrontFile=null,aadharBackFile=null,voterIdFrontFile=null,voterIdBackFile=null;
			
			FileDataBodyPart panFile1 = new FileDataBodyPart("pan_card", bean.getPanCard());
			panFile1.setContentDisposition(FormDataContentDisposition.name("pan_card").fileName(bean.getPanCardFileName()).build());
			
			
			
			MultiPart multipartEntity = new FormDataMultiPart().field("form-data", data, MediaType.APPLICATION_JSON_TYPE);
			multipartEntity.bodyPart(panFile1);
			
			if(bean.getAadhaarFront()!=null) {
				aadharFrontFile = new FileDataBodyPart("aadhar_front", bean.getAadhaarFront());
				aadharFrontFile.setContentDisposition(FormDataContentDisposition.name("aadhar_front").fileName(bean.getAadhaarFrontFileName()).build());
			
				if(bean.getAadhaarBack()!=null) {
					aadharBackFile = new FileDataBodyPart("aadhar_back", bean.getAadhaarBack());
					aadharBackFile.setContentDisposition(FormDataContentDisposition.name("aadhar_back").fileName(bean.getAadhaarBackFileName()).build());
					multipartEntity.bodyPart(aadharFrontFile);
					multipartEntity.bodyPart(aadharBackFile);
				}
			}
			if(bean.getVoterIdFront()!=null) {
				voterIdFrontFile = new FileDataBodyPart("voter_card_front", bean.getVoterIdFront());
				voterIdFrontFile.setContentDisposition(FormDataContentDisposition.name("voter_card_front").fileName(bean.getVoterIdFrontFileName()).build());
			
				if(bean.getVoterIdBack()!=null) {
					voterIdBackFile = new FileDataBodyPart("voter_card_back", bean.getVoterIdBack());
					voterIdBackFile.setContentDisposition(FormDataContentDisposition.name("voter_card_back").fileName(bean.getVoterIdBackFileName()).build());
					multipartEntity.bodyPart(voterIdFrontFile);
					multipartEntity.bodyPart(voterIdBackFile);
				}
			}
			
			
			respString = builder.type(MediaType.MULTIPART_FORM_DATA).put(String.class, multipartEntity);
			System.out.println("Response : " + respString);
			
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return respString;
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
	
	public static JSONObject sendAPIRechargeRequest(String Op,String amt,
			String cn,String reqId,String ip,String service,String agentId,String key) {
		JSONObject response=null;
		JSONObject obj=new JSONObject();
		obj.put("agent_id", agentId);
		obj.put("txn_key", key);
		obj.put("OpCode", Op);
		obj.put("mobile_no", cn);
		obj.put("amount", amt);
		obj.put("service", service);
		obj.put("request_id", reqId);
		obj.put("IP", ip);

		String url="SKPRE/Recharge";
		response=webservicecall(obj ,url);

		return response;
	}

	public static JSONObject sendAPIBillpayRequest(String Op,String amt,String cn,String reqId,String ad1,String ad2,String ad3,
			String ad4,String opName,String reference_id,String service,String agentId,String key) {
		
		JSONObject response=null;
		try {
			
			JSONObject obj=new JSONObject();
			obj.put("agent_id", agentId);
			obj.put("txn_key", key);
			obj.put("OP", Op);
			obj.put("OPName", opName);
			obj.put("CN", cn);
			obj.put("AMT", amt);
			obj.put("service", service);
			obj.put("REQUEST_ID", reqId);
			obj.put("AD4", ad4);
			obj.put("AD1", ad1);
			obj.put("AD2", ad2);
			obj.put("AD3", ad3);
			obj.put("reqType", "Pay");
			obj.put("reference_id",reference_id);

			String url="POST/Billpay";
			response=webservicecall(obj ,url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return response;
	}

	public static JSONObject viewBillpayRequest(String tranId,String Op,String cn,String amt,
			String ad1,String ad2,String ad3,String ad4,String OPName,String service,String agentId,String key) {
		JSONObject response=null;
		
		try {
			JSONObject obj=new JSONObject();
			obj.put("agent_id", agentId);
			obj.put("txn_key", key);
			obj.put("OP", Op);
			obj.put("OPName", OPName);
			obj.put("CN", cn);
			obj.put("AMT", amt);
			obj.put("service", service);
			obj.put("REQUEST_ID", tranId);
			obj.put("AD4", ad4);
			obj.put("AD1", ad1);
			obj.put("AD2", ad2);
			obj.put("AD3", ad3);
			obj.put("reqType", "ViewBill");
			obj.put("reference_id", "");

			String url="POST/Billpay";
			response=webservicecall(obj ,url);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return response;
	}


	public static List<HashMap<String,String>> fetchOperator(JSONObject obj,String agentId,String key){
		List<HashMap<String,String>> list=new ArrayList<HashMap<String,String>>();

		JSONObject respObj=new JSONObject();
		try {

			obj.put("agent_id", agentId);
			obj.put("txn_key", key);
			obj.put("service", "all");

			System.out.println("Request :: "+obj.toString());
			respObj=webservicecall(obj, "POST/billpayOperator");
			if(respObj!=null && respObj.get("response-code").toString().equalsIgnoreCase("0")){
				JSONArray jsonArray=(JSONArray)respObj.get("data");

				for (int i = 0; i < jsonArray.size(); i++) {

					JSONObject operator=(JSONObject)jsonArray.get(i);

					HashMap<String,String> opMap=new HashMap<>();
					opMap.put("Service", operator.get("Service")+"");
					opMap.put("OpCode", operator.get("OpCode")+"");
					opMap.put("BillFetch", operator.get("BillFetch")+"");
					opMap.put("OperatorName", operator.get("OperatorName")+"");
					opMap.put("DisplayName", operator.get("DisplayName")+"");
					list.add(opMap);
				}

			}else
				return null;

		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	public static JSONObject webservicecall(JSONObject obj ,String url) {
		JSONObject json_obj=null;
		try {

			Client client = getClient();

			String fUrl=PropertyFile.MRA_URL+url;
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


		} catch (Exception e) {

			e.printStackTrace();

		}
		return json_obj;
	}
	
	public static String ekoCall(String url,String data,String method){

		String inputLine = "";
		System.out.println("url :: "+url);
		System.out.println("data :: "+data);
		try {


			// Create a trust manager that does not validate certificate chains
			TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					return null;
				}

				public void checkClientTrusted(X509Certificate[] certs, String authType) {
				}

				public void checkServerTrusted(X509Certificate[] certs, String authType) {
				}
			} };

			// Install the all-trusting trust manager
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

			// Create all-trusting host name verifier
			HostnameVerifier allHostsValid = new HostnameVerifier() {
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			};

			// Install the all-trusting host verifier
			HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
			URL myurl = new URL(url);
			HttpsURLConnection con = (HttpsURLConnection) myurl.openConnection();
			con.setRequestMethod(method);

			// con.setRequestProperty( "Content-Type",
			// "application/x-www-form-urlencoded");
			con.setReadTimeout(120000);
			con.setConnectTimeout(120000);

			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			con.setRequestProperty("Accept", "application/json");
			// con.setRequestProperty( "developer_key",
			// PropertyFile.developer_key);
			con.setRequestProperty("developer_key", PropertyFile.DEVELOPER_KEY);
			con.setRequestProperty("secret-key", PropertyFile.secret_key);
			con.setRequestProperty("secret-key-timestamp", PropertyFile.secret_key_timestamp);
			con.setUseCaches(false);

			if(!"GET".equalsIgnoreCase(method)){
				con.setDoInput(true);
				con.setDoOutput(true);
				DataOutputStream wr = new DataOutputStream(con.getOutputStream());
				wr.writeBytes(data.toString());
				wr.flush();
				wr.close();
			}

			StringBuilder stringBuilder = new StringBuilder();
			int responseCode = con.getResponseCode();
			InputStream ins = con.getInputStream();
			InputStreamReader isr = new InputStreamReader(ins);
			BufferedReader in = new BufferedReader(isr);

			while ((inputLine = in.readLine()) != null) {
				stringBuilder.append(inputLine);

			}

			in.close();
			System.out.println(stringBuilder.toString());
			
			return stringBuilder.toString();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}


}
