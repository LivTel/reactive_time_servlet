package ngat.mail;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Properties;

import com.sun.mail.smtp.SMTPTransport;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender
{
	private static Session session = null;
	private EmailerProperties properties;
	
	public EmailSender() 
	{
		properties = EmailerProperties.getInstance();
		
		session = Session.getDefaultInstance(properties);
		
		//no SMTP debug output (we get stacks of socket output if it is)
		//session.setDebug(false);
	}
		
	public boolean send(Email email) 
	{
		SMTPTransport transport = null;
		boolean sendSuccess = false;
		
		try 
		{
			System.out.println("Sending email");
			System.out.println("  session=" + session);
			properties.debugShowProperties(new PrintWriter(System.out));
			MimeMessage message = new MimeMessage(session);
			
			//set the 'from' property from the settings file
			String mailFrom = properties.getProperty(EmailerProperties.KEY_MAIL_FROM);
			System.out.println("... mailFrom=" + mailFrom);
			
			message.setFrom(new InternetAddress(mailFrom));
			Iterator ri = email.recipients.iterator();
			while (ri.hasNext()) {
				EmailRecipient emailRecipient = (EmailRecipient) ri.next();
				System.out.println("... adding recipient: " + emailRecipient);
				RecipientType recipientType;
				if (emailRecipient.recipientType.equals(EmailRecipient.TYPE_TO)) {
					recipientType = Message.RecipientType.TO;
				} else {
					recipientType = Message.RecipientType.CC;
				}
				message.addRecipient(recipientType, new InternetAddress(emailRecipient.emailAddress));
			}
			
			message.setSubject(email.subject);
			message.setText(email.body.toString());//I've deleted the code that checked for a body

			message.saveChanges();
			
			System.out.println("message: " + message);

			String smtpServer = properties.getProperty("mail.smtp.host");
			String smtpUsername = properties.getProperty("mail.smtp.user");
			String smtpPassword = properties.getProperty("mail.smtp.password");
			System.out.println("Getting transport from session.");
			transport = (SMTPTransport) session.getTransport();
			System.out.println("Connecting using server:"+smtpServer+" username:"+smtpUsername);
			transport.connect(smtpServer, smtpUsername, smtpPassword);
			System.out.println("Sending message to "+message.getAllRecipients());
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			
			sendSuccess = true;
			
			System.err.println("Send successful");
		} catch(Exception e) {
			sendSuccess = false;
			
			System.err.println("    !SEND FAILED!");
			System.err.println("    EmailSender.send() throws Exception " + e);
			e.printStackTrace();
		}

		return sendSuccess;
	}

}