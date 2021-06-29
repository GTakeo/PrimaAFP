package pe.com.prima.afp.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Util {

	public Properties propertiesJDBC() throws IOException {
	    Properties prop = new Properties();
	    String propFileName = "jdbc.properties";
	    
	    InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
	    
	    if (inputStream != null) {
			prop.load(inputStream);
		} else {
			throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
		}
	    
		return prop;
	}
	
	public Properties properties() throws IOException {
	    Properties prop = new Properties();
	    String propFileName = "TestPrimaAFP.properties";
	    
	    InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
	    
	    if (inputStream != null) {
			prop.load(inputStream);
		} else {
			throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
		}
	    
		return prop;
	}
	
}
