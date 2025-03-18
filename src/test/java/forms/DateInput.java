package forms; // Или друг подходящ пакет

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.NoSuchElementException;
import java.util.logging.Logger;

import static org.testng.Assert.fail;


public class DateInput {

    private static final By DATE_INPUT_LOCATOR = By.cssSelector("input[type='date']"); // Заменете с реалния ID
    private static final String DATE_INPUT_FILLED = "Date input filled successfully.";
    private static final String DATE_INPUT_NOT_FOUND = "Date input element not found.";

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Logger logger = Logger.getLogger(DateInput.class.getName());

    /**
     * Constructor for class DateInput.
     *
     * @param driver WebDriver инстанция.
     * @param wait WebDriverWait инстанция.
     */
    public DateInput(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    /**
     * @param date Date for input.
     */
    public void setDateInput(String date) {
        try {
            WebElement dateInput = wait.until(ExpectedConditions.elementToBeClickable(DATE_INPUT_LOCATOR));
            dateInput.sendKeys(date);
            logger.info(DATE_INPUT_FILLED);
        } catch (TimeoutException e) {
            logger.severe(DATE_INPUT_NOT_FOUND + e.getMessage());
            fail(DATE_INPUT_NOT_FOUND);
        } catch (NoSuchElementException e) {
            logger.severe(DATE_INPUT_NOT_FOUND + e.getMessage());
            fail(DATE_INPUT_NOT_FOUND);
        }
    }
}