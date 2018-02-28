package ngat.mail;

import java.util.ArrayList;

public class EmailSendingTestMain
{
	public static void main (String[] args) {
		System.out.println("Started");
		
		ArrayList recpients = new ArrayList();
			EmailRecipient recipient = new EmailRecipient("neil.clay@lagrangepoint.co.uk",  EmailRecipient.TYPE_TO);
		recpients.add(recipient);
		
		StringBuffer body = new StringBuffer();
			body.append("line 1");
			body.append("line 2");
			body.append("line 3");
			
		Email email = new Email(recpients, "test of service", body);
		
		System.err.println("sendEmail = " + new EmailSender().send(email));
	}
}