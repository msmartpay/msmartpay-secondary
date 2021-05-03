package com.tradebalanceactivity.self;

import java.util.ArrayList;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;
import com.common.ConvertUtility;
import com.formBean.adminUser.PortalUserTransactionFormBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class TBPendingListAction  extends ActionSupport implements ModelDriven,
	ServletRequestAware, ServletResponseAware,ServletContextAware {

	Logger log=Logger.getLogger(TBPendingListAction.class);
	
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private ServletContext context = null;
	TradeBalanceFormBean FormBean = new TradeBalanceFormBean();
	PortalUserTransactionFormBean TxnBean=new PortalUserTransactionFormBean();
	PortalUserTransactionFormBean TxnBean2=new PortalUserTransactionFormBean();
	public Object getModel() {
		return TxnBean;	
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}		
	public ServletContext getServletContext() {
		return context;
	}
	public void setServletContext(ServletContext context) {
		this.context = context;
	}
	/** Action Method,provide the trade balance pending request list of MDS/portalUser(accept/decline) */
	public String showPendingList()
	{

		TradeBalanceSelfDAO dao=new TradeBalanceSelfDAO();	
		Map session = ActionContext.getContext().getSession();
		try 
		{
			String userId = (String) session.get("userId");
			if (userId == null) 
			{
				request.setAttribute("message","Your Login Session has Expired. Please Login Again.");
				return "sessionExpire";
			}
			String type=request.getParameter("type");
			if(type==null)
				type="";

			String loginType=(String)session.get("loginType");

			long adminUserId=Integer.parseInt((String)session.get("userId"));
			long portalid=0;
			if(adminUserId!=1){
				portalid=Integer.parseInt((String)session.get("adminUserPortalId"));
			}			
			ArrayList pendinglist=dao.pendingList(portalid,loginType,type);
			int size=pendinglist.size();
			if(size<=0){
				request.setAttribute("message", "No Pending Request Found.");
			}
			request.setAttribute("ListType", type);
			session.put("list", pendinglist);
			String message="";


			return "success";
		} 
		catch (Exception e) 
		{
			request.setAttribute("message", "Process aborted due to Technical Failure.");

			System.out.println(e.toString());
			return "err";
		}
	}	
	/** Action Method,update the trade balance credit request of MDS/APIClient/portalUser(accept/decline) */

	public String updatePendingRequest()
	{
		long loginPortal,portalId;
		HttpSession session;
		String userId = "",result = "";
		loginPortal = 0;
		portalId = 0;
		session = ServletActionContext.getRequest().getSession();

		if(session.getAttribute("userId") == null)
		{
			request.setAttribute("message", "Your Login Session has Expired. Please Login Again.");
			return "sessionExpire";
		}
		try
		{

			userId = (String)session.getAttribute("userId");
			TradeBalanceSelfDAO dao = new TradeBalanceSelfDAO();

			String type = request.getParameter("type");
			long requesterId = Long.parseLong(request.getParameter("userId"));
			long loginId = Long.parseLong(userId);
			String loginType = (String)session.getAttribute("loginType");
			String userType = request.getParameter("userType");
			String remarkAdmin = request.getParameter("remarkAdmin");
			String remark = request.getParameter("remarks");
			String status = request.getParameter("status");
			String TnxId = request.getParameter("transactionId");
			String bankname=request.getParameter("bankname");
			String modeofPayment=request.getParameter("mop");
			String tranAmount=request.getParameter("tranAmount");
			String serviceCharge=request.getParameter("bankCharg");
			String ipAddress = request.getRemoteAddr();


			if(!userType.equals("APIClient"))
				portalId = Long.parseLong(request.getParameter("portalId"));


			String transactionNo=ConvertUtility.transactionId();

			log.info("requesterId : "+requesterId +" : loginId : "+loginId+ " : loginType : "+loginType+" : userType"+userType+" : remarkAdmin : "+remarkAdmin+
					" : remark : "+remark+" : status : "+status+" : TnxId : "+TnxId+" : bankname : "+bankname+" : modeofPayment : "+modeofPayment+" : tranAmount : "+tranAmount+
					" : serviceCharge : "+serviceCharge+" : ipAddress : "+ipAddress);

			if(null==tranAmount || "".equalsIgnoreCase(tranAmount) || tranAmount.length()<=0){
				request.setAttribute("message", "Amount should not be empty or null.");
			}else if(Double.parseDouble(tranAmount)<=0){
				request.setAttribute("message", "Amount should be greater than 0.");
			}else if(null==tranAmount || "".equalsIgnoreCase(tranAmount) || tranAmount.length()<=0){
				request.setAttribute("message", "Amount should be greater than 0.");
			}else if(null==status || "".equalsIgnoreCase(status) || tranAmount.length()<=0){
				request.setAttribute("message", "Status should not be empty.");
			}else{


				result = dao.uspUpdateWBRequest(requesterId, Double.parseDouble(tranAmount), loginId,Double.parseDouble(serviceCharge),userType, TnxId, 
						transactionNo, ipAddress, bankname, modeofPayment, loginType,  TnxId, status, remarkAdmin, remark);

				log.info("result : "+result);

				if(result.equalsIgnoreCase("success"))
					request.setAttribute("message", "Process has been completed Successfully.");
				else if(result.equalsIgnoreCase("InsufficientBalance"))
					request.setAttribute("message", "Insufficient Balance.");
				else if(result.equalsIgnoreCase("InvalidAmount"))
					request.setAttribute("message", "Amount should be greater than 0.");
				else if(result.equalsIgnoreCase("Failure"))
					request.setAttribute("message", "Process aborted due to Technical Failure.");
				else
					request.setAttribute("message", result);

			}

			ArrayList pendinglist = dao.pendingList(portalId, loginType, type);
			request.setAttribute("ListType", type);
			session.setAttribute("list", pendinglist);



		}
		catch(Exception e)
		{
			request.setAttribute("message", "Process aborted due to Technical Failure.");
			return "err";
		}
		return "TBPending";
	}


}
