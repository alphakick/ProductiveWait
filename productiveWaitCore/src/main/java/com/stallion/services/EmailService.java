package com.stallion.services;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;


public class EmailService {
	
	final static Logger logger = Logger.getLogger(EmailService.class);
	
	private static Properties  props = null;
	
	static {		
		props = new Properties();
		FileInputStream in;
		try {
			in = new FileInputStream("/Users/sumeetkhurana/git/LocalProductiveWaitRepository/productiveWaitCore/src/main/resources/email.properties");
			
			props.load(in);
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	public void sendEmail(String toEmailAddress) {
		logger.debug("Sending email to :" + toEmailAddress);
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(props.getProperty("mail.sender.username"), props.getProperty("mail.sender.password"));
			}
		  });
		
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(props.getProperty("mail.sender.emailaddress")));
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(toEmailAddress));
			message.setSubject("Testing Subject");
			message.setText("Testing our Application");
			Transport.send(message);
			logger.debug("Email Sent");
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void sendSecureEmail(String toEmailAddress) {
		logger.debug("Sending email to :" + toEmailAddress);
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
					return new PasswordAuthentication("username","password");
				}
			});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("from@no-spam.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse("to@no-spam.com"));
			message.setSubject("Testing Subject");
			message.setText("Dear Mail Crawler," +
					"\n\n No spam to my email, please!");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
