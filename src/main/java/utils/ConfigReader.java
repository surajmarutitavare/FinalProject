package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.util.Properties;

// This class reads configuration settings from a config file
public class ConfigReader {

    // Logger to record what's happening (for debugging)
    private static final Logger logger =
            LogManager.getLogger(ConfigReader.class);

    // A storage object for all the settings/properties from the config file
    static Properties prop;

    // This block runs once when the class is first loaded (automatic setup)

    static {
        try {
            // Record in log that we're starting to read the config file
            logger.info("Loading config.properties file");

            // Create an empty storage object for properties
            prop = new Properties();
            // Build the path to the config file (location where settings are stored)
            String path = System.getProperty("user.dir") + "/src/main/resources/config.properties";

            // Open the config file to read it
            FileInputStream fis =
                    new FileInputStream(path);

            // Load all settings from the file into the 'prop' object
            prop.load(fis);

            // Record in log that everything loaded fine
            logger.info("Configuration loaded successfully");

        } catch (Exception e) {

            // If something goes wrong, record the error in the log
            logger.error("Failed to load config.properties", e);
        }
    }

    // This method retrieves a specific setting value by its name/key
    // Example: getProperty("username") returns "john"
    public static String getProperty(String key) {

        // Get the value for the requested key from properties
        String value = prop.getProperty(key);

        // Record which setting was requested and what value it had
        logger.info("Property Read : {} = {}", key, value);

        // Return the value to whoever asked for it
        return value;
    }
}