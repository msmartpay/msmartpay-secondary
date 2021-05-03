package com.msmart.controller;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import org.apache.log4j.Logger;

import com.msmart.dao.MobileAppRechargeDao;
import com.msmart.dao.SearchTransactionDao;
import com.msmart.dao.StatementNReportDao;

import net.sf.json.JSONObject;

@Path("SKTH")
public class TransactionController {


	Logger logger = Logger.getLogger(TransactionController.class);


	@Context HttpServletRequest request;

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	@Path("/TranHistory")

	public JSONObject transactionHistory(JSONObject jsonObject)	
	{
		JSONObject response=new JSONObject(); 
		try {
			StatementNReportDao srd=new StatementNReportDao();
			String agent_id=jsonObject.getString("agent_id");
			logger.info("TransactionController.transactionHistory() : "+jsonObject.toString() );
			MobileAppRechargeDao mrd=new MobileAppRechargeDao();
			String txn_key=jsonObject.getString("txn_key");
			String txn_key_val=mrd.txn_key_Validation(txn_key, agent_id);
			if(!txn_key_val.equalsIgnoreCase("Y"))
			{
				response.put("response-code", "1");
				response.put("response-message", "Invalid request!");
			}else{
				response=srd.statementDao(agent_id);
				
				if(response==null)
				{
					
					response=new JSONObject();
					response.put("response-code", "1");
					response.put("response-message", "Transaction Not Available.");
				}
			}

		
			
		} catch (Exception e) {
			e.printStackTrace();
			response=new JSONObject();
			response.put("response-code", "1");
			response.put("message", "Failure");
			return response;
		}

		return response;
	}
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	@Path("/TranHistoryByDate")

	public JSONObject tranHistoryByDate(JSONObject jsonObject)	
	{
		JSONObject response=new JSONObject(); 
		try {
			StatementNReportDao srd=new StatementNReportDao();
			String agent_id=jsonObject.getString("agent_id");
			logger.info("TransactionController.TranHistoryByDate() : "+jsonObject.toString() );
			MobileAppRechargeDao mrd=new MobileAppRechargeDao();
			String txn_key=jsonObject.getString("txn_key");
			String txn_key_val=mrd.txn_key_Validation(txn_key, agent_id);
			if(!txn_key_val.equalsIgnoreCase("Y"))
			{
				response.put("response-code", "1");
				response.put("response-message", "Invalid request!");
			}else{
				String fromDate=jsonObject.getString("fromDate");
				String toDate=jsonObject.getString("toDate");
				
				response=srd.statementDao(agent_id,fromDate,toDate);
				
				if(response==null)
				{
					
					response=new JSONObject();
					response.put("response-code", "1");
					response.put("response-message", "Transaction Not Available.");
				}
			}

		
			
		} catch (Exception e) {
			e.printStackTrace();
			response=new JSONObject();
			response.put("response-code", "1");
			response.put("message", "Failure");
			return response;
		}

		return response;
	}
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	@Path("/OrderHistory")

	public JSONObject orderHistory(JSONObject jsonObject)	
	{
		JSONObject response=new JSONObject(); 
		try {
			StatementNReportDao srd=new StatementNReportDao();
			String agent_id=jsonObject.getString("agent_id");
			logger.info("TransactionController.orderHistory()"+jsonObject.toString());
			MobileAppRechargeDao mrd=new MobileAppRechargeDao();
			String txn_key=jsonObject.getString("txn_key");
			String txn_key_val=mrd.txn_key_Validation(txn_key, agent_id);
			if(!txn_key_val.equalsIgnoreCase("Y"))
			{
				response.put("response-code", "1");
				response.put("response-message", "Invalid request!");
			}else{
				response=srd.orderHistory(agent_id);
				if(response==null)
				{
					
					response=new JSONObject();
					response.put("response-code", "1");
					response.put("response-message", "Your have not order any thing yet.");
				}
			}

		
			
		} catch (Exception e) {
			e.printStackTrace();
			response=new JSONObject();
			response.put("response-code", "1");
			response.put("message", "Failure");
			return response;
		}

		return response;
	}
	

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/SearchTran")

	public JSONObject searchTran(JSONObject jsonObject )	
	{

		JSONObject response=new JSONObject();
		logger.info("TransactionController.searchTran()"+jsonObject.toString());

		try {

			String agent_id=jsonObject.getString("agent_id");
			String connNo=jsonObject.getString("connection_no");
			String date=jsonObject.getString("date");
			String txn_key=jsonObject.getString("txn_key");

			MobileAppRechargeDao mobileAppRechargeDao=new MobileAppRechargeDao(); 
			String txn_key_val=mobileAppRechargeDao.txn_key_Validation(txn_key, agent_id);
			if(!txn_key_val.equalsIgnoreCase("Y"))
			{
				response.put("response-code", "1");
				response.put("response-message", "Invalid request!");
			}else{
				
				SearchTransactionDao searchTransactionDao=new SearchTransactionDao();
				
				response=searchTransactionDao.searchTransaction(agent_id, connNo,date);
				if(response!=null)
				{
					logger.info(response);
					request.setAttribute("result",response.toString());
					return response;
				}else{
					response=new JSONObject();
					response.put("response-code", "1");
					response.put("response-message", "Transaction Not Available");
				}
			}
			
		} catch (Exception e) {

			e.printStackTrace();
			response=new JSONObject();
			response.put("status", "1");
			response.put("message", "Failure");
			return response;
		}
		
		return response;

	}
	
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/TranStatus")

