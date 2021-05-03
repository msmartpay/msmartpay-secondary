package com.controlPanel.SKUmgt;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class SKUManagementAction extends ActionSupport implements ServletRequestAware,ServletResponseAware
{
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request; 
	private HttpServletResponse response;
	private Map session;
	
	public String execute () throws Exception
	{
		session=ActionContext.getContext().getSession();
		try{
			String userId=(String)session.get("userId");
			if(userId==null)
			{
				request.setAttribute("message","Your Login Session has Expired. Please Login Again.");
				return "sessionExpire";
			}
			
			String param=request.getParameter("param");
			System.out.println("SKUManagementAction param is "+param);
			if(param.equalsIgnoreCase("getSKUPage"))
			{
				SKUManagementDao daoObj=new SKUManagementDao();
				ArrayList<String> CategoryName=daoObj.getCategory();
				ArrayList<String> MainServiceName=daoObj.getMainService();
				ArrayList<String> SubServiceName=daoObj.getSubService();
				ArrayList clientDetails=daoObj.getClientId();
				
				session.put("CategoryName", CategoryName);
				session.put("MainServiceName", MainServiceName);
				session.put("SubServiceName", SubServiceName);
				session.put("clientDetails", clientDetails);
				
				return "skuPage";
			}
			else if(param.equalsIgnoreCase("getSKUData"))
			{
				String category=request.getParameter("category");
				String mainService=request.getParameter("mainService");
				String subService=request.getParameter("subService");
				String clientId=request.getParameter("clientId");
				SKUManagementDao daoObj=new SKUManagementDao();
				ArrayList SKUData=daoObj.getSKUdata(category,mainService,subService,clientId);
				if(SKUData.size()<=0)
				{
					request.setAttribute("message", "Please Check Input value");
					
				}
				session.put("clientId", clientId);
				request.setAttribute("SKUData", SKUData);
				request.setAttribute("Displaycategory", category);
				request.setAttribute("DisplaymainService", mainService);
				request.setAttribute("DisplaysubService", subService);
				return "skuPage";
			}
			else if(param.equalsIgnoreCase("updateSKUdata"))
			{
				String clientId=(String) session.get("clientId");
				String loginType=(String)session.get("loginType");
				System.out.println("loginType "+loginType);
				String[] checkOperator=(String[])request.getParameterValues("checkpartial");
				System.out.println("check Partial is "+checkOperator);
				SKUManagementDao daoObj=new SKUManagementDao();
				String result="";
				String Displaycategory=request.getParameter("Displaycategory");
				String DisplaymainService=request.getParameter("DisplaymainService");
				String DisplaysubService=request.getParameter("DisplaysubService");
				System.out.println("Displaycategory 	:"+Displaycategory);
				System.out.println("DisplaymainService 	:"+DisplaymainService);
				System.out.println("DisplaysubService 	:"+DisplaysubService);
				if(loginType.equalsIgnoreCase("superadmin")||loginType.equalsIgnoreCase("activityAdmin"))
				{
					System.out.println("we are into 1 if");
					if(checkOperator==null)
					{
						request.setAttribute("message", "Please select Check box");
						return "skuPage"; 
					}
					ArrayList<String> list=null;
					ArrayList<ArrayList<String>> totallist=new ArrayList<ArrayList<String>>();;
			        
					int totpass=checkOperator.length;
					for(int i=0;i<totpass;i++)
					{
						list=new ArrayList<String>();
		 		        System.out.println("we are u=into to get value");
						String value=checkOperator[i];
						String mainService =request.getParameter("mser"+value);
						list.add(mainService);
						String subService =request.getParameter("ser"+value);
						list.add(subService);
						String category =request.getParameter("cat"+value);
						list.add(category);
						String SKUname =request.getParameter("sku"+value);
						list.add(SKUname);
						String SKUComm =request.getParameter("SKUComm"+value);
						list.add(SKUComm);
						String SKUCommType =request.getParameter("SKUCommType"+value);
						list.add(SKUCommType);
						String SKUCharge =request.getParameter("SKUCharge"+value);
						list.add(SKUCharge);
						String SKUChargeType =request.getParameter("SKUChargeType"+value);
						list.add(SKUChargeType);
						System.out.println("Now all data is "+list);
						totallist.add(list);
					}
					ArrayList<String> listopr=null;
					for(int j=0;j<totallist.size();j++)
					{
						System.out.println("we are going to update ");
						listopr=totallist.get(j);
						String mainService=listopr.get(0);
						String subService=listopr.get(1);
		 	        	String category=listopr.get(2);	 	        	 
		 	        	String SKUname=listopr.get(3);
		 	        	String SKUComm=listopr.get(4);
		 	        	
		 	        	String SKUCommType=listopr.get(5);
		 	        	String SKUCharge=listopr.get(6);
		 	        	String SKUChargeType=listopr.get(7);
		 	        	
		 	        	result=daoObj.updateSKU(clientId,mainService,subService,category,SKUname,SKUComm,SKUCommType,SKUCharge,SKUChargeType,userId);
					}
					if(result.equalsIgnoreCase("Success"))
					{
						request.setAttribute("message", "Proceess has been completed.");
						
					}else{
						request.setAttribute("", "Process aborted due to Technical Failure.");
					}
					ArrayList SKUData=daoObj.getSKUdata(Displaycategory,DisplaymainService,DisplaysubService,clientId);
					if(SKUData.size()<=0)
					{
						request.setAttribute("message", "Please Check Input value");
					}
					request.setAttribute("SKUData", SKUData);
					request.setAttribute("Displaycategory", DisplaysubService);
					request.setAttribute("DisplaymainService", DisplaymainService);
					request.setAttribute("DisplaysubService", DisplaysubService);
					return "skuPage";
				}
			}else if(param.equalsIgnoreCase("addSKUPage"))
			{
				return "addSKU";
			}
		}catch(Exception e)
		{
			request.setAttribute("message","Process aborted due to Technical Failure.");
			System.out.println("Exception in SKUManagementAction");
			System.out.println(e.toString());
			return "err";
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

	public Map getSession(){
		return session;
	}

	public void setServletResponse(HttpServletResponse response) 
	{
		this.response=response;
	}
}
