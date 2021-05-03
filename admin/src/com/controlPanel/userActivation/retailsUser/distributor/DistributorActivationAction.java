package com.controlPanel.userActivation.retailsUser.distributor;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.formBean.distributor.DistributorDetailsFormBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
* Updated By :Arvind
* Updated Date : 10 Jun 2013
* Updated Matter : Format of class.
*/

/**
* Updated By 		 : Manoj
* Updated Date		 : 18 Jun 2013
* Updated Matter	 : Optimization of code and database .
*/

public  class DistributorActivationAction extends ActionSupport implements ServletRequestAware,ServletResponseAware
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
			if(userId==null)
			{
				request.setAttribute("message","Your Login Session has Expired. Please Login Again.");
				return "sessionExpire";
			}
			String param=request.getParameter("param");
			System.out.println("DistributorActivationAction and Param is : "+param);
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
					DistributorActivationDao daoObj=new DistributorActivationDao();
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
				String advanceSearchOption="N";
				String portalId=(String)request.getParameter("portalId");
				DistributorActivationDao daoObj=new DistributorActivationDao();
				if(portalId==null)
				{
					portalId=userId;
				}
				if(advanceSearchOption.equalsIgnoreCase("N"))
				{
					
					if(searchUserType.equalsIgnoreCase("distributor"))
					{
						String  distributorId=(String)request.getParameter("userId");
						if(distributorId==null || distributorId=="")
						{
							session.remove("distributorActivationUserId");
							String status=request.getParameter("status");
							session.put("buttonStatus",status);
							if(portalId.equalsIgnoreCase("all") && status.equals("all"))
							{
								int pageNumber=Integer.parseInt((String)request.getParameter("pageNumber"));
								if(pageNumber==1)
								{
									int noOfRecords=daoObj.getNoOfRecordsAllDistributorDetails();
									if(noOfRecords==0)
									{
										request.setAttribute("message","Please Check Input Value.");
										return "searchUser";
									}
									session.put("noOfRecords",noOfRecords);
								}
								ArrayList distributorDetails=daoObj.getAllDistributorsDetail(pageNumber);
								session.put("userType",searchUserType);
								session.put("pageNumber",pageNumber);
								session.put("status",status);
								session.put("portalId",portalId);
								session.put("advanceSearchOption",advanceSearchOption);
								session.put("distributorSearchList",distributorDetails);
								return "distributorList";
							}
							if(portalId.equalsIgnoreCase("all") && !status.equals("all"))
							{
								int pageNumber=Integer.parseInt((String)request.getParameter("pageNumber"));
								if(status.equalsIgnoreCase("blocked") || status.equalsIgnoreCase("unblocked")){
//									System.out.println("we are here in blocked status wise of ds-----------");
									if(pageNumber==1)
									{
										int noOfRecords=daoObj.getNoOfRecordsAllDistributorDetailsBlockStatusWise(status);
//										System.out.println("total no of records :"+noOfRecords);
										if(noOfRecords==0)
										{
											request.setAttribute("message","Please Check Input Value.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
									ArrayList distributorDetails=daoObj.getDistributorDetailsBlockStatusWise(status,pageNumber);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("portalId",portalId);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("distributorSearchList",distributorDetails);
									return "distributorList";
								}
								else{
									if(pageNumber==1)
									{
										int noOfRecords=daoObj.getNoOfRecordsAllDistributorDetailsStatusWise(status);
										if(noOfRecords==0)
										{
											request.setAttribute("message","Please Check Input Value.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
									ArrayList distributorDetails=daoObj.getDistributorDetailsStatusWise(status,pageNumber);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("portalId",portalId);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("distributorSearchList",distributorDetails);
									return "distributorList";
								}
							}
							if(!portalId.equalsIgnoreCase("all") && status.equals("all"))
							{
								int pageNumber=Integer.parseInt((String)request.getParameter("pageNumber"));
								if(pageNumber==1)
								{
									int noOfRecords=daoObj.getNoOfRecordsAllDistributorDetailsPortalIdWise(portalId);
									if(noOfRecords==0)
									{
										request.setAttribute("message","Please Check Input Value.");
										return "searchUser";
									}
									session.put("noOfRecords",noOfRecords);
								}
								ArrayList distributorDetails=daoObj.getDistributorDetailsPortalWise(portalId,pageNumber);
								session.put("userType",searchUserType);
								session.put("pageNumber",pageNumber);
								session.put("status",status);
								session.put("portalId",portalId);
								session.put("advanceSearchOption",advanceSearchOption);
								session.put("distributorSearchList",distributorDetails);
								return "distributorList";
							} 
							if(!portalId.equalsIgnoreCase("all") && !status.equals("all"))
							{
								int pageNumber=Integer.parseInt((String)request.getParameter("pageNumber"));
								if(status.equalsIgnoreCase("blocked") || status.equalsIgnoreCase("unblocked")){
									if(pageNumber==1)
									{
										int noOfRecords=daoObj.getNoOfRecordsAllDistributorDetailsPortalIdBlockStatusWise(portalId,status);
										if(noOfRecords==0)
										{
											request.setAttribute("message","Please Check Input Value.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
									ArrayList distributorDetails=daoObj.getDistributorDetailsOfPortalIdBlockStatusWise(portalId,status,pageNumber);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("portalId",portalId);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("distributorSearchList",distributorDetails);
									return "distributorList";
								}
								else{
									if(pageNumber==1)
									{
										int noOfRecords=daoObj.getNoOfRecordsAllDistributorDetailsPortalIdStatusWise(portalId,status);
										if(noOfRecords==0)
										{
											request.setAttribute("message","Please Check Input Value.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
									ArrayList distributorDetails=daoObj.getDistributorDetailsOfPortalIdStatusWise(portalId,status,pageNumber);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("portalId",portalId);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("distributorSearchList",distributorDetails);
									return "distributorList";
								}
							}
						}
						else{
							int pageNumber=Integer.parseInt((String)request.getParameter("pageNumber"));
							if(pageNumber==1)
							{
								int noOfRecords=daoObj.getNoOfRecordsAllDistributorDetailsDistributorIdWise(distributorId,portalId);
								if(noOfRecords==0)
								{
									request.setAttribute("message","Please Check Input Value.");
									return "searchUser";
								}
								session.put("noOfRecords",noOfRecords);
							}
							ArrayList distributorDetails=daoObj.getDistributorDetailsDistributorIdWise(distributorId,pageNumber,portalId);
							session.put("userType",searchUserType);
							session.put("pageNumber",pageNumber);
							session.put("distributorActivationUserId",distributorId);	
							session.put("buttonStatus","all");
							session.put("portalId",portalId);
							session.put("advanceSearchOption",advanceSearchOption);
							session.put("distributorSearchList",distributorDetails);
							return "distributorList";
						}
					}
				}
			}	
			else if("exportDS".equalsIgnoreCase(param)) {
				
				String loginType=(String)session.get("loginType");
				String portalId="";
				if(!loginType.equalsIgnoreCase("SuperAdmin"))
				{
					portalId=(String)session.get("adminUserPortalId");
				}
				
				DistributorActivationDao daoObj=new DistributorActivationDao();
				List<DistributorDetailsFormBean> list=daoObj.downloadDS(loginType, portalId);
				ArrayList<String> rows = new ArrayList<String>();
				
				if(list!=null && list.size()>0)
				{
					
					response.setContentType("application/vnd.ms-excel");
					String reportName = "DS_details.xls";
					response.setHeader("Content-disposition", "attachment; filename=" + reportName);

					rows.add("DsId");
					rows.add("\t");
					rows.add("MdId");
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
					rows.add("\n"); 

					for(DistributorDetailsFormBean bean:list)
					{
						rows.add(bean.getDistributorInitial()+bean.getDistributorId());
						rows.add("\t");
						rows.add(bean.getMdId()+"");
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
			else if(param.equalsIgnoreCase("activateDistributor"))
			{
				HashMap dynamicDetails=(HashMap) session.get("dynamicDetails");
				String clientFlag=(String)dynamicDetails.get("clientFlag");
				String[] distributorIds=request.getParameterValues("distributorIds");
				DistributorActivationDao daoObj=new DistributorActivationDao();
				daoObj.activateDistributors(distributorIds,clientFlag,session);
				ArrayList activatedDistributorsList=(ArrayList)session.get("activatedDistributorsList");
				ArrayList deactivatedDistributorsList=(ArrayList)session.get("deactivatedDistributorList");
				
				String message="";
				if(activatedDistributorsList.size()>0)
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
					if(searchUserType.equalsIgnoreCase("distributor"))
					{
						String  distributorId=(String)session.get("distributorActivationUserId");
						if(distributorId==null || distributorId=="")
						{
							session.remove("distributorActivationUserId");
							String status=(String)session.get("status");
							session.put("buttonStatus",status);
							if(portalId.equalsIgnoreCase("all") && status.equals("all"))
							{
								int pageNumber=(Integer)session.get("pageNumber");
								if(pageNumber==1)
								{
									int noOfRecords=daoObj.getNoOfRecordsAllDistributorDetails();
									if(noOfRecords==0)
									{
										request.setAttribute("message","Please Check Input Value.");
										return "searchUser";
									}
									session.put("noOfRecords",noOfRecords);
								}
								ArrayList distributorDetails=daoObj.getAllDistributorsDetail(pageNumber);
								session.put("userType",searchUserType);
								session.put("pageNumber",pageNumber);
								session.put("status",status);
								session.put("portalId",portalId);
								session.put("advanceSearchOption",advanceSearchOption);
								session.put("distributorSearchList",distributorDetails);
								return "distributorList";
							}
							if(portalId.equalsIgnoreCase("all") && !status.equals("all"))
							{
								int pageNumber=(Integer)session.get("pageNumber");
								if(status.equalsIgnoreCase("blocked") || status.equalsIgnoreCase("unblocked"))
								{
									if(pageNumber==1)
									{
										int noOfRecords=daoObj.getNoOfRecordsAllDistributorDetailsBlockStatusWise(status);
										if(noOfRecords==0)
										{
											request.setAttribute("message","Please Check Input Value.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
									ArrayList distributorDetails=daoObj.getDistributorDetailsBlockStatusWise(status,pageNumber);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("portalId",portalId);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("distributorSearchList",distributorDetails);
									return "distributorList";
								}
								else{
									if(pageNumber==1)
									{
										int noOfRecords=daoObj.getNoOfRecordsAllDistributorDetailsStatusWise(status);
										if(noOfRecords==0){
											request.setAttribute("message","Please Check Input Value.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
									ArrayList distributorDetails=daoObj.getDistributorDetailsStatusWise(status,pageNumber);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("portalId",portalId);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("distributorSearchList",distributorDetails);
									return "distributorList";
								}
							}
							if(!portalId.equalsIgnoreCase("all") && status.equals("all"))
							{
								int pageNumber=(Integer)session.get("pageNumber");
								if(pageNumber==1)
								{
									int noOfRecords=daoObj.getNoOfRecordsAllDistributorDetailsPortalIdWise(portalId);
									if(noOfRecords==0)
									{
										request.setAttribute("message","Please Check Input Value.");
										return "searchUser";
									}
									session.put("noOfRecords",noOfRecords);
								}
								ArrayList distributorDetails=daoObj.getDistributorDetailsPortalWise(portalId,pageNumber);
								session.put("userType",searchUserType);
								session.put("pageNumber",pageNumber);
								session.put("status",status);
								session.put("portalId",portalId);
								session.put("advanceSearchOption",advanceSearchOption);
								session.put("distributorSearchList",distributorDetails);
								return "distributorList";
							} 
							if(!portalId.equalsIgnoreCase("all") && !status.equals("all"))
							{
								int pageNumber=(Integer)session.get("pageNumber");
								if(status.equalsIgnoreCase("blocked") || status.equalsIgnoreCase("unblocked")){
									if(pageNumber==1)
									{
										int noOfRecords=daoObj.getNoOfRecordsAllDistributorDetailsPortalIdBlockStatusWise(portalId,status);
										if(noOfRecords==0)
										{
											request.setAttribute("message","Please Check Input Value.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
									ArrayList distributorDetails=daoObj.getDistributorDetailsOfPortalIdBlockStatusWise(portalId,status,pageNumber);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("portalId",portalId);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("distributorSearchList",distributorDetails);
									return "distributorList";
								}
								else{
									if(pageNumber==1){
										int noOfRecords=daoObj.getNoOfRecordsAllDistributorDetailsPortalIdStatusWise(portalId,status);
										if(noOfRecords==0)
										{
											request.setAttribute("message","Please Check Input Value.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
									ArrayList distributorDetails=daoObj.getDistributorDetailsOfPortalIdStatusWise(portalId,status,pageNumber);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("portalId",portalId);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("distributorSearchList",distributorDetails);
									return "distributorList";
								}
							}
						}
						else{
							int pageNumber=(Integer)session.get("pageNumber");
							if(pageNumber==1)
							{
								int noOfRecords=daoObj.getNoOfRecordsAllDistributorDetailsDistributorIdWise(distributorId,portalId);
								if(noOfRecords==0)
								{
									session.put("message","Please Check Input Value.");
									return "searchUser";
								}
								session.put("noOfRecords",noOfRecords);
							}
							ArrayList distributorDetails=daoObj.getDistributorDetailsDistributorIdWise(distributorId,pageNumber,portalId);
							session.put("userType",searchUserType);
							session.put("distributorActivationUserId",distributorId);
							session.put("pageNumber",pageNumber);
							session.put("buttonStatus","all");
							session.put("portalId",portalId);							  
							session.put("advanceSearchOption",advanceSearchOption);
							session.put("distributorSearchList",distributorDetails);
							return "distributorList";
						}
					}
				}
				return "distributorList";
			}	
			else if(param.equalsIgnoreCase("deactivateDistributor"))
			{
				HashMap dynamicDetails=(HashMap) session.get("dynamicDetails");
				String clientFlag=(String)dynamicDetails.get("clientFlag");
				String[] distributorIds=request.getParameterValues("distributorIds");
				
				DistributorActivationDao daoObj=new DistributorActivationDao();
				daoObj.deactivateDistributors(distributorIds,clientFlag,session);
				ArrayList activatedDistributorsList=(ArrayList)session.get("activatedDistributorsList");
				ArrayList deactivatedDistributorList=(ArrayList)session.get("deactivatedDistributorList");
				String message="";
				
				if(deactivatedDistributorList.size()>0)
				{
					message="Selected User/s has been Deactivated.";
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
					if(searchUserType.equalsIgnoreCase("distributor"))
					{
						String  distributorId=(String)session.get("distributorActivationUserId");
						if(distributorId==null || distributorId=="")
						{
							session.remove("distributorActivationUserId");
							String status=(String)session.get("status");
							session.put("buttonStatus",status);
							if(portalId.equalsIgnoreCase("all") && status.equals("all"))
							{
								int pageNumber=(Integer)session.get("pageNumber");
								if(pageNumber==1)
								{
									int noOfRecords=daoObj.getNoOfRecordsAllDistributorDetails();
									if(noOfRecords==0)
									{
										request.setAttribute("message","Please Check Input Value.");
										return "searchUser";
									}
									session.put("noOfRecords",noOfRecords);
								}
								ArrayList distributorDetails=daoObj.getAllDistributorsDetail(pageNumber);
								session.put("userType",searchUserType);
								session.put("pageNumber",pageNumber);
								session.put("status",status);
								session.put("portalId",portalId);
								session.put("advanceSearchOption",advanceSearchOption);
								session.put("distributorSearchList",distributorDetails);
								return "distributorList";
							}
							if(portalId.equalsIgnoreCase("all") && !status.equals("all"))
							{
								int pageNumber=(Integer)session.get("pageNumber");
								if(status.equalsIgnoreCase("blocked") || status.equalsIgnoreCase("unblocked"))
								{
									if(pageNumber==1)
									{
										int noOfRecords=daoObj.getNoOfRecordsAllDistributorDetailsBlockStatusWise(status);
										if(noOfRecords==0)
										{
											request.setAttribute("message","Please Check Input Value.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
									ArrayList distributorDetails=daoObj.getDistributorDetailsBlockStatusWise(status,pageNumber);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("portalId",portalId);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("distributorSearchList",distributorDetails);
									return "distributorList";
								}
								else{
									if(pageNumber==1)
									{
										int noOfRecords=daoObj.getNoOfRecordsAllDistributorDetailsStatusWise(status);
										if(noOfRecords==0)
										{
											request.setAttribute("message","Please Check Input Value.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
									ArrayList distributorDetails=daoObj.getDistributorDetailsStatusWise(status,pageNumber);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("portalId",portalId);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("distributorSearchList",distributorDetails);
									return "distributorList";
								}
							}
							if(!portalId.equalsIgnoreCase("all") && status.equals("all"))
							{
								int pageNumber=(Integer)session.get("pageNumber");
								if(pageNumber==1)
								{
									int noOfRecords=daoObj.getNoOfRecordsAllDistributorDetailsPortalIdWise(portalId);
									if(noOfRecords==0)
									{
										request.setAttribute("message","Please Check Input Value.");
										return "searchUser";
									}
									session.put("noOfRecords",noOfRecords);
								}
								ArrayList distributorDetails=daoObj.getDistributorDetailsPortalWise(portalId,pageNumber);
								session.put("userType",searchUserType);
								session.put("pageNumber",pageNumber);
								session.put("status",status);
								session.put("portalId",portalId);
								session.put("advanceSearchOption",advanceSearchOption);
								session.put("distributorSearchList",distributorDetails);
								return "distributorList";
							} 
							if(!portalId.equalsIgnoreCase("all") && !status.equals("all"))
							{
								int pageNumber=(Integer)session.get("pageNumber");
								if(status.equalsIgnoreCase("blocked") || status.equalsIgnoreCase("unblocked"))
								{
									if(pageNumber==1)
									{
										int noOfRecords=daoObj.getNoOfRecordsAllDistributorDetailsPortalIdBlockStatusWise(portalId,status);
										if(noOfRecords==0)
										{
											request.setAttribute("message","Please Check Input Value.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
									ArrayList distributorDetails=daoObj.getDistributorDetailsOfPortalIdBlockStatusWise(portalId,status,pageNumber);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("portalId",portalId);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("distributorSearchList",distributorDetails);
									return "distributorList";
								}
								else{
									if(pageNumber==1)
									{
										int noOfRecords=daoObj.getNoOfRecordsAllDistributorDetailsPortalIdStatusWise(portalId,status);
										if(noOfRecords==0)
										{
											request.setAttribute("message","Please Check Input Value.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
									ArrayList distributorDetails=daoObj.getDistributorDetailsOfPortalIdStatusWise(portalId,status,pageNumber);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("portalId",portalId);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("distributorSearchList",distributorDetails);
									return "distributorList";
								}
							}
						}
						else{
							int pageNumber=(Integer)session.get("pageNumber");
							if(pageNumber==1)
							{
								int noOfRecords=daoObj.getNoOfRecordsAllDistributorDetailsDistributorIdWise(distributorId,portalId);
								if(noOfRecords==0)
								{
									request.setAttribute("message","Please Check Input Value.");
									return "searchUser";
								}
								session.put("noOfRecords",noOfRecords);
							}
							ArrayList distributorDetails=daoObj.getDistributorDetailsDistributorIdWise(distributorId,pageNumber,portalId);
							session.put("userType",searchUserType);
							session.put("pageNumber",pageNumber);
							session.put("distributorActivationUserId",distributorId);
							session.put("buttonStatus","all");
							session.put("portalId",portalId);							 
							session.put("advanceSearchOption",advanceSearchOption);
							session.put("distributorSearchList",distributorDetails);
							return "distributorList";
						}
					}
				}
				
				return "distributorList";
			}	
			else if(param.equalsIgnoreCase("blockDistributor"))
			{
				HashMap dynamicDetails=(HashMap) session.get("dynamicDetails");
				String clientFlag=(String)dynamicDetails.get("clientFlag");
				String validUpto=request.getParameter("dateOfActivation");
				String[] distributorIds=request.getParameterValues("distributorIds");
				
				DistributorActivationDao daoObj=new DistributorActivationDao();
				daoObj.blockDistributors(distributorIds,clientFlag,session);
				ArrayList blockedDistributorsList=(ArrayList)session.get("blockedDistributorsList");
				ArrayList unblockedDistributorList=(ArrayList)session.get("unblockedDistributorList");
				String message="";
				
				if(blockedDistributorsList.size()>0)
				{
					message="Selected User/s has been Blocked.";
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
					if(searchUserType.equalsIgnoreCase("distributor"))
					{
						String  distributorId=(String)session.get("distributorActivationUserId");
						if(distributorId==null || distributorId=="")
						{
							session.remove("distributorActivationUserId");
							String status=(String)session.get("status");
							session.put("buttonStatus",status);
							if(portalId.equalsIgnoreCase("all") && status.equals("all"))
							{
								int pageNumber=(Integer)session.get("pageNumber");
								if(pageNumber==1)
								{
									int noOfRecords=daoObj.getNoOfRecordsAllDistributorDetails();
									if(noOfRecords==0)
									{
										request.setAttribute("message","Please Check Input Value.");
										return "searchUser";
									}
									session.put("noOfRecords",noOfRecords);
								}
								ArrayList distributorDetails=daoObj.getAllDistributorsDetail(pageNumber);
								session.put("userType",searchUserType);
								session.put("pageNumber",pageNumber);
								session.put("status",status);
								session.put("portalId",portalId);
								session.put("advanceSearchOption",advanceSearchOption);
								session.put("distributorSearchList",distributorDetails);
								return "distributorList";
							}
							if(portalId.equalsIgnoreCase("all") && !status.equals("all"))
							{
								int pageNumber=(Integer)session.get("pageNumber");
								if(status.equalsIgnoreCase("blocked") || status.equalsIgnoreCase("unblocked"))
								{
									if(pageNumber==1)
									{
										int noOfRecords=daoObj.getNoOfRecordsAllDistributorDetailsBlockStatusWise(status);
										if(noOfRecords==0)
										{
											request.setAttribute("message","Please Check Input Value.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
									ArrayList distributorDetails=daoObj.getDistributorDetailsBlockStatusWise(status,pageNumber);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("portalId",portalId);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("distributorSearchList",distributorDetails);
									return "distributorList";
								}
								else{
									if(pageNumber==1)
									{
										int noOfRecords=daoObj.getNoOfRecordsAllDistributorDetailsStatusWise(status);
										if(noOfRecords==0)
										{
											request.setAttribute("message","Please Check Input Value.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
									ArrayList distributorDetails=daoObj.getDistributorDetailsStatusWise(status,pageNumber);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("portalId",portalId);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("distributorSearchList",distributorDetails);
									return "distributorList";
								}
							}
							if(!portalId.equalsIgnoreCase("all") && status.equals("all"))
							{
								int pageNumber=(Integer)session.get("pageNumber");
								if(pageNumber==1)
								{
									int noOfRecords=daoObj.getNoOfRecordsAllDistributorDetailsPortalIdWise(portalId);
									if(noOfRecords==0)
									{
										request.setAttribute("message","Please Check Input Value.");
										return "searchUser";
									}
									session.put("noOfRecords",noOfRecords);
								}
								ArrayList distributorDetails=daoObj.getDistributorDetailsPortalWise(portalId,pageNumber);
								session.put("userType",searchUserType);
								session.put("pageNumber",pageNumber);
								session.put("status",status);
								session.put("portalId",portalId);
								session.put("advanceSearchOption",advanceSearchOption);
								session.put("distributorSearchList",distributorDetails);
								return "distributorList";
							} 
							if(!portalId.equalsIgnoreCase("all") && !status.equals("all"))
							{
								int pageNumber=(Integer)session.get("pageNumber");
								if(status.equalsIgnoreCase("blocked") || status.equalsIgnoreCase("unblocked"))
								{
									if(pageNumber==1)
									{
										int noOfRecords=daoObj.getNoOfRecordsAllDistributorDetailsPortalIdBlockStatusWise(portalId,status);
										if(noOfRecords==0)
										{
											request.setAttribute("message","Please Check Input Value.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
									ArrayList distributorDetails=daoObj.getDistributorDetailsOfPortalIdBlockStatusWise(portalId,status,pageNumber);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("portalId",portalId);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("distributorSearchList",distributorDetails);
									return "distributorList";
								}
								else{
									if(pageNumber==1)
									{
										int noOfRecords=daoObj.getNoOfRecordsAllDistributorDetailsPortalIdStatusWise(portalId,status);
										if(noOfRecords==0)
										{
											request.setAttribute("message","Please Check Input Value.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
									ArrayList distributorDetails=daoObj.getDistributorDetailsOfPortalIdStatusWise(portalId,status,pageNumber);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("portalId",portalId);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("distributorSearchList",distributorDetails);
									return "distributorList";
								}
							}
						}
						else{
							int pageNumber=(Integer)session.get("pageNumber");
							if(pageNumber==1)
							{
								int noOfRecords=daoObj.getNoOfRecordsAllDistributorDetailsDistributorIdWise(distributorId,portalId);
								if(noOfRecords==0)
								{
									request.setAttribute("message","Please Check Input Value.");
									return "searchUser";
								}
								session.put("noOfRecords",noOfRecords);
							}
							ArrayList distributorDetails=daoObj.getDistributorDetailsDistributorIdWise(distributorId,pageNumber,portalId);
							session.put("userType",searchUserType);
							session.put("pageNumber",pageNumber);
							session.put("distributorActivationUserId",distributorId);	
							session.put("buttonStatus","all");
							session.put("portalId",portalId);
							session.put("advanceSearchOption",advanceSearchOption);
							session.put("distributorSearchList",distributorDetails);
							return "distributorList";
						}
					}
				}
				return "distributorList";
			}	
			else if(param.equalsIgnoreCase("unblockDistributor"))
			{
				HashMap dynamicDetails=(HashMap) session.get("dynamicDetails");
				String clientFlag=(String)dynamicDetails.get("clientFlag");
				String validUpto=request.getParameter("dateOfActivation");
				String[] distributorIds=request.getParameterValues("distributorIds");
				
				DistributorActivationDao daoObj=new DistributorActivationDao();
				daoObj.unblockDistributors(distributorIds,clientFlag,session);
				ArrayList unblockedDistributorsList=(ArrayList)session.get("unblockedDistributorList");
				ArrayList blockedDistributorsList=(ArrayList)session.get("blockedDistributorsList");
				String message="";
				
				if(unblockedDistributorsList.size()>0)
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
				if(advanceSearchOption.equalsIgnoreCase("N"))
				{
					if(searchUserType.equalsIgnoreCase("distributor"))
					{
						String  distributorId=(String)session.get("distributorActivationUserId");
//						System.out.println("distributorId>>>>>>>>>>>>>>>>>>>>>>>"+distributorId);
						if(distributorId==null || distributorId=="")
						{
							session.remove("distributorActivationUserId");
							String status=(String)session.get("status");
							session.put("buttonStatus",status);
							if(portalId.equalsIgnoreCase("all") && status.equals("all"))
							{
								int pageNumber=(Integer)session.get("pageNumber");
								if(pageNumber==1)
								{
									int noOfRecords=daoObj.getNoOfRecordsAllDistributorDetails();
									if(noOfRecords==0)
									{
										request.setAttribute("message","Please Check Input Value.");
										return "searchUser";
									}
									session.put("noOfRecords",noOfRecords);
								}
//								System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
								ArrayList distributorDetails=daoObj.getAllDistributorsDetail(pageNumber);
								session.put("userType",searchUserType);
								session.put("pageNumber",pageNumber);
								session.put("status",status);
								session.put("portalId",portalId);
								session.put("advanceSearchOption",advanceSearchOption);
								session.put("distributorSearchList",distributorDetails);
								return "distributorList";
							}
							if(portalId.equalsIgnoreCase("all") && !status.equals("all"))
							{
								int pageNumber=(Integer)session.get("pageNumber");
								if(status.equalsIgnoreCase("blocked") || status.equalsIgnoreCase("unblocked"))
								{
									if(pageNumber==1)
									{
										int noOfRecords=daoObj.getNoOfRecordsAllDistributorDetailsBlockStatusWise(status);
										if(noOfRecords==0)
										{
											request.setAttribute("message","Please Check Input Value.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
//									System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
									ArrayList distributorDetails=daoObj.getDistributorDetailsBlockStatusWise(status,pageNumber);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("portalId",portalId);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("distributorSearchList",distributorDetails);
									return "distributorList";
								}
								else{
									if(pageNumber==1)
									{
										int noOfRecords=daoObj.getNoOfRecordsAllDistributorDetailsStatusWise(status);
										if(noOfRecords==0)
										{
											request.setAttribute("message","Please Check Input Value.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
//									System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
									ArrayList distributorDetails=daoObj.getDistributorDetailsStatusWise(status,pageNumber);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("portalId",portalId);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("distributorSearchList",distributorDetails);
									return "distributorList";
								}
							}
							if(!portalId.equalsIgnoreCase("all") && status.equals("all"))
							{
								int pageNumber=(Integer)session.get("pageNumber");
								if(pageNumber==1)
								{
									int noOfRecords=daoObj.getNoOfRecordsAllDistributorDetailsPortalIdWise(portalId);
									if(noOfRecords==0)
									{
										request.setAttribute("message","Please Check Input Value.");
										return "searchUser";
									}
									session.put("noOfRecords",noOfRecords);
								}
//								System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
								ArrayList distributorDetails=daoObj.getDistributorDetailsPortalWise(portalId,pageNumber);
								session.put("userType",searchUserType);
								session.put("pageNumber",pageNumber);
								session.put("status",status);
								session.put("portalId",portalId);
								session.put("advanceSearchOption",advanceSearchOption);
								session.put("distributorSearchList",distributorDetails);
								return "distributorList";
							} 
							if(!portalId.equalsIgnoreCase("all") && !status.equals("all"))
							{
								int pageNumber=(Integer)session.get("pageNumber");
								if(status.equalsIgnoreCase("blocked") || status.equalsIgnoreCase("unblocked"))
								{
									if(pageNumber==1)
									{
										int noOfRecords=daoObj.getNoOfRecordsAllDistributorDetailsPortalIdBlockStatusWise(portalId,status);
										if(noOfRecords==0)
										{
											request.setAttribute("message","Please Check Input Value.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
//									System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
									ArrayList distributorDetails=daoObj.getDistributorDetailsOfPortalIdBlockStatusWise(portalId,status,pageNumber);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("portalId",portalId);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("distributorSearchList",distributorDetails);
									return "distributorList";
								}
								else{
									if(pageNumber==1)
									{
										int noOfRecords=daoObj.getNoOfRecordsAllDistributorDetailsPortalIdStatusWise(portalId,status);
										if(noOfRecords==0)
										{
											request.setAttribute("message","Please Check Input Value.");
											return "searchUser";
										}
										session.put("noOfRecords",noOfRecords);
									}
//									System.out.println("pageNumber in action class is>>>>>>>>>>>>>>>>>>>"+pageNumber);
									ArrayList distributorDetails=daoObj.getDistributorDetailsOfPortalIdStatusWise(portalId,status,pageNumber);
									session.put("userType",searchUserType);
									session.put("pageNumber",pageNumber);
									session.put("status",status);
									session.put("portalId",portalId);
									session.put("advanceSearchOption",advanceSearchOption);
									session.put("distributorSearchList",distributorDetails);
									return "distributorList";
								}
							}
						}
						else{
							int pageNumber=(Integer)session.get("pageNumber");
							if(pageNumber==1)
							{
								int noOfRecords=daoObj.getNoOfRecordsAllDistributorDetailsDistributorIdWise(distributorId,portalId);
								if(noOfRecords==0)
								{
									request.setAttribute("message","Please Check Input Value.");
									return "searchUser";
								}
								session.put("noOfRecords",noOfRecords);
							}
							ArrayList distributorDetails=daoObj.getDistributorDetailsDistributorIdWise(distributorId,pageNumber,portalId);
							session.put("userType",searchUserType);
							session.put("pageNumber",pageNumber);
							session.put("distributorActivationUserId",distributorId);	
							session.put("buttonStatus","all");
							session.put("portalId",portalId);
							session.put("advanceSearchOption",advanceSearchOption);
							session.put("distributorSearchList",distributorDetails);
							return "distributorList";
						}
					}
				}
				return "distributorList";
			}	
		}catch(Exception e){
			request.setAttribute("message", "Process aborted due to Technical Failure.");
			System.out.println("Exception in DistributorActivationAction");
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
