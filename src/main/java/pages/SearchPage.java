package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ExcelUtility;
import utils.ScreenshotUtils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SearchPage {
    WebDriver driver;
    static List<String> ratingsList = new ArrayList<>();
    static List<String> open24x7List = new ArrayList<>();
    static List<String> parkingList = new ArrayList<>();

    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    By locationSearchFieldElement = By.xpath("//div[@id='c-omni-container']/descendant::input[1]");
    By bangaloreCityElement = By.xpath("//div[contains(text(),'Bangalore')]");
    By searchHospitalFieldElement = By.xpath("//div[@id='c-omni-container']/descendant::input[2]");
    By selectHospitalElement = By.xpath("//div[text()='Hospital'][1]");
    By hospitalListElement = By.tagName("li");
    static By hospitalElement = By.xpath(".//descendant::a");
    static By hospitalNameElement = By.xpath("//h1[@class='c-profile__title']/child::span");
    static By ratingElement = By.xpath("//span[@class='common__star-rating__value']");
    static By hospitalTimingElement = By.xpath("//div[@data-qa-id='timings_title']/following-sibling::div/descendant::p");
    static By readMoreInfoButtonElement = By.xpath("//div[@class='pure-u-1']//span[@data-qa-id='read_more_info']/descendant::span");
    static By amenitiesElment = By.xpath("//div[@class='p-entity__item']//span");

    public static boolean checkFacilities(WebDriver driver, WebElement element) {
        boolean isRating = false;
        boolean isAvailable = false;
        boolean isParking = false;
        try {
            WebElement hospital = element.findElement(hospitalElement);
            String text = hospital.getText();
            System.out.println(text);
            hospital.click();

            String mainWindow = driver.getWindowHandle();
            Set<String> windows = driver.getWindowHandles();
            for (String i : windows) {
                if (!mainWindow.equals(i)) driver.switchTo().window(i);
            }
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

            WebElement hospitalName = driver.findElement(hospitalNameElement);
            String hospitalNameText = hospitalName.getText();


            WebElement rating = wait.until(ExpectedConditions.visibilityOfElementLocated(ratingElement));
            String ratingText = rating.getText();
            float ratingValue = Float.parseFloat(ratingText);
            System.out.println("rating -->" + ratingText);


            /// rating
            if (ratingValue >= 3.5) {
                isRating = true;
                ratingsList.add(ratingText);
                System.out.println("Hospital Rating above 3.5 --> " + hospitalNameText);

            }

            ///  24 x 7 Availability
            WebElement hospitalTiming = driver.findElement(hospitalTimingElement);
            String hospitalTimingText = hospitalTiming.getText();
            System.out.println(hospitalTimingText);

            if (hospitalTimingText.equals("Open 24 x 7")) {
                isAvailable = true;
                open24x7List.add(hospitalTimingText);
                System.out.println("Hospital Hospital available 24x7--> " + hospitalNameText);
            }

            WebElement readMoreInfoButton = driver.findElement(readMoreInfoButtonElement);
            readMoreInfoButton.click();


            List<WebElement> amenities = driver.findElements(amenitiesElment);
            for (WebElement i : amenities) {
                if (i.getText().equals("Parking")) {
                    isParking = true;
                    parkingList.add(i.getText());
                    System.out.println(i.getText());
                    break;
                }
            }

            driver.close();
            driver.switchTo().window(mainWindow);
            System.out.println("----------------------------------------------------------------------------");
            System.out.println("----------------------------------------------------------------------------");
            return isRating && isAvailable && isParking;
        } catch (Exception e) {
            System.out.println("Error in checking facilities-->" + e.getMessage());
            return isRating && isAvailable && isParking;
        }

    }


    public void searchHospital() {
        WebElement location = driver.findElement(locationSearchFieldElement);
        location.clear();

        location.sendKeys("B");
        location.sendKeys("a");
        location.sendKeys("n");
        location.sendKeys("g");


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement bangalore = wait.until(ExpectedConditions.visibilityOfElementLocated(bangaloreCityElement));
        bangalore.click();

        WebElement search = driver.findElement(searchHospitalFieldElement);
        search.clear();
        search.sendKeys("Hospital");
        WebElement hospital = wait.until(ExpectedConditions.visibilityOfElementLocated(selectHospitalElement));
        hospital.click();


        List<WebElement> hospitalList = driver.findElements(hospitalListElement);

        System.out.println("Final size -->" + hospitalList.size());
//        ScreenshotUtils.captureScreenshot(driver);

        List<WebElement> hospitalWithFeatured = new ArrayList<>();
        for (WebElement i : hospitalList) {
            if (checkFacilities(driver, i)) {
                hospitalWithFeatured.add(i);
            }
        }

        for (int i = 0; i < hospitalWithFeatured.size(); i++) {
            WebElement hospitalNew = hospitalWithFeatured.get(i).findElement(hospitalElement);
            String text = hospitalNew.getText();
            System.out.println("Hospital with Featured -->" + text);

            ExcelUtility.setCellData(i + 1, 0, text);
            ExcelUtility.setCellData(i + 1, 1, ratingsList.get(i)+ " ★ ");
            ExcelUtility.setCellData(i + 1, 2, parkingList.get(i)+ " Available ");
            ExcelUtility.setCellData(i + 1, 3, open24x7List.get(i));

        }
        System.out.println("hospital With Featured " + hospitalWithFeatured.size());
//        driver.navigate().back();
    }
}