package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CorporateWellness;

public class CorporateWellnessTest extends BaseTest {

    @Test
    public void validateCorporateWellnessForm() {

        CorporateWellness page =
                new CorporateWellness(driver);

        page.formDetails();

        Assert.assertTrue(
                page.isPhoneValidationDisplayed(),
                "Phone validation message not displayed"
        );

        Assert.assertTrue(
                page.isEmailValidationDisplayed(),
                "Email validation message not displayed"
        );
    }
}