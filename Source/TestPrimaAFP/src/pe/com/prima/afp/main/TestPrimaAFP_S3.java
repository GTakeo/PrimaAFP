package pe.com.prima.afp.main;

import java.io.IOException;
import java.util.Properties;

import pe.com.prima.afp.util.Log;
import pe.com.prima.afp.util.Util;

public class TestPrimaAFP_S3 {
	private static Properties properties;
	private static Log log = null;
	
	public static void main(String[] args) {
		try {
						
			initializeValues();
			
			Log.LogMessage("Success text message", true,true , false);
						
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	static void initializeValues(){
		
        try {
        	Util util = new Util();
			properties = util.properties();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
		
	    boolean logToFile = Boolean.parseBoolean(properties.getProperty("logToFile"));
	    boolean logToConsole = Boolean.parseBoolean(properties.getProperty("logToConsole"));
	    boolean logMessage = Boolean.parseBoolean(properties.getProperty("logMessage"));
	    boolean logWarning = Boolean.parseBoolean(properties.getProperty("logWarning"));
	    boolean logError = Boolean.parseBoolean(properties.getProperty("logError"));
	    boolean logToDatabase = Boolean.parseBoolean(properties.getProperty("logToDatabase"));
				
	    log = new Log(logToFile,logToConsole,logMessage,logWarning,logError,logToDatabase );
		
	}
	


}
