//package pages;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.Select;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import reports.ExtentTestManager;
//import utils.ConfigReader;
//import utils.ScreenshotUtils;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//import java.time.Duration;
//
//public class CorporateWellness {
//    WebDriver driver;
//    private static final Logger logger =
//
//            LogManager.getLogger(CorporateWellness.class);
//
//    public CorporateWellness(WebDriver driver) {
//        this.driver = driver;
//    }
//
//    By corporateElement = By.xpath("//div[@class='nav-right text-right']/descendant::span[contains(text(),'For Corporates')]");
//    By corporateWellnessElement = By.linkText("Health & Wellness Plans");
//    By nameElement = By.xpath("//input[@id='name']");
//    By organizationNameElement = By.xpath("//input[@id='organizationName']");
//    By contactNumberElement = By.xpath("//input[@id='contactNumber']");
//    By officialEmailIdElement = By.xpath("//input[@id='officialEmailId']");
//    By organizationSizeElemet = By.xpath("//select[@id='organizationSize']");
//    By interestedInButtonElement = By.xpath("//select[@id='interestedIn']");
//    By scheduleDemoElement = By.xpath("//button[contains(text(),'Schedule a demo')]");
//
//
//    public boolean isPhoneValidationDisplayed() {
//
//        boolean result =
//                driver.findElement(contactNumberElement)
//                        .getAttribute("class")
//                        .contains("error");
//
//        if (result) {
//            logger.info("Phone validation displayed");
//            ExtentTestManager.test.pass("Phone validation displayed");
//        } else {
//            logger.warn("Phone validation not displayed");
//            ExtentTestManager.test.fail("Phone validation not displayed");
//        }
//
//        return result;
//    }
//
//
//    public boolean isEmailValidationDisplayed() {
//
//        boolean result =
//                driver.findElement(officialEmailIdElement)
//                        .getAttribute("class")
//                        .contains("error");
//
//        if (result) {
//            logger.info("Email validation displayed");
//            ExtentTestManager.test.pass("Email validation displayed");
//        } else {
//            logger.warn("Email validation not displayed");
//            ExtentTestManager.test.fail("Email validation not displayed");
//        }
//
//        return result;
//    }
//
//    public void formDetails() {
//        try {
//
//            String TestDataName = ConfigReader.getProperty("name");
//            String TestDataOrganization = ConfigReader.getProperty("organization");
//            String TestDataContactNUmber =  ConfigReader.getProperty("contactNumber");
//            String TestDataEmail = ConfigReader.getProperty("emailId");
//            System.out.println("tst------------>"+TestDataName);
//            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//
//            WebElement corporate = wait.until(ExpectedConditions.visibilityOfElementLocated(corporateElement));
//            corporate.click();
//            ExtentTestManager.test.info("Opening Corporate Wellness Form");
//            logger.info("Opening Corporate Wellness Form");
//
//            WebElement corporateWellness = driver.findElement(corporateWellnessElement);
//            corporateWellness.click();
//
//
//            WebElement name = wait.until(ExpectedConditions.visibilityOfElementLocated(nameElement));
//            name.click();
//
//            name.sendKeys(TestDataName);
//            ExtentTestManager.test.info(
//                    "Entered Name :"+ TestDataName
//            );
//            logger.info("Entered Name : {}",TestDataName);
//
//            WebElement organizationName = driver.findElement(organizationNameElement);
//
//            organizationName.sendKeys(TestDataOrganization);
//            ExtentTestManager.test.info(
//                    "Entered Organization {}:"+ TestDataOrganization
//            );
//            logger.info("Entered Organization :{}", TestDataOrganization);
//
//            WebElement contactNumber = driver.findElement(contactNumberElement);
//            contactNumber.sendKeys(TestDataContactNUmber);
//            ExtentTestManager.test.info(
//                    "Entered Invalid Phone");
//            logger.info("Entered Invalid Phone Number");
////
//
//            WebElement officialEmailId = driver.findElement(officialEmailIdElement);
//            officialEmailId.sendKeys(TestDataEmail);
//            ExtentTestManager.test.info(
//                    "Entered Invalid Email");
//            logger.info("Entered Invalid Email Address");
//
//
//            WebElement organizationSize = driver.findElement(organizationSizeElemet);
//            organizationSize.click();
//            Select select = new Select(organizationSize);
//            select.selectByIndex(2);
//            ExtentTestManager.test.info(
//                    "Selected Organization Size"
//            );
//            logger.info("Selected Organization Size");
//            WebElement interestedInButton = driver.findElement(interestedInButtonElement);
//            interestedInButton.click();
//            Select select1 = new Select(interestedInButton);
//            select1.selectByVisibleText("Taking a demo");
//            ExtentTestManager.test.info(
//                    "Selected Taking a Demo"
//            );
//            logger.info("Selected Taking a Demo Option");
//
//            ExtentTestManager.test.info("Submitting Form");
//            logger.info("Submitting Corporate Wellness Form");
//            WebElement scheduleDemo = driver.findElement(scheduleDemoElement);
//            scheduleDemo.click();
//        } catch (Exception e) {
//            logger.error("Error occurred in formDetails()", e);
//
//            ExtentTestManager.test.fail(
//                    "Error in formDetails() : " + e.getMessage());
//        }
//
//    }
//}



