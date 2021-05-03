package com.reports.ccModule.transactionStatus;

/**	Created By  	: 	Manoj
 * 	Created Date 	:	14/01/2013
 * 	Created Matter	: 	This class will give the details of All data according to a connection number
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class TransactionSearchAction extends ActionSupport implements ServletRequestAware 
{
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	
	public String execute()throws Exception
	{		
		try
		{
			Map session = ActionContext.getContext().getSession();
			String userID=(String)session.get("userId");
			if(userID=="" || userID==null)
			{
				request.setAttribute("message","Your Login Session has Expired. Please Login Again.");
				return "sessionExpire";
			}
			
			String param=request.getParameter("param");
			System.out.println("TransactionSearchAction class param is "+param);
			if(param.equalsIgnoreCase("SearchTranpage"))
			{
				return "SerachTranPage";
			}
			if(param.equalsIgnoreCase("SeachTranData"))
			{
				String connectionNo=request.getParameter("connectionNo");
				String portalId="0";
				String loginType=(String)session.get("loginType");
				if(!loginType.equalsIgnoreCase("SuperAdmin"))
				{
					portalId=(String)session.get("adminUserPortalId");
				}
				
				TransactionSearchDao daoObj=new TransactionSearchDao();
				ArrayList<HashMap<String,String>> data=daoObj.getTranData(userID,connectionNo,loginType,portalId);
				if(data.size()<=0)
				{
					request.setAttribute("message", "Data Not Available.");
					return "SerachTranPage";
				}
				else
				{
					request.setAttribute("data", data);
					return "SerachTranPage";
				}
			}
			
		}catch(Exception e)
		{
			request.setAttribute("message", "Process aborted due to Technical Failure.");
			System.out.println("Exception in TransactionSearchAction");
			System.out.println(e.toString());
			return "SerachTranPage";
		}
		return "err";
		}
	
	public void setServletRequest(HttpServletRequest httpServletRequest) 
	{
		this.request = httpServletRequest;
	}
}
