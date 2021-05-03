package com.activity.pgtbrequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import com.common.PropertyFile;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class PGTBRequestController extends ActionSupport {

	private static Logger log = Logger.getLogger(PGTBRequestController.class);
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Map session;
	PGTBRequestDao dao=new PGTBRequestDao();
	public String execute(){
		
		session=ActionContext.getContext().getSession();
		request=ServletActionContext.getRequest();
		try{
			String userId=(String)session.get("userId");
			if(userId==null){
				request.setAttribute("message","Your Login Session has Expired. Please Login Again.");
				return "sessionExpire";
			}
			String param=request.getParameter("param");
			if(param.equalsIgnoreCase("welcomePage")){
				
				return "forward";
			}
			if(param.equalsIgnoreCase("getPGTBDetails")){
				
				String tranId=request.getParameter("Tran_id");
				String fromdate=request.getParameter("fromDate");
				String todate=request.getParameter("toDate");
				ArrayList list=dao.getpgTBDetails(tranId,fromdate,todate);
				if(list!=null && list.size()>0){
					
					request.setAttribute("tbdetails",list);
					
				}else{
					request.setAttribute("message","Transaction Id does not exist.");
				}
				return "forward";
			}else if(param.equalsIgnoreCase("updatePGTBDetails")){
				
				String[] choices;
				PGTBRequestDao dao=new PGTBRequestDao();
				HashMap<String,Object> hm=null;
				choices=request.getParameterValues("checkpartial");
				for(int i=0; i<choices.length; i++)
				{
					String tokenNumber=choices[i];
					int Idnumber=Integer.parseInt(tokenNumber);
					hm=new HashMap<String,Object>();
					String agentId=request.getParameter("agentId"+Idnumber);
					String tranId=request.getParameter("tranId"+Idnumber);
					String amount=request.getParameter("amount"+Idnumber);
					String action=request.getParameter("action"+Idnumber);
					String service=PropertyFile.PG_Service_Name;
					String operator=PropertyFile.PG_Operator_Name;
					
					
					if(action.equalsIgnoreCase("Credit")){
						
						String ipAddress=request.getRemoteAddr();
						String result=dao.creditPGTBAmount(service,tranId,amount,ipAddress,operator);
						if(result.equals("error")){
							
							request.setAttribute("message","Technical Failured.");
						}else if(result.equals("success")){
							
							request.setAttribute("message","Action is successfully Updated");
							
						}else if(result.equals("invalid")){
							
							request.setAttribute("message","Transaction Failured.");
						}else if(result.equals("input")){
							request.setAttribute("message","Input is not valid.");
						}
						
					}else if(action.equalsIgnoreCase("Decline")){
						
						String update=dao.updatePGTBStatus(tranId,action);
						if(update.equals("success"))
							request.setAttribute("message","Action is successfully Updated");
						else
							request.setAttribute("message","Technical failured.");
					}
				}
				return "forward";
			}
		}catch(Exception e){
			log.info("Exception in PGTBRequestController");
			request.setAttribute("message", "Process aborted due to Technical Failure.");
			log.info(e.toString());
			return "err";
		}


		return null;
	}
}
