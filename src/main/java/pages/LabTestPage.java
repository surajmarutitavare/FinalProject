package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import reports.ExtentTestManager;
import utils.ScreenshotUtils;

import java.util.List;

public class LabTestPage {
    WebDriver driver;

    public LabTestPage(WebDriver driver) {
        this.driver = driver;
    }

    By labTestsElement = By.xpath("//a/child::div[contains(text(),'Lab Tests')]");
    By CitiesElement = By.xpath("//ul//li[@class='u-text--center']");

//    public void topCities() {
//        WebElement labTests = driver.findElement(labTestsElement);
//        labTests.click();
//
//        List<WebElement> Cities = driver.findElements(CitiesElement);
////
//        System.out.println("---City names---");
//        for (WebElement i : Cities) {
//            System.out.print(i.getText() + " ");
//        }
//
//        System.out.println();

    /// /        ScreenshotUtils.captureScreenshot(driver);
    /// /
    /// /        driver.navigate().back();
//    }
    public void topCities() {

        ExtentTestManager.test.info(
                "Opening Lab Tests Section"
        );

        WebElement labTests =
                driver.findElement(labTestsElement);

        labTests.click();

        List<WebElement> cities =
                driver.findElements(CitiesElement);

        ExtentTestManager.test.info(
                "Total Cities Found : "
                        + cities.size()
        );

        for (WebElement city : cities) {

            ExtentTestManager.test.info(
                    "City : "
                            + city.getText()
            );
        }
    }
}
