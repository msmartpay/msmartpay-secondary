package com.statedistrict;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class StateDistrictListAction extends Action  {	
	
	

	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception {
		
	PrintWriter out=response.getWriter();	
	
	try
	{ 
		
		HashMap <String,ArrayList<String>> StateDistrictList=null;
		@SuppressWarnings("unused")
		String state=(String)request.getParameter("state");
		//System.out.println("+++++++++++inside StateDistrictListAction class and value of state"+state);
		@SuppressWarnings("unused")
		String district=(String)request.getParameter("district");
		//System.out.println(">>>>>>>inside StateDistrictListAction class and value of district id is"+district);
		ServletContext cnt=getServlet().getServletContext();
		String OutPut="";
		StateDistrictList=(HashMap<String,ArrayList<String>>)cnt.getAttribute("districtData");
		//System.out.println("<<<<<<<<inside StateDistrictListAction class and the list is"+StateDistrictList);
		
		ArrayList<String> districtList=(ArrayList)StateDistrictList.get(state);
		
		System.out.println("------district list in arrylist are "+districtList);
		
		
		if(districtList==null){
		OutPut="invalid";
		}
		else 
		{
		
			String districtInitial="<select name='"+district+"' class=\"style2\"  style=\"font-size:13px;padding:5px;height:30px;width:249px;\"><option value=\"select\">Select</option>";
			StringBuffer districtDetails=new StringBuffer();
			
			
			for(int i=0;i<districtList.size();i++)
			{ 
				String districtname=(String)districtList.get(i);
				 
				System.out.println("district name is"+districtname);
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
		
		System.out.println("Exception in StateDistrictListAction is "+ex);
	}
	
	return null;
}

	
	


}
