package com.report;

import java.io.File;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class DistributorReportAction extends Action{

	public ActionForward execute(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
					throws Exception {

		HttpSession session=request.getSession(true);
		Connection con=null;

		String mdId=(String)session.getAttribute("mdId");

		if(mdId==null){
			session.setAttribute("message","Your login session has expired, Please relogin to continue");
			return mapping.findForward("sessionexp");
		}   



		String param=request.getParameter("param");

		if(param.equalsIgnoreCase("getReport")){
			return mapping.findForward("reportPage");
		}

		if(param.equalsIgnoreCase("downloadATT")){
			agentReportDao rDao = new agentReportDao();

			String filePath1 = getServlet().getServletContext().getRealPath("/")+"transferreport/agenttran/";

			String filePath2=mdId+".xls";

			String filePath =filePath1+filePath2;

			File f = new File(filePath);

			// Make sure the file or directory exists and isn't write protected

			if (!f.exists()) {

				//System.out.println("file not exit with path"+filePath);

				String  Status=rDao.downloadATTReport(con,mdId,filePath);

				if(Status.equals("DownloadNotAllowed")){
					request.setAttribute("tranDownloadmessage","The Transaction you are trying to download facing some technical problem.");
					return mapping.findForward("reportPage");
				}
				response.sendRedirect("transferreport/agenttran/"+filePath2);

			}
			else {

				boolean success = f.delete();

				if (!success){	

					String filepathdownload="transferreport/agenttran/"+filePath2;
					session.setAttribute("fileStatus",filepathdownload);
					return(mapping.findForward("reportPage"));

				}

				else {        
					String  Status=rDao.downloadATTReport(con,mdId,filePath);

					if(Status.equals("DownloadNotAllowed")){
						request.setAttribute("tranDownloadmessage","Download Failed.");
						return mapping.findForward("reportPage");
					}
					response.sendRedirect("transferreport/agenttran/"+filePath2);
				}

			}
		}

		agentReportDao rDao = new agentReportDao();

		String filePath1 = getServlet().getServletContext().getRealPath("/")+"transferreport/agenttran/";

		String filePath2=mdId+".xls";

		String filePath =filePath1+filePath2;

		File f = new File(filePath);

		// Make sure the file or directory exists and isn't write protected

		if (!f.exists()) {

			//System.out.println("file not exit with path"+filePath);

			String  Status=rDao.downloadLRTReport(con,mdId,filePath);

			if(Status.equals("DownloadNotAllowed")){
				request.setAttribute("tranDownloadmessage","The Report you are trying to Download facing some Technical Problem.");
				return mapping.findForward("reportPage");
			}
			response.sendRedirect("transferreport/agenttran/"+filePath2);

		}
		else {

			boolean success = f.delete();

			if (!success){	

				String filepathdownload="transferreport/agenttran/"+filePath2;
				session.setAttribute("fileStatus",filepathdownload);
				return(mapping.findForward("reportPage"));

			}

			else {        
				String  Status=rDao.downloadLRTReport(con,mdId,filePath);

				if(Status.equals("DownloadNotAllowed")){
					request.setAttribute("tranDownloadmessage","The Report you are trying to Download facing some Technical Problem.");
					return mapping.findForward("reportPage");
				}
				response.sendRedirect("transferreport/agenttran/"+filePath2);
			}

		}

		return null;

	}

}
