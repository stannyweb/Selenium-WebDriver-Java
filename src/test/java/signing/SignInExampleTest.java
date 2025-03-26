package signing;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ShopPage;
import pages.SignInPage;

import java.time.Duration;

public class SignInExampleTest {

    private final String baseURL = "https://rahulshettyacademy.com/loginpagePractise/";
    private WebDriver driver;
    private WebDriverWait wait;
    private SignInPage signInPage;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();

        driver.get(baseURL);

        signInPage = new SignInPage(driver, wait);
    }

    @Test
    public void userSignInTest() {
        signInPage.performSignIn();
        ShopPage clickSignInButton = signInPage.clickSignInButton();
        clickSignInButton.addItemsToCartAndVerifyCheckout();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null)
            driver.quit();
    }
}
