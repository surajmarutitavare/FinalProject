package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import reports.ExtentTestManager;
import utils.ExcelUtility;
import java.time.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.ScreenshotUtils;

// Class definition for SearchPage Page Object Model
public class SearchPage {

    // WebDriver instance to control the browser
    WebDriver driver;

    // Logger instance for logging messages during test execution
    private static final Logger logger =
            LogManager.getLogger(SearchPage.class);

    // Constructor to initialize SearchPage with WebDriver
    public SearchPage(WebDriver driver) {
        // Assign WebDriver to instance variable
        this.driver = driver;
        // Initialize all @FindBy annotated WebElements using PageFactory
        PageFactory.initElements(driver, this);
    }

    // Static list to store hospital ratings
    static List<String> ratingsList = new ArrayList<>();
    // Static list to store 24x7 availability information
    static List<String> open24x7List = new ArrayList<>();
    // Static list to store parking availability information
    static List<String> parkingList = new ArrayList<>();

    // -------------------- Page Factory Elements --------------------

    // Locator for location search field on the page
    @FindBy(xpath = "//div[@id='c-omni-container']/descendant::input[1]")
      WebElement locationSearchField;

    // Locator for Bangalore city option in dropdown
    @FindBy(xpath = "//div[contains(text(),'Bangalore')]")
      WebElement bangaloreCity;

    // Locator for hospital search field on the page
    @FindBy(xpath = "//div[@id='c-omni-container']/descendant::input[2]")
      WebElement searchHospitalField;

    // Locator for Hospital selection option
    @FindBy(xpath = "//div[text()='Hospital'][1]")
      WebElement selectHospital;

    // Locator for list of hospitals displayed on search results
    @FindBy(tagName = "li")
      List<WebElement> hospitalList;

    // -------------------- Static Locators --------------------

    // Locator for hospital element link that opens hospital details
    static By hospitalElement =
            By.xpath(".//div[@class='c-estb-info']//a[@target='_blank']");

    // Locator for hospital name element on detail page
    static By hospitalNameElement =
            By.xpath("./descendant::h2[@class='line-1']");

    // Locator for hospital rating value
    static By ratingElement =
            By.xpath("//span[@class='common__star-rating__value']");

    // Locator for hospital timing/hours of operation
    static By hospitalTimingElement =
            By.xpath("//div[@data-qa-id='timings_title']/following-sibling::div/descendant::p");

    // Locator for read more info button to view amenities
    static By readMoreInfoButtonElement =
            By.xpath("//div[@class='pure-u-1']//span[@data-qa-id='read_more_info']/descendant::span");

    // Locator for amenities list (parking, etc.)
    static By amenitiesElement =
            By.xpath("//div[@class='p-entity__item']//span");

    // -------------------- Check Facilities --------------------

    // Method to check if hospital has all required facilities (rating, 24x7, parking)
    public static boolean checkFacilities(WebDriver driver, WebElement element) {

        // Flag to track if rating is >= 3.5
        boolean isRating = false;
        // Flag to track if hospital is open 24x7
        boolean isAvailable = false;
        // Flag to track if parking facility is available
        boolean isParking = false;

        try {

            // Create WebDriverWait object for explicit wait of 30 seconds
            WebDriverWait wait =
                    new WebDriverWait(driver, Duration.ofSeconds(30));

            // Store the main window handle before switching
            String mainWindow = driver.getWindowHandle();

            // Wait and find the hospital element link
            WebElement hospital = wait.until(
                    ExpectedConditions.visibilityOf(
                            element.findElement(hospitalElement)));

            // Extract hospital name text
            String hospitalNameText = hospital.getText();

            // Log hospital name to console
            logger.info("Hospital Found : {}", hospitalNameText);

            // Click on hospital link to open hospital details page
            hospital.click();

            // Get all window handles after hospital page opens
            Set<String> windows = driver.getWindowHandles();

            // Loop through all windows to find and switch to the new hospital window
            for (String i : windows) {
                // Check if window is not the main window
                if (!mainWindow.equals(i)) {
                    // Switch to the new hospital details window
                    driver.switchTo().window(i);
                }
            }

            // Log hospital checking info to test report
            ExtentTestManager.test.info(
                    "Checking Hospital : " + hospitalNameText);

            // Wait for rating element to be visible on hospital detail page
            WebElement rating = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            ratingElement));

