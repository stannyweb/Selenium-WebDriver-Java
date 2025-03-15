package dropdowns;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class DestinationDynamicDropdown extends UpdatingDropdownTest {

    @Test
    public void dynamicDestinationDropdownTest() {
        getDestinationDropdown();
        validateDestinationText();
    }


    private void getDestinationDropdown() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("(//input[@class='css-1cwyjr8 r-homxoj r-ubezar r-10paoce r-13qz1uu'])[1]"))).click();

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[text()='International']"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[text()='Bangkok']"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[text()='Bagdogra']"))).click();
    }

    private void validateDestinationText() {
        String errMessage = "Incorrect city value";
        String expectedCityValueBangkok = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//input[@value='Bangkok (BKK)']"))).getDomAttribute("value");

        String expectedCityValueBagdogra = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//input[@value='Bagdogra (IXB)']"))).getDomAttribute("value");

        assertEquals(expectedCityValueBangkok, "Bangkok (BKK)", errMessage);
        assertEquals(expectedCityValueBagdogra, "Bagdogra (IXB)", errMessage);

        System.out.println("Bangkok value: " + expectedCityValueBangkok);
        System.out.println("Bagdogra value: " + expectedCityValueBagdogra);
    }
}
