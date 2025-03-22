package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.testng.Assert.assertEquals;

public class CartPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private final By INPUT_PROMO_CODE = By.cssSelector("input.promoCode");
    private final By PROMO_BUTTON = By.cssSelector("button.promoBtn");
    private final String PROMO_CODE = "rahulshettyacademy";
    private final By CODE_APPLIED_INFO = By.cssSelector("span.promoInfo");
    private final String EXPECTED_SUCCESS_INFO = "Code applied ..!";
    private final String INCORRECT_PROMO_SUCCESS_INFO = "Incorrect promo code message";

    public CartPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }


    public CartPage enterPromoCode() {
        wait.until(ExpectedConditions.elementToBeClickable(INPUT_PROMO_CODE)).sendKeys(PROMO_CODE);
        return this;
    }

    public CartPage applyDiscountWithPromoCode() {
        wait.until(ExpectedConditions.elementToBeClickable(PROMO_BUTTON)).click();
        return this;

    }

    public String getDiscountPromoCodeSuccessMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(CODE_APPLIED_INFO)).getText();
    }

    public CartPage validatePromoCodeMessage() {
        String actualMessage = getDiscountPromoCodeSuccessMessage();
        assertEquals(actualMessage, EXPECTED_SUCCESS_INFO, INCORRECT_PROMO_SUCCESS_INFO);
        return this;
    }
}

