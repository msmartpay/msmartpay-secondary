package com.report.agentDepositReport;

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

public class AgentDepositReportAction extends ActionSupport implements  ModelDriven<Object>,ServletRequestAware,ServletResponseAware 
{
	 
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
     Logger logger = Logger.getLogger(AgentDepositReportAction.class);
	 LogWriter log=new LogWriter();
	 private  reportForm reportForm=new reportForm();
	 public Object getModel(){return reportForm;}
	
	 @SuppressWarnings("unchecked")
	  public String execute() throws Exception { 
	  try 
	  { 
		  Map session = ActionContext.getContext().getSession();
		  String param=reportForm.getParam();
		  AgentDepositReportDao AgentDepositReportDao=new AgentDepositReportDao();
		  log.print("param value in AgentDepositReportActin is "+param, logger);
		  String userId=(String)session.get("distributorId");
		  if(userId==null)
		  {
			  String  message2="<html><body><table><tr><td><font color=\"red\" size=\"2\">Your session has been expire ,relogin to continue</td></tr></table></body></html>";

			  request.setAttribute("Loginmessage",message2);
	    	          return "sessionexpired"; 
		  }
		  
//-----------------------Call to get top 20 transaction-------------------------------
		  if(param.equals("AgentDeposit20"))
		     {
			  
			    int start=0;
				int end=0;
				int pagecount=0;
				int count=0;
				int count1=0;
				int pno=0;
				int check1AD=0;
				int check2AD=0;
				session.put("check1AD",check1AD);
				session.put("check2AD",check2AD);
				session.put("start",start);
				session.put("end",end);
				session.put("pagecount",pagecount);
				session.put("count",count);
				session.put("count1",count1);
				session.put("pno",pno);
		
				ArrayList<HashMap<String,Object>>AgentDepositReport=AgentDepositReportDao.getAgentDepositReportDetails(userId);
			  
			  
			  if(AgentDepositReport.size()!=0)
			  {
				  
				 request.setAttribute("AgentDepositReport",AgentDepositReport); 
				 return "AgentDepositReport";
				
			  }
			  else
			  {
					 String  message="<html><body><table><tr><td><font color=\"red\" size=\"2\">No data to  display</td></tr></table></body></html>";
                     request.setAttribute("message", message);
				   					return "AgentDepositReport";
				  
			  }
			  
			  
			  }


		  
		//--------------------------------call to get data with given date period--------------------------
		  if(param.equals("AgentDepositReport"))
		     {
			   
			    String toDt=reportForm.getToDate();
		        session.put("toDate", toDt);
			    String fromDt=reportForm.getFromDate();
			    session.put("fromDate", fromDt);
				String reportBy=reportForm.getReportBy();
				session.put("reportBy", reportBy);
				String enterAgentId=reportForm.getEnterAgentId();
				session.put("enterAgentId",enterAgentId);
		  
			    int start=0;
				int end=0;
            	int pno=1;
            	int check1AD=0;
				int check2AD=9;
				session.put("check1AD",check1AD);
				session.put("check2AD",check2AD);
				session.put("pno",pno);
				int  modcount=(Integer)reportForm.getCount1();
				String agentid="";
				
			  if(reportBy.equalsIgnoreCase("ById"))
			  {
				 
				  HashMap<String,Object> AgentVerified=AgentDepositReportDao.getAgentVerified(enterAgentId);
				  agentid=(String)AgentVerified.get("agentId");

				 session.put("agentid",agentid);
				 String Status=(String)AgentVerified.get("status");
				 if(Status.equalsIgnoreCase("Noexist"))
				 {
					 
					 String  message="<html><body><table><tr><td><font color=\"red\" size=\"2\">AgentId does not exist</td></tr></table></body></html>";

					 request.setAttribute("message",message);
					 return "AgentDepositReport";
				 }
				 
				 
			  }
			    ArrayList<HashMap<String,Object>>AgentDepositReport=AgentDepositReportDao.getAgentDepositReportSearch(toDt,fromDt,userId,modcount,start,end,agentid,session,reportBy);
			  
			  if(AgentDepositReport.size()!=0)
			  {
				  request.setAttribute("AgentDepositReport",AgentDepositReport); 
				  return "AgentDepositReport";
				
			  }
			  else
			  {
					 String  message="<html><body><table><tr><td><font color=\"red\" size=\"2\">No data to  display</td></tr></table></body></html>";
                     request.setAttribute("message", message);
				   
					return "AgentDepositReport";
				  
			  }
				
			  
			  
			  }
		  
	
		  
		//----------------------------------------		  PageNO-----------------
				  if(param.equalsIgnoreCase("pageno"))
				   {
		             String toDt=(String) session.get("toDate");
				     String fromDt=(String) session.get("fromDate");
					 String reportBy=(String) session.get("reportBy");
				     String agentid=(String) session.get("agentid");
						
					 int end=0;
					 int start=0;
					 int pno=reportForm.getPgno();
					 session.put("pno",pno);
					 end=pno*50;
					 start=end-50;
					 int  modcount=0;
					 
					  int check1AD=(Integer)session.get("check1AD");
					  
				      int check2AD=(Integer)session.get("check2AD");
				      
		              int pagecheck=pno-1;
				      if(check2AD==pagecheck)
				      {
				    	
				    	  check1AD=check1AD-2;
				    	  check1AD=check1AD+10;

				    	  check2AD=check2AD-1;
				    	  check2AD=check2AD+10;
				      }
				    
					
					 session.put("check1AD",check1AD);
					 session.put("check2AD",check2AD);
					 
					    ArrayList<HashMap<String,Object>>AgentDepositReport=AgentDepositReportDao.getAgentDepositReportSearch(toDt,fromDt,userId,modcount,start,end,agentid,session,reportBy);
					  
					  if(AgentDepositReport.size()!=0)
					  {
						  request.setAttribute("AgentDepositReport",AgentDepositReport); 
						  return "AgentDepositReport";
						
					  }
					  else
					  {
							 String  message="<html><body><table><tr><td><font color=\"red\" size=\"2\">No data to  display</td></tr></table></body></html>";
		                     request.setAttribute("message", message);
						    
							return "AgentDepositReport";
						  
					  }
						
				   }	
	//------------------------------------------------------next----------------------------			  
				  if(param.equalsIgnoreCase("next"))
				   {
		         
					String toDt=(String) session.get("toDate");
					String fromDt=(String) session.get("fromDate");
				    String reportBy=(String) session.get("reportBy");
					String agentid=(String) session.get("agentid");
						 
					 String  start1=(String)request.getParameter("a");
					 String end1=(String)request.getParameter("b");
					 int pgno=reportForm.getPno();
					 int pno=pgno+1;
					 session.put("pno",pno);
					 int start=Integer.parseInt( start1);
					 int end=Integer.parseInt(end1);
		    	     start=end+1;
					  int modcount=reportForm.getCount1();
					  int check1AD=(Integer)session.get("check1AD");
				      int check2AD=(Integer)session.get("check2AD");
				      int pagecheck=pgno-1;
				      if(check2AD==pagecheck)
				      {
				    	  check1AD=check1AD-2;
				    	  check1AD=check1AD+10;

				    	  check2AD=check2AD-1;
				    	  check2AD=check2AD+10;
				      }
				      
				      
					
					 session.put("check1AD",check1AD);
					 session.put("check2AD",check2AD);
					
					 
 ArrayList<HashMap<String,Object>>AgentDepositReport=AgentDepositReportDao.getAgentDepositReportSearch(toDt,fromDt,userId,modcount,start,end,agentid,session,reportBy);
					  
					  if(AgentDepositReport.size()!=0)
					  {
						  request.setAttribute("AgentDepositReport",AgentDepositReport); 
						  return "AgentDepositReport";
						
					  }
					  else
					  {
							 String  message="<html><body><table><tr><td><font color=\"red\" size=\"2\">No data to  display</td></tr></table></body></html>";
		                     request.setAttribute("message", message);
						    
							return "AgentDepositReport";
						  
					  }

				   }
				  		  
	
				  
//-------------------------prevoius------------------------
				  
				  if(param.equalsIgnoreCase("previous"))
				   {

			         String toDt=(String) session.get("toDate");
					 String fromDt=(String) session.get("fromDate");
					 String reportBy=(String) session.get("reportBy");
					 String agentid=(String) session.get("agentid");
					 String  start1=(String)request.getParameter("a");
					 String end1=(String)request.getParameter("b");
					 int pgno=reportForm.getPno();
					 int start=Integer.parseInt( start1);
					 int end=Integer.parseInt(end1);
					 int pno=pgno-1;
					 session.put("pno",pno);
		    	     end=start;
					 start=start-50;
					 int check1AD=(Integer)session.get("check1AD");
				      int check2AD=(Integer)session.get("check2AD");
				      if(check1AD==pgno)
				      {
				    	  check1AD=check1AD-10;
				         check2AD=check2AD-10;
				      }
				      
					
					 session.put("check1AD",check1AD);
					 session.put("check2AD",check2AD);
					 int modcount=reportForm.getCount1();
						
						 
					  ArrayList<HashMap<String,Object>>AgentDepositReport=AgentDepositReportDao.getAgentDepositReportSearch(toDt,fromDt,userId,modcount,start,end,agentid,session,reportBy);
					 					  
					 					  if(AgentDepositReport.size()!=0)
					 					  {
					 						  request.setAttribute("AgentDepositReport",AgentDepositReport); 
					 						  return "AgentDepositReport";
					 						
					 					  }
					 					  else
					 					  {
					 						 String  message="<html><body><table><tr><td><font color=\"red\" size=\"2\">No data to  display</td></tr></table></body></html>";
					 	                     request.setAttribute("message", message);
					 					   
					 							return "AgentDepositReport";
					 						  
					 					  }
				   }
				  
				  
				  
	//----------------------------------DOWNLOAD-------------------------------------
				  
					if(param.equalsIgnoreCase("download"))
					   {
						     String toDt=(String) session.get("toDate");
						     String fromDt=(String) session.get("fromDate");
							 String reportBy=(String) session.get("reportBy");
						     String agentid=(String) session.get("agentid");
							 String enterAgentId=(String) session.get("enterAgentId");
							 String distributorInitial=(String)session.get("distributorInitial");
							 String filePath2="";
		                 String filePath1 = request.getRealPath("/")+"Reportfile/agentDeposit/";
				 
				         if(reportBy.equalsIgnoreCase("ById"))
				         {
					          filePath2=enterAgentId+".xls";
 
				         }
				         else
				         {
				        	  filePath2=distributorInitial+userId+".xls"; 
				         }
				         
				         
		  	             String filePath =filePath1+filePath2;
		                 File f = new File(filePath);

		               if (!f.exists())
		               {
			 
		                String  fileStatus=AgentDepositReportDao.downloadDepositReport(filePath,toDt,fromDt,userId,agentid,reportBy);
		                String filepathdownload="agentDeposit/"+filePath2;
		                session.put("fileStatus",filepathdownload);
					    return  "successdownload";

			                }
			                  else {

		                   boolean success = f.delete();

			                     if (!success)
			                     {
			                    	 String filepathdownload="agentDeposit/"+filePath2;
		                              session.put("fileStatus",filepathdownload);
					                 return "successdownload";

		                       }
		     
				                   else 
				                   {

						                String  fileStatus=AgentDepositReportDao.downloadDepositReport(filePath,toDt,fromDt,userId,agentid,reportBy);
		                               String filepathdownload="agentDeposit/"+filePath2;
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