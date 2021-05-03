package com.activity.apiTask;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class APITaskAction extends ActionSupport
{
	private HttpServletRequest request=null;
	private HttpSession session=null;
	
	public String execute() 
	{
		String key="";
		
		
		request=ServletActionContext.getRequest();
		session=request.getSession();
		
		String userId=(String)session.getAttribute("userId");
		if(userId==null) 
		{
			request.setAttribute("message","Your Login Session has Expired. Please Login Again.");
			return "sessionExpire";
		}
		String param=(String)request.getParameter("param");
		System.out.println("In APITask Action ------------------------------"+param);
		
		if(param.equalsIgnoreCase("getAddClientPage"))
		{
			key= "addClientPage";
		}
		else if(param.equalsIgnoreCase("getAssignAPIPage"))
		{
			APITaskDao dao=new APITaskDao();
			ArrayList clientList=dao.getDistinctClientName();
			if(clientList.size()<=0)
				request.setAttribute("message", "Client detail not available. Please add client first.");
			
				
			request.setAttribute("clientList", clientList);
			key= "assignAPIPage";
		}
		else if(param.equalsIgnoreCase("addClient"))
		{
			APITaskDao dao=new APITaskDao();
			String dbName=request.getParameter("dbName");
			String clientName=request.getParameter("clientName");
			
			String clientStatus=dao.getClientStatus(clientName);
			
			if(clientStatus.equalsIgnoreCase("N"))
				request.setAttribute("message", "Client Name already exists.");
			else
			{
				String serverIP=request.getParameter("serverIP");
				System.out.println("DBname : "+dbName+"  clientName : "+clientName+" ServerIP : "+serverIP);
				
				
				String status=dao.addClient(clientName, dbName, serverIP);
				if(status.equalsIgnoreCase("success"))
					request.setAttribute("message", "Process has been completed Successfully.");
				else if(status.equalsIgnoreCase("err"))
					request.setAttribute("message", "Process aborted due to Technical Failure.");
				else
					request.setAttribute("message", "Process not completed Successfully.");
			}
			
			key= "addClientPage";
		}
		else if(param.equalsIgnoreCase("getAPIDetails"))
		{
			String clientName=request.getParameter("clientName");
			String apiID=request.getParameter("apiId");
			
			APITaskDao dao=new APITaskDao();
			HashMap apiDetailMap=dao.getClientDBName(clientName);
			System.out.println("apiDetailMap : "+apiDetailMap);
			if(!apiDetailMap.containsKey("err"))
			{
				apiDetailMap=dao.getAPIDetails(apiID,apiDetailMap);
				System.out.println("apiDetailMap : "+apiDetailMap);
				if(apiDetailMap.containsKey("err"))
				{
					request.setAttribute("flag", "N");	
				}
				else if(apiDetailMap.isEmpty())
				{
					request.setAttribute("flag", "N");
					request.setAttribute("message", "API Details Not Available.");
				}
				else
				{
					request.setAttribute("flag", "Y");
					request.setAttribute("apiDetailMap", apiDetailMap);
				}
				ArrayList clientList=dao.getDistinctClientName();
				request.setAttribute("clientList", clientList);
			}
			return "assignAPIPage";
		}
		/*else if(param.equalsIgnoreCase("assignAPI"))
		{
			String dbName=request.getParameter("dbName");
			String serverIP=request.getParameter("serverIP");
			String agentId=request.getParameter("agentId");
			String agentHeaderId=request.getParameter("agentHeaderId");
			String agentHeaderPassword=request.getParameter("agentHeaderPassword");
			String subAgentId=request.getParameter("subAgentId");
			String subAgentPassword=request.getParameter("subAgentPassword");
			String subAgentMobile=request.getParameter("subAgentMobile");
			
			APITaskDao dao=new APITaskDao();
			String status=dao.assignAPI(dbName, agentId, serverIP,agentHeaderId,agentHeaderPassword,subAgentId,subAgentPassword,subAgentMobile);
			if(status.equalsIgnoreCase("success"))
				request.setAttribute("message", "Process has been completed Successfully.");
			else if(status.equalsIgnoreCase("err"))
				request.setAttribute("message", "Process aborted due to Technical Failure.");
			else
				request.setAttribute("message", "Process not completed Successfully.");
			
			ArrayList clientList=dao.getDistinctClientName();
			request.setAttribute("clientList", clientList);
			key= "assignAPIPage";
		}*/
		
		return key;
	}
}
