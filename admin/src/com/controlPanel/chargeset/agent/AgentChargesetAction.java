package com.controlPanel.chargeset.agent;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;

public class AgentChargesetAction extends ActionSupport implements ServletRequestAware,ServletResponseAware 
{
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	
	public String execute()
	{
		//System.out.println("------------------------------------------------------------------------------------------------");
		HttpSession session = request.getSession(true);
		String userId=(String)session.getAttribute("userId");
		String param=request.getParameter("param");
		//System.out.println("in action ------------------------------------------------------------------------:" +param);
		
		if(param.equalsIgnoreCase("getDetails"))
		{
			System.out.println("in getDetails");
			String userType=(String)session.getAttribute("loginType");
			int portalId=0;
		
			
			List getDetails=null;
			
			if(userId==null)
			{
				session.setAttribute("loginMessage","Session has been expired,Please relogin to continue.");
				return "sessionExpire";
			}		
			if(userType.equalsIgnoreCase("portalAdmin")||userType.equalsIgnoreCase("portalUser"))
			{
				portalId=Integer.parseInt((String)session.getAttribute("adminUserPortalId"));
			}
			
			System.out.println("Portal id is : "+portalId); 
			String enterUserID=request.getParameter("EnterUserId");
			String service=request.getParameter("selectService");
			String ByType=request.getParameter("usertype");		 
		
			AgentChargesetDao dao=new AgentChargesetDao();	
		
			getDetails=dao.getAgentMarginDetails(enterUserID,service,ByType,portalId,userType);
			
			//System.out.println(getDetails.size());
			//System.out.println(getDetails);
			
			if(getDetails.size()>0)
			{
				//System.out.println(getDetails);
				request.setAttribute("getDetails", getDetails);
				request.setAttribute("enterUserID", enterUserID);				
				request.setAttribute("service", service);
				request.setAttribute("userType", ByType);
				request.setAttribute("flag", "Y");
				
			}else
			{
				request.setAttribute("message","User does not exist");	
				request.setAttribute("enterUserID", enterUserID);
			}
		}
		//System.out.println("hii arvind");
		//request.setAttribute("flag", "Y");
		return "Recharge"; 
	}
		
	public void setServletRequest(HttpServletRequest request) 
	{
		this.request=request;
	}

	public void setServletResponse(HttpServletResponse arg0) {
	}
}
