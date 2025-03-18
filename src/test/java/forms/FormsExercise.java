package forms;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.*;

public class FormsExercise {
    private final String BASE_URL = "https://rahulshettyacademy.com/angularpractice/";
    private final By NAME_INPUT = By.xpath("//input[@name='name']");
    private final By EMAIL_INPUT = By.xpath("//input[@name='email']");
    private final By PASSWORD_INPUT = By.id("exampleInputPassword1");
    private final By ICE_CREAM_CHECKBOX = By.id("exampleCheck1");


    private WebDriver driver;
    private WebDriverWait wait;
    private GenderDropdown genderDropdown;
    private EmploymentStatusRadioButton employmentStatusRadioButton;
    private DateInput dateInput;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(BASE_URL);

        genderDropdown = new GenderDropdown(driver, wait);
        employmentStatusRadioButton = new EmploymentStatusRadioButton(driver, wait);
        dateInput = new DateInput(driver, wait);
    }

    @Test
    public void formExerciceTest() {
        enterCredentials("Stan", "stannyweb@gmail.com", "123456");
        setICE_CREAM_CHECKBOX();
        genderDropdown.selectFemale();
        employmentStatusRadioButton.selectEmploymentStatus();
        dateInput.setDateInput("04011991");
        System.out.println("Selected gender: " + genderDropdown.getSelectedGender());

    }

    /**
     * Enter credentials for the input fields
     *
     * @param name
     * @param email
     * @param password
     */
    private void enterCredentials(String name, String email, String password) {
        wait.until(ExpectedConditions.presenceOfElementLocated(NAME_INPUT)).sendKeys(name);
        wait.until(ExpectedConditions.presenceOfElementLocated(EMAIL_INPUT)).sendKeys(email);
        wait.until(ExpectedConditions.presenceOfElementLocated(PASSWORD_INPUT)).sendKeys(password);
    }

    private void setICE_CREAM_CHECKBOX() {
        try {
            WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(ICE_CREAM_CHECKBOX));
            if (checkbox.isEnabled()) {
                checkbox.click();
                assertTrue(checkbox.isSelected(), "Checkbox should be selected");
            } else {
                System.out.println("Checkbox is disabled and cannot be selected.");
                assertFalse(checkbox.isSelected(), "Checkbox should not be selected if disabled!");
            }
        } catch (TimeoutException e) {
            System.out.println("Checkbox not found or not clickable within the timeout.");
            fail("Checkbox should exist.");
        }
    }


//    @AfterMethod
//    public void tearDown() {
//        if (driver != null)
//            driver.quit();
//    }
}
