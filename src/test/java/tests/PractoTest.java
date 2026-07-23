// Package name for test classes
package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import pages.CorporateWellness;
import pages.LabTestPage;
import pages.SearchPage;
import reports.ExtentTestManager;

// Test class - inherits from BaseTest to get setup/cleanup methods
// WHY: BaseTest has common setup like browser initialization, screenshot capture
// HOW: Extends BaseTest so we don't repeat code in every test class
public class PractoTest extends BaseTest {


    //  Separates page elements from test logic (Page Object Model pattern)
    //  Stores all hospital search methods in SearchPage class
    private SearchPage searchPage;
    

    //  Organized code - each page has its own class
    //  Reusable methods for lab test operations
    private LabTestPage labTestPage;

    //  Centralizes form handling in one place
    //  Contains form filling and validation methods
    private CorporateWellness corporateWellness;



    // @BeforeClass annotation - runs ONCE before all tests in this class
    //Prints message showing class initialization
    @BeforeClass
    public void beforeClass() {

        // Print message to console
        System.out.println("========== Test Class Started ==========");
    }

    // priority=1 means it runs FIRST among all tests
    //Checks if website title is correct
    @Test(priority = 1)
    public void verifyUrl() {

        // Create a new test case in the report with name "Verify URL"
        // WHY: Test report needs to track each test separately
        // HOW: ExtentTestManager creates test entry for reporting
        ExtentTestManager.test =
                extent.createTest("Verify URL");

        // Expected title of Practo homepage
        String expectedTitle =
                "Practo | Video Consultation with Doctors, Book Doctor Appointments, Order Medicine, Diagnostic Tests";

        // Get actual page title from browser
        // WHY: Compare expected vs actual to verify page loaded
        // HOW: driver.getTitle() retrieves the browser tab title
        String actualTitle = driver.getTitle();

        // Log info message to test report (what we're testing)
        // WHY: Report documents what test is checking
        // HOW: Info is informational log level
        ExtentTestManager.test.info("Verifying page title");

        // Assert that title is not null (page loaded)
        // WHY: Null check ensures page loaded properly
        // HOW: Fail test if title is null
        Assert.assertNotNull(actualTitle);
        
        // Assert that actual title equals expected title
        // WHY: Verify correct page opened
        // HOW: trim() removes extra spaces before comparing
        Assert.assertEquals(actualTitle.trim(), expectedTitle);

        // Log PASS status to report when assertion succeeds
        // WHY: Report shows test passed with proof
        // HOW: Pass message indicates test completed successfully
        ExtentTestManager.test.pass("Title verified successfully");
    }

    // @Test with priority=2 - runs SECOND
    // Creates SearchPage object and calls search method
    @Test(priority = 2)
    public void verifyHospitalSearch() {

        // Create new test in report
        ExtentTestManager.test =
                extent.createTest(
                        "Verify Hospital Search"
                );

        // Initialize SearchPage object with WebDriver
        searchPage = new SearchPage(driver);

        // Log starting message to report
        ExtentTestManager.test.info(
                "Searching Hospital in Bangalore"
        );

        // Call method to search hospitals
        searchPage.searchHospital();

        // Log completion message
        ExtentTestManager.test.pass(
                "Hospital Search Completed"
        );
    }


    // @Test with priority=3 - runs THIRD
    //Creates LabTestPage object and retrieves cities
    @Test(priority = 3)
    public void verifyTopCities() {

        // Create new test in report for lab tests
        ExtentTestManager.test =
                extent.createTest(
                        "Verify Top Cities"
                );

        // Initialize LabTestPage object with WebDriver
        labTestPage = new LabTestPage(driver);

        // Log starting message
        ExtentTestManager.test.info(
                "Opening Lab Tests Page"
        );

        // Call method to get top cities
        labTestPage.topCities();

        // Log completion message
        ExtentTestManager.test.pass(
                "Top Cities Retrieved"
        );
    }


    // @Test with priority=4 - runs FOURTH (last)
    // Fills form and validates error messages
    @Test(priority = 4)
    public void validateCorporateWellnessForm() {

        // Create new test in report for form validation
        ExtentTestManager.test =
                extent.createTest(
                        "Corporate Wellness Validation"
                );

        // Initialize CorporateWellness page object with WebDriver
        corporateWellness =
                new CorporateWellness(driver);

        // Log form filling starting
        ExtentTestManager.test.info(
                "Filling Corporate Form"
        );

        // Call method to fill form with test data
        corporateWellness.formDetails();

        // Log phone validation check starting
        ExtentTestManager.test.info(
                "Checking Phone Validation"
        );

        // Assert phone validation error is displayed
        // Verify validation works (phone should show error)
        Assert.assertTrue(
                corporateWellness.isPhoneValidationDisplayed(),
                "Phone validation message not displayed"
        );

        // Log email validation check starting
        ExtentTestManager.test.info(
                "Checking Email Validation"
        );

        // Assert email validation error is displayed
        // Verify email validation works
        Assert.assertTrue(
                corporateWellness.isEmailValidationDisplayed(),
                "Email validation message not displayed"
        );

        // Log all validations passed
        ExtentTestManager.test.pass(
                "Validation Successful"
        );
    }

    // @AfterClass annotation - runs ONCE after all tests in this class complete
    //Prints message showing class completed
    @AfterClass
    public void afterClass() {

        // Print completion message to console
        System.out.println("========== Test Class Completed ==========");
    }

    // @AfterSuite annotation - runs ONCE after entire test suite finishes
    // Prints suite completion message
    @AfterSuite
    public void afterSuite() {

        // Print final completion message
        System.out.println("========== Test Suite Completed ==========");
    }

}