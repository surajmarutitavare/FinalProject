package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.ScreenshotUtils;

import java.time.Duration;

public class CorporateWellness {
    WebDriver driver;
    private static final Logger logger = LogManager.getLogger(CorporateWellness.class);

    public CorporateWellness(WebDriver driver) {
        this.driver = driver;
        logger.info("CorporateWellness page object initialized");
    }

    By corporateElement = By.xpath("//div[@class='nav-right text-right']/descendant::span[contains(text(),'For Corporates')]");
    By corporateWellnessElement = By.linkText("Health & Wellness Plans");
    By nameElement = By.xpath("//input[@id='name']");
    By organizationNameElement = By.xpath("//input[@id='organizationName']");
    By contactNumberElement = By.xpath("//input[@id='contactNumber']");
    By officialEmailIdElement = By.xpath("//input[@id='officialEmailId']");
    By organizationSizeElemet = By.xpath("//select[@id='organizationSize']");
    By interestedInButtonElement = By.xpath("//select[@id='interestedIn']");
    By scheduleDemoElement = By.xpath("//button[contains(text(),'Schedule a demo')]");


    public boolean isPhoneValidationDisplayed() {
        try {
            logger.debug("Checking phone validation error");
            WebElement contactNumberField =
                    driver.findElement(contactNumberElement);

            String classValue =
                    contactNumberField.getAttribute("class");

            boolean isPhoneError = classValue.contains("error");
            if (isPhoneError) {
                logger.info("Phone validation error is displayed");
            } else {
                logger.warn("Phone validation error is not displayed");
            }
            return isPhoneError;
        } catch (Exception e) {
            logger.error("Error checking phone validation: " + e.getMessage(), e);
            return false;
        }
    }

    public boolean isEmailValidationDisplayed() {
        try {
            logger.debug("Checking email validation error");
            WebElement emailField =
                    driver.findElement(officialEmailIdElement);

            String classValue =
                    emailField.getAttribute("class");

            boolean isEmailError = classValue.contains("error");
            if (isEmailError) {
                logger.info("Email validation error is displayed");
            } else {
                logger.warn("Email validation error is not displayed");
            }
            return isEmailError;
        } catch (Exception e) {
            logger.error("Error checking email validation: " + e.getMessage(), e);
            return false;
        }
    }




    public void formDetails() {
        try {
            logger.info("Starting Corporate Wellness form submission");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

            logger.debug("Clicking on 'For Corporates' element");
            WebElement corporate = wait.until(ExpectedConditions.visibilityOfElementLocated(corporateElement));
            corporate.click();
            logger.info("Successfully clicked 'For Corporates'");

            logger.debug("Clicking on 'Health & Wellness Plans' link");
            WebElement corporateWellness = driver.findElement(corporateWellnessElement);
            corporateWellness.click();
            logger.info("Successfully clicked 'Health & Wellness Plans'");

            logger.debug("Entering name: Mrunmayee");
            WebElement name = wait.until(ExpectedConditions.visibilityOfElementLocated(nameElement));
            name.click();
            name.sendKeys("Mrunmayee");
            logger.info("Name field filled successfully");

            logger.debug("Entering organization name: Cognizant");
            WebElement organizationName = driver.findElement(organizationNameElement);
            organizationName.sendKeys("Cognizant");
            logger.info("Organization name filled successfully");

            logger.debug("Entering contact number: 90876534231");
            WebElement contactNumber = driver.findElement(contactNumberElement);
            contactNumber.sendKeys("90876534231");
            logger.info("Contact number filled successfully");

            logger.debug("Entering official email: xyz@gmail.");
            WebElement officialEmailId = driver.findElement(officialEmailIdElement);
            officialEmailId.sendKeys("xyz@gmail.");
            logger.info("Email field filled successfully");

            logger.debug("Selecting organization size (index 2)");
            WebElement organizationSize = driver.findElement(organizationSizeElemet);
            organizationSize.click();
            Select select = new Select(organizationSize);
            select.selectByIndex(2);
            logger.info("Organization size selected successfully");

            logger.debug("Selecting interested in: Taking a demo");
            WebElement interestedInButton = driver.findElement(interestedInButtonElement);
            interestedInButton.click();
            Select select1 = new Select(interestedInButton);
            select1.selectByVisibleText("Taking a demo");
            logger.info("'Interested In' dropdown selected successfully");

            logger.debug("Clicking 'Schedule a demo' button");
            WebElement scheduleDemo = driver.findElement(scheduleDemoElement);
            scheduleDemo.click();
            logger.info("'Schedule a demo' button clicked successfully - Form submission completed");

        } catch (Exception e) {
            logger.error("Error during form submission: " + e.getMessage(), e);
            throw new RuntimeException("Form submission failed", e);
        }
    }
}
