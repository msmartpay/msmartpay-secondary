package com.controlPanel;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public final class ControlPanelAction extends ActionSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	
	public String execute()
	throws Exception {
		
		request=ServletActionContext.getRequest();
		HttpSession session=request.getSession(false);
		
		String agentID="";
		
		try
		{
			
			if(session.getAttribute("agentID")==null){
				request.setAttribute("message", "Your Login Session has Expired. Please Login Again.");
				return "sessionExp";
			}
			agentID=(String)session.getAttribute("agentID");
			String param=request.getParameter("param");
			

			if("editProfile".equalsIgnoreCase(param)){
				
				ControlPanelDao daoObj=new ControlPanelDao();
				HashMap<String,String> mapdata=daoObj.getAgentViewProfileData(agentID);
				request.setAttribute("EditProfileData", mapdata);
				return "editProfile";
			}
			
			else if("updateProfile".equalsIgnoreCase(param)){
				String AgencyName=request.getParameter("AgencyName");
				String address=request.getParameter("address");
				String state=request.getParameter("state");
				String district=request.getParameter("district");
				String city=request.getParameter("city");
				String pinCode=request.getParameter("pinCode");
				String emailID=request.getParameter("emailID");
				String mobileNo=request.getParameter("mobileNo");
				HashMap<String,String> AgentDetailData=(HashMap)session.getAttribute("AgentDetailData");
				String emailIdOld=AgentDetailData.get("emailID");
				String mobileNoOld=AgentDetailData.get("agentMobileNo");
				String completeAgentID=AgentDetailData.get("completeAgentID");
				ControlPanelDao daoObj=new ControlPanelDao();
				String status=daoObj.updateUserProfile(agentID,AgencyName,address,state,district,city,pinCode,emailID,mobileNo,emailIdOld,mobileNoOld,completeAgentID);
				if("fail".equalsIgnoreCase(status)){
					request.setAttribute("message", "We are facing technical issues. Please try later.");
				}else if("success".equalsIgnoreCase(status)){
					HashMap<String,String> mapdata=daoObj.getAgentViewProfileData(agentID);
					request.setAttribute("message", "Your Profile has been updated Successfully.");
					request.setAttribute("EditProfileData", mapdata);
				}
				return "editProfile";
				
			}
			
			else if("pwdChangePage".equalsIgnoreCase(param)){
				
				return "pwdChangePage";
			}
			
			else if("changePass".equalsIgnoreCase(param)){
				ControlPanelDao daoObj=new ControlPanelDao();
				String currentPass=request.getParameter("currentPass");
				if(!daoObj.validateExistingPass(agentID, currentPass)){
					request.setAttribute("message", "Password is not matching with Existing Password. Please Try Again.");
					return "pwdChangePage";
				}
				String newPass=request.getParameter("newPass");
				if(currentPass.equalsIgnoreCase(newPass)){
					request.setAttribute("message", "Password is not same as Existing Password. Please Try Different.");
					return "pwdChangePage";
				}
				String confirmPass=request.getParameter("confirmPass");
				String status="";
				if(!newPass.equalsIgnoreCase(confirmPass)){
					request.setAttribute("message", "Mismatch between New password and Confirmed Password.");
					return "pwdChangePage";
				}
				else{
					String url="";
					
					status=daoObj.changePassword(newPass,agentID);
					if("fail".equalsIgnoreCase(status)){
						request.setAttribute("message", "We are facing technical issues. Please try later.");
						url="pwdChangePage";
					}else if("success".equalsIgnoreCase(status)){
						request.setAttribute("message", "Your password has been Successfully Changed.");
						url="agentHome";
					}
					return url;
				}
				
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		}
	return "ERROR";
	}
}
