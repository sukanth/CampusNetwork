package com.campusnetwork.controllers;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {
	
	public static void send(String to,String Subject,String Body) {

        final String username = "theucmcampusnetwork@gmail.com";
        final String password = "1234@1234";

        Properties props = new Properties();  
        props.setProperty("mail.transport.protocol", "smtp");     
        props.setProperty("mail.host", "smtp.gmail.com");  
        props.put("mail.smtp.auth", "true");  
        props.put("mail.smtp.port", "465");  
        props.put("mail.debug", "true");  
        props.put("mail.smtp.socketFactory.port", "465");  
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");  
        props.put("mail.smtp.socketFactory.fallback", "false");  
        
        Session session = Session.getDefaultInstance(props,  
        new javax.mail.Authenticator() {
           protected PasswordAuthentication getPasswordAuthentication() {  
           return new PasswordAuthentication(username,password);  
       }  
       });  

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("theucmcampusnetwork@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(to));
            message.setSubject(Subject);
            message.setContent(Body, "text/html; charset=utf-8");

            Transport.send(message);


        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
