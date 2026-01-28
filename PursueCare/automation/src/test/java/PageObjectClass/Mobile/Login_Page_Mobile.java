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
    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='email-input']")
    /** Email input field - iOS */
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[@name='email']")
    public WebElement emailId;
	
    /** Password input field - Android */
    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='password-input']")
    /** Password input field - iOS */
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeSecureTextField[@name='password']")
    public WebElement password;
	
    /** Login submit button - Android */
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Login']")
    /** Login submit button - iOS */
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Login']")
    public WebElement loginButton;
 
    /** Validation message for invalid credentials - Android */
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text, 'Invalid')]")
    /** Validation message for invalid credentials - iOS */
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name, 'Invalid')]")
    public WebElement loginValidationMessage;

    /** Empty email validation message - Android */
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Email is required\"]")
    public WebElement EmptyemailValidation;

    /** Empty password validation message - Android */
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Password is required\"]")
    public WebElement EmptyPasswordValidation;

    /** Invalid email format validation message - Android */
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Please enter a valid email address\"]")
    public WebElement InvalidEmailValidation;

    /** Password too short validation message - Android */
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Password is too short\"]")
    public WebElement PasswordTooShortValidation;

    /** Welcome text "Welcome to PursueCare" - Android */
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Welcome to PursueCare\"]")
    public WebElement pursuecaretext;

    /** Welcome back text - Android */
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Welcome back,\"]")
    public WebElement WelcomeBackText;

    /** "Let's get started!" text - Android */
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Let's get started!\"]")
    public WebElement LetsStartedText;

    /** Forgot Password link - Android */
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Forgot Password ?\"]")
    public WebElement ForgotPassword;

    /** Sign Up text - Android */
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Sign Up\"]")
    public WebElement SignUpText;

    /** Version text - Android */
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Version - 2.7.3\"]")
    public WebElement VersionText;

    /** About Us text - Android */
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"About Us\"]")
    public WebElement AboutUsText;

    /** Contact Us text - Android */
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Contact Us\"]")
    public WebElement ContactUsText;

    /** Change Language text - Android */
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"English\"]")
    public WebElement ChangeLanguage;

    // ==================== Forgot Password Elements ====================
    
    /** Password reset instruction text - Android */
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Please provide the email or phone number linked to your account for a password reset link.\"]")
    public WebElement ForgotPasswordInstructionText;

    /** Enter email/phone for forgot password - Android */
    @AndroidFindBy(xpath = "//android.widget.EditText")
    public WebElement EnterForgotPassword;

    /** Request reset link button - Android */
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Request a reset link\"]")
    public WebElement ClickForgotPassword;

    // ==================== Dialog/Popup Elements ====================
    
    /** System button (android:id/button2) - Android */
    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id=\"android:id/button2\"]")
    public WebElement SystemButton2;

    /** Got it button (ViewGroup) - Android */
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Got it!\"]")
    public WebElement ClickGotItButton;

    /** Got it text - Android */
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Got it!\"]")
    public WebElement GotItText;

    // ==================== Navigation/Menu Elements ====================
    
    /** Breadcrumb menu - Android */
    @AndroidFindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup[1]/com.horcrux.svg.SvgView/com.horcrux.svg.GroupView/com.horcrux.svg.PathView")
    public WebElement BreadcrumbMenu;

    /** Sign Out text - Android */
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Sign Out\"]")
    public WebElement SignOut;

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
