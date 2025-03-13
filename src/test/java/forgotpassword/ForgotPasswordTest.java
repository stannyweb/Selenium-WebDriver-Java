package forgotpassword;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ForgotPasswordTest extends BaseTest {


    @Test
    public void forgotPasswordTest() {
        clickForgotPasswordLinkOnLoginPage();
        enterCredentials("stan", "stannyweb@gmail.com", 1234567);
        clickResetLogin();
        validateTransitionBetweenSections("Sign in");
    }

    private void clickForgotPasswordLinkOnLoginPage() {
        WebElement element = driver.findElement(By.xpath("//a[text()='Forgot your password?']"));
        element.click();
    }

    private void enterCredentials(String username, String email, int phoneNumber) {
        String phoneNumberSTR = String.valueOf(phoneNumber);

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@placeholder='Name']"))).sendKeys(username);
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@placeholder='Email']"))).sendKeys(email);
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@placeholder='Phone Number']"))).sendKeys(phoneNumberSTR);

    }

    private void clickResetLogin() {
        driver.findElement(By.xpath("//button[@class='go-to-login-btn']")).click();
    }

    private void validateTransitionBetweenSections(String expectedTitle) {
        String title =
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Sign in']"))).getText();
        Assert.assertEquals(expectedTitle, "Sign in", "Incorrect Title");
        System.out.println("Title: " + title);
    }


}
