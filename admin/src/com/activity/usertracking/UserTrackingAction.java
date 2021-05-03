package com.activity.usertracking;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

public class UserTrackingAction 
{
	private HttpServletRequest request=null;
	private HttpSession session=null;
	private String userType;
	private String fromDate;
	private String toDate;
	private String userId;
	private String param;
	
	public String execute() 
	{
		String key="";
		
		
		request=ServletActionContext.getRequest();
		session=request.getSession();
		
		String loginUserId=(String)session.getAttribute("userId");
		if(loginUserId==null) 
		{
			request.setAttribute("message","Your Login Session has Expired. Please Login Again.");
			return "sessionExpire";
		}
		System.out.println("In UserTracking Action ------------------------------"+param);
		
		if(param.equalsIgnoreCase("page"))
		{
			key= "page";
		}
		else if(param.equalsIgnoreCase("fetchTrackingDetails"))
		{
			if(null==userId || "".equalsIgnoreCase(userId))
				userId="0";
			
			UserTrackingDao dao=new UserTrackingDao();
			List<UserTracking> list=dao.getTrackingDetails(userType, Long.parseLong(userId),fromDate,toDate);
			if(list!=null && list.size()>0) {
				request.setAttribute("TrackingDetails", list);
			}else
				request.setAttribute("message", "Details not available");
				
			
			key= "page";
		}
		
		
		return key;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
}
