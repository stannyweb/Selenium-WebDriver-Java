package assignments;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class Exercise1Test {

    private final String baseURL = "https://the-internet.herokuapp.com/";
    private final By MULTIPLY_WINDOWS = By.xpath("//a[text()='Multiple Windows']");

    private WebDriver driver;
    private WebDriverWait wait;
    private Exercise1MultiplyWindowsPage parentChildTextExtraction;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        parentChildTextExtraction = new Exercise1MultiplyWindowsPage(driver, wait);

        driver.get(baseURL);
    }

    @Test
    public void verifyNewWindowText() {
        clickMultiplyWindowsLink();
        parentChildTextExtraction.manageTextFromChildAndParentWindows();
    }


    private void clickMultiplyWindowsLink() {
        WebElement multiplyWindows = wait.until(ExpectedConditions.visibilityOfElementLocated(MULTIPLY_WINDOWS));
        multiplyWindows.click();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null)
            driver.quit();
    }

}
