package com.smartkinda.ds.controller;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import org.apache.log4j.Logger;
import com.Password.PasswordAction;
import com.login.LoginAction;
import net.sf.json.JSONObject;

@Path("/DSLogin")
public class LoginController {

	Logger logger = Logger.getLogger(LoginController.class);


	@Context HttpServletRequest request;

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/Login")

	public JSONObject login(JSONObject jsonObject)	
	{

		JSONObject responseJson=new JSONObject();
		//LoginId,Password
		logger.info("Login : "+request.getQueryString());

		try {

			LoginAction action=new LoginAction();
			responseJson=action.dsLogin(jsonObject);


		} catch (Exception e) {

			e.printStackTrace();
			responseJson=new JSONObject();
			responseJson.put("status", "1");
			responseJson.put("message", "Login Failed");
			return responseJson;
		}
		return responseJson;
	}
	
	/*--------------   forgetPassword  ----------------*/
	
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/ForgetPass")

	public JSONObject forgetPassword(JSONObject jsonObject)	{

		JSONObject responseJson=new JSONObject();
		logger.info("ForgetPassword : "+request.getQueryString());
		try 
		{
			PasswordAction passAction = new PasswordAction();
			responseJson=passAction.forgetPassword(jsonObject);

		} catch (Exception e) {

			e.printStackTrace();
			responseJson=new JSONObject();
			responseJson.put("status", "1");
			responseJson.put("message", "Your email id is not registered.Plz, check your mail id.");
			return responseJson;
		}
		return responseJson;

	}

	//--------------   changePassword  ----------------//
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/ChangePass")

	public JSONObject changePassword(JSONObject jsonObject )	
	{

		JSONObject responseJson=new JSONObject();
		logger.info("changePassword : "+request.getQueryString());

		try {
			PasswordAction passAction = new PasswordAction();
			responseJson=passAction.changePassword(jsonObject);
			
			
		} catch (Exception e) {

			e.printStackTrace();
			responseJson=new JSONObject();
			responseJson.put("response-code", "1");
			responseJson.put("response-message", "Invalid Request!");
			return responseJson;
		}
		return responseJson;

	}

	
}
