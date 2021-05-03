
/**
 *  Created Date---7/5/2012
 *  Created By---Amit Pathak 
 *  Purpose- The class(EgenServiceControlAction)is created for to manage wl clinet  service
 *  Updated Date-7/5/2012
 *  Updated By--Amit Pathak 
 *  Update Purpose-
*/

package com.controlPanel.manageEGEN.egenService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class EgenServiceControlAction extends ActionSupport implements ModelDriven<Object>,ServletRequestAware,ServletResponseAware  {

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	ServiceControlFormBean formBean=new ServiceControlFormBean();
	
	public Object getModel() {
		return formBean;
	}
	EgenServiceControlDao dao=new EgenServiceControlDao();
	//------------------Block to get data for all the wl client
	 
	public String EgenServiceControl()
	{
		HttpSession session = request.getSession(true);
		String userId=(String)session.getAttribute("userId");
		if(userId==null)
		{
			request.setAttribute("message","Your Login Session has Expired. Please Login Again.");
			return "sessionExpire";
		}
		try 
		{
			ArrayList<HashMap<String,Object>> getEgenIdDetails=dao.getclientDetails();
			if(getEgenIdDetails.size()>0) {
				request.setAttribute("getEgenIdDetails", getEgenIdDetails);
		}
		}
		catch (Exception e) 
		{
			request.setAttribute("message","Process aborted due to Technical Failure.");
			System.out.println(e.toString());
			System.out.println("Exception in EgenServiceControlAction ");
		}
		return "EgenServiceControl";
	}
	 
	 
	//-----------------------getUpdateWLServiceDetails -----------------------
	public String getUpdateEgenServiceDetails()
	{
		HttpSession session = request.getSession(true);
		String userId=(String)session.getAttribute("userId");
		if(userId==null){
			session.setAttribute("loginMessage","Session has been expired,Please relogin to continue.");
			return "sessionExpire";
		}
		try {
			String subservice="";
			String service=formBean.getService();
			if("rechargeService".equalsIgnoreCase(service))
			{
				subservice=formBean.getSubservice();	
			}
			else
			{
				subservice=formBean.getOthersubservice();	
			}
			
			String EnteruserId=formBean.getUserType();
			String Operator=formBean.getOperator();
		  
			ArrayList<HashMap<String,Object>> getDetails=dao.getUpdateEgenServiceDetails(EnteruserId,Operator,subservice,service);
			if(getDetails.size()>0){
				request.setAttribute("getDetails", getDetails); 
				request.setAttribute("service", service); 
				request.setAttribute("subservice", subservice); 
				request.setAttribute("EnteruserId", EnteruserId); 
				request.setAttribute("Operator", Operator); 
				return "UpdateEgenServiceControl";
			}
			else
			{
				request.setAttribute("message", "Data not found");
			}
			
		} catch (Exception e) {
			request.setAttribute("message","Process aborted due to Technical Failure.");
			System.out.println(e.toString());
			System.out.println("Exception in EgenServiceControlAction ");
		}
		return "EgenServiceControl";
	}
	 
	 
//------------------------------Method to update----------------
	 
	public String updateEgenService() {
		HttpSession session = request.getSession(true);
		String userId=(String)session.getAttribute("userId");
		String userType=(String)session.getAttribute("adminUserType");
		String UpdateBy=(userType)+userId;
		if(userId==null)
		{
			session.setAttribute("loginMessage","Session has been expired,Please relogin to continue.");
			return "sessionExpire";
		}
		try 
		{
			String OperatorName="";
			String subservice="";
			String tokenNumber="";
			String enternalresonse="";
			String ipAddress=formBean.getIpAddress();
			String[]updateCheckpartail=request.getParameterValues("updateCheckpartail");
			String[]internalmessage=request.getParameterValues("internalremark");
			String[]externalMessage=request.getParameterValues("response");
			String userid=(String)request.getParameter("EnteruserId");
			String actionTake=formBean.getActionTake();
			for(int i=0; i<updateCheckpartail.length; i++)
			{
				String partialDetails=updateCheckpartail[i];
				StringTokenizer st = new StringTokenizer(partialDetails,"&"); 
				while(st.hasMoreTokens()) 
				{ 
					OperatorName = st.nextToken();
					subservice= st.nextToken();
					tokenNumber = st.nextToken();
				} 
				int Idnumber=Integer.parseInt(tokenNumber);
				String internalRemark=(String)internalmessage[Idnumber];
				enternalresonse=(String)externalMessage[Idnumber];
				String update=dao.doupdate(internalRemark,enternalresonse,OperatorName,UpdateBy,actionTake,subservice,userid,ipAddress);
				if("Update".equalsIgnoreCase(update))
				{
					request.setAttribute("message","<font color=\"red\" style=\"font-family: Trebuchet MS; font-size: 10pt;\">Successfuly Updated</font>");	
				}
				else
				{
					request.setAttribute("message","<font color=\"red\" style=\"font-family: Trebuchet MS; font-size: 10pt;\">Updatdation failed</font>");	
				}
			}
				
		} catch (Exception e) {
			request.setAttribute("message","Process aborted due to Technical Failure.");
			System.out.println(e.toString());
			System.out.println("Exception in EgenServiceControlAction ");
		}
		ArrayList<HashMap<String,Object>> getEgenIdDetails=dao.getclientDetails();

		if(getEgenIdDetails.size()>0){
			request.setAttribute("getEgenIdDetails", getEgenIdDetails);
		}	
		return "EgenServiceControl";
	}
	public void setServletRequest(HttpServletRequest request) {
			this.request=request;
	}

	public void setServletResponse(HttpServletResponse response) {
		
	}

}
