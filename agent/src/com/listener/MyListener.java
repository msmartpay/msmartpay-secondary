/*	Updated By		 : Arshad
 * 	Updated Date	 :2012/12/28
 * 	Updated Matter	 : optimization of panel
 * 
 */

package com.listener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyListener implements ServletContextListener  {
	 
	HashMap <String,ArrayList<String>> StateDistrictList=null;	
	public void contextDestroyed(ServletContextEvent arg) 
	{
		ServletContext cnt=arg.getServletContext();
		
		StateDistrictList=(HashMap)cnt.getAttribute("districtData");
		if(StateDistrictList!=null)
		{
			StateDistrictList=null;
			cnt.removeAttribute("districtData");
		}
	}
	public void contextInitialized(ServletContextEvent arg) {
		
		try{
			ServletContext cnt = arg.getServletContext();
			StateDistrictDetailsDao StateDistrictDetailsdao=new StateDistrictDetailsDao();
			StateDistrictList=StateDistrictDetailsdao.getStateDistrict();
			//System.out.print("StateDistrictList is "+StateDistrictList);
			cnt.setAttribute("districtData",StateDistrictList);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}	
	}
 }