	public JSONObject tranStatus(JSONObject jsonObject )	
	{

		JSONObject response=new JSONObject();
		logger.info("TransactionController.searchTran()"+jsonObject.toString());

		try {

			String agent_id=jsonObject.getString("agent_id");
			String txnId=jsonObject.getString("txn_id");
			String txn_key=jsonObject.getString("txn_key");

			MobileAppRechargeDao mobileAppRechargeDao=new MobileAppRechargeDao(); 
			String txn_key_val=mobileAppRechargeDao.txn_key_Validation(txn_key, agent_id);
			if(!txn_key_val.equalsIgnoreCase("Y"))
			{
				response.put("response-code", "1");
				response.put("response-message", "Invalid request!");
			}else{
				
				SearchTransactionDao searchTransactionDao=new SearchTransactionDao();
				
				response=searchTransactionDao.transactionStatus(agent_id, txnId);
				if(response==null)
				{
					response=new JSONObject();
					response.put("response-code", "1");
					response.put("response-message", "Transaction Not Available");
				}
			}
			
		} catch (Exception e) {

			e.printStackTrace();
			response=new JSONObject();
			response.put("response-code", "1");
			response.put("message", "Failure");
			return response;
		}
		
		return response;

	}

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/MobileHistory")

	public JSONObject getRestaurantsByArea(JSONObject obj )	
	{

		JSONObject response=new JSONObject();
		logger.info("RequestController.areaByCity()");

		try {

		} catch (Exception e) {

			e.printStackTrace();
			response=new JSONObject();
			response.put("response-code", "1");
			response.put("message", "Failure");
			return response;
		}
		return response;
	}

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/DthHistory")

	public JSONObject getRestaurantsCategory(JSONObject obj )	
	{

		JSONObject response=new JSONObject();
		logger.info("TransactionController.getRestaurantsCategory()");

		try {

		} catch (Exception e) {

			e.printStackTrace();
			response=new JSONObject();
			response.put("response-code", "1");
			response.put("message", "Failure");
			return response;
		}
		return response;
	}

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/DataCardHistory")

	public JSONObject signup(JSONObject jsonObject)	
	{

		//Name,Email,Mobile,Password,ConfirmPassword
		JSONObject response=new JSONObject();
		logger.info("RequestController.signup()");

		try {
			
			
		} catch (Exception e) {

			e.printStackTrace();
			response=new JSONObject();
			jsonObject.put("response-code", "1");
			jsonObject.put("message", "Unable to process your request. Please try later.");
			return jsonObject;
		}

		return response;
	}

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/ElecHistory")

	public JSONObject signin(JSONObject jsonObject)	
	{

		JSONObject response=new JSONObject();
		//LoginId,Password
		logger.info("RequestController.foodOrder()");

		try {

		} catch (Exception e) {

			e.printStackTrace();
			response=new JSONObject();
			response.put("response-code", "1");
			response.put("message", "Login Failed");
			return response;
		}
		return response;
	}

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/MobilePostpaidHistory")

	public JSONObject lostPassword(JSONObject jsonObject)	
	{

		JSONObject response=new JSONObject();
		logger.info("RequestController.lostPassword()");

		try {

		} catch (Exception e) {

			e.printStackTrace();
			response=new JSONObject();
			response.put("response-code", "1");
			response.put("message", "Invalid User Credentials");
			return response;
		}
		return response;
	}

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/LandLineHistory")

	public JSONObject getRestaurantsItems(JSONObject obj )	
	{

		JSONObject response=new JSONObject();
		logger.info("RequestController.GetRestaurantsItems()");

		try {

		} catch (Exception e) {

			e.printStackTrace();
			response=new JSONObject();
			response.put("response-code", "1");
			response.put("message", "Invalid Request!");
			return response;
		}
		return response;
	}
	
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/GasHistory")

	public JSONObject foodOrder(JSONObject jsonObject)	
	{

		JSONObject response=new JSONObject();
		logger.info("RequestController.foodOrder()");
		logger.info("reqObject : "+jsonObject.toString());

		try {

		} catch (Exception e) {

			e.printStackTrace();
			response=new JSONObject();
			response.put("response-code", "1");
			response.put("message", "Invalid Request!");
			return response;
		}
		return response;
	}

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/WateHistory")

	public JSONObject waterHistory(JSONObject jsonObject)	
	{

		JSONObject response=new JSONObject();
		logger.info("RequestController.foodOrder()");
		logger.info("reqObject : "+jsonObject.toString());

		try {

		} catch (Exception e) {

			e.printStackTrace();
			response=new JSONObject();
			response.put("response-code", "1");
			response.put("message", "Invalid Request!");
			return response;
		}
		return response;
	}
	
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/InsuranceHistory")

	public JSONObject insuranceHistory(JSONObject jsonObject)	
	{

		JSONObject response=new JSONObject();
		logger.info("RequestController.foodOrder()");
		logger.info("reqObject : "+jsonObject.toString());

		try {

		} catch (Exception e) {

			e.printStackTrace();
			response=new JSONObject();
			response.put("response-code", "1");
			response.put("message", "Invalid Request!");
			return response;
		}
		return response;
	}

}
