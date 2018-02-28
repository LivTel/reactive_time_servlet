package ngat.mail;

import java.io.*;
import java.util.*;

public class Email
{	
	public List recipients;
	public String subject;
	public StringBuffer body;
	
	public Email(List recipients, String subject, StringBuffer body) {
		this.recipients = recipients;
		this.subject = subject;
		this.body = body;
	}
	
}