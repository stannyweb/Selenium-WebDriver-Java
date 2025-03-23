package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.testng.Assert.assertEquals;

public class CartPage {

    private final WebDriverWait wait;

    private final By INPUT_PROMO_CODE = By.cssSelector("input.promoCode");
    private final By PROMO_BUTTON = By.cssSelector("button.promoBtn");
    private final By CODE_APPLIED_INFO = By.cssSelector("span.promoInfo");

    public CartPage( WebDriverWait wait) {
        this.wait = wait;
    }


    public CartPage enterPromoCode() {
        String PROMO_CODE = "rahulshettyacademy";
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
        String EXPECTED_SUCCESS_INFO = "Code applied ..!";
        String INCORRECT_PROMO_SUCCESS_INFO = "Incorrect promo code message";
        assertEquals(actualMessage, EXPECTED_SUCCESS_INFO, INCORRECT_PROMO_SUCCESS_INFO);
        return this;
    }
}

