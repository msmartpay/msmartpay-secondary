package com.masterdistributor;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class MasterDistributorAction extends Action {
	
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
			
			String param=(String)request.getParameter("param");
			System.out.println("param is===================== "+param);
			if("myProfile".equals(param))
			{
				String md_id=(String)session.getAttribute("completeId");
				HashMap mdInfo=MasterDistributorBusinessDelegator.getMasterDistributorInformation(md_id);
				request.setAttribute("mdInfo",mdInfo);
				return mapping.findForward("myProfile");
			}
			if("updateProfile".equals(param))
			{		
				HashMap mdInfo=(HashMap)session.getAttribute("mdInfo");
				session.setAttribute("mdInfo",mdInfo);
				return mapping.findForward("updateProfile");
			}
			if("saveProfile".equals(param))
			{			
				String md_id=(String)session.getAttribute("completeId");
				String firmname=(String)request.getParameter("firmname");
				String Address1=(String)request.getParameter("Address1");
				String Address2=(String)request.getParameter("Address2");
				String state=(String)request.getParameter("state");
				String District=(String)request.getParameter("District");
				String city=(String)request.getParameter("city");
				String PinCode=(String)request.getParameter("officePinCode");
				
				
				String status=MasterDistributorBusinessDelegator.updateMasterDistributorProfile(md_id,firmname,Address1,Address2,state,District,city,PinCode);
				System.out.println("status is================="+status);
				if(status.equalsIgnoreCase("notupdate")){
					request.setAttribute("message", "Process aborted due to Technical Failure.");
					return mapping.findForward("myProfile");
				}
				HashMap mdInfo=MasterDistributorBusinessDelegator.getMasterDistributorInformation(md_id);
			//	System.out.println("mdInfo id======================="+mdInfo);
				request.setAttribute("mdInfo",mdInfo);
				request.setAttribute("message", "Profile is Successfully Updated.");
				return mapping.findForward("myProfile");
			}			
		}catch(Exception e){
			System.out.println("Exception in MasterDistributorAction  "+e.toString());
			e.printStackTrace();	
		}
		finally
		{
			try
			{
			
			}
			catch(Exception e)
			{
				System.out.println("Exception in MasterDistributorAction  "+e.toString());
			}	
		}
		return null;
	}
}

