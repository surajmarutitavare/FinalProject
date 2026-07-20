



package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverFactory {

    private static final Logger logger =
            LogManager.getLogger(DriverFactory.class);

    static WebDriver driver;

    public static WebDriver getDriver(String browser) {

        try {

            logger.info("Requested Browser : {}", browser);

            if (browser.equalsIgnoreCase("chrome")) {

                logger.info("Launching Chrome Browser");

                driver = new ChromeDriver();

                logger.info("Chrome Browser Launched Successfully");

            } else if (browser.equalsIgnoreCase("edge")) {

                logger.info("Launching Edge Browser");

                driver = new EdgeDriver();

                logger.info("Edge Browser Launched Successfully");

            } else {

                logger.warn("Invalid Browser '{}' . Defaulting to Chrome", browser);

                driver = new ChromeDriver();

                logger.info("Chrome Browser Launched Successfully");
            }

        } catch (Exception e) {

            logger.error("Error while launching browser", e);
        }

        return driver;
    }
}