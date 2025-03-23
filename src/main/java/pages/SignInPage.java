package pages;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.LoggerFactory;

import java.util.NoSuchElementException;
import java.util.logging.Logger;

public class SignInPage {

    private WebDriver driver;
    private WebDriverWait wait;


    private final Logger logger = Logger.getLogger(SignInPage.class.getName());
    //LOCATORS
    private final By USERNAME_INPUT_FIELD = By.id("username");
    private final By PASSWORD_INPUT_FIELD = By.id("password");
    private final By USER_RADIO_BUTTON = By.cssSelector("input[type='radio'][value='user']");
    private final By SELECT_OPTIONS = By.cssSelector("select[class='form-control']");
    private final By AGREE_TERMS = By.id("terms");
    private final By SIGN_IN_BUTTON = By.id("signInBtn");
    private final By ALERT_ACCEPT_BUTTON = By.id("okayBtn");

    private final String USERNAME_VALUE = "rahulshettyacademy";
    private final String PASSWORD_VALUE = "learning";
    private final String NO_ELEMENT_PRESENT = "Element with locator '%s' is not present";

    public SignInPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void performSignIn() {
        enterUsernameField();
        enterPasswordField();
        clickUserType();
        selectUserType();
        agreeUserTerms();
    }

    private void enterUsernameField() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(USERNAME_INPUT_FIELD)).sendKeys(USERNAME_VALUE);
        logger.info("Username " + USERNAME_VALUE + " has been successfully written.");
    }

    private void enterPasswordField() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(PASSWORD_INPUT_FIELD)).sendKeys(PASSWORD_VALUE);
        logger.info("Password " + PASSWORD_VALUE + " has been successfully written.");
    }

    private void clickUserType() {
        try {
            WebElement element = driver.findElement(USER_RADIO_BUTTON);
            element.click();
            logger.info("User radio button has been clicked.");
            wait.until(ExpectedConditions.visibilityOfElementLocated(ALERT_ACCEPT_BUTTON)).click();

        } catch (NoSuchElementException e) {
            System.out.println("No alert present");
        }
    }

    private void selectUserType() {
        String selectOptionText = "Teacher";
        try {
            WebElement selectDropdown = wait.until(ExpectedConditions.elementToBeClickable(SELECT_OPTIONS));

            Select select = new Select(selectDropdown);
            select.selectByVisibleText(selectOptionText);

            WebElement firstSelectedOption = select.getFirstSelectedOption();
            logger.info("Selected option is: " + firstSelectedOption.getText());
        } catch (NoSuchElementException | TimeoutException e) {
            logger.severe(String.format(NO_ELEMENT_PRESENT, SELECT_OPTIONS));
        }
    }

    private void agreeUserTerms() {
        try {
            WebElement agreeTermsCheckbox = wait.until(ExpectedConditions.elementToBeClickable(AGREE_TERMS));

            if (!agreeTermsCheckbox.isEnabled()) {
                logger.warning(String.format("Checkbox '%s' is not enabled. ", AGREE_TERMS));
            }

            agreeTermsCheckbox.click();

            if (!agreeTermsCheckbox.isSelected()) {
                logger.warning(String.format("Checkbox '%s' was not selected after click.", AGREE_TERMS));
            }

            logger.info(String.format("Checkbox '%s' has been clicked and selected.", AGREE_TERMS));

        } catch (NoSuchElementException e) {
            logger.severe(String.format(NO_ELEMENT_PRESENT, AGREE_TERMS));
        } catch (TimeoutException e) {
            logger.severe(String.format("Checkbox '%s' was not clickable in the time limit.", AGREE_TERMS));
        }
    }

    public ShopPage clickSignInButton() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(SIGN_IN_BUTTON)).click();
        } catch (TimeoutException | NoSuchElementException e) {
            logger.severe(String.format(NO_ELEMENT_PRESENT, SIGN_IN_BUTTON));
        }
        return new ShopPage(driver, wait);
    }
}
