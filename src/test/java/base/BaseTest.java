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
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import reports.ExtentManager;
import reports.ExtentTestManager;
import utils.DriverSetup;
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

        driver = DriverSetup.getDriver("chrome");

        driver.manage().window().maximize();

        driver.get("https://www.practo.com");
    }

    @AfterMethod
    public void tearDown(ITestResult result) {

        String testName =
                result.getMethod().getMethodName();

        String screenshotPath =
                ScreenshotUtils.captureScreenshot(
                        driver,
                        testName
                );

        if(result.getStatus()==ITestResult.SUCCESS){

            ExtentTestManager.test.log(
                    Status.PASS,
                    "Test Passed"
            ).addScreenCaptureFromPath(screenshotPath);
        }

        else if(result.getStatus()==ITestResult.FAILURE){

            ExtentTestManager.test.log(
                    Status.FAIL,
                    result.getThrowable()
            );

            try {

                ExtentTestManager.test
                        .addScreenCaptureFromPath(
                                screenshotPath
                        );

            } catch(Exception e){

                e.printStackTrace();
            }
        }

        else if(result.getStatus()==ITestResult.SKIP){

            ExtentTestManager.test.log(
                    Status.SKIP,
                    "Test Skipped"
            );
        }

        if(driver!=null){
            driver.quit();
        }
    }

    @AfterSuite
    public void flushReport(){

        extent.flush();
    }
}