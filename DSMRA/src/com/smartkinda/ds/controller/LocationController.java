package com.smartkinda.ds.controller;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.common.CommonDetailDao;
import com.login.LoginDao;

import net.sf.json.JSONObject;

@Path("location")
public class LocationController {

	Logger logger = Logger.getLogger(LocationController.class);

	@Context HttpServletRequest request;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/save")

	public JSONObject getCity(JSONObject requestJson)	
	{
		JSONObject response=new JSONObject(); 
		try {

			String userId=requestJson.getString("distributorId");

			String txnkey=requestJson.getString("txnkey");

			LoginDao dao=new LoginDao();
			if(!dao.varifyTxnKey(Long.parseLong(userId), txnkey))
			{
				response.put("status", "1");
				response.put("message", "Unauthorized User Request!");
				return response;
			}

			JSONObject data=requestJson.getJSONObject("data");
			CommonDetailDao daoCommonDetailDao=new CommonDetailDao();
			int count=daoCommonDetailDao.saveLocation(Long.parseLong(userId), data.toString(), "DS");
			if(count==1) {
				response.put("status", "0");
				response.put("message", "Location Saved Successfully");
				return response;
			}else {
				response.put("status", "1");
				response.put("message", "Location not saved!");
				return response;
			}


		} catch (Exception e) {
			e.printStackTrace();
			response.put("message","Process aborted due to Technical Failure.");
			response.put("status", "1");
			return response;
		}

	}

}
