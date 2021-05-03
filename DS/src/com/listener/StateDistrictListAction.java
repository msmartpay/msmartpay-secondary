package com.listener;


import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;


public class StateDistrictListAction implements ServletRequestAware, ServletResponseAware,ServletContextAware
{	
	private HttpServletRequest request;
	private HttpServletResponse response;
	private ServletContext context = null;
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}	
	
	public HttpServletResponse getServletResponse() {
		return response;
	}
	public ServletContext getServletContext() {
		return context;
	}
	public void setServletContext(ServletContext context) {
		this.context = context;
	}
	
	public String execute(){
		
	//PrintWriter out=response.getWriter();	
	
	try
	{ 
		PrintWriter out=response.getWriter();
		HashMap <String,ArrayList<String>> StateDistrictList=null;
		
		String state=request.getParameter("state");
		
		String district=request.getParameter("district");
		 String OutPut="";
		StateDistrictList=(HashMap<String,ArrayList<String>>)context.getAttribute("districtData");
		ArrayList<String> districtList=(ArrayList)StateDistrictList.get(state);
		
		if(districtList==null){
		OutPut="invalid";
		}
		else {
		
		String districtInitial="";
		//"<div id='"+district+"'><select name='"+district+"' class=\"style2\"  style=\"width:275px;\">";
		StringBuffer districtDetails=new StringBuffer();
		
		
		for(int i=0;i<districtList.size();i++)
		{ 
			
			String districtname=(String)districtList.get(i);
			//System.out.println("district name is"+districtname);
			districtDetails.append("<option value=\""+districtname+"\">"+districtname+"</option>");
					
		}
		String districtvalue=districtDetails.toString();
		String districtend="</select>";
        OutPut=districtInitial+districtvalue+districtend;
		}
		System.out.println(OutPut);
		out.print(OutPut);
		
		return null;
	}
	catch(Exception ex){
		
		System.out.println("Exception in StateDistrictListAction is "+ex);
	}
	
	return null;
}

	
		


}
