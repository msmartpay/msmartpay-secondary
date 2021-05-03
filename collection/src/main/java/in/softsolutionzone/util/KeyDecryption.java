package in.softsolutionzone.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import javax.crypto.Cipher;

import lombok.extern.log4j.Log4j2;


@Log4j2
public class KeyDecryption {
	
	
	/*
	 * Convert into hex values
	 */
	private static String hex(String binStr) {


		String newStr = new String();

		try {
			String hexStr = "0123456789ABCDEF";
			byte[] p = binStr.getBytes();
			for (int k = 0; k < p.length; k++) {
				int j = (p[k] >> 4) & 0xF;
				newStr = newStr + hexStr.charAt(j);
				j = p[k] & 0xF;
				newStr = newStr + hexStr.charAt(j) + " ";
			}
		} catch (Exception e) {
			log.info("Failed to convert into hex values: " + e);
		}
		return newStr;
	}
	
	public static String decryptResource(String resource) {
		String decryptedmessage = "";
		try {

			// The source of randomness
			SecureRandom secureRandom = new SecureRandom();

			// Obtain a RSA Cipher Object
			Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

			// Loading private key file  
			String keyFile = ResourceReader.readFileToString("classpath:softsolution.pem").trim();
			InputStream inStream = new ByteArrayInputStream(keyFile.getBytes());
			byte[] encKey = new byte[inStream.available()];
			inStream.read(encKey);
			inStream.close();
			String pvtKey = new String(encKey);

			pvtKey = pvtKey.replaceAll("\\n", "").replace("-----BEGIN PRIVATE KEY-----", "").replace("-----END PRIVATE KEY-----", "");

			log.info(pvtKey);
			// Read the private key from file
			log.info("RSA PrivateKeyInfo: " + encKey.length + " bytes\n");
			// PKCS8EncodedKeySpec privKeySpec=new PKCS8EncodedKeySpec(Base64.getDecoder().decode(encKey));//new PKCS8EncodedKeySpec(encKey);
			PKCS8EncodedKeySpec privKeySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(pvtKey));
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			log.info("KeyFactory Object Info:");
			log.info("Algorithm = " + keyFactory.getAlgorithm());
			log.info("Provider = " + keyFactory.getProvider());
			PrivateKey priv = (RSAPrivateKey) keyFactory.generatePrivate(privKeySpec);
			log.info("Loaded " + priv.getAlgorithm() + " " + priv.getFormat() + " private key.");

			// Initialize the cipher for decryption
			cipher.init(Cipher.DECRYPT_MODE, priv, secureRandom);

			// Decrypt the message
			//	textBytes = cipher.doFinal(ciphertextBytes);
			byte[] cipherByte = org.bouncycastle.util.encoders.Base64.decode(resource.getBytes("UTF-8"));
			log.info(cipherByte);

			decryptedmessage = new String(cipher.doFinal(cipherByte));
			log.info("Message decrypted with file private key:\n");
			log.info(decryptedmessage);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return decryptedmessage;
	}

