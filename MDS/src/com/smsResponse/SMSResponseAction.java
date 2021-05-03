package com.smsResponse;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class SMSResponseAction extends Action {
	
	public ActionForward execute(ActionMapping mapping,
			 ActionForm form,
			 HttpServletRequest request,
			 HttpServletResponse response)
throws Exception {
		
		
		HttpSession session=request.getSession(true);
		String mdId=(String)session.getAttribute("mdId");
		if(mdId==null){
			session.setAttribute("message","Your Login session has Expired. Please Login to Continue.");
			return mapping.findForward("sessionexp");
		}
		try
		{
			SMSResponseDao daoObj =new SMSResponseDao();
			String param=(String)request.getParameter("param");
			System.out.println("param is>>>>>>>>>>>>>>>>>>>>>>>>>>"+param);
			
			if("SMSResponse".equals(param))
			{
				return mapping.findForward("SMSResponse");
			}
			if(param.equalsIgnoreCase("getSMSData"))
			{
				String searchBy=request.getParameter("select_box");
				System.out.println("SeachBY  :"+searchBy);
				if ( searchBy.equalsIgnoreCase("mobNum")){
					String mobNum=request.getParameter("mobile");
					//System.out.println("mobNum ::"+mobNum);
					String DOJ=daoObj.getDOJ(mobNum);
					if(DOJ==null){
						DOJ=(String)session.getAttribute("DOJ");
					}
					session.setAttribute("DOJ", DOJ);
					ArrayList<?> getSMSDetails=daoObj.getSMSDetailsByMob(mdId,mobNum,DOJ);
            		//System.out.println("getDetailsRMobile ::"+getSMSDetails);
            		if(getSMSDetails.size()<=0){
            			request.setAttribute("message", "Details not Found.");
						return mapping.findForward("SMSResponse");
            		}
					request.setAttribute("getDetailsRMobile", getSMSDetails);
					return mapping.findForward("SMSData");
				}        	
				else if( searchBy.equalsIgnoreCase("agentId")){
					String userId=request.getParameter("userId");
					//System.out.println("User ID :"+userId);
					
					String mobNum=daoObj.getAgentMob(userId);
					if(mobNum==null || mobNum.equalsIgnoreCase("")){
						request.setAttribute("message", "Details not Found.");
						return mapping.findForward("SMSResponse");
					}		
					String DOJ=daoObj.getDOJ(mobNum);
					if(DOJ==null){
						DOJ=(String)session.getAttribute("DOJ");
					}
					session.setAttribute("DOJ", DOJ);
					
					ArrayList<?> getSMSDetails=daoObj.getSMSDetailsByAgentID(mdId,mobNum,DOJ);
					//System.out.println("getDetailsRMobile ::"+getSMSDetails);
					if(getSMSDetails.size()<=0){
						request.setAttribute("message", "Details not Found.");
						return mapping.findForward("SMSResponse");
					}
					request.setAttribute("getDetailsRMobile", getSMSDetails);
					return mapping.findForward("SMSData");			
				}   
				else{
					request.setAttribute("message", "Process aborted due to Technical Failure.");
					return mapping.findForward("SMSResponse");
				}
			}
		}catch(Exception e){
			System.out.println("Exception in AdminDepAgentAction"+e.toString());
			e.printStackTrace();	
		}	
		return null;
	}
}

