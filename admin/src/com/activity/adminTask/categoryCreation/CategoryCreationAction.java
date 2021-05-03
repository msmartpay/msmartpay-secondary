/**
 *  Created Date---9/5/2012
 *  Created By---Amit Pathak 
 *  Purpose- The class(CategoryCreationAction)is created for category creation an setting commission
 *  Updated Date-11/5/2012
 *  Updated By--Amit Pathak 
 *  Update Purpose-
*/
package com.activity.adminTask.categoryCreation;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CategoryCreationAction  extends ActionSupport implements ModelDriven<Object>,ServletRequestAware,ServletResponseAware  
{
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	//----------------------Introducing model interceptor------------------- 
	CategoryCreationForm formBean=new CategoryCreationForm();
	
	public Object getModel() 
	{
		return formBean;
	}
	
	//-----------------------Object of dao class--------------------------------
	CategoryCreationDao dao=new CategoryCreationDao();
	 
	//-------------	 Method to redirect header control to page----------------
	 
	public String categoryCreationSearch()
	{
		HttpSession session = request.getSession(true);
		String userId=(String)session.getAttribute("userId");
		if(userId==null){
			request.setAttribute("message","Your Login Session has Expired. Please Login Again.");
			return "sessionExpire";
		}
		return "categoryCreation";
	}
	 
	//---------------------------Block for ajax call to get all category name---------------
	 
	public String checkCategoryAvailability()
	{
		try 
		{
			String newCatName=formBean.getNewCatName();
			String resultResponse="";
			PrintWriter out=response.getWriter();
			String getResponse=dao.getAvailability(newCatName);
			System.out.println("getResponse---"+getResponse);
			resultResponse=getResponse;
			out.print(resultResponse);
		} 
		catch (Exception e) 
		{
			request.setAttribute("message","Process aborted due to Technical Failure.");
			System.out.println("Exception in CategoryCreationAction");
			System.out.println(e.toString());
		}
		return null;
	}
	 
	//-=----------------------Method to create category---------------
	 
	public String createCategory()
	{
		HttpSession session = request.getSession(true);
		String userId=(String)session.getAttribute("userId");
		String userType=(String)session.getAttribute("adminUserType");
		String UpdateBy=(userType)+userId;
		if(userId==null){
			request.setAttribute("message","Your Login Session has Expired. Please Login Again.");
			return "sessionExpire";
		}
		try {
			String newCatName=formBean.getNewCatName();
			System.out.println("newCatName---"+newCatName);
			String createCategory=dao.createcategory(UpdateBy,newCatName);
			if("success".equalsIgnoreCase(createCategory)){
				request.setAttribute("categoryName",newCatName);
				request.setAttribute("message","Category has been successfully created,Now you can update its commission and service charges.");
			}else{
				request.setAttribute("message","Name Already Exist.");	
			}
		} catch (Exception e) {
			request.setAttribute("message","Process aborted due to Technical Failure.");
			System.out.println("Exception in CategoryCreationAction");
			System.out.println(e.toString());
		}
		return "categoryCreation";	 
	}
	
	//---------------------------------------Block to get all the names of registered category--------------
	 
	public String getExistDetails()
	{
		HttpSession session = request.getSession(true);
		String userId=(String)session.getAttribute("userId");
		if(userId==null) {
			request.setAttribute("message","Your Login Session has Expired. Please Login Again.");
			return "sessionExpire";
		}
		ArrayList<HashMap<String, Object>> getCatDetails=dao.getCatDetails();
		if(getCatDetails.size()>0){
			request.setAttribute("getCatDetails", getCatDetails); 
		}else {
			request.setAttribute("message","No data found.");	
		}
		return "categoryCreation";	  
	}
	
	//-------------------------------block for Recharge  service----------------\
	
	public String getServiceCategoryCommDetail() 
	{
		HttpSession session = request.getSession(true);
		String userId=(String)session.getAttribute("userId");
		if(userId==null) {
			request.setAttribute("message","Your Login Session has Expired. Please Login Again.");
			return "sessionExpire";
		}
		String service=formBean.getService();
		String categoryName=formBean.getCategoryName();
		String actionUpdate=formBean.getUpdateaction();
		try {
			ArrayList<HashMap<String,Object>> getCommDetails=dao.getCommDetails(categoryName,service,actionUpdate);
	    	      
			if(getCommDetails.size()>0){
				request.setAttribute("commDetails",getCommDetails);
				request.setAttribute("commCategoryName",categoryName);
				request.setAttribute("service",service);
				request.setAttribute("actionUpdate",actionUpdate);
			} 
			else {
				request.setAttribute("message","Commission is not set properly for this category and service.");	
			}
		} catch (Exception e) {
			request.setAttribute("message","Process aborted due to Technical Failure.");
			System.out.println("Exception in CategoryCreationAction");
			System.out.println(e.toString());
		}
		request.setAttribute("service",service);
		request.setAttribute("categoryName",categoryName);
		return "categoryCreation";	  
	}
	
	public String updateServiceCategoryCommDetail() 
	{
		HttpSession session = request.getSession(true);
		String userId=(String)session.getAttribute("userId");
		if(userId==null) {
			request.setAttribute("message","Your Login Session has Expired. Please Login Again.");
			return "sessionExpire";
		}
		String service=formBean.getService();
		String categoryName=formBean.getCategoryName();
		String actionUpdate=formBean.getUpdateaction();
		String[] checkOperator=(String[])request.getParameterValues("checkpartial");
		try {
			if(checkOperator==null){
				ArrayList<HashMap<String,Object>> getCommDetails=dao.getCommDetails(categoryName,service,actionUpdate);
				request.setAttribute("commDetails",getCommDetails);
				request.setAttribute("commCategoryName",categoryName);
				request.setAttribute("service",service);
				request.setAttribute("actionUpdate",actionUpdate);
				request.setAttribute("message","No operator is selected for margin update, Please select atleast one operator");	
				return "categoryCreation"; 
			}
			if(service.equalsIgnoreCase("air"))
			{
				ArrayList<String> list=null;
				ArrayList<ArrayList<String>> totallist=new ArrayList<ArrayList<String>>();;
				int totpass=checkOperator.length;
				for(int i=0;i<totpass;i++){
					list=new ArrayList<String>();
					String value=checkOperator[i];
					list.add(value);
					String sector=(String)request.getParameter("sec"+value);
					list.add(sector);
					String disbasic=(String)request.getParameter("disb"+value);
					list.add(disbasic);
					String disYQ=(String)request.getParameter("disyq"+value);
					list.add(disYQ);
					String disgros=(String)request.getParameter("disg"+value);
					list.add(disgros);
					String disrs=(String)request.getParameter("disrs"+value);
					list.add(disrs);
					String markup=(String)request.getParameter("markup"+value);
					list.add(markup);
					String ccharge=(String)request.getParameter("ccharge"+value);
					list.add(ccharge);
					String errorcomm=(String)request.getParameter("errorcomm"+value);
					list.add(errorcomm);
					totallist.add(list);
				}
				ArrayList<String> listopr=null;
				for(int j=0;j<totallist.size();j++){
					listopr=totallist.get(j);
					dao.updateCategoryMarginAir(categoryName,(String)listopr.get(0),(String)listopr.get(1),new Float((String)listopr.get(2)),new Float((String)listopr.get(3)),new Float((String)listopr.get(4)),new Float((String)listopr.get(5)),new Float((String)listopr.get(6)),new Float((String)listopr.get(7)),new Float((String)listopr.get(8)));
				}
				ArrayList<HashMap<String,Object>> getCommDetails=dao.getCommDetails(categoryName,service,actionUpdate);
				request.setAttribute("commDetails",getCommDetails);
				request.setAttribute("commCategoryName",categoryName);
				request.setAttribute("service",service);
				request.setAttribute("actionUpdate",actionUpdate);
				request.setAttribute("message","<font color=\"red\" style=\"font-family: Trebuchet MS; font-size: 10pt;\">Margin has been update successfully.</font>");	
				return "categoryCreation";
			}
			ArrayList<String> list=null;
			ArrayList<ArrayList<String>> totallist=new ArrayList<ArrayList<String>>();;
			
			int totpass=checkOperator.length;
			for(int i=0;i<totpass;i++){
				list=new ArrayList<String>();
				String value=checkOperator[i];
				String operator=(String)request.getParameter("opr"+value);
				list.add(operator);
				String margin=(String)request.getParameter("margin"+value);
				list.add(margin);
				String mark=(String)request.getParameter("mark"+value);
				list.add(mark);
				String subService=(String)request.getParameter("ser"+value);
				list.add(subService);
				String mservice=(String)request.getParameter("mser"+value);
				list.add(mservice);
				totallist.add(list);
			}
			ArrayList<String> listopr=null;
			for(int j=0;j<totallist.size();j++){
				listopr=totallist.get(j);
				String operator=listopr.get(0);
				String margin=listopr.get(1);
				String mark=listopr.get(2);
				String subService=listopr.get(3);
				String mService=listopr.get(4);
				dao.updateCategoryMargin(categoryName,mService,subService,margin,mark,operator,actionUpdate);
			}
			ArrayList<HashMap<String,Object>> getCommDetails=dao.getCommDetails(categoryName,service,actionUpdate);
			request.setAttribute("commDetails",getCommDetails);
			request.setAttribute("commCategoryName",categoryName);
			request.setAttribute("service",service);
			request.setAttribute("actionUpdate",actionUpdate);
			request.setAttribute("message","Margin has been update successfully.");	
			return "categoryCreation";
		} catch (Exception e) {
			request.setAttribute("message","Process aborted due to Technical Failure.");
			System.out.println("Exception in CategoryCreationAction");
			System.out.println(e.toString());
		}
		request.setAttribute("service",service);
		request.setAttribute("categoryName",categoryName);
		return "categoryCreation";	  
	}
	 
	public String categoryAssignment() 
	{
		HttpSession session = request.getSession(true);
		String userId=(String)session.getAttribute("userId");
		if(userId==null) {
			request.setAttribute("message","Your Login Session has Expired. Please Login Again.");
			return "sessionExpire";
		}
		try {
			String action=request.getParameter("action");
			if (action.equals("view"))
			{
				ArrayList<HashMap<String, Object>> getCatDetails=dao.getCatDetails();
				if(getCatDetails.size()>0){
					ArrayList<ArrayList<Object>> getWlClinetDetails=dao.getWLClientDetails();
					request.setAttribute("getWlClinetDetails", getWlClinetDetails); 
					request.setAttribute("getCatDetails", getCatDetails); 
					return "categoryAssign";	
				}
				else 
				{
					request.setAttribute("message","No category available for assign .Kindly create category before assign.");
					return "categoryAssign";	
				}
			}
			else
			{
				String[] clientIdList=(String[])request.getParameterValues("checkclient");
				if(clientIdList==null)
				{
					ArrayList<HashMap<String, Object>> getCatDetails=dao.getCatDetails();
					ArrayList<ArrayList<Object>> getWlClinetDetails=dao.getWLClientDetails();
					request.setAttribute("getWlClinetDetails", getWlClinetDetails); 
					request.setAttribute("getCatDetails", getCatDetails);
					request.setAttribute("message","Please select at least Client Id for Assign New Category.");
					return "categoryAssign";
				}
				String newcatagory=(String)request.getParameter("categoryName");
				int total=clientIdList.length;
				for(int i=0;i<total;i++)
				{
					String ClientId=(String)clientIdList[i];
					dao.updateClinetCatcategory(newcatagory,ClientId);
				}
				ArrayList<HashMap<String, Object>> getCatDetails=dao.getCatDetails();
				ArrayList<ArrayList<Object>> getWlClinetDetails=dao.getWLClientDetails();
				request.setAttribute("getWlClinetDetails", getWlClinetDetails); 
				request.setAttribute("getCatDetails", getCatDetails);
				request.setAttribute("message","New Category has been successfully assign.");
				return "categoryAssign";
			}
		} 
		catch (Exception e) {
			request.setAttribute("message","Process aborted due to Technical Failure.");
			System.out.println("Exception in CategoryCreationAction");
			System.out.println(e.toString());
			return "categoryAssign";
		}
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response=response;
	}
}
