package com.appnit;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.log4j.Logger;
import java.util.Arrays;
import javax.crypto.spec.IvParameterSpec;
public class AES128Bit {
	
	Logger logger=Logger.getLogger(AES128Bit.class);
	
    private static final String ALGORITHM = "AES/CBC/PKCS5Padding";
    private static String iv              = "fedcba9876543210";
    public static String encrypt(String valueToEnc, String secretKey) {
        String encryptedValue = null;
        try {
        	
        	IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());
            Key key = AES128Bit.generateKeyFromString(secretKey);
            Cipher c = Cipher.getInstance(ALGORITHM);
            c.init(1, key,ivspec);
            byte[] encValue = c.doFinal(valueToEnc.getBytes("UTF-8"));
            encryptedValue = new String(AppnitBase64Coder.encode((byte[])encValue));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return encryptedValue;
    }

    public static String decrypt(String encryptedValue, String secretKey) {
        String decryptedValue = null;
        try {
        	IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());
            Key key = AES128Bit.generateKeyFromString(secretKey);
            Cipher c = Cipher.getInstance(ALGORITHM);
            c.init(2, key,ivspec);
            byte[] decordedValue = AppnitBase64Coder.decode((String)encryptedValue);
            byte[] decValue = c.doFinal(decordedValue);
            decryptedValue = new String(decValue);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return decryptedValue;
    }

    private static Key generateKeyFromString(String secretKey) throws Exception {
       
        byte[] keyValue=AppnitBase64Coder.decode((String)secretKey);//Arrays.copyOf(AppnitBase64Coder.decode((String)secretKey), 32);
        if(keyValue.length > 32)
         keyValue=Arrays.copyOf(AppnitBase64Coder.decode((String)secretKey), 32);
        
        SecretKeySpec key = new SecretKeySpec(keyValue, "AES");
        return key;
    }

    public static String generateNewKey() {
        String newKey = null;
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128);
            SecretKey skey = kgen.generateKey();
            byte[] raw = skey.getEncoded();
            newKey = new String(AppnitBase64Coder.encode((byte[])raw));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return newKey;
    }

}