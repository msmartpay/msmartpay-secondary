package com.listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyListener implements ServletContextListener  
{
	HashMap <String,ArrayList<String>> StateDistrictList=null;
	Map<String, String> domAirportNames = null;	
	
	@Override
	public void contextDestroyed(ServletContextEvent arg) 
	{
		ServletContext cnt=arg.getServletContext();
		
		StateDistrictList=(HashMap<String,ArrayList<String>>)cnt.getAttribute("districtData");
		if(StateDistrictList!=null)
		{
			StateDistrictList=null;
			cnt.removeAttribute("districtData");
		}
	}
	@Override
	public void contextInitialized(ServletContextEvent arg) 
	{
		try
		{
			ServletContext cnt = arg.getServletContext();
			StateDistrictDetailsDao StateDistrictDetailsdao=new StateDistrictDetailsDao();
			StateDistrictList=StateDistrictDetailsdao.getStateDistrict();
			
			cnt.setAttribute("districtData",StateDistrictList);

			 // Get airport names from DB
			domAirportNames = new HashMap<String, String>();
			// domAirportNames = DBConnectorBusinesDelegater.getAirportNames();
			//System.out.println("^^^^^^^^^^^^^^^^^^^     "  + domAirportNames);
			cnt.setAttribute("domAirportNames", domAirportNames);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
