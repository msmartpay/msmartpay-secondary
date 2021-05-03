/*
 Class Property :  This class (CheckDetailsAction) is created to get request data of transaction details and process it accordingly.

  Created Date   : 8 january-2012 at 10.00 AM.
  Created By     : Amit Pathak

  Updated Date   : 8 january-2012
  Update By      :Amit Pathak

 */




package com.TransactionDetails;

import java.util.HashMap;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import com.common.LogWriter;
import com.login.LoginDao;




public class CheckDetailsAction {

	Logger logger = Logger.getLogger(CheckDetailsAction.class);
	LogWriter log=new LogWriter();

	public JSONObject transactionStatus(JSONObject requestObject) {

		JSONObject responseJson=new JSONObject();
		String param=requestObject.getString("param");
		try {

			String userId=requestObject.getString("distributorId");
			String txnkey=requestObject.getString("txnkey");
			if(txnkey!=null){

				LoginDao dao=new LoginDao();
				if(dao.varifyTxnKey(Long.parseLong(userId), txnkey)){


					if(param.equals("transactionPopUp"))

					{
						String Service=requestObject.getString("Service");		    
						String TransactionNo=requestObject.getString("TransactionNo");
						String TransactionStatus=requestObject.getString("TransactionStatus");
						String AgentCompleteId=requestObject.getString("AgentCompleteId");		

						if(Service.equalsIgnoreCase("transfertoagent"))
						{

							HashMap<String,Object> map=CheckDetailsDao.getPopUpDetails(Service,TransactionStatus,TransactionNo,userId);
							responseJson.put("mapTransafer",map);
							return responseJson;
						}

						if(Service.equalsIgnoreCase("AgentCreationCharge"))
						{

							HashMap<String,Object> map=CheckDetailsDao.getPopUpDetails(Service,TransactionStatus,TransactionNo,userId);	
							System.out.println("map :"+map);
							responseJson.put("mapTransafer",map);	     
							return responseJson;
						}

						if((Service.equalsIgnoreCase("transfertodist")) || (Service.equalsIgnoreCase("mdtodist")) )

						{	
							HashMap<String,Object> map=CheckDetailsDao.getPopUpDetails(Service,TransactionStatus,TransactionNo,userId);
							responseJson.put("mapTransafer",map);
							return responseJson;
						}  

						if(Service.equalsIgnoreCase("accountadjustment"))
						{
							HashMap<String,Object> map=CheckDetailsDao.getPopUpDetails(Service,TransactionStatus,TransactionNo,userId);
							responseJson.put("mapTransafer",map);
							System.out.println("map :"+map);
							return responseJson;
						}

						if(Service.equalsIgnoreCase("Account Adjustment-DS"))
						{
							HashMap<String,Object> map=CheckDetailsDao.getPopUpDetails(Service,TransactionStatus,TransactionNo,userId);
							responseJson.put("mapTransafer",map);
							System.out.println("map :"+map);
							return responseJson;
						}


						//-----------------------------------
						/*  if(Service.equalsIgnoreCase("ROffLineMobRech") || Service.equalsIgnoreCase("RliveMobRech"))
		     {

		    	 HashMap<String,Object> map=CheckDetailsDao.getPopUpDetails(Service,TransactionStatus,TransactionNo,userId);
		         session.put("mapTransafer",map);
		    	 return "OffLineMobRech";
		    }*/

						if(Service.equalsIgnoreCase("AdmintoAgent"))
						{
							HashMap<String,Object> map=CheckDetailsDao.getPopUpDetails(Service,TransactionStatus,TransactionNo,userId);
							responseJson.put("mapTransafer",map);
							return  responseJson;
						}
						//---------------- 
						if(Service.equalsIgnoreCase("Cash Back"))
						{
							HashMap<String,Object> map=CheckDetailsDao.getPopUpDetails(Service,TransactionStatus,TransactionNo,userId);
							responseJson.put("mapTransafer",map);
							return  responseJson;
						}

						//----------------------------------------------------
						/*	    if(Service.equalsIgnoreCase("RliveMobRech")&& (TransactionStatus.equalsIgnoreCase("refunded")||TransactionStatus.equalsIgnoreCase("refund")))
		    {
				// String agentid=CheckDetailsDao.getAgentid(AgentCompleteId);
				 String NewService="RliveMobRechRefunded";
				 HashMap<String,Object> map=CheckDetailsDao.getPopUpDetails(NewService,TransactionStatus,TransactionNo,userId);
			     session.put("mapTransafer",map);
		    	 return  "cashback";
			}

						 */
						/*    if(Service.equalsIgnoreCase("DisttoAgent"))
		    {
				 String agentid=CheckDetailsDao.getAgentid(AgentCompleteId);
				 HashMap<String,Object> map=CheckDetailsDao.getPopUpDetails(Service,TransactionStatus,TransactionNo,agentid);
			     session.put("mapTransafer",map);
		    	 return  "DisttoAgent";
			}*/


						if(Service.equalsIgnoreCase("liveDTHRech")|| Service.equalsIgnoreCase("liveMobRech") )
						{
							HashMap<String,Object> map=CheckDetailsDao.getPopUpDetails(Service,TransactionStatus,TransactionNo,userId);
							responseJson.put("mapTransafer",map);
							return  responseJson;
						}


						if((Service.equalsIgnoreCase("liveDTHRech")) || (Service.equalsIgnoreCase("liveMobRech"))&& (TransactionStatus.equalsIgnoreCase("refunded")||TransactionStatus.equalsIgnoreCase("refund"))  )
						{


							String NewService="liveDTHRefund";

							HashMap<String,Object> map=CheckDetailsDao.getPopUpDetails(NewService,TransactionStatus,TransactionNo,userId);
							responseJson.put("mapTransafer",map);
							return  responseJson;
						}



						if(Service.equalsIgnoreCase("RliveDTHRech") || Service.equalsIgnoreCase("RliveMobRech"))
						{
							String NewService="liveDTHRefund";
							HashMap<String,Object> map=CheckDetailsDao.getPopUpDetails(NewService,TransactionStatus,TransactionNo,userId);
							responseJson.put("mapTransafer",map);
							return  responseJson;
						}


						/*  if(Service.equalsIgnoreCase("liveMobRech") )
		    {

		    	String NewService="liveDTHRech";
		    	HashMap<String,Object> map=CheckDetailsDao.getPopUpDetails(NewService,TransactionStatus,TransactionNo,userId);
			     session.put("mapTransafer",map);
		    	 return  "liveMobRech";
			}*/


					}else{
						responseJson.put("message", "Invalid request.");
						responseJson.put("status", "1");

					}
				}else{
					responseJson.put("message", "Invalid request.");
					responseJson.put("status", "1");

				}
			}else{
				responseJson.put("message", "Invalid request.");
				responseJson.put("status", "1");

			}


		}catch(Exception ex){
			ex.printStackTrace();
			String message="Due to some technical issue we are unable to proceed your request.";
			responseJson.put("message", message);
			responseJson.put("status", "1");
			return responseJson;
		}
		return responseJson;

	}
}
