package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverSetup {
    static WebDriver driver ;

    public static WebDriver getDriver(String browser) {


        try {
            if (browser.equalsIgnoreCase("chrome")) {
                driver = new ChromeDriver();
            } else if (browser.equalsIgnoreCase("edge")) {
                driver = new EdgeDriver();
            } else {
                System.out.println("Invalid Browser\n setting to CHROME ----");
                driver = new ChromeDriver();
            }
        } catch (Exception e) {
            System.out.println("Error in setting Browser....." + e.getMessage());
        }
        return driver;
    }

}
