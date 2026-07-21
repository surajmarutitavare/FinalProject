

package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Logger logger =
            LogManager.getLogger(ConfigReader.class);

    static Properties prop;

    static {
        try {
            logger.info("Loading config.properties file");

            prop = new Properties();
            String path = System.getProperty("user.dir") + "/src/main/resources/config.properties";

            FileInputStream fis =
                    new FileInputStream(path);

            prop.load(fis);

            logger.info("Configuration loaded successfully");

        } catch (Exception e) {

            logger.error("Failed to load config.properties", e);
        }
    }

    public static String getProperty(String key) {

        String value = prop.getProperty(key);

        logger.info("Property Read : {} = {}", key, value);

        return value;
    }
}