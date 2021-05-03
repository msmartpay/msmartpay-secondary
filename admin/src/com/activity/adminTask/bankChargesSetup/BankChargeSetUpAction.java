package com.activity.adminTask.bankChargesSetup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BankChargeSetUpAction extends ActionSupport implements
ServletRequestAware, ServletResponseAware{
	
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public String listOfBankCharges()
	{
		String result="";
		BankChargeSetUpDao dao=new BankChargeSetUpDao();
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
			if (value==1)	mode="Cash on Desk";
			if (value==2)	mode="Cash in Bank";
			if (value==3) 	mode="Cheque/DD";
			if (value==4) 	mode="Online E-Transfer";
			if (value==5) 	mode="NEFT/RTGS";
			if(value!=1)	bankName=request.getParameter("bankName");
			list=dao.listOfBankCharges(mode, bankName); 
			session.put("chargeslist", list);
			request.setAttribute("mode", mode);	
			return "success";
		}catch (Exception e) {
			request.setAttribute("message","Process aborted due to Technical Failure.");
			System.out.println("Exception in BankChargeSetUpAction");
			System.out.println(e.toString());
			return "err";
		}	
	}
	
	public String updateBankCharges()
	{
		String result="";
		String message="";
		BankChargeSetUpDao dao=new BankChargeSetUpDao();
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
		    for(int i=0; i<checkcharge.length; i++)
		    {
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
		     if(result.equalsIgnoreCase("success")){
		    	 request.setAttribute("message", "Proceess has been completed.");
		     }else{
		    	 request.setAttribute("message", "Process aborted due to Technical Failure.");
		     }
			return result;
		}catch (Exception e) {
			request.setAttribute("message","Process aborted due to Technical Failure.");
			System.out.println("Exception in CategoryCreationAction");
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
