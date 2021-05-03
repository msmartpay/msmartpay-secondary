package com.msmart.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import org.apache.log4j.Logger;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.listener.StateDistrictDetailsDao;
import com.msmart.dao.ChangePasswordDao;
import com.msmart.dao.CommonServiceDao;
import com.msmart.dao.ForgetPasswordDao;
import com.msmart.dao.MobileAppRechargeDao;
import com.msmart.dao.OtpValidationDao;
import com.msmart.dao.RechargeControllerDao;
import com.msmart.dao.RegistrationDao;
import com.msmart.service.Encryption;
import com.msmart.service.MSmartPropertyFile;
import com.msmart.util.SendSmsOnMobile;
import com.msmart.util.SendSmtpMail;

@Path("SKLogin")
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
		logger.info("Login : "+jsonObject.toString());

		try {

			RechargeControllerDao rcd=null;

			String mobileNo=jsonObject.getString("mobileNo");
			String password=jsonObject.getString("password");
			String app_version=jsonObject.getString("version");



			if(mobileNo.equals("") || password.equals(""))
			{
				responseJson.put("response-code", "1");
				responseJson.put("response-message", "Invalid Username or password!");

			}else{

				rcd=new RechargeControllerDao();

				String ServerURL=request.getRequestURI();
				String ServerName=request.getServerName();
				int ServerPort=request.getServerPort();
				String port=Integer.toString(ServerPort);

				String URLString="http://"+ServerName+":"+port+ServerURL;
				String queryString = request.getQueryString();

				String sendUrl=URLString+'?'+queryString;

				if(app_version.equalsIgnoreCase("6.0"))
				{
					responseJson.put("response-code", "2");
					responseJson.put("app-link", "https://play.google.com/store/apps/details?id=msmartpay.in");
					responseJson.put("response-message", "Please Install Updated Version!");
				}else{

					logger.debug("Recieved  "+sendUrl);
					responseJson=rcd.agentAuthenticationDao(mobileNo, password);

				}

			}

			return responseJson;


		} catch (Exception e) {

			e.printStackTrace();
			responseJson=new JSONObject();
			responseJson.put("response-code", "1");
			responseJson.put("response-message", "Login Failed");
			return responseJson;
		}

	}

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/ForgetPass")

	public JSONObject forgetPass(JSONObject jsonObject)	
	{
		String message="Invalid Mobile: Please fill correct mobile and retry!";

		JSONObject responseJsonObject=new JSONObject();
		String mob=jsonObject.getString("mob");
		String OTP=Encryption.generateOtp();

		ForgetPasswordDao fpd=new ForgetPasswordDao();
		System.out.println("RequestController.forgetPass()");

		try {

			if(mob==null || mob.length()==0)
			{
				responseJsonObject.put("response-code", "1");
				responseJsonObject.put("response-message", "Invalid Mobile: Please fill correct mobile and retry!");
			}else{
				String agentId=fpd.validateMobileNo(mob,OTP);
				if(agentId!=null){
					String updateStatus=fpd.updatePasswordProcess(Long.parseLong(agentId), OTP);
					if("Y".equalsIgnoreCase(updateStatus)){
						String smsmText="Dear Partner, Your updated password is "+OTP+". Please change after login.";
						
						SendSmsOnMobile.sendMobileSmsSMSZONE(mob,smsmText);
						System.out.println("Paasword"+message);
						responseJsonObject.put("response-code", "0");
						responseJsonObject.put("agentId",agentId);
						responseJsonObject.put("response-message", "Updated password has been sent to you registered mobile number.,");
					
					}else{
						responseJsonObject.put("response-code", "1");
						responseJsonObject.put("message", "Unable to process your request.");
					}
				}else{
					responseJsonObject.put("response-code", "1");
					responseJsonObject.put("response-message", "Invalid mobile number.");
				}
				
			}
			
			return responseJsonObject;


		} catch (Exception e) {

			e.printStackTrace();
			responseJsonObject=new JSONObject();
			responseJsonObject.put("response-code", "1");
			responseJsonObject.put("response-message", "Failure");
			return responseJsonObject;
		}

	}
	
	/*@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/ForgetPass")

	public JSONObject forgetPass(JSONObject jsonObject)	
	{
		String message="Invalid Mobile: Please fill correct mobile and retry!";

		JSONObject responseJsonObject=new JSONObject();
		String mob=jsonObject.getString("mob");
		String OTP=Encryption.generateOtp();

		ForgetPasswordDao fpd=new ForgetPasswordDao();
		System.out.println("RequestController.forgetPass()");

		try {

			if(mob==null || mob.length()==0)
			{
				responseJsonObject.put("response-code", "1");
				responseJsonObject.put("response-message", "Invalid Mobile: Please fill correct mobile and retry!");
			}else{
				String agentId=fpd.validateMobileNo(mob,OTP);
				if(agentId!=null){
						
						message="Your OTP has been sent to your registered mobile number!";
						responseJsonObject.put("response-code", "0");
						responseJsonObject.put("agentId",agentId);
						responseJsonObject.put("response-message",message);
						
						String smsmText="Dear Partner, Your need an OTP for your mobile verification. The OTP is "+OTP+". Never share it  with anyone.";
						
						SendSmsOnMobile.sendMobileSmsSMSZONE(mob,smsmText);
						System.out.println("Paasword"+message);

					}
				else{
					responseJsonObject.put("response-code", "1");
					responseJsonObject.put("response-message", message);
				}
				}
			
			return responseJsonObject;


		} catch (Exception e) {

			e.printStackTrace();
			responseJsonObject=new JSONObject();
			responseJsonObject.put("status", "1");
			responseJsonObject.put("message", "Success");
			return responseJsonObject;
		}

	}*/

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/resetPass")

	public JSONObject getresetPass(JSONObject jsonObject)	
	{
		
		String message="OTP: please enter valid OTP!";

		JSONObject responseJsonObject=new JSONObject();
		String agentId=jsonObject.getString("agentId");
		String OTP=jsonObject.getString("OTP");
		String pas=jsonObject.getString("pas");
		

		System.out.println("RequestController.foodOrder()");

		try {

			if(agentId==null || agentId.length()==0)
			{
				responseJsonObject.put("response-code", "1");
				responseJsonObject.put("response-message", "OTP: please enter valid OTP!");
			}else{
				ForgetPasswordDao resepd=new ForgetPasswordDao();

				String result=resepd.updatePasswordProcess(Long.parseLong(agentId), OTP, pas);
				if(result.equalsIgnoreCase("Y"))
				{
					responseJsonObject.put("response-code", "0");
					responseJsonObject.put("response-message", " Your password has been successfully changed!");

				}
				else
				{
					responseJsonObject.put("response-code", "1");
					responseJsonObject.put("response-message", message);

				}
			}
			
			return responseJsonObject;


		} catch (Exception e) {

			e.printStackTrace();
			responseJsonObject=new JSONObject();
			responseJsonObject.put("status", "1");
			responseJsonObject.put("message", "Success");
			return responseJsonObject;
		}

	}


	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/ChangePass")

	public JSONObject changePass(JSONObject jsonObject )	
	{

		JSONObject responseJson=new JSONObject();
		System.out.println("RequestController.getRestaurantsItemsByBrand()");
		logger.info("reqObject : "+jsonObject.toString());
		try {

			String user_id=jsonObject.getString("agent_id");
			String old_pass=jsonObject.getString("oldpass");
			String new_pass=jsonObject.getString("newpass");

			CommonServiceDao commonServiceDao=new CommonServiceDao();

			user_id=user_id.replaceAll("AG", "").replaceAll("ag", "").replaceAll("aG", "").replaceAll("Ag", "");

			String txn_key=jsonObject.getString("txn_key");
			String txn_key_val=commonServiceDao.txn_key_Validation(txn_key, user_id);
			if(!txn_key_val.equalsIgnoreCase("Y"))
			{
				responseJson.put("response-code", "1");
				responseJson.put("response-message", "Invalid request!");
				return responseJson;
			}

			if(user_id==null || user_id.length()==0 || old_pass==null || old_pass.length()==0 || new_pass==null || new_pass.length()==0)
			{
				responseJson.put("response-code", "1");
				responseJson.put("response-message", "Invalid request!");

			}else if(old_pass.equalsIgnoreCase(new_pass)){
				responseJson.put("response-code", "1");
				responseJson.put("response-message", "New Password Must Be Different From Old Password.");
			}else if(new_pass.length()<6 ){
				responseJson.put("response-code", "1");
				responseJson.put("response-message", "Please Enter Valid 6-Digits Password.");
			}else{
				ChangePasswordDao cpd=new ChangePasswordDao();

				int result=cpd.changePassword(Long.parseLong(user_id), new_pass, old_pass);
				if(result==1)
				{
					responseJson.put("response-code", "0");
					responseJson.put("response-message", " Your password has been successfully changed!");

				}
				else
				{
					responseJson.put("response-code", "1");
					responseJson.put("response-message", "We can't change your password, because your old password is not correct!");

				}
			}
			return responseJson;

		} catch (Exception e) {

			e.printStackTrace();
			responseJson=new JSONObject();
			responseJson.put("response-code", "1");
			responseJson.put("response-message", "Invalid Request!");
			return responseJson;
		}

	}

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/generatetoken")

	public JSONObject generateToken(JSONObject jsonObject)	
	{

		JSONObject response=new JSONObject();
		//LoginId,Password

		try {

			System.out.println("reqObject : "+jsonObject.toString());

			String key=jsonObject.getString("Key");
			String user_id=jsonObject.getString("agent_id");
			String token=jsonObject.getString("token");



			CommonServiceDao daoo=new CommonServiceDao();
			String txn_key_val=daoo.txn_key_Validation(key, user_id);
			if(txn_key_val.equalsIgnoreCase("Y"))
			{

				String getToken=daoo.tokenValidation(user_id, token);

				if( getToken.equalsIgnoreCase("Y")){
					response.put("status", "0");
					response.put("message", "success");
				}else{
					response.put("status", "2");
					response.put("message", "token not generated");
				}
			}else{
				response.put("status", "1");
				response.put("message", "Invalid Request!");
			}

			return response;


		} catch (Exception e) {

			e.printStackTrace();
			response=new JSONObject();
			response.put("status", "1");
			response.put("message", "Failure");
			return response;
		}

	}


	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/getOperator")

	public JSONObject getOperator(JSONObject jsonObject)	
	{

		JSONObject response=new JSONObject();
		//LoginId,Password

		try {

			System.out.println("reqObject : "+jsonObject.toString());

			String key=jsonObject.getString("Key");
			String user_id=jsonObject.getString("agent_id");

			CommonServiceDao daoo=new CommonServiceDao();
			String txn_key_val=daoo.txn_key_Validation(key, user_id);
			if(txn_key_val.equalsIgnoreCase("Y"))
			{

				ArrayList<HashMap<String, Object>> operaterList=daoo.getOprator(user_id);

				if( operaterList!=null && operaterList.size()>0){
					response.put("status", "0");
					response.put("message", "success");
					response.put("operaterList", operaterList);
				}else{
					response.put("status", "2");
					response.put("message", "Operator not avalable");
				}
			}else{
				response.put("status", "1");
				response.put("message", "Invalid Request!");
			}

			return response;


		} catch (Exception e) {

			e.printStackTrace();
			response=new JSONObject();
			response.put("status", "1");
			response.put("message", "Failure");
			return response;
		}

	}

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/mobileValidation")
	public JSONObject mobileValidation(JSONObject jsonObject)	
	{

		JSONObject response=new JSONObject();
		//LoginId,Password



		System.out.println("reqObject : "+jsonObject.toString());

		String mobile=jsonObject.getString("mobile");
		if(mobile!=null && mobile.length()>0)
		{
			try {

				OtpValidationDao dao=new OtpValidationDao();
				String mobileValidation=dao.validateMobileNo(mobile);
				if(mobileValidation!=null && mobileValidation.length()>0)
				{

					response.put("response-code", "2");
					response.put("response-message", "Dear Customer, Your  mobile number is allready Exist ");

					return response;
				}


				else {

					String otp=Encryption.generateOtp();
					JSONObject  Otpgenrate=dao.insertOTP(otp, mobile);

					if(Otpgenrate!=null && Otpgenrate.size()>0)
					{

						response.put("response-code", "0");
						response.put("response-message", " Dear Customer, OTP has been sent on your mobile number");
						String Mobilemessage="Dear Customer, Your  mobile number is "+Otpgenrate.getLong("mobile")+" and One Time Password (OTP) for MSmartPay Customer Registration is "+Otpgenrate.getLong("otp")+", Please Do Not share This Any Body.";

						SendSmsOnMobile.sendMobileSmsSMSZONE(mobile, Mobilemessage);
						return response;


					}
					else {



						response.put("response-code", "1.");
						response.put("response-message", " Process aborted due to technical failure.");
					}

				}







			} catch (Exception e) {

				e.printStackTrace();
				response=new JSONObject();
				response.put("response-code", "1");
				response.put("response-message", "Process aborted due to technical failure.");
				return response;


			}
		}
		return response;
	}

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/OtpValidation")
	public JSONObject OtpValidation(JSONObject jsonObject)	
	{

		JSONObject response=new JSONObject();
		//LoginId,Password



		System.out.println("reqObject : "+jsonObject.toString());

		String otp=jsonObject.getString("otp");
		String mobile=jsonObject.getString("mobile");
		if(otp!=null && otp.length()>0)
		{
			try {


				OtpValidationDao dao=new OtpValidationDao();
				String otpValidation=dao.validateOTP(mobile, otp);

				if(otpValidation.equalsIgnoreCase("Y"))
				{
					response.put("response-code", "0");
					response.put("response-message", "Congratulation! Your mobile number variefied successfully.");
					return response;
				}
				else {
					response.put("response-code", "1");
					response.put("response-message", "We are unable to verify your mobile. Please try later.");
				}




				return response;


			} catch (Exception e) {

				e.printStackTrace();
				response=new JSONObject();
				response.put("response-code", "1");
				response.put("response-message", "We are unable to verify your mobile. Please try later.");
				return response;


			}
		}
		return response;
	}

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/SKRegister")

	public JSONObject signup(JSONObject jsonObject )	
	{

		JSONObject registerJsonObject=new JSONObject();
		try {

			String dsid="";
			if(jsonObject.containsKey("dsid"))
				dsid=jsonObject.getString("dsid");
			else
				dsid="DSUP33273056";

			final String firstname=jsonObject.getString("firstname");
			final String lastname=jsonObject.getString("lastname");
			final String firmname=jsonObject.getString("firmname");
			final String email=jsonObject.getString("email");
			final String mobileno=jsonObject.getString("mobileno");

			final String address=jsonObject.getString("address");
			final String state=jsonObject.getString("state");
			final String ditrict=jsonObject.getString("city");
			final String city=jsonObject.getString("city");
			final String pincode=jsonObject.getString("pincode");
			String password=jsonObject.getString("password");
			final String referral_code=jsonObject.getString("referral_code");

			String longitude=jsonObject.getString("longitude");
			String latitude=jsonObject.getString("latitude");
			if(longitude==null)
				longitude="";
			if(latitude==null)
				latitude="";

			String location=longitude+latitude;

			String gender="Male";

			String dateOfBirth="2000-10-12";
			String pannumber="";

			logger.info("RegistrationAction.execute().. "+firstname+" : "+lastname+" : "+dateOfBirth+" : "+gender
					+" : "+firmname+" : "+email+" : "+mobileno+"dsid "+dsid);
			RegistrationDao registrationDao=new RegistrationDao();
			/*String password=registrationDao.generatePassword();*/
			if(mobileno.length()==10){
				String checkEmailStatus=registrationDao.checkEmail(email);
				if(checkEmailStatus.equalsIgnoreCase("valid")){
					String checkMobileStatus=registrationDao.checkMob(mobileno);
					if(checkMobileStatus.equalsIgnoreCase("valid")){
						HashMap<String, String> map=registrationDao.agentRegistration("DSUP33273056", firstname, lastname, dateOfBirth, 
								gender, "Non_Register", firmname, email, mobileno, address, location, "India", state, ditrict, city,pincode, pannumber, password, email,referral_code);
						//String addressLine1,String addressLine2, String country, String state, String district,String cityCode, String pincode, String panNo,

						if(map!=null){
							String userId=map.get("completeAgentId");
							String pMail=map.get("distributorEmailId");
							String pid=map.get("distributorID");
							String pass=map.get("MPIN");

							//HashMap<String,String>  hashmapinfo=(HashMap<String,String>)registrationDao.getMobileappsdetails(userId,pass);

							String msg="Congratulations! Your wallet has been created successfully. Please use OTP "+pass+" to activate your account.";
							SendSmsOnMobile.sendMobileSmsSMSZONE(mobileno,msg);

							String recipients[] ={email,MSmartPropertyFile.receientMail};
							String subject="Wallet Registration";
							String message="<html><BODY cellpadding=\"0\" cellspacing=\"0\"><table border=\"0\"  width=\"100%\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" hspace=\"0\" vspace=\"0\"  bordercolor=\"#FFFFFF\"><tr><td><table height=\"200\" ><tr><td style='font-family:\"Trebuchet MS\";font-size:14px;'>Dear &nbsp;User,</td></tr>"+
									"<p><tr><td>"+
									"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td colspan='3'>&nbsp;</td></tr><tr><td colspan='3' style='font-family:\"Trebuchet MS\";font-size:14px;'>Congratulations! You have successfully registered with MSmartPay.<br></td></tr>"+
									"<tr><td colspan='3'>&nbsp;<br></td></tr>"+
									"<tr><td colspan='3'>Mobile App Login Details<br></td></tr>"+
									"<tr><td colspan='3'>&nbsp;<br></td></tr>"+
									"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;' align='left'>App User ID&nbsp;</td><td align='center' style='width:50px;font-family:\"Trebuchet MS\";font-size:14px;'>:</td><td align='left' style='font-family:\"Trebuchet MS\";font-size:14px;'><font>"+mobileno+"</font><br></td></tr>"+
									/*"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;' align='left'>App Password&nbsp;</td><td align='center' style='width:50px;font-family:\"Trebuchet MS\";font-size:14px;'>:</td><td align='left' style='font-family:\"Trebuchet MS\";font-size:14px;'><font>"+pass+"</font><br></td></tr>"+*/
									/*"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;' align='left'>Partner Email Id&nbsp;</td><td align='center' style='width:50px;font-family:\"Trebuchet MS\";font-size:14px;'>:</td><td align='left' style='font-family:\"Trebuchet MS\";font-size:14px;'><font>"+pMail+"</font><br></td></tr>"+
									"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;' align='left'>Partner ID&nbsp;</td><td align='center' style='width:50px;font-family:\"Trebuchet MS\";font-size:14px;'>:</td><td align='left' style='font-family:\"Trebuchet MS\";font-size:14px;'><font>"+pid+"</font><br></td></tr>"+*/
									"<tr><td colspan='3'>&nbsp;<br></td></tr>"+
									"<tr><td colspan='3' style='font-family:\"Trebuchet MS\";font-size:14px;'>Please always quote your App User ID in further communication with us.<br><br></tr>"+
									"</table></td></tr></p>"+
									"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;'>Regards,</td></tr>"+
									"<tr><td>&nbsp;</td></tr>"+
									"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;'>Team Activation</td></tr><br>"+				   
									"</tr></td></table></BODY></html>";

							final String ccfrom="no-reply@smartkinda.com";


							registerJsonObject.put("response-code", "0");
							registerJsonObject.put("response-message","Congratulations! You have successfully registered with MSmartPay.");


							//https://play.google.com/store/apps/details?id=com.ssz
							try {
								String mobilemsg="Congratulations! You have successfully registered with MSmartPay. Your APP Login User Id is "+mobileno+" and Password is "+pass+". Please change your password.";
								SendSmsOnMobile.sendMobileSmsSMSZONE(mobileno,mobilemsg);

							} catch (Exception e) {
								e.printStackTrace();
							}
							try {
								
								SendSmtpMail.sendSSLMessage(recipients, subject, message, ccfrom);
								
								Thread thread = new Thread(new Runnable() {
									@Override
									public void run() {

										try {
																						
											String ccrecipients[] ={MSmartPropertyFile.receientMail};
											String ccsubject="New User Registration";
											String ccmessage="<html><BODY cellpadding=\"0\" cellspacing=\"0\"><table border=\"0\"  width=\"100%\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" hspace=\"0\" vspace=\"0\"  bordercolor=\"#FFFFFF\"><tr><td><table height=\"200\" ><tr><td style='font-family:\"Trebuchet MS\";font-size:14px;'>Dear &nbsp;Team,</td></tr>"+
											"<p><tr><td>"+
											"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td colspan='3'>&nbsp;</td></tr><tr><td colspan='3' style='font-family:\"Trebuchet MS\";font-size:14px;'></td></tr>"+
											"<tr><td colspan='3'>Name : "+firstname+"</td></tr>"+
											"<tr><td colspan='3'>Mobile : "+mobileno+"</td></tr>"+
											"<tr><td colspan='3'>Email Id : "+email+"</td></tr>"+
											
											"<tr><td colspan='3'>Company Name : "+firmname+"</td></tr>"+
											"<tr><td colspan='3'>Address : "+address+"</td></tr>"+
											
											"<tr><td colspan='3'>State : "+state+"</td></tr>"+
											"<tr><td colspan='3'>District : "+ditrict+"</td></tr>"+
											"<tr><td colspan='3'>City : "+city+"</td></tr>"+
											"<tr><td colspan='3'>Refra Code : "+pincode+"</td></tr>"+
											"<tr><td colspan='3'>Refra Code : "+referral_code+"</td></tr>"+
											
											"<tr><td colspan='3'>Portal Id : Mobile APP</td></tr>"+
											"<tr><td colspan='3'>&nbsp;<br></td></tr>"+
											"</table></td></tr></p>"+
											"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;'>Regards,</td></tr>"+
											"<tr><td>&nbsp;</td></tr>"+
											"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;'>Team Activation</td></tr><br>"+				   
											"</tr></td></table></BODY></html>";
											
											SendSmtpMail.sendSSLMessage(ccrecipients, ccsubject, ccmessage, ccfrom);
											
										} catch (MessagingException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
										
										
									}

								});
								thread.start();
								
							} catch (Exception e) {
								e.printStackTrace();
							}
						}else{

							registerJsonObject.put("response-code", "1");
							registerJsonObject.put("response-message","Registration Failed. Re-try Again." );

						}
					}else{
						registerJsonObject.put("response-code", "1");
						registerJsonObject.put("response-message","Mobile Number Already In Use. Please try forget Passwor." );

					}
				}else{
					registerJsonObject.put("response-code", "1");
					registerJsonObject.put("response-message","Email ID Already in use." );
				}
			}else{
				registerJsonObject.put("response-code", "1");
				registerJsonObject.put("response-message","Invalid Mobile Number. Use 10 digit mobile No." );
			}
			System.out.println("Registration.execute() : "+registerJsonObject.toString());

			return registerJsonObject;

		} catch (Exception e) {

			e.printStackTrace();
			registerJsonObject=new JSONObject();
			registerJsonObject.put("response-code", "1");
			registerJsonObject.put("response-message", "Invalid Request!");
			return registerJsonObject;
		}

	}


	/*******************************************************>>> My Account <<<************************************************************/

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/getUpdateProfile")

	public JSONObject getUpdateProfile(JSONObject jsonObject )	
	{

		JSONObject uprofilejson=new JSONObject();
		try {

			String agentID=jsonObject.getString("agentID");
			String firstname=jsonObject.getString("firstname");
			String lastname=jsonObject.getString("lastname");
			String firmname=jsonObject.getString("firmname");
			String email=jsonObject.getString("email");
			String mobileno=jsonObject.getString("mobileno");

			String address=jsonObject.getString("address");
			String state=jsonObject.getString("state");
			String ditrict=jsonObject.getString("District");
			String city=jsonObject.getString("city");
			String pincode=jsonObject.getString("pincode");
			String country=jsonObject.getString("country");
			String DateOfBirth=jsonObject.getString("DateOfBirth");
			String gender=jsonObject.getString("gender");
			String pannumber=jsonObject.getString("pannumber");
			String txn_key=jsonObject.getString("txn_key");

			CommonServiceDao commonServiceDao=new CommonServiceDao();
			String txn_key_val=commonServiceDao.txn_key_Validation(txn_key, agentID);
			if(txn_key_val.equalsIgnoreCase("Y"))
			{

				RegistrationDao registrationDao=new RegistrationDao();
				String map=registrationDao.getUpdateprofile(agentID, firstname, lastname,gender, firmname,
						email, mobileno, address,country, state, ditrict, city, pincode,pannumber,DateOfBirth);

				if(map=="Y"){
					uprofilejson.put("status", "0");
					uprofilejson.put("message","Profile has been updated successfully");

				}else{

					uprofilejson.put("status", "1");
					uprofilejson.put("message","We are unable to update your profile");

				}

				System.out.println("Registration.execute() : "+uprofilejson.toString());

				return uprofilejson;
			}else {

				uprofilejson.put("response-code", "1");
				uprofilejson.put("response-message","Invalid request!");

			}

		} catch (Exception e) {

			e.printStackTrace();
			uprofilejson=new JSONObject();
			uprofilejson.put("status", "1");
			uprofilejson.put("message", "Invalid Request!");
			return uprofilejson;
		}
		return uprofilejson;

	}
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/getRequestKyc")

	public JSONObject getRequestKyc(JSONObject jsonObject )	
	{

		JSONObject uprofilejson=new JSONObject();
		try {

			String agentID=jsonObject.getString("agentID");
			String adharcard=jsonObject.getString("adharcardNo");
			String adharHoldername=jsonObject.getString("adharHoldername");
			String email=jsonObject.getString("email");
			String txn_key=jsonObject.getString("txn_key");

			CommonServiceDao commonServiceDao=new CommonServiceDao();
			String txn_key_val=commonServiceDao.txn_key_Validation(txn_key, agentID);
			if(txn_key_val.equalsIgnoreCase("Y"))
			{

				RegistrationDao registrationDao=new RegistrationDao();
				String map=registrationDao.getRequestKyc(agentID, adharcard, adharHoldername);

				if(map=="Y"){


					/*	String msg="Congratulations! Your KYC request has been created successfully.";
					SendSmsOnMobile.sendMobileSmsSMSZONE(mobileNo,msg);*/

					String recipients[] ={email,MSmartPropertyFile.receientMail};
					String subject="KYC Requst";
					String message="<html><BODY cellpadding=\"0\" cellspacing=\"0\"><table border=\"0\"  width=\"100%\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" hspace=\"0\" vspace=\"0\"  bordercolor=\"#FFFFFF\"><tr><td><table height=\"200\" ><tr><td style='font-family:\"Trebuchet MS\";font-size:14px;'>Dear &nbsp;User,</td></tr>"+
							"<p><tr><td>"+
							"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td colspan='3'>&nbsp;</td></tr><tr><td colspan='3' style='font-family:\"Trebuchet MS\";font-size:14px;'>Congratulations! You KYC request successfully applied with MSmartPay.<br></td></tr>"+
							"<tr><td colspan='3'>&nbsp;<br></td></tr>"+
							"<tr><td colspan='3'>Your KYC details<br></td></tr>"+
							"<tr><td colspan='3'>&nbsp;<br></td></tr>"+
							"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;' align='left'>AgentID&nbsp;</td><td align='center' style='width:50px;font-family:\"Trebuchet MS\";font-size:14px;'>:</td><td align='left' style='font-family:\"Trebuchet MS\";font-size:14px;'><font>"+agentID+"</font><br></td></tr>"+
							/*"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;' align='left'>App Password&nbsp;</td><td align='center' style='width:50px;font-family:\"Trebuchet MS\";font-size:14px;'>:</td><td align='left' style='font-family:\"Trebuchet MS\";font-size:14px;'><font>"+pass+"</font><br></td></tr>"+*/
							"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;' align='left'>Adhaar Number &nbsp;</td><td align='center' style='width:50px;font-family:\"Trebuchet MS\";font-size:14px;'>:</td><td align='left' style='font-family:\"Trebuchet MS\";font-size:14px;'><font>"+adharcard+"</font><br></td></tr>"+
							"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;' align='left'>Adhaar Holder Name &nbsp;</td><td align='center' style='width:50px;font-family:\"Trebuchet MS\";font-size:14px;'>:</td><td align='left' style='font-family:\"Trebuchet MS\";font-size:14px;'><font>"+adharHoldername+"</font><br></td></tr>"+
							"<tr><td colspan='3'>&nbsp;<br></td></tr>"+
							"<tr><td colspan='3' style='font-family:\"Trebuchet MS\";font-size:14px;'>Please always quote your Customer ID in further communication with us.<br><br></tr>"+
							"</table></td></tr></p>"+
							"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;'>Regards,</td></tr>"+
							"<tr><td>&nbsp;</td></tr>"+
							"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;'>Team Activation</td></tr><br>"+				   
							"</tr></td></table></BODY></html>";

					String from="support@smartkinda.com";
					SendSmtpMail.sendSSLMessage(recipients, subject, message, from);


					uprofilejson.put("status", "0");
					uprofilejson.put("message","Congratulations! Your KYC request has been updated successfully.");

					//https://play.google.com/store/apps/details?id=com.ssz

				}
				else if(map=="applied"){

					uprofilejson.put("status", "2");
					uprofilejson.put("message"," Your KYC request already applied.");

				}

				else{

					uprofilejson.put("status", "1");
					uprofilejson.put("message","We are unable to update your KYC");

				}

				System.out.println("Registration.execute() : "+uprofilejson.toString());

				return uprofilejson;
			}else {

				uprofilejson.put("response-code", "1");
				uprofilejson.put("response-message","Invalid request!");

			}

		} catch (Exception e) {

			e.printStackTrace();
			uprofilejson=new JSONObject();
			uprofilejson.put("status", "1");
			uprofilejson.put("message", "Invalid Request!");
			return uprofilejson;
		}
		return uprofilejson;

	}

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/getKycDetails")

	public JSONObject getKycDetails(JSONObject jsonObject )	
	{

		JSONObject uprofilejson=null;
		try {

			String agentID=jsonObject.getString("agentID");
			String txn_key=jsonObject.getString("txn_key");

			CommonServiceDao commonServiceDao=new CommonServiceDao();
			String txn_key_val=commonServiceDao.txn_key_Validation(txn_key, agentID);
			if(txn_key_val.equalsIgnoreCase("Y"))
			{

				RegistrationDao registrationDao=new RegistrationDao();
				uprofilejson=registrationDao.getKycDetails(agentID);

				if(uprofilejson!=null){

					uprofilejson.put("status", "0");
					uprofilejson.put("message", "success");
					return uprofilejson;


				}
				else{
					uprofilejson=new JSONObject();
					uprofilejson.put("status", "1");
					uprofilejson.put("message", "Please upgrade you KYC!");
				}
			}
			else{}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return uprofilejson;
	}


	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/getProfile")

	public JSONObject getProfile(JSONObject jsonObject )	
	{

		JSONObject uprofilejson=new JSONObject();
		try {

			String agentID=jsonObject.getString("agentID");
			String txn_key=jsonObject.getString("txn_key");

			CommonServiceDao commonServiceDao=new CommonServiceDao();
			String txn_key_val=commonServiceDao.txn_key_Validation(txn_key, agentID);
			if(txn_key_val.equalsIgnoreCase("Y"))
			{

				RegistrationDao registrationDao=new RegistrationDao();
				HashMap<String, String> map=registrationDao.getprofile(agentID);

				if(map!=null){

					uprofilejson.put("profile", map);
					uprofilejson.put("status", "0");
					uprofilejson.put("message", "success");

					return uprofilejson;
				}else{

					uprofilejson.put("status", "1");
					uprofilejson.put("message","Profile details are not available");

				}

				System.out.println("Registration.execute() : "+uprofilejson.toString());

				return uprofilejson;
			}else {

				uprofilejson.put("response-code", "1");
				uprofilejson.put("response-message","Invalid request!");

			}

		} catch (Exception e) {

			e.printStackTrace();
			uprofilejson=new JSONObject();
			uprofilejson.put("status", "1");
			uprofilejson.put("message", "Invalid Request!");
			return uprofilejson;
		}
		return uprofilejson;

	}

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/getState")

	public JSONObject getState(JSONObject requestObject) {

		JSONObject responseJson=new JSONObject();
		JSONArray stateArry=new JSONArray();
		try{				
			String agentID=requestObject.getString("agentID");
			String txn_key=requestObject.getString("txn_key");

			CommonServiceDao commonServiceDao=new CommonServiceDao();
			String txn_key_val=commonServiceDao.txn_key_Validation(txn_key, agentID);
			if(txn_key_val.equalsIgnoreCase("Y"))
			{


				StateDistrictDetailsDao  stateDao = new StateDistrictDetailsDao();
				ArrayList<String> stateList=stateDao.getState();
				if (stateList!=null){
					JSONObject statJson=null;
					for (int i = 0; i < stateList.size(); i++) {
						statJson=new JSONObject();
						statJson.put("state", stateList.get(i));
						stateArry.add(statJson);

					}
					responseJson.put("StateList",stateArry);
					responseJson.put("message","Success");
					responseJson.put("status","0");


				}else{
					responseJson.put("message","StateList not available.");
					responseJson.put("status","1");
				}


			}else{
				responseJson.put("message", "Invalid request.");
				responseJson.put("status", "1");
			}


		}catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
			responseJson.put("message", "Process aborted due to Technical Failure.");
			responseJson.put ("status","1");
		}
		return responseJson;
	}

	//--------------- StateDistrict	order by state -------------------------//

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/getStateDistrict")

	public JSONObject getStateDistrict(JSONObject requestObject) {

		JSONObject responseJson=new JSONObject();
		JSONArray districtArray=new JSONArray();
		try{				
			String agentID=requestObject.getString("agentID");
			String txn_key=requestObject.getString("txn_key");


			System.out.println("Request Object::"+requestObject);
			CommonServiceDao commonServiceDao=new CommonServiceDao();
			String txn_key_val=commonServiceDao.txn_key_Validation(txn_key, agentID);
			if(txn_key_val.equalsIgnoreCase("Y"))
			{

				StateDistrictDetailsDao  stateDao = new StateDistrictDetailsDao();
				JSONArray districtList=stateDao.getStateDistrict(requestObject.getString("state"));
				if (districtList!=null){
					JSONObject districtJson=null;
					for (int i = 0; i < districtList.size(); i++) {
						districtJson=new JSONObject();
						districtJson.put("district", districtList.get(i));
						districtArray.add(districtJson);							
					}

					responseJson.put("districtList",districtArray);
					responseJson.put("message","Success");
					responseJson.put("status","0");

					System.out.println("Didtrict::::::::::::::"+responseJson);
				}else{
					responseJson.put("message","DistrictList not available.");
					responseJson.put("status","1");
				}

			}else{
				responseJson.put("message", "Invalid request.");
				responseJson.put("status", "1");
			}


		}catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
			responseJson.put("message", "Process aborted due to Technical Failure.");
			responseJson.put ("status","1");
		}
		return responseJson;
	}
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/Operators")
	public JSONObject skRegisterConfirm(JSONObject jsonObject )	
	{

		JSONObject responseJson=new JSONObject();
		try {

			String agentID=jsonObject.getString("agent_id");
			String txn_key=jsonObject.getString("txn_key");

			MobileAppRechargeDao mrd=new MobileAppRechargeDao();
			String txn_key_val=mrd.txn_key_Validation(txn_key, agentID);
			if(!txn_key_val.equalsIgnoreCase("Y"))
			{
				responseJson.put("Status", "1");
				responseJson.put("message", "Unauthorized User Request!");
				return responseJson;
			}else{
				org.json.JSONArray opList=CommonServiceDao.getOpertorDetails();
				if(opList!=null && opList.length()>0){
					HashMap<String, Object> hashMap=new HashMap<>();
					hashMap.put("OperatorName", "Select Operator");
					opList.put(0,hashMap);
					responseJson.put("Operator", opList.toString());
					responseJson.put("Status", "0");
					responseJson.put("message", "Success");
				}else{
					responseJson.put("Status", "1");
					responseJson.put("message", "Not Found");
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			responseJson.put("Status", "1");
			responseJson.put("message", "Not Found");
		}

		return responseJson;
	}


}
