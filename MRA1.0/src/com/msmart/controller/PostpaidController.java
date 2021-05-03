package com.msmart.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import org.apache.log4j.Logger;

import com.msmart.dao.MobileAppRechargeDao;
import com.msmart.service.PostpaidService;

import net.sf.json.JSONObject;

@Path("SKPOST")
public class PostpaidController {


	Logger logger = Logger.getLogger(PostpaidController.class);


	@Context HttpServletRequest request;


	@POST
	@Consumes("application/json")
	@Produces("application/json")
	@Path("/Billpay")

	public JSONObject newBillpay(JSONObject jsonObject)	
	{
		JSONObject response=new JSONObject(); 
		ServletContext context=null;
		String agentID="",CN="";
		try {

			context=request.getServletContext();

			logger.info("PostpaidController.newBillpay() : "+jsonObject.toString());

			String transactionId="";

			CN=jsonObject.getString("CN");
			agentID=jsonObject.getString("agent_id");
			String txn_key=jsonObject.getString("txn_key");


			if(context.getAttribute(CN)==null||!"".equalsIgnoreCase(context.getAttribute(CN)+"")){
				context.setAttribute(CN, CN);	

				String queryString = jsonObject.toString();

				String OP=jsonObject.getString("OP");
				String OPName=jsonObject.getString("OPName");
				
				String AMT=jsonObject.getString("AMT");

				String AD1=jsonObject.getString("AD1");
				String AD2=jsonObject.getString("AD2");
				String AD3=jsonObject.getString("AD3");
				String AD4=jsonObject.getString("AD4");
				String service=jsonObject.getString("service");

				String reqType=jsonObject.getString("reqType");

				transactionId=jsonObject.getString("REQUEST_ID");
				String referenceId=jsonObject.getString("reference_id");

				PostpaidService postpaidService=new PostpaidService();

				response=postpaidService.newpostpaidService(request,agentID,txn_key,queryString, OP,OPName
						,  CN, AMT,reqType, AD1, AD2, AD3, AD4, transactionId,referenceId,service);


				logger.info("PostpaidController.mobileBill() : "+response.toString());

			}else {
				response.put("response-code", "1");
				response.put("response-message", "Transaction is Under Process. Please Wait.");
			}

		} catch (Exception e) {

			if(context.getAttribute(CN)!=null)
				context.removeAttribute(CN);
			
			e.printStackTrace();
			response=new JSONObject();
			response.put("response-code", "1");
			response.put("response-message", "Transaction under process. Please check status.");
			return response;
		}

		return response;


	}

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	@Path("/billpayOperator")

	public org.json.simple.JSONObject billpayOperator(JSONObject jsonObject)	
	{
		org.json.simple.JSONObject response=new org.json.simple.JSONObject(); 
		try {

			logger.info("jsonObject :: "+jsonObject.toString());

			PostpaidService postpaidService=new PostpaidService();

			String agentID=jsonObject.getString("agent_id");
			String txn_key=jsonObject.getString("txn_key");
			String service=jsonObject.getString("service");
			MobileAppRechargeDao mrd=new MobileAppRechargeDao();
			String txn_key_val=mrd.txn_key_Validation(txn_key, agentID);
			if(!txn_key_val.equalsIgnoreCase("Y"))
			{
				response.put("response-code", "1");
				response.put("response-message", "Invalid request!");
				return response;

			}else{
				ArrayList<HashMap<String, Object>> opList=postpaidService.fetchOperator(service);

				response.put("response-code", "0");
				response.put("response-message","Success");
				response.put("data", opList);


			}

		}catch(Exception e){
			e.printStackTrace();
			e.printStackTrace();
			response.put("response-code", "1");
			response.put("response-message", "Please check status.");
		}
		return response;
	}

}
