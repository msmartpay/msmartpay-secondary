package com.controlPanel.userActivation;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public  class UserActivationAction extends ActionSupport implements ServletRequestAware,ServletResponseAware
{
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request; 
	private HttpServletResponse response;
	private Map session;
	
	public String execute () throws Exception
	{
		Map session=ActionContext.getContext().getSession();
		try{
			String userId=(String)session.get("userId");
			if(userId==null)
			{
				request.setAttribute("message","Your Login Session has Expired. Please Login Again.");
				return "sessionExpire";
			}
			String param=request.getParameter("param");
			System.out.println("UserActivationAction and param is "+param);
			if(param.equalsIgnoreCase("internalUser"))
			{
				session.remove("noOfRecords");
				session.remove("pageSize");
				session.remove("pageNumber");
				session.remove("noOfPages");
				session.remove("userType");
				session.remove("status");
				session.remove("portalId");
				session.remove("buttonStatus");
				session.remove("advanceSearchOption");
				String userType=(String)session.get("adminUserType");
				int pageNumber=1;
				int pageSize=100;
				
				if(userType.equalsIgnoreCase("superadmin") || userType.equalsIgnoreCase("activityAdmin")|| userType.equalsIgnoreCase("activityuser"))
				{
					UserActivationDao daoObj=new UserActivationDao();
					ArrayList portalIdList= daoObj.getPortalIdList();
					session.put("searchPortalIdList",portalIdList);
				}
				session.put("pageNumber",pageNumber);
				session.put("pageSize",pageSize);
				return "InternalUserAct";
			}
			else if(param.equalsIgnoreCase("CorporateUser"))
			{
				session.remove("pageSize");
				session.remove("pageNumber");
				session.remove("noOfPages");
				session.remove("userType");
				session.remove("status");
				session.remove("portalId");
				session.remove("buttonStatus");
				session.remove("advanceSearchOption");
				String userType=(String)session.get("adminUserType");
				if(userType.equalsIgnoreCase("superadmin") || userType.equalsIgnoreCase("activityAdmin")|| userType.equalsIgnoreCase("activityuser"))
				{
					UserActivationDao daoObj=new UserActivationDao();
					ArrayList portalIdList= daoObj.getPortalIdList();
					session.put("searchPortalIdList",portalIdList);
				}
				int pageNumber=1;
				int pageSize=100;
				session.put("pageNumber",pageNumber);
				session.put("pageSize",pageSize);
				return "CorporateUserAct";
			}
			else if(param.equalsIgnoreCase("RetailUser"))
			{
				session.remove("noOfRecords");
				session.remove("pageSize");
				session.remove("pageNumber");
				session.remove("noOfPages");
				session.remove("userType");
				session.remove("status");
				session.remove("portalId");
				session.remove("buttonStatus");
				session.remove("advanceSearchOption");
				String userType=(String)session.get("adminUserType");
				int pageNumber=1;
				int pageSize=100;
			 
				if(userType.equalsIgnoreCase("superadmin")|| userType.equalsIgnoreCase("activityAdmin"))
				{
					UserActivationDao daoObj=new UserActivationDao();
					ArrayList portalIdList= daoObj.getPortalIdList();
					session.put("searchPortalIdList",portalIdList);
				}

				session.put("pageNumber",pageNumber);
				session.put("pageSize",pageSize);
				return "RetailUserAct";
			}
		}catch(Exception e){
			request.setAttribute("message","Process aborted due to Technical Failure.");
			System.out.println("Exception in UserRegistrationAction");
			System.out.println(e.toString());
			return "err";
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
