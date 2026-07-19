package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SearchPage {

    private static final Logger logger = LogManager.getLogger(SearchPage.class);

    WebDriver driver;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    By locationSearchFieldElement = By.xpath("//div[@id='c-omni-container']/descendant::input[1]");
    By bangaloreCityElement = By.xpath("//div[contains(text(),'Bangalore')]");
    By searchHospitalFieldElement = By.xpath("//div[@id='c-omni-container']/descendant::input[2]");
    By selectHospitalElement = By.xpath("//div[text()='Hospital'][1]");
    By hospitalListElement = By.tagName("li");

    static By hospitalElement = By.xpath(".//descendant::a");
    static By hospitalNameElement = By.xpath("//h1[@class='c-profile__title']/child::span");
    static By ratingElement = By.xpath("//span[@class='common__star-rating__value']");
    static By hospitalTimingElement = By.xpath("//div[@data-qa-id='timings_title']/following-sibling::div/descendant::p");
    static By readMoreInfoButtonElement = By.xpath("//div[@class='pure-u-1']//span[@data-qa-id='read_more_info']/descendant::span");
    static By amenitiesElement = By.xpath("//div[@class='p-entity__item']//span");

    public static boolean checkFacilities(WebDriver driver, WebElement element) {

        boolean isRating = false;
        boolean isAvailable = false;
        boolean isParking = false;

        String mainWindow = driver.getWindowHandle();

        try {

            WebElement hospital = element.findElement(hospitalElement);
            String hospitalText = hospital.getText();

            logger.info("Checking hospital: {}", hospitalText);

            hospital.click();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

            wait.until(d -> d.getWindowHandles().size() > 1);

            Set<String> windows = driver.getWindowHandles();

            for (String window : windows) {
                if (!mainWindow.equals(window)) {
                    driver.switchTo().window(window);
                    break;
                }
            }

            WebElement hospitalName =
                    wait.until(ExpectedConditions.visibilityOfElementLocated(hospitalNameElement));

            String hospitalNameText = hospitalName.getText();

            logger.info("Opened hospital profile: {}", hospitalNameText);

            WebElement rating =
                    wait.until(ExpectedConditions.visibilityOfElementLocated(ratingElement));

            float ratingValue = Float.parseFloat(rating.getText());

            logger.info("Hospital Rating: {}", ratingValue);

            if (ratingValue >= 3.5) {
                isRating = true;
                logger.info("Rating criteria passed for {}", hospitalNameText);
            }

            WebElement hospitalTiming =
                    wait.until(ExpectedConditions.visibilityOfElementLocated(hospitalTimingElement));

            String hospitalTimingText = hospitalTiming.getText();

            logger.info("Hospital Timing: {}", hospitalTimingText);

            if (hospitalTimingText.equalsIgnoreCase("Open 24 x 7")) {
                isAvailable = true;
                logger.info("24x7 availability criteria passed for {}", hospitalNameText);
            }

            WebElement readMoreInfoButton =
                    wait.until(ExpectedConditions.elementToBeClickable(readMoreInfoButtonElement));

            readMoreInfoButton.click();

            logger.info("Clicked Read More Info button");

            List<WebElement> amenities = driver.findElements(amenitiesElement);

            for (WebElement amenity : amenities) {

                logger.debug("Amenity Found: {}", amenity.getText());

                if (amenity.getText().equalsIgnoreCase("Parking")) {
                    isParking = true;
                    logger.info("Parking facility available");
                    break;
                }
            }

            logger.info(
                    "Verification Result -> Rating: {}, Availability: {}, Parking: {}",
                    isRating,
                    isAvailable,
                    isParking);

            return isRating && isAvailable && isParking;

        } catch (Exception e) {

            logger.error("Error while checking facilities", e);
            return false;

        }
//        finally {
//
//            try {
//                if (driver.getWindowHandles().size() > 1) {
//                    driver.close();
//                    driver.switchTo().window(mainWindow);
//                }
//            } catch (Exception ex) {
//                logger.error("Error while switching back to main window", ex);
//            }
//
//            logger.info("------------------------------------------------------");
//        }
    }

    public void searchHospital() {

        logger.info("Starting Hospital Search");

        WebElement location = driver.findElement(locationSearchFieldElement);

        location.clear();
        location.sendKeys("Bangalore");

        logger.info("Entered location: Bangalore");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        WebElement bangalore =
                wait.until(ExpectedConditions.visibilityOfElementLocated(bangaloreCityElement));

        bangalore.click();

        logger.info("Selected Bangalore from suggestions");

        WebElement search = driver.findElement(searchHospitalFieldElement);

        search.clear();
        search.sendKeys("Hospital");

        logger.info("Searching hospitals");

        WebElement hospital =
                wait.until(ExpectedConditions.visibilityOfElementLocated(selectHospitalElement));

        hospital.click();

        logger.info("Hospital option selected");

        List<WebElement> hospitalList = driver.findElements(hospitalListElement);

        logger.info("Total hospitals found: {}", hospitalList.size());

        List<WebElement> hospitalWithFeatured = new ArrayList<>();

        for (WebElement element : hospitalList) {

            try {

                if (checkFacilities(driver, element)) {
                    hospitalWithFeatured.add(element);
                    logger.info("Hospital added to filtered list");
                }

            } catch (Exception e) {
                logger.error("Error while processing hospital", e);
            }
        }

        logger.info("Hospitals matching all criteria: {}", hospitalWithFeatured.size());
        logger.info("Hospital Search Completed");
    }
}