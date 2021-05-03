package com.common;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.formBean.dynamicDetails.SendMessage;

public class SendSmsOnMobile
{
	
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
	
	public static void main(String[] args) {
		sendMobileSmsSMSZONE("9716025028", "Test");
	}
	/*
	 * public static void sendMobileSmsSMSZONE(String mobileNo,String message) { try
	 * { message=message.replaceAll(" ", "%20"); message=message.replaceAll("&",
	 * "and"); String
	 * url="http://api.msg91.com/api/sendhttp.php?country=91&sender="+PropertyFile.
	 * SMS_SENDER_ID+"&route=4&mobiles="+mobileNo+"&authkey="+PropertyFile.
	 * SMS_AUTH_KEY+"&message="+message;
	 * PostMessageUrl.postMessage(url,"text/xml","GET"); } catch (Exception e) {
	 * System.out.println(e.toString()); } finally {
	 * 
	 * } }
	 * 
	 * public void sendMobileSmsSMSZONE(String mobileNo,String message,String
	 * senderId,String password) { try { message=message.replaceAll(" ", "%20");
	 * message=message.replaceAll("&", "and"); String
	 * url="http://api.msg91.com/api/sendhttp.php?country=91&sender="+PropertyFile.
	 * SMS_SENDER_ID+"&route=4&mobiles="+mobileNo+"&authkey="+PropertyFile.
	 * SMS_AUTH_KEY+"&message="+message;
	 * PostMessageUrl.postMessage(url,"text/xml","GET"); } catch (Exception e) {
	 * System.out.println(e.toString()); } finally {
	 * 
	 * } }
	 */
}
