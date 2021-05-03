package com.controlPanel.profileMgt.corporateUser.whitelabelClient;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.formBean.dynamicDetails.DynamicDetailsFormBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class WhiteLabelClientProfileAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{

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
			System.out.println("WhiteLabelClientProfileAction, param is:"+param);
			if(param.equals("viewWlPrfile")){
				String clientID=request.getParameter("clientId");
				WhiteLabelClientProfileDao daoObj=new WhiteLabelClientProfileDao();
				DynamicDetailsFormBean formBean=daoObj.viewWLClientDetails(clientID);
				request.setAttribute("wlDetailsFormBean",formBean );
				return "viewWlProfile";
			}else if("updatewlProfile".equalsIgnoreCase(param)){
				String companyName=request.getParameter("companyName");
				String domainName=request.getParameter("domainName");
				String HelpEmailId=request.getParameter("HelpEmailId");
				String HelpMobileNo=request.getParameter("HelpMobileNo");
				String txnNotificationEmailID=request.getParameter("txnNotificationEmailID");
				String careUrl=request.getParameter("careUrl");
				
				String clientID=request.getParameter("clientID");
			    //System.out.println(clientID);
				//System.out.println("we are here");
				WhiteLabelClientProfileDao daoObj=new WhiteLabelClientProfileDao();
				//System.out.println("we aree going to call method");
				String result=daoObj.updateWLClientDetails(clientID,companyName,domainName,HelpEmailId,HelpMobileNo,txnNotificationEmailID,careUrl);
			    if("fail".equalsIgnoreCase(result)){
			    	request.setAttribute("message", "Process aborted due to Technical Failure.");
			    }else if("success".equalsIgnoreCase(result)){
			    	request.setAttribute("message", "Proceess has been completed.");
			    }
			    
			    DynamicDetailsFormBean formBean=daoObj.viewWLClientDetails(clientID);
				request.setAttribute("wlDetailsFormBean",formBean );
				return "viewWlProfile";
			}
		}catch(Exception e){
			System.out.println("Exception in WhiteLabelClientProfileAction");
			request.setAttribute("message", "Process aborted due to Technical Failure.");
			System.out.println(e.toString());
			return "viewWlProfile";
		}
		return "err";   
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
