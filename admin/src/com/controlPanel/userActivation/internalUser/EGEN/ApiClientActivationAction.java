package com.controlPanel.userActivation.internalUser.EGEN;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ApiClientActivationAction extends ActionSupport implements ServletRequestAware,ServletResponseAware
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
			System.out.println("ApiClientActivationAction and Param is :"+param);
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
				int pageNumber=1;
				int pageSize=100;
				session.put("pageNumber",pageNumber);
				session.put("pageSize",pageSize);
				return "searchUser";
			}
			else if(param.equals("searchUserType"))
			{
				session.remove("noOfRecords");					 
				session.remove("noOfPages");
				session.remove("userType");
				session.remove("status");
				session.remove("portalId");
				session.remove("buttonStatus");
				session.remove("advanceSearchOption");
				String searchUserType=(String)request.getParameter("userType");
				//System.out.println("searchUserType>>>>>>>>>>>>>>>>>>>>>>>"+searchUserType);
				String advanceSearchOption="N";
				if(advanceSearchOption.equalsIgnoreCase("N"))
				{
					if(searchUserType.equalsIgnoreCase("apiClient"))
					{
						String  apiClientId=(String)request.getParameter("userId");
						//System.out.println("apiClientId>>>>>>>>>>>>>>>>>>>>>>>"+apiClientId);
						
						if(apiClientId==null || apiClientId=="")
						{
							session.remove("apiClientActivationUserId");
							String status=(String)request.getParameter("status");
							session.put("buttonStatus",status);
							
							if(status.equals("all"))
							{
								int pageNumber=Integer.parseInt((String)request.getParameter("pageNumber"));
								if(pageNumber==1)
								{
									int noOfRecords=ApiClientActivationDao.getNoOfRecordsAllApiClientDetails();
									if(noOfRecords==0)
									{
										session.put("apiClientActivationMessage","No Records found.");
										return "searchUser";
									}
									session.put("noOfRecords",noOfRecords);
								}
								//System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
								ArrayList apiClientDetails=ApiClientActivationDao.getAllApiClientDetail(pageNumber);
								session.put("userType",searchUserType);
								session.put("pageNumber",pageNumber);
								session.put("status",status);
								session.put("advanceSearchOption",advanceSearchOption);
								session.put("apiClientSearchList",apiClientDetails);
								System.out.println("ApiClientDetails : "+apiClientDetails);
								return "apiClientList";
							}
							if(!status.equals("all"))
							{
								int pageNumber=Integer.parseInt((String)request.getParameter("pageNumber"));
								if(pageNumber==1){
									int noOfRecords=ApiClientActivationDao.getNoOfRecordsAllApiClientDetailsStatusWise(status);
									if(noOfRecords==0)
									{
										session.put("apiClientActivationMessage","No Records found.");
										return "searchUser";
									}
									session.put("noOfRecords",noOfRecords);
								}
								//System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
								ArrayList apiClientDetails=ApiClientActivationDao.getApiClientDetailsStatusWise(status,pageNumber);
								session.put("userType",searchUserType);
								session.put("pageNumber",pageNumber);
								session.put("status",status);
								session.put("advanceSearchOption",advanceSearchOption);
								session.put("apiClientSearchList",apiClientDetails);
								return "apiClientList";
							}
						}
						else
						{
							int pageNumber=Integer.parseInt((String)request.getParameter("pageNumber"));
							if(pageNumber==1)
							{
								int noOfRecords=ApiClientActivationDao.getNoOfRecordsApiClientDetailsUserIdWise(apiClientId);
								if(noOfRecords==0)
								{
									session.put("apiClientActivationMessage","No Records found.");
									return "searchUser";
								}
								session.put("noOfRecords",noOfRecords);
							}
							ArrayList apiClientDetails=ApiClientActivationDao.getApiClientDetailsUserIdWise(apiClientId,pageNumber);
							session.put("userType",searchUserType);
							session.put("pageNumber",pageNumber);
							session.put("apiClientActivationUserId",apiClientId);
							session.put("buttonStatus","all");
							session.put("advanceSearchOption",advanceSearchOption);
							session.put("apiClientSearchList",apiClientDetails);
							return "apiClientList";
						}
					}
				}
			}	
			else if(param.equalsIgnoreCase("activateApiClient"))
			{
				String[] userIds=request.getParameterValues("userIds");
				ApiClientActivationDao.activateApiClient(userIds,session);
				ArrayList activatedApiClientList=(ArrayList)session.get("activatedApiClientList");
				ArrayList deactivatedApiClientList=(ArrayList)session.get("deactivatedApiClientList");
				String message="";
				if(activatedApiClientList.size()>0)
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
				if(advanceSearchOption.equalsIgnoreCase("N"))
				{
					if(searchUserType.equalsIgnoreCase("apiClient"))
					{
						String  apiClientId=(String)session.get("apiClientActivationUserId");
						//System.out.println("apiClientId is>>>>>>>>>>>>>in action class>>"+apiClientId);
						if(apiClientId==null || apiClientId=="")
						{
							session.remove("apiClientActivationUserId");
							String status=(String)session.get("status");
							//System.out.println("status is>>>>>>>>>>>>>in action class>>"+status);
							session.put("buttonStatus",status);
							if(status.equals("all"))
							{
								int pageNumber=(Integer)session.get("pageNumber");
								if(pageNumber==1)
								{
									int noOfRecords=ApiClientActivationDao.getNoOfRecordsAllApiClientDetails();
									if(noOfRecords==0)
									{
										session.put("apiClientActivationMessage","No Records found.");
										return "searchUser";
									}
									session.put("noOfRecords",noOfRecords);
								}
								//System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
								ArrayList apiClientDetails=ApiClientActivationDao.getAllApiClientDetail(pageNumber);
								session.put("userType",searchUserType);
								session.put("pageNumber",pageNumber);
								session.put("status",status);
								session.put("advanceSearchOption",advanceSearchOption);
								session.put("apiClientSearchList",apiClientDetails);

								return "apiClientList";
							}
							if(!status.equals("all"))
							{
								int pageNumber=(Integer)session.get("pageNumber");
								if(status.equalsIgnoreCase("blocked") || status.equalsIgnoreCase("unblocked"))
								{
									
								}
								else
								{
									if(pageNumber==1)
									{
										int noOfRecords=ApiClientActivationDao.getNoOfRecordsAllApiClientDetailsStatusWise(status);
										if(noOfRecords==0)
										{
											session.put("apiClientActivationMessage","No Records found.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
									//System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
									ArrayList apiClientDetails=ApiClientActivationDao.getApiClientDetailsStatusWise(status,pageNumber);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("apiClientSearchList",apiClientDetails);
									
									return "apiClientList";
								}
							}
						}
						else
						{
							int pageNumber=(Integer)session.get("pageNumber");
							if(pageNumber==1)
							{
								int noOfRecords=ApiClientActivationDao.getNoOfRecordsApiClientDetailsUserIdWise(apiClientId);
								if(noOfRecords==0)
								{
									session.put("apiClientActivationMessage","No Records found.");
									return "searchUser";
								}
								session.put("noOfRecords",noOfRecords);
							}
							ArrayList apiClientDetails=ApiClientActivationDao.getApiClientDetailsUserIdWise(apiClientId,pageNumber);

							session.put("userType",searchUserType);
							session.put("pageNumber",pageNumber);
							session.put("apiClientActivationUserId",apiClientId);
							session.put("buttonStatus","all");
							
							session.put("advanceSearchOption",advanceSearchOption);
							session.put("apiClientSearchList",apiClientDetails);
							return "apiClientList";
						}
					}
				}
			}
			else if(param.equalsIgnoreCase("deactivateApiClient"))
			{
				HashMap dynamicDetails=(HashMap) session.get("dynamicDetails");
				String clientFlag=(String)dynamicDetails.get("clientFlag");
				String[] userIds=request.getParameterValues("userIds");
				ApiClientActivationDao.deactivateApiClient(userIds,session);
				ArrayList activatedApiClientList=(ArrayList)session.get("activatedApiClientList");
				ArrayList deactivatedApiClientList=(ArrayList)session.get("deactivatedApiClientList");
				String message="";
				
				if(deactivatedApiClientList.size()>0)
				{
					message="Selected User/s has been Deactivated.";
				}
				else
				{
					message="Process aborted due to Technical Failure.";
				}
				
				request.setAttribute("message",message);
				String searchUserType=(String)session.get("userType");
				//System.out.println("searchUserType is>>>>>>>>>>>>>in action class>>"+searchUserType);
				String advanceSearchOption="N";
				//System.out.println("advanceSearchOption is>>>>>>>>>>>>>in action class>>"+advanceSearchOption);
				if(advanceSearchOption.equalsIgnoreCase("N"))
				{
					if(searchUserType.equalsIgnoreCase("apiClient"))
					{
						String  apiClientId=(String)session.get("apiClientActivationUserId");
					  	//System.out.println("apiClientId is>>>>>>>>>>>>>in action class>>"+apiClientId);
					  	if(apiClientId==null || apiClientId=="")
					  	{
					  		session.remove("apiClientActivationUserId");
					  		String status=(String)session.get("status");
						    //System.out.println("status is>>>>>>>>>>>>>in action class>>"+status);
						    session.put("buttonStatus",status);
						    
						    if(status.equals("all"))
						    {
						    	int pageNumber=(Integer)session.get("pageNumber");;
						    	if(pageNumber==1)
						    	{
						    		int noOfRecords=ApiClientActivationDao.getNoOfRecordsAllApiClientDetails();
						    		if(noOfRecords==0)
						    		{
						    			session.put("apiClientActivationMessage","No Records found.");
						    			return "searchUser";
						    		}
						    		session.put("noOfRecords",noOfRecords);
						    	}
						    	//System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
						    	ArrayList apiClientDetails=ApiClientActivationDao.getAllApiClientDetail(pageNumber);
						    	session.put("userType",searchUserType);
						    	session.put("pageNumber",pageNumber);
						    	session.put("status",status);
						    	session.put("advanceSearchOption",advanceSearchOption);
						    	session.put("apiClientSearchList",apiClientDetails);
						    	return "apiClientList";
						    }
						    if(!status.equals("all"))
						    {
						    	int pageNumber=(Integer)session.get("pageNumber");;
						    	if(status.equalsIgnoreCase("blocked") || status.equalsIgnoreCase("unblocked")){}
						    	else
						    	{
						    		if(pageNumber==1)
						    		{
						    			int noOfRecords=ApiClientActivationDao.getNoOfRecordsAllApiClientDetailsStatusWise(status);
						    			if(noOfRecords==0)
						    			{
						    				session.put("apiClientActivationMessage","No Records found.");
						    				return "searchUser";
						    			}
						    			session.put("noOfRecords",noOfRecords);
						    		}
						    		//System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
						    		ArrayList apiClientDetails=ApiClientActivationDao.getApiClientDetailsStatusWise(status,pageNumber);
						    		session.put("userType",searchUserType);
						    		session.put("pageNumber",pageNumber);
						    		session.put("status",status);
						    		session.put("advanceSearchOption",advanceSearchOption);
						    		session.put("apiClientSearchList",apiClientDetails);
						    		return "apiClientList";
						    	}
						    }
					  	}
					  	else
					  	{
					  		int pageNumber=(Integer)session.get("pageNumber");
					  		if(pageNumber==1)
					  		{
					  			int noOfRecords=ApiClientActivationDao.getNoOfRecordsApiClientDetailsUserIdWise(apiClientId);
					  			if(noOfRecords==0)
					  			{
					  				session.put("apiClientActivationMessage","No Records found.");
					  				return "searchUser";
					  			}
					  			session.put("noOfRecords",noOfRecords);
					  		}
					  		ArrayList apiClientDetails=ApiClientActivationDao.getApiClientDetailsUserIdWise(apiClientId,pageNumber);
					  		session.put("userType",searchUserType);
					  		session.put("pageNumber",pageNumber);
					  		session.put("apiClientActivationUserId",apiClientId);
					  		session.put("buttonStatus","all");
					  		session.put("advanceSearchOption",advanceSearchOption);
					  		session.put("apiClientSearchList",apiClientDetails);
					  		return "apiClientList";
					  	}
					}
				}
				return "apiClientList";	
			}	
			else if(param.equalsIgnoreCase("blockApiClient"))
			{
				String[] userIds=request.getParameterValues("userIds");
				ApiClientActivationDao.blockApiClient(userIds,session);
				ArrayList blockedApiClientList=(ArrayList)session.get("blockedApiClientList");
				ArrayList unblockedApiClientList=(ArrayList)session.get("unblockedApiClientList");
				String message="";
				
				if(blockedApiClientList.size()>0)
				{
					message="Selected User/s has been Blocked.";
				}
				else
				{
					message="Process aborted due to Technical Failure.";
				}
				
				request.setAttribute("message",message);
				String searchUserType=(String)session.get("userType");
				String advanceSearchOption="N";
				if(advanceSearchOption.equalsIgnoreCase("N")){
					if(searchUserType.equalsIgnoreCase("apiClient"))
					{
						String  apiClientId=(String)session.get("apiClientActivationUserId");
						//System.out.println("apiClientId is>>>>>>>>>>>>>in action class>>"+apiClientId);
						if(apiClientId==null || apiClientId=="")
						{
							session.remove("apiClientActivationUserId");
							String status=(String)session.get("status");
							//System.out.println("status is>>>>>>>>>>>>>in action class>>"+status);
							session.put("buttonStatus",status);
							if(status.equals("all"))
							{
								int pageNumber=(Integer)session.get("pageNumber");
								if(pageNumber==1)
								{
									int noOfRecords=ApiClientActivationDao.getNoOfRecordsAllApiClientDetails();
									if(noOfRecords==0)
									{
										session.put("apiClientActivationMessage","No Records found.");
										return "searchUser";
									}
									session.put("noOfRecords",noOfRecords);
								}
								//System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
								ArrayList apiClientDetails=ApiClientActivationDao.getAllApiClientDetail(pageNumber);
								session.put("userType",searchUserType);
								session.put("pageNumber",pageNumber);
								session.put("status",status);
								session.put("advanceSearchOption",advanceSearchOption);
								session.put("apiClientSearchList",apiClientDetails);
								return "apiClientList";
							}
							if(!status.equals("all"))
							{
								int pageNumber=(Integer)session.get("pageNumber");
								if(status.equalsIgnoreCase("blocked") || status.equalsIgnoreCase("unblocked")){}
								else
								{
									if(pageNumber==1)
									{
										int noOfRecords=ApiClientActivationDao.getNoOfRecordsAllApiClientDetailsStatusWise(status);
										if(noOfRecords==0)
										{
											session.put("apiClientActivationMessage","No Records found.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
									//System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
									ArrayList apiClientDetails=ApiClientActivationDao.getApiClientDetailsStatusWise(status,pageNumber);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("apiClientSearchList",apiClientDetails);
									return "apiClientList";
								}
							}
						}
						else
						{
							int pageNumber=(Integer)session.get("pageNumber");
							if(pageNumber==1)
							{
								int noOfRecords=ApiClientActivationDao.getNoOfRecordsApiClientDetailsUserIdWise(apiClientId);
								if(noOfRecords==0)
								{
									session.put("apiClientActivationMessage","No Records found.");
									return "searchUser";
								}
								session.put("noOfRecords",noOfRecords);
							}
							ArrayList apiClientDetails=ApiClientActivationDao.getApiClientDetailsUserIdWise(apiClientId,pageNumber);
							session.put("userType",searchUserType);
							session.put("pageNumber",pageNumber);
							session.put("apiClientActivationUserId",apiClientId);
							session.put("buttonStatus","all");
							session.put("advanceSearchOption",advanceSearchOption);
							session.put("apiClientSearchList",apiClientDetails);
							return "apiClientList";
						}
					}
				}
			}
			else if(param.equalsIgnoreCase("unblockApiClient"))
			{
				String[] userIds=request.getParameterValues("userIds");
				ApiClientActivationDao.unblockApiClient(userIds,session);
				ArrayList blockedApiClientList=(ArrayList)session.get("blockedApiClientList");
				ArrayList unblockedApiClientList=(ArrayList)session.get("unblockedApiClientList");
				String message="";
				if(unblockedApiClientList.size()>0)
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
				if(advanceSearchOption.equalsIgnoreCase("N"))
				{
					if(searchUserType.equalsIgnoreCase("apiClient"))
					{
						String  apiClientId=(String)session.get("apiClientActivationUserId");
						//System.out.println("apiClientId is>>>>>>>>>>>>>in action class>>"+apiClientId);
						if(apiClientId==null || apiClientId=="")
						{
							session.remove("apiClientActivationUserId");
							String status=(String)session.get("status");
							//System.out.println("status is>>>>>>>>>>>>>in action class>>"+status);
							session.put("buttonStatus",status);
							if(status.equals("all"))
							{
								int pageNumber=(Integer)session.get("pageNumber");
								if(pageNumber==1)
								{
									int noOfRecords=ApiClientActivationDao.getNoOfRecordsAllApiClientDetails();
									if(noOfRecords==0)
									{
										session.put("apiClientActivationMessage","No Records found.");
										return "searchUser";
									}
									session.put("noOfRecords",noOfRecords);
								}
								//System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
								ArrayList apiClientDetails=ApiClientActivationDao.getAllApiClientDetail(pageNumber);
								session.put("userType",searchUserType);
								session.put("pageNumber",pageNumber);
								session.put("status",status);
								session.put("advanceSearchOption",advanceSearchOption);
								session.put("apiClientSearchList",apiClientDetails);
								return "apiClientList";
							}
							if(!status.equals("all"))
							{
								int pageNumber=(Integer)session.get("pageNumber");
								if(status.equalsIgnoreCase("blocked") || status.equalsIgnoreCase("unblocked")){}
								else
								{
									if(pageNumber==1)
									{
										int noOfRecords=ApiClientActivationDao.getNoOfRecordsAllApiClientDetailsStatusWise(status);
										if(noOfRecords==0)
										{
											session.put("apiClientActivationMessage","No Records found.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
									//System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
									ArrayList apiClientDetails=ApiClientActivationDao.getApiClientDetailsStatusWise(status,pageNumber);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("apiClientSearchList",apiClientDetails);
									return "apiClientList";
								}
							}
						}
						else
						{
							int pageNumber=(Integer)session.get("pageNumber");
							if(pageNumber==1)
							{
								int noOfRecords=ApiClientActivationDao.getNoOfRecordsApiClientDetailsUserIdWise(apiClientId);
								if(noOfRecords==0)
								{
									session.put("apiClientActivationMessage","No Records found.");
									return "searchUser";
								}
								session.put("noOfRecords",noOfRecords);
							}
							ArrayList apiClientDetails=ApiClientActivationDao.getApiClientDetailsUserIdWise(apiClientId,pageNumber);
							session.put("userType",searchUserType);
							session.put("pageNumber",pageNumber);
							session.put("apiClientActivationUserId",apiClientId);
							session.put("buttonStatus","all");
							session.put("advanceSearchOption",advanceSearchOption);
							session.put("apiClientSearchList",apiClientDetails);
							return "apiClientList";
						}
					}
				}
			}
			else if(param.equalsIgnoreCase("getApiApi"))
			{
				//System.out.println("we are into getApi details ");
				String corporateAgentId=request.getParameter("corporateAgentId");
				//System.out.println(corporateAgentId);
				ApiClientActivationDao dao=new ApiClientActivationDao();
				HashMap map=dao.getApiIp(corporateAgentId);
				//System.out.println("map is "+map);
				map.put("corporateAgentId", corporateAgentId);
				request.setAttribute("data",map );
				return "editApiIPPage";
			}
			else if(param.equalsIgnoreCase("updateApiIP"))
			{
				String id=request.getParameter("agentId");
				String ip1=request.getParameter("authorizedIp1");
				String ip2=request.getParameter("authorizedIp2");
				String status=request.getParameter("status");
				ApiClientActivationDao dao=new ApiClientActivationDao();
			    String result=dao.updateApiIp(id, ip1, ip2, status);
			    
			    if(result.equalsIgnoreCase("success"))
			    {
			    	request.setAttribute("message", "Proceess has been completed.");
			    }else
			    {
			    	request.setAttribute("message", "Process aborted due to Technical Failure.");
			    }
			    
			    HashMap map=dao.getApiIp(id);
				map.put("corporateAgentId", id);
				request.setAttribute("data",map );
			    return "editApiIPPage";
			}
		}catch(Exception e)
		{
			System.out.println("Exception in ApiClientActivationAction");
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