	/*
	 * Encrypt a message using a certificate file cacert.pem (contains public key).
	 * Decrypt the encrypted message using a private key file (cakey.p8c).
	 */
	public static void main(String[] args) {

		String message = "";
		byte[] messageBytes;
		byte[] tempPub = null;
		String sPub = null;
		byte[] ciphertextBytes = null;
		byte[] textBytes = null;

		try {

			// The source of randomness
			SecureRandom secureRandom = new SecureRandom();

			// Obtain a RSA Cipher Object
			Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

			// Loading certificate file  
			String certFile = ResourceReader.readFileToString("classpath:softsolution.txt").trim();
			InputStream inStream = new ByteArrayInputStream(certFile.getBytes());
			CertificateFactory cf = CertificateFactory.getInstance("X.509");
			X509Certificate cert = (X509Certificate) cf.generateCertificate(inStream);
			inStream.close();

			// Read the public key from certificate file
			RSAPublicKey pubkey = (RSAPublicKey) cert.getPublicKey();
			tempPub = pubkey.getEncoded();
			sPub = new String(tempPub);
			log.info("Public key from certificate file:\n" + hex(sPub) + "\n");
			log.info("Public Key Algorithm = " + cert.getPublicKey().getAlgorithm() + "\n");

			// Set plain message
			message = "{\r\n" +
					"\"merchantId\":\"109618\",\r\n" +
					"\"subMerchantId\":\"12234\",\r\n" +
					"\"terminalId\":\"5411\",\r\n" +
					"\"merchantName\":\"test stores\",\r\n" +
					"\"subMerchantName\":\"sub_ test stores\",\r\n" +
					"\"payerVa\":\"testother2@icici\",\r\n" +
					"\"amount\":\"5.00\",\r\n" +
					"\"note\":\"collect-pay-request\",\r\n" +
					"\"collectByDate\":\"10/04/2018 11:01 AM\",\r\n" +
					"\"merchantTranId\":\"6124114576893\",\r\n" +
					"\"billNumber\":\"SDF234278\"\r\n" +
					"}\r\n" +
					"";
			messageBytes = message.getBytes();
			log.info("Plain message:\n" + message + "\n");

			// Initialize the cipher for encryption
			cipher.init(Cipher.ENCRYPT_MODE, pubkey, secureRandom);

			// Encrypt the message
			ciphertextBytes = cipher.doFinal(messageBytes);
			log.info("Message encrypted with certificate file public key:\n" + new String(org.bouncycastle.util.encoders.Base64.encode(ciphertextBytes)) + "\n");
			String msg=new String(org.bouncycastle.util.encoders.Base64.encode(ciphertextBytes));
			log.info("Decoded Message encrypted with certificate file public key:\n" +msg + "\n");
			
			
			
			// Loading private key file  
			String keyFile = ResourceReader.readFileToString("classpath:softsolution.pem").trim();
			inStream = new ByteArrayInputStream(keyFile.getBytes());
			byte[] encKey = new byte[inStream.available()];
			inStream.read(encKey);
			inStream.close();
			String pvtKey = new String(encKey);


			pvtKey = pvtKey.replaceAll("\\n", "").replace("-----BEGIN PRIVATE KEY-----", "").replace("-----END PRIVATE KEY-----", "");


			log.info(pvtKey);
			// Read the private key from file
			log.info("RSA PrivateKeyInfo: " + encKey.length + " bytes\n");
			// PKCS8EncodedKeySpec privKeySpec=new PKCS8EncodedKeySpec(Base64.getDecoder().decode(encKey));//new PKCS8EncodedKeySpec(encKey);
			PKCS8EncodedKeySpec privKeySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(pvtKey));
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			log.info("KeyFactory Object Info:");
			log.info("Algorithm = " + keyFactory.getAlgorithm());
			log.info("Provider = " + keyFactory.getProvider());
			PrivateKey priv = (RSAPrivateKey) keyFactory.generatePrivate(privKeySpec);
			log.info("Loaded " + priv.getAlgorithm() + " " + priv.getFormat() + " private key.");


			// Initialize the cipher for decryption
			cipher.init(Cipher.DECRYPT_MODE, priv, secureRandom);



			// Decrypt the message
			//	textBytes = cipher.doFinal(ciphertextBytes);
			byte[] cipherByte = org.bouncycastle.util.encoders.Base64.decode(msg.getBytes("UTF-8"));
			log.info(cipherByte);

			String op = new String(cipher.doFinal(cipherByte));
			//   String s = new String(textBytes);
			log.info("Message decrypted with file private key:\n");
			log.info(op);

		} catch (IOException e) {
			log.info("IOException:" + e);
			e.printStackTrace();
		} catch (Exception e) {
			log.info("Exception:" + e);
			e.printStackTrace();
		}
	}
}
