package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
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


    public boolean isPhoneValidationDisplayed() {

        WebElement contactNumberField =
                driver.findElement(contactNumberElement);

        String classValue =
                contactNumberField.getAttribute("class");

        return classValue.contains("error");
    }

    public boolean isEmailValidationDisplayed() {

        WebElement emailField =
                driver.findElement(officialEmailIdElement);

        String classValue =
                emailField.getAttribute("class");

        return classValue.contains("error");
    }




    public void formDetails() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        WebElement corporate = wait.until(ExpectedConditions.visibilityOfElementLocated(corporateElement));
        corporate.click();


        WebElement corporateWellness = driver.findElement(corporateWellnessElement);
        corporateWellness.click();


        WebElement name = wait.until(ExpectedConditions.visibilityOfElementLocated(nameElement));
        name.click();
        name.sendKeys("Mrunmayee");

        WebElement organizationName = driver.findElement(organizationNameElement);
        organizationName.sendKeys("Cognizant");

        WebElement contactNumber = driver.findElement(contactNumberElement);
        contactNumber.sendKeys("90876534231");
//        String contactNumber1ClassAttribute = contactNumber1.getAttribute("class");
//        System.out.println(contactNumber1ClassAttribute);

//        WebElement contactNumber2 = driver.findElement(contactNumber);
//        String contactNumber2ClassAttribute = contactNumber2.getAttribute("class");
//        System.out.println(contactNumber2ClassAttribute);


//        if (contactNumber2ClassAttribute != null && contactNumber2ClassAttribute.contains("error")) {
//            System.out.println("Test Passed ✅✅✅");
//        }

        WebElement officialEmailId = driver.findElement(officialEmailIdElement);
//        String officialEmailId1ClassAttribute = officialEmailId1.getAttribute("class");
//        System.out.println(officialEmailId1ClassAttribute);
        officialEmailId.sendKeys("xyz@gmail.");

//        WebElement officialEmailId2 = driver.findElement(officialEmailIdElement);
//        String officialEmailId2ClassAttribute = officialEmailId2.getAttribute("class");
//        System.out.println(officialEmailId2ClassAttribute);
//
//        if (officialEmailId2ClassAttribute != null && officialEmailId2ClassAttribute.contains("error")) {
//            System.out.println("Test Passed ✅✅✅");
//        }


        WebElement organizationSize = driver.findElement(organizationSizeElemet);
        organizationSize.click();
        Select select = new Select(organizationSize);
        select.selectByIndex(2);

        WebElement interestedInButton = driver.findElement(interestedInButtonElement);
        interestedInButton.click();
        Select select1 = new Select(interestedInButton);
        select1.selectByVisibleText("Taking a demo");

//        ScreenshotUtils.captureScreenshot(driver);

        WebElement scheduleDemo = driver.findElement(scheduleDemoElement);
        scheduleDemo.click();


    }
}
