package ngat.web;

import ngat.util.logging.BogstanLogFormatter;
import ngat.util.logging.ConsoleLogHandler;
import ngat.util.logging.LogManager;
import ngat.util.logging.Logger;

public class LoggerUtil {

	public static final String TRACE_LOGGER_NAME = "TRACE";
	public static final String ERROR_LOGGER_NAME = "ERROR";
	
	public static void setUpLoggers() {
		
		ConsoleLogHandler console = new ConsoleLogHandler(new BogstanLogFormatter());
		console.setLogLevel(Logger.ALL);
		
		Logger traceLogger = LogManager.getLogger(TRACE_LOGGER_NAME);
		traceLogger.setLogLevel(Logger.ALL);	
		traceLogger.addHandler(console);
		
		Logger errorLogger = LogManager.getLogger(ERROR_LOGGER_NAME);
		errorLogger.setLogLevel(Logger.ALL);	
		errorLogger.addHandler(console);
	}
}
