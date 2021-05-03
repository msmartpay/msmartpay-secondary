package com.SystemAccounting;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import com.pendingTransaction.SMSJob;
import com.pendingTransaction.UpdateEkoAEPSPendingTransaction;
import com.pendingTransaction.UpdateWINUSPendingTransaction;

public class QuartzUserAccountTrigger {
	

    public  Scheduler schedular() throws Exception
    {
		  JobDetail jDetail;
		  CronTrigger cronTrigger;
		  SchedulerFactory sf = new StdSchedulerFactory();
		  Scheduler scheduler = sf.getScheduler();
		  scheduler.start();

		  //jDetail = new JobDetail("Job1","UpdatePendingTran",UpdateRechPendingTransaction.class);
		  //cronTrigger = new CronTrigger("cronTrigger1","UpdateRechPendingTransaction"," 0 0/5 * * * ?");
		  //scheduler.scheduleJob(jDetail, cronTrigger);
		  
		  /*jDetail = new JobDetail("Job2","UpdateHermesPendingTran",UpdateHermesPendingTransaction.class);
		  cronTrigger = new CronTrigger("cronTrigger2","UpdateHermesPendingTransaction"," 0 0/5 * * * ?");
		  scheduler.scheduleJob(jDetail, cronTrigger);*/
		  
		  jDetail = new JobDetail("Job1","UpdateAePSPendingTran",UpdateEkoAEPSPendingTransaction.class);
		  cronTrigger = new CronTrigger("cronTrigger1","UpdateEkoAEPSPendingTransaction"," 0 0/3 * * * ?");
		  scheduler.scheduleJob(jDetail, cronTrigger);
		  
		  jDetail = new JobDetail("Job3","SMARTTran",UpdateWINUSPendingTransaction.class);
		  cronTrigger = new CronTrigger("cronTrigger3","SMARTTransaction"," 0 0/1 * * * ?");
		  scheduler.scheduleJob(jDetail, cronTrigger);
		  
		  
		  jDetail = new JobDetail("Job4","DailyReportSMARTTran",DailyReport.class);
		  cronTrigger = new CronTrigger("cronTrigger4","DailyReportSMARTTransaction","01 02 07 * * ?");
		  scheduler.scheduleJob(jDetail, cronTrigger);
		  
		  
		  jDetail = new JobDetail("Job2","SMSJob",SMSJob.class);
		  cronTrigger = new CronTrigger("cronTrigger2","SMSJobClear","0/30 * * * * ?");
		  //scheduler.scheduleJob(jDetail, cronTrigger);
		  return scheduler;
    	   	
        }
    
        public static void main(String as[])
    	{
        	try
        	{
        		QuartzUserAccountTrigger qt=new QuartzUserAccountTrigger();
        		qt.schedular();
        	}
        	catch(Exception ex)
        	{}
    	}
	}
