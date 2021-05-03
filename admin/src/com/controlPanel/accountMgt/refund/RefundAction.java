package com.controlPanel.accountMgt.refund;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public final class RefundAction extends ActionSupport implements ServletRequestAware,ServletResponseAware
{
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public String execute()
	{
		Map session= ActionContext.getContext().getSession();
		String param=(String)request.getParameter("param");
		System.out.println(" RefundAction class and param is :: "+param);
		RefundDao daoObj=new RefundDao();
		try
		{
			String userId=(String)session.get("userId");
			if(userId==null)
			{
				request.setAttribute("message","Your Login Session has Expired. Please Login Again.");
				return "sessionExpire";
			}
			
			if(param.equalsIgnoreCase("getRefundPage"))
			{
				return "getPage";
			}
			
			else if(param.equalsIgnoreCase("getTran"))
			{
				//System.out.println("we are in param getTran of refund class");
				String tranNo=(String)request.getParameter("Tran_no");
				String fromDate=(String)request.getParameter("fromDate");
				String toDate=(String)request.getParameter("toDate");				
				//System.out.println("tranID  is "+tranID);
				tranNo.trim();
					
				tranNo=daoObj.getTranNo(tranNo,fromDate,toDate);
				
				//System.out.println("vendorID no is "+vendorID);
				//System.out.println("tranNo is "+tranNo);
				if(!tranNo.equals(""))
				{
					HashMap map=daoObj.getTran(tranNo,fromDate,toDate);
					int size=map.size();
				
					if(size<=0)
					{
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
			else if(param.equalsIgnoreCase("getTranByTranNo"))
			{
				String tranNo=(String)request.getParameter("Tran_no");
				
				//System.out.println("vendorID no is "+vendorID);
				//System.out.println("tranNo is "+tranNo);
				if(!tranNo.equals(""))
				{
					HashMap map=daoObj.getTran(tranNo);
					int size=map.size();
				
					if(size<=0)
					{
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
				String tranID=(String)request.getParameter("tranID");
				String tranRefId=request.getParameter("tranRefId");
				String curStatus=(String)request.getParameter("status");
				String updatedStatus=(String) request.getParameter("updatedStatus");
				String service=(String) request.getParameter("service");
				String ipAdd=request.getParameter("ipAdd");
				String portalId="1";
				String loginType=(String)session.get("loginType");
				
				if(!loginType.equalsIgnoreCase("SuperAdmin"))
				{
					portalId=(String)session.get("adminUserPortalId");
				}
				
				
				if("R-DMR".equalsIgnoreCase(service)){
					
					if("Refund".equalsIgnoreCase(updatedStatus) || "SuccessRefund".equalsIgnoreCase(updatedStatus))
						updatedStatus="Failure";
					
					String IpAddress=request.getRemoteAddr();
					String updateStatus=daoObj.RBLRefund(tranID, updatedStatus, service, IpAddress);
					
					request.setAttribute("message",updateStatus);
					
				}else{
					//update success transaction to pending
					String Status="";
					if(updatedStatus.equalsIgnoreCase("SuccessRefund")){
						Status=daoObj.updateSuccessTran(tranID,curStatus,updatedStatus,ipAdd,portalId);
					}else{
						Status=daoObj.updateTran(tranID,curStatus,updatedStatus,ipAdd,portalId,tranRefId);
					}
					//System.out.println("status is "+Status);
					if(Status.equalsIgnoreCase("NotUpdated"))
					{
						request.setAttribute("message","Transaction can not be succefully updated due to some technical problem.");
					}else if(Status.equalsIgnoreCase("0"))
					{
						request.setAttribute("message","ACTION NOT ALLOWED");
					}else if(Status.equalsIgnoreCase("1"))
					{
						request.setAttribute("message","NO ACTION TAKEN (Success in both Table)");
					}else if(Status.equalsIgnoreCase("2"))
					{
						request.setAttribute("message","SUCCESSFULLY UPDATED (Transaction Status Made Success in Both Table)");
					}else if(Status.equalsIgnoreCase("3"))
					{
						request.setAttribute("message","NO ACTION TAKEN (Transaction is Already Refunded)");
					}else if(Status.equalsIgnoreCase("4"))
					{
						request.setAttribute("message","SUCCESSFULLY UPDATED (Transaction Status Made Success)");
					}else if(Status.equalsIgnoreCase("5"))
					{
						request.setAttribute("message","SUCCESSFULLY UPDATED (Transaction Refunded Successfully)");
					}else if(Status.equalsIgnoreCase("6"))
					{
						request.setAttribute("message","SUCCESSFULLY UPDATED (Transaction Status Made Success In Live Recharge)");
					}else if(Status.equalsIgnoreCase("7"))
					{
						request.setAttribute("message","SUCCESSFULLY UPDATED (Transaction Refunded Successfully with Status Change)");
					}else if(Status.equalsIgnoreCase("8"))
					{
						request.setAttribute("message","SUCCESSFULLY UPDATED (Transaction Status Made Failure in Live Recharge)");
					}else if(Status.equalsIgnoreCase("9"))
					{
						request.setAttribute("message","ACTION NOT APPROVED REPORT PRODUCT TEAM");
					}
				}
				
				 
				return "getPage";
			}
		}catch(Exception e)
		{
			System.out.println("Exception in RefundAction class");
			request.setAttribute("message","Process aborted due to Technical Failure.");
			System.out.println(e.toString());
			e.printStackTrace();
		}
		return "err";
	}
	public void setServletRequest(HttpServletRequest httpServletRequest) 
	{
		this.request = httpServletRequest;
	}

	public void setServletResponse(HttpServletResponse response) 
	{
		this.response=response;
	}
	
	public HttpServletResponse getServletResponse()
	{
		return response;
	}
}
