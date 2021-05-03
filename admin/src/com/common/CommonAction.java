package com.common;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.login.LoginDao;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.utility.WebServicesUtility;

public class CommonAction extends ActionSupport implements ServletRequestAware,ServletResponseAware
{
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request; 
	private HttpServletResponse response; 
	private Map session;
	
	public String execute() throws Exception
	{ 
		Map session=ActionContext.getContext().getSession();
		try
		{
			PrintWriter out=response.getWriter();
			String param=request.getParameter("param");
			String userId=(String)session.get("userId");
			if(param.equals("checkSession"))
			{
				if(userId==null)
				{
					out.print("invalid");
				}
				else
				{
					out.print("valid");
				}
				return null;
			}
			else if(userId==null)
			{
				request.setAttribute("message","Your Login Session has Expired. Please Login Again.");
				return "sessionExpire";
			}

			else if(param.equals("logout"))
			{
				((SessionMap)ActionContext.getContext().getSession()).invalidate();
				return "logout";
			}
			else if(param.equals("home"))
			{
				LoginDao daoObj=new LoginDao();
				String clientId="";
				if(Long.parseLong(userId)!=1)
					clientId=session.get("clientId")+"";
				long userIdlong=Long.parseLong(clientId);
				HashMap<String,Object> dashboardMap=daoObj.dashboard(userIdlong);

				request.setAttribute("dashboardMap",dashboardMap);
				return "adminHome";
			}else if(param.equals("InstantBillpayStatusCheck"))
			{

				out.print(WebServicesUtility.instantPayBillpayStatusCheck(request.getParameter("REQUEST_ID")));
				out.close();
				return null;
				
			}else if(param.equals("iciciStatusCheck"))
			{

				out.print(WebServicesUtility.iciciStatusCheck(request.getParameter("REQUEST_ID")));
				out.close();
				return null;
				
			}else if(param.equals("ekoStatusCheck"))
			{

				out.print(WebServicesUtility.iciciStatusCheck(request.getParameter("REQUEST_ID")));
				out.close();
				return null;
				
			}else if(param.equals("InstantBillpayStatusCheckPage"))
			{
				request.setAttribute("apiResponse",request.getParameter("apiResponse"));

				return "LiveStatus";
			}
		}
		catch(Exception e)
		{
            request.setAttribute("message","Process aborted due to Technical Failure.");
            System.out.println("Exception in class Name");
            System.out.println(e.toString());
  	      return "err";
		}
		return "err";
	}	
		  
	public void setSession(Map session)
	{
		session = this.getSession();
	}
	
	public Map getSession(){
		return session;
	}
	@Override
	public void setServletRequest(HttpServletRequest request)
	{
		this.request=request;
	}
	public void setServletResponse(HttpServletResponse response) 
	{
		this.response=response;
	}
}
