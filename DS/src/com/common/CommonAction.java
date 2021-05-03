/*
  Class Property :  This class (CommonAction) is created for redirection all pages according to action .

  Created Date   : 7-Dec-2011 at 4:12 PM.
  Created By     : Bharatveer Singh.

  Updated Date   : 9-Dec-2011.
  Update By      : Bharatveer Singh
  
  Updated Date	: 7-Sep-2012
  Updated By	: Monika Nethani
  Updated for "Agent registration deduction charges"

*/
package com.common;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.hibernate.Query;
import org.hibernate.Session;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


@SuppressWarnings("serial")
public class CommonAction extends ActionSupport implements ServletRequestAware,ServletResponseAware  {
		public CommonAction() {}
		 
		 private HttpServletRequest request;
		 private HttpServletResponse response;			
		 Logger logger = Logger.getLogger(CommonAction.class);
		 LogWriter log=new LogWriter();
		
		
		 @SuppressWarnings("unchecked")
		  public String execute() throws Exception { 
		  try 
		  { 
			  PrintWriter out=response.getWriter();
			  Map session = ActionContext.getContext().getSession();
			  String param=(String)request.getParameter("param");
			  //log.print("Param value in CommonAction class is "+param, logger);
			  String userId=(String)session.get("distributorId");  
			  
			System.out.println("inside CommonAction param is :"+param);
			  
			  if(param.equalsIgnoreCase("checksession")){				  
				  if(userId==null){
					  out.print("invalid");
				  }
				  else {
					  out.print("valid");
				  }				
				  return null;					  
			  }			  
			  if(userId==null){				  
				  String  message2="Your Login session has Expired. Please Login to Continue.";				  
				  request.setAttribute("Loginmessage",message2);
				  return "sessionexpired"; 
			  }			  
			  if(param.equalsIgnoreCase("home")){
				  return "home";
			  }
			  if(param.equalsIgnoreCase("regAgent")){
				  System.out.println("inside Agent registration block :"+userId);
				
				  double totalCash = 0;
				  double usedCash = 0;
				  double cutoffAamount = 0;
				  double agent_creation_charge = 0;	
				  double amount =0;
				  Session session1=HibernateSession.getSessionFactory().openSession();
				  DecimalFormat dff = new DecimalFormat("##.0000");
				  Connection con=session1.connection();
				  try{					  
					  String qry1 = "select dsa.totalCash,dsa.usedCash,dsa.cutoffAamount from DistributorAmountForm dsa where dsa.distributorId="+userId+"";
					  Query query1 = session1.createQuery(qry1);
				

					  Iterator it = query1.iterate();
					  if (it.hasNext()) {
						  Object[] row = (Object[]) it.next();
						  totalCash = (Double) row[0];
						  usedCash = (Double) row[1];
						  cutoffAamount = (Double) row[2];
					  }					
					  amount = totalCash - usedCash;					  
					  System.out.println("amount :"+amount);
					  
					  String sql = "select ADF.agentCreationCharge from DistributorDetailForm ADF where ADF.distributorId="+userId+"";
					  Query query =session1.createQuery(sql);
					  agent_creation_charge=(Double)query.uniqueResult();
					  session.put("agent_creation_charge", agent_creation_charge);
					 	  
					  System.out.println("agent_creation_charge :"+agent_creation_charge);
					  
				// commented By Kalpna Mam For Some Time.	  
					/*  if (agent_creation_charge > amount){	
						  System.out.println("amount :"+amount);	
						  String  message="Insufficient Balance for Agent Creation. Please Update your Balance.";
						  request.setAttribute("message",message );
						  return "home";
					  }*/	
					  
				  }catch (Exception e) 
				  {
					  e.printStackTrace();
				  }
				  finally {						
					  try {
						  session1.flush();
						  session1.close();
					  } 
					  catch (Exception e) {
							
						  e.printStackTrace();
					  }
				  }
				  String  message="";
				  request.setAttribute("message",message);
				  return "regAgent";
			  }
			  if(param.equalsIgnoreCase("report")){
				  
				  return "report";
			  }
			  if(param.equalsIgnoreCase("logout")){
				  ((SessionMap)ActionContext.getContext().getSession()).invalidate();
				  
				  return "logout";
			  }
			  if(param.equalsIgnoreCase("pushBalance"))
			  {
				  return "pushBalance";
			  }
			  
			  if(param.equalsIgnoreCase("report"))
			  {
				  return "report";
			  }
			  if(param.equalsIgnoreCase("ChangePassword"))
			  {
				  return "ChangePassword";
			  }
			  
			  /********************************
			   * 	VIEW BUSINESS
			   * *******************************************************/
			  if(param.equalsIgnoreCase("viewBusiness"))
			  {
				  
				  ArrayList<HashMap<String,String>>  businessDetails=CommonDetailDao.getBusinessDetails(userId);
				  if(businessDetails!=null)
					  {
					  	request.setAttribute("businessDetails", businessDetails);
					  }
				  else
					  request.setAttribute("message", "No Business Details Avilable .");
				  return "ViewBusiness";
			  }
			
			 
			 
		  }
		  
		  catch(Exception ex){
			  ex.printStackTrace();
			  request.setAttribute("message", "Process aborted due to Technical Failure.");
			  return "error";
		  }
		  return "error";
	      
		 }
		
		 @Override
		 public void setServletRequest(HttpServletRequest httpServletRequest) {
			 this.request = httpServletRequest;
		 }

		 @Override
		 public void setServletResponse(HttpServletResponse httpServletResponse) {
			 this.response =httpServletResponse;
			 
		 }
		
		
		 
}
	 