            // Convert rating text to float for comparison
            float ratingValue =
                    Float.parseFloat(rating.getText());

            // Log hospital rating to console
            logger.info("Hospital Rating : {}", rating.getText());

            // Check if hospital rating is greater than or equal to 3.5
            if (ratingValue >= 3.5) {

                // Set rating flag to true
                isRating = true;
                // Add rating to ratingsList
                ratingsList.add(rating.getText());

                // Log pass status to test report
                ExtentTestManager.test.pass(
                        "Rating >= 3.5 : " + ratingValue);

                // Log hospital rating status to console
                logger.info(
                        "Hospital Rating above 3.5 : {}",
                        hospitalNameText);
            }

            // Find hospital timing element on detail page
            WebElement hospitalTiming =
                    driver.findElement(hospitalTimingElement);

            // Extract timing text (e.g., "Open 24 x 7")
            String hospitalTimingText =
                    hospitalTiming.getText();

            // Log hospital timing to console
            logger.info(
                    "Hospital Timing : {}",
                    hospitalTimingText);

            // Check if hospital is open 24x7
            if (hospitalTimingText.equals("Open 24 x 7")) {

                // Set availability flag to true
                isAvailable = true;

                // Add timing information to open24x7List
                open24x7List.add(hospitalTimingText);

                // Log pass status to test report
                ExtentTestManager.test.pass(
                        "Hospital Available 24 x 7");

                // Log 24x7 availability status to console
                logger.info(
                        "Hospital Available 24x7 : {}",
                        hospitalNameText);
            }

            // Click on "Read More" button to expand amenities section
            WebElement readMoreInfoButton =
                    driver.findElement(readMoreInfoButtonElement);

            // Click the read more button
            readMoreInfoButton.click();

            // Find all amenities elements (parking, wifi, etc.)
            List<WebElement> amenities =
                    driver.findElements(amenitiesElement);

            // Loop through all amenities to find parking
            for (WebElement i : amenities) {

                // Check if current amenity is "Parking"
                if (i.getText().equals("Parking")) {

                    // Set parking flag to true
                    isParking = true;

                    // Add parking to parkingList
                    parkingList.add(i.getText());

                    // Log pass status to test report
                    ExtentTestManager.test.pass(
                            "Parking Facility Available");

                    // Log parking amenity found to console
                    logger.info(
                            "Amenity Found : {}",
                            i.getText());

                    // Exit loop once parking is found
                    break;
                }
            }

            // Close the hospital details window
            driver.close();
            // Switch back to the main search results window
            driver.switchTo().window(mainWindow);

            // Log separator line to console
            logger.info(
                    "------------------------------------------------------------");

            // Print separator line to output
            System.out.println(
                    "------------------------------------------------------------");

