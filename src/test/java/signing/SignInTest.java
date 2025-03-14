package signing;

import base.BaseTest;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignInTest extends BaseTest {

    @Test
    public void signInWithIncorrectCredentials() {
        // Test case to verify error message for incorrect credentials.
        enterCredentials("stanny", "password123"); // Calls the method to enter credentials.
        clickSignInButton(); // Calls the method to click the sign-in button.
        verifyErrorMessage("* Incorrect username or password"); // Calls the method to verify the error message.
    }

    private void enterCredentials(String username, String password) {
        // Method to enter username and password.
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputUsername"))).sendKeys(username);
        // Explicit wait to ensure the username input field is visible before sending keys.
        driver.findElement(By.name("inputPassword")).sendKeys(password);
        // Finds the password input field by name and sends the password.
    }

    private void clickSignInButton() {
        // Method to click the sign-in button.
        driver.findElement(By.cssSelector("button[class='submit signInBtn']")).click();
        // Finds the sign-in button by CSS selector and clicks it.
    }

    private void verifyErrorMessage(String expectedMessage) {
        // Method to verify the error message.
        String errorMessage =
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p.error"))).getText();
        // Explicit wait to ensure the error message element is visible and then retrieves its text.
        System.out.println("Error message: " + errorMessage);
        // Prints the error message to the console for debugging.
        Assert.assertEquals(errorMessage, expectedMessage,
                "Error message is incorrect. Expected: '" + expectedMessage + "', but found: '" + errorMessage + "'");
        // Asserts that the actual error message matches the expected message. Provides a detailed error message if
        // the assertion fails.
    }
}
