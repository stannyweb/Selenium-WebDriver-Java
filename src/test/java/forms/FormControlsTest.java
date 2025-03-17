package forms;

import dropdowns.StaticDropdownTest;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class FormControlsTest extends StaticDropdownTest {

    private static final By ONE_WAY_OPTION = By.id("ctl00_mainContent_rbtnl_Trip_0");
    private static final By ROUND_TRIP_OPTION = By.id("ctl00_mainContent_rbtnl_Trip_1");
    private static final By DATE_CALENDAR = By.id("ctl00_mainContent_view_date1");
    private static final By DATE = By.cssSelector("a[class='ui-state-default ui-state-active']");
    private static final By FRIENDS_FAMILY_CHECKBOX = By.cssSelector("input[id*='friendsandfamily']");
//    private static final By SENIOR_CITIZEN_CHECKBOX = By.cssSelector("input[id*='SeniorCitizenDiscount']");


    @Test
    public void formControlTest() {
        selectTravelOption(ROUND_TRIP_OPTION);
        clickCalendar(DATE_CALENDAR);
        setTravelDate(DATE);
        selectCheckboxes(FRIENDS_FAMILY_CHECKBOX);

        assertTrue(driver.findElement(ROUND_TRIP_OPTION).isSelected(), "Round trip option should be selected.");
        assertTrue(driver.findElement(FRIENDS_FAMILY_CHECKBOX).isSelected(), "Friends & Family checkbox should be " +
                "selected.");
//        assertTrue(driver.findElement(SENIOR_CITIZEN_CHECKBOX).isSelected(), "Senior Citizen checkbox should be " +
//                "selected.");
    }

    private void selectTravelOption(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    private void clickCalendar(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    private void setTravelDate(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    private void selectCheckboxes(By friendsFamily) {
        try {
            WebElement friendsFamilyCheckbox = wait.until(ExpectedConditions.visibilityOfElementLocated(friendsFamily));
            friendsFamilyCheckbox.click();
        } catch (TimeoutException e) {
            System.out.println("Friends Family checkbox not found");
        }
    }
}
