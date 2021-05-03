package com.login;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;



public class LoginAction extends Action {

	public ActionForward execute(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
					throws Exception {
		HttpSession ses=request.getSession(true);
		Connection con=null;

		if(ses==null){			
			return mapping.findForward("sessionexp");
		}		
		try
		{	
			String url="";			
			String param=(String)request.getParameter("param");			
			if("login".equals(param))
			{  
				String userType=(String)ses.getAttribute("userType");
				String clientId=(String)ses.getAttribute("clientId");
				String userName=(String)request.getParameter("userName");
				String password=(String)request.getParameter("password");

				if(clientId==null || userType==null){
					request.setAttribute("message","Your Login session has Expired. Please Login to Continue.");
					return mapping.findForward("sessionexp");
				}				
				url=LoginBusinessDelegater.chekLoginStatus(con,userName,password,request,clientId,userType);
				System.out.println(url);
				return mapping.findForward(url);
			}
			if("logout".equals(param))
			{
				String mdId=(String)ses.getAttribute("mdId");
				if(mdId==null){
					request.setAttribute("message","Your Login session has Expired. Please Login to Continue.");
					return mapping.findForward("sessionexp");
				}
				@SuppressWarnings("unused")
				String status=LoginDao.updateLoginStatus(mdId);
				ses.removeAttribute(mdId);
				ses.removeAttribute("userType");
				ses.invalidate();
				request.setAttribute("message","Your Login session has been invalidated. Please Login to Continue.");
				return mapping.findForward("sessionexp");
			}
			if("logout1".equals(param))
			{
				String mdId=(String)ses.getAttribute("mdId");
				if(mdId==null){
					request.setAttribute("message","Your Login session has Expired. Please Login to Continue.");
					return mapping.findForward("sessionexp");
				}
				@SuppressWarnings("unused")
				String status=LoginDao.updateLoginStatus(mdId);
				ses.removeAttribute(mdId);
				ses.removeAttribute("userType");
				ses.invalidate();
				request.setAttribute("message","Your Login session has been invalidated. Please Login to Continue.");
				return mapping.findForward("sessionexp");
			}			
			String userid=(String)ses.getAttribute("mdId");

			if(userid==null){
				request.setAttribute("message","Your Login session has Expired. Please Login to Continue.");
				return mapping.findForward("sessionexp");
			} 			
			if("mdHome".equals(param))
			{
				return mapping.findForward("mdHome");
			}

			if("home".equals(param))
			{
				return mapping.findForward("home");
			}
		}
		catch(Exception e){
			System.out.println("Exception in LoginAction class"+e.toString());	
		}	
		return null;
	}
}

