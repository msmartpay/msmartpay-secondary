package com.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import sun.misc.BASE64Encoder;

public class PostMessageURL {
	
	public  String postMessage(String urlLocation,String requestContentType,String requestMethod) throws IOException {
		  String response = null;
		
		  
		   if (urlLocation != null) {
		   BufferedReader reader = null;
		   OutputStreamWriter writer = null;
		   HttpURLConnection connection = null;

		   try {
		    BASE64Encoder encoder = new BASE64Encoder();
		    String password = "girish:girish"; 
		    String authString = "Basic "
		      + encoder.encode(password.getBytes());
		    
		    URL url = new URL(urlLocation);
		    connection = (HttpURLConnection) url.openConnection();
		    connection.setConnectTimeout(220000);
		    connection.setReadTimeout(220000);
		    connection.setDoOutput(true);
		    connection.setRequestMethod(requestMethod);
		    connection.setRequestProperty("Authorization", authString);

		    if (requestContentType != null) {
		     connection.setRequestProperty("Content-Type",
		       requestContentType);
		    }

		 

		    StringBuilder sb = new StringBuilder();
		    String line = null;

		    try {
		     reader = new BufferedReader(new InputStreamReader(
		       connection.getInputStream()));
		    } catch (IOException e) {
		     InputStream errorIp = connection.getErrorStream();
		     if (errorIp != null) {
		      BufferedReader errorReader = new BufferedReader(
		        new InputStreamReader(errorIp));
		      while ((line = errorReader.readLine()) != null) {
		       sb.append(line);
		      }
		      System.out.println("Got Error Response: " + sb.toString());
		     }     
		     throw e;
		    }

		    while ((line = reader.readLine()) != null) {
		     sb.append(line);
		    }
		    response = sb.toString();
		    
		   } finally {
		    try {
		     if (writer != null) {
		      writer.close();
		     }
		     if (reader != null) {
		      reader.close();
		     }
		     if (connection != null) {
		      connection.disconnect();
		     }
		    } catch (Exception e) {
		    }
		   }
		  }
		  if (response != null) {
		   
		   response = new String(response.getBytes());
		  }
	  
		  return response;
		 }

}
