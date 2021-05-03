package com.common;

import java.security.Security;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
//import java.io.*;
import java.lang.String;


public class SendSmtpMail
{


	// capture connect event
	public static String SMTP_HOST_NAME = "smtp.gmail.com";


	public static String SMTP_PORT = "465";
	public static  String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

	public static String  sendSSLMessage(String recipients[], String subject,String message1,String from) throws MessagingException
	{
		boolean debug = true;
		String sendStatus=null;

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("","");
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("support@softsolutionzone.in"));


			InternetAddress addressTo[] = new InternetAddress[recipients.length];
			for(int i = 0; i < recipients.length; i++)
				addressTo[i] = new InternetAddress(recipients[i]);


			message.setRecipients(javax.mail.Message.RecipientType.TO, addressTo);

			message.setRecipients(Message.RecipientType.TO, addressTo);

			message.setSubject(subject);
			//message.setText(message1);
			message.setContent(message1,"text/html");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}


		return "MailSuccesfullysend";



	}


}