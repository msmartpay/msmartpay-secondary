package com.controlPanel.profileMgt.selfUser;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public  class SelfUserProfileAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request; 
	private HttpServletResponse response;
	private Map session;
	
	public String execute() throws Exception{ 
		Map session=ActionContext.getContext().getSession();
		try{
			String userId=(String)session.get("userId");
			if(userId==null){
				request.setAttribute("message","Your Login Session has Expired. Please Login Again.");
				return "sessionExpire";
			}
			String param=request.getParameter("param");
			System.out.println("SelfUserProfileAction, param is : "+param);
			if(param.equals("viewSelfProfile")){
				SelfUserProfileDao daoObj=new SelfUserProfileDao();
				int id=Integer.parseInt(userId);
				HashMap<String,String> map=daoObj.showSelfProfile(id);
				if(map.size()<=0){
					request.setAttribute("message", "Process aborted due to Technical Failure.");
				}else{
					request.setAttribute("selfProfileData",map);
				}
				return "viewSelfProfile";
			}
			else if("checkEmailID".equalsIgnoreCase(param)){
				String checkEmailID=request.getParameter("checkEmailID");
				SelfUserProfileDao daoObj=new SelfUserProfileDao();
				String userNameStatus=daoObj.checkUserEmail(checkEmailID,userId);
				PrintWriter out=response.getWriter();
				  
				out.print(userNameStatus);
				return null;
			}
			else if("checkUserMobile".equalsIgnoreCase(param)){
				String Mobile=request.getParameter("Mobile");
				SelfUserProfileDao daoObj=new SelfUserProfileDao();
				String userMobileStatus=daoObj.checkMobileNo(Mobile,userId);
				PrintWriter out=response.getWriter();
				  
				out.print(userMobileStatus);
				return null;
			}else if("updateSelfUser".equalsIgnoreCase(param)){
				String type=(String)session.get("loginType");
				String firstName=request.getParameter("firstName");
				String lastName=request.getParameter("lastName");
				String dob=request.getParameter("dob");
				String gender=request.getParameter("gender");
				String companyType=request.getParameter("companyType");
				String companyName=request.getParameter("companyName");
				String emailId=request.getParameter("emailId");
				String mobileNo=request.getParameter("mobileNo");
				
				SelfUserProfileDao daoObj=new SelfUserProfileDao();
				String emailIdStatus=daoObj.checkUserEmail(emailId,userId);
				int id=Integer.parseInt(userId);
				if(emailIdStatus.equalsIgnoreCase("invalid")){
					request.setAttribute("message","Email ID is Duplicate.");
					HashMap<String,String> map=daoObj.showSelfProfile(id);
					request.setAttribute("selfProfileData",map);
					return "viewSelfProfile";
				}
				if(!"superAdmin".equalsIgnoreCase(type)){
					String userNameStatus=daoObj.checkUserName(emailId,userId);
					if(userNameStatus.equalsIgnoreCase("invalid")){
						request.setAttribute("message","User Login ID is Duplicate.");
						HashMap<String,String> map=daoObj.showSelfProfile(id);
						request.setAttribute("selfProfileData",map);
						return "viewSelfProfile";
					}
				}
				String mobileStatus=daoObj.checkMobileNo(mobileNo,userId);
				if(mobileStatus.equalsIgnoreCase("invalid")){
					request.setAttribute("message","Mobile Number is Duplicate.");
					HashMap<String,String> map=daoObj.showSelfProfile(id);
					request.setAttribute("selfProfileData",map);
					return "viewSelfProfile";
				}
				String addressLine1=request.getParameter("addressLine1");
				String addressLine2=request.getParameter("addressLine2");
				String state=request.getParameter("state");
				String District=request.getParameter("District");
				String city=request.getParameter("city");
				String pincode=request.getParameter("pincode");
				String panNo=request.getParameter("panNo");
				
				String status=daoObj.updateSelfProfile(firstName,lastName,dob,gender,companyType,companyName,emailId,mobileNo,addressLine1,addressLine2,state,District,city,pincode,panNo,userId,type);
				if(status.equalsIgnoreCase("success")){
					request.setAttribute("message", "Proceess has been completed.");
				}else {
					request.setAttribute("message", "Process aborted due to Technical Failure.");
				}
				
				HashMap<String,String> map=daoObj.showSelfProfile(id);
				request.setAttribute("selfProfileData",map);
				return "viewSelfProfile";
			}
		}catch(Exception e){
			request.setAttribute("message", "Process aborted due to Technical Failure.");
			System.out.println("Exception in SelfUserProfileAction");
			System.out.println(e.toString());
			return "viewSelfProfile";
		}
		return "err";   
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
