package tests;

import base.BaseTest;
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

public class PractoTest extends BaseTest {

    private SearchPage searchPage;
    private LabTestPage labTestPage;
    private CorporateWellness corporateWellness;

    @BeforeSuite
    public void beforeSuite() {

        System.out.println("========== Test Suite Started ==========");
    }

    @BeforeClass
    public void beforeClass() {

        System.out.println("========== Test Class Started ==========");
    }

//    @Test(priority = 1)
//    public void verifyHospitalSearch() {
//
//        searchPage = new SearchPage(driver);
//
//        searchPage.searchHospital();
//
//        System.out.println("Hospital Search Completed");
//    }
@Test(priority=1)
public void verifyHospitalSearch() {

    ExtentTestManager.test =
            extent.createTest(
                    "Verify Hospital Search"
            );

    searchPage = new SearchPage(driver);

    ExtentTestManager.test.info(
            "Searching Hospital in Bangalore"
    );

    searchPage.searchHospital();

    ExtentTestManager.test.pass(
            "Hospital Search Completed"
    );
}


//    @Test(priority = 2)
//    public void verifyTopCities() {
//
//        labTestPage = new LabTestPage(driver);
//
//        labTestPage.topCities();
//
//        System.out.println("Top Cities Retrieved Successfully");
//    }


    @Test(priority=2)
    public void verifyTopCities() {

        ExtentTestManager.test =
                extent.createTest(
                        "Verify Top Cities"
                );

        labTestPage = new LabTestPage(driver);

        ExtentTestManager.test.info(
                "Opening Lab Tests Page"
        );

        labTestPage.topCities();

        ExtentTestManager.test.pass(
                "Top Cities Retrieved"
        );
    }



//    @Test(priority = 3)
//    public void validateCorporateWellnessForm() {
//
//        corporateWellness = new CorporateWellness(driver);
//
//        corporateWellness.formDetails();
//
//        Assert.assertTrue(
//                corporateWellness.isPhoneValidationDisplayed(),
//                "Phone validation message not displayed"
//        );
//
//        Assert.assertTrue(
//                corporateWellness.isEmailValidationDisplayed(),
//                "Email validation message not displayed"
//        );
//
//        System.out.println("Corporate Wellness Validation Successful");
//    }

    @Test(priority=3)
    public void validateCorporateWellnessForm() {

        ExtentTestManager.test =
                extent.createTest(
                        "Corporate Wellness Validation"
                );

        corporateWellness =
                new CorporateWellness(driver);

        ExtentTestManager.test.info(
                "Filling Corporate Form"
        );

        corporateWellness.formDetails();

        ExtentTestManager.test.info(
                "Checking Phone Validation"
        );

        Assert.assertTrue(
                corporateWellness.isPhoneValidationDisplayed(),
                "Phone validation message not displayed"
        );

        ExtentTestManager.test.info(
                "Checking Email Validation"
        );

        Assert.assertTrue(
                corporateWellness.isEmailValidationDisplayed(),
                "Email validation message not displayed"
        );

        ExtentTestManager.test.pass(
                "Validation Successful"
        );
    }

    @AfterClass
    public void afterClass() {

        System.out.println("========== Test Class Completed ==========");
    }

    @AfterSuite
    public void afterSuite() {

        System.out.println("========== Test Suite Completed ==========");
    }

}