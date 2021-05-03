package in.softsolutionzone.util;

import java.security.InvalidKeyException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import com.squareup.okhttp.internal.Base64;

public class HmacSHA256 {
	public static final String HMAC_SHA256 = "HmacSHA256";

	/*
	 * public static void main(String[] args) {
	 * Date date = new Date();
	 * String secret_key_timestamp = Long.toString(date.getTime());
	 * String secret_key=generateSecretKey(secret_key_timestamp,
	 * "a3d463b45d3e29a6a80c8cc1dd7d13ab5b84741c");
	 * System.out.println(secret_key_timestamp);
	 * System.out.println(secret_key);
	 * }
	 */
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
