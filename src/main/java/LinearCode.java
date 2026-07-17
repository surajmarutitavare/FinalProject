import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class LinearCode {

    public static boolean checkFacilities(WebDriver driver, WebElement element) {
        boolean isRating = false;
        boolean isAvailable = false;
        boolean isParking = false;
        try {
            WebElement hospital = element.findElement(By.xpath(".//descendant::a"));
            String text = hospital.getText();
            System.out.println(text);
            hospital.click();

            String mainWindow = driver.getWindowHandle();
            Set<String> windows = driver.getWindowHandles();
//            System.out.println("main window-->" + mainWindow);
            for (String i : windows) {
//                System.out.println("new tab-->" + i);
                if (!mainWindow.equals(i)) driver.switchTo().window(i);
            }
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

            WebElement rating = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='common__star-rating__value']")));
            float ratingValue = Float.parseFloat(rating.getText());
            System.out.println("rating -->" + rating.getText());
            WebElement hospitalName = driver.findElement(By.xpath("//h1[@class='c-profile__title']/child::span"));
            String hospitalNameText = hospitalName.getText();

            /// rating
            if (ratingValue >= 3.5) {
                isRating = true;
                System.out.println("Hospital Rating above 3.5 --> " + hospitalNameText);
            }

            ///  24 x 7 Availability
            WebElement hospitalTiming = driver.findElement(By.xpath("//div[@data-qa-id='timings_title']/following-sibling::div/descendant::p"));
            String hospitalTimingText = hospitalTiming.getText();
            System.out.println(hospitalTimingText);
            if (hospitalTimingText.equals("Open 24 x 7")) {
                isAvailable = true;
                System.out.println("Hospital Hospital available 24x7--> " + hospitalNameText);
            }

            WebElement readMoreInfoButton = driver.findElement(By.xpath("//div[@class='pure-u-1']//span[@data-qa-id='read_more_info']/descendant::span"));

            readMoreInfoButton.click();


            List<WebElement> amenities = driver.findElements(By.xpath("//div[@class='p-entity__item']//span"));
            for (WebElement i : amenities) {
                if (i.getText().equals("Parking")) {
                    isParking = true;
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

    public static void main(String[] args) {
        System.out.println("hello");
        WebDriver driver;
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.practo.com/");
//        driver.get("https://www.practo.com/plus/corporate/");

        WebElement locationElement = driver.findElement(By.xpath("//div[@id='c-omni-container']/descendant::input[1]"));
        String locationElementText = locationElement.getAttribute("value");
        System.out.println("location element text-->" + locationElementText);
//        if (locationElementText.length() > 1) {
        locationElement.clear();
//        } else {
        locationElement.clear();
//        }
        locationElement.sendKeys("B");
        locationElement.sendKeys("a");
        locationElement.sendKeys("n");
        locationElement.sendKeys("g");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement bangaloreElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Bangalore')]")));
        bangaloreElement.click();

        WebElement searchElement = driver.findElement(By.xpath("//div[@id='c-omni-container']/descendant::input[2]"));
        searchElement.clear();
        searchElement.sendKeys("Hospital");
        WebElement hospitalElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Hospital'][1]")));
        hospitalElement.click();


//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        long pageHeight = (long) js.executeScript("return document.body.scrollHeight") - 1609;
//        System.out.println(pageHeight);
        List<WebElement> hospitalList = driver.findElements(By.tagName("li"));
//        System.out.println("1 -->" + hospitalList.size());
//        js.executeScript("window.scrollTo(0, 3000)");
//        hospitalList = driver.findElements(By.tagName("li"));
//        System.out.println("2 -->" + hospitalList.size());
//        Thread.sleep(3000);
//        js.executeScript("window.scrollTo(0, 3500)");
//        hospitalList = driver.findElements(By.tagName("li"));
//        System.out.println("3 -->" + hospitalList.size());
//        long pageHeight1 = (long) js.executeScript("return document.body.scrollHeight")-1609;
//        System.out.println(pageHeight1);

//        js.executeScript("window.scrollTo(0, 3500)");

//        while (hospitalList.size() <= 20) {
//            long pageHeight = (long) js.executeScript("return document.body.scrollHeight");
//
//            long pageHeight1 = pageHeight - 1609;
//            js.executeScript("window.scrollTo(0, arguments[0])", pageHeight1);
//            hospitalList = driver.findElements(By.tagName("li"));
//            System.out.println(hospitalList.size());
//        }
        System.out.println("Final size -->" + hospitalList.size());

        List<WebElement> hospitalWithFeatured = new ArrayList<>();
        for (WebElement i : hospitalList) {
            if (checkFacilities(driver, i)) {
                hospitalWithFeatured.add(i);
            }
        }
        System.out.println("hospital With Featured " + hospitalWithFeatured.size());

        driver.navigate().back();


//        ---------------------------------------------------------------------------------------

        WebElement labTests = driver.findElement(By.xpath("//a/child::div[contains(text(),'Lab Tests')]"));
        labTests.click();

        List<WebElement> Cities = driver.findElements(By.xpath("//ul//li[@class='u-text--center']"));
//        List<WebElement> Cities = driver.findElements(By.xpath("//ul[@class='u-br-rule u-marginb--std-half u-pointer u-padb--dbl o-flex o-flex__justify--between']/li[@class='u-text--center']"));
//
        System.out.println("---City names---");
        for (WebElement i : Cities) {
            System.out.print(i.getText() + " ");
        }

        System.out.println();

        driver.navigate().back();


//        --------------------------------------------------------------------------------------------------------------------



        WebElement corporateElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='nav-right text-right']/descendant::span[contains(text(),'For Corporates')]")));
        corporateElement.click();
//        WebElement corporateElement = driver.findElement(By.xpath("//div[@class='nav-right text-right']/descendant::span[contains(text(),'For Corporates')]"));
//        corporateElement.clear();

        WebElement corporateWellnessElement = driver.findElement(By.linkText("Health & Wellness Plans"));
        corporateWellnessElement.click();


        WebElement nameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='name']")));
        nameElement.click();
        nameElement.sendKeys("Mrunmayee");

        WebElement organizationName = driver.findElement(By.xpath("//input[@id='organizationName']"));
        organizationName.sendKeys("Cognizant");

        WebElement contactNumber1 = driver.findElement(By.xpath("//input[@id='contactNumber']"));
        contactNumber1.sendKeys("90876534231");
        String contactNumber1ClassAttribute = contactNumber1.getAttribute("class");
        System.out.println(contactNumber1ClassAttribute);

        WebElement contactNumber2 = driver.findElement(By.xpath("//input[@id='contactNumber']"));
        String contactNumber2ClassAttribute = contactNumber2.getAttribute("class");
        System.out.println(contactNumber2ClassAttribute);


        if (contactNumber2ClassAttribute != null && contactNumber2ClassAttribute.contains("error")) {
            System.out.println("Test Passed ✅✅✅");

        }

        WebElement officialEmailId1 = driver.findElement(By.xpath("//input[@id='officialEmailId']"));
        String officialEmailId1ClassAttribute = officialEmailId1.getAttribute("class");
        System.out.println(officialEmailId1ClassAttribute);
        officialEmailId1.sendKeys("xyz@gmail.");

        WebElement officialEmailId2 = driver.findElement(By.xpath("//input[@id='officialEmailId']"));
        String officialEmailId2ClassAttribute = officialEmailId2.getAttribute("class");
        System.out.println(officialEmailId2ClassAttribute);

        if (officialEmailId2ClassAttribute != null && officialEmailId2ClassAttribute.contains("error")) {
            System.out.println("Test Passed ✅✅✅");

        }

        WebElement organizationSize = driver.findElement(By.xpath("//select[@id='organizationSize']"));
        organizationSize.click();
        Select select = new Select(organizationSize);
        select.selectByIndex(2);

        WebElement interestedInButton = driver.findElement(By.xpath("//select[@id='interestedIn']"));
        interestedInButton.click();
        Select select1 = new Select(interestedInButton);
        select1.selectByVisibleText("Taking a demo");

        WebElement scheduleDemo = driver.findElement(By.xpath("//button[contains(text(),'Schedule a demo')]"));
        scheduleDemo.click();

    }
}


