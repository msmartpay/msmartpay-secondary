package com.reports.ccModule.userSearch;

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
 * Created Date 	:22/02/2013
 * Created By 		:Manoj
 * Created Matter 	: To search user by mobile number and email id
 *
 */

public class SearchUserAction extends ActionSupport implements ServletRequestAware,ServletResponseAware
{
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request; 
	private HttpServletResponse response;
	private Map session;
	
	public String execute() throws Exception
	{
		try
		{
			session= ActionContext.getContext().getSession();
			String userId=(String)session.get("userId");
			if(userId==null)
			{
				request.setAttribute("message","Your Login Session has Expired. Please Login Again.");
				return "sessionExpire";
			}
			String param=request.getParameter("param");

			System.out.println("SearchUserAction , param is : "+param);

			if(param.equalsIgnoreCase("getSearchUserPage"))
			{
				
				return "UserSearchPage";
			}
			else if(param.equalsIgnoreCase("getUserDetails"))
			{
				String userType=request.getParameter("userType");
				String mobileNo=request.getParameter("mobileNo");
				String emailID=request.getParameter("emailID");
				String name=request.getParameter("name");
				String searchUserId=request.getParameter("searchUserId");
				String loginType=(String)session.get("loginType");
				System.out.println("All details is userType "+userType+"mobileNo is "+mobileNo+"emailID is "+emailID +" searchUserId is "+searchUserId);
				SearchUserDao daoObj=new SearchUserDao();
				ArrayList<HashMap<String,String>> UserData=new ArrayList<HashMap<String,String>>();
				
				if(userType.equalsIgnoreCase("adminUser"))
				{
					UserData=daoObj.getAdminUserData(userType,mobileNo,emailID,loginType,userId,searchUserId,name);
				}else if(userType.equalsIgnoreCase("mds"))
				{
					UserData=daoObj.getMdsUserData(userType,mobileNo,emailID,loginType,userId,searchUserId,name);
				}else if(userType.equalsIgnoreCase("ds"))
				{
					UserData=daoObj.getDSUserData(userType,mobileNo,emailID,loginType,userId,searchUserId,name);
				}else if(userType.equalsIgnoreCase("agent"))
				{
					UserData=daoObj.getAgentUserData(userType,mobileNo,emailID,loginType,userId,searchUserId,name);
				}else if(userType.equalsIgnoreCase("egen"))
				{
					UserData=daoObj.getEgenUserData(userType,mobileNo,emailID,searchUserId);
				}
				
				int size=UserData.size();
				if(size<=0)
				{
					request.setAttribute("message", "Please Check Input Value.");
					return "UserSearchPage";
				}else
				{
					request.setAttribute("UserData", UserData);
					if(userType.equalsIgnoreCase("adminUser"))
					{
						return "AdminUserData";
					}else if(userType.equalsIgnoreCase("mds"))
					{
						return "MDSUserData";
					}else if(userType.equalsIgnoreCase("ds"))
					{
						return "DSUserData";
					}else if(userType.equalsIgnoreCase("agent"))
					{
						return "AgentUserData";
					}else if(userType.equalsIgnoreCase("egen"))
					{
						return "EgenUserData";
					}
				}
			}
		}catch (Exception e) 
		{
			request.setAttribute("message", "Process aborted due to Technical Failure.");
			System.out.println("Exception in SearchUserAction");
			System.out.println(e.toString());
			return "UserSearchPage";
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
