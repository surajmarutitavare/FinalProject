package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.ScreenshotUtils;

import java.util.List;

public class LabTestPage {
    WebDriver driver;
    private static final Logger logger = LogManager.getLogger(LabTestPage.class);

    public LabTestPage(WebDriver driver) {
        this.driver = driver;
        logger.info("LabTestPage page object initialized");
    }

    By labTestsElement = By.xpath("//a/child::div[contains(text(),'Lab Tests')]");
    By CitiesElement = By.xpath("//ul//li[@class='u-text--center']");

    public void topCities() {
        try {
            logger.info("Starting to fetch top cities for Lab Tests");
            
            logger.debug("Clicking on 'Lab Tests' element");
            WebElement labTests = driver.findElement(labTestsElement);
            labTests.click();
            logger.info("Successfully clicked 'Lab Tests'");

            logger.debug("Fetching all cities list");
            List<WebElement> Cities = driver.findElements(CitiesElement);
            logger.info("Found " + Cities.size() + " cities");

            logger.info("---City names---");
            StringBuilder cityNames = new StringBuilder();
            for (WebElement i : Cities) {
                String cityName = i.getText();
                cityNames.append(cityName).append(" ");
                logger.debug("City: " + cityName);
            }
            logger.info("Cities retrieved: " + cityNames.toString());
            System.out.println(cityNames.toString());

        } catch (Exception e) {
            logger.error("Error while fetching top cities: " + e.getMessage(), e);
            throw new RuntimeException("Failed to fetch top cities", e);
        }
    }
}
