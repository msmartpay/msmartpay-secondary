package com.changepassword;

/**
 *  Updated By : Manoj
 *  Updated Matter :optimization of code
 *  Updated Date: 29 june 2013
 */

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.common.Encryption;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ChangePasswordAction extends ActionSupport implements ServletRequestAware,ServletResponseAware
{
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request; 
	private Map session;
	
	public String execute() throws Exception
	{ 
		Map session=ActionContext.getContext().getSession();
		try
		{
			String userId=(String)session.get("userId");
			if(userId==null)
			{
				request.setAttribute("message","Your Login Session has Expired. Please Login Again.");
				return "sessionExpire";
			}
			
			String param=request.getParameter("param");
			String adminUserType=(String)session.get("adminUserType");
			System.out.println("ChangePasswordAction and Param is : "+param);
			if(param.equals("resetPassword"))
			{
				if(adminUserType.equals("superadmin"))
				{
					session.put("loginUserName", "Super Admin");
				}
				else
				{
					ChangePasswordDao daoObj=new ChangePasswordDao();
					HashMap<String,Object> loginDetails=daoObj.getLoginDetails(userId,adminUserType);
					String loginUserName=(String)loginDetails.get("loginUserName");
					session.put("loginUserName", loginUserName);
					daoObj=null;
				}
				return "resetPassword";
			}
			else if(param.equals("changePassword"))
			{
				String oldPassword=request.getParameter("oldPassword");
				String newPassword=request.getParameter("newPassword");
				HashMap<String,Object> loginDetails=new HashMap<String,Object>();
				ChangePasswordDao daoObj=new ChangePasswordDao();
				loginDetails=daoObj.getLoginDetails(userId,adminUserType);
				String currentPassword=(String)loginDetails.get("password");
				String oldPass="";
				String newPass="";
				try 
				{
					oldPass= Encryption.SHA1(oldPassword);
					newPass=Encryption.SHA1(newPassword);
				} catch (NoSuchAlgorithmException e)
				{ 
					e.toString();
				} catch (UnsupportedEncodingException e)
				{ 
					e.toString();
				} 
				if(!currentPassword.equals(oldPass))
				{
					request.setAttribute("message","Password is not matching with Existing Password. Please Try Again.");
					daoObj=null;
					return "resetPassword";
				}
				if(oldPass.equals(newPass))
				{
					request.setAttribute("message","New Password Should Be Different. Please Choose Another Password.");
					daoObj=null;
					return "resetPassword";
				}
				String changePasswordStatus=daoObj.updatePassword(userId,newPass,adminUserType);
				if(changePasswordStatus.equals("success"))
				{
					request.setAttribute("message","Your password has been Successfully Changed.");
					daoObj=null;
					return "resetPassword";
				}
				else
				{
					request.setAttribute("changePasswordMessage","Process aborted due to Technical Failure.");
					daoObj=null;
					return "resetPassword";
				}
			}
		}catch(Exception e)
		{
			request.setAttribute("message","Process aborted due to Technical Failure.");
			System.out.println("Exception in class Name");
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
		
	}
}
