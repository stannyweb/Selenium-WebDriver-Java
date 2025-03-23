package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.fail;

public class ShopPage {

    private final WebDriverWait wait;

    private final By ADD_BUTTON = By.cssSelector("button[class='btn btn-info']");
    private final By CHECKOUT_BUTTON = By.cssSelector("a[class='nav-link btn btn-primary']");

    private final Logger logger = Logger.getLogger(ShopPage.class.getName());

    public ShopPage(WebDriver driver, WebDriverWait wait) {
        this.wait = wait;
    }

    public void addItemsToCartAndVerifyCheckout() {
        try {
            addItemsInTheCart();
            assertCheckoutCart();
        } catch (TimeoutException e) {
            logger.severe("TimeoutException occurred: " + e.getMessage());
            fail("TimeoutException occurred during cart operations.");
        }
    }

    private void addItemsInTheCart() {
        List<WebElement> shoppingCartItems =
                wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ADD_BUTTON));

        for (WebElement item : shoppingCartItems) {
            item.click();
            logger.info("Clicked on 'Add to Cart' button.");
        }
    }

    private void assertCheckoutCart() {
        WebElement checkoutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(CHECKOUT_BUTTON));
        String checkoutCartText = checkoutButton.getText();
        String expectedCheckoutText = "Checkout ( 4 )\n(current)";

        assertEquals(checkoutCartText, expectedCheckoutText, "Checkout cart count does not match expected value.");
        logger.info("Cart count is: " + checkoutCartText);
    }
}
