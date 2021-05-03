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








public class SendMailAttachmentWithCC {

	public static String SMTP_HOST_NAME = "dedrelay.secureserver.net";
	//public static String SMTP_HOST_NAME = "mail.corporatetravels.in";
	//set Smtp port no
	public static String SMTP_PORT = "25";

	//public static String SMTP_HOST_NAME = "smtp.gmail.com";

	//public static String SMTP_PORT = "587";
	public static  String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

	public static String  sendAttachMessage(String recipients[], String subject,String message,String from,String filepath1,String filepath2,String filepath3,String idProofFileName,String addressProofFileName,String businessProofFileName) throws MessagingException

	{

		//  public static String  sendAttachMessage(String recipients[],String ccrecipients[], String subject,String message,String from,String filepath1,String filepath2,String filepath3) throws MessagingException

		// String[]  recipients={"amit.pathak@commsoft.in"};



		boolean debug = true;
		String sendStatus=null;

		Properties props = new Properties();



		props.put("mail.smtp.host", SMTP_HOST_NAME);
		props.put("mail.smtp.auth", "false");
		props.put("mail.debug", "true");
		props.put("mail.smtp.port", SMTP_PORT);
		props.put("mail.oyazen.socketFactory.port", SMTP_PORT);
		props.put("mail.oyazen.socketFactory.class", SSL_FACTORY);
		props.put("mail.oyazen.socketFactory.fallback", "false");


		// capture session event
		Session session = Session.getDefaultInstance(props);                                          
		// enable debugging ... all output is sent to System.out
		session.setDebug(debug);
		// create new message type instance
		Message msg = new MimeMessage(session);
		InternetAddress addressFrom = new InternetAddress(from);
		msg.setFrom(addressFrom);
		/*  InternetAddress[] addressCC = new InternetAddress[ccrecipients.length];

                         for (int i = 0; i < ccrecipients.length; i++)
                         {
                           addressCC[i] = new InternetAddress(ccrecipients[i]);
                          }


                           msg.setRecipients(Message.RecipientType.CC,addressCC); */



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
		messageBodyPart1.setFileName("IDDoc");
		multipart.addBodyPart(messageBodyPart1);
		FileDataSource fileDataSource2 =new FileDataSource(filepath2);
		messageBodyPart2.setDataHandler(new DataHandler(fileDataSource2));
		messageBodyPart2.setFileName("AddressDoc");
		multipart.addBodyPart(messageBodyPart2);
		FileDataSource fileDataSource3 =new FileDataSource(filepath3);
		messageBodyPart3.setDataHandler(new DataHandler(fileDataSource3));
		messageBodyPart3.setFileName("BusinessDoc");
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
	/*   public static void main(String args[])
	    {
	       String recipient[]={"pathak.amit783@gmail.com"};

	       String from="amit.pathak@commsoft.in";
	       String subject="Hello multiple files attached";
	       String message="hellllllllllloooooooooooo";
	       String filepath1="D://filesToSend/Key.txt";
	       String filepath2="D://filesToSend/r.txt";
	       String filepath3="D://filesToSend/b.txt";
	       try {
			String result=sendAttachMessage(recipient,subject,message,from,filepath1,filepath2,filepath3);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   }*/



}





