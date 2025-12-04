package PageObjectClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import BasePage.BasePageClass;

public class LoginPage extends BasePageClass{

    WebDriver driver;

public LoginPage(WebDriver driver)
 {
    super(driver);
    this.driver = driver;
 }

@FindBy(xpath="//input[@formcontrolname='email']")
public WebElement emailId;
	
@FindBy(xpath="//input[@formcontrolname='password']")
public WebElement password;
	
@FindBy(xpath="//button[@type='submit']")
public WebElement Loginbtn;
 
@FindBy(xpath="//div[normalize-space()='Invalid Username And Password']")
public WebElement Login_Validations;


@FindBy(xpath="//div[normalize-space()='Email address and Password not valid !']")
public WebElement Login_Val_emptyUserPass;

// ForGot Password Functionality
@FindBy(xpath="//a[normalize-space()='Forgot Password?']")
public WebElement Forgot_Password;

@FindBy(xpath="//input[@formcontrolname='email']")
public WebElement email_FgtPwd;

@FindBy(xpath="//span[normalize-space()='Continue']")
public WebElement ContinueBtn;

@FindBy(xpath="//div[normalize-space()='Please check your inbox / spam, we have sent you reset password link.']")
public WebElement FgtPwdMailSent;

// Remember Me
@FindBy(xpath="//label[normalize-space()='Remember me']//span[@class='check']")
public WebElement clickRemeberMeChkbx;

//Logout Functionality Related

@FindBy(xpath = "//img[@alt='User']")
public WebElement clickonImageforLogout;

@FindBy(xpath = "//mat-icon[normalize-space()='power_settings_new']")
public	WebElement clickonLogout;

 public void enterEmailId(String email)
 {
   emailId.clear();
    emailId.sendKeys(email);
 }
 
 public void enterPassword(String pass)
 {
   password.clear();
    password.sendKeys(pass);
 }

 public void clickLoginbtn()
 {
    Loginbtn.click();
 }

 public String Login_Validations()
 {
   String val = Login_Validations.getText();
   return val;
 }


 public String Login_Val_emptyUserPass()
 {
   String val = Login_Val_emptyUserPass.getText();
   return val;
 }



 
}
