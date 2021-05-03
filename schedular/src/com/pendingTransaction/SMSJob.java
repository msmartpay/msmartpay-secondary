
package com.pendingTransaction;

import java.util.HashMap;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.msmart.util.SmsSendDao;

public class SMSJob  implements Job {


	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {

		System.out.println("Job Started");
		clearPendingSMS();
		System.out.println("Job End");
	}


	public void clearPendingSMS() {

		try {

			SmsSendDao smsDao=new SmsSendDao();

			String smsVendor=smsDao.getVendor("SMS");

			List<HashMap<String, String>> smsList=smsDao.getPendingSMS(0, "SMS");
			if(smsList!=null && smsList.size()>0) {
				for (int i = 0; i < smsList.size(); i++) {
					HashMap<String,String> sms=smsList.get(i);
					smsDao.updateSMSStatus(1,Long.valueOf(sms.get("id")));

					System.out.println("sms : "+sms);
					String message=sms.get("message");
					String mobile=sms.get("destination");
					if(message.contains("Dear Partner, Your updated password is")) {

						message="Dear Partner, Your username is "+mobile+" and password is "+message+". Its confidential. Please change your password. Regards, Arpit Enterprises";
					
						smsDao.msg91(sms.get("destination"), sms.get("message"),"1007292453545896111");
					}else {

						if("msg91".equalsIgnoreCase(smsVendor)) {
							smsDao.msg91(sms.get("destination"), sms.get("message"));
						}else if("sms2".equalsIgnoreCase(smsVendor)) {
							smsDao.arnaventerprises(sms.get("destination"), sms.get("message"));
						}else {

						}
					}
				}
			}


		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