            // Return true if all three facilities are available
            return isRating && isAvailable && isParking;

        } catch (Exception e) {

            // Log error message if any exception occurs
            logger.error(
                    "Error while checking facilities",
                    e);

            // Log failure to test report
            ExtentTestManager.test.fail(
                    e.getMessage());

            // Return result even if error occurs
            return isRating && isAvailable && isParking;
        }
    }

    // -------------------- Search Hospital --------------------

    // Method to search for hospitals in Bangalore with required facilities
    public void searchHospital() {

        try {

            // Get the location search field element
            WebElement location = locationSearchField;

            // Create Actions object for performing user actions
            Actions action = new Actions(driver);

            // Double click on location field to select all text
            action.doubleClick(location).perform();
            // Delete the selected text using backspace
            action.sendKeys(Keys.BACK_SPACE);

            // Type "Bang" to search for Bangalore
            action.sendKeys(location, "Bang").perform();

            // Log location selection info to test report
            ExtentTestManager.test.info(
                    "Selecting Bangalore Location");

            // Create WebDriverWait object for explicit wait of 30 seconds
            WebDriverWait wait =
                    new WebDriverWait(driver, Duration.ofSeconds(30));

            // Wait for Bangalore city option to be visible
            wait.until(
                    ExpectedConditions.visibilityOf(
                            bangaloreCity));

            // Click on Bangalore city option
            bangaloreCity.click();

            // Log successful Bangalore selection to test report
            ExtentTestManager.test.pass(
                    "Bangalore Selected");

            // Clear the search hospital field before typing
            searchHospitalField.clear();

            // Type "Hospital" in the search field
            searchHospitalField.sendKeys("Hospital");

            // Log hospital search info to test report
            ExtentTestManager.test.info(
                    "Searching Hospitals");

            // Wait for Hospital option to appear in dropdown
            wait.until(
                    ExpectedConditions.visibilityOf(
                            selectHospital));

            // Click on Hospital option to search for hospitals
            selectHospital.click();
            // Commented out screenshot capture
//            ScreenshotUtils.captureScreenshot(driver, "Search_page");

            // Log hospital search started info to test report
            ExtentTestManager.test.info(
                    "Hospital Search Started");

            // Create list to store hospitals that match all criteria
            List<WebElement> hospitalWithFeatured =
                    new ArrayList<>();

            // Log total hospitals found to console
            logger.info(
                    "Total Hospitals Found : {}",
                    hospitalList.size());

            // Log total hospitals found to test report
            ExtentTestManager.test.info(
                    "Total Hospitals Found : "
                            + hospitalList.size());

            // Loop through each hospital in the search results
            for (WebElement i : hospitalList) {

                // Check if hospital has all required facilities
                if (checkFacilities(driver, i)) {

                    // Add hospital to list if it meets all criteria
                    hospitalWithFeatured.add(i);
                }
            }

            // Loop through hospitals that matched all criteria
            for (int i = 0;
                 i < hospitalWithFeatured.size();
                 i++) {

                // Get hospital name element from featured hospital list
                WebElement hospitalNew =
                        hospitalWithFeatured.get(i)
                                .findElement(
                                        hospitalNameElement);

                // Extract hospital name text
                String text =
                        hospitalNew.getText();

                // Write hospital data row to Excel file
                ExcelUtility.writeHospitalRow(
                        i + 1,
                        text,
                        ratingsList.get(i) + " ★",
                        parkingList.get(i) + " Available",
                        open24x7List.get(i)
                );

                // Log hospital details to console with all information
                logger.info(
                        "Excel Row {} -> Hospital: {}, Rating: {}, Parking: {}, Open24x7: {}",
                        i + 1,
                        text,
                        ratingsList.get(i),
                        parkingList.get(i),
                        open24x7List.get(i)
                );

                // Log hospital details to test report
                ExtentTestManager.test.info(
                        "Hospital: " + text
                                + " | Rating: "
                                + ratingsList.get(i)
                                + " | Parking: "
                                + parkingList.get(i)
                                + " | Open24x7: "
                                + open24x7List.get(i)
                );
            }

            // Log total hospitals matching criteria to console
            logger.info(
                    "Hospitals Matching Criteria : {}",
                    hospitalWithFeatured.size());

            // Log total hospitals matching criteria to test report
            ExtentTestManager.test.info(
                    "Hospitals Matching Criteria : "
                            + hospitalWithFeatured.size());

        } catch (Exception e) {

            // Log error message if any exception occurs
            logger.error(
                    "Error occurred in searchHospital()",
                    e);

            // Log failure message to test report
            ExtentTestManager.test.fail(
                    "Error in searchHospital() : "
                            + e.getMessage());
        }
    }
// End of SearchPage class
}