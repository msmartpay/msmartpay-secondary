package com.msmart.util;

import java.util.Date;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import com.squareup.okhttp.internal.Base64;
import java.security.InvalidKeyException;

public class HmacSHA256 {
	public static final String HMAC_SHA256 = "HmacSHA256";

	public static void activateEKyc()	
	{
		try {

			String secret_key="",secret_key_timestamp="";
		// Initializing key in some variable. You will receive this key from Eko via email
		String key = "f74c50a1-f705-4634-9cda-30a477df91b7";
		String encodedKey = Base64.encode(key.getBytes());
//		String encodedKey = Base64.encodeBase64String(key.getBytes());
		//String encodedKey = Base64.getEncoder().encodeToString(key.getBytes());
		// Encode it using base64

		// Get secret_key_timestamp: current timestamp in milliseconds since UNIX epoch as STRING
		// Check out https://currentmillis.com to understand the timestamp format
		Date date = new Date();
		secret_key_timestamp = Long.toString(date.getTime());

		// Computes the signature by hashing the salt with the encoded key 
		Mac sha256_HMAC;

		sha256_HMAC = Mac.getInstance(HMAC_SHA256);

		SecretKeySpec signature = new SecretKeySpec(encodedKey.getBytes(), HMAC_SHA256);
		try {
			sha256_HMAC.init(signature);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Encode it using base64 to get secret-key
		secret_key =  Base64.encode(sha256_HMAC.doFinal(secret_key_timestamp.getBytes())).trim();

		System.out.println("secret_key : "+secret_key);
		System.out.println("secret_key_timestamp : "+secret_key_timestamp);


		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static String generateSecretKey(String secret_key_timestamp,String key)	
	{
		String secret_key="";
		try {

			// Initializing key in some variable. You will receive this key from Eko via email
			//String key = PropertyFile.EKO_KEY;
			String encodedKey = Base64.encode(key.getBytes());

			// Get secret_key_timestamp: current timestamp in milliseconds since UNIX epoch as STRING
			// Check out https://currentmillis.com to understand the timestamp format


			// Computes the signature by hashing the salt with the encoded key 
			Mac sha256_HMAC;

			sha256_HMAC = Mac.getInstance(HMAC_SHA256);

			SecretKeySpec signature = new SecretKeySpec(encodedKey.getBytes(), HMAC_SHA256);
			try {
				sha256_HMAC.init(signature);
			} catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			secret_key =  Base64.encode(sha256_HMAC.doFinal(secret_key_timestamp.getBytes())).trim();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return secret_key;

	}
}
