package dropdowns;

import base.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertEquals;

public class StaticDropdownTest extends BaseTest {
    private Select select;

    @Test
    public void staticDropdownTest() {
        selectCurrencyByIndex(1);
        validateSelectedTextOption();
    }

    @BeforeMethod
    @Override
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
    }

    private void selectCurrencyByIndex(int index) {
        WebElement currencyDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(
                "ctl00_mainContent_DropDownListCurrency")));
        select = new Select(currencyDropdown);
        select.selectByIndex(index);
    }

    private String getSelectedCurrencyText() {
        return select.getFirstSelectedOption().getText();
    }

    private void validateSelectedTextOption() {
        String expectedText = "INR";
        String errMessage = "Incorrect currency value";
        String actualText = getSelectedCurrencyText();
        assertEquals(actualText, expectedText, errMessage);
        System.out.println(expectedText);
    }

    @AfterMethod
    @Override
    public void tearDown() {
        super.tearDown();
    }
}
