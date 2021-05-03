/*
 Class Property :  This class (CheckDetailsAction) is created to get request data of transaction details and process it accordingly.

  Created Date   : 8 january-2012 at 10.00 AM.
  Created By     : Amit Pathak

  Updated Date   : 8 january-2012
  Update By      :Amit Pathak

*/




package com.TransactionDetails;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.common.LogWriter;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;



public class CheckDetailsAction extends ActionSupport implements ModelDriven<Object>,ServletRequestAware,ServletResponseAware 
{
	 
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(CheckDetailsAction.class);
	LogWriter log=new LogWriter();
	private HttpServletRequest request; 
	private  CheckDetailsForm CheckDetailsForm=new CheckDetailsForm();
	public Object getModel(){return CheckDetailsForm;}
	 
	 @SuppressWarnings("unchecked")
	  public String execute() throws Exception { 
	  try 
	  { 
		  Map session = ActionContext.getContext().getSession();
		  String param=CheckDetailsForm.getParam();
		  log.print("param value in CheckDetailsAction is "+param, logger);
		  String userId=(String)session.get("distributorId");
		  if(userId==null){
			  String  message2="<html><body><table><tr><td><font color=\"red\" size=\"2\">Your session has been expire ,relogin to continue</td></tr></table></body></html>";

			  request.setAttribute("Loginmessage",message2);
	    		          return "sessionexpired"; 
		  }
		  

		  if(param.equals("transactionPopUp"))
		  
		  {
			  String Service=CheckDetailsForm.getService();
		    
			  String TransactionNo=CheckDetailsForm.getTransactionNo();
			  String TransactionStatus=CheckDetailsForm.getTransactionStatus();
			  String AgentCompleteId=CheckDetailsForm.getAgentCompleteId();		
 
			 if(Service.equalsIgnoreCase("transfertoagent"))
			 {
			       
				   HashMap<String,Object> map=CheckDetailsDao.getPopUpDetails(Service,TransactionStatus,TransactionNo,userId);
			       session.put("mapTransafer",map);
		           return "transfertoagent";
		     }
		
			 if(Service.equalsIgnoreCase("AgentCreationCharge"))
			 {
			       
				   HashMap<String,Object> map=CheckDetailsDao.getPopUpDetails(Service,TransactionStatus,TransactionNo,userId);	
				   System.out.println("map :"+map);
				   session.put("mapTransafer",map);	     
				   return "AgentCreationCharge";
			 }
			 
		     if((Service.equalsIgnoreCase("transfertodist")) || (Service.equalsIgnoreCase("mdtodist")) )
		 
		     {	
		    	    HashMap<String,Object> map=CheckDetailsDao.getPopUpDetails(Service,TransactionStatus,TransactionNo,userId);
			        session.put("mapTransafer",map);
			        return "transfertodistributor";
		     }  
		
		    if(Service.equalsIgnoreCase("accountadjustment"))
		     {
		    	     HashMap<String,Object> map=CheckDetailsDao.getPopUpDetails(Service,TransactionStatus,TransactionNo,userId);
			         session.put("mapTransafer",map);
			         System.out.println("map :"+map);
		             return "accountadjustment";
		    }
		    
		    if(Service.equalsIgnoreCase("Account Adjustment-DS"))
		     {
		    	     HashMap<String,Object> map=CheckDetailsDao.getPopUpDetails(Service,TransactionStatus,TransactionNo,userId);
			         session.put("mapTransafer",map);
			         System.out.println("map :"+map);
		             return "accountadjustmentds";
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
			     session.put("mapTransafer",map);
		    	 return  "AdmintoAgent";
			}
			//---------------- 
		    if(Service.equalsIgnoreCase("Cash Back"))
		    {
				 HashMap<String,Object> map=CheckDetailsDao.getPopUpDetails(Service,TransactionStatus,TransactionNo,userId);
			     session.put("mapTransafer",map);
		    	 return  "cashback";
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
			     session.put("mapTransafer",map);
		    	 return  "liveDTHRechother";
			}
		    
		    
		    if((Service.equalsIgnoreCase("liveDTHRech")) || (Service.equalsIgnoreCase("liveMobRech"))&& (TransactionStatus.equalsIgnoreCase("refunded")||TransactionStatus.equalsIgnoreCase("refund"))  )
		    {
				
		    	
		    	 String NewService="liveDTHRefund";
		    	
				 HashMap<String,Object> map=CheckDetailsDao.getPopUpDetails(NewService,TransactionStatus,TransactionNo,userId);
			     session.put("mapTransafer",map);
		    	 return  "liveDTHRechrefund";
			}
		    
		    
		    
		    if(Service.equalsIgnoreCase("RliveDTHRech") || Service.equalsIgnoreCase("RliveMobRech"))
		    {
		    	String NewService="liveDTHRefund";
				 HashMap<String,Object> map=CheckDetailsDao.getPopUpDetails(NewService,TransactionStatus,TransactionNo,userId);
			     session.put("mapTransafer",map);
		    	 return  "liveDTHRechrefund";
			}
		    
		    
		  /*  if(Service.equalsIgnoreCase("liveMobRech") )
		    {
				
		    	String NewService="liveDTHRech";
		    	HashMap<String,Object> map=CheckDetailsDao.getPopUpDetails(NewService,TransactionStatus,TransactionNo,userId);
			     session.put("mapTransafer",map);
		    	 return  "liveMobRech";
			}*/
		    
		    
		      }
				  
		   
		  
	   }
	  
	      catch(Exception ex){
		   ex.printStackTrace();
		   request.setAttribute("message", "Due to some technical issue we are unable to proceed your request");
		   return "error";
	         }
	       return "error";
     
	}
	
	public void setServletRequest(HttpServletRequest httpServletRequest) {
       this.request = httpServletRequest;
   }

	public void setServletResponse(HttpServletResponse arg0) {
		
	}

	
	
	
	 
}
