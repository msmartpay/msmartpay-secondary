package com.TranSearch;

/**	Created By  	: 	Arshad
 * 	Created Date 	:	14/01/2013
 * 	Created Matter	: 	This class will give the details of All data according to a connection number
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public final class TransactionSearchAction extends ActionSupport implements ServletRequestAware {
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	public String execute()throws Exception{
		
		try{
			
			Map session = ActionContext.getContext().getSession();
			String userID=(String)session.get("distributorId");
			if(userID=="" || userID==null){
				String  message2="Your Login session has Expired. Please Login to Continue.";
				request.setAttribute("Loginmessage",message2);
				return "sessionexpired";
			}
			String param=request.getParameter("param");
			System.out.println("TransactionSearchAction class param is "+param);
			if(param.equalsIgnoreCase("SearchTranpage")){
				return "SerachTranPage";
			}
			if(param.equalsIgnoreCase("SeachTranData")){
				String connectionNo=request.getParameter("connectionNo");
				TransactionSearchDao daoObj=new TransactionSearchDao();
				ArrayList<HashMap<String,String>> data=daoObj.getTranData(userID,connectionNo);
				if(data.size()<=0){
					request.setAttribute("message", "Data is Not Available.");
					return "SerachTranData";
				}
				else{
					request.setAttribute("data", data);
					return "SerachTranData";
				}
			}
			
		}catch(Exception e){
			System.out.println("Exception in TransactionSearchAction");
			e.printStackTrace();
		}
		return "error";
	}
	public void setServletRequest(HttpServletRequest httpServletRequest) {
	       this.request = httpServletRequest;
	   }
}
