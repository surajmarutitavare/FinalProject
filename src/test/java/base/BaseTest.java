//
//package base;
//
//import org.openqa.selenium.WebDriver;
//import org.testng.ITestResult;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeMethod;
//import utils.DriverSetup;
//import utils.ScreenshotUtils;
//
//public class BaseTest {
//
//    protected WebDriver driver;
//
//    @BeforeMethod
//    public void setUp() {
//
//        driver = DriverSetup.getDriver("chrome");
//
//        driver.manage().window().maximize();
//
//        driver.get("https://www.practo.com");
//    }
//
//    @AfterMethod
//    public void takeScreenshot(ITestResult result) {
//
//        String testName = result.getMethod().getMethodName();
//
//        ScreenshotUtils.captureScreenshot(driver, testName);
//
//        if(driver != null){
//            driver.quit();
//        }
//    }
//}


package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import reports.ExtentManager;
import reports.ExtentTestManager;
import utils.ConfigReader;
import utils.DriverFactory;
import utils.ScreenshotUtils;

public class BaseTest {

    protected WebDriver driver;

    protected static ExtentReports extent;

    @BeforeSuite
    public void startReport() {

        extent = ExtentManager.getInstance();
    }

    @BeforeMethod
    public void setUp() {
        String browser = ConfigReader.getProperty("browser");
        driver = DriverFactory.getDriver(browser);
        driver.manage().window().maximize();
        String url = ConfigReader.getProperty("url");
        driver.get(url);
        String title = "Internet Security by Zscaler";
        String pageTitle = driver.getTitle();
        if (pageTitle.equalsIgnoreCase(title)) {
            driver.findElement(By.xpath("//input[@class='btn']")).click();
        }

    }

    @AfterMethod
    public void tearDown(ITestResult result) {

        String testName = result.getMethod().getMethodName();

        String screenshotPath =
                ScreenshotUtils.captureScreenshot(driver, testName);

        if (result.getStatus() == ITestResult.SUCCESS) {
            ExtentTestManager.test.log(Status.PASS, "Test Passed").addScreenCaptureFromPath(screenshotPath);
        } else if (result.getStatus() == ITestResult.FAILURE) {

            ExtentTestManager.test.log(Status.FAIL, result.getThrowable());

            try {
                ExtentTestManager.test.addScreenCaptureFromPath(screenshotPath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (driver != null) {
            driver.quit();
        }
    }

    @AfterSuite
    public void flushReport() {

        extent.flush();
    }
}