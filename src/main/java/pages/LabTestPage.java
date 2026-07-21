//package pages;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import reports.ExtentTestManager;
//import utils.ScreenshotUtils;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//import java.util.List;
//
//public class LabTestPage {
//    WebDriver driver;
//    private static final Logger logger =
//
//            LogManager.getLogger(LabTestPage.class);
//
//
//    public LabTestPage(WebDriver driver) {
//        this.driver = driver;
//    }
//
//    By labTestsElement = By.xpath("//a/child::div[contains(text(),'Lab Tests')]");
//    By CitiesElement = By.xpath("//ul//li[@class='u-text--center']");
//
//
//    public void topCities() {
//        try {
//            ExtentTestManager.test.info(
//                    "Opening Lab Tests Section"
//            );
//            logger.info("Opening Lab Tests Section");
//            WebElement labTests =
//                    driver.findElement(labTestsElement);
//
//            labTests.click();
//
//            List<WebElement> cities =
//                    driver.findElements(CitiesElement);
//
//            ExtentTestManager.test.info(
//                    "Total Cities Found : "
//                            + cities.size()
//            );
//            logger.info("Total Cities Found : {}", cities.size());
//
//            for (WebElement city : cities) {
//                logger.info("City : {}", city.getText());
//                ExtentTestManager.test.info(
//                        "City : "
//                                + city.getText()
//                );
//            }
//        } catch (Exception e) {
//            logger.error("Error occurred in topCities()", e);
//
//            ExtentTestManager.test.fail(
//                    "Error in topCities() : " + e.getMessage());
//
//        }
//    }
//}



package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import reports.ExtentTestManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class LabTestPage {

    WebDriver driver;

    private static final Logger logger =
            LogManager.getLogger(LabTestPage.class);

    public LabTestPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Page Factory Elements

    @FindBy(xpath = "//a/child::div[contains(text(),'Lab Tests')]")
      WebElement labTests;

    @FindBy(xpath = "//ul//li[@class='u-text--center']")
      List<WebElement> cities;

    public void topCities() {

        try {

            ExtentTestManager.test.info(
                    "Opening Lab Tests Section"
            );

            logger.info("Opening Lab Tests Section");

            labTests.click();

            ExtentTestManager.test.info(
                    "Total Cities Found : " + cities.size()
            );

            logger.info(
                    "Total Cities Found : {}",
                    cities.size()
            );

            for (WebElement city : cities) {

                logger.info(
                        "City : {}",
                        city.getText()
                );

                ExtentTestManager.test.info(
                        "City : " + city.getText()
                );
            }

        } catch (Exception e) {

            logger.error(
                    "Error occurred in topCities()",
                    e
            );

            ExtentTestManager.test.fail(
                    "Error in topCities() : "
                            + e.getMessage()
            );
        }
    }
}