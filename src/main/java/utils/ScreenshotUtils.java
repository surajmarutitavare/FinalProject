package utils;


import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtils {

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
        } catch (IOException e) {
            // Handle error during file cop
            System.out.println("Error while taking screenshot: " + e.getMessage());
        }
        // Return the saved screenshot path
        return path;
    }
}