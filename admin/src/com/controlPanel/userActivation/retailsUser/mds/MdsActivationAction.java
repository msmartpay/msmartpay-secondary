package com.controlPanel.userActivation.retailsUser.mds;

/*	Updated By 		: Manoj Khan
 * 	Updated Matter 	: To add some field on md details Query
 * 	Updated Date 	: 12/11/2012
 */

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
* Updated Date : 8 Jun 2013
* Updated Matter : Format of class.
*/

/**
* Updated By 		 : Manoj
* Updated Date		 : 13 Jun 2013
* Updated Matter	 : Optimization of code and database .
*/

public  class MdsActivationAction extends ActionSupport implements ServletRequestAware,ServletResponseAware
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
			System.out.println("MdsActivationAction , param is : "+param+" userId : "+userId);
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
					MdsActivationDao daoObj=new MdsActivationDao();
					ArrayList portalIdList= daoObj.getPortalIdList();
					session.put("searchPortalIdList",portalIdList);
				}
				session.put("pageNumber",pageNumber);
				session.put("pageSize",pageSize);
				return "searchUser";
			}
			else if(param.equals("searchUserType"))
			{
				session.remove("noOfPages");
				session.remove("userType");
				session.remove("status");
				session.remove("portalId");
				session.remove("buttonStatus");
				session.remove("advanceSearchOption");
				String searchUserType=request.getParameter("userType");
				MdsActivationDao daoObj=new MdsActivationDao();
				String advanceSearchOption="N";
				String portalId=(String)request.getParameter("portalId");
				System.out.println("portalId : "+portalId);
				if(portalId==null)
				{
					portalId=userId;
				}
//				System.out.println("portalId is>>>>>>>>>>>"+portalId);
				
				if(searchUserType.equalsIgnoreCase("mds"))
				{
					String  mdId=(String)request.getParameter("userId");
					System.out.println("mdId>>>>>>>>>>>>>>>>>>>>>>>"+mdId);
					if(mdId==null || mdId=="")
					{
						session.remove("mdsActivationUserId");
						String status=(String)request.getParameter("status");
						session.put("buttonStatus",status);
						if(portalId.equalsIgnoreCase("all") && status.equals("all"))
						{
							int pageNumber=Integer.parseInt((String)request.getParameter("pageNumber"));
							if(pageNumber==1)
							{
								int noOfRecords=daoObj.getNoOfRecordsAllMdsDetails();
								if(noOfRecords==0)
								{
									request.setAttribute("message","Please Check Input Value.");
									return "searchUser";
								}
								session.put("noOfRecords",noOfRecords);
							}
//							System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
							ArrayList mdsDetails=daoObj.getAllMdsDetail(pageNumber);
							session.put("userType",searchUserType);
							session.put("pageNumber",pageNumber);
							session.put("status",status);
							session.put("portalId",portalId);
							session.put("advanceSearchOption",advanceSearchOption);
							session.put("mdsSearchList",mdsDetails);
							//System.out.println("mdsList >>>>>>>>>>>>>>>>>>>"+mdsDetails);
							return "mdsList";
						}
						if(portalId.equalsIgnoreCase("all") && !status.equals("all"))
						{
							int pageNumber=Integer.parseInt((String)request.getParameter("pageNumber"));
							if(status.equalsIgnoreCase("blocked") || status.equalsIgnoreCase("unblocked"))
							{
								if(pageNumber==1)
								{
									int noOfRecords=daoObj.getNoOfRecordsAllMdsDetailsBlockStatusWise(status);
									if(noOfRecords==0)
									{
										request.setAttribute("message","Please Check Input Value.");
										return "searchUser";
									}
									session.put("noOfRecords",noOfRecords);
								}
//								System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
								ArrayList mdsDetails=daoObj.getMdsDetailsBlockStatusWise(status,pageNumber);
								session.put("userType",searchUserType);
								session.put("pageNumber",pageNumber);
								session.put("status",status);
								session.put("portalId",portalId);
								session.put("advanceSearchOption",advanceSearchOption);
								session.put("mdsSearchList",mdsDetails);
								return "mdsList";
								
							}
							else
							{
								if(pageNumber==1)
								{
									int noOfRecords=daoObj.getNoOfRecordsAllMdsDetailsStatusWise(status);
									if(noOfRecords==0)
									{
										request.setAttribute("message","Please Check Input Value.");
										return "searchUser";
									}
									session.put("noOfRecords",noOfRecords);
								}
//								System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
								ArrayList mdsDetails=daoObj.getMdsDetailsStatusWise(status,pageNumber);
								session.put("userType",searchUserType);
								session.put("pageNumber",pageNumber);
								session.put("status",status);
								session.put("portalId",portalId);
								session.put("advanceSearchOption",advanceSearchOption);
								session.put("mdsSearchList",mdsDetails);
								return "mdsList";
							}
						}
						if(!portalId.equalsIgnoreCase("all") && status.equals("all"))
						{
							int pageNumber=Integer.parseInt((String)request.getParameter("pageNumber"));
							if(pageNumber==1)
							{
								int noOfRecords=daoObj.getNoOfRecordsAllMdsDetailsPortalIdWise(portalId);
								if(noOfRecords==0)
								{
									request.setAttribute("message","Please Check Input Value.");
									return "searchUser";
								}
								session.put("noOfRecords",noOfRecords);
							}
//							System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
							ArrayList mdsDetails=daoObj.getMdsDetailsPortalWise(portalId,pageNumber);
							session.put("userType",searchUserType);
							session.put("pageNumber",pageNumber);
							session.put("status",status);
							session.put("portalId",portalId);
							session.put("advanceSearchOption",advanceSearchOption);
							session.put("mdsSearchList",mdsDetails);
							return "mdsList";
						} 
						if(!portalId.equalsIgnoreCase("all") && !status.equals("all"))
						{
							int pageNumber=Integer.parseInt((String)request.getParameter("pageNumber"));
							if(status.equalsIgnoreCase("blocked") || status.equalsIgnoreCase("unblocked"))
							{
								if(pageNumber==1)
								{
									int noOfRecords=daoObj.getNoOfRecordsAllMdsDetailsPortalIdBlockStatusWise(portalId,status);
									if(noOfRecords==0)
									{
										request.setAttribute("message","Please Check Input Value.");
										return "searchUser";
									}
									session.put("noOfRecords",noOfRecords);
								}
//								System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
								ArrayList mdsDetails=daoObj.getMdsDetailsOfPortalIdBlockStatusWise(portalId,status,pageNumber);
								session.put("userType",searchUserType);
								session.put("pageNumber",pageNumber);
								session.put("status",status);
								session.put("portalId",portalId);
								session.put("advanceSearchOption",advanceSearchOption);
								session.put("mdsSearchList",mdsDetails);
								return "mdsList";
							}
							else
							{
								if(pageNumber==1)
								{
									int noOfRecords=daoObj.getNoOfRecordsAllMdsDetailsPortalIdStatusWise(portalId,status);
									if(noOfRecords==0)
									{
										request.setAttribute("message","Please Check Input Value.");
										return "searchUser";
									}
									session.put("noOfRecords",noOfRecords);
								}
//								System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
								ArrayList mdsDetails=daoObj.getMdsDetailsOfPortalIdStatusWise(portalId,status,pageNumber);
								session.put("userType",searchUserType);
								session.put("pageNumber",pageNumber);
								session.put("status",status);
								session.put("portalId",portalId);
								session.put("advanceSearchOption",advanceSearchOption);
								session.put("mdsSearchList",mdsDetails);
								return "mdsList";
							}
						}	
					}
					else
					{
						int pageNumber=Integer.parseInt((String)request.getParameter("pageNumber"));
						if(pageNumber==1)
						{
							int noOfRecords=daoObj.getNoOfRecordsAllMdsDetailsMdIdWise(mdId,portalId);
							if(noOfRecords==0)
							{
								request.setAttribute("message","Please Check Input Value.");
								return "searchUser";
							}
							session.put("noOfRecords",noOfRecords);
						}
						ArrayList mdsDetails=daoObj.getMdsDetailsMdIdWise(mdId,pageNumber,portalId);
						session.put("userType",searchUserType);
						session.put("pageNumber",pageNumber);
						session.put("mdsActivationUserId",mdId);
						session.put("buttonStatus","all");
						session.put("portalId",portalId);
						session.put("advanceSearchOption",advanceSearchOption);
						session.put("mdsSearchList",mdsDetails);
						return "mdsList";
					}
				}
			}	
			
			else if(param.equalsIgnoreCase("activateMds"))
			{
				HashMap dynamicDetails=(HashMap) session.get("dynamicDetails");
				String clientFlag=(String)dynamicDetails.get("clientFlag");
				String[] mdIds=request.getParameterValues("mdIds");
				
				MdsActivationDao daoObj=new MdsActivationDao();
				daoObj.activateMds(mdIds,session);
				ArrayList activatedMdsList=(ArrayList)session.get("activatedMdsList");
				
				String message="";
				
				if(activatedMdsList.size()>0)
				{
					message="Selected User/s has been Activated.";
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
					if(searchUserType.equalsIgnoreCase("mds"))
					{
						String  mdId=(String)session.get("mdsActivationUserId");
						
						if(mdId==null || mdId=="")
						{
							session.remove("mdsActivationUserId");
							String status=(String)session.get("status");
							session.put("buttonStatus",status);
							if(portalId.equalsIgnoreCase("all") && status.equals("all"))
							{
								int pageNumber=(Integer)session.get("pageNumber");
								if(pageNumber==1)
								{
									int noOfRecords=daoObj.getNoOfRecordsAllMdsDetails();
									if(noOfRecords==0)
									{
										request.setAttribute("message","Please Check Input Value.");
										return "searchUser";
									}
									session.put("noOfRecords",noOfRecords);
								}
//								System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
								ArrayList mdsDetails=daoObj.getAllMdsDetail(pageNumber);
								session.put("userType",searchUserType);
								session.put("pageNumber",pageNumber);
								session.put("status",status);
								session.put("portalId",portalId);
								session.put("advanceSearchOption",advanceSearchOption);
								session.put("mdsSearchList",mdsDetails);
								return "mdsList";
							}
							
							if(portalId.equalsIgnoreCase("all") && !status.equals("all"))
							{
								int pageNumber=(Integer)session.get("pageNumber");
								if(status.equalsIgnoreCase("blocked") || status.equalsIgnoreCase("unblocked"))
								{
									if(pageNumber==1)
									{
										int noOfRecords=daoObj.getNoOfRecordsAllMdsDetailsBlockStatusWise(status);
										if(noOfRecords==0)
										{
											request.setAttribute("message","Please Check Input Value.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
						
									ArrayList mdsDetails=daoObj.getMdsDetailsBlockStatusWise(status,pageNumber);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("portalId",portalId);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("mdsSearchList",mdsDetails);
									return "mdsList";
								}
								else{
									if(pageNumber==1){
										int noOfRecords=daoObj.getNoOfRecordsAllMdsDetailsStatusWise(status);
										if(noOfRecords==0){
											request.setAttribute("message","Please Check Input Value.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
						
									ArrayList mdsDetails=daoObj.getMdsDetailsStatusWise(status,pageNumber);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("portalId",portalId);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("mdsSearchList",mdsDetails);
									return "mdsList";
								}
							}
							if(!portalId.equalsIgnoreCase("all") && status.equals("all")){
								int pageNumber=(Integer)session.get("pageNumber");
								if(pageNumber==1){
									int noOfRecords=daoObj.getNoOfRecordsAllMdsDetailsPortalIdWise(portalId);
									if(noOfRecords==0){
										request.setAttribute("message","Please Check Input Value.");
										return "searchUser";
									}
									session.put("noOfRecords",noOfRecords);
								}
							
								ArrayList mdsDetails=daoObj.getMdsDetailsPortalWise(portalId,pageNumber);
								session.put("userType",searchUserType);
								session.put("pageNumber",pageNumber);
								session.put("status",status);
								session.put("portalId",portalId);
								session.put("advanceSearchOption",advanceSearchOption);
								session.put("mdsSearchList",mdsDetails);
								return "mdsList";
							} 
							if(!portalId.equalsIgnoreCase("all") && !status.equals("all")){
								int pageNumber=(Integer)session.get("pageNumber");
								if(status.equalsIgnoreCase("blocked") || status.equalsIgnoreCase("unblocked")){
									if(pageNumber==1){
										int noOfRecords=daoObj.getNoOfRecordsAllMdsDetailsPortalIdBlockStatusWise(portalId,status);
										if(noOfRecords==0){
											request.setAttribute("message","Please Check Input Value.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
							
									ArrayList mdsDetails=daoObj.getMdsDetailsOfPortalIdBlockStatusWise(portalId,status,pageNumber);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("portalId",portalId);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("mdsSearchList",mdsDetails);
									return "mdsList";
								}
								else{
									if(pageNumber==1){
										int noOfRecords=daoObj.getNoOfRecordsAllMdsDetailsPortalIdStatusWise(portalId,status);
										if(noOfRecords==0){
											session.put("message","Please Check Input Value.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
							
									ArrayList mdsDetails=daoObj.getMdsDetailsOfPortalIdStatusWise(portalId,status,pageNumber);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("portalId",portalId);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("mdsSearchList",mdsDetails);
									return "mdsList";
								}
							}
						}
						else{
							int pageNumber=(Integer)session.get("pageNumber");
							if(pageNumber==1){
								int noOfRecords=daoObj.getNoOfRecordsAllMdsDetailsMdIdWise(mdId,portalId);
								if(noOfRecords==0){
									session.put("message","Please Check Input Value.");
								  return "searchUser";
								}
								session.put("noOfRecords",noOfRecords);
							}
							ArrayList mdsDetails=daoObj.getMdsDetailsMdIdWise(mdId,pageNumber,portalId);
							session.put("userType",searchUserType);
							session.put("mdsActivationUserId",mdId);
							session.put("pageNumber",pageNumber);
							session.put("buttonStatus","all");
							session.put("portalId",portalId);
							session.put("advanceSearchOption",advanceSearchOption);
							session.put("mdsSearchList",mdsDetails);
							return "mdsList";
						}
					}
				}
				
				return "mdsList";
			}	
			else if(param.equalsIgnoreCase("deactivateMds")){
				HashMap dynamicDetails=(HashMap) session.get("dynamicDetails");
				String clientFlag=(String)dynamicDetails.get("clientFlag");
				String[] mdIds=request.getParameterValues("mdIds");
				
				MdsActivationDao daoObj=new MdsActivationDao();
				daoObj.deactivateMds(mdIds,session);
				ArrayList activatedMdsList=(ArrayList)session.get("activatedMdsList");
				ArrayList deactivatedMdsList=(ArrayList)session.get("deactivatedMdsList");
				String message;
				if(deactivatedMdsList.size()>0){
					message="Selected User/s has been Deactivated.";
				}
				else{
					message="Process aborted due to Technical Failure.";
				}
				
				request.setAttribute("message",message);
				String searchUserType=(String)session.get("userType");
			
				String advanceSearchOption="N";
			
				String portalId=(String)session.get("portalId");
				if(advanceSearchOption.equalsIgnoreCase("N")){
					if(searchUserType.equalsIgnoreCase("mds")){
						String  mdId=(String)session.get("mdsActivationUserId");
						
						if(mdId==null || mdId==""){
							session.remove("mdsActivationUserId");
						
							String status=(String)session.get("status");
						
							session.put("buttonStatus",status);
							if(portalId.equalsIgnoreCase("all") && status.equals("all")){
								int pageNumber=(Integer)session.get("pageNumber");
								if(pageNumber==1){
									int noOfRecords=daoObj.getNoOfRecordsAllMdsDetails();
									if(noOfRecords==0){
										request.setAttribute("message","Please Check Input Value.");
										return "searchUser";
									}
									session.put("noOfRecords",noOfRecords);
								}
						
								ArrayList mdsDetails=daoObj.getAllMdsDetail(pageNumber);
								session.put("userType",searchUserType);
								session.put("pageNumber",pageNumber);
								session.put("status",status);
								session.put("portalId",portalId);
								session.put("advanceSearchOption",advanceSearchOption);
								session.put("mdsSearchList",mdsDetails);
								return "mdsList";
							}
							if(portalId.equalsIgnoreCase("all") && !status.equals("all")){
								int pageNumber=(Integer)session.get("pageNumber");
								if(status.equalsIgnoreCase("blocked") || status.equalsIgnoreCase("unblocked")){
									if(pageNumber==1){
										int noOfRecords=daoObj.getNoOfRecordsAllMdsDetailsBlockStatusWise(status);
										if(noOfRecords==0){
											request.setAttribute("message","Please Check Input Value.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
						
									ArrayList mdsDetails=daoObj.getMdsDetailsBlockStatusWise(status,pageNumber);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("portalId",portalId);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("mdsSearchList",mdsDetails);
									return "mdsList";
								}
								else{
									
									if(pageNumber==1){
										int noOfRecords=daoObj.getNoOfRecordsAllMdsDetailsStatusWise(status);
										if(noOfRecords==0){
											request.setAttribute("message","Please Check Input Value.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
									
									ArrayList mdsDetails=daoObj.getMdsDetailsStatusWise(status,pageNumber);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("portalId",portalId);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("mdsSearchList",mdsDetails);
									return "mdsList";
								}
							}
							if(!portalId.equalsIgnoreCase("all") && status.equals("all")){
								int pageNumber=(Integer)session.get("pageNumber");
								if(pageNumber==1){
									int noOfRecords=daoObj.getNoOfRecordsAllMdsDetailsPortalIdWise(portalId);
									if(noOfRecords==0){
										request.setAttribute("message","Please Check Input Value.");
										return "searchUser";
									}
									session.put("noOfRecords",noOfRecords);
								}
								
								ArrayList mdsDetails=daoObj.getMdsDetailsPortalWise(portalId,pageNumber);
								session.put("userType",searchUserType);
								session.put("pageNumber",pageNumber);
								session.put("status",status);
								session.put("portalId",portalId);
								session.put("advanceSearchOption",advanceSearchOption);
								session.put("mdsSearchList",mdsDetails);
								return "mdsList";
							} 
							if(!portalId.equalsIgnoreCase("all") && !status.equals("all")){
								int pageNumber=(Integer)session.get("pageNumber");
								if(status.equalsIgnoreCase("blocked") || status.equalsIgnoreCase("unblocked")){
									if(pageNumber==1){
										int noOfRecords=daoObj.getNoOfRecordsAllMdsDetailsPortalIdBlockStatusWise(portalId,status);
										if(noOfRecords==0){
											request.setAttribute("message","Please Check Input Value.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
								
									ArrayList mdsDetails=daoObj.getMdsDetailsOfPortalIdBlockStatusWise(portalId,status,pageNumber);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("portalId",portalId);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("mdsSearchList",mdsDetails);
									return "mdsList";
								}
								else{
									if(pageNumber==1){
										int noOfRecords=daoObj.getNoOfRecordsAllMdsDetailsPortalIdStatusWise(portalId,status);
										if(noOfRecords==0){
											request.setAttribute("message","Please Check Input Value.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
									
									ArrayList mdsDetails=daoObj.getMdsDetailsOfPortalIdStatusWise(portalId,status,pageNumber);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("portalId",portalId);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("mdsSearchList",mdsDetails);
									return "mdsList";
								}
							}
						}
						else{
							int pageNumber=(Integer)session.get("pageNumber");
							if(pageNumber==1){
								int noOfRecords=daoObj.getNoOfRecordsAllMdsDetailsMdIdWise(mdId,portalId);
								if(noOfRecords==0){
									request.setAttribute("message","Please Check Input Value.");
									return "searchUser";
								}
								session.put("noOfRecords",noOfRecords);
							}
							ArrayList mdsDetails=daoObj.getMdsDetailsMdIdWise(mdId,pageNumber,portalId);
							session.put("userType",searchUserType);
							session.put("pageNumber",pageNumber);
							session.put("mdsActivationUserId",mdId);
							session.put("buttonStatus","all");
							session.put("portalId",portalId);							 
							session.put("advanceSearchOption",advanceSearchOption);
							session.put("mdsSearchList",mdsDetails);
							return "mdsList";
						}
					}
				}
				
				return "mdsList";
			}	
			else if(param.equalsIgnoreCase("blockMds")){
				HashMap dynamicDetails=(HashMap) session.get("dynamicDetails");
				String clientFlag=(String)dynamicDetails.get("clientFlag");
				String validUpto=request.getParameter("dateOfActivation");
				String[] mdIds=request.getParameterValues("mdIds");
				
				MdsActivationDao daoObj=new MdsActivationDao();
				daoObj.blockMds(mdIds,session);
				ArrayList blockedMdsList=(ArrayList)session.get("blockedMdsList");
				ArrayList unblockedMdsList=(ArrayList)session.get("unblockedMdsList");
				String message="";
				
				if(blockedMdsList.size()>0){
					message="Selected User/s has been Blocked.";
				}
				else{
					message="Process aborted due to Technical Failure.";
				}
				
				request.setAttribute("message",message);
				String searchUserType=(String)session.get("userType");
				
				String advanceSearchOption="N";
				
				String portalId=(String)session.get("portalId");
				if(advanceSearchOption.equalsIgnoreCase("N")){
					if(searchUserType.equalsIgnoreCase("mds")){
						String  mdId=(String)session.get("mdsActivationUserId");
					
						if(mdId==null || mdId==""){
							session.remove("mdsActivationUserId"); 
							String status=(String)session.get("status");
							session.put("buttonStatus",status);
							if(portalId.equalsIgnoreCase("all") && status.equals("all")){
								int pageNumber=(Integer)session.get("pageNumber");
								if(pageNumber==1){
									int noOfRecords=daoObj.getNoOfRecordsAllMdsDetails();
									if(noOfRecords==0){
										request.setAttribute("message","Please Check Input Value.");
										return "searchUser";
									}
									session.put("noOfRecords",noOfRecords);
								}

								ArrayList mdsDetails=daoObj.getAllMdsDetail(pageNumber);
								session.put("userType",searchUserType);
								session.put("pageNumber",pageNumber);
								session.put("status",status);
								session.put("portalId",portalId);
								session.put("advanceSearchOption",advanceSearchOption);
								session.put("mdsSearchList",mdsDetails);
								return "mdsList";
							}
							if(portalId.equalsIgnoreCase("all") && !status.equals("all")){
								int pageNumber=(Integer)session.get("pageNumber");
								if(status.equalsIgnoreCase("blocked") || status.equalsIgnoreCase("unblocked")){
									if(pageNumber==1){
										int noOfRecords=daoObj.getNoOfRecordsAllMdsDetailsBlockStatusWise(status);
										if(noOfRecords==0){
											request.setAttribute("message","Please Check Input Value.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
									
									ArrayList mdsDetails=daoObj.getMdsDetailsBlockStatusWise(status,pageNumber);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("portalId",portalId);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("mdsSearchList",mdsDetails);
									return "mdsList";
								}
								else{
									if(pageNumber==1){
										int noOfRecords=daoObj.getNoOfRecordsAllMdsDetailsStatusWise(status);
										if(noOfRecords==0){
											request.setAttribute("message","Please Check Input Value.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
									
									ArrayList mdsDetails=daoObj.getMdsDetailsStatusWise(status,pageNumber);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("portalId",portalId);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("mdsSearchList",mdsDetails);
									return "mdsList";
								}
							}
							if(!portalId.equalsIgnoreCase("all") && status.equals("all")){
								int pageNumber=(Integer)session.get("pageNumber");
								if(pageNumber==1){
									int noOfRecords=daoObj.getNoOfRecordsAllMdsDetailsPortalIdWise(portalId);
									if(noOfRecords==0){
										request.setAttribute("message","Please Check Input Value.");
										return "searchUser";
									}
									session.put("noOfRecords",noOfRecords);
								}
								
								ArrayList mdsDetails=daoObj.getMdsDetailsPortalWise(portalId,pageNumber);
								session.put("userType",searchUserType);
								session.put("pageNumber",pageNumber);
								session.put("status",status);
								session.put("portalId",portalId);
								session.put("advanceSearchOption",advanceSearchOption);
								session.put("mdsSearchList",mdsDetails);
								return "mdsList";
							} 
							if(!portalId.equalsIgnoreCase("all") && !status.equals("all")){
								int pageNumber=(Integer)session.get("pageNumber");
								if(status.equalsIgnoreCase("blocked") || status.equalsIgnoreCase("unblocked")){
									if(pageNumber==1){
										int noOfRecords=daoObj.getNoOfRecordsAllMdsDetailsPortalIdBlockStatusWise(portalId,status);
										if(noOfRecords==0){
											request.setAttribute("message","Please Check Input Value.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
									
									ArrayList mdsDetails=daoObj.getMdsDetailsOfPortalIdBlockStatusWise(portalId,status,pageNumber);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("portalId",portalId);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("mdsSearchList",mdsDetails);
									return "mdsList";
								}
								else{
									if(pageNumber==1){
										int noOfRecords=daoObj.getNoOfRecordsAllMdsDetailsPortalIdStatusWise(portalId,status);
										if(noOfRecords==0){
											request.setAttribute("message","Please Check Input Value.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
									
									ArrayList mdsDetails=daoObj.getMdsDetailsOfPortalIdStatusWise(portalId,status,pageNumber);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("portalId",portalId);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("mdsSearchList",mdsDetails);
									return "mdsList";
								}
							}
						}
						else{
							int pageNumber=(Integer)session.get("pageNumber");
							if(pageNumber==1){
								int noOfRecords=daoObj.getNoOfRecordsAllMdsDetailsMdIdWise(mdId,portalId);
								if(noOfRecords==0){
									session.put("mdsActivationMessage","Please Check Input Value.");
									return "searchUser";
								}
								session.put("noOfRecords",noOfRecords);
							}
							ArrayList mdsDetails=daoObj.getMdsDetailsMdIdWise(mdId,pageNumber,portalId);
							session.put("userType",searchUserType);
							session.put("pageNumber",pageNumber);
							session.put("mdsActivationUserId",mdId);
							session.put("buttonStatus","all");
							session.put("portalId",portalId);							 
							session.put("advanceSearchOption",advanceSearchOption);
							session.put("mdsSearchList",mdsDetails);
							return "mdsList";
						}
					}
				}
				
				return "mdsList";
			}	
			else if(param.equalsIgnoreCase("unblockMds")){
				HashMap dynamicDetails=(HashMap) session.get("dynamicDetails");
				String clientFlag=(String)dynamicDetails.get("clientFlag");
				String validUpto=request.getParameter("dateOfActivation");
				String[] mdIds=request.getParameterValues("mdIds");
				
				MdsActivationDao daoObj=new MdsActivationDao();
				daoObj.unblockMds(mdIds,session);
				ArrayList unblockedMdsList=(ArrayList)session.get("unblockedMdsList");
				ArrayList blockedMdsList=(ArrayList)session.get("blockedMdsList");
				String message="";
				
				if(unblockedMdsList.size()>0){
					message="Selected User/s has been Unblocked.";
				}
				else{
					message="Process aborted due to Technical Failure.";
				}
				request.setAttribute("message",message);
				String searchUserType=(String)session.get("userType");
				
				String advanceSearchOption="N";
				
				String portalId=(String)session.get("portalId");
				if(advanceSearchOption.equalsIgnoreCase("N")){
					if(searchUserType.equalsIgnoreCase("mds")){
						String  mdId=(String)session.get("mdsActivationUserId");
				
						if(mdId==null || mdId==""){
							session.remove("mdsActivationUserId");
							String status=(String)session.get("status");
							session.put("buttonStatus",status);
							if(portalId.equalsIgnoreCase("all") && status.equals("all")){
								int pageNumber=(Integer)session.get("pageNumber");
								if(pageNumber==1){
									int noOfRecords=daoObj.getNoOfRecordsAllMdsDetails();
									if(noOfRecords==0){
										request.setAttribute("message","Please Check Input Value.");
										return "searchUser";
									}
									session.put("noOfRecords",noOfRecords);
								}
				
								ArrayList mdsDetails=daoObj.getAllMdsDetail(pageNumber);
								session.put("userType",searchUserType);
								session.put("pageNumber",pageNumber);
								session.put("status",status);
								session.put("portalId",portalId);
								session.put("advanceSearchOption",advanceSearchOption);
								session.put("mdsSearchList",mdsDetails);
								return "mdsList";
							}
							if(portalId.equalsIgnoreCase("all") && !status.equals("all")){
								int pageNumber=(Integer)session.get("pageNumber");
								if(status.equalsIgnoreCase("blocked") || status.equalsIgnoreCase("unblocked")){
									if(pageNumber==1){
										int noOfRecords=daoObj.getNoOfRecordsAllMdsDetailsBlockStatusWise(status);
										if(noOfRecords==0){
											request.setAttribute("message","Please Check Input Value.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
				
									ArrayList mdsDetails=daoObj.getMdsDetailsBlockStatusWise(status,pageNumber);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("portalId",portalId);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("mdsSearchList",mdsDetails);
									return "mdsList";
								}
								else{
									if(pageNumber==1){
										int noOfRecords=daoObj.getNoOfRecordsAllMdsDetailsStatusWise(status);
										if(noOfRecords==0){
											request.setAttribute("message","Please Check Input Value.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
				
									ArrayList mdsDetails=daoObj.getMdsDetailsStatusWise(status,pageNumber);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("portalId",portalId);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("mdsSearchList",mdsDetails);
									return "mdsList";
								}
							}
							if(!portalId.equalsIgnoreCase("all") && status.equals("all")){
								int pageNumber=(Integer)session.get("pageNumber");
								if(pageNumber==1){
									int noOfRecords=daoObj.getNoOfRecordsAllMdsDetailsPortalIdWise(portalId);
									if(noOfRecords==0){
										request.setAttribute("message","Please Check Input Value.");
										return "searchUser";
									}
									session.put("noOfRecords",noOfRecords);
								}
				
								ArrayList mdsDetails=daoObj.getMdsDetailsPortalWise(portalId,pageNumber);
								session.put("userType",searchUserType);
								session.put("pageNumber",pageNumber);
								session.put("status",status);
								session.put("portalId",portalId);
								session.put("advanceSearchOption",advanceSearchOption);
								session.put("mdsSearchList",mdsDetails);
								return "mdsList";
							} 
							if(!portalId.equalsIgnoreCase("all") && !status.equals("all")){
								int pageNumber=(Integer)session.get("pageNumber");
								if(status.equalsIgnoreCase("blocked") || status.equalsIgnoreCase("unblocked")){
									if(pageNumber==1){
										int noOfRecords=daoObj.getNoOfRecordsAllMdsDetailsPortalIdBlockStatusWise(portalId,status);
										if(noOfRecords==0){
											request.setAttribute("message","Please Check Input Value.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
				
									ArrayList mdsDetails=daoObj.getMdsDetailsOfPortalIdBlockStatusWise(portalId,status,pageNumber);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("portalId",portalId);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("mdsSearchList",mdsDetails);
									return "mdsList";
								}
								else{
									if(pageNumber==1){
										int noOfRecords=daoObj.getNoOfRecordsAllMdsDetailsPortalIdStatusWise(portalId,status);
										if(noOfRecords==0){
											session.put("message","Please Check Input Value.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
				
									ArrayList mdsDetails=daoObj.getMdsDetailsOfPortalIdStatusWise(portalId,status,pageNumber);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("portalId",portalId);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("mdsSearchList",mdsDetails);
									return "mdsList";
								}
							}
						}
						else{
							int pageNumber=(Integer)session.get("pageNumber");
							if(pageNumber==1){
							  int noOfRecords=daoObj.getNoOfRecordsAllMdsDetailsMdIdWise(mdId,portalId);
							  if(noOfRecords==0){
								  request.setAttribute("message","Please Check Input Value.");
								  return "searchUser";
							  }
							  session.put("noOfRecords",noOfRecords);
							}
							ArrayList mdsDetails=daoObj.getMdsDetailsMdIdWise(mdId,pageNumber,portalId);
							session.put("userType",searchUserType);
							session.put("pageNumber",pageNumber);
							session.put("mdsActivationUserId",mdId);	
							session.put("buttonStatus","all");
							session.put("portalId",portalId);
							session.put("advanceSearchOption",advanceSearchOption);
							session.put("mdsSearchList",mdsDetails);
							return "mdsList";
						}
					}
				}
				return "mdsList";
			}	
		}catch(Exception e){
			request.setAttribute("message", "Process aborted due to Technical Failure.");
			System.out.println("Exception in  MdsActivationAction");
			System.out.println(e.toString());
			return "registrationPage";
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
