package ngat.mail;

public class EmailRecipient {

	public static final String TYPE_TO = "to";
	public static final String TYPE_CC = "cc";
	
	public String emailAddress;
	public String recipientType;
	
	public EmailRecipient(String emailAddress, String recipientType) {
		this.emailAddress = emailAddress;
		this.recipientType = recipientType;
	}
	
	public String toString() {
		String s = "";
		s += this.getClass().getName() + "[";
		s += "emailAddress=" + emailAddress + ", ";
		s += "recipientType=" + recipientType + "]";
		return s;
	}
}
