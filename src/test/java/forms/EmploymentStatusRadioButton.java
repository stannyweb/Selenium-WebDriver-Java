package forms;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;
import java.util.logging.Logger;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;


/**
 * Class for managing the "Employment Status" radio button.
 * Provides methods for selecting the radio button and handling errors.
 */
public class EmploymentStatusRadioButton {

    private static final By EMPLOYMENT_STATUS_LOCATOR = By.id("inlineRadio2"); // Заменете с реалния ID
    private static final String EMPLOYMENT_STATUS_SELECTED = "Employment status selected.";
    private static final String EMPLOYMENT_STATUS_NOT_FOUND = "Employment status element not found.";
    private static final String EMPLOYMENT_STATUS_NOT_CLICKABLE = "Employment status element not clickable.";
    private static final String EMPLOYMENT_STATUS_SELECTION_FAILED = "Employment status selection failed.";

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Logger logger = Logger.getLogger(EmploymentStatusRadioButton.class.getName());

    /**
     * Constructor for the EmploymentStatusRadioButton class.
     *
     * @param driver WebDriver instance.
     * @param wait WebDriverWait instance.
     */
    public EmploymentStatusRadioButton(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    /**
     * Selects the "Employment Status" radio button.
     * Logs a success or error message.
     * Throws AssertError on failure.
     */
    public void selectEmploymentStatus() {
        try {
            WebElement employmentStatus =
                    wait.until(ExpectedConditions.elementToBeClickable(EMPLOYMENT_STATUS_LOCATOR));

            if (!employmentStatus.isSelected()) {
                employmentStatus.click();
                logger.info(EMPLOYMENT_STATUS_SELECTED);
                assertTrue(employmentStatus.isSelected(), EMPLOYMENT_STATUS_SELECTION_FAILED);
            }
        } catch (TimeoutException e) {
            logger.severe(EMPLOYMENT_STATUS_NOT_FOUND + e.getMessage());
            fail(EMPLOYMENT_STATUS_NOT_FOUND);
        } catch (NoSuchElementException e) {
            logger.severe(EMPLOYMENT_STATUS_NOT_FOUND + e.getMessage());
            fail(EMPLOYMENT_STATUS_NOT_FOUND);
        } catch (ElementNotInteractableException e) {
            logger.severe(EMPLOYMENT_STATUS_NOT_CLICKABLE + e.getMessage());
            fail(EMPLOYMENT_STATUS_NOT_CLICKABLE);
        }
    }
}
