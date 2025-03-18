package forms;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.logging.Logger;

import static org.testng.Assert.*;

public class SubmitButton {

    private static final By SUBMIT_BUTTON_LOCATOR = By.cssSelector("input[class='btn btn-success']");
    private static final By ALERT_SUCCESS_LOCATOR = By.cssSelector("div[class='alert alert-success " +
            "alert-dismissible']");
    private static final String ALERT_SUCCESS_TEXT = "×\nSuccess! The Form has been submitted successfully!.";
    private static final String SUBMIT_BUTTON_CLICKED = "Submit button clicked successfully.";
    private static final String SUBMIT_BUTTON_NOT_FOUND = "Submit button not found.";
    private static final String SUBMIT_BUTTON_NOT_CLICKABLE = "Submit button not clickable.";
    private static final String ALERT_SUCCESS_NOT_FOUND = "Success alert not found.";
    private static final String ALERT_SUCCESS_TEXT_MISMATCH = "Success alert text does not match.";

    private final Logger logger = Logger.getLogger(SubmitButton.class.getName());

    private WebDriver driver;
    private WebDriverWait wait;

    public SubmitButton(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void clickSubmitButton() {
        try {
            WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(SUBMIT_BUTTON_LOCATOR));
            submitButton.click();
            logger.info(SUBMIT_BUTTON_CLICKED);

            WebElement successAlert = wait.until(ExpectedConditions.visibilityOfElementLocated(ALERT_SUCCESS_LOCATOR));
            assertNotNull(successAlert, ALERT_SUCCESS_NOT_FOUND);
            assertEquals(successAlert.getText(), ALERT_SUCCESS_TEXT, ALERT_SUCCESS_TEXT_MISMATCH);

        } catch (TimeoutException e) {
            logger.severe(SUBMIT_BUTTON_NOT_FOUND + e.getMessage());
            fail(SUBMIT_BUTTON_NOT_FOUND);
        } catch (NoSuchElementException e) {
            logger.severe(SUBMIT_BUTTON_NOT_CLICKABLE + e.getMessage());
            fail(SUBMIT_BUTTON_NOT_FOUND);
        }
    }
}
