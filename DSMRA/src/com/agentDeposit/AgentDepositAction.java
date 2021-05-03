

/*
 Class Property :  This class (AgentDepositAction) is created to get request data of agent deposit and process it accordingly.

  Created Date   : 23-Dec-2011 at 19:55 PM.
  Created By     : Amit Pathak

  Updated Date   : 13-march-2012
  Update By      :Amit Pathak

 */

package com.agentDeposit;

import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import com.dsamount.DistributorJournalForm;
import net.sf.json.JSONObject;

public class AgentDepositAction
{
	Logger logger = Logger.getLogger(AgentDepositAction.class);
	
	private ServletContext context = null;
	private HttpServletRequest request;
	
	private  DistributorJournalForm DistributorJournal=new DistributorJournalForm();
	public Object getModel(){return DistributorJournal;}

	public String executeMethod(JSONObject jsonObject) { 
		try 
		{ 
			String param=DistributorJournal.getParam();
			AgentDepositDao AgentDepositDao=new AgentDepositDao();

			String userId=jsonObject.getString("distributorId");
			if(param.equals("agentDepositRequest"))
			{		  
				ArrayList<HashMap<String,Object>> DepositDetails=AgentDepositDao.getDepsitDetails(userId);
				if(DepositDetails.size()!=0)
				{
					request.setAttribute("DepositDetails",DepositDetails);
					return "DepositRequest";
				}
				else
				{
					// String  message="Data is Not Available.";
					request.setAttribute("message", "");
					return "DepositRequest";			  
				}		  
			}			  
			//--------------------------------Block to update agent deposit------------------------------------
			if("UpdateDepositRequest".equalsIgnoreCase(param))
			{
				String Tnxstatus=(String)context.getAttribute(userId);
				if(Tnxstatus==null||!Tnxstatus.equalsIgnoreCase(userId))
				{
					context.setAttribute(userId, userId); 

					String TransactionNo=DistributorJournal.getTransactionJournalId();
					double BankCharges=DistributorJournal.getBankCharges();
					String Status=DistributorJournal.getStatus();
					String agentid=DistributorJournal.getAgentid();
					String ipAddress=DistributorJournal.getIpAddress();
					double Amount=DistributorJournal.getAcceptedAmount();
					double amtOfCharege=0.0;			

					//-------------------method to get Distributor balance------------------		  

					String DisBalance=AgentDepositDao.getDsBalance(userId);
					//----------------Method to update 	Agent Deposit request in case of selected status is ACCEPTED ---------- 

					if(Status.equalsIgnoreCase("Accepted"))
					{				
						String getStatus=AgentDepositDao.getTranStatus(TransactionNo);					  
						if(getStatus.equalsIgnoreCase("Pending"))
						{					
							Double halfOftransferAmt=Amount/2;				 
							amtOfCharege=Amount-BankCharges;						  
							double Disbal=(Double.parseDouble(DisBalance));
							double DistributorFinalBalance=(Double.parseDouble(DisBalance)-amtOfCharege);
							if(BankCharges>halfOftransferAmt)
							{
								String messageDist="Bank Charges cannot be Greater than 50% of Total Amount.";
								request.setAttribute("message",messageDist);
								ArrayList<HashMap<String,Object>> DepositDetails=AgentDepositDao.getDepsitDetails(userId);
								request.setAttribute("DepositDetails",DepositDetails);
								context.removeAttribute(userId);
								return "DepositRequest";
							}

							if(BankCharges<0)
							{
								String messageDist="Bank Charges cannot be Less than Zero.";
								request.setAttribute("message",messageDist);
								ArrayList<HashMap<String,Object>> DepositDetails=AgentDepositDao.getDepsitDetails(userId);
								request.setAttribute("DepositDetails",DepositDetails);
								context.removeAttribute(userId);
								return "DepositRequest";
							}
							if(Amount >Disbal)
							{   						 
								String messageDistt="Insufficient Balance. Please Recharge Your Account.";

								request.setAttribute("message",messageDistt);
								ArrayList<HashMap<String,Object>> DepositDetails=AgentDepositDao.getDepsitDetails(userId);
								request.setAttribute("DepositDetails",DepositDetails);
								context.removeAttribute(userId);
								return "DepositRequest";						    
							}	

							String distributorTableUpdate= AgentDepositDao.updateDistributorAccount(userId,agentid,Status,amtOfCharege,TransactionNo,BankCharges,ipAddress,Amount,DistributorFinalBalance,Disbal);

							if(distributorTableUpdate.equalsIgnoreCase("NotUpdate"))
							{
								String messageDistt="Process aborted due to Technical Failure.";
								request.setAttribute("message",messageDistt);
								ArrayList<HashMap<String,Object>> DepositDetails=AgentDepositDao.getDepsitDetails(userId);
								request.setAttribute("DepositDetails",DepositDetails);
								context.removeAttribute(userId);
								return "DepositRequest";	
							}				 
							/*	 String agentTableUpdate=AgentDepositDao.updateAgentAccount(userId,agentid,Status,amtOfCharege,BankCharges,Amount,TransactionNo,ipAddress);
							 if(agentTableUpdate.equalsIgnoreCase("NotUpdate"))
							 {
								 String messageDistt="<html><body><table><tr><td><font color=\"red\" size=\"2\">Transaction not updated,Please try again </td></tr></table></body></html>";
								 request.setAttribute("message",messageDistt);
								 ArrayList<HashMap<String,Object>> DepositDetails=AgentDepositDao.getDepsitDetails(userId);
								 request.setAttribute("DepositDetails",DepositDetails);
								 return "DepositRequest";	
							 } */				     
							String messageDistt="Balance Transferred Successfully.";
							request.setAttribute("message",messageDistt);
							ArrayList<HashMap<String,Object>> DepositDetails=AgentDepositDao.getDepsitDetails(userId);
							request.setAttribute("DepositDetails",DepositDetails);
							context.removeAttribute(userId);
							return "DepositRequest"; 
						}				 
						else
						{
							String messageDist="Command Disallowed.";
							request.setAttribute("message",messageDist);
							ArrayList<HashMap<String,Object>> DepositDetails=AgentDepositDao.getDepsitDetails(userId);
							request.setAttribute("DepositDetails",DepositDetails);
							context.removeAttribute(userId);
							return "DepositRequest";
						}
					}			  
					//----------------Method to update 	Agent Deposit request in case of selected status is DECLINED ---------- 
					if(Status.equalsIgnoreCase("Declined"))
					{				  
						String getStatus=AgentDepositDao.getTranStatus(TransactionNo);

						if(getStatus.equalsIgnoreCase("Pending"))
						{
							String agentTableUpdate=AgentDepositDao.updateAgentAccount(userId,agentid,Status,amtOfCharege,BankCharges,Amount,TransactionNo,ipAddress);
							if(agentTableUpdate.equalsIgnoreCase("NotUpdate"))
							{
								String messageDistt="Process Aborted due to Technical Failure.";
								request.setAttribute("message",messageDistt);
								ArrayList<HashMap<String,Object>> DepositDetails=AgentDepositDao.getDepsitDetails(userId);
								request.setAttribute("DepositDetails",DepositDetails);
								context.removeAttribute(userId);
								return "DepositRequest";	
							}
							String  message="Process has been Completed Successfully.";

							request.setAttribute("message",message);
							ArrayList<HashMap<String,Object>> DepositDetails=AgentDepositDao.getDepsitDetails(userId);
							request.setAttribute("DepositDetails",DepositDetails);
							context.removeAttribute(userId);
							return "DepositRequest"; 
						}
						else
						{
							String messageDist="Command Disallowed.";
							request.setAttribute("message",messageDist);
							ArrayList<HashMap<String,Object>> DepositDetails=AgentDepositDao.getDepsitDetails(userId);
							request.setAttribute("DepositDetails",DepositDetails);
							context.removeAttribute(userId);
							return "DepositRequest";		 
						}
					}
					//----------------Method to update 	Agent Deposit request in case of selected status is PENDING ---------- 
					if(Status.equalsIgnoreCase("Pending"))
					{
						String  messageDist="Please Accept or Decline the Deposit Request.";

						request.setAttribute("message",messageDist);
						ArrayList<HashMap<String,Object>> DepositDetails=AgentDepositDao.getDepsitDetails(userId);
						request.setAttribute("DepositDetails",DepositDetails);
						context.removeAttribute(userId);
						return "DepositRequest";
					}	
				}
				else{
					String message = "Transaction is Under Process.";
					request.setAttribute("message",message);
					return "DepositRequest";
				}	  
			}	  
			//--------------------------------------FOR POP UP-----------------------------------		  
			if(param.equalsIgnoreCase("transactionPopUp"))
			{
				String JournalId=DistributorJournal.getTransactionJournalId();
				HashMap<String,Object> depositRequest=AgentDepositDao.getPopData(JournalId);
				if(depositRequest.size()!=0)
				{
					request.setAttribute("depositRequest", depositRequest);	
					return "AgentDepositPopUp";
				}
				else
				{	
					String  message="Data is Not Available.";					
					request.setAttribute("message",message);
					return "AgentDepositPopUp";		
				}		  
			}	  
		}	  
		catch(Exception ex){
			ex.printStackTrace();
			request.setAttribute("message", "Process aborted due to Technical Failure.");
			return "error";
		}
		return "error";

	}





}
