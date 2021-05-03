package com.statedistrict;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

 public class MyListener implements ServletContextListener  {

	
	
	 HashMap <String,ArrayList<String>> StateDistrictList=null;

	
	
	@SuppressWarnings("unchecked")
	@Override
	public void contextDestroyed(ServletContextEvent arg) 
	{
		 
		    ServletContext cnt=arg.getServletContext();
				
		       System.out.println("checkin status");
		    
		      StateDistrictList=(HashMap<String,ArrayList<String>>)cnt.getAttribute("districtData");
			  if(StateDistrictList!=null)
				  
			  {
				System.out.println("===checkin status inside myListener===");

				  StateDistrictList=null;
				  cnt.removeAttribute("districtData");
				}
		
	}


	@Override
	public void contextInitialized(ServletContextEvent arg) {
		
		try{
			 ServletContext cnt=arg.getServletContext();
			 
			 StateDistrictDetailsDao StateDistrictDetailsdao=new StateDistrictDetailsDao();
			 StateDistrictList=StateDistrictDetailsdao.getStateDistrict();
			 //System.out.print("=====StateDistrictList is.... "+StateDistrictList);
			 cnt.setAttribute("districtData",StateDistrictList);
			
		 }
	       catch(Exception ex){
		 }
	}
}
