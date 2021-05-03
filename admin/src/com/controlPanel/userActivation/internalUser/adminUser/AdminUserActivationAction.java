package com.controlPanel.userActivation.internalUser.adminUser;


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
* Updated By :Arvind
* Updated Date : 7 Jun 2013
* Updated Matter : Format of class.
*/

public class AdminUserActivationAction extends ActionSupport implements ServletRequestAware,ServletResponseAware
{
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request; 
	private HttpServletResponse response;
	private Map session;
	
	public String execute() throws Exception
	{ 
		Map session=ActionContext.getContext().getSession();
		try
		{
			String userId=(String)session.get("userId");
			if(userId==null)
			{
				request.setAttribute("message","Your Login Session has Expired. Please Login Again.");
				return "sessionExpire";
			}
			
			String param=request.getParameter("param");
			System.out.println("AdminUserActivationAction Class and Param is "+param);
			if(param.equals("searchUser"))
			{
				session.remove("noOfRecords");
				session.remove("pageSize");
				session.remove("pageNumber");
				session.remove("noOfPages");
				session.remove("userType");
				session.remove("status");
				session.remove("portalId");
				session.remove("buttonStatus");
				session.remove("advanceSearchOption");
				String userType=(String)session.get("adminUserType");
				int pageNumber=1;
				int pageSize=100;
				
				if(userType.equalsIgnoreCase("superadmin"))
				{
					AdminUserActivationDao daoObj=new AdminUserActivationDao();
					ArrayList portalIdList= daoObj.getPortalIdList();
					session.put("searchPortalIdList",portalIdList);
				}
				
				session.put("pageNumber",pageNumber);
				session.put("pageSize",pageSize);
				return "searchUser";
			}
			else if(param.equals("searchUserType"))
			{
				//System.out.println("we are here Manoj");
				//session.remove("noOfRecords");					 
				session.remove("noOfPages");
				session.remove("userType");
				session.remove("status");
				session.remove("portalId");
				session.remove("buttonStatus");
				session.remove("advanceSearchOption");
				String searchUserType=request.getParameter("userType");
				String advanceSearchOption="N";
				String portalId=request.getParameter("portalId");
				
				if(portalId==null)
				{
					portalId=userId;
				}
				if(advanceSearchOption.equalsIgnoreCase("N"))
				{
					//System.out.println("we are in advanceSearchOption N");
					AdminUserActivationDao daoObj=new AdminUserActivationDao();
					if(searchUserType.equalsIgnoreCase("adminUser"))
					{
						String  adminUserId=request.getParameter("userId");
						//System.out.println("adminUserId-----------------------"+adminUserId);
						if(adminUserId==null || adminUserId=="")
						{
							//System.out.println("we are in adminUserId in null ");
							session.remove("adminUserActivationUserId");
							String status=request.getParameter("status");
							session.put("buttonStatus",status);
							if(portalId.equalsIgnoreCase("all") && status.equals("all"))
							{
								//System.out.println("we are in portalId all case");
								int pageNumber=Integer.parseInt(request.getParameter("pageNumber"));
								//System.out.println("page number is "+pageNumber);
								if(pageNumber==1)
								{
									int noOfRecords=daoObj.getNoOfRecordsAllAdminUserDetails();
									if(noOfRecords==0){
										request.setAttribute("message","No Records found.");
										return "searchUser";
									}
									session.put("noOfRecords",noOfRecords);
								}
								//System.out.println("pageNumber in action class is------------------"+pageNumber);
								ArrayList adminUserDetails=daoObj.getAllAdminUserDetail(pageNumber);
								session.put("userType",searchUserType);
								session.put("pageNumber",pageNumber);
								session.put("status",status);
								session.put("portalId",portalId);
								session.put("advanceSearchOption",advanceSearchOption);
								session.put("adminUserSearchList",adminUserDetails);
								return "adminUserList";
							}
							if(portalId.equalsIgnoreCase("all") && !status.equals("all"))
							{
								int pageNumber=Integer.parseInt((String)request.getParameter("pageNumber"));
								if(status.equalsIgnoreCase("blocked") || status.equalsIgnoreCase("unblocked"))
								{
									if(pageNumber==1){
										
										int noOfRecords=daoObj.getNoOfRecordsAllAdminUserDetailsBlockStatusWise(status);
										if(noOfRecords==0)
										{
											request.setAttribute("message","No Records found.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
									//System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
									
									ArrayList adminUserDetails=daoObj.getAdminUserDetailsBlockStatusWise(status,pageNumber);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("portalId",portalId);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("adminUserSearchList",adminUserDetails);
									return "adminUserList";
								}
								else
								{
									if(pageNumber==1)
									{
										int noOfRecords=daoObj.getNoOfRecordsAllAdminUserDetailsStatusWise(status);
										if(noOfRecords==0){
											request.setAttribute("message","No Records found.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
									//System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
									ArrayList adminUserDetails=daoObj.getAdminUserDetailsStatusWise(status,pageNumber);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("portalId",portalId);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("adminUserSearchList",adminUserDetails);
									return "adminUserList";
								}
							}
							if(!portalId.equalsIgnoreCase("all") && status.equals("all"))
							{
								int pageNumber=Integer.parseInt((String)request.getParameter("pageNumber"));
								if(pageNumber==1)
								{
									
									int noOfRecords=daoObj.getNoOfRecordsAllAdminUserDetailsPortalIdWise(portalId);
									if(noOfRecords==0)
									{
										request.setAttribute("message","No Records found.");
										return "searchUser";
									}
									session.put("noOfRecords",noOfRecords);
								}
								//System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
								
								ArrayList adminUserDetails=daoObj.getAdminUserDetailsPortalWise(portalId,pageNumber);
								session.put("userType",searchUserType);
								session.put("pageNumber",pageNumber);
								session.put("status",status);
								session.put("portalId",portalId);
								session.put("advanceSearchOption",advanceSearchOption);
								session.put("adminUserSearchList",adminUserDetails);
								return "adminUserList";
							} 
							if(!portalId.equalsIgnoreCase("all") && !status.equals("all"))
							{
								int pageNumber=Integer.parseInt((String)request.getParameter("pageNumber"));
								if(status.equalsIgnoreCase("blocked") || status.equalsIgnoreCase("unblocked"))
								{
									if(pageNumber==1)
									{
										int noOfRecords=daoObj.getNoOfRecordsAllAdminUserDetailsPortalIdBlockStatusWise(portalId,status);
										if(noOfRecords==0)
										{
											request.setAttribute("message","No Records found.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
									//System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
									
									ArrayList adminUserDetails=daoObj.getAdminUserDetailsOfPortalIdBlockStatusWise(portalId,status,pageNumber);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("portalId",portalId);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("adminUserSearchList",adminUserDetails);
									return "adminUserList";
								}
								else
								{
									if(pageNumber==1)
									{
										int noOfRecords=daoObj.getNoOfRecordsAllAdminUserDetailsPortalIdStatusWise(portalId,status);
										if(noOfRecords==0)
										{
											request.setAttribute("message","No Records found.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
									//System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
									
									ArrayList adminUserDetails=daoObj.getAdminUserDetailsOfPortalIdStatusWise(portalId,status,pageNumber);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("portalId",portalId);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("adminUserSearchList",adminUserDetails);
									return "adminUserList";
								}
							}
						}
						else
						{
							//System.out.println("we are in admin user id block");
							int pageNumber=Integer.parseInt((String)request.getParameter("pageNumber"));
							//System.out.println("page number is"+pageNumber);
							if(pageNumber==1)
							{
								//System.out.println("page number is ");
								int noOfRecords=daoObj.getNoOfRecordsAllAdminUserDetailsUserIdWise(adminUserId,portalId);
								if(noOfRecords==0)
								{
									request.setAttribute("message","No Records found.");
									return "searchUser";
								}
								session.put("noOfRecords",noOfRecords);
							}
							
							ArrayList adminUserDetails=daoObj.getAdminUserDetailsUserIdWise(adminUserId,pageNumber,portalId);
					     	//System.out.println("we are here adminUserDetails"+adminUserDetails);
					     	session.put("userType",searchUserType);
					     	session.put("pageNumber",pageNumber);
					     	session.put("adminUserActivationUserId",adminUserId);
					     	session.put("buttonStatus","all");
					     	session.put("portalId",portalId);
					     	session.put("advanceSearchOption",advanceSearchOption);
					     	session.put("adminUserSearchList",adminUserDetails);
					     	return "adminUserList";
						}
					}
				}
			}
			else if(param.equalsIgnoreCase("activateAdminUser"))
			{
				String[] userIds=request.getParameterValues("userIds");
				
				AdminUserActivationDao daoObj=new AdminUserActivationDao();
				daoObj.activateAdminUser(userIds,session);
				ArrayList activatedAdminUserList=(ArrayList)session.get("activatedAdminUserList");
				ArrayList deactivatedAdminUserList=(ArrayList)session.get("deactivatedAdminUserList");
				String message="";
				if(activatedAdminUserList.size()>0)
				{
					message="Selected User/s has been Activated.";
				}
				else{
					message="Process aborted due to Technical Failure.";
				}
				request.setAttribute("message",message);
				 
				String searchUserType=(String)session.get("userType");
				String advanceSearchOption="N";
				String portalId=(String)session.get("portalId");
				if(advanceSearchOption.equalsIgnoreCase("N"))
				{
					if(searchUserType.equalsIgnoreCase("adminUser"))
					{
						String  adminUserId=(String)session.get("adminUserActivationUserId");
						if(adminUserId==null || adminUserId=="")
						{
							session.remove("adminUserActivationUserId");
							String status=(String)session.get("status");
							session.put("buttonStatus",status);
							if(portalId.equalsIgnoreCase("all") && status.equals("all"))
							{
								int pageNumber=(Integer)session.get("pageNumber");
								if(pageNumber==1)
								{
									int noOfRecords=daoObj.getNoOfRecordsAllAdminUserDetails();
									if(noOfRecords==0)
									{
										request.setAttribute("message","No Records found.");
										return "searchUser";
									}
									session.put("noOfRecords",noOfRecords);
								}
								  //System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
								ArrayList adminUserDetails=daoObj.getAllAdminUserDetail(pageNumber);
								session.put("userType",searchUserType);
								session.put("pageNumber",pageNumber);
								session.put("status",status);
								session.put("portalId",portalId);
								session.put("advanceSearchOption",advanceSearchOption);
								session.put("adminUserSearchList",adminUserDetails);
								return "adminUserList";
							}
							if(portalId.equalsIgnoreCase("all") && !status.equals("all"))
							{
								int pageNumber=(Integer)session.get("pageNumber");
								if(status.equalsIgnoreCase("blocked") || status.equalsIgnoreCase("unblocked"))
								{
									if(pageNumber==1)
									{
										int noOfRecords=daoObj.getNoOfRecordsAllAdminUserDetailsBlockStatusWise(status);
										if(noOfRecords==0)
										{
											request.setAttribute("message","No Records found.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
									//System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
									ArrayList adminUserDetails=daoObj.getAdminUserDetailsBlockStatusWise(status,pageNumber);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("portalId",portalId);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("adminUserSearchList",adminUserDetails);
									return "adminUserList";
								}
								else
								{
									if(pageNumber==1)
									{
										int noOfRecords=daoObj.getNoOfRecordsAllAdminUserDetailsStatusWise(status);
										if(noOfRecords==0)
										{
											request.setAttribute("message","No Records found.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
									  //System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
									ArrayList adminUserDetails=daoObj.getAdminUserDetailsStatusWise(status,pageNumber);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("portalId",portalId);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("adminUserSearchList",adminUserDetails);
									return "adminUserList";
								}
							}
							if(!portalId.equalsIgnoreCase("all") && status.equals("all"))
							{
								int pageNumber=(Integer)session.get("pageNumber");
								if(pageNumber==1)
								{
									int noOfRecords=daoObj.getNoOfRecordsAllAdminUserDetailsPortalIdWise(portalId);
									if(noOfRecords==0)
									{
										request.setAttribute("message","No Records found.");
										return "searchUser";
									}
									session.put("noOfRecords",noOfRecords);
								}
								  //System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
								ArrayList adminUserDetails=daoObj.getAdminUserDetailsPortalWise(portalId,pageNumber);
								session.put("userType",searchUserType);
								session.put("pageNumber",pageNumber);
								session.put("status",status);
								session.put("portalId",portalId);
								session.put("advanceSearchOption",advanceSearchOption);
								session.put("adminUserSearchList",adminUserDetails);
								return "adminUserList";
							} 
							if(!portalId.equalsIgnoreCase("all") && !status.equals("all"))
							{
								int pageNumber=(Integer)session.get("pageNumber");
								if(status.equalsIgnoreCase("blocked") || status.equalsIgnoreCase("unblocked"))
								{
									if(pageNumber==1)
									{
										int noOfRecords=daoObj.getNoOfRecordsAllAdminUserDetailsPortalIdBlockStatusWise(portalId,status);
										if(noOfRecords==0)
										{
											request.setAttribute("message","No Records found.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
									//System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
									ArrayList adminUserDetails=daoObj.getAdminUserDetailsOfPortalIdBlockStatusWise(portalId,status,pageNumber);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("portalId",portalId);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("adminUserSearchList",adminUserDetails);
									return "adminUserList";
								}
								else
								{
									if(pageNumber==1)
									{
										int noOfRecords=daoObj.getNoOfRecordsAllAdminUserDetailsPortalIdStatusWise(portalId,status);
										if(noOfRecords==0)
										{
											request.setAttribute("message","No Records found.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
									  //System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
									ArrayList adminUserDetails=daoObj.getAdminUserDetailsOfPortalIdStatusWise(portalId,status,pageNumber);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("portalId",portalId);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("adminUserSearchList",adminUserDetails);
									return "adminUserList";
								}
							}
						}
						else
						{
							int pageNumber=(Integer)session.get("pageNumber");
							if(pageNumber==1)
							{
								int noOfRecords=daoObj.getNoOfRecordsAllAdminUserDetailsUserIdWise(adminUserId,portalId);
								if(noOfRecords==0)
								{
									request.setAttribute("message","No Records found.");
									return "searchUser";
								}
								session.put("noOfRecords",noOfRecords);
							}
							ArrayList adminUserDetails=daoObj.getAdminUserDetailsUserIdWise(adminUserId,pageNumber,portalId);
							session.put("userType",searchUserType);
							session.put("adminUserActivationUserId",adminUserId);
							session.put("pageNumber",pageNumber);
							session.put("buttonStatus","all");
							session.put("portalId",portalId);
							session.put("advanceSearchOption",advanceSearchOption);
							session.put("adminUserSearchList",adminUserDetails);
							return "adminUserList";
						}
					}
				}
				return "adminUserList";
			}	
			else if(param.equalsIgnoreCase("deactivateAdminUser"))
			{
				HashMap dynamicDetails=(HashMap) session.get("dynamicDetails");
				String clientFlag=(String)dynamicDetails.get("clientFlag");
				String[] userIds=request.getParameterValues("userIds");
				AdminUserActivationDao daoObj=new AdminUserActivationDao();
				daoObj.deactivateAdminUser(userIds,session);
				ArrayList activatedAdminUserList=(ArrayList)session.get("activatedAdminUserList");
				ArrayList deactivatedAdminUserList=(ArrayList)session.get("deactivatedAdminUserList");
				String message="";
				
				if(deactivatedAdminUserList.size()>0)
				{
					message="Selected User/s has been Deactivated.";
				}
				else
				{
					message="Process aborted due to Technical Failure.";
				}
				
				request.setAttribute("message",message);
				String searchUserType=(String)session.get("userType");
				String advanceSearchOption="N";
				String portalId=(String)session.get("portalId");
				if(advanceSearchOption.equalsIgnoreCase("N"))
				{
					if(searchUserType.equalsIgnoreCase("adminUser"))
					{
						String  adminUserId=(String)session.get("adminUserActivationUserId");
						if(adminUserId==null || adminUserId=="")
						{
							session.remove("adminUserActivationUserId");
							String status=(String)session.get("status");
							session.put("buttonStatus",status);
							if(portalId.equalsIgnoreCase("all") && status.equals("all"))
							{
								int pageNumber=(Integer)session.get("pageNumber");
								if(pageNumber==1)
								{
									int noOfRecords=daoObj.getNoOfRecordsAllAdminUserDetails();
									if(noOfRecords==0)
									{
										request.setAttribute("message","No Records found.");
										return "searchUser";
									}
									session.put("noOfRecords",noOfRecords);
								}
								//System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
								ArrayList adminUserDetails=daoObj.getAllAdminUserDetail(pageNumber);
								session.put("userType",searchUserType);
								session.put("pageNumber",pageNumber);
								session.put("status",status);
								session.put("portalId",portalId);
								session.put("advanceSearchOption",advanceSearchOption);
								session.put("adminUserSearchList",adminUserDetails);
								return "adminUserList";
							}
						   if(portalId.equalsIgnoreCase("all") && !status.equals("all"))
						   {
							   int pageNumber=(Integer)session.get("pageNumber");
							   if(status.equalsIgnoreCase("blocked") || status.equalsIgnoreCase("unblocked"))
							   {
								   if(pageNumber==1)
								   {
									   int noOfRecords=daoObj.getNoOfRecordsAllAdminUserDetailsBlockStatusWise(status);
									   if(noOfRecords==0)
									   {
										   request.setAttribute("message","No Records found.");
										   return "searchUser";
									   }
									   session.put("noOfRecords",noOfRecords);
								   }
								   //System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
								   ArrayList adminUserDetails=daoObj.getAdminUserDetailsBlockStatusWise(status,pageNumber);
								   session.put("userType",searchUserType);
								   session.put("pageNumber",pageNumber);
								   session.put("status",status);
								   session.put("portalId",portalId);
								   session.put("advanceSearchOption",advanceSearchOption);
								   session.put("adminUserSearchList",adminUserDetails);
								   return "adminUserList";
							   }
							   else
							   {
								   if(pageNumber==1)
								   {
									   int noOfRecords=daoObj.getNoOfRecordsAllAdminUserDetailsStatusWise(status);
									   if(noOfRecords==0)
									   {
										   request.setAttribute("message","No Records found.");
										   return "searchUser";
									   }
									   session.put("noOfRecords",noOfRecords);
								   }
								   //System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
								   ArrayList adminUserDetails=daoObj.getAdminUserDetailsStatusWise(status,pageNumber);
								   session.put("userType",searchUserType);
								   session.put("pageNumber",pageNumber);
								   session.put("status",status);
								   session.put("portalId",portalId);
								   session.put("advanceSearchOption",advanceSearchOption);
								   session.put("adminUserSearchList",adminUserDetails);
								   return "adminUserList";
							   }
						   }
						   if(!portalId.equalsIgnoreCase("all") && status.equals("all"))
						   {
							   int pageNumber=(Integer)session.get("pageNumber");
							   if(pageNumber==1)
							   {
								   int noOfRecords=daoObj.getNoOfRecordsAllAdminUserDetailsPortalIdWise(portalId);
								   if(noOfRecords==0)
								   {
									   request.setAttribute("message","No Records found.");
									   return "searchUser";
								   }
								   session.put("noOfRecords",noOfRecords);
							   }
							 
							   ArrayList adminUserDetails=daoObj.getAdminUserDetailsPortalWise(portalId,pageNumber);
							   session.put("userType",searchUserType);
							   session.put("pageNumber",pageNumber);
							   session.put("status",status);
							   session.put("portalId",portalId);
							   session.put("advanceSearchOption",advanceSearchOption);
							   session.put("adminUserSearchList",adminUserDetails);
							   return "adminUserList";
						   } 
						   if(!portalId.equalsIgnoreCase("all") && !status.equals("all"))
						   {
							   int pageNumber=(Integer)session.get("pageNumber");
							   if(status.equalsIgnoreCase("blocked") || status.equalsIgnoreCase("unblocked")){
								   if(pageNumber==1)
								   {
									   int noOfRecords=daoObj.getNoOfRecordsAllAdminUserDetailsPortalIdBlockStatusWise(portalId,status);
									   if(noOfRecords==0)
									   {
										   request.setAttribute("message","No Records found.");
										   return "searchUser";
									   }
									   session.put("noOfRecords",noOfRecords);
								   }
								   ArrayList adminUserDetails=daoObj.getAdminUserDetailsOfPortalIdBlockStatusWise(portalId,status,pageNumber);
								   session.put("userType",searchUserType);
								   session.put("pageNumber",pageNumber);
								   session.put("status",status);
								   session.put("portalId",portalId);
								   session.put("advanceSearchOption",advanceSearchOption);
								   session.put("adminUserSearchList",adminUserDetails);
								   return "adminUserList";
							   }
							   else
							   {
								   if(pageNumber==1)
								   {
									   int noOfRecords=daoObj.getNoOfRecordsAllAdminUserDetailsPortalIdStatusWise(portalId,status);
									   if(noOfRecords==0)
									   {
										   request.setAttribute("message","No Records found.");
										   return "searchUser";
									   }
									   session.put("noOfRecords",noOfRecords);
								   }
								   //System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
								   ArrayList adminUserDetails=daoObj.getAdminUserDetailsOfPortalIdStatusWise(portalId,status,pageNumber);
								   session.put("userType",searchUserType);
								   session.put("pageNumber",pageNumber);
								   session.put("status",status);
								   session.put("portalId",portalId);
								   session.put("advanceSearchOption",advanceSearchOption);
								   session.put("adminUserSearchList",adminUserDetails);
								   return "adminUserList";
							   }
						   }
						}
						else
						{
							int pageNumber=(Integer)session.get("pageNumber");
							if(pageNumber==1)
							{
								int noOfRecords=daoObj.getNoOfRecordsAllAdminUserDetailsUserIdWise(adminUserId,portalId);
								if(noOfRecords==0)
								{
									request.setAttribute("message","No Records found.");
									return "searchUser";
								}
								session.put("noOfRecords",noOfRecords);
							}
							ArrayList adminUserDetails=daoObj.getAdminUserDetailsUserIdWise(adminUserId,pageNumber,portalId);
							session.put("userType",searchUserType);
							session.put("pageNumber",pageNumber);
							session.put("adminUserActivationUserId",adminUserId);
							session.put("buttonStatus","all");
							session.put("portalId",portalId);
							session.put("advanceSearchOption",advanceSearchOption);
							session.put("adminUserSearchList",adminUserDetails);
							return "adminUserList";
						}
					}
				}
				return "adminUserList";
			}
			else if(param.equalsIgnoreCase("blockAdminUser"))
			{
				//System.out.println("we are into blockAdminUser");
				String[] userIds=request.getParameterValues("userIds");
				
				AdminUserActivationDao daoObj=new AdminUserActivationDao();
				daoObj.blockAdminUser(userIds,session);
				ArrayList blockedAdminUserList=(ArrayList)session.get("blockedAdminUserList");
				ArrayList unblockedAdminUserList=(ArrayList)session.get("unblockedAdminUserList");
				String message="";
				
				if(blockedAdminUserList.size()>0)
				{
					message="Selected User/s has been Blocked.";
				}
				else
				{
					message="Process aborted due to Technical Failure.";
				}
				request.setAttribute("message",message);
				String searchUserType=(String)session.get("userType");
				//System.out.println("searchUserType>>>>>>>>>>>>>>>>>>>>>>>"+searchUserType);
				String advanceSearchOption="N";
				String portalId=(String)session.get("portalId");
				if(advanceSearchOption.equalsIgnoreCase("N"))
				{
					if(searchUserType.equalsIgnoreCase("adminUser"))
					{
						String  adminUserId=(String)session.get("adminUserActivationUserId");
						if(adminUserId==null || adminUserId=="")
						{
							session.remove("adminUserActivationUserId"); 
							String status=(String)session.get("status");
							session.put("buttonStatus",status);
							if(portalId.equalsIgnoreCase("all") && status.equals("all"))
							{
								int pageNumber=(Integer)session.get("pageNumber");
								if(pageNumber==1)
								{
									int noOfRecords=daoObj.getNoOfRecordsAllAdminUserDetails();
									if(noOfRecords==0)
									{
										request.setAttribute("message","No Records found.");
										return "searchUser";
									}
									session.put("noOfRecords",noOfRecords);
								}
								//System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
								ArrayList adminUserDetails=daoObj.getAllAdminUserDetail(pageNumber);
								session.put("userType",searchUserType);
								session.put("pageNumber",pageNumber);
								session.put("status",status);
								session.put("portalId",portalId);
								session.put("advanceSearchOption",advanceSearchOption);
								session.put("adminUserSearchList",adminUserDetails);
								return "adminUserList";
							}
							if(portalId.equalsIgnoreCase("all") && !status.equals("all"))
							{
								int pageNumber=(Integer)session.get("pageNumber");
								if(status.equalsIgnoreCase("blocked") || status.equalsIgnoreCase("unblocked"))
								{
									if(pageNumber==1)
									{
										int noOfRecords=daoObj.getNoOfRecordsAllAdminUserDetailsBlockStatusWise(status);
										if(noOfRecords==0)
										{
											request.setAttribute("message","No Records found.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
									//System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
									ArrayList adminUserDetails=daoObj.getAdminUserDetailsBlockStatusWise(status,pageNumber);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("portalId",portalId);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("adminUserSearchList",adminUserDetails);
									return "adminUserList";
								}
								else
								{
									if(pageNumber==1)
									{
										int noOfRecords=daoObj.getNoOfRecordsAllAdminUserDetailsStatusWise(status);
										if(noOfRecords==0)
										{
											request.setAttribute("message","No Records found.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
									//System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
									ArrayList adminUserDetails=daoObj.getAdminUserDetailsStatusWise(status,pageNumber);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("portalId",portalId);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("adminUserSearchList",adminUserDetails);
									return "adminUserList";
								}
							}
							if(!portalId.equalsIgnoreCase("all") && status.equals("all"))
							{
								int pageNumber=(Integer)session.get("pageNumber");
								if(pageNumber==1)
								{
									int noOfRecords=daoObj.getNoOfRecordsAllAdminUserDetailsPortalIdWise(portalId);
									if(noOfRecords==0)
									{
										session.put("adminUserActivationMessage","No Records found.");
										return "searchUser";
									}
									session.put("noOfRecords",noOfRecords);
								}
								//System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
								ArrayList adminUserDetails=daoObj.getAdminUserDetailsPortalWise(portalId,pageNumber);
								session.put("userType",searchUserType);
								session.put("pageNumber",pageNumber);
								session.put("status",status);
								session.put("portalId",portalId);
								session.put("advanceSearchOption",advanceSearchOption);
								session.put("adminUserSearchList",adminUserDetails);
								return "adminUserList";
							} 
							if(!portalId.equalsIgnoreCase("all") && !status.equals("all"))
							{
								int pageNumber=(Integer)session.get("pageNumber");
								if(status.equalsIgnoreCase("blocked") || status.equalsIgnoreCase("unblocked"))
								{
									if(pageNumber==1)
									{
										int noOfRecords=daoObj.getNoOfRecordsAllAdminUserDetailsPortalIdBlockStatusWise(portalId,status);
										if(noOfRecords==0)
										{
											session.put("adminUserActivationMessage","No Records found.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
									//System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
									ArrayList adminUserDetails=daoObj.getAdminUserDetailsOfPortalIdBlockStatusWise(portalId,status,pageNumber);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("portalId",portalId);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("adminUserSearchList",adminUserDetails);
									return "adminUserList";
								}
								else
								{
									if(pageNumber==1)
									{
										int noOfRecords=daoObj.getNoOfRecordsAllAdminUserDetailsPortalIdStatusWise(portalId,status);
										if(noOfRecords==0)
										{
											request.setAttribute("message","No Records found.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
									//System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
									ArrayList adminUserDetails=daoObj.getAdminUserDetailsOfPortalIdStatusWise(portalId,status,pageNumber);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("portalId",portalId);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("adminUserSearchList",adminUserDetails);
									return "adminUserList";
								}
							}
						}
						else{
							int pageNumber=(Integer)session.get("pageNumber");
							if(pageNumber==1)
							{
								int noOfRecords=daoObj.getNoOfRecordsAllAdminUserDetailsUserIdWise(adminUserId,portalId);
								if(noOfRecords==0)
								{
									session.put("adminUserActivationMessage","No Records found.");
									return "searchUser";
								}
								session.put("noOfRecords",noOfRecords);
							}
							ArrayList adminUserDetails=daoObj.getAdminUserDetailsUserIdWise(adminUserId,pageNumber,portalId);
							session.put("userType",searchUserType);
							session.put("pageNumber",pageNumber);
							session.put("adminUserActivationUserId",adminUserId);
							session.put("buttonStatus","all");
							session.put("portalId",portalId);
							session.put("advanceSearchOption",advanceSearchOption);
							session.put("adminUserSearchList",adminUserDetails);
							return "adminUserList";
						}
					}
				}
				return "adminUserList";
			}	
			else if(param.equalsIgnoreCase("unblockAdminUser"))
			{
				String[] userIds=request.getParameterValues("userIds");
				
				AdminUserActivationDao daoObj=new AdminUserActivationDao();
				daoObj.unblockAdminUser(userIds,session);
				ArrayList unblockedAdminUserList=(ArrayList)session.get("unblockedAdminUserList");
				ArrayList blockedAdminUserList=(ArrayList)session.get("blockedAdminUserList");
				String message="";
				
				if(unblockedAdminUserList.size()>0)
				{
					message="Selected User/s has been Unblocked.";
				}
				else
				{
					message="Process aborted due to Technical Failure.";
				}
				
				request.setAttribute("message",message);
				String searchUserType=(String)session.get("userType");
				String advanceSearchOption="N";
				String portalId=(String)session.get("portalId");
				if(advanceSearchOption.equalsIgnoreCase("N"))
				{
					if(searchUserType.equalsIgnoreCase("adminUser"))
					{
						String  adminUserId=(String)session.get("adminUserActivationUserId");
						//System.out.println("adminUserId>>>>>>>>>>>>>>>>>>>>>>>"+adminUserId);
						if(adminUserId==null || adminUserId=="")
						{
							session.remove("adminUserActivationUserId");
							String status=(String)session.get("status");
							session.put("buttonStatus",status);
							if(portalId.equalsIgnoreCase("all") && status.equals("all"))
							{
								int pageNumber=(Integer)session.get("pageNumber");
								if(pageNumber==1)
								{
									int noOfRecords=daoObj.getNoOfRecordsAllAdminUserDetails();
									if(noOfRecords==0)
									{
										request.setAttribute("request.setAttributeessage","No Records found.");
										return "searchUser";
									}
									session.put("noOfRecords",noOfRecords);
								}
								//System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
								ArrayList adminUserDetails=daoObj.getAllAdminUserDetail(pageNumber);
								session.put("userType",searchUserType);
								session.put("pageNumber",pageNumber);
								session.put("status",status);
								session.put("portalId",portalId);
								session.put("advanceSearchOption",advanceSearchOption);
								session.put("adminUserSearchList",adminUserDetails);
								return "adminUserList";
							}
							if(portalId.equalsIgnoreCase("all") && !status.equals("all"))
							{
								int pageNumber=(Integer)session.get("pageNumber");
								if(status.equalsIgnoreCase("blocked") || status.equalsIgnoreCase("unblocked")){
									if(pageNumber==1)
									{
										int noOfRecords=daoObj.getNoOfRecordsAllAdminUserDetailsBlockStatusWise(status);
										if(noOfRecords==0)
										{
											request.setAttribute("message","No Records found.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
									//System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
									ArrayList adminUserDetails=daoObj.getAdminUserDetailsBlockStatusWise(status,pageNumber);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("portalId",portalId);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("adminUserSearchList",adminUserDetails);
									return "adminUserList";
								}
								else
								{
									if(pageNumber==1)
									{
										int noOfRecords=daoObj.getNoOfRecordsAllAdminUserDetailsStatusWise(status);
										if(noOfRecords==0)
										{
											request.setAttribute("message","No Records found.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
									//System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
									ArrayList adminUserDetails=daoObj.getAdminUserDetailsStatusWise(status,pageNumber);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("portalId",portalId);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("adminUserSearchList",adminUserDetails);
									return "adminUserList";
								}
							}
							if(!portalId.equalsIgnoreCase("all") && status.equals("all"))
							{
								int pageNumber=(Integer)session.get("pageNumber");
								if(pageNumber==1)
								{
									int noOfRecords=daoObj.getNoOfRecordsAllAdminUserDetailsPortalIdWise(portalId);
									if(noOfRecords==0)
									{
										request.setAttribute("message","No Records found.");
										return "searchUser";
									}
									session.put("noOfRecords",noOfRecords);
								}
								//System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
								ArrayList adminUserDetails=daoObj.getAdminUserDetailsPortalWise(portalId,pageNumber);
								session.put("userType",searchUserType);
								session.put("pageNumber",pageNumber);
								session.put("status",status);
								session.put("portalId",portalId);
								session.put("advanceSearchOption",advanceSearchOption);
								session.put("adminUserSearchList",adminUserDetails);
								return "adminUserList";
							} 
							if(!portalId.equalsIgnoreCase("all") && !status.equals("all"))
							{
								int pageNumber=(Integer)session.get("pageNumber");
								if(status.equalsIgnoreCase("blocked") || status.equalsIgnoreCase("unblocked"))
								{
									if(pageNumber==1)
									{
										int noOfRecords=daoObj.getNoOfRecordsAllAdminUserDetailsPortalIdBlockStatusWise(portalId,status);
										if(noOfRecords==0)
										{
											request.setAttribute("message","No Records found.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
									//System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
									ArrayList adminUserDetails=daoObj.getAdminUserDetailsOfPortalIdBlockStatusWise(portalId,status,pageNumber);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("portalId",portalId);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("adminUserSearchList",adminUserDetails);
									return "adminUserList";
								}
								else
								{
									if(pageNumber==1)
									{
										int noOfRecords=daoObj.getNoOfRecordsAllAdminUserDetailsPortalIdStatusWise(portalId,status);
										if(noOfRecords==0)
										{
											session.put("adminUserActivationMessage","No Records found.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
									//System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
									ArrayList adminUserDetails=daoObj.getAdminUserDetailsOfPortalIdStatusWise(portalId,status,pageNumber);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("portalId",portalId);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("adminUserSearchList",adminUserDetails);
									return "adminUserList";
								}
							}
						}
						else
						{
							int pageNumber=(Integer)session.get("pageNumber");
							if(pageNumber==1)
							{
								int noOfRecords=daoObj.getNoOfRecordsAllAdminUserDetailsUserIdWise(adminUserId,portalId);
								if(noOfRecords==0)
								{
									request.setAttribute("message","No Records found.");
									return "searchUser";
								}
								session.put("noOfRecords",noOfRecords);
							}
							ArrayList adminUserDetails=daoObj.getAdminUserDetailsUserIdWise(adminUserId,pageNumber,portalId);
							session.put("userType",searchUserType);
							session.put("pageNumber",pageNumber);
							session.put("adminUserActivationUserId",adminUserId);	
							session.put("buttonStatus","all");
							session.put("portalId",portalId);
							session.put("advanceSearchOption",advanceSearchOption);
							session.put("adminUserSearchList",adminUserDetails);
							return "adminUserList";
						}
					}
				}
				return "adminUserList";
			}	
		
		}catch(Exception e){
			System.out.println("Exception in AdminserActivationAction");
			request.setAttribute("message", "Process aborted due to Technical Failure.");
			System.out.println(e.toString());
			return "searchUser";
		}
		return "err";   
	}
	public void setServletRequest(HttpServletRequest request)
	{
		this.request=request;
	}
	public void setSession(Map session)
	{
		session = this.getSession();
	}
	public Map getSession()
	{
		return session;
	}
	public void setServletResponse(HttpServletResponse response) 
	{
		this.response=response;
	}
}
