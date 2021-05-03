package com.controlPanel.userActivation.corporateUser.whitelabelClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


/**
* Updated By :Manoj
* Updated Date : 9 July 2013
* Updated Matter : Optimization of code and DB
*/
public  class WhiteLabelClientActivationAction extends ActionSupport implements ServletRequestAware,ServletResponseAware
{
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
			System.out.println("WhiteLabelClientActivationAction, param is:"+param);
			if(param.equals("searchUser")){
				//session.remove("noOfRecords");
				session.remove("pageSize");
				session.remove("pageNumber");
				session.remove("noOfPages");
				session.remove("userType");
				session.remove("status");
				session.remove("portalId");
				session.remove("buttonStatus");
				session.remove("advanceSearchOption");
				int pageNumber=1;
				int pageSize=100;
				session.put("pageNumber",pageNumber);
				session.put("pageSize",pageSize);
				return "searchUser";
			}
			else if(param.equals("searchUserType")){
				session.remove("noOfRecords");					 
				session.remove("noOfPages");
				session.remove("userType");
				session.remove("status");
				session.remove("portalId");
				session.remove("buttonStatus");
				session.remove("advanceSearchOption");
				String searchUserType=(String)request.getParameter("userType");
//				System.out.println("searchUserType>>>>>>>>>>>>>>>>>>>>>>>"+searchUserType);
				String advanceSearchOption="N";
				WhiteLabelClientActivationDao daoObj=new WhiteLabelClientActivationDao();
				if(advanceSearchOption.equalsIgnoreCase("N"))
				{
					if(searchUserType.equalsIgnoreCase("wlClient"))
					{
						String  whiteLabelClientId=(String)request.getParameter("userId");
						//System.out.println("whiteLabelClientId>>>>>>>>>>>>>>>>>>>>>>>"+whiteLabelClientId);
						if(whiteLabelClientId==null || whiteLabelClientId=="")
						{
							session.remove("whiteLabelClientActivationUserId");
							String status=(String)request.getParameter("status");
							session.put("buttonStatus",status);
							int pageNumber=(Integer)session.get("pageNumber");
							if(status.equals("all")){	
								int noOfRecords=daoObj.getNoOfRecordsAllWhiteLabelClientDetails();
								if(noOfRecords==0){
								request.setAttribute("message","No Records found.");
									return "searchUser";
								}
								session.put("noOfRecords",noOfRecords);
								pageNumber=Integer.parseInt(request.getParameter("pageNumber"));
								//System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
								ArrayList whiteLabelClientDetails=daoObj.getAllWhiteLabelClientDetail(pageNumber);
								session.put("userType",searchUserType);
								session.put("pageNumber",pageNumber);
								session.put("status",status);
								session.put("advanceSearchOption",advanceSearchOption);
								session.put("whiteLabelClientSearchList",whiteLabelClientDetails);
								return "whiteLabelClientList";
							}
							if(!status.equals("all"))
							{
								if(status.equalsIgnoreCase("blocked") || status.equalsIgnoreCase("unblocked")){
									if(pageNumber==1)
									{
										int noOfRecords=daoObj.getNoOfRecordsAllWhiteLabelClientDetailsBlockStatusWise(status);
										if(noOfRecords==0)
										{
											request.setAttribute("message","No Records found.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
									//System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
									ArrayList whiteLabelClientDetails=daoObj.getWhiteLabelClientDetailsBlockStatusWise(status,pageNumber);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("whiteLabelClientSearchList",whiteLabelClientDetails);
									return "whiteLabelClientList";
								}
								else{
									if(pageNumber==1)
									{
										int noOfRecords=daoObj.getNoOfRecordsAllWhiteLabelClientDetailsStatusWise(status);
										if(noOfRecords==0)
										{
											request.setAttribute("essage","No Records found.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
									//System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
									ArrayList whiteLabelClientDetails=daoObj.getWhiteLabelClientDetailsStatusWise(status,pageNumber);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("whiteLabelClientSearchList",whiteLabelClientDetails);
									return "whiteLabelClientList";
								}
							}
						}
						else{
							int pageNumber=(Integer)session.get("pageNumber");
							if(pageNumber==1){
								int noOfRecords=daoObj.getNoOfRecordsWhiteLabelClientDetailsClientIdWise(whiteLabelClientId);
								if(noOfRecords==0){
									request.setAttribute("message","No Records found.");
									return "searchUser";
								}
								session.put("noOfRecords",noOfRecords);
							}
							ArrayList whiteLabelClientDetails=daoObj.getWhiteLabelClientDetailsClientIdWise(whiteLabelClientId,pageNumber);
							session.put("userType",searchUserType);
							session.put("pageNumber",pageNumber);
							session.put("whiteLabelClientActivationUserId",whiteLabelClientId);
							session.put("buttonStatus","all");
							session.put("advanceSearchOption",advanceSearchOption);
							session.put("whiteLabelClientSearchList",whiteLabelClientDetails);
							return "whiteLabelClientList";
						}
					}
				}
			}	
			else if(param.equalsIgnoreCase("activateWhiteLabelClient"))
			{
				String[] userIds=request.getParameterValues("userIds");
				WhiteLabelClientActivationDao daoObj=new WhiteLabelClientActivationDao();
				daoObj.activateWhiteLabelClient(userIds,session);
				ArrayList activatedWhiteLabelClient=(ArrayList)session.get("activatedWhiteLabelClient");
				ArrayList deactivatedWhiteLabelClient=(ArrayList)session.get("deactivatedWhiteLabelClient");
				String message="";
				if(activatedWhiteLabelClient.size()>0)
				{
					message="Selected User/s has been Activated.";
				}
				else{
					message="Process aborted due to Technical Failure.";
				}
				request.setAttribute("message",message);
				String searchUserType=(String)session.get("userType");
				String advanceSearchOption="N";
				if(advanceSearchOption.equalsIgnoreCase("N")){
					if(searchUserType.equalsIgnoreCase("wlClient"))
					{
						String  whiteLabelClientId=(String)session.get("whiteLabelClientActivationUserId");
						//System.out.println("whiteLabelClientId is>>>>>>>>>>>>>in action class>>"+whiteLabelClientId);
						if(whiteLabelClientId==null || whiteLabelClientId=="")
						{
							session.remove("whiteLabelClientActivationUserId");
							String status=(String)session.get("status");
							//System.out.println("status is>>>>>>>>>>>>>in action class>>"+status);
							session.put("buttonStatus",status);
							int pageNumber=(Integer)session.get("pageNumber");
							if(status.equals("all"))
							{
								if(pageNumber==1)
								{
									int noOfRecords=daoObj.getNoOfRecordsAllWhiteLabelClientDetails();
									if(noOfRecords==0)
									{
										request.setAttribute("message","No Records found.");
										return "searchUser";
									}
									session.put("noOfRecords",noOfRecords);
								}
								//System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
								ArrayList whiteLabelClientDetails=daoObj.getAllWhiteLabelClientDetail(pageNumber);
								session.put("userType",searchUserType);
								session.put("pageNumber",pageNumber);
								session.put("status",status);
								session.put("advanceSearchOption",advanceSearchOption);
								session.put("whiteLabelClientSearchList",whiteLabelClientDetails);
								return "whiteLabelClientList";
							}
							if(!status.equals("all"))
							{
								if(status.equalsIgnoreCase("blocked") || status.equalsIgnoreCase("unblocked")){
									if(pageNumber==1){
										int noOfRecords=daoObj.getNoOfRecordsAllWhiteLabelClientDetailsBlockStatusWise(status);
										if(noOfRecords==0)
										{
											request.setAttribute("message","No Records found.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
									//System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
									ArrayList whiteLabelClientDetails=daoObj.getWhiteLabelClientDetailsBlockStatusWise(status,pageNumber);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("whiteLabelClientSearchList",whiteLabelClientDetails);
									return "whiteLabelClientList";
								}
								else{
									if(pageNumber==1)
									{
										int noOfRecords=daoObj.getNoOfRecordsAllWhiteLabelClientDetailsStatusWise(status);
										if(noOfRecords==0)
										{
											session.put("whiteLabelClientActivationMessage","No Records found.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
									//System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
									ArrayList whiteLabelClientDetails=daoObj.getWhiteLabelClientDetailsStatusWise(status,pageNumber);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("whiteLabelClientSearchList",whiteLabelClientDetails);
									return "whiteLabelClientList";
								}
							}
						}
						else{
							int pageNumber=(Integer)session.get("pageNumber");
							if(pageNumber==1)
							{
								int noOfRecords=daoObj.getNoOfRecordsWhiteLabelClientDetailsClientIdWise(whiteLabelClientId);
								if(noOfRecords==0)
								{
									request.setAttribute("message","No Records found.");
								  return "searchUser";
								}
								session.put("noOfRecords",noOfRecords);
							}
							ArrayList whiteLabelClientDetails=daoObj.getWhiteLabelClientDetailsClientIdWise(whiteLabelClientId,pageNumber);
							session.put("userType",searchUserType);
							session.put("pageNumber",pageNumber);
							session.put("whiteLabelClientActivationUserId",whiteLabelClientId);
							session.put("buttonStatus","all");
							session.put("advanceSearchOption",advanceSearchOption);
							session.put("whiteLabelClientSearchList",whiteLabelClientDetails);
							return "whiteLabelClientList";
						}
					}
				}
			}
			else if(param.equalsIgnoreCase("deactivateWhiteLabelClient"))
			{
				HashMap dynamicDetails=(HashMap) session.get("dynamicDetails");
				String clientFlag=(String)dynamicDetails.get("clientFlag");
				String[] userIds=request.getParameterValues("userIds");
				
				WhiteLabelClientActivationDao daoObj=new WhiteLabelClientActivationDao();
				daoObj.deactivateWhiteLabelClient(userIds,session);
				ArrayList activatedWhiteLabelClient=(ArrayList)session.get("activatedWhiteLabelClient");
				ArrayList deactivatedWhiteLabelClient=(ArrayList)session.get("deactivatedWhiteLabelClient");
				String message="";
				if(deactivatedWhiteLabelClient.size()>0)
				{
					message="Selected User/s has been Deactivated.";
				}
				else{
					message="Process aborted due to Technical Failure.";
				}
				request.setAttribute("message",message);
				String searchUserType=(String)session.get("userType");
				//System.out.println("searchUserType is>>>>>>>>>>>>>in action class>>"+searchUserType);
				String advanceSearchOption="N";
				//System.out.println("advanceSearchOption is>>>>>>>>>>>>>in action class>>"+advanceSearchOption);
				if(advanceSearchOption.equalsIgnoreCase("N"))
				{
					if(searchUserType.equalsIgnoreCase("wlClient"))
					{
						String  whiteLabelClientId=(String)session.get("whiteLabelClientActivationUserId");
						//System.out.println("whiteLabelClientId is>>>>>>>>>>>>>in action class>>"+whiteLabelClientId);
					   if(whiteLabelClientId==null || whiteLabelClientId=="")
					   {
						   session.remove("whiteLabelClientActivationUserId");
						   String status=(String)session.get("status");
						   //System.out.println("status is>>>>>>>>>>>>>in action class>>"+status);
						   session.put("buttonStatus",status);
						   int pageNumber=(Integer)session.get("pageNumber");
						   if(status.equals("all")){
							   if(pageNumber==1){
								   int noOfRecords= daoObj.getNoOfRecordsAllWhiteLabelClientDetails();
								   if(noOfRecords==0){
									   request.setAttribute("message","No Records found.");
									   return "searchUser";
								   }
								   session.put("noOfRecords",noOfRecords);
							   }
							   //System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
							   ArrayList whiteLabelClientDetails= daoObj.getAllWhiteLabelClientDetail(pageNumber);
							   session.put("userType",searchUserType);
							   session.put("pageNumber",pageNumber);
							   session.put("status",status);
							   session.put("advanceSearchOption",advanceSearchOption);
							   session.put("whiteLabelClientSearchList",whiteLabelClientDetails);
							   return "whiteLabelClientList";
						   }
						   if(!status.equals("all"))
						   {
							   if(status.equalsIgnoreCase("blocked") || status.equalsIgnoreCase("unblocked")){
								   if(pageNumber==1)
								   {
									   int noOfRecords= daoObj.getNoOfRecordsAllWhiteLabelClientDetailsBlockStatusWise(status);
									   if(noOfRecords==0)
									   {
										   request.setAttribute("message","No Records found.");
										   return "searchUser";
									   }
									   session.put("noOfRecords",noOfRecords);
								   }
								   //System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
								   ArrayList whiteLabelClientDetails= daoObj.getWhiteLabelClientDetailsBlockStatusWise(status,pageNumber);
								   session.put("userType",searchUserType);
								   session.put("pageNumber",pageNumber);
								   session.put("status",status);
								   session.put("advanceSearchOption",advanceSearchOption);
								   session.put("whiteLabelClientSearchList",whiteLabelClientDetails);
								   return "whiteLabelClientList";
							   }
							   else{
								   if(pageNumber==1){
									   int noOfRecords= daoObj.getNoOfRecordsAllWhiteLabelClientDetailsStatusWise(status);
									   if(noOfRecords==0)
									   {
										   request.setAttribute("message","No Records found.");
										   return "searchUser";
									   }
									   session.put("noOfRecords",noOfRecords);
								   }
								   //System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
								   ArrayList whiteLabelClientDetails= daoObj.getWhiteLabelClientDetailsStatusWise(status,pageNumber);
								   session.put("userType",searchUserType);
								   session.put("pageNumber",pageNumber);
								   session.put("status",status);
								   session.put("advanceSearchOption",advanceSearchOption);
								   session.put("whiteLabelClientSearchList",whiteLabelClientDetails);
								   return "whiteLabelClientList";
							   }
						   }
					   }
					   else{
						   int pageNumber=(Integer)session.get("pageNumber");
						   if(pageNumber==1)
						   {
							   int noOfRecords= daoObj.getNoOfRecordsWhiteLabelClientDetailsClientIdWise(whiteLabelClientId);
							   if(noOfRecords==0)
							   {
								   request.setAttribute("message","No Records found.");
								   return "searchUser";
							   }
							   session.put("noOfRecords",noOfRecords);
						   }
						   ArrayList whiteLabelClientDetails= daoObj.getWhiteLabelClientDetailsClientIdWise(whiteLabelClientId,pageNumber);
						   session.put("userType",searchUserType);
						   session.put("pageNumber",pageNumber);
						   session.put("whiteLabelClientActivationUserId",whiteLabelClientId);
						   session.put("buttonStatus","all");
						   session.put("advanceSearchOption",advanceSearchOption);
						   session.put("whiteLabelClientSearchList",whiteLabelClientDetails);
						   return "whiteLabelClientList";
					   }
					}
				}
				return "whiteLabelClientList";
			}	
			else if(param.equalsIgnoreCase("blockWhiteLabelClient"))
			{
				HashMap dynamicDetails=(HashMap) session.get("dynamicDetails");
				String clientFlag=(String)dynamicDetails.get("clientFlag");
				String[] userIds=request.getParameterValues("userIds");
				
				WhiteLabelClientActivationDao daoObj=new WhiteLabelClientActivationDao();
				daoObj.blockWhiteLabelClient(userIds,session);
				ArrayList blockedWhiteLabelClient=(ArrayList)session.get("blockedWhiteLabelClient");
				ArrayList unblockedWhiteLabelClient=(ArrayList)session.get("unblockedWhiteLabelClient");
				String message="";
				if(blockedWhiteLabelClient.size()>0)
				{
					message="Selected User/s has been Blocked.";
				}
				else{
					message="Process aborted due to Technical Failure.";
				}
				
				request.setAttribute("message",message);
				String searchUserType=(String)session.get("userType");
				//System.out.println("searchUserType is>>>>>>>>>>>>>in action class>>"+searchUserType);
				String advanceSearchOption="N";
				//System.out.println("advanceSearchOption is>>>>>>>>>>>>>in action class>>"+advanceSearchOption);
				if(advanceSearchOption.equalsIgnoreCase("N"))
				{
					if(searchUserType.equalsIgnoreCase("wlClient")){
						String  whiteLabelClientId=(String)session.get("whiteLabelClientActivationUserId");
						//System.out.println("whiteLabelClientId is>>>>>>>>>>>>>in action class>>"+whiteLabelClientId);
						if(whiteLabelClientId==null || whiteLabelClientId=="")
						{
							session.remove("whiteLabelClientActivationUserId");
							String status=(String)session.get("status");
							//System.out.println("status is>>>>>>>>>>>>>in action class>>"+status);
							session.put("buttonStatus",status);
							int pageNumber=(Integer)session.get("pageNumber");
							if(status.equals("all"))
							{
								if(pageNumber==1)
								{
									int noOfRecords=daoObj.getNoOfRecordsAllWhiteLabelClientDetails();
									if(noOfRecords==0)
									{
										request.setAttribute("message","No Records found.");
										return "searchUser";
									}
									session.put("noOfRecords",noOfRecords);
								}
								//System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
								ArrayList whiteLabelClientDetails=daoObj.getAllWhiteLabelClientDetail(pageNumber);
								session.put("userType",searchUserType);
								session.put("pageNumber",pageNumber);
								session.put("status",status);
								session.put("advanceSearchOption",advanceSearchOption);
								session.put("whiteLabelClientSearchList",whiteLabelClientDetails);
								return "whiteLabelClientList";
							}
							if(!status.equals("all")){
								if(status.equalsIgnoreCase("blocked") || status.equalsIgnoreCase("unblocked")){
									if(pageNumber==1)
									{
										int noOfRecords=daoObj.getNoOfRecordsAllWhiteLabelClientDetailsBlockStatusWise(status);
										if(noOfRecords==0)
										{
											request.setAttribute("message","No Records found.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
									//System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
									ArrayList whiteLabelClientDetails=daoObj.getWhiteLabelClientDetailsBlockStatusWise(status,pageNumber);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("whiteLabelClientSearchList",whiteLabelClientDetails);
									return "whiteLabelClientList";
								}
								else{
									if(pageNumber==1)
									{
										int noOfRecords=daoObj.getNoOfRecordsAllWhiteLabelClientDetailsStatusWise(status);
										if(noOfRecords==0)
										{
											request.setAttribute("message","No Records found.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
									//System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
									ArrayList whiteLabelClientDetails=daoObj.getWhiteLabelClientDetailsStatusWise(status,pageNumber);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("whiteLabelClientSearchList",whiteLabelClientDetails);
									return "whiteLabelClientList";
								}
							}
						}
						else{
							int pageNumber=(Integer)session.get("pageNumber");
							if(pageNumber==1)
							{
								int noOfRecords=daoObj.getNoOfRecordsWhiteLabelClientDetailsClientIdWise(whiteLabelClientId);
								if(noOfRecords==0)
								{
									request.setAttribute("message","No Records found.");
									return "searchUser";
								}
								session.put("noOfRecords",noOfRecords);
							}
							ArrayList whiteLabelClientDetails=daoObj.getWhiteLabelClientDetailsClientIdWise(whiteLabelClientId,pageNumber);
							session.put("userType",searchUserType);
							session.put("pageNumber",pageNumber);
							session.put("whiteLabelClientActivationUserId",whiteLabelClientId);
							session.put("buttonStatus","all");
							session.put("advanceSearchOption",advanceSearchOption);
							session.put("whiteLabelClientSearchList",whiteLabelClientDetails);
							return "whiteLabelClientList";
						}
					}
				}
				return "whiteLabelClientList";
			}	
			else if(param.equalsIgnoreCase("unblockWhiteLabelClient"))
			{
				HashMap dynamicDetails=(HashMap) session.get("dynamicDetails");
				String[] userIds=request.getParameterValues("userIds");
				
				WhiteLabelClientActivationDao daoObj=new WhiteLabelClientActivationDao();
				daoObj.unblockWhiteLabelClient(userIds,session);
				ArrayList blockedWhiteLabelClient=(ArrayList)session.get("blockWhiteLabelClientList");
				ArrayList unblockedWhiteLabelClient=(ArrayList)session.get("unblockWhiteLabelClientList");
				String message="";
				if(unblockedWhiteLabelClient.size()>0)
				{
					message="Selected User/s has been Unblocked.";
				}
				else{
					message="Process aborted due to Technical Failure.";
				}
				
				request.setAttribute("message",message);
				String searchUserType=(String)session.get("userType");
				//System.out.println("searchUserType is>>>>>>>>>>>>>in action class>>"+searchUserType);
				String advanceSearchOption="N";
				//System.out.println("advanceSearchOption is>>>>>>>>>>>>>in action class>>"+advanceSearchOption);
				if(advanceSearchOption.equalsIgnoreCase("N"))
				{
					if(searchUserType.equalsIgnoreCase("wlClient"))
					{
						String  whiteLabelClientId=(String)session.get("whiteLabelClientActivationUserId");
						//System.out.println("whiteLabelClientId is>>>>>>>>>>>>>in action class>>"+whiteLabelClientId);
						if(whiteLabelClientId==null || whiteLabelClientId=="")
						{
							session.remove("whiteLabelClientActivationUserId");
							String status=(String)session.get("status");
							//System.out.println("status is>>>>>>>>>>>>>in action class>>"+status);
							session.put("buttonStatus",status);
							int pageNumber=(Integer)session.get("pageNumber");
							if(status.equals("all"))
							{
								if(pageNumber==1){
									int noOfRecords=daoObj.getNoOfRecordsAllWhiteLabelClientDetails();
									if(noOfRecords==0)
									{
										request.setAttribute("message","No Records found.");
										return "searchUser";
									}
									session.put("noOfRecords",noOfRecords);
								}
								//System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
								ArrayList whiteLabelClientDetails=daoObj.getAllWhiteLabelClientDetail(pageNumber);
								session.put("userType",searchUserType);
								session.put("pageNumber",pageNumber);
								session.put("status",status);
								session.put("advanceSearchOption",advanceSearchOption);
								session.put("whiteLabelClientSearchList",whiteLabelClientDetails);
								return "whiteLabelClientList";
							}
							if(!status.equals("all"))
							{
								if(status.equalsIgnoreCase("blocked") || status.equalsIgnoreCase("unblocked")){
									if(pageNumber==1)
									{
										int noOfRecords=daoObj.getNoOfRecordsAllWhiteLabelClientDetailsBlockStatusWise(status);
										if(noOfRecords==0)
										{
											session.put("whiteLabelClientActivationMessage","No Records found.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
									//System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
									ArrayList whiteLabelClientDetails=daoObj.getWhiteLabelClientDetailsBlockStatusWise(status,pageNumber);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("whiteLabelClientSearchList",whiteLabelClientDetails);
									return "whiteLabelClientList";
								}	
								else{
									if(pageNumber==1)
									{
										int noOfRecords=daoObj.getNoOfRecordsAllWhiteLabelClientDetailsStatusWise(status);
										if(noOfRecords==0)
										{
											request.setAttribute("message","No Records found.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
									//System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
									ArrayList whiteLabelClientDetails=daoObj.getWhiteLabelClientDetailsStatusWise(status,pageNumber);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("whiteLabelClientSearchList",whiteLabelClientDetails);
									return "whiteLabelClientList";
								}
							}
						}
						else{
							int pageNumber=(Integer)session.get("pageNumber");
							if(pageNumber==1)
							{
								int noOfRecords=daoObj.getNoOfRecordsWhiteLabelClientDetailsClientIdWise(whiteLabelClientId);
								if(noOfRecords==0)
								{
									request.setAttribute("message","No Records found.");
									return "searchUser";
								}
								session.put("noOfRecords",noOfRecords);
							}
							ArrayList whiteLabelClientDetails=daoObj.getWhiteLabelClientDetailsClientIdWise(whiteLabelClientId,pageNumber);
							session.put("userType",searchUserType);
							session.put("pageNumber",pageNumber);
							session.put("whiteLabelClientActivationUserId",whiteLabelClientId);
							session.put("buttonStatus","all");
							session.put("advanceSearchOption",advanceSearchOption);
							session.put("whiteLabelClientSearchList",whiteLabelClientDetails);
							return "whiteLabelClientList";
						}
					}
				}
				return "whiteLabelClientList";
			}
		}catch(Exception e){
			request.setAttribute("message","Process aborted due to Technical Failure.");
			System.out.println("Exception in WhiteLabelClientActivationAction");
			System.out.println(e.toString());
			return "searchUser";
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

