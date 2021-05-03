package com.common;


import java.io.File;
import java.security.Security;
import java.util.*;
import javax.mail.*;
import javax.activation.*;
import javax.mail.internet.*;








public class SendMailAttachmentTEPReport {

	//public static String SMTP_HOST_NAME = "mail.eservices.net.in";
	//public static String SMTP_HOST_NAME = "mail.corporatetravels.in";
	//set Smtp port no
	//public static String SMTP_PORT = "25";

	public static String SMTP_HOST_NAME = "smtp.gmail.com";

	public static String SMTP_PORT = "465";
	public static  String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

	public static String  sendAttachMessage(String recipients[], String subject,String message,String from,String filepath1) throws MessagingException
	{
		boolean debug = true;
		String sendStatus=null;

		Properties props = new Properties();



		props.put("mail.smtp.host", SMTP_HOST_NAME);
		props.put("mail.smtp.auth", "true");
		props.put("mail.debug", "true");
		props.put("mail.smtp.port", SMTP_PORT);
		props.put("mail.oyazen.socketFactory.port", SMTP_PORT);
		props.put("mail.oyazen.socketFactory.class", SSL_FACTORY);
		props.put("mail.oyazen.socketFactory.fallback", "false");


		// capture session event
		Session session = Session.getDefaultInstance(props,new javax.mail.Authenticator()
		{
			//capyure password authentication
			protected PasswordAuthentication getPasswordAuthentication()
			{
				return new PasswordAuthentication("care@payenething.in","11223344");

			}
		}                                           );
		// enable debugging ... all output is sent to System.out
		session.setDebug(debug);
		// create new message type instance
		Message msg = new MimeMessage(session);
		InternetAddress addressFrom = new InternetAddress(from);
		msg.setFrom(addressFrom);



		InternetAddress[] addressTo = new InternetAddress[recipients.length];






		for (int i = 0; i < recipients.length; i++)
		{
			addressTo[i] = new InternetAddress(recipients[i]);
		}

		msg.setRecipients(Message.RecipientType.TO, addressTo);

		// set subject
		msg.setSubject(subject);
		//set content


		BodyPart messageBodyPart1 = new MimeBodyPart();
		BodyPart messageBodyPart2 = new MimeBodyPart();
		BodyPart messageBodyPart3 = new MimeBodyPart();

		Multipart multipart = new MimeMultipart();

		MimeBodyPart htmlPart = new MimeBodyPart();
		htmlPart.setContent(message, "text/html");
		multipart.addBodyPart(htmlPart);
		FileDataSource fileDataSource1 =new FileDataSource(filepath1);
		messageBodyPart1.setDataHandler(new DataHandler(fileDataSource1));
		messageBodyPart1.setFileName("TEP ACCOUNT REPORT");
		multipart.addBodyPart(messageBodyPart1);

		msg.setContent(multipart);
		try {
			Transport.send(msg);
			//set security provider of smtp


			sendStatus="MailSuccesfullysend";
		}
		catch(Exception e) {
			System.out.println("Exception in sending mail  "+e);


		}

		//set message for user
		//System.out.println("Sucessfully Sent mail to All Users");
		return sendStatus;
	}

	public String  sendAttachMessage(String recipients[], String subject,String message,String from,String filepath,String filename) throws MessagingException
	{
		boolean debug = true;
		String sendStatus=null;
		Properties props = new Properties();
		props.put("mail.smtp.host", SMTP_HOST_NAME);
		props.put("mail.smtp.auth", "true");
		props.put("mail.debug", "true");
		props.put("mail.smtp.port", SMTP_PORT);
		props.put("mail.oyazen.socketFactory.port", SMTP_PORT);
		props.put("mail.oyazen.socketFactory.class", SSL_FACTORY);
		props.put("mail.oyazen.socketFactory.fallback", "false");

		// capture session event
		Session session = Session.getDefaultInstance(props,new javax.mail.Authenticator()
		{
			//capyure password authentication
			protected PasswordAuthentication getPasswordAuthentication()
			{
				//return new PasswordAuthentication("reports@eservices.net.in","admin@yahoo.321.com");
				return new PasswordAuthentication("support@myaadhaarshila.com","aadhaarshila@123");
				//return new PasswordAuthentication("public@corporatetravels.in","public");
				//return new PasswordAuthentication("no-reply@travelepoint.in","#compark10@");

			}
		}                                           );
		// enable debugging ... all output is sent to System.out
		session.setDebug(debug);
		// create new message type instance
		Message msg = new MimeMessage(session);
		InternetAddress addressFrom = new InternetAddress(from);
		msg.setFrom(addressFrom);

		InternetAddress[] addressTo = new InternetAddress[recipients.length];

		for (int i = 0; i < recipients.length; i++)
		{
			addressTo[i] = new InternetAddress(recipients[i]);
		}

		msg.setRecipients(Message.RecipientType.TO, addressTo);

		// set subject
		msg.setSubject(subject);
		//set content

		BodyPart messageBodyPart1 = new MimeBodyPart();
		BodyPart messageBodyPart2 = new MimeBodyPart();
		BodyPart messageBodyPart3 = new MimeBodyPart();

		Multipart multipart = new MimeMultipart();

		MimeBodyPart htmlPart = new MimeBodyPart();
		htmlPart.setContent(message, "text/html");
		multipart.addBodyPart(htmlPart);

		FileDataSource fileDataSource1 =new FileDataSource(filepath);
		messageBodyPart1.setDataHandler(new DataHandler(fileDataSource1));
		messageBodyPart1.setFileName(filename);

		multipart.addBodyPart(messageBodyPart1);

		msg.setContent(multipart);
		try {
			Transport.send(msg);
			//set security provider of smtp


			sendStatus="MailSuccesfullysend";
		}
		catch(Exception e) {
			System.out.println("Exception in sending mail  "+e);
		}

		//set message for user
		//System.out.println("Sucessfully Sent mail to All Users");
		return sendStatus;
	}
	/* public static void main(String args[])
	    {
	       String recipient[]={"rati.sharma@commsoft.in"};
	       String from="prachi.sharma@commsoft.in";
	       String subject="Hello multiple files attached";
	       String message="hellllllllllloooooooooooo";
	       String filepath1="D://filesToSend/Key.txt";
	       String filepath2="D://filesToSend/r.txt";
	       String filepath3="D://filesToSend/r.bmp";
	       try {
			String result=sendAttachMessage(recipient,subject,message,from,filepath1,filepath2,filepath3);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    }*/



}





