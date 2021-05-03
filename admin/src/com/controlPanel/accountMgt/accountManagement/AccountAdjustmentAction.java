/*
 Class Property :  This class (AccountAdjustmentAction) is created to get request data for account adjustment data and process it accordingly.

  Created Date   : 25 jan-2012 at 1.00 PM.
  Created By     : Amit Pathak

  Updated Date   : 25 Dec-201
  Update By      :Amit Pathak

*/

package com.controlPanel.accountMgt.accountManagement;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.common.GenerateIdFunction;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;


public class AccountAdjustmentAction extends ActionSupport implements ModelDriven<Object>, ServletRequestAware,ServletResponseAware
{
	 
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private  AccountAdjusmentForm AccountAdjusmentForm=new AccountAdjusmentForm();
	
	public Object getModel()
	{
		return AccountAdjusmentForm;}
 
	@SuppressWarnings("unchecked")
	public String execute() throws Exception 
	{ 
		try 
		{ 
			Map session = ActionContext.getContext().getSession();
			String param=AccountAdjusmentForm.getParam();
			String userId=(String)session.get("userId");
			if(userId==null) 
			{
				request.setAttribute("message","Your Login Session has Expired. Please Login Again.");
				return "sessionExpire";
			}
			if(param.equalsIgnoreCase("AccountAdjustment")){
				return "AccountAdjustment";
			}
		  
			//-========================Block to  get details of particular user details============================----	
			else if(param.equalsIgnoreCase("AccountAdjustmentDetail"))
			{
				HashMap <String,Object> AccountManagentDetail=new HashMap <String,Object> ();
				String UserType=AccountAdjusmentForm.getUserType();
				String RequestUserId=AccountAdjusmentForm.getRequestUserId();
				HashMap dynamicDetails=(HashMap) session.get("dynamicDetails");
				String clientFlag=(String)dynamicDetails.get("clientFlag");
				
				String portalUserType=(String)session.get("loginType");
				
				String status="fail";
				if(portalUserType.equalsIgnoreCase("SuperAdmin"))
				{
					status="success";
				}
				else
				{
					status=AccountAdjustmentDao.validateId(RequestUserId, userId,UserType);
				}
				System.out.println("status : "+status);
				if(status.equalsIgnoreCase("success"))
				{
					if(UserType.equalsIgnoreCase("Agent"))
					{
						AccountManagentDetail=AccountAdjustmentDao.getAccountAgentData(clientFlag,RequestUserId);
					}
					else if(UserType.equalsIgnoreCase("Portal"))
					{
						AccountManagentDetail=AccountAdjustmentDao.getAccountPortalData(RequestUserId);
					} 
					else if(UserType.equalsIgnoreCase("API/EGEN Partner"))
					{
						AccountManagentDetail=AccountAdjustmentDao.getAccountAPIData(RequestUserId);
					} 
					else 
					{
						AccountManagentDetail=AccountAdjustmentDao.getAccountData(UserType,RequestUserId);
					}
					if(AccountManagentDetail.size()>1)
					{
						request.setAttribute("AccountManagentDetail",AccountManagentDetail);
						request.setAttribute("UserType", UserType);
						request.setAttribute("RequestUserId", RequestUserId);
					}else 
					{
						request.setAttribute("message","There is no data available for this User Id");	
					}
				}
				else
				{
					request.setAttribute("message","There is no data available for this User Id.Please enter a valid User ID.");
					return "AccountAdjustment";					
				}
				
				return "AccountAdjustment";
			}
		  
			//--------------------------Block to work on account adjustment--------------------------
			
			else if(param.equalsIgnoreCase("updateAccount"))
			{
				double amount= AccountAdjusmentForm.getAmount();
				if(amount<0)
				{
					request.setAttribute("message","Negative value not Accepted");	  
				}
				
				String loginType=(String)session.get("loginType");  
				GenerateIdFunction GenerateIdFunction=new GenerateIdFunction();
				HashMap <String,Object> AccountManagentDetail=new HashMap <String,Object> ();
				String UserType=AccountAdjusmentForm.getUserType();
				String RequestUserId=AccountAdjusmentForm.getRequestUserId();			  
				String actionType=AccountAdjusmentForm.getActionType();
				String internalRemark=AccountAdjusmentForm.getInternalRemark();
				String externalRemark=AccountAdjusmentForm.getExternalRemark();
				int userOnlyId=AccountAdjusmentForm.getUserOnlyId();
				String ipAddress=AccountAdjusmentForm.getIpAddress();
				String IDNo=GenerateIdFunction.getIdNo();
				
				//-=========================================Calling Update Method=======================================	
				if(loginType.equalsIgnoreCase("superadmin")||loginType.equalsIgnoreCase("activityAdmin")||loginType.equalsIgnoreCase("PortalAdmin"))
				{
					System.out.println("amount is "+amount);
					System.out.println("userId is "+userId);
					System.out.println("actionType is "+actionType);
					System.out.println("userOnlyId is "+userOnlyId);
					System.out.println("UserType is "+UserType);
					if(actionType.equalsIgnoreCase("debit"))
					{
						String status=AccountAdjustmentDao.CheckUserAmount(amount,actionType,userOnlyId,UserType);
						if(status.equalsIgnoreCase("invalid"))
						{
							request.setAttribute("message","Insufficient Balance.");
							return "AccountAdjustment";
						}
					}
					String Status=AccountAdjustmentDao.updateAgentAmount(IDNo,RequestUserId,amount,loginType,userId,actionType,internalRemark,externalRemark,userOnlyId,ipAddress,UserType);
					HashMap dynamicDetails=(HashMap) session.get("dynamicDetails");
					String clientFlag=(String)dynamicDetails.get("clientFlag");
					//-============================================Method call to show data on View after updating ================================
					if(UserType.equalsIgnoreCase("Agent")) 
					{
						AccountManagentDetail=AccountAdjustmentDao.getAccountAgentData(clientFlag,RequestUserId);
					}else if(UserType.equalsIgnoreCase("Portal"))
					{
						AccountManagentDetail=AccountAdjustmentDao.getAccountPortalData(RequestUserId);
					}else if(UserType.equalsIgnoreCase("API/EGEN Partner"))
					{
						AccountManagentDetail=AccountAdjustmentDao.getAccountAPIData(RequestUserId);
					}else
					{
						AccountManagentDetail=AccountAdjustmentDao.getAccountData(UserType,RequestUserId);
					}
		
					if(AccountManagentDetail.size()!=0)
					{
						request.setAttribute("AccountManagentDetail",AccountManagentDetail);
						request.setAttribute("UserType", UserType);
						request.setAttribute("RequestUserId", RequestUserId);
					}
					if(Status.equalsIgnoreCase("valid")) 
					{
						request.setAttribute("message","Successfully Updated"); 
					}else
					{
						request.setAttribute("message","Update was not successfull");   
					}
				}
				else
				{
					request.setAttribute("message","You are not Authorized for this service");
				}
				return "AccountAdjustment";  
			}
		}
		catch(Exception ex)
		{
			request.setAttribute("message","Process aborted due to Technical Failure.");
			System.out.println(ex.toString());
			System.out.println("Exception in AccountAdjustmentAction ");
			return "error";
		}
		return "error";
	}
	
	@Override
	 public void setServletRequest(HttpServletRequest httpServletRequest) 
	{
		this.request = httpServletRequest;
	}

	@Override
	public void setServletResponse(HttpServletResponse arg0) 
	{
		
	}

	 
}