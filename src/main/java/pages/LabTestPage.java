package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import reports.ExtentTestManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;
import java.util.List;

// Class definition for LabTestPage Page Object Model
public class LabTestPage {

    // WebDriver instance to control the browser
    WebDriver driver;

    // Logger instance for logging messages during test execution
    private static final Logger logger =
            LogManager.getLogger(LabTestPage.class);

    // Constructor to initialize LabTestPage with WebDriver
    public LabTestPage(WebDriver driver) {
        // Assign WebDriver to instance variable
        this.driver = driver;
        // Initialize all @FindBy annotated WebElements using PageFactory
        PageFactory.initElements(driver, this);
    }

    // -------------------- Page Factory Elements --------------------

    // Locator for Lab Tests menu option/link
    @FindBy(xpath = "//a/child::div[contains(text(),'Lab Tests')]")
    WebElement labTests;

    // Locator for list of cities displayed in the lab tests section
    @FindBy(xpath = "//ul//li[@class='u-text--center']")
    List<WebElement> cities;

    // Locator for the top city section container
    @FindBy(xpath = "//div[@class='u-bg--white u-marginb--std']")
    WebElement topCityDiv;


    // Method to fetch and display top cities offering lab tests
    public void topCities() {

        try {
            // Create WebDriverWait object for explicit wait of 20 seconds
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            // Log info message to test report about opening lab tests section
            ExtentTestManager.test.info(
                    "Opening Lab Tests Section"
            );

            // Log opening lab tests section to console
            logger.info("Opening Lab Tests Section");

            // Click on Lab Tests menu to open the lab tests section
            labTests.click();

            // Wait for top city div element to be visible on the page
            wait.until(ExpectedConditions.visibilityOf(topCityDiv));

            // Log total number of cities found to test report
            ExtentTestManager.test.info(
                    "Total Cities Found : " + cities.size()
            );

            // Log total cities count to console
            logger.info(
                    "Total Cities Found : {}",
                    cities.size()
            );

            // Loop through each city in the cities list
            for (WebElement city : cities) {

                // Log each city name to console
                logger.info(
                        "City : {}",
                        city.getText()
                );

                // Log each city name to test report
                ExtentTestManager.test.info(
                        "City : " + city.getText()
                );
            }

        } catch (Exception e) {

            // Log error message if any exception occurs during execution
            logger.error(
                    "Error occurred in topCities()",
                    e
            );

            // Log failure message with error details to test report
            ExtentTestManager.test.fail(
                    "Error in topCities() : "
                            + e.getMessage()
            );
        }
    }
// End of LabTestPage class
}