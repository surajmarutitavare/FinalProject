package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

// Class to create browser drivers
public class DriverFactory {

    // Logger to track events
    private static final Logger logger =
            LogManager.getLogger(DriverFactory.class);

    // Browser driver variable
    static WebDriver driver;

    // Get driver based on browser name
    public static WebDriver getDriver(String browser) {

        // Error handling
        try {

            // Log requested browser
            logger.info("Requested Browser : {}", browser);

            // Check for Chrome
            if (browser.equalsIgnoreCase("chrome")) {

                // Log Chrome start
                logger.info("Launching Chrome Browser");

                // Create Chrome driver
                driver = new ChromeDriver();

                // Log Chrome success
                logger.info("Chrome Browser Launched Successfully");

            } 
            // Check for Edge
            else if (browser.equalsIgnoreCase("edge")) {

                // Log Edge start
                logger.info("Launching Edge Browser");

                // Create Edge driver
                driver = new EdgeDriver();

                // Log Edge success
                logger.info("Edge Browser Launched Successfully");

            } 
            // Invalid browser
            else {

                // Log invalid browser
                logger.warn("Invalid Browser '{}' . Defaulting to Chrome", browser);

                // Use Chrome instead
                driver = new ChromeDriver();

                // Log Chrome success
                logger.info("Chrome Browser Launched Successfully");
            }

        } 
        // Handle errors
        catch (Exception e) {

            // Log error
            logger.error("Error while launching browser", e);
        }

        // Return driver
        return driver;
    }
}