package com.msmart.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import org.apache.log4j.Logger;

import com.msmart.service.PrepaidService;

import net.sf.json.JSONObject;

@Path("SKPRE")
public class PrepaidController {


	Logger logger = Logger.getLogger(PrepaidController.class);
	@Context HttpServletRequest request;

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	@Path("/Recharge")

	public JSONObject mobilerecharge(JSONObject jsonObject)	
	{
		JSONObject response=new JSONObject(); 
		String transactionId="",agentID="";
		String OperatorCode, mobileNo="", amtrechg="", servicename;
		ServletContext context = null;
		try {

			context=request.getServletContext();

			logger.info("jsonObject "+jsonObject.toString());
			agentID=jsonObject.getString("agent_id");
			String txn_key=jsonObject.getString("txn_key");
			mobileNo=jsonObject.getString("mobile_no");

			if(context.getAttribute(mobileNo)==null||!"".equalsIgnoreCase(context.getAttribute(mobileNo)+"")){
				context.setAttribute(mobileNo, mobileNo);	

				String cleintIpaddress=request.getRemoteHost();

				

				servicename=jsonObject.getString("service");
				OperatorCode=jsonObject.optString("OpCode");
				logger.info("OpCode :: "+OperatorCode);
				if(OperatorCode==null || "".equalsIgnoreCase(OperatorCode))
					OperatorCode=jsonObject.optString("operator");

				logger.info("operator :: "+OperatorCode);

				
				amtrechg=jsonObject.getString("amount");

				transactionId=jsonObject.getString("request_id");

				PrepaidService prepaidService=new PrepaidService();

				response=prepaidService.rechargeController(agentID,txn_key,cleintIpaddress,
						servicename,OperatorCode,mobileNo,amtrechg,transactionId,request);
			}else {
				response.put("response-code", "1");
				response.put("response-message", "Transaction is Under Process. Please Wait.");
			}

		} catch (Exception e) {
			if(context.getAttribute(mobileNo)!=null)
				context.removeAttribute(mobileNo);
				
			e.printStackTrace();
			response=new JSONObject();
			response.put("response-code", "1");
			response.put("response-message", "Transaction under process. Please check status.");
		}

		return response;

	}

}
