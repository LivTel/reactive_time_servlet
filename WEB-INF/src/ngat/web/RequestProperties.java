package ngat.web;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import ngat.util.logging.LogManager;
import ngat.util.logging.Logger;

public class RequestProperties extends Properties{
	
	static Logger traceLogger = LogManager.getLogger(LoggerUtil.TRACE_LOGGER_NAME);
	static Logger errorLogger = LogManager.getLogger(LoggerUtil.ERROR_LOGGER_NAME);
	
	public static final String BASE_DIR 																	= "/etc/grb";
	private static final String PROPERTIES_FILE_PATH 											= BASE_DIR + "/server.configuration";
	
																																												//e.g.:
	public static final String ASYNCH_UPDATE_LOG_PATH 									= "asynch.update.log";			// /usr/local/apache-tomcat-5.5.12/logs/asynch_rtml.log
	public static final String CONTACT_NAME_TEST 												= "contact.name.test"; 			//Testing
	public static final String CONTACT_USER_TEST 												= "contact.user.test"; 				//LTOps/LTTest
	public static final String CONTACT_NAME														= "contact.name"; 					//GRB FOLLOWUP
	public static final String CONTACT_USER															= "contact.user"; 					//grb_followup
	public static final String PROJECT_NAME_TEST												= "project.name.test"; 			//LTExpired
	public static final String PROJECT_NAME															= "project.name"; 					//grb_followup 
	public static final String IA_HOST_URL															    = "ia.host.name"; 					//http://150.204.240.8:8080/org_estar_loggingagent/services/NodeAgent
	public static final String IA_PREFIX																	= "ia.prefix"; 							//GRB_Post1hr_
	public static final String TARGET_EQUINOX														= "target.equinox"; 					//2000
	public static final String TARGET_IDENT															= "target.ident"; 						//GRBFollowup
	public static final String TARGET_TYPE															= "target.type"; 						//normal
	public static final String DEVICE_TYPE																= "device.type"; 						//camera
	public static final String DEVICE_SPECTRAL_REGION										= "device.spectral.region";		//optical
	public static final String EXPOSURE_UNITS														= "exposure.units"; 					//seconds
	public static final String EXPOSURE_TYPE														= "exposure.type"; 					//time
	
	public static final String URL_TELESCOPE_IP_FTN											= "telescope.ip.ftn";				//132.160.98.239
	public static final String URL_TELESCOPE_IP_FTS											= "telescope.ip.fts";					//150.203.153.202
	public static final String URL_TELESCOPE_IP_LT												= "telescope.ip.lt";					//161.72.57.3

	public static final String URL_TELESCOPE_POSTFIX_FTN									= "telescope.postfix.ftn";			//:8080/axis/services/NodeAgent
	public static final String URL_TELESCOPE_POSTFIX_FTS									= "telescope.postfix.fts";			//:8080/axis/services/NodeAgent
	public static final String URL_TELESCOPE_POSTFIX_LT									= "telescope.postfix.lt";			//:8080/org_estar_nodeagent/services/NodeAgent

	public static final String URN				 															= "urn";									//urn:/node_agent
	
	public static final String AUTH_USERNAME				 									 	= "auth.username";					//grb_followup
	public static final String AUTH_PASSWORD				 									 	= "auth.password";					//GRBfollowup
	
	public static final String TRUE 																			= new Boolean(true).toString();
	public static final String FALSE 																		= new Boolean(false).toString();
	
	public static RequestProperties instance;
	
	public static RequestProperties getInstance() {
		if (instance == null) {
			try {
				instance = new RequestProperties();
			} catch (IOException e) {
				errorLogger.log(1, RequestProperties.class.getName(), "IOException in loading " + PROPERTIES_FILE_PATH + " no properties loaded!!!");
			}
		}

		return instance;
	}
	
	private RequestProperties() throws IOException {
		super(getPropertiesFromFile(PROPERTIES_FILE_PATH));
	}
	
	private static Properties getPropertiesFromFile(String sfp) throws IOException {
		Properties properties = new Properties();
		FileInputStream in = new FileInputStream(sfp);
		properties.load(in);
		return properties;
	}
	
	public void debugShowProperties() {
		Enumeration keysE = this.keys();
		String s = this.getClass().getName() +"[";
		
		while (keysE.hasMoreElements()) {
			Object key = keysE.nextElement();
			Object value = this.get(key);
			s = key + ":" + value;
		}
		s += "]";
		traceLogger.log(5, RequestProperties.class.getName(), s);
	}
}
