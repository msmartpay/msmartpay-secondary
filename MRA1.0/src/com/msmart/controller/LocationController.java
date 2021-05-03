package com.msmart.controller;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import org.apache.log4j.Logger;
import com.msmart.dao.CommonServiceDao;
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

			String agentId=requestJson.getString("agent_id");
			String txn_key=requestJson.getString("txn_key");

			CommonServiceDao commonServiceDao=new CommonServiceDao();

			
			String txn_key_val=commonServiceDao.txn_key_Validation(txn_key, agentId);
			if(!txn_key_val.equalsIgnoreCase("Y"))
			{
				response.put("response-code", "1");
				response.put("response-message", "Unauthorized User Request!");
				return response;
			}

			JSONObject data=requestJson.getJSONObject("data");
			CommonServiceDao dao=new CommonServiceDao();
			int count=dao.saveLocation(Long.parseLong(agentId), data.toString(), "Agent");
			if(count==1) {
				response.put("response-code", "0");
				response.put("response-message", "Location Saved Successfully");
				return response;
			}else {
				response.put("response-code", "1");
				response.put("response-message", "Location not saved!");
				return response;
			}


		} catch (Exception e) {
			e.printStackTrace();
			response.put("response-code", "1");
			response.put("response-message", "Unable to process your request.");
			return response;
		}

	}

}
