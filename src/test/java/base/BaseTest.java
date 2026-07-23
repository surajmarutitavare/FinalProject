package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import reports.ExtentManager;
import reports.ExtentTestManager;
import utils.ConfigReader;
import utils.DriverFactory;
import utils.ScreenshotUtils;

import java.time.Duration;

// Base class that all test classes inherit from
// This class has setup and cleanup methods for test execution
public class BaseTest {

    // WebDriver variable accessible to all child test classes
    protected WebDriver driver;

    // Extent Reports object for generating test reports
    protected static ExtentReports extent;

    // @BeforeSuite annotation: Runs ONCE before the entire test suite starts
    // Use: Initialize test report and resources needed for all tests
    @BeforeSuite
    public void startReport() {
        // Print message to console showing suite has started
        System.out.println("========== Test Suite Started ==========");
        // Get or create the Extent Reports instance
        extent = ExtentManager.getInstance();
    }

    // @BeforeMethod annotation: Runs BEFORE each test method
    // Setup browser and website for every individual test
    @BeforeMethod
    public void setUp() {
        // Read browser name from configuration file
        String browser = ConfigReader.getProperty("browser");
        // Create browser driver (Chrome, Edge, etc.)
        driver = DriverFactory.getDriver(browser);
        // Maximize browser window
        driver.manage().window().maximize();
        // Read website URL from configuration file
        String url = ConfigReader.getProperty("url");
        // Navigate to the website
        driver.get(url);

        // Create wait object with 10 seconds timeout
        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        // Check if security warning page appears (Zscaler popup)
        if (driver.getTitle().equalsIgnoreCase("Internet Security by Zscaler")) {

            // Wait for button to be clickable and click it
            wait.until(ExpectedConditions.elementToBeClickable(
                            By.xpath("//input[@class='btn']")))
                    .click();
        }
    }

    // @AfterMethod annotation: Runs AFTER each test method completes
    // Use: Cleanup browser and capture test results
    @AfterMethod
    public void tearDown(ITestResult result) {

        // Get the name of the test method that just ran
        String testName = result.getMethod().getMethodName();

        // Capture screenshot of final state
        String screenshotPath =
                ScreenshotUtils.captureScreenshot(driver, testName);

        // Check if test passed
        if (result.getStatus() == ITestResult.SUCCESS) {
            // Log PASS status and attach screenshot to report
            ExtentTestManager.test.log(Status.PASS, "Test Passed").addScreenCaptureFromPath(screenshotPath);
        } 
        // Check if test failed
        else if (result.getStatus() == ITestResult.FAILURE) {

            // Log FAIL status with error details
            ExtentTestManager.test.log(Status.FAIL, result.getThrowable());

            // Try to attach screenshot to failed test report
            try {
                ExtentTestManager.test.addScreenCaptureFromPath(screenshotPath);
            } catch (Exception e) {
                // Print error if screenshot attachment fails
                e.printStackTrace();
            }
        }

        // Check if driver exists
        if (driver != null) {
            // Close browser and end session
            driver.quit();
        }
    }

    // @AfterSuite annotation: Runs ONCE after all tests in suite finish
    // Use: Generate final test report
    @AfterSuite
    public void flushReport() {

        // Generate and write the final HTML report
        extent.flush();
    }
}