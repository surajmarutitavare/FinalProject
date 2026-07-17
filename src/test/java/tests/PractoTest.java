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

    @Test(priority = 1)
    public void verifyHospitalSearch() {

        searchPage = new SearchPage(driver);

        searchPage.searchHospital();

        System.out.println("Hospital Search Completed");
    }

    @Test(priority = 2)
    public void verifyTopCities() {

        labTestPage = new LabTestPage(driver);

        labTestPage.topCities();

        System.out.println("Top Cities Retrieved Successfully");
    }

    @Test(priority = 3)
    public void validateCorporateWellnessForm() {

        corporateWellness = new CorporateWellness(driver);

        corporateWellness.formDetails();

        Assert.assertTrue(
                corporateWellness.isPhoneValidationDisplayed(),
                "Phone validation message not displayed"
        );

        Assert.assertTrue(
                corporateWellness.isEmailValidationDisplayed(),
                "Email validation message not displayed"
        );

        System.out.println("Corporate Wellness Validation Successful");
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