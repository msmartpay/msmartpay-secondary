package com.distributor;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class DistributorDetailsAction extends Action {
	
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
		int val=0;
		Connection con = null;
		try
		{			
			String param=(String)request.getParameter("param");
            System.out.println(" param is============== : "+param);
            
            if("DoAllActivateDistributor".equals(param))
    		{  
            	String ActiveStatus=(String)DistributorDetailsBusinessDelegator.DoAllActivateDistributor(mdId);            	
            	if (ActiveStatus.equals("update"))
            	{
            		request.setAttribute("message","Selected User is Activated.");                        
            	}
            	if(ActiveStatus.equals("notupdate"))
            	{		
            		request.setAttribute("message","Process aborted due to Technical Failure.");            		
            	}
    		
               	ArrayList distributorList=DistributorDetailsBusinessDelegator.getAllDistributorActive(mdId);
                String ControlButton="active";
    			request.setAttribute("ControlButton",ControlButton);
                String UpdatedStatus="Activated";
    			request.setAttribute("UpdatedStatus",UpdatedStatus);
    			request.setAttribute("distributorList",distributorList);

    			return mapping.findForward("distributorlist");
    		 }          
            
            if("DoAllDeactivateDistributor".equals(param))
            {
            	String ActiveStatus=(String) DistributorDetailsBusinessDelegator.DoAllDeactivateDistributor(mdId);            	
            	if (ActiveStatus.equals("update"))
            	{
            		request.setAttribute("message","Selected User is Deactivated.");            		
            	}
            	if(ActiveStatus.equals("notupdate"))
            	{
            		request.setAttribute("message","Process aborted due to Technical Failure.");    					
            	}    
            	ArrayList distributorList=DistributorDetailsBusinessDelegator.getAllDistributorDeactive(mdId);    		    
    			String ControlButton="deactive";
    			request.setAttribute("ControlButton",ControlButton);
                String UpdatedStatus="Deactivated";
    			request.setAttribute("UpdatedStatus",UpdatedStatus);
    			request.setAttribute("distributorList",distributorList);
    			return mapping.findForward("distributorlist");
            }            
            
            if("activateDistributor".equals(param))
            {			
            	String ids=(String)request.getParameter("distributorids");
            	ArrayList<String> ActiveDistData= new ArrayList<String>();
            	ArrayList<String> NotActiveDistData= new ArrayList<String>();			
            	StringTokenizer str=new StringTokenizer(ids,"_");
            	String distributorId="";
            	while(str.hasMoreTokens())
            	{
            		distributorId=str.nextToken();
            		String ActiveStatus=(String)DistributorDetailsBusinessDelegator.activateDistributorStatus(distributorId,mdId);
            		
            		if (ActiveStatus.equals("update"))
            		{					
						ActiveDistData.add(distributorId);
            		}
            		if(ActiveStatus.equals("notupdate"))
            		{					
            			NotActiveDistData.add(distributorId);
            		}
            	}			
            	request.setAttribute("ActiveDistData",ActiveDistData);
            	request.setAttribute("NotActiveDistData",NotActiveDistData);
            	ArrayList distributorList=DistributorDetailsBusinessDelegator.getAllDistributorActive(mdId);
            	String ControlButton="active";
            	request.setAttribute("ControlButton",ControlButton);
            	String UpdatedStatus="Activated";
            	request.setAttribute("UpdatedStatus",UpdatedStatus);
            	request.setAttribute("distributorList",distributorList);

            	return mapping.findForward("distributorlist");
            }      
            if("deactivateDistributor".equals(param))
            {	     	
            	String ids=(String)request.getParameter("distributorids");        	
            	ArrayList<String> ActiveDistData= new ArrayList<String>();
            	ArrayList<String> NotActiveDistData= new ArrayList<String>();        	
            	StringTokenizer str=new StringTokenizer(ids,"_");
            	String distributorId="";
            	while(str.hasMoreTokens())
            	{
            		distributorId=str.nextToken();
            		String ActiveStatus=(String) DistributorDetailsBusinessDelegator.deactivateDistributorStatus(distributorId,mdId);
            		
            		if (ActiveStatus.equals("update"))
            		{
            			ActiveDistData.add(distributorId);
            		}
            		if(ActiveStatus.equals("notupdate"))
            		{
            			NotActiveDistData.add(distributorId);
            		}
            	}
            	request.setAttribute("ActiveDistData",ActiveDistData);
            	request.setAttribute("NotActiveDistData",NotActiveDistData);
            	ArrayList<?> distributorList=DistributorDetailsBusinessDelegator.getAllDistributorDeactive(mdId);
		    
            	String ControlButton="deactive";
            	request.setAttribute("ControlButton",ControlButton);
            	String UpdatedStatus="Deactivated";
            	request.setAttribute("UpdatedStatus",UpdatedStatus);
            	request.setAttribute("distributorList",distributorList);

            	return mapping.findForward("distributorlist");
            }

            if("viewDistributor".equals(param))
            {				
            	ArrayList<?> distributorList=DistributorDetailsBusinessDelegator.getAllDistributor(mdId);
            	request.setAttribute("distributorList",distributorList);

            	String ControlButton="AllActiveDis";
            	request.setAttribute("ControlButton",ControlButton);
            	return mapping.findForward("distributorlist");
            }
            if("active".equals(param))
            {				
            	ArrayList<?> distributorList=DistributorDetailsBusinessDelegator.getAllDistributorActive(mdId);
            	//System.out.println("distributorList id======================="+distributorList);
            	request.setAttribute("distributorList",distributorList);
            	String ControlButton="active";
            	request.setAttribute("ControlButton",ControlButton);
            	return mapping.findForward("distributorlist");
            }
            if("deactive".equals(param))
            {				
            	ArrayList<?> distributorList=DistributorDetailsBusinessDelegator.getAllDistributorDeactive(mdId);
            	//System.out.println("distributorList id======================="+distributorList);
            	request.setAttribute("distributorList",distributorList);
            	String ControlButton="deactive";
            	request.setAttribute("ControlButton",ControlButton);
				return mapping.findForward("distributorlist");
            }
            if("updateProfile".equals(param))
            {	     	
            	HashMap distributorInfo=(HashMap)session.getAttribute("distributorDetails");
            	session.setAttribute("distributorInfo",distributorInfo);
            	return mapping.findForward("updateProfile");
            }			
		}			
		catch(Exception e){
		System.out.println("Exception in DistributorDetailsAction"+e.toString());
		e.printStackTrace();	
	}	
return null;
}
}

