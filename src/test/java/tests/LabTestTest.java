package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.LabTestPage;

public class LabTestTest extends BaseTest {

    @Test(priority = 2)
    public void verifyTopCities() {

        LabTestPage labTestPage = new LabTestPage(driver);

        labTestPage.topCities();


    }
}