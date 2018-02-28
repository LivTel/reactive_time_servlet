package ngat.mail;

import java.util.Iterator;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender
{
	private static Session session = null;
	private Properties properties;
	
	public EmailSender() {
		properties = EmailerProperties.getInstance();
		
		//session = Session.getDefaultInstance(properties, null);
		session = Session.getInstance(properties, null);
		
		//no SMTP debug output (we get stacks of socket output if it is)
		session.setDebug(false);
	}
		
	public boolean send(Email email) {
		Transport transport = null;
		boolean sendSuccess = false;
		
		try {
			System.out.println("Sending email");
			System.out.println("  session=" + session);

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
			
			transport = session.getTransport("smtp");
			transport.connect(); //throws MessagingException if no connection to the internet
			transport.send(message, message.getAllRecipients());
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