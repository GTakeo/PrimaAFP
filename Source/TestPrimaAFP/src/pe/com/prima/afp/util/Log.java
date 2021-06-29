package pe.com.prima.afp.util;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Log {
    private static boolean logToFile;
    private static boolean logToConsole;
    private static boolean logToDatabase;
    private static boolean logMessage;
    private static boolean logWarning;
    private static boolean logError;

    private static Logger logger;
    
    private static Properties propertiesJDBC;
    private static Properties properties;
    

    @SuppressWarnings("null")
	public Log(boolean logToFileParam, boolean logToConsoleParam, boolean logToDatabaseParam,
                boolean logMessageParam, boolean logWarningParam, boolean logErrorParam) {
        logger = Logger.getLogger("MyLog");
        logError = logErrorParam;
        logMessage = logMessageParam;
        logWarning = logWarningParam;
        logToDatabase = logToDatabaseParam;
        logToFile = logToFileParam;
        logToConsole = logToConsoleParam;
        
        try {
        	Util util = new Util();
			propertiesJDBC = util.propertiesJDBC();
			properties = util.properties();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

	public static void LogMessage(String messageText, boolean message, boolean warning, boolean error) throws Exception {
		messageText = messageText.trim();
		
        if (messageText == null || messageText.length() == Constante.CERO) {
            return;
        }
        if (!logToConsole && !logToFile && !logToDatabase) {
            throw new Exception("Invalid configuration");
        }
        if ((!logError && !logMessage && !logWarning) || (!message && !warning && !error)) {
            throw new Exception("Error or Warning or Message must be specified");
        }

        Connection connection = null;
        Properties connectionProps = new Properties();
        connectionProps.put(Constante.DB_USER, propertiesJDBC.getProperty("jdbc.username"));
        connectionProps.put(Constante.DB_PASSWORD, propertiesJDBC.getProperty("jdbc.password"));

        connection = DriverManager.getConnection(propertiesJDBC.getProperty("jdbc.url"), connectionProps);

        int logType = Constante.CERO;
        
        if (message && logMessage) {
            logType = Constante.LOG_SUCCESS;
        }

        if (error && logError) {
            logType = Constante.LOG_ERROR;
        }

        if (warning && logWarning) {
            logType = Constante.LOG_WARNING;
        }

        Statement stmt = connection.createStatement();

        String newMessageText = Constante.EMPTY;
        
        File logFile = new File(properties.getProperty("logFileFolder"));
        if (!logFile.exists()) {
            logFile.createNewFile();
        }

        FileHandler fh = new FileHandler(properties.getProperty("logFileFolder"));
        ConsoleHandler ch = new ConsoleHandler();
        
        Level level = null;
        
        if (message && logMessage) {
            newMessageText =  "message -" + DateFormat.getDateInstance(DateFormat.LONG).format(new Date()) +"- "+ messageText;
            level = Level.INFO;
        }
        
        if (warning && logWarning) {
            newMessageText = "warning -" + DateFormat.getDateInstance(DateFormat.LONG).format(new Date()) + "- "+ messageText;
            level = Level.WARNING;
        }
        
        if (error && logError) {
            newMessageText = "error -" + DateFormat.getDateInstance(DateFormat.LONG).format(new Date()) + "- "+ messageText;
            level = Level.SEVERE;
        }
       
        if(logToFile) {
            logger.addHandler(fh);
			logger.log(level, newMessageText);
        }

        if(logToConsole) {
            logger.addHandler(ch);
            logger.log(level, newMessageText);
        }
        if(logToDatabase) {
            stmt.executeUpdate("insert into Log_Values (MESSAGE,MESSAGE_TYPE) VALUES ('" + newMessageText + "', " + String.valueOf(logType) + ")");
        }
    }
}
