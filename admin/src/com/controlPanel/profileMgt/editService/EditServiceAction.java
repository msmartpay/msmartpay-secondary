package com.controlPanel.profileMgt.editService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class EditServiceAction extends ActionSupport implements ServletRequestAware,ServletResponseAware
{
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request; 
	private HttpServletResponse response;
	private HttpSession ses=null;
	private Map session;
	
	public String execute() throws Exception
	{
		Map session=ActionContext.getContext().getSession();
		ses=request.getSession();
		try{
			String userId=(String)session.get("userId");
			if(userId==null)
			{
				request.setAttribute("message","Your Login Session has Expired. Please Login Again.");
				return "sessionExpire";
			}
			String param=request.getParameter("param");
			System.out.println("EditServiceAction Class and Param is "+param);
			if(param.equals("getEditServicePage"))
			{
				List<String> idList=null;
				String userType=request.getParameter("userType");
				String editUserId=null;
				String url="fail";
			
					
					
					EditServiceDao daoObj=new EditServiceDao();
					if(userType.equalsIgnoreCase("mds"))
					{
						String[] mdIds=request.getParameterValues("mdIds");
						editUserId=mdIds[0];
						request.setAttribute("editUserId", editUserId);
						idList=Arrays.asList(mdIds);
						ses.setAttribute("msIds", idList);
						url="mds";
					}
					else if(userType.equalsIgnoreCase("DS"))
					{
						String[] distributorIds=request.getParameterValues("distributorIds");
						idList=Arrays.asList(distributorIds);
						editUserId=distributorIds[0];
						
						HashMap<String,String> serviceData=daoObj.getDSService(editUserId);
						request.setAttribute("serviceData", serviceData);
						url="DS";
						ses.setAttribute("dsIds", idList);
					}
					else if(userType.equalsIgnoreCase("agent"))
					{
						String[] agentIds=request.getParameterValues("agentIds");
						idList=Arrays.asList(agentIds);
						editUserId=agentIds[0];
						
						HashMap<String,String> serviceData=daoObj.getAgentService(editUserId);
						request.setAttribute("serviceData", serviceData);
						url="agent";
						ses.setAttribute("agentIds", idList);
					}
				
				request.setAttribute("userType", userType);
				
				return url;
			}
			else if(param.equals("EditServiceOfAgent"))
			{
				String userType=request.getParameter("userType");
				String editUserId=null;
				String url="fail";
				List<String> agentIds=(List<String>)ses.getAttribute("agentIds");
				if(agentIds.size()>0)
				{
					
					editUserId=agentIds.get(0);
					String Flights=request.getParameter("Flights");
					String Bus=request.getParameter("Bus");
					String Hotel=request.getParameter("Hotel");
					String Recharge=request.getParameter("Recharge");
					String Utility=request.getParameter("Utility");
					String PayCard=request.getParameter("PayCard");
					String TestMerit=request.getParameter("TestMerit");
					String DTHX=request.getParameter("DTHX");
					String rail=request.getParameter("rail");
					String SMS_TXN=request.getParameter("SMS_TXN");
					String Holidays=request.getParameter("Holidays");
					String WW=request.getParameter("WW");
					String DMR=request.getParameter("DMR");
					System.out.println(userType);
					//System.out.println(editUserId);
					
					EditServiceDao daoObj=new EditServiceDao();
					String result=daoObj.updateAgentService(agentIds,Flights,Bus,Hotel,Recharge,Utility,PayCard,TestMerit,DTHX,rail,SMS_TXN,WW,Holidays,DMR);
					if(result.equalsIgnoreCase("success"))
					{
						request.setAttribute("message", "Proceess has been completed.");
					}else if(result.equalsIgnoreCase("fail"))
					{
						request.setAttribute("message", "Process aborted due to Technical Failure.");
					}
					HashMap<String,String> serviceData=daoObj.getAgentService(editUserId);
					request.setAttribute("serviceData", serviceData);
					url="agent";
					request.setAttribute("userType", userType);
					request.setAttribute("editUserId", editUserId);
				}
				else
				{
					url="fail";
					request.setAttribute("message", "Edit Service process failure.");
				}
				
				
				return url;
			}
			else if(param.equals("EditServiceOfDS"))
			{
				String userType=request.getParameter("userType");
				
				String url="fail";
				/*List<String> dsIds=(List<String>)ses.getAttribute("dsIds");
				
					String Flights=request.getParameter("Flights");
					String Bus=request.getParameter("Bus");
					String Hotel=request.getParameter("Hotel");
					String Recharge=request.getParameter("Recharge");
					String Utility=request.getParameter("Utility");
					String PayCard=request.getParameter("PayCard");
					String TestMerit=request.getParameter("TestMerit");
					String DTHX=request.getParameter("DTHX");
					String rail=request.getParameter("rail");
					String SMS_TXN=request.getParameter("SMS_TXN");
					//System.out.println(userType);
					//System.out.println(editUserId);
					
					EditServiceDao daoObj=new EditServiceDao();
					String result=daoObj.updateDSService(dsIds,Flights,Bus,Hotel,Recharge,Utility,PayCard,TestMerit,DTHX,rail,SMS_TXN);
					if(result.equalsIgnoreCase("success")){
						request.setAttribute("message", "Proceess has been completed.");
					}else if(result.equalsIgnoreCase("fail")){
						request.setAttribute("message", "Process aborted due to Technical Failure.");
					}
					HashMap<String,String> serviceData=daoObj.getDSService(editUserId);
					request.setAttribute("serviceData", serviceData);
					url="DS";
					request.setAttribute("userType", userType);
					request.setAttribute("editUserId", editUserId);
					*/
				
					url="fail";
					request.setAttribute("message", "Edit Service process not available.");
				
				return url;
			}
			else if(param.equals("getEditServiceMDS"))
			{
				String userType=request.getParameter("userType");
				String editUserId=request.getParameter("editUserId");
				System.out.println("User Type in class is "+userType);
				System.out.println("User ID in class is "+editUserId);
				String url="fail";
				EditServiceDao daoObj=new EditServiceDao();
				HashMap<String,String> serviceData=daoObj.getMDSService(editUserId);
				request.setAttribute("serviceData", serviceData);
				url="mds";
				request.setAttribute("userType", userType);
				request.setAttribute("editUserId", editUserId);
				return url;
			}
			else if(param.equals("getEditOperatorMDS"))
			{
				String userType=request.getParameter("userType");
				String editUserId=request.getParameter("editUserId");
				String subservice=request.getParameter("subservice");
				String operator=request.getParameter("operator");
				System.out.println("User Type in class is "+userType);
				System.out.println("User ID in class is "+editUserId);
				String url="fail";
				EditServiceDao daoObj=new EditServiceDao();
				String showService="";
				String DBservice="";
				if(subservice.equalsIgnoreCase("Billpay")){showService="Utility";DBservice="Billpay";}else{showService="Recharge";DBservice="Recharge";}
				ArrayList<HashMap<String,String>> operatorData=daoObj.getMDSOperatorStatus(editUserId,subservice,operator);
				request.setAttribute("operatorData", operatorData);
				url="mds";
				request.setAttribute("userType", userType);
				request.setAttribute("displayService", showService);
				request.setAttribute("DBservice", DBservice);
				request.setAttribute("editUserId", editUserId);
				return url;
			}
			else if(param.equalsIgnoreCase("EditServiceOfMDS"))
			{
				List<String> mdIds=(List<String>)ses.getAttribute("msIds");
				String editUserId="";
				String userType=request.getParameter("userType");
				//String editUserId=request.getParameter("editUserId");
				String Flights=request.getParameter("Flights");
				String Bus=request.getParameter("Bus");
				String Hotel=request.getParameter("Hotel");
				String Recharge=request.getParameter("Recharge");
				String Utility=request.getParameter("Utility");
				String PayCard=request.getParameter("PayCard");
				String TestMerit=request.getParameter("TestMerit");
				String DTHX=request.getParameter("DTHX");
				String rail=request.getParameter("rail");
				String SMS_TXN=request.getParameter("SMS_TXN");
				String Holidays=request.getParameter("Holidays");
				String WW=request.getParameter("WW");
				String DMR=request.getParameter("DMR");
				//System.out.println(userType);
				//System.out.println(editUserId);
				String url="fail";
				EditServiceDao daoObj=new EditServiceDao();
				String result=daoObj.updateMDSService(mdIds,Flights,Bus,Hotel,Recharge,Utility,PayCard,TestMerit,DTHX,rail,SMS_TXN,WW,Holidays,DMR);
				if(result.equalsIgnoreCase("success"))
				{
					request.setAttribute("message", "Proceess has been completed.");
				}else if(result.equalsIgnoreCase("fail")){
					request.setAttribute("message", "Process aborted due to Technical Failure.");
				}
				HashMap<String,String> serviceData=daoObj.getMDSService(mdIds.get(0));
				request.setAttribute("serviceData", serviceData);
				url="mds";
				editUserId=mdIds.get(0);
				request.setAttribute("userType", userType);
				request.setAttribute("editUserId", editUserId);
				return url;
			}
			else if(param.equalsIgnoreCase("editOperatorOfMds"))
			{
				//System.out.println("we are into edit operator of mds");
				String editUserId=request.getParameter("editUserId");
				String service=request.getParameter("service");
				if(service.equalsIgnoreCase("Live_Recharge"))
					service="Recharge";
				if(service.equalsIgnoreCase("Live_Billpay"))
					service="Billpay";
				
				String sNo=request.getParameter("SNo");
				int totalSize=Integer.parseInt(sNo);
				String updateStatus="";
				String url="";
				EditServiceDao daoObj=new EditServiceDao();
				//System.out.println("SNO "+sNo);
				for( int j=0;j<totalSize;j++)
				{
					String i=Integer.toString(j);
					String opName=request.getParameter("opName"+i);
					String mark=request.getParameter("mark"+i);
					String subService=request.getParameter("subService"+i);
					//System.out.println("Operator name is "+opName);
					//System.out.println("mark     name is "+mark);
					//System.out.println("Subservice    is "+subService+" : "+service);
					updateStatus=daoObj.updateMDSOperator(editUserId,service,opName,mark,subService);
				}
				if(updateStatus.equalsIgnoreCase("success")){
					request.setAttribute("message", "Proceess has been completed.");
				}else{
					request.setAttribute("message", "Process aborted due to Technical Failure.");
				}
				url="mds";
				request.setAttribute("editUserId",editUserId);
				return url;
			}
		}catch (Exception e) {
			System.out.println("Exception in EditServiceAction");
			request.setAttribute("message","Process aborted due to Technical Failure.");
			e.printStackTrace();
			return "err";
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
