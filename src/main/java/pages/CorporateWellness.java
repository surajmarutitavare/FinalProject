package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import reports.ExtentTestManager;
import utils.ScreenshotUtils;

import java.time.Duration;

public class CorporateWellness {
    WebDriver driver;

    public CorporateWellness(WebDriver driver) {
        this.driver = driver;
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


//    public boolean isPhoneValidationDisplayed() {
//
//        WebElement contactNumberField =
//                driver.findElement(contactNumberElement);
//
//        String classValue =
//                contactNumberField.getAttribute("class");
//
//        return classValue.contains("error");
//    }


    public boolean isPhoneValidationDisplayed() {
        boolean result =
                driver.findElement(contactNumberElement).getAttribute("class").contains("error");

        if (result) {
            ExtentTestManager.test.pass("Phone validation displayed");
        } else {
            ExtentTestManager.test.fail("Phone validation not displayed");
        }
        return result;
    }


//    public boolean isEmailValidationDisplayed() {
//
//        WebElement emailField =
//                driver.findElement(officialEmailIdElement);
//        String classValue =
//                emailField.getAttribute("class");
//        return classValue.contains("error");
//    }



    public boolean isEmailValidationDisplayed() {

        boolean result =
                driver.findElement(officialEmailIdElement).getAttribute("class").contains("error");

        if(result){
            ExtentTestManager.test.pass("Email validation displayed");

        } else {
            ExtentTestManager.test.fail("Email validation not displayed");
        }
        return result;
    }

    public void formDetails() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        WebElement corporate = wait.until(ExpectedConditions.visibilityOfElementLocated(corporateElement));
        corporate.click();
        ExtentTestManager.test.info("Opening Corporate Wellness Form");

        WebElement corporateWellness = driver.findElement(corporateWellnessElement);
        corporateWellness.click();


        WebElement name = wait.until(ExpectedConditions.visibilityOfElementLocated(nameElement));
        name.click();
        name.sendKeys("Mrunmayee");
        ExtentTestManager.test.info(
                "Entered Name : Mrunmayee"
        );

        WebElement organizationName = driver.findElement(organizationNameElement);
        organizationName.sendKeys("Cognizant");
        ExtentTestManager.test.info(
                "Entered Organization : Cognizant"
        );

        WebElement contactNumber = driver.findElement(contactNumberElement);
        contactNumber.sendKeys("90876534231");
        ExtentTestManager.test.info(
                "Entered Invalid Phone");
//

        WebElement officialEmailId = driver.findElement(officialEmailIdElement);
        officialEmailId.sendKeys("xyz@gmail.");
        ExtentTestManager.test.info(
                "Entered Invalid Email");


        WebElement organizationSize = driver.findElement(organizationSizeElemet);
        organizationSize.click();
        Select select = new Select(organizationSize);
        select.selectByIndex(2);
        ExtentTestManager.test.info(
                "Selected Organization Size"
        );

        WebElement interestedInButton = driver.findElement(interestedInButtonElement);
        interestedInButton.click();
        Select select1 = new Select(interestedInButton);
        select1.selectByVisibleText("Taking a demo");
        ExtentTestManager.test.info(
                "Selected Taking a Demo"
        );

        ExtentTestManager.test.info("Submitting Form");
        WebElement scheduleDemo = driver.findElement(scheduleDemoElement);
        scheduleDemo.click();


    }
}
