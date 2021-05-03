package com.common;

/**
 *  Created By 	: Arshad
 *  Created Date : 19/04/2013
 */
import java.util.*;

import javax.mail.*;
import javax.activation.*;
import javax.mail.internet.*;

public final class SendMailAttachmentCashReciept {
	
	public static String SMTP_HOST_NAME = "mail.eservices.net.in";
	public static String SMTP_PORT = "25";

	public static  String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
	    
	public static String  sendAttachMessage(String recipients[], String subject,String message,String from,String filepath1,String filePath2) throws MessagingException
	{
		boolean debug = true;
		String sendStatus=null;

		Properties props = new Properties();
    	props.put("mail.smtp.host", "smtp.gmail.com");
    	props.put("mail.smtp.socketFactory.port", "465");
    	props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
    	props.put("mail.smtp.auth", "true");
    	props.put("mail.smtp.port", "465");

    	Session session = Session.getDefaultInstance(props,
		new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("no-reply@smartkinda.com","");
			}
		});
		
		session.setDebug(debug);
		
		Message msg = new MimeMessage(session);
		InternetAddress addressFrom = new InternetAddress(from);
		msg.setFrom(addressFrom);
		InternetAddress[] addressTo = new InternetAddress[recipients.length];
		for (int i = 0; i < recipients.length; i++)
		{
			addressTo[i] = new InternetAddress(recipients[i]);
		}
		
		msg.setRecipients(Message.RecipientType.TO, addressTo);
		
		
		msg.setSubject(subject);
		
	                   
	                    
		BodyPart messageBodyPart1 = new MimeBodyPart();
		BodyPart messageBodyPart2 = new MimeBodyPart();
		BodyPart messageBodyPart3 = new MimeBodyPart();

		Multipart multipart = new MimeMultipart();
		                   
		MimeBodyPart htmlPart = new MimeBodyPart();
		htmlPart.setContent(message, "text/html");
		multipart.addBodyPart(htmlPart);
   	                    
		FileDataSource fileDataSource1 =new FileDataSource(filepath1);
		messageBodyPart1.setDataHandler(new DataHandler(fileDataSource1));
		messageBodyPart1.setFileName(filePath2);
	                    
	                   
	                    
		multipart.addBodyPart(messageBodyPart1);
	                    
		msg.setContent(multipart);
		try {
			Transport.send(msg);
			
	        sendStatus="MailSuccesfullysend";
		}
		catch(Exception e) {
	        e.printStackTrace();
		}
		return sendStatus;
	}
}


	 


