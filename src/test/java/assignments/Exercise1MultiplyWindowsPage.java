package assignments;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.Iterator;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class Exercise1MultiplyWindowsPage {

    private final By CLICK_HERE_LINK = By.xpath("//a[text()='Click Here']");
    private final By NEW_WINDOW_TEXT = By.xpath("//h3[text()='New Window']");
    private final By PARENT_TEXT = By.xpath("//h3[text()='Opening a new window']");
    private final String MISMATCH_TEXT = "The text is not valid from the expected";


    private WebDriver driver;
    private WebDriverWait wait;

    public Exercise1MultiplyWindowsPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void manageTextFromChildAndParentWindows() {
        clickHereLink();
        checkTextFromChildWindow();
    }

    private void clickHereLink() {
        wait.until(ExpectedConditions.elementToBeClickable(CLICK_HERE_LINK)).click();
    }

    private void checkTextFromChildWindow() {
        final String EXPECTED_CHILD_TEXT = "New Window";
        final String EXPECTED_PARENT_TEXT = "Opening a new window";

        Set<String> windowHandles = driver.getWindowHandles();
        Iterator<String> iterator = windowHandles.iterator();

        String parentWindow = iterator.next();
        String childWindow = "";

        while (iterator.hasNext()) {
            childWindow = iterator.next();
            driver.switchTo().window(childWindow);
        }

        String childText = wait.until(ExpectedConditions.visibilityOfElementLocated(NEW_WINDOW_TEXT)).getText();
        System.out.println("Child Text: " + childText);

        assertEquals(childText, EXPECTED_CHILD_TEXT, MISMATCH_TEXT);

        driver.switchTo().window(parentWindow);

        String parentText = wait.until(ExpectedConditions.visibilityOfElementLocated(PARENT_TEXT)).getText();
        System.out.println("Parent Text: " + parentText);

        assertEquals(parentText, EXPECTED_PARENT_TEXT, EXPECTED_PARENT_TEXT);
    }


}
