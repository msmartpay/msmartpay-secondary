package com.userSearch;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.agent.AgentDetailsBusinessDelegator;
import com.distributor.DistributorDetailsBusinessDelegator;

public class UserSearchAction extends Action {
	
	public ActionForward execute(ActionMapping mapping,
			 ActionForm form,
			 HttpServletRequest request,
			 HttpServletResponse response)
throws Exception {

		HttpSession session=request.getSession(true);
		String mdId=(String)session.getAttribute("mdId");
		System.out.println("mdId is==============================="+mdId);
		if(mdId==null){
            session.setAttribute("message","Your Login session has Expired. Please Login to Continue.");
			return mapping.findForward("sessionexp");
		}		
		try
		{  
			UserSearchDao daoObj=new UserSearchDao();
			String param=(String)request.getParameter("param");
            System.out.println(" param is============== : "+param);
			
            if("userSearch".equalsIgnoreCase(param))
			{             	
				return mapping.findForward("serachBy");		 
			}
            if ("getDetails".equalsIgnoreCase(param)){
            	
            	String searchBy=request.getParameter("searchBy");
            	System.out.println("serach By ::"+searchBy);
            	if(searchBy.equalsIgnoreCase("Distributor")){
            		String email=request.getParameter("email");
            	//	System.out.println("email ::"+email);
            		String mobNum=request.getParameter("mobNum");
            	//	System.out.println("mobil ::"+mobNum);
            		String userId=request.getParameter("userId");
            	//	System.out.println("user ::"+userId);            		
            		
            		ArrayList distributorList=daoObj.getDSDetails(mdId,mobNum,email,userId);
            	//	System.out.println("distributorList ::"+distributorList);
            		
            		if(distributorList.size()<=0){
            			request.setAttribute("message", "Data is Not Available.");
            			return mapping.findForward("serachBy");		 
            		}
            		request.setAttribute("distributorList", distributorList);
            		return mapping.findForward("userDetailsDS");            		
            	}            	
            	if(searchBy.equalsIgnoreCase("Agent")){
            		String email=request.getParameter("email");
            	//	System.out.println("email  :"+email);
            		String mobNum=request.getParameter("mobNum");
            	//	System.out.println("mob ::"+mobNum);
            		String userId=request.getParameter("userId");
            	//	System.out.println("user id: "+userId);
            		ArrayList AgentList=daoObj.getAgentDetails(mdId,mobNum,email,userId);
            	//	System.out.println(AgentList.size());
            		if(AgentList.size()<=0){
            			request.setAttribute("message", "Data is Not Available.");
            			return mapping.findForward("serachBy");		 
            		}
            		request.setAttribute("AgentList", AgentList);
            		return mapping.findForward("userDetailsAG");	
            	}           	
            }	
		}		
	catch(Exception e){
		System.out.println("Exception in DistributorDetailsAction"+e.toString());
		e.printStackTrace();	
	}
	
return null;
}
}