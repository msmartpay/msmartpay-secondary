package com.login;

import java.sql.Connection;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;



public class MarketingUserLoginAction extends Action {
	
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
			if("OtherUserlogin".equals(param))
			{  
				/*String userType=(String)ses.getAttribute("userType");
				String clientId=(String)ses.getAttribute("clientId");
				String userName=(String)request.getParameter("userName");
				String password=(String)request.getParameter("password");
		*/
				 String clientId="";
				 String userName="";
				 String password="";
				 String userType="";
				
				 String FlagType=request.getParameter("flag");
				 System.out.println("FLag Is ::"+FlagType);
				  
				  if (FlagType.equalsIgnoreCase("REP")){
					  
					  clientId="10003";			  
					  userName ="demorechargeepoint@gmail.com";
					  ses.setAttribute("DistributorMailId",userName);
					  password="123456";
					  ses.setAttribute("password",password);
					  userType="REPMD";
					  System.out.println(userName+ "......"+password+"........."+userType +"REP" +clientId);		 
					  ses.setAttribute("userType",userType);
					  ses.setAttribute("clientId",clientId);
					  String domainName="www.rechargeepoint.com";
					  HashMap info=LoginDao.getDynamicDetails(domainName);
					  ses.setAttribute("info", info);
				  }	
				
				  else{
					  clientId="10001";			  
					  userName ="demotravelepoint@gmail.com";
					  ses.setAttribute("DistributorMailId",userName);
					  password="123456";
					  ses.setAttribute("password",password);
					  userType="company owner";
					  System.out.println(userName+ "......"+password+"........."+userType +"REP" +clientId);		 
					  ses.setAttribute("userType",userType);
					  ses.setAttribute("clientId",clientId);	
					  String domainName="www.travelepoint.com";
					  HashMap info=LoginDao.getDynamicDetails(domainName);
					  ses.setAttribute("info", info);
				  }
			
				if(clientId==null || userType==null){
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
					return mapping.findForward("sessionexp");
				}
				@SuppressWarnings("unused")
				String status=LoginDao.updateLoginStatus(mdId);
				ses.removeAttribute(mdId);
				ses.removeAttribute("userType");
                ses.invalidate();
				response.sendRedirect("/mds");
			}
			if("logout1".equals(param))
			 {
				String mdId=(String)ses.getAttribute("mdId");
				if(mdId==null){
					return mapping.findForward("sessionexp");
				}
				@SuppressWarnings("unused")
				String status=LoginDao.updateLoginStatus(mdId);
				ses.removeAttribute(mdId);
				ses.removeAttribute("userType");
                ses.invalidate();
				response.sendRedirect("/mds");
			 }			
			String userid=(String)ses.getAttribute("mdId");
			
			if(userid==null){
				ses.setAttribute("message","Your Login session has Expired. Please Login to Continue.");
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

