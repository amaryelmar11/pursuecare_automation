package PageObjectClass.Mobile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import BasePage.MobileBasePageClass;
import java.time.Duration;

/**
 * Page Object Class for Mobile Login functionality.
 * This class contains all the mobile elements and methods related to:
 * - User login (email and password)
 * - Login validation messages
 * - Mobile-specific interactions
 * 
 * Note: Use @AndroidFindBy for Android-specific locators
 *       Use @iOSXCUITFindBy for iOS-specific locators
 *       Use @FindBy for common locators that work on both platforms
 * 
 * @author Automation Team
 */
public class Login_Page_Mobile extends MobileBasePageClass {

    AppiumDriver driver;

    /**
     * Constructor to initialize the Login_Page_Mobile.
     * 
     * @param driver AppiumDriver instance
     */
    public Login_Page_Mobile(AppiumDriver driver) {
        super(driver);
        this.driver = driver;
        // Initialize PageFactory with AppiumFieldDecorator for mobile
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    // ==================== Login Elements ====================
    
    /** Email input field - Android */
    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='email']")
    /** Email input field - iOS */
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[@name='email']")
    public WebElement emailId;
	
    /** Password input field - Android */
    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='password']")
    /** Password input field - iOS */
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeSecureTextField[@name='password']")
    public WebElement password;
	
    /** Login submit button - Android */
    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id='loginButton']")
    /** Login submit button - iOS */
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Login']")
    public WebElement loginButton;
 
    /** Validation message for invalid credentials - Android */
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text, 'Invalid')]")
    /** Validation message for invalid credentials - iOS */
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name, 'Invalid')]")
    public WebElement loginValidationMessage;

    // ==================== Login Methods ====================
    
    /**
     * Enters email address in the email input field.
     * 
     * @param email Email address to enter
     */
    public void enterEmailId(String email) {
        emailId.clear();
        emailId.sendKeys(email);
    }
 
    /**
     * Enters password in the password input field.
     * 
     * @param pass Password to enter
     */
    public void enterPassword(String pass) {
        password.clear();
        password.sendKeys(pass);
    }

    /**
     * Clicks the login button to submit the login form.
     */
    public void clickLoginButton() {
        loginButton.click();
    }

    /**
     * Gets the validation message text for invalid login credentials.
     * 
     * @return Validation message text
     */
    public String getLoginValidationMessage() {
        return loginValidationMessage.getText();
    }

    /**
     * Performs complete login action.
     * 
     * @param email Email address
     * @param password Password
     */
    public void login(String email, String password) {
        enterEmailId(email);
        enterPassword(password);
        clickLoginButton();
    }
}
