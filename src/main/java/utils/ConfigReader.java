//package utils;
//
//import java.io.FileInputStream;
//import java.util.Properties;
//
//public class ConfigReader {
//    static Properties prop;
//
//    static {
//        try {
//            String path = System.getProperty("user.dir") + "/src/test/resources/config.properties";
//            FileInputStream fis = new FileInputStream(path);
//            prop = new Properties();
//            prop.load(fis);
//        } catch (Exception e) {
//            System.out.println("Error loading config file " + e.getMessage());
//        }
//    }
//
//    public static String getProperty(String key) {
//        return prop.getProperty(key);
//    }
//}










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

            FileInputStream fis =
                    new FileInputStream("src/main/resources/config.properties");

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