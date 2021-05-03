package com.controlPanel.profileMgt.viewHierarchy;

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
 * Created By  : Manoj
 * Created Date : 4 july 2013
 * Created Matter : To separate the logic of view Hierarchy.
 */
public  class ViewHierarchyAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{
	
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	public String execute()
	{
		
		ViewHierarchyDao dao=new ViewHierarchyDao();
		Map session = ActionContext.getContext().getSession();		
		try {
			String userId = (String) session.get("userId");
			if (userId == null) {
				request.setAttribute("message","Your Login Session has Expired. Please Login Again.");
				return "sessionExpire";
			}	
			String status=request.getParameter("status");
			String type=request.getParameter("type");
			String hierarchy=request.getParameter("hierarchy");
			int id=Integer.parseInt(request.getParameter("userId"));
			System.out.println("Status is :"+status);
			System.out.println("Type is :"+type);
			System.out.println("Hierarchy is : "+hierarchy);
			System.out.println("Id is : "+id);
			System.out.println("ViewHierarchyAction Class And Type is :  "+type);
			ArrayList<HashMap<String,String>> data=new ArrayList<HashMap<String,String>>();
			if(type.equalsIgnoreCase("adminUser")){
				if(hierarchy.equalsIgnoreCase("upper")){
					request.setAttribute("id", id);
					request.setAttribute("type", type);
					request.setAttribute("message", "Requested Data Does Not Exists.");
					return "adminHierarchy";
				}else if(hierarchy.equalsIgnoreCase("lower")){
					//System.out.println("-----MDS LOWER HIERARCHY-------");
					data=dao.getMdsHierarchy(id,status,type);
//					System.out.println(data);
					int size=data.size();
					if(size==0){
						request.setAttribute("message", "No Data Found.");
					}
					request.setAttribute("mdsSearchList",data);
					request.setAttribute("id", id);
					request.setAttribute("type", type);
					return "mdsHierarchy";
				}
			}
			else if(type.equalsIgnoreCase("mds")){
				if(hierarchy.equalsIgnoreCase("upper")){
					//System.out.println("-----MDS UPPER HIERARCHY-------");
					data=dao.getPortalHierarchy(id,status,type);
					int size=data.size();
					//System.out.println("size in class is "+size );
					if(size==0){
						request.setAttribute("message", "No Data Found.");
					}
					request.setAttribute("id", id);
					request.setAttribute("type", type);
					request.setAttribute("portalSearchList", data);
					return "adminHierarchy";
				}else if(hierarchy.equalsIgnoreCase("lower")){
					//System.out.println("-----MDS LOWER HIERARCHY-------");
					data=dao.getdsHierarchy(id,status,type);
					int size=data.size();
					if(size==0){
						request.setAttribute("message", "No Data Found.");
					}
					request.setAttribute("distributorSearchList",data);
					request.setAttribute("id", id);
					request.setAttribute("type", type);
					return "dsHierarchy";
				}
			}
			else if(type.equalsIgnoreCase("ds")){
				if(hierarchy.equalsIgnoreCase("upper")){
					//System.out.println("-------DS UPPER HIERARCHY -------------");
					data=dao.getMdsHierarchy(id,status,type);
					int size=data.size();
					if(size==0){
						request.setAttribute("message", "No Data Found.");
					}
					request.setAttribute("id", id);
					request.setAttribute("type", type);
					request.setAttribute("mdsSearchList",data);
					return "mdsHierarchy";
				}else if(hierarchy.equalsIgnoreCase("lower")){
					System.out.println("-------DS LOWER HIERARCHY -------------");
					data=dao.getAgentHierarchy(id,status,type);
					int size=data.size();
					System.out.println(size);
					System.out.println(data);
					if(size==0){
						request.setAttribute("message", "No Data Found.");
					}
					request.setAttribute("agentSearchList",data);
					request.setAttribute("id", id);
					request.setAttribute("type", type);
					return "agentHierarchy";
				}
			}
			else if(type.equalsIgnoreCase("agent")){
				if(hierarchy.equalsIgnoreCase("upper")){
					//System.out.println("-------AGENT UPPER HIERARCHY -------------");
					data=dao.getdsHierarchy(id,status,type);
					int size=data.size();
					if(size==0){
						request.setAttribute("message", "No Data Found.");
					}
					request.setAttribute("id", id);
					request.setAttribute("type", type);
					request.setAttribute("distributorSearchList",data);
					return "dsHierarchy";
				}
				if(hierarchy.equalsIgnoreCase("lower")){
					//System.out.println("-------AGENT LOWER HIERARCHY -------------");
					request.setAttribute("id", id);
					request.setAttribute("type", type);
					request.setAttribute("message", "Requested Data Does Not Exists.");
					return "dsHierarchy";
				}
			}
		}catch (Exception e) {	
			request.setAttribute("message", "Process aborted due to Technical Failure.");
			System.out.println("Exception in viewHierarchy ");
			System.out.println(e.toString());
			return "err";
		}
		return "err";	
	}
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;

	}
	public void setServletResponse(HttpServletResponse response) {
		this.response=response;
 	}
}
