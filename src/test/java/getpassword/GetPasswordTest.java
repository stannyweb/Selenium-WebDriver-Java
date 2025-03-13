package getpassword;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GetPasswordTest extends BaseTest {

    @Test
    public void getPasswordTest() throws InterruptedException {
        clickForgotPasswordLinkOnLoginPage();
        clickResetLogin();
        String password = getPasswordString();
        clickGoToLoginBtn();
        enterCredentials("Stan", password);
        clickCheckboxes();
        clickSignInButton();
        validateLoginScreen();
    }

    private void clickForgotPasswordLinkOnLoginPage() {
        WebElement element = driver.findElement(By.xpath("//a[text()='Forgot your password?']"));
        element.click();
    }

    private void clickResetLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='reset-pwd-btn']"))).click();
    }

    private String getPasswordString() {
        String passwordText =
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("infoMsg"))).getText();
        String[] passwordArr = passwordText.split("'");
        return passwordArr[1];
    }

    private void clickGoToLoginBtn() {
        driver.findElement(By.xpath("//button[@class='go-to-login-btn']")).click();
    }

    private void enterCredentials(String username, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputUsername"))).sendKeys(username);
        driver.findElement(By.name("inputPassword")).sendKeys(password);
    }

    private void clickCheckboxes() throws InterruptedException {
        Thread.sleep(1000);
        WebElement checkBox = wait.until(ExpectedConditions.elementToBeClickable(By.id("chkboxOne")));
        checkBox.click();
        WebElement checkBox2 = wait.until(ExpectedConditions.elementToBeClickable(By.id("chkboxTwo")));
        checkBox2.click();
    }

    private void clickSignInButton() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[class='submit signInBtn']"))).click();
    }

    private void validateLoginScreen() {
        String validateSuccessText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()" +
                "='You are successfully logged in.']"))).getText();

        assertEquals(validateSuccessText, "You are successfully logged in.", "Incorrect login message!");
        System.out.println(validateSuccessText);
    }

}
