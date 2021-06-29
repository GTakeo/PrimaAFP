package test;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import pe.com.prima.afp.util.Log;

public class TestPrimaAFPTest {

	private static Log log = null;
	
	@Before
	public void before() {
	    boolean logToFile = true;
	    boolean logToConsole = true;
	    boolean logMessage = true;
	    boolean logWarning = true;
	    boolean logError = true;
	    boolean logToDatabase = true;
				
	    log = new Log(logToFile,logToConsole,logMessage,logWarning,logError,logToDatabase );
	}
	
	@Test
	public void sucessText() throws Exception {
	    Log.LogMessage("JUnit Test - Success text message", true,false , false);
	    assertTrue(true);
	}
	
	@Test
	public void warningText() throws Exception {
	    Log.LogMessage("JUnit Test - Warning text message", false,true , false);
	    assertTrue(true);

	}
	
	@Test
	public void errorText() throws Exception {

	    Log.LogMessage("JUnit Test - Error text message", false,false , true);
	    assertTrue(true);
	}

}
