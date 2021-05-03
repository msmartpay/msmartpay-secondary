package com.social.controller;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import org.apache.log4j.Logger;

import com.msmart.dao.MobileAppRechargeDao;

import net.sf.json.JSONObject;

@Path("Socal")
public class SocialController {


	Logger logger = Logger.getLogger(SocialController.class);


	@Context HttpServletRequest request;

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	@Path("/getReferrelCode")

	public JSONObject transactionHistory(JSONObject jsonObject)	
	{
		JSONObject response=new JSONObject(); 
		try {
			SocialDao srd=new SocialDao();
			String agent_id=jsonObject.getString("agent_id");
			System.out.println("SocialController.SocialController() : "+jsonObject.toString() );
			MobileAppRechargeDao mrd=new MobileAppRechargeDao();
			String txn_key=jsonObject.getString("txn_key");
			String txn_key_val=mrd.txn_key_Validation(txn_key, agent_id);
			if(!txn_key_val.equalsIgnoreCase("Y"))
			{
				response.put("status", "1");
				response.put("message", "Cause of network error unable to find refferal code. Please try later.");

			}
			else{

				String refcode=srd.getReferralCode(agent_id);

				if(refcode!=null)
				{
					System.out.println("TransactionController"+refcode);

					response.put("refcode", refcode);
					response.put("subject", "Invite a friend to join SmartKinda &  earn upto \u20B9 100!");
					response.put("message", "Hi, download the SmartKinda mobile app and earn Rs.10 for your travel booking,Recharges and bill-payment. You can further earn up to Rs. 100 by referring your friends or family. Enter this code -"+refcode+"  while signing up or click on https://play.google.com/store/apps/details?id=com.smartkinda");
					response.put("status", "0");
				}
				else{
					response.put("status", "1");
					response.put("message", "Cause of network error unable to find refferal code. Please try later.");
				}

			}



		} catch (Exception e) {
			e.printStackTrace();
			response=new JSONObject();
			response.put("status", "1");
			response.put("message", "Cause of network error unable to find refferal code. Please try later");
			return response;
		}

		return response;
	}

}