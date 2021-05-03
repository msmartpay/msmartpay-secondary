package in.softsolutionzone.provider.call;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.xml.ws.http.HTTPException;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import lombok.extern.java.Log;

@Log
@Component
public class ProviderCall {


	
	public JSONObject sszplApiCall(String url,String data,String method,HashMap<String, String> headerMaps){

		JSONObject json_obj = new JSONObject();
		String inputLine = "";
		HttpsURLConnection httpsCon =null;
		HttpURLConnection httpCon =null;
		InputStream ins = null;
		InputStreamReader isr = null;
		BufferedReader in = null;
		try {
			log.info("URL : "+url);
			log.info("Method : "+method);
			log.info("Data : "+data);
			String protocall[]= url.split("://");
			if("http".equalsIgnoreCase(protocall[0])) {

				URL myurl = new URL(url);
				httpCon = (HttpURLConnection) myurl.openConnection();
				httpCon.setRequestMethod(method);

				httpCon.setReadTimeout(120000);
				httpCon.setConnectTimeout(120000);
				httpCon.setUseCaches(false);
				httpCon.setRequestProperty("Content-Type", "application/json");

				if(headerMaps!=null) {
					for (Map.Entry<String, String> entry : headerMaps.entrySet()) {
						httpCon.setRequestProperty(entry.getKey(), entry.getValue());
					}
				}

				if(!"GET".equalsIgnoreCase(method)){
					httpCon.setDoOutput(true);
					DataOutputStream wr = new DataOutputStream(httpCon.getOutputStream());
					wr.writeBytes(data.toString());
					wr.flush();
					wr.close();
				}


				StringBuilder stringBuilder = new StringBuilder();
				int responseCode = httpCon.getResponseCode();

				if(responseCode<400) {
					ins = httpCon.getInputStream();
				}else {
					ins=httpCon.getErrorStream();
				}
				isr = new InputStreamReader(ins);
				in = new BufferedReader(isr);

				while ((inputLine = in.readLine()) != null) {
					stringBuilder.append(inputLine);
				}
				if(stringBuilder!=null && stringBuilder.length()>0)
					json_obj=new JSONObject(stringBuilder.toString());

			}else {

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
				httpsCon = (HttpsURLConnection) myurl.openConnection();
				httpsCon.setRequestMethod(method);
				httpsCon.setRequestProperty("Content-Type", "application/json");
				httpsCon.setReadTimeout(120000);
				httpsCon.setConnectTimeout(120000);
				httpsCon.setUseCaches(false);
				if(headerMaps!=null) {
					for (Map.Entry<String, String> entry : headerMaps.entrySet()) {
						httpsCon.setRequestProperty(entry.getKey(), entry.getValue());
					}
				}
				if(!"GET".equalsIgnoreCase(method)){
					httpsCon.setDoInput(true);
					httpsCon.setDoOutput(true);
					DataOutputStream wr = new DataOutputStream(httpsCon.getOutputStream());
					wr.writeBytes(data.toString());
					wr.flush();
					wr.close();
				}

				StringBuilder stringBuilder = new StringBuilder();
				int responseCode = httpsCon.getResponseCode();

				log.info("Status code : "+responseCode);
				if(responseCode<400) {
					ins = httpsCon.getInputStream();
				}else {
					ins=httpsCon.getErrorStream();
				}
				isr = new InputStreamReader(ins);
				in = new BufferedReader(isr);

				while ((inputLine = in.readLine()) != null) {
					stringBuilder.append(inputLine);

				}

				if(stringBuilder!=null && stringBuilder.length()>0)
					json_obj=new JSONObject(stringBuilder.toString());

			}

			return json_obj;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}finally {

			try {
				if(httpCon!=null)
					httpCon.disconnect();
				if(httpsCon!=null)
					httpsCon.disconnect();
				if(ins!=null)
					ins.close();
				if(isr!=null)
					isr.close();
				if(in!=null)
					in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
	}

	public String apiCallWithStringResponse(String url,String data,String method,Map<String, String> headerMaps){

		String resp = "";
		String inputLine = "";
		HttpsURLConnection httpsCon =null;
		HttpURLConnection httpCon =null;
		InputStream ins = null;
		InputStreamReader isr = null;
		BufferedReader in = null;
		try {
			log.info("URL : "+url);
			log.info("Method : "+method);
			log.info("Data : "+data);
			String protocall[]= url.split("://");
			if("http".equalsIgnoreCase(protocall[0])) {

				URL myurl = new URL(url);
				httpCon = (HttpURLConnection) myurl.openConnection();
				httpCon.setRequestMethod(method);

				httpCon.setReadTimeout(120000);
				httpCon.setConnectTimeout(120000);
				httpCon.setUseCaches(false);
				

				if(headerMaps!=null) {
					for (Map.Entry<String, String> entry : headerMaps.entrySet()) {
						httpCon.setRequestProperty(entry.getKey(), entry.getValue());
					}
				}

				if(!"GET".equalsIgnoreCase(method)){
					httpCon.setDoOutput(true);
					DataOutputStream wr = new DataOutputStream(httpCon.getOutputStream());
					wr.writeBytes(data.toString());
					wr.flush();
					wr.close();
				}


				StringBuilder stringBuilder = new StringBuilder();
				int responseCode = httpCon.getResponseCode();

				if(responseCode<400) {
					ins = httpCon.getInputStream();
				}else {
					ins=httpCon.getErrorStream();
				}
				isr = new InputStreamReader(ins);
				in = new BufferedReader(isr);

				while ((inputLine = in.readLine()) != null) {
					stringBuilder.append(inputLine);
				}
				if(stringBuilder!=null && stringBuilder.length()>0)
					resp=stringBuilder.toString();

			}else {

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
				httpsCon = (HttpsURLConnection) myurl.openConnection();
				httpsCon.setRequestMethod(method);

				httpsCon.setReadTimeout(120000);
				httpsCon.setConnectTimeout(120000);
				httpsCon.setUseCaches(false);
				if(headerMaps!=null) {
					for (Map.Entry<String, String> entry : headerMaps.entrySet()) {
						httpsCon.setRequestProperty(entry.getKey(), entry.getValue());
					}
				}
				if(!"GET".equalsIgnoreCase(method)){
					httpsCon.setDoInput(true);
					httpsCon.setDoOutput(true);
					DataOutputStream wr = new DataOutputStream(httpsCon.getOutputStream());
					wr.writeBytes(data.toString());
					wr.flush();
					wr.close();
				}

				StringBuilder stringBuilder = new StringBuilder();
				int responseCode = httpsCon.getResponseCode();

				log.info("Status code : "+responseCode);
				if(responseCode<400) {
					ins = httpsCon.getInputStream();
				}else {
					ins=httpsCon.getErrorStream();
				}
				isr = new InputStreamReader(ins);
				in = new BufferedReader(isr);

				while ((inputLine = in.readLine()) != null) {
					stringBuilder.append(inputLine);

				}

				if(stringBuilder!=null && stringBuilder.length()>0)
					resp=stringBuilder.toString();

			}

			return resp;

		}catch (HTTPException he) {
			// TODO: handle exception
			he.printStackTrace();
			return null;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}finally {

			try {
				if(httpCon!=null)
					httpCon.disconnect();
				if(httpsCon!=null)
					httpsCon.disconnect();
				if(ins!=null)
					ins.close();
				if(isr!=null)
					isr.close();
				if(in!=null)
					in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
	}
}
