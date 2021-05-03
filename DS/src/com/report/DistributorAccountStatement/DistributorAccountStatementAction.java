package com.report.DistributorAccountStatement;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.common.LogWriter;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.report.form.reportForm;



public class DistributorAccountStatementAction extends ActionSupport implements ModelDriven<Object>, ServletRequestAware,ServletResponseAware 
{

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	Logger logger = Logger.getLogger(DistributorAccountStatementAction.class);
	LogWriter log=new LogWriter();
	private  reportForm reportForm=new reportForm();
	public Object getModel(){return reportForm;}


	@SuppressWarnings("unchecked")
	public String execute() throws Exception { 
		try 
		{ 
			Map session = ActionContext.getContext().getSession();
			String param=reportForm.getParam();
			DistributorAccountStatementDao DistributorAccountStatementDao=new DistributorAccountStatementDao();
			log.print("param value in DistributorAccountStatementAction is "+param, logger);
			String userId=(String)session.get("distributorId");
			if(userId==null){
				String  message2="Your session has been expire ,relogin to continue";

				request.setAttribute("Loginmessage",message2);	    
				return "sessionexpired"; 
			}

			//------------------------Call to get top 20 Acount statement report-------------------------------------
			if(param.equals("distributorAccountStatement"))
			{

				int start=0;
				int end=0;
				int pagecount=0;
				int count=0;
				int count1=0;
				int pno=0;
				int check1AS=0;
				int check2AS=0;

				session.put("start",start);
				session.put("end",end);
				session.put("pagecount",pagecount);
				session.put("count",count);
				session.put("count1",count1);
				session.put("pno",pno);
				session.put("check1AS",check1AS);
				session.put("check2AS",check2AS);
				ArrayList<HashMap<String,Object>> DistributorAccountStatement=DistributorAccountStatementDao.getDistributorAccountStatementDetails(userId);


				if(DistributorAccountStatement.size()!=0)
				{

					HashMap data=(HashMap)DistributorAccountStatement.get(0);

					session.put("finalbalamt",data.get("FinalBalanceAmount").toString());
					int i=DistributorAccountStatement.size()-1;
					data=(HashMap)DistributorAccountStatement.get(i);

					session.put("balamtbefded",data.get("FinalBalanceAmount").toString());

					request.setAttribute("DistributorAccountStatement",DistributorAccountStatement); 
					return "AccountStatement";

				}
				else
				{
					String  message="No data to  display";
					request.setAttribute("message", message);
					return "AccountStatement";

				}
			}

			//----------------------------Call to get data according to user input  with specific time-------------------------
			if(param.equals("accountreport"))
			{ 		     
				System.out.println("in account reports");
				String type=reportForm.getType();
				session.put("type", type);
				String reportof=reportForm.getReportof();
				session.put("reportof", reportof);
				String toDt=reportForm.getToDate();
				session.put("toDate", toDt);
				String fromDt=reportForm.getFromDate();
				session.put("fromDate", fromDt);
				int start=0;
				int end=0;
				int pno=1;
				int check1AS=0;
				int check2AS=9;
				session.put("check1AS",check1AS);
				session.put("check2AS",check2AS);

				session.put("pno",pno);
				int  modcount=(Integer)reportForm.getCount1();


				ArrayList<HashMap<String,Object>>DistributorAccountReport=DistributorAccountStatementDao.getDistributorAccountStatementReport(toDt,fromDt,userId,reportof,type,modcount,start,end,session);


				if(DistributorAccountReport.size()!=0)
				{


					HashMap data=(HashMap)DistributorAccountReport.get(0);
					session.put("finalbalamt",data.get("FinalBalanceAmount").toString());
					int i=DistributorAccountReport.size()-1;
					data=(HashMap)DistributorAccountReport.get(i);
					session.put("balamtbefded",data.get("FinalBalanceAmount").toString());
					request.setAttribute("DistributorAccountStatement",DistributorAccountReport); 
					return "AccountStatement";
				}
				else
				{
					String  message="<html><body><table><tr><td><font color=\"red\" size=\"2\">No data to  display</td></tr></table></body></html>";
					request.setAttribute("message", message);
					return "AccountStatement";

				}
			}


			//---------------------------------------------Call for page number------------------		  

			if(param.equalsIgnoreCase("pageno"))
			{


				String type=(String)session.get("type");
				String reportof=(String)session.get("reportof");
				String toDt=(String) session.get("toDate");
				String fromDt=(String) session.get("fromDate");
				int end=0;
				int start=0;
				int pno=reportForm.getPgno();
				session.put("pno",pno);
				end=pno*50;
				start=end-50;
				int  modcount=0;
				int check1AS=(Integer)session.get("check1AS");
				int check2AS=(Integer)session.get("check2AS");
				int pagecheck=pno-1;
				if(check2AS==pagecheck)
				{

					check1AS=check1AS-2;
					check1AS=check1AS+10;

					check2AS=check2AS-1;
					check2AS=check2AS+10;
				}


				session.put("check1AS",check1AS);
				session.put("check2AS",check2AS);

				ArrayList<HashMap<String,Object>>DistributorAccountReport=DistributorAccountStatementDao.getDistributorAccountStatementReport(toDt,fromDt,userId,reportof,type,modcount,start,end,session);

				if(DistributorAccountReport.size()!=0)
				{


					HashMap data=(HashMap)DistributorAccountReport.get(0);
					session.put("finalbalamt",data.get("FinalBalanceAmount").toString());
					int i=DistributorAccountReport.size()-1;
					data=(HashMap)DistributorAccountReport.get(i);

					session.put("balamtbefded",data.get("FinalBalanceAmount").toString());
					request.setAttribute("DistributorAccountStatement",DistributorAccountReport); 
					return "AccountStatement";


				}
				else
				{
					String  message="<html><body><table><tr><td><font color=\"red\" size=\"2\">No data to  display</td></tr></table></body></html>";
					request.setAttribute("message", message);
					return "AccountStatement";

				}


			}	  


			//-------------------------------------NEXT--------------------------------------------------
			if(param.equalsIgnoreCase("next"))
			{


				String type=(String)session.get("type");
				String reportof=(String)session.get("reportof");
				String toDt=(String) session.get("toDate");
				String fromDt=(String) session.get("fromDate");
				String  start1=(String)request.getParameter("a");
				System.out.println("start1 is"+start1);
				String end1=(String)request.getParameter("b");
				int pgno=reportForm.getPno();
				int pno=pgno+1;
				session.put("pno",pno);
				int start=Integer.parseInt( start1);
				int end=Integer.parseInt(end1);
				start=end+1;
				int check1AS=(Integer)session.get("check1AS");
				int check2AS=(Integer)session.get("check2AS");
				int pagecheck=pno-1;

				if(check2AS==pagecheck)
				{
					check1AS=check1AS-2;
					check1AS=check1AS+10;
					check2AS=check2AS-1;
					check2AS=check2AS+10;
				}


				session.put("check1AS",check1AS);
				session.put("check2AS",check2AS);

				int modcount=reportForm.getCount1();


				ArrayList<HashMap<String,Object>>DistributorAccountReport=DistributorAccountStatementDao.getDistributorAccountStatementReport(toDt,fromDt,userId,reportof,type,modcount,start,end,session);

				if(DistributorAccountReport.size()!=0)
				{


					HashMap data=(HashMap)DistributorAccountReport.get(0);
					session.put("finalbalamt",data.get("FinalBalanceAmount").toString());
					int i=DistributorAccountReport.size()-1;
					data=(HashMap)DistributorAccountReport.get(i);
					session.put("balamtbefded",data.get("FinalBalanceAmount").toString());
					request.setAttribute("DistributorAccountStatement",DistributorAccountReport); 
					return "AccountStatement";

				}
				else
				{
					String  message="<html><body><table><tr><td><font color=\"red\" size=\"2\">No data to  display</td></tr></table></body></html>";
					request.setAttribute("message", message);
					return "AccountStatement";

				}

			}

			//-----------------------------------PREVIOUS----------------------------------------------  

			if(param.equalsIgnoreCase("previous"))
			{

				String type=(String)session.get("type");
				String reportof=(String)session.get("reportof");
				String toDt=(String) session.get("toDate");
				String fromDt=(String) session.get("fromDate");
				String  start1=(String)request.getParameter("a");

				String end1=(String)request.getParameter("b");
				int pgno=reportForm.getPno();
				int start=Integer.parseInt( start1);
				int end=Integer.parseInt(end1);
				int pno=pgno-1;
				session.put("pno",pno);
				end=start;
				start=start-50;
				int check1AS=(Integer)session.get("check1AS");
				int check2AS=(Integer)session.get("check2AS");
				if(check1AS==pgno)
				{
					check1AS=check1AS-10;
					check2AS=check2AS-10;
				}


				session.put("check1AS",check1AS);
				session.put("check2AS",check2AS);
				int modcount=reportForm.getCount1();

				ArrayList<HashMap<String,Object>>DistributorAccountReport=DistributorAccountStatementDao.getDistributorAccountStatementReport(toDt,fromDt,userId,reportof,type,modcount,start,end,session);

				if(DistributorAccountReport.size()!=0)
				{


					HashMap data=(HashMap)DistributorAccountReport.get(0);
					session.put("finalbalamt",data.get("FinalBalanceAmount").toString());
					int i=DistributorAccountReport.size()-1;
					data=(HashMap)DistributorAccountReport.get(i);
					session.put("balamtbefded",data.get("FinalBalanceAmount").toString());
					request.setAttribute("DistributorAccountStatement",DistributorAccountReport); 
					return "AccountStatement";

				}
				else
				{
					String  message="No data to  display";
					request.setAttribute("message", message);
					return "AccountStatement";

				}

			}



			//-------------------------------------DOWNLOAD-------------------------
			if(param.equalsIgnoreCase("download"))
			{

				System.out.println("in side download------------------");
				String type=reportForm.getType();
				String reportof=reportForm.getReportof();
				String toDt=reportForm.getToDate();
				String fromDt=reportForm.getFromDate();
				String distributorInitial=(String)session.get("distributorInitial");
				String filePath1 = request.getRealPath("/")+"Reportfile/account_statement/";
				String filePath2=distributorInitial+userId+".xls";
				String filePath =filePath1+filePath2;
				File f = new File(filePath);
				if (!f.exists())
				{

					String  fileStatus=DistributorAccountStatementDao.downloadaccountStatement(filePath,toDt,fromDt,userId,reportof,type,distributorInitial);
					String filepathdownload="account_statement/"+filePath2;
					request.setAttribute("fileStatus",filepathdownload);
					

				}else{

					boolean success = f.delete();

					if (!success)
					{
						String filepathdownload="account_statement/"+filePath2;
						request.setAttribute("fileStatus",filepathdownload);
						

					}else{

						String  fileStatus=DistributorAccountStatementDao.downloadaccountStatement(filePath,toDt,fromDt,userId,reportof,type,distributorInitial);
						String filepathdownload="account_statement/"+filePath2;
						request.setAttribute("fileStatus",filepathdownload);

					}

				}
				
				ArrayList<HashMap<String,Object>>DistributorAccountStatement=DistributorAccountStatementDao.getDistributorAccountStatementDetails(userId);


				if(DistributorAccountStatement.size()!=0)
				{

					HashMap data=(HashMap)DistributorAccountStatement.get(0);
					session.put("finalbalamt",data.get("FinalBalanceAmount").toString());
					int i=DistributorAccountStatement.size()-1;
					data=(HashMap)DistributorAccountStatement.get(i);

					session.put("balamtbefded",data.get("FinalBalanceAmount").toString());

					request.setAttribute("DistributorAccountStatement",DistributorAccountStatement); 

				}
				else
				{
					String  message="No data to  display";
					request.setAttribute("message", message);

				}
				
				return  "AccountStatement";

			}

		}catch(Exception ex){
			ex.printStackTrace();
			request.setAttribute("message", "Due to some technical issue we are unable to proceed your request");
			return "AccountStatement";
		}
		return "AccountStatement";

	}

	@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
	}

	@Override
	public void setServletResponse(HttpServletResponse arg0) {

	}
}