package dropdowns;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class AutoSuggestiveDropdownTest extends StaticDropdownTest {

    private static final String EXPECTED_COUNTRY = "Austria";
    private static final String SUGGESTED_COUNTRY_PREFIX = "Aus";
    private static final String AUTOSUGGEST_ID = "autosuggest";
    private static final String SUGGESTED_COUNTRIES_XPATH = "//li[@class='ui-menu-item']/a";
    private static final String INCORRECT_COUNTRY_ERROR_MESSAGE = "Selected incorrect country";

    @Test
    public void suggestedDropdownCountryTest() {
        String selectedCountry = selectCountryFromSuggestions();
        validateSuggestedCountry(selectedCountry);
    }

    private String selectCountryFromSuggestions() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(AUTOSUGGEST_ID))).sendKeys(SUGGESTED_COUNTRY_PREFIX);

        List<WebElement> suggestedCountries =
                wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.xpath(SUGGESTED_COUNTRIES_XPATH)));

        assertFalse(suggestedCountries.isEmpty(), "No suggested countries found");

        for (WebElement option : suggestedCountries) {
            if (option.getText().equalsIgnoreCase(EXPECTED_COUNTRY))
                option.click();
        }
        return EXPECTED_COUNTRY;
    }

    private void validateSuggestedCountry(String selectedCountry) {
        assertEquals(selectedCountry, EXPECTED_COUNTRY, INCORRECT_COUNTRY_ERROR_MESSAGE);
        System.out.println("Selected country: " + selectedCountry);
    }
}
