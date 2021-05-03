package com.common;

import java.security.Security;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import com.sun.net.ssl.internal.ssl.Provider;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
//import java.io.*;
import java.lang.String;


public class SendSmtpMaild {

	//set Smtp hostname
	/* String SMTP_HOST_NAME = "smtp.gmail.com";
       //set Smtp port no
       String SMTP_PORT = "465";
       //SMTP related events class name
       String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
       //for Email content
       String emailMsgTxt;
       //for email subject
       String emailSubjectTxt;
       //for login id
       String login_id;
       //for login password
       String login_pass;
       // set from address
       String emailFromAddress;
       // set recipient address
       String[] sendTo ={"inmcc.rdb@gmail.com"};*/

	// capture connect event
	//public static String SMTP_HOST_NAME = "mail.eservices.net.in";
	//public static String SMTP_HOST_NAME = "mail.corporatetravels.in";
	//set Smtp port no
	//public static String SMTP_PORT = "25";

	/*public static String SMTP_HOST_NAME = "smtp.gmail.com";

    	public static String SMTP_PORT = "465";
	public static  String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";*/

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
				return new PasswordAuthentication("care@payenething.in","11223344");
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("support@myaadhaarshila.com"));


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

		/*Properties props = new Properties();
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
                                      //return new PasswordAuthentication("noreply@eservices.net.in","admin@yahoo.123");
                            		return new PasswordAuthentication("support@myaadhaarshila.com","aadhaarshila@123");
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
                    msg.setContent(message, "text/html");
                    try {
                    Transport.send(msg);
                    //set security provider of smtp

                    Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
                    sendStatus="MailSuccesfullysend";
                    }
                    catch(Exception e) {
                    	System.out.println("Exception in sending mail  "+e);


                    }*/
		//set message for user
		//System.out.println("Sucessfully Sent mail to All Users");
		return "MailSuccesfullysend";
	}

	public static String sendSSLMessageBCC(String recipients[], String ccrecipients[], String subject, String message1, String from)
			throws MessagingException
	{
		boolean debug = true;
		String sendStatus = null;

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
				return new PasswordAuthentication("care@payenething.in","11223344");
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("support@myaadhaarshila.com"));


			InternetAddress addressTo[] = new InternetAddress[recipients.length];
			for(int i = 0; i < recipients.length; i++)
				addressTo[i] = new InternetAddress(recipients[i]);

			InternetAddress addressCC[] = new InternetAddress[ccrecipients.length];
			for(int i = 0; i < ccrecipients.length;i++)
			{
				addressCC[i] = new InternetAddress(ccrecipients[i]);
			}


			message.setRecipients(javax.mail.Message.RecipientType.CC, addressCC);
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