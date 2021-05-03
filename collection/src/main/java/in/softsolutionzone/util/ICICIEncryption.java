package in.softsolutionzone.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import in.softsolutionzone.model.ICICICollectionEncryptedResponseModel;

public class ICICIEncryption {

	private static final Logger logger = LoggerFactory.getLogger(ICICIEncryption.class);
	
	private static Cipher encCipher = null;
	private static Cipher decCipher = null;

	public static String encrypt(byte[] input) throws Exception {
		Cipher cipher = getEncCipher();
		byte[] body = cipher.doFinal(input);
		return Base64.getEncoder().encodeToString(body);
	}

	public static String decrypt(String input) throws Exception {
		Cipher cipher = getDecCipher();
		input = input.replaceAll("\\r\\n", "");
		byte[] bytes = Base64.getDecoder().decode(input.getBytes(StandardCharsets.UTF_8));
		String output = new String(cipher.doFinal(bytes));
		return output;
	}

	private static Cipher getEncCipher() throws CertificateException, IOException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeyException {

		if (ICICIEncryption.encCipher != null) {
			return ICICIEncryption.encCipher;
		}

		RSAPublicKey pubkey = getRSAPublicKey();

		// Obtain a RSA Cipher Object
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

		// The source of randomness
		SecureRandom secureRandom = new SecureRandom();

		// Initialize the cipher for encryption
		cipher.init(Cipher.ENCRYPT_MODE, pubkey, secureRandom);

		ICICIEncryption.encCipher = cipher;

		return ICICIEncryption.encCipher;
	}

	private static Cipher getDecCipher() throws Exception {

		if (ICICIEncryption.decCipher != null) {
			return ICICIEncryption.decCipher;
		}

		// The source of randomness
		SecureRandom secureRandom = new SecureRandom();

		// Obtain a RSA Cipher Object
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

		String pvtKey = ResourceReader.readFileToString("classpath:msmartpay.pem").trim(); //"Here You have to pass private Key content";
		pvtKey = pvtKey.replaceAll("\\n", "").replace("-----BEGIN PRIVATE KEY-----", "").replace("-----END PRIVATE KEY-----", "");
		
		PKCS8EncodedKeySpec privKeySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(pvtKey));
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PrivateKey priv = (RSAPrivateKey) keyFactory.generatePrivate(privKeySpec);

		// Initialize the cipher for decryption
		cipher.init(Cipher.DECRYPT_MODE, priv, secureRandom);
		ICICIEncryption.decCipher = cipher;

