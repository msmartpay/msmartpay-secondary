package com.accountstatement;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zip.ZipCreator;

public class AccountStatementAction extends Action{

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Connection con = null;
		try
		{
			HttpSession session=request.getSession(true);
			String mdId=(String)session.getAttribute("mdId");		
			System.out.println("mdId is==============================="+mdId);
			if(mdId==null){
				session.setAttribute("message","Your Login session has Expired. Please Login to Continue.");
				return mapping.findForward("sessionexp");
			}   

			String param=request.getParameter("param");
			System.out.println("param================================is"+param);

			if(param.equals("accountStatement"))

			{
				System.out.println("we are inside AccountStatment class and param is accountStatement ");
				Vector vector=new Vector();

				vector=AccountStatementBusinessDelegator.getAccStatement(con,mdId);
				System.out.println("vector size is ::"+vector.size());
				try{
					int size = vector.size();

					if(size <= 0)
					{
						request.setAttribute("message","Data not available");
						return mapping.findForward("MyAccountStatement");
					}
				}
				catch(ArrayIndexOutOfBoundsException aioe)
				{
					System.out.println("---exception in vector size ------");
					aioe.printStackTrace();
				}
				HashMap<?, ?> hm=(HashMap<?, ?>)vector.elementAt(0);

				String openBal=(String)hm.get("previousBalance");

				session.setAttribute("previousBalance",openBal);
				int i=vector.size()-1;


				hm=(HashMap)vector.elementAt(i);
				String closingBal=(String)hm.get("finalBalance");
				session.setAttribute("finalBalance",closingBal);
				request.setAttribute("vector",vector);

				return mapping.findForward("MyAccountStatement");
			}
			if(param.equals("accountReport"))
			{
				System.out.println("accountReport param ");
				String type=(String)request.getParameter("type");				
				Vector vector=new Vector();
				//System.out.println("value of report of================"+reportof);
				String toDt=request.getParameter("toDate");
				String fromDt=request.getParameter("fromDate");
				session.setAttribute("fromDate",fromDt);
				session.setAttribute("toDate",toDt);

				session.setAttribute("type",type);
				int start=0;
				int end=0;
				int pno=1;
				session.setAttribute("pno",pno);
				String modcount=(String)request.getParameter("count1");
				if(modcount==null)
				{
					modcount="0";
				}
				System.out.println(mdId);
				vector=AccountStatementBusinessDelegator.accountStatementReport(con,mdId,toDt,fromDt,type,modcount,start,end,session);
				//System.out.println("vector is"+vector);
				if(!vector.isEmpty())
				{				
					HashMap hm=(HashMap)vector.elementAt(0);

					String openBal=(String)hm.get("previousBalance");
					session.setAttribute("previousBalance",openBal);
					int i=vector.size()-1;


					hm=(HashMap)vector.elementAt(i);
					String closingBal=(String)hm.get("finalBalance");
					session.setAttribute("finalBalance",closingBal);
					request.setAttribute("vector",vector);
					return mapping.findForward("MyAccountStatement");

				}
				else
				{
					//System.out.println("vector is"+vector);
					request.setAttribute("vector",vector);
					return mapping.findForward("MyAccountStatement");
				}

				// System.out.println("vector is"+vector);

			}
			/*if(param.equalsIgnoreCase("next"))
			{				
				System.out.println("");
				System.out.println("inside param" +param);
				String type=(String)session.getAttribute("type");
				//System.out.println("value of type of================"+type);

				Vector vector=new Vector();

				String toDt=(String) session.getAttribute("toDate");
				String fromDt=(String) session.getAttribute("fromDate");

				System.out.println(" toDt is "+toDt);
				System.out.println("from dtae is "+fromDt);

				session.setAttribute("fromdate",fromDt);
				session.setAttribute("todate",toDt);
				session.setAttribute("type",type);
				String  start1=(String)request.getParameter("a");

				String end1=(String)request.getParameter("b");
				String pageno=(String)request.getParameter("pno");
				int pgno=Integer.parseInt(pageno);
				int pno=pgno+1;
				System.out.println("page no is" +pno);
				session.setAttribute("pno",pno);
				int start=Integer.parseInt( start1);
				int end=Integer.parseInt(end1);
				start=end+1;
				String modcount=(String)request.getParameter("count1");
				System.out.println("inside modcount" +modcount);
				if(modcount==null)
				{
					modcount="0";
				}


				System.out.println("we are going in method md id is ::::::::::::::::"+mdId);
				vector=AccountStatementBusinessDelegator.accountStatementReport(con,mdId,toDt,fromDt,type,modcount,start,end,session);


				if(!vector.isEmpty())
				{					
					HashMap hm=(HashMap)vector.elementAt(0);

					String openBal=(String)hm.get("previousBalance");
					session.setAttribute("previousBalance",openBal);
					int i=vector.size()-1;


					hm=(HashMap)vector.elementAt(i);
					String closingBal=(String)hm.get("finalBalance");
					session.setAttribute("finalBalance",closingBal);
					request.setAttribute("vector",vector);
					return mapping.findForward("MyAccountStatement");

				}
				else
				{			
					request.setAttribute("vector",vector);
					return mapping.findForward("MyAccountStatement");
				}


			}*/


			if (param.equalsIgnoreCase("download")){

				String from=request.getParameter("from");
				String to=request.getParameter("to");

				System.out.println("FROM DATE ::"+from);
				System.out.println("FROM TO ::"+to);				

				Date date = new SimpleDateFormat("MM/dd/yyyy").parse(from);
				String dateStringfrom = new SimpleDateFormat("yyyy-MM-dd").format(date);
				System.out.println(dateStringfrom);

				Date dateto = new SimpleDateFormat("MM/dd/yyyy").parse(to);
				String dateStringto = new SimpleDateFormat("yyyy-MM-dd").format(dateto);
				System.out.println(dateStringto);

				/*			SimpleDateFormat formatterB = new SimpleDateFormat("yyyy-MM-dd");
			String Fromdate = formatterB.format(from);
			String Todate = formatterB.format(to);

			System.out.println("Fromdate"+Fromdate);
			System.out.println("Todate"+Todate);*/

				String filePath1 = request.getRealPath("/")+"Reportfile/ClientReport/";
				String filePath2="MFCAccountStatement"+".xls";
				String path="/usr/Trandata/";
				//	String path="E:/Work/";
				String zipFilePath2="MFCAccountStatement"+".rar";
				String filePath =path+filePath2;
				String zipFilePath=filePath1+zipFilePath2;

				String fileStatus=AccountStatementBusinessDelegator.getAccountStatement(con,filePath,mdId,session,dateStringfrom,dateStringto);
				if(fileStatus.equalsIgnoreCase("NoRecord")){

					request.setAttribute("message","Data is Not Available.");
					return mapping.findForward("MyAccountStatement");
				}
				else if(fileStatus.equalsIgnoreCase("MoreRecord")){

					request.setAttribute("message","Transaction Size is More Than Allowed Limit.");
					return mapping.findForward("MyAccountStatement");
				}
				else if(fileStatus.equalsIgnoreCase("ERROR")){

					request.setAttribute("message","Process aborted due to Technical Failure.");
					return mapping.findForward("MyAccountStatement");
				}else if(fileStatus.equalsIgnoreCase("Success")){
					ZipCreator zipObject=new ZipCreator();
					String result=zipObject.createRarFile(filePath, zipFilePath);
					if(result.equals("error")){

						request.setAttribute("message","Transaction Size is More Than Allowed Limit.");
						return mapping.findForward("MyAccountStatement");
					}
					response.sendRedirect("Reportfile/ClientReport/"+zipFilePath2);	
				}		
			}
			if(param.equalsIgnoreCase("transactionid"))
			{
				String transid=request.getParameter("tran_id");
				String service=request.getParameter("service");
				System.out.println("service :"+service);
				if(service.equalsIgnoreCase("DS TB-Transfer"))
				{
					HashMap ham=AccountStatementBusinessDelegator.getMDtoDistDetails(con,transid);
					session.setAttribute("mdToDistInfo",ham);
					return mapping.findForward("disttradebal");
				}
				if((service.equalsIgnoreCase("MD TB-Taken")) ||  (service.equalsIgnoreCase("AdmintoMD")))
				{
					HashMap ham=AccountStatementBusinessDelegator.getAdmintoMDDetails(con,transid);
					session.setAttribute("adminToMdInfo",ham);
					return mapping.findForward("tradebal");
				}
				if((service.equalsIgnoreCase("accountadjustment")) || (service.equalsIgnoreCase("Account Adjustment-MD")) || (service.equalsIgnoreCase("Account Adjustment")) || (service.equalsIgnoreCase("Commission")) || (service.equalsIgnoreCase("TDS"))
						|| (service.equalsIgnoreCase("MD TB-Push")))
				{
					HashMap ham=AccountStatementBusinessDelegator.getDetailAccountAdjustment(con,transid,mdId);
					session.setAttribute("accountAdjustmentInfo",ham);
					return mapping.findForward("accountadjustment");
				}
			}

		}
		catch(Exception e){
			System.out.println(e);	
		}
		return null;
	}

}
