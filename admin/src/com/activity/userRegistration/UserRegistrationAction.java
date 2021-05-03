package com.activity.userRegistration;

/**
 *  @author Manoj 
 *  Created Date	 : 30/01/2013
 *  Creature Matter	 : to separate the logic of registration process
 */

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UserRegistrationAction extends ActionSupport implements ServletRequestAware,ServletResponseAware
{
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request; 
	private HttpServletResponse response;
	private Map session;
	
	public String execute () throws Exception
	{
		session=ActionContext.getContext().getSession();
		try
		{
			String userId=(String)session.get("userId");
			if(userId==null)
			{
				request.setAttribute("message","Your Login Session has Expired. Please Login Again.");
				return "sessionExpire";
			}
			
			String param=request.getParameter("param");
			System.out.println("UserRegistrationAction param is "+param);
			if(param.equalsIgnoreCase("internalUser"))
			{
				return "InternalUserReg";
			}
			else if(param.equalsIgnoreCase("CorporateUser"))
			{
				return "CorporateUserReg";
			}
			else if(param.equalsIgnoreCase("RetailUser"))
			{
				return "RetailUserReg";
			}
		}catch(Exception e)
		{
			request.setAttribute("message","Process aborted due to Technical Failure.");
			System.out.println("Exception in UserRegistrationAction");
			System.out.println(e.toString());
			return "err";
		}
		return "err";
	}
	public void setServletRequest(HttpServletRequest request)
	{
		this.request=request;
	}

	public void setSession(Map session)
	{
		session = this.getSession();
	}

	public Map getSession()
	{
		return session;
	}

	public void setServletResponse(HttpServletResponse response) 
	{
		this.response=response;
	}
}
