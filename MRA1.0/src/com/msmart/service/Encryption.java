package com.msmart.service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.lang.RandomStringUtils;

import com.msmart.dao.RegistrationDao;
import com.msmart.util.HmacSHA256;

import net.sf.json.JSONObject;

public class Encryption {

	private static String convertToHex(byte[] data) 
	{ 
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < data.length; i++) 
		{ 
			int halfbyte = (data[i] >>> 4) & 0x0F;
			int two_halfs = 0;
			do 
			{ 
				if ((0 <= halfbyte) && (halfbyte <= 9)) 
					buf.append((char) ('0' + halfbyte));
				else 
					buf.append((char) ('a' + (halfbyte - 10)));
				halfbyte = data[i] & 0x0F;
			} while(two_halfs++ < 1);
		} 
		return buf.toString();
	} 
 
	public static String SHA1(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException 
	{ 
		MessageDigest md;
		md = MessageDigest.getInstance("SHA-1");
		byte[] sha1hash = new byte[40];
		md.update(text.getBytes("iso-8859-1"), 0, text.length());
		sha1hash = md.digest();
		return convertToHex(sha1hash);
	}
	public static String generateOtp() {
		return RandomStringUtils.randomNumeric(6);
	}
	
	public static JSONObject generateSecretKey(JSONObject reqJson) {
		JSONObject body=new JSONObject();
		try {
			String agentID=reqJson.getString("AgentID");
			
			RegistrationDao dao=new RegistrationDao();
			
			HashMap<String, String> map=dao.getprofile(agentID);
						
			String user_code=map.get("user_code");
			
			Date date = new Date();
			String secret_key_timestamp = Long.toString(date.getTime());
			String secret_key= HmacSHA256.generateSecretKey(secret_key_timestamp,PropertyFile.key);
			JSONObject data=new JSONObject();
			data.put("secret_key_timestamp", secret_key_timestamp);
			data.put("secret_key", secret_key);
			data.put("developer_key", PropertyFile.developer_key);
			data.put("initiator_id", PropertyFile.initiator_id);
			data.put("user_code", user_code);
			data.put("partner_name", PropertyFile.PARTNER_NAME);
			
			body.put("data", data);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			body=new JSONObject();
			body.put("Status", "1");
			body.put("message", "Unable to process your request. Please try Later");
		}
		return body;
	}
}
