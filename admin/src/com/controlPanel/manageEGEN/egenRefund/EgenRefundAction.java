package com.controlPanel.manageEGEN.egenRefund;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public final class EgenRefundAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Map session;
	
	public String execute()
	{
		Map session=ActionContext.getContext().getSession();
		EgenRefundDao daoObj=new EgenRefundDao();
		try{
			
			String userId=(String)session.get("userId");
			if(userId==null)
			{
				request.setAttribute("message","Your Login Session has Expired. Please Login Again.");
				return "sessionExpire";
			}
			String param=(String)request.getParameter("param");
			System.out.println(" EgenRefundAction param is:"+param);
			if(param.equalsIgnoreCase("getEgenRefundPage"))
			{
				return "getPage";
			
			}
			else if(param.equalsIgnoreCase("getTran")){
				//System.out.println("we are in param getTran of refund class");
				String TranID=(String)request.getParameter("TranID");
				String CorTranId=(String)request.getParameter("CorTranId");
				String vendorID=(String)request.getParameter("vendorID");
				//System.out.println("Tran no is "+TranID);
				//System.out.println("CorTranId no is "+CorTranId);
				//System.out.println("vendorID no is "+vendorID);
				String fromDate=(String)request.getParameter("fromDate");
				String toDate=(String)request.getParameter("toDate");				
				//System.out.println(fromDate);
				//System.out.println(toDate);
				TranID.trim();
				CorTranId.trim();
				vendorID.trim();
				String RefrenceId="";
				
				if(!TranID.equals("")&& !CorTranId.equals("") && !vendorID.equals("")){
					//System.out.println("we are in 1st when tranno and tranid and venodrid is not null");
					RefrenceId=daoObj.getTranNo(TranID,CorTranId,vendorID,fromDate,toDate);
				}else if(!TranID.equals("")&& !CorTranId.equals("")){
					//System.out.println("we are in 2nd only tran id and tranNo is not null");
					RefrenceId=daoObj.getTranNo(TranID,CorTranId,vendorID,fromDate,toDate);
				}else if(!CorTranId.equals("") && !vendorID.equals("")){
					//System.out.println("we are in 3rd when tran no and vendorID is not null");
					RefrenceId=daoObj.getTranNo(TranID,CorTranId,vendorID,fromDate,toDate);
				}else if(!TranID.equals("")){
					//System.out.println("we are in 4th when tranID is not null");
					RefrenceId=daoObj.getTranNo(TranID,CorTranId,vendorID,fromDate,toDate);
				}else if(!CorTranId.equals("")){
					//System.out.println("we are in 5th when CorTranId is not null");
					RefrenceId=daoObj.getTranNo(TranID,CorTranId,vendorID,fromDate,toDate);
				}
				
				//System.out.println("vendorID no is "+vendorID);
				//System.out.println("RefrenceId is "+RefrenceId);
				if(!RefrenceId.equals(""))
				{
					HashMap map=daoObj.getTran(RefrenceId,fromDate,toDate);
					int size=map.size();
				
					if(size<=0){
						request.setAttribute("message", "No Data Found Regarding This Transaction");
						return "getTranPage";
					}
					request.setAttribute("TranData", map);
				}else
				{
					request.setAttribute("message", "No Data Found Regarding This Transaction");
				}
				return "getTranPage";
			}
			else if(param.equalsIgnoreCase("UpdateTran"))
			{
				//System.out.println("we are in UpdateTran of refund class");
				String tranID=(String)request.getParameter("tranID");
				String RefrenceId=(String) request.getParameter("RefrenceId");
				//System.out.println(RefrenceId);
				String corpAgentId=(String)request.getParameter("userId");
				//System.out.println(corpAgentId);
				String curStatus=(String)request.getParameter("status");
				String updatedStatus=(String) request.getParameter("updatedStatus");
				//System.out.println("tranID   "+tranID);
				//System.out.println("curStatus   "+curStatus);
				//System.out.println("updatedStatus   "+updatedStatus);
				String ipAdd=request.getParameter("ipAdd");
				//System.out.println("IP Address "+ipAdd);
				String portalId="1";
				String loginType=(String)session.get("loginType");
				if(!loginType.equalsIgnoreCase("SuperAdmin"))
				{
					portalId=(String)session.get("adminUserPortalId");
				}
				// logic for generate id no
				String genId="";
				String prefix="";
				String result="";

				Date now = new Date();
				long logntranId = now.getTime();

				int length = corpAgentId.length();
				if(length<=4){
					prefix="100"+corpAgentId;
				}
				if(length>=6){
					prefix="1"+corpAgentId;
				}
				String suffix = new String();
				suffix=String.valueOf(logntranId);
				genId=prefix+suffix;
				
				String Status=daoObj.updateTran(tranID,curStatus,updatedStatus,ipAdd,portalId,RefrenceId,genId);
				//System.out.println("status is "+Status);
				if(Status.equalsIgnoreCase("NotUpdated"))
				{
					request.setAttribute("message","Transaction can not be succefully updated due to some technical problem.");
				}else if(Status.equalsIgnoreCase("0")){
					request.setAttribute("message","ACTION NOT ALLOWED");
				}else if(Status.equalsIgnoreCase("1")){
					request.setAttribute("message","NO ACTION TAKEN (Success in both Table)");
				}else if(Status.equalsIgnoreCase("2")){
					request.setAttribute("message","SUCCESSFULLY UPDATED (Transaction Status Made Success in Both Table)");
				}else if(Status.equalsIgnoreCase("3")){
					request.setAttribute("message","NO ACTION TAKEN (Transaction is Already Refunded)");
				}else if(Status.equalsIgnoreCase("4")){
					request.setAttribute("message","SUCCESSFULLY UPDATED (Transaction Status Made Success)");
				}else if(Status.equalsIgnoreCase("5")){
					request.setAttribute("message","SUCCESSFULLY UPDATED (Transaction Refunded Successfully)");
				}else if(Status.equalsIgnoreCase("6")){
					request.setAttribute("message","SUCCESSFULLY UPDATED (Transaction Status Made Success In Live Recharge)");
				}else if(Status.equalsIgnoreCase("7")){
					request.setAttribute("message","SUCCESSFULLY UPDATED (Transaction Refunded Successfully with Status Change)");
				}else if(Status.equalsIgnoreCase("8"))
				{
					request.setAttribute("message","ACTION NOT APPROVED REPORT PRODUCT TEAM");
				}
				return "getPage";
			}
		}catch(Exception e){
			System.out.println("Exception in EgenRefundAction class");
			request.setAttribute("message","Process aborted due to Technical Failure.");
			System.out.println(e.toString());
		}
		return "err";
	}
	
	public void setServletRequest(HttpServletRequest request){
		this.request=request;
		 
	}
	public void setServletResponse(HttpServletResponse response) {
		this.response=response;
			
	}
	public void setSession(Map session){
		session = this.getSession();
	}

	public Map getSession(){
		return session;
	}
}