package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import reports.ExtentTestManager;
import utils.ConfigReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;

public class CorporateWellness {

    WebDriver driver;

    private static final Logger logger =
            LogManager.getLogger(CorporateWellness.class);

    public CorporateWellness(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Page Factory Elements

    @FindBy(xpath = "//div[@class='nav-right text-right']/descendant::span[contains(text(),'For Corporates')]")
      WebElement corporate;

    @FindBy(linkText = "Health & Wellness Plans")
      WebElement corporateWellness;

    @FindBy(xpath = "//input[@id='name']")
      WebElement name;

    @FindBy(xpath = "//input[@id='organizationName']")
      WebElement organizationName;

    @FindBy(xpath = "//input[@id='contactNumber']")
      WebElement contactNumber;

    @FindBy(xpath = "//input[@id='officialEmailId']")
      WebElement officialEmailId;

    @FindBy(xpath = "//select[@id='organizationSize']")
      WebElement organizationSize;

    @FindBy(xpath = "//select[@id='interestedIn']")
      WebElement interestedInButton;

    @FindBy(xpath = "//button[contains(text(),'Schedule a demo')]")
      WebElement scheduleDemo;

    public boolean isPhoneValidationDisplayed() {

        boolean result = contactNumber
                .getAttribute("class")
                .contains("error");

        if (result) {

            logger.info("Phone validation displayed");

            ExtentTestManager.test.pass(
                    "Phone validation displayed");

        } else {

            logger.warn("Phone validation not displayed");

            ExtentTestManager.test.fail(
                    "Phone validation not displayed");
        }

        return result;
    }

    public boolean isEmailValidationDisplayed() {

        boolean result = officialEmailId
                .getAttribute("class")
                .contains("error");

        if (result) {

            logger.info("Email validation displayed");

            ExtentTestManager.test.pass(
                    "Email validation displayed");

        } else {

            logger.warn("Email validation not displayed");

            ExtentTestManager.test.fail(
                    "Email validation not displayed");
        }

        return result;
    }

    public void formDetails() {

        try {

            String testDataName =
                    ConfigReader.getProperty("name");

            String testDataOrganization =
                    ConfigReader.getProperty("organization");

            String testDataContactNumber =
                    ConfigReader.getProperty("contactNumber");

            String testDataEmail =
                    ConfigReader.getProperty("emailId");

            System.out.println(
                    "tst------------>" + testDataName);

            WebDriverWait wait =
                    new WebDriverWait(
                            driver,
                            Duration.ofSeconds(30));

            WebElement corporateElement =
                    wait.until(
                            ExpectedConditions.visibilityOf(
                                    corporate));

            corporateElement.click();

            ExtentTestManager.test.info(
                    "Opening Corporate Wellness Form");

            logger.info(
                    "Opening Corporate Wellness Form");

            corporateWellness.click();

            WebElement nameField =
                    wait.until(
                            ExpectedConditions.visibilityOf(
                                    name));

            nameField.click();

            nameField.sendKeys(testDataName);

            ExtentTestManager.test.info(
                    "Entered Name : " + testDataName);

            logger.info(
                    "Entered Name : {}",
                    testDataName);

            organizationName.sendKeys(
                    testDataOrganization);

            ExtentTestManager.test.info(
                    "Entered Organization : "
                            + testDataOrganization);

            logger.info(
                    "Entered Organization : {}",
                    testDataOrganization);

            contactNumber.sendKeys(
                    testDataContactNumber);

            ExtentTestManager.test.info(
                    "Entered Invalid Phone");

            logger.info(
                    "Entered Invalid Phone Number");

            officialEmailId.sendKeys(
                    testDataEmail);

            ExtentTestManager.test.info(
                    "Entered Invalid Email");

            logger.info(
                    "Entered Invalid Email Address");

            organizationSize.click();

            Select select =
                    new Select(organizationSize);

            select.selectByIndex(2);

            ExtentTestManager.test.info(
                    "Selected Organization Size");

            logger.info(
                    "Selected Organization Size");

            interestedInButton.click();

            Select select1 =
                    new Select(interestedInButton);

            select1.selectByVisibleText(
                    "Taking a demo");

            ExtentTestManager.test.info(
                    "Selected Taking a Demo");

            logger.info(
                    "Selected Taking a Demo Option");

            ExtentTestManager.test.info(
                    "Submitting Form");

            logger.info(
                    "Submitting Corporate Wellness Form");

            scheduleDemo.click();

        } catch (Exception e) {

            logger.error(
                    "Error occurred in formDetails()",
                    e);

            ExtentTestManager.test.fail(
                    "Error in formDetails() : "
                            + e.getMessage());
        }
    }
}