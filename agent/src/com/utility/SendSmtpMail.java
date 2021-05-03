package com.utility;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

import java.lang.String;


public class SendSmtpMail
{
	Logger logger=Logger.getLogger(SendSmtpMail.class);

	public static String  sendSSLMessage(String recipients[], String subject,String message1,String from) throws MessagingException
	{
		boolean debug = true;
		try {
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

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setContent(message1,"text/html");

			InternetAddress addressTo[] = new InternetAddress[recipients.length];
			for(int i = 0; i < recipients.length; i++)
				addressTo[i] = new InternetAddress(recipients[i]);


			message.setRecipients(javax.mail.Message.RecipientType.TO, addressTo);

			message.setRecipients(Message.RecipientType.TO, addressTo);

			message.setSubject(subject);
			//message.setText(message1);


			Transport.send(message);


		} catch (MessagingException e) {
			e.printStackTrace();
		}


		return "MailSuccesfullysend";


	}

}