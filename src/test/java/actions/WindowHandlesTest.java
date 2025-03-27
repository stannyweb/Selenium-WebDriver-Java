package actions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import signing.SignInExampleTest;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import static org.apache.commons.lang3.StringUtils.trim;

public class WindowHandlesTest {

    private final String baseURL = "https://rahulshettyacademy.com/loginpagePractise/";
    private WebDriver driver;
    private WebDriverWait wait;

    private final By BLINKING_TEXT = By.cssSelector("a[class='blinkingText']");
    private final By CHILD_WINDOW_TEXT = By.cssSelector(".im-para.red");
    private final By USERNAME_FIELD = By.id("username");

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();

        driver.get(baseURL);
    }

    private void clickOnBlinkingText() {
        WebElement blinkingText = wait.until(ExpectedConditions.visibilityOfElementLocated(BLINKING_TEXT));
        blinkingText.click();
    }

    @Test
    public void verifyTextFromChildWindow() {
        clickOnBlinkingText();
        handleChildWindows();
    }

    private void handleChildWindows() {
        Set<String> windowHandles = driver.getWindowHandles();
        Iterator<String> iterator = windowHandles.iterator();
        String parentWindow = iterator.next();
        String childWindow = "";

        while (iterator.hasNext()) {
            childWindow = iterator.next();
            driver.switchTo().window(childWindow);
        }

        String extractTextFromChildWindow =
                wait.until(ExpectedConditions.visibilityOfElementLocated(CHILD_WINDOW_TEXT))
                        .getText().split("at")[1].trim().split(" ")[0];
        driver.switchTo().window(parentWindow);
        wait.until(ExpectedConditions.visibilityOfElementLocated(USERNAME_FIELD)).sendKeys(extractTextFromChildWindow);
    }
}
