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



}
