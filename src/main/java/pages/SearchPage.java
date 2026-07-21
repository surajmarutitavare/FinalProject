package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import reports.ExtentTestManager;
import utils.ExcelUtility;
import utils.ScreenshotUtils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;

public class SearchPage {
    WebDriver driver;
    private static final Logger logger = LogManager.getLogger(SearchPage.class);

    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    static List<String> ratingsList = new ArrayList<>();
    static List<String> open24x7List = new ArrayList<>();
    static List<String> parkingList = new ArrayList<>();

    By locationSearchFieldElement = By.xpath("//div[@id='c-omni-container']/descendant::input[1]");
    By bangaloreCityElement = By.xpath("//div[contains(text(),'Bangalore')]");
    By searchHospitalFieldElement = By.xpath("//div[@id='c-omni-container']/descendant::input[2]");
    By selectHospitalElement = By.xpath("//div[text()='Hospital'][1]");
    By hospitalListElement = By.tagName("li");
    //    static By hospitalElement = By.xpath("./descendant::a");
    static By hospitalElement = By.xpath(".//div[@class='c-estb-info']//a[@target='_blank']");


//    static By hospitalNameElement = By.xpath("//h1[@class='c-profile__title']//span");
    static By hospitalNameElement = By.xpath("./descendant::h2[@class='line-1']");


    static By ratingElement = By.xpath("//span[@class='common__star-rating__value']");
    static By hospitalTimingElement = By.xpath("//div[@data-qa-id='timings_title']/following-sibling::div/descendant::p");
    static By readMoreInfoButtonElement = By.xpath("//div[@class='pure-u-1']//span[@data-qa-id='read_more_info']/descendant::span");
    static By amenitiesElment = By.xpath("//div[@class='p-entity__item']//span");

    public static boolean checkFacilities(WebDriver driver, WebElement element) {
        boolean isRating = false;
        boolean isAvailable = false;
        boolean isParking = false;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

            String mainWindow = driver.getWindowHandle();
            WebElement hospital = wait.until(ExpectedConditions.visibilityOf(element.findElement(hospitalElement)));
            String hospitalNameText = hospital.getText();
            logger.info("Hospital Found : {}", hospitalNameText);
            hospital.click();

            Set<String> windows = driver.getWindowHandles();
            for (String i : windows) {
                if (!mainWindow.equals(i)) driver.switchTo().window(i);
            }
            ExtentTestManager.test.info(
                    "Checking Hospital : "
                            + hospitalNameText
            );

            WebElement rating = wait.until(ExpectedConditions.visibilityOfElementLocated(ratingElement));
            float ratingValue = Float.parseFloat(rating.getText());
            logger.info("Hospital Rating : {}", rating.getText());


            /// rating
            if (ratingValue >= 3.5) {
                isRating = true;
                ratingsList.add(rating.getText());
                ExtentTestManager.test.pass("Rating >= 3.5 : " + ratingValue);
                logger.info("Hospital Rating above 3.5 : {}", hospitalNameText);
            }

            ///  24 x 7 Availability
            WebElement hospitalTiming = driver.findElement(hospitalTimingElement);
            String hospitalTimingText = hospitalTiming.getText();
            logger.info("Hospital Timing : {}", hospitalTimingText);

            if (hospitalTimingText.equals("Open 24 x 7")) {
                isAvailable = true;
                open24x7List.add(hospitalTimingText);
                ExtentTestManager.test.pass("Hospital Available 24 x 7");
                logger.info("Hospital Available 24x7 : {}", hospitalNameText);
            }

            WebElement readMoreInfoButton = driver.findElement(readMoreInfoButtonElement);
            readMoreInfoButton.click();


            List<WebElement> amenities = driver.findElements(amenitiesElment);
            for (WebElement i : amenities) {
                if (i.getText().equals("Parking")) {
                    isParking = true;
                    parkingList.add(i.getText());
                    ExtentTestManager.test.pass("Parking Facility Available");
                    logger.info("Amenity Found : {}", i.getText());
                    break;
                }
            }

            driver.close();
            driver.switchTo().window(mainWindow);
            logger.info("------------------------------------------------------------");
            System.out.println("----------------------------------------------------------------------------");
            return isRating && isAvailable && isParking;
        } catch (Exception e) {
            logger.error("Error while checking facilities", e);
            ExtentTestManager.test.fail(e.getMessage());
            return isRating && isAvailable && isParking;
        }

    }

    public void searchHospital() {
        try {
            WebElement location = driver.findElement(locationSearchFieldElement);

            Actions action = new Actions(driver);
            action.doubleClick(location).perform();
            action.sendKeys(Keys.BACK_SPACE);
            action.sendKeys(location, "Bang").perform();

            ExtentTestManager.test.info(
                    "Selecting Bangalore Location");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            WebElement bangalore = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(bangaloreCityElement));
            bangalore.click();

            ExtentTestManager.test.pass(
                    "Bangalore Selected");

            WebElement search = driver.findElement(searchHospitalFieldElement);
            search.clear();

            search.sendKeys("Hospital");
            ExtentTestManager.test.info(
                    "Searching Hospitals");

            WebElement hospital = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(selectHospitalElement));
            hospital.click();

            ExtentTestManager.test.info("Hospital Search Started");

            List<WebElement> hospitalList = driver.findElements(hospitalListElement);
            List<WebElement> hospitalWithFeatured = new ArrayList<>();
            logger.info("Total Hospitals Found : {}", hospitalList.size());
            ExtentTestManager.test.info("Total Hospitals Found : " + hospitalList.size());

            for (WebElement i : hospitalList) {
                if (checkFacilities(driver, i)) {
                    hospitalWithFeatured.add(i);
                }
            }


            for (int i = 0; i < hospitalWithFeatured.size(); i++) {

                WebElement hospitalNew =
                        hospitalWithFeatured.get(i).findElement(hospitalNameElement);

                String text = hospitalNew.getText();


                ExcelUtility.writeHospitalRow(
                        i + 1,
                        text,
                        ratingsList.get(i) + " ★",
                        parkingList.get(i) + " Available",
                        open24x7List.get(i)
                );

                logger.info(
                        "Excel Row {} -> Hospital: {}, Rating: {}, Parking: {}, Open24x7: {}",
                        i + 1,
                        text,
                        ratingsList.get(i),
                        parkingList.get(i),
                        open24x7List.get(i)
                );

                ExtentTestManager.test.info(
                        "Hospital: " + text
                                + " | Rating: " + ratingsList.get(i)
                                + " | Parking: " + parkingList.get(i)
                                + " | Open24x7: " + open24x7List.get(i)
                );
            }
            logger.info("Hospitals Matching Criteria : {}", hospitalWithFeatured.size());


            ExtentTestManager.test.info("Hospitals Matching Criteria : " + hospitalWithFeatured.size());

//            System.out.println("Hospitals Matching Criteria " + hospitalWithFeatured.size());

        } catch (Exception e) {
            logger.error("Error occurred in searchHospital()", e);

            ExtentTestManager.test.fail(
                    "Error in searchHospital() : " + e.getMessage());

        }
    }
}