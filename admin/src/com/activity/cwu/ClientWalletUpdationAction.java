package com.activity.cwu;

/**
 *  Created By     : Manoj
 *  Created Date   : 2013/02/26
 *  Created Matter : To provide the functionality of update the balance of client Admin 
 */

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.common.ConvertUtility;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public final class ClientWalletUpdationAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Map session;
	
	public String execute() throws Exception{
		
		session=ActionContext.getContext().getSession();
		try{
			String userId=(String)session.get("userId");
			if(userId==null){
				request.setAttribute("message","Your Login Session has Expired. Please Login Again.");
				return "sessionExpire";
			}
			String param=request.getParameter("param");
			System.out.println("ClientWalletUpdationAction and param is "+param);
			
			if(param.equalsIgnoreCase("getPage")){
				return "walletPage";
			}else if(param.equalsIgnoreCase("createClient")){
				return "createClient";
			}
			else if(param.equalsIgnoreCase("updateAmount")){
				
				String loginType=(String)session.get("loginType");
				if(loginType.equalsIgnoreCase("SuperAdmin"))
				{
					String clientName=request.getParameter("clientName");
					String clientUrl=request.getParameter("clientUrl");
					double amount=Double.parseDouble(request.getParameter("amount"));
					String ipAdd=request.getParameter("ipAdd");
					//System.out.println("clientName is "+clientName +"clientUrl is "+clientUrl+ " amount is "+amount);
				
					String transactionNo = ConvertUtility.transactionId();
					//System.out.println(transactionNo);
					String message="";
				
					ClientWalletUpdationDao daoObj=new ClientWalletUpdationDao();
					String Status=daoObj.updateClientAmount(clientName,clientUrl,amount,loginType,transactionNo,ipAdd);
					//System.out.println(Status);
					
					if(Status.equalsIgnoreCase("fail")){
						message="Due to some technical problem Transcation can not be updated successfully.";
					}else if(Status.equalsIgnoreCase("Success")){
						message="Transaction has been successfully updated";
					}else if(Status.equalsIgnoreCase("DBNA")){
						message="No such user exist please enter correct Client name and URl.";
					}
					request.setAttribute("message", message);
					return "walletPage";
				}else{
					request.setAttribute("message","You are Not Authorized to do this Activity.");
					return "walletPage";
				}
			}else{
				request.setAttribute("message","You are Not Authorized to do this Activity.");
				return "walletPage";
			}
		}
		catch(Exception e){
			System.out.println("Exception in ClientWalletUpdationAction");
			request.setAttribute("message","Process aborted due to Technical Failure.");
			System.out.println(e.toString());
			return "err";
		}
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
