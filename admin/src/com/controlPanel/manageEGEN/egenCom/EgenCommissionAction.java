package com.controlPanel.manageEGEN.egenCom;

/*
 * Created By   : Manoj 
 * Created Date : 3/12/2012
 * Matter       : This class is used for Set Egen Client Commission and status update.
 */
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public final class EgenCommissionAction extends ActionSupport implements ServletRequestAware{
	
	
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Map session;
	public String execute() throws Exception{
		
		Map session=ActionContext.getContext().getSession();
		EgenCommissionDao daoObj=new EgenCommissionDao();
		try{
			String userId=(String)session.get("userId");
			if(userId==null){

				request.setAttribute("message","Your Login Session has Expired. Please Login Again.");
				return "sessionExpire";
			}
			String param=(String)request.getParameter("param");
			System.out.println("EgenCommissionAction param is"+param);
			if(param.equalsIgnoreCase("EgenCommPage"))
			{
				
				return "EgenCommPage";
			}
			if(param.equals("blockOperator"))
			{
				
				ArrayList corpAgentDetails=daoObj.getServices();
				request.setAttribute("Data",corpAgentDetails);
				return "editservice";
			}
			else if(param.equals("updateService"))
			{
				String status=(String)request.getParameter("status");
				String mark=(String)request.getParameter("mark"+status);
				String vendor=(String)request.getParameter("vendor"+status);
				String service=(String)request.getParameter("service"+status);
	           	String operator=(String)request.getParameter("operator"+status);
	            String updateStatus=daoObj.updateServices(mark,operator,service);
	            
	            if(updateStatus.equalsIgnoreCase("notsuccess")){
					request.setAttribute("message","Transaction is not succesful updated due to some technical Problem.");
				}
				request.setAttribute("message","Service is succefully updated.");
				ArrayList corpAgentDetails=daoObj.getServices();
	            request.setAttribute("Data",corpAgentDetails);
				return "editservice";
			}
			// Edit Service Agent Wise
			else if(param.equalsIgnoreCase("blockServAgent"))
			{
				return "editServAgentWise";
			}
			else if(param.equals("updateAgntServ")){
				//System.out.println("we are in updateAgntServ");
			     String corpAgentId=(String)request.getParameter("corpAgentId");
			     String status=(String)request.getParameter("status");
				 //System.out.println("status is"+status);
			     String mark=(String)request.getParameter("mark"+status);
			     String service=(String)request.getParameter("service"+status);
          	     String remark=(String)request.getParameter("remark"+status);
          	     String updateStatus=daoObj.updateAgntServices(corpAgentId,mark,service,remark);
          	     if(updateStatus.equalsIgnoreCase("notsuccess")){
          	    	request.setAttribute("message","Transaction is not succesful updated due to some technical Problem.");	
          	     }
          	     //ArrayList corpAgentDetails=daoObj.getServices();
          	     ArrayList corpAgentDetails=daoObj.getCorpAgentServDetails(corpAgentId);
          	     request.setAttribute("Data",corpAgentDetails);
          	     request.setAttribute("message","Service is succefully updated.");	
          	     return "editServAgentWise";
			}
			else if(param.equalsIgnoreCase("getAgentServDetails"))
			{
				String corpAgentId=request.getParameter("corpAgentId");
				
				String chkStatus=daoObj.chkCorpAgentId(corpAgentId);
				if(chkStatus.equalsIgnoreCase("notexists"))
				{
					request.setAttribute("status","Invalid Corporate Agent Id");
					return "editServAgentWise";
				}
				ArrayList corpAgentDetails=daoObj.getCorpAgentServDetails(corpAgentId);
				request.setAttribute("Data",corpAgentDetails);
				return "editServAgentWise";
			}
			else if(param.equalsIgnoreCase("getAgentOptrDetails"))
			{
				String corpAgentId=request.getParameter("corpAgentId");
				String service=request.getParameter("service");
				//System.out.println(corpAgentId);
				//System.out.println(service);
				String chkStatus=daoObj.chkCorpAgentId(corpAgentId);
				if(chkStatus.equalsIgnoreCase("notexists")){
				
					request.setAttribute("message","Invalid Corporate Agent Id");
					return "editOptrAgentWise";
				}
				ArrayList corpAgentDetails=daoObj.getCorpAgentDetails(corpAgentId,service);
				request.setAttribute("Data",corpAgentDetails);
				return "editOptrAgentWise";
			}
			else if(param.equals("updateOptrStatusComm")){
			     
				String corpAgentId=(String)request.getParameter("corporateAgntId");
				String status=(String)request.getParameter("status");
				String mark=(String)request.getParameter("mark"+status);
				String comm=(String)request.getParameter("comm"+status);
				String commMark=(String)request.getParameter("commMark"+status);
				String service=(String)request.getParameter("service");
				String optrCode=(String)request.getParameter("operator"+status);
				String updateStatus=daoObj.updateOptrAll(optrCode,service,mark,comm,commMark,corpAgentId);
				if(updateStatus.equalsIgnoreCase("notsuccess")){
					request.setAttribute("message","Transaction is not succesful updated due to some technical Problem.");
				}
				request.setAttribute("message","Operator status is succefully updated.");
				ArrayList corpAgentDetails=daoObj.getCorpAgentDetails(corpAgentId,service);
				request.setAttribute("Data",corpAgentDetails);
				return "editOptrAgentWise";
			}
			else if(param.equalsIgnoreCase("UpdateAllOptrStatus"))
			{
				String corpAgentId=request.getParameter("corporateAgntId");
				String service=request.getParameter("service");
				String sNo=request.getParameter("SNo");
				int totalSize=Integer.parseInt(sNo);
				String updateStatus="";
				for( int j=0;j<totalSize;j++)
				{
					String i=Integer.toString(j);
					String status=(String)request.getParameter("mark"+i);
					String optrCode=(String)request.getParameter("operator"+i);
					//System.out.println("status is "+status+" operator is "+optrCode);
					updateStatus=daoObj.updateOptrStatus(optrCode,service,status,corpAgentId);
				}
				if(updateStatus.equalsIgnoreCase("notsuccess")){
					request.setAttribute("message","Transaction is not succesful updated due to some technical Problem.");
				}
				
				request.setAttribute("message","Operator Status is succefully updated.");
				ArrayList corpAgentDetails=daoObj.getCorpAgentDetails(corpAgentId,service);
				request.setAttribute("Data",corpAgentDetails);
				return "editOptrAgentWise";
			}
			else if(param.equalsIgnoreCase("updateAllOptrComm"))
			{
				String corpAgentId=request.getParameter("corporateAgntId");
				String service=request.getParameter("service");
				String sNo=request.getParameter("SNo");
				int totalSize=Integer.parseInt(sNo);
				String updateStatus="";
				
				for( int j=0;j<totalSize;j++)
				{
					    
					String i=Integer.toString(j);
					String comm=(String)request.getParameter("comm"+i);
					String commMark=(String)request.getParameter("commMark"+i);
					String optrCode=(String)request.getParameter("operator"+i);
					//System.out.println("status is "+status+" operator is "+optrCode);
					updateStatus=daoObj.updateOptrComm(optrCode,service,comm,commMark,corpAgentId);
				}
				if(updateStatus.equalsIgnoreCase("notsuccess")){
					request.setAttribute("message","Transaction is not succesful updated due to some technical Problem.");
				}
				
				request.setAttribute("message","Commission is succefully updated.");
				ArrayList corpAgentDetails=daoObj.getCorpAgentDetails(corpAgentId,service);
				request.setAttribute("Data",corpAgentDetails);
				return "editOptrAgentWise";
			}
			else if(param.equalsIgnoreCase("UpdateAll"))
			{
				String corpAgentId=request.getParameter("corporateAgntId");
				String service=request.getParameter("service");
				String sNo=request.getParameter("SNo");
				int totalSize=Integer.parseInt(sNo);
				String updateStatus="";
				
				for( int j=0;j<totalSize;j++)
				{
					    
					String i=Integer.toString(j);
					String status=(String)request.getParameter("mark"+i);
					String comm=(String)request.getParameter("comm"+i);
					String commMark=(String)request.getParameter("commMark"+i);
					String optrCode=(String)request.getParameter("operator"+i);
					//System.out.println("status is "+status+" operator is "+optrCode);
					updateStatus=daoObj.updateOptrAll(optrCode,service,status,comm,commMark,corpAgentId);
				}
				if(updateStatus.equalsIgnoreCase("notsuccess")){
					request.setAttribute("message","Transaction is not succesful updated due to some technical Problem.");
				}
				
				request.setAttribute("message","Transaction is succefully updated.");
				ArrayList corpAgentDetails=daoObj.getCorpAgentDetails(corpAgentId,service);
				request.setAttribute("Data",corpAgentDetails);
				return "editOptrAgentWise";
			}
			
			
		}catch(Exception e){
			System.out.println("Exception in EgenCommissionAction ");
			request.setAttribute("message","Process aborted due to Technical Failure.");
			System.out.println(e.toString());
			
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

}
