package com.msmart.api.controller;

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

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import net.sf.json.JSONObject;

public class ICICIAPICall {

	Logger log=Logger.getLogger(ICICIAPICall.class);
	
	public JSONObject getJSONobjectPost(String url, JSONObject obj) {

		JSONObject jsonObject = null;
		String inputLine = "";
		InputStream ins = null;
		InputStreamReader isr = null;
		BufferedReader in = null;
		HttpsURLConnection con=null;
		try {
			
			log.info("URL : " + url);
			log.info("JSON : " + obj.toString());

			String[] urlParam=url.split(":");
			if (urlParam[0].equals("http")) {


				Client client = Client.create();
				client.setConnectTimeout(150000);
				client.setReadTimeout(150000);
				WebResource webResource = client.resource(url);
				ClientResponse response1 = webResource.type("application/json").post(ClientResponse.class,
						obj.toString());

				String output = response1.getEntity(String.class);
				if (response1.getStatus() != 200) {
					throw new RuntimeException("Failed : HTTP error code : " + response1.getStatus());
				}
				/*JSONParser parser = new JSONParser();
				Object object = parser.parse(output);*/
				jsonObject = JSONObject.fromObject(output);
				response1.close();
			}
			else{
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
				con = (HttpsURLConnection) myurl.openConnection();
				con.setRequestMethod("POST");
				// con.setRequestProperty( "Content-Type",
				// "application/x-www-form-urlencoded");
				con.setRequestProperty("Content-Type", "application/json");
				con.setRequestProperty("Accept", "application/json");
				con.setConnectTimeout(150000);
				con.setReadTimeout(150000);
				con.setDoInput(true); // we want feedback
				con.setUseCaches(false); // no caches
				// Send post request
				con.setDoOutput(true);// we will send stuff
				DataOutputStream wr = new DataOutputStream(con.getOutputStream());
				wr.writeBytes(obj.toString());
				wr.flush();
				wr.close();
				StringBuilder stringBuilder = new StringBuilder();
				int responseCode = con.getResponseCode();
				log.info("response1.getStatus()" + responseCode);
				 ins = con.getInputStream();
				 isr = new InputStreamReader(ins);
				 in = new BufferedReader(isr);

				while ((inputLine = in.readLine()) != null) {
					stringBuilder.append(inputLine);

				}
				jsonObject = JSONObject.fromObject(stringBuilder.toString());
				con.disconnect();
			}
			log.info("ConsumePostMethod : " + jsonObject);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
		}finally {
			try {
				if(ins!=null)
					ins.close();
				if(isr!=null)
					isr.close();
				if(in!=null)
					in.close();
				if(con!=null)
				con.disconnect();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return jsonObject;
	}
	
	
}
