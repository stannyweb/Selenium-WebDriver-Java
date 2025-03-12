package org.stannyweb;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class SignInTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://rahulshettyacademy.com/locatorspractice/");

    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) driver.quit();
    }

    @Test
    public void signInWithIncorrectCredentials() {
        enterCredentials("stanny", "password123");
        clickSignInButton();
        verifyErrorMessage("* Incorrect username or password");
    }

    private void enterCredentials(String username, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputUsername"))).sendKeys(username);
        driver.findElement(By.name("inputPassword")).sendKeys(password);
    }

    private void clickSignInButton() {
        driver.findElement(By.cssSelector(".submit.signInBtn")).click();
    }

    private void verifyErrorMessage(String expectedMessage) {
        String errorMessage =
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p.error"))).getText();
        System.out.println("Error message: " + errorMessage);
        Assert.assertEquals(errorMessage, expectedMessage,
                "Error message is incorrect. Expected: '" + expectedMessage + "', but found: '" + errorMessage + "'");
    }

}
