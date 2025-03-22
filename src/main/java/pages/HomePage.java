package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;
    private String baseURL;

    private final By CART_ICON = By.cssSelector("img[alt='Cart']");
    private final By PROCEED_TO_CHECKOUT_BUTTON = By.xpath("//button[text()='PROCEED TO CHECKOUT']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    public void selectCartIcon() {
        wait.until(ExpectedConditions.elementToBeClickable(CART_ICON)).click();
    }

    public CartPage goToCartPage() {
        wait.until(ExpectedConditions.elementToBeClickable(PROCEED_TO_CHECKOUT_BUTTON)).click();
        return new CartPage(driver, wait);

    }


}
