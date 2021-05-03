package com.reports.ccModule.noticeAlert;

/**
 *  Created Date---30/4/2012
 *  Created By---Amit Pathak 
 *  Purpose- The class(NoticeAlertAction)is created for to show and update notice action service
 *  Updated Date-5/30/2012
 *  Updated By--Amit Pathak 
 *  Update Purpose-Implement Login to update all ticker message for all agent
*/

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class NoticeAlertAction extends  ActionSupport implements ModelDriven<Object>,ServletRequestAware,ServletResponseAware 
{
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	TickerFormBean formBean=new TickerFormBean();
	
	public Object getModel() 
	{
		return formBean;
	}
	
	NoticeAlertDao dao=new NoticeAlertDao();
//--------------------------Block to redirect header control to panel control--------
	
	public String noticeAlert()
	{
		HttpSession session = request.getSession(true);
		String userId=(String)session.getAttribute("userId");
		if(userId==null) 
		{
			request.setAttribute("message","Your Login Session has Expired. Please Login Again.");
			return "sessionExpire";
		}
		String UserType=(String)session.getAttribute("loginType");
		try 
		{
			ArrayList<HashMap<String,String>> clientDetails=dao.getclientDetails(userId,UserType);
			session.setAttribute("clientIDs", clientDetails);
		} catch (Exception e)
		{
			request.setAttribute("message", "Process aborted due to Technical Failure.");
			System.out.println("Exception in NoticeAlertAction");
			System.out.println(e.toString());
			return "NoticeAlert";
		}
		return "NoticeAlert";
	}
	
	
//-----------------------------block to get message----
	public String getTickerMessage()
	{
		HttpSession session = request.getSession(true);
		String userId=(String)session.getAttribute("userId");
		if(userId==null) 
		{
			request.setAttribute("message","Your Login Session has Expired. Please Login Again.");
			return "sessionExpire";
		}
		try 
		{
			int id=formBean.getUserID();
			//System.out.println(id);
			String userType=formBean.getUserType();
			//System.out.println(userId);
			//System.out.println(userType);
			String portalUserType=(String)session.getAttribute("loginType");
			//System.out.println(portalUserType);
			String getMessage=dao.getMessage(id,userType,portalUserType,userId);
			ArrayList<HashMap<String,String>> clientDetails=dao.getclientDetails(userId,portalUserType);
			request.setAttribute("clientIDs", clientDetails);
			request.setAttribute("userID", id);
			request.setAttribute("userType", userType);
			request.setAttribute("getMessage", getMessage);
			request.setAttribute("NewTickerMessage", "checkMessage");
		} catch (Exception e) 
		{
			request.setAttribute("message", "Process aborted due to Technical Failure.");
			System.out.println("Exception in NoticeAlertAction");
			System.out.println(e.toString());
		}
		return "NoticeAlert";
	}

	
//------------------method to update----------------
	public String updateMessage()
	{
		HttpSession session = request.getSession(true);
		String userId=(String)session.getAttribute("userId");
		if(userId==null) 
		{
			request.setAttribute("message","Your Login Session has Expired. Please Login Again.");
			return "sessionExpire";
		}
		try 
		{
			int id=formBean.getUserID();
			String userType=formBean.getUserType();
			String NewMessage=formBean.getNewMessage();
			String portalUserType=(String)session.getAttribute("loginType");
			String UpdateMessage=dao.updateMessage(id,userType,NewMessage,portalUserType,userId);
				
			if("Update".equalsIgnoreCase(UpdateMessage))
			{
				request.setAttribute("message","Process has been completed Successfully.");
			}else
			{
				request.setAttribute("message","Process aborted due to Technical Failure.");
			}
				
			ArrayList<HashMap<String,String>> clientDetails=dao.getclientDetails(userId,portalUserType);
			request.setAttribute("clientIDs", clientDetails);
			
		} catch (Exception e)
		{
			request.setAttribute("message", "Process aborted due to Technical Failure.");
			System.out.println("Exception in NoticeAlertAction");
			System.out.println(e.toString());
		}
		finally
		{
			if(dao!=null)
				dao=null;
		}
		return "NoticeAlert";
	}
	
	public void setServletRequest(HttpServletRequest request)
	{
		this.request=request;
	}

	public void setServletResponse(HttpServletResponse arg0)
	{
		
	}
}
