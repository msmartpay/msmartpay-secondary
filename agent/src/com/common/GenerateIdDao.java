package com.common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;

public final class GenerateIdDao {

	Logger logger=Logger.getLogger(GenerateIdDao.class);
	public static String getAETranId(String agentId)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyymmddhhmmss");
		Date date=new Date();
		String tran_id=sdf.format(date);
		String ran = agentId+String.valueOf(tran_id);

		return ran;
	}
	public static String dateTime()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyymmddhhmmss");
		Date date=new Date();
		String tran_id=sdf.format(date);

		return tran_id+RandomStringUtils.randomNumeric(3);
	}
	public static String  getIdNo() {

		String transaction_id="";
		try
		{
			String random=RandomStringUtils.randomNumeric(4);
			SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
			Date date=new Date();
			String tran_id=sdf.format(date);
			transaction_id=random+tran_id;

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return transaction_id;
	}


	public static String  getTicketId() {
		String transaction_id="";
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmssSSSS");
			Date date=new Date();
			String tran_id=sdf.format(date);
			int n = 1;
			Random randGen = new Random();
			int startNum = (int) Math.pow(10, n-1);
			int range = (int) (Math.pow(10, n) - startNum);
			int randomNum = randGen.nextInt(range) + startNum;
			String ran = String.valueOf(randomNum);
			transaction_id="T"+tran_id;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return transaction_id;
	}
	public static String  getSenderRegTxnId() {
		String tran_id="";
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmssSSSS");
			Date date=new Date();
			tran_id=sdf.format(date);
			int n = 1;
			Random randGen = new Random();
			int startNum = (int) Math.pow(10, n-1);
			int range = (int) (Math.pow(10, n) - startNum);
			int randomNum = randGen.nextInt(range) + startNum;
			String ran = String.valueOf(randomNum);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return tran_id;
	}
	
	public static String createTransactionID()
	{
		String AgentTranID = "";
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmssSSSS");
			Date date = new Date();
			String tranID = sdf.format(date);
			int n = 4;
			Random randGen = new Random();
			int startNum = (int)Math.pow(10D, n - 1);
			int range = (int)(Math.pow(10D, n) - (double)startNum);
			int randomNum = randGen.nextInt(range) + startNum;
			String ran = String.valueOf(randomNum);
			AgentTranID = (new StringBuilder(String.valueOf(tranID))).append(ran).toString();
		}
		catch(Exception e)
		{
		}
		return AgentTranID;
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

	public static String SHAHashing(String password)throws Exception
	{

		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(password.getBytes());

		byte byteData[] = md.digest();

		//convert the byte to hex format method 1
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		}

		//convert the byte to hex format method 2
		StringBuffer hexString = new StringBuffer();
		for (int i=0;i<byteData.length;i++) {
			String hex=Integer.toHexString(0xff & byteData[i]);
			if(hex.length()==1) hexString.append('0');
			hexString.append(hex);
		}
		return hexString.toString();
	}
}
