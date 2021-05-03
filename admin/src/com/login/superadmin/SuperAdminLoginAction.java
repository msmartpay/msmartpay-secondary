package com.login.superadmin;

/**
 *  Updated By : Manoj
 *  Updated Matter : Code optimization on 1/6/2013
 */

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.common.Encryption;
import com.formBean.superAdmin.SuperAdminLoginInfoFormBean;
import com.login.LoginDao;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public  class SuperAdminLoginAction extends ActionSupport implements ModelDriven,ServletRequestAware,ServletResponseAware
{
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request; 
	private Map session;
	private SuperAdminLoginInfoFormBean loginform=new SuperAdminLoginInfoFormBean();	
	public Object getModel(){return loginform;}

	public String execute() throws Exception
	{ 
		Map session=ActionContext.getContext().getSession();
		try
		{
			String param=loginform.getParam();
			System.out.println("SuparAdminLoginAction, Param is : "+param);
			SuperAdminLoginDao daoObj=new  SuperAdminLoginDao();
			if(param.equals("login"))
			{
				String password=loginform.getPassword();
				String pass=Encryption.SHA1(password);
				System.out.println("Pass : "+pass);
				String userName=loginform.getUserName();
				String Ip=request.getParameter("IP");
				//--------------------------------------Method call to  check user login status---------------------		
				String loginStatus=daoObj.checkLoginDetails(userName,pass,session,Ip);
				if(loginStatus.equalsIgnoreCase("valid"))
				{
					return "login";
				}else
				{
					request.setAttribute("message", "Your User Name or Password is Invalid.");
					return "loginFailed";
				}
			}
			else if("checkloginInfo".equals(param))
			{
				String userId=request.getParameter("userId");
				String portalId=(String)session.get("adminUserPortalId");
				HashMap<String,Object> serviceMap=daoObj.getUserServiceAuthenticationDetails();
				HashMap dynamicDetailsMap=daoObj.getDynamicDetails();
				session.put("dynamicDetails",dynamicDetailsMap);
				session.put("adminServiceDetails",serviceMap);

				LoginDao daoObjd=new LoginDao();
				long userIdlong=Long.parseLong(session.get("userId").toString());
				HashMap<String,Object> dashboardMap=daoObjd.dashboard(userIdlong);

				request.setAttribute("dashboardMap",dashboardMap);

				return "adminHome";
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception in SuperAdminLoginAction");
			System.out.println(e.toString());
			request.setAttribute("message", "Process aborted due to Technical Failure.");
			return "loginFailed";
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
	@Override
	public void setServletResponse(HttpServletResponse response)
	{

	}
}