		return ICICIEncryption.decCipher;
	}

	private static RSAPublicKey getRSAPublicKey() throws CertificateException, IOException {
		String certData = ResourceReader.readFileToString("classpath:ICICI_PUBLIC_CERT_PROD.txt").trim(); //"Here You have to pass ICICI public key content";

		InputStream inStream = new ByteArrayInputStream(certData.getBytes());
		CertificateFactory cf = CertificateFactory.getInstance("X.509");
		X509Certificate cert = (X509Certificate) cf.generateCertificate(inStream);
		inStream.close();
		RSAPublicKey pubkey = (RSAPublicKey) cert.getPublicKey();
		return pubkey;
	}

	public static String decryptByAES(String secretKey, String cipherText) throws Exception {
		int INIT_VECTOR_LENGTH = 16;

		// Get raw encoded data
		byte[] encrypted = org.apache.commons.codec.binary.Base64.decodeBase64(cipherText);

		// Slice initialization vector
		IvParameterSpec ivParameterSpec = new IvParameterSpec(encrypted, 0, INIT_VECTOR_LENGTH);
		// Set secret password
		SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes("UTF-8"), "AES");

		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);

		// Trying to get decrypted text
		String decryptedData = new String(
				cipher.doFinal(encrypted, INIT_VECTOR_LENGTH, encrypted.length - INIT_VECTOR_LENGTH));

		return decryptedData;
	}
	
	public static String doubleDecryption(String encryptedKey,String encryptedData) {
		String decryptedData=null;
		try {
			logger.info("encryptedKey :"+encryptedKey);
			logger.info("encryptedData :"+encryptedData);
			
			String key=decrypt(encryptedKey);
			logger.info("key :"+key);
			
			decryptedData=decryptByAES(key,encryptedData);	
			logger.info("getStatement decryptedData : "+decryptedData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Exception ", e);
		}
		return decryptedData;
	}

	
	public static ICICICollectionEncryptedResponseModel encryptDataByAES(String responseJson)
    {
		ICICICollectionEncryptedResponseModel responseModel=new ICICICollectionEncryptedResponseModel();
        try {
			byte[] databyte1 = Base64.getEncoder().encode(responseJson.getBytes());

			byte[] RANDOMNO = new byte[16];
			for (int i = 0; i < 16; i++)
			{
			    RANDOMNO[i] = databyte1[i];
			}
			String encryptedKey = encrypt(RANDOMNO);

			IvParameterSpec ivParameterSpec = new IvParameterSpec(RANDOMNO);

			byte[] ENCR_DATA = encryptSendData(responseJson, RANDOMNO, ivParameterSpec);
			String encryptedData = Base64.getEncoder().encodeToString(ENCR_DATA);
			
			responseModel.setEncryptedKey(encryptedKey);
			responseModel.setEncryptedData(encryptedData);
			responseModel.setIv("");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Exception ", e);
		}
        
        return responseModel;
    }
	
	public static byte[] encryptSendData(String plainText, byte[] key,IvParameterSpec ivParameterSpec) throws Exception {
        byte[] clean = plainText.getBytes();


        // Hashing key.
        // Set secret password
     	SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");

        // Encrypt.
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        byte[] encrypted = cipher.doFinal(clean);

        // Combine IV and encrypted part.
        byte[] encryptedIVAndText = new byte[key.length + encrypted.length];
        System.arraycopy(key, 0, encryptedIVAndText, 0, key.length);
        System.arraycopy(encrypted, 0, encryptedIVAndText, key.length, encrypted.length);

        return encryptedIVAndText;
    }


	public static void main(String[] args) {
		try {
			//System.out.println(decrypt("qNcrTynqXWuOBqeHOJ/Euw+KdF6z8QJo/vqCTmYJR3BMndjP4UO3LeHzPwI29re7y1uZHE8WQFlHzlE9fxiBtui2QA8Kwxv9Ia8BeWHwQmfA4NtmbUifn2SZEy9v6IGa5WkDh/8I24PXLz20ZMgfLOXl7i0Cc5uMngXlTFTFBzO3aobVXmh+M6XTmgPtNkV+gKkdPqfoGzZhV1xgYWACDc2ZVH+/dLmEAjAr1bHsB31PY1SPAgOyVA74r9LIRQowV7+8xCQ6O8gXdoX5PfziExHv+dJkju1Qc65xdvbiZxFObtA8ag1OsmUAGEXj3vY5kg0Fs2JMN393DrdstMrkjMUJCMrqTNhR5fX8E64D9y7mHckF503NAUFNMdmAM9iDvjmTpPWeIVEuoEp7nM91R3wZrhtHtLgPdAPKjaDSjiIU/V+vYJbbugMhlot1CEz0du3Eo8tQypOC6i14/CqsUTH68xxwVBVwFKjRzR1/hYNMf2FDey+v0zgZqy5ScqCTbuzEOlNo/+mO00249YhP/Gu2p1/gXUPlh2Nme2g3p6KWYeOGWe0jPvCQ1n9Ro7mmDifS5HD17jg/8vKif/l0S0koSDKXBpnUkZXTRZVXumhQWYgDH4Oxw58YC/6HqRQKHWppd7ebJagZLMQT/A9/JU+HU3WB3ITCrrjdLM2MD6g="));
		
			String key="342cd52154c1448882c5f32e461a850e";
			String data="Tmn3+vYqqpsl208whCbNjA==";
			
			System.out.println(decryptByAES(key, data));
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Exception ", e);
		}
	}

	
	
}
