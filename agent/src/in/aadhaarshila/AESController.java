package in.aadhaarshila;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.rmi.RemoteException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.xml.rpc.ServiceException;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class AESController {

	Logger logger=Logger.getLogger(AESController.class);
	
	final static String AgentAuthId="Pay Enething Services Pvt";
	final static String AgentAuthPassword="d1rth7avsx";
	final static String BASE_URL="https://services.myaadhaarshila.com:8443/AESAPI/resources/AESAPI/";

	public HashMap<String,Object> adhaarshilaRechargeRequest(String service,String op,String cn,String amt,String tranId) {
		HashMap<String, Object> map=new HashMap<String, Object>();


		try {
			JSONObject obj=new JSONObject();
			obj.put("AgentAuthId",AgentAuthId);
			obj.put("AgentAuthPassword", AgentAuthPassword);
			obj.put("ST", service);
			obj.put("CN", cn);
			obj.put("OP", op);
			obj.put("AMT", amt);
			obj.put("CIR", "ALL");
			//obj.put("AD1", "");
			//obj.put("AD2", "");
			//obj.put("AD3", "");
			//obj.put("AD4", "");
			obj.put("REQID", tranId);

			String response=callAdhaarshila("https://services.myaadhaarshila.com:8443/AESAPI/resources/AESAPI/"+"Recharge", obj.toString());
			//String response="{\"ST\":\"mobile\",\"AgentAuthId\":\"Pay Enething Services Pvt\",\"AMT\":\"10\",\"REQID\":\"2343444242342432424\",\"OP\":\"1102\",\"CN\":\"9716025028\",\"CIR\":\"ALL\",\"AgentAuthPassword\":\"d1rth7avsx\",\"Status\":\"1\",\"Description\":\"Your connection Number is Recharged Successfully.\",\"apiTranId\":\"13092016100069055311\",\"updatedBalance\":\"951.0\"}";

			if(response!=null){
				JSONParser parser=new JSONParser();
				JSONObject jsonObject=(JSONObject) parser.parse(response);

				//logger.info("AESController.adhaarshilaRechargeRequest() : "+jsonObject.toString());

				map.put("InputXML",obj.toString());
				//map.put("ResoponseXML",response);
				map.put("ResoponseXML",jsonObject.toString());
				if(jsonObject!=null){
					String Status =(String) jsonObject.get("Status");

					if(Status.equalsIgnoreCase("1")){
						map.put("Status","Success");
						map.put("Description",jsonObject.get("Description"));
						map.put("refId",jsonObject.get("REQID"));
						map.put("aesRefId",jsonObject.get("apiTranId"));

					}			
					else if(Status.equalsIgnoreCase("0")){
						map.put("Status","Failure");
						map.put("Description",jsonObject.get("Description"));
						map.put("refId",jsonObject.get("REQID"));
						map.put("aesRefId",(jsonObject.get("apiTranId")==null?"":jsonObject.get("apiTranId")));
					}
					else{
						map.put("Status","Pending");
						map.put("Description",jsonObject.get("Description"));
						map.put("refId",jsonObject.get("REQID"));
						map.put("aesRefId",jsonObject.get("apiTranId"));
					}
				}
			}else{
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		logger.info("AESController.adhaarshilaRechargeRequest() : "+map);
		return map;
	}
	public static void main(String[] args) {
		new AESController().adhaarshilaRechargeRequest("mobile", "1102", "9716025028", "10", "2343444232342432444");
	}

	public HashMap<String,Object> adhaarshilaBillpyRequest(String mobile,String op,String cn,String amt,String tranId) {
		HashMap<String, Object> map=null;

		try {

		} catch (Exception e) {
			e.printStackTrace();
		}

		return map;
	}

	public HashMap<String,Object> adhaarshilaStatusRequest(String mobile,String op,String cn,String amt,String tranId) {
		HashMap<String, Object> map=null;

		try {

		} catch (Exception e) {
			e.printStackTrace();
		}

		return map;
	}

	public HashMap<String,Object> adhaarshilaViewBillRequest(String mobile,String op,String cn,String amt,String tranId) {
		HashMap<String, Object> map=null;

		try {

		} catch (Exception e) {
			e.printStackTrace();
		}

		return map;
	}

	public String callAdhaarshila(String url,String data) throws RemoteException {
		String response =null;
		try {

			ServiceLocator locator = new ServiceLocator(); 
			ServiceSoapStub myStub = (ServiceSoapStub) locator.getServiceSoap();

			response = myStub.redirect(url, data);
			logger.info("callAdhaarshila : "+response);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} 


		return response;
	}

	public JSONObject clientApiCall(String requestType,JSONObject obj){

		JSONObject jsonObject = null;
		String baseUrl = null;
		String inputLine="";
		try{

			String url=BASE_URL+requestType;
			logger.info("WhiteLabelClientApiCall.clientApiCall() URL : "+url);

			// Create a trust manager that does not validate certificate chains
			TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					return null;
				}
				public void checkClientTrusted(X509Certificate[] certs, String authType) {
				}
				public void checkServerTrusted(X509Certificate[] certs, String authType) {
				}
			}
			};

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
			con.setRequestMethod("POST");
			//con.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded");
			con.setRequestProperty("Content-Type", "application/json");

			con.setDoInput(true); //we want feedback
			con.setUseCaches(false); //no caches
			// Send post request
			con.setDoOutput(true);//we will send stuff
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(obj.toString());
			wr.flush();
			wr.close();
			StringBuilder stringBuilder = new StringBuilder();
			int responseCode = con.getResponseCode();

			InputStream ins = con.getInputStream();
			InputStreamReader isr = new InputStreamReader(ins);
			BufferedReader in = new BufferedReader(isr);

			while ((inputLine = in.readLine()) != null)
			{
				stringBuilder.append(inputLine);

			}
			JSONParser parser2 = new JSONParser();
			jsonObject = (JSONObject) parser2.parse(stringBuilder.toString());
			logger.info("WhiteLabelClientApiCall.main() : "+jsonObject.toString());


		}catch(Exception e){
			e.printStackTrace();
			return null;
		}

		return jsonObject;
	}



	/*public HashMap<String,Object> convertJSONToHashMap() {

	}*/
}
