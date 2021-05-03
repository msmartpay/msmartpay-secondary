/*
  Class Property :  This class (AgentDetailAction) is created for controlling and display active ,non active agent details .

  Created Date   : 9-Dec-2011 at 11:55 PM.
  Created By     : Bharatveer Singh.

  Updated Date   : 9-Dec-2011.
  Update By      : Bharatveer Singh

*/
package com.agentDetails;

import java.util.ArrayList;
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

@SuppressWarnings("serial")
public class AgentDetailAction extends ActionSupport implements  ModelDriven<Object>, ServletRequestAware,ServletResponseAware  {
	
	private HttpServletRequest request;
	Logger logger = Logger.getLogger(AgentDetailAction.class);
	LogWriter log=new LogWriter();
	
	private  AgentDetailForm AgentDetailForm=new AgentDetailForm();
	public Object getModel(){
		return AgentDetailForm;
	} 
	 
	@SuppressWarnings("unchecked")
	public String execute() throws Exception { 
		try 
		{ 
			Map session = ActionContext.getContext().getSession();
			AgentDetailDao agentDetailDao=new AgentDetailDao();
			String page=null;
			String param=AgentDetailForm.getParam();
			log.print("param value in DistributorProfileAction is "+param, logger);
			logger.info(logger);

			String distributorInitial=(String)session.get("distributorInitial"); 
			String userId=(String)session.get("distributorId");
			if(userId==null){
				request.setAttribute("Loginmessage","Your session has been expire ,relogin to continue.");
				return "sessionexpired"; 
			}
			String DsId=distributorInitial+userId;
			if(param.equals("AgentDetails")){
				  
				page=(String)request.getParameter("page");			
				request.setAttribute("page", page);
				System.out.println("PAge in :"+page);				
				if(page==null)					
				{									 
					page="0";
				}				 
				String AgentCurrentStatus=(String)request.getParameter("checkStatus");
				if(AgentCurrentStatus==null){
					AgentCurrentStatus =(String)session.get("AgentCurrentStatus");
				}
				session.put("AgentCurrentStatus", AgentCurrentStatus);
				System.out.println("AgentCurrentStatus : "+AgentCurrentStatus);
				session.put("AgentCurrentStatus", AgentCurrentStatus);
				String clientFlag=(String)session.get("panelType");
				page=(String)request.getParameter("page");
				if(page==null)					
				{									 
					page="0";
				}					  
				if(AgentCurrentStatus.equals("Activate"))
				{
					ArrayList newAgents=agentDetailDao.AllActiveAgent(DsId,page,clientFlag);
					if (newAgents.size()<=0){
						return "existActiveAgent";
					}
					request.setAttribute("newAgents",newAgents);
					return "existActiveAgent";
				}
				else if(AgentCurrentStatus.equals("Deactive")){
					ArrayList newAgents=agentDetailDao.AllDeactivateAgent(DsId,page,clientFlag);
					if (newAgents.size()<=0){
						return "existActiveAgent";
					}
					request.setAttribute("newAgents",newAgents);
					return "existActiveAgent";					  
				}else if(AgentCurrentStatus.equals("All")) {					  
					ArrayList newAgents=agentDetailDao.AllAgent(DsId,page,clientFlag);
					if (newAgents.size()<=0){
						return "existActiveAgent";
					}
					request.setAttribute("newAgents",newAgents);
					return "existActiveAgent";
				}
				else{
					request.setAttribute("message","Process aborted due to Technical Failure." );
					return "home";
				}				  
			}
			
			if("changeStatusAgent".equals(param))
			{				
				page=(String)request.getParameter("page");			
				request.setAttribute("page", page);
				System.out.println("PAge in :"+page);				
				if(page==null)					
				{									 
					page="0";
				}			  
				String[] changeStatusAgentAll = request.getParameterValues("checkpartial");
				String AgentRequiredStatus=(String)request.getParameter("checkChangeStatus");  
				String clientFlag=(String)session.get("panelType"); 
				  
/*				System.out.println("changeStatusAgentAll :"+changeStatusAgentAll);
				System.out.println("AgentRequiredStatus :"+AgentRequiredStatus);				
				System.out.println("clientFlag :"+clientFlag);	*/	  
				  
				String changeStatusAgent=agentDetailDao.dochangeStatusAgent(changeStatusAgentAll,AgentRequiredStatus,clientFlag,userId);				
				System.out.println("changeStatusAgent :"+changeStatusAgent);				
				if(changeStatusAgent.equalsIgnoreCase("Update"))
				{
					if(AgentRequiredStatus.equalsIgnoreCase("Deactivate"))
					{
						request.setAttribute("message","Selected User is Deactivated.");
						ArrayList newAgents=agentDetailDao.AllDeactivateAgent(DsId,page,clientFlag);
						if(newAgents.size()<=0){
							request.setAttribute("message","Data is Not Available.");  
							return "existActiveAgent";
						}
						request.setAttribute("newAgents",newAgents);
						return "existActiveAgent"; 
					}
					else if (AgentRequiredStatus.equalsIgnoreCase("Activate")){
						request.setAttribute("message","Selected User is Activated.");
						ArrayList newAgents=agentDetailDao.AllActiveAgent(DsId,page,clientFlag);
						if(newAgents.size()<=0){
							request.setAttribute("message","Data is Not Available.");  
							return "existActiveAgent";
						}
						request.setAttribute("newAgents",newAgents);
						return "existActiveAgent"; 
					}
					else{
						String  msg="Process aborted due to Technical Failure.";						  
						request.setAttribute("message",msg);
						return "existActiveAgent"; 
					}
				}
				else
				{				  
					String  msg="Process aborted due to Technical Failure.";						  
					request.setAttribute("message",msg);
					return "existActiveAgent"; 
				}	 			
			}   
		}	  
		catch(Exception ex){
			ex.printStackTrace();
			request.setAttribute("message", "Due to some technical issue we are unable to proceed your request");
			return "error";
		}
		return "error";    
	}	
	@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
	}
	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		
	}	 
  }
