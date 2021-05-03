package com.controlPanel.userActivation.retailsUser.agent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.formBean.agent.AgentDetailsFormBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Updated By 		 : Manoj
 * Updated Date		 : 18 Jun 2013
 * Updated Matter	 : Optimization of code and database .
 */
public  class AgentActivationAction extends ActionSupport implements ServletRequestAware,ServletResponseAware
{
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request; 
	private HttpServletResponse response;
	private Map session;

	public String execute() throws Exception
	{ 
		Map session=ActionContext.getContext().getSession();
		try{
			String userId=(String)session.get("userId");

			if(userId==null){
				request.setAttribute("message","Your Login Session has Expired. Please Login Again.");
				return "sessionExpire";
			}
			HashMap dynamicDetails=(HashMap) session.get("dynamicDetails");
			String clientFlag=(String)dynamicDetails.get("clientFlag");
			String param=request.getParameter("param");
			System.out.println("AgentActivationAction and param is:"+param);
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
				String userType=(String)session.get("loginType");
				int pageNumber=1;
				int pageSize=100;
				if(userType.equalsIgnoreCase("superadmin")||userType.equalsIgnoreCase("ActivityAdmin")){
					AgentActivationDao daoObj=new AgentActivationDao();
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
				String searchUserType=(String)request.getParameter("userType");
				//				System.out.println("searchUserType>>>>>>>>>>>>>>>>>>>>>>>"+searchUserType);
				String advanceSearchOption="N";
				String portalId=(String)request.getParameter("portalId");
				AgentActivationDao daoObj=new AgentActivationDao();
				if(portalId==null)
				{
					portalId=userId;
				}
				String userType=(String)session.get("loginType");
				String	portalType="";
				if(userType.equalsIgnoreCase("superadmin")||userType.equalsIgnoreCase("activityAdmin") ||userType.equalsIgnoreCase("activityUser")){  
					if(portalId.equalsIgnoreCase("all"))
					{
						portalType=(String)request.getParameter("portalType");
						session.put("portalType",portalType);
						if(portalType.equalsIgnoreCase("TEP"))
						{
							clientFlag="0";
						}
						if(portalType.equalsIgnoreCase("REP"))
						{
							clientFlag="1";
						}
						if(portalType.equalsIgnoreCase("Combo"))
						{
							clientFlag="2";
						}
					}
					else{
						//						System.out.println("portalId>>>>>>>>>>>>>>>>>>>>>>>>>>"+portalId);
						String portalFlag=daoObj.getPortalFlag(portalId);
						//						System.out.println("portalFlag>>>>>>>>>>>>>>>>>>>>>>>>>>"+portalFlag);
						clientFlag=portalFlag;
						//						System.out.println("clientFlag>>>>>>>>>>>>>>>>>>>>>>>>>>"+clientFlag);
						if(portalFlag.equalsIgnoreCase("0"))
						{
							portalType="TEP";
						}
						if(portalFlag.equalsIgnoreCase("1"))
						{
							portalType="REP";
						}
						if(portalFlag.equalsIgnoreCase("2"))
						{
							portalType="Combo";
						}
						session.put("portalType",portalType);
					}
				}

				if(advanceSearchOption.equalsIgnoreCase("N"))
				{
					if(searchUserType.equalsIgnoreCase("agent"))
					{
						String  agentId=(String)request.getParameter("userId");
						//						System.out.println("agentId>>>>>>>>>>>>>>>>>>>>>>>"+agentId);
						if(agentId==null || agentId=="")
						{
							session.remove("agentActivationUserId");
							String status=(String)request.getParameter("status");
							session.put("buttonStatus",status);
							if(portalId.equalsIgnoreCase("all") && status.equals("all"))
							{
								int pageNumber=Integer.parseInt((String)request.getParameter("pageNumber"));
								if(pageNumber==1)
								{
									int noOfRecords=daoObj.getNoOfRecordsAllAgentDetails(clientFlag);
									if(noOfRecords==0)
									{
										request.setAttribute("message","Please Check Input Value.");
										return "searchUser";
									}
									session.put("noOfRecords",noOfRecords);
								}
								//								System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
								ArrayList agentDetails=daoObj.getAllAgentsDetail(pageNumber,clientFlag);
								session.put("userType",searchUserType);
								session.put("pageNumber",pageNumber);
								session.put("status",status);
								session.put("portalId",portalId);
								session.put("advanceSearchOption",advanceSearchOption);
								session.put("agentSearchList",agentDetails);
								return "agentList";
							}
							if(portalId.equalsIgnoreCase("all") && !status.equals("all"))
							{
								int pageNumber=Integer.parseInt((String)request.getParameter("pageNumber"));
								if(status.equalsIgnoreCase("blocked") || status.equalsIgnoreCase("unblocked")){
									if(pageNumber==1)
									{
										int noOfRecords=daoObj.getNoOfRecordsAllAgentDetailsBlockStatusWise(status,clientFlag);
										if(noOfRecords==0)
										{
											request.setAttribute("message","Please Check Input Value.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
									//									System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
									ArrayList agentDetails=daoObj.getAgentDetailsBlockStatusWise(status,pageNumber,clientFlag);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("portalId",portalId);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("agentSearchList",agentDetails);
									return "agentList";
								}
								else{
									if(pageNumber==1)
									{
										int noOfRecords=daoObj.getNoOfRecordsAllAgentDetailsStatusWise(status,clientFlag);
										if(noOfRecords==0){
											request.setAttribute("message","Please Check Input Value.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
									//									System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
									ArrayList agentDetails=daoObj.getAgentDetailsStatusWise(status,pageNumber,clientFlag);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("portalId",portalId);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("agentSearchList",agentDetails);
									return "agentList";
								}
							}
							if(!portalId.equalsIgnoreCase("all") && status.equals("all")){
								int pageNumber=Integer.parseInt((String)request.getParameter("pageNumber"));
								if(pageNumber==1)
								{
									int noOfRecords=daoObj.getNoOfRecordsAllAgentDetailsPortalIdWise(portalId,clientFlag);
									if(noOfRecords==0)
									{
										request.setAttribute("message","Please Check Input Value.");
										return "searchUser";
									}
									session.put("noOfRecords",noOfRecords);
								}
								//								System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
								ArrayList agentDetails=daoObj.getAgentDetailsPortalWise(portalId,pageNumber,clientFlag);
								session.put("userType",searchUserType);
								session.put("pageNumber",pageNumber);
								session.put("status",status);
								session.put("portalId",portalId);
								session.put("advanceSearchOption",advanceSearchOption);
								session.put("agentSearchList",agentDetails);
								return "agentList";
							} 
							if(!portalId.equalsIgnoreCase("all") && !status.equals("all"))
							{
								int pageNumber=Integer.parseInt((String)request.getParameter("pageNumber"));
								if(status.equalsIgnoreCase("blocked") || status.equalsIgnoreCase("unblocked")){
									if(pageNumber==1)
									{
										int noOfRecords=daoObj.getNoOfRecordsAllAgentDetailsPortalIdBlockStatusWise(portalId,status,clientFlag);
										if(noOfRecords==0)
										{
											request.setAttribute("message","Please Check Input Value.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
									//									System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
									ArrayList agentDetails=daoObj.getAgentDetailsOfPortalIdBlockStatusWise(portalId,status,pageNumber,clientFlag);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("portalId",portalId);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("agentSearchList",agentDetails);
									return "agentList";
								}
								else{
									if(pageNumber==1)
									{
										int noOfRecords=daoObj.getNoOfRecordsAllAgentDetailsPortalIdStatusWise(portalId,status,clientFlag);
										if(noOfRecords==0)
										{
											request.setAttribute("message","Please Check Input Value.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
									//									System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
									ArrayList agentDetails=daoObj.getAgentDetailsOfPortalIdStatusWise(portalId,status,pageNumber,clientFlag);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("portalId",portalId);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("agentSearchList",agentDetails);
									return "agentList";
								}
							}
						}
						else{
							int pageNumber=Integer.parseInt((String)request.getParameter("pageNumber"));
							if(pageNumber==1)
							{
								int noOfRecords=daoObj.getNoOfRecordsAllAgentDetailsAgentIdWise(agentId,portalId,clientFlag);
								if(noOfRecords==0)
								{
									request.setAttribute("message","Please Check Input Value.");
									return "searchUser";
								}
								session.put("noOfRecords",noOfRecords);
							}
							ArrayList agentDetails=daoObj.getAgentDetailsAgentIdWise(agentId,pageNumber,portalId,clientFlag);
							session.put("userType",searchUserType);
							session.put("pageNumber",pageNumber);
							session.put("agentActivationUserId",agentId);	
							session.put("portalId",portalId);
							session.put("advanceSearchOption",advanceSearchOption);
							session.put("buttonStatus","all");
							session.put("agentSearchList",agentDetails);
							return "agentList";
						}
					}
				}

			}	
			else if("exportAgent".equalsIgnoreCase(param)) {
				
				String loginType=(String)session.get("loginType");
				String portalId="";
				if(!loginType.equalsIgnoreCase("SuperAdmin"))
				{
					portalId=(String)session.get("adminUserPortalId");
				}
				
				AgentActivationDao daoObj=new AgentActivationDao();
				List<AgentDetailsFormBean> list=daoObj.downloadAgent(loginType, portalId);
				
				ArrayList<String> rows = new ArrayList<String>();
				
				if(list!=null && list.size()>0)
				{
					
					response.setContentType("application/vnd.ms-excel");
					String reportName = "Agent_details.xls";
					response.setHeader("Content-disposition", "attachment; filename=" + reportName);

					rows.add("agentId");
					rows.add("\t");
					rows.add("distId");
					rows.add("\t");
					rows.add("firstName");
					rows.add("\t");
					rows.add("lastName");
					rows.add("\t");
					rows.add("dateOfJoining");
					rows.add("\t");
					rows.add("companyName");
					rows.add("\t");
					rows.add("companyType");
					rows.add("\t");
					rows.add("addressLine1");
					rows.add("\t");
					rows.add("addressLine2");
					rows.add("\t");
					rows.add("pincode");
					rows.add("\t");
					rows.add("city");
					rows.add("\t");
					rows.add("district");
					rows.add("\t");
					rows.add("state");
					rows.add("\t");
					rows.add("country");
					rows.add("\t");
					rows.add("emailId");
					rows.add("\t");
					rows.add("mobileNo");
					rows.add("\t");
					rows.add("dob");
					rows.add("\t");
					rows.add("gender");
					rows.add("\t");
					rows.add("panNo");
					rows.add("\t");
					rows.add("aadhaarNo");
					rows.add("\n"); 

					for(AgentDetailsFormBean bean:list)
					{
						rows.add(bean.getAgentInitial()+bean.getAgentId());
						rows.add("\t");
						rows.add(bean.getDistId()+"");
						rows.add("\t");
						rows.add(bean.getFirstName());
						rows.add("\t");
						rows.add(bean.getLastName());
						rows.add("\t");
						rows.add(bean.getDateOfJoining());
						rows.add("\t");
						rows.add(bean.getCompanyName());
						rows.add("\t");
						rows.add(bean.getCompanyType());
						rows.add("\t");
						rows.add(bean.getAddressLine1());
						rows.add("\t");
						rows.add(bean.getAddressLine2());
						rows.add("\t");
						rows.add(bean.getPincode());
						rows.add("\t");
						rows.add(bean.getCity());
						rows.add("\t");
						rows.add(bean.getDistrict());
						rows.add("\t");
						rows.add(bean.getState());
						rows.add("\t");
						rows.add(bean.getCountry());
						rows.add("\t");
						rows.add(bean.getEmailId());
						rows.add("\t");
						rows.add(bean.getMobileNo());
						rows.add("\t");
						rows.add(bean.getDob());
						rows.add("\t");
						rows.add(bean.getGender());
						rows.add("\t");
						rows.add(bean.getPanNo());
						rows.add("\t");
						rows.add(bean.getAadhaarNo());
						rows.add("\n");
					}
					Iterator<String> iter = rows.iterator();
					while (iter.hasNext()) {
						String outputString = (String) iter.next();
						response.getOutputStream().print(outputString);
					}
					response.getOutputStream().flush();
					response.getOutputStream().close();
					
					return null;
				}
				else
				{
					request.setAttribute("message","Data not found");
					return "searchUser";
				}

				
					
			}
			else if(param.equalsIgnoreCase("activateAgent"))
			{
				String[] agentIds=request.getParameterValues("agentIds");

				AgentActivationDao	daoObj=new AgentActivationDao();
				daoObj.activateAgents(agentIds,session);
				ArrayList activatedAgentsList=(ArrayList)session.get("activatedAgentsList");
				ArrayList deactivatedAgentList=(ArrayList)session.get("deactivatedAgentList");
				String message="";
				if(activatedAgentsList.size()>0){
					message="Selected User/s has been Activated.";
				}
				else{
					message="Process aborted due to Technical Failure.";
				}

				request.setAttribute("message",message);

				String searchUserType=(String)session.get("userType");
				//				System.out.println("searchUserType>>>>>>>>>>>>>>>>>>>>>>>"+searchUserType);
				String advanceSearchOption="N";
				//				System.out.println("advanceSearchOption>>>>>>>>>>>>>>>>>>>>>>>"+advanceSearchOption);
				String portalId=(String)session.get("portalId");
				//				System.out.println("portalId>>>>>>>>>>>>>>>>>>>>>>>"+portalId);
				String userType=(String)session.get("loginType");
				if(userType.equalsIgnoreCase("superadmin")||userType.equalsIgnoreCase("activityAdmin")){  
					if(portalId.equalsIgnoreCase("all")){
						String	portalType=(String)session.get("portalType");
						if(portalType.equalsIgnoreCase("TEP"))
						{
							clientFlag="0";
						}
						if(portalType.equalsIgnoreCase("REP"))
						{
							clientFlag="1";
						}
						if(portalType.equalsIgnoreCase("both"))
						{
							clientFlag="2";
						}
					}
					else{
						String portalFlag=daoObj.getPortalFlag(portalId);
						clientFlag=portalFlag;
					}
				}
				if(advanceSearchOption.equalsIgnoreCase("N"))
				{
					if(searchUserType.equalsIgnoreCase("agent"))
					{
						String  agentId=(String)session.get("agentActivationUserId");
						//						System.out.println("agentId>>>>>>>>>>>>>>>>>>>>>>>"+agentId);
						if(agentId==null || agentId=="")
						{
							session.remove("agentActivationUserId");
							String status=(String)session.get("status");
							session.put("buttonStatus",status);
							if(portalId.equalsIgnoreCase("all") && status.equals("all")){
								int pageNumber=(Integer)session.get("pageNumber");
								if(pageNumber==1)
								{
									int noOfRecords=daoObj.getNoOfRecordsAllAgentDetails(clientFlag);
									if(noOfRecords==0)
									{
										request.setAttribute("message","Please Check Input Value.");
										return "searchUser";
									}
									session.put("noOfRecords",noOfRecords);
								}
								//								System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
								ArrayList agentDetails=daoObj.getAllAgentsDetail(pageNumber,clientFlag);
								session.put("userType",searchUserType);
								session.put("pageNumber",pageNumber);
								session.put("status",status);
								session.put("portalId",portalId);
								session.put("advanceSearchOption",advanceSearchOption);
								session.put("agentSearchList",agentDetails);
								return "agentList";
							}
							if(portalId.equalsIgnoreCase("all") && !status.equals("all"))
							{
								int pageNumber=(Integer)session.get("pageNumber");
								if(status.equalsIgnoreCase("blocked") || status.equalsIgnoreCase("unblocked")){
									if(pageNumber==1)
									{
										int noOfRecords=daoObj.getNoOfRecordsAllAgentDetailsBlockStatusWise(status,clientFlag);
										if(noOfRecords==0)
										{
											request.setAttribute("message","Please Check Input Value.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
									//									System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
									ArrayList agentDetails=daoObj.getAgentDetailsBlockStatusWise(status,pageNumber,clientFlag);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("portalId",portalId);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("agentSearchList",agentDetails);
									return "agentList";
								}
								else{
									if(pageNumber==1)
									{
										int noOfRecords=daoObj.getNoOfRecordsAllAgentDetailsStatusWise(status,clientFlag);
										if(noOfRecords==0)
										{
											request.setAttribute("message","Please Check Input Value.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
									//									System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
									ArrayList agentDetails=daoObj.getAgentDetailsStatusWise(status,pageNumber,clientFlag);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("portalId",portalId);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("agentSearchList",agentDetails);
									return "agentList";
								}
							}
							if(!portalId.equalsIgnoreCase("all") && status.equals("all")){
								int pageNumber=(Integer)session.get("pageNumber");
								if(pageNumber==1)
								{
									int noOfRecords=daoObj.getNoOfRecordsAllAgentDetailsPortalIdWise(portalId,clientFlag);
									if(noOfRecords==0)
									{
										request.setAttribute("message","Please Check Input Value.");
										return "searchUser";
									}
									session.put("noOfRecords",noOfRecords);
								}
								//								System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
								ArrayList agentDetails=daoObj.getAgentDetailsPortalWise(portalId,pageNumber,clientFlag);
								session.put("userType",searchUserType);
								session.put("pageNumber",pageNumber);
								session.put("status",status);
								session.put("portalId",portalId);
								session.put("advanceSearchOption",advanceSearchOption);
								session.put("agentSearchList",agentDetails);
								return "agentList";
							} 
							if(!portalId.equalsIgnoreCase("all") && !status.equals("all"))
							{
								int pageNumber=(Integer)session.get("pageNumber");
								if(status.equalsIgnoreCase("blocked") || status.equalsIgnoreCase("unblocked")){
									if(pageNumber==1)
									{
										int noOfRecords=daoObj.getNoOfRecordsAllAgentDetailsPortalIdBlockStatusWise(portalId,status,clientFlag);
										if(noOfRecords==0)
										{
											request.setAttribute("message","Please Check Input Value.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
									//									System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
									ArrayList agentDetails=daoObj.getAgentDetailsOfPortalIdBlockStatusWise(portalId,status,pageNumber,clientFlag);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("portalId",portalId);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("agentSearchList",agentDetails);
									return "agentList";
								}
								else{
									if(pageNumber==1)
									{
										int noOfRecords=daoObj.getNoOfRecordsAllAgentDetailsPortalIdStatusWise(portalId,status,clientFlag);
										if(noOfRecords==0)
										{
											request.setAttribute("message","Please Check Input Value.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
									//									System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
									ArrayList agentDetails=daoObj.getAgentDetailsOfPortalIdStatusWise(portalId,status,pageNumber,clientFlag);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("portalId",portalId);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("agentSearchList",agentDetails);
									return "agentList";
								}
							}
						}
						else{
							int pageNumber=(Integer)session.get("pageNumber");
							if(pageNumber==1)
							{
								int noOfRecords=daoObj.getNoOfRecordsAllAgentDetailsAgentIdWise(agentId,portalId,clientFlag);
								if(noOfRecords==0)
								{
									request.setAttribute("message","Please Check Input Value.");
									return "searchUser";
								}
								session.put("noOfRecords",noOfRecords);
							}
							ArrayList agentDetails=daoObj.getAgentDetailsAgentIdWise(agentId,pageNumber,portalId,clientFlag);
							session.put("userType",searchUserType);
							session.put("agentActivationUserId",agentId);
							session.put("pageNumber",pageNumber);
							session.put("buttonStatus","all");
							session.put("portalId",portalId);
							session.put("advanceSearchOption",advanceSearchOption);
							session.put("agentSearchList",agentDetails);
							return "agentList";
						}
					}
				}

				return "agentList";
			}	
			else if(param.equalsIgnoreCase("deactivateAgent"))
			{
				String[] agentIds=request.getParameterValues("agentIds");
				AgentActivationDao daoObj=new AgentActivationDao();
				daoObj.deactivateAgents(agentIds,clientFlag,session);
				ArrayList activatedAgentsList=(ArrayList)session.get("activatedAgentsList");
				ArrayList deactivatedAgentList=(ArrayList)session.get("deactivatedAgentList");
				String message="";

				if(deactivatedAgentList.size()>0)
				{
					message="Selected User/s has been Deactivated.";
				}
				else{
					message="Process aborted due to Technical Failure.";
				}

				request.setAttribute("message",message);

				String searchUserType=(String)session.get("userType");
				//				System.out.println("searchUserType>>>>>>>>>>>>>>>>>>>>>>>"+searchUserType);
				String advanceSearchOption="N";
				//				System.out.println("advanceSearchOption>>>>>>>>>>>>>>>>>>>>>>>"+advanceSearchOption);
				String portalId=(String)session.get("portalId");
				String userType=(String)session.get("adminUserType");
				if(userType.equalsIgnoreCase("superadmin"))
				{  
					if(portalId.equalsIgnoreCase("all"))
					{
						String	portalType=(String)session.get("portalType");
						if(portalType.equalsIgnoreCase("TEP"))
						{
							clientFlag="0";
						}
						if(portalType.equalsIgnoreCase("REP"))
						{
							clientFlag="1";
						}
						if(portalType.equalsIgnoreCase("both"))
						{
							clientFlag="2";
						}
					}
					else{
						String portalFlag=daoObj.getPortalFlag(portalId);
						clientFlag=portalFlag;
					}
				}
				if(advanceSearchOption.equalsIgnoreCase("N"))
				{
					if(searchUserType.equalsIgnoreCase("agent"))
					{
						String  agentId=(String)session.get("agentActivationUserId");
						//						System.out.println("agentId>>>>>>>>>>>>>>>>>>>>>>>"+agentId);
						if(agentId==null || agentId=="")
						{
							session.remove("agentActivationUserId");
							//							System.out.println("portalId>>>>>>>>>>>>>>>>>>>>>>>"+portalId);
							String status=(String)session.get("status");
							//							System.out.println("status>>>>>>>>>>>>>>>>>>>>>>>"+status);
							session.put("buttonStatus",status);
							if(portalId.equalsIgnoreCase("all") && status.equals("all"))
							{
								int pageNumber=(Integer)session.get("pageNumber");
								if(pageNumber==1)
								{
									int noOfRecords=daoObj.getNoOfRecordsAllAgentDetails(clientFlag);
									if(noOfRecords==0)
									{
										request.setAttribute("message","Please Check Input Value.");
										return "searchUser";
									}
									session.put("noOfRecords",noOfRecords);
								}
								//								System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
								ArrayList agentDetails=daoObj.getAllAgentsDetail(pageNumber,clientFlag);
								session.put("userType",searchUserType);
								session.put("pageNumber",pageNumber);
								session.put("status",status);
								session.put("portalId",portalId);
								session.put("advanceSearchOption",advanceSearchOption);
								session.put("agentSearchList",agentDetails);
								return "agentList";
							}
							if(portalId.equalsIgnoreCase("all") && !status.equals("all"))
							{
								int pageNumber=(Integer)session.get("pageNumber");
								if(status.equalsIgnoreCase("blocked") || status.equalsIgnoreCase("unblocked")){
									if(pageNumber==1)
									{
										int noOfRecords=daoObj.getNoOfRecordsAllAgentDetailsBlockStatusWise(status,clientFlag);
										if(noOfRecords==0)
										{
											request.setAttribute("message","Please Check Input Value.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
									//									System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
									ArrayList agentDetails=daoObj.getAgentDetailsBlockStatusWise(status,pageNumber,clientFlag);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("portalId",portalId);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("agentSearchList",agentDetails);
									return "agentList";
								}
								else{
									//									System.out.println("inside else>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+pageNumber);
									if(pageNumber==1)
									{
										int noOfRecords=daoObj.getNoOfRecordsAllAgentDetailsStatusWise(status,clientFlag);
										if(noOfRecords==0)
										{
											request.setAttribute("message","Please Check Input Value.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
									//									System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
									ArrayList agentDetails=daoObj.getAgentDetailsStatusWise(status,pageNumber,clientFlag);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("portalId",portalId);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("agentSearchList",agentDetails);
									return "agentList";
								}
							}
							if(!portalId.equalsIgnoreCase("all") && status.equals("all"))
							{
								int pageNumber=(Integer)session.get("pageNumber");
								if(pageNumber==1)
								{
									int noOfRecords=daoObj.getNoOfRecordsAllAgentDetailsPortalIdWise(portalId,clientFlag);
									if(noOfRecords==0)
									{
										request.setAttribute("message","Please Check Input Value.");
										return "searchUser";
									}
									session.put("noOfRecords",noOfRecords);
								}
								//								System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
								ArrayList agentDetails=daoObj.getAgentDetailsPortalWise(portalId,pageNumber,clientFlag);
								session.put("userType",searchUserType);
								session.put("pageNumber",pageNumber);
								session.put("status",status);
								session.put("portalId",portalId);
								session.put("advanceSearchOption",advanceSearchOption);
								session.put("agentSearchList",agentDetails);
								return "agentList";
							} 
							if(!portalId.equalsIgnoreCase("all") && !status.equals("all"))
							{
								int pageNumber=(Integer)session.get("pageNumber");
								if(status.equalsIgnoreCase("blocked") || status.equalsIgnoreCase("unblocked")){
									if(pageNumber==1)
									{
										int noOfRecords=daoObj.getNoOfRecordsAllAgentDetailsPortalIdBlockStatusWise(portalId,status,clientFlag);
										if(noOfRecords==0)
										{
											request.setAttribute("message","Please Check Input Value.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
									//									System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
									ArrayList agentDetails=daoObj.getAgentDetailsOfPortalIdBlockStatusWise(portalId,status,pageNumber,clientFlag);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("portalId",portalId);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("agentSearchList",agentDetails);
									return "agentList";
								}
								else{
									if(pageNumber==1)
									{
										int noOfRecords=daoObj.getNoOfRecordsAllAgentDetailsPortalIdStatusWise(portalId,status,clientFlag);
										if(noOfRecords==0)
										{
											request.setAttribute("message","Please Check Input Value.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
									//										System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
									ArrayList agentDetails=daoObj.getAgentDetailsOfPortalIdStatusWise(portalId,status,pageNumber,clientFlag);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("portalId",portalId);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("agentSearchList",agentDetails);
									return "agentList";
								}
							}
						}
						else{
							int pageNumber=(Integer)session.get("pageNumber");
							if(pageNumber==1)
							{
								int noOfRecords=daoObj.getNoOfRecordsAllAgentDetailsAgentIdWise(agentId,portalId,clientFlag);
								if(noOfRecords==0)
								{
									request.setAttribute("message","Please Check Input Value.");
									return "searchUser";
								}
								session.put("noOfRecords",noOfRecords);
							}
							ArrayList agentDetails=daoObj.getAgentDetailsAgentIdWise(agentId,pageNumber,portalId,clientFlag);
							session.put("userType",searchUserType);
							session.put("pageNumber",pageNumber);
							session.put("agentActivationUserId",agentId);	
							session.put("buttonStatus","all");
							session.put("portalId",portalId);							 
							session.put("advanceSearchOption",advanceSearchOption);
							session.put("agentSearchList",agentDetails);
							return "agentList";
						}
					}
				}

				return "agentList";
			}	
			else if(param.equalsIgnoreCase("blockAgent"))
			{
				String validUpto=request.getParameter("dateOfActivation");
				String[] agentIds=request.getParameterValues("agentIds");
				AgentActivationDao daoObj=new AgentActivationDao();
				daoObj.blockAgents(agentIds,clientFlag,session);
				ArrayList blockedAgentsList=(ArrayList)session.get("blockedAgentsList");
				ArrayList unblockedAgentList=(ArrayList)session.get("unblockedAgentList");
				String message="";

				if(blockedAgentsList.size()>0)
				{
					message="Selected User/s has been Blocked.";
				}
				else{
					message="Process aborted due to Technical Failure.";
				}

				request.setAttribute("message",message);

				String searchUserType=(String)session.get("userType");
				//				System.out.println("searchUserType>>>>>>>>>>>>>>>>>>>>>>>"+searchUserType);
				String advanceSearchOption="N";
				//				System.out.println("advanceSearchOption>>>>>>>>>>>>>>>>>>>>>>>"+advanceSearchOption);
				String portalId=(String)session.get("portalId");
				String userType=(String)session.get("adminUserType");
				if(userType.equalsIgnoreCase("superadmin"))
				{  
					if(portalId.equalsIgnoreCase("all"))
					{
						String	portalType=(String)session.get("portalType");
						if(portalType.equalsIgnoreCase("TEP"))
						{
							clientFlag="0";
						}
						if(portalType.equalsIgnoreCase("REP"))
						{
							clientFlag="1";
						}
						if(portalType.equalsIgnoreCase("both"))
						{
							clientFlag="2";
						}
					}
					else{
						String portalFlag=daoObj.getPortalFlag(portalId);
						clientFlag=portalFlag;
					}
				}
				if(advanceSearchOption.equalsIgnoreCase("N"))
				{
					if(searchUserType.equalsIgnoreCase("agent"))
					{
						String  agentId=(String)session.get("agentActivationUserId");
						//						System.out.println("agentId>>>>>>>>>>>>>>>>>>>>>>>"+agentId);
						if(agentId==null || agentId=="")
						{
							session.remove("agentActivationUserId");
							String status=(String)session.get("status");
							session.put("buttonStatus",status);
							if(portalId.equalsIgnoreCase("all") && status.equals("all"))
							{
								int pageNumber=(Integer)session.get("pageNumber");
								if(pageNumber==1)
								{
									int noOfRecords=daoObj.getNoOfRecordsAllAgentDetails(clientFlag);
									if(noOfRecords==0)
									{
										request.setAttribute("message","Please Check Input Value.");
										return "searchUser";
									}
									session.put("noOfRecords",noOfRecords);
								}
								//								System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
								ArrayList agentDetails=daoObj.getAllAgentsDetail(pageNumber,clientFlag);
								session.put("userType",searchUserType);
								session.put("pageNumber",pageNumber);
								session.put("status",status);
								session.put("portalId",portalId);
								session.put("advanceSearchOption",advanceSearchOption);
								session.put("agentSearchList",agentDetails);
								return "agentList";
							}
							if(portalId.equalsIgnoreCase("all") && !status.equals("all"))
							{
								int pageNumber=(Integer)session.get("pageNumber");
								if(status.equalsIgnoreCase("blocked") || status.equalsIgnoreCase("unblocked"))
								{
									if(pageNumber==1)
									{
										int noOfRecords=daoObj.getNoOfRecordsAllAgentDetailsBlockStatusWise(status,clientFlag);
										if(noOfRecords==0)
										{
											request.setAttribute("message","Please Check Input Value.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
									//									System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
									ArrayList agentDetails=daoObj.getAgentDetailsBlockStatusWise(status,pageNumber,clientFlag);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("portalId",portalId);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("agentSearchList",agentDetails);
									return "agentList";
								}
								else{
									if(pageNumber==1)
									{
										int noOfRecords=daoObj.getNoOfRecordsAllAgentDetailsStatusWise(status,clientFlag);
										if(noOfRecords==0){
											request.setAttribute("message","Please Check Input Value.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
									//									System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
									ArrayList agentDetails=daoObj.getAgentDetailsStatusWise(status,pageNumber,clientFlag);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("portalId",portalId);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("agentSearchList",agentDetails);
									return "agentList";
								}
							}
							if(!portalId.equalsIgnoreCase("all") && status.equals("all"))
							{
								int pageNumber=(Integer)session.get("pageNumber");
								if(pageNumber==1)
								{
									int noOfRecords=daoObj.getNoOfRecordsAllAgentDetailsPortalIdWise(portalId,clientFlag);
									if(noOfRecords==0)
									{
										request.setAttribute("message","Please Check Input Value.");
										return "searchUser";
									}
									session.put("noOfRecords",noOfRecords);
								}
								//								System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
								ArrayList agentDetails=daoObj.getAgentDetailsPortalWise(portalId,pageNumber,clientFlag);
								session.put("userType",searchUserType);
								session.put("pageNumber",pageNumber);
								session.put("status",status);
								session.put("portalId",portalId);
								session.put("advanceSearchOption",advanceSearchOption);
								session.put("agentSearchList",agentDetails);
								return "agentList";
							} 
							if(!portalId.equalsIgnoreCase("all") && !status.equals("all"))
							{
								int pageNumber=(Integer)session.get("pageNumber");
								if(status.equalsIgnoreCase("blocked") || status.equalsIgnoreCase("unblocked")){
									if(pageNumber==1)
									{
										int noOfRecords=daoObj.getNoOfRecordsAllAgentDetailsPortalIdBlockStatusWise(portalId,status,clientFlag);
										if(noOfRecords==0)
										{
											request.setAttribute("message","Please Check Input Value.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
									//									System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
									ArrayList agentDetails=daoObj.getAgentDetailsOfPortalIdBlockStatusWise(portalId,status,pageNumber,clientFlag);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("portalId",portalId);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("agentSearchList",agentDetails);
									return "agentList";
								}
								else{
									if(pageNumber==1){
										int noOfRecords=daoObj.getNoOfRecordsAllAgentDetailsPortalIdStatusWise(portalId,status,clientFlag);
										if(noOfRecords==0){
											request.setAttribute("message","Please Check Input Value.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
									//									System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
									ArrayList agentDetails=daoObj.getAgentDetailsOfPortalIdStatusWise(portalId,status,pageNumber,clientFlag);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("portalId",portalId);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("agentSearchList",agentDetails);
									return "agentList";
								}
							}
						}
						else{
							int pageNumber=(Integer)session.get("pageNumber");
							if(pageNumber==1){
								int noOfRecords=daoObj.getNoOfRecordsAllAgentDetailsAgentIdWise(agentId,portalId,clientFlag);
								if(noOfRecords==0){
									request.setAttribute("message","Please Check Input Value.");
									return "searchUser";
								}
								session.put("noOfRecords",noOfRecords);
							}
							ArrayList agentDetails=daoObj.getAgentDetailsAgentIdWise(agentId,pageNumber,portalId,clientFlag);
							session.put("userType",searchUserType);
							session.put("pageNumber",pageNumber);
							session.put("agentActivationUserId",agentId);
							session.put("buttonStatus","all");
							session.put("portalId",portalId);
							session.put("advanceSearchOption",advanceSearchOption);
							session.put("agentSearchList",agentDetails);
							return "agentList";
						}
					}
				}
				return "agentList";
			}	
			else if(param.equalsIgnoreCase("unblockAgent"))
			{
				String validUpto=request.getParameter("dateOfActivation");
				String[] agentIds=request.getParameterValues("agentIds");

				AgentActivationDao daoObj=new AgentActivationDao();
				daoObj.unblockAgents(agentIds,clientFlag,session);
				ArrayList unblockedAgentList=(ArrayList)session.get("unblockedAgentList");
				ArrayList blockedAgentsList=(ArrayList)session.get("blockedAgentsList");
				String message="";

				if(unblockedAgentList.size()>0)
				{
					message="Selected User/s has been Unblocked.";
				}
				else{
					message="Process aborted due to Technical Failure.";
				}

				request.setAttribute("message",message);
				String searchUserType=(String)session.get("userType");
				//				System.out.println("searchUserType>>>>>>>>>>>>>>>>>>>>>>>"+searchUserType);
				String advanceSearchOption="N";
				//				System.out.println("advanceSearchOption>>>>>>>>>>>>>>>>>>>>>>>"+advanceSearchOption);
				String portalId=(String)session.get("portalId");
				String userType=(String)session.get("adminUserType");
				if(userType.equalsIgnoreCase("superadmin")){  
					if(portalId.equalsIgnoreCase("all")){
						String	portalType=(String)session.get("portalType");
						if(portalType.equalsIgnoreCase("TEP"))
						{
							clientFlag="0";
						}
						if(portalType.equalsIgnoreCase("REP"))
						{
							clientFlag="1";
						}
						if(portalType.equalsIgnoreCase("both"))
						{
							clientFlag="2";
						}
					}
					else{
						String portalFlag=daoObj.getPortalFlag(portalId);
						clientFlag=portalFlag;
					}
				}	
				if(advanceSearchOption.equalsIgnoreCase("N"))
				{
					if(searchUserType.equalsIgnoreCase("agent"))
					{
						String  agentId=(String)session.get("agentActivationUserId");
						//					 			System.out.println("agentId>>>>>>>>>>>>>>>>>>>>>>>"+agentId);
						if(agentId==null || agentId=="")
						{
							session.remove("agentActivationUserId");   
							String status=(String)session.get("status");
							session.put("buttonStatus",status);
							if(portalId.equalsIgnoreCase("all") && status.equals("all")){
								int pageNumber=(Integer)session.get("pageNumber");
								if(pageNumber==1)
								{
									int noOfRecords=daoObj.getNoOfRecordsAllAgentDetails(clientFlag);
									if(noOfRecords==0)
									{
										request.setAttribute("message","Please Check Input Value.");
										return "searchUser";
									}
									session.put("noOfRecords",noOfRecords);
								}
								//					 					System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
								ArrayList agentDetails=daoObj.getAllAgentsDetail(pageNumber,clientFlag);
								session.put("userType",searchUserType);
								session.put("pageNumber",pageNumber);
								session.put("status",status);
								session.put("portalId",portalId);
								session.put("advanceSearchOption",advanceSearchOption);
								session.put("agentSearchList",agentDetails);
								return "agentList";
							}
							if(portalId.equalsIgnoreCase("all") && !status.equals("all")){
								int pageNumber=(Integer)session.get("pageNumber");
								if(status.equalsIgnoreCase("blocked") || status.equalsIgnoreCase("unblocked"))
								{
									if(pageNumber==1){
										int noOfRecords=daoObj.getNoOfRecordsAllAgentDetailsBlockStatusWise(status,clientFlag);
										if(noOfRecords==0)
										{
											request.setAttribute("message","Please Check Input Value.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
									//					 						System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
									ArrayList agentDetails=daoObj.getAgentDetailsBlockStatusWise(status,pageNumber,clientFlag);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("portalId",portalId);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("agentSearchList",agentDetails);
									return "agentList";
								}
								else{
									if(pageNumber==1){
										int noOfRecords=daoObj.getNoOfRecordsAllAgentDetailsStatusWise(status,clientFlag);
										if(noOfRecords==0)
										{
											request.setAttribute("message","Please Check Input Value.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
									//					 						System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
									ArrayList agentDetails=daoObj.getAgentDetailsStatusWise(status,pageNumber,clientFlag);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("portalId",portalId);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("agentSearchList",agentDetails);
									return "agentList";
								}
							}
							if(!portalId.equalsIgnoreCase("all") && status.equals("all"))
							{
								int pageNumber=(Integer)session.get("pageNumber");
								if(pageNumber==1)
								{
									int noOfRecords=daoObj.getNoOfRecordsAllAgentDetailsPortalIdWise(portalId,clientFlag);
									if(noOfRecords==0)
									{
										request.setAttribute("message","Please Check Input Value.");
										return "searchUser";
									}
									session.put("noOfRecords",noOfRecords);
								}
								//					 					System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
								ArrayList agentDetails=daoObj.getAgentDetailsPortalWise(portalId,pageNumber,clientFlag);
								session.put("userType",searchUserType);
								session.put("pageNumber",pageNumber);
								session.put("status",status);
								session.put("portalId",portalId);
								session.put("advanceSearchOption",advanceSearchOption);
								session.put("agentSearchList",agentDetails);
								return "agentList";
							} 
							if(!portalId.equalsIgnoreCase("all") && !status.equals("all")){
								int pageNumber=(Integer)session.get("pageNumber");
								if(status.equalsIgnoreCase("blocked") || status.equalsIgnoreCase("unblocked"))
								{
									if(pageNumber==1){
										int noOfRecords=daoObj.getNoOfRecordsAllAgentDetailsPortalIdBlockStatusWise(portalId,status,clientFlag);
										if(noOfRecords==0)
										{
											request.setAttribute("message","Please Check Input Value.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
									//					 						System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
									ArrayList agentDetails=daoObj.getAgentDetailsOfPortalIdBlockStatusWise(portalId,status,pageNumber,clientFlag);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("portalId",portalId);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("agentSearchList",agentDetails);
									return "agentList";
								}
								else{
									if(pageNumber==1){
										int noOfRecords=daoObj.getNoOfRecordsAllAgentDetailsPortalIdStatusWise(portalId,status,clientFlag);
										if(noOfRecords==0){
											request.setAttribute("message","Please Check Input Value.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
									//					 						System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
									ArrayList agentDetails=daoObj.getAgentDetailsOfPortalIdStatusWise(portalId,status,pageNumber,clientFlag);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("portalId",portalId);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("agentSearchList",agentDetails);
									return "agentList";
								}
							}
						}
						else{
							int pageNumber=(Integer)session.get("pageNumber");
							if(pageNumber==1){
								int noOfRecords=daoObj.getNoOfRecordsAllAgentDetailsAgentIdWise(agentId,portalId,clientFlag);
								if(noOfRecords==0){
									request.setAttribute("message","Please Check Input Value.");
									return "searchUser";
								}
								session.put("noOfRecords",noOfRecords);
							}
							ArrayList agentDetails=daoObj.getAgentDetailsAgentIdWise(agentId,pageNumber,portalId,clientFlag);
							session.put("userType",searchUserType);
							session.put("pageNumber",pageNumber);
							session.put("agentActivationUserId",agentId);	
							session.put("buttonStatus","all");
							session.put("portalId",portalId);
							session.put("advanceSearchOption",advanceSearchOption);
							session.put("agentSearchList",agentDetails);
							return "agentList";
						}
					}
				}

				return "agentList";
			}	
		}catch(Exception e){
			request.setAttribute("message", "Process aborted due to Technical Failure.");
			System.out.println("Exception in AgentActivationAction");
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
