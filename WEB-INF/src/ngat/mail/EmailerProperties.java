package ngat.mail;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Properties;

public class EmailerProperties extends Properties{
	
	public static final String BASE_DIR 											= "/etc/reactive_time_mailer";
	private static final String PROPERTIES_FILE_PATH 					= BASE_DIR + "/server.configuration";
																																			//e.g.:
	public static final String KEY_MAIL_TRANSPORT_PROTOCOL	= "mail.transport.protocol";	//smtp
	public static final String KEY_MAIL_SMTP_HOST                 	= "mail.smtp.host";			//mail.astro.livjm.ac.uk
	public static final String KEY_MAIL_SMTP_USER                   	= "mail.smtp.user";			//eng
	public static final String KEY_MAIL_SMTP_AUTH                   	= "mail.smtp.auth";			//eng
	public static final String KEY_MAIL_FROM               					= "mail.from";					//ltsupport_astronomer@astro.livjm.ac.uk
	public static final String KEY_MAIL_DEBUG               				= "mail.debug";					//true
	
	public static final String TRUE 																			= new Boolean(true).toString();
	public static final String FALSE 																		= new Boolean(false).toString();
	
	public static EmailerProperties instance;
	
	public static EmailerProperties getInstance() {
		if (instance == null) {
			try {
				instance = new EmailerProperties();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return instance;
	}
	
	private void hardCodeProperties() {
		this.setProperty(KEY_MAIL_TRANSPORT_PROTOCOL, "smtp");
		this.setProperty(KEY_MAIL_SMTP_HOST, "mail.astro.livjm.ac.uk");
		this.setProperty(KEY_MAIL_SMTP_USER, "eng");
		this.setProperty(KEY_MAIL_SMTP_AUTH, "eng");
		this.setProperty(KEY_MAIL_FROM, "ltsupport_astronomer@astro.livjm.ac.uk");
		this.setProperty(KEY_MAIL_DEBUG, "true");
	}
	
	private EmailerProperties() throws IOException {
		hardCodeProperties();
		//super(getPropertiesFromFile(PROPERTIES_FILE_PATH));
	}
	
	private static Properties getPropertiesFromFile(String sfp) throws IOException {
		Properties properties = new Properties();
		FileInputStream in = new FileInputStream(sfp);
		properties.load(in);
		return properties;
	}
	
	public void debugShowProperties(PrintWriter out) {
		out.println();
		Enumeration keysE = this.keys();
		String s = this.getClass().getName() +"[";
		boolean hadElements = false;
		while (keysE.hasMoreElements()) {
			Object key = keysE.nextElement();
			Object value = this.get(key);
			s += key + ":" + value +", ";
			hadElements = true;
		}
		
		if (hadElements) {
			s = s.substring(0, s.length()-2);
		}
		
		s += "]";
		out.println(s);
		out.flush();
	}
}
