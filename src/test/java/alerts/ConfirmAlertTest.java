package alerts;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class ConfirmAlertTest {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(ConfirmAlertTest.class);
    private final String baseURL = "https://rahulshettyacademy.com/AutomationPractice/";

    private final By INPUT_NAME = By.id("name");
    private final By ALERT_BUTTON = By.id("alertbtn");
    private final String INPUT_NAME_CLICKED = "Clicked on the input field";
    private final String BUTTON_CLICKED = "The button for the alert has been clicked";
    private final String SUGGESTED_NAME = "Stan";
    private final String ALERT_TEXT = "Hello Stan, share this practice page and share your knowledge";
    private final String ALERT_TEXT_MISMATCH = "The alert text is incorrect!";
    private final String ALERT_TEXT_NOT_FOUND = "Alert message with locator '%s' was not found.";
    private final String ALERT_ACCEPTED = "Alert accepted.";

    private WebDriver driver;
    private WebDriverWait wait;
    private final Logger logger = Logger.getLogger(ConfirmAlertTest.class.getName());

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get(baseURL);
    }

    @Test
    public void testAlertText() {
        String alertText = getAlertText();
        validateAlertText(alertText);
    }

    private String getAlertText() {

        try {
            WebElement inputName = wait.until(ExpectedConditions.elementToBeClickable(INPUT_NAME));
            inputName.sendKeys(SUGGESTED_NAME);
            logger.info(INPUT_NAME_CLICKED);

            WebElement alertBtn = wait.until(ExpectedConditions.elementToBeClickable(ALERT_BUTTON));
            alertBtn.click();
            logger.info(BUTTON_CLICKED);
            logger.info(ALERT_ACCEPTED);

        } catch (NoSuchElementException e) {
            logger.severe(ALERT_TEXT_NOT_FOUND);
        } catch (TimeoutException e) {
            logger.severe(String.format(ALERT_TEXT_NOT_FOUND, ALERT_BUTTON + " " + e.getMessage()));
            fail(String.format(ALERT_TEXT_NOT_FOUND, ALERT_BUTTON));
        }

        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        alert.accept();
        return alertText;
    }

    private void validateAlertText(String actualText) {
        assertEquals(actualText, ALERT_TEXT, ALERT_TEXT_MISMATCH);
    }
}
