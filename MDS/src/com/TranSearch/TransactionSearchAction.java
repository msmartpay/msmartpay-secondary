package com.TranSearch;

/**	Created By  	: 	Arshad
 * 	Created Date 	:	14/01/2013
 * 	Created Matter	: 	This class will give the details of All data according to a connection number
 */
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public final class TransactionSearchAction extends Action {
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			 HttpServletResponse response)throws Exception{
		HttpSession session=request.getSession(false);
		String userID=(String)session.getAttribute("mdId");
		try{
			if(userID=="" || userID==null){
				String message="Your login session has expired, Please relogin to continue";
				request.setAttribute("message",message);
				return (mapping.findForward("NotActivateAgent"));
			}
			String param=request.getParameter("param");
			System.out.println("TransactionSearchAction class param is "+param);
			if(param.equalsIgnoreCase("SearchTranPage")){
				return mapping.findForward("SerachTranPage");
			}
			else if(param.equalsIgnoreCase("SearchTranData")){
				
				String connectionNo=request.getParameter("connectionNo");
				
				TransactionSearchDao daoObj=new TransactionSearchDao();
				ArrayList<HashMap<String,String>> data=daoObj.getTranData(userID,connectionNo);
				if(data.size()<=0){
					request.setAttribute("message", "No transaction found for this connection number");
					return mapping.findForward("SearchTranData");
				}
				else{
					request.setAttribute("data", data);
					return mapping.findForward("SearchTranData");
				}
			}
			
		}catch(Exception e){
			System.out.println("Exception in TransactionSearchAction");
			e.printStackTrace();
		}
		return null;
	}

}
