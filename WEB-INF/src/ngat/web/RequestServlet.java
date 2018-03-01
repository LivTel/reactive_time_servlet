package ngat.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ngat.mail.Email;
import ngat.mail.EmailRecipient;
import ngat.mail.EmailSender;
import ngat.mail.EmailerProperties;
import ngat.util.logging.LogManager;
import ngat.util.logging.Logger;

import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;

public class RequestServlet extends HttpServlet {
	
	static Logger traceLogger, errorLogger;
	
	private RequestProperties properties = RequestProperties.getInstance();
	
	private static final String HUMAN_WORD = "reactive";
	
	public void init() throws ServletException {
		LoggerUtil.setUpLoggers();
		
		traceLogger = LogManager.getLogger(LoggerUtil.TRACE_LOGGER_NAME);
		errorLogger = LogManager.getLogger(LoggerUtil.ERROR_LOGGER_NAME);
	
		traceLogger.log(5, RequestServlet.class.getName(), this.getClass().getName() +".init()");
	}
	
	//invoked by web-service
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		
		traceLogger.log(5, RequestServlet.class.getName(), "RECEIVED REACTIVE TIME REQUEST");
		
		ReactiveTimeRequestBean reactiveTimeRequestBean = new ReactiveTimeRequestBean();
		RequestUtil.populateBean(reactiveTimeRequestBean, request);
	
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e1) {
			e1.printStackTrace();
			return;
		}
		
		if (!reactiveTimeRequestBean.getHumanWord().equalsIgnoreCase(HUMAN_WORD)) {
			out.println("The request was not submitted. If you're not a robot, select the browser back button and try again.");
			return;
		}
		
		//remove characters that can be used in cross-site scripting attacks
		reactiveTimeRequestBean.cleanXSSCharacters();
		
		//test for correctly entered captcha text
		String remoteAddr = request.getRemoteAddr();
		
		/*
		//recaptcha code - stopped working, no Java version now
		
        ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
        reCaptcha.setPrivateKey("6LcXjPASAAAAAK_NScXnW80mdTKZsYkaMmY9Nu0M");
		String challenge = request.getParameter("recaptcha_challenge_field");
        String uresponse = request.getParameter("recaptcha_response_field");
        
        ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(remoteAddr, challenge, uresponse);

        if (!reCaptchaResponse.isValid()) {
          traceLogger.log(5, RequestServlet.class.getName(), "The Captcha text entered was incorrect.");
          out.println("The Captcha text entered was incorrect. Click 'back' to try again.\n");
          traceLogger.log(5, RequestServlet.class.getName(), "... REQUEST FAILED HUMANITY TEST.");
          traceLogger.log(5, RequestServlet.class.getName(), reactiveTimeRequestBean.toString());
          return;
        }
		*/
        
		if (!reactiveTimeRequestBean.tagIsValid()) {
			traceLogger.log(5, RequestServlet.class.getName(), "... user didn't select a TAG. No emails will be sent.");
			out.println("No TAG was selected so NO EMAILS WERE SENT\n");
			out.println("Press the browser back button in order to try again\n");
			return;
		}
		
		if (!reactiveTimeRequestBean.shouldSendEmail()) {
			traceLogger.log(5, RequestServlet.class.getName(), "... request is a test. No emails will be sent.");
			//
			out.println(this.getClass().getName() + ".doPost()\n");
			out.println("NO EMAILS WERE SENT\n");
			out.println("THE REQUEST RECEIVED HAD CONTENT SIZE: " + request.getContentLength() + "\n");
			out.println("PARAMETERS are:\n\n");
			debugShowRequest(request);
			
			//print out details of request
			
			return;
		}
		
		List recipients; //list of EmailRecipient objects
		recipients = getEmailRecipientsForTag(reactiveTimeRequestBean.getTag());
		traceLogger.log(5, "Starting email sending service");
		
		String subject = "URGENT - A Liverpool Telescope reactive time application has been made";
		
		String m;
		boolean allSucceeded = true;

		traceLogger.log(5, "Sending email to " + debugShowRecipients(recipients));
		
		StringBuffer body = new StringBuffer();
		body.append(reactiveTimeRequestBean.formattedForEmail());
		
		Email email = new Email(recipients, subject, body);
		EmailSender emailSender = new EmailSender();
		
		boolean success = false;
		try {
			success = emailSender.send(email);
			if (success) {
				traceLogger.log(5, "Send of email was successful.");
			} else {
				traceLogger.log(5, "THE ATTEMPT FAILED");
				allSucceeded = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			allSucceeded = false;
		}
		
		
		if (allSucceeded) {
			m = "Summary: The email was sent successfully.";
			out.println(m);
			traceLogger.log(5, m);
			out.println("The submission was successful. Many thanks.");
		} else {
			out.println("Summary: Not all the emails were sent successfully.");
			out.println("Please contact the support astronomer at:");
			out.println("ltsupport_astronomer@ljmu.ac.uk");
			out.println("and give an approximate time of this failure");
			out.println("We apologise for the inconvenience");
		}
		
	}
	
	private void debugShowRequest(HttpServletRequest request){
			Enumeration parameterNamesEnum = request.getParameterNames();
			System.out.println("debugShowRequest()");
			System.out.println("... human user IP address : " + request.getRemoteAddr());
			while (parameterNamesEnum.hasMoreElements()) {
				String parameterName = (String) parameterNamesEnum.nextElement();
				String parameter = (String) request.getParameter(parameterName);
				System.out.println("... " + parameterName + " : " +parameter);
			}
	}
	
	private ArrayList getEmailRecipientsForTag(String tagName) {
		
		String fileName = EmailerProperties.BASE_DIR + "/" + tagName + ".emails";
		
		ArrayList fileLines = getFileLines(new File(fileName));
		ArrayList emailRecipients = new  ArrayList();
		
		Iterator fli = fileLines.iterator();
		
		//overkill error handlng
		while (fli.hasNext()) {
			String emailAddress = ""; String type = "";
			boolean foundEmail = false; boolean foundType = false;
			
			String line = (String) fli.next();
			StringTokenizer st = new StringTokenizer(line, ",");
			try{
				if (st.hasMoreTokens()) {
					foundEmail = true;
					emailAddress = st.nextToken();
				}
				if (st.hasMoreTokens()) {
					foundType = true;
					type = st.nextToken();
				}
				if (foundEmail && foundType) {
					EmailRecipient emailRecipient = new EmailRecipient(emailAddress, type);
					emailRecipients.add(emailRecipient);
				}
			} catch(NoSuchElementException nsee) {
				traceLogger.log(5, nsee);
			}
		}
		return emailRecipients;
	}
	
	private String debugShowRecipients(List recipients) {
		String s = "";
		Iterator ri =  recipients.iterator();
		while (ri.hasNext()) {
			EmailRecipient recipient = (EmailRecipient) ri.next();
			s += recipient + ", ";
		}
		s = s.substring(0, s.length() - 1);
		return s;
	}
	
	private ArrayList getFileLines(File aFile) {
		ArrayList lines = new ArrayList();
		BufferedReader input = null;
		try {
			input = new BufferedReader( new FileReader(aFile) );
			String line = null; //not declared within while loop
			while (( line = input.readLine()) != null){
				lines.add(line);
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (input!= null) {
					input.close();
				}
			}
			catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return lines;
	}
}
