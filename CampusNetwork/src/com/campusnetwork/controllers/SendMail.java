package com.campusnetwork.controllers;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Service
public class SendMail {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@RequestMapping(method = RequestMethod.POST)
	public String doSendEmail(String to,String subject,String body) {

		
		mailSender.send(new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws MessagingException {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				message.setFrom("theucmcampusnetwork@gmail.com");
				message.setTo(to);
				message.setSubject(subject);
				message.setText(body, true);
			}
		});
		
		// forwards to the view named "Result"
		return "Result";
	}
	
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
        props.put("mail.smtp.starttls.enable","true"); 
        props.put("mail.smtp.EnableSSL.enable","true");
        
        
        
        System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
        
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
	
	public static void main(String[] args) {
		send("mzubair2310@gmail.com", "Test Mail", "<h1>sending html mail check</h1>");
	}

}
