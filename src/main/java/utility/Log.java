package utility;

import org.apache.log4j.Logger;

public class Log {
 private final static Logger logger = Logger.getLogger(Logger.class);

 	public static Logger getLogger() {
 		return logger;
 	}
}