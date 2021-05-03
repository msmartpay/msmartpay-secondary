package com.tradebalanceactivity.self;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.common.CommonServiceDao;
import com.common.ConvertUtility;
import com.formBean.adminUser.PortalUserTransactionFormBean;
import com.formBean.adminUser.UserAmountFormBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 *Created Date=3/App/2012,
 *
 * Created BY=Vibhor Kumar ,
 * 
 * Purpose=Action Class,
 * 
 * Save Deposit Information of Portal User ,
 * 
 * Updated Date="" ,Updated By=""
 */
public class TradeBalanceSelfActivity extends ActionSupport implements
ServletRequestAware, ServletResponseAware {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private TradeBalanceFormBean tbFormBean = new TradeBalanceFormBean();
	private PortalUserTransactionFormBean formBean=new PortalUserTransactionFormBean();


	ArrayList myAccount = null;

	public String execute() {
		String result = "err";
		Map session = ActionContext.getContext().getSession();

		try {
			String userId = (String) session.get("userId");
			if (userId == null) {
				request.setAttribute("message","Your Login Session has Expired. Please Login Again.");
				return "sessionExpire";
			}
			String loginType=(String)session.get("loginType");
			TradeBalanceSelfDAO dao=new TradeBalanceSelfDAO();
			long id = Long.parseLong(userId);
			double amount = Double.parseDouble(request.getParameter("amount"));
			if(amount<=0)
			{
				request.setAttribute("message", "Amount can not be < 1");
				return "success";
			}else{

				String transactionId = ConvertUtility.transactionId();
				String ipAddress=request.getRemoteAddr();
				result = dao.saveTBSelfRequest(id,transactionId,amount,ipAddress,loginType);
				if("Success".equalsIgnoreCase(result))
					request.setAttribute("message", "Process has been completed Successfully.");
				else
					request.setAttribute("message", result);
			}
			return "success";
		} catch (Exception e) {

			request.setAttribute("message", "Process aborted due to Technical Failure.");
			return "success";
		}

	}
	/**Action method,display the account info of logged user*/
	public String MyAccountInfo() {

		Map session = ActionContext.getContext().getSession();
		try {
			String userId = (String) session.get("userId");
			if (userId == null) {
				request.setAttribute("message","Your Login Session has Expired. Please Login Again.");
				return "sessionExpire";
			}
			UserAmountFormBean superBean = TradeBalanceSelfDAO.myAccountInfo(userId);
			session.put("AccountInfo", superBean);

			return "success";
		} catch (Exception e) {
			request.setAttribute("message", "Process aborted due to Technical Failure.");
			e.printStackTrace();
			return "err";
		}

	}
	/**Action method for return user info for trade balance push..*/
	public String viewRequestingUser()
	{

		Map session = ActionContext.getContext().getSession();
		try {
			String userId = (String) session.get("userId");
			if (userId == null) {
				request.setAttribute("message","Your Login Session has Expired. Please Login Again.");
				return "sessionExpire";
			}
			int portalId=0;
			if(!userId.equalsIgnoreCase("1"))
			{
				portalId=Integer.parseInt((String)session.get("adminUserPortalId"));
			}
			String loginType=(String)session.get("loginType");
			String type=request.getParameter("type");
			String id=request.getParameter("id");
			String viewType=request.getParameter("viewType");
			if("Chield".equalsIgnoreCase(viewType)){
				request.setAttribute("viewType", viewType);
				request.setAttribute("type", type);
				request.setAttribute("id", id);
			}

			HashMap map=TradeBalanceSelfDAO.viewRequestingUser(type, id,loginType,portalId);
			session.put("userMap", map);
			session.put("TBPush","Y");

			return "success";
		} catch (Exception e) {
			request.setAttribute("message", "Process aborted due to Technical Failure.");
			System.out.println(e.toString());
			return "err";
		}
	}
	/**Action method for Trade Balance Push*/
	public String tbTransferPush()
	{    

		String result="";
		TradeBalanceSelfDAO dao=new TradeBalanceSelfDAO();
		Map session = ActionContext.getContext().getSession();

		String message="";
		try {
			String userId = (String) session.get("userId");
			if (userId == null) {
				request.setAttribute("message","Your Login Session has Expired. Please Login Again.");
				return "sessionExpire";
			}
			String viewType=request.getParameter("viewType");

			if(session.get("TBPush")!=null){



				double amount=Double.parseDouble(request.getParameter("amount"));
				String name=request.getParameter("name");
				String intremark=request.getParameter("intremark");
				String extremark=request.getParameter("extremark");
				String userType=request.getParameter("userType");
				String loginType=(String)session.get("loginType");
				long loginId=Long.parseLong(userId);
				long portalId=0;
				if(!userId.equalsIgnoreCase("1"))
				{
					portalId=Long.parseLong((String)session.get("adminUserPortalId"));
				}
				long receiverId=Long.parseLong(request.getParameter("userId"));
				if(amount>0)
				{

					String ipAddress = request.getRemoteAddr();
					String transactionNo=ConvertUtility.transactionId();
					String txnNumber=ConvertUtility.transactionNo();

					result=dao.AdminPushBalance(receiverId, portalId, loginId, amount, name, intremark, extremark, userType, loginType, txnNumber, transactionNo, ipAddress);
					if(result.equalsIgnoreCase("success")){
						request.setAttribute("message","Process has been completed Successfully.");
					}else if(result.equalsIgnoreCase("InsufficientBalance")){
						request.setAttribute("message","Insufficient Balance.");
					}
					else if(result.equalsIgnoreCase("InvalidAmount"))
						request.setAttribute("message", "Amount should be greater than 0.");
					else if(result.equalsIgnoreCase("Failure"))
						request.setAttribute("message", "Process aborted due to Technical Failure.");
					else
						request.setAttribute("message", result);
				}
				else
				{
					request.setAttribute("message","Amount can not be less than 0.");	
				}

				session.remove("TBPush");


			}else{
				message="Process already completed Successfully.";
				session.remove("TBPush");
			}


			if("Chield".equalsIgnoreCase(viewType)){

				String loginType=(String)session.get("loginType");
				String type=request.getParameter("type");
				String id=request.getParameter("id");

				int portalId=0;
				if(!userId.equalsIgnoreCase("1"))
				{
					portalId=Integer.parseInt((String)session.get("adminUserPortalId"));
				}

				HashMap map=TradeBalanceSelfDAO.viewRequestingUser(type, id,loginType,portalId);
				session.put("userMap", map);
				session.put("TBPush","Y");

				request.setAttribute("viewType", viewType);
				request.setAttribute("type", type);
				request.setAttribute("id", id);


				request.setAttribute("chieldMessage", message);
				return "viewUserSuccess";
			}
			return "success";

		} catch (Exception e) {
			request.setAttribute("message", "Process aborted due to Technical Failure.");
			e.printStackTrace();
			session.remove("TBPush");
			return "err";
		}	
	}


	/**Action method for Trade Balance Push*/

	public String listOfBankCharges()
	{

		String result="";
		TradeBalanceSelfDAO dao=new TradeBalanceSelfDAO();
		ArrayList list=new ArrayList();
		Map session = ActionContext.getContext().getSession();

		try {
			String userId = (String) session.get("userId");
			if (userId == null) {
				request.setAttribute("message","Your Login Session has Expired. Please Login Again.");
				return "sessionExpire";
			}
			String mode="";
			String bankName="";
			int value=Integer.parseInt(request.getParameter("modeOfPayment"));
			if (value==1)         
				mode="Cash on Desk";
			if (value==2) 
				mode="Cash in Bank";
			if (value==3) 
				mode="Cheque/DD";
			if (value==4) 
				mode="Online E-Transfer";
			if (value==5) 
				mode="NEFT/RTGS";
			if(value!=1)
				bankName=request.getParameter("bankName");
			list=dao.listOfBankCharges(mode, bankName); 
			session.put("chargeslist", list);
			request.setAttribute("mode", mode);	
			return "success";
		}catch (Exception e) {
			request.setAttribute("message", "Process aborted due to Technical Failure.");
			System.out.println(e.toString());
			return "err";
		}	

	}
	public String updateBankCharges()
	{

		String result="";
		String message="";
		TradeBalanceSelfDAO dao=new TradeBalanceSelfDAO();
		Map session = ActionContext.getContext().getSession();
		try {
			String userId = (String) session.get("userId");
			if (userId == null) {
				request.setAttribute("message","Your Login Session has Expired. Please Login Again.");
				return "sessionExpire";
			}
			ArrayList chargeList=new ArrayList();			
			String[] checkcharge=(String[])request.getParameterValues("checkcharge"); 
			String[] limits=(String[])request.getParameterValues("limit");		    
			String[]charges=(String[])request.getParameterValues("charge");		   
			String[]opts=(String[])request.getParameterValues("opt");
			String[]snos=(String[])request.getParameterValues("sno");
			for(int i=0; i<checkcharge.length; i++){
				String selected=checkcharge[i];
				int No=Integer.parseInt(selected);
				String limit=(String)limits[No];
				String charge=(String)charges[No];
				String chargeType=(String)opts[No];
				int sno=Integer.parseInt((String)snos[No]);
				HashMap chargeMap=new HashMap();
				chargeMap.put("limit",limit );
				chargeMap.put("charge",charge );
				chargeMap.put("chargeType",chargeType );
				chargeMap.put("sno", sno);
				chargeList.add(chargeMap);
			}
			result=dao.updateBankCharges(chargeList);
			request.setAttribute("message", "Process has been completed Successfully.");

			return result;
		}catch (Exception e) {
			request.setAttribute("message", "Process aborted due to Technical Failure.");
			System.out.println(e.toString());
			return "err";
		}	

	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;

	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;

	}

}
