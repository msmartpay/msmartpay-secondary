package com.MyContextListener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.quartz.Scheduler;

import com.SystemAccounting.QuartzUserAccountTrigger;

public class SchedulerMyListener implements ServletContextListener{

	@Override

	public void contextDestroyed(ServletContextEvent arg) 
	{
		ServletContext cnt=arg.getServletContext();
		  try
		  {
		        Scheduler sh1=(Scheduler)cnt.getAttribute("schedular");
		        sh1.shutdown();
		  }catch(Exception ex)
		  	{
			  ex.printStackTrace();
		  	}
	     }

	@Override
	public void contextInitialized(ServletContextEvent arg) 
	{
		
		try
		{
			System.out.println("inside MyListener class>>>>>>>>>>>>>>>>>>>>..");
			ServletContext cnt=arg.getServletContext();
			QuartzUserAccountTrigger qat=new QuartzUserAccountTrigger();
			Scheduler qas=qat.schedular();
			cnt.setAttribute("schedular", qas);
		}   
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
	}

}
