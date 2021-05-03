
/*
 Class Property :  This class (PushBalanceAction) is created to get request data of transaction details and process it accordingly.

  Created Date   : 3 Dec-2012 at 10.00 AM.
  Created By     : Monika nethani

  Updated Date   : 3 Dec-2012
  Update By      :Monika Nethani



 */

package com.PushBalance;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;

import com.common.LogWriter;
import com.common.SendSmsOnMobile;
import com.login.LoginDao;

import net.sf.json.JSONObject;


public class PushBalanceAction {

	Logger logger = Logger.getLogger(PushBalanceAction.class);
	LogWriter log=new LogWriter();
	private ServletContext context = null;
	public JSONObject pushBalanceAction(JSONObject requestObject,HttpServletRequest request) {

		JSONObject responseJson=new JSONObject();
		try 
		{ 
			String userId=requestObject.getString("distributorId");
			String ip=request.getRemoteAddr();
			String txnkey=requestObject.getString("txnkey");
			if(txnkey!=null){
				
				LoginDao dao=new LoginDao();
				if(dao.varifyTxnKey(Long.parseLong(userId), txnkey)){
					
					String message="",agentmobileNmber="";
					String FinalTransafer=null;
					
					PushBalanceDao PushBalanceDao = new PushBalanceDao();		
					
					
					String ReceiverID=requestObject.getString("agentId");
					String TransferAmount=requestObject.getString("TransaferAmount");
					String PaymentRemark=requestObject.getString("PaymentRemark");
					PaymentRemark=ReceiverID+" "+PaymentRemark;
					
					double DoubleTransferAmount=Double.parseDouble(TransferAmount);
					DecimalFormat dff = new DecimalFormat("##.#### ");				
					//------------------------Method to check weather user exist in system or not-----
					HashMap<String,Object> IdStatus=PushBalanceDao.getIdStatus(ReceiverID,userId);				  
					String agentIdOnly="";
					if(IdStatus.size()==0)
					{
						message="Please Enter Correct Agent ID.";
						responseJson.put("message",message); 
						responseJson.put("status","1");
						return responseJson;
					}
					else
					{
						agentIdOnly=(String) IdStatus.get("ReceiverOnlyId");
						String agentmobileNmberIs=(String)IdStatus.get("ReceiverMobileNo");
						agentmobileNmber=agentmobileNmberIs.trim();	
					}
					//------------------Method to compare the available balance with transfer amount of distributor-----

					String BalanceStatus = PushBalanceDao.getBalanceStatus(DoubleTransferAmount,userId);
					if("FundInSufficent".equalsIgnoreCase(BalanceStatus))
					{
						message="Insufficient Balance. Please Recharge Your Account.";
						responseJson.put("message",message); 
						responseJson.put("status","1");
						return responseJson;
					}				  
					//---------------------------Logic to generate Transaction ID----------------------------------
					String transactionId=agentIdOnly+RandomStringUtils.randomAlphanumeric(12).toUpperCase();
					//----------------Final Method  to transfer amount to Agent\DistributorID-------------------------
					String agentUpdatedAmount2 = "";
					context=request.getServletContext();
					String Tnxstatus=(String)context.getAttribute(userId);
					if(Tnxstatus==null||!Tnxstatus.equalsIgnoreCase(userId))
					{
						context.setAttribute(userId, userId);					
						FinalTransafer=PushBalanceDao.doFinalTransfer(agentIdOnly,userId,DoubleTransferAmount,PaymentRemark,ip,transactionId);					
						//---------------Method to get Update balance of agent-----------------------------------					
						double agentUpdatedAmount=PushBalanceDao.getAgentUpdatedAmount(agentIdOnly);
						agentUpdatedAmount2 = dff.format(agentUpdatedAmount);
						if(FinalTransafer.equalsIgnoreCase("valid"))
						{
							String testmessage="Rs. "+TransferAmount+" has been credited to your ID: "+ReceiverID+" (Bal: Rs. "+agentUpdatedAmount+").";
							SendSmsOnMobile.sendMobileSmsSMSZONE(agentmobileNmber,testmessage);
							message="Rs. "+TransferAmount+" Has Been Successfully Transferred to "+ReceiverID+" & Updated Balance For This User is Rs."+agentUpdatedAmount+"";

							responseJson.put("message",message);
							responseJson.put("status","0");
							context.removeAttribute(userId);
							return responseJson;
						}
						else{
							if(FinalTransafer.equalsIgnoreCase("not valid")){
								message="Duplicate Push Transfer is not Allowed within 5 Minutes. Please Try Again.";
								responseJson.put("message",message);
								responseJson.put("status","1");
								context.removeAttribute(userId);
								// return "pushBalancefromAction";
								return responseJson;	
							}
							else if(FinalTransafer.equalsIgnoreCase("insufficient balance")){
								message="Insufficient Balance.";
								responseJson.put("message",message);
								responseJson.put("status","1");
								context.removeAttribute(userId);
								// return "pushBalancefromAction";
								return responseJson;	
							}
							else{
								message="Process aborted due to Technical Failure.";
								context.setAttribute("message",message);
								responseJson.put("status","1");
								context.removeAttribute(userId);
								// return "pushBalancefromAction";
								return responseJson;
							}
						}
					}					  
					else{

						message="Transaction is Under Process.";
						responseJson.put("message",message);
						responseJson.put("status","1");
						context.removeAttribute(userId);
						// return "pushBalancefromAction";
						return responseJson;
					}
					
				}else{
					responseJson.put("message", "Invalid request.");
					responseJson.put("status", "1");
				}
			}else{
				responseJson.put("message", "Invalid request.");
				responseJson.put("status", "1");
			}
		
		}	  
		catch(Exception ex){
			ex.printStackTrace();
			responseJson.put("userId", ex);
			responseJson.put("message", "Process aborted due to Technical Failure.");
			responseJson.put("status", "1");
			return responseJson;
		}
		return responseJson;     
	}	
	
}
