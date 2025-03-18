package forms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GenderDropdown {

    private static final By GENDER_DROPDOWN_LOCATOR = By.id("exampleFormControlSelect1");
    private static final String GENDER_MALE = "Male";
    private static final String GENDER_FEMALE = "Female";

    private final WebDriver driver;
    private final Select select;
    private final WebDriverWait wait;


    public GenderDropdown(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        WebElement genderDropdown =
                wait.until(ExpectedConditions.elementToBeClickable((GENDER_DROPDOWN_LOCATOR)));
        this.select = new Select(genderDropdown);
    }

    public void selectMale() {
        select.selectByVisibleText(GENDER_MALE);
    }

    public void selectFemale() {
        select.selectByVisibleText(GENDER_FEMALE);
    }

    public String getSelectedGender() {
        return select.getFirstSelectedOption().getText();
    }
}
