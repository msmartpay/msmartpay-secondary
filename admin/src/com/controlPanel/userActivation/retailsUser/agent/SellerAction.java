package com.controlPanel.userActivation.retailsUser.agent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Updated By 		 : Manoj
 * Updated Date		 : 18 Jun 2013
 * Updated Matter	 : Optimization of code and database .
 */
public  class SellerAction extends ActionSupport implements ServletRequestAware,ServletResponseAware
{
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request; 
	private HttpServletResponse response;
	private Map session;

	public String execute() throws Exception
	{ 
		Map session=ActionContext.getContext().getSession();
		try{
			String userId=(String)session.get("userId");

			if(userId==null){
				request.setAttribute("message","Your Login Session has Expired. Please Login Again.");
				return "sessionExpire";
			}
			HashMap dynamicDetails=(HashMap) session.get("dynamicDetails");
			String clientFlag=(String)dynamicDetails.get("clientFlag");
			String param=request.getParameter("param");
			System.out.println("AgentActivationAction and param is:"+param);
			if(param.equals("searchUserType"))
			{
				session.remove("noOfPages");
				session.remove("userType");
				session.remove("status");
				session.remove("portalId");
				session.remove("buttonStatus");
				session.remove("advanceSearchOption");
				
				SellerDao daoObj=new SellerDao();
				
				String userType=(String)session.get("loginType");
				if(userType.equalsIgnoreCase("superadmin")||userType.equalsIgnoreCase("activityAdmin") ||userType.equalsIgnoreCase("activityUser")){


					session.remove("agentActivationUserId");
					String status=(String)request.getParameter("status");
					
					//session.put("buttonStatus",status);
					
					int pageNumber=Integer.parseInt((String)request.getParameter("pageNumber"));
					if(pageNumber==1)
					{
						int noOfRecords=daoObj.getNoOfRecordsAllAgentDetails(clientFlag);
						if(noOfRecords==0)
						{
							request.setAttribute("message","Please Check Input Value.");
							return "searchUser";
						}
						session.put("noOfRecords",noOfRecords);
					}
					//								System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
					ArrayList agentDetails=daoObj.getAllAgentsDetail(pageNumber,clientFlag);
					
					session.put("pageNumber",pageNumber);
					session.put("status",status);
					session.put("agentSearchList",agentDetails);
					
					
				}

			}
		}catch(Exception e){
			request.setAttribute("message", "Process aborted due to Technical Failure.");
			System.out.println("Exception in AgentActivationAction");
			System.out.println(e.toString());
			return "registrationPage";
		}
		return "err";   
	}
	public void setServletRequest(HttpServletRequest request){
		this.request=request;
	}
	public void setSession(Map session){
		session = this.getSession();
	}
	public Map getSession(){
		return session;
	}
	public void setServletResponse(HttpServletResponse response) {
		this.response=response;
	}
}
