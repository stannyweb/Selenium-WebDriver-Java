package forms;

import dropdowns.StaticDropdownTest;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

/**
 * Test class for verifying the functionality of basic form controls.
 */
public class FormControlsTest extends StaticDropdownTest {

    private static final By ONE_WAY_OPTION = By.id("ctl00_mainContent_rbtnl_Trip_0");
    private static final By ROUND_TRIP_OPTION = By.id("ctl00_mainContent_rbtnl_Trip_1");
    private static final By DATE_CALENDAR = By.id("ctl00_mainContent_view_date1");
    private static final By DATE = By.cssSelector("a[class='ui-state-default ui-state-active']");
    private static final By FRIENDS_FAMILY_CHECKBOX = By.cssSelector("input[id*='friendsandfamily']");


    /**
     * Test for verifying basic form controls.
     */
    @Test
    public void formControlTest() {
        selectTravelOption(ROUND_TRIP_OPTION);
        clickCalendar(DATE_CALENDAR);
        setTravelDate(DATE);
        selectCheckboxes(FRIENDS_FAMILY_CHECKBOX);

        assertTravelOptionSelected();
        assertDateSelected();
        assertFriendsFamilyCheckboxSelected();
    }

    /**
     * Selects a travel option (radio button).
     *
     * @param locator Locator of the radio button.
     */
    private void selectTravelOption(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    /**
     * Clicks on the calendar.
     *
     * @param locator Locator of the calendar.
     */
    private void clickCalendar(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    /**
     * Sets the travel date from the calendar.
     *
     * @param locator Locator of the date.
     */
    private void setTravelDate(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    /**
     * Selects a checkbox.
     *
     * @param friendsFamily Locator of the checkbox.
     */
    private void selectCheckboxes(By friendsFamily) {
        try {
            WebElement friendsFamilyCheckbox = wait.until(ExpectedConditions.visibilityOfElementLocated(friendsFamily));
            friendsFamilyCheckbox.click();
        } catch (TimeoutException e) {
            System.out.println("Friends Family checkbox not found");
        }
    }

    /**
     * Asserts that the travel option is selected.
     */
    private void assertTravelOptionSelected() {
        assertTrue(driver.findElement(ROUND_TRIP_OPTION).isSelected(), "Round trip option should be selected.");
    }

    /**
     * Asserts that the date is selected from the calendar.
     */
    private void assertDateSelected() {
        assertTrue(driver.findElement(DATE).getAttribute("class").contains("ui-state-active"), "Date should be " +
                "selected.");
    }

    /**
     * Asserts that the Friends & Family checkbox is selected.
     */
    private void assertFriendsFamilyCheckboxSelected() {
        assertTrue(driver.findElement(FRIENDS_FAMILY_CHECKBOX).isSelected(), "Friends & Family checkbox should be " +
                "selected.");
    }
}
