package ngat.mail;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Properties;

/**
 * An instance of this class is used to store properties used to configure the JavaMail mailer.
 * @author cjm
 */
public class EmailerProperties extends Properties
{
	
	public static final String BASE_DIR 					= "/etc/reactive_time_mailer";
	private static final String PROPERTIES_FILE_PATH 		= BASE_DIR + "/server.configuration";
																																			//e.g.:
	public static final String KEY_MAIL_TRANSPORT_PROTOCOL	= "mail.transport.protocol";	//smtp
	public static final String KEY_MAIL_SMTP_HOST           = "mail.smtp.host";				//telescope.ljmu.ac.uk
	public static final String KEY_MAIL_SMTP_USER           = "mail.smtp.user";				//eng
	public static final String KEY_MAIL_SMTP_AUTH           = "mail.smtp.auth";				//eng
	public static final String KEY_MAIL_FROM               	= "mail.from";					//ltsupport_astronomer@ljmu.ac.uk
	public static final String KEY_MAIL_DEBUG               = "mail.debug";					//true
	
	public static final String TRUE 						= new Boolean(true).toString();
	public static final String FALSE 						= new Boolean(false).toString();
	
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
	
	/**
	 * Constructor. Load the mail configuration properties from the properties file PROPERTIES_FILE_PATH.
	 * @throws IOException Thrown if the property file cannot be loaded.
	 * @see #getPropertiesFromFile
	 * @see #PROPERTIES_FILE_PATH
	 */
	private EmailerProperties() throws IOException 
	{
		super(getPropertiesFromFile(PROPERTIES_FILE_PATH));
	}
	
	/**
	 * Method to load a set of properties from a properties file.
	 * @param sfp A string containing the filename of a valid proeprties file to load.
	 * @return The routine returns a set of loaded Properties on success.
	 * @throws IOException The method throws an IOException if the properties cannot be loaded from the properties file.
	 */
	private static Properties getPropertiesFromFile(String sfp) throws IOException {
		Properties properties = new Properties();
		FileInputStream in = new FileInputStream(sfp);
		properties.load(in);
		return properties;
	}
	
	public void debugShowProperties(PrintWriter out) {
		out.println();
		Enumeration keysE = this.propertyNames();
		String s = this.getClass().getName() +"[";
		boolean hadElements = false;
		while (keysE.hasMoreElements()) {
			String key = keysE.nextElement().toString();
			String value = this.getProperty(key).toString();
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
