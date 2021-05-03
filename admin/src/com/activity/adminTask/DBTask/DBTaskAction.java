package com.activity.adminTask.DBTask;
import java.io.PrintWriter;
/*
 * Created By   : Manoj 
 * Created Date : 12 April 2013
 * Matter       : This class is used for DB Tasks
 */
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.common.SendSmsOnMobile;
import com.common.SendSmtpMail;
import com.common.ZipCreator;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public final class DBTaskAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Map session;
	static String date;
	static int maxAmount;
	public String execute(){
		try{
			session= ActionContext.getContext().getSession();
			String userId=(String)session.get("userId");
			if(userId==null){
				request.setAttribute("message","Your Login Session has Expired. Please Login Again.");
				return "sessionExpire";
			}
			String param=request.getParameter("param");
			System.out.println("Class is DBTaskAction and Param is "+param);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");      
			if(param.equalsIgnoreCase("getPage")){
				if(DBTaskAction.date==null || !DBTaskAction.date.equalsIgnoreCase(dateFormat.format(new Date()))){
					date=dateFormat.format(new Date());
				}
				return "DbPage";
			}
			else if(param.equalsIgnoreCase("getActionPage")){
				String action=request.getParameter("Activity");
				return action;
			}else if(param.equalsIgnoreCase("viewTicket")){
				DBTaskDao dao=new DBTaskDao();
				ArrayList<HashMap<String,String>> ticketData=dao.getTicketDetails();
				if(ticketData!=null && ticketData.size()>0){
					request.setAttribute("ticketData", ticketData);
				}else{
					request.setAttribute("message", "Ticket not available for current day.");
				}
				
				
				return "viewTicket";
			}else if(param.equalsIgnoreCase("viewTicketByDate")){
				String fromDate=request.getParameter("fromDate");
				String toDate=request.getParameter("toDate");
				String ticketId=request.getParameter("ticketId");
				
				ArrayList<HashMap<String,String>> ticketData=null;
				DBTaskDao dao=new DBTaskDao();
				
				ticketData=dao.getTicketDetails(fromDate,toDate,ticketId);
				
				if(ticketData!=null && ticketData.size()>0){
					request.setAttribute("ticketData", ticketData);
				}else{
					request.setAttribute("message", "Ticket not available for current day.");
				}
				
				return "viewTicket";
			}else if(param.equalsIgnoreCase("updateTicket")){
				String ticketId=request.getParameter("ticketId");
				String solution=request.getParameter("solution");
				DBTaskDao dao=new DBTaskDao();
				
				if(dao.updateTicketDetails(ticketId, solution))
					request.setAttribute("message", "Ticket closed successfully.");
				else
					request.setAttribute("message", "Ticket not closed successfully. Try again.");
				
				ArrayList<HashMap<String,String>> ticketData=dao.getTicketDetails();
				if(ticketData!=null && ticketData.size()>0){
					request.setAttribute("ticketData", ticketData);
				}

				
				return "viewTicket";
			}
			else if(param.equalsIgnoreCase("bulksmsandmail")){
				return "bulksmsandmail";
			}
			else if(param.equalsIgnoreCase("sendSmsAndMail")){
				String messageTest="";
				String fetchType=request.getParameter("Activity");
				String activityFor=request.getParameter("activityFor");
				DBTaskDao dao=new DBTaskDao();
				
				List<String> list=dao.getMobileAndMailId(activityFor, fetchType);
				
				if(fetchType.equalsIgnoreCase("bulkmail")){
					request.getParameter("bulkmailmessage");
					String subject=request.getParameter("subject");
					//String[] recipients = list.toArray(new String[list.size()]);
					
					try {
						for (int i = 0; i < list.size(); i++) {
							String mailId=list.get(i);
							String[] recipients={mailId};
							SendSmtpMail.sendSSLMessage(recipients, subject, messageTest, "support@smartkinda.com");
						}
						
					} catch (Exception e) {
						e.printStackTrace();
						request.setAttribute("message","Email Successfully Sent.");
						return "bulksmsandmail";
					}
					request.setAttribute("message","Email Successfully Sent.");
				}else if(fetchType.equalsIgnoreCase("bulksms")){
					request.getParameter("bulksmsmessage");
					for (int i = 0; i < list.size(); i++) {
						String mobile=list.get(i);
						SendSmsOnMobile smsOnMobile=new SendSmsOnMobile();
						smsOnMobile.sendMobileSmsSMSZONE(mobile, messageTest);
					}
					request.setAttribute("message","Message Successfully Sent");
				}else{
					request.setAttribute("message","Invalid Action.");
				}
				
				return "bulksmsandmail";
			}
			else if(param.equalsIgnoreCase("remarkSerach")){
				
				String dbType=request.getParameter("dbType");
				String tableType=request.getParameter("tableType");
				String remark=request.getParameter("remark");
				String fromDate=request.getParameter("fromDate");
				String toDate=request.getParameter("toDate");
				String filePath1 = request.getRealPath("/")+"Reportfile/DBTASK/";
				String filePath2="RemarkReport"+".xls";
				
			    String path="/usr/Trandata/";
//				String path="E:/Work/";
				String zipFilePath2="RemarkReport"+".rar";
				String filePath =path+filePath2;
				String zipFilePath=filePath1+zipFilePath2;
				String fileStatus="";
				DBTaskDao daoObj=new DBTaskDao();
				if(dbType.equalsIgnoreCase("TEP")){
					if(tableType.equalsIgnoreCase("LRT")){
						fileStatus=daoObj.getRemarkLRTTran(filePath,fromDate,toDate,remark);
					}else{
						fileStatus=daoObj.getRemarkATTTran(filePath,fromDate,toDate,remark);
					}
				}else if(dbType.equalsIgnoreCase("API")){
					if(tableType.equalsIgnoreCase("LRT")){
						fileStatus=daoObj.getRemarkLRTTranAPI(filePath,fromDate,toDate,remark);
					}else{
						fileStatus=daoObj.getRemarkATTTranAPI(filePath,fromDate,toDate,remark);
					}
				}
				if(fileStatus.equalsIgnoreCase("Norecord")){
					request.setAttribute("message","Data Not Available.");
					return "remark";
				}else if(fileStatus.equalsIgnoreCase("MoreRecord")){
					request.setAttribute("message","Transaction are more than 3000 which is not allowed for download");
					return "remark";
				}else if(fileStatus.equalsIgnoreCase("ERROR")){
					request.setAttribute("message","Process aborted due to Technical Failure.");
					return "remark";
				}
				ZipCreator zipObject=new ZipCreator();
		    	String result=zipObject.createRarFile(filePath, zipFilePath);
		    	if(result.equals("error")){
		    		
		    		request.setAttribute("fileStatus","Process aborted due to Technical Failure.");
		    		return "remark";
		    	}
		    	
		    	response.sendRedirect("Reportfile/DBTASK/"+zipFilePath2);
			}else if(param.equalsIgnoreCase("PendingTran")){
				
				String dbType=request.getParameter("dbType");
				String tableType=request.getParameter("tableType");
				String fromDate=request.getParameter("fromDate");
				String toDate=request.getParameter("toDate");
				String filePath1 = request.getRealPath("/")+"Reportfile/DBTASK/";
				String filePath2="PendingTranReport"+".xls";
				
			    String path="/usr/Trandata/";
//				String path="E:/Work/";
				String zipFilePath2="PendingTranReport"+".rar";
				String filePath =path+filePath2;
				String zipFilePath=filePath1+zipFilePath2;
				String fileStatus="";
				DBTaskDao daoObj=new DBTaskDao();
				if(dbType.equalsIgnoreCase("TEP")){
					if(tableType.equalsIgnoreCase("LRT")){
						fileStatus=daoObj.getLRTPendingTran(filePath,fromDate,toDate);
					}else{
						fileStatus=daoObj.getATTPendingTran(filePath,fromDate,toDate);
					}
				}else if(dbType.equalsIgnoreCase("API")){
					if(tableType.equalsIgnoreCase("LRT")){
						fileStatus=daoObj.getLRTPendingTranAPI(filePath,fromDate,toDate);
					}else{
						fileStatus=daoObj.getATTPendingTranAPI(filePath,fromDate,toDate);
					}
				}
				if(fileStatus.equalsIgnoreCase("Norecord")){
					request.setAttribute("message","There is no transaction in report.");
					return "pendingTran";
				}else if(fileStatus.equalsIgnoreCase("MoreRecord")){
					request.setAttribute("message","Transaction are more than 3000 which is not allowed for download");
					return "pendingTran";
				}else if(fileStatus.equalsIgnoreCase("ERROR")){
					request.setAttribute("message","Process aborted due to Technical Failure.");
					return "pendingTran";
				}
				ZipCreator zipObject=new ZipCreator();
		    	String result=zipObject.createRarFile(filePath, zipFilePath);
		    	if(result.equals("error")){
		    		
		    		request.setAttribute("fileStatus","Process aborted due to Technical Failure.");
		    		return "pendingTran";
		    	}
		    	
		    	response.sendRedirect("Reportfile/DBTASK/"+zipFilePath2);
			}
			
			else if(param.equalsIgnoreCase("creditQuery")){
				String dbType=request.getParameter("dbType");
				String typeOfUser=request.getParameter("typeOfUser");
				String userID=request.getParameter("userID");
				String amount=request.getParameter("amount");
				String remark=request.getParameter("remark");
				String ipAdd=request.getParameter("ipAdd");
				DBTaskDao daoObj=new DBTaskDao();
				int dummyAmount=DBTaskAction.maxAmount;
				
				if(dummyAmount+Integer.parseInt(amount)<20000){
					if(DBTaskAction.date.equalsIgnoreCase(dateFormat.format(new Date())) && DBTaskAction.maxAmount<20000){
						String status=daoObj.executeCreditQuery(dbType,typeOfUser,userID,amount,remark,ipAdd);
						if(status.equalsIgnoreCase("valid")){
							maxAmount=maxAmount+Integer.parseInt(amount);
							request.setAttribute("message", " Your Query has been executed Sucessfully.");
						}else if(status.equalsIgnoreCase("invalid")){
							request.setAttribute("message", "Your Query has been Aborted.");
						}else if(status.equalsIgnoreCase("ERROR")){
							request.setAttribute("message", "Process aborted due to Technical Failure.");
						}
					}else{
						request.setAttribute("message", "You Have Reached Authorised Transaction Limit.");
					}
				}else{
					request.setAttribute("message", "You Have Reached Authorised Transaction Limit.");
				}
				return "creditPage";
			}
			
			else if(param.equalsIgnoreCase("debitQuery")){
				String dbType=request.getParameter("dbType");
				String typeOfUser=request.getParameter("typeOfUser");
				String userID=request.getParameter("userID");
				String amount=request.getParameter("amount");
				String remark=request.getParameter("remark");
				String ipAdd=request.getParameter("ipAdd");
				DBTaskDao daoObj=new DBTaskDao();
				String status=daoObj.executeDebitQuery(dbType,typeOfUser,userID,amount,remark,ipAdd);
				if(status.equalsIgnoreCase("valid")){
					request.setAttribute("message", " Your Query has been executed Sucessfully.");
				}else if(status.equalsIgnoreCase("invalid")){
					request.setAttribute("message", "Your Query has been Aborted.");
				}else if(status.equalsIgnoreCase("ERROR")){
					request.setAttribute("message", "Process aborted due to Technical Failure.");
				}
				return "DebitPage";
			
			}else if(param.equalsIgnoreCase("balanceSheet")){
				String dbType=request.getParameter("dbType");
				String parentUser=request.getParameter("parentUser");
				String parentID=request.getParameter("parentID");
				String child=request.getParameter("child");
				
				DBTaskDao daoObj=new DBTaskDao();
				String filePath1 = request.getRealPath("/")+"Reportfile/DBTASK/";
				String filePath2="BalanceReport"+".xls";
				
				String zipFilePath=filePath1+filePath2;
				String status=daoObj.getBalanceOfUser(dbType,parentUser,parentID,child,zipFilePath);
				if(status.equalsIgnoreCase("Norecord")){
					request.setAttribute("message","There is no transaction in report.");
					return "balanceSheet";
				}else if(status.equalsIgnoreCase("MoreRecord")){
					request.setAttribute("message","Transaction are more than 3000 which is not allowed for download");
					return "balanceSheet";
				}else if(status.equalsIgnoreCase("ERROR")){
					request.setAttribute("message","Process aborted due to Technical Failure.");
					return "balanceSheet";
				}else if(status.equalsIgnoreCase("wrongInput")){
					request.setAttribute("message","Please Select correct Hierarchy.");
					return "balanceSheet";
				}
		    	response.sendRedirect("Reportfile/DBTASK/"+filePath2);
			}else if(param.equalsIgnoreCase("loginIDChange")){
				String dbType=request.getParameter("dbType");
				String user=request.getParameter("user");
				String userType="";
				if(user.equalsIgnoreCase("agent")){
					userType=request.getParameter("userType");
				}
				
				String oldLoginID=request.getParameter("oldLoginID");
				String newLoginID=request.getParameter("newLoginID");
				DBTaskDao daoObj=new DBTaskDao();
				String status= daoObj.getStatusOfoldLoginID(dbType,user,userType,oldLoginID);
				if(status.equalsIgnoreCase("Correct")){
					status=daoObj.checkNewloginID(dbType,user,userType,newLoginID);
					if(status.equalsIgnoreCase("Correct")){
						status=daoObj.updateloginID(dbType,user,userType,newLoginID,oldLoginID);
						if(status.equalsIgnoreCase("Correct")){
							request.setAttribute("", "Login ID updated Successfully.");
							
						}else{
							request.setAttribute("", "Login ID not updated Successfully.");
						}
					}else if(status.equalsIgnoreCase("INCorrect")){
						request.setAttribute("message", "New mail Id Exist");
					}
				}else if(status.equalsIgnoreCase("INCorrect")){
					request.setAttribute("message", "Either no mail id exist or more than one id exist.");
				}
				return "loginIDChange";
			}
			else if(param.equalsIgnoreCase("PayOutSetup")){
				return "PayOutSetup";
			}else if(param.equalsIgnoreCase("viewPayOutDetails")){
				DBTaskDao dao=new DBTaskDao();
				
				String clientId=request.getParameter("clientId");
				String userType=request.getParameter("userType");
				
				String mdId="";
				
				if("DS".equalsIgnoreCase(userType)){
					mdId=request.getParameter("mdId");
				}
				
				if("all".equalsIgnoreCase(clientId)){
					request.setAttribute("clientId", clientId);
				}else{
					ArrayList<HashMap<String,String>> PayoutDetailsList=dao.getPayOutDetails(userType, Long.parseLong(clientId));
					if(PayoutDetailsList!=null && PayoutDetailsList.size()>0)
						request.setAttribute("PayoutDetailsList", PayoutDetailsList);
				}
				request.setAttribute("userType", userType);
				request.setAttribute("mdId", mdId);
				request.setAttribute("pageStatus", "Y");

				return "PayOutSetup";
			}else if(param.equalsIgnoreCase("updatePayOutDetails")){
				
				try {
					
					boolean status=false;
					DBTaskDao dao=new DBTaskDao();

					String[] checkedVal=(String[])request.getParameterValues("checkpartial");
					String updateStatus=request.getParameter("updateStatus");
					String mdId=request.getParameter("mdId");
					String userTypeMain=request.getParameter("userType");
					
					HashMap<String,String> map=null;
					int totpass=checkedVal.length;
					for(int i=0;i<totpass;i++)
					{
						map=new HashMap<>();
					    ////System.out.println("we are u=into to get value");
						String value=checkedVal[i];
						String clientId=request.getParameter("userId"+value);
						String userType=request.getParameter("userType"+value);
						String serviceType=request.getParameter("serviceType"+value);
						String CommMark=request.getParameter("CommMark"+value);
						String comm=request.getParameter("comm"+value);
						map.put("clientId", clientId);
						map.put("userType", userType);
						map.put("serviceType", serviceType);
						map.put("CommMark", CommMark);
						map.put("comm", comm);
						
						status=dao.updatePayOutCommission(map,mdId,userTypeMain);
						
						System.out.println("Update status : "+status);
						
						//System.out.println("Now all data is "+list);
					}
					if(!status){
						request.setAttribute("message","Not Updated.");
					}else
						request.setAttribute("message","Updated successfully.");
					

					return "PayOutSetup";
				} catch (Exception e) {
					request.setAttribute("message","Process aborted due to Technical Failure.");
					// TODO Auto-generated catch block
					e.printStackTrace();
					return "PayOutSetup";
				}
			}else if("PayOutSchedular".equalsIgnoreCase(param)){
				return "PayOutSchedular";
			}else if("RunPayOutSchedular".equalsIgnoreCase(param)){
				
				try {
					
					String clientId=request.getParameter("clientId");
					String userType=request.getParameter("userType");
					String date=request.getParameter("date");
					DBTaskDao dao=new DBTaskDao();
					
					String parentMDSId="";
					String idType="id";
					if("all".equalsIgnoreCase(clientId)){
						clientId="0";
						idType="all";
						
						if("DS".equalsIgnoreCase(userType)){
							parentMDSId=request.getParameter("mdId");
						}
					}
					
					
					String status=dao.runDsPayOut(clientId, userType, date,idType,parentMDSId);
					request.setAttribute("message",status);
					
				} catch (Exception e) {
					// TODO: handle exception
					request.setAttribute("message","Process aborted due to Technical Failure.");
				}
				
				return "PayOutSchedular";
			}
			else if("balanceRequest".equalsIgnoreCase(param)){
				PrintWriter out=null;
				try {
					
					response=ServletActionContext.getResponse();
					out=response.getWriter();
					
					DBTaskDao dbTaskDao=new DBTaskDao();
					boolean status=dbTaskDao.balanceRequestStatus();
					
					if(status)
						out.print("Y");
					else
						out.print("N");
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					out.print("N");
				}
				return null;
			}else if("TicketStatus".equalsIgnoreCase(param)){
				PrintWriter out=null;
				try {
					
					response=ServletActionContext.getResponse();
					out=response.getWriter();
					
					DBTaskDao dbTaskDao=new DBTaskDao();
					boolean status=dbTaskDao.ticketRequestStatus();
					
					if(status)
						out.print("Y");
					else
						out.print("N");
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					out.print("N");
				}
				return null;
			}
		}catch (Exception e) {
			request.setAttribute("message","Process aborted due to Technical Failure.");
			System.out.println(e.toString());
			System.out.println("Exception in DBTaskAction ");
			return "DbPage";
		}
		return "err";
	}
	public void setServletRequest(HttpServletRequest request){
		 this.request=request;
		 
	 }
	public void setSession(Map session){
        session = this.getSession();
       }

      public Map getSession(){
         return session;
      }
	
      public void setServletResponse(HttpServletResponse response) {
			this.response=response;
			
		}
}
