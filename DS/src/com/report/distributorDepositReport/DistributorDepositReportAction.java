/**
 * 
 */
package com.report.distributorDepositReport;

/**
 * @author amit
 *
 */


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


@SuppressWarnings("serial")
public class DistributorDepositReportAction extends ActionSupport implements ModelDriven<Object>,ServletRequestAware,ServletResponseAware 
{
	 

	 private HttpServletRequest request;
     Logger logger = Logger.getLogger(DistributorDepositReportAction.class);
	 LogWriter log=new LogWriter();
	 private  reportForm reportForm=new reportForm();
	 public Object getModel(){return reportForm;}
	 @SuppressWarnings("unchecked")
	  public String execute() throws Exception { 
	  try 
	  { 
		  Map session = ActionContext.getContext().getSession();
		  String param=(String)request.getParameter("param");
		  DistributorDepositReportDao DistributorDepositReportDao=new DistributorDepositReportDao();
		  log.print("param value in DistributorDepositReport is "+param, logger);
		  String userId=(String)session.get("distributorId");
		  if(userId==null){
			  String  message2="<html><body><table><tr><td><font color=\"red\" size=\"2\">Your session has been expire ,relogin to continue</td></tr></table></body></html>";

			  request.setAttribute("Loginmessage",message2);	          return "sessionexpired"; 
		  }
		  

//---------------------Call to get Top 20 transaction--------------------------------------------------
		  if(param.equals("distributorDepositSearch"))
		     {
			  
			    int start=0;
				int end=0;
				int pagecount=0;
				int count=0;
				int count1=0;
				int pno=0;
				int check1DD=0;
				int check2DD=0;
				session.put("check1DD",check1DD);
				session.put("check2DD",check2DD);
				
				session.put("start",start);
				session.put("end",end);
				session.put("pagecount",pagecount);
				session.put("count",count);
				session.put("count1",count1);
				session.put("pno",pno);
			  ArrayList<HashMap<String,Object>>DistributorDepositReport=DistributorDepositReportDao.getDistributorDepositReportDetails(userId);
			  
			  if(DistributorDepositReport.size()!=0)
			  {
				  
				 request.setAttribute("DistributorDepositReport",DistributorDepositReport); 
				 return "distributorDepositReport";
				
			  }
			  else
			  {
					request.setAttribute("message","No data to display"); 
					return "distributorDepositReport";
				  
			  }
				
			  
			  
			  }
				  
		
		  
		  
		  
//--------------------------------call to get data with given date period--------------------------
		  if(param.equals("distributorDepositReport"))
		     {
			  
			  
			    String toDt=reportForm.getToDate();
		        session.put("toDate", toDt);
          	    
			    String fromDt=reportForm.getFromDate();
			    session.put("fromDate", fromDt);
				
			  
			  
			  
				int start=0;
				int end=0;
            	int pno=1;
            	int check1DD=0;
				int check2DD=9;
				session.put("check1DD",check1DD);
				session.put("check2DD",check2DD);
				session.put("pno",pno);
				int  modcount=(Integer)reportForm.getCount1();
				System.out.println("modcount---"+modcount);
				
				  ArrayList<HashMap<String,Object>>DistributorDepositReport=DistributorDepositReportDao.getDistributorDepositReportSearch(toDt,fromDt,userId,modcount,start,end,session);
			  
			  if(DistributorDepositReport.size()!=0)
			  {
				  System.out.println("in DistributorDepositReport");
				 request.setAttribute("DistributorDepositReport",DistributorDepositReport); 
				 return "distributorDepositReport";
				
			  }
			  else
			  {
				  String  message="<html><body><table><tr><td><font color=\"red\" size=\"2\">No data to  display</td></tr></table></body></html>";
                  request.setAttribute("message", message);
				   					return "distributorDepositReport";
				  
			  }
				
			  
			  
			  }
		  
		  
		  
		  
//----------------------------------------		  PageNO-----------------
		  if(param.equalsIgnoreCase("pageno"))
		   {
      
             System.out.println("<<<<<<<<<<<<<<<<<<<in side  pageno>>>>>>>>>>>>>>>>>. ");
		     String toDt=(String) session.get("toDate");
        	 System.out.println("value of toDt of================"+toDt);
		     String fromDt=(String) session.get("fromDate");
			 System.out.println("value of fromDt of================"+fromDt);
			 int end=0;
			 int start=0;
			 int pno=reportForm.getPgno();
			 session.put("pno",pno);
			 end=pno*50;
			 start=end-50;
			 int  modcount=0;
			 
			 int check1DD=(Integer)session.get("check1DD");
		      int check2DD=(Integer)session.get("check2DD");
             int pagecheck=pno-1;
		      if(check2DD==pno)
		      {
		    	
		    	  check1DD=check1DD-2;
		    	  check1DD=check1DD+10;

		    	  check2DD=check2DD-1;
		    	  check2DD=check2DD+10;
		      }
		    
			
			 session.put("check1DD",check1DD);
			 session.put("check2DD",check2DD);
			 
			 
		    ArrayList<HashMap<String,Object>>DistributorDepositReport=DistributorDepositReportDao.getDistributorDepositReportSearch(toDt,fromDt,userId,modcount,start,end,session);
			  
			  if(DistributorDepositReport.size()!=0)
			  {
				  System.out.println("in DistributorDepositReport");
				 request.setAttribute("DistributorDepositReport",DistributorDepositReport); 
				 return "distributorDepositReport";
				
			  }
			  else
			  {
				  String  message="<html><body><table><tr><td><font color=\"red\" size=\"2\">No data to  display</td></tr></table></body></html>";
                  request.setAttribute("message", message);
				   					return "distributorDepositReport";
				  
			  }

		   }	
		  
		  
	//-----------------------------------------Next-----------------------------------------------------
		  if(param.equalsIgnoreCase("next"))
		   {
         
	             System.out.println("<<<<<<<<<<<<<<<<<<<in side  next>>>>>>>>>>>>>>>>>. ");

		     String toDt=(String) session.get("toDate");
        	 System.out.println("value of toDt of================"+toDt);
		     String fromDt=(String) session.get("fromDate");
			 System.out.println("value of fromDt of================"+fromDt);
			 String  start1=(String)request.getParameter("a");
			 System.out.println("start1 is"+start1);
			 String end1=(String)request.getParameter("b");
			 System.out.println("end1 is"+end1);
			 int pgno=reportForm.getPno();
			 System.out.println("pgno----"+pgno);
			 int pno=pgno+1;
			 session.put("pno",pno);
			 int start=Integer.parseInt( start1);
			 int end=Integer.parseInt(end1);
    	     start=end+1;
			  int modcount=reportForm.getCount1();
			  int check1DD=(Integer)session.get("check1DD");
		      int check2DD=(Integer)session.get("check2DD");
		    
		      if(check2DD==pgno)
		      {
		    	  check1DD=check1DD-2;
		    	  check1DD=check1DD+10;

		    	  check2DD=check2DD-1;
		    	  check2DD=check2DD+10;
		      }
		      
		      
			
			 session.put("check1AD",check1DD);
			 session.put("check2AD",check2DD);
			
			 
			   ArrayList<HashMap<String,Object>>DistributorDepositReport=DistributorDepositReportDao.getDistributorDepositReportSearch(toDt,fromDt,userId,modcount,start,end,session);
				  
				  if(DistributorDepositReport.size()!=0)
				  {
					  System.out.println("in DistributorDepositReport");
					 request.setAttribute("DistributorDepositReport",DistributorDepositReport); 
					 return "distributorDepositReport";
					
				  }
				  else
				  {
					  String  message="<html><body><table><tr><td><font color=\"red\" size=\"2\">No data to  display</td></tr></table></body></html>";
	                     request.setAttribute("message", message);
					   						return "distributorDepositReport";
					  
				  }

		   }
		  
//---------------------------------------Previous-----------------------------------
		  if(param.equalsIgnoreCase("previous"))
		   {
	             System.out.println("<<<<<<<<<<<<<<<<<<<in side  previous>>>>>>>>>>>>>>>>>. ");

	         String toDt=(String) session.get("toDate");
        	 System.out.println("value of toDt of================"+toDt);
		     String fromDt=(String) session.get("fromDate");
			 System.out.println("value of fromDt of================"+fromDt);
			 String  start1=(String)request.getParameter("a");
			 System.out.println("start1 is"+start1);
			 String end1=(String)request.getParameter("b");
			 int pgno=reportForm.getPno();
			 int start=Integer.parseInt( start1);
			 int end=Integer.parseInt(end1);
			 int pno=pgno-1;
			 session.put("pno",pno);
    	     end=start;
			 start=start-50;
			 int modcount=reportForm.getCount1();
			 int check1DD=(Integer)session.get("check1DD");
		      int check2DD=(Integer)session.get("check2DD");
		      if(check1DD==pgno)
		      {
		    	  check1DD=check1DD-10;
		         check2DD=check2DD-10;
		      }
		      
			
			 session.put("check1DD",check1DD);
			 session.put("check2DD",check2DD);
			 ArrayList<HashMap<String,Object>>DistributorDepositReport=DistributorDepositReportDao.getDistributorDepositReportSearch(toDt,fromDt,userId,modcount,start,end,session);
			  
			  if(DistributorDepositReport.size()!=0)
			  {
				  System.out.println("in DistributorDepositReport");
				 request.setAttribute("DistributorDepositReport",DistributorDepositReport); 
				 return "distributorDepositReport";
				
			  }
			  else
			  {
				  String  message="<html><body><table><tr><td><font color=\"red\" size=\"2\">No data to  display</td></tr></table></body></html>";
                  request.setAttribute("message", message);
				   					return "distributorDepositReport";
				  
			  }
		   }
		  
		  
		  
//-----------------------------------//-------------------------------------DOWNLOAD-------------------------
			if(param.equalsIgnoreCase("download"))
			   {
			     String toDt=(String) session.get("toDate");
	         	 System.out.println("value of toDt of================"+toDt);
			     String fromDt=(String) session.get("fromDate");
				 System.out.println("value of fromDt of================"+fromDt);
				 System.out.println("value of toDt of================"+toDt);
			   
				 String distributorInitial=(String)session.get("distributorInitial");
				 System.out.println("value of distributorInitial of================"+distributorInitial);
                 String filePath1 = request.getRealPath("/")+"Reportfile/distributorDeposit/";
		         String filePath2=distributorInitial+userId+".xls";
		 
  	             String filePath =filePath1+filePath2;
			     System.out.println("Server file path is   >>>>>"+filePath);
                 File f = new File(filePath);

               if (!f.exists())
               {
	 
		        System.out.println("file not exit with path"+filePath);
                String  fileStatus=DistributorDepositReportDao.downloadDepositReport(filePath,toDt,fromDt,userId,distributorInitial);
                System.out.println("Filepath is"+fileStatus);
                String filepathdownload="distributorDeposit/"+filePath2;
                session.put("fileStatus",filepathdownload);
			    return  "successdownload";

	                }
	                  else {

                   boolean success = f.delete();

	                     if (!success)
	                     {
	                    	 String filepathdownload="distributorDeposit/"+filePath2;
                             session.put("fileStatus",filepathdownload);
			                 return "successdownload";

                       }
     
		                   else 
		                   {

		                       String  fileStatus=DistributorDepositReportDao.downloadDepositReport(filePath,toDt,fromDt,userId,distributorInitial);
                               String filepathdownload="distributorDeposit/"+filePath2;
                               session.put("fileStatus",filepathdownload);
			                   return "successdownload";
    
		                    }

                        }





					}		  
		  
	 }
	  
	      catch(Exception ex){
		   ex.printStackTrace();
		   request.setAttribute("message", "Due to some technical issue we are unable to proceed your request");
		   return "error";
	         }
	       return "error";
     
	}
	
	@Override
	 public void setServletRequest(HttpServletRequest httpServletRequest) {
       this.request = httpServletRequest;
   }

	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		
	}
}