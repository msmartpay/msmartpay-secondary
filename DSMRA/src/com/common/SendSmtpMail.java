package com.common;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class SendSmtpMail
  {
	
    public static void sendSSLMessage(String recipients[], String subject,String message,String from) throws MessagingException
    {
            boolean debug = true;

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
                    	return new PasswordAuthentication("no-reply@smartkinda.com","");
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
            Transport.send(msg);
            //set security provider of smtp
            //Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            //set message for user
            System.out.println("Sucessfully Sent mail to All Users");
    }

 }