package PageObjectClass;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import BasePage.BasePageClass;

public class Settings_Page_Provider extends BasePageClass{

    WebDriver driver;

    public Settings_Page_Provider(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
    }

    // Provider Change Password
    @FindBy(xpath="//span[normalize-space()='Settings']")
    public WebElement SelectSettingsProviderDash;

    @FindBy(xpath="//a[@href='/provider/users/changepassword']")
    public WebElement ClickChangePassword;

    @FindBy(xpath="//mat-label[normalize-space()='Current Password']/parent::label/following-sibling::input")
    public WebElement EnterCurrentPass;

    @FindBy(xpath="//mat-label[normalize-space()='New Password']/parent::label/following-sibling::input")
    public WebElement EnterNewPass;

    @FindBy(xpath="//mat-label[normalize-space()='Confirm Password']/parent::label/following-sibling::input")
    public WebElement EnterConfirmPass;

    @FindBy(xpath="//span[normalize-space()='Save']")
    public WebElement ClikcSaveBtn;

    @FindBy(xpath="//div[@class='cdk-overlay-container']/div")
    public WebElement GetChangePassToast;

    // Click on the Time Block

    @FindBy(xpath="//a[@href='/provider/users/providertimeoffs']")
    public WebElement ClickTimeBlockDash;

    @FindBy(xpath="//input[@name='Search']")
    public WebElement ClickSearchInput;

    @FindBy(xpath="//span[normalize-space()='Provider'][1]/span")
    public WebElement UserRoleByDefaultProvider;

    @FindBy(xpath="//mat-label[normalize-space()='Time Off Type']")
    public WebElement SelectTimeOffFilter;

    @FindBy(xpath="//span[normalize-space()='Vacation (PTO/UTO)']")
    public WebElement SelectVacationTypeTimeOff;

    @FindBy(xpath="//mat-icon[normalize-space()='add']/parent::button")
    public WebElement SelectAddButton;

    
    @FindBy(xpath="//div[@class='sidebar-userpic-name']")
    public WebElement GetLoggedInProviderName;

    @FindBy(xpath="//mat-icon[normalize-space()='edit']/parent::button")
    public WebElement ClickEditButton;

    
    @FindBy(xpath="//div[normalize-space()='Edit Provider Time Block']/parent::div/div/div/div")
    public WebElement VerifyEditBtn;

    @FindBy(xpath="//mat-icon[normalize-space()='visibility']/parent::button")
    public WebElement ClickViewButton;

    @FindBy(xpath="//div[normalize-space()='View Provider Time Block']/parent::div/div/div/div")
    public WebElement VerifyViewButton;

    @FindBy(xpath="//mat-icon[normalize-space()='delete']/parent::button")
    public WebElement ClickDeleteButton;

    @FindBy(xpath="//span[normalize-space()='Delete']/parent::button")
    public WebElement ClickDeleteButtonPopup;

    // Date Related
    @FindBy(xpath="//mat-label[normalize-space()='Start Date']/ancestor::div[2]/div[2]/mat-datepicker-toggle/button")
    public WebElement ClickStartDateButton;

    // Chat Avilabilty Related

    @FindBy(xpath="//mat-error[normalize-space()='Please select weekdays']")
    public WebElement WeekDaysValidation;

    @FindBy(xpath="//mat-error[normalize-space()='Please enter slot start time.']")
    public WebElement StartTimeValidation;

    @FindBy(xpath="//mat-error[normalize-space()='Please enter slot end time.']")
    public WebElement EndTimeValidation;


    @FindBy(xpath="//h6[normalize-space()='Working Days']")
    public WebElement ValidateEorkingDaysText;

    @FindBy(xpath="//h6[normalize-space()='Away message preferences']")
    public WebElement ValidateAwayMsgTxt;

    @FindBy(xpath="//a[@href='/provider/settings/chatavailabilitysetting']")
    public WebElement ClickChatAvilability;

    @FindBy(xpath="//mat-label[normalize-space()='Days of the week']")
    public WebElement ClickDaysOfWeek;

    @FindBy(xpath="//mat-label[normalize-space()='Start Time']")
    public WebElement ClickStartTime;

    @FindBy(xpath="//mat-label[normalize-space()='End Time']")
    public WebElement ClickEndTime;

    @FindBy(xpath="//span[normalize-space()='Save']")
    public WebElement SelectSaveBtn;

    @FindBy(xpath="//mat-checkbox[@formcontrolname='chkboxDayOfWeek']/div/div")
    public WebElement SelectAwayMessageChkbx;

    @FindBy(xpath="//mat-slide-toggle[@mattooltip='Away Setting ON / OFF']/div/button")
    public WebElement ClickAwayMsg;

    @FindBy(xpath="//span[normalize-space()='Yes']/parent::button")
    public WebElement ClickYesOnPopup;

    @FindBy(xpath="//div[@class='chat-comment d-table max-w-p100 bg-primary-light px-15 py-10 rounded10 away-box-margin ng-star-inserted']")
    public WebElement AwayMsgChatbox;

    // Resource Center Related
    @FindBy(xpath="//a[@href='/provider/users/ResourceCenter']")
    public WebElement ClickResourceCenter;

    @FindBy(xpath="//input[@formcontrolname='name']")
    public WebElement ClickOnName;

    @FindBy(xpath="//mat-radio-button[@value='link']/div/div/div")
    public WebElement ClickLinkRadioBtn;

    @FindBy(xpath="//input[@formcontrolname='baseurlControl']")
    public WebElement InputURL;

    @FindBy(xpath="//textarea[@formcontrolname='note']")
    public WebElement EnterNotesText;

    @FindBy(xpath="//input[@placeholder='Search']")
    public WebElement SearcBox;

    @FindBy(xpath="//button[@aria-label='Clear filters']")
    public WebElement ClickResetBtn;

    @FindBy(xpath="//button[@mattooltip='Edit Resource']")
    public WebElement ClickEditResourceBtn;

    @FindBy(xpath="//button[@mattooltip='Delete Resource']")
    public WebElement ClickDeleteResourceBtn;


    public void clickDayCheckbox(String dayName) {
        String xpath = "//span[normalize-space()='" + dayName + "']";
        WebElement checkbox = driver.findElement(By.xpath(xpath));
        WebDriverWait  wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(checkbox));
        checkbox.click();
    }
    


}
