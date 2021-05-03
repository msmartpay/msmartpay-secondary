package com.msmart.controller;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.log4j.Logger;
import org.json.JSONException;

import com.ekoaeps.model.HookRequestModel;
import com.msmart.dao.CommonServiceDao;
import com.msmart.eko.EkoAEPS;
import com.msmart.service.EkoAepsService;
import com.msmart.service.Encryption;

import net.sf.json.JSONObject;

@Path("AEPS")
public class EKOAEPSController {

	Logger logger = Logger.getLogger(EKOAEPSController.class);

	@Context HttpServletRequest request;

	private EkoAepsService service;
	private ResponseBuilder builder;
	
	@POST
	@Produces("application/JSON")
	@Consumes("application/JSON")
	@Path("/RequestOTP")
	public JSONObject  requestOTP(JSONObject reqObj) throws JSONException
	{
		logger.info("RequestOTP : "+reqObj.toString());

		String key=reqObj.getString("Key");
		String AgentID=reqObj.getString("AgentID");
		String mobile=reqObj.getString("Mobile");
		JSONObject jsonObject=null;
		try {
			CommonServiceDao commonServiceDao=new CommonServiceDao();
			String txn_key_val=commonServiceDao.txn_key_Validation(key, AgentID);
			if(!txn_key_val.equalsIgnoreCase("Y"))
			{
				jsonObject=new JSONObject();
				jsonObject.put("Status", "1");
				jsonObject.put("message", "Invalid request!");
			}else if(mobile==null || mobile.length()<10){
				jsonObject=new JSONObject();
				jsonObject.put("Status", "1");
				jsonObject.put("message", "Invalid Mobile!");
			}else{
				EkoAEPS ekoDMT=new EkoAEPS();
				jsonObject=ekoDMT.requestOTP(mobile);
			}

		} catch (Exception e) {
			e.printStackTrace();
			jsonObject=new JSONObject();
			jsonObject.put("Status", "1");
			jsonObject.put("message", "Unable to process your request. Please try Later");
			return jsonObject;
		}
		logger.info("RequestOTP Response : "+jsonObject.toString());
		return jsonObject;
	}

	@POST
	@Produces("application/JSON")
	@Consumes("application/JSON")
	@Path("/VerifyMobile")
	public JSONObject  verifyMobile(JSONObject reqObj) throws JSONException
	{
		logger.info("VerifyMobile : "+reqObj.toString());

		String key=reqObj.getString("Key");
		String AgentID=reqObj.getString("AgentID");
		String senderId=reqObj.getString("Mobile");
		String otp=reqObj.getString("OTP");
		JSONObject jsonObject=null;
		try {
			CommonServiceDao commonServiceDao=new CommonServiceDao();
			String txn_key_val=commonServiceDao.txn_key_Validation(key, AgentID);
			if(!txn_key_val.equalsIgnoreCase("Y"))
			{
				jsonObject=new JSONObject();
				jsonObject.put("Status", "1");
				jsonObject.put("message", "Invalid request!");
			}else{
				EkoAEPS ekoDMT=new EkoAEPS();
				jsonObject=ekoDMT.verifyMobile(senderId,otp);
			}

		} catch (Exception e) {
			e.printStackTrace();
			jsonObject=new JSONObject();
			jsonObject.put("Status", "1");
			jsonObject.put("message", "Unable to process your request. Please try Later");
			return jsonObject;
		}
		logger.info("VerifyMobile Response : "+jsonObject.toString());
		return jsonObject;
	}
	
	@POST
	@Produces("application/JSON")
	@Consumes("application/JSON")
	@Path("/UserOnboard")
	public JSONObject  userOnboard(JSONObject reqObj) throws JSONException
	{
		logger.info("UserOnboard : "+reqObj.toString());

		String key=reqObj.getString("Key");
		String AgentID=reqObj.getString("AgentID");
		JSONObject jsonObject=null;
		try {
			CommonServiceDao commonServiceDao=new CommonServiceDao();
			String txn_key_val=commonServiceDao.txn_key_Validation(key, AgentID);
			if(!txn_key_val.equalsIgnoreCase("Y"))
			{
				jsonObject=new JSONObject();
				jsonObject.put("Status", "1");
				jsonObject.put("message", "Invalid request!");
			}else{
				EkoAEPS ekoDMT=new EkoAEPS();
				jsonObject=ekoDMT.userOnboard(reqObj);
			}

		} catch (Exception e) {
			e.printStackTrace();
			jsonObject=new JSONObject();
			jsonObject.put("Status", "1");
			jsonObject.put("message", "Unable to process your request. Please try Later");
			return jsonObject;
		}

		logger.info("UserOnboard Response : "+jsonObject.toString());

		return jsonObject;
	}
	
	
	@Path("hook")
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	public Response hook(HookRequestModel hookRequestModel) {
		
		logger.info("Method : "+request.getMethod());
		String ip=request.getRemoteAddr();
		logger.info("hook "+hookRequestModel.getAction()+" : "+hookRequestModel.toString());
		service=new EkoAepsService();
		builder=service.hook(hookRequestModel, ip);
		return builder.build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("generateKey")
	public JSONObject generateAccessKey(JSONObject reqJson) {
		JSONObject respJson=new JSONObject();
		CommonServiceDao commonServiceDao=new CommonServiceDao();
		try {
			String key=reqJson.getString("Key");
			String agentID=reqJson.getString("AgentID");
			String txn_key_val=commonServiceDao.txn_key_Validation(key, agentID);
			if(!txn_key_val.equalsIgnoreCase("Y"))
			{
				respJson.put("Status", "1");
				respJson.put("message", "Unauthorized User Request!");
				
			}else{
				respJson=Encryption.generateSecretKey(reqJson);
				respJson.put("Status", "0");
				respJson.put("message", "Success");
				return respJson;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			respJson.put("Status", "1");
			respJson.put("message", "Technical failure");
		}
		return respJson;
	}
}
