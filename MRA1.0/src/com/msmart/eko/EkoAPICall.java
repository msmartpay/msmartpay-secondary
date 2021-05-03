package com.msmart.eko;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import net.sf.json.JSONObject;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.core.MediaType;

import com.msmart.service.PropertyFile;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.representation.Form;
import com.sun.xml.internal.stream.Entity;

import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

public class EkoAPICall {

	Logger log=Logger.getLogger(EkoAPICall.class);
	
	

	/*public JSONObject consumeEkoApi(String httpsURL, String obj) {
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
			con.setRequestProperty("secret-key", PropertyFile.secret_key);
			con.setRequestProperty("secret-key-timestamp", PropertyFile.secret_key_timestamp);
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
	}*/

	/*public JSONObject consumeEkoApiGet(String httpsURL) {
		log.info("executing consumeEkoApiGet method of Dmr_consume_eko class");
		JSONObject json_obj = null;
		String inputLine = "";
		StringBuilder stringBuilder;
		URL myurl;
		try {
			myurl = new URL(httpsURL);

			HttpsURLConnection con = (HttpsURLConnection) myurl.openConnection();
			con.setRequestProperty("developer_key", PropertyFile.developer_key);
			con.setRequestProperty("secret-key", PropertyFile.secret_key);
			con.setRequestProperty("secret-key-timestamp", PropertyFile.secret_key_timestamp);
			
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
			con.setRequestProperty("secret-key", PropertyFile.secret_key);
			con.setRequestProperty("secret-key-timestamp", PropertyFile.secret_key_timestamp);
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
		con.setRequestProperty("secret-key", PropertyFile.secret_key);
		con.setRequestProperty("secret-key-timestamp", PropertyFile.secret_key_timestamp);
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
	
	*
	*/
	
	public JSONObject ekoCall(String url,String data,String metjod){
		
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
			con.setRequestMethod(metjod);
			
			// con.setRequestProperty( "Content-Type",
			// "application/x-www-form-urlencoded");
			con.setRequestProperty("Content-Type", "application/json");
			con.setReadTimeout(60000);
			con.setConnectTimeout(60000);
			
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			con.setRequestProperty("Accept", "application/json");
			// con.setRequestProperty( "developer_key",
			// PropertyFile.developer_key);
			con.setRequestProperty("developer_key", PropertyFile.developer_key);
			con.setRequestProperty("secret-key", PropertyFile.secret_key);
			con.setRequestProperty("secret-key-timestamp", PropertyFile.secret_key_timestamp);
			con.setUseCaches(false);
			
			if(!"GET".equalsIgnoreCase(metjod)){
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

	public JSONObject ekoCallwithoutSecretKey(String url,String data,String metjod){
		
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
			con.setRequestMethod(metjod);
			
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
			
			if(!"GET".equalsIgnoreCase(metjod)){
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
	
	

}
