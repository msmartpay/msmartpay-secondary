package com.AdminScheduler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pendingTransaction.UpdateWINUSPendingTransaction;

public class AdminSchedulerController extends HttpServlet {

	private static final long serialVersionUID = 8303716977167744738L;
	static int threadcount=0;

	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
	{
		StartAdminScheduler sas=null;
		String ip=request.getRemoteAddr();
		System.out.println("Request Ip "+ip);

		String param=request.getParameter("param");
		PrintWriter out=response.getWriter();

		if(ip.equalsIgnoreCase("121.243.151.37") || ip.equalsIgnoreCase("198.57.163.182") || ip.equalsIgnoreCase("198.57.169.16")|| ip.equalsIgnoreCase("162.144.201.20"))
		{
			if(threadcount==0)
			{
				sas=new StartAdminScheduler(param);
				Thread t=new Thread(sas);
				t.start();
				out.println("Scheduler satrted!");
			}
			else
			{
				out.println("Scheduler is already running!");
			}
		}
		else
		{
			out.println("invalid ip address!");
		}
	}

	class StartAdminScheduler implements Runnable {

		String param=null;

		public StartAdminScheduler(String param) {
			this.param=param;
		}

		@Override
		public void run() {

			threadcount++;
			UpdateWINUSPendingTransaction teptxn=null;

			if(param.equalsIgnoreCase("tepscheduler"))
			{
				teptxn=new UpdateWINUSPendingTransaction();
			}
			threadcount--;
		}
	}
}
