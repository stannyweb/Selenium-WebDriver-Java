package signing;

import base.BaseTest;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignInTest extends BaseTest {


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
        driver.findElement(By.cssSelector("button[class='submit signInBtn']")).click();
    }

    private void verifyErrorMessage(String expectedMessage) {
        String errorMessage =
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p.error"))).getText();
        System.out.println("Error message: " + errorMessage);
        Assert.assertEquals(errorMessage, expectedMessage,
                "Error message is incorrect. Expected: '" + expectedMessage + "', but found: '" + errorMessage + "'");
    }

}
