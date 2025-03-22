package ecommerce;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.HomePage;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

import static org.testng.Assert.*;

public class ShoppingList {

    private WebDriver driver;
    private WebDriverWait wait;
    private ProductPage productPage;
    private HomePage homePage;

    private final String baseURL = "https://rahulshettyacademy.com/seleniumPractise/";

    private final Logger logger = Logger.getLogger(ShoppingList.class.getName());

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get(baseURL);
        homePage = new HomePage(driver);
        productPage = new ProductPage(driver);
    }

    @Test
    public void shoppingListItemsTest() {
        selectShoppingListItems();
        homePage.selectCartIcon();
        CartPage cartPage = homePage.goToCartPage()
                .enterPromoCode()
                .applyDiscountWithPromoCode()
                .validatePromoCodeMessage();
    }

    private void selectShoppingListItems() {

        List<String> desiredProducts = List.of("Cucumber", "Beetroot", "Tomato", "Carrot");
        List<WebElement> productNames = productPage.getProductNames();
        int addedProductsCount = 0;

        for (int i = 0; i < productNames.size(); i++) {
            String[] items = productNames.get(i).getText().split("-");
            String productName = items[0].trim();

            if (desiredProducts.contains(productName)) {
                try {
                    productPage.clickAddToCartButton(i);
                    addedProductsCount++;
                    logger.info("Product " + productName + " added to cart.");

                } catch (NoSuchElementException | TimeoutException e) {
                    logger.severe("Failed to add product " + productName + " to cart: " + e.getMessage());
                    fail("Failed to add product " + productName + " to cart.");
                }
            }
        }
        assertEquals(addedProductsCount, desiredProducts.size(), "Not all products were added to cart");
    }
}
