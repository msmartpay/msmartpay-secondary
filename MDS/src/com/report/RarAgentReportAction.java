package com.report;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public final class RarAgentReportAction extends Action{

	public ActionForward execute(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
					throws Exception {
		HttpSession ses=request.getSession(true);
		try
		{
			RarAgentReportDao daoObj=new RarAgentReportDao();
			String mdId=(String)ses.getAttribute("mdId");
			if(mdId==null){
				return mapping.findForward("sessionexp");
			}
			String param=request.getParameter("param");
			if(param.equalsIgnoreCase("getReport")){
				return mapping.findForward("reportPage");
			}
			if(param.equalsIgnoreCase("AgentReport")){
				return mapping.findForward("getPage");
			}
			if(param.equalsIgnoreCase("DSReport")){
				return mapping.findForward("getDSPage");
			}
			if(param.equalsIgnoreCase("LRTReport")){
				return mapping.findForward("LRTReport");
			}
			if(param.equalsIgnoreCase("ATTReport")){
				return mapping.findForward("ATTReport");
			}
			if(param.equalsIgnoreCase("DMRReport")){
				return mapping.findForward("DMRReport");
			}

			if(param.equalsIgnoreCase("downloadLRT")){

				String from=request.getParameter("from");
				String to=request.getParameter("to");

				String filePath1 = request.getRealPath("/")+"Reportfile/ClientReport/";
				String filePath2="Live_Transaction_Report"+".xls";
				//String filePath1="D:/Work/";
				String filePath =filePath1+filePath2;

				String fileStatus=daoObj.downloadLRTReport(mdId,filePath,from,to);
				if(fileStatus.equalsIgnoreCase("NoRecord")){

					request.setAttribute("message","Data is Not Available.");
					return mapping.findForward("reportPage");
				}
				else if(fileStatus.equalsIgnoreCase("MoreRecord")){

					request.setAttribute("message","Transaction Size is More Than Allowed Limit.");
					return mapping.findForward("reportPage");
				}
				else if(fileStatus.equalsIgnoreCase("ERROR")){

					request.setAttribute("message","Process aborted due to Technical Failure.");
					return mapping.findForward("reportPage");
				}else if(fileStatus.equalsIgnoreCase("Success")){
					response.sendRedirect("Reportfile/ClientReport/"+filePath2);	
				}
			}
			if(param.equalsIgnoreCase("downloadATT")){

				String from=request.getParameter("from");
				String to=request.getParameter("to");

				String filePath1 = request.getRealPath("/")+"Reportfile/ClientReport/";
				String filePath2="Agent_Account_Statment"+".xls";
				//String filePath1="D:/Work/";
				String filePath =filePath1+filePath2;

				String fileStatus=daoObj.downloadATTReport(mdId,filePath,from,to);
				if(fileStatus.equalsIgnoreCase("NoRecord")){

					request.setAttribute("message","Data is Not Available.");
					return mapping.findForward("reportPage");
				}
				else if(fileStatus.equalsIgnoreCase("MoreRecord")){

					request.setAttribute("message","Transaction Size is More Than Allowed Limit.");
					return mapping.findForward("reportPage");
				}
				else if(fileStatus.equalsIgnoreCase("ERROR")){

					request.setAttribute("message","Process aborted due to Technical Failure.");
					return mapping.findForward("reportPage");
				}else if(fileStatus.equalsIgnoreCase("Success")){
					response.sendRedirect("Reportfile/ClientReport/"+filePath2);	
				}
			}
			if(param.equalsIgnoreCase("downloadDMR")){

				String from=request.getParameter("from");
				String to=request.getParameter("to");
				String senderId=request.getParameter("senderId");

				String filePath1 = request.getRealPath("/")+"Reportfile/ClientReport/";
				String filePath2="DMR_Account_Statment"+".xls";
				//String filePath1="D:/Work/";
				String filePath =filePath1+filePath2;

				String fileStatus=daoObj.downloadDMRReport(mdId,filePath,from,to,senderId);
				if(fileStatus.equalsIgnoreCase("NoRecord")){

					request.setAttribute("message","Data is Not Available.");
					return mapping.findForward("reportPage");
				}
				else if(fileStatus.equalsIgnoreCase("MoreRecord")){

					request.setAttribute("message","Transaction Size is More Than Allowed Limit.");
					return mapping.findForward("reportPage");
				}
				else if(fileStatus.equalsIgnoreCase("ERROR")){

					request.setAttribute("message","Process aborted due to Technical Failure.");
					return mapping.findForward("reportPage");
				}else if(fileStatus.equalsIgnoreCase("Success")){
					response.sendRedirect("Reportfile/ClientReport/"+filePath2);	
				}
			}

			if(param.equalsIgnoreCase("downloadDSAccountDetails")){

				String from=request.getParameter("from");
				String to=request.getParameter("to");

				String filePath1 = request.getRealPath("/")+"Reportfile/ClientReport/";
				String filePath2="Distributor_Account_Statement"+".xls";
				//String filePath1="D:/Work/";
				String filePath =filePath1+filePath2;
				String CompleteMDID=(String) ses.getAttribute("completeId");

				String fileStatus=daoObj.downloadDSReport(mdId,filePath,CompleteMDID,from,to);
				if(fileStatus.equalsIgnoreCase("NoRecord")){

					request.setAttribute("message","Data is Not Available.");
					return mapping.findForward("DSreportPage");
				}
				else if(fileStatus.equalsIgnoreCase("MoreRecord")){

					request.setAttribute("message","Transaction Size is More Than Allowed Limit.");
					return mapping.findForward("DSreportPage");
				}
				else if(fileStatus.equalsIgnoreCase("ERROR")){

					request.setAttribute("message","Process aborted due to Technical Failure.");
					return mapping.findForward("DSreportPage");
				}else if(fileStatus.equalsIgnoreCase("Success")){
					response.sendRedirect("Reportfile/ClientReport/"+filePath2);	
				}
			}

		}catch(Exception e){
			System.out.println("Excepton in ReportAction class");
			e.printStackTrace();
		}
		return null;	
	}
}
