package com.travelbooking;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class TravelBookingAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;
	
	
	public String execute() {
		
		request=ServletActionContext.getRequest();
		response=ServletActionContext.getResponse();
		session=request.getSession();
		
		try {
			
			if(session.getAttribute("agentID")==null){
				request.setAttribute("message", "Your Login Session has Expired. Please Login Again.");
				return "sessionExp";
			}
			String param=request.getParameter("param");
			if(param.equalsIgnoreCase("getPage")){
				return "success";
			}else if(param.equalsIgnoreCase("travelBooking")){
				
				
				
				
				
				
				
				
				return "success";
			}
			
		} catch (Exception e) {
			request.setAttribute("message", "Technical Failure. Please Login Again.");
			return "sessionExp";
		}
		
		
		
		return null;
	}
}
