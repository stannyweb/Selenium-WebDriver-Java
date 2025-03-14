package dropdowns;

import base.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertEquals;

public class UpdatingDropdownTest extends BaseTest {


    @Test
    public void updatePassengersTest() {
        getPassengersDropdown();
        validateNumberOfPassengers("6", "2");
    }


    @BeforeMethod
    @Override
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://www.spicejet.com/");
    }


    private void getPassengersDropdown() {
        final int ADULT_COUNT = 5;
        final int CHILD_COUNT = 2;

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text() ='1 Adult']"))).click();

//        Adults section
        for (int i = 0; i < ADULT_COUNT; i++) {
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[@data-testid='Adult-testID-plus-one-cta']"))).click();
        }
//        Children section
        for (int i = 0; i < CHILD_COUNT; i++) {
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[@data-testid='Children-testID-plus-one-cta']"))).click();
        }

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@data-testid='home-page-travellers-done-cta']"))).click();
    }

    private void validateNumberOfPassengers(String adults, String children) {

        String errMessage = "Incorrect number of passengers";
        String expectedAdults = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("div[class='css-1dbjc4n r-k8qxaj r-d9fdf6'] div:nth-child(1) div:nth-child(2) " +
                                "div:nth-child(2) div:nth-child(1)"))).getText();

        String expectedChildren = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[@class='css-1dbjc4n r-k8qxaj r-d9fdf6']/div[@class='css-1dbjc4n r-18u37iz " +
                                "r-1wtj0ep r-1x0uki6'][2]/div[@class='css-1dbjc4n r-1awozwy " +
                                "r-18u37iz']/div[@class='css-1dbjc4n r-1kz6sp']/div[@class='css-76zvg2 r-homxoj " +
                                "r-adyw6z r-q4m81j']"))).getText();

        assertEquals(expectedAdults, adults, errMessage);
        assertEquals(expectedChildren, children, errMessage);

        System.out.println("Adults passengers: " + adults);
        System.out.println("Children Passengers: " + children);
    }


}
