package com.msmart.dao;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Random;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.apache.tomcat.util.codec.binary.Base64;
import net.sf.json.JSONObject;

public class RechargeColUtil {

	static final Logger logger = Logger.getLogger(RechargeColUtil.class);
	private RechargeControllerDao rcd=null;
	public JSONObject generateXmlResponse(JSONObject authjsn) throws JSONException
	{
		String txn_key=null;	
		String agent_id=null;
		JSONObject jsonObject=new JSONObject();
		
		rcd=new RechargeControllerDao();

		if(authjsn.getString("Status").equalsIgnoreCase("Activate"))
		{
			agent_id=authjsn.getString("agent_Id");
			
			txn_key=init_random();
			rcd.insert_Txn_Key(txn_key, agent_id);
			
			jsonObject.put("response-code","0");
			jsonObject.put("response-message",authjsn.get("Status"));
			jsonObject.put("agent-initial",authjsn.get("agentId_initial"));
			jsonObject.put("agent-dist-id",authjsn.get("agent_dst_id"));
			jsonObject.put("agent-id",agent_id);
			jsonObject.put("txn_key",txn_key);
			jsonObject.put("balance",authjsn.get("Balance"));
			jsonObject.put("mobile-number",authjsn.get("mobileNumber"));
			jsonObject.put("app-text",authjsn.get("appText"));
			jsonObject.put("agentcommissin",authjsn.get("agentcommissin"));
			jsonObject.put("agentmark",authjsn.get("agentmark"));
			jsonObject.put("walletStatus",authjsn.get("walletStatus"));
			
			jsonObject.put("DMR",authjsn.get("DMR"));
			jsonObject.put("DMRUrl","");
			jsonObject.put("agentName",authjsn.get("agentName"));
			jsonObject.put("emailId",authjsn.get("emailId"));
			jsonObject.put("support1",authjsn.get("supportMobile"));
			jsonObject.put("support2",authjsn.get("supportMobile"));
			jsonObject.put("supportEmail",authjsn.get("supportEmail"));
			jsonObject.put("token", authjsn.get("token"));
			jsonObject.put("addmoney",authjsn.get("addmoney"));
			jsonObject.put("kycStatus",authjsn.get("kycStatus"));
			jsonObject.put("about", authjsn.get("about"));
			jsonObject.put("DailyNeedStatus", authjsn.get("DailyNeedStatus"));
			jsonObject.put("CleaningRepairStatus", authjsn.get("CleaningRepairStatus"));
			jsonObject.put("partner",authjsn.get("partner"));
			jsonObject.put("seller",authjsn.get("seller"));
			jsonObject.put("flightStatus",authjsn.get("flightStatus"));
			jsonObject.put("busStatus", authjsn.get("busStatus"));
			jsonObject.put("HotelStatus", authjsn.get("HotelStatus"));
			jsonObject.put("addAmtStatus", authjsn.get("addAmtStatus"));
			jsonObject.put("cancelationPolicy", authjsn.get("cancelationPolicy"));
			jsonObject.put("dmrVendor", authjsn.get("dmrVendor"));
			
		}																									
		else if(( authjsn.getString("Status")).equalsIgnoreCase("invalidmobile"))
		{
			jsonObject.put("response-code","1");
			jsonObject.put("response-message","Invalid Mobile");
		}
		else if(authjsn.getString("Status").equalsIgnoreCase("invalidPassword"))
		{
			jsonObject.put("response-code","1");
			jsonObject.put("response-message","Invalid Password");
		}
		else if(authjsn.getString("Status").equalsIgnoreCase("Deactive"))
		{
			jsonObject.put("response-code","1");
			jsonObject.put("response-message","Your ID is Inactive, Please contact customer support at +91-9711402774.");
		}
		else
		{
			jsonObject.put("response-code","1");
			jsonObject.put("response-message",authjsn.getString("Status"));
		}

		return jsonObject;
	}

	public  String  init_random() {

		String str="";
		try
		{
			int n = 10;
			Random randGen = new Random();	    
			int startNum = (int) Math.pow(10, n-1);
			int range = (int) (Math.pow(10, n) - startNum);	    
			int randomNum = randGen.nextInt(range) + startNum;
			str=SHA1(String.valueOf(randomNum));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		} 
		return str;
	}

	public  String SHA1(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException 
	{
		MessageDigest md;
		md = MessageDigest.getInstance("SHA-1");
		byte[] sha1hash = new byte[40];
		md.update(text.getBytes("iso-8859-1"), 0, text.length());
		sha1hash = md.digest();
		String cth=convertToHex(sha1hash);
		return cth;
	} 

	private  String convertToHex(byte[] data) { 

		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < data.length; i++) { 
			int halfbyte = (data[i] >>> 4) & 0x0F;
			int two_halfs = 0;
			do { 
				if ((0 <= halfbyte) && (halfbyte <= 9)) 
					buf.append((char) ('0' + halfbyte));
				else 
					buf.append((char) ('a' + (halfbyte - 10)));
				halfbyte = data[i] & 0x0F;
			} while(two_halfs++ < 1);
		} 
		return buf.toString();
	} 
}
