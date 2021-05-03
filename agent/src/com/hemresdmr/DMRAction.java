package com.hemresdmr;

import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class DMRAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	
	
	public String execute()
	throws Exception {
		
		request=ServletActionContext.getRequest();
		HttpSession session=request.getSession(false);
		
		String agentID="";
		ServletContext context=null;
		try
		{
			
			if(session.getAttribute("agentID")==null){
				request.setAttribute("message", "Your Login Session has Expired. Please Login Again.");
				return "sessionExp";
			}
			agentID=(String)session.getAttribute("agentID");
			HeremsDMRDao dao=new HeremsDMRDao();
			
			HashMap<String,String> map=dao.getUserDetails(agentID);
			request.setAttribute("UserDetails",map);
			
			return "Success";
			
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
	}
}
