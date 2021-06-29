package org.main;

import java.util.HashMap;
import java.util.Map;

import org.acme.Demo;

public class TestPrimaAFP_S1 {
	
	static Demo demo = null;
	
	public static void main(String[] args) {
		try {
			initializeValues();
			
			Demo.LogMessage("Seccess text message", true,false , false);
						
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	static void initializeValues(){
		
	    boolean logToFile = true;
	    boolean logToConsole = true;
	    boolean logMessage = true;
	    boolean logWarning = true;
	    boolean logError = true;
	    boolean logToDatabase = true;
		
		Map<String, String> properties = new HashMap<String,String>();
		
		properties.put("logFileFolder", "C:/logTest");
		properties.put("userName", "bd_primaAFPdev");
		properties.put("password", "123456");
		properties.put("dbms", "oracle:thin");
		properties.put("serverName", "localhost");
		properties.put("portNumber", "1521");
		
		demo = new Demo(logToFile,logToConsole,logMessage,logWarning,logError,logToDatabase,properties );
	}

}
