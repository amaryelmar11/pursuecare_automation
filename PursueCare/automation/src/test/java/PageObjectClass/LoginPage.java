package PageObjectClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import BasePage.BasePageClass;

/**
 * Page Object Class for Login functionality.
 * This class contains all the web elements and methods related to:
 * - User login (email and password)
 * - Login validation messages
 * - Forgot password functionality
 * - Remember me checkbox
 * - Logout functionality
 * 
 * @author Automation Team
 */
public class LoginPage extends BasePageClass {

    WebDriver driver;

    /**
     * Constructor to initialize the LoginPage.
     * 
     * @param driver WebDriver instance
     */
    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    // ==================== Login Elements ====================
    
    /** Email input field */
    @FindBy(xpath = "//input[@formcontrolname='email']")
    public WebElement emailId;
	
    /** Password input field */
    @FindBy(xpath = "//input[@formcontrolname='password']")
    public WebElement password;
	
    /** Login submit button */
    @FindBy(xpath = "//button[@type='submit']")
    public WebElement Loginbtn;
 
    /** Validation message for invalid username/password */
    @FindBy(xpath = "//div[normalize-space()='Invalid Username And Password']")
    public WebElement Login_Validations;

    /** Validation message for empty email/password */
    @FindBy(xpath = "//div[normalize-space()='Email address and Password not valid !']")
    public WebElement Login_Val_emptyUserPass;

    // ==================== Forgot Password Elements ====================
    
    /** Forgot Password link */
    @FindBy(xpath = "//a[normalize-space()='Forgot Password?']")
    public WebElement Forgot_Password;

    /** Email input field in forgot password form */
    @FindBy(xpath = "//input[@formcontrolname='email']")
    public WebElement email_FgtPwd;

    /** Continue button in forgot password form */
    @FindBy(xpath = "//span[normalize-space()='Continue']")
    public WebElement ContinueBtn;

    /** Success message after sending forgot password email */
    @FindBy(xpath = "//div[normalize-space()='Please check your inbox / spam, we have sent you reset password link.']")
    public WebElement FgtPwdMailSent;

    // ==================== Remember Me Elements ====================
    
    /** Remember me checkbox */
    @FindBy(xpath = "//label[normalize-space()='Remember me']//span[@class='check']")
    public WebElement clickRemeberMeChkbx;

    // ==================== Logout Elements ====================
    
    /** User image/avatar to open logout menu */
    @FindBy(xpath = "//img[@alt='User']")
    public WebElement clickonImageforLogout;

    /** Logout button/menu item */
    @FindBy(xpath = "//mat-icon[normalize-space()='power_settings_new']")
    public WebElement clickonLogout;

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
    public void clickLoginbtn() {
        Loginbtn.click();
    }

    /**
     * Gets the validation message text for invalid login credentials.
     * 
     * @return Validation message text
     */
    public String Login_Validations() {
        String val = Login_Validations.getText();
        return val;
    }

    /**
     * Gets the validation message text for empty email/password.
     * 
     * @return Validation message text
     */
    public String Login_Val_emptyUserPass() {
        String val = Login_Val_emptyUserPass.getText();
        return val;
    }
}
