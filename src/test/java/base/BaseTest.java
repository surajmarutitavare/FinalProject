
package base;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.DriverSetup;
import utils.ScreenshotUtils;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {

        driver = DriverSetup.getDriver("chrome");

        driver.manage().window().maximize();

        driver.get("https://www.practo.com");
    }

    @AfterMethod
    public void takeScreenshot(ITestResult result) {

        String testName = result.getMethod().getMethodName();

        ScreenshotUtils.captureScreenshot(driver, testName);

        if(driver != null){
            driver.quit();
        }
    }
}