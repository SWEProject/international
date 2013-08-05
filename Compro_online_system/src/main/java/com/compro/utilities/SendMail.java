/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.compro.utilities;

/**
 *
 * @author Admin
 */
//import java.util.Properties;
 
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.Message.RecipientType;
//import javax.mail.internet.AddressException;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
 
    import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class SendMail {
 

  

	private String from;
	private String to;
	private String subject;
	private String text;
 
	public SendMail(String from, String to, String subject, String text){
		this.from = from;
		this.to = to;
		this.subject = subject;
		this.text = text;
	}
 
	   public void send() {
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
                        return new PasswordAuthentication("cs425.intl@gmail.com", "mummum123");
                    }
                });

                try {

                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(from));
                    message.setRecipients(Message.RecipientType.TO,
                            InternetAddress.parse(to));
                    message.setSubject(subject);
                    message.setText(text);

                    System.out.println("before Done");
                    Transport.send(message);

                    System.out.println("Done");

                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
    }
//		Properties props = new Properties();
//		props.put("mail.smtp.host", "smtp.gmail.com");
//		props.put("mail.smtp.port", "465");
//                System.out.println("SendMail from address =" + from + " to address = " + to);
// 
//		Session mailSession = Session.getDefaultInstance(props);
//		Message simpleMessage = new MimeMessage(mailSession);
// 
//		InternetAddress fromAddress = null;
//		InternetAddress toAddress = null;
//		try {
//			fromAddress = new InternetAddress(from);
//			toAddress = new InternetAddress(to);
//		} catch (AddressException e) {
//			// TODO Auto-generated catch block
//		}
// 
//		try {
//			simpleMessage.setFrom(fromAddress);
//			simpleMessage.setRecipient(RecipientType.TO, toAddress);
//			simpleMessage.setSubject(subject);
//			simpleMessage.setText(text);
// 
//			Transport.send(simpleMessage);			
//		} catch (MessagingException e) {
//			// TODO Auto-generated catch block
//		}		
//	}
}
