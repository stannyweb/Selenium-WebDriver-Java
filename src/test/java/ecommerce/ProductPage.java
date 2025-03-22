package ecommerce;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private final By ADD_TO_CART_BUTTON_LOCATOR = By.xpath("//div[@class='product-action']/button");
    private final By PRODUCT_NAME_LIST = By.cssSelector("h4.product-name");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public List<WebElement> getProductNames() {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(PRODUCT_NAME_LIST));
    }

    public void clickAddToCartButton(int index) {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ADD_TO_CART_BUTTON_LOCATOR)).get(index).click();
    }
}
