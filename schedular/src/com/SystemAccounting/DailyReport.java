package com.SystemAccounting;

import java.io.File;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class DailyReport implements Job {

	
	public static final String filePath = "/usr/Trandata/";
	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {



		System.out.println("DailyReport.execute() Schedular start....");
		java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
		String filename="Finance_Report_"+sqlDate+".xls";
		try{
			
			String filePathwithFileName=filePath+filename;
			
			System.out.println("DailyReport.execute() :: "+filePathwithFileName);
			
			File f=new File(filePathwithFileName);
			if(!f.exists())
				f.createNewFile();
				
			DailyReportDao dailyReportDao=new DailyReportDao();
			dailyReportDao.	dailyReport(filePathwithFileName);	

			
			String recipients[]={"mukeshkjindal72@gmail.com"};

			
			String subject="Daily Report of "+sqlDate.toString()+"";

			System.out.println("subject >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>.."+subject);

			String Message="<html xmlns=\"http://www.w3.org/1999/xhtml\">"+
					"<head>"+
					"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\" />"+
					"<title>Daily Report</title>"+

			"</head><body>"+
			"<table width=70% border=0 align=center cellpadding=0 cellspacing=0 class=maintext>"+
			"<tr><td><div align=right></div></td></tr>"+
			"<tr><td>"+
			"<tr>"+
			"<td>&nbsp;</td>"+
			" <td>&nbsp;</td>"+
			" <td>&nbsp;</td>"+
			"</tr>"+
			"</div><p align=justify>&nbsp;</p>" +
			"<p align=justify>Warm regards,</p><p align=justify>SmartKinda</p><p align=justify></p>" +
			"<p></p><p></p></td></tr></table></body></html>";
			String mailStatus=SendMailAttachmentDailyReport.sendAttachMessage(recipients, subject, Message,"support@softsolutionzone.in",filePathwithFileName,filename);
			//String mailStatus=SendMailAttachmentAESDailyReport.sendAttachMessage(recipients, subject, Message,WriteFile.from,filePath+"AgentDetails.rar","AgentDetails.rar");
			System.out.println("Mail Status : "+mailStatus);


		}catch(Exception e){
			e.printStackTrace();
		}

	}

}
