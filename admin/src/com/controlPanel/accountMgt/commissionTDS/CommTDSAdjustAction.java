package com.controlPanel.accountMgt.commissionTDS;

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

/* Created By     : Manoj khan
 * Created Date   : 23 Sep 2012 Sunday 11:20 AM
 * Created Matter : This class used for Commission and TDS Adjustment
 */

public final class CommTDSAdjustAction extends ActionSupport implements ModelDriven<Object>,ServletRequestAware,ServletResponseAware
{
	private static final long serialVersionUID = 1L;
	private HttpServletResponse response;
	private HttpServletRequest request;
	private CommTDSAdjusmentForm form=new CommTDSAdjusmentForm();
	
	CommTDSAdjDao daoObj=new CommTDSAdjDao();
	
	public Object getModel()
	{
		return form;
	}
	
	public String execute()
	{
		try
		{
			Map session= ActionContext.getContext().getSession();
			String param=form.getParam();
			String userId=(String)session.get("userId");
			if(userId==null)
			{
				request.setAttribute("message","Your Login Session has Expired. Please Login Again.");
				return "sessionExpire";
			}
			
			if(param.equalsIgnoreCase("CommTdsAdjustment"))
			{
				return "CommTdsAdjustment";
			}
			
			else if(param.equalsIgnoreCase("CommTDSAdjustmentDetail"))
			{
				HashMap <String,Object> AccountManagentDetail=new HashMap <String,Object> ();
				String UserType=form.getUserType();
				//System.out.println("user type is ::"+UserType);
				String RequestUserId=form.getRequestUserId();
				
				HashMap dynamicDetails=(HashMap) session.get("dynamicDetails");
				String clientFlag=(String)dynamicDetails.get("clientFlag");

				if(UserType.equalsIgnoreCase("Agent")) 
				{
					AccountManagentDetail=daoObj.getAccountAgentData(clientFlag,RequestUserId);
				}else if(UserType.equalsIgnoreCase("Portal")) 
				{
					//System.out.println("we are in portal");
					AccountManagentDetail=daoObj.getAccountPortalData(RequestUserId);
				} else if(UserType.equalsIgnoreCase("API/EGEN Partner"))
				{
					//System.out.println("we are calling this mathod");
					AccountManagentDetail=daoObj.getAccountAPIData(RequestUserId);
				} else 
				{
					//System.out.println("we are in ds and md");
					AccountManagentDetail=daoObj.getAccountData(UserType,RequestUserId);
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
				return "CommTdsAdjustment";
			}
			else if(param.equalsIgnoreCase("updateAccount"))
			{
				double amount= form.getAmount();
				if(amount<0)
				{
					request.setAttribute("message","Negative value not Accepted");	  
				}
				
				String loginType=(String)session.get("loginType");  
				GenerateIdFunction GenerateIdFunction=new GenerateIdFunction();
				HashMap <String,Object> AccountManagentDetail=new HashMap <String,Object> ();
				String UserType=form.getUserType();
				String RequestUserId=form.getRequestUserId();	
				String actionOn=form.getActionOn();
				String actionType=null;
				
				if(actionOn.equalsIgnoreCase("Commision"))
				{
					actionType="credit";
				}else if(actionOn.equalsIgnoreCase("TDS"))
				{
					actionType="debit";
				}else
				{
					actionType="";
				}
				
				String internalRemark=form.getInternalRemark();
				String externalRemark=form.getExternalRemark();
				int userOnlyId=form.getUserOnlyId();
				String ipAddress=form.getIpAddress();
//				System.out.println("RequestUserId is "+RequestUserId);
//				System.out.println("UserType is "+UserType);
//				System.out.println("ipAddress is "+ipAddress);
//				System.out.println("actionOn is "+actionOn);
//				System.out.println("actionType is "+actionType);
//				System.out.println("internalRemark is "+internalRemark);
//				System.out.println("externalRemark is "+externalRemark);
//				System.out.println("userOnlyId is "+userOnlyId);
				String IDNo=GenerateIdFunction.getIdNo();
				
				//-=========================================Calling Update Method=======================================	
				if(loginType.equalsIgnoreCase("superadmin")||loginType.equalsIgnoreCase("activityAdmin")||loginType.equalsIgnoreCase("PortalAdmin"))
				{
					String Status="";
					if(actionOn.equalsIgnoreCase("Commision"))
					{
						//System.out.println("we are in Commision");
						Status=daoObj.updateAgentCommAmount(IDNo,RequestUserId,amount,loginType,userId,actionType,internalRemark,externalRemark,userOnlyId,ipAddress,UserType); 
					}else if(actionOn.equalsIgnoreCase("TDS"))
					{
						//System.out.println("we are in TDS ");
						//System.out.println(actionType);
						String status=daoObj.CheckUserAmount(amount,actionType,userOnlyId,UserType);
						if(status.equalsIgnoreCase("invalid"))
						{
							request.setAttribute("message","Insufficient Balance.");
							return "CommTdsAdjustment";
						}
						Status=daoObj.updateAgentTDSAmount(IDNo,RequestUserId,amount,loginType,userId,actionType,internalRemark,externalRemark,userOnlyId,ipAddress,UserType);
					}
					//System.out.println("Status is "+Status);
					HashMap dynamicDetails=(HashMap) session.get("dynamicDetails");
					String clientFlag=(String)dynamicDetails.get("clientFlag");
					//-=========	===================================Method call to show data on View after updating ================================
					if(UserType.equalsIgnoreCase("Agent"))
					{
						AccountManagentDetail=daoObj.getAccountAgentData(clientFlag,RequestUserId);
				      }else if(UserType.equalsIgnoreCase("Portal"))
				      
				      {
					         AccountManagentDetail=daoObj.getAccountPortalData(RequestUserId);
				       }else if(UserType.equalsIgnoreCase("API/EGEN Partner"))
				       {
					         AccountManagentDetail=daoObj.getAccountAPIData(RequestUserId);
				         }else
				         {
				  		     AccountManagentDetail=daoObj.getAccountData(UserType,RequestUserId);
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
				return "CommTdsAdjustment";  
			}
			
		}catch(Exception e)
		{
			System.out.println("Exception in CommTDSAdjustAction class");
			request.setAttribute("message","Process aborted due to Technical Failure.");
			System.out.println(e.toString());
			return "err";           
		}
		return null;
	}

@Override
public void setServletRequest(HttpServletRequest httpServletRequest)
{
	this.request = httpServletRequest;
}

@Override
public void setServletResponse(HttpServletResponse arg0) {
	
	}
}
