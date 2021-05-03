package com.agent;

import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AgentDetailsAction extends Action {
	
	@SuppressWarnings("unused")
	public ActionForward execute(ActionMapping mapping,
			 ActionForm form,
			 HttpServletRequest request,
			 HttpServletResponse response)
throws Exception {
		
		HttpSession session=request.getSession(true);
		String mdId=(String)session.getAttribute("mdId");
		System.out.println("mdId is==============================="+mdId);
		if(mdId==null){
			session.setAttribute("message","Your Login session has Expired. Please Login to Continue.");
			return mapping.findForward("sessionexp");
		}
		try
		{
			AgentDetailsDao daoObj=new AgentDetailsDao();
			String clientId=(String)session.getAttribute("clientId");
			String ClientFlag=daoObj.getClientTyp(clientId);
			System.out.println("Client Flag :"+ClientFlag);
			String param=(String)request.getParameter("param");
			System.out.println(" param in AgentDeails === : "+param);
			ArrayList<?> AgentList=null;
			String page=null;
			if("viewAgent".equals(param))
			{
				System.out.println("we are here ::: "+param);        	   
				ArrayList distributorIdList=AgentDetailsBusinessDelegator.getAllDistributorId(mdId);			
				//System.out.println("DS ID :"+distributorIdList);
				session.setAttribute("distributorIdList",distributorIdList);			
				return mapping.findForward("distributorlist");	 
			}
			
			if(param.equalsIgnoreCase("active"))
			{				
				page=(String)request.getParameter("page");			
				request.setAttribute("page", page);
				session.setAttribute("ControlButton", "active");
				System.out.println("PAge in :"+page);
			
				if(page==null)					
				{									 
					page="0";
				}				
				String distributorId=(String)request.getParameter("did");
				if(distributorId==null){
					distributorId = (String)session.getAttribute("distributorId");
				}
				session.setAttribute("distributorId", distributorId);
				System.out.println("distributorId  :"+distributorId);
			
				AgentList=AgentDetailsBusinessDelegator.getActiveAgents(distributorId,page,ClientFlag);
				if(AgentList.size()<=0){
					//request.setAttribute("message", "Data is Not Available.");
					ArrayList distributorIdList=AgentDetailsBusinessDelegator.getAllDistributorId(mdId);
					session.setAttribute("distributorIdList",distributorIdList);
					return mapping.findForward("agentDetailsList");
				}
				session.setAttribute("AgentList", AgentList);
				return mapping.findForward("agentDetailsList");
			}     
			else if(param.equalsIgnoreCase("deactive"))
			{				
				page=(String)request.getParameter("page");			
				request.setAttribute("page", page);
				session.setAttribute("ControlButton", "deactive");
				System.out.println("PAge in :"+page);			
				if(page==null)					
				{									 
					page="0";
				}				
				String distributorId=(String)request.getParameter("did");
				if(distributorId==null){
					distributorId = (String)session.getAttribute("distributorId");
				}
				session.setAttribute("distributorId", distributorId);
			//	System.out.println("distributorId  :"+distributorId);			
				AgentList=AgentDetailsBusinessDelegator.getDeactiveAgents(distributorId,page,ClientFlag);
				session.setAttribute("AgentList", AgentList);
				return mapping.findForward("agentDetailsList");
			}       	
			else if(param.equalsIgnoreCase("viewAgentAll"))
			{				
				page=(String)request.getParameter("page");			
				request.setAttribute("page", page);
				session.setAttribute("ControlButton", "viewAgentAll");
			//	System.out.println("PAge in :"+page);			
				if(page==null)					
				{									 
					page="0";
				}				
				String distributorId=(String)request.getParameter("did");
				if(distributorId==null){
					distributorId = (String)session.getAttribute("distributorId");
				}
				session.setAttribute("distributorId", distributorId);
			//	System.out.println("distributorId  :"+distributorId);
			
				AgentList=AgentDetailsBusinessDelegator.getAllAgents(distributorId,page,ClientFlag);
				
			//	System.out.println(AgentList.size()+" *size in class ");
				session.setAttribute("AgentList", AgentList);
				return mapping.findForward("agentDetailsList");
			}  

			if("activateAgent".equals(param))
			{			
				String distributorId=null;
				ArrayList distributorIdList=AgentDetailsBusinessDelegator.getAllDistributorId(mdId);	
				String agentIds=(String)request.getParameter("distributorids");
				System.out.println("gbvhgvghgvhvgh ::"+agentIds);
				ArrayList ActiveAgentData= new ArrayList();
				ArrayList NotActiveAgentData= new ArrayList();
				
				StringTokenizer str=new StringTokenizer(agentIds,"_");
				String AgentId="";
	           
				while(str.hasMoreTokens())
				{
					AgentId=str.nextToken();					
			//		System.out.println("agentId is===================="+AgentId);
					String ActiveStatus=(String)AgentDetailsBusinessDelegator.activateAgentStatus(AgentId,mdId);				
					if (ActiveStatus.equals("Activate"))
					{			     	
						ActiveAgentData.add(AgentId);
						request.setAttribute("message", "Selected User is Activated.");
					}
					if(ActiveStatus.equals("Notactive"))
					{						
						NotActiveAgentData.add(AgentId);
						request.setAttribute("message", "Process aborted due to Technical Failure.");
					}
				}
				page=(String)request.getParameter("page");			
				request.setAttribute("page", page);
				session.setAttribute("ControlButton", "viewAgentAll");
				System.out.println("PAge in :"+page);			
				if(page==null)					
				{									 
					page="0";
				}	
				
				if(distributorId==null){
					distributorId = (String)session.getAttribute("distributorId");
				}
				session.setAttribute("distributorId", distributorId);
				AgentList=AgentDetailsBusinessDelegator.getAllAgents(distributorId,page,ClientFlag);
				session.setAttribute("AgentList", AgentList);
				session.setAttribute("ControlButton","viewAgentAll");
				
				/*request.setAttribute("ActiveAgentData",ActiveAgentData);
	            request.setAttribute("NotActiveAgentData",NotActiveAgentData);
	
				String UpdatedStatus="Activated";
				request.setAttribute("UpdatedStatus",UpdatedStatus);*/
	            request.setAttribute("distributorIdList",distributorIdList);
				return mapping.findForward("agentDetailsList");
			}
			if("deactivateAgent".equals(param))
			{
				String distributorId=null;
				ArrayList ActiveAgentData= new ArrayList();
				ArrayList NotActiveAgentData= new ArrayList();		
				String AgentIdT="";		 
				String agentId=(String)request.getParameter("distributorids");
				StringTokenizer str=new StringTokenizer(agentId,"_");		 
				while(str.hasMoreTokens())
				{
					AgentIdT=str.nextToken();					
					System.out.println("agentId is===================="+AgentIdT);
					String ActiveStatus=(String)AgentDetailsBusinessDelegator.deactivateAgentStatus(AgentIdT,mdId);
					System.out.println(" Statsus s is d  "+ActiveStatus);
					
					if (ActiveStatus.equals("update"))
					{				     	
						ActiveAgentData.add(AgentIdT);
						request.setAttribute("message", "Selected User is Deactivated.");
					}
					if(ActiveStatus.equals("notupdate"))
					{						
						NotActiveAgentData.add(AgentIdT);
						request.setAttribute("message", "Process aborted due to Technical Failure.");
					}
				}
			//	request.setAttribute("ActiveAgentData",ActiveAgentData);
			//	request.setAttribute("NotActiveAgentData",NotActiveAgentData);  
				page=(String)request.getParameter("page");			
				request.setAttribute("page", page);
				session.setAttribute("ControlButton", "viewAgentAll");
				System.out.println("PAge in :"+page);			
				if(page==null)					
				{									 
					page="0";
				}	
				
				if(distributorId==null){
					distributorId = (String)session.getAttribute("distributorId");
				}
				session.setAttribute("distributorId", distributorId);
				AgentList=AgentDetailsBusinessDelegator.getAllAgents(distributorId,page,ClientFlag);
				session.setAttribute("AgentList", AgentList);
				session.setAttribute("ControlButton","viewAgentAll");
			//	String UpdatedStatus="Deactive";
				//request.setAttribute("UpdatedStatus",UpdatedStatus);      
				return mapping.findForward("agentDetailsList");
			}			
			if("DoAllActivateAgent".equalsIgnoreCase(param))
			{ 
				String   distributorId = (String) session.getAttribute("distributorId");				       
				//	System.out.println("DS ID :"+distributorId);
				 String StatusUpdate=AgentDetailsBusinessDelegator.DoAllActivateAgent(mdId,distributorId);			   			
				 if(StatusUpdate.equalsIgnoreCase("update")){
					 request.setAttribute("message", "Selected User is Activated.");
				 }else{
					 request.setAttribute("message", "Process aborted due to Technical Failure.");
				 }
				 ArrayList distributorIdList=AgentDetailsBusinessDelegator.getAllDistributorId(mdId);
				 session.setAttribute("distributorIdList",distributorIdList);			
				 return mapping.findForward("distributorlist");			 
			 }
			 if("DoAllDeactivateAgent".equalsIgnoreCase(param))
			 { 
			//	 System.out.println("we are here ::: "+param); 
				 String   distributorId = (String) session.getAttribute("distributorId");
			//	 System.out.println("DS ID :"+distributorId);
					       	   
				 String StatusUpdate=AgentDetailsBusinessDelegator.DoAllDeactivateAgent(mdId,distributorId);	
				 if(StatusUpdate.equalsIgnoreCase("update")){
					 request.setAttribute("message", "Selected User is Deactivated.");
				 }else{
					 request.setAttribute("message", "Process aborted due to Technical Failure.");
				 }
				 //System.out.println("DS ID :"+distributorIdList);
				 ArrayList distributorIdList=AgentDetailsBusinessDelegator.getAllDistributorId(mdId);
				 session.setAttribute("distributorIdList",distributorIdList);			
				 return mapping.findForward("distributorlist");			 
			 }
			 else{
				 request.setAttribute("message", "Process aborted due to Technical Failure.");
				 return mapping.findForward("distributorlist");
			 }
		}catch(Exception e){
			System.out.println("Exception in AgentDetailsAction"+e.toString());
			e.printStackTrace();	
		}	
		return null;
	}
}