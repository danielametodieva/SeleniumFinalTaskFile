import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.apache.commons.lang3.RandomStringUtils;


import java.time.Duration;

public class RegisterAccount {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        this.driver = new ChromeDriver();
        this.driver.get("http://shop.pragmatic.bg");
        this.driver.manage().window().maximize();
    }

//    @AfterMethod
//    public void tearDown(){
//        driver.quit();
//    }
    @Test
    public void testRegisterAccount(){
        this.clickOnMyAccount();
        this.clickOnRegister();
        this.fillForm();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));

        Assert.assertEquals(successMessage.getText(), "Your Account Has Been Created!");
    }

    private void clickOnMyAccount() {
        WebElement myAccountLink = driver.findElement(By.cssSelector("#top-links a[title='My Account']"));
        myAccountLink.click();
    }

    private void clickOnRegister() {
        WebElement registerLink = driver.findElement(By.cssSelector("#top-links a[title='My Account'] + ul li:first-child a"));
        registerLink.click();
    }

    private void fillForm() {
        WebElement firstNameInput = driver.findElement(By.name("firstname"));
        firstNameInput.sendKeys("Daniela");

        WebElement lastNameInput = driver.findElement(By.name("lastname"));
        lastNameInput.sendKeys("Metodieva");

        String emailPrefix = RandomStringUtils.randomAlphabetic(10);
        String emailSuffix = RandomStringUtils.randomAlphabetic(3);
        String emailAddress = emailPrefix + "@" + emailSuffix + ".com";
        WebElement emailInput = driver.findElement(By.name("email"));
        emailInput.sendKeys(emailAddress);

        WebElement telephoneInput = driver.findElement(By.name("telephone"));
        telephoneInput.sendKeys("0878680598");

        WebElement passwordInput = driver.findElement(By.name("password"));
        passwordInput.sendKeys("nqmaparola");

        WebElement confirmPasswordInput = driver.findElement(By.name("confirm"));
        confirmPasswordInput.sendKeys("nqmaparola");

        WebElement privacyPolicyCheckbox = driver.findElement(By.name("agree"));
        privacyPolicyCheckbox.click();

        WebElement continueButton = driver.findElement(By.cssSelector("input[type='submit']"));
        continueButton.click();
    }


}
