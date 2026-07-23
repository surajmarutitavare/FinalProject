package utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtils {
    private static final Logger logger = LogManager.getLogger(ScreenshotUtils.class);


    // Method to capture screenshot and return file path
    public static String captureScreenshot(WebDriver driver, String testName) {

        // Converts WebDriver to TakesScreenshot interface
        TakesScreenshot ts = (TakesScreenshot) driver;

        // Capture screenshot and store it as a file (temporary)
        File src = ts.getScreenshotAs(OutputType.FILE);

        // Create unique timestamp to avoid file name duplication
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());


        // Define final path where screenshot will be saved
        // File name = testName + timestamp
        String path = System.getProperty("user.dir") + "/screenshots/" + testName + "_" + timestamp + ".png";

        try {

            // Create destination file
            File dest = new File(path);

            // Copy screenshot from source to destination
            FileHandler.copy(src, dest);
            logger.info("Screenshot saved successfully : {}", path);

        } catch (IOException e) {
            // Handle error during file cop
            logger.error("Error while taking screenshot", e);
                    }
        // Return the saved screenshot path
        return path;
    }
}