package com.activity.adminTask.schedulerRun;

/**
 *  Created By     : Manoj
 *  Created Date   : 2013/02/26
 *  Created Matter : To provide the functionality of running Scheduler 
 */
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.utility.UtilityP;

public final class SchedulerAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{	
	
	private static final long serialVersionUID = 1L;	
	private HttpServletRequest request; 	
	private HttpServletResponse response;	
	private Map session;	
	
	public String execute() throws Exception{
		Map session=ActionContext.getContext().getSession();
		try{		
			String userId=(String)session.get("userId");	
			if(userId==null){	
				request.setAttribute("message","Your Login Session has Expired. Please Login Again.");	
				return "sessionExpire";
			}	
			String param=request.getParameter("param");	
			
			System.out.println("Class is SchedulerAction and param is "+param);	
			
			if(param.equalsIgnoreCase("getPage")){
				
				return "SchedularPage";
			}else if(param.equalsIgnoreCase("runSchedular")){
				
				String type=request.getParameter("type");
				//System.out.println(type);
				String url ="";
				if(type.equalsIgnoreCase("webSchedular")){
					// Test Url
//					url="http://184.172.248.134:8091/TepScheduler/AdminScheduler?param=tepscheduler";
					// Live URl
					url="http://scheduler.commsoft.co.in/TepScheduler/AdminScheduler?param=tepscheduler";
				}else if(type.equalsIgnoreCase("ApiSchedular")){
					// Test URl
//						url="http://184.172.248.134:8091/TepScheduler/AdminScheduler?param=apischeduler";
					// Live URL
					url="http://scheduler.commsoft.co.in/TepScheduler/AdminScheduler?param=apischeduler";
				}else if(type.equalsIgnoreCase("MappSchedular")){
					//Test URl
//					url="http://184.172.248.134:8091/TepScheduler/AdminScheduler?param=mobappscheduler";
					// Live URL
					url="http://scheduler.commsoft.co.in/TepScheduler/AdminScheduler?param=mobappscheduler";
				}else{
					request.setAttribute("Message", "Service is not available at this time.");
					return "SchedularPage";
				}
				//System.out.println("url is "+url);		
				String searchResponse = UtilityP.post(url, null, "text/xml", "GET", null);
				//System.out.println(searchResponse);
				request.setAttribute("message", searchResponse);
				return "SchedularPage";
				
			}else{	
				request.setAttribute("message", "Process aborted due to Technical Failure.");
				return "SchedularPage";
			}	
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "Process aborted due to Technical Failure.");
			System.out.println(e.toString());	
		}
		return "SchedularPage";	
	}
	public void setServletRequest(HttpServletRequest request){
		this.request=request;
	}
	public void setSession(Map session){
		session = this.getSession();
	}
	
	public Map getSession(){
		return session;
	}
	
	public void setServletResponse(HttpServletResponse response) {
		this.response=response;
	}
}
