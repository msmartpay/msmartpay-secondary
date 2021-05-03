package com.listener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

 public class MyListener implements ServletContextListener  {

	
	
	 HashMap <String,ArrayList<String>> StateDistrictList=null;
	 Map<String, String> domAirportNames = null;	
	
	@SuppressWarnings("unchecked")
	@Override
	public void contextDestroyed(ServletContextEvent arg) 
	{
		 
		    ServletContext cnt=arg.getServletContext();
		   // System.out.println("we are into DS :::::::::::");
		      StateDistrictList=(HashMap<String,ArrayList<String>>)cnt.getAttribute("districtData");
			  if(StateDistrictList!=null)
				{
				  StateDistrictList=null;
				  cnt.removeAttribute("districtData");
				}
			
			
	}


	@Override
	public void contextInitialized(ServletContextEvent arg) {
		
		try{
			//System.out.println("we are into DS ::::::::::: Getting Information:::::::::::::::::::");
			 ServletContext cnt = arg.getServletContext();
			 StateDistrictDetailsDao StateDistrictDetailsdao=new StateDistrictDetailsDao();
			 StateDistrictList=StateDistrictDetailsdao.getStateDistrict();
			 //System.out.print("StateDistrictList is "+StateDistrictList);
			 cnt.setAttribute("districtData",StateDistrictList);

			 // Get airport names from DB
			 domAirportNames = new HashMap<String, String>();
			// domAirportNames = DBConnectorBusinesDelegater.getAirportNames();
			 //System.out.println("^^^^^^^^^^^^^^^^^^^     "  + domAirportNames);
			 cnt.setAttribute("domAirportNames", domAirportNames);

		 }
	       catch(Exception ex){
		 }
	}
}
