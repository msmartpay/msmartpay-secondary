package com.activity.adminTask.tradeBalanceLimitSetup;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionContext;

public class TradeBalanceLimitSetupAction implements ServletRequestAware, ServletResponseAware
{
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public String limitSetUp()
	{
		String result="";	
		Map session = ActionContext.getContext().getSession();		
		try {
			String userId = (String) session.get("userId");
			if (userId == null) {
				request.setAttribute("message","Your Login Session has Expired. Please Login Again.");
				return "sessionExpire";
			}
			TradeBalanceLimitSetupDAO DAO=new TradeBalanceLimitSetupDAO();
			ArrayList tbLimitList=DAO.limitSetup();
			request.setAttribute("tbLimitList", tbLimitList);
			
			return "success";
		}catch (Exception e) {			
			request.setAttribute("message","Process aborted due to Technical Failure.");
			System.out.println("Exception in TradeBalanceLimitSetupAction");
			System.out.println(e.toString());
			return "err";
		}	
	}
	
	public String updateLimitSetup()
	{
		String result="";	
		Map session = ActionContext.getContext().getSession();	
		String message="";
		try {
			String userId = (String) session.get("userId");
			if (userId == null) {
				request.setAttribute("message","Your Login Session has Expired. Please Login Again.");
				return "sessionExpire";
			}
			TradeBalanceLimitSetupDAO DAO=new TradeBalanceLimitSetupDAO();
			ArrayList<String> list=new ArrayList<String>();
			ArrayList<String> userlist=new ArrayList<String>();
			list.add(0,request.getParameter("portallimit"));
			list.add(1,request.getParameter("mdslimit"));
			list.add(2,request.getParameter("dslimit"));
			list.add(3,request.getParameter("agentlimit"));
			list.add(4,request.getParameter("apilimit"));
			userlist.add(0, "portalAdmin");
			userlist.add(1, "mds");
			userlist.add(2, "distributor");
			userlist.add(3, "agent");
			userlist.add(4, "apiClient");
			int id=Integer.parseInt(userId);
			for(int i=0;i<5;i++)
			{
				double limit=Double.parseDouble(list.get(i));
				String userType=userlist.get(i);
			    result=DAO.updateLimitSetup(limit, userType, id);
			}
			limitSetUp();
			 
			request.setAttribute("message", "Proceess has been completed.");
		  return "success";
		}catch (Exception e) {	
			
			request.setAttribute("message","Process aborted due to Technical Failure.");
			System.out.println("Exception in TradeBalanceLimitSetupAction");
			System.out.println(e.toString());
			return "err";
		}	
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
}

