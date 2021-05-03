package com.SystemAccounting;


import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class SendMailAttachmentDailyReport {

	public static String SMTP_HOST_NAME = "smtp.gmail.com";
	public static String SMTP_PORT = "465";

	public static  String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

	public static String  sendAttachMessage(String recipients[], String subject,String message,String from,String filepath,String filename) throws MessagingException
	{
		String sendStatus=null;

		try {
			Properties props = new Properties();
			props.put("mail.smtp.host", SMTP_HOST_NAME);
			props.put("mail.smtp.socketFactory.port", SMTP_PORT);
			props.put("mail.smtp.socketFactory.class",SSL_FACTORY);
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", SMTP_PORT);

			Session session = Session.getDefaultInstance(props,new javax.mail.Authenticator()
			{

				protected PasswordAuthentication getPasswordAuthentication()
				{
					return new PasswordAuthentication("support@softsolutionzone.in","supp@09895");
				}
			});
			//session.setDebug(debug);
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



			Multipart multipart = new MimeMultipart();

			MimeBodyPart htmlPart = new MimeBodyPart();
			htmlPart.setContent(message, "text/html");
			multipart.addBodyPart(htmlPart);


			FileDataSource fileDataSource1 =new FileDataSource(filepath);
			BodyPart messageBodyPart1 = new MimeBodyPart();
			messageBodyPart1.setDataHandler(new DataHandler(fileDataSource1));
			messageBodyPart1.setFileName(filename);

			multipart.addBodyPart(messageBodyPart1);

			msg.setContent(multipart);

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



}





