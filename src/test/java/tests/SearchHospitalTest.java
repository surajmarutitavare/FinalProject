package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.SearchPage;

public class SearchHospitalTest extends BaseTest {

    @Test(priority = 1)
    public void verifyHospitalSearch() {

        SearchPage searchPage = new SearchPage(driver);

        searchPage.searchHospital();
    }

}