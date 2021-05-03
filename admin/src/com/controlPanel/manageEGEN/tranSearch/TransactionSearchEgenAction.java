package com.controlPanel.manageEGEN.tranSearch;

/**	Created By  	: 	Manoj
 * 	Created Date 	:	12/02/2013
 * 	Created Matter	: 	This class will give the details of All data according to a connection number For Egen
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public final class TransactionSearchEgenAction extends ActionSupport implements ServletRequestAware {
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	public String execute()throws Exception{
		
		try{
			
			Map session = ActionContext.getContext().getSession();
			String userID=(String)session.get("userId");
			if(userID=="" || userID==null){
				
				request.setAttribute("message","Your Login Session has Expired. Please Login Again.");
				return "sessionexpired";
			}
			String param=request.getParameter("param");
			System.out.println("TransactionSearchEgenAction class param is "+param);
			if(param.equalsIgnoreCase("SearchTranpage")){
				return "SerachTranPage";
			}
			else if(param.equalsIgnoreCase("SeachTranData")){
				String connectionNo=request.getParameter("connectionNo");
				//System.out.println(connectionNo);
				TransactionSearchEgenDao daoObj=new TransactionSearchEgenDao();
				ArrayList<HashMap<String,String>> data=daoObj.getTranData(connectionNo);
				if(data.size()<=0){
					request.setAttribute("message", "Data Not Available.");
					return "SerachTranData";
				}
				else{
					request.setAttribute("data", data);
					return "SerachTranData";
				}
			}
			
		}catch(Exception e){
			System.out.println("Exception in TransactionSearchEgenAction");
			request.setAttribute("message","Process aborted due to Technical Failure.");
			System.out.println(e.toString());
		}
		return "error";
	}
	public void setServletRequest(HttpServletRequest httpServletRequest) {
	       this.request = httpServletRequest;
	   }
}
