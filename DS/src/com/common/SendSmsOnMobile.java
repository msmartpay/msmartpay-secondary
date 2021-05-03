package com.common;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.report.form.SendMessage;


public class SendSmsOnMobile
{
	Logger logger = Logger.getLogger(SendSmsOnMobile.class);
	LogWriter log=new LogWriter();
	
	public static void sendMobileSmsSMSZONE(String mobileNo,String message)
	{
		Session session=null;   
		Transaction txn=null;
		try{
			
			SendMessage sendMessage=new SendMessage();
			sendMessage.setMessage(message);
			sendMessage.setDestination(mobileNo);
			sendMessage.setType("SMS");
			sendMessage.setStatus(0);
			
			session = HibernateSession.getSessionFactory().openSession();
			txn=session.beginTransaction();
			session.save(sendMessage);
			
			txn.commit();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				session.flush();
				session.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in getPortalIdList");
				System.out.println(e.toString());
			}
		}
	}

	/*
	 * public String sendMobileSmsSMSZONE(String mobileNo,String message,String
	 * smsId,String smsPassword) {
	 * 
	 * PostMessageURL postmessageurl=new PostMessageURL(); try {
	 * message=message.replaceAll(" ", "%20"); message=message.replaceAll("&",
	 * "and");
	 * 
	 * String
	 * url="http://api.msg91.com/api/sendhttp.php?country=91&sender="+PropertyFile.
	 * SMS_SENDER_ID+"&route=4&mobiles="+mobileNo+"&authkey="+PropertyFile.
	 * SMS_AUTH_KEY+"&message="+message;
	 * postmessageurl.postMessage(url,"text/xml","GET");
	 * 
	 * 
	 * } catch (Exception e) {
	 * 
	 * e.printStackTrace(); } finally {
	 * 
	 * } return message;
	 * 
	 * 
	 * 
	 * } public String sendMobileSmsSMSZONE(String mobileNo,String message) {
	 * 
	 * PostMessageURL postmessageurl=new PostMessageURL(); try {
	 * message=message.replaceAll(" ", "%20"); message=message.replaceAll("&",
	 * "and");
	 * 
	 * String
	 * url="http://api.msg91.com/api/sendhttp.php?country=91&sender="+PropertyFile.
	 * SMS_SENDER_ID+"&route=4&mobiles="+mobileNo+"&authkey="+PropertyFile.
	 * SMS_AUTH_KEY+"&message="+message;
	 * postmessageurl.postMessage(url,"text/xml","GET");
	 * 
	 * 
	 * } catch (Exception e) {
	 * 
	 * e.printStackTrace(); } finally {
	 * 
	 * } return message;
	 * 
	 * 
	 * 
	 * }
	 */

}
