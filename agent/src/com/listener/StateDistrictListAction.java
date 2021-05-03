package com.listener;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class StateDistrictListAction extends ActionSupport  {	
	Logger logger=Logger.getLogger(StateDistrictListAction.class);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletResponse response;
	private HttpServletRequest request;
	
	@SuppressWarnings("unchecked")
	public String execute()throws Exception {
		
		request=ServletActionContext.getRequest();
		response=ServletActionContext.getResponse();
		PrintWriter out=response.getWriter();	
		try
		{	 
			ServletContext cnt=request.getServletContext();
			HashMap <String,ArrayList<String>> StateDistrictList=null;
			logger.info("StateDistrictListAction class ");
			String state=request.getParameter("state");
			String district=(String)request.getParameter("district");
			
			String OutPut="";
			StateDistrictList=(HashMap<String,ArrayList<String>>)cnt.getAttribute("districtData");
			ArrayList<String> districtList=(ArrayList<String>)StateDistrictList.get(state);
			if(districtList==null){
				OutPut="invalid";
			}
			else {
		
				String districtInitial="<select name='"+district+"' class=\"style2\"  style=\"width:275px;\"><option value=\"select\">--Select District--</option>";
				StringBuffer districtDetails=new StringBuffer();
		
		
				for(int i=0;i<districtList.size();i++)
				{ 
			
					String districtname=(String)districtList.get(i);
					districtDetails.append("<option value=\""+districtname+"\">"+districtname+"</option>");
					
				}
				String districtvalue=districtDetails.toString();
				String districtend="</select>";
				OutPut=districtInitial+districtvalue+districtend;
			}
		
			out.print(OutPut);
		
			return null;
		}
		catch(Exception ex){
			logger.info("Exception in StateDistrictListAction is ");
			ex.printStackTrace();
		}finally{
			try {
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	
		return null;
	}
}
