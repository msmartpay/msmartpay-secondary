package com.msmart.ekonew;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.log4j.Logger;

import com.msmart.service.PropertyFile;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class EkoAPICall {

	Logger log=Logger.getLogger(EkoAPICall.class);
	
	public JSONObject getAllBeneficiary(String mobile,String initiator_id,String type,String vendorSatus,String ekoUsrCode) {
		log.info("Execute getAllBeneficiary method of dmr_customer_dao class");
		JSONObject finalObject=new JSONObject();
		JSONArray jsonArray=new JSONArray();
		String url = PropertyFile.ekokycurl + mobile+ "/recipients?initiator_id=" + initiator_id + "&user_code="+ekoUsrCode;
		JSONObject returnobj = ekoCall(url,"","GET");
		JSONObject jsonObject = null;
		try {
			if (returnobj != null) {
				int status = (int) returnobj.get("status");
				if (status == 0 && returnobj.containsKey("data") /*&& !returnobj.isNull("data")*/) {
					JSONObject dataobj = (JSONObject) returnobj.get("data");
					JSONArray recipientlist = (JSONArray) dataobj.get("recipient_list");
					String Recipient_id_type = "";
					String account = "";
					String ifsc = "";
					

					log.info("recipientlist" + recipientlist.size());
					for (int i = 0; i < recipientlist.size(); i++) {
						jsonObject = new JSONObject();
						JSONObject json_data = recipientlist.getJSONObject(i);
						int recipient_idint = (int) json_data.get("recipient_id");
						String recipient_id = String.valueOf(recipient_idint);
						Recipient_id_type = json_data.optString("recipient_id_type");
						String name = json_data.getString("recipient_name");
						String channel = json_data.getString("channel");
						account = json_data.getString("account");
						ifsc = json_data.getString("ifsc");
						String bank_name = json_data.getString("bank");
						mobile = json_data.getString("recipient_mobile");
						if(bank_name==null)
							bank_name="";
						if(mobile==null || mobile.equalsIgnoreCase("null"))
							mobile="NA";

						jsonObject.put("RecipientId", recipient_id);
						jsonObject.put("RecipientIdType", Recipient_id_type==null?"NA":Recipient_id_type);
						jsonObject.put("BeneName", name);
						jsonObject.put("Account", account);
						jsonObject.put("Ifsc", ifsc);
						jsonObject.put("BankName", bank_name);
						jsonObject.put("BeneMobile", mobile);
						jsonObject.put("IMPS", "2");
						jsonObject.put("NEFT", "1");
						
						if("Self".equalsIgnoreCase(vendorSatus))
							jsonObject.put("Channel", "1");
						else
							jsonObject.put("Channel", channel);
						
						jsonObject.put("IsVerified", json_data.opt("is_verified"));
						
						
						if(null==type)
							jsonArray.add(jsonObject);
						
						if("CCD".equalsIgnoreCase(type) && bank_name.contains("Credit Card")){
							jsonObject.put("ImageUrl", "http://agent.payenething.in/Demo/Links/images/hdfc.png");
							jsonArray.add(jsonObject);
						}
						if("Loan".equalsIgnoreCase(type) && bank_name.contains("Loan")){
							jsonObject.put("ImageUrl", "http://agent.payenething.in/Demo/Links/images/hdfc.png");
							jsonArray.add(jsonObject);
						}
					}
				} else {
					jsonArray = null;
				}
			} else {
				jsonArray = null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error in getAllBeneficiary method of dmr_customer_dao class" + e.getMessage());

			return null;
		}
		finalObject.put("recipient_list", jsonArray);
		return finalObject;
	}
	
	public JSONObject ekoCall(String url,String data,String method){

		JSONObject json_obj = new JSONObject();
		String inputLine = "";

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
			con.setRequestProperty("developer_key", PropertyFile.developer_key);
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
			json_obj=JSONObject.fromObject(stringBuilder.toString());

			return json_obj;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	public JSONObject ekoCallwithoutSecretKey(String url,String data,String method){

		JSONObject json_obj = new JSONObject();
		String inputLine = "";

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
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("developer_key", "hgasdjvqyudt32867rygbhdsa");
			con.setReadTimeout(60000);
			con.setConnectTimeout(60000);

			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			con.setRequestProperty("Accept", "application/json");
			// con.setRequestProperty( "developer_key",
			// PropertyFile.developer_key);
			con.setRequestProperty("developer_key", PropertyFile.developer_key);
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
			json_obj=JSONObject.fromObject(stringBuilder.toString());

			return json_obj;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	/*

	public JSONObject consumeEkoApi(String httpsURL, String obj) {
		log.info("executing consumeEkoApi method of Dmr_consume_eko class");
		JSONObject json_obj = null;
		URL myurl;
		StringBuilder stringBuilder;
		try {
			myurl = new URL(httpsURL);
			HttpsURLConnection con = (HttpsURLConnection) myurl.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			con.setRequestProperty("Accept-Language", "application/json");
			con.setRequestProperty("developer_key", PropertyFile.developer_key);
			con.setDoInput(true); // we want feedback
			con.setUseCaches(false); // no caches
			// Send post request
			con.setDoOutput(true);// we will send stuff
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(obj.toString());
			wr.flush();
			wr.close();
			stringBuilder = new StringBuilder();
			int responseCode = con.getResponseCode();
			log.info("responseCode:::::::::::::::"+responseCode);
			InputStream ins = con.getInputStream();
			InputStreamReader isr = new InputStreamReader(ins);
			BufferedReader in = new BufferedReader(isr);
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				stringBuilder.append(inputLine);

			}
			//log.info("Transaction Response : "+stringBuilder.append(inputLine));
			in.close();
			json_obj=JSONObject.fromObject(stringBuilder.toString());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("Error in consumeEkoApi method of Dmr_consume_eko class"+e.getMessage());
		}
		return json_obj;
	}

	public JSONObject consumeEkoApiGet(String httpsURL) {
		log.info("executing consumeEkoApiGet method of Dmr_consume_eko class");
		JSONObject json_obj = null;
		String inputLine = "";
		StringBuilder stringBuilder;
		URL myurl;
		try {
			myurl = new URL(httpsURL);

			HttpsURLConnection con = (HttpsURLConnection) myurl.openConnection();
			con.setRequestProperty("developer_key", PropertyFile.developer_key);
			
			 * con.setRequestProperty("developer_key",
			 * "becbbce45f79c6f5109f848acd540567");
			 
			InputStream ins = con.getInputStream();
			InputStreamReader isr = new InputStreamReader(ins);
			BufferedReader in = new BufferedReader(isr);
			stringBuilder = new StringBuilder();
			while ((inputLine = in.readLine()) != null) {
				stringBuilder.append(inputLine);
				log.info(inputLine);
			}
			//log.info("consumeEkoApiGet"+stringBuilder.toString());
			in.close();
			json_obj=JSONObject.fromObject(stringBuilder.toString());

			//json_obj=(JSONObject) parser.parse(stringBuilder.toString());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("Error in consumeEkoApiGet method of Dmr_consume_eko class"+e.getMessage());
		}

		return json_obj;
	}

	public JSONObject methodPut(String httpsURL, String val) throws Exception {

		JSONObject json_obj = null;
		try {
			json_obj = new JSONObject();
			// making the connection object and passing it through the ssl
			// certificate using the simple x509trustmanager class
			log.info("executing methodPut method of Dmr_consume_eko class");

			URL myurl = new URL(httpsURL);
			StringBuilder stringBuilder;
			String inputLine = "";
			HttpsURLConnection con = (HttpsURLConnection) myurl.openConnection();
			con.setRequestMethod("PUT");
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			con.setRequestProperty("Accept", "application/json");
			con.setRequestProperty("developer_key", PropertyFile.developer_key);
			con.setUseCaches(false);
			OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
			wr.write(val.toString());
			wr.flush();
			InputStream ins = con.getInputStream();
			InputStreamReader isr = new InputStreamReader(ins);
			BufferedReader in = new BufferedReader(isr);
			stringBuilder = new StringBuilder();
			// output the information
			while ((inputLine = in.readLine()) != null) {
				stringBuilder.append(inputLine);
			}
			//log.info("methodPut"+stringBuilder.toString());
			in.close();
			json_obj=JSONObject.fromObject(stringBuilder.toString());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}


		return json_obj;

	}

	public JSONObject methodDelete(String httpsURL, String val) throws Exception {
		log.info("executing methodDelete method of Dmr_consume_eko class");
		// making the connection object and passing it through the ssl
		// certificate using the simple x509trustmanager class
		JSONObject json_obj = new JSONObject();
		URL myurl = new URL(httpsURL);
		StringBuilder stringBuilder;
		String inputLine = "";
		HttpsURLConnection con = (HttpsURLConnection) myurl.openConnection();
		con.setRequestMethod("DELETE");
		con.setDoInput(true);
		con.setDoOutput(true);
		con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		con.setRequestProperty("Accept", "application/json");
		// con.setRequestProperty( "developer_key",
		// PropertyFile.developer_key);
		con.setRequestProperty("developer_key", PropertyFile.developer_key);
		con.setUseCaches(false);
		OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
		wr.write(val.toString());
		wr.flush();
		InputStream ins = con.getInputStream();
		InputStreamReader isr = new InputStreamReader(ins);
		BufferedReader in = new BufferedReader(isr);
		stringBuilder = new StringBuilder();
		// output the information
		while ((inputLine = in.readLine()) != null) {
			stringBuilder.append(inputLine);
		}
		in.close();
		json_obj=JSONObject.fromObject(stringBuilder.toString());

		return json_obj;

	}


	public JSONObject methodPutActivateKyc(String httpsURL, String val,String skey,String skeytimestamp,
			String developer_key) throws Exception {

		JSONObject json_obj = null;
		try {
			json_obj = new JSONObject();
			// making the connection object and passing it through the ssl
			// certificate using the simple x509trustmanager class
			log.info("executing methodPut method of Dmr_consume_eko class");
			log.info("httpsURL : "+httpsURL);
			log.info("Data : "+val);
			URL myurl = new URL(httpsURL);
			StringBuilder stringBuilder;
			String inputLine = "";
			HttpsURLConnection con = (HttpsURLConnection) myurl.openConnection();
			con.setRequestMethod("PUT");
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			con.setRequestProperty("developer_key", developer_key);
			con.setRequestProperty("secret-key", skey);
			con.setRequestProperty("secret-key-timestamp", skeytimestamp);
			con.setUseCaches(false);
			OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
			wr.write(val.toString());
			wr.flush();
			InputStream ins = con.getInputStream();
			InputStreamReader isr = new InputStreamReader(ins);
			BufferedReader in = new BufferedReader(isr);
			stringBuilder = new StringBuilder();
			// output the information
			while ((inputLine = in.readLine()) != null) {
				stringBuilder.append(inputLine);
			}
			log.info("methodPut"+stringBuilder.toString());
			in.close();
			json_obj=JSONObject.fromObject(stringBuilder.toString());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}


		return json_obj;

	}

*/
	

}
