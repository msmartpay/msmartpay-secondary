/*
  Class Property :  This class (AgentRegistrationAction) is created for adding new agents in system.

  Created Date   : 19-Nov-2011 at 10:20 PM.
  Created By     : Amit Pathak

  Updated Date   : 13-DEC-2012 
  Update By      : Monika Nethani

 */

package com.agentregistration;


import java.util.ArrayList;
import java.util.HashMap;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import com.common.LogWriter;
import com.common.SendSmsOnMobile;
import com.common.SendSmtpMail;
import com.listener.StateDistrictDetailsDao;
import com.login.LoginDao;

public class AgentRegistrationAction {

	Logger logger = Logger.getLogger(AgentRegistrationAction.class);
	LogWriter log=new LogWriter();

	public JSONObject getState(JSONObject requestObject) {

		JSONObject responseJson=new JSONObject();
		try{				
			String userId=requestObject.getString("distributorId");
			String txnkey=requestObject.getString("txnkey");
			if(txnkey!=null){

				LoginDao dao=new LoginDao();
				if(dao.varifyTxnKey(Long.parseLong(userId), txnkey)){


					StateDistrictDetailsDao  stateDao = new StateDistrictDetailsDao();
					JSONArray stateList=stateDao.getState();
					if (stateList!=null){
						responseJson.put("StateList",stateList.toString());
						responseJson.put("message","Success");
						responseJson.put("status","0");
					}else{
						responseJson.put("message","State List not available.");
						responseJson.put("status","1");
					}


				}else{
					responseJson.put("message", "Invalid request.");
					responseJson.put("status", "1");
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

	public JSONObject getStateDistrict(JSONObject requestObject) {

		JSONObject responseJson=new JSONObject();
		try{				
			String userId=requestObject.getString("distributorId");
			String txnkey=requestObject.getString("txnkey");
			if(txnkey!=null){

				LoginDao dao=new LoginDao();
				if(dao.varifyTxnKey(Long.parseLong(userId), txnkey)){

					StateDistrictDetailsDao  stateDao = new StateDistrictDetailsDao();
					JSONArray districtList=stateDao.getStateDistrict(requestObject.getString("state"));
					if (districtList!=null){
						responseJson.put("districtList",districtList.toString());
						responseJson.put("message","Success");
						responseJson.put("status","0");
					}else{
						responseJson.put("message","DistrictList not available.");
						responseJson.put("status","1");
					}

				}else{
					responseJson.put("message", "Invalid request.");
					responseJson.put("status", "1");
				}

			}
		}catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
			responseJson.put("message", "Process aborted due to Technical Failure.");
			responseJson.put ("status","1");
		}
		return responseJson;
	}



	public JSONObject agentRegistrationAction(JSONObject requestObject) {

		JSONObject responseJson=new JSONObject();
		String param=requestObject.getString("param");
		try{				
			String userId=requestObject.getString("distributorId");
			String txnkey=requestObject.getString("txnkey");
			if(txnkey!=null){

				LoginDao dao=new LoginDao();
				if(dao.varifyTxnKey(Long.parseLong(userId), txnkey)){
					if(param.equals("AgentRegistration")){
					AgentRegistrationDao registrationDao = new AgentRegistrationDao();
							

						String DSFullId=requestObject.getString("DSFullId");   
						String firstName=requestObject.getString("Firstname");   			 
						String lastname=requestObject.getString("Lastname"); 
						if(lastname==null){
							lastname="";
						}		
						String dateofbirth =requestObject.getString("dateofbirth");
						String gender=requestObject.getString("Gender");			
						String companyType=requestObject.getString("CompanyType");			
						String companyName=requestObject.getString("AgencyName");
						String panNo=requestObject.getString("PanNo");	
						String officeAddress1=requestObject.getString("Address");
						String officeAddress2="";
						String officeState=requestObject.getString("State");
						String officeDistrict=requestObject.getString("District");
						String officeCity=requestObject.getString("City");					
						String officeCountry=requestObject.getString("Country");
						String officePincode=requestObject.getString("Pincode");
						String AuthoEmailId=requestObject.getString("EmailId");
						String AthoMobile=requestObject.getString("AthoMobile");

						String emailResponse=registrationDao.chkEmail(AuthoEmailId);
						if(emailResponse.equals("invalid")){					
							String message="User Name is Duplicate.";
							responseJson.put("message",message);
							responseJson.put("status", "1");
						}			
						String mobileResponse=registrationDao.chkMobile(AthoMobile);
						if(mobileResponse.equals("invalid")){
							String message="Mobile Number is Duplicate.";					
							responseJson.put("message",message);
							responseJson.put("status","1");
						}			
						String  password=registrationDao.getRandomString(10);	

						HashMap<String,Object> mapAgentDetails=registrationDao.registerAgent(DSFullId,firstName,lastname,dateofbirth,gender,companyType,companyName,panNo,officeAddress1,officeAddress2,officeState,officeDistrict,officeCountry,officeCity,officePincode,AuthoEmailId,password,AthoMobile);
						if(mapAgentDetails==null){
							responseJson.put("message", "Process aborted due to Technical Failure.");	
							responseJson.put("status","1");	
						}

						//String agentId=(String)mapAgentDetails.get("agentId");				
						String completeAgentId=(String)mapAgentDetails.get("completeAgentId");			
						String distributorEmailId=(String)mapAgentDetails.get("distributorEmailId");
						String clientFlag=(String)mapAgentDetails.get("clientFlag");			
						String message="Congrats! Your mobile successfully Activated for transactions using Android APP. Your Mobile Password is "+password+". Delete this SMS after storing MPIN at safe place";
						responseJson.put("status", "0");
						SendSmsOnMobile.sendMobileSmsSMSZONE(AthoMobile,message); 


						if(clientFlag.equalsIgnoreCase("1")){
						}
						else{    
						}	
						String agentLoginUrl="#";			  
						//String agentCellEmailId=requestObject.getString("agentCellEmailId");
						//System.out.println("agentCellEmailId----->>>>>>>>>>>>>>>>>>"+agentCellEmailId);		   



						System.out.println("clientFlag : "+clientFlag);

						String recipientsagnt[]={AuthoEmailId,distributorEmailId};//emailId
						String subjectagnt="User Registration – Retailer";
						String Messageagnt="<html><BODY cellpadding=\"0\" cellspacing=\"0\"><table border=\"0\"  width=\"100%\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" hspace=\"0\" vspace=\"0\"  bordercolor=\"#FFFFFF\"><tr><td><table height=\"200\" ><tr><td style='font-family:\"Trebuchet MS\";font-size:14px;'>Dear &nbsp;User,</td></tr>"+
						"<p><tr><td>"+
						"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td colspan='3'>&nbsp;</td></tr><tr><td colspan='3' style='font-family:\"Trebuchet MS\";font-size:14px;'>Your Retailer ID has been registered. Your Login Details are given below.<br></td></tr>"+
						"<tr><td colspan='3'>Web Login Details<br></td></tr>"+
						"<tr><td colspan='3'><br></td></tr>"+
						"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;width:150px;' align='left'>Login URL&nbsp;</td><td align='center' style='width:50px;font-family:\"Trebuchet MS\";font-size:14px;'>:</td><td align='left' style='font-family:\"Trebuchet MS\";font-size:14px;'><a href=\""+agentLoginUrl+"\">"+agentLoginUrl+"</a><br></td></tr>"+
						"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;' align='left'>User ID&nbsp;</td><td align='center' style='width:50px;font-family:\"Trebuchet MS\";font-size:14px;'>:</td><td align='left' style='font-family:\"Trebuchet MS\";font-size:14px;'><font>"+AuthoEmailId+"</font><br></td></tr>"+
						"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;' align='left'>Password&nbsp;</td><td align='center' style='width:50px;font-family:\"Trebuchet MS\";font-size:14px;'>:</td><td align='left' style='font-family:\"Trebuchet MS\";font-size:14px;'><font>"+password+"</font><br></td></tr>"+
						"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;' align='left'>Agent ID&nbsp;</td><td align='center' style='width:50px;font-family:\"Trebuchet MS\";font-size:14px;'>:</td><td align='left' style='font-family:\"Trebuchet MS\";font-size:14px;'><font>"+completeAgentId+"</font><br></td></tr>"+
						"<tr><td colspan='3'>Mobile App Login Details<br></td></tr>"+
						"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;' align='left'>App User ID&nbsp;</td><td align='center' style='width:50px;font-family:\"Trebuchet MS\";font-size:14px;'>:</td><td align='left' style='font-family:\"Trebuchet MS\";font-size:14px;'><font>"+AthoMobile+"</font><br></td></tr>"+
						"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;' align='left'>App Password&nbsp;</td><td align='center' style='width:50px;font-family:\"Trebuchet MS\";font-size:14px;'>:</td><td align='left' style='font-family:\"Trebuchet MS\";font-size:14px;'><font>"+password+"</font><br></td></tr>"+
						"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;' align='left'>Partner Email Id&nbsp;</td><td align='center' style='width:50px;font-family:\"Trebuchet MS\";font-size:14px;'>:</td><td align='left' style='font-family:\"Trebuchet MS\";font-size:14px;'><font>"+distributorEmailId+"</font><br></td></tr>"+
						"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;' align='left'>Partner ID&nbsp;</td><td align='center' style='width:50px;font-family:\"Trebuchet MS\";font-size:14px;'>:</td><td align='left' style='font-family:\"Trebuchet MS\";font-size:14px;'><font>"+DSFullId+"</font><br></td></tr>"+
						"<tr><td colspan='3'>&nbsp;<br></td></tr>"+
						"<tr><td colspan='3' style='font-family:\"Trebuchet MS\";font-size:14px;'>Please always quote your Retailer ID in further communication with us.<br><br></tr>"+
						"</table></td></tr></p>"+
						"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;'>Regards,</td></tr>"+
						"<tr><td>&nbsp;</td></tr>"+
						"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;'>Team Activation</td></tr><br>"+				   
						"</tr></td></table></BODY></html>";				
						try {
							SendSmtpMail.sendSSLMessage(recipientsagnt,subjectagnt,Messageagnt,"support@smartkinda.com");
						}catch (Exception e){

							System.out.println("AgentRegistrationAction.execute()"+e.getMessage());
						}

						/*double dutAmount =requestObject.getDouble("agent_creation_charge");
					System.out.println("dutAmount   "+dutAmount);
					String IpAdress=requestObject.getString("RemoteAddr");						
					String trnId=registrationDao.getTranId();
					String refrId=registrationDao.getTranId();	*/	
						//String update = registrationDao.updateAgentCreationCharge(userId,trnId,refrId,IpAdress,dutAmount,completeAgentId);					
						message="Thanks for Registering with us. Please check your Email for Information regarding User ID & Password.";
						responseJson.put("message",message);
						responseJson.put("status","0");			

					}else{
						responseJson.put("message", "Invalid request.");
						responseJson.put("status", "1");

					}
				}
				else{
					responseJson.put("message", "Invalid request.");
					responseJson.put("status", "1");

				}
			}

			AgentRegistrationDao registrationDao =new AgentRegistrationDao();
			if(param.equalsIgnoreCase("CheckEmail")){				 
				String email=requestObject.getString("email");	
				String status=registrationDao.checkEmail(email);
				System.out.println("Status :: "+status);
				System.out.print(status);
				return null;				
			}
			if(param.equalsIgnoreCase("CheckMob")){				 
				String mob=requestObject.getString("mob");				  
				String status=registrationDao.checkMob(mob);
				System.out.println("Status :: "+status);				 	  
				System.out.print(status);
				return null;		  
			}


		}catch(Exception ex){
			ex.printStackTrace();
			responseJson.put("message", "Process aborted due to Technical Failure.");
			responseJson.put ("status","1");
		}
		return responseJson;	
	}	
}