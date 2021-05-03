package com.common;


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

public class SendMailAttachmentWithCC 
{
	    
	public static String  sendAttachMessage(String recipients[],String ccrecipients[], String subject,String message,String from,String filepath1,String filepath2,String filepath3) throws MessagingException
	{
		boolean debug = true;
		String sendStatus=null;
		Properties props = new Properties();
    	props.put("mail.smtp.host", "smtp.gmail.com");
    	props.put("mail.smtp.socketFactory.port", "465");
    	props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
    	props.put("mail.smtp.auth", "true");
    	props.put("mail.smtp.port", "465");
		Session session = Session.getDefaultInstance(props,new javax.mail.Authenticator()
		{
			//capyure password authentication
			protected PasswordAuthentication getPasswordAuthentication()
			{
				return new PasswordAuthentication("support@softsolutionzone.in","");
			}
		}                                           );
		// enable debugging ... all output is sent to System.out
		session.setDebug(debug);
		// create new message type instance
		Message msg = new MimeMessage(session);
		InternetAddress addressFrom = new InternetAddress(from);
		msg.setFrom(addressFrom);
		InternetAddress[] addressCC = new InternetAddress[ccrecipients.length];
		for (int i = 0; i < recipients.length; i++)
		{
			addressCC[i] = new InternetAddress(ccrecipients[i]);
		}
		msg.setRecipients(Message.RecipientType.BCC,addressCC); 
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
		messageBodyPart1.setFileName(filepath1);
		multipart.addBodyPart(messageBodyPart1);
		FileDataSource fileDataSource2 =new FileDataSource(filepath2);
		messageBodyPart2.setDataHandler(new DataHandler(fileDataSource2));
		messageBodyPart2.setFileName(filepath2);
		multipart.addBodyPart(messageBodyPart2);
		FileDataSource fileDataSource3 =new FileDataSource(filepath3);
		messageBodyPart3.setDataHandler(new DataHandler(fileDataSource3));
		messageBodyPart3.setFileName(filepath3);
		multipart.addBodyPart(messageBodyPart3);
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
}


	 


