 package com.depositreport;

import java.io.PrintWriter;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.depositreport.DepositReportBusinessDelegator;

public class DepositReportAction extends Action {
	
	public ActionForward execute(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
	throws Exception {
		HttpSession session=request.getSession(false);
		
		if(session==null){
			session.setAttribute("message","Your login session has expired, Please relogin to continue");
			return mapping.findForward("sessionexp");
		}
		
		Connection con = null;
		try
		{
			Vector vector=new Vector();
			String dist_id=(String)session.getAttribute("user_id");
			
			String param=(String)request.getParameter("param");
			//System.out.println("Parameter is "+param);
			if(param.equals("agentdepositreport")){
				String option=(String)request.getParameter("searchoption");
				String agent_id=(String)request.getParameter("agentid");
				String todt=(String)request.getParameter("todate");
				String fromdt=(String)request.getParameter("fromdate");
				if(option.equals("agentid")){

					String status=DepositReportBusinessDelegator.getAgentIdStatus(con,agent_id,dist_id);
					System.out.println("status is=============="+status);
					if(status.equalsIgnoreCase("valid")){
						vector=DepositReportBusinessDelegator.getAgentDepositReport(con,dist_id,agent_id,todt,fromdt);
					}
					else{
						session.setAttribute("message","Please enter correct email id.");
						return mapping.findForward("agentreport");
					}
			
				}
				if(option.equals("all")){
					vector=DepositReportBusinessDelegator.getAllAgentDepositReport(con,dist_id,todt,fromdt);
				}
			
				session.setAttribute("vector",vector);
				return mapping.findForward("agentdepositreport");
			}
			if(param.equals("report")){			    
				session.setAttribute("message","");
				return mapping.findForward("report");
			}
			if(param.equals("agentreport")){			    
				session.setAttribute("message","");
				return mapping.findForward("agentreport");
			}
			
			if(param.equals("depositrequest")){			
				String todt=(String)request.getParameter("todate");
				String fromdt=(String)request.getParameter("fromdate");
				
				vector=DepositReportBusinessDelegator.getDistributorDepositReport(con,dist_id,todt,fromdt);
			
				session.setAttribute("vector",vector);
				return mapping.findForward("depositrequest");
			}
		}
		catch(Exception e){
			System.out.println(e);
			System.out.println("report generation");	
		}
		return null;
	}
}