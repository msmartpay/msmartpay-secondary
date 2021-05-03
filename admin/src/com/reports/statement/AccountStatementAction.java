package com.reports.statement;


import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.common.CommonServiceDao;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class AccountStatementAction extends ActionSupport implements ServletRequestAware, ServletResponseAware
{
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Map session;

	public final String getAccountStatementPage()
	{
		Map session = ActionContext.getContext().getSession();
		String userId=(String)session.get("userId");
		if(userId==null)
		{
			request.setAttribute("message","Your Login Session has Expired. Please Login Again..");
			return "sessionExpire";
		}
		System.out.println(" getAccountStatementPage");
		return "getChanelACPage";
	}
	public final String getAccountStatementICICIPage()
	{
		Map session = ActionContext.getContext().getSession();
		String userId=(String)session.get("userId");
		if(userId==null)
		{
			request.setAttribute("message","Your Login Session has Expired. Please Login Again..");
			return "sessionExpire";
		}
		System.out.println(" getAccountStatementPage");
		return "ICICIStatement";
	}
	public final String getPendingAccountStatementPage()
	{
		Map session = ActionContext.getContext().getSession();
		String userId=(String)session.get("userId");
		if(userId==null)
		{
			request.setAttribute("message","Your Login Session has Expired. Please Login Again..");
			return "sessionExpire";
		}
		System.out.println(" getPendingChanelACPage");
		return "getPendingChanelACPage";
	}

	public final String getPendingAccountStatement()
	{
		request=ServletActionContext.getRequest();
		/*String param=request.getParameter("param");
		if(param.equalsIgnoreCase("viewReport"))
		{*/			

		ArrayList listData=new ArrayList();
		Map session = ActionContext.getContext().getSession();
		AccountStatementDao daoObj=new AccountStatementDao();
		try 
		{
			String userId=(String)session.get("userId");
			if(userId==null)
			{
				request.setAttribute("message","Your Login Session has Expired. Please Login Again..");
				return "sessionExpire";
			}

			String loginType=(String)session.get("loginType");
			String portalId="0";
			if(!loginType.equalsIgnoreCase("SuperAdmin"))
			{
				portalId=(String)session.get("adminUserPortalId");
			}
			String userType=request.getParameter("accountType");
			//System.out.println("userType is :::::::::::::::::"+userType);
			String id="";
			String resultId="";


			id="all";


			String fromDate= request.getParameter("fromDate");
			String toDate =request.getParameter("toDate");

			String fileStatus="";

			if(userType.equalsIgnoreCase("agent"))
			{
				listData =daoObj.getPendingAdminAccountStatementView(loginType,portalId,userType,id,fromDate,toDate,request);

				if (listData!=null && listData.size() <=0)
				{
					System.out.println("agent user");
					request.setAttribute("message","Data not Available");
					return "getPage";
				}
				request.setAttribute("showService", "Agent");
			}else{
				request.setAttribute("message","Data not Available");
				return "getPage";
			}
		}
		catch(Exception e)
		{
			request.setAttribute("message", "Process aborted due to Technical Failure.");
			System.out.println("Exception in AccountStatementAction");
			System.out.println(e.toString());
			e.printStackTrace();
			return "getPage";
		}
		//System.out.println(listData.size());	
		request.setAttribute("flag", "Y");
		request.setAttribute("RechargeData", listData);

		//}
		return "getPage"; 
	}


	public final String getAccountStatement()
	{
		request=ServletActionContext.getRequest();
		/*String param=request.getParameter("param");
		if(param.equalsIgnoreCase("viewReport"))
		{*/			

		ArrayList listData=new ArrayList();
		Map session = ActionContext.getContext().getSession();
		AccountStatementDao daoObj=new AccountStatementDao();
		try 
		{
			String userId=(String)session.get("userId");
			if(userId==null)
			{
				request.setAttribute("message","Your Login Session has Expired. Please Login Again..");
				return "sessionExpire";
			}

			String loginType=(String)session.get("loginType");
			String portalId="0";
			if(!loginType.equalsIgnoreCase("SuperAdmin"))
			{
				portalId=(String)session.get("adminUserPortalId");
			}
			String userType=request.getParameter("accountType");
			//System.out.println("userType is :::::::::::::::::"+userType);

			String id="";
			String resultId="";

			System.out.println("id is "+ id +"resultId : "+resultId+"  loginType : "+loginType);



			String fromDate= request.getParameter("fromDate");
			String toDate =request.getParameter("toDate");
			System.out.println(" fromDate "+fromDate);
			System.out.println(" toDate "+toDate);
			System.out.println(" resultId "+resultId);
			System.out.println(" userType "+userType);

			String fileStatus="";
			System.out.println(" we are here in  "+userType);

			if(userType.equalsIgnoreCase("adminUser"))
			{
				String stmt=request.getParameter("stmt");
				if(stmt.equalsIgnoreCase("all"))
				{
					id="all";
					//System.out.println("stmt is :"+stmt +"id is "+ id +"resultId : "+resultId);
				}
				else
				{
					id=request.getParameter("adminId");

					if(!userType.equalsIgnoreCase("EgenAS"))
					{
						resultId=daoObj.checkIdStatus(userType,id);

						if(resultId.equalsIgnoreCase("NotFound"))
						{
							request.setAttribute("message","The ID is not correct." );
							return userType;
						}
					}
				}

				listData=daoObj.getAdminAccountStatementView(loginType,portalId,userType,id,fromDate,toDate,request);
				if (listData.size() <=0)
				{
					System.out.println("admin user");
					request.setAttribute("message","Data not Found");
					return "getPage";
				}
				request.setAttribute("showService", "Admin");
			}

			else if(userType.equalsIgnoreCase("mds"))
			{
				String stmt=request.getParameter("mdsstmt");
				if(stmt.equalsIgnoreCase("all"))
				{
					id="all";
					//System.out.println("stmt is :"+stmt +"id is "+ id +"resultId : "+resultId);
				}
				else
				{
					id=request.getParameter("mdsId");

					if(!userType.equalsIgnoreCase("EgenAS"))
					{
						resultId=daoObj.checkIdStatus(userType,id);

						if(resultId.equalsIgnoreCase("NotFound"))
						{
							request.setAttribute("message","The ID is not correct." );
							return userType;
						}
					}
				}
				listData =daoObj.getMDAccountStatement(loginType,portalId,userType,id,fromDate,toDate,request);
				if (listData.size() <=0)
				{
					System.out.println("mds user");
					request.setAttribute("message","Data not Found");
					return "getPage";
				}
				request.setAttribute("showService", "MD");
			}

			else if(userType.equalsIgnoreCase("ds"))
			{
				String stmt=request.getParameter("dsstmt");
				if(stmt.equalsIgnoreCase("all"))
				{
					id="all";
					//System.out.println("stmt is :"+stmt +"id is "+ id +"resultId : "+resultId);
				}
				else
				{
					id=request.getParameter("dsId");

					if(!userType.equalsIgnoreCase("EgenAS"))
					{
						resultId=daoObj.checkIdStatus(userType,id);

						if(resultId.equalsIgnoreCase("NotFound"))
						{
							request.setAttribute("message","The ID is not correct." );
							return userType;
						}
					}
				}
				listData =daoObj.getDistributorAccountStatement(loginType,portalId,userType,id,fromDate,toDate,request);

				if (listData.size() <=0)
				{
					System.out.println("ds user");
					request.setAttribute("message","Data not Found");
					return "getPage";
				}
				request.setAttribute("showService", "DS");
			}

			else if(userType.equalsIgnoreCase("agent"))
			{
				String stmt=request.getParameter("agentstmt");
				if(stmt.equalsIgnoreCase("all"))
				{
					id="all";
					//System.out.println("stmt is :"+stmt +"id is "+ id +"resultId : "+resultId);
				}
				else
				{
					id=request.getParameter("agentId");

					if(!userType.equalsIgnoreCase("EgenAS"))
					{
						resultId=daoObj.checkIdStatus(userType,id);

						if(resultId.equalsIgnoreCase("NotFound"))
						{
							request.setAttribute("message","The ID is not correct." );
							return userType;
						}
					}
				}
				listData =daoObj.getAgentAccountStatement(loginType,portalId,userType,id,fromDate,toDate,request);

				if (listData.size() <=0)
				{
					System.out.println("agent user");
					request.setAttribute("message","Data not Available");
					return "getPage";
				}
				request.setAttribute("showService", "Agent");
			}
		}
		catch(Exception e)
		{
			request.setAttribute("message", "Process aborted due to Technical Failure.");
			System.out.println("Exception in AccountStatementAction");
			System.out.println(e.toString());
			return "getPage";
		}
		//System.out.println(listData.size());	
		request.setAttribute("flag", "Y");
		request.setAttribute("RechargeData", listData);

		//}
		return "getPage"; 
	}

	public final String getAccountStatementEGEN()
	{
		request=ServletActionContext.getRequest();

		ArrayList listData=new ArrayList();
		Map session = ActionContext.getContext().getSession();
		AccountStatementDao daoObj=new AccountStatementDao();
		try 
		{
			String userId=(String)session.get("userId");
			if(userId==null)
			{
				request.setAttribute("message","Your Login Session has Expired. Please Login Again.");
				return "sessionExpire";
			}
			String loginType=(String)session.get("loginType");
			String portalId="0";
			if(!loginType.equalsIgnoreCase("SuperAdmin"))
			{
				portalId=(String)session.get("adminUserPortalId");
			}

			String userType=request.getParameter("accountType");
			System.out.println("userType is :::::::::::::::::"+userType);
			String stmt=request.getParameter("stmt");
			String id="all";
			id=(String)request.getParameter("adminId");
			System.out.println("ID : "+id);
			if(id==null || id.equalsIgnoreCase(""))
				id="all";

			String resultId="";

			System.out.println("stmt is :"+stmt +"id is : "+ id +"resultId : "+resultId);

			String fromDate= request.getParameter("fromDate");
			String toDate =request.getParameter("toDate");


			String fileStatus="";
			/*if(userType.equalsIgnoreCase("EgenAS"))
			{
				listData=daoObj.getAccountStatementEGEN(id,portalId,fromDate,toDate);
				if(listData.size()<=0)
				{
					request.setAttribute("message", "Data not Available.");
				}
				request.setAttribute("flag", "Y");
				request.setAttribute("RechargeData", listData);
				request.setAttribute("showService", "EGEN");
				return "getPageEGEN";
			}

			else if(userType.equalsIgnoreCase("EgenASDetails"))
			{
				listData =daoObj.getAccountStatementEGEN(id,portalId,fromDate,toDate);
				//System.out.println("listData size is   "+listData.size());
				if(listData.size()<=0)
				{
					request.setAttribute("message", "Data not Available.");
				}
				request.setAttribute("flag", "Y");
				request.setAttribute("RechargeData", listData);
				request.setAttribute("showService", "EGEN");
				return "getPageEGEN";
			}*/

			listData=daoObj.getAccountStatementEGEN("all",id,fromDate,toDate);
			if(listData.size()<=0)
			{
				request.setAttribute("message", "Data not Available.");
			}else{
				request.setAttribute("flag", "Y");
				request.setAttribute("RechargeData", listData);
				request.setAttribute("showService", "EGEN");
			}
			return "getPageEGEN";
		}
		catch(Exception e)
		{
			request.setAttribute("message", "Process aborted due to Technical Failure.");
			System.out.println("Exception in AccountStatementAction");
			e.printStackTrace();
		}
		request.setAttribute("flag", "N");

		return "getPageEGEN";
	} 

	public final String AccountStatement()
	{
		Map session = ActionContext.getContext().getSession();
		AccountStatementDao daoObj=new AccountStatementDao();

		try 
		{
			String userId=(String)session.get("userId");
			if(userId==null)
			{
				request.setAttribute("message","Your Login Session has Expired. Please Login Again.");
				return "sessionExpire";
			}

			String loginType=(String)session.get("loginType");
			String portalId="0";

			if(!loginType.equalsIgnoreCase("SuperAdmin"))
			{
				portalId=(String)session.get("adminUserPortalId");
			}

			String userType=request.getParameter("accountType");
			//System.out.println("userType is :::::::::::::::::"+userType);
			String resultId="";

			String id="all";

			String fromDate= request.getParameter("fromDate");
			String toDate =request.getParameter("toDate");
			//String filePath1 = "E:/Work/";
			String filePath1=request.getRealPath("/")+"Reportfile/AccountStatement/";
			//System.out.println(" file path1 :"+filePath1);
			String filePath2="AccountStatement"+".xls";

			//String path="/usr/Trandata/";
			//String path="E:/Work/";
			//String zipFilePath2="AccountStatement"+".rar";
			//String filePath =path+filePath2;
			String zipFilePath=filePath1+filePath2;
			String fileStatus="";

			////System.out.println("userType "+userType);
			if(userType.equalsIgnoreCase("agent"))
			{
				id=(String)request.getParameter("agentId");
				//System.out.println("ID : "+id);
				if(id==null || id.equalsIgnoreCase(""))
					id="all";

				fileStatus =daoObj.getAgentAccountstmt(loginType,portalId,userType,id,fromDate,toDate,zipFilePath);
			}
			if(userType.equalsIgnoreCase("agentDetail"))
			{
				id=(String)request.getParameter("agentIddet");
				//System.out.println("ID : "+id);
				if(id==null || id.equalsIgnoreCase(""))
					id="all";

				fileStatus =daoObj.getAgentAccountstmtDetail(loginType,portalId,userType,id,fromDate,toDate,zipFilePath);
			}
			else if(userType.equalsIgnoreCase("ds"))
			{
				id=(String)request.getParameter("dsId");
				//System.out.println("ID : "+id);
				if(id==null || id.equalsIgnoreCase(""))
					id="all";

				fileStatus =daoObj.getDSAccountstmt(loginType,portalId,userType,id,fromDate,toDate,zipFilePath);
			}
			else if(userType.equalsIgnoreCase("dsdetail"))
			{
				id=(String)request.getParameter("dsIddet");
				//System.out.println("ID : "+id);
				if(id==null || id.equalsIgnoreCase(""))
					id="all";

				fileStatus =daoObj.getDSAccountstmtDetails(loginType,portalId,userType,id,fromDate,toDate,zipFilePath);
			}
			else if(userType.equalsIgnoreCase("mds"))
			{
				id=(String)request.getParameter("mdsId");
				//System.out.println("ID : "+id);
				if(id==null || id.equalsIgnoreCase(""))
					id="all";

				fileStatus =daoObj.getMDAccountstmt(loginType,portalId,userType,id,fromDate,toDate,zipFilePath);

			}else if(userType.equalsIgnoreCase("mdsdetail"))
			{
				id=(String)request.getParameter("mdsIddet");
				//System.out.println("ID : "+id);
				if(id==null || id.equalsIgnoreCase(""))
					id="all";

				fileStatus =daoObj.getMDAccountstmtDetails(loginType,portalId,userType,id,fromDate,toDate,zipFilePath);
			}
			else if(userType.equalsIgnoreCase("adminUser"))
			{
				id=(String)request.getParameter("adminId");
				//System.out.println("ID : "+id);
				if(id==null || id.equalsIgnoreCase(""))
					id="all";

				fileStatus =daoObj.getPortalAccountstmt(loginType,portalId,userType,id,fromDate,toDate,zipFilePath);

			}else if(userType.equalsIgnoreCase("adminUserdetail"))
			{
				id=(String)request.getParameter("adminIddet");
				//System.out.println("ID : "+id);
				if(id==null || id.equalsIgnoreCase(""))
					id="all";

				fileStatus =daoObj.getPortalAccountstmtDetails(loginType,portalId,userType,id,fromDate,toDate,zipFilePath);
			}	
			
			else if(userType.equalsIgnoreCase("icici"))
			{
				CommonServiceDao commonServiceDao=new CommonServiceDao();
				
				fileStatus=commonServiceDao.getICICIAccountstmt(fromDate,toDate,zipFilePath);
				//System.out.println("Arvind"+fileStatus);
			}
			else
			{
				//System.out.println("we are here into last else ");
			}

			if(fileStatus.equalsIgnoreCase("Norecord"))
			{
				request.setAttribute("message","There is no transaction in report.");
				return userType;
			}
			if(fileStatus.equalsIgnoreCase("ERROR"))
			{
				request.setAttribute("message","Due to some technical problem we can not provide you the report.");
				return userType;
			}
			if(fileStatus.equalsIgnoreCase("MoreRecord"))
			{
				request.setAttribute("message","Transaction are more than 25000 which is not allowed for download");
				return userType;
			}

			/*ZipCreator zipObject=new ZipCreator();
			String result=zipObject.createRarFile(filePath, zipFilePath);

			if(result.equals("error"))
			{
				request.setAttribute("message","Transaction are more than 25000 which is not allowed for download");
				return userType;
			}*/
			response.sendRedirect("Reportfile/AccountStatement/"+filePath2);
		}catch(Exception e)
		{
			//System.out.println("Exception in AccountStatementAction class");
			request.setAttribute("message", "Process aborted due to Technical Failure.");
			e.printStackTrace();
		}
		return null;
	}

	public void setServletRequest(HttpServletRequest request) 
	{
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) 
	{
		this.response = response;
	}
}
