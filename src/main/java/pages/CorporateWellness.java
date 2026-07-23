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

// Class definition for CorporateWellness Page Object Model
public class CorporateWellness {

    // WebDriver instance to control the browser
    WebDriver driver;

    // Logger instance for logging messages during test execution
    private static final Logger logger =
            LogManager.getLogger(CorporateWellness.class);

    // Constructor to initialize CorporateWellness with WebDriver
    public CorporateWellness(WebDriver driver) {
        // Assign WebDriver to instance variable
        this.driver = driver;
        // Initialize all @FindBy annotated WebElements using PageFactory
        PageFactory.initElements(driver, this);
    }

    // -------------------- Page Factory Elements --------------------

    // Locator for "For Corporates" menu link in navigation
    @FindBy(xpath = "//div[@class='nav-right text-right']/descendant::span[contains(text(),'For Corporates')]")
      WebElement corporate;

    // Locator for "Health & Wellness Plans" menu link
    @FindBy(linkText = "Health & Wellness Plans")
      WebElement corporateWellness;

    // Locator for name input field in the form
    @FindBy(xpath = "//input[@id='name']")
      WebElement name;

    // Locator for organization name input field
    @FindBy(xpath = "//input[@id='organizationName']")
      WebElement organizationName;

    // Locator for contact number input field
    @FindBy(xpath = "//input[@id='contactNumber']")
      WebElement contactNumber;

    // Locator for official email ID input field
    @FindBy(xpath = "//input[@id='officialEmailId']")
      WebElement officialEmailId;

    // Locator for organization size dropdown menu
    @FindBy(xpath = "//select[@id='organizationSize']")
      WebElement organizationSize;

    // Locator for interested in dropdown menu
    @FindBy(xpath = "//select[@id='interestedIn']")
      WebElement interestedInButton;

    // Locator for "Schedule a demo" submit button
    @FindBy(xpath = "//button[contains(text(),'Schedule a demo')]")
      WebElement scheduleDemo;

    // Method to check if phone validation error message is displayed
    public boolean isPhoneValidationDisplayed() {

        // Get the class attribute from contact number field and check if it contains "error"
        boolean result = contactNumber
                .getAttribute("class")
                .contains("error");

        // Check if phone validation error is present
        if (result) {

            // Log success message if validation error is displayed
            logger.info("Phone validation displayed");

            // Report test pass to extent report
            ExtentTestManager.test.pass(
                    "Phone validation displayed");

        } else {

            // Log warning if validation error is not displayed
            logger.warn("Phone validation not displayed");

            // Report test failure to extent report
            ExtentTestManager.test.fail(
                    "Phone validation not displayed");
        }

        // Return true if validation error is displayed, false otherwise
        return result;
    }

    // Method to check if email validation error message is displayed
    public boolean isEmailValidationDisplayed() {

        // Get the class attribute from email field and check if it contains "error"
        boolean result = officialEmailId
                .getAttribute("class")
                .contains("error");

        // Check if email validation error is present
        if (result) {

            // Log success message if validation error is displayed
            logger.info("Email validation displayed");

            // Report test pass to extent report
            ExtentTestManager.test.pass(
                    "Email validation displayed");

        } else {

            // Log warning if validation error is not displayed
            logger.warn("Email validation not displayed");

            // Report test failure to extent report
            ExtentTestManager.test.fail(
                    "Email validation not displayed");
        }

        // Return true if validation error is displayed, false otherwise
        return result;
    }

    // Method to fill and submit the corporate wellness form with test data
    public void formDetails() {

        try {

            // Read name from configuration properties file
            String testDataName =
                    ConfigReader.getProperty("name");

            // Read organization name from configuration properties file
            String testDataOrganization =
                    ConfigReader.getProperty("organization");

            // Read contact number from configuration properties file
            String testDataContactNumber =
                    ConfigReader.getProperty("contactNumber");

            // Read email from configuration properties file
            String testDataEmail =
                    ConfigReader.getProperty("emailId");

            // Print test data name to console for debugging
            System.out.println(
                    "tst------------>" + testDataName);

            // Create WebDriverWait object for explicit wait of 30 seconds
            WebDriverWait wait =
                    new WebDriverWait(
                            driver,
                            Duration.ofSeconds(30));

            // Wait for corporate menu element to be visible on page
            WebElement corporateElement =
                    wait.until(
                            ExpectedConditions.visibilityOf(
                                    corporate));

            // Click on "For Corporates" menu link
            corporateElement.click();

            // Log opening corporate wellness form to test report
            ExtentTestManager.test.info(
                    "Opening Corporate Wellness Form");

            // Log opening corporate wellness form to console
            logger.info(
                    "Opening Corporate Wellness Form");

            // Click on "Health & Wellness Plans" link
            corporateWellness.click();

            // Wait for name field to be visible on form
            WebElement nameField =
                    wait.until(
                            ExpectedConditions.visibilityOf(
                                    name));

            // Click on name field to focus it
            nameField.click();

            // Type the test data name into name field
            nameField.sendKeys(testDataName);

            // Log entered name to test report
            ExtentTestManager.test.info(
                    "Entered Name : " + testDataName);

            // Log entered name to console
            logger.info(
                    "Entered Name : {}",
                    testDataName);

            // Type organization name into organization name field
            organizationName.sendKeys(
                    testDataOrganization);

            // Log entered organization to test report
            ExtentTestManager.test.info(
                    "Entered Organization : "
                            + testDataOrganization);

            // Log entered organization to console
            logger.info(
                    "Entered Organization : {}",
                    testDataOrganization);

            // Type contact number into contact number field
            contactNumber.sendKeys(
                    testDataContactNumber);

            // Log entered phone number to test report
            ExtentTestManager.test.info(
                    "Entered Invalid Phone");

            // Log entered phone number to console
            logger.info(
                    "Entered Invalid Phone Number");

            // Type email into email field
            officialEmailId.sendKeys(
                    testDataEmail);

            // Log entered email to test report
            ExtentTestManager.test.info(
                    "Entered Invalid Email");

            // Log entered email to console
            logger.info(
                    "Entered Invalid Email Address");

            // Click on organization size dropdown menu to open it
            organizationSize.click();

            // Create Select object to handle dropdown selection
            Select select =
                    new Select(organizationSize);

            // Select the 3rd option (index 2) from organization size dropdown
            select.selectByIndex(2);

            // Log selected organization size to test report
            ExtentTestManager.test.info(
                    "Selected Organization Size");

            // Log selected organization size to console
            logger.info(
                    "Selected Organization Size");

            // Click on "Interested In" dropdown menu to open it
            interestedInButton.click();

            // Create Select object to handle dropdown selection
            Select select1 =
                    new Select(interestedInButton);

            // Select "Taking a demo" option from interested in dropdown
            select1.selectByVisibleText(
                    "Taking a demo");

            // Log selected demo option to test report
            ExtentTestManager.test.info(
                    "Selected Taking a Demo");

            // Log selected demo option to console
            logger.info(
                    "Selected Taking a Demo Option");

            // Log form submission info to test report
            ExtentTestManager.test.info(
                    "Submitting Form");

            // Log form submission info to console
            logger.info(
                    "Submitting Corporate Wellness Form");

            // Click on "Schedule a demo" button to submit the form
            scheduleDemo.click();

        } catch (Exception e) {

            // Log error message if any exception occurs during form filling
            logger.error(
                    "Error occurred in formDetails()",
                    e);

            // Log failure message with error details to test report
            ExtentTestManager.test.fail(
                    "Error in formDetails() : "
                            + e.getMessage());
        }
    }
// End of CorporateWellness class
}