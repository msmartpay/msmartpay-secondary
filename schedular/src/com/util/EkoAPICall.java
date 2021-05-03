package com.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
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

import net.sf.json.JSONObject;

public class EkoAPICall {


	public JSONObject ekoCall(String url,String data,String metjod){

		JSONObject json_obj = new JSONObject();
		String inputLine = "";
		HttpsURLConnection con=null;
		InputStream ins = null;
		InputStreamReader isr = null;
		BufferedReader in = null;
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
			con = (HttpsURLConnection) myurl.openConnection();
			con.setRequestMethod(metjod);

			// con.setRequestProperty( "Content-Type",
			// "application/x-www-form-urlencoded");
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			con.setReadTimeout(150000);
			con.setConnectTimeout(150000);
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
			if(responseCode>=400) {
				ins = con.getErrorStream();
			}else {
				ins = con.getInputStream();
			}
			isr = new InputStreamReader(ins);
			in = new BufferedReader(isr);
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
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}
	}

	public JSONObject ekoCallwithoutSecretKey(String url,String data,String metjod){

		JSONObject json_obj = new JSONObject();
		String inputLine = "";
		HttpsURLConnection con=null;
		InputStream ins = null;
		InputStreamReader isr = null;
		BufferedReader in = null;
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
			con = (HttpsURLConnection) myurl.openConnection();
			con.setRequestMethod(metjod);

			// con.setRequestProperty( "Content-Type",
			// "application/x-www-form-urlencoded");
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			con.setReadTimeout(150000);
			con.setConnectTimeout(150000);
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
			ins = con.getInputStream();
			isr = new InputStreamReader(ins);
			in = new BufferedReader(isr);

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
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}
	}



}
